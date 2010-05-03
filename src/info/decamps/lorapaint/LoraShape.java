package info.decamps.lorapaint;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Canvas;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

public interface LoraShape {
	/**
	 * This function draws our shape on the openGL view.
	 */
	public void draw(GL10 gl);

	/**
	 * This function draws the shape on an Android canvas.
	 * 
	 * @param canvas
	 */
	public void draw(Canvas canvas);

	/**
	 * This function is called on the screen a MotionEvent is raised, and in
	 * particular when the screen is touched
	 * 
	 * @param event
	 * @return
	 */
	public abstract boolean onTouchEvent(MotionEvent event);

	public void setLoraView(LoraSurfaceView surfaceView);
}