object zad2 {

    def main(args: Array[String])
    { val ciag=List(2, 4, 3, 5)
      val ciag2=List(1, 2, 2, 3, 1, 5)
      print(tasuj(ciag,ciag2))

    }

  def tasuj(l1: List[Int],l2: List[Int]): Unit={
    def assist(l1: List[Int],l2: List[Int],i1:Int,i2:Int,ogon: List[Int],prev:Int) : Unit={
      val prev2 = prev
      if (i1 < l1.size && i2 < l2.size ) {
        print(l1(i1),l2(i2),prev)
        (l1(i1),l2(i2)) match {
          case (prev,prev2) => {
            print("prevx2")
            assist(l1,l2,i1+1,i2+1,ogon,prev)
          }
          case (prev,_) => {
            print("prevx1_")
            if (l2(i2)<prev){
              assist(l1,l2,i1+1,i2+1,l2(i2)::ogon,l2(i2))
            }
            if(l2(i2)>prev){
              assist(l1,l2,i1+1,i2,ogon,prev)
            }
          }
          case (_,prev) => {
            print("_prevx1")
            if (l1(i1)<prev){
              assist(l1,l2,i1+1,i2+1,l1(i1)::ogon,l1(i1))
            }
            if(l1(i1)>prev){
              assist(l1,l2,i1,i2+1,ogon,prev)
            }
          }
          case (_,_) => {
            print("noprev")
            if (l1(i1)>l2(i2)){
              assist(l1,l2,i1+1,i2,l1(i1)::ogon,l1(i1))
            }
            else{
              assist(l1,l2,i1,i2+1,l2(i2)::ogon,l2(i2))
            }
          }
        }
      }
    if(i1 > l1.size && i2 < l2.size){
      assist(l1,l2,i1,i2+1,l2(i2)::ogon,l2(i2))
    }
    if(i2 > l2.size && i1 < l1.size){
      assist(l1,l2,i1+1,i2,l1(i1)::ogon,l1(i1))
    }
    if (i1 > l1.size && i2 > l2.size){
      return print(ogon)
    }
    }
    return assist(l1,l2,0,0,List.empty,-2)
  }
}

