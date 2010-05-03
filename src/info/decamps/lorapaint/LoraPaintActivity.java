package info.decamps.lorapaint;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

public class LoraPaintActivity extends Activity {
	private boolean fullscreen=false;
	private LoraSurfaceView lView; 
	public static enum MENU {SHAPE_SQUARE,FILE_SAVE,FILE_QUIT}; 
	
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
   		//lView.setRenderer(new LoraRenderer());
   		setContentView(lView);
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        lView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        lView.onResume();
    }
    
	/* option menu */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		SubMenu shapeMenu= menu.addSubMenu("Select Shape");
		shapeMenu.add(0, MENU.SHAPE_SQUARE.ordinal(),0,"Square");
		SubMenu fileMenu = menu.addSubMenu("File");
		fileMenu.add(0,MENU.FILE_SAVE.ordinal(),0,"Save");
	    fileMenu.add(0, MENU.FILE_QUIT.ordinal(), 0, "Quit");
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (MENU.values().clone()[item.getItemId()]) {
		//TODO Introspection on available shapes
		case SHAPE_SQUARE:
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