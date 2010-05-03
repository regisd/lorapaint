package info.decamps.lorapaint.shape;

import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import info.decamps.lorapaint.LoraShape;
import info.decamps.lorapaint.LoraSurfaceView;

public class ClearShape implements LoraShape {
	private float mRed;
    private float mGreen;
    private float mBlue;
	private GLSurfaceView glView;
    
	@Override
	public void draw(GL10 gl) {
		gl.glClearColor(mRed, mGreen, mBlue, 1.0f);
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		
	}
	
    public void setColor(float r, float g, float b) {
        mRed = r;
        mGreen = g;
        mBlue = b;
    }


	@Override
	public boolean onTouchEvent(MotionEvent event) {
		setColor(event.getX() / glView.getWidth(), event.getY() / glView.getHeight(), 1.0f);
		return false;
	}

	@Override
	public void setGLView(GLSurfaceView glSurfaceView) {
		glView=glSurfaceView;
		
	}

}
