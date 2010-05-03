package info.decamps.lorapaint.shape;

import info.decamps.lorapaint.LoraShape;
import info.decamps.lorapaint.LoraSurfaceView;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

public class ClearShape implements LoraShape {
	private int mRed;
	private int mGreen;
	private int mBlue;
	private int pressure;
	
	private LoraSurfaceView lView;

	@Override
	public void draw(GL10 gl) {
		gl.glClearColor(mRed, mGreen, mBlue, 1.0f);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

	}

	@Override
	public void draw(Canvas canvas) {
		Paint p = new Paint();
		int alpha=pressure/2+125;
		p.setColor(Color.argb(alpha,mRed, mGreen, mBlue));
		p.setStyle(Style.FILL);
		canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), p);
	}

	public void setColor(float r, float g, float b) {
		mRed = (int)(255*r);
		mGreen = (int)(255*g);
		mBlue = (int)(255*b);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		setColor(event.getX() / lView.getWidth(), event.getY()
				/ lView.getHeight(), 1.0f);
		setPressure(event.getPressure());
		return false;
	}

	private void setPressure(float p) {
		pressure=(int)(255*p);		
	}

	@Override
	public void setLoraView(LoraSurfaceView lView) {
		this.lView = lView;

	}
}
