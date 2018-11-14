
public class Sphere extends Entity {
	// x0, y0, z0 are from center point of Sphere
	float x0,y0,z0,rad;
	
	/* equation of a sphere: https://en.wikipedia.org/wiki/Sphere
	(x - x0)^2 + (y - y0)^2 + (z - z0)^2 = rad^2
	*/
	Sphere(Vec3D centerPoint, float radius) {
		x0 = centerPoint.x;
		y0 = centerPoint.y;
		z0 = centerPoint.z;
		rad = radius;
	}

	@Override
	Vec3D intersect(Vec3D ray) {
		float originX = 0;
		float originY = 0;
		float originZ = 0;
		Vec3D start = new Vec3D(originX, originY, originZ);		// the start point of 3D vector
		float d = ray.len();									// distance
		Vec3D u = new Vec3D(ray.x / d, ray.y / d, ray.z / d);	// unit vector

		// Using the form of Quadratic fomular
		// a*x^2 + b*x + c = 0
		// with delta = b^2 - 4 * a * c
		// http://www.ambrsoft.com/TrigoCalc/Sphere/SpherLineIntersection_.htm
		float a = (float) (Math.pow(
			ray.x - originX, 2) + 
			Math.pow(ray.y - originY, 2) + 
			Math.pow(ray.z - originZ, 2)
			);

		float b =  (float) (2 * (
			(ray.x - originX) * (originX - this.x0) + 
			(ray.y - originY) * (originY - this.y0) + 
			(ray.z - originZ) * (originZ - this.z0)
			));

		float c = (float) (
			Math.pow(this.x0, 2) + 
			Math.pow(this.y0, 2) + 
			Math.pow(this.z0, 2) + 
			Math.pow(originX, 2) + 
			Math.pow(originY, 2) + 
			Math.pow(originZ, 2) + 
			2 * (
				this.x0 * originX + 
				this.y0 * originY + 
				this.z0 * originZ) - 
			Math.pow(this.rad, 2)
			);

		float delta = (float) (Math.pow(b, 2) - 4 * a * c);
		if (delta == 0) {
			if (a <= 0) {	// not a line
				return null;
			}

			float t = - b / (2 * a);

			// Parametric equation of the form L = P + tU
        	// where 'L' is the intersection point, 'P' is the point on the line and
			// U is the unit vector (end point - origin)			 
			return start.add(u.mul(t));

		} else if (delta > 0) {

			float t1 = (float) ((-b + Math.sqrt(delta)) / (2 * a));
			float t2 = (float) ((-b - Math.sqrt(delta)) / (2 * a));

			Vec3D result1 = start.add(u.mul(t1));
			Vec3D result2 = start.add(u.mul(t2));

			// which one is shoter means it stays closer to the origin
			return (result1.len() < result2.len()) ? result1 : result2;

		} else {
			return null;
		}
	}
}
