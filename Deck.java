import java.util.Scanner;
public class Deck
{
    private CardNode list;
    public Deck()
    {
        list = null;
    }
    public void add(Card c)
    {
        CardNode node = new CardNode(c);
        CardNode current;
        
        if(list == null)
        {
            list=node;
        } else
        {
            current = list;
            while(current.next !=null)
            {
                current = current.next;
            }
            current.next = node;
        }
    }
    public String toString()
    {
        String result = "";
        
        CardNode c = list;
        while(c!=null)
        {
            result += c.card + "\n";
            c = c.next;
        }
        return result;
    }
    private class CardNode
    {
        public Card card;
        public CardNode next;
        public CardNode(Card c)
        {
            card = c;
            next = null;
        }
    }
}