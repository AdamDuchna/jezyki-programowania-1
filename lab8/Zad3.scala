object Zad3 {

  def main(args: Array[String]): Unit = {
    val linie = io.Source
      .fromResource("ogniem-i-mieczem.txt")
      .getLines.toList
    histogram(4,linie)
  }

  def histogram(maks: Int,tresc: List[String] ): Unit = {
      val ciagText=tresc.mkString(" ")
      val grouped=ciagText.toLowerCase.toList.groupBy(identity)
      val mapped=grouped.map{ case (char,list) => (char,if(list.length>maks)"*"*maks 
      else "*"*list.length)}
      val sorted=mapped.toSeq.sortBy(_._1)
      val clear=sorted.dropWhile(_._1 != 'a')
      print(clear)

  }
}
