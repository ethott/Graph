import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Created by ethannott on 11/7/16.
 */

public class UserInterface extends Canvas {

    private static ArrayList<Point> myPoints = new ArrayList<>();
    private static ArrayList<Point> mouseMovedPoints = new ArrayList<>();
    private static Point mousePos = new Point();
    private static JFrame _frame;
    private static JPanel _mPanel;
    private static UserInterface _canvas;
    private static JLabel _locationLabel;

    private final Color _pointColor = new Color(165, 69, 3);
    private final Color _lineColor = new Color(0, 0, 0);
    private final Color _gridColor = new Color(168, 165, 169);
    private final Color _pointNavigatorColor = new Color(168,165,169);
    private final Color _navigatorDimensionColor = new Color(0,0,0);
    private final int gw = 40;

    private static Point mouseHasBeenClicked(Point mousePoint) {
        //TODO stuff here
        myPoints.add(mousePoint);
        return mousePoint;
    }
    private static Point mouseHasBeenMoved(Point mouseMovedPoint) {
        //TODO stuff here
//        mouseMovedPoints.add(mouseMovedPoint);
        mousePos = mouseMovedPoint;
        return mouseMovedPoint;
    }

    public UserInterface() {
        //Constructor
        _frame = new JFrame("Graph");
        _mPanel = new JPanel();
        _canvas = this;
        _locationLabel= new JLabel();
    }

    public static void init() {
        //initializing
        _frame.setSize(400, 422);
        _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _frame.setResizable(false);
        _frame.setContentPane(_mPanel);
        _frame.setVisible(true);
        _locationLabel.setText(""+mousePos.x+"x"+mousePos.y);

        _mPanel.add(_canvas, BorderLayout.CENTER);

        _canvas.setSize(400, 400);
        _canvas.addMouseListener(new MouseEar());
        _canvas.setFocusable(true);
        _canvas.addMouseMotionListener(new MouseEar());
    }

    public void paint(Graphics g) {
        //graphicsd
        Graphics2D g2 = (Graphics2D) g;
//        Polygon tri = new Polygon();
//        tri.addPoint(100, 100);
//        tri.addPoint(100, 300);
//        tri.addPoint(200, 300);

        //draw whole grid
        //draw y axis grid
        for (int i = 0; i < getHeight(); i += gw) {
            g2.setColor(_gridColor);
            g2.drawLine(0, i, getWidth(), i);

        }
        //draw x axis grid
        for (int j = 0; j < getWidth(); j += gw) {
            g2.setColor(_gridColor);
            g2.drawLine(j, 0, j, getHeight());

        }
        //draw x and y axis
        g2.setColor(_lineColor);
        g2.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);
        g2.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());

        //Painting each point in the array
        g2.setColor(_pointColor);
        myPoints.forEach(p -> g2.fillOval(p.x - 4, p.y - 4, 8, 8));


//        if(myPoints.isEmpty() == false) {
//            for (int i = 0; i < myPoints.size(); i++) {
//                int lastPointX = i - 1;
//                int lastPointY = i - 1;
//                //g2.drawLine(myPoints.get(0).x,myPoints.get(0).y,myPoints.get(i).x,myPoints.get(i).y);
//                g2.drawLine(myPoints.get(myPoints.size() -1).x, myPoints.get(myPoints.size() -1).y, myPoints.get(i).x, myPoints.get(i).y);
//
//            }
//        } else{
//        System.out.println("is empty");
//        }


        //point navigator
        g2.setColor(_pointNavigatorColor);

        g2.drawLine(0, mousePos.y, mousePos.x, mousePos.y);
        g2.drawLine(mousePos.x, 0, mousePos.x, mousePos.y);
        //pant location on point navigator
        g2.setColor(_navigatorDimensionColor);
        Font font = new Font("Jokerman", Font.ROMAN_BASELINE, 5);
        g2.drawString(mousePos.x+"x"+mousePos.y,mousePos.x,mousePos.y);

    }


    static class MouseEar implements MouseListener, MouseMotionListener, MouseWheelListener {


        @Override
        public void mouseClicked(MouseEvent e) {
            Point clickedPoint = e.getPoint();
            _canvas.repaint();
            mouseHasBeenClicked(clickedPoint);
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {
            _canvas.repaint();
            Point mouseMovedPoint = e.getPoint();
            mouseHasBeenMoved(mouseMovedPoint);
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {

        }
    }
}
