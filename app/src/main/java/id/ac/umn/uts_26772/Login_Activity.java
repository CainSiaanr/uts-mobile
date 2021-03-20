package id.ac.umn.uts_26772;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Login_Activity extends AppCompatActivity {
    TextView error;
    EditText username, password;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        imageView = (ImageView) findViewById(R.id.logo1);
        imageView.setClipToOutline(true);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        error = (TextView) findViewById(R.id.notif);
    }

    public void cekLogin(View view){
        if(username.getText().toString().equals("uasmobile") && password.getText().toString().equals("uasmobilegenap")){
            Intent loginIntent = new Intent(Login_Activity.this, ListLaguActivity.class);
            String isi = "IniLogin";
            loginIntent.putExtra("IniLogin", isi);
            startActivity(loginIntent);
        }
        else{
            error.setVisibility(View.VISIBLE);
        }
    }

    public void backtomain(View view){
        finish();
    }
}