package info.androidhive.materialtabs.activity;

import info.androidhive.materialtabs.R;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.sql.StatementEvent;
public class PayBill extends AppCompatActivity implements View.OnClickListener {
    Bundle bundle;
    Button done,ordermore;
    TextView billAmount;
    String num,data="";
    Bundle extras;
    private JSONArray bill = null;
    private static final String JSON_ARRAY ="result";
    private static final String TABLENUM = "TableNumber";
    private static final String COLOUR= "Colour";
    private static final String BILL = "Bill";
    public static final String JSON_URL = "http://resauto.16mb.com/paybill.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_bill);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String bill=Integer.toString(ViewMenu.total);
        num=Table.chosentable;
        done=(Button)findViewById(R.id.done);
        ordermore=(Button)findViewById(R.id.ordermore);
        billAmount=(TextView)findViewById(R.id.billtext);
        billAmount.setText(bill);

        ordermore.setOnClickListener(PayBill.this);
        done.setOnClickListener(PayBill.this);

        //bundle=getIntent().getExtras();

    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id==R.id.ordermore){
            Intent i=new Intent(this,ViewMenu.class);
           // i.putExtra("number",num);
            startActivity(i);
        }
        if(id==R.id.done){
            BackGround b=new BackGround();
            b.execute(num);
            ViewMenu.total=0;
        }

    }
    class BackGround extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String num = params[0];
            String data = "";
            int tmp;
            try {
                URL url = new URL("http://resauto.16mb.com/paybill.php");
                String urlParams = "number="+num;
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
            Toast.makeText(PayBill.this, s, Toast.LENGTH_LONG).show();
        }
    }


}

