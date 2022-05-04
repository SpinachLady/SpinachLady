import java.util.* ;
import java.io.* ;
public class Eight {
    public static void main(String[] args) throws InterruptedException, IOException { //main metod
        Scanner scan = new Scanner(System.in); //skapar en scanner för att läsa inmatningar
        while (true) { //loopen för hela spelet.
            int o = 1; //håller koll på hur många omgångar som körts
            int sp = 0; //lagrar antalet spelare
            var Yatzy1 = new Yatzy(); //skapar ett nytt objekt av klassen Yatzy
            var Regler = new BufferedReader (new FileReader("yatzy_regler") ) ;
            System.out.println ("Välkommen till Yatzy! Vill du läsa reglerna - skriv 0. Annars - skriv 1.") ;
            int r = 2 ;
            boolean regler ;
            while (true) {
                try {
                    r = scan.nextInt();
                }
                catch (InputMismatchException e) {
                    scan.nextLine() ;
                }
                if (r == 0) {
                    regler = true ;
                    break ;
                }
                if (r == 1) {
                    regler = false ;
                    break ;
                }
                else {
                    System.out.println ("Vänligen ange 0 eller 1.") ;
                }
            }
            if (regler) {
                while (true) {
                    String rad = Regler.readLine() ;
                    if (rad == null) {
                        break ;
                    }
                    System.out.println(rad) ;
                    Thread.sleep(4000) ;
                }
            }
            System.out.println("Man kan vara 2-5 spelare. Hur många ska spela?");
            while (sp == 0) { //kör tills korrekt inmatning sker, dvs 2, 3, 4 eller 5 och inget annat.
                try {
                    sp = scan.nextInt();
                } catch (InputMismatchException e) {
                    scan.nextLine() ;
                }
                if (sp < 2 || sp > 5) {
                    sp = 0 ;
                    System.out.println("Felaktigt antal. Vänligen skriv ett nummer mellan 2 och 5");
                }
            }
            Spelare allaSpelare[] = new Spelare[sp]; //skapar en array för alla spelare
            System.out.println("Skriv in den första spelarens namn:");
            allaSpelare[0] = new Spelare(scan.nextLine()); //av okänd anledning funkar det bara om det är två
            allaSpelare[0] = new Spelare(scan.nextLine()); //st likadana rader här.
            System.out.println("Skriv in den andra spelarens namn:");
            allaSpelare[1] = new Spelare(scan.nextLine());
            if (sp > 2) { //om det är fler än 2 spelare skrivs tredje spelaren in
                System.out.println("Skriv in den tredje spelarens namn:");
                allaSpelare[2] = new Spelare(scan.nextLine());
            }
            if (sp > 3) { //om det är fler än 3 spelare skrivs fjärde spelaren in
                System.out.println("Skriv in den fjärde spelarens namn:");
                allaSpelare[3] = new Spelare(scan.nextLine());
            }
            if (sp > 4) { //om det är fler än 4 spelare skrivs femte spelaren in
                System.out.println("Skriv in den femte spelarens namn:");
                allaSpelare[4] = new Spelare(scan.nextLine());
            }
            while (o < 17) { //det är totalt 16 omgångar, denna loopen kör därför tom 16.
                int s = 0 ; //resetas när alla spelare har kört en omgång
                System.out.println("Omgång " + o);
                while (s < sp) {
                    if (o == 7) {//den sjunde omgången skriver ut summan för alla spelare
                        o++;
                        System.out.println("Första delen klar!");
                        int i = 0;
                        while (i < sp) {
                            System.out.println(allaSpelare[i].skrivNamn() + " har " +
                                    allaSpelare[i].skrivPoäng() + " poäng.");
                            i++;
                        }
                    }
                    int i = 1;
                    //varje omgång utom 7 kör detta:
                    System.out.println(allaSpelare[s].skrivNamn() + " 's tur. Skriv 0 för att slå tärningarna");
                    while (true) { //om något annat än 0 skrivs in fortsätter loopen
                        try {
                            i = scan.nextInt();
                        } catch (InputMismatchException e) {
                            scan.nextLine();
                        }
                        if (i == 0) {
                            break;
                        } else {
                            System.out.println("Ogiltigt.");
                        }
                    }
                    System.out.println(Yatzy1.kast1()); //skriver ut tärningskastet
                    int y = 0;
                    int x = 10;
                    while (y < 2) {
                        System.out.println("Hur många tärningar vill du slå om?");
                        while (true) { //om man skriver in något annat än 1, 2, 3, 4, 5 så fortsätter loopen
                            try {
                                x = scan.nextInt();
                            } catch (InputMismatchException e) {
                                scan.nextLine();
                            }
                            if (x == 0 || x == 1 || x == 2 || x == 3 || x == 4 || x == 5) {
                                break;
                            } else {
                                System.out.println("Vänligen välj ett nummer mellan 0 och 5.");
                            }
                        }
                        int u = 0;
                        int[] val = new int[x]; //array som lagrar placering på tärningarna man vill slå om
                        System.out.println("Skriv placeringen på de tärningar du vill slå om");
                        while (u < x) {
                            try {
                                val[u] = scan.nextInt();
                            } catch (InputMismatchException e) {
                                scan.nextLine();
                            }
                            if (val[u] == 1 || val[u] == 2 || val[u] == 3 || val[u] == 4 || val[u] == 5) {
                                Yatzy1.kasta(val[u]);
                                u++;
                            } else {
                                //om något annat än 1, 2, 3, 4, 5 skrivs ut fortsätter loopen
                                System.out.println("Vänligen välj endast nummer mellan 0 och 5.");
                            }
                        }
                        System.out.println(Yatzy1.utSkrift());
                        y++; //denna loop körs två gånger, för kast 2 och 3.
                    }
                    Yatzy1.sorteraKast(Yatzy1.mittKast);
                    Thread.sleep(2000); //2 sekunder paus så man hinner kolla på det sista kastet
                    System.out.println(Kategorier.kategorierLista(o, allaSpelare[s])); //frågar vilken kategori
                    int w = 0 ;
                    while (w == 0) {
                        try {
                            w = scan.nextInt();
                        }
                        catch (InputMismatchException e) {
                            scan.nextLine() ;
                            System.out.println("Du kan inte välja denna kategori. Vänligen välj en giltig kategori.");
                        }
                    }
                    while ((Kategorier.valdKategori(w, o, allaSpelare[s], Yatzy1)) == -1) {
                        //om spelaren valt en ogiltig kategori
                        System.out.println("Du kan inte välja denna kategori. Vänligen välj en giltig kategori.");
                        try {
                            w = scan.nextInt();
                        }
                        catch (InputMismatchException e) {
                            scan.nextLine() ;
                        }
                    }
                    allaSpelare[s].sättPoäng(Kategorier.valdKategori(w, o, allaSpelare[s], Yatzy1)); //sätter poäng
                    s++; //nästa spelares tur.
                }
                o++; //när alla spelare kört en omgång blir det ny omgång.
            }
            System.out.println("Vinnaren är...") ; //skriver ut vinnaren
            Thread.sleep(3000) ;
            if (sp == 2) {
                System.out.println(Yatzy1.resultat(allaSpelare [0], allaSpelare [1])) ;
            }
            else if (sp == 3) {
                System.out.println(Yatzy1.resultat(allaSpelare [0], allaSpelare [1], allaSpelare [2])) ;
            }
            else if (sp == 4) {
                System.out.println(Yatzy1.resultat(allaSpelare [0], allaSpelare [1], allaSpelare [2], allaSpelare [3])) ;
            }
            else {
                System.out.println(Yatzy1.resultat(allaSpelare [0], allaSpelare [1], allaSpelare [2], allaSpelare [3],
                        allaSpelare [4])) ;
            }
            Thread.sleep(3000);
            System.out.println ("Hela resultatet:") ; //hela resultatet skrivs ut (poängen för varje spelare)
            int ö = 0 ;
            while (ö < sp) {
                System.out.println (allaSpelare [ö].skrivNamn() + " = " + allaSpelare [ö].skrivPoäng() + " poäng") ;
                ö++ ;
            }
            Thread.sleep(3000);
            System.out.println ("Vill du spela igen - tryck 0. Avsluta - tryck 1.") ;
            int igen = scan.nextInt() ;
            if (igen == 0) { //om man vill spela igen börjar hela loopen om från början
                System.out.println ("Ny omgång!") ;
            }
            else {
                System.out.println ("Avslutar! Ha en bra dag.") ; //annars avslutas programmet
                break ;
            }
        }
    }
}

class Kategorier {
    static String kategorierLista(int o, Spelare x) {
        //beroende på vilken omgång det är skrivs olika kategorier ut. de kategorierna som en spelare valt skrivs
        //inte ut igen för samma spelare nästa omgång.
        if (o < 7) {
            System.out.println("Vänligen välj kategori:");
            if (x.kategorierKvar[0] == true) {
                System.out.println("Skriv 1 för ettor");
            }
            if (x.kategorierKvar[1] == true) {
                System.out.println("Skriv 2 för tvåor");
            }
            if (x.kategorierKvar[2] == true) {
                System.out.println("Skriv 3 för treor");
            }
            if (x.kategorierKvar[3] == true) {
                System.out.println("Skriv 4 för fyror");
            }
            if (x.kategorierKvar[4] == true) {
                System.out.println("Skriv 5 för femmor");
            }
            if (x.kategorierKvar[5] == true) {
                System.out.println("Skriv 6 för sexor");
            }
        }
        if (o > 7 && o < 17) {
            if (x.kategorierKvar [6] == true) {
                System.out.println ("Skriv 1 för ett par") ;
            }
            if (x.kategorierKvar [7] == true) {
                System.out.println ("Skriv 2 för två par") ;
            }
            if (x.kategorierKvar [8] == true) {
                System.out.println ("Skriv 3 för tretal") ;
            }
            if (x.kategorierKvar [9] == true) {
                System.out.println ("Skriv 4 för fyrtal") ;
            }
            if (x.kategorierKvar [10] == true) {
                System.out.println ("Skriv 5 för kåk") ;
            }
            if (x.kategorierKvar [11] == true) {
                System.out.println ("Skriv 6 för liten stege") ;
            }
            if (x.kategorierKvar [12] == true) {
                System.out.println ("Skriv 7 för stor stege") ;
            }
            if (x.kategorierKvar [13] == true) {
                System.out.println ("Skriv 8 för chans") ;
            }
            if (x.kategorierKvar [14] == true) {
                System.out.println ("Skriv 9 för Yatzy") ;
            }
        }
        return "" ;
    }
    //returnerar poängen för den kategori personen skrivit in.
    static int valdKategori (int i, int o, Spelare x, Yatzy y) {
        if (o < 7) {
            if (i > 0 && i < 7) {
                if (x.kategorierKvar[i - 1]) { //enkla
                    x.kategorierKvar[i - 1] = false; //tar bort kategorin från listan över kategorier som finns kvar
                    return (y.antalSort[i - 1] * i);
                }
            }
            else {
                return -1;
            }
        }
        else if (o > 7 && o < 17) {
            if (i == 1 && x.kategorierKvar[6]) { //ett par
                x.kategorierKvar[6] = false;
                int c = 5;
                while (c >= 0) {
                    if (y.antalSort[c] == 2) {
                        return ((c + 1) * 2);
                    }
                    c--;
                }
                return 0;
            } else if (i == 2 && x.kategorierKvar[7]) { //två par
                x.kategorierKvar[7] = false;
                int c = 5;
                int d;
                while (c >= 0) {
                    d = 5;
                    while (d >= 0) {
                        if (y.antalSort[c] == 2 && y.antalSort[d] == 2) {
                            return ((c + 1) * 2 + (d + 1) * 2);
                        }
                        d--;
                    }
                    c--;
                }
                return 0;
            } else if (i == 3 && x.kategorierKvar[8]) { //tretal
                x.kategorierKvar[8] = false;
                int c = 5;
                while (c >= 0) {
                    if (y.antalSort[c] == 3) {
                        return ((c + 1) * 3);
                    }
                    c--;
                }
                return 0;
            } else if (i == 4 && x.kategorierKvar[9]) { //fyrtal
                x.kategorierKvar[9] = false;
                int c = 5;
                while (c >= 0) {
                    if (y.antalSort[c] == 4) {
                        return ((c + 1) * 4);
                    }
                    c--;
                }
                return 0;
            } else if (i == 5 && x.kategorierKvar[10]) { //kåk
                x.kategorierKvar[10] = false;
                int c = 5;
                int d;
                while (c >= 0) {
                    d = 5;
                    while (d >= 0) {
                        if (y.antalSort[c] == 2 && y.antalSort[d] == 3) {
                            return ((c + 1) * 2 + (d + 1) * 3);
                        } else if (y.antalSort[c] == 3 && y.antalSort[d] == 2) {
                            return ((c + 1) * 3 + (d + 1) * 2);
                        }
                        d--;
                    }
                    c--;
                }
                return 0;
            } else if (i == 6 && x.kategorierKvar[11]) { //liten stege
                x.kategorierKvar[11] = false;
                if (y.antalSort[0] > 0 && y.antalSort[1] > 0 && y.antalSort[2] > 0
                        && y.antalSort[3] > 0 && y.antalSort[4] > 0) {
                    return (15);
                } else {
                    return 0;
                }
            } else if (i == 7 && x.kategorierKvar[12]) { //stor stege
                x.kategorierKvar[12] = false;
                if (y.antalSort[1] > 0 && y.antalSort[2] > 0
                        && y.antalSort[3] > 0 && y.antalSort[4] > 0 && y.antalSort[5] > 0) {
                    return (20);
                } else {
                    return 0;
                }
            } else if (i == 8 && x.kategorierKvar[13]) { //chans
                x.kategorierKvar[13] = false;
                return (y.mittKast[0] + y.mittKast[1] + y.mittKast[2] + y.mittKast[3] + y.mittKast[4]);

            } else if (i == 9 && x.kategorierKvar[14]) { //yatzy
                x.kategorierKvar[14] = false;
                int c = 5;
                while (c >= 0) {
                    if (y.antalSort[c] == 5) {
                        return (50);
                    }
                    c--;
                }
                return 0;
            } else {
                return (-1); //om spelaren skrivit in ett felaktigt värde returneras -1.
            }
        }
        return 0;
    }
}




class Yatzy {
    int[] mittKast = {0, 0, 0, 0, 0}; //lagrar värdena på tärningarna som kastas
    int[] antalSort = {0, 0, 0, 0, 0, 0}; //lagrar hur många tärningar det finns av varje sort


    public void sorteraKast (int [] x) { //sorterar kastet efter hur många av varje sort det finns.
        antalSort[0] = 0 ;
        antalSort[1] = 0 ;
        antalSort[2] = 0 ;
        antalSort[3] = 0 ;
        antalSort[4] = 0 ;
        antalSort[5] = 0 ;
        int go = 0 ;
        while (go < 5) {
            sortera(x[go]) ;
            go++;
        }
    }

    String kast1() { //det första kastet, här slås såklart alltid alla tärningar om.
        for (int i = 1; i < 6; i++) {
            kasta(i);
        }
        return utSkrift();
    }

    //kastar en tärning, dvs ger den ett random nummer mellan 1 och 6
    public void kasta(int x) {
        mittKast[x - 1] = (int) (Math.random() * 6) + 1;
    }

    //sorteringsmetoden som används för att sortera kast
    private void sortera(int x) {
        if (x == 1) {
            antalSort[0] = antalSort[0] + 1;
        }
        if (x == 2) {
            antalSort[1] = antalSort[1] + 1;
        }
        if (x == 3) {
            antalSort[2] = antalSort[2] + 1;
        }
        if (x == 4) {
            antalSort[3] = antalSort[3] + 1;
        }
        if (x == 5) {
            antalSort[4] = antalSort[4] + 1;
        }
        if (x == 6) {
            antalSort[5] = antalSort[5] + 1;
        }
    }

    //skriver ut värdena på tärningarna när de är kastade
    public String utSkrift() {
        int i = 0;
        String s = "";
        while (i < 5) {
            s = s + String.valueOf(mittKast[i]) + " ";
            i++;
        }
        return s;
    }


    //räknar ut vem som vann. Den här metoden är ganska lång eftersom den fungerar olika beroende på antal spelare.
    public String resultat(Spelare v, Spelare w, Spelare x, Spelare y, Spelare z) {
        int störst = v.skrivPoäng();
        String s = v.skrivNamn();
        if (w.skrivPoäng() >= störst) {
            if (w.skrivPoäng() > störst) {
                störst = w.skrivPoäng();
                s = w.skrivNamn();
            } else { //om w och s har samma poäng
                s = s + " och " + w.skrivNamn();
            }

        }
        if (x.skrivPoäng() >= störst) {
            if (x.skrivPoäng() > störst) {
                störst = x.skrivPoäng();
                s = x.skrivNamn();
            } else {
                s = s + " och " + x.skrivNamn();
            }

        }
        if (y.skrivPoäng() >= störst) {
            if (y.skrivPoäng() > störst) {
                störst = y.skrivPoäng();
                s = y.skrivNamn();
            } else {
                s = s + " och " + y.skrivNamn();
            }

        }
        if (z.skrivPoäng() >= störst) {
            if (z.skrivPoäng() > störst) {
                störst = z.skrivPoäng();
                s = z.skrivNamn();
            } else {
                s = s + " och " + z.skrivNamn();
            }

        }
        return s;
    }

    public String resultat(Spelare v, Spelare w, Spelare x, Spelare y) {
        int störst = v.skrivPoäng();
        String s = v.skrivNamn();
        if (w.skrivPoäng() >= störst) {
            if (w.skrivPoäng() > störst) {
                störst = w.skrivPoäng();
                s = w.skrivNamn();
            } else { //om w och s har samma poäng
                s = s + " och " + w.skrivNamn();
            }

        }
        if (x.skrivPoäng() >= störst) {
            if (x.skrivPoäng() > störst) {
                störst = x.skrivPoäng();
                s = x.skrivNamn();
            } else {
                s = s + " och " + x.skrivNamn();
            }

        }
        if (y.skrivPoäng() >= störst) {
            if (y.skrivPoäng() > störst) {
                störst = y.skrivPoäng();
                s = y.skrivNamn();
            } else {
                s = s + " och " + y.skrivNamn();
            }

        }
        return s ;
    }

    public String resultat (Spelare v, Spelare w, Spelare x) {
        int störst = v.skrivPoäng();
        String s = v.skrivNamn();
        if (w.skrivPoäng() >= störst) {
            if (w.skrivPoäng() > störst) {
                störst = w.skrivPoäng();
                s = w.skrivNamn();
            } else { //om w och s har samma poäng
                s = s + " och " + w.skrivNamn();
            }

        }
        if (x.skrivPoäng() >= störst) {
            if (x.skrivPoäng() > störst) {
                störst = x.skrivPoäng();
                s = x.skrivNamn();
            } else {
                s = s + " och " + x.skrivNamn();
            }

        }

        return s ;
    }

    public String resultat (Spelare v, Spelare w) {
        int störst = v.skrivPoäng();
        String s = v.skrivNamn();
        if (w.skrivPoäng() >= störst) {
            if (w.skrivPoäng() > störst) {
                störst = w.skrivPoäng();
                s = w.skrivNamn();
            } else { //om w och s har samma poäng
                s = s + " och " + w.skrivNamn();
            }

        }
        return s ;
    }
}





class Spelare extends Yatzy {
    //lagrar info om varje spelare.
    private String namn ;
    private int poäng = 0 ;
    public boolean [] kategorierKvar = {true, true, true, true, true, true, true, true, true, true, true, true,
    true, true, true} ;


    public Spelare (String namn){
        this.namn = namn ;
    } //konstruktur för att skapa en ny spelare (i början)

    public String skrivNamn (){
        return (namn) ;
    } //skriver ut namnet
    public int skrivPoäng () {
        return (poäng) ;
    } //skriver ut poängen

    public void sättPoäng (int i){ //ändrar poängen (lägger till på befintlig poäng)
        poäng = poäng + i ;
    }
}
