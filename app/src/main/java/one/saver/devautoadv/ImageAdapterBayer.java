package one.saver.devautoadv;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapterBayer extends BaseAdapter {
    private Context mContext;

    public ImageAdapterBayer(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return mThumbIds[position];
    }

    public long getItemId(int position) {
        return position;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(200, 200));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(4, 4, 4, 4);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    // references to our images
   /* public	Integer[] mThumbIds = { R.drawable.traffic_jump, R.drawable.card2,
            R.drawable.card3, R.drawable.card4, R.drawable.card5,
            R.drawable.card6, R.drawable.card7, R.drawable.card8,
            R.drawable.card9, R.drawable.card10, R.drawable.card11,
            R.drawable.card12, R.drawable.card13, R.drawable.card14,
            R.drawable.card15, R.drawable.card16, R.drawable.card17,
            R.drawable.card18, R.drawable.card19, R.drawable.card20,
            R.drawable.card21 };  */
    public	Integer[] mThumbIds = { R.drawable.audi, R.drawable.bmw, R.drawable.citroen,
            R.drawable.fiatlogo, R.drawable.ford, R.drawable.honda, R.drawable.hyundai, R.drawable.landrover,
            R.drawable.lexus, R.drawable.mazda, R.drawable.mercedes_benz, R.drawable.mitsubishi, R.drawable.nissan,
            R.drawable.opel, R.drawable.seat, R.drawable.skoda, R.drawable.subaru,
            R.drawable.thumbsvolkswagen, R.drawable.toyota, R.drawable.volvo};
}