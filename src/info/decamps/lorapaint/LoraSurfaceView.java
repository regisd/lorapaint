package info.decamps.lorapaint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import android.widget.ImageView;

public class LoraSurfaceView extends ImageView {
	LoraShape shape;
	Paint paint;

	public Paint getPaint() {
		return paint;
	}

	public LoraSurfaceView(Context context) {
		super(context);
		paint = new Paint();
		paint.setColor(Color.WHITE);
	}

	/** Grabs and transfers the onTouchEvent to the shape */
	public boolean onTouchEvent(final MotionEvent event) {
		System.out.println("touched for shape " + shape);
		if (shape != null)
			shape.onTouchEvent(event);
		this.invalidate();
		return true;
	}

	public void setShape(LoraShape shape) {
		this.shape=shape;
		shape.setLoraView(this);
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		if (shape != null)
			shape.draw(canvas);
	}
}
