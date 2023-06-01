package flights.controller;

import java.time.LocalDate;
import java.util.*;

import flights.model.Airport;
import flights.model.FlightSchedule;
import flights.persistence.DataManager;

public class MyController implements Controller
{
	DataManager dataManager;
	List<Airport> sortedAirports;
	
	@Override
	public List<Airport> getSortedAirports()
	{
		AirportComparator airportComparator = new AirportComparator();
		Collections.sort(this.sortedAirports, airportComparator);
		return this.sortedAirports;
	}

	@Override
	public List<FlightSchedule> searchFlights(Airport departureAirport, Airport arrivalAirport, LocalDate date)
	{
		List<FlightSchedule> result = new ArrayList<FlightSchedule>();
		for (FlightSchedule voloPianificato : this.dataManager.getFlightSchedules())
		{
			if (voloPianificato.getArrivalAirport().equals(arrivalAirport) && voloPianificato.getDepartureAirport().equals(departureAirport) && voloPianificato.getDaysOfWeek().contains(date.getDayOfWeek()))
				result.add(voloPianificato);
		}
		return result;
	}
	
	public MyController(DataManager dataManager)
	{
		this.dataManager = dataManager;
		this.sortedAirports = new ArrayList<Airport>(this.dataManager.getAirportMap().values());
	}
}
