package model;

public class AppointmentCollection
{
	private static final int DEFAULT_PHYSICAL_SIZE = 10;
	private static final int DEFAULT_GROWTH_FACTOR = 2;
	
	private Appointment[] innerContainer;
	private int size;
	
	public AppointmentCollection(int capacity)
	{
		innerContainer = new Appointment[capacity];
		size = 0;
	}
	
	public AppointmentCollection()
	{
		this(DEFAULT_PHYSICAL_SIZE);
	}
	
	public int size()
	{
		return size;
	}
	
	public int indexOf(Appointment element)
	{
		for (int i = 0; i < this.size(); i++)
			if (this.innerContainer[i].equals(element))
				return i;
		return -1; //appuntamento non presente nella collezione di appuntamenti.
	}
	
	public void add(Appointment f)
	{
		if (innerContainer.length == size)
		{
			Appointment[] newContainer = new Appointment[size * DEFAULT_GROWTH_FACTOR];
			for (int i = 0; i < innerContainer.length; i++)
				newContainer[i] = innerContainer[i];
			innerContainer = newContainer;
		}
		innerContainer[size++] = f;
	}
	
	public void remove(int index)
	{
		if (index < 0 || index >= size)
			return;
		
		for (int i = index; i < size - 1; i++)
		{
			innerContainer[i] = innerContainer[i + 1];
		}
		size--;
	}
	
	public Appointment get(int index)
	{
		if (index < 0 || index >= size)
			return null;
	
		return innerContainer[index];
	}
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < size(); i++)
		{
			sb.append(get(i).toString());
			if (i < size() - 1)
				sb.append(", ");
		}
		sb.append("]");
		return sb.toString();
	}
}
