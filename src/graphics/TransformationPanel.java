package graphics;

import gc.TransformationOperation;

import javax.swing.*;
import java.awt.*;

public class TransformationPanel extends JPanel {
    public static final String PANEL_TITLE = "Transformations";
    private static final String DO_IT_BUTTON_TEXT = "Do it!";

    private final JComboBox<TransformationOperation> comboBoxOperations = new JComboBox<>();
    private final JLabel label1 = new JLabel();
    private final NumberField numberField1 = new NumberField();
    private final JLabel label2 = new JLabel();
    private final NumberField numberField2 = new NumberField();
    private final JLabel label3 = new JLabel();
    private final NumberField numberField3 = new NumberField();
    private final JButton doItButton = new JButton();

    public TransformationPanel() {
        super();
        GridLayout gridLayout = new GridLayout(3, 1, 10, 10);
        setLayout(gridLayout);
        setBorder(BorderFactory.createTitledBorder(PANEL_TITLE));

        createComponents();
    }

    private void createComponents() {
        fillComboBoxOperations();

        add(comboBoxOperations, BorderLayout.PAGE_START);
        comboBoxOperations.addItemListener(e -> comboBoxChanged());

        add(addFields(), BorderLayout.CENTER);

        doItButton.setText(DO_IT_BUTTON_TEXT);
        add(doItButton, BorderLayout.PAGE_END);

        comboBoxChanged();
    }

    private void fillComboBoxOperations() {
        for (TransformationOperation item : TransformationOperation.values()) {
            comboBoxOperations.addItem(item);
        }
    }

    private void comboBoxChanged() {
        controlLabelsText();
        controlFieldsVisibility();
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

    private void controlLabelsText() {
        switch ((TransformationOperation) comboBoxOperations.getSelectedItem()) {
            case TRANSLATION:
                setLabelTextForTranslation();
                break;
            case ROTATION:
                setLabelTextForRotation();
                break;
            case SCALE:
                setLabelTextForScale();
                break;
            case SHEAR:
                setLabelTextForShear();
                break;
        }
    }

    private void setLabelTextForTranslation() {
        label1.setText("X: ");
        label2.setText("Y: ");
        label3.setText("Z: ");
    }

    private void setLabelTextForRotation() {
        label1.setText("");
        label2.setText("Grau: ");
        label3.setText("");
    }

    private void setLabelTextForScale() {
        //TODO: implementar
    }

    private void setLabelTextForShear() {
        //TODO: implementar
    }

    private void controlFieldsVisibility() {
        //TODO: implementar
    }
}
