import akka.actor.{ActorSystem, Actor, Props,ActorRef,Terminated,PoisonPill}
import scala.concurrent.duration._
import scala.util.Random

case object Start
case class StartCastle(enemy: ActorRef,strat: Int)
case class Ask(ref: ActorRef)
case class SendCount(n: Int)
case object Count
case object Hit
case object Kill
case class Shoot(n: Int)
case object End


class Planista extends Actor {
  def receive: Receive={
    case Start =>{
      val castle1 = context.actorOf(Props[Zamek],"zamek1")
      val castle2 = context.actorOf(Props[Zamek],"zamek2")
      val sekw= IndexedSeq(castle1,castle2)
      context.become(ready(sekw))
      castle1 ! StartCastle(castle2,2)
      castle2 ! StartCastle(castle1,3)
      self ! Count
    }
  }
      
  def ready(castles: IndexedSeq[ActorRef]): Receive={
    case Count =>{
    castles(0) ! Shoot(1)
    castles(1) ! Shoot(1)   
    }
    case End =>context.become(receive)
  }
}


class Zamek extends Actor {
  def receive: Receive ={
    case StartCastle(enemy,strat) =>{
      val strzelcy=for( i <- 0 to 99 ) yield context.actorOf(Props[Lucznik],"lucznik"+i)
      context.become(shoot(strzelcy,400,100,enemy,sender,strat))

    }
  }
     
  def shoot(strzelcy: IndexedSeq[ActorRef],rezerwa: Int,wrogowie: Int,wrogZamek: ActorRef,nadzorca: ActorRef,strat: Int): Receive ={

    case Count=> sender ! SendCount(strzelcy.length)
    case SendCount(n)=> context.become(shoot(strzelcy,rezerwa,n,sender,nadzorca,strat))
    case Shoot(_)=>{
      for (i <- 0 to strzelcy.length-1){
      wrogZamek ! Count
      strzelcy(i) ! Shoot(wrogowie)
      }
    }
    case Hit=> wrogZamek ! Kill
    case Kill=>{
    if (strzelcy.length>0){
      context.watch(strzelcy.last)
      strzelcy.last ! PoisonPill
      }
    else{
      println(wrogZamek.path.name+" wygrał")
      context.become(receive)
      nadzorca ! End
    }
    }
    case Terminated(actor) =>{
      println(self.path.name,strzelcy.length,rezerwa)
      if((rezerwa <= 0)){
        if(strzelcy.length == 0){ println(wrogZamek.path.name+" wygrał")
          context.become(receive)
          nadzorca ! End}
        else{
          val removed=strzelcy.drop(1)
          context.become(shoot(removed,rezerwa,wrogowie,wrogZamek,nadzorca,strat))
        }  
      }
      
      else{
        val removed=strzelcy.dropRight(1)
        //STRATEGIA 1 LEPSZA NIZ WSZYSTKIE POZOSTAŁE
        //Utrzymuj 100 jednostek
        if(strat == 1){
        val withNew=removed :+ context.actorOf(Props[Lucznik],"lucznikNew")
        context.become(shoot(withNew,rezerwa-1,wrogowie,wrogZamek,nadzorca,strat))
        }
        //STRATEGIA 2 > 4, < 1, <3
        //Gdy jest jeden strzelec wypełnij do 100
        if(strat == 2){
          if(strzelcy.length == 1){
          if (rezerwa > 98){
            val newArch = for(i <- 1 to 99) yield context.actorOf(Props[Lucznik],"lucznikNew"+i)
            context.become(shoot(newArch ++ removed,rezerwa-99,wrogowie,wrogZamek,nadzorca,strat))
            }
          else{
            val newArch = for(i <- 1 to rezerwa+1) yield context.actorOf(Props[Lucznik],"lucznikNew"+i)
            context.become(shoot(newArch ++ removed,0,wrogowie,wrogZamek,nadzorca,strat))
            }
          }
          else{context.become(shoot(removed,rezerwa-1,wrogowie,wrogZamek,nadzorca,strat))}
        }
        //STRATEGIA 3 < 4, < 1, >2
        //Utrzymywanie 2 jednostek
        if(strat == 3){
          if(strzelcy.length == 1){
          val withNew=removed :+ context.actorOf(Props[Lucznik],"lucznikNew")
          context.become(shoot(withNew,rezerwa-1,wrogowie,wrogZamek,nadzorca,strat))
          }
          else{context.become(shoot(removed,rezerwa-1,wrogowie,wrogZamek,nadzorca,strat))}
        }
        //STRATEGIA 4 > 3, < 1, < 2
        //Utrzymywanie 25 jednostek
        if(strat==4){
          if(strzelcy.length == 24){
          val withNew=removed :+ context.actorOf(Props[Lucznik],"lucznikNew")
          context.become(shoot(withNew,rezerwa-1,wrogowie,wrogZamek,nadzorca,strat))
          }
          else{context.become(shoot(removed,rezerwa-1,wrogowie,wrogZamek,nadzorca,strat))}
        }
      }
    }
  }
}
class Lucznik extends Actor{
  def receive: Receive={
    case Shoot(num) =>{ 
      val prob=num/2
      val getRand=Random.between(0,101)
      if(getRand<=prob){
        sender ! Hit}
    }
  }
}

object Main{
  def main(args: Array[String]): Unit = {
      val system = ActorSystem("system")
      import system.dispatcher
      val planner= system.actorOf(Props[Planista], "planista")

      planner ! Start

      val ticker = system.scheduler.scheduleWithFixedDelay(
        Duration.Zero,
        50.milliseconds,
        planner,
        Count
      )
      // system.terminate()
  }
}

