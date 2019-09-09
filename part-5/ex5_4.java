import java.security.InvalidParameterException;

/*
 * A monochrome screen is stored as a single array of bytes,
 * allowing 8 consecutive pixels to be stored in one byte.
 * The screen width (number of bytes on line * 8) is divisible by 8
 * (that is, no byte will be split across rows)
 * Following function draws a horizontal line from (x1,y) to (x2,y)
 */

public class ex5_4 {

	public static void testDrawLine() {
		byte[] screen=new byte[8*8];
		int x1=34;
		int x2=60;
		int y=5;
		
		drawLine(screen,64,x1,x2,y);	
		printScreen(screen,64);
	}
	
	private static void printScreen(byte[] screen, int width) {
		for(int i=0;i<screen.length;i++){
			if(i%(width/8)==0)
				System.out.println();
			if(screen[i]!=0)
				System.out.print(String.format("%8s", Integer.toBinaryString(screen[i] & 0xFF).replace(' ', '0'))+" ");
			else System.out.print("00000000 ");
		}
	}

	private static void drawLine(byte[] screen, int width, int x1, int x2, int y) {  // method signature given
		// let's validate 
		if (x1>x2 || x2-x1>width-1) //not enough => make sure they are on the same line !!!!
			throw new InvalidParameterException("One of the conditions not satisfied: x1<=x2, x2-x1<=31");
		
		if(x1==x2){
			screen[x1]=1;
			return;
		}
		
		/* Think of x1 and x2 and y as coordinates, so x1==column, not array index*/
		int first_full_byte=x1/8;
		int last_full_byte=x2/8;
		
		int start_offset=x1%8;
		int end_offset=x2%8;	
			
		if(start_offset!=0)
			first_full_byte++;
		if(end_offset!=7)
			last_full_byte--;

		int prevLinesBytes=width/8*y;

		for(int i=prevLinesBytes+first_full_byte;i<=prevLinesBytes+last_full_byte;i++){
			System.out.println("setting byte "+i+" to all ones");
			screen[i]=(byte)0xFF;
		}
		
		byte start_mask=(byte)((0xFF)>>start_offset);
		byte end_mask=(byte)~((0xFF)>>(end_offset+1));
		
		if(x1/8==x2/8) // x1 and x2 in the same byte
			screen[prevLinesBytes+first_full_byte]=(byte) (start_mask&end_mask);
		else{
			if(start_offset!=0)
				screen[prevLinesBytes+first_full_byte-1]|=start_mask;
			
			if(end_offset!=0)
				screen[prevLinesBytes+last_full_byte+1]|=end_mask;
		}		
	}


}
