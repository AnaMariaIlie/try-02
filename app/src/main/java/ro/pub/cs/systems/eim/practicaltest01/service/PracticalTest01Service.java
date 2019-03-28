package ro.pub.cs.systems.eim.practicaltest01.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class PracticalTest01Service extends Service {

    ProcessingThread processingThread;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        System.out.println("service invoked");
        processingThread = new ProcessingThread(this, intent.getStringExtra("textservice1"), intent.getStringExtra("textservice2"));
        processingThread.start();
        return START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            processingThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
