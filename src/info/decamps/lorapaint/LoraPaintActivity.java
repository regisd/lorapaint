package info.decamps.lorapaint;

import info.decamps.lorapaint.shape.ClearShape;
import info.decamps.lorapaint.shape.PointShape;
import info.decamps.lorapaint.shape.RectShape;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.Window;
import android.view.WindowManager;

public class LoraPaintActivity extends Activity {
	private boolean fullscreen = false;
	private LoraSurfaceView lView;

	public static enum MENU {
		SHAPE_SQUARE, SHAPE_CLEAR, SHAPE_POINT, FILE_SAVE, FILE_QUIT
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
		lView.setShape(new RectShape(lView));

		setContentView(lView);
		lView.requestFocus();
	}

	/* option menu */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		SubMenu shapeMenu = menu.addSubMenu("Select Shape");
		// TODO plugin architecture
		shapeMenu.add(0, MENU.SHAPE_SQUARE.ordinal(), 0, "Rectangle");
		shapeMenu.add(0, MENU.SHAPE_CLEAR.ordinal(), 0, "Colorful background");
		shapeMenu.add(0, MENU.SHAPE_POINT.ordinal(), 0, "debug point");
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
			new RectShape(lView);
			//lView.setShape(s); done in constructor
			return true;
		case SHAPE_CLEAR:
			new ClearShape(lView);
			//lView.setShape(s);
			return true;
		case SHAPE_POINT:
			new PointShape(lView);
			//lView.setShape(s);
			return true;
		case FILE_QUIT:
			quit();
			return true;
		}
		return false;
	}

	public void quit() {
		// TODO Auto-generated method stub
	}
}