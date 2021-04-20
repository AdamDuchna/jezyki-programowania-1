object Zad2 {

  def main(args: Array[String]): Unit = {
    val linie = io.Source
      .fromResource("nazwiska.txt")
      .getLines.toList
    print(find(linie))
  }
    def find(strgs:List[String]): List[String]={
    val toSort= strgs.foldLeft(List.empty[Tuples])((acc,el)=>{
      val imieDistCount=el.takeWhile(x=> x!=' ').distinct.length
      val nazwiskoL=el.dropWhile(x=> x!=' ').distinct.length
      val toAppend=(el,imieDistCount,nazwiskoL)
      toAppend+:acc
    })
    val toReduce=toSort.sortBy( el=>(el._2,el._3))
    val wynik=toReduce.foldLeft(List.empty[String])((acc,cur)=>{
      (cur._1)+:acc
    })
    wynik
  }

}

