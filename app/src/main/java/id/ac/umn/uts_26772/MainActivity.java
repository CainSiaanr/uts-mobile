package id.ac.umn.uts_26772;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    private Button btnProfil, btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.logo);
        imageView.setClipToOutline(true);
        btnProfil = (Button) findViewById(R.id.profil);
        btnLogin = (Button) findViewById(R.id.login);
    }

    public void kirimLogin(View view){
        Intent loginIntent = new Intent(MainActivity.this, Login_Activity.class);
        startActivity(loginIntent);
    }

    public void kirimProfil(View view){
        Intent profilIntent = new Intent(MainActivity.this, ProfileActivity.class);
        startActivity(profilIntent);
    }
}