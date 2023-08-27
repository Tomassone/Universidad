package ghigliottina.ui;

import java.util.List;

import ghigliottina.model.Ghigliottina;

public class MyController implements Controller
{
	private List<Ghigliottina> ghigliottine;
	
	public MyController(List<Ghigliottina> ghigliottine)
	{
		this.ghigliottine = ghigliottine;
	}

	@Override
	public Ghigliottina sorteggiaGhigliottina()
	{
		int numeroRandomico = (int) (Math.random() * listaGhigliottine().size());
		return this.listaGhigliottine().get(numeroRandomico);
	}

	@Override
	public List<Ghigliottina> listaGhigliottine()
	{
		return this.ghigliottine;
	}

}
