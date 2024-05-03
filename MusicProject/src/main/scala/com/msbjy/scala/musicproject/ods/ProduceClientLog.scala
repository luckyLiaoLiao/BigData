package com.msbjy.scala.musicproject.ods

import com.alibaba.fastjson.{JSON, JSONObject}
import com.msbjy.scala.musicproject.base.PairRDDMultipleTextOutputFormat
import com.msbjy.scala.musicproject.common.ConfigUtils
import org.apache.spark.{HashPartitioner, SparkContext}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

/**
  * 读取 运维人员每天上传到服务器上的客户端日志，进行解析，并加载到 ODS层 的表
  *     加载的ODS表如下：
  *
  *  注意:运行此类需要指定参数是 指定当前日期，格式：20201231 ,
  *   第二个参数是指定对应的日志保存的目录，例如：hdfs://mycluster/logdata/currentday_clientlog.tar.gz ,
  *     本地直接指定：./MusicProject/data/currentday_clientlog.tar.gz
  */
object ProduceClientLog {

  private val localrun: Boolean = ConfigUtils.LOCAL_RUN
  private val hiveMetaStoreUris = ConfigUtils.HIVE_METASTORE_URIS
  private val hiveDataBase = ConfigUtils.HIVE_DATABASE
  private var sparkSession : SparkSession = _
  private var sc: SparkContext = _
  //下划线表示给一个默认初始值
  private val hdfsclientlogpath : String = ConfigUtils.HDFS_CLIENT_LOG_PATH
  private var clientLogInfos : RDD[String] = _

  def main(args: Array[String]): Unit = {
    /**
      * 先判断有没有传递一个参数
      *  指定当前log数据的日期时间 格式：20201231
      *  2.指定当前log的上传路径：例如 : ./MusicProject/data/currentday_clientlog.tar.gz
      */
    if(args.length<1){
      println(s"需要指定 数据日期")
      System.exit(1)
    }
    val logDate = args(0) // 日期格式 ： 年月日 20201231

    if(!localrun){
      sparkSession = SparkSession.builder()
        .master("local")
        .appName("ProduceClientLog")
        .config("hive.metastore.uris",hiveMetaStoreUris).enableHiveSupport().getOrCreate()
      //配置hive.metastore.uris，因为在本地读取hive的数据时，必须配置这个才能连接到hive的metastore
      //enableHiveSupport()想在sparksql里操作hive里面的数据，一定要开启hive的支持
      sc = sparkSession.sparkContext
      clientLogInfos = sc.textFile(
//        C:\Users\meira\Desktop\MusicProject\data\currentday_clientlog.tar.gz
//        data/currentday_clientlog.tar.gz这是才项目根目录开始的路径
        "file:///C:\\Users\\meira\\Desktop\\MusicProject\\data\\currentday_clientlog.tar.gz")
      //在本地执行加载的就是本地的文件
      //clientLogInfos是一个RDD（见上面的变量声明）
    }else{
      sparkSession = SparkSession.builder().appName("ProduceClientLog").enableHiveSupport().getOrCreate()
      //在集群执行时只要不设置master是local，也不需要连接hive的metastore，因为提交到集群里时本身就去找hive的环境变量。其他都是一样的
      sc = sparkSession.sparkContext
      clientLogInfos = sc.textFile(s"${hdfsclientlogpath}/currentday_clientlog.tar.gz")
      //在集群里执行用到了hdfs的路径
    }

    //组织K,V格式的数据 ： （客户端请求类型，对应的info信息）
    val tableNameAndInfos = clientLogInfos.map(line => line.split("&"))
      .filter(item => item.length == 6)
      .map(line => (line(2), line(3)))

    //获取当日出现的所有的“请求类型”
//    val allTableNames = tableNameAndInfos.keys.distinct().collect()

    //转换数据，将数据分别以表名的方式存储在某个路径中
    val value = tableNameAndInfos.map(tp => {
      val tableName = tp._1 //客户端请求类型
      val tableInfos = tp._2 //请求的json string
      if ("MINIK_CLIENT_SONG_PLAY_OPERATE_REQ".equals(tableName)) {
        val jsonObject: JSONObject = JSON.parseObject(tableInfos)
        val songid = jsonObject.getString("songid") //歌曲ID
        val mid = jsonObject.getString("mid") //机器ID
        val optrateType = jsonObject.getString("optrate_type") //0:点歌, 1:切歌,2:歌曲开始播放,3:歌曲播放完成,4:录音试听开始,5:录音试听切歌,6:录音试听完成
        val uid = jsonObject.getString("uid") //用户ID（无用户则为0）
        val consumeType = jsonObject.getString("consume_type") //消费类型：0免费；1付费
        val durTime = jsonObject.getString("dur_time") //总时长单位秒（operate_type:0时此值为0）
        val sessionId = jsonObject.getString("session_id") //局数ID
        val songName = jsonObject.getString("songname") //歌曲名
        val pkgId = jsonObject.getString("pkg_id") //套餐ID类型
        val orderId = jsonObject.getString("order_id") //订单号
        (tableName, songid + "\t" + mid + "\t" + optrateType + "\t" + uid + "\t" + consumeType + "\t" + durTime + "\t" + sessionId + "\t" + songName + "\t" + pkgId + "\t" + orderId)
      } else {
        //将其他表的infos 信息直接以json格式保存到目录中
        tp
      }
    })

//    logDate是当天日期，用于分区
    println(s"save path ${hdfsclientlogpath}/all_client_tables/${logDate}")

//    把tar.gz里面的日志数据都存到hive中了，MINIK_CLIENT_SONG_PLAY_OPERATE_REQ的按照\t分隔各个字段，其他的原封不动地存储
//    存放的地方不是一个表，而是一个文件，文件名就是下面的path
    value.saveAsHadoopFile(
      s"${hdfsclientlogpath}/all_client_tables/${logDate}",
      //      ${logDate}就是今天的日期（见开头的声明）
      classOf[String],
      classOf[String],
      classOf[PairRDDMultipleTextOutputFormat]
    )

    /**
      * 在Hive中创建 ODS层的 TO_CLIENT_SONG_PLAY_OPERATE_REQ_D 表
      */
    sparkSession.sql(s"use $hiveDataBase ")
    sparkSession.sql(
      """
        |CREATE EXTERNAL TABLE IF NOT EXISTS `TO_CLIENT_SONG_PLAY_OPERATE_REQ_D`(
        | `SONGID` string,  --歌曲ID
        | `MID` string,     --机器ID
        | `OPTRATE_TYPE` string,  --操作类型
        | `UID` string,     --用户ID
        | `CONSUME_TYPE` string,  --消费类型
        | `DUR_TIME` string,      --时长
        | `SESSION_ID` string,    --sessionID
        | `SONGNAME` string,      --歌曲名称
        | `PKG_ID` string,        --套餐ID
        | `ORDER_ID` string       --订单ID
        |)
        |partitioned by (data_dt string)
        |ROW FORMAT DELIMITED  FIELDS TERMINATED BY '\t'
        |LOCATION 'hdfs://mycluster/user/hive/warehouse/data/song/TO_CLIENT_SONG_PLAY_OPERATE_REQ_D'
      """.stripMargin)

//    load data inpath即把hdfs中的数据加载到TO_CLIENT_SONG_PLAY_OPERATE_REQ_D表中，按照dt分区
    sparkSession.sql(
      s"""
        | load data inpath
        | '${hdfsclientlogpath}/all_client_tables/${logDate}/MINIK_CLIENT_SONG_PLAY_OPERATE_REQ'
        | into table TO_CLIENT_SONG_PLAY_OPERATE_REQ_D partition (data_dt='${logDate}')
      """.stripMargin)

    println("**** all finished ****")
  }
}

