/**
 *  The purpose of this game is to guess exact number by comparing random number.
 *  @author Seongyeop + Jeong (jeon0042@algonquinlive.com)
 */

package jeon0042.hilo;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


//    private TextView userGuessText;
    Random rnd = new Random();

    private int theNumber; // random number
    private int userGuess; // user guess number
    private int lowGuess; // user guess which is low number
    private int highGuess; // user guess which is high number
    private short totalSuperiorWin;
    private short totalExcellentWin;
    private short totalLose;
    private String previousNum;
//    private String countNum;
    private EditText userGuessEditText;
    private TextView chosenNumView;
    private TextView counterNum;
    private short count; // total guess
    private boolean checkLose;
    private boolean checkWin;
    public MainActivity() {
        count = 0;
        lowGuess = 0;
        highGuess = 0;
        totalSuperiorWin = 0;
        totalExcellentWin = 0;
        totalLose = 0;
        checkLose = false;
        checkWin = false;
    }

    public void checkCount(short count) {
        checkWin = true;
        if (count < 5) {
            totalSuperiorWin++;
            checkWin = true;
            Toast.makeText(getApplicationContext(), "Superior win!", Toast.LENGTH_LONG).show();
        }
        if (count <10 && count > 5) {
            totalExcellentWin++;
            checkWin = true;
            Toast.makeText(getApplicationContext(), "Excellent win!", Toast.LENGTH_LONG).show();
        }
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                Toast.makeText(getApplicationContext(), "You Win. Please Reset to play again", Toast.LENGTH_LONG).show();
            }
        }, 3000);
    }

    public void mainFunc() throws NumberFormatException {

        try {
            userGuess = Integer.parseInt(userGuessEditText.getText().toString()); // user guessed number
            if (userGuess > 1000 || userGuess < 1) {
                userGuessEditText.setError("it does not allow to input more than 1000 / less than 1");
                userGuessEditText.requestFocus();
                return;
            }

        } catch(NumberFormatException nex) {
            userGuessEditText.setError("Only number is required");
            userGuessEditText.requestFocus();
            return;
        }

        previousNum = userGuessEditText.getText().toString();
//                countNum = ;
        if (count >= 10) {
            if (!checkLose)
                totalLose++;
            Toast.makeText(getApplicationContext(), "Please Reset!", Toast.LENGTH_LONG).show();
            checkLose = true;
            return;
        }

        if (userGuess == theNumber) {
            if (!checkWin)
                checkCount(count);
            return;
        } else if (userGuess > theNumber) {
            highGuess++;
            chosenNumView.setText("Lower than " + previousNum);
            Toast.makeText(getApplicationContext(), "You number is LOW than the Number", Toast.LENGTH_LONG).show();
        } else {//if (userGuess < theNumber) {
            lowGuess++;
            chosenNumView.setText("Higher than " + previousNum);
            Toast.makeText(getApplicationContext(), "You number is HIGH than the Number", Toast.LENGTH_LONG).show();
        }
        count++;
        counterNum.setText(String.valueOf(count));
        //userGuessEditText.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userGuessEditText = findViewById(R.id.guess_edit);
        chosenNumView = findViewById(R.id.user_guess_view);
        counterNum = findViewById(R.id.count_number);
        theNumber = rnd.nextInt(1001);

        userGuessEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //Enter key Action
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    mainFunc();

                    return true;
                }
                return false;
            }
        });

        Button aboutButton = (Button)findViewById(R.id.about_button);
        aboutButton.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  new AlertDialog.Builder(MainActivity.this)
                          .setTitle("About")
                          .setMessage("Guess what? \nDeveloped by Seongyeop Jeong")
                          .setIcon(android.R.drawable.btn_minus)
                          .show();
              }
        });


        Button guessButton = (Button) findViewById(R.id.guess_button);
        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainFunc();
            }
        });

        Button resetButton = (Button) findViewById(R.id.reset_button);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theNumber = rnd.nextInt(1001);
                count = 0;
                lowGuess = 0;
                highGuess = 0;
                checkLose = false;
                checkWin = false;
                counterNum.setText(String.valueOf(count));
                chosenNumView.setText("0 ~ 1000");
                userGuessEditText.setText("");
            }
        });
        resetButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent( getApplicationContext(), ResultsActivity.class );
                intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP );

                intent.putExtra( "userGuess", String.valueOf(userGuess) );
                intent.putExtra( "comNumber", String.valueOf(theNumber) );
                intent.putExtra( "totalGuess", String.valueOf(count) );
                intent.putExtra( "highGuess", String.valueOf(highGuess) );
                intent.putExtra( "lowGuess", String.valueOf(lowGuess) );
                intent.putExtra( "totalSuperiorWin", String.valueOf(totalSuperiorWin) );
                intent.putExtra( "totalExcellentWin", String.valueOf(totalExcellentWin) );
                intent.putExtra( "totalLose", String.valueOf(totalLose) );
                startActivity( intent );

                Log.i("mainActivity", "LEAVE onLongClick()");
                return false;
            }
        });

    } // end of onCreate
} // end of the class
