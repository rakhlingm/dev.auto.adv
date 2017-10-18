package one.saver.devautoadv;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AdvertFromList extends Activity {
    Button remove;
    Button toMyAdList;
    Button update;
    ImageView logoMake;
    TextView textMake;
    ImageButton imgIsMain;
    DataBaseHelper dbHelper;
    int makeIndex;
    int indexNumber;
    int isMain = 0;
    Advert advert;
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
        update = (Button) findViewById(R.id.buttonUpdateAdvert);
        textMake = (TextView) findViewById(R.id.textMake);
        imgIsMain = (ImageButton) findViewById(R.id.imageButtonIsMain);
        Intent intent = getIntent();
        // Selected image id
        makeIndex = intent.getExtras().getInt("makeIndex");
        Log.e("makeIndex", Integer.toString(makeIndex));
        logoMake.setImageResource(mThumbIds[makeIndex]);
        indexNumber = intent.getExtras().getInt("indexNumber");
        Log.e("indexNumber", Integer.toString(indexNumber));
        dbHelper = new DataBaseHelper(this);
        advert = dbHelper.getAdvert(indexNumber);
        Log.e("Advert from DB", advert.toString());
        textMake.setText(advert.getMake());
        if(advert.getIsMain() == 1) {
            imgIsMain.setTag(android.R.drawable.btn_star_big_on);
            imgIsMain.setImageResource(android.R.drawable.btn_star_big_on);
            isMain = 1;
            Log.e("Is the advert main","TRUE");
        } else {
            imgIsMain.setTag(android.R.drawable.btn_star_big_off);
            imgIsMain.setImageResource(android.R.drawable.btn_star_big_off);
            isMain = 0;
            Log.e("Is the advert main","FALSE");
        }
        imgIsMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imgIsMain.getTag().equals(android.R.drawable.btn_star_big_off)) {
                    imgIsMain.setTag(android.R.drawable.btn_star_big_on);
                    imgIsMain.setImageResource(android.R.drawable.btn_star_big_on);
                    isMain = 1;
                    Log.e("Is the advert main","TRUE");
                } else {
                    imgIsMain.setTag(android.R.drawable.btn_star_big_off);
                    imgIsMain.setImageResource(android.R.drawable.btn_star_big_off);
                    isMain = 0;
                    Log.e("Is the advert main","FALSE");
                }
      /*          List<Advert> advertList = new ArrayList<Advert>();
                advertList = dbHelper.getAllAdverts();
                for (int i = 0; i < advertList.size(); i++) {
                    if(advert.getIndexNumber() == indexNumber) {
                        Advert advertUpdate = advertList.get(i);
                        advertUpdate.setIsMain(0);
                        dbHelper.updateAdvert(advertUpdate);
                    } else {
                        Advert advertUpdate = advertList.get(i);
                        advertUpdate.setIsMain(isMain);
                        dbHelper.updateAdvert(advertUpdate);
                    }
                }  */
    //            String advertCount = Integer.toString(dbHelper.getAdvertCount());
    //            Log.e("Count of adverts", advertCount);
            }
        });
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
        toMyAdList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdvertFromList.this, AdvertList.class));
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Advert advert = dbHelper.getAdvert(indexNumber);
                Log.e("Advert from DB", advert.toString());
                List<Advert> advertList = new ArrayList<Advert>();
                advertList = dbHelper.getAllAdverts();
                for (int i = 0; i < advertList.size(); i++) {
                    if(advertList.get(i).getIndexNumber() != indexNumber) {
                        Advert advertUpdate = advertList.get(i);
                        Log.e("Advert != indexNumber", advertUpdate.toString());
                        advertUpdate.setIsMain(0);
                        dbHelper.updateAdvert(advertUpdate);

                    } else {
                        advert.setIsMain(isMain);
                        dbHelper.updateAdvert(advert);
                    }
                }
                startActivity(new Intent(AdvertFromList.this, AdvertList.class));
            }
        });
    }
    @Override
    public void onRestart() {
        super.onRestart();
   //     finish();
   //     startActivity(getIntent());
        remove.setText("Re-save");
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdvertFromList.this, Seller.class));
            }
        });
    }
}
