package info.androidhive.materialtabs.fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import info.androidhive.materialtabs.R;
import info.androidhive.materialtabs.activity.ViewMenu;


public class Soups extends Fragment {
    ListView list;
    //public static int y=0;

    String[] web = {
            "Hot and Sour Soup",
            "Mixed Veg Soup",
            "Sweet corn Soup",
            "Tomato Soup",
            "Veg Noodle Soup",
            "Veg Clear Soup",
            "Schezwan Noodle Soup",
            "Cabbage Soup",
            "Mexican Black Bean Soup",
    } ;
    Integer[] imageId = {
            R.drawable.menu_1hotandsoursoup,
            R.drawable.menu_1mixedvegsoup,
            R.drawable.menu_1sweetcornsoup,
            R.drawable.menu_1tomatosoup,
            R.drawable.menu_1vegnoodlesoup,
            R.drawable.menu_1vegclearsoup,
            R.drawable.menu_1schezwannoodlesoup,
            R.drawable.menu_1cabbagesoup,
            R.drawable.menu_1mexicanblackbeansoup,

    };
    String[] price={"Rs.70","Rs.65","Rs.70","Rs.60","Rs.70","Rs.60","Rs.75","Rs.70","Rs.80"

    };
    Integer[] cost={70,65,70,60,70,60,75,70,80};


    public Soups() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View V=inflater.inflate(R.layout.fragment_soups, container, false);
        CustomList adapter = new
                CustomList(getActivity(), web, imageId,price);

        list = (ListView)V.findViewById(R.id.list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getActivity(), "You Clicked at " + web[+position], Toast.LENGTH_SHORT).show();
                CustomDialogClass cdd=new CustomDialogClass(getActivity());
                ViewMenu.y=cost[position];
                ViewMenu.item=web[position];
                cdd.show();


            }
        });
        return V;
    }

    }





