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

    Vec3D intersect(Vec3D ray) {
        float ds = ray.len();									// distance
        Vec3D l = new Vec3D(ray.x / ds, ray.y / ds, ray.z / ds);// unit vector  - direction of the line

        // Algebraic form: https://en.wikipedia.org/wiki/Line–plane_intersection#Parametric_form
        // d = (p0 - l0) * n / l * n
        float top = point.sub(ray).mul(this.normalVector);      // (p0 - l0) * n    -- lo: ray, p0: point(pointOnPlane)
        float bottom = l.mul(this.normalVector);                // l * n
        
        if (bottom != 0) {                                      // intersect in a single point
            float d = top / bottom;
            return l.mul(d).sub(ray);                           // the point of intersection is given by d * l + l0
        } else {                                                // parallel
            if (top == 0) {                                     // the vector is contained on the plane
                return null;
            } else {                                            // just parallel
                return null;
            }
        }
    }
}