package flights.persistence;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;
import flights.model.*;

public class DataManager
{
	private HashMap<String, Airport> airportMap;
	private HashMap<String, Aircraft> aircraftMap;
	private Collection<FlightSchedule> flightSchedules;
	private AircraftsReader aircraftsReader;
	private CitiesReader citiesReader;
	private FlightScheduleReader flightScheduleReader;
	
	public DataManager(CitiesReader citiesReader, AircraftsReader aircraftsReader, FlightScheduleReader flightScheduleReader)
	{
		this.aircraftsReader = aircraftsReader;
		this.citiesReader = citiesReader;
		this.flightScheduleReader = flightScheduleReader;
		this.airportMap = new HashMap<String, Airport>();
		this.aircraftMap = new HashMap<String, Aircraft>();
	}
	
	public Map<String, Airport> getAirportMap()
	{
		return this.airportMap;
	}
	
	public Map<String, Aircraft> getAircraftMap()
	{
		return this.aircraftMap;
	}
	
	public Collection<FlightSchedule> getFlightSchedules()
	{
		return this.flightSchedules;
	}
	
	public void readAll() throws IOException, BadFileFormatException
	{
		Reader lettoreAircrafts = new FileReader("Aircrafts.txt");
		Reader lettoreCities = new FileReader("Cities.txt");
		Reader lettoreSchedules = new FileReader("FlightSchedule.txt");
				
		for (City cittadina : this.citiesReader.read(lettoreCities))
			for (Airport aereoporto: cittadina.getAirports())
				this.airportMap.put(aereoporto.getCode(), aereoporto);
		
		for (Aircraft veivolo : this.aircraftsReader.read(lettoreAircrafts))
				this.aircraftMap.put(veivolo.getCode(), veivolo);
		
		this.flightSchedules = this.flightScheduleReader.read(lettoreSchedules, this.getAirportMap(), this.getAircraftMap());
	}
	
}
