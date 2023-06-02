package flights.persistence;

import java.util.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;

import flights.model.Aircraft;
import flights.model.Airport;
import flights.model.FlightSchedule;

public class MyFlightScheduleReader implements FlightScheduleReader
{
	private DateTimeFormatter timeFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).withLocale(Locale.ITALY);
	
	private Collection<DayOfWeek> readDaysOfWeek(String days) throws BadFileFormatException
	{
		Collection<DayOfWeek> result = new ArrayList<DayOfWeek>();
		
		if (days.length() != 7)
			throw new BadFileFormatException();
		
		for (int i = 0; i < days.length(); i++)
		{
			if (days.charAt(i) != '-')
			{
				if (Character.getNumericValue(days.charAt(i)) != (i + 1))
					throw new BadFileFormatException();
				result.add(DayOfWeek.of(i + 1));
				//System.out.println(i + 1);
			}
		}
		return result;
	}
	
	private FlightSchedule readSchedule(BufferedReader reader, Map<String, Airport> airportMap, Map<String, Aircraft> aircraftMap) throws IOException, BadFileFormatException
	{
		String lineaLetta = reader.readLine();
		if (lineaLetta == null || lineaLetta.trim().isEmpty())
			return null;
		
		StringTokenizer st = new StringTokenizer(lineaLetta, ",");
		
		String idVolo = st.nextToken();
		//System.out.println(idVolo);
		//System.out.println(arrivalTime);
		String departureAirport = st.nextToken();
		//System.out.println(departureAirport);
		LocalTime departureTime;
		try
		{
			departureTime = LocalTime.parse(st.nextToken(), this.timeFormatter);
		}
		catch(DateTimeParseException e)
		{
			throw new BadFileFormatException();
		}
		//System.out.println(departureTime);
		String arrivalAirport = st.nextToken();
		//System.out.println(arrivalAirport);
		LocalTime arrivalTime;
		try
		{
			arrivalTime = LocalTime.parse(st.nextToken(), this.timeFormatter);
		}
		catch(DateTimeParseException e)
		{
			throw new BadFileFormatException();
		}
		
		int dayOffset = 0;
		try
		{
			dayOffset = Integer.parseInt(st.nextToken());
		}
		catch (NumberFormatException e)
		{
			throw new BadFileFormatException();
		}
		//System.out.println(dayOffset);
		Collection<DayOfWeek> daysOfWeek = readDaysOfWeek(st.nextToken());
		String aircraftType = st.nextToken();
		//System.out.println(aircraftType);
		Airport trueDepartureAirport = airportMap.get(departureAirport);
		Airport trueArrivalAirport = airportMap.get(arrivalAirport);
		Aircraft aircraft = aircraftMap.get(aircraftType);
		if (trueDepartureAirport == null)
			throw new BadFileFormatException("missing departure airport");
		if (trueArrivalAirport == null)
			throw new BadFileFormatException("missing arrival airport");
		if (aircraft == null)
			throw new BadFileFormatException("missing aircraft");
		return new FlightSchedule(idVolo, trueDepartureAirport, departureTime, trueArrivalAirport, arrivalTime, dayOffset, daysOfWeek, aircraft);
	}
	
	@Override
	public Collection<FlightSchedule> read(Reader reader, Map<String, Airport> airportMap,
			Map<String, Aircraft> aircraftMap) throws IOException, BadFileFormatException 
	{
		Collection<FlightSchedule> result = new ArrayList<FlightSchedule>();
		BufferedReader bf = new BufferedReader(reader);
		FlightSchedule tempSchedule;
		bf.mark(0);
		do
		{
			bf.reset();
			tempSchedule = this.readSchedule(bf, airportMap, aircraftMap);
			if (tempSchedule != null)
				result.add(tempSchedule);
			bf.mark(0);
		}
		while(bf.readLine() != null);
		return result;
	}
}
