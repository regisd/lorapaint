package info.decamps.lorapaint.shape;

import javax.microedition.khronos.opengles.GL10;

import android.view.MotionEvent;
import info.decamps.lorapaint.LoraShape;

public class ClearShape implements LoraShape {
    private float mRed;
    private float mGreen;
    private float mBlue;
    
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
		// TODO Auto-generated method stub
		return false;
	}

}
