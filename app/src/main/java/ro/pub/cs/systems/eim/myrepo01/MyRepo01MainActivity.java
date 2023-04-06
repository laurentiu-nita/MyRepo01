package ro.pub.cs.systems.eim.myrepo01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MyRepo01MainActivity extends AppCompatActivity {

    private Button navigateToSecondaryActivityButton;
    private EditText leftEditText, rightEditText;
    private Button pressMeButton;
    private Button pressMeTooButton;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.navigate_to_secondary_activity_button:
                    Intent intent = new Intent("ro.pub.cs.systems.eim.myrepo01.intent.action.MyRepo01SecondaryActivity");
                    String leftValue = leftEditText.getText().toString();
                    String rightValue = rightEditText.getText().toString();
                    if (leftValue != null && !leftValue.isEmpty() && rightValue != null && !rightValue.isEmpty()) {
                        try {
                            int leftNumber = Integer.parseInt(leftValue);
                            int rightNumber = Integer.parseInt(rightValue);
                            int numberOfClicks = leftNumber + rightNumber;
                            intent.putExtra(Constants.NUMBER_OF_CLICKS, numberOfClicks);
                        } catch (NumberFormatException numberFormatException) {
                            Toast.makeText(getApplicationContext(), numberFormatException.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                    startActivityForResult(intent, Constants.SECONDARY_ACTIVITY_REQUEST_CODE);
                    break;
                case R.id.press_me_button:
                    String leftEditValue = leftEditText.getText().toString();
                    if (leftEditValue != null && !leftEditValue.isEmpty()) {
                        try {
                            int leftNumber = Integer.parseInt(leftEditValue);
                            leftNumber++;
                            leftEditText.setText(String.valueOf(leftNumber));
                        } catch (NumberFormatException numberFormatException) {
                            Toast.makeText(getApplicationContext(), numberFormatException.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                    break;
                case R.id.press_me_too_button:
                    String rightEditValue = rightEditText.getText().toString();
                    if (rightEditValue != null && !rightEditValue.isEmpty()) {
                        try {
                            int rightNumber = Integer.parseInt(rightEditValue);
                            rightNumber++;
                            rightEditText.setText(String.valueOf(rightNumber));
                        } catch (NumberFormatException numberFormatException) {
                            Toast.makeText(getApplicationContext(), numberFormatException.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_repo01_main);

        navigateToSecondaryActivityButton = (Button)findViewById(R.id.navigate_to_secondary_activity_button);
        navigateToSecondaryActivityButton.setOnClickListener(buttonClickListener);
        leftEditText = (EditText)findViewById(R.id.left_edit_text);
        rightEditText = (EditText)findViewById(R.id.right_edit_text);
        pressMeButton = (Button)findViewById(R.id.press_me_button);
        pressMeButton.setOnClickListener(buttonClickListener);
        pressMeTooButton = (Button)findViewById(R.id.press_me_too_button);
        pressMeTooButton.setOnClickListener(buttonClickListener);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(Constants.LEFT_EDIT_TEXT)) {
                leftEditText.setText(savedInstanceState.getString(Constants.LEFT_EDIT_TEXT));
            } else {
                leftEditText.setText(Constants.ZERO);
            }
            if (savedInstanceState.containsKey(Constants.RIGHT_EDIT_TEXT)) {
                rightEditText.setText(savedInstanceState.getString(Constants.RIGHT_EDIT_TEXT));
            } else {
                rightEditText.setText(Constants.ZERO);
            }
        } else {
            leftEditText.setText(Constants.ZERO);
            rightEditText.setText(Constants.ZERO);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(Constants.LEFT_EDIT_TEXT, leftEditText.getText().toString());
        savedInstanceState.putString(Constants.RIGHT_EDIT_TEXT, rightEditText.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey(Constants.LEFT_EDIT_TEXT)) {
            leftEditText.setText(savedInstanceState.getString(Constants.LEFT_EDIT_TEXT));
        } else {
            leftEditText.setText(Constants.ZERO);
        }
        if (savedInstanceState.containsKey(Constants.RIGHT_EDIT_TEXT)) {
            rightEditText.setText(savedInstanceState.getString(Constants.RIGHT_EDIT_TEXT));
        } else {
            rightEditText.setText(Constants.ZERO);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        switch(requestCode) {
            case Constants.SECONDARY_ACTIVITY_REQUEST_CODE:
                Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
                break;
        }
    }
}