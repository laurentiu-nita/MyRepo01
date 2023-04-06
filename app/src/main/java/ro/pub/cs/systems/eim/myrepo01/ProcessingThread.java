package ro.pub.cs.systems.eim.myrepo01;

import android.content.Intent;

public class ProcessingThread extends Thread {
    private boolean isRunning = true;
    private String leftEditText;
    private String rightEditText;
    private MyRepo01Service myRepo01Service;

    public ProcessingThread(MyRepo01Service myRepo01Service, String leftEditText, String rightEditText) {
        this.myRepo01Service = myRepo01Service;
        this.leftEditText = leftEditText;
        this.rightEditText = rightEditText;
    }

    @Override
    public void run() {
        while (isRunning) {
            sendMessage();
            sleep();
        }
    }

    private void sendMessage() {
        Intent intent = new Intent();
        intent.setAction(Constants.ACTION_STRING);
        intent.putExtra(Constants.LEFT_NUMBER, leftEditText);
        intent.putExtra(Constants.RIGHT_NUMBER, rightEditText);
        myRepo01Service.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }
}
