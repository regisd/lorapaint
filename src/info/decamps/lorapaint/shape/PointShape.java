package info.decamps.lorapaint.shape;

import info.decamps.lorapaint.LoraDrawable;
import info.decamps.lorapaint.LoraSurfaceView;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.view.MotionEvent;

public class PointShape extends LoraDrawable{
	boolean drawing=false;
	private float x;
	private float y;
	private float pressure;
	private LoraSurfaceView lView;
	private static float MAX_RADIUS=20;
	private static float MIN_RADIUS=5;

	public PointShape(Paint paint) {
		super.paint=paint;
	}

	@Override
	public void draw(Canvas canvas) {
		Paint p = super.paint;
		p.setAlpha((int)(255*pressure)/2+125);
		float radius=MAX_RADIUS*pressure+MIN_RADIUS*(1f-pressure);
		canvas.drawCircle(x, y, radius, p);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		System.out.println("x="+event.getX());
		System.out.println("y="+event.getY());
	    if(event.getAction() == MotionEvent.ACTION_DOWN){
	        drawing = true;
	    }else if(event.getAction() == MotionEvent.ACTION_UP)
	        drawing = false;

	    x = event.getX();
	    y = event.getY();
	    pressure=event.getPressure();
		return false;
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
