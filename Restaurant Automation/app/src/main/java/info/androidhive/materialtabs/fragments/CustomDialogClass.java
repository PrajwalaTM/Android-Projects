package info.androidhive.materialtabs.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import info.androidhive.materialtabs.R;
import info.androidhive.materialtabs.activity.Table;
import info.androidhive.materialtabs.activity.ViewMenu;

/**
 * Created by Prajwala on 02-04-2016.
 */
public class CustomDialogClass extends Dialog implements View.OnClickListener {
    public Activity c;
    public Dialog d;
    public Button order, cancel;
    EditText quantity;
    public static String q;
    public static int x;

    public CustomDialogClass(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        order = (Button) findViewById(R.id.btn_order);
        cancel = (Button) findViewById(R.id.cancel);
        quantity = (EditText) findViewById(R.id.editText2);
        order.setOnClickListener(this);
        cancel.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_order:
                q = quantity.getText().toString();
                final String num= Table.chosentable;
                x = Integer.parseInt(q);
                if (x > 0) {
                    ViewMenu.total = ViewMenu.total + x * ViewMenu.y;
                }
                else
                x=0;
                BackGround b = new BackGround();
                b.execute(num, Integer.toString(ViewMenu.total));

                quantity.clearComposingText();
                ViewMenu.y=0;x=0;
                Toast.makeText(getContext(),"You ordered "+q+" "+ViewMenu.item,Toast.LENGTH_SHORT).show();
                dismiss();
                break;
            case R.id.cancel:
                dismiss();
                break;
        }
        dismiss();
    }

    class BackGround extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String number = params[0];
            String amount = params[1];
            String data = "";
            int tmp;

            try {
                URL url = new URL("http://resauto.16mb.com/updatebill.php");
                String urlParams = "number=" + number + "&amount=" + amount;

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

        }
    }
}
