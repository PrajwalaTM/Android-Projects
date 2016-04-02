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


public class QuickBites extends Fragment{

    ListView list;
    String[] web = {
            "Baby Corn Manchuria",
            "French Fries",
            "Paneer Tikka",
            "Spring Rolls",
            "Veg Grilled Sandwich",
            "Veg Manchuria",
            "Gobi Manchuria",
            "Cheese Grilled Sandwich",
            "Veg Puff",
            "Paneer Puff",
            "Chilli Paneer",
            "Veg Momos"
    } ;
    Integer[] imageId = {
            R.drawable.menu_2babycornmanchuria,
            R.drawable.menu_2frenchfries,
            R.drawable.menu_2paneertikka,
            R.drawable.menu_2springrolls,
            R.drawable.menu_2veggrilledsandwich,
            R.drawable.menu_2vegmanchuria,
            R.drawable.menu_2gobimanchurian,
            R.drawable.menu_2cheesegrilledsandwich,
            R.drawable.menu_2vegpuff,
            R.drawable.menu_2paneerpuff,
            R.drawable.menu_2chillipanner,
            R.drawable.menu_2vegmomos
    };
    String price[]={"Rs.110","Rs.90","Rs.110","Rs.100","Rs.60","Rs.80","Rs.80","Rs.70","Rs.25","Rs.40","Rs.90","Rs.80"};
    Integer cost[]={110,90,110,100,60,80,80,75,25,40,90,80};

    public QuickBites() {
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
        View V=inflater.inflate(R.layout.fragment_quick_bites, container, false);
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