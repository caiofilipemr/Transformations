package graphics;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PointPanel extends JPanel {
    private final NumberField xField = new NumberField();
    private final NumberField yField = new NumberField();
    private final NumberField zField = new NumberField();
    private final JButton saveButton = new JButton();

    public PointPanel() {
        super();
        createComponents();
        setBorder(BorderFactory.createTitledBorder("Create point"));
    }

    private void createComponents() {
        GridBagLayout gridLayout = new GridBagLayout();
        setLayout(gridLayout);

        addPointInputLine("X: ", xField, 0);
        addPointInputLine("Y: ", yField, 1);
        addPointInputLine("Z: ", zField, 2);
        addPointSaveButton(3);
    }

    private void addPointInputLine(String label, NumberField numberField, int row) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = row;
        c.gridwidth = 1;
        c.weightx = 0.1;
        add(new JLabel(label, SwingConstants.RIGHT), c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = row;
        c.gridwidth = 1;
        c.weightx = 0.9;
        add(numberField, c);
    }

    private void addPointSaveButton(int row) {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = row;
        c.gridwidth = 2;
        c.weightx = 1;
        c.insets = new Insets(10, 10, 10, 10);

        saveButton.setText("Save");
        add(saveButton, c);
    }

    public gc.Point getInputtedPoint() {
        return new gc.Point(xField.getDoubleValue(),
                yField.getDoubleValue(),
                zField.getDoubleValue());
    }

    public void clearPointFields() {
        xField.setText("");
        yField.setText("");
        zField.setText("");
    }

    public void addSavePointListener(ActionListener savePointListener) {
        saveButton.addActionListener(savePointListener);
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
