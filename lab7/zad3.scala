object zad3{
    def main(args: Array[String]) {
        val set = Set(-3, 0, 1, 2, 5, 6)
        print(minNotContained(set))   

    }
    def minNotContained(set: Set[Int]): Int = {
        val sekw=set.toSeq.sortBy(x => x)
        sekw.foldLeft(1)((wart,x)=>{
                if (x == wart ) wart+1
                else  wart

        })
    }
}