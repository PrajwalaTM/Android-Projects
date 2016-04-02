package info.androidhive.materialtabs.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
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

import info.androidhive.materialtabs.R;

public class Clear extends AppCompatActivity implements View.OnClickListener {

    Button table1,table2,table3,table4,table5,table6,table7,table8,done;
    String data="";
    private JSONArray tables = null;
    private static final String JSON_ARRAY ="result";
    private static final String TABLENUM = "TableNumber";
    private static final String COLOUR= "Colour";
    private static final String BILL = "Bill";
    Drawable table_green,table_red;
    public static final String MY_JSON ="MY_JSON";

    public static final String JSON_URL = "http://resauto.16mb.com/gettablelayout.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clear);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        table1=(Button)findViewById(R.id.table1);
        table2=(Button)findViewById(R.id.table2);
        table3=(Button)findViewById(R.id.table3);
        table4=(Button)findViewById(R.id.table4);
        table5=(Button)findViewById(R.id.table5);
        table6=(Button)findViewById(R.id.table6);
        table7=(Button)findViewById(R.id.table7);
        table8=(Button)findViewById(R.id.table8);
        table1.setOnClickListener(this);
        table2.setOnClickListener(this);
        table3.setOnClickListener(this);
        table4.setOnClickListener(this);
        table5.setOnClickListener(this);
        table6.setOnClickListener(this);
        table7.setOnClickListener(this);
        table8.setOnClickListener(this);
        table_green=getResources().getDrawable(R.drawable.image_green);
        table_red=getResources().getDrawable(R.drawable.image_red);
        done=(Button)findViewById(R.id.done);
        done.setOnClickListener(this);
        getJSON(JSON_URL);


    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        String num;Intent x;
        BackGround b;
        switch(id) {

            case R.id.table1:
                table1.setBackground(table_green);
                Toast.makeText(this, "Table Cleared!", Toast.LENGTH_SHORT).show();
                table1.setEnabled(true);
                num = getTableNum(R.id.table1);
                b = new BackGround();
                b.execute(num, "green"); break;



            case R.id.table2:
                table2.setBackground(table_green);
                Toast.makeText(this, "Table Cleared !", Toast.LENGTH_SHORT).show();
                table2.setEnabled(true);
                num = getTableNum(R.id.table2);
                b = new BackGround();
                b.execute(num, "green");

                break;
            case R.id.table3:
                table3.setBackground(table_green);
                Toast.makeText(this, "Table Cleared !", Toast.LENGTH_SHORT).show();
                table3.setEnabled(true);
                num = getTableNum(R.id.table3);
                b = new BackGround();
                b.execute(num, "green");

                break;
            case R.id.table4:
                table4.setBackground(table_green);
                Toast.makeText(this, "Table Cleared !", Toast.LENGTH_SHORT).show();
                table4.setEnabled(true);
                num = getTableNum(R.id.table4);
                b = new BackGround();
                b.execute(num, "green");

                break;
            case R.id.table5:
                table5.setBackground(table_green);
                Toast.makeText(this, "Table Cleared !", Toast.LENGTH_SHORT).show();
                table5.setEnabled(true);
                num = getTableNum(R.id.table5);
                b = new BackGround();
                b.execute(num, "green");

               break;
            case R.id.table6:
                table6.setBackground(table_green);
                Toast.makeText(this, "Table Cleared !", Toast.LENGTH_SHORT).show();
                table6.setEnabled(true);
                num = getTableNum(R.id.table6);
                b = new BackGround();
                b.execute(num, "green");

               break;
            case R.id.table7:
                table7.setBackground(table_green);
                Toast.makeText(this, "Table Cleared !", Toast.LENGTH_SHORT).show();
                table7.setEnabled(true);
                num = getTableNum(R.id.table7);
                b = new BackGround();
                b.execute(num, "green");
                break;

            case R.id.table8:
                table8.setBackground(table_green);
                Toast.makeText(this, "Table Cleared !", Toast.LENGTH_SHORT).show();
                table8.setEnabled(true);
                num = getTableNum(R.id.table8);
                b = new BackGround();
                b.execute(num, "green");

               break;
            case R.id.done: startActivity(new Intent(this,Waiter.class));break;

        }

    }
    class BackGround extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String number = params[0];
            String color = params[1];
            String data = "";
            int tmp;

            try {
                URL url = new URL("http://resauto.16mb.com/settablelayout.php");
                String urlParams = "number=" + number + "&color=" + color;

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
            Toast.makeText(Clear.this, s, Toast.LENGTH_LONG).show();
        }
    }


    String getTableNum(int id)
    {
        switch (id)
        {
            case R.id.table1: return "1";
            case R.id.table2: return "2";
            case R.id.table3: return "3";
            case R.id.table4: return "4";
            case R.id.table5: return "5";
            case R.id.table6: return "6";
            case R.id.table7: return "7";
            case R.id.table8: return "8";
        }
        return "0";
    }

    public void getJSON(String url) {
        class GetJSON extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Clear.this, "Please Wait...", null, true, true);
            }

            @Override
            protected String doInBackground(String... params) {

                String uri = params[0];

                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while((json = bufferedReader.readLine())!= null){
                        sb.append(json+"\n");
                    }

                    return sb.toString().trim();

                } catch (Exception e){
                    return null;
                }

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                data=s;
                extractJSON();
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute(url);
    }
    private void extractJSON(){

        try {
            JSONObject jsonObject = new JSONObject(data);
            tables = jsonObject.getJSONArray(JSON_ARRAY);
            Button b;

            for (int i = 0; i < tables.length(); i++) {
                JSONObject object = tables.getJSONObject(i);
                String tablenum=object.getString(TABLENUM);
                String colour=object.getString(COLOUR);
                String bill=object.getString(BILL);
                switch (tablenum){
                    case "1" : b=(Button)findViewById(R.id.table1);
                        setColour(b,colour);
                        break;
                    case "2" : b=(Button)findViewById(R.id.table2);
                        setColour(b,colour);
                        break;
                    case "3" : b=(Button)findViewById(R.id.table3);
                        setColour(b,colour);
                        break;
                    case "4" : b=(Button)findViewById(R.id.table4);
                        setColour(b,colour);
                        break;
                    case "5" : b=(Button)findViewById(R.id.table5);
                        setColour(b,colour);
                        break;
                    case "6" : b=(Button)findViewById(R.id.table6);
                        setColour(b,colour);
                        break;
                    case "7" : b=(Button)findViewById(R.id.table7);
                        setColour(b,colour);
                        break;
                    case "8" : b=(Button)findViewById(R.id.table8);
                        setColour(b,colour);
                        break;

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void setColour(Button temp,String colour){
        if(colour.equals("green"))
            temp.setBackgroundResource(R.drawable.image_green);
        else if(colour.equals("red")){
            temp.setBackgroundResource(R.drawable.image_red);
        }
    }
}
