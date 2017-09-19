package one.saver.devautoadv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class AdvertActivity extends Activity {
    TextView textMake;
    Spinner spinnerPrice;
    Spinner spinnerMinPrice;
    Spinner spinnerMaxPrice;
    String[] prise = {"No minimum", "Самара", "Вологда", "Волгоград", "Саратов", "Воронеж"};
    String[] priseMin = {"No minimum", "Самара", "Вологда", "Волгоград", "Саратов", "Воронеж"};
    String[] priseMax = {"No maximum", "Самара", "Вологда", "Волгоград", "Саратов", "Воронеж"};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advert);
        textMake = (TextView) findViewById(R.id.textMake);
        spinnerPrice = (Spinner) findViewById(R.id.spinnerPrice);
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
        ArrayAdapter<String> adapterPrice = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, prise);
        // Определяем разметку для использования при выборе элемента
        adapterPrice.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        spinnerPrice.setAdapter(adapterPrice);
        ArrayAdapter<String> adapterMinPrice = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, priseMin);
        adapterMinPrice.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMinPrice.setAdapter(adapterMinPrice);
        ArrayAdapter<String> adapterMaxPrice = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, priseMax);
        adapterMaxPrice.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMaxPrice.setAdapter(adapterMaxPrice);

    }
}