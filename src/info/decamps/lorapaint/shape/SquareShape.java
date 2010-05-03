package info.decamps.lorapaint.shape;

import info.decamps.lorapaint.LoraShape;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.view.MotionEvent;

public class SquareShape implements LoraShape {
	private final static int X = 0;
	private final static int Y = 1;
	private final static int Z = 2;
	// Our vertices.
	private float vertices[][] = null;
	// = { [point][x,y,z]
	// -1.0f, 1.0f, 0.0f, // 0, Top Left
	// -1.0f, -1.0f, 0.0f, // 1, Bottom Left
	// 1.0f, -1.0f, 0.0f, // 2, Bottom Right
	// 1.0f, 1.0f, 0.0f, // 3, Top Right
	// };

	// The order we like to connect them.
	private short[] indices = { 0, 1, 2, 0, 2, 3 };

	// Our vertex buffer.
	private FloatBuffer vertexBuffer;

	// Our index buffer.
	private ShortBuffer indexBuffer;

	public SquareShape() {
		// short is 2 bytes, therefore we multiply the number if
		// vertices with 2.
		ByteBuffer ibb = ByteBuffer.allocateDirect(indices.length * 2);
		ibb.order(ByteOrder.nativeOrder());
		indexBuffer = ibb.asShortBuffer();
		indexBuffer.put(indices);
		indexBuffer.position(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * info.decamps.lorapaint.shape.LoraShape#draw(javax.microedition.khronos
	 * .opengles.GL10)
	 */
	public void draw(GL10 gl) {
		if (vertices==null) return;
		// Counter-clockwise winding.
		gl.glFrontFace(GL10.GL_CCW); // OpenGL docs
		// Enable face culling.
		gl.glEnable(GL10.GL_CULL_FACE); // OpenGL docs
		// What faces to remove with the face culling.
		gl.glCullFace(GL10.GL_BACK); // OpenGL docs

		// Enabled the vertices buffer for writing and to be used during
		// rendering.
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);// OpenGL docs.
		// Specifies the location and data format of an array of vertex
		// coordinates to use when rendering.
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, // OpenGL docs
				vertexBuffer);

		gl.glDrawElements(GL10.GL_TRIANGLES, indices.length,// OpenGL docs
				GL10.GL_UNSIGNED_SHORT, indexBuffer);

		// Disable the vertices buffer.
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY); // OpenGL docs
		// Disable face culling.
		gl.glDisable(GL10.GL_CULL_FACE); // OpenGL docs
	}

	public void setTopLeftCorner(float x, float y) {
		vertices[0][X]=x;
		vertices[0][Y]=y;
		vertices[1][X]=x;
		//vertices[2] unchanged
		vertices[3][Y]=y;
		updateVertexBuffer();
	}

	private void setBottomRightCorner(float x, float y) {
		// vertices[0] untocuhed
		vertices[1][Y] = y;
		vertices[2][X] = x;
		vertices[2][Y] = y;
		vertices[3][X] = x;
		updateVertexBuffer();
	}

	private void updateVertexBuffer() {
		// a float is 4 bytes, therefore we multiply the number if
		// vertices with 4.
		ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * vertices[0].length * 4);
		vbb.order(ByteOrder.nativeOrder());
		vertexBuffer = vbb.asFloatBuffer();
		//vertexBuffer.put(vertices);
		for (float[] point : vertices) {
			vertexBuffer.put(point);
		}
		vertexBuffer.position(0);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float x = event.getX();
		float y = event.getY();
		int a=event.getAction();
		if (a==MotionEvent.ACTION_MOVE || a==MotionEvent.ACTION_UP) {
			setBottomRightCorner(x,y);
		}
		else if (a==MotionEvent.ACTION_DOWN) {
			// drawing starts
			vertices=new float[4][3];
			setTopLeftCorner(x, y);
		}
		return true;
	}

}