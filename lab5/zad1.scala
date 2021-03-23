object zad1{

  def main(args: Array[String]){ 

  }

  def compose[A,B,C](f: A => B)(g: B => C): A => C ={
    (a: A) => g(f(a))
  }
  def prod[A,B,C,D](f:A => C ,g: B => D ): (A,B) => (C,D) ={
    (a:A,b:B) =>(f(a),g(b))
  }
  def lift[A, B, T](op: (T,T) => T)(f: A => T, g: B => T): (A,B) => T ={
    (a: A,b: B) => op(f(a),g(b))
  }
}

