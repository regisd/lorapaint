package info.decamps.lorapaint.shape;

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
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

public class RectShape extends LoraDrawable {
	private Point orig;
	private Point dest;

	public RectShape(Paint paint) {
		super.paint=paint;
	}

	public void draw(GL10 gl) {
	}

	@Override
	public void draw(Canvas canvas) {
		if (dest != null && orig != null) {
			canvas.drawRect(orig.x, orig.y, dest.x, dest.y, super.paint);
		}
	}

	public void setTopLeftCorner(float x, float y) {
		orig = new Point((int) x, (int) y);
		dest=null;
	}

	private void setBottomRightCorner(float x, float y) {
		dest = new Point((int) x, (int) y);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float x = event.getX();
		float y = event.getY();
		int a = event.getAction();
		if (a == MotionEvent.ACTION_MOVE || a == MotionEvent.ACTION_UP) {
			setBottomRightCorner(x, y);
		} else if (a == MotionEvent.ACTION_DOWN) {
			// drawing starts
			// vertices = new float[4][3];
			setTopLeftCorner(x, y);
		}
		return true;
	}


	@Override
	public String toString() {
		return "Rectangle "+orig+dest;
	}

	@Override
	public int getOpacity() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setAlpha(int alpha) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setColorFilter(ColorFilter cf) {
		// TODO Auto-generated method stub
		
	}
}