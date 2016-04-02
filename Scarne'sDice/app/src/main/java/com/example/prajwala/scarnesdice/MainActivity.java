package com.example.prajwala.scarnesdice;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    int useroverall=0,userturn=0,compoverall=0,compturn=0;
    Button roll,hold,reset;
    TextView score;
    ImageView dice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        roll=(Button)findViewById(R.id.roll);
        hold=(Button)findViewById(R.id.hold);
        reset=(Button)findViewById(R.id.reset);
        dice=(ImageView)findViewById(R.id.dice);
        score=(TextView)findViewById(R.id.score);
       roll.setOnClickListener(this);
        hold.setOnClickListener(this);
        reset.setOnClickListener(this);

    }
    public int helperdie()
    {
        Random random = new Random();
        int num = random.nextInt(6)+1;

        switch (num)
        {
            case 1:  dice.setImageResource(R.drawable.dice1);
                break;
            case 2:  dice.setImageResource(R.drawable.dice2);
                break;
            case 3:  dice.setImageResource(R.drawable.dice3);
                break;
            case 4:  dice.setImageResource(R.drawable.dice4);
                break;
            case 5:  dice.setImageResource(R.drawable.dice5);
                break;
            case 6:  dice.setImageResource(R.drawable.dice6);
                break;

        }
        return num;
    }
    public void onClick(View v){
        switch (v.getId())
        {
            case R.id.roll: int num=helperdie();
                        if(num!=1)
                        {
                            userturn=userturn+num;
                            score.setText("Your score:"+Integer.toString(useroverall)+" computer score:"+Integer.toString(compoverall)+" your turn score: "+Integer.toString(userturn));
                        }
                        else {
                            userturn = 0;
                            score.setText("Your score:" + Integer.toString(useroverall) + " computer score:" + Integer.toString(compoverall) + " your turn score: " + Integer.toString(userturn));
                            computerTurn();
                        }
                       break;

            case R.id.hold: useroverall=useroverall+userturn;
                            userturn=0;
                score.setText("Your score:"+Integer.toString(useroverall)+" computer score:"+Integer.toString(compoverall)+" your turn score: "+Integer.toString(userturn));
                            computerTurn();
                            break;

            case R.id.reset: useroverall=0;
                            userturn=0;
                            compoverall=0;
                            compturn=0;
                            score.setText("Your score:" + Integer.toString(useroverall) + " computer score:" + Integer.toString(compoverall) + " your turn score: " + Integer.toString(userturn));
                            break;

        }
    }
    public void computerTurn() {
        roll.setEnabled(false);
        hold.setEnabled(false);
       final Handler h = new Handler();

        Runnable r = new Runnable()
        {
            public void run() {
                //write here whaterver you want to repeat
                int num = helperdie();
                if (num != 1) {
                    if((compturn+num)<20) {
                        compturn = compturn + num;
                        score.setText("Computer round score: " + Integer.toString(compturn));
                        h.postDelayed(this, 1000);
                    }
                    else
                    {
                     helpercomp();
                    }
                }
             else
                {
                    compturn = 0;
                    score.setText("Computer rolled a one");
                    helpercomp();
            }

            }
        };
        h.postDelayed(r,0);
        /*while(compturn<20) {
        int num = helperdie();

        if (num != 1) {
            compturn = compturn + num;
            score.setText("Computer round score: " + Integer.toString(compturn));

        } else {
            compturn = 0;
            score.setText("Computer rolled a one");

            //break;
        }

        }*/


    }
   public void helpercomp()
   {

       compoverall=compoverall+compturn;
       compturn=0;
       roll.setEnabled(true);
       hold.setEnabled(true);
   }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
