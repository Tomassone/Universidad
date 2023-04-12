package fondt2.tlc;

public class PhonePlan
{
	private String name;
	private Rate[] rates;
	
	public double getCallCost(PhoneCall call)
	{
		int i = 0;
		while (!rates[i].isApplicableTo(call.getDestNumber()))
			i++;
		return rates[i].getCallCost(call);
	}
	
	public boolean isValid()
	{
		for (int i = 0; i < this.getRates().length; i++)
			if (!this.rates[i].isValid()) //se anche solo una delle tariffe non è valida, il piano telefonico non è valido.
				return false;
		return true;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	private Rate[] getRates()
	{
		return this.rates;
	}
	
	public PhonePlan(String name, Rate[] rates)
	{
		this.name = name;
		this.rates = rates;
	}
	
}
