object zad2 {

    def main(args: Array[String]) { 
      val sekwencja = Seq(1, 1, 2, 4, 4, 4, 1, 3)
      print(deStutter[Int](sekwencja))
    }
  def deStutter[A](seq: Seq[A]): Seq[A] = {
    val q=seq.foldLeft(Seq.empty[A])((x, y) =>{
      if(x.isEmpty){y+:x}
      else{
        if(y!=x.head) y+:x else x
      }
    })
    q.reverse
  }
}



