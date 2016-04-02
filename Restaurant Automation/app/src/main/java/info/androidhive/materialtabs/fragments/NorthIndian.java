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


public class NorthIndian extends Fragment{
    ListView list;
    String[] web = {
            "Aloo Paratha",
            "Chapati",
            "Chole Bhatura",
            "Dal Makhani",
            "Dum Aloo",
            "Kadai Paneer",
            "Palak Paneer",
            "Paneer Butter Masala",
            "Rumali Roti",
            "Veg Biriyani",
            "Veg Kolhapuri",
            "Veg Pulao"
    } ;
    Integer[] imageId = {
            R.drawable.menu_5alooparatha,
            R.drawable.menu_5chapati,
            R.drawable.menu_5cholebhature,
            R.drawable.menu_5dalmakhani,
            R.drawable.menu_5dumaloo,
            R.drawable.menu_5kadaipaneer,
            R.drawable.menu_5palakpaneer,
            R.drawable.menu_5paneerbuttermasala,
            R.drawable.menu_5rumaliroti,
            R.drawable.menu_5vegbiryani,
            R.drawable.menu_5vegkolhapuri,
            R.drawable.menu_5vegpulao

    };
    String price[]={"Rs.40","Rs.20","Rs.50","Rs.60","Rs.55","Rs.100","Rs.90","Rs.90","Rs.55","Rs.70","Rs.65","Rs.55"};
    Integer cost[]={40,20,50,60,55,100,90,90,55,70,65,55};
    public NorthIndian() {
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
        View V=inflater.inflate(R.layout.fragment_north_indian, container, false);
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