package graphics;

import javax.swing.*;
import java.awt.*;

public class OperationPanel extends JPanel {
    private final TransformationPanel transformationPanel = new TransformationPanel();
    private final ProjectionPanel projectionPanel = new ProjectionPanel();

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
}
