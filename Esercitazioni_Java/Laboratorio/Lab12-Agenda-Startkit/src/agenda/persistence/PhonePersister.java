package agenda.persistence;

import java.util.StringTokenizer;

import agenda.model.Detail;
import agenda.model.Phone;

public class PhonePersister implements DetailPersister
{
private final String SEPARATOR = ";";
	
	@Override
	public Detail load(StringTokenizer source) throws BadFileFormatException
	{
		if (!source.hasMoreElements())
			throw new BadFileFormatException("Detail or EndContact expected");
		
		Phone result = new Phone();
		
		result.setDescription(source.nextToken());
		result.setValue(source.nextToken());
		
		return result;
	}

	@Override
	public void save(Detail d, StringBuilder sb) 
	{
		Phone phone = (Phone) d;
		sb.append("Phone" + this.SEPARATOR + phone.getDescription() + this.SEPARATOR + phone.getValue() + "\n");
	}
}
