package graphics;

import operation.transformation.Transformation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class OperationPanel extends JPanel {
    private final TransformationPanel transformationPanel = new TransformationPanel();
    private final ProjectionPanel projectionPanel = new ProjectionPanel();
    private Object transformation;

    public OperationPanel(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        transformationPanel.setPreferredSize(new Dimension(300, getPreferredSize().height - 10));
        projectionPanel.setPreferredSize(new Dimension(300, getPreferredSize().height - 10));
        createComponents();
    }

    private void createComponents() {
        add(transformationPanel);
        add(projectionPanel);
    }

    public void setDoTransformationListener(ActionListener doTransformationListener) {
        this.transformationPanel.setDoItListener(doTransformationListener);
    }

    public Transformation getTransformation() {
        return transformationPanel.getTransformation();
    }
}
