package test

import com.alibaba.fastjson.JSON
import com.msbjy.scala.musicproject.base.PairRDDMultipleTextOutputFormat
import org.apache.spark.{SparkConf, SparkContext}

object Test1_print {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setMaster("local")
    conf.setAppName("test")
    val sc = new SparkContext(conf)
    val lines = sc.textFile("/currentday_clientlog.tar.gz")
//    lines.foreach(println)

    lines.filter(line => {line.split("&").size == 6}).map(line => {(line.split("&")(2), line.split("&")(3))})
      .foreach(println)
  }

}
