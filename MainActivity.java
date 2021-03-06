package example.cw.com.babycare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String Temperatuur = "temperatuur";
        String heartRate = "heartRate";
        String location="glocation";
     //  new GetAsync().execute(Temperatuur, heartRate);



       Button signupBtn = (Button) findViewById(R.id.SignUp);
        Button signinBtn = (Button) findViewById(R.id.SignIn);


        class GetAsync extends AsyncTask<String, String, JSONObject> {
            PassDetails passDetails = new PassDetails();

            private ProgressDialog pDialog;
            private static final String LOGIN_URL = "http://192.168.1.12";
            private static final String TAG_SUCCESS = "success";
            private static final String TAG_MESSAGE = "message";

            @Override
            protected void onPreExecute() {
                pDialog = new ProgressDialog(MainActivity.this);
                pDialog.setMessage("Attempting login..");
                pDialog.setIndeterminate(false);
                pDialog.setCancelable(true);
                pDialog.show();
            }

            @Override
            protected  JSONObject doInBackground(String... args) {

                try {

                    HashMap<String, String> params = new HashMap<>();
                    params.put("temperatuur", args[0]);
                    params.put("heartRate", args[1]);
                    params.put("location", args[2]);

                    Log.d("request", "starting");

                    JSONObject json = PassDetails.makeHttpRequest(
                            LOGIN_URL, "GET", params);

                    if (json != null) {
                        Log.d("JSON Data output", json.toString());


                        return json;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                return null;
            }

            protected void onPostExecute(JSONObject json) {

                int success = 0;
                String message = "";

                if (pDialog != null && pDialog.isShowing()) {
                    pDialog.dismiss();
                }

                if (json != null) {
                    Toast.makeText(MainActivity.this, json.toString(),
                            Toast.LENGTH_LONG).show();

                    try {
                        success = json.getInt(TAG_SUCCESS);
                        message = json.getString(TAG_MESSAGE);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                if (success == 1) {
                    Log.d("Success!", message);
                }else{
                    Log.d("Failure", message);
                }
            }
        }
    }
    public void goToSignUp (View view){
        Intent intent = new Intent (this, signUpActivity.class);
        startActivity(intent);
    }
    public void goToDetail (View view){
        Intent intent = new Intent (this, DetailActivity.class);
        startActivity(intent);
    }


}
