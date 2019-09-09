import java.lang.Math;

import javafx.util.converter.NumberStringConverter; 


public class Test
{

	public static void main(String[] args)
   {
		String str="AbcdDcba , ,";
		/*
		System.out.println(isUniqueChar1(str));
		System.out.println(isUniqueChar2(str));
		System.out.println(isUniqueChar3(str));
		*/
		
		String str1="abcdk ";
		String str2="abc k ";
		/*
		System.out.println(checkPermutation1(str1,str2));
		System.out.println(checkPermutation2(str1,str2));
		*/
		
		char str3[]= {'a','b',' ','j',' ', '6',' ','*','*','*','*','*','*'};		
		//replaceSpaces(str3,7);
		//System.out.println(str3);
		
		/*
		System.out.println(checkPalindromePermutation1(str));
		System.out.println(checkPalindromePermutation2(str));
		System.out.println(checkPalindromePermutation3(str));
		*/
		
		//System.out.println(areOneEditAway(str1,str2));

		/*
		String stringToCompress="aaaaaan9984444y";
		System.out.println(compress1(stringToCompress));
		System.out.println(compress2(stringToCompress));
		*/
		
		/*
		int[][] matrix= {{1,1,1,1},{2,2,2,2},{3,3,3,3},{4,4,4,4}};
		printMatrix(matrix);
		System.out.println();
		printMatrix(rotate(matrix));
		*/
		
		int[][] matrix= {{1,1,0,1},{2,2,2,2},{3,3,3,3},{4,4,4,4},{9,9,9,9}};
		setZeros(matrix);
		printMatrix(matrix);
		
   }
	
	// For when the string only uses the lowercase letters a through z
	static boolean isUniqueChar1(String str)
	{
		int checker=0;
		for (int i =0;i<str.length();i++)
		{
			int val=str.charAt(i)-'a';

			if ((checker & (1<<val))>0)
				return false;
			checker|=(1<<val);
		}
		return true;
	}
	
	// using additional data structure, but without changing the input string
	static boolean isUniqueChar2(String str)
	{
		if(str.length()>128)
			return false;
		
		boolean[] char_set=new boolean[128];
		for(int i=0;i<str.length();i++)
		{
			int val=str.charAt(i);
			
			//already found this char in str
			if(char_set[val])
				return false;
			char_set[val]=true;
		}
		return true;
	}
	
	// when allowed to change input string, with no additional ata structure
	static boolean isUniqueChar3(String str)
	{
		if(str.length()>128)
			return false;
		
		char[] str_chars=str.toCharArray();
		java.util.Arrays.sort(str_chars);
		 for(int i=0;i<str.length()-1;i++)
		 {
			 char c1=str.charAt(i);
			 char c2=str.charAt(i+1);
			 if(c1==c2)
				 return false;	
		 }
		 return true;		
	}
	
	// sort a string
	static String sort(String str)
	{
		char[] str_chars=str.toCharArray();
		java.util.Arrays.sort(str_chars);
		
		return new String(str_chars);
	}
	
	// Allowed to change the input strings
	static boolean checkPermutation1(String str1, String str2)
	{
		if(str1.length()!=str2.length())
			return false;
	
		 return sort(str1).equals(sort(str2));		
	}
	
	
	// Without changing input, with an additional data structure : an array of ints which keeps the characters counts
	static boolean checkPermutation2(String str1, String str2)
	{
		if(str1.length()!=str2.length())
			return false;
		
		int[] chars_counts=new int[128]; //assumption
		
		for(int i=0;i<str1.length();i++)
		{
			int c=str1.charAt(i);
			chars_counts[c]++;
		}
		
		for(int i=0;i<str2.length();i++)
		{
			int c=str2.charAt(i);
			if (chars_counts[c]==0)
				return false;
			chars_counts[c]--;
		}
		
		//we don't need the following because if there is a char that has an occurrence in str1, and none in str2
		// we will definitely have one more occurrence in str2 that is not in str1, or the lengths would be not equal
		/*
		for(int i:chars_counts)
		{
			if (i!=0)
				return false;
		}
		*/
		return true;
	}
	
	// IN-Place, trueLength=length of the initial string=nr of chars 
	static void replaceSpaces(char[] str, int trueLength)
	{
		int spaceCount=0;
		for(char c:str)
		{
			if(c==' ')
				spaceCount++;
		}
		
		int index=trueLength+spaceCount*2-1;
		
		// Not sure why would need the following, 
		//if(trueLength<str.length)
		//	str[trueLength]='\0';
		
		for(int i=trueLength-1;i>=0;i--)
		{
			if(str[i]==' ')
			{
				str[index-2]='%';
				str[index-1]='2';
				str[index]='0';
				index-=3;
			}
			else
			{
				str[index]=str[i];
				index--;
			}
		}
	}
	
	//assume it's case insensitive => Mom is not a palindrome
	//used an array
	static boolean checkOddCount(boolean[] char_set, boolean evenLength)
	{
		// better without evenLength - worst case - no oddCount => run extra lines for each element in char_set
		boolean oneOddCount=false;
		for(boolean c:char_set)
		{
			if(c)
			{
				if(evenLength)
					return false;
				else
					if (oneOddCount)
						return false;
					else
						oneOddCount=true;
			}
		}		
		return true;
	}
	
	static boolean checkPalindromePermutation1(String str)
	{	
		boolean[] char_set=new boolean[128];
		for(int i=0;i<str.length();i++)
		{
			int val=str.charAt(i);
			char_set[val]=!char_set[val];
		}
		
		return checkOddCount(char_set,str.length()%2==0);
	}
	
	
	//what if it is case sensitive?  M==m
	static boolean checkPalindromePermutation2(String str)
	{	
		boolean[] char_set=new boolean[128];
		for(int i=0;i<str.length();i++)
		{
			int val=str.charAt(i);
			if(val>='A' && val<='Z')
			{
				val=val+'a'-'A';
			}
			char_set[val]=!char_set[val];	
		}
		
		return checkOddCount(char_set,str.length()%2==0);
	}
	
	
	// As a PALINDROME refers to a word or phrase, we will consider just English letters, ignore any other character
	// Bit Vector - use just an INTEGER - 32b - 26 letters
	
	static boolean checkPalindromePermutation3(String str)
	{	
		int bitVector=createBitVector(str);
		return (bitVector==0 || ((bitVector&(bitVector-1))==0)); // check if there's at most 1 odd count		
	}
	
	//case sensitive
	// Map each character to a number
	// Non-letters chars map to -1
	static int getCharNumber(char c)
	{
		int a=Character.getNumericValue('a');
		int z=Character.getNumericValue('z');
		int A=Character.getNumericValue('A');
		int Z=Character.getNumericValue('Z');
		int val=Character.getNumericValue(c);
		
		if(a<=val && val<=z)
			return val-a;
		if(A<=val && val<=Z)
			return val+32-a;
		return -1;
	}
	
	static int createBitVector(String str)
	{
		int bitVector=0;
		for(int i=0;i<str.length();i++)
		{
			int bitIndex=getCharNumber(str.charAt(i));
			if(bitIndex!=-1) 
			{
				bitVector=toggle(bitVector, bitIndex);
			}
		}
		return bitVector;
	}
	
	static int toggle (int bitVector, int bitIndex)
	{
		int mask=(1<<bitIndex);
		if ((mask & bitVector)==0)
		{
			//set bit
			bitVector|=mask;				
		}
		else
		{
			//reset bit
			bitVector&=(~mask);
		}
		return bitVector;
	}
	
	/*
	 * There are 3 types of edits that can be performed on strings: insert a character, remove a character, or replace
	 * a character. Given two strings, write a function to check if they are one (or zero) edit away.
	 */
	
	static boolean areOneEditAway(String str1, String str2)
	{
		if (str1.equals(str2))
			return true;
		if (Math.abs(str1.length()-str2.length())>1)
			return false;

		/*
		 *  It's important to have the equal sign here, in case lengths are equal
		 *  without the equal sign at one of them, we get false for both, thus str2 for both
		 */
		
		String shorter=str1.length()<=str2.length()?str1:str2;
		String longer=str1.length()>str2.length()?str1:str2;

		int index1=0;
		int index2=0;
		
		boolean foundDifference=false;
		
		while(index1<shorter.length() && index2<longer.length())
		{
			if(shorter.charAt(index1)!=longer.charAt(index2))
			{
				System.out.println("found difference\n");
				if(foundDifference)
					return false;
				foundDifference=true;
				
				if(shorter.length()==longer.length())
				{
					index1++;
				}
				
			}
			else index1++;
			index2++;
		}		
		return true;
	}
	
	
	/*
	 * Implement a method to perform basic string compression using the counts of repeated characters.
	 * If the compressed string would not become smaller than the original string, your method should return the original string
	 * For example, the string aabcccccaaa would become a2b1c5a3.
	 */
	
	
	static String compress1(String str)
	{
		StringBuilder chars_set=new StringBuilder();
		int count=0;
		
		for(int i=0;i<str.length();i++)
		{
			count++;
			if(i==str.length()-1 || str.charAt(i)!=str.charAt(i+1))
			{
				chars_set.append(str.charAt(i));
				chars_set.append(count);
				count=0;
			}
		}
		
		return str.length()<chars_set.length()?str:chars_set.toString();
	}
	
	/*
	 * In cases where we don't have a large number of repeating characters, the following will be more optimal
	 * It will avoid us to create a string that we never use.
	 * DOWNSIDE : it causes a second loop through the characters and also adds nearly duplicated code
	 * BENEFIT : we can initialize StringBuilder to its necessary capacity up-front
	 * without this, StringBuilder will (behind the scenes) need to double its capacity every time it hits capacity
	 * the capacity could be double what we ultimately need
	 */

	static String compress2(String str)
	{
		int finalLength=countCompression(str);
		if (finalLength>=str.length())
			return str;
		
		//same
		StringBuilder compressed=new StringBuilder();
		int count=0;
		
		for(int i=0;i<str.length();i++)
		{
			count++;
			if(i==str.length()-1 || str.charAt(i)!=str.charAt(i+1))
			{
				compressed.append(str.charAt(i));
				compressed.append(count);
				count=0;
			}
		}
		
		return compressed.toString();
	}
	
	static int countCompression(String str)
	{
		int compressedLength=0;
		int count=0;
		
		for(int i=0;i<str.length();i++)
		{
			count++;
			if(i>=str.length()-1 || str.charAt(i)!=str.charAt(i+1))
			{
				compressedLength+=1+String.valueOf(count).length();
				count=0;				
			}
		}		
		return compressedLength;
	}
	
	static int[][] rotate(int matrix[][])
	{
		int n=matrix.length;
		if (n!=matrix[0].length)
			return null;
				
		for(int layer=0;layer<n/2;layer++)
		{
			int first=layer;
			int last=n-1-layer;
			
			for(int i=first;i<last;i++)
			{

				int offset=i-first;
				int topLeft=matrix[first][i]; //2
				matrix[first][i]=matrix[last-offset][first]; //
				matrix[last-offset][first]=matrix[last][last-offset];
				matrix[last][last-offset]=matrix[i][last];
				matrix[i][last]=topLeft;				
			}
		}	
		return matrix;
	}
	
	static void printMatrix(int [][] m)
	{
		for(int i=0;i<m.length;i++)
		{	
			for(int j=0;j<m[0].length;j++)
				System.out.print(m[i][j]+" ");
			System.out.println();
		}
	}

	
	/*
	 * Write an algorithm such that if an element in a MxN matrix is zero, its entire row and column are set to zero.
	 */
	
	static void setZeros(int matrix[][])
	{
		boolean[] rows=new boolean[matrix.length];
		boolean[] columns=new boolean[matrix[0].length];
		
		for(int i=0;i<matrix.length;i++)
			for(int j=0;j<matrix[0].length;j++)
			{
				if(matrix[i][j]==0)
				{
					rows[i]=true;
					columns[j]=true;
				}
			}
		for(int i=0;i<matrix.length;i++)
		{
			if(rows[i])
				nullifyRow(matrix,i);
		}
		for(int i=0;i<matrix[0].length;i++)
		{
			if(columns[i])
				nullifyColumn(matrix,i);
		}	
	}
	
	static void nullifyRow(int[][] matrix,int row)
	{
		for(int i=0;i<matrix[0].length;i++)
		{
			matrix[row][i]=0;
		}
	}
	static void nullifyColumn(int[][] matrix, int column)
	{
		for(int i=0;i<matrix.length;i++)
		{
			matrix[i][column]=0;
		}
	}
	
	
	/*
	 * Assume you have a method isSubstring which checks if one word is a substring of another.
	 * Given 2 strings write code to check if s2 is a rotation of s1, using only one call to isSubstring
	 * e.g. waterbottle is a rotation of erbottlewat
	 */
	
	static boolean isSubstring(String s1,String s2)
	{
		
		return true;
	}
	
	static boolean isRotation(String s1,String s2)
	{
		if(s1==null ||s2==null)
			return false;
		if (s1.equals(s2))
				return true;
		if (s1.length()!=s2.length())
			return false;
		
		/*
		 * s1=xy 
		 * s2=yx
		 * s1s1=xyxy => yx isSubstring of xyxy
		 * So we concatenate s1 and s1 in new buffer
		 */
		
		String s1s1=s1+s1;
		
		return isSubstring(s1s1,s2);
	}
		
}