package info.decamps.lorapaint.shape;

import info.decamps.lorapaint.LoraDrawable;
import info.decamps.lorapaint.LoraSurfaceView;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.view.MotionEvent;

public class PointShape extends LoraDrawable{
	public PointShape(LoraSurfaceView view) {
		super(view);
	}

	boolean drawing=false;
	private float x;
	private float y;
	private float pressure;

	private static float MAX_RADIUS=30;
	private static float MIN_RADIUS=8;

	@Override
	public void draw(Canvas canvas) {
		Paint p = super.paint;
		p.setAlpha((int)(255*pressure)/2+125);
		float radius=MAX_RADIUS*pressure+MIN_RADIUS*(1f-pressure);
		canvas.drawCircle(x, y, radius, p);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
	    if(event.getAction() == MotionEvent.ACTION_DOWN){
	        drawing = true;
	    }else if(event.getAction() == MotionEvent.ACTION_UP)
	        drawing = false;

	    x = event.getX();
	    y = event.getY();
	    pressure=event.getPressure();
		return false;
	}

}
