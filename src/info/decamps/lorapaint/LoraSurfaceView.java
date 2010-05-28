package info.decamps.lorapaint;

import info.decamps.lorapaint.shape.ClearShape;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class LoraSurfaceView extends View {
	private LoraDrawable shape;

	private ArrayList<LoraDrawable> history;

	public LoraSurfaceView(Context context) {
		super(context);
		history = new ArrayList<LoraDrawable>();
	}

	public boolean onTouchEvent(final MotionEvent event) {
		if (shape != null) {
			// transfers TouchEvent to the Shape
			shape.onTouchEvent(event);
		}
		if (event.getAction() == MotionEvent.ACTION_UP) {
			if (shape.getClass() == ClearShape.class && history.size()>0) {
				// replace background
				history.set(0, shape);
			} else {
				history.add(shape);				
			}
			
			try {
				// prepare next drawing
				//shape = shape.clone();
				Paint paint=shape.lPaint;
				shape=shape.getClass().newInstance();
				shape.init(this, paint);
			} catch (Exception e) {
				// safe
				e.printStackTrace();
			}
			Log.d("LoraPaint", "shape " + shape + " added in history");
		}
		this.invalidate();
		return true;
	}

	public LoraDrawable getShape() {
		return shape;
	}

	public void setShape(LoraDrawable shape) {
		this.shape = shape;
		// shape.setLoraView(this); done in constructor of shape
	}

	@Override
	public void draw(Canvas canvas) {
		// super.draw(canvas);
		for (LoraDrawable s : history) {
			s.draw(canvas);
			// Log.d("LoraPaint","draw from history"+s);
		}
		if (shape != null)
			shape.draw(canvas);
	}
}