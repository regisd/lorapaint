package info.decamps.lorapaint;

import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;

public abstract class LoraDrawable extends Drawable implements Cloneable, Drawable.Callback {
	protected Paint lPaint;
	protected LoraSurfaceView lView;
	protected long creationTime;
	
	protected Handler mHandler;
	protected Runnable mUpdateAlphaTask;
	
	private static String TAG="LoraPaint LorDrawable";
	public LoraDrawable() {
	}

	public void init(LoraSurfaceView view, Paint paint) {
		usePaint(paint);
		setLoraView(view);
		setCallback(this);
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
	
	@Override
	public void invalidateDrawable(Drawable who) {
		// TODO Auto-generated method stub
		
		final Rect dirty=who.getBounds();
		lView.invalidate(dirty.left,dirty.top,dirty.right,dirty.bottom);
		Log.d(TAG, "invalidate "+dirty.left+" "+dirty.top+" "+dirty.right+" "+dirty.bottom);
	}

	@Override
	public void scheduleDrawable(Drawable who, Runnable what, long when) {
		// TODO Auto-generated method stub
		Log.d(TAG, "what should i do? scheduleDrawable");

	}

	@Override
	public void unscheduleDrawable(Drawable who, Runnable what) {
		// TODO Auto-generated method stub
		Log.d(TAG, "what should i do? unscheduleDrawable");

	}
	public long getCreationTime() {
		return creationTime;
	}
}