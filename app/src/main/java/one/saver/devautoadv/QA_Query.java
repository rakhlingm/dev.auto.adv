package one.saver.devautoadv;

import android.app.NotificationManager;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class QA_Query extends AppCompatActivity {
    BluetoothManager btManager;
    BluetoothAdapter btAdapter;
    BluetoothLeScanner btScanner;
    private ScanSettings settings;
    private List<ScanFilter> filters;
    Button startScanningButton;
    Button stopScanningButton;
    TextView peripheralTextView;
    TextView textView;
    TextView textViewPLU;
    private final static int REQUEST_ENABLE_BT = 1;
    private static final int PERMISSION_REQUEST_COARSE_LOCATION = 1;
    boolean isAdvertChanged = true;
    int intIsAdvertChanged = 0;
    public	Integer[] makeAudioFile = { R.raw.audi, R.raw.bmw, R.raw.citroen,
            R.raw.fiat, R.raw.ford, R.raw.honda, R.raw.hyundai, R.raw.land_rover,
            R.raw.lexus, R.raw.mazda, R.raw.mercedes_benz, R.raw.mitsubishi, R.raw.nissan,
            R.raw.opel, R.raw.seat, R.raw.skoda, R.raw.subaru,
            R.raw.volkswagen, R.raw.toyota, R.raw.volvo};
    int indexMakeAudioFile = 0;
    BackgroundSound runner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qa_query);
        peripheralTextView = (TextView) findViewById(R.id.PeripheralTextView);
        peripheralTextView.setMovementMethod(new ScrollingMovementMethod());
        textView = (TextView) findViewById(R.id.TextView);
        textViewPLU = (TextView) findViewById(R.id.textView2);

        startScanningButton = (Button) findViewById(R.id.StartScanButton);
        peripheralTextView.append("Stopped Scanning");
        startScanningButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startScanning();
                //      textViewPLU.setText("  Press the SCAN button to stop!");
                //        textViewPLU.setText("PLU")
                //       textViewGPS.setText("GPS");
            }
        });

        stopScanningButton = (Button) findViewById(R.id.StopScanButton);
        stopScanningButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                stopScanning();
                //     textView.setText("  Press the SCAN button to PLU searching!");
                textViewPLU.setText("");
            }
        });
        stopScanningButton.setVisibility(View.INVISIBLE);

        btManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        btAdapter = btManager.getAdapter();
        btScanner = btAdapter.getBluetoothLeScanner();


        if (btAdapter != null && !btAdapter.isEnabled()) {
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
        }
    }

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
                //     Log.e("String", strPLU);
                //         Log.e("String", strPLU.substring(33, 39));
                int makeIndex = Integer.parseInt(strPLU.substring(53, 55));
                indexMakeAudioFile = makeIndex;
                if(makeIndex != intIsAdvertChanged) {
                    intIsAdvertChanged = makeIndex;
                    isAdvertChanged = true;
                    runner = new BackgroundSound();
                    runner.execute();
                    NotificationCompat.Builder mBuilder =
                            (NotificationCompat.Builder) new NotificationCompat.Builder(getBaseContext())
                                    .setPriority(2)
                                    .setSmallIcon(android.R.drawable.btn_star_big_on)
                                    .setContentTitle("CarApp")
                                    .setContentText("This is first notification!");
// Gets an instance of the NotificationManager service
                    NotificationManager notificationManager =(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

//to post your notification to the notification bar
                    notificationManager.notify(0 , mBuilder.build());
                } else {
                    isAdvertChanged = false;
                }
                String strMakeIndex = getResources().getStringArray(R.array.makeArray)[makeIndex];
                Log.e("Make", strMakeIndex);
                Log.e("intIsAdvertChanged", Integer.toString(intIsAdvertChanged));
                peripheralTextView.append("Make:    " + strMakeIndex + "\n");
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
            final int scrollAmount = peripheralTextView.getLayout().getLineTop(peripheralTextView.getLineCount()) - peripheralTextView.getHeight();
            // if there is no need to scroll, scrollAmount will be <=0
            if (scrollAmount > 0)
                peripheralTextView.scrollTo(0, scrollAmount);
        }
    };

    public void startScanning() {
        Log.e("Scanner", "Start scanning");
        peripheralTextView.setText("");
        startScanningButton.setVisibility(View.INVISIBLE);
        stopScanningButton.setVisibility(View.VISIBLE);
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
        peripheralTextView.append("Stopped Scanning");
        startScanningButton.setVisibility(View.VISIBLE);
        stopScanningButton.setVisibility(View.INVISIBLE);
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
            player = MediaPlayer.create(QA_Query.this, makeAudioFile[indexMakeAudioFile]);
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
                    player = MediaPlayer.create(QA_Query.this, R.raw.word_you_are_interested_in);
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
                    player = MediaPlayer.create(QA_Query.this, R.raw.word_near_you);
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
            player.start();
            return null;
        }
    }
}