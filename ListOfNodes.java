import java.util.Set;

public class ListOfNodes {
    private DictionaryNode head;
    private int count;
    ListOfNodes()
    {
    	//System.out.println("ListOfNodes constructor");
    	head = null;
    	count = 0;
    }
    /*void initializeHeadToNull()
    {
    	System.out.println("initializeHeadToNull");
    	head = null;
    }*/
    int getCount()
    {
    	return count;
    }
    void incrementCount()
    {
    	count++;
    }
    DictionaryNode getHead()
    {
    	//System.out.println("getHead");
    	return head;
    }
    void setInitialHead(String word , int level )
    {
    	head = new DictionaryNode(word , level);
    }
    void setHead(DictionaryNode head_node , DictionaryNode curr_node) 
    {
    	head.setNext(head_node, curr_node);
    }
    void insertIntoList(String word , int level)
    {
    	//System.out.println("insertIntoList");
    	if(head == null)
    	{
    		//System.out.println("head is null case");
    		append(word , level);
    		incrementCount();
    	}
    	else
    	{
    		if(word.compareTo(head.getWord()) < 0)
    		{
    			System.out.println("\""+word+ "\" is inserted in level " +level );
    			push(word , level);
    			incrementCount();
    			return;
    		}
    		else if(word.compareTo(head.getWord()) == 0)
    		{
    			System.out.println("\""+word+"\" already exists in the dictionary");
				return;
    		}
    		DictionaryNode temp = head;
    		
    		while(temp != null)
    		{
				//System.out.println("temp.getWord "+temp.getWord()+"  word "+word);
    			if(temp.getNext() != null ) 
    			{
					//System.out.println("temp.getWord "+temp.getWord()+"  word "+word);
    				if(word.compareTo(temp.getNext().getWord()) < 0)
    				{
						//System.out.println("inserting after "+ temp.getWord());
						System.out.println("\""+word+ "\" is inserted in level " +level );
    					insertAfter(temp , word , level);
    					incrementCount();
        				return;
    				}
    				else if(word.compareTo(temp.getNext().getWord()) == 0)
    				{
    					System.out.println("\""+word+"\" already exists in the dictionary");
        				return;
    				}
    			}
				
				//System.out.println("get next while");
    			temp = temp.getNext();
    		}
    		//System.out.println("inserting at last");
			System.out.println("\""+word+ "\" is inserted in level " +level);
    		incrementCount();
    		append(word , level);
    		return;
    	}
    }
    /*void displayList()
    {
    	DictionaryNode head1 = head;
    	while(head1 != null)
    	{
    		System.out.print(head1.getWord());
    		System.out.print("  ");
    		System.out.println(head1.getLevel());
    		head1 = head1.getNext();
    	}
    }
    void displayList(DictionaryNode head)
    {
        
    	while(head != null)
    	{
    		System.out.print(head.getWord());
    		System.out.print("  ");
    		System.out.println(head.getLevel());
    		head = head.getNext();
    	}
    }*/
    
    
    //linked list API's
    public void push(String word , int level)  // insert at the beginning
    { 
        DictionaryNode new_node = new DictionaryNode(word , level); 
        new_node.setNext(new_node , head); 
        head = new_node; 
    } 
  
    
    public void insertAfter(DictionaryNode prev_node, String word , int level) 
    { 
        if (prev_node == null) 
        { 
            System.out.println("The given previous node cannot be null"); 
            return; 
        } 
        DictionaryNode new_node = new DictionaryNode(word , level); 
        new_node.setNext(new_node, prev_node.getNext());
        new_node.setNext(prev_node, new_node);
    } 
     

    public void append(String word , int level) //insert at last of list
    { 
    	DictionaryNode new_node = new DictionaryNode(word , level); 
        if (head == null) 
        { 
            head = new DictionaryNode(word , level); 
            return; 
        } 
        new_node.setNext(new_node , null); 
        DictionaryNode last = head;  
        while (last.getNext() != null) 
            last = last.getNext(); 
        last.setNext(last , new_node); 
        return; 
    } 

    public void printList() 
    { 
    	DictionaryNode tnode = head; 
        while (tnode != null) 
        { 
            System.out.print(tnode.getWord()+" "); 
            System.out.print(tnode.getLevel()+" ");
            tnode = tnode.getNext(); 
        } 
        System.out.println("  ");
    }
    
    boolean search(String word)
    {
    	DictionaryNode tnode = head; 
    	while (tnode != null) 
        { 
    		if(tnode.getWord().compareTo(word) == 0)
    		{
    			return true;
    		}
    		tnode = tnode.getNext(); 
        }
    	return false;
    }
	boolean search(String word , int level)
    {
    	DictionaryNode tnode = head; 
    	while (tnode != null) 
        { 
    		if(tnode.getWord().compareTo(word) == 0 )
    		{
				if(level == 2)
				{
					return true;
				}
    			else
				{
					if(tnode.getLevel() == level)
					{
						return true;
					}
				}
			
    		}
    		tnode = tnode.getNext(); 
        }
    	return false;
    }

    String findResponse(Set <String> checkSet , int level)
    {
    	DictionaryNode tnode = head; 
    	while (tnode != null) 
        { 
    		
    		if(!checkSet.contains(tnode.getWord()) )
    		{
    			if(level == 2)
    			{
    				return tnode.getWord();
    			}
    			else
    			{
    				if(tnode.getLevel() == level)
    				{
    					return tnode.getWord();
    				}
    			}
    		}
    		tnode = tnode.getNext(); 
        }
    	return "*"; 
    }
    
}
