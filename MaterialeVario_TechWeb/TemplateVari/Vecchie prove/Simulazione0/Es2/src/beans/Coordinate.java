package beans;

public class Coordinate {
	private int x;
	private int y;
	
	public Coordinate()
	{
		this.x = 0;
		this.y = 0;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public boolean equals(Coordinate coord)
	{
		return (this.x == coord.getX()) && (this.y == coord.getY());
	}
	
	public boolean isNear(Coordinate coord, int parameter)
	{
		int distance = (int) Math.sqrt((this.x - coord.getX())*(this.x - coord.getX()) + (this.y - coord.getY())*(this.y - coord.getY()));
		return (distance < parameter);
	}
}
