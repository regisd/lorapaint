package info.decamps.lorapaint.shape;

import info.decamps.lorapaint.LoraShape;
import info.decamps.lorapaint.LoraSurfaceView;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

public class SquareShape implements LoraShape {
	private Point orig;
	private Point dest;
	private LoraSurfaceView lView;

	public void draw(GL10 gl) {
	}

	@Override
	public void draw(Canvas canvas) {
		if (dest != null && orig != null) {
			canvas.drawRect(orig.x, orig.y, dest.x, dest.y, lView.getPaint());
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
		System.out.println("event " + event.getAction());
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
	public void setLoraView(LoraSurfaceView l) {
		lView = l;
	}

}