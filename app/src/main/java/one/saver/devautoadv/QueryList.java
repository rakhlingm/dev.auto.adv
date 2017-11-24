package one.saver.devautoadv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class QueryList extends AppCompatActivity {
    ListView list;
    DataBaseHelper dbHelper;
    List<Query> queryList = new ArrayList<Query>();
    public	Integer[] makeLogoIds = { R.drawable.any_car, R.drawable.audi, R.drawable.bmw, R.drawable.citroen,
            R.drawable.fiatlogo, R.drawable.ford, R.drawable.honda, R.drawable.hyundai, R.drawable.landrover,
            R.drawable.lexus, R.drawable.mazda, R.drawable.mercedes_benz, R.drawable.mitsubishi, R.drawable.nissan,
            R.drawable.opel, R.drawable.seat, R.drawable.skoda, R.drawable.subaru,
            R.drawable.thumbsvolkswagen, R.drawable.toyota, R.drawable.volvo};
    public	Integer[] isActiveLogoIds = { android.R.drawable.btn_star_big_off, android.R.drawable.btn_star_big_on};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_list);
        dbHelper = new DataBaseHelper(this);
        queryList = dbHelper.getAllQueries();
        String[] makeArray = new String[queryList.size()];
        String[] modelArray = new String[queryList.size()];
        Integer[] makeLogoImgid = new Integer[queryList.size()];
        Integer[] isMainImgid = new Integer[queryList.size()];
        for (int i = 0; i < makeArray.length; i++) {
            if(queryList.get(i).getMake() != null) {
                makeArray[i] = queryList.get(i).getMake();
            } else {
                makeArray[i] = "Any car";
            }
            modelArray[i] = queryList.get(i).getModel();
            makeLogoImgid[i] = makeLogoIds[queryList.get(i).getMakeIndex()];
            isMainImgid[i] = isActiveLogoIds[queryList.get(i).getIsActive()];
            Log.e("makeArray", Integer.toString(i));
            Log.e("Make", makeArray[i]);
            Log.e("Model", modelArray[i]);
            Log.e("Is active", Integer.toString(isMainImgid[i]));
        }
        QueryListAdapter adapter=new QueryListAdapter(this, makeArray, modelArray, makeLogoImgid, isMainImgid);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                //       Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(),
                        AdvertFromList.class);
                // passing array index
                i.putExtra("makeIndex", queryList.get(position).getMakeIndex());
                i.putExtra("indexNumber", queryList.get(position).getIndexNumber());
                startActivity(i);
                //         dbHelper.deleteAdvert(queryList.get(position));
            }
        });
    }
    public void onBackPressed(){
        Intent in = new Intent(getApplicationContext(),
                MainActivity.class);
        startActivity(in);
        finish();
    }
    @Override
    public void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
}