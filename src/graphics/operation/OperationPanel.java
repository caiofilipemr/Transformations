package graphics.operation;

import gc.Polyhedron;
import operation.projection.Projection;
import operation.transformation.Transformation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class OperationPanel extends JPanel {
    public static final int CHILDREN_WIDTH = 250;
    public static final int CHILDREN_HEIGHT_PAD = 10;
    private final TransformationPanel transformationPanel = new TransformationPanel();
    private final ProjectionPanel projectionPanel = new ProjectionPanel();
    private final ExamplePanel examplePanel = new ExamplePanel();
    private final ExtraPanel extraPanel = new ExtraPanel();

    public OperationPanel(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        transformationPanel.setPreferredSize(new Dimension(CHILDREN_WIDTH, getPreferredSize().height - CHILDREN_HEIGHT_PAD));
        projectionPanel.setPreferredSize(new Dimension(CHILDREN_WIDTH, getPreferredSize().height - CHILDREN_HEIGHT_PAD));
        examplePanel.setPreferredSize(new Dimension(CHILDREN_WIDTH, getPreferredSize().height - CHILDREN_HEIGHT_PAD));
        extraPanel.setPreferredSize(new Dimension(CHILDREN_WIDTH, getPreferredSize().height - CHILDREN_HEIGHT_PAD));
        createComponents();
    }

    private void createComponents() {
        add(transformationPanel);
        add(projectionPanel);
        add(examplePanel);
        add(extraPanel);
    }

    private void createExtraPanelComponents() {

    }

    public void setDoTransformationListener(ActionListener doTransformationListener) {
        this.transformationPanel.setDoItListener(doTransformationListener);
    }

    public Transformation getTransformation() {
        return transformationPanel.getTransformation();
    }

    public void setDoProjectionListener(ActionListener doProjectionListener) {
        this.projectionPanel.setDoItListener(doProjectionListener);
    }

    public Projection getSelectedProjection() {
        return projectionPanel.getSelectedProjection();
    }

    public void setDoExampleListener(ActionListener doExampleListener) {
        this.examplePanel.setDoItListener(doExampleListener);
    }

    public Polyhedron getExamplePolyhedron() {
        return examplePanel.getPolyhedron();
    }

    public void setUseNewPolyhedronListener(ActionListener useNewPolyhedronListener) {
        extraPanel.setUseNewPolyhedronListener(useNewPolyhedronListener);
    }

    public void setZoomInListener(ActionListener zoomInListener) {
        extraPanel.setZoomInListener(zoomInListener);
    }

    public void setZoomOutListener(ActionListener zoomOutListener) {
        extraPanel.setZoomOutListener(zoomOutListener);
    }

    public void setMoveRightListener(ActionListener moveRightListener) {
        extraPanel.setMoveRightListener(moveRightListener);
    }

    public void setMoveLeftListener(ActionListener moveLeftListener) {
        extraPanel.setMoveLeftListener(moveLeftListener);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        transformationPanel.setEnabled(enabled);
        projectionPanel.setEnabled(enabled);
        examplePanel.setEnabled(enabled);
        extraPanel.setEnabled(enabled);
    }
}
