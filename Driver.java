import java.awt.*;
import javax.swing.*;
import java.io.*;
public class Driver
{
    public static void main(String[] args) throws IOException
    {
        JFrame frame = new JFrame("cards");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Table theGame = new Table();
        frame.getContentPane().add(theGame);
        
        frame.pack();
        frame.setVisible(true);
    }
}
