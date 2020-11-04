import java.util.*;
import java.awt.*;

public class TileManager
{
    //empty Arraylist of Tile objects
    private ArrayList<Tile> tilesList;

    // This constructor is called every time a new tile manager object is created. 
    // Initially your manager is not storing any tiles.
    public TileManager()
    {
        tilesList = new ArrayList<Tile>();
    }

    // In this method you should add the given tile to the end of your tile 
    // manager's list of tiles.
    public void addTile(Tile rect)
    {
        tilesList.add(rect);
    }

    // This method should cause all of the tiles in the tile manager to draw 
    // themselves on the screen using the given graphical pen. You do not need to do 
    // this yourself directly by calling methods on the Graphics object; each Tile 
    // object already has a draw method that it can use to draw itself. Draw the 
    // tiles from bottom (start) to top (end) of your manager's list.
    // Recall that in order to refer to type Graphics, you must import java.awt.*; 
    // in your code.
    public void drawAll(Graphics g)
    {
        for (Tile tile : tilesList) //enhanced for loop
        {
            tile.draw(g);
        }
    }

    //checks if the x and y coordinates are inside the boundaries of the tile
    //by comparing them to the leftmost and topmost value as well as the width and height
    private boolean isInTile(int x, int y, Tile t)
    {
        
        if ((x >= t.getX() && x <= (t.getX() + t.getWidth())) &&
            (y > t.getY() && y <= (t.getY() + t.getHeight())))
        {
            return true;
        } else {
            return false;
        }
    }

    private int whichTile(int x, int y)
    {
        for (int m = tilesList.size() - 1; m >= 0 ; m--)
        {
            if (isInTile(x, y, tilesList.get(m))) {
                return m;
            }
        }

        return (-1);
    }

    // Called when the user left-clicks. It passes you the x/y coordinates the user 
    // clicked. If these coordinates touch any tiles, you should move the topmost of 
    // these tiles to the very top (end) of the list.
    public void raise(int x, int y)
    {
        int w = whichTile(x, y);          
        if (w != -1)
        {
            Tile placeHold = tilesList.get(w);
            tilesList.remove(w);
            tilesList.add(placeHold);
        }
    }

    // Called when the user Shift-left-clicks. If these coordinates touch any tiles, 
    // you should move the topmost of these tiles to the very bottom (beginning) of 
    // the list.
    public void lower(int x, int y)
    {
        int w = whichTile(x, y);
        if (w != -1)
        {
            Tile placeHold = tilesList.get(w);
            tilesList.remove(w);
            tilesList.add(0, placeHold);
        }
    }

    // Called when the user right-clicks. If these coordinates touch any tiles, you 
    // should delete the topmost of these tiles from the list.
    public void delete(int x, int y)
    {
        int w = whichTile(x, y);
        if (w != -1)
        {
            tilesList.remove(w);
        }
    }

    // Called when the user Shift-right-clicks. If these coordinates touch any 
    // tiles, you should delete all such tiles from the list.
    public void deleteAll(int x, int y)
    {
        for (int i = 0; i <= tilesList.size() - 1; i++) //order of loop does not matter as much here, because we would remove all tiles under click
        {
            if (isInTile(x, y, tilesList.get(i))) 
            {
                tilesList.remove(i);
                i--; //had i started from the bottom of the arraylist, adjustment of index is not needed, more efficient that way
            }
        }
    }

    // Called when the user types S. This method should perform two actions: (1) 
    // reordering the tiles in the list into a random order, and (2) moving every 
    // tile on the screen to a new random x/y pixel position. The random position 
    // should be such that the square's top-left x/y position is non-negative and 
    // also such that every pixel of the tile is within the passed width and height. 
    // For example, if the width passed is 300 and the height is 200, a tile of size 
    // 20x10 must be moved to a random position such that its top-left x/y position 
    // is between (0, 0) and (280, 190).
    // You can use the built-in Java method Collections.shuffle to randomly 
    // rearrange the elements of your list (step 1).
    public void shuffle(int width, int height) 
    {
        Collections.shuffle(tilesList);  //rearrange elements of tilesList
        for (Tile tile : tilesList)
        {          
            tile.setX((int)(Math.random()*(width - tile.getWidth())));
            tile.setY((int)(Math.random()*(height - tile.getHeight())));
        }
    }
}