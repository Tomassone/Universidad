package bussy.model;

import java.util.Map;
import java.util.Optional;

public class LineaCircolare extends Linea
{
	public LineaCircolare(String id, Map<Integer, Fermata> orarioPassaggioAlleFermate)
	{
		super(id, orarioPassaggioAlleFermate);
		if (!this.isCircolare())
			throw new IllegalArgumentException("Linea punto a punto invece che circolare.");
	}
	
	@Override
	public Optional<Percorso> getPercorso(String fermataIniziale, String fermataFinale)
	{
		Optional<Percorso> result = Optional.empty();
		if (!this.isNomeFermataInMap(fermataIniziale) || !this.isNomeFermataInMap(fermataFinale))
			return result;
		else
		{
			Percorso interno;
			if (this.getOrarioPassaggioAllaFermata(fermataFinale) > this.getOrarioPassaggioAllaFermata(fermataIniziale))
				interno = new Percorso(fermataIniziale, fermataFinale, this, this.getOrarioPassaggioAllaFermata(fermataFinale) - this.getOrarioPassaggioAllaFermata(fermataIniziale));
			else if (this.getOrarioPassaggioAllaFermata(fermataFinale) == this.getOrarioPassaggioAllaFermata(fermataIniziale))
				interno = new Percorso(fermataIniziale, fermataFinale, this, this.getCapolineaFinale().getKey());
			else
				interno = new Percorso(fermataIniziale, fermataFinale, this, this.getCapolineaFinale().getKey() - (this.getOrarioPassaggioAllaFermata(fermataIniziale) - this.getOrarioPassaggioAllaFermata(fermataFinale)));
			return Optional.of(interno);
		}
	}
}
