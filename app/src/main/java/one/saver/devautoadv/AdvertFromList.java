package one.saver.devautoadv;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdvertFromList extends Activity implements Imageutils.ImageAttachmentListener{
    Button remove;
    Button toMyAdList;
    Button update;
    ImageView logoMake;
    TextView textMake;
    Spinner spinnerModel;
    Spinner spinnerColor;
    Spinner spinnerMinPrice;
    Spinner spinnerMaxPrice;
    Spinner spinnerMinMileage;
    Spinner spinnerMaxMileage;
    ImageButton imgIsMain;
    String[] model;
    String[] colors;
    String[] priceMin;
    String[] priceMax;
    String[] mileageMin;
    String[] mileageMax;
    DataBaseHelper dbHelper;
    int makeIndex;
    int indexNumber;
    int isMain = 0;
    int intMinPrice;
    int intMaxPrice;
    int intMinMileage;
    int intMaxMileage;
    ImageView image_1;
    ImageView image_2;
    int imageViewSelected = 0;
    Imageutils imageutils;
    private Bitmap bitmap;
    private String file_name;
    String pathImage_1 = "";
    String pathImage_2 = "";
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
        spinnerModel = (Spinner) findViewById(R.id.spinnerModel);
        spinnerColor = (Spinner) findViewById(R.id.spinnerColor);
        spinnerMinPrice = (Spinner) findViewById(R.id.spinnerMinPrice);
        spinnerMaxPrice = (Spinner) findViewById(R.id.spinnerMaxPrice);
        spinnerMinMileage = (Spinner) findViewById(R.id.spinnerMinMileage);
        spinnerMaxMileage = (Spinner) findViewById(R.id.spinnerMaxMileage);
        imgIsMain = (ImageButton) findViewById(R.id.imageButtonIsMain);
        image_1 = (ImageView) findViewById(R.id.imageView_1);
        image_2 = (ImageView) findViewById(R.id.imageView_2);
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

        model = models_arrays.get(advert.getMakeIndex() + 1);
        ArrayAdapter<String> adapterModel = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                model);
        adapterModel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerModel.setAdapter(adapterModel);
        spinnerModel.setSelection(advert.getModelIndex());
        colors = getResources().getStringArray(R.array.colors);
        ArrayAdapter<String> adapterColor = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                colors);
        adapterColor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerColor.setAdapter(adapterColor);
        int indexColor = 0;
        for(int i = 0; i < colors.length; i++) {
            if(colors[i].toString().equals(advert.getColor())) {
                indexColor = i;
                Log.e("indexColor",Integer.toString(i));
            }
        }
        spinnerColor.setSelection(indexColor);
        priceMin = getResources().getStringArray(R.array.priseMin);
        ArrayAdapter<String> adapterMinPrice = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                priceMin);
        adapterMinPrice.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMinPrice.setAdapter(adapterMinPrice);
        int indexMinPrice = 0;
        switch (advert.getMinPrice()) {
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
                indexMinPrice = advert.getMinPrice() / 1000;
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
        switch (advert.getMaxPrice()) {
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
                indexMaxPrice = advert.getMaxPrice() / 1000;
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
        switch (advert.getMinMileage()) {
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
                indexMinMileage = advert.getMinMileage() / 10000 + 2;
            }
        }
        spinnerMinMileage.setSelection(indexMinMileage);
        mileageMax = getResources().getStringArray(R.array.maxMileage);
        ArrayAdapter<String> adapterMaxMileage = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                mileageMax);
        adapterMaxMileage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMaxMileage.setAdapter(adapterMaxMileage);
        int indexMaxMileage = 0;
        switch (advert.getMaxMileage()) {
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
                indexMaxMileage = advert.getMaxMileage() / 10000 + 2;
            }
        }
        spinnerMaxMileage.setSelection(indexMaxMileage);
        if(advert.getImage_1().contains(".png")) {
            image_1.setImageBitmap(BitmapFactory.decodeFile(advert.getImage_1()));
        } else {
            image_1.setImageResource(R.drawable.ph_add_image);
        }
        if(advert.getImage_2().contains(".png")) {
            image_2.setImageBitmap(BitmapFactory.decodeFile(advert.getImage_2()));
        } else {
            image_2.setImageResource(R.drawable.ph_add_image);
        }
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
                if(spinnerMinPrice.getSelectedItemPosition() > spinnerMaxPrice.getSelectedItemPosition()
                        && spinnerMaxPrice.getSelectedItemPosition() != 0) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Max price cannot be less than min price", Toast.LENGTH_SHORT);
                    TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
                    if( v != null) v.setGravity(Gravity.CENTER);
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
                if(spinnerMinMileage.getSelectedItemPosition() > spinnerMaxMileage.getSelectedItemPosition()
                        && spinnerMaxMileage.getSelectedItemPosition() != 0) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Min mileage cannot be bigger than max mileage", Toast.LENGTH_SHORT);
                    TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
                    if( v != null) v.setGravity(Gravity.CENTER);
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
                if(spinnerMinMileage.getSelectedItemPosition() > spinnerMaxMileage.getSelectedItemPosition()) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Max mileage cannot be less than min mileage", Toast.LENGTH_SHORT);
                    TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
                    if( v != null) v.setGravity(Gravity.CENTER);
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
        imgIsMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imgIsMain.getTag().equals(android.R.drawable.btn_star_big_off)) {
                    imgIsMain.setTag(android.R.drawable.btn_star_big_on);
                    imgIsMain.setImageResource(android.R.drawable.btn_star_big_on);
                    isMain = 1;
                    Log.e("Is the advert main?","TRUE");
                } else {
                    imgIsMain.setTag(android.R.drawable.btn_star_big_off);
                    imgIsMain.setImageResource(android.R.drawable.btn_star_big_off);
                    isMain = 0;
                    Log.e("Is the advert main?","FALSE");
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
        imageutils =new Imageutils(this);
        image_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("ImageView 1", "ImageView 1 was pressed");
                Log.e("Opening Activity", "opening ImageAttachmentActivity.Activity");
               //       startActivity(new Intent(AdvertFromList.this, ImageAttachmentActivity.class));
                imageViewSelected = 1;
                imageutils.imagepicker(1);
            }
        });
        image_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("ImageView 2", "ImageView 2 was pressed");
                Log.e("Opening Activity", "opening ImageAttachmentActivity.Activity");
                imageViewSelected = 2;
                imageutils.imagepicker(1);
            }
        });
    }
 /*   public void image_attachment(int from, String filename, Bitmap file, Uri uri) {
        this.bitmap=file;
        this.file_name=filename;
        //     iv_attachment.setImageBitmap(file);
        switch (imageViewSelected) {
            case 1: {
                image_1.setImageBitmap(file);
                String path =  Environment.getExternalStorageDirectory() + File.separator + Constants.DIRECTORY + File.separator;
                TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE); //receiving IMEI (Phone ID)
                String device_id = tm.getDeviceId();
                Long tsLong = System.currentTimeMillis()/1000;
                String ts = tsLong.toString();
                filename = device_id + "_" + ts + ".png";
                imageutils.createImage(file,filename,path,false);
                Log.e("Filename", filename.toString());
                pathImage_1 = path + filename;
                Log.e("Image_1 path", pathImage_1);
                //   imageSaving(file);
                break;
            }
            case 2: {
                image_2.setImageBitmap(file);
                String path =  Environment.getExternalStorageDirectory() + File.separator + Constants.DIRECTORY + File.separator;
                //receiving IMEI (Phone ID)
                TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
                String device_id = tm.getDeviceId();
                Long tsLong = System.currentTimeMillis()/1000;
                String ts = tsLong.toString();
                filename = device_id + "_" + ts + ".png";
                imageutils.createImage(file,filename,path,false);
                Log.e("Filename", filename.toString());
                pathImage_2 = path + filename;
                Log.e("Image_2 path", pathImage_2);

                //   imageSaving(file);
                break;
            }
        }

    }  */
 @Override
 protected void onActivityResult(int requestCode, int resultCode, Intent data)
 {
     super.onActivityResult(requestCode, resultCode, data);
     imageutils.onActivityResult(requestCode, resultCode, data);

 }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        imageutils.request_permission_result(requestCode, permissions, grantResults);
    }
    private void imageSaving(Bitmap file) {
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            Log.e("Log", "SD-карта не доступна: " + Environment.getExternalStorageState());
            return;
        }
        // получаем путь к SD
        File sdPath = Environment.getExternalStorageDirectory();
        // добавляем свой каталог к пути
        sdPath = new File(sdPath.getAbsolutePath() + "/" + "MyFiles");
        // создаем каталог
        sdPath.mkdirs();
        //receiving IMEI (Phone ID)
        TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        String device_id = tm.getDeviceId();
        // формируем объект File, который содержит путь к файлу
        File sdFile = new File(sdPath, device_id + ".png");
        try {
            // открываем поток для записи
            BufferedWriter bw = new BufferedWriter(new FileWriter(sdFile));
            // пишем данные
            bw.write("Содержимое файла на SD");
            // закрываем поток
            bw.close();
            Log.d("Log", "Файл записан на SD: " + sdFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(sdFile);
            file.compress(Bitmap.CompressFormat.PNG, 100, out); // bmp is your Bitmap instance
            // PNG is a lossless format, the compression factor (100) is ignored
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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

    @Override
    public void image_attachment(int from, String filename, Bitmap file, Uri uri) {
        this.bitmap=file;
        this.file_name=filename;
        //     iv_attachment.setImageBitmap(file);
        switch (imageViewSelected) {
            case 1: {
                image_1.setImageBitmap(file);
                String path =  Environment.getExternalStorageDirectory() + File.separator + Constants.DIRECTORY + File.separator;
                TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE); //receiving IMEI (Phone ID)
                String device_id = tm.getDeviceId();
                Long tsLong = System.currentTimeMillis()/1000;
                String ts = tsLong.toString();
                filename = device_id + "_" + ts + ".png";
                imageutils.createImage(file,filename,path,false);
                Log.e("Filename", filename.toString());
                pathImage_1 = path + filename;
                Log.e("Image_1 path", pathImage_1);
                //   imageSaving(file);
                break;
            }
            case 2: {
                image_2.setImageBitmap(file);
                String path =  Environment.getExternalStorageDirectory() + File.separator + Constants.DIRECTORY + File.separator;
                //receiving IMEI (Phone ID)
                TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
                String device_id = tm.getDeviceId();
                Long tsLong = System.currentTimeMillis()/1000;
                String ts = tsLong.toString();
                filename = device_id + "_" + ts + ".png";
                imageutils.createImage(file,filename,path,false);
                Log.e("Filename", filename.toString());
                pathImage_2 = path + filename;
                Log.e("Image_2 path", pathImage_2);

                //   imageSaving(file);
                break;
            }
        }
    }
}
