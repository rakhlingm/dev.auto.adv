package one.saver.devautoadv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

public class Buyer extends Activity {

    private TextView mSelectText;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer);

        mSelectText = (TextView) findViewById(R.id.infoBuyer);
        GridView gridview = (GridView) findViewById(R.id.gridViewBuyer);
        gridview.setAdapter(new ImageAdapterBuyer(this));

        gridview.setOnItemClickListener(gridviewOnItemClickListener);
    }

    private GridView.OnItemClickListener gridviewOnItemClickListener = new GridView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View v, int position,
                                long id) {
            // TODO Auto-generated method stub

            // Sending image id to FullScreenActivity
            Intent i = new Intent(getApplicationContext(),
                    QueryActivity.class);
            // passing array index
            i.putExtra("id", position);
            startActivity(i);
        }
    };
}