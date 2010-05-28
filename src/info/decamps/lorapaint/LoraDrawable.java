package info.decamps.lorapaint;

import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;

public abstract class LoraDrawable extends Drawable implements Cloneable {
	protected Paint lPaint;
	protected LoraSurfaceView lView;

	public LoraDrawable() {
		// TODO Auto-generated constructor stub
	}
	public void init(LoraSurfaceView view, Paint paint) {
		usePaint(paint);
		setLoraView(view);
	}

	
	/**
	 * This function is called on the screen a MotionEvent is raised, and in
	 * particular when the screen is touched
	 * 
	 * @param event
	 * @return
	 */
	public abstract boolean onTouchEvent(MotionEvent event);

	@Override
	protected LoraDrawable clone() throws CloneNotSupportedException {
		return (LoraDrawable) super.clone();
	}

	@Override
	/* Opacity 0 = transparent */
	public int getOpacity() {
		return lPaint.getAlpha();
	}

	@Override
	public void setAlpha(int alpha) {
		lPaint.setAlpha(alpha);

	}

	public void usePaint(Paint currentPaint) {
		lPaint=new Paint();
		lPaint.setColor(currentPaint.getColor());
	}
	
	public void setLoraView(LoraSurfaceView view) {
		lView=view;
		lView.setShape(this);
	}
	
	@Override
	public void setColorFilter(ColorFilter cf) {
		// TODO Auto-generated method stub
	}
}