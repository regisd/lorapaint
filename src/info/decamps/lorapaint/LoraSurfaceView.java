package info.decamps.lorapaint;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.widget.ImageView;

public class LoraSurfaceView extends ImageView {
	private LoraDrawable shape;

	private ArrayList<LoraDrawable> history;

	public LoraSurfaceView(Context context) {
		super(context);
		history=new ArrayList<LoraDrawable>();

	}

	/** Grabs and transfers the onTouchEvent to the shape */
	public boolean onTouchEvent(final MotionEvent event) {
		
		if (shape != null){
			shape.onTouchEvent(event);
		}
		if (event.getAction()==MotionEvent.ACTION_UP) {
			history.add(shape);
			System.out.println("shape " + shape+" added in history");
		}
		this.invalidate();
		return true;
	}

	public void setShape(LoraDrawable shape) {
		this.shape=shape;
		shape.setLoraView(this);
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		for (LoraDrawable s : history) {
			s.draw(canvas);
			System.out.println("draw from history"+s);
		}
		if (shape != null)
			shape.draw(canvas);
	}
}
