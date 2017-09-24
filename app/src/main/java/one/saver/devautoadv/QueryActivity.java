package one.saver.devautoadv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class QueryActivity extends Activity {
    TextView textMake;
    Spinner spinnerPrice;
    Spinner spinnerMinPrice;
    Spinner spinnerMaxPrice;
    Spinner spinnerMinMileage;
    Spinner spinnerMaxMileage;
    Spinner spinnerModel;
    Spinner spinnerColor;
    String[] priceMin;
    String[] priceMax;
    String[] mileageMin;
    String[] mileageMax ;
    String[] model ;
    String[] colors ;
    Button buttonToMyAccount;
    Button buttonAddNewAdv;
    boolean isMinPriceCorrect = false;
    boolean isMaxPriceCorrect = false;
    boolean isMinMileageCorrect = false;
    boolean isMaxMileageCorrect = false;
    int minPriceCounter = 0;
    int maxPriceCounter = 0;
    int minMileageCounter = 0;
    int maxMileageCounter = 0;
    int counter = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);
        textMake = (TextView) findViewById(R.id.textMake_query);
        spinnerModel = (Spinner) findViewById(R.id.spinnerModel_query);
        spinnerMinPrice = (Spinner) findViewById(R.id.spinnerMinPrice_query);
        spinnerMaxPrice = (Spinner) findViewById(R.id.spinnerMaxPrice_query);
        spinnerMinMileage = (Spinner) findViewById(R.id.spinnerMinMileage_query);
        spinnerMaxMileage = (Spinner) findViewById(R.id.spinnerMaxMileage_query);
        spinnerColor = (Spinner) findViewById(R.id.spinnerColor_query);
        buttonToMyAccount = (Button) findViewById(R.id.buttonToMyAccount_query);
        buttonAddNewAdv = (Button) findViewById(R.id.buttonAddNewAdv_query);
        // get intent data
        Intent intent = getIntent();
        // Selected image id
        int position = intent.getExtras().getInt("id");
        ImageAdapterBuyer imageAdapter = new ImageAdapterBuyer(this);
        ImageView imageView = (ImageView) findViewById(R.id.full_image_view_query);
        imageView.setImageResource(imageAdapter.mThumbIds[position]);
        if(position != 0) {
            textMake.setText(getResources().getStringArray(R.array.makeArray_query)[position]);
        } else {
            textMake.setText("");
        }

  /*      ArrayAdapter<String> adapterPrice = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, price);
        // Определяем разметку для использования при выборе элемента
        adapterPrice.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        spinnerPrice.setAdapter(adapterPrice);   */

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

        model = models_arrays.get(position);
        ArrayAdapter<String> adapterModel = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                model);
        adapterModel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerModel.setAdapter(adapterModel);

        colors = getResources().getStringArray(R.array.colors);
        ArrayAdapter<String> adapterColor = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                colors);
        adapterColor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerColor.setAdapter(adapterColor);

        priceMin = getResources().getStringArray(R.array.priseMin);
        ArrayAdapter<String> adapterMinPrice = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                priceMin);
        adapterMinPrice.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMinPrice.setAdapter(adapterMinPrice);

        priceMax = getResources().getStringArray(R.array.priseMax);
        ArrayAdapter<String> adapterMaxPrice = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                priceMax);
        adapterMaxPrice.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMaxPrice.setAdapter(adapterMaxPrice);

        mileageMin = getResources().getStringArray(R.array.minMileage);
        ArrayAdapter<String> adapterMinMileage = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                mileageMin);
        adapterMinMileage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMinMileage.setAdapter(adapterMinMileage);

        mileageMax = getResources().getStringArray(R.array.minMileage);
        ArrayAdapter<String> adapterMaxMileage = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                mileageMax);
        adapterMaxMileage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMaxMileage.setAdapter(adapterMaxMileage);

        AdapterView.OnItemSelectedListener spinnerMinPriceListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(spinnerMinPrice.getSelectedItemPosition() > spinnerMaxPrice.getSelectedItemPosition()
                        && spinnerMaxPrice.getSelectedItemPosition() != 0
                        ) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Min price cannot be bigger than max price", Toast.LENGTH_SHORT);
                    TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
                    if( v != null) v.setGravity(Gravity.CENTER);
                    toast.show();
                } else {

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        };
        spinnerMinPrice.setOnItemSelectedListener(spinnerMinPriceListener);
        AdapterView.OnItemSelectedListener spinnerMaxPriceListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(spinnerMinPrice.getSelectedItemPosition() > spinnerMaxPrice.getSelectedItemPosition()
                        && spinnerMaxPrice.getSelectedItemPosition() != 0) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Max price cannot be less than min price", Toast.LENGTH_SHORT);
                    TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
                    if( v != null) v.setGravity(Gravity.CENTER);
                    toast.show();
                } else {

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        };
        spinnerMaxPrice.setOnItemSelectedListener(spinnerMaxPriceListener);
        AdapterView.OnItemSelectedListener spinnerMinMileageListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(spinnerMinMileage.getSelectedItemPosition() > spinnerMaxMileage.getSelectedItemPosition()
                        && spinnerMaxMileage.getSelectedItemPosition() != 0) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Min mileage cannot be bigger than max mileage", Toast.LENGTH_SHORT);
                    TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
                    if( v != null) v.setGravity(Gravity.CENTER);
                    toast.show();
                } else {
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        };
        spinnerMinMileage.setOnItemSelectedListener(spinnerMinMileageListener);
        AdapterView.OnItemSelectedListener spinnerMaxMileageListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(spinnerMinMileage.getSelectedItemPosition() > spinnerMaxMileage.getSelectedItemPosition()) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Max mileage cannot be less than min mileage", Toast.LENGTH_SHORT);
                    TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
                    if( v != null) v.setGravity(Gravity.CENTER);
                    toast.show();
                } else {
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        };
        spinnerMaxMileage.setOnItemSelectedListener(spinnerMaxMileageListener);
        buttonToMyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        buttonAddNewAdv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((spinnerMinPrice.getSelectedItemPosition() > spinnerMaxPrice.getSelectedItemPosition()
                        && spinnerMaxPrice.getSelectedItemPosition() != 0) ||
                        (spinnerMinPrice.getSelectedItemPosition() > spinnerMaxPrice.getSelectedItemPosition()
                                && spinnerMaxPrice.getSelectedItemPosition() != 0)
                        || ((spinnerMinPrice.getSelectedItemPosition() > spinnerMaxPrice.getSelectedItemPosition()
                        && spinnerMaxPrice.getSelectedItemPosition() == 0) ||
                        (spinnerMinPrice.getSelectedItemPosition() > spinnerMaxPrice.getSelectedItemPosition()
                                && spinnerMaxPrice.getSelectedItemPosition() == 0)))
                        ) {
                    Log.e("buttonAddNewAdv", "Please, check parameters of your query!");
                /*           "Please, check parameters of your query!", Toast.LENGTH_SHORT);
                   TextView tv = (TextView) toast.getView().findViewById(android.R.id.message);
                   if( tv != null) tv.setGravity(Gravity.CENTER);
                   toast.show(); */
                } else {
                    Log.e("buttonAddNewAdv", "Your advert is adding know!");
             /*      Toast toast = Toast.makeText(getApplicationContext(),
                           "Your advert is adding know!", Toast.LENGTH_SHORT);
                   TextView tv = (TextView) toast.getView().findViewById(android.R.id.message);
                   if( tv != null) tv.setGravity(Gravity.CENTER);
                   toast.show();   */
                }
            }
        });

    }
}