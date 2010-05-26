package info.decamps.lorapaint.shape;

import info.decamps.lorapaint.LoraDrawable;
import info.decamps.lorapaint.LoraSurfaceView;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

public class ClearShape extends LoraDrawable {
	private int mRed;
	private int mGreen;
	private int mBlue;
	private int pressure;
	
	private LoraSurfaceView lView;

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
