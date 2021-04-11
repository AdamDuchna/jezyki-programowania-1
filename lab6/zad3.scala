object zad3 {

    def main(args: Array[String]) { 
      val sekw=Seq(1, 1, 2, 4, 4, 4, 1, 3)
      print(remElems(sekw,2))

    }
    def remElems[A](seq: Seq[A], k: Int): Seq[A] = {
      val zippedElems: Seq[(A,Int)]=seq.zipWithIndex
      val filtered=zippedElems.filter(x=>x._2 !=k)
      filtered.map(x => x._1)
    }
}



