object zad2 {

    def main(args: Array[String]) { 
      val ciag=List(2, 4, 3, 5)

      print(divide[Int](ciag))

    }

  def divide[A](l: List[A]): (List[A], List[A]) = {
    def assist(l: List[A],ogon1:List[A],ogon2:List[A],index:Int=0): (List[A], List[A])={
      l match {
        case lH::lT => {
          if (index % 2 ==0){
            assist(lT,lH::ogon1,ogon2,index+1)
          }
          else {
            assist(lT,ogon1,lH::ogon2,index+1)
          }
        }
        case _ => {
          return (ogon1.reverse,ogon2.reverse)
        }
      }
    }
    return assist(l,List(),List())
  }
}


