package library;

import java.io.*;
import java.util.*;

class Geometry {

    public static void main(String[] args) throws IOException {
        point p1 = new point(3, 4);
        point p2 = new point(10, 3);
        System.out.println(rotateByTheta(p2, 77).x + " " + rotateByTheta(p2, 77).y);

    }

//    distance between points p1 and p2
    static double distance(point a, point b) {
        return Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
    }

//    rotate p by theta degrees CCW w.r.t origin (0, 0)
    static point rotateByTheta(point p, double theta) {
        double rad = (theta * Math.PI) / (180.0);
        return new point(p.x * Math.cos(rad) - p.y * Math.sin(rad), p.x * Math.sin(rad) + p.y * Math.cos(rad));
    }

    
//    checking a point isinside a circle or border or outside
//    if points are in floating point convert it
    static int insideCircle(pointInt p, pointInt c, int r) {
        int dx = p.x - c.x, dy = p.y - c.y;                     // all integer version
        int lhs = dx * dx + dy * dy, rhs = r * r;                
        return lhs < rhs ? 0 : lhs == rhs ? 1 : 2;               //inside/border/outside
    }

}

class point {

    double x, y;

    public point() {
        this.x = this.y = 0;
    }

    public point(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

class pointInt {

    int x, y;

    public pointInt() {
        this.x = this.y = 0;
    }

    public pointInt(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
