package points;

// Intersection
public class ex6_4 
{

	public static void main(String[] args) 
	{
		Point start1=new Point(3,30);
		Point end1=new Point(3,6); // slope==1/2
		Point start2=new Point(0,7); //slope==infinity
		Point end2=new Point(10,7);
		
		System.out.println("Segment1: "+start1.toString()+"-"+end1.toString());
		System.out.println("Segment2: "+start2.toString()+"-"+end2.toString());
		
		Point p=getIntersection(start1,end1,start2,end2);

		if(p!=null)
			System.out.println("Intersection point: "+p.toString());
		else
			System.out.println("Segments don't intersect.");
		
		// slope==infinity case
	}
	
	/* Given 2 straight line segments (represented as a start point and an end point,
	 * compute the point of intersection. */	
	public static Point getIntersection(Point start1, Point end1, Point start2, Point end2)
	{
		/* Rearrange points so that, in order of x values:
		 * start is before end and point1 is before point2 */
		if(start1.x>end1.x) swap(start1,end1);
		if(start2.x>end2.x) swap(start2,end2);
		if(start1.x>start2.x) 
		{
			/* Swap both start points and end points (like swapping lines). */
			swap(start1,start2);
			swap(end1,end2);
		}
		
		/* Create lines. */
		Line line1 = new Line(start1,end1);
		Line line2 = new Line(start2,end2);
		
		/* If lines are parallel, they intercept only if they have the same Oyintercept
		 * and start2 is on line1 (Some portion of their segments overlap). */
		if(line1.slope==line2.slope || (isInfinity(line1.slope) && isInfinity(line2.slope)))
		{		
			if(isBetween(start1,start2,end1))
				if(line1.slope==Double.POSITIVE_INFINITY || line1.Oyintercept==line2.Oyintercept) //+ infinity case
					return start2;
			return null;
		}
		
		/* Compute intersection point for lines. */
		double x=0,y=0;
		Point intersection=null;
		if(!isInfinity(line1.slope) && !isInfinity(line2.slope))
		{
			x=(line2.Oyintercept-line1.Oyintercept)/(line1.slope-line2.slope);
			y=x*line1.slope+line1.Oyintercept;
			intersection=new Point(x,y);
		}
		else intersection=intersectionForVerticalLine(start1,start2,line1,line2);
		
		/* Check if within line segment range. */
		if(isBetween(start1,intersection,end1) && isBetween(start2,intersection,end2))
			return intersection;
		
		return null;
	}

	private static boolean isInfinity(double val) 
	{
		return val==Double.POSITIVE_INFINITY || val==Double.NEGATIVE_INFINITY;
	}


	private static Point intersectionForVerticalLine(Point start1, Point start2, Line line1, Line line2) 
	{
		Point p=null;
		double x=start2.x;
		double y=line1.getYforX(x,start1);
		
		if(!isInfinity(line2.slope) )
		{
			/* If One of the segments is vertical(x=const), then they intersect in y=f(x=const), f(x) being the equation of the other line. */
			x=start1.x;
			y=line2.getYforX(x,start2);
			System.out.println("Got where we shouldnt have");
		}	
		
		p=new Point(x,y);
		return p;
	}

	private static boolean isBetween(Point start, Point p, Point end) 
	{
		// return start.x<=p.x && start.y<=p.y && end.x>=p.x && end.y>=p.y; - NOT ENOUGH
		/* Don't assume start has smaller coordinates than end! */
		return isBetween(start.x,p.x,end.x) && isBetween(start.y,p.y,end.y);
	}

	private static boolean isBetween(double start, double middle, double end) 
	{
		if(start<end)
			return start<=middle && middle<=end;
		else
			return end<=middle && middle<=start;
	}

	private static void swap(Point p1, Point p2) 
	{
		double x=p1.x;
		double y=p1.y;
		p1.setCoordinates(p2.x,p2.y);
		p2.setCoordinates(x,y);		
	}
	

}
