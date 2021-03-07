object zad4
{
    def main(args: Array[String])
    {
      val n=7
      pascalTriangleRows(n)
    }

  def pascalTraingle(column:Int,row:Int): Int =
    {
     if((column == 0) || (column == row))
    {
      return 1
    }
    else
    {
      return pascalTraingle(column-1,row-1)+pascalTraingle(column,row-1)
    }
  }
  def pascalTriangleRows(number:Int,row:Int=0): Unit=
  {
    if(number <= 0 )
    {
      print("Wartosc musi byc wieksza od 0")
    }
    if(row<number)
    {
      pascalTraingleColumn(row)
      print("\n")
      pascalTriangleRows(number,row+1)
    }
  }
  def pascalTraingleColumn(row:Int,column:Int=0) :Unit=
  {
    if(column <= row){
    print(pascalTraingle(column,row) + " ")
    pascalTraingleColumn(row,column+1)
    }
  }
}
