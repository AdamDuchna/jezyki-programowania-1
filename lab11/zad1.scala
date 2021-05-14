package lab11

import akka.actor.{ActorSystem, Actor, ActorRef, Props}
case class Init(liczbaPracownikow: Int)
case class Zlecenie(tekst: List[String])
case class Wykonaj(linia: String)
case class Wynik(mapa: Map[String,Int])
case object Pokaz

class Nadzorca extends Actor {
  def receive: Receive = {
    case Init(liczba) => {
      val children = for( i <- 1 to liczba ) yield context.actorOf(Props[Pracownik],"child"+i)
        context.become(start(children))

      val workerCount=liczba
      
    }
  }
  def start(pracownicy: IndexedSeq[ActorRef]): Receive = {
    case Zlecenie(tekst) => {
      for( i <- 0 to tekst.length-1 ){
        val pointChild=i%pracownicy.length
        pracownicy(pointChild) ! Wykonaj(tekst(i))
      }
      context.become(zbieraj(Map.empty[String,Int]))
    }
  def zbieraj(mapaMain: Map[String,Int]): Receive = {
    
    case Wynik(mapa) =>{
      val sum=(mapaMain /: mapa)((map,kv) => {
        map + ( kv._1 -> (kv._2 + map.getOrElse(kv._1, 0)))
      })
      context.become(zbieraj(sum))
    }
    case Pokaz =>
    {
      println(mapaMain)
    }
  }

  }
}

class Pracownik extends Actor {
  def receive: Receive = {
    case Wykonaj(linia) => {
      val wykonane=linia.split("[^\\p{L}0-9']+").map(_.toLowerCase).groupBy(identity).mapValues(_.size).toMap
      sender() ! Wynik(wykonane)
    }
  }
}

object Main {

  def dane(): List[String] = {
    scala.io.Source.fromResource("ogniem_i_mieczem.txt").getLines.toList
  }

  def main(args: Array[String]): Unit = {
    val system = ActorSystem("HaloAkka")
      val nadzor = system.actorOf(Props[Nadzorca], "nadzorca")
      nadzor ! Init(2)
      nadzor ! Zlecenie(dane())
      Thread.sleep(2000)
      nadzor ! Pokaz

  }

}
