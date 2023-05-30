package flights.model;

import java.time.*;
import java.util.*;

public class FlightSchedule
{
	private Aircraft aircraft;
	private Airport arrivalAirport;
	private LocalTime arrivalLocalTime;
	private String code;
	private int dayOffset;
	private Set<DayOfWeek> DaysOfWeek;
	private Airport departureAirport;
	private LocalTime departureLocalTime;
	
	@Override
	public int hashCode()
	{
		return Objects.hash(DaysOfWeek, aircraft, arrivalLocalTime, arrivalAirport, code, dayOffset, departureAirport,
				departureLocalTime);
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
		FlightSchedule other = (FlightSchedule) obj;
		return Objects.equals(DaysOfWeek, other.DaysOfWeek) && Objects.equals(aircraft, other.aircraft)
				&& Objects.equals(arrivalLocalTime, other.arrivalLocalTime)
				&& Objects.equals(arrivalAirport, other.arrivalAirport) && Objects.equals(code, other.code)
				&& dayOffset == other.dayOffset && Objects.equals(departureAirport, other.departureAirport)
				&& Objects.equals(departureLocalTime, other.departureLocalTime);
	}

	public Aircraft getAircraft()
	{
		return this.aircraft;
	}
	
	public Airport getArrivalAirport()
	{
		return this.arrivalAirport;
	}
	
	public LocalTime getArrivalLocalTime()
	{
		return this.arrivalLocalTime;
	}
	
	public String getCode()
	{
		return this.code;
	}
	
	public int getDayOffset()
	{
		return this.dayOffset;
	}
	
	public Set<DayOfWeek> getDaysOfWeek()
	{
		return this.DaysOfWeek;
	}
	
	public Airport getDepartureAirport()
	{
		return this.departureAirport;
	}
	
	public LocalTime getDepartureLocalTime()
	{
		return this.departureLocalTime;
	}

	public FlightSchedule(String code, Airport departureAirport, LocalTime departureLocalTime, Airport arrivalPort, LocalTime arrivalLocalTime, int dayOffset, Collection<DayOfWeek> dayOfWeek, Aircraft aircraft)
	{
		this.aircraft = aircraft;
		this.arrivalAirport = arrivalPort;
		this.arrivalLocalTime = arrivalLocalTime;
		this.code = code;
		this.dayOffset = dayOffset;
		this.DaysOfWeek = new HashSet<DayOfWeek>(dayOfWeek);
		this.departureAirport = departureAirport;
		this.departureLocalTime = departureLocalTime;
	}
	
	public Duration getFlightDuration()
	{
		OffsetDateTime departureAbsoluteTime = OffsetDateTime.of(LocalDate.now(), this.getDepartureLocalTime(), ZoneOffset.ofHours(this.getDepartureAirport().getCity().getTimeZone()));
		OffsetDateTime arrivalAbsoluteTime = OffsetDateTime.of(LocalDate.now().plusDays(getDayOffset()), this.arrivalLocalTime, ZoneOffset.ofHours(this.getArrivalAirport().getCity().getTimeZone()));
		Duration result = Duration.between(departureAbsoluteTime, arrivalAbsoluteTime);
		
		if (result.isNegative())
			result = result.plusHours(24);

		return result;
	}
	
}
