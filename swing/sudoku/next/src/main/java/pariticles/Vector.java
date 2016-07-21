/**
 * Project Sudoku Next!
 * @author pRobE
 * @last update 2010-1-6
 */
package pariticles;

/**
 * Vectors类 
 * 向量类
 * @version 0.1
 */
public class Vector {
	
		public static Vector zero = new Vector(0.0f, 0.0f, 0.0f);

		private final float x;

		private final float y;

		private final float z;

		public Vector(float inX, float inY, float inZ) {
			this.x = inX;
			this.y = inY;
			this.z = inZ;
		}

		public Vector add(Vector vector) {
			if (vector == null)
				return this;

			return new Vector(x + vector.getX(), y + vector.getY(), z
					+ vector.getZ());
		}

		public Vector multiply(float i) {

			return new Vector(x * i, y * i, z * i);
		}

		public float getX() {
			return x;
		}

		public float getY() {
			return y;
		}

		public float getZ() {
			return z;
		}
	
}
