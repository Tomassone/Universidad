
#define _CRT_SECURE_NO_DEPRECATE //direttiva mandandata al compilatore; per la scanf_s (ci permette di usare la scanf normale)
#include <stdio.h> //direttiva al compilatore che richiede l'inclusione di una libreria (linker)
//#include <ctype.h>
//#include <stdlib.h>
//#include <time.h>
//#include <math.h>

/*void clrscreen() {
	#ifdef _WIN32
		system("cls");
	#elif __linux__
		system("clear");
	#endif;
}

int main() {
	int a;
	a = 2 + 3;
	printf("Hello World!");
	getchar();
	clrscreen();
	a = a - 3;
	return 0;
}*/

//int main() {
	///*int*/ float IVAperc = 0.2f; //specifichiamo che 0.2 sia un float
	//float prezzo = 11200;
	//printf("IVA %f", (double) IVAperc * prezzo); //facendo il cast, siamo sicuro che il risultato ci stia
	//return 0;
//}

/*void menu(int* x)
{
	printf("\n1) char\n2) short int\n3) int\n4) long int\n5) float\n6) double\n7) long double\n\n");
	printf("Scegli una delle opzioni elencate: ");
	scanf("%d", &(*x));
}

void stamp_ris(int *x)
{
	int n;
	switch (*x)
	{
		case 1:
			n = sizeof(char);
			printf("La dimensione del tipo char e' di %d byte.\n", n);
			break;
		case 2:
			n = sizeof(short int);
			printf("La dimensione del tipo short int e' di %d byte.\n", n);
			break;
		case 3:
			n = sizeof(int);
			printf("La dimensione del tipo int e' di %d byte.\n", n);
			break;
		case 4:
			n = sizeof(long int);
			printf("La dimensione del tipo long int e' di %d byte.\n", n);
			break;
		case 5:
			n = sizeof(float);
			printf("La dimensione del tipo float e' di %d byte.\n", n);
			break;
		case 6:
			n = sizeof(double);
			printf("La dimensione del tipo double e' di %d byte.\n", n);
			break;
		case 7:
			n = sizeof(long double);
			printf("La dimensione del tipo long double e' di %d byte.\n", n);
			break;
		default:
			printf("Hai selezionato un'opzione non valida.\n");
	}
}

int main()
{
	int s; //variabile per la scelta del tipo da stampare
	do
	{
		menu(&s);
		stamp_ris(&s);
	}
	while(1);
	return 0;
}*/

//int main() {
	/*unsigned*/ //short int i; //uno short int è troppo piccolo per rappresentare il 40000
	//short int k;
	//k = 10000;
	//i = 30000 + k;
	//printf("%d", i);
	//return 0;
//}

//int main()
//{
//	//float k;
//	//k = 5.6F;
//	//k = k - 5.59F;
//	//printf("%f", k); //nasconde l'approssimazione effettuata
//	// ----------
//	//int k;
//	//scanf("%d", &k);
//	//printf("Il quadrato di %d e' %d", k, k*k);
//	//-----------
//	/*char Nome = 'F';
//	char Cognome = 'R';
//	printf("%s\n%c. %c. \n\n%s\n", "Programma scritto da:", Nome, Cognome, "Fine");
//	return 0;*/
//	//-----------
//	/*int intero1, intero2;
//	float reale1;
//	char car1, car2;
//	scanf("%d%d", &intero1, &intero2);
//	printf("%d%d", intero1, intero2);*/
//	//-----------
//	//scanf("%d,%d", &intero1, &intero2); es. devo digitare <12,35> o <12,   35> (non <12 35>)
//	//scanf("%*c"); leggi un carattere e scartalo (necessario perchè in Windows il tasto invio corrisponde a due caratteri)
//	//--
//	/*int n;
//	char c;
//	printf("Digita il tuo saldo sul conto corrente in centesimi e la sua valuta corrispondente (E o D): ");
//	scanf("%d %c", &n, &c);
//	printf("Tu hai: %d %c.", n/100, c);
//	return 0;*/
//	//---
//	//int i, k;
//	//float j;
//	//i = 20;
//	//k = i % 3;
//	//i = i / 3;
//	//k = i / 4.0F; (riperdo il valore decimale => perchè uso una variabile intera)
//	//j = i / 4.0F;
//	////---
//	//int i;
//	//scanf("%d", &i);
//	//printf("%d", i);
//	//---
//	/*int torta_i = 12;
//	int torta_f;
//	torta_f = torta_i - 2 - (5 - 3) - (6 - (4 - 1));
//	printf("Sono rimaste %d fette di torta.", torta_f);*/
//	/*int med;
//	int cf = 900 + 500 + 120, cc = 800 + 500 + 120, ce =  1300 + 600 + 120;
//	printf("1 ha speso %d centesimi, 2 ha speso %d centesimi e 3 ha speso %d centesimi.\n", cf, cc, ce);
//	printf("1 ha speso %d.%d euro, 2 ha speso %d.%d euro e 3 ha speso %d.%d euro.\n", cf/100, cf%100, cc / 100, cc%100, ce / 100, ce%100);
//	med = (cf + cc + ce);
//	printf("Ogni amico dovra' pagare: %f centesimi.", med/3);*/
//	//int n, m, r;
//	//int i;
//	//printf("Digita un numero: ");
//	//scanf("%d", &n);
//	//printf("Digita un numero: ");
//	//scanf("%d", &m);
//	//printf("Digita un numero: ");
//	//scanf("%d", &r);
//	//printf("Questa e' la loro somma: %d.\n", n + m +r);
//	//printf("Questa e' la loro media: %f.", (n + m + r)/3.0);
//	//float n;
//	//printf("Digita un numero reale: ");
//	//scanf("%f", &n);
//	//printf("Il suo valore assoluto e': %0.2f.\n", (n>0) ? n : -n);
//	//printf("La sua parte intera e': %d.\n", (n > 0) ? (int) n : (int) -n);
//	//int a = 5, b = 5, c = 5;
//	//if (a > 0 || (a = a + 1)) //so già che a>0, dunque non valuto a = a + 1 (si parla infatti di un or logico)
//	//	printf("%d", a);
//	//if (b > 0 && (b = b + 1)) //devo valutare sia b>0 e b = b + 1 (un et è vera se e solo se solo se le due espressioni semplici sono entrambe vere)
//	//	printf("%d", b);
//	//if (c > 0 && (c = c - 5)) //la prima è vera e la seconda è falsa, quindi non entro neanche 
//	//	printf("%d", c);
//	//char c1, c2, c3;
//	//char min, mid, max;
//	//printf("Digita un carattere: ");
//	//scanf("%c", &c1);
//	//scanf("%*c"); //sennò la scanf interpreta l'invio (2nd carattere) come char
//	//printf("Digita un carattere: ");
//	//scanf("%c", &c2);
//	//scanf("%*c");
//	//printf("Digita un carattere: ");
//	//scanf("%c", &c3); 
//	//scanf("%*c");
//	//c1 = toupper(c1), c2 = toupper(c2), c3 = toupper(c3);
//	//if (c1>=c2)
//	//{
//	//	if (c1 >= c3)
//	//	{
//	//		max = c1;
//	//		if (c2 >= c3)
//	//			min = c3;
//	//		else
//	//			min = c2;
//	//	}
//	//	else
//	//	{
//	//		max = c3;
//	//		min = c2;
//	//	}
//	//}
//	//else
//	//{
//	//	if (c2 >= c3)
//	//	{
//	//		max = c2;
//	//		if (c1 >= c3)
//	//			min = c3;
//	//		else
//	//			min = c1;
//	//	}
//	//	else
//	//	{
//	//		max = c3;
//	//		min = c1;
//	//	}
//	//}
//	//mid = c1 + c2 + c3 - max - min;
//	//printf("Quesi sono i caratteri in ordine: %c %c %c.\n", min, mid, max);
//	/*float v1, v2, v3, v4;
//	float sum = 0;
//	printf("Inserisci un voto: ");
//	scanf("%f", &v1);
//	printf("Inserisci un voto: ");
//	scanf("%f", &v2);
//	printf("Inserisci un voto: ");
//	scanf("%f", &v3);
//	printf("Inserisci un voto: ");
//	scanf("%f", &v4);
//	if ((v1 >= 0.0 && v1 <= 10.0) && (v2 >= 0.0 && v2 <= 10.0) && (v3 >= 0.0 && v3 <= 10.0) && (v4 >= 0.0 && v4 <= 10.0))
//	{
//		if ((v1 >= v2) && (v1 >= v3) && (v1 >= v4))
//			sum = v2 + v3 + v4;
//		else if ((v2 >= v1) && (v2 >= v3) && (v2 >= v4))
//			sum = v1 + v3 + v4;
//		else if ((v3 >= v1) && (v3 >= v2) && (v3 >= v4))
//			sum = v1 + v2 + v4;
//		else if ((v4 >= v1) && (v4 >= v2) && (v4 >= v3))
//			sum = v1 + v2 + v3;
//		printf("La media dei valori inseriti e': %f.", sum/3.0);
//		return 0;
//	}
//	else
//	{
//		printf("Almeno uno dei valori che hai inserito non risulta valido.");
//		return 1;
//	}*/
//	/*float n1, n2;
//	char c;
//	printf("Digita un operando: ");
//	scanf("%f", &n1);
//	printf("Digita un operando: ");
//	scanf("%f", &n2);
//	scanf("%*c");
//	printf("Digita un operatore (+, -, *, /): ");
//	scanf("%c", &c);
//	switch (c)
//	{
//		case '+':
//			n1 = n1 + n2;
//			printf("Il risultato dell'operazione e' %0.2f", n1);
//			break;
//		case '-':
//			n1 = n1 - n2;
//			printf("Il risultato dell'operazione e' %0.2f", n1);
//			break;
//		case '*':
//			n1 = n1 * n2;
//			printf("Il risultato dell'operazione e' %0.2f", n1);
//			break;
//		case '/':
//			if (n2 != 0)
//			{
//				n1 = n1 / n2;
//				printf("Il risultato dell'operazione e' %0.2f", n1);
//			}
//			else
//				printf("Non si puo' dividere per 0.");
//			break;
//		default:
//			printf("L'operatore digitato non risulta definito.");
//	}*/
//	//////////////////////////////////
//	/*float P1, P2, P3;
//	float MAX, TOT;
//	printf("Inserisci un prezzo: ");
//	scanf("%f", &P1);
//	printf("Inserisci un prezzo: ");
//	scanf("%f", &P2);
//	printf("Inserisci un prezzo: ");
//	scanf("%f", &P3);
//	if (P1 >= P2)
//		MAX = P1;
//	else
//		MAX = P2;
//	if (P3 >= MAX)
//		MAX = P3;
//	TOT = P1 + P2 + P3 - (MAX * 0.10);
//	printf("In totale hai speso : %f");*/
//	///////////////////////
//	
//	//int n, g = 0;
//	//printf("Digita il numero corrispondente ad un mese: ");
//	//scanf("%d", &n);
//	//if (n % 3 == 0)
//	//{
//	//	printf("Digita il numero che corrisponde ad un giorno: ");
//	//	scanf("%d", &g);
//	//	if (g >= 1 && g <= 20)
//	//		n = n - 1;
//	//	else
//	//		n = n + 1;
//	//}
//	//switch (n)
//	//{
//	//	case 1: case 2: 
//	//		printf("Sei in inverno.");
//	//		break;
//	//	case 4: case 5:
//	//		printf("Sei in primavera.");
//	//		break;
//	//	case 7: case 8:
//	//		printf("Sei in estate.");
//	//		break;
//	//	case 10: case 11:
//	//		printf("Sei in autunno.");
//	//		break;
//	//}
//	
//	/////////////////////////
//	//char c; //slide 17
//	//do
//	//{
//	//	printf("Digita una lettera: ");
//	//	scanf("%c", &c);
//	//	scanf("%*c");
//	//	if ((c >= 65 && c <= 90) || (c >= 97 && c <= 122))
//	//		printf("Il carattere e' %c e il suo valore ASCII e' %d.\n", c, c);
//	//	else if (c == '0');
//	//	else
//	//		printf("Non hai digitato una lettera.\n");
//	//} 
//	//while (c != '0');
//	///////////////////////
//	/*float B, X;
//	do
//	{
//		printf("Inserisci la base del logaritmo: ");
//		scanf("%f", &B);
//		printf("Inserisci il valore di cui bisogna fare il logaritmo: ");
//		scanf("%f", &X);
//		if (B > 1 && X > 0)
//			printf("Il logaritmo in base %0.2f di %0.2f e' uguale a %0.2f.\n", B, X, log10(X) / log10(B));
//		else
//			printf("Non hai fornito due numeri razionali positivi.\n");
//	} 
//	while (B < 1 || X < 0);*/
//	///////////////////////
//
//	/*signed int n;
//	signed int somma = 0;
//	int prodotto = 1;
//	do
//	{
//		printf("Digita un intero: ");
//		scanf("%d", &n);
//		if (n > 0)
//			prodotto = prodotto * n;
//		else
//			somma = somma + n;
//	} 
//	while (n != 0);
//	printf("La somma e' %d, mentre il prodotto e' %d.", somma, prodotto);*/
//	
//	///////////////////////
//
//	//float a, a1;
//	//int i, n;
//	//float sum = 1;
//	//printf("Digita la base: ");
//	//scanf("%f", &a);
//	//a1 = a;
//	//printf("Digita il numero di elementi da sommare: ");
//	//scanf("%d", &n);
//	//for (i = 0; i < n; i++)
//	//{
//	//	sum = sum + a;
//	//	a = a * a1;
//	//}
//	//printf("Questa e' la sommatoria risultante: %0.2f.", sum);
//
//	////////////////////////// 1 + (1+2) + (1+2+3) + (1+2+3+4) 
//	int a, i, j;
//	int sum = 0;
//	printf("Digita l'indice a della sommatoria: ");
//	scanf("%d", &a);
//	for (i = 1; i <= a; i++)
//		for (j = 1; j <= i; j++)
//			sum = sum + j;
//	printf("Il valore risultante e': %d.", sum);
//}

//pressione invio => line feed (\n) + carriage return (\r) => moving cursor at the begging of the line
//scanf insicura con caratteri e stringhe

//int main() //4 - 4/3 + 4/5 - 4/7
//{ 
//	int n, i;
//	double sum;
//	do
//	{
//		sum = 4;
//		printf("Digita un numero: ");
//		scanf("%d", &n);
//		if (n >= 0)
//		{
//			for (i = 1; i <= n; i++)
//			{
//				if (i % 2 == 0)
//					sum = sum + (4 / (2 * (float) i + 1));
//				else
//					sum = sum - (4 / (2 * (float) i + 1));
//			}
//			printf("Il valore risultante e': %f.\n", sum);
//		}
//		else
//			printf("Il valore inserito e' minore di 0.\n");
//	} 
//	while (n >= 0);
//	return 0;
//}

//int main()
//{
//	int n, i = 0;
//	float sum = 0;
//	do
//	{
//		printf("Digita un numero intero: ");
//		scanf("%d", &n);
//		if (n > 0)
//		{
//			sum = sum + n;
//			i++;
//		}
//	} 
//	while (n != 0);
//	printf("La media dei numeri positivi digitati e': %f", sum/i);
//	return 0;
//}

//int main()
//{
//	int n, m, i, sum = 0, fact;
//	printf("Quanti numeri vuoi inserire?\n");
//	scanf("%d", &n);
//	for (i = 0; i < n; i++)
//	{
//		fact = 1;
//		printf("Digita il numero che vuoi inserire: ");
//		scanf("%d", &m);
//		while (m != 0)
//		{
//			fact = fact * m;
//			m--;
//		} 
//		sum = sum + fact;
//	}
//	printf("Questo e' il risultato dell'operazione: %d.\n", sum);
//	return 0;
//}

//int main()
//{
//	int n, i, r;
//	printf("Digita un numero intero: ");
//	scanf("%d", &n);
//	if (n >= 1 && n <= 10)
//	{
//		r = 1;
//		for (i = 1; i <= n; i++)
//			r = r * n;
//		printf("Il valore che vuoi e': %d.\n", r);
//	}
//	else if (n >= 11 && n <= 20)
//	{
//		r = 0;
//		for (i = 1; i <= n; i++)
//			r = r + i;
//		printf("Il valore che vuoi e': %d.\n", r);
//	}
//	else
//		printf("Il valore digitato non risulta valido.\n");
//	return 0;
//}

//int main()
//{
//	int a, t, i;
//	float c_in, c_fin = 1;
//	printf("Digita la somma a cui ammonta il capitale iniziale: ");
//	scanf("%f", &c_in);
//	printf("Digita il tasso di interesse: ");
//	scanf("%d", &t);
//	printf("Digita il numero di anni dopo i quali verra' ritirato il capitale: ");
//	scanf("%d", &a);
//	if (a > 0 && t > 0 && c_in > 0)
//	{
//		c_fin = c_in;
//		for (i = 1; i <= a; i++)
//		{
//			c_fin = c_fin * (1 + (float) t / 100);
//			printf("Il capitale dopo %d anni ammonta a: %0.2f.\n", i, c_fin);
//		}
//	}
//	else
//		printf("I valori digitati non risultano validi.\n");
//	return 0;
//}

//int main()
//{
//	char n = '0', m = '0';
//	int sum = 0, max = 0;
//	do 
//	{
//		printf("Digita un valore: ");
//		scanf("%c%*c", &n);
//		if (n >= '1' && n <= '9')
//		{
//			if (n >= m)
//				sum = sum + (n - '0');
//			else
//			{
//				if (max <= sum)
//					max = sum;
//				sum = n - '0';
//			}
//			m = n;
//		}
//		else if (n == '0');
//		else
//			printf("Il valore digitato non risulta valido.\n");
//	} 
//	while (n != '0');
//	printf("Il risultato di tale operazione e': %d.\n", max);
//	return 0;
//}

//int mioMax(int x, int y)
//{
//	if (x >= y)
//		return x;
//	else
//		return y;
//}
//
//int max3(int x, int y, int z)
//{
//	/* int max;
//	max = mioMax(x, y);
//	if (z >= max)
//		return z;
//	else
//		return max;*/
//	return mioMax(mioMax(x, y), z);
//}
//
//int main()
//{
//	int n, m, r;
//	int max;
//	printf("Digita un intero: ");
//	scanf("%d", &n);
//	printf("Digita un intero: ");
//	scanf("%d", &m);
//	max = mioMax(n, m);
//	printf("Il valore massimo tra %d e %d e' %d.\n", n, m, max);
//	printf("Digita un intero: ");
//	scanf("%d", &r);
//	max = max3(n, m, r);
//	printf("Il valore massimo tra %d, %d e %d e' %d.\n", n, m, r, max);
//	return 0;
//}

/*int somma(int n)
{
	int i, sum = 0;
	for (i = 1; i <= n; i++)
		sum = sum + i;
	return sum;
}

int somma2(int n)
{
	int i, sum = 0;
	for (i = 1; i <= n; i++)
		sum = somma(i) + sum;
	return sum;
}

int main()
{
	int i, n, sum;
	printf("Digita un intero: ");
	scanf("%d", &n);
	sum = somma(n);
	printf("La sommatoria risultante e': %d.\n", sum);
	sum = somma2(n);
	printf("La sommatoria di sommatoria risultante e': %d.", sum);
	return 0;
}*/

//#include <math.h>
//
//float perimetro(float a, float b, float c)
//{
//	float sum;
//	if (a > 0 && b > 0 && c > 0)
//		sum = a + b + c;
//	else
//		sum = -1;
//	return sum;
//}
//
//float area(float a, float b, float c)
//{
//	float ar, per = perimetro(a, b, c)/2;
//	if (a > 0 && b > 0 && c > 0)
//		ar = (float) sqrt((double) per * ((double) per - (double) a) * ((double) per - (double) b) * ((double) per - (double) c));
//	else
//		ar = -1;
//		return ar;
//}
//
//int main()
//{
//	float n1, n2, n3;
//	float ar;
//	printf("Digita il valore di uno dei lati del triangolo: ");
//	scanf("%f", &n1);
//	printf("Digita il valore di uno dei lati del triangolo: ");
//	scanf("%f", &n2);
//	printf("Digita il valore di uno dei lati del triangolo: ");
//	scanf("%f", &n3);
//	ar = area(n1, n2, n3);
//	if (ar != -1)
//		printf("L'area del triangolo e' %0.2f", ar);
//	else
//		printf("Hai digitato dei valori non validi.\n");
//	return 0;
//}

//int isPrimo(int x) //uno puo' non essere considerato un numero primo: dunque andrebbe escluso con un if
//{
//	int i, r;
//	for (i = 2; i < x; i++)
//	{
//		r = x % i;
//		if (r == 0)
//			return 0;
//	}
//	return 1;
//}
//
//int main()
//{
//	int i, n, ctrl;
//	printf("Digita un numero intero: ");
//	scanf("%d", &n);
//	for (i = 0; i <= n; i++)
//	{
//		ctrl = isPrimo(i);
//		//printf("%d%d ", ctrl, i);
//		if (ctrl == 1)
//			printf("%d ", i);
//	}
//	return 0;
//}

/*#include <math.h>

int ter_pit(int n, int m)
{
	int x, y, z;
	int i, j;
	for (i = n; i <= m; i++)
	{
		x = i;
		for (j = n; j <= m; j++)
		{
			y = x + j;
			z = (int) sqrt((double)x * (double)x + (double)y * (double)y); //calcolo della radice intera della somma del quadrato dei cateti.
			if ((x * x + y * y == z * z) && y < z && z <= m)
				printf("Un esempio di terna pitagorica e': %d %d %d.\n", x, y, z);
		}
	}
	return n;
}

int main()
{
	int lim_inf = 1, lim_sup = 0;
	printf("Digita un numero intero: ");
	scanf("%d", &lim_sup);
	ter_pit(lim_inf, lim_sup);
	return 0;
}*/

//int isTerna(int x, int y, int z, int max)
//{
//	if ((x * x + y * y == z * z) && y < z && z <= max)
//		return 1;
//	else
//		return 0;
//}
//
//int main()
//{
//	int lim_sup = 0, ctrl;
//	int i, j, k;
//	printf("Digita un numero intero: ");
//	scanf("%d", &lim_sup);
//	for (i = 1; i <= lim_sup; i++)
//		for (j = 1; j <= lim_sup; j++)
//			for (k = 1; k <= lim_sup; k++)
//			{
//				ctrl = isTerna(i, j, k, lim_sup);
//				if (ctrl == 1)
//					printf("Un esempio di terna pitagorica e': %d %d %d.\n", i, j, k);
//			}
//	return 0;
//}


//fai es.4-es.8

/*int potenza(int x, int y)
{
	int i, acc = 1;
	for (i = 1; i <= y; i++)
		acc = acc * x;
	return acc;
}

int sommaPotenze(int a, int n)
{
	int i, acc = 0;
	for (i = 1; i <= n; i++)
			acc = acc + potenza(a, i);
	return acc;
}

void main()
{
	int n, a, res;
	printf("Digita un valore di a: ");
	scanf("%d", &a);
	printf("Digita un valore di n: ");
	scanf("%d", &n);
	res = sommaPotenze(a, n);
	printf("Il risultato e': %d.\n", res);
	return 0;
}*/

/*int ric(int x)
{
	if (x == 0)
		return 0;
	else
		return x + ric(x-1);
}

void main()
{
	int n;
	printf("Digita un numero intero: ");
	scanf("%d", &n);
	n = ric(n);
	printf("Il risultato e': %d.\n", n);
	return 0;
}*/

/*double f_it(double a, int n)
{
	int i;
	double sum = 0;
	for (i = 1; i <= n; i++)
		sum = sum + (a - (double)i / a);
	return sum;
}

double f_ric(double a, int n)
{
	if (n == 0)
		return 0;
	else
		return (a - n/a) + f_ric(a, n - 1);
	return a;
}

int main()
{
	int n;
	double a;
	double sum;
	printf("Digita un valore di n: ");
	scanf("%d", &n);
	printf("Digita un valore di a: ");
	scanf("%lf", &a);
	sum = f_it(a, n);
	printf("La somma iterativa e': %lf.\n", sum);
	sum = f_ric(a, n);
	printf("La somma ricorsiva e': %lf.\n", sum);
	return 0;
}*/
//---------------------------
/*int inv_it(int x)
{
	int res = 0;
	while (x != 0)
	{
		res = res * 10;
		res = res + x % 10; //volendo: res = res * 10 + x % 10;
		x = x / 10;
	}
	return res;
}

int inv_ric(int x) //rende possibile modificare il prototipo.
{
	return 	calc_rev_ric(x, 0);
}

int calc_rev_ric(int x, int res)
{
	if (x == 0)
		return res;
	else
		return calc_rev_ric(x / 10, res * 10 + x % 10);
}

int main()
{
	int n, res;
	printf("Digita un numero intero: ");
	scanf("%d", &n);
	res = inv_it(n);
	printf("Il risultato e': %d.\n", res);
	res = inv_ric(n);
	printf("Il risultato e': %d.\n", res);
	return 0;
}*/
//---------------------------

int inv_caratt(char x, int n)
{
	if (x == '\n')
		printf("%d", n - 1);
	else
	{
		scanf("%c", &x);
		inv_caratt(x, n + 1);
		printf("%c", x);
	}
}

int main()
{
	char c = '0'; int n = 0;
	printf("Digita una serie di caratteri: ");
	inv_caratt(c, n);
	return 0;
}