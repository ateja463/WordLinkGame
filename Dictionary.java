import java.util.*;
import java.io.*;
public class Dictionary {
	private ListOfNodes[] data;
	private int level ;
	Dictionary()
	{
		Scanner scanner = new Scanner(System.in);
		level = 1;
		//System.out.println("Dictionary Class constructor");
		data = new ListOfNodes[26];
		for(int i = 0 ;i<26 ;i++)
		{
			data[i] = new ListOfNodes();
		}
	}
	//sets the difficulty level of the game
	void setLevel()
	{
		System.out.println("");
		System.out.println("the current level is "+level);
		System.out.println("enter the level");
		Scanner scanner = new Scanner(System.in);
		int _level = scanner.nextInt();
		if(level != _level )
		{
			if (_level == 1 || _level == 2 )
			{
				level = _level;
				System.out.println("level set to " + _level);
			}
			else 
			{
				System.out.println("setting the difficulty level failed as  there are only two levels ");
			}
		}
		else
		{
			System.out.println("current level is "+ level + " no need to change the level");
		}
		WordLink.displayMenu();
		
	}
	//prints the words in the dictionary whose level is n
	/*private void printLevel(int n)
	{
		System.out.println("entered level "+n+" print");
		Scanner scanner = new Scanner(System.in);
		int wordCount = 0 , lineCount = 0;
		for(int i = 0 ;i < 26 ;i++)
		{
			if(data[i].getHead() != null)
			data[i].printList();
		}
	}*/
	private void printLevel(int n)
	{
		System.out.println("Level "+n);
		Scanner scanner = new Scanner(System.in);
		int wordCount = 0 , lineCount = 0;
		DictionaryNode temp;
		for(int i = 0 ;i < 26 ;i++)
		{
			temp = data[i].getHead();
			while(temp != null)
			{
				if(temp.getLevel() == n)
				{
					System.out.print(temp.getWord()+" "); 
					wordCount++;
				}
				if(wordCount == 7 )
				{
					System.out.println("  ");
					lineCount++;
					wordCount = 0;
				}
				if(lineCount == 5)
				{
					lineCount = 0;
					System.out.println();
					System.out.println();
					System.out.println("press enter key to continue");
					System.out.println();
					System.out.println();
					String s = scanner.nextLine();
				}
	            temp = temp.getNext(); 
			}
		}
		if(n == 1)
		{
			System.out.println();
			System.out.println();
			System.out.println("press enter key to continue");
			System.out.println();
			System.out.println();
			String s = scanner.nextLine();
		}
		
	}
	//displays the words in the dictionary level by level
	void displayDictionary()
	{
		System.out.println("Dictionary Class displayDictionary");
		printLevel(1);
		System.out.println("  ");
		
		printLevel(2);
		System.out.println("  ");
		WordLink.displayMenu();
	}
	//gets the index of the string where it has to be inserted in the data[] array;
	int getIndexOfChar(char a)
	{
		int n = 0;
		if('a' <= a && a <= 'z')
			n = a -'a';
		else if('A' <= a & a <= 'Z') 
			n =  a - 'A';
		return n;
	}
	//inserts a word into the dictionary
	void insertWord(String word, int level , boolean file)
	{
		int index = getIndexOfChar(word.charAt(0));
		data[index].insertIntoList(word, level);
        //data[index].printList(); 
		if(file == false)
		{
			WordLink.displayMenu();
		}
		
	}
	
	
	
	 void playGame1Player()
	 {
		System.out.println("play the game level "+ level);
		Scanner scanner = new Scanner(System.in);
		Set <String> checkSet = new HashSet<String>();//to find whether the word is given previously or not
		
		int firstIndex , lastIndex;
		String result = new String("");
		String comWord = new String("");
		System.out.println("enter a word");
		
		while(true)
		{
			String player1String = scanner.nextLine();
			if(player1String.length() != 0)
			{
				if(!comWord.equals("") && player1String.charAt(0) != comWord.charAt(comWord.length() - 1))
			    {
				    System.out.println("you lost because of mismatch");
			    	break;
			    }
				if(checkSet.contains(player1String))
				{
					System.out.println("\""+player1String +"\" is already given previously. you didn't win");
					break;
				}
				checkSet.add(player1String);
				result += player1String + " - ";
				firstIndex = getIndexOfChar(player1String.charAt(0));
				lastIndex = getIndexOfChar(player1String.charAt(player1String.length() - 1));
				System.out.println();
				//search the word in the dictionary
				if(data[firstIndex].search(player1String )) //if present give other word
				{
					//System.out.println("word present in the dictionary");
					//if unable to find the word declare player as winner
					comWord = data[lastIndex].findResponse(checkSet , level);
					//System.out.println(comWord);
					if(comWord.compareTo("*") == 0 ) 
					{
						System.out.println("Well Done! You Win");
						break;
					}
					else
					{	
						checkSet.add(comWord);
						result += comWord;
						//System.out.println(result);
					}
					
				
				}
				else//player one lost
				{
					System.out.println("\""+player1String+"\" does not exist in the dictionary. You did not win. ");				
					break;
				}
				result += " - ";
				System.out.print(result);
			}
		}
		WordLink.displayMenu();
	}
	 void playGame2Players()
	 {
		    int player = 1;
		    String prev = new String("");
		    System.out.println("play the game level "+ level);
			Scanner scanner = new Scanner(System.in);
			Set <String> checkSet = new HashSet<String>();//to find whether the word is given previously or not
			
			int firstIndex , lastIndex;
			String result = new String();
			String comWord = new String();
			System.out.println("P1 :enter a word");
			
			while(true)
			{
				String playerString = scanner.nextLine();
				if(checkSet.contains(playerString))
				{
					System.out.println("\""+playerString +"\" is already given previously. player"+player+" didn't win");
					break;
				}
				//checkSet.add(playerString);
				result += playerString ;
				firstIndex = getIndexOfChar(playerString.charAt(0));
				lastIndex = getIndexOfChar(playerString.charAt(playerString.length() - 1));
				System.out.println();
				//search the word in the dictionary
				if(data[firstIndex].search(playerString ) && !checkSet.contains(playerString)) //if present give other word
				{
					if(prev.equals(""))
					{
						checkSet.add(playerString);
					}
					else
					{
						if(playerString.charAt(0) == prev.charAt(prev.length()-1))
						{
							checkSet.add(playerString);
						}
						else
						{
							System.out.println("player"+ player+" lost mismatch between previuos and oresent words");
						}
					}
					
				}
				else
				{
					System.out.println("\""+playerString+"\" does not exist in the dictionary. Player"+player+" did not win. ");				
					break;
				}
				result += " - ";
				if(player == 1 ) 
				{
					//System.out.println("player 2");
					player = 2;
				}
				else if(player == 2)
				{
					//System.out.println("player 1");					
					 player = 1;
				}
				prev = playerString;
				System.out.print("P"+player+": -");
				System.out.print(result);
			}
			WordLink.displayMenu();
	 }
	 
}
