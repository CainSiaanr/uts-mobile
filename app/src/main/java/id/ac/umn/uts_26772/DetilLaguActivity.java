package id.ac.umn.uts_26772;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

public class DetilLaguActivity extends AppCompatActivity {
    private MediaPlayer laguPlayer;
    private TextView judulLagu, komposer, awal, now, akhir;
    private ImageButton playpause, prev, next;
    private SeekBar durasi;
    private int urutanlagu;
    ArrayList<SumberLagu> daftarLaguu;
    //SumberLagu sv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detil_lagu);

        judulLagu = (TextView) findViewById(R.id.songname);
        komposer = (TextView) findViewById(R.id.composer);

        playpause = (ImageButton) findViewById(R.id.pauseplay);
        prev = (ImageButton) findViewById(R.id.prev);
        next = (ImageButton) findViewById(R.id.next);
        durasi = (SeekBar) findViewById(R.id.durasi);

        awal = (TextView) findViewById(R.id.start);
        now = (TextView) findViewById(R.id.curr);
        akhir = (TextView) findViewById(R.id.end);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        //SumberLagu sv = (SumberLagu) bundle.getSerializable("DetilLagu");
        urutanlagu = (int) bundle.getSerializable("UrutanLagu");
        daftarLaguu = (ArrayList<SumberLagu>) bundle.getSerializable("ListLagu");
        playSong(urutanlagu);

        playpause.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(laguPlayer.isPlaying()){
                    laguPlayer.pause();
                    playpause.setBackgroundResource(R.drawable.ic_baseline_play_circle_filled_24);
                }else if(!laguPlayer.isPlaying()){
                    laguPlayer.start();
                    playpause.setBackgroundResource(R.drawable.ic_baseline_pause_circle_filled_24);
                    updateSeekBar();
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(urutanlagu >= (daftarLaguu.size() - 1)){
                    urutanlagu = 0;
                }else{
                    urutanlagu++;
                }
                laguPlayer.release();
                playSong(urutanlagu);
            }
        });

        prev.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(urutanlagu <= 0){
                    urutanlagu = daftarLaguu.size() - 1;
                }else{
                    urutanlagu--;
                }
                laguPlayer.release();
                playSong(urutanlagu);
            }
        });

        durasi.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(laguPlayer != null && fromUser){
                    laguPlayer.seekTo(progress);
                }
            }
        });

        /*btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });*/
    }

    private void playSong(int urutan){
        judulLagu.setText(daftarLaguu.get(urutan).getJudul());
        komposer.setText(daftarLaguu.get(urutan).getKeterangan());
        laguPlayer = MediaPlayer.create(this, Uri.parse(daftarLaguu.get(urutan).getLaguURI()));
        laguPlayer.seekTo(10);
        laguPlayer.start();
        durasi.setMax(laguPlayer.getDuration());
        akhir.setText(milliSecondsToTimer(laguPlayer.getDuration()));
        updateSeekBar();
    }

    private void updateSeekBar() {
        durasi.setProgress(laguPlayer.getCurrentPosition());
        now.setText(milliSecondsToTimer(laguPlayer.getCurrentPosition()));
        durasi.postDelayed(runnable, 50);
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            updateSeekBar();
        }
    };

    private String milliSecondsToTimer(long milliseconds) {
        String finalTimerString = "";
        String secondsString = "";

        // Convert total duration into time
        int hours = (int) (milliseconds / (1000 * 60 * 60));
        int minutes = (int) (milliseconds % (1000 * 60 * 60)) / (1000 * 60);
        int seconds = (int) ((milliseconds % (1000 * 60 * 60)) % (1000 * 60) / 1000);
        // Add hours if there
        if (hours > 0) {
            finalTimerString = hours + ":";
        }

        // Prepending 0 to seconds if it is one digit
        if (seconds < 10) {
            secondsString = "0" + seconds;
        } else {
            secondsString = "" + seconds;
        }

        finalTimerString = finalTimerString + minutes + ":" + secondsString;

        // return timer string
        return finalTimerString;
    }

    @Override
    public void onBackPressed() {
        durasi.removeCallbacks(runnable);
        laguPlayer.release();
        Intent backIntent = new Intent(DetilLaguActivity.this, ListLaguActivity.class);
        String isi = "IniBukanLogin";
        backIntent.putExtra("IniLogin", isi);
        startActivity(backIntent);
        finish();
    }

    public void backtomain(View view){
        durasi.removeCallbacks(runnable);
        laguPlayer.release();
        Intent backIntent = new Intent(DetilLaguActivity.this, ListLaguActivity.class);
        String isi = "IniBukanLogin";
        backIntent.putExtra("IniLogin", isi);
        startActivity(backIntent);
        finish();
    }
}