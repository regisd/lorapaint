package info.decamps.lorapaint;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

public class LoraSurfaceView extends GLSurfaceView {

	protected LoraRenderer mRenderer;

	public LoraSurfaceView(Context context) {
		super(context);
		mRenderer = new LoraRenderer();
		setRenderer(mRenderer);
	}

	/** Grabs and transfers the onTouchEvent to the shape */ 
	public boolean onTouchEvent(final MotionEvent event) {
		queueEvent(new Runnable() {
			public void run() {
				mRenderer.shape.onTouchEvent(event);
			}
		});
		return true;
	}
	
	public void setShape(LoraShape shape) {
		shape.setGLView(this);
		mRenderer.setShape(shape);
	}
}
