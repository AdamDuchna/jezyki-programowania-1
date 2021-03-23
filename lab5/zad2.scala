
object zad2{
  
  def main(args: Array[String]){ 

  }
  type MSet[A] = A => Int
  def sum[A](s1: MSet[A], s2: MSet[A]): MSet[A] = {
    (a: A) => s1(a)+s2(a)
  }
  def diff[A](s1: MSet[A], s2: MSet[A]): MSet[A] = {
    (a: A) => (s1(a)-s2(a)).abs
  }
  //def mult[A](s1: MSet[A], s2: MSet[A]): MSet[A] = {
  //  (a:A) => ...
  //}
}

