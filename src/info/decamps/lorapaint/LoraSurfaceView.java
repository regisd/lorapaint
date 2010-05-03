package info.decamps.lorapaint;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

public class LoraSurfaceView extends GLSurfaceView {

	LoraRenderer mRenderer;

	public LoraSurfaceView(Context context) {
		super(context);
		mRenderer = new LoraRenderer();
		setRenderer(mRenderer);
	}

	public boolean onTouchEvent(final MotionEvent event) {
		queueEvent(new Runnable() {
			public void run() {
				mRenderer.clearbg.setColor(event.getX() / getWidth(),
                        event.getY() / getHeight(), 1.0f);
			}
		});
		return true;
	}
}
