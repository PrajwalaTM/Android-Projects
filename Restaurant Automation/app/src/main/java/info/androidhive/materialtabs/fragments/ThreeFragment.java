package info.androidhive.materialtabs.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import info.androidhive.materialtabs.R;


public class ThreeFragment extends Fragment implements View.OnClickListener {

    EditText name, email, password;
    RadioGroup accounttype;
    RadioButton radioButton;
    Button createacc;
    String Name, Password, Email, AccountType;
    Context ctx;
    public ThreeFragment() {
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
        View V = inflater.inflate(R.layout.fragment_three, container, false);

        name = (EditText)V.findViewById(R.id.editTextName);
        password = (EditText)V.findViewById(R.id.editTextPassword);
        email = (EditText)V.findViewById(R.id.editTextEmail);
        accounttype = (RadioGroup)V.findViewById(R.id.accounttype);
        createacc = (Button)V.findViewById(R.id.buttonRegister);
        createacc.setOnClickListener(this);
        // Inflate the layout for this fragment
        return V;
        //return inflater.inflate(R.layout.fragment_two, container, false);
    }
    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.buttonRegister){
        //Toast.makeText(getActivity().getApplicationContext(), "please wait", Toast.LENGTH_LONG).show();
            Name = name.getText().toString();
            Password = password.getText().toString();
            Email = email.getText().toString();
            switch (accounttype.getCheckedRadioButtonId()) {
                case R.id.chefRadio:
                    AccountType = "Chef";
                    break;
                case R.id.serverRadio:
                    AccountType = "Waiter";
                    break;
                case R.id.customerRadio:
                    AccountType = "Customer";
                    break;

            }
            BackGround b = new BackGround();
            b.execute(Name, Password, Email,AccountType);
        }
    }

    class BackGround extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String name = params[0];
            String password = params[1];
            String email = params[2];
            String accounttype = params[3];
            String data = "";
            int tmp;

            try {
                URL url = new URL("http://resauto.16mb.com/register2.php");
                String urlParams = "name=" + name + "&password=" + password + "&email=" + email+"&accounttype="+accounttype;

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
            Toast.makeText(getActivity(), s, Toast.LENGTH_LONG).show();
        }


    }

}
