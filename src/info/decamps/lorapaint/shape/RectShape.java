package info.decamps.lorapaint.shape;

import info.decamps.lorapaint.LoraAlphaVariation;
import info.decamps.lorapaint.LoraDrawable;
import info.decamps.lorapaint.LoraSurfaceView;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.opengl.GLSurfaceView;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;

public class RectShape extends LoraDrawable {
	private Point orig;
	private Point dest;
	private Rect rect;
	private String TAG="LoraPaint RectShape";
	
	public RectShape() {
		mHandler=new Handler();
		mUpdateAlphaTask = new LoraAlphaVariation(this);
	}

	@Override
	public void draw(Canvas canvas) {
		if (rect != null) {
			canvas.drawRect(rect,lPaint);
			Log.d(TAG,"draw "+this.toString());
		}
	}

	public void setTopLeftCorner(float x, float y) {
		orig = new Point((int) x, (int) y);
		dest= new Point((int) x, (int) y);

		rect=new Rect(orig.x,orig.y,dest.x,dest.y);
		
		if (creationTime == 0L) {
			Log.d(TAG,"run LoraAlphaVariation");
			creationTime = SystemClock.uptimeMillis();
			mHandler.removeCallbacks(mUpdateAlphaTask);
			lPaint.setAlpha(LoraAlphaVariation.MIN_ALPHA);
			mHandler.postDelayed(mUpdateAlphaTask, 1);
		}
	}

	private void setBottomRightCorner(float x, float y) {
		dest= new Point((int) x, (int) y);
		rect.set(Math.min(orig.x, dest.x), Math.min(orig.y, dest.y),
		Math.max(orig.x, dest.x), Math.max(orig.y, dest.y));
		setBounds(rect);
		
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float x = event.getX();
		float y = event.getY();
		int a = event.getAction();
		if (a == MotionEvent.ACTION_MOVE) {
			setBottomRightCorner(x, y);
		}
		else if (a == MotionEvent.ACTION_UP) {
			setBottomRightCorner(x, y);
			mHandler.removeCallbacks(mUpdateAlphaTask);
		} else if (a == MotionEvent.ACTION_DOWN) {
			setTopLeftCorner(x, y);
		}
		return true;
	}

	@Override
	public String toString() {
		return rect+" alpha="+lPaint.getAlpha();
	}
}