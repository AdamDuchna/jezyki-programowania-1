object zad1 {

    def main(args: Array[String])
    {
      print(sumuj(List(Some(2.0), Some(4.0), Some(-3.0), None, Some(-3.0), None, Some(1.0))))
    }

  def sumuj(l: List[Option[Double]]): Option[Double]={
    def assist(l: List[Option[Double]],iter: Int=0,sum:Int=0) : Option[Double]={
      if(iter <= l.lenght ){
      l(iter) match {
        case Some(d) => {
          if(d>0){
            assist(l,iter+1,sum+d)
          }
        }
        case None => {
          assist(l,iter+1,sum)
        }

      }
      }
  }
  return assist(l)
  }
}

