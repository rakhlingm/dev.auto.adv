package one.saver.devautoadv;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.SimpleAdapter;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Doron Yechezkel on 9/30/2017.
 */

public class InvitationReceiver extends AsyncTask<Invitation, Void, Invitation> {
    URL url;
    HttpURLConnection urlConnection = null;
    int responseCode = 0;
    String strInvitation = "";
    public InvitationReceiver(){
        //set context variables if required
    }
    protected void uploadImage() {

    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.e("InvitationReceiver", "Started");
    }
    @Override
    protected Invitation doInBackground(Invitation... invitations) {
    //    Log.e("Invitation from server", strInvitation);
        for (Invitation invitation : invitations) {
            Log.e("Invit... from AsyncTask", invitation.toString());
            try {
                invitation = getInvitation(invitation.getIndexNumber(), invitation.getIMEI());
                return invitation;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    @Override
    protected void onPostExecute(Invitation result) {
        super.onPostExecute(result);
    }
    private Invitation getInvitation(int indexNumberUrl, String imeiUrl) {
        Invitation invitation = new Invitation();
        try{
            url = new URL("http://37.46.32.119:8080/CarsApp/rest/Admin/getInvitation/"
                    + Integer.toString(indexNumberUrl) + "/" + imeiUrl);
            Log.e("URL for server", url.toString());

            urlConnection = (HttpURLConnection) url
                    .openConnection();
            responseCode = urlConnection.getResponseCode();

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    urlConnection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                strInvitation = inputLine;
                try {
                    JSONObject jsonInvitaion = new JSONObject(inputLine);
                    String indexNumber = jsonInvitaion.getString("indexNumber");
                    String imei = jsonInvitaion.getString("imei");
                    String makeIndex = jsonInvitaion.getString("makeIndex");
                    String modelIndex = jsonInvitaion.getString("modelIndex");
                    String make = jsonInvitaion.getString("make");
                    String model = jsonInvitaion.getString("model");
                    String color = jsonInvitaion.getString("color");
                    String minPrice = jsonInvitaion.getString("minPrice");
                    String maxPrice = jsonInvitaion.getString("maxPrice");
                    String minMileage = jsonInvitaion.getString("minMileage");
                    String maxMileage = jsonInvitaion.getString("maxMileage");
                    String image_1 = jsonInvitaion.getString("image_1");
                    String image_2 = jsonInvitaion.getString("image_2");
                    invitation.setIndexNumber(Integer.parseInt(indexNumber));
                    invitation.setIMEI(imei);
                    invitation.setMakeIndex(Integer.parseInt(makeIndex));
                    invitation.setModelIndex(Integer.parseInt(modelIndex));
                    invitation.setMake(make);
                    invitation.setModel(model);
                    invitation.setColor(color);
                    invitation.setMinPrice(Integer.parseInt(minPrice));
                    invitation.setMaxPrice(Integer.parseInt(maxPrice));
                    invitation.setMinMileage(Integer.parseInt(minMileage));
                    invitation.setMaxMileage(Integer.parseInt(maxMileage));
                    invitation.setImage_1(image_1);
                    invitation.setImage_2(image_2);
                    Log.e("-Invitation from JSON-", invitation.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
           //  Log.e("Invitation from JSON", strInvitation);
            }
            return invitation;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return invitation;
    }
    public static String advertSender(String url, Advert advert)
            throws Exception {
        URL object=new URL(url);
        HttpURLConnection con = (HttpURLConnection) object.openConnection();
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestMethod("POST");
        JSONObject advertJson   = new JSONObject();
        //     advertJson.put("adv", "adv");
        advertJson.put("indexNumber", advert.getIndexNumber());
        advertJson.put("imei", advert.getIMEI());
        advertJson.put("makeIndex", advert.getMakeIndex());
        advertJson.put("modelIndex", advert.getModelIndex());
        advertJson.put("make", advert.getMake());
        advertJson.put("model", advert.getModel());
        advertJson.put("color", advert.getColor());
        advertJson.put("minPrice", advert.getMinPrice());
        advertJson.put("maxPrice", advert.getMaxPrice());
        advertJson.put("minMileage", advert.getMinMileage());
        advertJson.put("maxMileage", advert.getMaxMileage());
        advertJson.put("image_1", advert.getImage_1());
        advertJson.put("image_2", advert.getImage_2());
        System.out.println(advertJson);
        OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
        wr.write(advertJson.toString());
        wr.flush();
        //display what returns the POST request
        StringBuilder sb = new StringBuilder();
        int HttpResult = con.getResponseCode();
        Log.e("HttpResult", Integer.toString(HttpResult));
        if (HttpResult == HttpURLConnection.HTTP_OK) {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), "utf-8"));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();
            //        System.out.println("" + sb.toString());
            Log.e("Server response: ", "" + sb.toString());

        } else {
            System.out.println(con.getResponseMessage());
        }
        return sb.toString();
    }
    public void executeMultiPartRequest(String urlString, File file, String fileName, String fileDescription) throws Exception
    {
        HttpClient client = new DefaultHttpClient() ;
        HttpPost postRequest = new HttpPost(urlString) ;
        try
        {
            //Set various attributes
            MultipartEntity multiPartEntity = new MultipartEntity() ;
            multiPartEntity.addPart("fileDescription", new StringBody(fileDescription != null ? fileDescription : "")) ;
            multiPartEntity.addPart("fileName", new StringBody(fileName != null ? fileName : file.getName())) ;

            FileBody fileBody = new FileBody(file, "application/octect-stream") ;
            //Prepare payload
            multiPartEntity.addPart("attachment", fileBody) ;

            //Set to request body
            postRequest.setEntity(multiPartEntity) ;

            //Send request
            HttpResponse response = client.execute(postRequest) ;

            //Verify response if any
            if (response != null)
            {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace() ;
        }
    }

}