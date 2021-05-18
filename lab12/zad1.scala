import akka.actor.{ActorSystem, Actor, Props}
import scala.concurrent.duration._
/*
  W konfiguracji projektu wykorzystana została wtyczka
  sbt-revolver. W związku z tym uruchamiamy program poleceniem

    reStart

  a zatrzymujemy pisząc (mimo przesuwających się komunikatów)

     reStop

  i naciskając klawisz ENTER. Jeśli czynności powyższe
  już wykonywaliśmy to możemy też przywołać poprzednie
  polecenia używając strzałek góra/dół na klawiaturze.
*/

// Przykład wykorzystania Planisty (Scheduler)
case object Start
case class Ask(ref: ActorRef)
case class SendCount(n: Int)
case object Count
case object Kill

object Planista extends Actor {
  def receive: Receive={
    case Start => 
      val castle1 = context.actorOf(Props[Zamek],"zamek1")
      val castle2 = context.actorOf(Props[Zamek],"zamek2")
      context.become(ready(castle1+castle2))
      castle1 ! Start
      castle2 ! Start
  def ready(castles): Receive={
    case CountArchers =>
    castles[0] ! CountArchers(castles[1])
    castles[1] ! CountArchers(castles[0])

  }
  }

  object Zamek extends Actor {
    def receive: Receive ={
      case Start =>
        val strzelcy=for( i <- 0 to 99 ) yield context.actorOf(Props[Lucznik],"lucznik"+i)
        context.become(ready(strzelcy,100))
    }
    def ready(strzelcy,wrogowie): Receive ={
      case Ask(reference)=>
        reference ! Count
      case Count=>
        sender ! SendCount(strzelcy.length)
      case SendCount(n)=>
        context.become(strzelcy,n)

      
    }
    object Lucznik extends Actor{
      def receive: Receive={

      }

    }
  }

  }

  def main(args: Array[String]): Unit = {
    val system = ActorSystem("system")
    import system.dispatcher

    val tickActor = system.actorOf(Props[TickActor](), "defender")

    val ticker = system.scheduler.scheduleWithFixedDelay(
      Duration.Zero,
      50.milliseconds,
      tickActor,
      TickActor.Tick
    )

    // system.terminate()

  }
}
