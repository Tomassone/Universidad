package agenda.persistence;

import java.util.StringTokenizer;
import agenda.model.*;
import agenda.model.Detail;

public class AddressPersister implements DetailPersister
{
	private final String SEPARATOR = ";";
	
	@Override
	public Detail load(StringTokenizer source) throws BadFileFormatException
	{
		if (source.hasMoreElements())
			throw new BadFileFormatException("Detail or EndContact expected");
		
		Address result = new Address();
		
		result.setDescription(source.nextToken());
		result.setStreet(source.nextToken());
		result.setNumber(source.nextToken());
		result.setZipCode(source.nextToken());
		result.setCity(source.nextToken());
		result.setState(source.nextToken());
		
		return result;
	}

	@Override
	public void save(Detail d, StringBuilder sb) 
	{
		Address indirizzo = (Address) d;
		sb.append("Address" + this.SEPARATOR + indirizzo.getDescription() + this.SEPARATOR + indirizzo.getStreet() + this.SEPARATOR + indirizzo.getNumber() + this.SEPARATOR + indirizzo.getZipCode() + this.SEPARATOR + indirizzo.getCity() + this.SEPARATOR + indirizzo.getState() + "\n");
	}
}
