package info.decamps.lorapaint.shape;

import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import info.decamps.lorapaint.LoraShape;

public class PointShape implements LoraShape{
	String text;
	@Override
	public void draw(GL10 gl) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		System.out.println("x="+event.getX());
		System.out.println("y="+event.getY());
		return false;
	}

	@Override
	public void setGLView(GLSurfaceView glSurfaceView) {
		// TODO Auto-generated method stub
		System.out.println("surface width="+glSurfaceView.getWidth());
		System.out.println("surface height="+glSurfaceView.getHeight());
	}

}
