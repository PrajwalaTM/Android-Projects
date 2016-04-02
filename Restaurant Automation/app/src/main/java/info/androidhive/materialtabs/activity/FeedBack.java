package info.androidhive.materialtabs.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.style.BackgroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.graphics.drawable.Drawable;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import info.androidhive.materialtabs.R;

public class FeedBack extends AppCompatActivity implements View.OnClickListener {
    Button button_submit, cancel;
    EditText feedback;
    public String feed,email;
    Bundle bundle;
    ImageButton star1,star2,star3,star4,star5;
    Drawable gold,silver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bundle = getIntent().getExtras();
        feedback = (EditText) findViewById(R.id.feedback);
        button_submit = (Button) findViewById(R.id.button_submit);
        cancel = (Button) findViewById(R.id.cancelfeedback);

        gold=getResources().getDrawable(R.drawable.gold);
        silver=getResources().getDrawable(R.drawable.silver);
        star1=(ImageButton)findViewById(R.id.star1);
        star2=(ImageButton)findViewById(R.id.star2);
        star3=(ImageButton)findViewById(R.id.star3);
        star4=(ImageButton)findViewById(R.id.star4);
        star5=(ImageButton)findViewById(R.id.star5);
        star1.setBackground(silver);
        star2.setBackground(silver);
        star3.setBackground(silver);
        star4.setBackground(silver);
        star5.setBackground(silver);

        star1.setOnClickListener(this);
        star2.setOnClickListener(this);
        star3.setOnClickListener(this);
        star4.setOnClickListener(this);
        star5.setOnClickListener(this);

        button_submit.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.cancelfeedback) {
            Intent i = new Intent(this, Customer.class);
            //i.putExtra("number", bundle.getString("number"));
            startActivity(i);
        }
        else if (id == R.id.button_submit) {
            feed = feedback.getText().toString();
            if (feed.equals("")) {
                Toast.makeText(FeedBack.this, "Sorry. Empty feedback not accepted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(FeedBack.this, "Thank you for your feedback!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, Customer.class);
              //  i.putExtra("number", bundle.getString("number"));
                //email=bundle.getString("email");
                BackGround b = new BackGround();
                //b.execute(feed,email);
                startActivity(i);

            }
        }

        else{
            switch(id){
                case R.id.star1:
                    star1.setBackground(gold);
                    star2.setBackground(silver);
                    star3.setBackground(silver);
                    star4.setBackground(silver);
                    star5.setBackground(silver);
                    break;

                case R.id.star2:
                    star1.setBackground(gold);
                    star2.setBackground(gold);
                    star3.setBackground(silver);
                    star4.setBackground(silver);
                    star5.setBackground(silver);
                    break;

                case R.id.star3:
                    star1.setBackground(gold);
                    star2.setBackground(gold);
                    star3.setBackground(gold);
                    star4.setBackground(silver);
                    star5.setBackground(silver);
                    break;

                case R.id.star4:
                    star1.setBackground(gold);
                    star2.setBackground(gold);
                    star3.setBackground(gold);
                    star4.setBackground(gold);
                    star5.setBackground(silver);
                    break;

                case R.id.star5:
                    star1.setBackground(gold);
                    star2.setBackground(gold);
                    star3.setBackground(gold);
                    star4.setBackground(gold);
                    star5.setBackground(gold);
                    break;

            }
        }
    }

    class BackGround extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String emailid = params[1];
            String feedback = params[0];
            String data = "";
            int tmp;

            try {
                URL url = new URL("http://resauto.16mb.com/sendfeedback.php");
                String urlParams = "email=" + emailid + "feedback=" + feedback;
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                os.write(urlParams.getBytes());
                os.flush();
                os.close();
                InputStream is = httpURLConnection.getInputStream();
                while ((tmp = is.read()) != -1) {
                    data += (char) tmp;
                }
                is.close();
                httpURLConnection.disconnect();

                return data;

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            if (s.equals("")) {
                s = "Data saved successfully.";
            }
            //else
            //s="Not saved";
            Toast.makeText(FeedBack.this, s, Toast.LENGTH_LONG).show();
        }
    }
}
