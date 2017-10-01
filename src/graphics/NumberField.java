package graphics;

import javax.swing.*;
import java.awt.*;

public class NumberField extends JTextField {
    public NumberField() {
        super();
        setMaximumSize(new Dimension(getMaximumSize().getSize().width, 25));
    }

    public double getDoubleValue() {
        return Double.valueOf(getText());
    }

    public double getDoubleValueOr(double def) {
        return getText() == null || getText().isEmpty() ? def : getDoubleValue();
    }
}