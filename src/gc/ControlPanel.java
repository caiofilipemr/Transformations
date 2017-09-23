package gc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel {
    private final ActionListener savePointListener;

    private final NumberField xField = new NumberField();
    private final NumberField yField = new NumberField();
    private final NumberField zField = new NumberField();

    public ControlPanel(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        this.savePointListener = null;
        createComponents();
    }

    public ControlPanel(int width, int height, ActionListener savePointListener) {
        setPreferredSize(new Dimension(width, height));
        this.savePointListener = savePointListener;
        createComponents();
    }

    private void createComponents() {
        add(createPointPanel(), BorderLayout.PAGE_START);
    }

    private JPanel createPointPanel() {
        JPanel panel = new JPanel();
        GridBagLayout gridLayout = new GridBagLayout();
        panel.setLayout(gridLayout);
        panel.setPreferredSize(new Dimension(getPreferredSize().width, 105));

        addPointInputLine(panel, "X: ", xField, 0);
        addPointInputLine(panel, "Y: ", yField, 1);
        addPointInputLine(panel, "Z: ", zField, 2);
        addPointSaveButton(panel, 3);

        return panel;
    }

    private void addPointInputLine(JPanel panel, String label, NumberField numberField, int row) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = row;
        c.gridwidth = 1;
        c.weightx = 0.1;
        panel.add(new JLabel(label, SwingConstants.RIGHT), c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = row;
        c.gridwidth = 1;
        c.weightx = 0.9;
        panel.add(numberField, c);
    }

    private void addPointSaveButton(JPanel panel, int row) {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = row;
        c.gridwidth = 2;
        c.weightx = 1;
        c.insets = new Insets(10, 10, 10, 10);

        JButton jButton = new JButton("Save");
        jButton.addActionListener(savePointListener);
        panel.add(jButton, c);
    }

    public Point getInputedPoint() {
        return new Point(Double.valueOf(xField.getText()),
                Double.valueOf(yField.getText()),
                Double.valueOf(zField.getText()));
    }

    public void clearPointFields() {
        xField.setText("");
        yField.setText("");
        zField.setText("");
    }

    public NumberField getXField() {
        return xField;
    }

    public NumberField getYField() {
        return yField;
    }

    public NumberField getZField() {
        return zField;
    }
}
