package info.androidhive.materialtabs.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import info.androidhive.materialtabs.R;
import info.androidhive.materialtabs.activity.ViewMenu;


public class SouthIndian extends Fragment{
    ListView list;
    String[] web = {
            "Idli Vada",
            "Masala Dosa",
            "Paper Dosa",
            "Sambar Idly",
            "Upma",
            "Uthappam"
    } ;
    Integer[] imageId = {
            R.drawable.menu_6idlyvada,
            R.drawable.menu_6masaladosa,
            R.drawable.menu_6paperdosa,
            R.drawable.menu_6sambaridly,
            R.drawable.menu_6upma,
            R.drawable.menu_6utappam,

    };
    String[] price={"Rs.25","Rs.45","Rs.40","Rs.30","Rs.20","Rs.35"};
    Integer[] cost={25,45,40,30,20,35};
    public SouthIndian() {
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
        View V=inflater.inflate(R.layout.fragment_south_indian, container, false);
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
                cdd.show();
            }
        });
        return V;
    }

}