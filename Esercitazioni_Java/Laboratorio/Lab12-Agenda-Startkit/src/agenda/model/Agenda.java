package agenda.model;

import java.util.*;

public class Agenda
{
	private SortedSet<Contact> contactSet;
	
	public Agenda()
	{
		this.contactSet = new TreeSet<Contact>();
	}
	
	public Agenda(List<Contact> contacts)
	{
		this();
		for (Contact contatto : contacts)
		{
			this.contactSet.add(contatto);
		}
	}
	
	public SortedSet<Contact> getContacts()
	{
		return this.contactSet;
	}
	
	public Optional<Contact> getContact(String name, String surname)
	{
		Contact[] elencoContatti = this.contactSet.toArray(new Contact[0]);
		for (Contact contatto : elencoContatti)
		{
			if (contatto.getName().equals(name) && contatto.getSurname().equals(surname))
				return Optional.of(contatto);
		}
		return Optional.empty();
	}
	
	public Optional<Contact> getContact(int index)
	{
		Contact[] elencoContatti = this.contactSet.toArray(new Contact[0]);
		for (int i = 0; i < elencoContatti.length; i++)
		{
			if (i == index)
				return Optional.of(elencoContatti[i]);
		}
		return Optional.empty();
	}
	
	public void removeContact(Contact c)
	{
		Contact[] elencoContatti = this.contactSet.toArray(new Contact[0]);
		for (Contact contatto : elencoContatti)
		{
			if (contatto.equals(c))
				this.getContacts().remove(contatto);
		}
	}
	
	public void addContact(Contact c)
	{
		this.getContacts().add(c);
	}
	
	public SortedSet<Contact> searchContacts(String secondName)
	{
		Contact[] elencoContatti = this.contactSet.toArray(new Contact[0]);
		SortedSet<Contact> result = new TreeSet<Contact>();
		for (Contact contatto : elencoContatti)
		{
			if (contatto.getSurname().equals(secondName))
				result.add(contatto);
		}
		return result;
	}
}
