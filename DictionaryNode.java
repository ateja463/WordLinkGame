
public class DictionaryNode {
    protected String word;
    private int level;
    private DictionaryNode next;
    public DictionaryNode(String _word , int _level)
    {
    	//System.out.println("DictionaryNode constructor");
    	word = _word;
    	level = _level;
    	next = null;
    }
    int getLevel()
    {
    	return level;
    }
    DictionaryNode getNext()
    {
    	return next;
    }
    String getWord()
    {
    	return word;
    }
    void setNext(DictionaryNode curr_node , DictionaryNode next_node)
    {
    	curr_node.next = next_node;
    }
}
