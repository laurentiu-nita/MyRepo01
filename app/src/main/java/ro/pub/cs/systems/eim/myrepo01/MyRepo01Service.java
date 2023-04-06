package ro.pub.cs.systems.eim.myrepo01;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyRepo01Service extends Service {
    private ProcessingThread processingThread = null;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String leftEditText = intent.getStringExtra(Constants.LEFT_EDIT_TEXT);
        String rightEditText = intent.getStringExtra(Constants.RIGHT_EDIT_TEXT);
        processingThread = new ProcessingThread(this, leftEditText, rightEditText);
        processingThread.start();
        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        processingThread.stopThread();
    }
}
