package info.androidhive.materialtabs.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.gms.iid.InstanceID;

import java.io.IOException;

import info.androidhive.materialtabs.R;

public class Waiter extends AppCompatActivity implements View.OnClickListener{
     Button cleartable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        cleartable=(Button)findViewById(R.id.clear);
        cleartable.setOnClickListener(this);


        String iid = InstanceID.getInstance(this).getId();
        String authorizedEntity = "restaurantautomation-1261";
        String scope = "GCM"; // e.g. communicating using GCM, but you can use any
        // URL-safe characters up to a maximum of 1000, or
        // you can also leave it blank.
        try {
            String token = InstanceID.getInstance(this).getToken(authorizedEntity, scope);
        }
        catch (IOException e){

        }
        
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch(id)
        {
            case R.id.clear:
                Intent i1=new Intent(this,Clear.class);startActivity(i1);break;

        }
    }
}
