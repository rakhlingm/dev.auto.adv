package one.saver.devautoadv;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.telephony.TelephonyManager;
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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AdvertActivity extends Activity implements Imageutils.ImageAttachmentListener{
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
    ImageView image_1;
    ImageView image_2;
    ImageView iv_attachment;
    //For Image Attachment
    private Bitmap bitmap;
    private String file_name;
    int imageViewSelected = 0;
    Imageutils imageutils;
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
        image_1 = (ImageView) findViewById(R.id.imageView_1);
        image_2 = (ImageView) findViewById(R.id.imageView_2);
        // get intent data
        Intent intent = getIntent();
        // Selected image id
        int position = intent.getExtras().getInt("id");
        ImageAdapterSeller imageAdapter = new ImageAdapterSeller(this);
        ImageView imageView = (ImageView) findViewById(R.id.full_image_view_advert);
        imageView.setImageResource(imageAdapter.mThumbIds[position]);
        textMake.setText(getResources().getStringArray(R.array.makeArray)[position]);

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

        model = models_arrays.get(position+1);
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
               } else {
                   Log.e("buttonAddNewAdv", "Your advert is adding know!");
               }
            }
        });
        imageutils =new Imageutils(this);
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

    }

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

    @Override
    public void image_attachment(int from, String filename, Bitmap file, Uri uri) {
        this.bitmap=file;
        this.file_name=filename;
 //       iv_attachment.setImageBitmap(file);
        switch (imageViewSelected) {
            case 1: {
                image_1.setImageBitmap(file);
                break;
            }
            case 2: {
                image_2.setImageBitmap(file);
                break;
            }
        }
  //      imageViewSelected = 0;

        String path =  Environment.getExternalStorageDirectory() + File.separator + "ImageAttach" + File.separator;
        imageutils.createImage(file,filename,path,false);
        Log.e("Filename", filename.toString());

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
}