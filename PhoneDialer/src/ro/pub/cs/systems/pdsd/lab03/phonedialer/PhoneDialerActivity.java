package ro.pub.cs.systems.pdsd.lab03.phonedialer;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;


public class PhoneDialerActivity extends Activity {
	
	final protected int[] buttonIds = {
		R.id.button0,
		R.id.button1,
		R.id.button2,
		R.id.button3,
		R.id.button4,
		R.id.button5,
		R.id.button6,
		R.id.button7,
		R.id.button8,
		R.id.button9,
		R.id.button_diez,
		R.id.button_star
	};
	
	final protected int[] imageButtonIds = {
			R.id.call,
			R.id.back,
			R.id.hangup
	};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_dialer);
        MyListener myLis = new MyListener();
        for (int k = 0; k < buttonIds.length; k++) {
        	Button b = (Button)findViewById(buttonIds[k]);
        	b.setOnClickListener(myLis);
        }
        for (int k = 0; k < imageButtonIds.length; k++) {
        	ImageButton b = (ImageButton)findViewById(imageButtonIds[k]);
        	b.setOnClickListener(myLis);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.phone_dialer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    protected class MyListener implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			EditText editText = (EditText)findViewById(R.id.phone_number_edit_text);
			// TODO Auto-generated method stub
			if(v instanceof Button) {
				String textValue = ((Button) v).getText().toString();
				Log.d("myTag",editText+"*");
				editText.setText(editText.getText().toString()+textValue);
			}
			
			if(v instanceof ImageButton) {
				switch (v.getId()) {
				case R.id.call:
					Intent intent = new Intent(Intent.ACTION_CALL);
					intent.setData(Uri.parse("tel:"+editText.getText().toString()));
					startActivity(intent);
					break;
				case R.id.hangup:
					finish();
					break;
				case R.id.back:
					String phoneNumber = editText.getText().toString();
					
					if(phoneNumber.length() > 0) {
						String newPhoneNumber = phoneNumber.substring(0, phoneNumber.length() - 1);
						editText.setText(newPhoneNumber);
					}

					//break;
				}
			}
		}
    	
    }
}
