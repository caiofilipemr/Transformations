package graphics.operation;


import gc.Polyhedron;
import gc.PolyhedronFactory;
import gc.PolyhedronType;
import gc.Point;
import graphics.NumberField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ExamplePanel extends JPanel {
    public static final String PANEL_TITLE = "Examples";
    private static final String DO_IT_BUTTON_TEXT = "Do it!";

    private final JComboBox<PolyhedronType> comboBoxPolyhedron = new JComboBox<>();
    private final JLabel label1 = new JLabel("X: ");
    private final NumberField numberField1 = new NumberField();
    private final JLabel label2 = new JLabel("Y: ");
    private final NumberField numberField2 = new NumberField();
    private final JLabel label3 = new JLabel("Z: ");
    private final NumberField numberField3 = new NumberField();
    private final JButton doItButton = new JButton();

    public ExamplePanel() {
        super();
        GridLayout gridLayout = new GridLayout(3, 1, 10, 10);
        setLayout(gridLayout);

        createComponents();
        setBorder(BorderFactory.createTitledBorder(PANEL_TITLE));
    }

    private void createComponents() {
        fillComboBoxPolyhedron();
        add(comboBoxPolyhedron, BorderLayout.PAGE_START);

        add(addFields(), BorderLayout.CENTER);

        doItButton.setText(DO_IT_BUTTON_TEXT);
        add(doItButton, BorderLayout.PAGE_END);
    }

    private void fillComboBoxPolyhedron() {
        for (PolyhedronType item : PolyhedronType.values()) {
            comboBoxPolyhedron.addItem(item);
        }
    }

    private Component addFields() {
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

    public Polyhedron getPolyhedron() {
        return PolyhedronFactory.createPolyhedron((PolyhedronType) comboBoxPolyhedron.getSelectedItem(), getInputtedPoint());
    }

    public Point getInputtedPoint() {
        return new Point(numberField1.getDoubleValueOr(0), numberField2.getDoubleValueOr(0), numberField3.getDoubleValueOr(0));
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        doItButton.setEnabled(enabled);
    }
}