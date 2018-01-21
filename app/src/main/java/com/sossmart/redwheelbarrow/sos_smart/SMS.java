package com.sossmart.redwheelbarrow.sos_smart;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.sossmart.redwheelbarrow.sos_smart.MainActivity.PREFS_NAME;

public class SMS extends Activity
{
    Button btnSendSMS;
    EditText txtPhoneNo;
    EditText txtMessage;
    private Button mainActivityButton;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        final SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        btnSendSMS = (Button) findViewById(R.id.btnSendSMS);
        txtPhoneNo = (EditText) findViewById(R.id.txtPhoneNo);
        txtMessage = (EditText) findViewById(R.id.txtMessage);

        btnSendSMS.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String phoneNo = txtPhoneNo.getText().toString();
                String message = txtMessage.getText().toString();
                if (phoneNo.length()>0 && message.length()>0) {
                    //sendSMS(phoneNo, message);
                    boolean committed = settings.edit()
                            .putString("trustedContact", phoneNo)
                            .putString("emergencyMessage", message)
                            .commit();
                    if (committed) {
                        Toast.makeText(SMS.this, "Contact Saved!", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getBaseContext(),"Please enter both phone number and message.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // This is for the button to change to the SMS activity
        mainActivityButton = (Button) findViewById(R.id.btnMainActivity);

        // Capture button clicks

        mainActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
