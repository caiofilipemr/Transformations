package graphics.canvas.transition;

import gc.Polyhedron;

public interface Transition {
    boolean doTransition(Polyhedron transitionPolyhedron, Polyhedron targetPolyhedron, double transitionFactor);

    static double calculateNewCoord(double coord, double targetCoord, double transitionFactor) {
        if (transitionFactor > Math.abs(coord - targetCoord))
            return targetCoord;

        return coord < targetCoord ? coord + transitionFactor : coord - transitionFactor;
    }
}
