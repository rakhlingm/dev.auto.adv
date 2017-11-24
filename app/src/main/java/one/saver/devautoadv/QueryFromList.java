package one.saver.devautoadv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class QueryFromList extends Activity {
    Button remove;
    Button toMyQueryList;
    Button update;
    ImageView logoMake;
    TextView textMake;
    ImageButton imgIsActive;
    DataBaseHelper dbHelper;
    int makeIndex;
    int indexNumber;
    int isActive = 0;
    Query query;
    public	Integer[] makeLogo = { R.drawable.any_car, R.drawable.audi, R.drawable.bmw, R.drawable.citroen,
            R.drawable.fiatlogo, R.drawable.ford, R.drawable.honda, R.drawable.hyundai, R.drawable.landrover,
            R.drawable.lexus, R.drawable.mazda, R.drawable.mercedes_benz, R.drawable.mitsubishi, R.drawable.nissan,
            R.drawable.opel, R.drawable.seat, R.drawable.skoda, R.drawable.subaru,
            R.drawable.thumbsvolkswagen, R.drawable.toyota, R.drawable.volvo};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_from_list);
        remove = (Button) findViewById(R.id.buttonRemoveAd);
        logoMake = (ImageView) findViewById(R.id.imageView_logoMake);
        toMyQueryList = (Button) findViewById(R.id.buttonToMyAdList);
        update = (Button) findViewById(R.id.buttonUpdateAdvert);
        textMake = (TextView) findViewById(R.id.textMake);
        imgIsActive = (ImageButton) findViewById(R.id.imageButtonIsActive);
        Intent intent = getIntent();
        // Selected image id
        makeIndex = intent.getExtras().getInt("makeIndex");
        Log.e("makeIndex", Integer.toString(makeIndex));
        logoMake.setImageResource(makeLogo[makeIndex]);
        indexNumber = intent.getExtras().getInt("indexNumber");
        Log.e("indexNumber", Integer.toString(indexNumber));
        dbHelper = new DataBaseHelper(this);
        query = dbHelper.getQuery(indexNumber);
        Log.e("Advert from DB", query.toString());
        textMake.setText(query.getMake());
        if(query.getIsActive() == 1) {
            imgIsActive.setTag(android.R.drawable.btn_star_big_on);
            imgIsActive.setImageResource(android.R.drawable.btn_star_big_on);
            isActive = 1;
            Log.e("Is the query active","TRUE");
        } else {
            imgIsActive.setTag(android.R.drawable.btn_star_big_off);
            imgIsActive.setImageResource(android.R.drawable.btn_star_big_off);
            isActive = 0;
            Log.e("Is the query active","FALSE");
        }
        imgIsActive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imgIsActive.getTag().equals(android.R.drawable.btn_star_big_off)) {
                    imgIsActive.setTag(android.R.drawable.btn_star_big_on);
                    imgIsActive.setImageResource(android.R.drawable.btn_star_big_on);
                    isActive = 1;
                    Log.e("Is the query active","TRUE");
                } else {
                    imgIsActive.setTag(android.R.drawable.btn_star_big_off);
                    imgIsActive.setImageResource(android.R.drawable.btn_star_big_off);
                    isActive = 0;
                    Log.e("Is the query active","FALSE");
                }
      /*          List<Advert> queryList = new ArrayList<Advert>();
                queryList = dbHelper.getAllAdverts();
                for (int i = 0; i < queryList.size(); i++) {
                    if(advert.getIndexNumber() == indexNumber) {
                        Advert advertUpdate = queryList.get(i);
                        advertUpdate.setIsMain(0);
                        dbHelper.updateAdvert(advertUpdate);
                    } else {
                        Advert advertUpdate = queryList.get(i);
                        advertUpdate.setIsMain(isActive);
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
                Query query = dbHelper.getQuery(indexNumber);
                Log.e("Query from DB", query.toString());
                dbHelper.deleteQuery(query);
                List<Query> queryList = new ArrayList<Query>();
                queryList = dbHelper.getAllQueries();
                startActivity(new Intent(QueryFromList.this, QueryList.class));

            }
        });
        toMyQueryList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QueryFromList.this, QueryList.class));
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Query query = dbHelper.getQuery(indexNumber);
                Log.e("Query from DB", query.toString());
                List<Query> queryList = new ArrayList<Query>();
                queryList = dbHelper.getAllQueries();
                for (int i = 0; i < queryList.size(); i++) {
                    if(queryList.get(i).getIndexNumber() != indexNumber) {
                        Query queryUpdate = queryList.get(i);
                        Log.e("Query != indexNumber", queryUpdate.toString());
                        queryUpdate.setIsActive(0);
                        dbHelper.updateQuery(queryUpdate);

                    } else {
                        query.setIsActive(isActive);
                        dbHelper.updateQuery(query);
                    }
                }
                startActivity(new Intent(QueryFromList.this, QueryList.class));
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
                startActivity(new Intent(QueryFromList.this, Buyer.class));
            }
        });
    }
}
