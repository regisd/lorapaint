package info.decamps.lorapaint;


import info.decamps.lorapaint.shape.ClearShape;
import info.decamps.lorapaint.shape.PointShape;
import info.decamps.lorapaint.shape.RectShape;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.shapes.Shape;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.Window;
import android.view.WindowManager;

public class LoraPaintActivity extends Activity {
	private static final int REQUEST_CODE_PICK_COLOR = 1;
	// slightly transparent
	private static final int DEFAULT_ALPHA = 200;
	private boolean fullscreen = false;
	private LoraSurfaceView lView;
	private Paint currentPaint;

	public static enum MENU {
		SHAPE_SQUARE, SHAPE_CLEAR, SHAPE_POINT, COLOR_SELECT, FILE_SAVE, FILE_QUIT
	};

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (fullscreen) {
			this.requestWindowFeature(Window.FEATURE_NO_TITLE);
			getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
					WindowManager.LayoutParams.FLAG_FULLSCREEN);
		}
		lView = new LoraSurfaceView(this);

		// default shape to avoid NPE
		currentPaint = new Paint();
		currentPaint.setColor(Color.WHITE);
		currentPaint.setAlpha(DEFAULT_ALPHA);
		new RectShape().init(lView, currentPaint);

		setContentView(lView);
		lView.requestFocus();
	}

	/* option menu */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		SubMenu shapeMenu = menu.addSubMenu("Select Shape");
		// TODO plugin architecture
		shapeMenu.add(0, MENU.SHAPE_SQUARE.ordinal(), 0, "Rectangle");
		shapeMenu.add(0, MENU.SHAPE_POINT.ordinal(), 0, "Point");
		shapeMenu.add(0, MENU.SHAPE_CLEAR.ordinal(), 0, "Background in colors");

		MenuItem colorMenu = menu.add(0, MENU.COLOR_SELECT.ordinal(), 0,
				"Color");
		SubMenu fileMenu = menu.addSubMenu("File");
		fileMenu.add(0, MENU.FILE_SAVE.ordinal(), 0, "Save");
		fileMenu.add(0, MENU.FILE_QUIT.ordinal(), 0, "Quit");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (MENU.values().clone()[item.getItemId()]) {
		// TODO Introspection on available shapes
		case SHAPE_SQUARE:
			new RectShape().init(lView, currentPaint);
			// lView.setShape(s); done in constructor
			return true;
		case SHAPE_POINT:
			new PointShape().init(lView, currentPaint);
			return true;
		case SHAPE_CLEAR:
			new ClearShape().init(lView,currentPaint);
			return true;
		case COLOR_SELECT:
			Intent myIntent = new Intent();
			myIntent.setAction("org.openintents.action.PICK_COLOR");
			myIntent.putExtra("org.openintents.extra.COLOR", currentPaint
					.getColor());
			startActivityForResult(myIntent, REQUEST_CODE_PICK_COLOR);

			return true;
		case FILE_QUIT:
			quit();
			return true;
		}
		return false;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case REQUEST_CODE_PICK_COLOR:
			if (resultCode == RESULT_OK) {
				int mColor=0;
				mColor = data
						.getIntExtra("org.openintents.extra.COLOR", mColor);
				currentPaint=new Paint();
				currentPaint.setColor(mColor);
				currentPaint.setAlpha(DEFAULT_ALPHA);// TODO
				
				//apply this color to future objects, even if the shape is unchanged
				LoraDrawable s;
				try {
					s = lView.getShape().getClass().newInstance();
					s.usePaint(currentPaint);
					s.setLoraView(lView);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
			break;
		}
	}

	public void quit() {
		// TODO Confirm dialog box
		// TODO Save before quit
		System.exit(0);
	}
}