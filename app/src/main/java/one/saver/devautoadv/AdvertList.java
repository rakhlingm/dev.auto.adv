package one.saver.devautoadv;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AdvertList extends Activity {
    ListView list;
    DataBaseHelper dbHelper;
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

    Integer[] imgid={
            R.drawable.any_car, R.drawable.audi, R.drawable.bmw, R.drawable.citroen,
            R.drawable.fiatlogo, R.drawable.ford, R.drawable.honda, R.drawable.hyundai

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advert_list);
        dbHelper = new DataBaseHelper(this);
        List<Advert> advertList = new ArrayList<Advert>();
        advertList = dbHelper.getAllAdverts();
        String[] makeArray = new String[advertList.size()];
        String[] modelArray = new String[advertList.size()];

        for (int i = 0; i < makeArray.length; i++) {
            makeArray[i] = advertList.get(i).getMake();
            modelArray[i] = advertList.get(i).getModel();
            Log.e("makeArray", Integer.toString(i));
            Log.e("Make", makeArray[i]);
            Log.e("Model", modelArray[i]);
        }
        AdvertListAdapter adapter=new AdvertListAdapter(this, makeArray, modelArray, imgid);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String Slecteditem= itemname[+position];
                Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();

            }
        });
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