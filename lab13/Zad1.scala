import akka.actor._

case class Wstaw(n: Int)
case class Znajdź(n: Int)

class Węzeł extends Actor {

  def liść(wartość: Int): Receive = {
    case Wstaw(n) =>{
      if(n>wartość){
        val prawyLisc=context.actorOf(Props[Węzeł],"node"+n)
        context.become(zPrawymPoddrzewem(wartość,prawyLisc))
        prawyLisc ! Wstaw(n)
      }
      else if(n<wartość){
        val lewyLisc=context.actorOf(Props[Węzeł],"node"+n)
        context.become(zLewymPoddrzewem(lewyLisc,wartość))
        lewyLisc ! Wstaw(n)
      }
      else{println("Element już występuje")}
      }
    case Znajdź(n) =>{
    if(n == wartość ) println("found "+n)
    else{println("nie znaleziono "+n)}
    }
  }

  def zLewymPoddrzewem(lewe: ActorRef, wartość: Int): Receive = {
    case Wstaw(n)=>{
      if(n>wartość){
        val prawyLisc=context.actorOf(Props[Węzeł],"node"+n)
        context.become(zPoddrzewami(lewe,wartość,prawyLisc))
        prawyLisc ! Wstaw(n)
      }
      else if(n<wartość){
        lewe ! Wstaw(n)
      }
      else{println("Element już występuje")}
      }
    case Znajdź(n) =>{
      if(n == wartość) println("found "+n)
      else if(n<wartość){ lewe ! Znajdź(n)}
      else{println("nie znaleziono " +n)}
    }
  }

  def zPrawymPoddrzewem(wartość: Int, prawe: ActorRef): Receive = {
    case Wstaw(n)=>{
      if(n>wartość){
        prawe ! Wstaw(n)
      }
      else if(n<wartość){
        val lewyLisc=context.actorOf(Props[Węzeł],"node"+n)
        context.become(zPoddrzewami(lewyLisc,wartość,prawe))
        lewyLisc ! Wstaw(n)
      }
      else{println("Element już występuje")}
      }
    case Znajdź(n) =>{
      if(n == wartość) println("found "+n)
      else if(n>wartość){prawe ! Znajdź(n)}
      else{println("nie znaleziono "+n)}
    }
  }

  def zPoddrzewami(lewe: ActorRef, wartość: Int, prawe: ActorRef): Receive = {
    case Wstaw(n)=>{
      if(n>wartość){
        prawe ! Wstaw(n)
      }
      else if(n<wartość){
        lewe ! Wstaw(n)
      }
      else{println("Element już występuje")}
      }
    case Znajdź(n) =>{
      if(n == wartość) println("found "+n)
      else if(n>wartość){prawe ! Znajdź(n)}
      else if(n<wartość){lewe ! Znajdź(n)}
      else{println("nie znaleziono "+n)}
    }
  }

  def receive: Receive = {
    case Wstaw(n) => context.become(liść(n))
    case Znajdź(n) => println("Drzewo jest puste")
  }
}

object Zad1 extends App {
  val system = ActorSystem("system")
  val node= system.actorOf(Props[Węzeł], "emptynode")
  node ! Znajdź(2)
  node ! Wstaw(2)
  node ! Wstaw(3)
  node ! Wstaw(5)
  node ! Wstaw(4)
  node ! Wstaw(0)
  node ! Wstaw(2)
  node ! Znajdź(5)
  node ! Znajdź(1)
  node ! Znajdź(4)
  node ! Znajdź(0)


}
