package graphics.operation;

import javax.swing.*;

public class ProjectionPanel extends JPanel {

    public static final String PANEL_TITLE = "Projections";

    public ProjectionPanel() {
        super();
        createComponents();
        setBorder(BorderFactory.createTitledBorder(PANEL_TITLE));
    }

    private void createComponents() {

    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
    }
}
