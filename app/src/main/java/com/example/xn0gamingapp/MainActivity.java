package com.example.xn0gamingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //0:yellow 1:red
    int activeplayer=0;
    int[] gameState={2,2,2,2,2,2,2,2,2};
    int[][]winningpositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameactive=true;
public void coinnine(View view)
{
    ImageView counter = (ImageView) view;
    int tappedCounter = Integer.parseInt(counter.getTag().toString());
    if(gameState[tappedCounter]==2&& gameactive==true) {
        gameState[tappedCounter] = activeplayer;
        counter.setTranslationY(-1500);
        if (activeplayer == 0) {
            counter.setImageResource(R.drawable.yellow);
            activeplayer = 1;
        } else {
            counter.setImageResource(R.drawable.red);
            activeplayer = 0;
        }
        counter.animate().translationYBy(1500).setDuration(50);
        for (int[] winningposition : winningpositions) {
            if (gameState[winningposition[0]] == gameState[winningposition[1]] && gameState[winningposition[1]] == gameState[winningposition[2]] && gameState[winningposition[0]] != 2) {
//someone has won!!
                gameactive=false;
                String winner = "";
                if (activeplayer == 1) {
                    winner = "Yellow";
                } else {
                    winner = "Red";
                }
                Button playAgainButton=(Button)findViewById(R.id.playAgainButton);
                TextView winnerTextView=(TextView)findViewById(R.id.winnerTextView);
                winnerTextView.setText(winner+" has won!");
                playAgainButton.setVisibility(View.VISIBLE);
               winnerTextView.setVisibility(View.VISIBLE);
            }
        }
    }
}
    public void playAgain(View view){
        Button playAgainButton=(Button)findViewById(R.id.playAgainButton);
        TextView winnerTextView=(TextView)findViewById(R.id.winnerTextView);
        playAgainButton.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);
        GridLayout gridLayout=(GridLayout)findViewById(R.id.gridLayout);
        for (int i=0;i<gridLayout.getChildCount();i++)
        {
            ImageView counter=(ImageView)gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        for (int i=0;i<gameState.length;i++){
            gameState[i]=2;
        }
        activeplayer=0;
        gameactive=true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
