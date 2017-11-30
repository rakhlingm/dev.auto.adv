package one.saver.devautoadv;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class QueryActivity extends Activity {
    int position;
    TextView textMake;
    ImageButton imageButton;
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
    String[] mileageMax;
    String[] model;
    String[] colors;
    int isActive = 0;
    Button buttonToMyAccount;
    Button buttonAddNewQuery;
    boolean isMinPriceCorrect = false;
    boolean isMaxPriceCorrect = false;
    boolean isMinMileageCorrect = false;
    boolean isMaxMileageCorrect = false;
    int minPriceCounter = 0;
    int maxPriceCounter = 0;
    int minMileageCounter = 0;
    int maxMileageCounter = 0;
    int counter = 1;
    int makeIndex;
    int modelIndex;
    String strMake;
    String strModel;
    String strColor;
    int intMinPrice;
    int intMaxPrice;
    int intMinMileage;
    int intMaxMileage;
    String pathImage_1 = "";
    String pathImage_2 = "";
    DataBaseHelper dbHelp;

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
        buttonAddNewQuery = (Button) findViewById(R.id.buttonAddNewAdv_query);
        // get intent data
        Intent intent = getIntent();
        // Selected image id
        position = intent.getExtras().getInt("id");
        ImageAdapterBuyer imageAdapter = new ImageAdapterBuyer(this);
        ImageView imageView = (ImageView) findViewById(R.id.full_image_view_query);
        imageView.setImageResource(imageAdapter.makeLogoIds[position]);
        if (position != 0) {
            textMake.setText(getResources().getStringArray(R.array.makeArray_query)[position]);
            strMake = textMake.getText().toString();
        } else {
            textMake.setText("");
        }

  /*      ArrayAdapter<String> adapterPrice = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, price);
        // Определяем разметку для использования при выборе элемента
        adapterPrice.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        spinnerPrice.setAdapter(adapterPrice);   */
        imageButton = (ImageButton) findViewById(R.id.imageButtonIsMain);
        imageButton.setTag(android.R.drawable.btn_star_big_off);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageButton.getTag().equals(android.R.drawable.btn_star_big_off)) {
                    imageButton.setTag(android.R.drawable.btn_star_big_on);
                    imageButton.setImageResource(android.R.drawable.btn_star_big_on);
                    isActive = 1;
                    Log.e("Is the query active", "TRUE");
                } else {
                    imageButton.setTag(android.R.drawable.btn_star_big_off);
                    imageButton.setImageResource(android.R.drawable.btn_star_big_off);
                    isActive = 0;
                    Log.e("Is the query active", "FALSE");
                }
            }
        });
        Map<Integer, String[]> models_arrays = new HashMap<Integer, String[]>();
        models_arrays.put(0, new String[]{"All models"});
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
        AdapterView.OnItemSelectedListener spinnerModelListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
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
        AdapterView.OnItemSelectedListener spinnerMinPriceListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spinnerMinPrice.getSelectedItemPosition() > spinnerMaxPrice.getSelectedItemPosition()
                        && spinnerMaxPrice.getSelectedItemPosition() != 0
                        ) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Min price cannot be bigger than max price", Toast.LENGTH_SHORT);
                    TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
                    if (v != null) v.setGravity(Gravity.CENTER);
                    toast.show();
                } else {
                    switch (spinnerMinPrice.getSelectedItemPosition()) {
                        case 0: {
                            intMinPrice = 0;
                            break;
                        }
                        case 21: {
                            intMinPrice = 25000;
                            break;
                        }
                        case 22: {
                            intMinPrice = 30000;
                            break;
                        }
                        case 23: {
                            intMinPrice = 35000;
                            break;
                        }
                        case 24: {
                            intMinPrice = 40000;
                            break;
                        }
                        case 25: {
                            intMinPrice = 45000;
                            break;
                        }
                        case 26: {
                            intMinPrice = 50000;
                            break;
                        }
                        case 27: {
                            intMinPrice = 60000;
                            break;
                        }
                        case 28: {
                            intMinPrice = 70000;
                            break;
                        }
                        case 29: {
                            intMinPrice = 80000;
                            break;
                        }
                        case 30: {
                            intMinPrice = 90000;
                            break;
                        }
                        case 31: {
                            intMinPrice = 100000;
                            break;
                        }
                        default: {
                            intMinPrice = (spinnerMinPrice.getSelectedItemPosition()) * 1000;
                            break;
                        }
                    }
                    Log.e("Min price", Integer.toString(intMinPrice));
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
                if (spinnerMinPrice.getSelectedItemPosition() > spinnerMaxPrice.getSelectedItemPosition()
                        && spinnerMaxPrice.getSelectedItemPosition() != 0) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Max price cannot be less than min price", Toast.LENGTH_SHORT);
                    TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
                    if (v != null) v.setGravity(Gravity.CENTER);
                    toast.show();
                } else {
                    switch (spinnerMaxPrice.getSelectedItemPosition()) {
                        case 0: {
                            intMaxPrice = 1000000;
                            break;
                        }
                        case 21: {
                            intMaxPrice = 25000;
                            break;
                        }
                        case 22: {
                            intMaxPrice = 30000;
                            break;
                        }
                        case 23: {
                            intMaxPrice = 35000;
                            break;
                        }
                        case 24: {
                            intMaxPrice = 40000;
                            break;
                        }
                        case 25: {
                            intMaxPrice = 45000;
                            break;
                        }
                        case 26: {
                            intMaxPrice = 50000;
                            break;
                        }
                        case 27: {
                            intMaxPrice = 60000;
                            break;
                        }
                        case 28: {
                            intMaxPrice = 70000;
                            break;
                        }
                        case 29: {
                            intMaxPrice = 80000;
                            break;
                        }
                        case 30: {
                            intMaxPrice = 90000;
                            break;
                        }
                        case 31: {
                            intMaxPrice = 100000;
                            break;
                        }
                        default: {
                            intMaxPrice = spinnerMaxPrice.getSelectedItemPosition() * 1000;
                        }
                    }
                    Log.e("Max price", Integer.toString(intMaxPrice));
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
                if (spinnerMinMileage.getSelectedItemPosition() > spinnerMaxMileage.getSelectedItemPosition()
                        && spinnerMaxMileage.getSelectedItemPosition() != 0) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Min mileage cannot be bigger than max mileage", Toast.LENGTH_SHORT);
                    TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
                    if (v != null) v.setGravity(Gravity.CENTER);
                    toast.show();
                } else {
                    switch (spinnerMinMileage.getSelectedItemPosition()) {
                        case 0: {
                            intMinMileage = 0;
                            break;
                        }
                        case 1: {
                            intMinMileage = 1000;
                            break;
                        }
                        case 2: {
                            intMinMileage = 5000;
                            break;
                        }
                        case 13: {
                            intMinMileage = 125000;
                            break;
                        }
                        case 14: {
                            intMinMileage = 150000;
                            break;
                        }
                        case 15: {
                            intMinMileage = 200000;
                            break;
                        }
                        default: {
                            intMinMileage = (spinnerMinMileage.getSelectedItemPosition() - 2) * 10000;
                        }
                    }
                    Log.e("Min mileage", Integer.toString(intMinMileage));
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
                if (spinnerMinMileage.getSelectedItemPosition() > spinnerMaxMileage.getSelectedItemPosition()) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Max mileage cannot be less than min mileage", Toast.LENGTH_SHORT);
                    TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
                    if (v != null) v.setGravity(Gravity.CENTER);
                    toast.show();
                } else {
                    switch (spinnerMaxMileage.getSelectedItemPosition()) {
                        case 0: {
                            intMaxMileage = 1000000;
                            break;
                        }
                        case 1: {
                            intMaxMileage = 1000;
                            break;
                        }
                        case 2: {
                            intMaxMileage = 5000;
                            break;
                        }
                        case 13: {
                            intMaxMileage = 125000;
                            break;
                        }
                        case 14: {
                            intMaxMileage = 150000;
                            break;
                        }
                        case 15: {
                            intMaxMileage = 200000;
                            break;
                        }
                        default: {
                            intMaxMileage = (spinnerMaxMileage.getSelectedItemPosition() - 2) * 10000;
                        }
                    }
                    Log.e("Max mileage", Integer.toString(intMaxMileage));
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
        dbHelp = new DataBaseHelper(this);
        buttonAddNewQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((spinnerMinPrice.getSelectedItemPosition() > spinnerMaxPrice.getSelectedItemPosition()
                        && spinnerMaxPrice.getSelectedItemPosition() != 0) ||
                        (spinnerMinPrice.getSelectedItemPosition() > spinnerMaxPrice.getSelectedItemPosition()
                                && spinnerMaxPrice.getSelectedItemPosition() != 0)
                        || ((spinnerMinPrice.getSelectedItemPosition() > spinnerMaxPrice.getSelectedItemPosition()
                        && spinnerMaxPrice.getSelectedItemPosition() == 0) ||
                        (spinnerMinPrice.getSelectedItemPosition() > spinnerMaxPrice.getSelectedItemPosition()
                                && spinnerMaxPrice.getSelectedItemPosition() == 0)))
                        ) {
                    Log.e("buttonAddNewQuery", "Please, check parameters of your query!");
                /*           "Please, check parameters of your query!", Toast.LENGTH_SHORT);
                   TextView tv = (TextView) toast.getView().findViewById(android.R.id.message);
                   if( tv != null) tv.setGravity(Gravity.CENTER);
                   toast.show(); */
                } else {
                    Log.e("buttonAddNewQuery", "Your query is adding know!");
                    TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                    Query query = new Query();
                    if (ActivityCompat.checkSelfPermission(getBaseContext(), Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    query.setIMEI(tm.getDeviceId());
                    query.setMakeIndex(position);
                    query.setModelIndex(spinnerModel.getSelectedItemPosition());
                    query.setMake(strMake);
                    query.setModel(strModel);
                    query.setColor(strColor);
                    query.setMinPrice(intMinPrice);
                    query.setMaxPrice(intMaxPrice);
                    query.setMinMileage(intMinMileage);
                    query.setMaxMileage(intMaxMileage);
                    query.setImage_1(pathImage_1);
                    query.setImage_2(pathImage_2);
                    query.setIsActive(isActive);
                    Log.e("Query", query.toString());
                    dbHelp.addQuery(query);
                    startActivity(new Intent(QueryActivity.this, QueryList.class));
                    Log.e("buttonAddNewQuery", "AdvertList activity is opening.");
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