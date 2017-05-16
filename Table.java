import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import java.awt.event.*;
public class Table extends JPanel
{
    private final int WIDTH = 900, HEIGHT = 600;
    private JButton shuffle, deal;
    private ArrayList<Card> deck = new ArrayList<Card>();
    private int numCards = 5, numPlayers = 4;
    private int cardWidth = 60, cardHeight = 80;
    private ArrayList<Card> left = new ArrayList<Card>(), right = new ArrayList<Card>(), 
    up = new ArrayList<Card>(), down = new ArrayList<Card>();
    private ArrayList<Card> discard = new ArrayList<Card>();
    private javax.swing.Timer time, time2;
    int i = 0;
    public Table() throws IOException 
    {
        deck = deckGen();

        time = new javax.swing.Timer(20, new TimerListener());
        time2 = new javax.swing.Timer(100, new TimerListener());
        //buttons!!
        shuffle = new JButton("Shuffle");
        deal = new JButton("Deal");
        add(shuffle);
        add(deal);

        //listeners
        ButtonListener bl = new ButtonListener();
        shuffle.addActionListener(bl);
        deal.addActionListener(bl);
        addMouseListener(new MouseList());

        //important stuff
        setBackground(Color.GREEN.brighter());
        setFocusable(true);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }
    //fills the arrayList with cards
    public ArrayList<Card> deckGen() throws IOException
    {        
        for(int i = 0; i<52; i++)
        {
            deck.add(new Card(SpriteImages.getSprite(i)));
            //sets the cards original position to the middle of the screen
            deck.get(i).setX(WIDTH/2-30);
            deck.get(i).setY(HEIGHT/2-40);
            if(i<13)
            {
                deck.get(i).setSuit("Hearts");
            }
            if(i<26&&i>12)
            {
                deck.get(i).setSuit("Spades");
            }
            if(i<39&&i>25)
            {
                deck.get(i).setSuit("Diamonds");
            }
            if(i<52&&i>38)
            {
                deck.get(i).setSuit("Clubs");
            }
            deck.get(i).setName((i%13)+1);
        }
        return deck;
    }

    public void shuffle() throws IOException
    {
        Random genny = new Random();
        int swaploc;

        up.clear();
        down.clear();
        right.clear();
        left.clear();
        deck.clear();
        discard.clear();
        repaint();
        deck = deckGen();
        /*
        for(Card card: deck)
        {
        System.out.println(card + "\n  " + card.getX() + " " + card.getY());
        }
        System.out.println("================================================");
         */
        //does my patented shuffle swap. 
        for(int i = 0; i<100; i++)
        {
            swaploc = genny.nextInt(52);
            Card temp = deck.get(swaploc);
            deck.remove(swaploc);
            deck.add(temp);
        }

        /*
        for(Card card: deck)
        {
        System.out.println(card + "\n  " + card.getX() + " " + card.getY());

        }
        System.out.println("***********************************************");
         */
        repaint();
    }

    public void paintComponent(Graphics g)
    {          
        super.paintComponent(g);
        for(Card card:deck)
        {
            card.draw(g);
        }
        for(Card card:up)
        {
            card.draw(g);
        }
        for(Card card:left)
        {
            card.draw(g);
        }
        for(Card card:down)
        {
            card.draw(g);
        }
        for(Card card:right)
        {
            card.draw(g);
        }

    }

    /*
     * places a card in an evenly spaced out X-coordinate location in the middle
     * of the scree
     */
    public int placementX(int numCards,int cardNumber)
    {
        int toreturn = WIDTH/2 - (numCards/2)*cardWidth + cardWidth*cardNumber;
        if(numCards%2 == 0)
        {
            return toreturn;
        } else
        {
            return (int)(toreturn - 0.5*(cardWidth));
        }
    }

    /*
     * places a card in an evenly spaced out Y-coordinate location in the middle
     * of the screen
     */
    public int placementY(int numCards, int cardNumber)
    {
        int toreturn = HEIGHT/2 - (numCards/2)*cardHeight + cardHeight*cardNumber;
        if(numCards%2 == 0)
        {
            return toreturn;
        } else
        {
            return (int)(toreturn-0.5*(cardHeight));
        }
    }

    public void flip15()
    {
        time2.start();
    }

    public void discard(Card c)
    {
        Random gen = new Random();
        int x = gen.nextInt(780)+60;
        int y = gen.nextInt(440)+80;
        while(x>240&&x<540)
        {
            x = gen.nextInt(780)+60;
        }
        while(y>100&&y<340)
        {
            y = gen.nextInt(440)+80;
        }
        c.setX(x);
        c.setY(y);
        discard.add(c);
    }

    public Card findCard(int mouseX1, int mouseY1)
    {
        Card toreturn = null;
        for(Card card:up)
        {
            if(mouseX1 >= card.getX() && mouseX1 <=card.getX() + 60 &&
            mouseY1 >= card.getY() && mouseY1 <=card.getY() + 80)
            {
                toreturn = card;
            }
        }
        for(Card card:down)
        {
            if(mouseX1 >= card.getX() && mouseX1 <=card.getX() + 60 &&
            mouseY1 >= card.getY() && mouseY1 <=card.getY() + 80)
                toreturn = card;
        }
        for(Card card:right)
        {
            if(mouseX1 >= card.getX() && mouseX1 <=card.getX() + 60 &&
            mouseY1 >= card.getY() && mouseY1 <=card.getY() + 80)
                toreturn = card;
        }
        for(Card card:left)
        {
            if(mouseX1 >= card.getX() && mouseX1 <=card.getX() + 60 &&
            mouseY1 >= card.getY() && mouseY1 <=card.getY() + 80)
                toreturn = card;
        }
        return toreturn;
    }
    private class ButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        {
            if(e.getSource().equals(shuffle))
                try{shuffle();} catch(IOException ioe){}
            if(e.getSource().equals(deal))
            {

                try{shuffle();} catch(IOException ioe){}
                for(int j = 0; j<numCards; j++)
                {                    
                    up.add(deck.get(0));
                    right.add(deck.get(1));
                    down.add(deck.get(2));
                    left.add(deck.get(3));
                    deck.remove(3);
                    deck.remove(2);
                    deck.remove(1);
                    deck.remove(0);
                }
                time.start();
            }
            repaint();
        }
    }
    private class MouseList extends MouseAdapter
    {
        public void mouseClicked(MouseEvent e)
        {
            int mouseX1 = e.getX();
            int mouseY1 = e.getY();
            boolean match = false;
            try
            {
                Card clicked = findCard(mouseX1, mouseY1);
                for(int i = 0; i<15 && !match; i++)
                {
                    if(clicked.equalsNumber(deck.get(i)))
                    {
                        match = true;
                        
                        discard(clicked);
                        up.remove(clicked);
                        right.remove(clicked);
                        down.remove(clicked);
                        left.remove(clicked);
                    }
                }

            } catch(Exception ex)
            {
                ex.printStackTrace();
            }
            repaint();
        }
    }
    private class TimerListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource().equals(time))
            {
                up.get(i).setX(placementX(up.size(), i));
                up.get(i).setY(10);

                right.get(i).setX(WIDTH-70);
                right.get(i).setY(placementY(right.size(), i));

                down.get(i).setX(placementX(down.size(), i));
                down.get(i).setY(HEIGHT-90);

                left.get(i).setX(10);
                left.get(i).setY(placementY(left.size(), i));
                repaint();
                if(i<numCards-1)
                {
                    i++;
                } else
                {
                    i = 0;
                    time.stop();

                    flip15();
                    for(int i = 15;i<deck.size();i++)
                    {
                        deck.get(i).setX(WIDTH/2+150);
                        deck.get(i).setY(HEIGHT/2-40);
                        deck.get(i).flip();
                    }

                }
            }
            if(e.getSource().equals(time2))
            {               
                deck.get(i).setX(placementX(5,i%5));
                deck.get(i).setY(placementY(3,i%3));
                repaint();

                if(i<14)
                    i++;
                else{
                    time2.stop();
                    i = 0;
                }
            }
        }
    }
}
