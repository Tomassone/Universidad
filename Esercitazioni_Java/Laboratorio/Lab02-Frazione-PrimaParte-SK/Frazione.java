
public class Frazione
{
	int num, den;
	
	public Frazione(int n, int d)
	{
		if (Math.signum(n) == Math.signum(d) && Math.signum(d) != 0)
		{
			this.num = Math.abs(n); 
			this.den = Math.abs(d);
		}
		else if (Math.signum(n) != Math.signum(d) && Math.signum(d) != 0)
		{
			this.num = -Math.abs(n);
			this.den = Math.abs(d);
		}
		else
		{
			System.out.println("Errore! Non è possibile creare una frazione con denominatore 0.");
		}
	}
	
	public Frazione(int n)
	{	
		this.num = n; 
		this.den = 1;
		//volendo anche: this(num, 1);
	}
	
	public boolean equals(Frazione that)
	{
		if ((this.num * that.den) == (this.den * that.num))
			return true;
		else
			return false;
		//più sensato: return (this.num * that.den) == (this.den * that.num).
	}
	
	public int getDen()
	{
		return this.den;
	}
	
	public int getNum()
	{
		return this.num;
	}
	
	public Frazione minTerm()
	{
		int div_com = MyMath.mcd(this.num, this.den);
		Frazione result = new Frazione(this.num / div_com, this.den / div_com);
		return result;
	}
	
	public String toString()
	{
		return this.getNum() + "/" + this.getDen();
	}
}