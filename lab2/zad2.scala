object zad2
{
    def main(args: Array[String])
    {
      val tablica = Array(1,2,3,2,1)
      print(jestPalindromem(tablica))
    }

  def jestPalindromem(tab: Array[Int],i:Int=0): Boolean = 
    {
    if (tab(i) != tab((tab.size-1)-i))
    {
      return false
    }
    if ( i == ((tab.size-1)/2))
    {
      return true 
    }
    return jestPalindromem(tab,i+1)
  }
}
