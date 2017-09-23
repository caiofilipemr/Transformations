package gc;

import javax.swing.*;
import graphics.Canvas;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Transformations {
    public static final String FRAME_TITLE = "Transformations";
    private static Canvas canvas;
    private static ControlPanel controlPanel;
    private static Polyhedron polyhedron = new Polyhedron();

    public static void main(String[] args) {
        JFrame frame = new JFrame(FRAME_TITLE);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        canvas = new Canvas(polyhedron);
        canvas.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                savePointWithClick(e.getX(), e.getY());
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
        });
        frame.add(canvas, BorderLayout.CENTER);

        controlPanel = new ControlPanel(150, frame.getHeight(), e -> savePoint());
        frame.add(controlPanel, BorderLayout.EAST);

        frame.setVisible(true);
    }

    private static void savePoint() {
        polyhedron.addPoint(controlPanel.getInputedPoint());
        controlPanel.clearPointFields();
        canvas.updateUI();
    }

    private static void savePointWithClick(int x, int y) {
        polyhedron.addPoint(new Point(x, y, 0));
        canvas.updateUI();
    }
}