package test

import org.apache.spark.sql.{DataFrame, SparkSession}

object TestSparkSQl {

  def main(args: Array[String]): Unit = {
    val session: SparkSession = SparkSession.builder().master("local").appName("test").getOrCreate()
    val list = List[String](xs = "zhangsan", "lisi", "wangwu")

//    关掉日志
    session.sparkContext.setLogLevel("Error")

    import org.apache.spark.sql.functions._

    val myudf = udf((s:String)=>{s.length})

    import session.implicits._
    val df:DataFrame = list.toDF("name")

//    DataFrame的Api
    df.withColumn("age",lit(18)).withColumn("namelen",myudf(col("name"))).show()

  }
}
