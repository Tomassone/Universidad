package matrici;

public class Matrix
{
	private double[][] internalConteiner;
	
	public Matrix(double[][] receivedArray)
	{
		internalConteiner = new double[receivedArray.length][receivedArray[0].length];
		for (int i = 0; i < receivedArray.length; i++)
			for (int j = 0; j < receivedArray[0].length; j++)
				internalConteiner[i][j] = receivedArray[i][j];
	}
	
	private Matrix(int rows, int cols)
	{
		internalConteiner = new double[rows][cols];
	}
	
	public double det()
	{
		if (this.isSquared()) //se la matrice è quadrata.
			return this.calcDet();
		else
			return Double.NaN; //valore non numerico (corrispettivo primitivo del null).
	}
	
	public Matrix extractMinor(int row, int col)
	{
		Matrix result = new Matrix(this.getRows() - 1, this.getCols() - 1);
		if (row > this.getRows() || col > this.getCols() || !this.isSquared())
			return null;
		for (int i = 0; i < row; i++)
			for (int j = 0; j < col; j++)
					result.setValue(i, j, this.getValue(i, j));
		for (int i = row + 1; i < this.getRows(); i++)
			for (int j = col + 1; j < this.getCols(); j++)
					result.setValue(i - 1, j - 1, this.getValue(i, j));
		return result;
	}
	
	public Matrix extractSubMatrix(int startRow, int startCol, int rowCount, int colCount)
	{
		Matrix result = new Matrix(rowCount, colCount);
		if (startRow > this.getRows() || startCol > this.getCols() || (startRow + rowCount) > this.getRows() || (startCol + colCount) > this.getCols())
			return null;
		for (int i = startRow; i < startRow + rowCount; i++)
			for (int j = startCol; j < startCol + colCount; j++)
				result.setValue(i - startRow, j - startCol, this.getValue(i, j));
		return result;
	}
	
	private double calcDet()
	{
		int k = 0; //scelgo come collonna fissa la prima.
		double determinant = 0;
		if (this.getRows() == 1 && this.getCols() == 1)
			return this.internalConteiner[0][0];
		for (int i = 0; i < this.getRows(); i++)
			determinant = determinant +  Math.pow((-1), i + k) * this.getValue(i, k) * this.extractMinor(i, k).det();
		return determinant;
	}
	
	public int getRows()
	{
		return this.internalConteiner.length;
	}
	
	public int getCols()
	{
		return this.internalConteiner[0].length;
	}
	
	public double getValue(int row, int col)
	{
		if (row < this.getRows() && col < this.getCols())
			return this.internalConteiner[row][col];
		else
			return Double.NaN;
	}
	
	public boolean isSquared()
	{
		return this.getRows() == this.getCols();
	}
	
	public Matrix sum(Matrix that)
	{
		double[][] c = new double[this.getRows()][this.getCols()];
		if (this.getRows() != that.getRows() || this.getCols() != that.getCols())
			return null;
		for (int i = 0; i < this.getRows(); i++)
			for (int j = 0; j < this.getCols(); j++)
					c[i][j] = this.getValue(i, j) + that.getValue(i, j);
		return new Matrix(c);
	}
	
	public Matrix mul(Matrix that)
	{
		double[][] c = new double[this.getRows()][that.getCols()];
		double temp = 0;
		if (this.getCols() != that.getRows())
			return null;
		for (int i = 0; i < this.getRows(); i++) //esegue il prodotto riga-colonna completo.
		{
			for (int k = 0; k < that.getCols(); k++) //esegue il prodotto fra una riga e n colonne.
			{
				for (int j = 0; j < this.getCols(); j++) //esegue il prodotto fra una singola riga e una singola colonna.
					temp = temp + this.getValue(i, j) * that.getValue(j, k);
				c[i][k] = temp;
				temp = 0;
			}
		}
		return new Matrix(c);
	}
		
	private void setValue(int row, int col, double value)
	{
		this.internalConteiner[row][col] = value;
	}
	
	public String toString()
	{
		String result = "";
		for (int i = 0; i < this.getRows(); i++)
		{
			for (int j = 0; j < this.getCols(); j++)
				result = result + this.getValue(i, j) + " ";
			result = result + "\n";
		}
		return result;
	}
}
