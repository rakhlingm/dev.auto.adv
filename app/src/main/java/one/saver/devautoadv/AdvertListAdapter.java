package one.saver.devautoadv;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdvertListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] imake;
    private final String[] imodel;
    private final Integer[] imgid;

    public AdvertListAdapter(Activity context, String[] itemmake, String[] itemmodel, Integer[] imgid) {
        super(context, R.layout.advert_list, itemmake);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.imake =itemmake;
        this.imodel =itemmodel;
        this.imgid=imgid;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.advert_list, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView extratxt = (TextView) rowView.findViewById(R.id.textView1);
        txtTitle.setText(imake[position]);
        imageView.setImageResource(imgid[position]);
        extratxt.setText(imodel[position]);
        return rowView;

    };
}