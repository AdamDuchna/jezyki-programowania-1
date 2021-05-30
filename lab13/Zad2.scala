import akka.actor._

case class Wstaw2(n: Int)
case class Usuń(n: Int)
case class Pusto(n: Int)

class Węzeł2 extends Actor {

  def receive: Receive = {
    case Wstaw(n) => {
      context.become(korzeń(n))
    }
  }

  def korzeń(wartość: Int): Receive = {
    case Wstaw(n) => {
      if(wartość == n){
        val zbior=context.actorOf(Props[Węzeł2],"syn0")
        println(wartość+"->"+zbior)
        context.become(zPotomkami(wartość,Set(zbior)))}
    }
    case Usuń(n) => {
      if(wartość == n){ sender ! Pusto(n)}
      
    }
  }

  def zPotomkami(wartość: Int, potomkowie: Set[ActorRef]): Receive = {
    case Wstaw(n) => {
      if(wartość == n){
        val newZbior= potomkowie + context.actorOf(Props[Węzeł2],"syn" + potomkowie.size)
        println(wartość+"->"+newZbior)
        context.become(zPotomkami(wartość,newZbior))
      }

    }
    case Usuń(n) => {
      if(wartość == n){
        val toDel = potomkowie.last
        context.watch(toDel)
        toDel ! PoisonPill
        }
      }
    case Terminated(actor) =>{
          if( potomkowie.size == 1){context.become(korzeń(wartość))}
          else{
          val dropped = potomkowie.dropRight(1)
          println(wartość+"->"+dropped)
          context.become(zPotomkami(wartość,dropped)) }
    }
    
  }

}

class Nadzorca extends Actor {
  def receive: Receive = {
    case Wstaw(n) => {
      val zbior=context.actorOf(Props[Węzeł2],"zbior"+n)
      zbior ! Wstaw(n)
      context.become(stan(Set(n)))
    }
    case Usuń(n) => { println("Element nie istnieje")}
  }

  def stan(znane: Set[Int]): Receive = {
    case Wstaw(n) => {
      if (znane.exists(e=>e == n)){
        context.actorSelection("/user/nadzorca/*") ! Wstaw(n)

      }
      else{
        val zbior=context.actorOf(Props[Węzeł2],"zbior"+n)
        zbior ! Wstaw(n)
        context.become(stan(znane ++ Set(n)))
      }
    }
    case Usuń(n) => { 
      if (znane.exists(e=>e == n)){
        context.actorSelection("/user/nadzorca/*") ! Usuń(n)
      }
      else{println("Element nie istnieje")}
    }
    case Pusto(n) => {
      val filtered = znane.filter(e => e!=n)
      context.become(stan(filtered))
    }
  }
}

object Zad2 extends App {
  val system = ActorSystem("system")
  val nadzorca = system.actorOf(Props[Nadzorca],"nadzorca")
  nadzorca ! Usuń(8)
  nadzorca ! Wstaw(3)
   nadzorca ! Wstaw(3)
   nadzorca ! Wstaw(3)
    nadzorca ! Wstaw(3)
    nadzorca ! Usuń(3)
    Thread.sleep(1000)
    nadzorca ! Usuń(3)
    Thread.sleep(1000)
    nadzorca ! Usuń(3)
    Thread.sleep(1000)
    nadzorca ! Usuń(3)
    Thread.sleep(1000)
    nadzorca ! Usuń(3)
    nadzorca ! Wstaw(1)
    nadzorca ! Wstaw(1)
    nadzorca ! Usuń(5)



}