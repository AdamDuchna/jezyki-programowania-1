object zad4{
    def main(args: Array[String]) {
        val strefy: Seq[String] = java.util.TimeZone.getAvailableIDs.toSeq
        print(getEurope(strefy)) 

    }
    def getEurope(sekw:Seq[String]): Seq[String]={
        val europe=sekw.foldLeft(Seq.empty[String])((acc,x)=>{
            if (x.startsWith("Europe/")) x.stripPrefix("Europe/")+:acc
            else acc
        })
        val sorted=europe.sortBy(x => (x.length,x))
        sorted
    }
}