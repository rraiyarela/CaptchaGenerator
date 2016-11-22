package rr.captchagenerator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CaptchaControl ccRegistration;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ccRegistration = (CaptchaControl)findViewById(R.id.ccRegistration);
        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ccRegistration.isCaptchaValid()){
                    Toast.makeText(MainActivity.this, "Valid Captach", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(MainActivity.this, "Invalid Captach", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
