object zad1 {

    def main(args: Array[String])
    {
      val par = io.StdIn.readInt()
      print(isPrime(par))
    }


    def isPrime( n:Int,  i:Int=2) : Boolean = {

      if (n <=2)
      {
        if ( n==2){ return true }
        else { return false } 
      }

      if (n % i == 0){ return false }

      if ( i*i > n) { return true }

      return isPrime(n,i+1);
    }
}
