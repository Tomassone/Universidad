package bussy.model;

import java.util.Map;
import java.util.OptionalInt;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class MyCercatore implements Cercatore 
{
	private Map<String, Linea> mappaLinee;
	
	public SortedSet<Percorso> cercaPercorsi(String fermataDa, String fermataA, OptionalInt durataMax)
	{
		if (fermataDa == null || fermataA == null || fermataDa == "" || fermataA == "")
			throw new IllegalArgumentException("Valori di inizializzazione non validi.");
		
		Set<Map.Entry<String, Linea>> insiemeElementi = this.getMappaLinee().entrySet();
		SortedSet<Percorso> result = new TreeSet<Percorso>();
		
		if (durataMax.isPresent())
			for (Map.Entry<String, Linea> elemento : insiemeElementi)
			{
				if (elemento.getValue().getPercorso(fermataDa, fermataA).isPresent() && elemento.getValue().getPercorso(fermataDa, fermataA).get().getDurata() <= durataMax.getAsInt() && elemento.getValue().getPercorso(fermataDa, fermataA).get().getDurata() > 0)
					result.add(elemento.getValue().getPercorso(fermataDa, fermataA).get());
			}
		else
			for (Map.Entry<String, Linea> elemento : insiemeElementi)
			{
				if (elemento.getValue().getPercorso(fermataDa, fermataA).isPresent() && elemento.getValue().getPercorso(fermataDa, fermataA).get().getDurata() > 0)
					result.add(elemento.getValue().getPercorso(fermataDa, fermataA).get());
			}
		
		return result;
	}
	
	public Map<String, Linea> getMappaLinee()
	{
		return this.mappaLinee;
	}
	
	public MyCercatore(Map<String, Linea> mappaLinee)
	{
		if (mappaLinee == null)
			throw new IllegalArgumentException("Argomenti di inizializzazione invalidi.");
		this.mappaLinee = mappaLinee;
	}
}
