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
    private final Integer[] imgLogoId;
    private final Integer[] imgIsMainId;


    public AdvertListAdapter(Activity context, String[] itemmake, String[] itemmodel, Integer[] img_logo_id, Integer[] img_is_main_id) {
        super(context, R.layout.advert_list, itemmake);
        // TODO Auto-generated constructor stub

        this.context= context;
        this.imake = itemmake;
        this.imodel = itemmodel;
        this.imgLogoId = img_logo_id;
        this.imgIsMainId = img_is_main_id;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.advert_list, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        ImageView imageViewLogo = (ImageView) rowView.findViewById(R.id.icon);
        TextView extratxt = (TextView) rowView.findViewById(R.id.textView1);
        ImageView imageViewIsMain = (ImageView) rowView.findViewById(R.id.iconIsMain);
        txtTitle.setText(imake[position]);
        imageViewLogo.setImageResource(imgLogoId[position]);
        imageViewIsMain.setImageResource(imgIsMainId[position]);
        extratxt.setText(imodel[position]);
        return rowView;

    };
}