package one.saver.devautoadv;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;


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
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Doron Yechezkel on 9/30/2017.
 */

public class AdvertSender extends AsyncTask <Advert, Void, String> {
    URL url;
    HttpURLConnection urlConnection = null;
    int responseCode = 0;
    String sessionId = "";
    public AdvertSender(){
        //set context variables if required
    }
    protected void uploadImage() {

    }



    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.e("Why2???", "I'm here...");
    }
    @Override
    protected String doInBackground(Advert... adverts) {
        Log.e("HTTP", "HTTP is alive");
        String strURL = "http://37.46.32.119:8080/CarsApp/rest/Admin/sendAdvert";
        //     Advert advert = new Advert();
        for (Advert advert : adverts) {
            Log.e("Advert from AsyncTask", advert.toString());
            try {
                Log.e("Advert before File send", advert.toString());
                Log.e("Image_1", advert.getImage_1());
                advertSender(strURL,advert);
                File file = new File(advert.getImage_1()) ;
                Log.e("File for sending", file.toString());
                //Upload the file
                //      fileUpload.executeMultiPartRequest("http://localhost:8080/CarsApp/rest/Admin/image-upload",
                executeMultiPartRequest("http://37.46.32.119:8080/CarsApp/rest/Admin/image-upload",
                        file, file.getName(), "File Uploaded :: " + file) ;
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                File file = new File(advert.getImage_1()) ;
                Log.e("File for sending", file.toString());
                //Upload the file
                //      fileUpload.executeMultiPartRequest("http://localhost:8080/CarsApp/rest/Admin/image-upload",
                executeMultiPartRequest("http://37.46.32.119:8080/CarsApp/rest/Admin/image-upload",
                        file, file.getName(), "File Uploaded :: " + file) ;
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            try {
                File file2 = new File(advert.getImage_2()) ;
                Log.e("File for sending", file2.toString());
                //Upload the file
                //      fileUpload.executeMultiPartRequest("http://localhost:8080/CarsApp/rest/Admin/image-upload",
                executeMultiPartRequest("http://37.46.32.119:8080/CarsApp/rest/Admin/image-upload",
                        file2, file2.getName(), "File Uploaded :: " + file2) ;
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
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
    private void loginAdmin() {
        try{
            url = new URL("http://37.46.32.119:8080/CarsApp/rest/Admin/loginAdmin/user/1234");
            urlConnection = (HttpURLConnection) url
                    .openConnection();
            responseCode = urlConnection.getResponseCode();

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    urlConnection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                sessionId = inputLine;
            }
            Log.e("Session Id", "" + sessionId);
            String toSplit = sessionId;
            String result[] = toSplit.split("\"");
            String returnValue = result[result.length - 2];
            Log.e("returnValue: ", "" + returnValue);
            sessionId = returnValue;
            Log.e("Session ID", sessionId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}