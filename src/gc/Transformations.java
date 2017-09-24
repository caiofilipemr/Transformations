package gc;

import javax.swing.*;
import graphics.Canvas;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

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
        canvas.addMouseListener(new CanvasMouseListener());
        canvas.addMouseMotionListener(new CanvasMouseMotionListener());
        frame.add(canvas, BorderLayout.CENTER);

        controlPanel = new ControlPanel(150, frame.getHeight());
        controlPanel.addSavePointListener(e -> savePoint());
        controlPanel.addDeletePointListener(e -> deletePoint());
        controlPanel.setPointListModel(polyhedron.points);
        frame.add(controlPanel, BorderLayout.EAST);

        frame.setVisible(true);
    }

    private static void savePoint() {
        try {
            polyhedron.addPoint(controlPanel.getInputtedPoint());
            controlPanel.clearPointFields();
            controlPanel.pointListChanged();
            canvas.updateUI();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada inválida!");
        }
    }

    private static void savePointWithClick(double x, double y) {
        polyhedron.addPoint(new Point(x, y, 0));
        controlPanel.pointListChanged();
        canvas.updateUI();
    }

    private static void deletePoint() {
        int selectedPointIndex = controlPanel.getSelectedPointIndex();
        if (selectedPointIndex != -1) {
            if (polyhedron.deletePoint(selectedPointIndex)) {
                controlPanel.pointListChanged();
                canvas.updateUI();
            }
        }
    }

    private static class CanvasMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            savePointWithClick(e.getX() / (double) Canvas.PIXEL_FACTOR, e.getY() / (double) Canvas.PIXEL_FACTOR);
        }

        @Override
        public void mousePressed(MouseEvent e) { }

        @Override
        public void mouseReleased(MouseEvent e) { }

        @Override
        public void mouseEntered(MouseEvent e) { }

        @Override
        public void mouseExited(MouseEvent e) { }
    }

    private static class CanvasMouseMotionListener implements MouseMotionListener {

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {
            controlPanel.setMouseX(e.getX() / (double) Canvas.PIXEL_FACTOR);
            controlPanel.setMouseY(e.getY() / (double) Canvas.PIXEL_FACTOR);
            controlPanel.setMouseZ(0);
        }
    }
}