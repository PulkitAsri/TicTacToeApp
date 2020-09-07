package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    int[] gameState={2,2,2,2,2,2,2,2,2};//2-empty
    int emptyCount=9;
    int[][] winingPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    int activePlayer=0;//0-yellow 1-red
    boolean gameActive=true;

    public void dropIn(View view){

        ImageView counter=(ImageView)view;
        int tagcount= Integer.parseInt(counter.getTag().toString());
        if (gameState[tagcount]==2 && gameActive) {

            gameState[tagcount]=activePlayer;
            emptyCount--;
            counter.setTranslationY(-1500);

            if(activePlayer==0){
                counter.setImageResource(R.drawable.yellow);
                activePlayer=1;
            }
            else {
                counter.setImageResource(R.drawable.red);
                activePlayer=0;
            }
            counter.animate().translationYBy(1500).setDuration(300);
            for(int[] a:winingPositions){
                if (gameState[a[0]]==gameState[a[1]]&& gameState[a[1]]==gameState[a[2]]&& gameState[a[0]]!=2){
                    gameActive=false;
                    String winner="";
                    Button playAgain=(Button)findViewById(R.id.button);
                    TextView text =(TextView)findViewById(R.id.textView);
                    if (activePlayer==1) {
                        winner="Yellow";
                        text.setTextColor(Color.YELLOW );
                    }
                    else {
                        winner ="Red";
                        text.setTextColor(Color.RED );

                    }
                    //somewone has won!!!!
                    Toast.makeText(this, "Congratualations !! "+winner+" has Won!!", Toast.LENGTH_SHORT).show();

                    text.setVisibility(View.VISIBLE);
                    text.setText(winner+" has Won!!");

                    playAgain.setVisibility(View.VISIBLE);
                    return;
                }

                }
            if (emptyCount==0){
                    Toast.makeText(this, "Draw!!", Toast.LENGTH_SHORT).show();
                    Button playAgain=(Button)findViewById(R.id.button);
                    TextView text =(TextView)findViewById(R.id.textView);
                    text.setVisibility(View.VISIBLE);
                    text.setTextColor(Color.BLUE );
                    text.setText("Its A Draw");
                    playAgain.setVisibility(View.VISIBLE);
                    return;
            }
        }
        else
            Toast.makeText(this, "Sorry No Cheating Allowed XD\n(spot already occupied)", Toast.LENGTH_SHORT).show();
    }
    public void playAgain(View view){
        Button playAgain=(Button)findViewById(R.id.button);
        TextView text =(TextView)findViewById(R.id.textView);
        text.setVisibility(View.INVISIBLE);
        playAgain.setVisibility(View.INVISIBLE);
        text.setTextColor(Color.BLUE );

        GridLayout grid=(GridLayout)findViewById(R.id.gridLayout);
        for(int i=0;i < grid.getChildCount() ; i++){
            ImageView child=(ImageView)grid.getChildAt(i);
            child.setImageDrawable(null);
        }
        //2-empty
        Arrays.fill(gameState,2);
        activePlayer=0;//0-yellow 1-red
        gameActive=true;
        emptyCount=9;

    }
    public void sorry(View view){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}