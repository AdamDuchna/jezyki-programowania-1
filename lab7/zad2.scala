object zad2{
    def main(args: Array[String]) {
        val a=Seq(1, 2, 3, 4, 5)
        print(swap(a))   

    }
    def swap[A](seq: Seq[A]): Seq[A] = {

        val res=seq.grouped(2).foldLeft(Seq.empty[A])((x,y)=>{
            y match {
                case (first::second) => {
                print(x)
                first+:(second++:x)}
                case _ => x
                
            }
        })
        res.reverse
    }
}