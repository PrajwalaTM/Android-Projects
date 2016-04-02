package info.androidhive.materialtabs.activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import info.androidhive.materialtabs.R;

public class Customer extends AppCompatActivity implements AdapterView.OnItemClickListener,View.OnClickListener {
    private DrawerLayout drw;
    private ListView lst;
    private String []options;
    Button viewmenu,paybill,feedback;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bundle=getIntent().getExtras();
        drw=(DrawerLayout) findViewById(R.id.drw);
        options=getResources().getStringArray(R.array.options);
        lst=(ListView) findViewById(R.id.drawerlist);
        lst.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, options));
        lst.setOnItemClickListener(this);
        viewmenu=(Button)findViewById(R.id.viewmenu);
        paybill=(Button)findViewById(R.id.done);
        feedback=(Button)findViewById(R.id.feedback);
        viewmenu.setOnClickListener(this);
        paybill.setOnClickListener(this);
        feedback.setOnClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // TODO Auto-generated method stub
        switch(position)
        {
            case 0:
                Intent i1=new Intent(this,About.class);
                //i1.putExtra("number",bundle.getString("number"));
                startActivity(i1);
                break;
            case 1:
                Intent i2=new Intent(this,Help.class);
                //i2.putExtra("number",bundle.getString("number"));
                startActivity(i2);
                break;

            case 2:
                Intent i3=new Intent(this,Contact.class);
                //i3.putExtra("number",bundle.getString("number"));
                startActivity(i3);
                break;
            case 3:
                try
                { Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_SUBJECT, "Android App:Restaurant Automation");
                    String sAux = "\nSize : 3.21 MB\nPlease install the above Application\n";
                    sAux = sAux + "https://drive.google.com/file/d/0B4jcIK7SWDu6R2U0RUQtV2ZZWHM/view?usp=sharing";
                    i.putExtra(Intent.EXTRA_TEXT, sAux);
                    startActivity(Intent.createChooser(i, "Choose one"));
                }
                catch(Exception e)
                { //e.toString();
                }
                break;
            case 4:
                Toast.makeText(getApplicationContext(), "Version 1.0.0\n© Copyright 2015 \n",
                        Toast.LENGTH_LONG).show();
                break;
            case 5:
                finish();
                break;
        }
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch(id)
        {
            case R.id.viewmenu:Intent i1=new Intent(this,ViewMenu.class);
                               // i1.putExtra("number",bundle.getString("number"));
                startActivity(i1);
                break;
            case R.id.done: Intent i2=new Intent(this,PayBill.class);
                //i2.putExtra("number",bundle.getString("number"));
                startActivity(i2);
                break;
            case R.id.feedback: Intent i3=new Intent(this,FeedBack.class);
               // i3.putExtra("number",bundle.getString("number"));
                startActivity(i3);
                break;
        }
    }
}

