package com.example.prajwala.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Iterator;

public class MainActivity extends Activity {

    EditText name, password;
    String Name, Password;
    Context ctx = this;
    String NAME = null, PASSWORD = null, EMAIL = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        name = (EditText) findViewById(R.id.main_name);
        password = (EditText) findViewById(R.id.main_password);
    }

    public void main_register(View v) {
        startActivity(new Intent(this, Register.class));
    }

    public void main_login(View v) {
        Name = name.getText().toString();
        Password = password.getText().toString();
        BackGround b = new BackGround();
        b.execute(Name, Password);
    }

    class BackGround extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String name = params[0];
            String password = params[1];
            String data = "";
            int tmp;

            try {
                URL url = new URL("http://resauto.16mb.com/login2.php");
                String urlParams = "name=" + name + "&password=" + password;
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
            String err = null;
            try {
                JSONObject root = new JSONObject(s);
                JSONObject user_data = root.getJSONObject("user_data");
                //JSONArray user=new JSONArray(root.getJSONArray("user_data"));
                //JSONObject user_data=new JSONObject(user.toString());
                //JSONArray array=root.getJSONArray("user_data");
                NAME=user_data.getString("name");
                EMAIL=user_data.getString("email");
                PASSWORD=user_data.getString("password");






            }
            catch (JSONException e) {
                e.printStackTrace();
                err = "Exception: " + e.getMessage();
            }
            Intent i = new Intent(ctx, Home.class);
            i.putExtra("name", NAME);
            i.putExtra("password", PASSWORD);
            i.putExtra("email", EMAIL);
            i.putExtra("err", err);
            startActivity(i);


        }

    }
}