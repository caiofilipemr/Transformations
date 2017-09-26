package graphics;

import javax.swing.*;
import java.awt.*;

public class MouseCoordinatePanel extends JPanel {
    private final JLabel xLabel = new JLabel();
    private final JLabel yLabel = new JLabel();
    private final JLabel zLabel = new JLabel();

    public MouseCoordinatePanel() {
        super();
        createComponents();
        setBorder(BorderFactory.createTitledBorder("Mouse"));
    }

    private void createComponents() {
        GridLayout gridLayout = new GridLayout(3, 2);
        setLayout(gridLayout);
        add(new JLabel("X:"));
        add(xLabel);
        add(new JLabel("Y:"));
        add(yLabel);
        add(new JLabel("Z:"));
        add(zLabel);
    }

    public void setXLabel(String label) {
        this.xLabel.setText(label);
    }

    public void setYLabel(String label) {
        this.yLabel.setText(label);
    }

    public void setZLabel(String label) {
        this.zLabel.setText(label);
    }
}
