package graphics.operation;

import graphics.NumberField;
import operation.projection.Projection;
import operation.projection.ProjectionFactory;
import operation.projection.ProjectionOperation;

import gc.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ProjectionPanel extends JPanel {
    private static final String PANEL_TITLE = "Projections";
    private static final String DO_IT_BUTTON_TEXT = "Do it!";

    private final JComboBox<ProjectionOperation> comboBoxOperations = new JComboBox<>();
    private final JLabel label1 = new JLabel("X: ");
    private final NumberField numberField1 = new NumberField();
    private final JLabel label2 = new JLabel("Y: ");
    private final NumberField numberField2 = new NumberField();
    private final JLabel label3 = new JLabel("Z: ");
    private final NumberField numberField3 = new NumberField();
    private final JButton doItButton = new JButton();

    public ProjectionPanel() {
        super();
        GridLayout gridLayout = new GridLayout(3, 1, 0, 0);
        setLayout(gridLayout);

        createComponents();
        setBorder(BorderFactory.createTitledBorder(PANEL_TITLE));
    }

    private void createComponents() {
        fillComboBoxOperations();
        add(comboBoxOperations);

        add(addBasicFields());

        doItButton.setText(DO_IT_BUTTON_TEXT);
        add(doItButton);
    }

    private void fillComboBoxOperations() {
        for (ProjectionOperation item : ProjectionOperation.values()) {
            comboBoxOperations.addItem(item);
        }
    }

    private Component addBasicFields() {
        GridLayout gridLayout = new GridLayout(1, 6, 10, 10);
        JPanel jPanel = new JPanel(gridLayout);
        jPanel.add(label1);
        jPanel.add(numberField1);
        jPanel.add(label2);
        jPanel.add(numberField2);
        jPanel.add(label3);
        jPanel.add(numberField3);
        return jPanel;
    }

    public void setDoItListener(ActionListener doItListener) {
        doItButton.addActionListener(doItListener);
    }

    public Projection getSelectedProjection() {
        return ProjectionFactory.getProjection((ProjectionOperation) comboBoxOperations.getSelectedItem(),
                new Point(numberField1.getDoubleValueOr(0), numberField2.getDoubleValueOr(0), numberField3.getDoubleValueOr(0)));
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        doItButton.setEnabled(enabled);
    }
}
