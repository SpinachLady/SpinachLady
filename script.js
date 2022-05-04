var uttryck = ""; //Variabeln uttryck är en string som hela tiden visas i fältet på räknaren.
var j = 4; //j lagrar vilket räknesätt som används

function AC() { //Denna funktion används för att ta bort allt som användaren skrivit
    uttryck = ""
    ut ();
}
function tal (x) { //Denna används varje gång användaren trycker på en av siffer-knapparna
    uttryck = uttryck + x;
    ut ();
}
function punkt () { //Denna används när användaren lägger till ett komma
    uttryck = uttryck + '.'
    ut ();
}

function addi () { //Denna används när användaren trycker på plus-knappen
    check();
    uttryck = uttryck + " + "
    ut ();
    j = 0;

}
function subtra () { //Minus-knappen
    check();
    uttryck = uttryck + " - "
    ut ();
    j = 1;
}
function multi () { //Gånger-knappen
    check();
    uttryck = uttryck + " × "
    ut();
    j = 2;
}
function divi () { //Delat-knappen
    check();
    uttryck = uttryck + " ÷ "
    ut();
    j = 3;
}

function rakna () {
    /*Resultatet räknas ut genom att det första talet lokaliseras och görs till substring x 
    och det andra talet lokaliseras och görs till substring y. Eftersom alla räknetecken börjar
    och slutar med ' ' är det lätt att dela uttrycket i två*/
    var x = parseFloat(uttryck.substring(0, (uttryck.indexOf(" ")))); //substrings görs om till Float
    var y = parseFloat(uttryck.substring((uttryck.lastIndexOf(" ")))); 
    //här använder jag var j för att systemet ska veta vilket räknesätt som ska användas.
    if (j == 0) { 
        return (x + y).toFixed(2); //två decimaler returneras alltid i resultatet
    }
    if (j == 1) {
        return (x - y).toFixed(2);
    }
    if (j == 2) {
        return (x * y).toFixed(2);
    }
    if (j == 3) {
        return (x / y).toFixed(2);
    }

}


function result () { /*testar om det är giltigt att använda likhetstecknet när användaren trycker
    in den knappen. Om uttrycket slutar med ett räknetecken eller inte innehåller ett räknetecken
    alls händer inget när likhetstecken-knappen trycks in.*/
    if (!uttryck.endsWith(' ')) {
        if (uttryck.includes(' ')) {
            document.getElementById("resultat").innerHTML = rakna();
        }
    }
}

function ut () { /*skriver ut uttryck (smidigare att bara skriva ut () än att skriva hela raden
    nedan varje gång)*/
    document.getElementById("resultat").innerHTML = uttryck;
}

function check () { /*kollar ifall uttrycket redan innehåller ett räknetecken när användaren
    trycker på ett. Om det gör det så räknas resultatet ut direkt och skrivs ut istället, för 
    att inte uttrycket ska bli för långt*/
    if (uttryck.includes(' ')) {
        uttryck = rakna();
    }
}