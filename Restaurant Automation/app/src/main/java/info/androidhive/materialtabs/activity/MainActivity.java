package info.androidhive.materialtabs.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;

import java.util.Timer;
import java.util.TimerTask;

import info.androidhive.materialtabs.R;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    ProgressDialog progress,bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //progress = ProgressDialog.show(this, "Loading",
          //      "Please Wait...", true);
        //WaitTime wait = new WaitTime();
        //wait.execute();
        //final ProgressBar pbHeaderProgress = (ProgressBar) findViewById(R.id.pbHeaderProgress);
        //bar = (ProgressBar) this.findViewById(R.id.progressBar);
        //new ProgressTask().execute();
        Thread t = new Thread(){
            public void run(){
                try{
                    sleep(2000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent i = new Intent(MainActivity.this,Authentication.class);
                    startActivity(i);
                }
            }
        };
        t.start();

    }

    private class WaitTime extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
            setProgressBarIndeterminateVisibility(true);
           // progress.show();
        }
        protected void onPostExecute() {
           // progress.dismiss();
            Intent i=new Intent(MainActivity.this,Authentication.class);
            startActivity(i);
        }

        @Override
        protected void onCancelled() {
            //progress.dismiss();
            super.onCancelled();
        }

        @Override
        protected Void doInBackground(Void... params) {
            long delayInMillis = 4000;
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    // progress.dismiss();
                    //setProgressBarIndeterminateVisibility(false);
                }
            }, delayInMillis);
            return null;
        }
    }


}
