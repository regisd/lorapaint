package info.decamps.lorapaint.shape;

import info.decamps.lorapaint.LoraDrawable;
import info.decamps.lorapaint.LoraSurfaceView;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;

public class PointShape extends LoraDrawable {
	private static final int MAX_RADIUS = 80;
	private static final int MIN_RADIUS = 5;
	private static final int MIN_ALPHA = 10;
	private static final int MAX_ALPHA = 255;

	// private static float MIN_PRESSURE=0.1f;
	// private static float MAX_PRESSURE=0.7f;
	private static final int MIN_PRESSURE = 100;
	private static final int MAX_PRESSURE = 700;
	private static final int MIN_TIME = 50;// ms
	private static final int MAX_TIME = 3000;// ms

	private float x;
	private float y;
	private int radius = 0;
	private long creationTime = 0;
	private boolean drawing = false;

	@Override
	public void draw(Canvas canvas) {
		canvas.drawCircle(x, y, radius, lPaint);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			creationTime = System.currentTimeMillis();
			drawing = true;
		} else if (event.getAction() == MotionEvent.ACTION_UP) {
			drawing = false;
		}
		if (drawing) {
			// update position, radius and alpha when 'drawing' 
			// (ie. touch has not been released)
			x = event.getX();
			y = event.getY();

			Log.d("Point pressure", "p=" + event.getPressure());
			// when pressure increase, the circle is bigger
			int p = (int) (event.getPressure() * 1000);
			int r = ((MAX_RADIUS - MIN_RADIUS) * p)
					/ (MAX_PRESSURE - MIN_PRESSURE) + MIN_RADIUS;
			radius = Math.max(r, radius);

			// when time last, the circle is more transparent
			long d1 = (int) (System.currentTimeMillis() - creationTime);
			int d = (int) ((d1 > MAX_TIME) ? MAX_TIME : d1);
			int alpha = (MAX_ALPHA - MIN_ALPHA) * d / (MAX_TIME - MIN_TIME)
					+ MIN_ALPHA;
			alpha=Math.max(alpha,MIN_ALPHA);
			alpha=Math.min(alpha, MAX_ALPHA);
			lPaint.setAlpha(alpha);
		}
		return true;
	}
}
