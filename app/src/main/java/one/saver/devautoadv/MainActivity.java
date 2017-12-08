package one.saver.devautoadv;

import android.Manifest;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.AdvertiseCallback;
import android.bluetooth.le.AdvertiseSettings;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AlertDialog;
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

import java.util.Calendar;
import java.util.List;

import static android.support.v4.app.NotificationCompat.DEFAULT_SOUND;
import static android.support.v4.app.NotificationCompat.DEFAULT_VIBRATE;
import static android.support.v4.app.NotificationCompat.PRIORITY_MAX;
import static android.support.v4.app.NotificationCompat.VISIBILITY_PUBLIC;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Button buyer;
    Button seller;
    Button myQueries;
    Button myAdverts;
    Button invitations;
    Button QA_Query;
    DataBaseHelper dbHelper;
    BeaconTransmission bt;
    /* From Advert2 */
    BluetoothManager btManager;
    BluetoothAdapter btAdapter;
    BluetoothLeScanner btScanner;
    private ScanSettings settings;
    private List<ScanFilter> filters;
    public	Integer[] makeAudioFile = { R.raw.audi, R.raw.bmw, R.raw.citroen,
            R.raw.fiat, R.raw.ford, R.raw.honda, R.raw.hyundai, R.raw.land_rover,
            R.raw.lexus, R.raw.mazda, R.raw.mercedes_benz, R.raw.mitsubishi, R.raw.nissan,
            R.raw.opel, R.raw.seat, R.raw.skoda, R.raw.subaru,
            R.raw.volkswagen, R.raw.toyota, R.raw.volvo};
    int indexMakeAudioFile = 0;
    BackgroundSound runner;
    private final static int REQUEST_ENABLE_BT = 1;
    private static final int PERMISSION_REQUEST_COARSE_LOCATION = 1;
    boolean isAdvertChanged = true;
    int intIsAdvertChanged = 0;
    Invitation invitation;
    ImageAdapterSeller imageAdapter = new ImageAdapterSeller(this);


        org.altbeacon.beacon.BeaconTransmitter beaconTransmitter;
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
        invitations = (Button) findViewById(R.id.button5);
        QA_Query = (Button) findViewById(R.id.button6);
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
                startActivity(new Intent(MainActivity.this, QueryList.class));
            }
        });
        myAdverts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AdvertList.class));
                Log.e("To my adverts", "AdvertList activity is opening.");
            }
        });
        invitations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, InvitationList.class));
                Log.e("To QA_QUERY", "InvitationList activity is opening.");
            }
        });
        QA_Query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, QA_Query.class));
                Log.e("To QA_QUERY", "QA_Query activity is opening.");
            }
        });
        dbHelper = new DataBaseHelper(this);
   //     startTransmission();
   //     bt = new BeaconTransmission();
   //     bt.execute();

        /* From Advert2 */

          BackgroundScanning bs = new BackgroundScanning();
          bs.execute();
    //    invitationNotification(1);
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

    private boolean bleTransmission(Advert advert) {
        Log.i("Transmission: ", "Starting...");
        BeaconLayout beaconLayout = new BeaconLayout();
        String strLayout = "";
        StringBuffer sbIMEI = new StringBuffer (advert.getIMEI());
        String strInsert = "-";
        sbIMEI.insert (0, "0");
        sbIMEI.insert (6, strInsert);
        sbIMEI.insert (11, strInsert);
        sbIMEI.insert (16, strInsert);
        String IMEI = sbIMEI.toString();
        StringBuffer sbIndexNumber = new StringBuffer (Integer.toString(advert.getIndexNumber()));
        String indexNumber = "";
        if(advert.getIndexNumber() < 10) {
            indexNumber = sbIndexNumber.insert(0, "00-0").toString();
        }
        if(advert.getIndexNumber() > 9 && advert.getIndexNumber() < 100) {
            indexNumber = sbIndexNumber.insert(0, "00-").toString();
        }
        if(advert.getIndexNumber() > 99) {
            sbIndexNumber.insert(0, "0");
            indexNumber = sbIndexNumber.insert(2, strInsert).toString();
        }
        StringBuffer sbMakeIndex = new StringBuffer (Integer.toString(advert.getMakeIndex()));
        String makeIndex = "";
        if(advert.getMakeIndex() < 10) {
            sbMakeIndex.insert(0, "0");
            makeIndex = sbMakeIndex.toString();
        }
        if(advert.getMakeIndex() > 9) {
            makeIndex = sbMakeIndex.toString();
        }
        strLayout = IMEI + indexNumber + makeIndex;
        Log.e("strLayout", strLayout);
        Beacon beacon = beaconLayout.beaconLayout(strLayout);
        BeaconParser beaconParser = beaconLayout.beaconParser();
        beaconTransmitter =
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
        return  true;
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
    private void invitationNotification(int index) {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {
            String[] events = new String[6];
            events[0] = Integer.toString(index);
            events[1] = "X6";
            events[2] = "White";
            events[3] = "$70000";
            Bitmap modelIcon = BitmapFactory.decodeResource(getResources(),
                    R.drawable.bmw);

            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
                    this).setAutoCancel(true)
                    .setContentTitle("CarApp")
                    .setSmallIcon(android.R.drawable.btn_star_big_on).setLargeIcon(modelIcon)
                    .setContentText("The car you are interested in");

            NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();

            // Sets a title for the Inbox style big view
            inboxStyle.setBigContentTitle("Event Details");

            // Moves events into the big view
            for (int i = 0; i < events.length; i++) {

                inboxStyle.addLine(events[i]);
            }
            // Moves the big view style object into the notification object.
            mBuilder.setStyle(inboxStyle);

            // Creates an explicit intent for an Activity in your app
            Intent resultIntent = new Intent(this, InvitationActivity.class);

            // The stack builder object will contain an artificial back stack for
            // the
            // started Activity.
            // This ensures that navigating backward from the Activity leads out of
            // your application to the Home screen.
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

            // Adds the back stack for the Intent (but not the Intent itself)
            stackBuilder.addParentStack(InvitationActivity.class);

            // Adds the Intent that starts the Activity to the top of the stack
            stackBuilder.addNextIntent(resultIntent);
            PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            mBuilder.setContentIntent(resultPendingIntent);

            NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            // mId allows you to update the notification later on.
            mNotificationManager.notify(100, mBuilder.build());
        } else {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:0523960798"));
            PendingIntent callResult = PendingIntent.getActivity(this,
                    (int) Calendar.getInstance().getTimeInMillis(), callIntent, 0);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            //        startActivity(callIntent);

            Intent resultIntent = new Intent(this, InvitationList.class);
            resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent piResult = PendingIntent.getActivity(this,
                    (int) Calendar.getInstance().getTimeInMillis(), resultIntent, 0);
            NotificationCompat.BigPictureStyle bigPictureStyle =
                    new NotificationCompat.BigPictureStyle();
            bigPictureStyle.bigPicture(
                    BitmapFactory.decodeResource(getResources(),
                            R.drawable.bmw)).build();

            //build notification
            NotificationCompat.Builder builder =
                    new NotificationCompat.Builder(this)
                    //        .setSmallIcon(android.R.drawable.btn_star_big_on)
                            .setSmallIcon(imageAdapter.makeLogoIds[index])
                            .setContentTitle("CarsApp")
                            .setContentText("Row 1")
                            .setContentInfo("Row 2")
                            .setStyle(bigPictureStyle)
                            .setVisibility(VISIBILITY_PUBLIC)
                            .setDefaults(DEFAULT_SOUND | DEFAULT_VIBRATE)
                            .setPriority(NotificationCompat.PRIORITY_MAX)
                            //        .setFullScreenIntent(piResult, true)
                            //        .addAction(android.R.drawable.btn_star_big_on, "Log",
                            //        PendingIntent.getActivity(getApplicationContext(), 0,
                            //                        getIntent(), 0, null))
                            .addAction(android.R.drawable.sym_action_call, "Call to seller", callResult)
                            .addAction(android.R.drawable.btn_star_big_on, "See more", piResult);
            NotificationManager notificationManager =(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0, builder.build());
        }
    }
    private Invitation invitation() {

        return invitation;
    }
    class BeaconTransmission extends AsyncTask<Void, Void, Void> {
  //      DataBaseHelper dbHelper = new DataBaseHelper(this);
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.e("BLE AsyncTask", "Starting...");
        }

        @Override
        protected Void doInBackground(Void... params) {
            int indexMainAdvert = 0;
            boolean isBLETransmission = false;
            Advert advert = null;
       //     try {
                try{
                    advert = dbHelper.getMainAdvert(1);
                } catch (Exception e) {
                    Log.e("Advert is null", "Advert is null");
                }

                if(advert != null){
                    indexMainAdvert = advert.getIndexNumber();
                    Log.e("Advert is not null", advert.toString());
                    isBLETransmission = bleTransmission(advert);
                } else {
                    Log.e("Advert is null", "Advert is null");
                }

                while (true) {
                    Log.e("BLE While", "True");
                    try{
                        advert = dbHelper.getMainAdvert(1);
                    } catch (Exception e) {
                        Log.e("Advert in null", "Advert is null");
                        advert = null;
                    }
                    if(advert != null) {
                        if(indexMainAdvert != advert.getIndexNumber()) {
                            indexMainAdvert = advert.getIndexNumber();
                            Log.e("MainAdvert was changed", Integer.toString(indexMainAdvert));
                            if(isBLETransmission) {
                                beaconTransmitter.stopAdvertising();
                                isBLETransmission = false;
                            }
                            isBLETransmission = bleTransmission(advert);
                        }

                    } else {
                      if(advert == null)   {
                          if(isBLETransmission) {
                              beaconTransmitter.stopAdvertising();
                              isBLETransmission = false;
                          }
                          indexMainAdvert = 0;
                      }
                    }
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        /*    } catch (Exception e) {
                e.printStackTrace();
            }   */
      //      return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            Log.e("BLE Transmission", "Started");
        }
    }
    /* From Advert2 */
    private ScanCallback leScanCallback = new ScanCallback() {
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            //          peripheralTextView.append("Device Name: " + result.getDevice().getName() + " rssi: " + result.getRssi() + "\n");
            final StringBuilder builder = new StringBuilder();
            String strPLU = "";
       /*     if (result.getDevice().getAddress().toString().contains("45:5B")) {
                Log.e("SCANNER", "Device Name: " + result.getDevice().getAddress() + " rssi: " + result.getRssi()
                       + "  " + result.getDevice().getUuids() + "\n");
                peripheralTextView.append("Device name_" + result.getDevice().getAddress() + " rssi: " + result.getRssi() + "\n");
          */      byte[] mScanRecord = result.getScanRecord().getBytes();
            for (int j = 0; j < mScanRecord.length; j++) {
                strPLU =  result.getDevice().getAddress().toString() + "  " + builder.append(String.format("%02x", mScanRecord[j])).toString();
            }
            //        Log.e("String", strPLU);
            //        Log.e("String", strPLU.substring(27, 33));
            if(strPLU.substring(27, 33).contains("434152")) {
                     Log.e("String", strPLU);
                //         Log.e("String", strPLU.substring(33, 39));
                int makeIndex = Integer.parseInt(strPLU.substring(53, 55));
                indexMakeAudioFile = makeIndex;
                if(makeIndex != intIsAdvertChanged) {
                    intIsAdvertChanged = makeIndex;
                    isAdvertChanged = true;
                    runner = new BackgroundSound();
                    runner.execute();
                } else {
                    isAdvertChanged = false;
                }
                String strMakeIndex = getResources().getStringArray(R.array.makeArray)[makeIndex];
                Log.e("Make", strMakeIndex);
                Log.e("intIsAdvertChanged", Integer.toString(intIsAdvertChanged));
        //        peripheralTextView.append("Make:    " + strMakeIndex + "\n");
                //             peripheralTextView.append("Device name_" + result.getDevice().getAddress() + " rssi: " + result.getRssi() + strMakeIndex + "\n");

            }



       /*     } else {
            //             peripheralTextView.setText("PLU is not found!"  + "\n");
             //            Log.e("SCANNER", "Device Name: " + result.getDevice().getAddress() + " rssi: " + result.getRssi() + "\n");
           //     Log.e("SCANNER/Else", "Device Name: " + result.getDevice().getAddress() + " rssi: " + result.getRssi()
          //              + "  " + result.getScanRecord().toString() + "\n");
          //      peripheralTextView.append("Device name_" + result.getDevice().getAddress() + " rssi: " + result.getRssi() + "\n");

            }  */
            //  Log.e("SCANNER", "Device Name: " + result.getDevice().getAddress() + " rssi: " + result.getRssi() + "\n");
            //  peripheralTextView.append("Device name_" + result.getDevice().getAddress() + " rssi: " + result.getRssi() + "\n");

            // auto scroll for text view

            //          peripheralTextView.append("PLU_" + result.getDevice().getName() + " is found!"  + "\n");
     //       final int scrollAmount = peripheralTextView.getLayout().getLineTop(peripheralTextView.getLineCount()) - peripheralTextView.getHeight();
            // if there is no need to scroll, scrollAmount will be <=0
     //       if (scrollAmount > 0)
     //           peripheralTextView.scrollTo(0, scrollAmount);
        }
    };

    public void startScanning() {
        Log.e("Scanner", "Start scanning");
   //     peripheralTextView.setText("");
   //    startScanningButton.setVisibility(View.INVISIBLE);
   //     stopScanningButton.setVisibility(View.VISIBLE);
        ScanSettings.Builder scanSettingsBuilder = new ScanSettings.Builder();
        scanSettingsBuilder.setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY);
        scanSettingsBuilder.setReportDelay(0);
        settings = scanSettingsBuilder.build();
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                btScanner.startScan(filters, settings, leScanCallback);
            }
        });
    }

    public void stopScanning() {
        Log.e("Scanner", "Stop scanning");
 //       peripheralTextView.append("Stopped Scanning");
 //       startScanningButton.setVisibility(View.VISIBLE);
 //       stopScanningButton.setVisibility(View.INVISIBLE);
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                btScanner.stopScan(leScanCallback);
            }
        });
    }
    public class BackgroundSound extends AsyncTask<Void, Void, Void> {
        MediaPlayer player = null;
        @Override
        protected Void doInBackground(Void... params) {
            invitationNotification(indexMakeAudioFile);
            //       MediaPlayer player = MediaPlayer.create(MainActivity.this, makeAudioFile[indexMakeAudioFile]);
            //      for(int i = 0; i < 3; i++){
            //     Log.e("File size", Integer.toString(makeAudioFile[indexMakeAudioFile]));
            player = MediaPlayer.create(MainActivity.this, makeAudioFile[indexMakeAudioFile]);
            player.setVolume(100,100);

            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    player.stop();
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    player = MediaPlayer.create(MainActivity.this, R.raw.word_you_are_interested_in);
                    player.setVolume(100,100);
                    player.start();
                    while(player.isPlaying()) {

                    }
                    player.stop();
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    player = MediaPlayer.create(MainActivity.this, R.raw.word_near_you);
                    player.setVolume(100,100);
                    player.start();
                    while(player.isPlaying()) {

                    }
                    player.stop();
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runner.onCancelled();
                }
            });
    /*        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    player.stop();
                    player = MediaPlayer.create(MainActivity.this, R.raw.word_near_you);
                    player.setVolume(100,100);
                    player.start();
                }
            });  */
            player.start();


            // play next audio file

            //             }  }
            //        player.setLooping(true); // Set looping
            //      });  player.setVolume(100,100);
            //    player.start();
            return null;
        }

    }
    public class BackgroundScanning extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            btManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
            btAdapter = btManager.getAdapter();
            btScanner = btAdapter.getBluetoothLeScanner();
            startScanning();
            return null;
        }
    }
}

