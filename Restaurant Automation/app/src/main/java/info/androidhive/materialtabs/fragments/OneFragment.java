package info.androidhive.materialtabs.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import info.androidhive.materialtabs.R;
import info.androidhive.materialtabs.activity.Chef;
import info.androidhive.materialtabs.activity.Table;
import info.androidhive.materialtabs.activity.Waiter;

import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class OneFragment extends Fragment implements View.OnClickListener {
    Button logIn;
    EditText email, password;
    String Email, Password;
    Context ctx;
    String NAME=null, PASSWORD=null, EMAIL=null,ACCOUNTTYPE=null;

    public OneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ctx = container.getContext();
        View V = inflater.inflate(R.layout.fragment_one, container, false);

        password = (EditText)V.findViewById(R.id.editTextPassword);
        email = (EditText)V.findViewById(R.id.editTextEmail);

        logIn = (Button)V.findViewById(R.id.button_login);
        logIn.setOnClickListener(this);
        // Inflate the layout for this fragment
        return V;
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_one, container, false);
    }
    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.button_login)
        {
            Email = email.getText().toString();
            Password = password.getText().toString();

            BackGround b = new BackGround();
            b.execute(Email, Password);
        }
    }
    class BackGround extends AsyncTask<String, String,String> {

        @Override
        protected String doInBackground(String... params) {
            String email = params[0];
            String password = params[1];
            String data="";
            int tmp;

            try {
                URL url = new URL("http://resauto.16mb.com/login3.php");
                String urlParams = "email="+email+"&password="+password;
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                os.write(urlParams.getBytes());
                os.flush();
                os.close();

                InputStream is = httpURLConnection.getInputStream();
                while((tmp=is.read())!=-1){
                    data+= (char)tmp;
                }

                is.close();
                httpURLConnection.disconnect();

                return data;
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Exception: "+e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Exception: "+e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            String err=null;
            try {
                JSONObject root = new JSONObject(s);
                JSONObject user_data = root.getJSONObject("user_data");
                if(user_data.equals(null))
                {
                    Toast.makeText(ctx,"Wrong email/password",Toast.LENGTH_SHORT).show();
                }
                else {
                    NAME = user_data.getString("name");
                    PASSWORD = user_data.getString("password");
                    EMAIL = user_data.getString("email");
                    ACCOUNTTYPE = user_data.getString("accounttype");
                    Toast.makeText(ctx, "Logged in", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
                err = "Exception: "+e.getMessage();
            }
            /*Intent i = new Intent(ctx, Home.class);
            i.putExtra("name", NAME);
            i.putExtra("password", PASSWORD);
            i.putExtra("email", EMAIL);
            i.putExtra("err", err);
            startActivity(i);*/
            switch (ACCOUNTTYPE)
            {

                case "Chef": Intent i1 =new Intent(getActivity(),Chef.class);
                    i1.putExtra("name", NAME);
                    i1.putExtra("password", PASSWORD);
                    i1.putExtra("email", EMAIL);
                    i1.putExtra("err", err);
                    getActivity().startActivity(i1);break;

                case "Waiter": Intent i2 =new Intent(getActivity(),Waiter.class);
                    i2.putExtra("name", NAME);
                    i2.putExtra("password", PASSWORD);
                    i2.putExtra("email", EMAIL);
                    i2.putExtra("err", err);
                    getActivity().startActivity(i2);break;

                case "Customer": Intent i3 =new Intent(getActivity(),Table.class);
                    i3.putExtra("name", NAME);
                    i3.putExtra("password", PASSWORD);
                    i3.putExtra("email", EMAIL);
                    i3.putExtra("err", err);
                    getActivity().startActivity(i3);break;

            }

        }



    }





}
