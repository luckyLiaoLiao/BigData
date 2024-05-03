package test

import scalaj.http.{Http, HttpResponse}
import com.alibaba.fastjson.{JSON, JSONObject}
object test {
  def main(args: Array[String]): Unit = {

    /**
      * slice(m,m)
      */
//    val list = List[Int](1,2,3,4,5,6,7,8,9,10)
//    list.slice(4,10000).foreach(println)


//    println("[1,2,3,4,5]".stripPrefix("["))
//    println("[1,2,3,4,5]".stripPrefix("[").stripSuffix("]"))

//    println(ConfigUtils.LOCAL_RUN)
//    println(ConfigUtils.HIVE_DATABASE)
//    println(ConfigUtils.HIVE_METASTORE_URIS)
//    println(ConfigUtils.MYSQL_USER)

//      println("abcde".substring(0,4))

//    val list1  = new ListBuffer[String]()
//    list1.append("w")
//    val list2  = new ListBuffer[String]()
//    list2.append("a")
//    list2.append("b")
//    list2.append("c")
//    list2.append("d")
//    list1.++=(list2)
//    println(list1)

    val response: HttpResponse[String] = Http("https://restapi.amap.com/v3/geocode/regeo")
      .param("key","344bff6e68fdf2c56039a2bb8e4a36c6")
      .param("location","120.1049,30.3197|108.3313,22.8166|114.4133,23.1192|115.4427,36.4840")
      .param("batch","true")
      .asString

    val nObject: JSONObject = JSON.parseObject(response.body.toString)
    println(s"nObject = "+nObject)
    println(s"infocode = ${nObject.getString("infocode")}")
    println(s"response.body : ${response.body }")
    println(s"response.code : ${response.code}")
    println(s"response.headers : ${response.headers}")
    println(s"response.cookies : ${response.cookies}")
  }

}
