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


public class FastFoods extends Fragment{
    ListView list;
    String[] web = {
            "BBQ Pizza",
            "Margarita Pizza",
            "Mexican Pizza",
            "Olive Mushroom Pizza",
            "Paneer Pizza",
    } ;
    Integer[] imageId = {
            R.drawable.menu_4bbqpizza,
            R.drawable.menu_4margaritapizza,
            R.drawable.menu_4mexicanpizza,
            R.drawable.menu_4olivemushroompizza,
            R.drawable.menu_4paneerpizza,

    };

    String[] price={"Rs.250","Rs.200","Rs.300","Rs.350","Rs.200"};
    Integer[] cost={250,200,300,350,200};
    public FastFoods() {
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
        View V=inflater.inflate(R.layout.fragment_fast_foods, container, false);
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