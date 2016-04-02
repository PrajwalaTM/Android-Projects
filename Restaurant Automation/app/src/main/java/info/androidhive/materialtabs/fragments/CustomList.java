package info.androidhive.materialtabs.fragments;

/**
 * Created by Prajwala on 01-04-2016.
 */
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import info.androidhive.materialtabs.R;

public class CustomList extends ArrayAdapter<String>{

    private final Activity context;
    private final String[] web;
    private final Integer[] imageId;
    private final String[] price;
    public CustomList(Activity context,
                      String[] web, Integer[] imageId,String[] price) {
        super(context, R.layout.list_item2, web);
        this.context = context;
        this.web = web;
        this.imageId = imageId;
        this.price=price;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_item2, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        txtTitle.setText(web[position]);
        TextView cost=(TextView)rowView.findViewById(R.id.price);
        cost.setText(price[position]);
        imageView.setImageResource(imageId[position]);
        return rowView;
    }
}
