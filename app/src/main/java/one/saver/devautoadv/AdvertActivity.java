package one.saver.devautoadv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AdvertActivity extends Activity {
    TextView textMake;
    Spinner spinnerPrice;
    Spinner spinnerMinPrice;
    Spinner spinnerMaxPrice;
    String[] price = {"No minimum", "Самара", "Вологда", "Волгоград", "Саратов", "Воронеж"};
    String[] priceMin;
    String[] priceMax;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advert);
        textMake = (TextView) findViewById(R.id.textMake);
     //   spinnerPrice = (Spinner) findViewById(R.id.spinnerPrice);
        spinnerMinPrice = (Spinner) findViewById(R.id.spinnerMinPrice);
        spinnerMaxPrice = (Spinner) findViewById(R.id.spinnerMaxPrice);
        // get intent data
        Intent intent = getIntent();
        // Selected image id
        int position = intent.getExtras().getInt("id");
        ImageAdapter imageAdapter = new ImageAdapter(this);
        ImageView imageView = (ImageView) findViewById(R.id.full_image_view);
        imageView.setImageResource(imageAdapter.mThumbIds[position]);
        textMake.setText(Integer.toString(position));
  /*      ArrayAdapter<String> adapterPrice = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, price);
        // Определяем разметку для использования при выборе элемента
        adapterPrice.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        spinnerPrice.setAdapter(adapterPrice);   */
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
        AdapterView.OnItemSelectedListener spinnerMinPriceListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast toast = Toast.makeText(getApplicationContext(), spinnerMinPrice.getSelectedItem().toString(), Toast.LENGTH_SHORT);
                toast.show();
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
                Toast toast = Toast.makeText(getApplicationContext(), spinnerMaxPrice.getSelectedItem().toString(), Toast.LENGTH_SHORT);
                toast.show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        };
        spinnerMaxPrice.setOnItemSelectedListener(spinnerMaxPriceListener);
    }
}