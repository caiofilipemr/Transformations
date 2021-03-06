package graphics.control;

import graphics.canvas.transition.Transition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class ControlPanel extends JPanel {
    private final PointPanel pointPanel = new PointPanel();
    private final PointListPanel pointListPanel = new PointListPanel();
    private final MouseCoordinatePanel mouseCoordinatePanel = new MouseCoordinatePanel();
    private final TransitionPanel transitionPanel = new TransitionPanel();

    public ControlPanel(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        pointPanel.setPreferredSize(new Dimension(getPreferredSize().width, 130));
        pointListPanel.setPreferredSize(new Dimension(getPreferredSize().width, 230));
        mouseCoordinatePanel.setPreferredSize(new Dimension(getPreferredSize().width, 100));
        transitionPanel.setPreferredSize(new Dimension(getPreferredSize().width, 120));
        createComponents();
    }

    private void createComponents() {
        add(pointPanel, BorderLayout.PAGE_START);
        add(pointListPanel, BorderLayout.CENTER);
        add(mouseCoordinatePanel, BorderLayout.SOUTH);
        add(transitionPanel, BorderLayout.PAGE_END);
    }

    public void addSavePointListener(ActionListener savePointListener) {
        pointPanel.addSavePointListener(savePointListener);
    }

    public void addDeletePointListener(ActionListener deletePointListener) {
        pointListPanel.addDeletePointListener(deletePointListener);
    }

    public void clearPointFields() {
        pointPanel.clearPointFields();
    }

    public void pointListChanged(List<gc.Point> points) {
        pointListPanel.pointListChanged(points);
    }

    public gc.Point getInputtedPoint() {
        return pointPanel.getInputtedPoint();
    }

    public int getSelectedPointIndex() {
        return pointListPanel.getSelectedPointIndex();
    }

    public void setPointListModel(List<gc.Point> points) {
        pointListPanel.setModel(points);
    }

    public void setMouseX(double x) {
        mouseCoordinatePanel.setXLabel(String.valueOf(x));
    }

    public void setMouseY(double y) {
        mouseCoordinatePanel.setYLabel(String.valueOf(y));
    }

    public void setMouseZ(double z) {
        mouseCoordinatePanel.setZLabel(String.valueOf(z));
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        pointPanel.setEnabled(enabled);
        pointListPanel.setEnabled(enabled);
    }

    public long getTransitionDelay() {
        return transitionPanel.getTransitionTime();
    }

    public Transition getSelectedTransition() {
        return transitionPanel.getSelectedTransition();
    }
}
