package one.saver.devautoadv;

import android.bluetooth.le.AdvertiseCallback;
import android.bluetooth.le.AdvertiseSettings;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconParser;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Button buyer;
    Button seller;
    Button myQueries;
    Button myAdverts;
    DataBaseHelper dbHelper;
    BeaconTransmission bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle); // My changes: In original version was deprecated method - drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        buyer = (Button)findViewById(R.id.Button1);
        seller = (Button)findViewById(R.id.Button2);
        myQueries = (Button) findViewById(R.id.button3);
        myAdverts = (Button) findViewById(R.id.button4);
        buyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Button Buyer", "opening Buyer.Activity");
                startActivity(new Intent(MainActivity.this, Buyer.class));
            }
        });
        seller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Button Seller", "opening Seller.Activity");
                startActivity(new Intent(MainActivity.this, Seller.class));
            }
        });
        myQueries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("To my queries", "QueriesList activity is opening.");
            }
        });
        myAdverts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AdvertList.class));
                Log.e("To my adverts", "AdvertList activity is opening.");
            }
        });
        dbHelper = new DataBaseHelper(this);
   //     startTransmission();
        bt = new BeaconTransmission();
        bt.execute();
    }

 /*   private void startTransmission() {
        while(true) {
            Advert advert = dbHelper.getMainAdvert(1);
            bleTransmission(advert);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }  */

    private void bleTransmission(Advert advert) {
        Log.i("Transmission: ", "Starting...");
        BeaconLayout beaconLayout = new BeaconLayout();
        String strLayout = "";
        if(advert.getMakeIndex() < 10) {
            strLayout = "0" + Integer.toString(advert.getMakeIndex());
        }
        if(advert.getMakeIndex() > 9) {
            strLayout = Integer.toString(advert.getMakeIndex());
        }
        Log.e("strLayout", strLayout);
        Beacon beacon = beaconLayout.beaconLayout(strLayout);
        BeaconParser beaconParser = beaconLayout.beaconParser();
        org.altbeacon.beacon.BeaconTransmitter beaconTransmitter =
                new org.altbeacon.beacon.BeaconTransmitter(getApplicationContext(), beaconParser);
        beaconTransmitter.startAdvertising(beacon, new AdvertiseCallback() {
            @Override
            public void onStartSuccess(AdvertiseSettings settingsInEffect) {
                super.onStartSuccess(settingsInEffect);
                Log.e("BEACON", "Advertisement start succeeded");
            }
            public void onStartFailure(int errorCode) {
                Log.e("BEACON", "Advertisement start failed with code: " + errorCode);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    class BeaconTransmission extends AsyncTask<Void, Void, Void> {
  //      DataBaseHelper dbHelper = new DataBaseHelper(this);
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.e("BLE Transmission", "Starting...");
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                while (true) {
                    Log.e("BLE Transmission", "Transmitting");
                    Advert advert = dbHelper.getMainAdvert(1);
            //        Log.e("Advert in new thread", advert.toString());
                    bleTransmission(advert);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            Log.e("BLE Transmission", "Started");
        }
    }
}
