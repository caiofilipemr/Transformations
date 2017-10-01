package graphics.operation;

import gc.Polyhedron;
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

    public OperationPanel(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        transformationPanel.setPreferredSize(new Dimension(CHILDREN_WIDTH, getPreferredSize().height - CHILDREN_HEIGHT_PAD));
        projectionPanel.setPreferredSize(new Dimension(CHILDREN_WIDTH, getPreferredSize().height - CHILDREN_HEIGHT_PAD));
        examplePanel.setPreferredSize(new Dimension(CHILDREN_WIDTH, getPreferredSize().height - CHILDREN_HEIGHT_PAD));
        createComponents();
    }

    private void createComponents() {
        add(transformationPanel);
        add(projectionPanel);
        add(examplePanel);
    }

    public void setDoTransformationListener(ActionListener doTransformationListener) {
        this.transformationPanel.setDoItListener(doTransformationListener);
    }

    public Transformation getTransformation() {
        return transformationPanel.getTransformation();
    }

    public void setDoExampleListener(ActionListener doExampleListener) {
        this.examplePanel.setDoItListener(doExampleListener);
    }

    public Polyhedron getExamplePolyhedron() {
        return examplePanel.getPolyhedron();
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        transformationPanel.setEnabled(enabled);
        projectionPanel.setEnabled(enabled);
        examplePanel.setEnabled(enabled);
    }
}
