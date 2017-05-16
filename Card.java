import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
public class Card
{
    private BufferedImage face, front;
    private BufferedImage back;
    private int xLoc, yLoc;
    private String suit;
    private int name;
    private boolean flipped = false;
    public Card(BufferedImage p_face) throws IOException
    {
        face = p_face;
        front = p_face;
        back = SpriteImages.getSprite(53);
    }
    
    public void setName(int p_name)
    {
        name = p_name;
    }
    public int getName()
    {
        return name;
    }
    
    public void setSuit(String p_suit)
    {
        suit = p_suit;
    }
    public String getSuit()
    {
        return suit;
    }
    
    public int getX()
    {
        return xLoc;
    }
    public int getY()
    {
        return yLoc;
    }
    
    public void setX(int x)
    {
        xLoc = x;
    }
    public void setY(int y)
    {
        yLoc = y;
    }
    
    public void draw(Graphics g, int x, int y)
    {
        xLoc = x;
        yLoc = y;
        g.drawImage(face, x, y, null);
    }
    public void draw(Graphics g)
    {
        g.drawImage(face, xLoc, yLoc, null);
    }
    public void flip()
    {
        BufferedImage temp = face;
        face = back;
        back = temp;
        flipped = !flipped;
    }
    public void setFront()
    {
        face = front;
    }
    public boolean isFlipped()
    {
        return flipped;
    }
    public boolean equalsNumber(Card equalTo)
    {
        if(name == equalTo.getName())
            return true;
         else 
            return false;
    }
    public boolean equalsSuit(Card equalTo)
    {
        if(suit.equals(equalTo.getSuit()))
            return true;
        else
            return false;
    }
    public String toString()
    {
        return name + " of " + suit + " at x = " + xLoc + " & y = " + yLoc + ".";
    }
}