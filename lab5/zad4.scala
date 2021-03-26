object zad3 {

    def main(args: Array[String]) { 
      val l = List('a', 'a', 'b', 'c', 'c', 'c', 'a', 'a', 'b', 'd')

      print(compress[Char](l))

    }
    def compress[A](l: List[A]): List[(A, Int)] = {
      def assist(l: List[A],before:A,ogon:List[(A,Int)],count:Int): List[(A, Int)] = {
        l match {
          case lH::lT => {
            if(before==lH){
              assist(lT,lH,ogon,count+1)
            }
            else{
              assist(lT,lH,(before,count)::ogon,1)
            }
          }
          case _ => {
            return ogon.reverse
          }  
        }
      }
    return assist(l,l(0),List(),0)
  }
}



