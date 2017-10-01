package gc;

import javax.swing.*;
import graphics.Canvas;
import graphics.ControlPanel;
import graphics.OperationPanel;
import graphics.PlanPlotter;

import java.awt.*;
import java.awt.event.*;

public class Transformations {
    public static final String FRAME_TITLE = "Transformations";
    private static Canvas canvas;
    private static ControlPanel controlPanel;
    private static OperationPanel operationPanel;
    private static Polyhedron polyhedron = new Polyhedron();
    public static double Z_INDEX = 0;

    public static void main(String[] args) {
        JFrame frame = createFrame();

        addCanvasComponent(frame);
        addControlPanelComponent(frame);
        addOperationPanelComponent(frame);

        frame.setVisible(true);
    }

    private static JFrame createFrame() {
        JFrame frame = new JFrame(FRAME_TITLE);
        frame.setSize(1500, 800);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        return frame;
    }

    private static void addCanvasComponent(JFrame frame) {
        canvas = new Canvas(polyhedron);
        canvas.addMouseListener(new CanvasMouseListener());
        canvas.addMouseMotionListener(new CanvasMouseMotionListener());
        canvas.addMouseWheelListener(Transformations::alterZIndex);
        frame.add(canvas, BorderLayout.CENTER);
    }

    private static void addControlPanelComponent(JFrame frame) {
        controlPanel = new ControlPanel(150, frame.getHeight());
        controlPanel.addSavePointListener(e -> savePointControlPanel());
        controlPanel.addDeletePointListener(e -> deletePoint());
        controlPanel.setPointListModel(polyhedron.points);
        frame.add(controlPanel, BorderLayout.EAST);
    }

    private static void addOperationPanelComponent(JFrame frame) {
        operationPanel = new OperationPanel(frame.getWidth(), 150);
        operationPanel.setDoTransformationListener(e -> doTransformation());
        frame.add(operationPanel, BorderLayout.SOUTH);
    }

    private static void savePointControlPanel() {
        try {
            savePoint(controlPanel.getInputtedPoint());
            controlPanel.clearPointFields();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Incorrect format of point!");
        }
    }

    private static void savePoint(Point point) {
        polyhedron.addPoint(point);
        controlPanel.pointListChanged();
        PlanPlotter.setSelectedPoint(null);
        canvas.updateUI();
    }

    private static void setSelectedPoint(Point selectedPoint) {
        if (PlanPlotter.getSelectedPoint() != null) {
            polyhedron.addEdge(PlanPlotter.getSelectedPoint(), selectedPoint);
            PlanPlotter.setSelectedPoint(null);
        } else {
            PlanPlotter.setSelectedPoint(selectedPoint);
        }
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

    private static void doTransformation() {
        canvas.setModifiedPolyhedron(operationPanel.getTransformation().transform(polyhedron));
    }

    private static class CanvasMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            Point clickedPoint = new Point(e.getX(), e.getY(), 0);
            clickedPoint = PlanPlotter.getClickCollisionIfAny(clickedPoint, polyhedron.getPoints());

            if (clickedPoint == null) {
                savePoint(PlanPlotter.convert2dPointTo3d(new Point(e.getX(), e.getY(), Z_INDEX)));
            } else {
                setSelectedPoint(clickedPoint);
            }
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