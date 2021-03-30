object zad1 {

    def main(args: Array[String]) { 
      val list = List(1, 2, 3, 4, 5, 6)
      print(subSeq(list,2,5))


    }
  def subSeq[A](seq: Seq[A], begIdx: Int, endIdx: Int): Seq[A] = {
    val sekw=seq.drop(begIdx)
    return sekw.take(endIdx-begIdx)
  }
}



