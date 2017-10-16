package one.saver.devautoadv;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AdvertFromList extends Activity {
    Button remove;
    Button toMyAdList;
    ImageView logoMake;
    TextView textMake;
    DataBaseHelper dbHelper;
    int makeIndex;
    int indexNumber;
    public	Integer[] mThumbIds = { R.drawable.audi, R.drawable.bmw, R.drawable.citroen,
            R.drawable.fiatlogo, R.drawable.ford, R.drawable.honda, R.drawable.hyundai, R.drawable.landrover,
            R.drawable.lexus, R.drawable.mazda, R.drawable.mercedes_benz, R.drawable.mitsubishi, R.drawable.nissan,
            R.drawable.opel, R.drawable.seat, R.drawable.skoda, R.drawable.subaru,
            R.drawable.thumbsvolkswagen, R.drawable.toyota, R.drawable.volvo};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advert_from_list);
        remove = (Button) findViewById(R.id.buttonRemoveAd);
        logoMake = (ImageView) findViewById(R.id.imageView_logoMake);
        toMyAdList = (Button) findViewById(R.id.buttonToMyAdList);
        textMake = (TextView) findViewById(R.id.textMake);
        Intent intent = getIntent();
        // Selected image id
        makeIndex = intent.getExtras().getInt("makeIndex");
        Log.e("makeIndex", Integer.toString(makeIndex));
        logoMake.setImageResource(mThumbIds[makeIndex]);
        indexNumber = intent.getExtras().getInt("indexNumber");
        Log.e("indexNumber", Integer.toString(indexNumber));
        dbHelper = new DataBaseHelper(this);
        Advert advert = dbHelper.getAdvert(indexNumber);
        Log.e("Advert from DB", advert.toString());
        textMake.setText(advert.getMake());
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Advert advert = dbHelper.getAdvert(indexNumber);
                Log.e("Advert from DB", advert.toString());
                dbHelper.deleteAdvert(advert);
                List<Advert> advertList = new ArrayList<Advert>();
                advertList = dbHelper.getAllAdverts();
                startActivity(new Intent(AdvertFromList.this, AdvertList.class));
            }
        });

    }
}
