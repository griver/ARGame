package arpong.menu;

import ru.knk.JavaGL.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;
import arpong.graphics.GLSurfaceViewActivity;

public class StartingMenu extends Activity implements OnClickListener {
	public static Typeface tf;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_layout);
		
        tf = Typeface.createFromAsset(getAssets(),"data/fonts/paeterb.ttf");
        
        TextView tv = (TextView) findViewById(R.id.start);
        TextView tv1 = (TextView) findViewById(R.id.quick);
        TextView tv3 = (TextView) findViewById(R.id.about);
        TextView tv4 = (TextView) findViewById(R.id.exit);
        
        tv.setTypeface(tf);
        tv1.setTypeface(tf);
        tv3.setTypeface(tf);
        tv4.setTypeface(tf);
        
        tv.setOnTouchListener(new CustomTouchListener());
        tv1.setOnTouchListener(new CustomTouchListener());
        tv3.setOnTouchListener(new CustomTouchListener());
        tv4.setOnTouchListener(new CustomTouchListener());
        
        tv.setOnClickListener(this);
        tv1.setOnClickListener(this);
        tv3.setOnClickListener(this);
        tv4.setOnClickListener(this);        
        
	}

	@Override
	public void onClick(View v) {
		
		switch(v.getId()){
			case R.id.start:
				Intent i = new Intent(this, GLSurfaceViewActivity.class);
				startActivity(i);
				break;
			case R.id.quick:
				break;
			case R.id.about:
				makeDialog();
				break;	
			case R.id.exit:
				finish();
				break;
		}
		
	}
	
	private void makeDialog() {		
		
	    AlertDialog.Builder dialog = new AlertDialog.Builder(this);	    
	    
	    dialog.setMessage("This is a demo");

	    dialog.setPositiveButton("Buy the full version", new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface arg0, int arg1) {
	        	Toast.makeText(getBaseContext(), "BUY IT", Toast.LENGTH_LONG).show();
	        }
	    });
	
	    dialog.setNegativeButton("Close", new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface arg0, int arg1) {}
	    });
	
	    dialog.show();
	}
	
}