public class Coordinates implements Cloneable {
	
	public int x;
	public int y;
	
	public Coordinates(int x, int y) {
		this.x = x; this.y = y;
	}
	
	public boolean equals(Object o) {
		Coordinates c = (Coordinates)o;
		return c.x == x && c.y == y;
	}
	
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) { return null; }
	}
	
	public String toString() {
		return "(" + x + ", " + y + ")";
	}

}