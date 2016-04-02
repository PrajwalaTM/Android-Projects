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


public class Chats extends Fragment{
    ListView list;
    String[] web = {
            "Bhel Puri",
            "Pani Puri",
            "Pav Bhaji",
            "Samosa Chat",
            "Sev Puri",
            "Cutlet"
    } ;
    Integer[] imageId = {
            R.drawable.menu_3bhelpuri,
            R.drawable.menu_3panipuri,
            R.drawable.menu_3pavbhaji,
            R.drawable.menu_3samosachat,
            R.drawable.menu_3sevpuri,
            R.drawable.menu_vegcutlet
    };

    String[] price={"Rs.30","Rs.35","Rs.50","Rs.35","Rs.35","Rs.40"};
    Integer[] cost={30,35,50,35,35,40};
    public Chats() {
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
        View V=inflater.inflate(R.layout.fragment_chats, container, false);
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