package ro.pub.cs.systems.eim.practicaltest01.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "This is my Toast message!" + intent.getStringExtra("textservice1") + " " + intent.getStringExtra("textservice2"),
                Toast.LENGTH_LONG).show();
    }
}
