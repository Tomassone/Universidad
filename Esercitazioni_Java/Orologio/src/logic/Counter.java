package logic;

public class Counter
{
	private int value;
	
	public Counter()
	{ 
		value = 1; 
	}
	
	public Counter(int v)
	{ 
		value = v; 
	}
	
	public void reset()
	{
		this.value = 0;
	}
	
	public void inc()
	{
		this.value++;
	}
	
	public int getValue()
	{
		return this.value;
	}
	
	public boolean equals(Counter that)
	{
		return this.value == that.value;
	}
	
	public String toString()
	{
		return Integer.toString(this.value);
	}
}
