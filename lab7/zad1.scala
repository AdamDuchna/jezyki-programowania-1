object zad1{
    def main(args: Array[String]) {
      val a=Seq(1, 2, 1, 1, 5)
      val b = 1
      print(indices(a,b))   

    }


    def indices[A](seq: Seq[A], el: A): Set[Int] = {
        val res=seq.zipWithIndex.foldLeft(Seq.empty[Int])((x,y)=>{
            y match {
                case (wart,ind) =>{
                    if(wart==el) ind+:x
                    else x
                }
            }
        })
        res.reverse.toSet
    }
}