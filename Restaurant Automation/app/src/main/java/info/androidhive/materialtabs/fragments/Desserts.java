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


public class Desserts extends Fragment{
    ListView list;
    String[] web = {
            "Butterscotch Nutty Buddy",
            "Chocolate milkshake",
            "Chocolate Sundae",
            "Cold Coffee",
            "Pineapple Delight",
            "Strawberry Brownie Sundae",

    } ;
    Integer[] imageId = {
            R.drawable.menu_7butterscothnuttybuddy,
            R.drawable.menu_7chocolatemilkshake,
            R.drawable.menu_7chocolatesundae,
            R.drawable.menu_7coldcoffe,
            R.drawable.menu_7pineappledelight,
            R.drawable.menu_7strawberrybrowniesundae

    };
    String[] price={"Rs.110","Rs.90","Rs.120","Rs.85","Rs.100","Rs.130"};
    Integer[] cost={110,90,120,85,100,130};
    public Desserts() {
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
        View V=inflater.inflate(R.layout.fragment_desserts, container, false);
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