package agenda.persistence;

import java.util.StringTokenizer;

import agenda.model.EMail;
import agenda.model.Detail;

public class EMailPersister implements DetailPersister
{
	private final String SEPARATOR = ";";
	
	@Override
	public Detail load(StringTokenizer source) throws BadFileFormatException
	{
		if (!source.hasMoreElements())
			throw new BadFileFormatException("Detail or EndContact expected");
		
		EMail result = new EMail();
		
		result.setDescription(source.nextToken());
		result.setValue(source.nextToken());
		
		return result;
	}

	@Override
	public void save(Detail d, StringBuilder sb) 
	{
		EMail email = (EMail) d;
		sb.append("EMail" + this.SEPARATOR + d.getDescription() + this.SEPARATOR + email.getValue() + "\n");
	}
}
