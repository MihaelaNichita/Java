package points;

public class Point 
{
	public double x;
	double y;
	
	public Point(double x,double y)
	{
		this.x=x;
		this.y=y;
	}

	public void setCoordinates(double x1, double y1) 
	{
		x=x1;
		y=y1;		
	}
	
	public String toString()
	{
		return "("+x+","+y+")";
	}
	
}
