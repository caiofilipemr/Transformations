package gc;

import javax.swing.*;
import graphics.Canvas;
import graphics.ControlPanel;
import graphics.PlanPlotter;
import graphics.PointConverter;

import java.awt.*;
import java.awt.event.*;

public class Transformations {
    public static final String FRAME_TITLE = "Transformations";
    private static Canvas canvas;
    private static ControlPanel controlPanel;
    private static Polyhedron polyhedron = new Polyhedron();
    public static double Z_INDEX = 0;

    public static void main(String[] args) {
        JFrame frame = new JFrame(FRAME_TITLE);
        frame.setSize(1500, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        canvas = new Canvas(polyhedron);
        canvas.addMouseListener(new CanvasMouseListener());
        canvas.addMouseMotionListener(new CanvasMouseMotionListener());
        canvas.addMouseWheelListener(e -> alterZIndex(e));
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

    private static void savePointWithClick(Point point) {
        polyhedron.addPoint(point);
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

    private static void refreshCoordinates(MouseEvent e) {
        Point point = PlanPlotter.convert2dPointTo3d(new Point(e.getX(), e.getY(), Z_INDEX));
        controlPanel.setMouseX(point.x);
        controlPanel.setMouseY(point.y);
        controlPanel.setMouseZ(point.z);
    }

    private static void alterZIndex(MouseWheelEvent e) {
        Z_INDEX = Z_INDEX - (5 * e.getWheelRotation());
        refreshCoordinates(e);
    }

    private static class CanvasMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            savePointWithClick(PlanPlotter.convert2dPointTo3d(new Point(e.getX(), e.getY(), Z_INDEX)));
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
            refreshCoordinates(e);
        }
    }
}