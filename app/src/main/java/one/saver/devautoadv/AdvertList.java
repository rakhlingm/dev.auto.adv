package one.saver.devautoadv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class AdvertList extends Activity {
    ListView list;
    DataBaseHelper dbHelper;
    List<Advert> advertList = new ArrayList<Advert>();
    String[] itemname ={
            "Safari",
            "Camera",
            "Global",
            "FireFox",
            "UC Browser",
            "Android Folder",
            "VLC Player",
            "Cold War"
    };

 /*   Integer[] imgid={
            R.drawable.any_car, R.drawable.audi, R.drawable.bmw, R.drawable.citroen,
            R.drawable.fiatlogo, R.drawable.ford, R.drawable.honda, R.drawable.hyundai

    };   */
 public	Integer[] makeLogoIds = { R.drawable.audi, R.drawable.bmw, R.drawable.citroen,
         R.drawable.fiatlogo, R.drawable.ford, R.drawable.honda, R.drawable.hyundai, R.drawable.landrover,
         R.drawable.lexus, R.drawable.mazda, R.drawable.mercedes_benz, R.drawable.mitsubishi, R.drawable.nissan,
         R.drawable.opel, R.drawable.seat, R.drawable.skoda, R.drawable.subaru,
         R.drawable.thumbsvolkswagen, R.drawable.toyota, R.drawable.volvo};
public	Integer[] isMainLogoIds = { android.R.drawable.btn_star_big_off, android.R.drawable.btn_star_big_on};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advert_list);
        dbHelper = new DataBaseHelper(this);

        advertList = dbHelper.getAllAdverts();
        String[] makeArray = new String[advertList.size()];
        String[] modelArray = new String[advertList.size()];
        Integer[] makeLogoImgid = new Integer[advertList.size()];
        Integer[] isMainImgid = new Integer[advertList.size()];
        for (int i = 0; i < makeArray.length; i++) {
            makeArray[i] = advertList.get(i).getMake();
            modelArray[i] = advertList.get(i).getModel();
            makeLogoImgid[i] = makeLogoIds[advertList.get(i).getMakeIndex()];
            isMainImgid[i] = isMainLogoIds[advertList.get(i).getIsMain()];
            Log.e("makeArray", Integer.toString(i));
            Log.e("Make", makeArray[i]);
            Log.e("Model", modelArray[i]);
            Log.e("Is main", Integer.toString(isMainImgid[i]));
        }
        AdvertListAdapter adapter=new AdvertListAdapter(this, makeArray, modelArray, makeLogoImgid, isMainImgid);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String Slecteditem= itemname[+position];
         //       Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(),
                        AdvertFromList.class);
                // passing array index
                i.putExtra("makeIndex", advertList.get(position).getMakeIndex());
                i.putExtra("indexNumber", advertList.get(position).getIndexNumber());
                startActivity(i);
       //         dbHelper.deleteAdvert(advertList.get(position));
            }
        });
    }

    @Override
    public void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
}

/*    DataBaseHelper dbHelper;
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2" };
        dbHelper = new DataBaseHelper(this);
        List<Advert> advertList = new ArrayList<Advert>();
        advertList = dbHelper.getAllAdverts();
        ArrayAdapter<Advert> adapter = new ArrayAdapter<Advert>(this,
                android.R.layout.simple_list_item_1, advertList);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String item = (String) getListAdapter().getItem(position);
        Toast.makeText(this, item + " выбран", Toast.LENGTH_LONG).show();
    }
}  */