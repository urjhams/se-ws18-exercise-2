class Vec3D {

	public float x, y, z;
	Vec3D( float x, float y, float z ) { 
		this.x = x; 
		this.y = y; 
		this.z = z; 
	}

	Vec3D( String _s ) {
		String[] items = _s.split("\\s+");
		x = Float.parseFloat(items[1]);
		y = Float.parseFloat(items[2]);
		z = Float.parseFloat(items[3]);
	}

	public String toString() { 
		return "[ " + x + " " + y + " " + z + " ]"; 
	}
	
	Vec3D add( Vec3D other ) { 
		return new Vec3D( x + other.x, y + other.y, z + other.z ); 
	}
	Vec3D sub( Vec3D other ) { 
		return new Vec3D( x - other.x, y - other.y, z - other.z ); 
	}

	Vec3D mul( float other ) { 
		return new Vec3D( x * other, y * other, z * other ); 
	}
	
	float mul( Vec3D other ) { 
		return x * other.x + y * other.y + z * other.z; 
	}
	
	float len () {
		return (float) Math.sqrt((x * x) + (y * y) + (z * z));
	}
	
	Vec3D cross (Vec3D other) {
		float resultX = y * other.z - z * other.y;
		float resultY = z * other.x - x * other.z;
		float resultZ = x * other.y - y * other.x;
		return new Vec3D(resultX, resultY, resultZ);
	}


}
