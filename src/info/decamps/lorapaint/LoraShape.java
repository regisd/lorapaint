package info.decamps.lorapaint;

import javax.microedition.khronos.opengles.GL10;

import android.view.MotionEvent;

public interface LoraShape {

	/**
	 * This function draws our shape on the view.
	 * @param glview Open GL 1.0 view
	 */
	public abstract void draw(GL10 glview);
	
	public boolean onTouchEvent(MotionEvent event) ;

}