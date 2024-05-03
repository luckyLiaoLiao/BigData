package test

import com.alibaba.fastjson._
import com.msbjy.scala.musicproject.base.PairRDDMultipleTextOutputFormat
import org.apache.spark.{SparkConf, SparkContext}

object Test1 {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setMaster("local")
    conf.setAppName("test")
    val sc = new SparkContext(conf)
    val lines = sc.textFile("/currentday_clientlog.tar.gz")

    lines.filter(line=>{line.split("&").size==6})
      .map(line=>{(line.split("&")(2),line.split("&")(3))})
      .map(tp=>{
        val cmd:String = tp._1
        val jsonStr:String = tp._2
        if("MINIK_CLIENT_SONG_PLAY_OPERATE_REQ".equals(cmd)){
          val jsonObj = JSON.parseObject(jsonStr)
          val songID:String = jsonObj.getString("songid")
          val mid:String = jsonObj.getString("mid")
          val optrateType:String = jsonObj.getString("optrate_type")
          val uid:String = jsonObj.getString("uid")
          val consumeType:String = jsonObj.getString("consume_type")
          val playTime:String = jsonObj.getString("play_time")//playtime在建表时没有用到，所以这里也不要
          val durTime:String = jsonObj.getString("dur_time")
          val sessionId:String = jsonObj.getString("session_id")
          val songName:String = jsonObj.getString("songname")
          val pkgId:String = jsonObj.getString("pkg_id")
          val orderId:String = jsonObj.getString("order_id")
          (cmd,s"$songID\t$mid\t$optrateType\t$uid\t$consumeType\t$durTime\t$sessionId\t$songName\t$pkgId\t$orderId")
        }else{
          tp
        }
      }).saveAsHadoopFile("hdfs://mycluster/testdata" + System.currentTimeMillis(),classOf[String],classOf[String],classOf[PairRDDMultipleTextOutputFormat])
//    saveAsHadoopFile这个方法可以指定保存数据的格式；SaveAsTextFile这个方法不可以
  }

}
