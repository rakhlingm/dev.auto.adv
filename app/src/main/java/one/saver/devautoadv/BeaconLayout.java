package one.saver.devautoadv;

import android.bluetooth.le.AdvertiseSettings;
import android.util.Log;
import android.util.TypedValue;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;

import java.util.Arrays;

import static android.bluetooth.le.AdvertiseSettings.ADVERTISE_TX_POWER_HIGH;

/**
 * Created by Doron Yechezkel on 8/5/2017.
 */

public class BeaconLayout {
    private BeaconManager beaconManager;

    public Beacon beaconLayout(String strBeacon){
        Beacon beacon = new Beacon.Builder()
                .setId1("52" + strBeacon + "0000-0000-0000-0000-000000000000")
                .setId2("1")
                .setId3("2")
                .setManufacturer(0x0118)
                .setTxPower(ADVERTISE_TX_POWER_HIGH)
                .setDataFields(Arrays.asList(new Long[] {0l}))
                .build();
        return beacon;
    }
    public BeaconParser beaconParser(){
        BeaconParser beaconParser = new BeaconParser()
                .setBeaconLayout("m:2-3=4341,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25");
        return beaconParser;
    }
}