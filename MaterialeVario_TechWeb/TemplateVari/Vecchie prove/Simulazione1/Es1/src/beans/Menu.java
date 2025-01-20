package beans;

import java.util.ArrayList;
import java.util.List;

public class Menu {
	private List<Drink> elementi;
	
	public Menu() {
		this.elementi = new ArrayList<Drink>();
	}

	public List<Drink> getElementi() {
		return elementi;
	}

	public void setElementi(List<Drink> elementi) {
		this.elementi = elementi;
	}
	
	public Drink getById(String id)
	{
		boolean trovato = false;
		for (int i = 0; i < this.elementi.size() && trovato == false; i++)
		{
			if (this.elementi.get(i).getIdDrink().equals(id))
			{
				trovato = true;
				return this.elementi.get(i);
			}
		}
		return null;
	}
}
