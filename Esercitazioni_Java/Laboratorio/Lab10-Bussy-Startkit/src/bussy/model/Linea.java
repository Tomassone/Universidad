package bussy.model;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

abstract public class Linea
{
	public String id;
	public Map<Integer, Fermata> orarioPassaggioAlleFermate;
	
	@Override
	public int hashCode() 
	{
		return Objects.hash(id, orarioPassaggioAlleFermate);
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
		Linea other = (Linea) obj;
		return Objects.equals(id, other.id)
				&& Objects.equals(orarioPassaggioAlleFermate, other.orarioPassaggioAlleFermate);
	}

	public String getId()
	{
		return id;
	}

	public Map<Integer, Fermata> getOrariPassaggioAlleFermate()
	{
		return orarioPassaggioAlleFermate;
	}

	public Entry<Integer, Fermata> getCapolineaFinale()
	{
		Set <Map.Entry<Integer, Fermata>> insiemeElementi = this.getOrariPassaggioAlleFermate().entrySet();
		int maxKey = 0;
		for (Map.Entry<Integer, Fermata> elemento : insiemeElementi)
			if (maxKey < elemento.getKey().intValue())
				maxKey = elemento.getKey().intValue();
		for (Map.Entry<Integer, Fermata> elemento : insiemeElementi)
			if (elemento.getKey().equals(maxKey))
				return elemento;
		throw new IllegalArgumentException("Non c'è alcun capolinea finale.");
	}
	
	public Entry<Integer, Fermata> getCapolineaIniziale()
	{
		Set <Map.Entry<Integer, Fermata>> insiemeElementi = this.getOrariPassaggioAlleFermate().entrySet();
		for (Map.Entry<Integer, Fermata> elemento : insiemeElementi)
			if (elemento.getKey().equals(0))
				return elemento;
		throw new IllegalArgumentException("Non c'è alcun alcun capolinea iniziale.");
	}
	
	public boolean isNomeFermataInMap(String nomeFermata)
	{
		Set <Map.Entry<Integer, Fermata>> insiemeElementi = this.getOrariPassaggioAlleFermate().entrySet();
		for (Map.Entry<Integer, Fermata> elemento : insiemeElementi)
			if (elemento.getValue().getNome().equals(nomeFermata))
				return true;
		return false;
	}
	
	public int getOrarioPassaggioAllaFermata(String nomeFermata)
	{
		if (this.isNomeFermataInMap(nomeFermata))
		{
			Set <Map.Entry<Integer, Fermata>> insiemeElementi = this.getOrariPassaggioAlleFermate().entrySet();
			for (Map.Entry<Integer, Fermata> elemento : insiemeElementi)
				if (elemento.getValue().getNome().equals(nomeFermata))
					return elemento.getKey().intValue();
		}
		throw new IllegalArgumentException("La linea non passa per tale fermata.");
	}
	
	abstract public Optional<Percorso> getPercorso(String fermataIniziale, String fermataFinale);
	
	public boolean isCapolineaFinale(String nomeFermata)
	{
		return this.getCapolineaFinale().getValue().getNome().equals(nomeFermata);
	}
	
	public boolean isCapolineaIniziale(String nomeFermata)
	{
		return this.getCapolineaIniziale().getValue().getNome().equals(nomeFermata);
	}
	
	public boolean isCircolare()
	{
		return this.getCapolineaFinale().getValue().equals(this.getCapolineaIniziale().getValue());
	}
	
	public String toString()
	{
		return "ID Linea: " + this.getId() + ";\n" + this.getOrariPassaggioAlleFermate() + "\n";
	}
	
	public Linea(String id, Map<Integer, Fermata> orarioPassaggioAlleFermate)
	{
		if (id == null || id == "" || orarioPassaggioAlleFermate == null)
			throw new IllegalArgumentException("Valori invalidi di inizializzazione della linea.");
		this.id = id;
		this.orarioPassaggioAlleFermate = orarioPassaggioAlleFermate;
	}
}
