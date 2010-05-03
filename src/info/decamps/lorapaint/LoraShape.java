package info.decamps.lorapaint;

import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

public interface LoraShape {
	/**
	 * This function draws our shape on the view.
	 */
	public abstract void draw(GL10 gl);
	
	public abstract boolean onTouchEvent(MotionEvent event) ;

	public void setGLView(GLSurfaceView GLSurfaceView) ;
}