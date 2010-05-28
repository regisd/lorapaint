package info.decamps.lorapaint;

import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;

public abstract class LoraDrawable extends Drawable implements Cloneable {
	protected Paint lPaint;
	protected LoraSurfaceView lView;
	/**
	 * This function is called on the screen a MotionEvent is raised, and in
	 * particular when the screen is touched
	 * 
	 * @param event
	 * @return
	 */
	public abstract boolean onTouchEvent(MotionEvent event);

	public LoraDrawable(LoraSurfaceView view, Paint paint) {
		this.lPaint = paint;
		lView=view;
		lView.setShape(this);
	}

	@Override
	protected LoraDrawable clone() throws CloneNotSupportedException {
		return (LoraDrawable) super.clone();
	}

	@Override
	public int getOpacity() {
		return lPaint.getAlpha();
	}

	@Override
	public void setAlpha(int alpha) {
		lPaint.setAlpha(alpha);

	}

	@Override
	public void setColorFilter(ColorFilter cf) {
		// TODO Auto-generated method stub

	}

	public void setLoraView(LoraSurfaceView view) {
		this.lView=view;		
	}
}