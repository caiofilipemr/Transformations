package gc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class PointListPanel extends JPanel {
    private static final String PANEL_TITLE = "Points";

    private final JList<Point> pointList = new JList<>();
    private final JScrollPane scrollPane = new JScrollPane(pointList);
    private final JButton deleteButton = new JButton();

    private PointListModel pointListModel;

    public PointListPanel() {
        super();
        createComponents();
        setBorder(BorderFactory.createTitledBorder(PANEL_TITLE));
    }

    private void createComponents() {
        pointList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        pointList.setLayoutOrientation(JList.VERTICAL);
        add(scrollPane);

        deleteButton.setText("Delete");
        add(deleteButton);
    }

    public void addDeletePointListener(ActionListener deletePointListener) {
        deleteButton.addActionListener(deletePointListener);
    }

    @Override
    public void setPreferredSize(Dimension dimension) {
        super.setPreferredSize(dimension);
        scrollPane.setPreferredSize(new Dimension(dimension.width - 20, dimension.height - 65));
    }

    public void pointListChanged() {
        pointListModel.changed();
    }

    public void setModel(List<Point> points) {
        pointListModel = new PointListModel(points);
        pointList.setModel(pointListModel);
    }

    public int getSelectedPointIndex() {
        return pointList.getSelectedIndex();
    }

    private class PointListModel extends AbstractListModel<Point> {
        private final List<Point> points;

        PointListModel(List<Point> points) {
            this.points = points;
        }

        @Override
        public int getSize() {
            return points.size();
        }

        @Override
        public Point getElementAt(int index) {
            return points.get(index);
        }

        public void changed() {
            fireContentsChanged(this, 0, points.size());
        }
    }
}