package frazione; //la classe appartiene al package frazione.
import util.MyMath; //importo la classe MyMath del pacchetto util.
/**
 * Frazione come tipo di dato astratto (ADT) - Prima Parte
 * 
 * @author Fondamenti di Informatica T-2
 * @version March 2023
 */
public class Frazione {
	private int num, den;

	/**
	 * Costruttore della Frazione
	 * 
	 * @param num
	 *            Numeratore
	 * @param den
	 *            Denominatore
	 */
	public Frazione(int num, int den) {
		boolean negativo = num * den < 0;
		this.num = negativo ? -Math.abs(num) : Math.abs(num);
		this.den = Math.abs(den);
	}

	/**
	 * Costruttore della Frazione
	 * 
	 * @param num
	 *            Numeratore
	 */
	public Frazione(int num) {
		this(num, 1);
	}

	/**
	 * Recupera il numeratore
	 * 
	 * @return Numeratore della frazione
	 */
	public int getNum() {
		return num;
	}

	/**
	 * Recupera il denominatore
	 * 
	 * @return Denominatore della frazione
	 */
	public int getDen() {
		return den;
	}

	/**
	 * Calcola la funzione ridotta ai minimi termini.
	 * 
	 * @return Una nuova funzione equivalente all'attuale, ridotta ai minimi
	 *         termini.
	 */
	public Frazione minTerm() {
		if (getNum() == 0) return new Frazione(getNum(), getDen());
		int mcd = MyMath.mcd(Math.abs(getNum()), getDen());
		int n = getNum() / mcd;
		int d = getDen() / mcd;
		return new Frazione(n, d);
	}

	public boolean equals(Frazione f) {
		return f.getNum() * getDen() == f.getDen() * getNum();
	}

	@Override
    public String toString() {
	    String str = "";
 		int num = getNum();
		int den = getDen();

		str += getDen() == 1 ? num : num + "/" + den;		
		return str;	   
	}
	
	private Frazione deMinTerm(int n, int mcm, int fatt_molt)
	{
		if (getNum() == 0) 
			return new Frazione(getNum(), mcm);
		n = n * fatt_molt;
		return new Frazione(n, mcm);
	}
	
	public Frazione sumWithMcm(Frazione that) {
		int mcm = MyMath.mcm(this.getDen(), that.getDen());
		int fatt_molt = mcm / this.getDen();
		Frazione f1 = deMinTerm(this.getNum(), mcm, fatt_molt);
		fatt_molt = mcm / that.getDen();
		Frazione f2 = deMinTerm(that.getNum(), mcm, fatt_molt);
		Frazione fSum = new Frazione(f1.getNum() + f2.getNum(), f1.getDen());
		return fSum.minTerm();
	}
	
	public Frazione sum(Frazione f) {
		int mcm = MyMath.mcm(f.getDen(), this.getDen());
		int n = ((mcm / this.getDen()) * this.getNum()) + ((mcm / f.getDen()) * f.getNum());
		int d = mcm;
		return (new Frazione(n, d)).minTerm();
	}
	
	public Frazione mul(Frazione that) {
		Frazione result = new Frazione(this.getNum() * that.getNum(), this.getDen() * that.getDen());
		return result.minTerm();
	}
	
	public Frazione reciprocal() {
		if (this.getNum() != 0)
		{
			Frazione result = new Frazione(this.getDen(), this.getNum());
			return result.minTerm();
		}
		else 
		{
			System.out.println("Errore, non puoi fare il reciproco di questa frazione!");
			return new Frazione(0);
		}
	}
	
	public Frazione sub(Frazione f) {
		int n = num * f.den - den * f.num;
		int d = den * f.den;
		Frazione fSub = new Frazione(n, d);
		return fSub.minTerm();
	}
	
	public double getDouble()
	{
		return (double) this.getNum() / (double) this.getDen(); 
	}
	
	public int compareTo(Frazione that)
	{
		Frazione f1 = this.minTerm();
		Frazione f2 = that.minTerm();
		if (f1.equals(f2))
			return 0;
		else if (this.getDouble() > that.getDouble())
			return 1;
		else
			return -1;
	}
	
	public Frazione div(Frazione that)
	{
		return this.mul(that.reciprocal());
	}
	
	public static Frazione sum(Frazione[] tutte)
	{
		Frazione result;
		result = new Frazione(0, 1); //inizializzo la mia frazione di somma.
		for (Frazione fraz : tutte)
			result = result.sum(fraz); //necessario perchè i metodi della classe non sono distruttivi.
		return result;
	}
		
	public static Frazione mul(Frazione[] tutte)
	{
		Frazione result;
		result = new Frazione(1, 1); //inizializzo la mia frazione di somma.
		for (Frazione fraz : tutte)
			result = result.mul(fraz); //necessario perchè i metodi della classe non sono distruttivi.
		return result;
	}
}
