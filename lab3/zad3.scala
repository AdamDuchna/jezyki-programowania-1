object zad3 {

    def main(args: Array[String])
    {
      val number=10
      print(fib(number))
    }

def fib(index: Int): Int = {
  @annotation.tailrec
  def assist(index: Int, prev: Int=1, current: Int=0): Int = {
    if (index <= 0) {
      current
    } else {
      assist(index - 1, prev = prev + current, current = prev)
    }
  }

  assist(index)
}
}

