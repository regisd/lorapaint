package info.decamps.lorapaint.shape;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import info.decamps.lorapaint.LoraShape;
import info.decamps.lorapaint.LoraSurfaceView;

public class PointShape implements LoraShape{
	boolean drawing=false;
	private float x;
	private float y;
	private float pressure;
	private LoraSurfaceView lView;
	private static float MAX_RADIUS=20;
	private static float MIN_RADIUS=5;
	@Override
	public void draw(GL10 gl) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void draw(Canvas canvas) {
		Paint p = lView.getPaint();
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
	public void setLoraView(LoraSurfaceView lSurfaceView) {
		lView=lSurfaceView;
	}

}
