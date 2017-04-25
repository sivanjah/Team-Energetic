package example.cw.com.babycare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class signUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Button signBtn = (Button) findViewById(R.id.signBtn);
        Button cancelbtn = (Button) findViewById(R.id.cancelbtn);
    }

    public void gotoLogin (View view){
        Intent intent = new Intent (this, MainActivity.class);
        startActivity(intent);
    }

}
