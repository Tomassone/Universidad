package control;

import model.*;
import java.time.*;

public class MyCalendar
{
	private AppointmentCollection allApp;
	
	public MyCalendar()
	{
		this.allApp = new AppointmentCollection();
	}
	
	public void add (Appointment newElement)
	{
		this.allApp.add(newElement);
	}
	
	public AppointmentCollection getAllAppointments()
	{
		return allApp;
	}
	
	private AppointmentCollection getAppointmentsIn(LocalDateTime start, LocalDateTime end)
	{
		AppointmentCollection result = new AppointmentCollection();
		for (int i = 0; i < this.getAllAppointments().size(); i++)
			if (isOverlapped(this.getAllAppointments().get(i).getFrom(), this.getAllAppointments().get(i).getTo(), start, end))
				result.add(this.getAllAppointments().get(i));
		return result;
	}
	
	public AppointmentCollection getDayAppointments(LocalDate date)
	{
		LocalDateTime start = LocalDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(), 0, 0, 0);
		LocalDateTime end = start.plusDays(1);
		return this.getAppointmentsIn(start, end);
	}
	
	public AppointmentCollection getMonthAppointments(LocalDate date)
	{
		LocalDateTime start = LocalDateTime.of(date.getYear(), date.getMonth(), 1, 0, 0, 0);
		LocalDateTime end = start.plusMonths(1);
		return this.getAppointmentsIn(start, end);
	}
	
	public AppointmentCollection getWeekAppointments(LocalDate date)
	{
		LocalDateTime start = LocalDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(), 0, 0, 0).minusDays(date.getDayOfWeek().getValue() - 1);
		LocalDateTime end = start.plusDays(7);
		return this.getAppointmentsIn(start, end);
	}
	
	private boolean isOverlapped(LocalDateTime start, LocalDateTime end, LocalDateTime refStart, LocalDateTime refEnd)
	{
		// 4 casi di intersezione
		/*if (start.isAfter(refStart) && end.isBefore(refEnd))
			return true;
		else if (start.isBefore(refStart) && end.isAfter(refEnd))
			return true;
		else if (start.isAfter(refStart) && end.isBefore(refEnd))
			return true;
		else if (start.isBefore(refStart) && end.isAfter(refStart))
			return true;
		else if (start.isBefore(refEnd) && end.isAfter(refEnd))
			return true;*/
		if (start.isBefore(refEnd) && end.isAfter(refStart))
			return true;
		else
			return false;
	}
	
	public boolean remove (Appointment toBeDeleted)
	{
		for (int i = 0; i < this.getAllAppointments().size(); i++)
			if (this.getAllAppointments().get(i).equals(toBeDeleted))
			{
				this.getAllAppointments().remove(i);
				return true;
			}
		return false;
	}
}
