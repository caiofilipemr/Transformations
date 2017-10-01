package graphics.canvas.transition;

import gc.Point;
import gc.Polyhedron;

import java.util.Iterator;

public class AllPointsTransition implements Transition {
    @Override
    public boolean doTransition(Polyhedron transitionPolyhedron, Polyhedron targetPolyhedron, double transitionFactor) {
        boolean transitionOver = true;

        Iterator<Point> it = targetPolyhedron.getPoints().iterator();
        Point target;
        for (Point point : transitionPolyhedron.getPoints()) {
            target = it.next();
            if (point.x != target.x) {
                point.x = Transition.calculateNewCoord(point.x, target.x, transitionFactor);
                transitionOver = false;
            }
            if (point.y != target.y) {
                point.y = Transition.calculateNewCoord(point.y, target.y, transitionFactor);
                transitionOver = false;
            }
            if (point.z != target.z) {
                point.z = Transition.calculateNewCoord(point.z, target.z, transitionFactor);
                transitionOver = false;
            }
        }

        return transitionOver;
    }
}
