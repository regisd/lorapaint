package info.decamps.lorapaint.shape;

import info.decamps.lorapaint.LoraAlphaVariation;
import info.decamps.lorapaint.LoraDrawable;
import android.graphics.drawable.Drawable;
import info.decamps.lorapaint.LoraSurfaceView;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;

public class PointShape  extends LoraDrawable {
	private static final int MAX_RADIUS = 80;
	private static final int MIN_RADIUS = 2;

	private static final int MIN_PRESSURE = 100;
	private static final int MAX_PRESSURE = 700;

	public float x;
	public float y;
	public int radius;
	
	private static String TAG = "LoraPaint PointShape";

	public PointShape() {
		mHandler=new Handler();
		mUpdateAlphaTask = new LoraAlphaVariation(this);
	}

	@Override
	public void draw(Canvas canvas) {
		Log.d(TAG,this.toString());
		canvas.drawCircle(x, y, radius, lPaint);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			// countdown based on the idea of
			// http://developer.android.com/resources/articles/timed-ui-updates.html
			if (creationTime == 0L) {
				creationTime = SystemClock.uptimeMillis();
				mHandler.removeCallbacks(mUpdateAlphaTask);
				lPaint.setAlpha(LoraAlphaVariation.MIN_ALPHA);
				mHandler.postDelayed(mUpdateAlphaTask, 1);
			}

		} else if (event.getAction() == MotionEvent.ACTION_UP) {
			mHandler.removeCallbacks(mUpdateAlphaTask);
		}
		if (creationTime != 0L) {
			// update position, radius and alpha when 'drawing'
			// (ie. touch has not been released)
			x = event.getX();
			y = event.getY();

			Log.d(TAG, "Point pressure p=" + event.getPressure());
			// when pressure increase, the circle is bigger
			int p = (int) (event.getPressure() * 1000);
			int r = ((MAX_RADIUS - MIN_RADIUS) * p)
					/ (MAX_PRESSURE - MIN_PRESSURE) + MIN_RADIUS;
			radius = Math.max(r, radius);
			setBounds((int)(x-radius), (int)(y-radius), (int)(x+radius), (int)(y+radius));
		}
		return true;
	}
	
	@Override
	public String toString() {
		return "Circle radius=" + radius + " ; alpha=" + lPaint.getAlpha();
	}
}
