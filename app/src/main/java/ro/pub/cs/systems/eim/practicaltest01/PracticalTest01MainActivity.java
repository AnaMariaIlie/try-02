package ro.pub.cs.systems.eim.practicaltest01;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import ro.pub.cs.systems.eim.practicaltest01.service.Constants;
import ro.pub.cs.systems.eim.practicaltest01.service.PracticalTest01Service;

public class PracticalTest01MainActivity extends AppCompatActivity {

    Button nav;
    Button display;
    CheckBox cb1;
    CheckBox cb2;
    EditText et1;
    EditText et2;

    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(intent.getStringExtra("textservice1"), intent.getStringExtra("textservice2"));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_main);


        nav = findViewById(R.id.nav);
        display = findViewById(R.id.display);
        cb1 = findViewById(R.id.cb1);
        cb2 = findViewById(R.id.cb2);
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);

    display.setOnClickListener(buttonClickListener);
    nav.setOnClickListener(buttonClickListener);

        intentFilter.addAction(Constants.ACTION_STRING);


    }

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.nav:
                    Intent intent = new Intent(getApplicationContext(), PracticalTest01SecondaryActivity.class);
                    intent.putExtra("text1", et1.getText().toString());
                    intent.putExtra("text2", et2.getText().toString());
                    startActivityForResult(intent, 1);
                    break;
                case R.id.display:
                    if (cb1.isChecked() && cb2.isChecked()) {
                        if (!et1.getText().toString().equals("") && !et2.getText().toString().equals("") ) {
                            Toast.makeText(getApplicationContext(), et1.getText().toString() + et2.getText().toString(),
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "error",
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                    if (cb1.isChecked()) {
                        if (!et1.getText().toString().equals("")) {
                            Toast.makeText(getApplicationContext(), et1.getText().toString(),
                                    Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "error",
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                    if (cb2.isChecked()) {
                        if ( !et2.getText().toString().equals("")) {
                            Toast.makeText(getApplicationContext(), et2.getText().toString(),
                                    Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "error",
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                    Intent i = new Intent(getApplicationContext(), PracticalTest01Service.class);
                    i.putExtra("textservice1", et1.getText().toString());
                    i.putExtra("textservice2", et2.getText().toString());
                    getApplicationContext().startService(i);
                    break;

            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 1) {
            Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
        }
    }
    private IntentFilter intentFilter = new IntentFilter();
    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }

}
