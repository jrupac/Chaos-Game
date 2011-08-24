import java.awt.Color;
import java.util.ArrayList;

public class ChaosGame
{
    private final int WINDOW_X = 1000;
    private final int WINDOW_Y = 1000;
    private final int NUM_POINTS = 10000;

    public ChaosGame(int vertices, double ratio)
    {
        setup();

        ArrayList<Point> list = drawPolygon(vertices);
        Point prevPoint = new Point(StdRandom.uniform(WINDOW_X), 
                                    StdRandom.uniform(WINDOW_Y));
        StdDraw.point(prevPoint.x, prevPoint.y);

        for (int i = 0; i < NUM_POINTS; i++)
        {
            Point randomVertex = list.get(StdRandom.uniform(vertices));
            Point currentPoint = new Point(prevPoint.x + ratio * (randomVertex.x - prevPoint.x),
                                           prevPoint.y + ratio * (randomVertex.y - prevPoint.y));
            StdDraw.point(currentPoint.x, currentPoint.y);
            prevPoint = currentPoint;
        }
    
    }

    private void setup()
    {
        StdDraw.setXscale(0, WINDOW_X);
        StdDraw.setYscale(0, WINDOW_Y);
        StdDraw.clear(StdDraw.WHITE);
    }

    private ArrayList<Point> drawPolygon(int vertices)
    {
        ArrayList<Point> list = new ArrayList<Point>();
        double center_x = WINDOW_X / 2, center_y = WINDOW_Y / 2;
        int radius = 200;
        double intAngle = (2 * Math.PI) / vertices;
        
        StdDraw.setPenRadius(.05);

        for (double angle = 0.0; angle < 2 * Math.PI; angle += intAngle)
        {
            double x = radius * Math.cos(angle) + center_x, 
                   y = radius * Math.sin(angle) + center_y;

            StdDraw.point(x, y);            
            list.add(new Point(x, y));
        }
       
        StdDraw.setPenRadius();
        return list;
    }

    public static void main(String args[])
    {
        try
        {
            ChaosGame app = new ChaosGame(Integer.parseInt(args[0]), 
                                          Double.parseDouble(args[1]));
        } 
        catch(Exception e)
        {
            System.err.println("Usage: java ChaosGame <number of vertices> <ratio>");
        }
    }
}

class Point
{
    public double x, y;

    public Point(double x, double y) 
    { 
        this.x = x; 
        this.y = y; 
    }
}
