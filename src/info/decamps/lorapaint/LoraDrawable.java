package info.decamps.lorapaint;

import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;

public abstract class LoraDrawable extends Drawable {
	protected LoraSurfaceView lView;
	protected Paint paint;
	/**
	 * This function is called on the screen a MotionEvent is raised, and in
	 * particular when the screen is touched
	 * 
	 * @param event
	 * @return
	 */
	public abstract boolean onTouchEvent(MotionEvent event);

	public Paint getPaint() {
		return paint;
	}

	public void setPaint(Paint paint) {
		this.paint = paint;
	}

	public void setLoraView(LoraSurfaceView view) {
		lView=view;
	}
	
	public LoraSurfaceView getView() {
		return lView;
	}
}