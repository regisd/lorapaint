package info.decamps.lorapaint;

import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;

public class LoraAlphaVariation implements Runnable {
	public static final int MIN_ALPHA = 10;
	public static final int MAX_ALPHA = 255;
	private static final int MIN_TIME = 50;// ms
	private static final int MAX_TIME = 3000;// ms
	private String TAG = "LoraPaint LoraAlphaVariation";
	private LoraDrawable lDrawable;

	public LoraAlphaVariation(LoraDrawable who) {
		this.lDrawable=who;
	}

	@Override
	public void run() {
		final long now = SystemClock.uptimeMillis();
		long duration = now - lDrawable.getCreationTime();

		// when time last, the circle is more opaque
		Log.d(TAG, "Duration =" + duration);
		int alpha = (MAX_ALPHA - MIN_ALPHA) * (int) duration
				/ (MAX_TIME - MIN_TIME) + MIN_ALPHA;
		alpha = Math.max(alpha, MIN_ALPHA);
		alpha = Math.min(alpha, MAX_ALPHA);
		lDrawable.lPaint.setAlpha(alpha);
		Log.d(TAG, "alpha=" + alpha);
		// update again if it is not too high already
		if (duration < MAX_TIME) {
			lDrawable.mHandler.postAtTime(this, now + 60);
		}
		// redraw();
		lDrawable.invalidateSelf();
	}
}