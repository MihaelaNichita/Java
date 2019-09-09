package points;

public class Line 
{
	double slope, Oyintercept;

	public Line(Point p1,Point p2) 
	{
		double deltaY=p2.y-p1.y;
		double deltaX=p2.x-p1.x;
		slope=deltaY/deltaX; // deltaX==0 won't give exception, but infinity
		Oyintercept=p2.y-slope*p2.x;
	}
	
	public double getYforX(double x,Point p)
	{
		/* Point-Slope equation: y=m(x-Px)+Py */
		return slope*(x-p.x)+p.y;
	}

}
