package com.msbjy.scala.musicproject.base

import org.apache.hadoop.mapred.lib.MultipleTextOutputFormat

class PairRDDMultipleTextOutputFormat extends MultipleTextOutputFormat[Any, Any] {

  //1)文件名：根据key和value自定义输出文件名。 name：对应的part-0001文件名
  override def generateFileNameForKeyValue(key: Any, value: Any, name: String): String ={
//    直接把key作为文件名返回了
    val fileName=key.asInstanceOf[String]
    fileName
  }
  //2)文件内容：默认同时输出key和value。这里指定不输出key。
  override def generateActualKey(key: Any, value: Any): String = {
    null
    //这里为null，表示不输出key，如果写一个非null的字符串，key就是那个字符串
  }
}