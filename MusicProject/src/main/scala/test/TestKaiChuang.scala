package test

import org.apache.spark.sql.{DataFrame, SparkSession}

object TestKaiChuang {

  def main(args: Array[String]): Unit = {
    val session: SparkSession = SparkSession.builder().appName("test").master("local").getOrCreate()

    val df: DataFrame = session.read.option("header","true")csv("file:///C:\\Users\\meira\\Desktop\\MusicProject\\data\\mydata.csv")

    df.createTempView("temp")
//    注册一张表
    session.sql(
      """
        |select dt,tp,cast(money as int) money_int,row_number() over(partition by tp order by money_int desc) as rank
        |from temp
        |""".stripMargin
    ).show()
  }
}