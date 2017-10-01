package graphics;

import operation.transformation.Transformation;
import operation.transformation.TransformationFactory;
import operation.transformation.TransformationOperation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TransformationPanel extends JPanel {
    private static final String PANEL_TITLE = "Transformations";
    private static final String DO_IT_BUTTON_TEXT = "Do it!";

    private final JComboBox<TransformationOperation> comboBoxOperations = new JComboBox<>();
    private final JLabel label1 = new JLabel();
    private final NumberField numberField1 = new NumberField();
    private final JLabel label2 = new JLabel();
    private final NumberField numberField2 = new NumberField();
    private final JLabel label3 = new JLabel();
    private final NumberField numberField3 = new NumberField();
    private final JLabel label4 = new JLabel();
    private final NumberField numberField4 = new NumberField();
    private final JLabel label5 = new JLabel();
    private final NumberField numberField5 = new NumberField();
    private final JLabel label6 = new JLabel();
    private final NumberField numberField6 = new NumberField();
    private final JButton doItButton = new JButton();

    public TransformationPanel() {
        super();
        GridLayout gridLayout = new GridLayout(4, 1, 10, 10);
        setLayout(gridLayout);
        setBorder(BorderFactory.createTitledBorder(PANEL_TITLE));

        createComponents();
    }

    private void createComponents() {
        fillComboBoxOperations();

        add(comboBoxOperations);
        comboBoxOperations.addItemListener(e -> comboBoxChanged());

        add(addBasicFields());
        add(addExtraFields());

        doItButton.setText(DO_IT_BUTTON_TEXT);
        add(doItButton);

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

    private Component addExtraFields() {
        GridLayout gridLayout = new GridLayout(1, 6, 10, 10);
        JPanel jPanel = new JPanel(gridLayout);
        jPanel.add(label4);
        jPanel.add(numberField4);
        jPanel.add(label5);
        jPanel.add(numberField5);
        jPanel.add(label6);
        jPanel.add(numberField6);
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
        label1.setText("X°: ");
        label2.setText("Y°: ");
        label3.setText("Z°: ");
    }

    private void setLabelTextForScale() {
        label1.setText("Sx: ");
        label2.setText("Sy: ");
        label3.setText("Sz: ");
    }

    private void setLabelTextForShear() {
        label1.setText("XY: ");
        label2.setText("XZ: ");
        label3.setText("YX: ");
        label4.setText("YZ: ");
        label5.setText("ZX: ");
        label6.setText("ZY: ");
    }

    private void controlFieldsVisibility() {
        boolean showExtraFields = comboBoxOperations.getSelectedItem().equals(TransformationOperation.SHEAR);
        label4.setVisible(showExtraFields);
        label5.setVisible(showExtraFields);
        label6.setVisible(showExtraFields);
        numberField4.setVisible(showExtraFields);
        numberField5.setVisible(showExtraFields);
        numberField6.setVisible(showExtraFields);
    }

    public void setDoItListener(ActionListener doItListener) {
        doItButton.addActionListener(doItListener);
    }

    public Transformation getTransformation() {
        return TransformationFactory.getTransformation((TransformationOperation) comboBoxOperations.getSelectedItem(),
                numberField1.getDoubleValueOr(0), numberField2.getDoubleValueOr(0), numberField3.getDoubleValueOr(0),
                numberField4.getDoubleValueOr(0), numberField5.getDoubleValueOr(0), numberField6.getDoubleValueOr(0));
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        doItButton.setEnabled(enabled);
    }
}
