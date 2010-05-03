package info.decamps.lorapaint;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView.Renderer;

public class LoraRenderer implements Renderer {
	/** Current shape in use. */
	protected LoraShape shape;
	protected GL10 gl;
	
	public LoraShape getShape() {
		return shape;
	}

	public void setShape(LoraShape shape) {
		this.shape = shape;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.opengl.GLSurfaceView.Renderer#onSurfaceCreated(javax.microedition
	 * .khronos.opengles.GL10, javax.microedition.khronos.egl.EGLConfig)
	 */
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		this.gl=gl;
		
		// Set the background color to black ( rgba ).
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);
		gl.glDisable(GL10.GL_DITHER);
		// don't hide backward vertices
		gl.glDisable(GL10.GL_CULL_FACE);
		gl.glShadeModel(GL10.GL_SMOOTH);
		gl.glDisable(GL10.GL_DEPTH_TEST);
		gl.glEnable(GL10.GL_TEXTURE_2D);

		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		// Really nice perspective calculations.
		// gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);

		// TODO If you are developing a reactive application, you can call
		// GLSurfaceView.setRenderMode(RENDERMODE_WHEN_DIRTY), which turns off
		// the continuous animation. Then you call GLSurfaceView.requestRender()
		// whenever you want to re-render.

	}

	public GL10 getGL10() {
		return gl;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.opengl.GLSurfaceView.Renderer#onDrawFrame(javax.microedition.
	 * khronos.opengles.GL10)
	 */
	public void onDrawFrame(GL10 gl) {
		// Clears the screen and depth buffer.
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		// Replace the current matrix with the identity matrix
		gl.glLoadIdentity();
		// Translates the camera so that the screen corresponds to the view.
		gl.glTranslatef(0, 0, -100);
		// Draw our shapes.
		// square.draw(gl);
		shape.draw(gl);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.opengl.GLSurfaceView.Renderer#onSurfaceChanged(javax.microedition
	 * .khronos.opengles.GL10, int, int)
	 */
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// Sets the current view port to the new size.
		gl.glViewport(0, 0, width, height);

		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		//gl.glOrthof(0f, (float)width, 0f, (float)height, 0f, 1f);
		gl.glOrthof(0f, 1f, 1f, 0f, -1f, 1f);
	}

}
