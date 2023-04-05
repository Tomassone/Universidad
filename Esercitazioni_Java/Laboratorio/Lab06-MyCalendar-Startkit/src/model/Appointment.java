package model;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class Appointment
{
	private String description;
	private LocalDateTime from;
	private LocalDateTime to;
	
	public Appointment(String description, LocalDateTime from, LocalDateTime to)
	{
		this.setDescription(description);
		this.setFrom(from);
		this.setTo(to);
	}
	
	public Appointment(String description, LocalDateTime from, Duration duration)
	{
		this.setDescription(description);
		this.setFrom(from);
		this.setDuration(duration);
	}
	
	public boolean equals(Appointment that)
	{
		return (this.getDescription().equals(that.getDescription()) && this.getFrom().equals(that.getFrom()) && this.getTo().equals(that.getTo()));
	}
	
	public String getDescription()
	{
		return this.description;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public LocalDateTime getFrom()
	{
		return this.from;
	}
	
	public void setFrom(LocalDateTime from)
	{
		this.from = from;
	}

	public LocalDateTime getTo()
	{
		return this.to;
	}
	
	public void setTo(LocalDateTime to)
	{
		this.to = to;
	}
	
	public Duration getDuration ()
	{
		return Duration.between(this.getFrom(), this.getTo());
	}
	
	public void setDuration (Duration duration)
	{
		this.to = this.from.plusSeconds(duration.getSeconds());
	}
	
	public String toString()
	{
		DateTimeFormatter frmt = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT, FormatStyle.SHORT).withLocale(Locale.ITALY);
		return "Appointement: " + description + " from " + frmt.format(from) + " to " + frmt.format(from) + ".";
	}
}
