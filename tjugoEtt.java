import java.util.*;

public class Seventh {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in); //importerar scanner
        int poängS = 0; //spelarens poäng lagras här
        int poängD = 0; //datorns poäng lagras här
        int o = 0; //bestämmer vilket objekt i Kortlek-arrayen som ska visas (ökar med 1 för varje varv)
        String kort = ""; //lagrar datorns kort så att alla kan skrivas ut på rad
        System.out.println("Vad är spelarens namn?");
        String spelare = scan.nextLine();
        var spel = new Kortlek(); //skapar ett nytt objekt av klassen kortlek.
        // skriver ut att det är spelarens tur
        System.out.println(spelare + "'s tur! Skriv 0 för att lägga ett kort.") ;
        int start = scan.nextInt() ;
        //om spelaren skriver 0 startar loopen
        if (start == 0) {
            while (true) { //spelaren kör
                //skriver ut det första kortet
                System.out.println("Ditt kort är " + spel.läggKort(o));
                //om kortet är ett ess får spelaren välja om poängen ska vara 1 eller 14
                if (spel.läggVärde(o) == 1) {
                    System.out.println("Om du vill att esset ska ha poängen 1, skriv 1.");
                    System.out.println("Om du vill att esset ska ha poängen 14, skriv 14");
                    while (true) {
                        int å = scan.nextInt();
                        if (å == 14) { //om spelaren anger 14 läggs 14 poäng till till poängen
                            poängS = poängS + 14;
                            break;
                        }
                        if (å == 1) { //om spelaren anger 1 läggs 1 poäng till till poängen
                            poängS = poängS + 1;
                            break;
                        } else {
                            System.out.println("Vänligen skriv 1 eller 14") ;
                            //om spelaren skriver något annat än 1 eller 14
                        }
                    }
                } else {
                    //om kortet inte är ess läggs värdet på kortet till till poängen direkt
                    poängS = poängS + spel.läggVärde(o);
                }
                //spelarens totala poäng skrivs ut
                System.out.println("Din poäng är " + poängS + ".");
                if (poängS > 21) {
                    System.out.println("Din poäng är över 21!");
                    break;
                    //om poängen är över 21 förlorar spelaren. Då skrivs detta ut och loopen bryts.
                }
                System.out.println("Skriv 0 för att lägga ett till kort. Skriv 1 för att avsluta.");
                int f = scan.nextInt();
                if (f == 0) {
                    //om spelaren vill lägga ett till kort så ökar o med ett och loopen körs igen
                    o++;
                } else {
                    //om spelaren inte vill lägga ett till kort så bryts loopen
                    break;
                }
            }
        }
        else {
            throw new IllegalArgumentException("Felaktigt kommando") ;
            //programmet ska stoppas om spelaren inte skriver 0 i början.
        }
        System.out.println ("Datorns tur!") ;
        while (true) { //datorn kör
            if (spel.läggVärde(o) == 1 && poängD < 8) {
                /*om kortet är ett ess undersöker datorn om dens totala poäng blir under 21 om 14 läggs till.
                Om den totala poängen blir under eller lika med 21 så väljer datorn att esset ska vara värt 14*/
                poängD = poängD + spel.läggVärde(14) ;
                //annars blir värdet automatiskt 1 eftersom det är kortets värde.
            }
            else {
                poängD = poängD + spel.läggVärde(o) ; //poängen för kortet läggs till
                kort = kort + spel.läggKort(o) + " "; //namnet på kortet (färg + valör) läggs till i stringen.
                }
            int igen = TjugoEtt.smartDator (poängD) ;
            //metoden smartDator avgör om datorn ska ta ett nytt kort eller stanna.
            if (igen == 1) {
                o++ ;
                //om svaret blir ja fortsätter loopen.
            }
            else {
                System.out.println ("Datorns kort är " + kort ) ;
                System.out.println ("Datorns poäng är " + poängD) ;
                break ; //om svaret blir nej bryts loopen.
            }
        }
        //metoden printResult anropas för att avgöra vem som vann.
        String vinnare = TjugoEtt.printResult(poängS, poängD, spelare) ;
        System.out.println (vinnare) ; //vinnaren printas ut.
    }
}

class TjugoEtt { //metoder som anropas i programmet finns här

    static String printResult(int poängS, int poängD, String spelare) {
        //metod som räknar ut vem som vann och utropar vinnaren.
        if (poängS > 21 && poängD > 21) {
            return ("Båda hamnade över 21") ;
        }
        else if (poängS > 21) {
            return ("Datorn vann!");
        }
        else if (poängD > 21) {
            return (spelare + " vann!");
        }
        else if (poängS > poängD) {
            return (spelare + " vann!") ;
        }
        else if (poängS == poängD) {
            return ("Det blev oavgjort!") ;
        }
        else if (poängD > poängS) {
            return ("Datorn vann!") ;
        }
        else {
            return ("Något blev fel.") ;
        }
    }
    static int smartDator (int poängD) {
        /*den här metoden bestämmer om datorn ska dra ett till kort eller stanna. return 1 betyder
        dra ett nytt kort, return 0 betyder stanna. */
        if (poängD <= 12) {
            //om poängen är under 13 drar datorn alltid ett till kort
            return 1 ;
        }
        if (poängD > 12 && poängD < 15) {
            /*om poängen är 13 eller 14 slumpar datorn om den ska dra ett till kort.
            Sannolikheten att datorn drar ett till kort här är 2/3.*/
            int i = ((int) (Math.random() * 3)) ;
            if (i < 2){
                return 1 ;
            }
            else {
                return 0 ;
            }
        }
        if (poängD >= 15 && poängD < 17) {
            /*om poängen är 15 eller 16 slumpar datorn om den ska dra ett till kort.
            Sannolikheten att datorn drar ett till kort här är 1/3.
             */
            int i = ((int) (Math.random() * 3)) ;
            if (i < 1) {
                return 1 ;
            }
            else {
                //om poängen är 17 eller högre väljer datorn alltid att stanna och inte ta ett till kort.
                return 0 ;
            }
        }
        else { //om poängen är 17 eller högre stannar datorn alltid.
            return 0 ;
        }
    }
}