package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {

    TextView turnDisp;

    private String p1turn = "PLAYER 1\'s TURN";
    private String p2turn = "PLAYER 2\'s TURN";

    private Button buttons[][] = new Button[3][3];

    private boolean player1Turn = true;

    private int rounds = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        turnDisp = (TextView) findViewById(R.id.turn);

        if(player1Turn)
            turnDisp.setText(p1turn);
        else
            turnDisp.setText(p2turn);

        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++) {
                String button = "btn" + i + j;
                int buttonid = getResources().getIdentifier(button, "id", getPackageName());
                buttons[i][j] = findViewById(buttonid);
                buttons[i][j].setOnClickListener(this);
            }
        }
    }

    @Override
    public void onClick(View v) {

        if(player1Turn)
            turnDisp.setText(p2turn);
        else
            turnDisp.setText(p1turn);

        if(!((Button) v).getText().toString().equals(""))
        {
            Toast.makeText(this, "INVALID MOVE !", Toast.LENGTH_SHORT).show();
            return;
        }

        if(player1Turn)
        {
            ((Button) v).setText("O");
        }
        else
        {
            ((Button) v).setText("X");
        }

        rounds++;

        if(checkForWin())
        {
            if(player1Turn)
            {
                disableButtons();
                turnDisp.setText("Player 1 WON");
                Toast.makeText(this,"Player 1 WON !!!", Toast.LENGTH_SHORT).show();
            }
            else
            {
                disableButtons();
                turnDisp.setText("Player 2 WON !");
                Toast.makeText(this, "Player 2 WON !!!", Toast.LENGTH_SHORT).show();
            }
        }
        else if(rounds == 9)
        {
            disableButtons();
            Toast.makeText(this, "DRAW", Toast.LENGTH_SHORT).show();
            turnDisp.setText("DRAW");
        }
        else
            player1Turn = !player1Turn;
    }


    private boolean checkForWin()
    {
        String entries[][] = new String[3][3];

        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                entries[i][j] = buttons[i][j].getText().toString();
            }
        }

        for(int i=0;i<3;i++)
        {
            if(entries[i][0].equals(entries[i][1]) && entries[i][1].equals(entries[i][2]) && !entries[i][0].equals("")) {
                return true;
            }
        }

        for(int i=0;i<3;i++)
        {
            if(entries[0][i].equals(entries[1][i]) && entries[1][i].equals(entries[2][i]) && !entries[0][i].equals("")) {
                return true;
            }
        }

        if(entries[0][0].equals(entries[1][1]) && entries[1][1].equals(entries[2][2]) && !entries[0][0].equals(""))
            return true;

        if(entries[0][2].equals(entries[1][1]) && entries[1][1].equals(entries[2][0]) && !entries[0][2].equals(""))
            return true;

        return false;
    }

    private void disableButtons()
    {
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    private void enableButtons()
    {
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                buttons[i][j].setEnabled(true);
            }
        }
    }

    public void resetGame(View view) {
        enableButtons();
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                buttons[i][j].setText("");
            }
        }
        turnDisp.setText(p1turn);
        rounds=0;
    }
}