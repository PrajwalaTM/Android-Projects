package com.example.prajwala.quizapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class QuestionPage4 extends ActionBarActivity {

    int click=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_page4);
    }
    public void wrong(View v){
        click++;
        Toast.makeText(QuestionPage4.this, "Sorry, it's wrong!", Toast.LENGTH_SHORT).show();
        TextView score4=(TextView)findViewById(R.id.score4);
        score4.setText("Current score: " + MainActivity.score);


    }
    public void correct(View v)
    {
        click++;
        Toast.makeText(QuestionPage4.this,"Yes! You got it right!",Toast.LENGTH_SHORT).show();
        if(click==1)
        {MainActivity.score+=10;}
        TextView score4=(TextView)findViewById(R.id.score4);
        score4.setText("Current Score: "+MainActivity.score);
    }
    public void movenext(View v){
        Intent i = new Intent(this,QuestionPage5.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_question_page4, menu);
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
