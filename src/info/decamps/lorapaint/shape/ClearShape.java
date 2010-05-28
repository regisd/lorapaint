package info.decamps.lorapaint.shape;

import info.decamps.lorapaint.LoraDrawable;
import info.decamps.lorapaint.LoraSurfaceView;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Paint.Style;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class ClearShape extends LoraDrawable {
	private double MAX = 0;
	// Most screens are 320x480, bust init will set it correctly
	private Point POINT_MIDDLE=new Point(160,240);
	private float[] hsv = new float[3];

	@Override
	public void init(LoraSurfaceView view, Paint paint) {
		// Paint is smartly created when user touches the screen
		super.init(view, paint);
		//FIXME: POINT_MIDLE bouge pas
		POINT_MIDDLE.set(lView.getWidth() / 2 , lView.getHeight() / 2);
		MAX = Math.pow(lView.getWidth() / 2, 2);
	}

	@Override
	public void draw(Canvas canvas) {
		// p.setColor(Color.argb(mAlpha,mRed, mGreen, mBlue));
		super.lPaint.setStyle(Style.FILL);
		canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(),
				super.lPaint);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// mRed=event.getX() / lView.getWidth();
		// mGreen=event.getY()/ lView.getHeight();
		// mBlue= 1.0f;
		int x = (int) event.getX();
		int y = (int) event.getY();
		// hue 0..360. acos works in Radians
		double x1 = (double) (x - POINT_MIDDLE.x)
				/ ((double) super.lView.getWidth() / 2);
		x1 = Math.min(1, x1);
		x1 = Math.max(-1, x1);
		hsv[0] = (float) (Math.acos(x1) * 180 / Math.PI);
		if (y > POINT_MIDDLE.y) {
			hsv[0] = 360 - hsv[0];
		}
		// saturation [Ø..1]
		double d = dist(POINT_MIDDLE, x, y);
		hsv[1] = (float) Math.min(1, d);

		// value [0..1]
		hsv[2] = event.getPressure();

		super.lPaint.setColor(Color.HSVToColor(hsv));
		System.out.println("x=" + x + " y=" + y);

		System.out.println("hsv=" + hsv[0] + "," + hsv[1] + "," + hsv[2]);
		return true;
	}

	private double dist(Point p, int x, int y) {
		double dif = Math.pow((p.x - x), 2) + Math.pow(p.y - y, 2);
		return Math.sqrt(dif / MAX);
	}
}
