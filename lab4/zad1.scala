object zad1 {

    def main(args: Array[String])
    {
      print(sumuj(List(Some(2.0), Some(4.0), Some(-3.0), None, Some(-3.0), None, Some(1.0))))
    }

  def sumuj(l: List[Option[Double]]): Unit={
    def assist(l: List[Option[Double]],iter: Int=0,sum:Double=0) : Unit={
      if( iter <= l.length-1 ){
      l(iter) match {
        case Some(d) => {
          if(d>0){
            assist(l,iter+1,sum+d)
          }
          else{
            assist(l,iter+1,sum)
          }
        }
        case None => {
          assist(l,iter+1,sum)
        }

      }
      }
      else{
        print(sum)
        return sum
      }
  }
  return assist(l)
  }
}

