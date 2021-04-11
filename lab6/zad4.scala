object zad3 {

    def main(args: Array[String]) { 
      val seq = Seq('a','b','a','c','c','a')
      print(freq(seq))


    }
    def freq[A](seq: Seq[A]): Set[(A, Int)] = {
      val mapa=seq.groupBy(x => x)
      mapa.map(x=>(x._1,x._2.length)).toSet

    }
}



