package graphics.canvas.transition;

import gc.DimensionName;
import gc.Point;
import gc.Polyhedron;

import java.util.Iterator;

public class FarthestPointTransition implements Transition {
    @Override
    public boolean doTransition(Polyhedron transitionPolyhedron, Polyhedron targetPolyhedron, double transitionFactor) {
        Point target;
        boolean transitionOver = true;
        Iterator<Point> it = targetPolyhedron.getPoints().iterator();
        for (Point point : transitionPolyhedron.getPoints()) {
            target = it.next();
            transitionOver = doPointTransitionIfNecessary(point, target, transitionOver, transitionFactor);
        }
        return transitionOver;
    }

    public boolean doPointTransitionIfNecessary(Point point, Point target, boolean transitionOver, double transitionFactor) {
        DimensionName dimensionName;
        dimensionName = calculateGreatestDistance(point, target);
        if (dimensionName != null) {
            switch (dimensionName) {
                case X:
                    point.x = Transition.calculateNewCoord(point.x, target.x, transitionFactor);
                    transitionOver = false;
                    break;
                case Y:
                    point.y = Transition.calculateNewCoord(point.y, target.y, transitionFactor);
                    transitionOver = false;
                    break;
                case Z:
                    point.z = Transition.calculateNewCoord(point.z, target.z, transitionFactor);
                    transitionOver = false;
                    break;
            }
        }
        return transitionOver;
    }

    private DimensionName calculateGreatestDistance(Point point, Point target) {
        double x = Math.abs(point.x - target.x);
        double y = Math.abs(point.y - target.y);
        double z = Math.abs(point.z - target.z);

        if (x == 0 && y == 0 && z == 0)
            return null;

        DimensionName greatest = DimensionName.X;
        if (y > x) {
            if (y > z) {
                greatest = DimensionName.Y;
            } else {
                greatest = DimensionName.Z;
            }
        } else if (z > x) {
            greatest = DimensionName.Z;
        }
        return greatest;
    }
}
