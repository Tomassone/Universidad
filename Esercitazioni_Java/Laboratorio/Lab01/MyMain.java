/**
* Applicazione Java da linea di comando.
* Realizza un fattoriale ricorsivo
* @author Fondamenti di Informatica T-2
* @version 1.0 02/2023
*/
public class MyMain {
/**
* Calcola il fattoriale in modo ricorsivo
*/
public static int fact(int n) {
if(n==0)
return 1;
else
return n * fact(n-1);
}

/**
* Il main - calcola e stampa fattoriali
*/
public static void main(String[] args) {
int fact1 = fact(3); // RISULTATO ATTESO: 6
System.out.println(fact1 == 6); // success or failure?
int fact2 = fact(6); // RISULTATO ATTESO: 720
System.out.println(fact2 == 720); // success or failure?
int fact3 = fact(8); // RISULTATO ATTESO: 40320
System.out.println(fact3 == 40320); // success or failure?
}
}
