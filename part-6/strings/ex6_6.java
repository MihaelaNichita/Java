package strings;

import java.util.HashMap;

//WordFrequencies
public class ex6_6 
{

	public static void main(String[] args) 
	{
		String[] book= {"are","we","going","to","create","something","inovative","today,","tommorrow,","always?",
				"Let's","start","right","now","today!","the","moment","is ", "now!!!"};
		String[] words= {"now","cool","?","to"};
		
		System.out.println("Testing getFrequency without storing frequencies in a hash table:");
		for(int i=0;i<words.length;i++)
		{
			int f=getFrequency(book,words[i]);
			System.out.println("Frequency of word="+words[i]+" is: "+ f);
		}
		System.out.println();
		
		System.out.println("Testing getFrequency with frequencies stored in a hash table:");
		HashMap<String,Integer> map=setupDictionary(book);
		for(int i=0;i<words.length;i++)
		{
			int f=getFrequency(map,words[i]);
			System.out.println("Frequency of word="+words[i]+" is: "+ f);
		}
		
		
		
	}
		
	/* The method will find the frequency of occurrences of any given word in a book.*/
	private static int getFrequency(String[] book, String word) 
	{
		if(book==null || word==null || word.trim()=="")
			return -1;
		
		word=word.replaceAll("[^a-zA-Z0-9]", "").trim().toLowerCase();

		
		int f=0;
		for(String str:book)
		{
			if(str.trim().toLowerCase().replaceAll("[^a-zA-Z0-9]", "").equals(word))
				f++;
		}
		return f;		
	}
	
	/* If we were running this algorithm multiple times, then second approach: (Takes extra memory) */
	 private static HashMap<String,Integer> setupDictionary(String[] book)
	 {
		 if(book==null) return null;
		 
		 HashMap<String,Integer> map=new  HashMap<String,Integer>();
		 for(String word:book)
		 {
			 word=word.replaceAll("[^a-zA-z0-9]","").trim().toLowerCase();
			 if(word!="")
			 {
				 if(!map.containsKey(word))
					map.put(word,0);					 		
				 map.put(word, map.get(word)+1);
			 }
		 }
		 return map;
	 }
	 
	 private static int getFrequency(HashMap<String,Integer> map, String word)
	 {
		 if (map==null || word == null)
			 return -1;
		 
		 word=word.replaceAll("[^a-zA-Z0-9]", "").trim().toLowerCase();
		 if(map.containsKey(word))
			 return map.get(word);
		 
		 return 0;
	 }

	

}
