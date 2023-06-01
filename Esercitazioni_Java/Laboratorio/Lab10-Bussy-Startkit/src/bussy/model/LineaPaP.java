package bussy.model;

import java.util.Map;
import java.util.Optional;

public class LineaPaP extends Linea
{
	public LineaPaP(String id, Map<Integer, Fermata> orarioPassaggioAlleFermate)
	{
		super(id, orarioPassaggioAlleFermate);
		if (this.isCircolare())
			throw new IllegalArgumentException("Linea circolare invece che punto a punto.");
	}
	
	@Override
	public Optional<Percorso> getPercorso(String fermataIniziale, String fermataFinale)
	{
		Optional<Percorso> result = Optional.empty();
		if (!this.isNomeFermataInMap(fermataIniziale) || !this.isNomeFermataInMap(fermataFinale))
			return result;
		else
		{
			Percorso interno = new Percorso(fermataIniziale, fermataFinale, this, this.getOrarioPassaggioAllaFermata(fermataFinale) - this.getOrarioPassaggioAllaFermata(fermataIniziale));
			return Optional.of(interno);
		}
	}
}
