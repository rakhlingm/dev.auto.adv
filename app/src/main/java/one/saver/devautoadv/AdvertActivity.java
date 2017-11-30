package one.saver.devautoadv;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdvertActivity extends Activity implements Imageutils.ImageAttachmentListener {
    AdvertSender as;
    TextView textMake;
    ImageButton imageButton;
    int position;
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
    int isMain = 0;
    Button buttonToMyAccount;
    Button buttonAddNewAdv;
    Button getButtonToMyAccount;
    Button getButtonAddNewAdv;
    ImageView image_1;
    ImageView image_2;
    ImageView iv_attachment;
    //For Image Attachment
    private Bitmap bitmap;
    private String file_name;
    int imageViewSelected = 0;
    Imageutils imageutils;
    DataBaseHelper dbHelp;
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
        getButtonAddNewAdv = (Button) findViewById(R.id.buttonAddNewAdv);
        image_1 = (ImageView) findViewById(R.id.imageView_1);
        image_2 = (ImageView) findViewById(R.id.imageView_2);
        // get intent data
        Intent intent = getIntent();
        // Selected image id
        position = intent.getExtras().getInt("id");
        ImageAdapterSeller imageAdapter = new ImageAdapterSeller(this);
        ImageView imageView = (ImageView) findViewById(R.id.full_image_view_advert);
        imageView.setImageResource(imageAdapter.makeLogoIds[position]);
        textMake.setText(getResources().getStringArray(R.array.makeArray)[position]);
        strMake = textMake.getText().toString();
        Log.e("Make", strMake);
        imageButton = (ImageButton) findViewById(R.id.imageButtonIsMain);
        imageButton.setTag(android.R.drawable.btn_star_big_off);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageButton.getTag().equals(android.R.drawable.btn_star_big_off)) {
                    imageButton.setTag(android.R.drawable.btn_star_big_on);
                    imageButton.setImageResource(android.R.drawable.btn_star_big_on);
                    isMain = 1;
                    Log.e("Is the advert main", "TRUE");
                } else {
                    imageButton.setTag(android.R.drawable.btn_star_big_off);
                    imageButton.setImageResource(android.R.drawable.btn_star_big_off);
                    isMain = 0;
                    Log.e("Is the advert main", "FALSE");
                }
            }
        });
  /*      ArrayAdapter<String> adapterPrice = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, price);
        // Определяем разметку для использования при выборе элемента
        adapterPrice.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        spinnerPrice.setAdapter(adapterPrice);   */

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

        model = models_arrays.get(position + 1);
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
                startActivity(new Intent(AdvertActivity.this, AdvertList.class));
                Log.e("buttonToMyAccount", "AdvertList activity is opening.");

            }
        });
        imageutils = new Imageutils(this);
        image_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("ImageView 1", "ImageView 1 was pressed");
                Log.e("Opening Activity", "opening ImageAttachmentActivity.Activity");
                //      startActivity(new Intent(AdvertActivity.this, ImageAttachmentActivity.class));
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
        dbHelp = new DataBaseHelper(this);
        buttonAddNewAdv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                Advert advert = new Advert();
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
                advert.setIMEI(tm.getDeviceId());
                advert.setMakeIndex(position);
                advert.setModelIndex(spinnerModel.getSelectedItemPosition());
                advert.setMake(strMake);
                advert.setModel(strModel);
                advert.setColor(strColor);
                advert.setMinPrice(intMinPrice);
                advert.setMaxPrice(intMaxPrice);
                advert.setMinMileage(intMinMileage);
                advert.setMaxMileage(intMaxMileage);
                advert.setImage_1(pathImage_1);
                advert.setImage_2(pathImage_2);
                advert.setIsMain(isMain);
                Log.e("Advert", advert.toString());
                List<Advert> adverts = dbHelp.getAllAdverts();
                switch (isMain) {
                    case 0: {

                        break;
                    }
                    case 1: {
                        for (int i = 0; i < adverts.size(); i++) {
                            Advert advertUpdate = adverts.get(i);
                            Log.e("Advert != indexNumber", advertUpdate.toString());
                            advertUpdate.setIsMain(0);
                            dbHelp.updateAdvert(advertUpdate);
                        }
                        break;
                    }
                }
                dbHelp.addAdvert(advert);
                try {
                    as = new AdvertSender();
                    as.execute(pathImage_1, pathImage_2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(AdvertActivity.this, AdvertList.class));
                Log.e("buttonAddNewQuery", "AdvertList activity is opening.");

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imageutils.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        imageutils.request_permission_result(requestCode, permissions, grantResults);
    }

    @Override
    public void image_attachment(int from, String filename, Bitmap file, Uri uri) {
        this.bitmap = file;
        this.file_name = filename;
        //     iv_attachment.setImageBitmap(file);
        switch (imageViewSelected) {
            case 1: {
                image_1.setImageBitmap(file);
                String path = Environment.getExternalStorageDirectory() + File.separator + Constants.DIRECTORY + File.separator;
                TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE); //receiving IMEI (Phone ID)
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                String device_id = tm.getDeviceId();
                Long tsLong = System.currentTimeMillis() / 1000;
                String ts = tsLong.toString();
                filename = device_id + "_" + ts + ".png";
                imageutils.createImage(file, filename, path, false);
                Log.e("Filename", filename.toString());
                pathImage_1 = path + filename;
                Log.e("Image_1 path", pathImage_1);
                //   imageSaving(file);
                break;
            }
            case 2: {
                image_2.setImageBitmap(file);
                String path = Environment.getExternalStorageDirectory() + File.separator + Constants.DIRECTORY + File.separator;
                //receiving IMEI (Phone ID)
                TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                String device_id = tm.getDeviceId();
                Long tsLong = System.currentTimeMillis() / 1000;
                String ts = tsLong.toString();
                filename = device_id + "_" + ts + ".png";
                imageutils.createImage(file, filename, path, false);
                Log.e("Filename", filename.toString());
                pathImage_2 = path + filename;
                Log.e("Image_2 path", pathImage_2);

                //   imageSaving(file);
                break;
            }
        }

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
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
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
}