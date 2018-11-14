public class Plane extends Entity {

    Vec3D point, normalVector;
    /* equation of aplane, which is a set of point P:
    (P - P0) * n = 0
    P0: a point on plane
    n: the normal vector
    */
    Plane(Vec3D pointOnPlane, Vec3D normalVector) {
        this.normalVector = normalVector;
        this.point = pointOnPlane;
    }
    
    @Override
    Vec3D intersect(Vec3D ray) {
        float ds = ray.len();   // distance

        // unit vector - direction of the line
        Vec3D l = new Vec3D(ray.x / ds, ray.y / ds, ray.z / ds);

        // Algebraic form: https://en.wikipedia.org/wiki/Line–plane_intersection#Parametric_form
        // d = (p0 - l0) * n / (l * n)
        // lo: ray, p0: point(pointOnPlane), n: normal vector, l : direction vector (unit vector)
        float top = point.sub(ray).mul(this.normalVector);      // (p0 - l0) * n
        float bottom = l.mul(this.normalVector);                // l * n

        return (bottom != 0) ? l.mul(top / bottom).add(ray) : null;
    }
}