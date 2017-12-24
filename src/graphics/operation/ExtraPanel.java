package graphics.operation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

class ExtraPanel extends JPanel {
    public static final String PANEL_TITLE = "Extras";
    private static final String USE_NEW_POLYHEDRON_BUTTON_TEXT = "Use new polyhedron";

    private final JButton useNewPolyhedronButton = new JButton();
    private final JButton zoomInButton = new JButton("+");
    private final JButton zoomOutButton = new JButton("-");
    private final JButton moveRightButton = new JButton("->");
    private final JButton moveLeftButton = new JButton("<-");

    public ExtraPanel() {
        super();
        GridLayout gridLayout = new GridLayout(3, 1, 10, 10);
        setLayout(gridLayout);

        createComponents();
        setBorder(BorderFactory.createTitledBorder(PANEL_TITLE));
    }

    private void createComponents() {
        useNewPolyhedronButton.setText(USE_NEW_POLYHEDRON_BUTTON_TEXT);
        add(useNewPolyhedronButton, BorderLayout.PAGE_END);

        add(addZoomFields());
        add(addMoveHorizontalFields());
    }

    private JPanel addZoomFields() {
        GridLayout gridLayout = new GridLayout(1, 3, 10, 10);
        JPanel jPanel = new JPanel(gridLayout);
        jPanel.add(zoomInButton);
        JLabel label = new JLabel("Zoom");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        jPanel.add(label);
        jPanel.add(zoomOutButton);
        return jPanel;
    }

    private Component addMoveHorizontalFields() {
        GridLayout gridLayout = new GridLayout(1, 3, 10, 10);
        JPanel jPanel = new JPanel(gridLayout);
        jPanel.add(moveLeftButton);
        JLabel label = new JLabel("Move");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        jPanel.add(label);
        jPanel.add(moveRightButton);
        return jPanel;
    }

    public void setUseNewPolyhedronListener(ActionListener useNewPolyhedronListener) {
        useNewPolyhedronButton.addActionListener(useNewPolyhedronListener);
    }

    public void setZoomInListener(ActionListener zoomInListener) {
        zoomInButton.addActionListener(zoomInListener);
    }

    public void setZoomOutListener(ActionListener zoomOutListener) {
        zoomOutButton.addActionListener(zoomOutListener);
    }

    public void setMoveRightListener(ActionListener moveRightListener) {
        moveRightButton.addActionListener(moveRightListener);
    }

    public void setMoveLeftListener(ActionListener moveLeftListener) {
        moveLeftButton.addActionListener(moveLeftListener);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        useNewPolyhedronButton.setEnabled(enabled);
    }
}