
#include <stdio.h>

//funzioni

//es4

/*float square(float x)
{
	x = x * x;
	return x;
}

float cube(float x)
{
	x = square(x) * x;
	return x;
}

int main()
{
	float n;
	printf("Digita un numero reale: ");
	scanf("%f", &n);
	n = cube(n);
	printf("Questo e' il cubo di tale numero: %f.\n", n);
	return 0;
}*/

//es 5

/*float euro_to_dollari(float money)
{
	money = money * 0.98;
	return money;
}

float euro_to_lire(float money)
{
	money = money * 1936.27;
	return money;
}

int main()
{
	float n, res;
	printf("Inserisci la cifra in euro da convertire: ");
	scanf("%f", &n);
	res = euro_to_dollari(n);
	printf("Questo e' la cifra convertita in dollari: %f.\n", res);
	res = euro_to_lire(n);
	printf("Questo e' la cifra convertita in lire: %f.\n", res);
	return 0;
}*/

//es 6

/*int min_to_sec(int a)
{
	a = a * 60;
	return a;
}

int ore_to_sec(int a)
{
	a = min_to_sec(min_to_sec(a));
	return a;
}

int main()
{
	int sec, min, ore, sum = 0;
	printf("Inserisci la cifra dei secondi: ");
	scanf("%d", &sec);
	printf("Inserisci la cifra dei minuti: ");
	scanf("%d", &min);
	printf("Inserisci la cifra delle ore: ");
	scanf("%d", &ore);
	min = min_to_sec(min);
	ore = ore_to_sec(ore);
	sum = sec + min + ore;
	printf("Il risultato e' %d.\n", sum);
	return 0;
}*/

//es 7

/*#include <math.h>

float ipotenusa(float a, float b)
{
	float i;
	i = sqrt(a * a + b * b);
	return i;
}

int main()
{
	float l, m;
	printf("Inserisci un cateto: ");
	scanf("%f", &l);
	printf("Inserisci un cateto: ");
	scanf("%f", &m);
	l = ipotenusa(l, m);
	printf("L'ipotenusa del triangolo e' %f.\n", l);
	return 0;
}*/

//ricorsione

//es 5

/*int mcd(x, y)
{
	if (x == 0 && y == 0)
			return 0;
	else if (x != 0 && y == 0)
		return x;
	else if (x == 0 && y != 0)
		return y;
	else
	{
		if (x == y)
			return x;
		else if (x > y)
			return mcd(x - y, y);
		else if (x < y)
			return mcd(x, y - x);
		else
			return 0;
	}
}

int main()
{
	int x, y, res;
	printf("Inserisci un numero: ");
	scanf("%d", &x);
	printf("Inserisci un numero: ");
	scanf("%d", &y);
	res = mcd(x, y);
	printf("Il risultato e' %d.\n", res);
	return 0;
}*/

//es 6

int checkPal(char x, int n)

{
	char y;
	scanf("%c*c", &x);
	if (x == '@')
		return 0;
	else
	{
		n = checkPal(x, n); //mi dice se l'estremo sup e quello inf coincidono
		if (n) //mi serve se le due metà non hanno la stessa lunghezza
			return n;
		scanf("%c", &y);
		if (x == y)
			return n;
		else 
			return n + 1;		
	}
}

int isPal(char x)
{
	int n;
	n = !checkPal(x, 0);
	if (n == 1 && getchar() != '\n') //sequenze potenzialmente palindrome, ma in realtà di lunghezze diverse
		n = !n;
	return n;
}

int main()
{
	char c = '0';
	int res; 
	printf("Inserisci un carattere: ");
	res = isPal(c);
	if (res == 1)
		printf("Hai digitato una sequenza palindroma!\n");
	else
		printf("Non hai stampato una sequenza palindroma :(\n");
	return 0;
}

