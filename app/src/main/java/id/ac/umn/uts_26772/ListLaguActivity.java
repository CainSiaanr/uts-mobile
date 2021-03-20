package id.ac.umn.uts_26772;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.LinkedList;

public class ListLaguActivity extends AppCompatActivity {
    RecyclerView rvDaftarLagu;
    DaftarLaguAdapter mAdapter;
    private Toolbar toolbar;
    LinkedList<SumberLagu> daftarLagu = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_lagu);

        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        isiDaftarLagu();
        rvDaftarLagu = (RecyclerView) findViewById(R.id.recyclerView);
        mAdapter = new DaftarLaguAdapter(this, daftarLagu);
        rvDaftarLagu.setAdapter(mAdapter);
        rvDaftarLagu.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        if(intent.getStringExtra("IniLogin").equals("IniLogin")){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("Selamat Datang!")
                    .setMessage("Ivan Tenusa\n00000026772");

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.dismiss();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.lagu_menu, menu);
        return true;
    }

    public void isiDaftarLagu(){
        daftarLagu.add(new SumberLagu("Fur Elise","Ludwig Van Beethoven","android.resource://"+getPackageName() + "/"+ R.raw.fur_elise_beethoven));
        daftarLagu.add(new SumberLagu("In The Hall Of The Mountain King","Edward Grieg","android.resource://" +getPackageName() + "/"+ R.raw.in_the_hall_of_the_mountain_king_grieg));
        daftarLagu.add(new SumberLagu("Tocatta In D Minor","Johann Sebastian Bach","android.resource://" +getPackageName() + "/"+ R.raw.tocatta_in_d_minor_bach));
        daftarLagu.add(new SumberLagu("Morning Mood","Grieg","android.resource://" +getPackageName() + "/"+ R.raw.morning_mood_grieg));
        daftarLagu.add(new SumberLagu("Moonlight Sonata","Ludwig Van Beethoven","android.resource://" +getPackageName() + "/"+ R.raw.moonlight_sonata_beethoven));
    }

    public void logout(MenuItem item){
        Intent moveIntent = new Intent(ListLaguActivity.this, Login_Activity.class);
        startActivity(moveIntent);
    }

    public void gotoprofil(MenuItem item) {
        Intent moveIntent = new Intent(ListLaguActivity.this, ProfileActivity.class);
        startActivity(moveIntent);
    }
}