package graphics.canvas.transition;

import gc.Point;
import gc.Polyhedron;

import java.util.Iterator;

public class InOrderTransition implements Transition {
    @Override
    public boolean doTransition(Polyhedron transitionPolyhedron, Polyhedron targetPolyhedron, double transitionFactor) {
        boolean transitionOver = true;
        transitionOver = doInOrderXTransition(transitionPolyhedron, targetPolyhedron, transitionOver, transitionFactor);
        if (!transitionOver) return false;

        transitionOver = doInOrderTransitionY(transitionPolyhedron, targetPolyhedron, transitionOver, transitionFactor);
        if (!transitionOver) return false;

        transitionOver = doInOrderTransitionZ(transitionPolyhedron, targetPolyhedron, transitionOver, transitionFactor);
        return transitionOver;
    }

    private boolean doInOrderXTransition(Polyhedron transitionPolyhedron, Polyhedron targetPolyhedron,
                                         boolean transitionOver, double transitionFactor) {
        Iterator<Point> it = targetPolyhedron.getPoints().iterator();
        Point target;
        for (Point point : transitionPolyhedron.getPoints()) {
            target = it.next();
            if (point.x != target.x) {
                point.x = Transition.calculateNewCoord(point.x, target.x, transitionFactor);
                transitionOver = false;
            }
        }
        return transitionOver;
    }

    private boolean doInOrderTransitionY(Polyhedron transitionPolyhedron, Polyhedron targetPolyhedron,
                                         boolean transitionOver, double transitionFactor) {
        Point target;
        Iterator<Point> it = targetPolyhedron.getPoints().iterator();
        for (Point point : transitionPolyhedron.getPoints()) {
            target = it.next();
            if (point.y != target.y) {
                point.y = Transition.calculateNewCoord(point.y, target.y, transitionFactor);
                transitionOver = false;
            }
        }
        return transitionOver;
    }

    private boolean doInOrderTransitionZ(Polyhedron transitionPolyhedron, Polyhedron targetPolyhedron,
                                         boolean transitionOver, double transitionFactor) {
        Point target;
        Iterator<Point> it = targetPolyhedron.getPoints().iterator();
        for (Point point : transitionPolyhedron.getPoints()) {
            target = it.next();
            if (point.z != target.z) {
                point.z = Transition.calculateNewCoord(point.z, target.z, transitionFactor);
                transitionOver = false;
            }
        }
        return transitionOver;
    }
}
