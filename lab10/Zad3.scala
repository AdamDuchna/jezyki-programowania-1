package Lab10
import akka.actor.{ActorSystem, Actor, ActorRef, Props}

object Zad2 {
  case class Piłeczka(tresc: String,pass: List[ActorRef] )

  def main(args: Array[String]): Unit = {
    val system = ActorSystem("sysPingPong")
    val player1= system.actorOf(Props[Gracz],"player1")
    val player2= system.actorOf(Props[Gracz],"player2")
    val player3= system.actorOf(Props[Gracz],"player3")
    val player4= system.actorOf(Props[Gracz],"player4")
    val player5= system.actorOf(Props[Gracz],"player5")

    player1 ! Piłeczka("Ping",List(player2,player3,player4,player5,player1))
  }
  class Gracz extends Actor { 
    def receive: Receive={
      case Piłeczka(tresc,pass)=>{
        if(tresc=="Ping"){
          println("Pong")
          pass.head ! Piłeczka("Pong",pass.tail:+pass.head)

        }
        if(tresc=="Pong")
          println("Ping")
          pass.head ! Piłeczka("Ping",pass.tail:+pass.head)

      }

    } 

  }
}

