object zad2 {

    def main(args: Array[String])
    { val ciag=List(2, 4, 3, 5)
      val ciag2=List(1, 2, 2, 3, 1, 5)
      print(tasuj(ciag,ciag2))

    }

  def tasuj(l1: List[Int],l2: List[Int]): List[Int]={
    def assist(l1: List[Int],l2: List[Int],ogon: List[Int],prev:Int) : List[Int]={
      (l1,l2) match {
        case (l1H::l1T,l2H::l2T) => {
          if (l1H<l2H){
            if(prev!=l1H){
            assist(l1T,l2H::l2T,l1H::ogon,l1H)
            }
            else{
              assist(l1T,l2H::l2T,ogon,prev)
            }
          }
          else{
             if(prev!=l2H){
                assist(l1H::l1T,l2T,l2H::ogon,l2H)
             }
             else{
               assist(l1H::l1T,l2T,l2H::ogon,prev)
             }
          }
        }
        case (List(),l2H::l2T) => {
            if(prev!=l2H){
                assist(l1,l2T,l2H::ogon,l2H)
             }
             else{
               assist(l1,l2T,l2H::ogon,prev)
             }
        }
        case (l1H::l1T,List()) => {
            if(prev!=l1H){
            assist(l1T,l2,l1H::ogon,l1H)
            }
            else{
              assist(l1T,l2,ogon,prev)
            }
          }
        case _ => { 
          return ogon.reverse
          }
      }
    }
    return assist(l1,l2,List(),0)
  }
}


