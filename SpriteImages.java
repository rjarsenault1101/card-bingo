/**
 * SpriteImages - given a sprite sheet of pacman characters, provides
 * an easy interface for selecting a particular image.
 * 
 * @atuhor Scott Bateman
 * @version March 1, 2014
 */

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.util.*;

public class SpriteImages
{
    //the sprites file
    private static final String fileName = "gnv4Q.jpg";

    //CLUBS
    public static final int ACE_CLUBS = 39;
    public static final int TWO_CLUBS= 40;
    public static final int THREE_CLUBS = 41;
    public static final int FOUR_CLUBS = 42;
    public static final int FIVE_CLUBS = 43;
    public static final int SIX_CLUBS = 44;
    public static final int SEVEN_CLUBS = 45;
    public static final int EIGHT_CLUBS = 46;
    public static final int NINE_CLUBS = 47;
    public static final int TEN_CLUBS = 48;
    public static final int JACK_CLUBS = 49;
    public static final int QUEEN_CLUBS = 50;
    public static final int KING_CLUBS = 51;

    //SPADES
    public static final int ACE_SPADES = 13;
    public static final int TWO_SPADES = 14;
    public static final int THREE_SPADES = 15;
    public static final int FOUR_SPADES = 16;
    public static final int FIVE_SPADES = 17;
    public static final int SIX_SPADES = 18;
    public static final int SEVEN_SPADES = 19;
    public static final int EIGHT_SPADES = 20;
    public static final int NINE_SPADES = 21;
    public static final int TEN_SPADES = 22;
    public static final int JACK_SPADES = 23;
    public static final int QUEEN_SPADES = 24;
    public static final int KING_SPADES = 25;

    //DIAMONDS
    public static final int ACE_DIAMONDS = 26;
    public static final int TWO_DIAMONDS = 27;
    public static final int THREE_DIAMONDS = 28;
    public static final int FOUR_DIAMONDS = 29;
    public static final int FIVE_DIAMONDS = 30;
    public static final int SIX_DIAMONDS = 31;
    public static final int SEVEN_DIAMONDS = 32;
    public static final int EIGHT_DIAMONDS = 33;
    public static final int NINE_DIAMONDS = 34;
    public static final int TEN_DIAMONDS = 35;
    public static final int JACK_DIAMONDS = 36;
    public static final int QUEEN_DIAMONDS = 37;
    public static final int KING_DIAMONDS = 38;

    //HEARTS
    public static final int ACE_HEARTS = 0;
    public static final int TWO_HEARTS = 1;
    public static final int THREE_HEARTS = 2;
    public static final int FOUR_HEARTS = 3;
    public static final int FIVE_HEARTS = 4;
    public static final int SIX_HEARTS = 5;
    public static final int SEVEN_HEARTS = 6;
    public static final int EIGHT_HEARTS = 7;
    public static final int NINE_HEARTS = 8;
    public static final int TEN_HEARTS = 9;
    public static final int JACK_HEARTS = 10;
    public static final int QUEEN_HEARTS = 11;
    public static final int KING_HEARTS = 12;
    
    
    public static final int BACK = 53;
    //dimensions and number of sprites in image
    private static final int WIDTH = 225;
    private static final int HEIGHT = 315;
    private static final int SPACER = 0;
    private static final int ROWS = 4;
    private static final int COLS = 13;

    //all of the sprite images
    private static final BufferedImage[] sprites = new BufferedImage[ROWS*COLS];
    private static final String cardbackfile = "bicycleback01.jpg";
    public static BufferedImage back;
    /**
     * getSprite - given a sprite number, returns a BufferedImage containing the
     * sprite.
     */
    public static BufferedImage getSprite(int spriteNumber) throws IOException
    {
        if (sprites[0] == null)
            loadSprites();

        int row = spriteNumber / COLS;
        int col = spriteNumber % COLS;
        if(spriteNumber == 53)
            return back;
        return sprites[COLS * row + col];
    }

    /**
     * loadSprites - helper method to load the sprites into a static array.
     */
    private static void loadSprites() throws IOException
    {
        BufferedImage spriteSheet = ImageIO.read(new File(fileName));

        for (int y = 0; y < ROWS; y++)
            for (int x = 0; x < COLS; x++)
            {
                int xPos = x * (WIDTH + SPACER);
                int yPos = y * (HEIGHT + SPACER);
                int idx = (y * COLS) + x;

                sprites[idx] = spriteSheet.getSubimage(xPos, yPos, WIDTH, HEIGHT);
            }
        back = ImageIO.read(new File(cardbackfile));
        shrinkSprites();
    }

    private static void shrinkSprites()
    {
        for(int i = 0; i<sprites.length; i++)
        {
            sprites[i] = resize(sprites[i], 60, 80);
        }
        back = resize(back, 60, 80);
    }

    private static BufferedImage resize(BufferedImage img, int newW, int newH) { 
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }  
}
