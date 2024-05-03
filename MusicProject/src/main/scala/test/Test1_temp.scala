package test

import com.alibaba.fastjson
import com.alibaba.fastjson.{JSON, JSONObject}
import com.msbjy.scala.musicproject.base.PairRDDMultipleTextOutputFormat
import org.apache.spark.{SparkConf, SparkContext}

object Test1_temp {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setMaster("local")
    conf.setAppName("test")
    val sc = new SparkContext(conf)
    //    两种路径的写法都可以，一个是相对路径，一个是绝对路径
    //    val lines = sc.textFile("../MusicProject/data/currentday_clientlog.tar.gz")
    val lines = sc.textFile("/currentday_clientlog.tar.gz")
    //    val lines = sc.textFile("C:/Users/meira/Desktop/MusicProject/data/currentday_clientlog.tar.gz")
    //    lines.foreach(println)

    lines.filter(line=>{line.split("&").size==6})
      .map(line=>{(line.split("&")(2),(line.split("&"))(3))})
      .map(tp=>{
        val cmd:String = tp._1
        val jsonStr:String = tp._2
        if("MINIK_CLIENT_ADVERTISEMENT_RECORD".equals(cmd)){
          val jsonObj = JSON.parseObject(jsonStr)
          val src_verison = jsonObj.getString("src_verison")
          val mid = jsonObj.getString("mid")
          val adv_type = jsonObj.getString("adv_type")
          val src_type = jsonObj.getString("src_type")
          val sessionId = jsonObj.getString("session_id")
          val event_id = jsonObj.getString("event_id")
          val time = jsonObj.getString("time")

          (cmd,s"$src_verison\t$mid\t$adv_type\t$src_type\t$event_id\t$time\t$sessionId")
        }else{
          tp
        }
      }).saveAsHadoopFile("hdfs://mycluster/testdata" + System.currentTimeMillis(),classOf[String],classOf[String],classOf[PairRDDMultipleTextOutputFormat])
    //后面三个参数分别指定了key和value的格式，以及所输出文件的格式

  }
}
