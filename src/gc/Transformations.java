package gc;

import javax.swing.*;
import graphics.canvas.Canvas;
import graphics.control.ControlPanel;
import graphics.operation.OperationPanel;
import graphics.canvas.PlanPlotter;

import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

public class Transformations {
    private static final String FRAME_TITLE = "Transformations";
    private static Canvas canvas;
    private static ControlPanel controlPanel;
    private static OperationPanel operationPanel;
    private static double Z_INDEX = 0;
    private static boolean enabled = true;
    private static Timer timer = null;

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
        canvas = new Canvas(new Polyhedron());
        canvas.addMouseListener(new CanvasMouseListener());
        canvas.addMouseMotionListener(new CanvasMouseMotionListener());
        canvas.addMouseWheelListener(Transformations::alterZIndex);
        frame.add(canvas, BorderLayout.CENTER);
    }

    private static void addControlPanelComponent(JFrame frame) {
        controlPanel = new ControlPanel(150, frame.getHeight());
        controlPanel.addSavePointListener(e -> savePointControlPanel());
        controlPanel.addDeletePointListener(e -> deletePoint());
        controlPanel.setPointListModel(canvas.getPolyhedron().points);
        frame.add(controlPanel, BorderLayout.EAST);
    }

    private static void addOperationPanelComponent(JFrame frame) {
        operationPanel = new OperationPanel(frame.getWidth(), 150);
        operationPanel.setDoTransformationListener(e -> doTransformation());
        operationPanel.setDoExampleListener(e -> doExample());
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
        canvas.getPolyhedron().addPoint(point);
        controlPanel.pointListChanged(canvas.getPolyhedron().points);
        PlanPlotter.setSelectedPoint(null);
        canvas.updateUI();
    }

    private static void setSelectedPoint(Point selectedPoint) {
        if (PlanPlotter.getSelectedPoint() != null) {
            canvas.getPolyhedron().addEdge(PlanPlotter.getSelectedPoint(), selectedPoint);
            PlanPlotter.setSelectedPoint(null);
        } else {
            PlanPlotter.setSelectedPoint(selectedPoint);
        }
        canvas.updateUI();
    }

    private static void deletePoint() {
        int selectedPointIndex = controlPanel.getSelectedPointIndex();
        if (selectedPointIndex != -1) {
            if (canvas.getPolyhedron().deletePoint(selectedPointIndex)) {
                controlPanel.pointListChanged(canvas.getPolyhedron().points);
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
        canvas.setModifiedPolyhedron(operationPanel.getTransformation().transform(canvas.getPolyhedron()));
        beginTransition();
    }

    private static void beginTransition() {
        setEnabled(false);
        canvas.setTransition(controlPanel.getSelectedTransition());
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                doTransition();
            }
        }, 0, controlPanel.getTransitionDelay());
    }

    private static void doTransition() {
        canvas.doTransition();
        canvas.updateUI();
        if (!canvas.inTransition())
            endTransition();
    }

    private static void endTransition() {
        timer.cancel();
        timer = null;
        setEnabled(true);
    }

    private static void doExample() {
        canvas.setPolyhedron(operationPanel.getExamplePolyhedron());
        controlPanel.pointListChanged(canvas.getPolyhedron().points);
        canvas.updateUI();
    }

    public static void setEnabled(boolean enabled) {
        Transformations.enabled = enabled;
        controlPanel.setEnabled(enabled);
        operationPanel.setEnabled(enabled);
    }

    private static class CanvasMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (!enabled) return;

            Point clickedPoint = new Point(e.getX(), e.getY(), 0);
            clickedPoint = PlanPlotter.getClickCollisionIfAny(clickedPoint, canvas.getPolyhedron().getPoints());

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