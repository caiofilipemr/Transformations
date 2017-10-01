package gc;

public class PolyhedronFactory {
    public static Polyhedron createPolyhedron(PolyhedronType polyhedronType, Point initialPoint) {
        switch (polyhedronType) {
            case CUBE:
                return createCube(initialPoint);
            case TETRAHEDRON:
                return createTetrahedron(initialPoint);
        }
        return null;
    }

    private static Polyhedron createCube(Point initialPoint) {
        double size = 5;
        Polyhedron cube = new Polyhedron();
        cube.addPoint(initialPoint);
        cube.addPoint(new Point(initialPoint.x+size, initialPoint.y, initialPoint.z));
        cube.addPoint(new Point(initialPoint.x, initialPoint.y+size, initialPoint.z));
        cube.addPoint(new Point(initialPoint.x, initialPoint.y, initialPoint.z+size));
        cube.addEdge(cube.getPoints().get(0), cube.getPoints().get(1));
        cube.addEdge(cube.getPoints().get(0), cube.getPoints().get(2));
        cube.addEdge(cube.getPoints().get(0), cube.getPoints().get(3));

        initialPoint = new Point(initialPoint.x+size, initialPoint.y+size, initialPoint.z+size);
        cube.addPoint(initialPoint);
        cube.addPoint(new Point(initialPoint.x-size, initialPoint.y, initialPoint.z));
        cube.addPoint(new Point(initialPoint.x, initialPoint.y-size, initialPoint.z));
        cube.addPoint(new Point(initialPoint.x, initialPoint.y, initialPoint.z-size));
        cube.addEdge(cube.getPoints().get(4), cube.getPoints().get(5));
        cube.addEdge(cube.getPoints().get(4), cube.getPoints().get(6));
        cube.addEdge(cube.getPoints().get(4), cube.getPoints().get(7));

        cube.addEdge(cube.getPoints().get(1), cube.getPoints().get(6));
        cube.addEdge(cube.getPoints().get(1), cube.getPoints().get(7));
        cube.addEdge(cube.getPoints().get(2), cube.getPoints().get(5));
        cube.addEdge(cube.getPoints().get(2), cube.getPoints().get(7));
        cube.addEdge(cube.getPoints().get(3), cube.getPoints().get(5));
        cube.addEdge(cube.getPoints().get(3), cube.getPoints().get(6));
        return cube;
    }

    private static Polyhedron createTetrahedron(Point initialPoint) {
        double size = 2;
        Polyhedron cube = new Polyhedron();
        cube.addPoint(initialPoint);
        cube.addPoint(new Point(initialPoint.x+size, initialPoint.y, initialPoint.z));
        cube.addPoint(new Point(initialPoint.x, initialPoint.y, initialPoint.z+size));
        cube.addPoint(new Point(initialPoint.x+size, initialPoint.y, initialPoint.z+size));
        cube.addPoint(new Point(initialPoint.x+size/2, initialPoint.y+size/2, initialPoint.z+size/2));
        cube.addEdge(cube.getPoints().get(0), cube.getPoints().get(1));
        cube.addEdge(cube.getPoints().get(0), cube.getPoints().get(2));
        cube.addEdge(cube.getPoints().get(3), cube.getPoints().get(1));
        cube.addEdge(cube.getPoints().get(3), cube.getPoints().get(2));
        cube.addEdge(cube.getPoints().get(4), cube.getPoints().get(0));
        cube.addEdge(cube.getPoints().get(4), cube.getPoints().get(1));
        cube.addEdge(cube.getPoints().get(4), cube.getPoints().get(2));
        cube.addEdge(cube.getPoints().get(4), cube.getPoints().get(3));

        return cube;
    }
}
