object Zad1 {

  def main(args: Array[String]): Unit = {
    val linie = io.Source
      .fromResource("liczby.txt")
      .getLines.toList
    print(find(linie))
  }
  def find(strgs:List[String]): List[String]={
    val nonDesc= strgs.foldLeft(List.empty[String])((acc,el)=>{

      val zwrot1=el.reduceLeft((prev,curr)=>{
        if (prev == '.') prev
        if (curr>prev) curr
        else '.'
      })
      val zwrot2=el.foldLeft(0)((sum,curr)=>{
        sum+curr
      })

      if(zwrot1!='.' && zwrot2%2==0) el+:acc
      else acc
    })
    nonDesc
  }

}
