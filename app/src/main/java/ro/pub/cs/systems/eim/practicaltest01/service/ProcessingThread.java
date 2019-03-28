package ro.pub.cs.systems.eim.practicaltest01.service;

import android.content.Context;
import android.content.Intent;
import android.os.Build;

import java.time.LocalDateTime;
import java.util.Calendar;

class ProcessingThread extends Thread {


    private  String text1;
    private  String text2;
    private Context context;


    public ProcessingThread(Context context, String text1, String text2) {
        this.context = context;
        this.text1 = text1;
        this.text2 = text2;
    }

    @Override
    public void run() {

        while(true) {
            sendMessage();
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendMessage() {

        Intent intent = new Intent(Constants.ACTION_STRING);
        intent.putExtra("textservice1", text1 );
        intent.putExtra("textservice2", text2 );
        context.sendBroadcast(intent);
    }
}
