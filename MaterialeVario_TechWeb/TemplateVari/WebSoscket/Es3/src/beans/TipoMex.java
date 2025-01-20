package beans;

public class TipoMex {
	public String mex;
	public int tipo;
	
	public TipoMex() {
		this.mex = "";
		this.tipo = 0;
	}
	
	public String getMex() {
		return mex;
	}
	public void setMex(String mex) {
		this.mex = mex;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
}
