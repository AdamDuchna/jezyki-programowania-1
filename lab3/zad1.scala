import scala.annotation.tailrec
object zad1
{
    def main(args: Array[String])
    {
      val n="pozdrawiam"
      print(reverse(n))
    }
  def reverse(str: String) : String =
  { @tailrec
    def assist(ciag: String,ogon : String="") : String=
    { 
      if(ciag=="")
      {
        return ogon
      }
      else 
      { 
        assist(ciag.tail,ciag.head+ogon )
      }
    }
     return assist(str)
    

  }
}
