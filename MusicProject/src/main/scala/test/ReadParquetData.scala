package test

import java.util.Properties

import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}

/**
  * 读取parquet  ODS 数据
  */
object ReadParquetData {
  def main(args: Array[String]): Unit = {
    val properties = new Properties()
    properties.setProperty("user","root")
    properties.setProperty("password","123456")
    val session = SparkSession.builder().master("local").config("hive.metastore.uris","thrift://mynode1:9083").enableHiveSupport().appName("test").getOrCreate()
    session.sparkContext.setLogLevel("Error")
//    val frame: DataFrame = session.read.parquet("J:\\warehouse\\data\\song\\TO_SONG_INFO_D\\2020\\02\\05\\data.parquet")
    val frame: DataFrame = session.read.parquet(
      "C:\\Users\\wubai\\Desktop\\part-00000-2e30e81c-7af6-45ad-a6fb-1694fffd520b-c000.snappy.parquet")
    frame.show(100)
    println(s"总行数 = ${frame.count()}")
    println(s"xxx 表列长度为 ${frame.columns.length}")
//    frame.write.mode(SaveMode.Overwrite).jdbc(
//      "jdbc:mysql://mynode2:3306/ycak?useUnicode=true&characterEncoding=UTF-8",
//      "XXX",
//      properties)

    val to_song_rel_d: DataFrame = session.read.parquet(
      "C:\\Users\\wubai\\Desktop\\part-00000-71c1d21f-a7f5-4065-90cb-df30828d6b0f-c000.snappy.parquet")
    println(s"总行数 = ${to_song_rel_d.count()}")
    to_song_rel_d.createTempView("to_song_rel_d")
    to_song_rel_d.show(100)
    to_song_rel_d.write.mode(SaveMode.Overwrite).jdbc(
      "jdbc:mysql://mynode2:3306/ycak?useUnicode=true&characterEncoding=UTF-8",
      "XXX",
      properties)
//
//    val end = frame.join(to_song_rel_d,"order_id")
//      end.show(100)
//    println(s"总行数 = ${end.count()}")
//
//    val tw_song_baseinfo_d = session.table("TW_SONG_BASEINFO_D")

//    session.sql(
//      """
//        |select *
//        |from to_song_rel_d a join TW_SONG_BASEINFO_D b on a.RES_ID = b.NBR
//      """.stripMargin).show(100)



  }


}
