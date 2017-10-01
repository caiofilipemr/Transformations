package graphics.operation;

import operation.projection.Projection;
import operation.projection.ProjectionFactory;
import operation.projection.ProjectionOperation;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ProjectionPanel extends JPanel {
    private static final String PANEL_TITLE = "Projections";
    private static final String DO_IT_BUTTON_TEXT = "Do it!";

    private final JComboBox<ProjectionOperation> comboBoxOperations = new JComboBox<>();
    private final JButton doItButton = new JButton();

    public ProjectionPanel() {
        super();
        createComponents();
        setBorder(BorderFactory.createTitledBorder(PANEL_TITLE));
    }

    private void createComponents() {
        fillComboBoxOperations();
        add(comboBoxOperations);

        doItButton.setText(DO_IT_BUTTON_TEXT);
        add(doItButton);
    }

    private void fillComboBoxOperations() {
        for (ProjectionOperation item : ProjectionOperation.values()) {
            comboBoxOperations.addItem(item);
        }
    }

    public void setDoItListener(ActionListener doItListener) {
        doItButton.addActionListener(doItListener);
    }

    public Projection getSelectedProjection() {
        return ProjectionFactory.getProjection((ProjectionOperation) comboBoxOperations.getSelectedItem());
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        doItButton.setEnabled(enabled);
    }
}
