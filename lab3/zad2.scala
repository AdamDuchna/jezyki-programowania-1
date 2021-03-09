import scala.annotation.tailrec
object zad2 {

    def main(args: Array[String])
    {
      val par = io.StdIn.readInt()
      print(isPrime(par))
    }


    def isPrime( entry:Int) : Boolean = {
      def assist( n:Int,  i:Int=2) : Boolean =
      {
        if (n <=2)
        {
          if ( n==2){ return true }
          else { return false } 
        }

        if (n % i == 0){ return false }

        if ( i*i > n) { return true }

        assist(n,i+1)
      }
      return assist(entry)
    }
}

