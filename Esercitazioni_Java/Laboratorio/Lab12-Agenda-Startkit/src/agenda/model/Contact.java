package agenda.model;

import java.util.List;
import java.util.Objects;
import java.util.*;

public class Contact implements Comparable
{
	private String name;
	private String surname;
	private List<Detail> detail;

	@Override
	public int compareTo(Object o)
	{
		Contact that = (Contact) o;
		if (that.getName().compareTo(this.getName()) != 0)
			return that.getName().compareTo(this.getName());
		else
			return that.getSurname().compareTo(this.getSurname());
	}
	
	public Contact (String name, String surname)
	{
		this.name = name;
		this.surname = surname;
		this.detail = new ArrayList<Detail>();
	}
	
	public Contact (String name, String surname, List<Detail> detail)
	{
		this.name = name;
		this.surname = surname;
		this.detail = detail;
	}
	
	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public List<Detail> getDetailList()
	{
		return this.detail;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(detail, name, surname);
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		return Objects.equals(detail, other.detail) && Objects.equals(name, other.name)
				&& Objects.equals(surname, other.surname);
	}

	@Override
	public String toString()
	{
		String result = this.getName() + " " + this.surname + "\n";
		for (Detail dettaglio : this.getDetailList())
		{
			result = result + dettaglio.getName() + " :: " + dettaglio.getDescription() + "\n";
			result = result + dettaglio.getValues() + "\n";
		}
		return result;
	}
}
