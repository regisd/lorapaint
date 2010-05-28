package info.decamps.lorapaint.shape;

import info.decamps.lorapaint.LoraDrawable;
import info.decamps.lorapaint.LoraSurfaceView;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

public class PointShape extends LoraDrawable{
	public PointShape(LoraSurfaceView view, Paint paint) {
		super(view,paint);
	}

	boolean drawing=false;
	private float x;
	private float y;
	private float radius=0;

	private static float MAX_RADIUS=50;
	private static float MIN_RADIUS=5;

	@Override
	public void draw(Canvas canvas) {
		canvas.drawCircle(x, y, radius, lPaint);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
	    if(event.getAction() == MotionEvent.ACTION_DOWN){
	        drawing = true;
	    }else if(event.getAction() == MotionEvent.ACTION_UP)
	        drawing = false;

	    x = event.getX();
	    y = event.getY();
	    // when pressure increase, the circle is bigger and more transparent
	    float p=event.getPressure();
	    radius=Math.max(radius,MAX_RADIUS*p+MIN_RADIUS*(1f-p));

		lPaint.setAlpha(255-(int)(255*p)/2);
		
		return true;
	}

}
