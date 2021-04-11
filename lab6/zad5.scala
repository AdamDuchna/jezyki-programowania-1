object zad5{
    def main(args: Array[String]) { 
    val seq = Seq(1, 2, 2, 4)
    println(isOrdered[Int](seq)(_ < _))
    println(isOrdered[Int](seq)(_ <= _))
    }


    def isOrdered[A](seq: Seq[A])(leq:(A, A) => Boolean): Boolean ={
        seq.sliding(2).map(x=>leq(x(0),x(1))).foldLeft(true)((x,y)=>x && y)
    }
}