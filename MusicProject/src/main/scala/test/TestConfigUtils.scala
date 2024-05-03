package test

import com.msbjy.scala.musicproject.common.ConfigUtils

object TestConfigUtils {

  def main(args: Array[String]): Unit = {
//   需要导包 ConfigUtils
val kafkabrokers = ConfigUtils.KAFKA_BROKERS
    println(kafkabrokers)
  }

}
