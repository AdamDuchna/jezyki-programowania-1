object zad2 {

    def main(args: Array[String]) { 
      val ciag=List(2, 4, 3, 5)

      print(usun[Int](ciag,3))

    }

  def usun[A](l: List[A], el: A): List[A]={
    def assist(l: List[A],el: A,ogon:List[A]): List[A]={
      l match {
        case lH::lT => {
            if(lH == el){
              assist(lT,el,ogon) 
              }
            else{
              assist(lT,el,lH::ogon)
              }
          }
        case _ => { 
          return ogon.reverse
          }
      }
    }
  return assist(l,el,List())
  }
}


