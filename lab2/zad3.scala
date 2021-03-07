//Zadanie nie da się zrobić optymalniej bez korzystania ze zmiennych (chyba)?.Znacznie łatwiej by było zapisac liczby pierwsze w tablicy
object zad3
{
    def main(args: Array[String])
    {
      val n=17
      daSie(n)
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

    def daSie(n: Int) : Unit=
    { if(n>=2)
      {
        if(n%2==0)
        {
          check(n,n-1)
          daSie(n-2)
        }
        if(n%2==1)
        {
          check(n-1,n-2)
          daSie(n-3)
        }
      }
    }
    def check(number:Int,top:Int,bottom:Int=1) : Unit=
    { 
      if( isPrime(top)==true && isPrime(bottom)==true )
    {
      print(top,bottom)
    }
    if( bottom<number )
    {
      check(number,top-1,bottom+1)
    }
    }
}
