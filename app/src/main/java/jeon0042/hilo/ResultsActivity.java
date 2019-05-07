/**
 *  The purpose of this game is to guess exact number by comparing random number.
 *  @author Seongyeop + Jeong (jeon0042@algonquinlive.com)
 */

package jeon0042.hilo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {
    private TextView userGuessResult;
    private TextView comNumberResult;
    private TextView totalGuessResult;
    private TextView highGuessResult;
    private TextView lowGuessResult;
    private TextView supWinResult;
    private TextView excWinResult;
    private TextView loseResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // after you login, show the string of name and password
        userGuessResult = findViewById(R.id.user_guess_result);
        comNumberResult = findViewById(R.id.com_number_result);
        totalGuessResult = findViewById(R.id.total_guess_result);
        highGuessResult = findViewById(R.id.high_guess_result);
        lowGuessResult = findViewById(R.id.low_guess_result);
        supWinResult = findViewById(R.id.superior_win_result);
        excWinResult = findViewById(R.id.excellent_win_result);
        loseResult = findViewById(R.id.lose_result);

        // Get the bundle of extras that was sent to this activity
        Bundle bundle = getIntent().getExtras();
        if ( bundle != null ) {
            String userGuessFromMainActivity = bundle.getString( "userGuess" );
            String comNumberFromMainActivity = bundle.getString( "comNumber" );
            String totalGuessFromMainActivity = bundle.getString( "totalGuess" );
            String highGuessFromMainActivity = bundle.getString( "highGuess" );
            String lowGuessFromMainActivity = bundle.getString( "lowGuess" );
            String totalSuperiorWinFromMainActivity = bundle.getString( "totalSuperiorWin" );
            String totalExcellentWinFromMainActivity = bundle.getString( "totalExcellentWin" );
            String totalLoseFromMainActivity = bundle.getString( "totalLose" );

            userGuessResult.setText( userGuessFromMainActivity );
            comNumberResult.setText( comNumberFromMainActivity );
            totalGuessResult.setText( totalGuessFromMainActivity );
            highGuessResult.setText( highGuessFromMainActivity );
            lowGuessResult.setText( lowGuessFromMainActivity );
            supWinResult.setText( totalSuperiorWinFromMainActivity );
            excWinResult.setText( totalExcellentWinFromMainActivity );
            loseResult.setText( totalLoseFromMainActivity );

        }
    }
}
