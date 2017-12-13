package one.saver.devautoadv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class InvitationList extends Activity {
    ListView list;
    DataBaseHelper dbHelper;
    List<Invitation> invitationList = new ArrayList<Invitation>();
    public	Integer[] makeLogoIds = { R.drawable.audi, R.drawable.bmw, R.drawable.citroen,
            R.drawable.fiatlogo, R.drawable.ford, R.drawable.honda, R.drawable.hyundai, R.drawable.landrover,
            R.drawable.lexus, R.drawable.mazda, R.drawable.mercedes_benz, R.drawable.mitsubishi, R.drawable.nissan,
            R.drawable.opel, R.drawable.seat, R.drawable.skoda, R.drawable.subaru,
            R.drawable.thumbsvolkswagen, R.drawable.toyota, R.drawable.volvo};
    public	Integer[] isMainLogoIds = { android.R.drawable.btn_star_big_off, android.R.drawable.btn_star_big_on};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitation_list);
        dbHelper = new DataBaseHelper(this);

        invitationList = dbHelper.getAllInvitations();
        String[] makeArray = new String[invitationList.size()];
        String[] modelArray = new String[invitationList.size()];
        Integer[] makeLogoImgid = new Integer[invitationList.size()];
        Integer[] isReadImgid = new Integer[invitationList.size()];
        for (int i = 0; i < makeArray.length; i++) {
            makeArray[i] = invitationList.get(i).getMake();
            modelArray[i] = invitationList.get(i).getModel();
            makeLogoImgid[i] = makeLogoIds[invitationList.get(i).getMakeIndex()];
            isReadImgid[i] = isMainLogoIds[invitationList.get(i).getIsRead()];
            Log.e("makeArray", Integer.toString(i));
            Log.e("Make", makeArray[i]);
            Log.e("Model", modelArray[i]);
            Log.e("Is read", Integer.toString(isReadImgid[i]));
        }
        InvitationListAdapter adapter=new InvitationListAdapter(this, makeArray, modelArray, makeLogoImgid, isReadImgid);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                //       Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(),
                        InvitationFromList.class);
                // passing array index
                i.putExtra("makeIndex", invitationList.get(position).getMakeIndex());
                i.putExtra("indexNumber", invitationList.get(position).getIndexNumber());
                startActivity(i);
                //         dbHelper.deleteAdvert(queryList.get(position));
            }
        });
    }
    public void onBackPressed(){
        Intent in = new Intent(getApplicationContext(),
                MainActivity.class);
        startActivity(in);
        finish();
    }
    @Override
    public void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
}