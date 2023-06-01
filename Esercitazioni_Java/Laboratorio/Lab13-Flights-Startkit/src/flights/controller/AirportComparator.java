package flights.controller;

import java.util.Comparator;

import flights.model.Airport;

public class AirportComparator implements Comparator<Airport>
{
	@Override
	public int compare(Airport a1, Airport a2)
	{
		if (a1.getCity().getName().equals(a2.getCity().getName()))
			return a1.getName().compareTo(a2.getName());
		else
			return a1.getCity().getName().compareTo(a2.getCity().getName());
	}
}
