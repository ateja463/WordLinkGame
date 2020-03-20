import java.util.*;
import java.io.*;
public class WordLink {

	public static void main(String[] args) throws Exception
	{
		// TODO Auto-generated method stub
		//System.out.println("creating dictionary object");
        Dictionary d = new Dictionary();
        //change trhis path name accordingly
        //File file = new File("C:\\Users\\ateja\\eclipse-workspace\\dictionary.txt");
		File file = new File("dictionary.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String str;
        int lvl = 1;
		while((str = br.readLine() ) != null)
		{
			System.out.println(str);
			if(str.equals("1"))
			{
				System.out.println(lvl);
				lvl = 1;
			}
			else if (str.equals("2") )
			{
				lvl = 2;
				System.out.println(lvl);
				
			}
			else
			{
				StringTokenizer st = new StringTokenizer(str , " ");
				while(st.hasMoreTokens())
				{
					d.insertWord(st.nextToken(), lvl , true);
				}
			}
		}
		br.close();
        displayMenu();
        Scanner scanner = new Scanner(System.in);
        while(true)
        {
        	String input = scanner.nextLine();
        	if ("A".equals(input) || "a".equals(input) ) {
        		System.out.println("A set the difficulty level ");
        		d.setLevel();
        	}
        	else if ("B".equals(input) || "b".equals(input) ) {
        		System.out.println("B display the dictionary ");
        		d.displayDictionary();
        	}
        	else if ("C".equals(input) || "c".equals(input)) {
        		System.out.println("C insert a word to the dictionary ");
        		System.out.println("enter word");
        		String word = scanner.nextLine();
        		System.out.println("enter level");
        		int level = scanner.nextInt();
        		d.insertWord(word , level , false);
				
        	}
        	else if ("D".equals(input) || "d".equals(input)) {
        		System.out.println("D play the game (1 player)");
        		d.playGame1Player();
        	}
        	else if ("E".equals(input) || "e".equals(input) ) {
                System.out.println("Updating Dictionary Byee....!");
                break;
            }
        	else if("F".equals(input) || "f".equals(input) ){
        		System.out.println("D play the game (2 players)");
        		d.playGame2Players();
        	}
        	else 
        	{
        		//System.out.println("");
        		//displayMenu();
        	}
        	
        }
        scanner.close();
        //d.printDictionary();
        
	}
	static void displayMenu()
	{
		System.out.println("WordLink");
		//System.out.println("");
		System.out.println("   A: set the difficulty level");
		System.out.println("   B: display the dictionary");
		System.out.println("   C: insert a word to the dictionary");
		System.out.println("   D: play the game(1 player)");
		System.out.println("   E: exit");
		System.out.println("   F: play the game(2 players)");
		System.out.print("select a function from menu:");
		
	}
}
