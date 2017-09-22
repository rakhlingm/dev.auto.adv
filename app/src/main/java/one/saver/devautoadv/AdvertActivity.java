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

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

public class AdvertActivity extends Activity {
    TextView textMake;
    Spinner spinnerPrice;
    Spinner spinnerMinPrice;
    Spinner spinnerMaxPrice;
    Spinner spinnerMinMileage;
    Spinner spinnerMaxMileage;
    Spinner spinnerModel;
    Spinner spinnerColor;
    String[] price = {"No minimum", "Самара", "Вологда", "Волгоград", "Саратов", "Воронеж"};
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
        setContentView(R.layout.activity_advert);
        textMake = (TextView) findViewById(R.id.textMake);
        spinnerModel = (Spinner) findViewById(R.id.spinnerModel);
        spinnerMinPrice = (Spinner) findViewById(R.id.spinnerMinPrice);
        spinnerMaxPrice = (Spinner) findViewById(R.id.spinnerMaxPrice);
        spinnerMinMileage = (Spinner) findViewById(R.id.spinnerMinMileage);
        spinnerMaxMileage = (Spinner) findViewById(R.id.spinnerMaxMileage);
        spinnerColor = (Spinner) findViewById(R.id.spinnerColor);
        buttonToMyAccount = (Button) findViewById(R.id.buttonToMyAccount);
        buttonAddNewAdv = (Button) findViewById(R.id.buttonAddNewAdv);
        // get intent data
        Intent intent = getIntent();
        // Selected image id
        int position = intent.getExtras().getInt("id");
        ImageAdapter imageAdapter = new ImageAdapter(this);
        ImageView imageView = (ImageView) findViewById(R.id.full_image_view);
        imageView.setImageResource(imageAdapter.mThumbIds[position]);
        if(position != 0) {
            textMake.setText(getResources().getStringArray(R.array.makeArray)[position]);
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
                            "Min price cannot be bigger than max price", Toast.LENGTH_LONG);
                    TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
                    if( v != null) v.setGravity(Gravity.CENTER);
                    toast.show();
                    Log.e("Min Price B (if = true)", isMinPriceCorrect + " " + isMaxPriceCorrect
                            + " " + isMinMileageCorrect + " " + isMaxMileageCorrect + " " + counter);
                    buttonAddNewAdv.setEnabled(false);
                    isMinPriceCorrect = false;
                    counter++;
                    Log.e("Min Price B(if = true)", isMinPriceCorrect + " " + isMaxPriceCorrect
                    + " " + isMinMileageCorrect + " " + isMaxMileageCorrect + " " + counter);
                } else
                    if (spinnerMinPrice.getSelectedItemPosition() > spinnerMaxPrice.getSelectedItemPosition()
                            && spinnerMaxPrice.getSelectedItemPosition()== 0
                            && (!isMinPriceCorrect || !isMaxPriceCorrect || !isMinMileageCorrect|| !isMaxMileageCorrect)) {
                        Log.e("MinPrice B(if = true/2)", isMinPriceCorrect + " " + isMaxPriceCorrect
                                + " " + isMinMileageCorrect + " " + isMaxMileageCorrect + " " + counter);
                        buttonAddNewAdv.setEnabled(false);
                        isMinPriceCorrect = false;
                        counter++;
                        Log.e("MinPriceA(if = true/2)", isMinPriceCorrect + " " + isMaxPriceCorrect
                                + " " + isMinMileageCorrect + " " + isMaxMileageCorrect + " " + counter);
                    } else
                        if(spinnerMinPrice.getSelectedItemPosition() < spinnerMaxPrice.getSelectedItemPosition()
                                && (!isMinPriceCorrect || !isMaxPriceCorrect || !isMinMileageCorrect|| !isMaxMileageCorrect)
                                ){
                        Log.e("MinPrice B(if = true/3)", isMinPriceCorrect + " " + isMaxPriceCorrect
                                + " " + isMinMileageCorrect + " " + isMaxMileageCorrect + " " + counter);
                        buttonAddNewAdv.setEnabled(false);
                        isMinPriceCorrect = true;
                        isMaxPriceCorrect = true;
                        counter++;
                        Log.e("MinPriceA(if = true/3)", isMinPriceCorrect + " " + isMaxPriceCorrect
                                + " " + isMinMileageCorrect + " " + isMaxMileageCorrect + " " + counter);
                    } else

                        {
                    Log.e("Min Price B(if = false)", isMinPriceCorrect + " " + isMaxPriceCorrect
                            + " " + isMinMileageCorrect + " " + isMaxMileageCorrect + " " + counter);
                    if(counter > 5) {
                        isMinPriceCorrect = true;
                    }
                    counter++;
                    buttonAddNewAdv.setEnabled(true);
                    Log.e("Min Price A(if = false)", isMinPriceCorrect + " " + isMaxPriceCorrect
                            + " " + isMinMileageCorrect + " " + isMaxMileageCorrect + " " + counter);
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
                        && spinnerMaxPrice.getSelectedItemPosition() != 0
                        && (!isMinPriceCorrect || !isMaxPriceCorrect || !isMinMileageCorrect|| !isMaxMileageCorrect)) {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Max price cannot be less than min price", Toast.LENGTH_LONG);
                    TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
                    if( v != null) v.setGravity(Gravity.CENTER);
                        toast.show();
                    Log.e("Max Price B (if = true)", isMinPriceCorrect + " " + isMaxPriceCorrect
                            + " " + isMinMileageCorrect + " " + isMaxMileageCorrect + " " + counter);
                    buttonAddNewAdv.setEnabled(false);
                    isMaxPriceCorrect = false;
                    counter++;
                    Log.e("Max Price A (if = true)", isMinPriceCorrect + " " + isMaxPriceCorrect
                            + " " + isMinMileageCorrect + " " + isMaxMileageCorrect + " " + counter);
                } else
                if (spinnerMinPrice.getSelectedItemPosition() < spinnerMaxPrice.getSelectedItemPosition()
                        && (!isMinPriceCorrect || !isMaxPriceCorrect || !isMinMileageCorrect|| !isMaxMileageCorrect)) {
                    Log.e("MaxPrice B(if = true/2)", isMinPriceCorrect + " " + isMaxPriceCorrect
                            + " " + isMinMileageCorrect + " " + isMaxMileageCorrect + " " + counter);
                    buttonAddNewAdv.setEnabled(false);
                    isMaxPriceCorrect = false;
                    counter++;
                    Log.e("MaxPrice A(if = true/2)", isMinPriceCorrect + " " + isMaxPriceCorrect
                            + " " + isMinMileageCorrect + " " + isMaxMileageCorrect + " " + counter);
                } else {
                    Log.e("Max Price B(if = false)", isMinPriceCorrect + " " + isMaxPriceCorrect
                            + " " + isMinMileageCorrect + " " + isMaxMileageCorrect + " " + counter);
                    if(counter > 5) {
                        isMaxPriceCorrect = true;
                    }
                    counter++;
                    Log.e("Max Price A(if = false)", isMinPriceCorrect + " " + isMaxPriceCorrect
                            + " " + isMinMileageCorrect + " " + isMaxMileageCorrect + " " + counter);
                    buttonAddNewAdv.setEnabled(true);
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
                        && spinnerMaxMileage.getSelectedItemPosition() != 0
                        && (!isMinPriceCorrect || !isMaxPriceCorrect || !isMinMileageCorrect|| !isMaxMileageCorrect)) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Min mileage cannot be bigger than max mileage", Toast.LENGTH_LONG);
                    TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
                    if( v != null) v.setGravity(Gravity.CENTER);
                    toast.show();
                    buttonAddNewAdv.setEnabled(false);
                    Log.e("Min Mil B(if = true)", isMinPriceCorrect + " " + isMaxPriceCorrect
                            + " " + isMinMileageCorrect + " " + isMaxMileageCorrect + " " + counter);
                    isMinMileageCorrect = false;
                    counter++;
                    Log.e("Min Mil A(if = true)", isMinPriceCorrect + " " + isMaxPriceCorrect
                            + " " + isMinMileageCorrect + " " + isMaxMileageCorrect + " " + counter);
                } else
                if (spinnerMinPrice.getSelectedItemPosition() > spinnerMaxPrice.getSelectedItemPosition()
                        && spinnerMaxPrice.getSelectedItemPosition()== 0
                        && (!isMinPriceCorrect || !isMaxPriceCorrect || !isMinMileageCorrect|| !isMaxMileageCorrect)) {
                    Log.e("Min Mil B(if = true/2)", isMinPriceCorrect + " " + isMaxPriceCorrect
                            + " " + isMinMileageCorrect + " " + isMaxMileageCorrect + " " + counter);
                    buttonAddNewAdv.setEnabled(false);
                    isMinMileageCorrect = false;
                    counter++;
                    Log.e("Min Mil A(if = true/2)", isMinPriceCorrect + " " + isMaxPriceCorrect
                            + " " + isMinMileageCorrect + " " + isMaxMileageCorrect + " " + counter);

                } else {
                    Log.e("Min Mil B(if = false)", isMinPriceCorrect + " " + isMaxPriceCorrect
                            + " " + isMinMileageCorrect + " " + isMaxMileageCorrect + " " + counter);
                    if(counter > 5) {
                        isMinMileageCorrect = true;
                        isMaxMileageCorrect = true;
                        }
                    counter++;
                    Log.e("Min Mil A(if = false)", isMinPriceCorrect + " " + isMaxPriceCorrect
                            + " " + isMinMileageCorrect + " " + isMaxMileageCorrect + " " + counter);
                    buttonAddNewAdv.setEnabled(true);
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
                if(spinnerMinMileage.getSelectedItemPosition() > spinnerMaxMileage.getSelectedItemPosition()
                        && spinnerMaxMileage.getSelectedItemPosition() != 0
                        && (!isMinPriceCorrect || !isMaxPriceCorrect || !isMinMileageCorrect|| !isMaxMileageCorrect)) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Max mileage cannot be less than min mileage", Toast.LENGTH_LONG);
                    TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
                    if( v != null) v.setGravity(Gravity.CENTER);
                    toast.show();
                    buttonAddNewAdv.setEnabled(false);
                    Log.e("Max Mil B(if = true)", isMinPriceCorrect + " " + isMaxPriceCorrect
                            + " " + isMinMileageCorrect + " " + isMaxMileageCorrect + " " + counter);
                    isMaxMileageCorrect = false;
                    counter++;
                    Log.e("Max Mil A(if = true)", isMinPriceCorrect + " " + isMaxPriceCorrect
                            + " " + isMinMileageCorrect + " " + isMaxMileageCorrect + " " + counter);
                } else
                if (spinnerMaxPrice.getSelectedItemPosition() < spinnerMaxPrice.getSelectedItemPosition()
                        && (!isMinPriceCorrect || !isMaxPriceCorrect || !isMinMileageCorrect|| !isMaxMileageCorrect)) {
                    Log.e("Max Mil B(if = true/2)", isMinPriceCorrect + " " + isMaxPriceCorrect
                            + " " + isMinMileageCorrect + " " + isMaxMileageCorrect + " " + counter);
                    buttonAddNewAdv.setEnabled(false);
                    isMaxMileageCorrect = false;
                    counter++;
                    Log.e("Max Mil A(if = true/2)", isMinPriceCorrect + " " + isMaxPriceCorrect
                            + " " + isMinMileageCorrect + " " + isMaxMileageCorrect + " " + counter);
                } else
                if(spinnerMinMileage.getSelectedItemPosition() < spinnerMaxMileage.getSelectedItemPosition()
                        && counter == 5
                        ) {
                    Log.e("Max Mil B(if = true/3)", isMinPriceCorrect + " " + isMaxPriceCorrect
                            + " " + isMinMileageCorrect + " " + isMaxMileageCorrect + " " + counter);
                    buttonAddNewAdv.setEnabled(true);
                    isMaxMileageCorrect = true;
                    isMinMileageCorrect = true;
                    counter++;
                    Log.e("Max Mil A(if = true/3)", isMinPriceCorrect + " " + isMaxPriceCorrect
                            + " " + isMinMileageCorrect + " " + isMaxMileageCorrect + " " + counter);
                } else
                if(spinnerMinMileage.getSelectedItemPosition() < spinnerMaxMileage.getSelectedItemPosition()
                        && (!isMinPriceCorrect || !isMaxPriceCorrect || !isMinMileageCorrect|| !isMaxMileageCorrect)
                        ){
                    Log.e("Max Mil B(if = true/4)", isMinPriceCorrect + " " + isMaxPriceCorrect
                            + " " + isMinMileageCorrect + " " + isMaxMileageCorrect + " " + counter);
                    buttonAddNewAdv.setEnabled(false);
                    isMaxMileageCorrect = true;
                    isMinMileageCorrect = true;
                    counter++;
                    Log.e("Max Mil A(if = true/4)", isMinPriceCorrect + " " + isMaxPriceCorrect
                            + " " + isMinMileageCorrect + " " + isMaxMileageCorrect + " " + counter);
                } else {
                    Log.e("Max Mil B(if = false)", isMinPriceCorrect + " " + isMaxPriceCorrect
                            + " " + isMinMileageCorrect + " " + isMaxMileageCorrect + " " + counter);
                    if(counter > 5) {
                        isMaxMileageCorrect = true;
                        isMinMileageCorrect = true;
                    }
                    counter++;
                    Log.e("Max Mil A(if = false)", isMinPriceCorrect + " " + isMaxPriceCorrect
                            + " " + isMinMileageCorrect + " " + isMaxMileageCorrect + " " + counter);
                    buttonAddNewAdv.setEnabled(true);
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
             //   if()
            }
        });

    }
}