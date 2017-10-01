package graphics.control;

import graphics.canvas.transition.Transition;
import graphics.canvas.transition.TransitionFactory;
import graphics.canvas.transition.TransitionType;

import javax.swing.*;
import java.awt.*;

public class TransitionPanel extends JPanel {
    private static final String PANEL_TITLE = "Transition";

    private final JSpinner timeSpinner = new JSpinner();
    private final JComboBox<TransitionType> comboBoxTransition = new JComboBox<>();

    TransitionPanel() {
        super();
        GridLayout gridLayout = new GridLayout(4, 1, 10, 10);
        setLayout(gridLayout);
        setBorder(BorderFactory.createTitledBorder(PANEL_TITLE));

        createComponents();
    }

    private void createComponents() {
        add(new JLabel("Transition time:"));
        timeSpinner.setModel(new SpinnerNumberModel(1, 1, 100, 1));
        timeSpinner.setValue(1);
        add(timeSpinner);

        add(new JLabel("Transition type:"));
        fillComboBoxTransition();
        add(comboBoxTransition);
    }

    private void fillComboBoxTransition() {
        for (TransitionType transitionType : TransitionType.values()) {
            comboBoxTransition.addItem(transitionType);
        }
    }

    public long getTransitionTime() {
        return (Integer) timeSpinner.getValue() * 10;
    }

    public Transition getSelectedTransition() {
        return TransitionFactory.createTransition((TransitionType) comboBoxTransition.getSelectedItem());
    }
}
