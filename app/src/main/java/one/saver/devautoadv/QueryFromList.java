package one.saver.devautoadv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryFromList extends Activity {
    Button remove;
    Button toMyQueryList;
    Button update;
    ImageView logoMake;
    TextView textMake;
    Spinner spinnerModel;
    Spinner spinnerColor;
    Spinner spinnerMinPrice;
    Spinner spinnerMaxPrice;
    Spinner spinnerMinMileage;
    Spinner spinnerMaxMileage;
    ImageButton imgIsActive;
    String[] model;
    String[] colors;
    String[] priceMin;
    String[] priceMax;
    String[] mileageMin;
    String[] mileageMax;
    DataBaseHelper dbHelper;
    int makeIndex;
    int modelIndex;
    String strModel;
    String strColor;
    int indexNumber;
    int isActive = 0;
    int intMinPrice;
    int intMaxPrice;
    int intMinMileage;
    int intMaxMileage;
    String pathImage_1 = "";
    String pathImage_2 = "";
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
        textMake = (TextView) findViewById(R.id.textMake);
        spinnerModel = (Spinner) findViewById(R.id.spinnerModel);
        spinnerColor = (Spinner) findViewById(R.id.spinnerColor);
        spinnerMinPrice = (Spinner) findViewById(R.id.spinnerMinPrice);
        spinnerMaxPrice = (Spinner) findViewById(R.id.spinnerMaxPrice);
        spinnerMinMileage = (Spinner) findViewById(R.id.spinnerMinMileage);
        spinnerMaxMileage = (Spinner) findViewById(R.id.spinnerMaxMileage);
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
        Log.e("Query from DB", query.toString());
        textMake.setText(query.getMake());
        modelIndex = query.getModelIndex();
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
        Map<Integer, String[]> models_arrays = new HashMap<Integer, String[]>();
        models_arrays.put(0, new String []{"All models"});
        models_arrays.put(1, getResources().getStringArray(R.array.audiModels));
        models_arrays.put(2, getResources().getStringArray(R.array.bmwModels));
        models_arrays.put(3, getResources().getStringArray(R.array.citroenModels));
        models_arrays.put(4, getResources().getStringArray(R.array.fiatModels));
        models_arrays.put(5, getResources().getStringArray(R.array.fordModels));
        models_arrays.put(6, getResources().getStringArray(R.array.hondaModels));
        models_arrays.put(7, getResources().getStringArray(R.array.hyundaiModels));
        models_arrays.put(8, getResources().getStringArray(R.array.landRoverModels));
        models_arrays.put(9, getResources().getStringArray(R.array.lexusModels));
        models_arrays.put(10, getResources().getStringArray(R.array.mazdaModels));
        models_arrays.put(11, getResources().getStringArray(R.array.mercedes_BenzModels));
        models_arrays.put(12, getResources().getStringArray(R.array.mitsubishiModels));
        models_arrays.put(13, getResources().getStringArray(R.array.nissanModels));
        models_arrays.put(14, getResources().getStringArray(R.array.opelModels));
        models_arrays.put(15, getResources().getStringArray(R.array.seatModels));
        models_arrays.put(16, getResources().getStringArray(R.array.skodaModels));
        models_arrays.put(17, getResources().getStringArray(R.array.subaruModels));
        models_arrays.put(18, getResources().getStringArray(R.array.volkswagenModels));
        models_arrays.put(19, getResources().getStringArray(R.array.toyotaModels));
        models_arrays.put(20, getResources().getStringArray(R.array.volvoModels));

        model = models_arrays.get(query.getMakeIndex());
        ArrayAdapter<String> adapterModel = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                model);
        adapterModel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerModel.setAdapter(adapterModel);
        spinnerModel.setSelection(query.getModelIndex());
        colors = getResources().getStringArray(R.array.colors);
        colors = getResources().getStringArray(R.array.colors);
        ArrayAdapter<String> adapterColor = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                colors);
        adapterColor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerColor.setAdapter(adapterColor);
        int indexColor = 0;
        for (int i = 0; i < colors.length; i++) {
            if (colors[i].toString().equals(query.getColor())) {
                indexColor = i;
                Log.e("indexColor", Integer.toString(i));
            }
        }
        spinnerColor.setSelection(indexColor);
        priceMin = getResources().getStringArray(R.array.priseMin);
        ArrayAdapter<String> adapterMinPrice = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                priceMin);
        adapterMinPrice.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMinPrice.setAdapter(adapterMinPrice);
        int indexMinPrice = 0;
        switch (query.getMinPrice()) {
            case 0: {
                indexMinPrice = 0;
                break;
            }
            case 25000: {
                indexMinPrice = 21;
                break;
            }
            case 30000: {
                indexMinPrice = 22;
                break;
            }
            case 35000: {
                indexMinPrice = 23;
                break;
            }
            case 40000: {
                indexMinPrice = 24;
                break;
            }
            case 45000: {
                indexMinPrice = 25;
                break;
            }
            case 50000: {
                indexMinPrice = 26;
                break;
            }
            case 60000: {
                indexMinPrice = 7;
                break;
            }
            case 70000: {
                indexMinPrice = 28;
                break;
            }
            case 80000: {
                indexMinPrice = 29;
                break;
            }
            case 90000: {
                indexMinPrice = 30;
                break;
            }
            case 100000: {
                indexMinPrice = 31;
                break;
            }
            default: {
                indexMinPrice = query.getMinPrice() / 1000;
                break;
            }
        }
        spinnerMinPrice.setSelection(indexMinPrice);
        priceMax = getResources().getStringArray(R.array.priseMax);
        ArrayAdapter<String> adapterMaxPrice = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                priceMax);
        adapterMaxPrice.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMaxPrice.setAdapter(adapterMaxPrice);
        int indexMaxPrice = 0;
        switch (query.getMaxPrice()) {
            case 1000000: {
                indexMaxPrice = 0;
                break;
            }
            case 25000: {
                indexMaxPrice = 21;
                break;
            }
            case 30000: {
                indexMaxPrice = 22;
                break;
            }
            case 35000: {
                indexMaxPrice = 23;
                break;
            }
            case 40000: {
                indexMaxPrice = 24;
                break;
            }
            case 45000: {
                indexMaxPrice = 25;
                break;
            }
            case 50000: {
                indexMaxPrice = 26;
                break;
            }
            case 60000: {
                indexMaxPrice = 7;
                break;
            }
            case 70000: {
                indexMaxPrice = 28;
                break;
            }
            case 80000: {
                indexMaxPrice = 29;
                break;
            }
            case 90000: {
                indexMaxPrice = 30;
                break;
            }
            case 100000: {
                indexMaxPrice = 31;
                break;
            }
            default: {
                indexMaxPrice = query.getMaxPrice() / 1000;
                break;
            }
        }
        spinnerMaxPrice.setSelection(indexMaxPrice);
        mileageMin = getResources().getStringArray(R.array.minMileage);
        ArrayAdapter<String> adapterMinMileage = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                mileageMin);
        adapterMinMileage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMinMileage.setAdapter(adapterMinMileage);
        int indexMinMileage = 0;
        switch (query.getMinMileage()) {
            case 0: {
                indexMinMileage = 0;
                break;
            }
            case 1000: {
                indexMinMileage = 1;
                break;
            }
            case 5000: {
                indexMinMileage = 2;
                break;
            }
            case 125000: {
                indexMinMileage = 13;
                break;
            }
            case 150000: {
                indexMinMileage = 14;
                break;
            }
            case 200000: {
                indexMinMileage = 15;
                break;
            }
            default: {
                indexMinMileage = query.getMinMileage() / 10000 + 2;
            }
        }
        spinnerMinMileage.setSelection(indexMinMileage);
        mileageMax = getResources().getStringArray(R.array.maxMileage);
        ArrayAdapter<String> adapterMaxMileage = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                mileageMax);
        adapterMaxMileage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMaxMileage.setAdapter(adapterMaxMileage);
        int indexMaxMileage = 0;
        switch (query.getMaxMileage()) {
            case 1000000: {
                indexMaxMileage = 0;
                break;
            }
            case 1000: {
                indexMaxMileage = 1;
                break;
            }
            case 5000: {
                indexMaxMileage = 2;
                break;
            }
            case 125000: {
                indexMaxMileage = 13;
                break;
            }
            case 150000: {
                indexMaxMileage = 14;
                break;
            }
            case 200000: {
                indexMaxMileage = 15;
                break;
            }
            default: {
                indexMaxMileage = query.getMaxMileage() / 10000 + 2;
            }
        }
        spinnerMaxMileage.setSelection(indexMaxMileage);
        AdapterView.OnItemSelectedListener spinnerModelListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                modelIndex = spinnerModel.getSelectedItemPosition();
                strModel = spinnerModel.getSelectedItem().toString();
                Log.e("Model", strModel);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        spinnerModel.setOnItemSelectedListener(spinnerModelListener);
        AdapterView.OnItemSelectedListener spinnerColorlListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strColor = spinnerColor.getSelectedItem().toString();
                Log.e("Color", strColor);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        spinnerColor.setOnItemSelectedListener(spinnerColorlListener);
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
                    if(queryList.get(i).getIndexNumber() != indexNumber && isActive == 1) {
                        Query queryUpdate = queryList.get(i);
                        Log.e("Query != indexNumber", queryUpdate.toString());
                        queryUpdate.setIsActive(0);
                        dbHelper.updateQuery(queryUpdate);
                    } else {
                        if(isActive == 1) {
                            query.setIsActive(isActive);
                        } else {
                            query.setIsActive(0);
                        }
                        query.setModelIndex(modelIndex);
                        query.setModel(strModel);
                        query.setColor(strColor);
                        query.setMinPrice(intMinPrice);
                        query.setMaxPrice(intMaxPrice);
                        query.setMinMileage(intMinMileage);
                        query.setMaxMileage(intMaxMileage);
            //            query.setImage_1(pathImage_1);
            //            query.setImage_2(pathImage_2);
                        Log.e("Update: Query == indNum", query.toString());
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
