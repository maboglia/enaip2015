package it.bogliaccino.myappbogliaccino;

import android.app.Activity;
import android.app.Activity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;

import android.os.Bundle;
import android.os.Handler;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import java.util.concurrent.TimeUnit;


public class Suona extends Activity {

    Button back, fwd, play,pause;
    ImageView copertina;
    MediaPlayer mediaPlayer;
    double startTime = 0;
    double finalTime = 0;
    int fwdTime = 5000;
    int bwdTime = 5000;
    TextView titoloBrano, durataBrano, esecuzioneBrano;
    SeekBar seekbar;
    Handler mioHandler = new Handler();

    public static int oneTime = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suona);

        back = (Button) findViewById(R.id.btnBack);
        fwd = (Button) findViewById(R.id.btnFWD);
        play = (Button) findViewById(R.id.btnPlay);
        pause = (Button) findViewById(R.id.btnPause);

        copertina = (ImageView) findViewById(R.id.copertinaSuono);

        titoloBrano = (TextView) findViewById(R.id.tvTitoloBrano);
        durataBrano = (TextView) findViewById(R.id.tvDurataBrano);
        esecuzioneBrano = (TextView) findViewById(R.id.tvEsecuzioneBrano);

        seekbar = (SeekBar) findViewById(R.id.seekBrano);
        seekbar.setClickable(false);
        seekbar.setEnabled(false);

        mediaPlayer = MediaPlayer.create(this,R.raw.champions);

        pause.setEnabled(false);




        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Sta suonando qualcosa", Toast.LENGTH_SHORT).show();

                titoloBrano.setText("We are the Champions! The Queen, 1977");

                //faccio partire la canzone
                mediaPlayer.start();

                //memorizzo la durata del brano
                finalTime = mediaPlayer.getDuration();
                //rilevo la posizione corrente
                startTime = mediaPlayer.getCurrentPosition();

                //setto il valore max della seekbar e impedisco il loop
                if (oneTime == 0) {
                    seekbar.setMax((int) finalTime);
                    oneTime = 1;
                }

                /*  */
                esecuzioneBrano.setText(String.format("esecuzione: %d min, %d sec",
                        TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                        TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) startTime))
                ));

                durataBrano.setText(String.format("durata: %d min, %d sec",
                        TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
                        TimeUnit.MILLISECONDS.toSeconds((long) finalTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) finalTime))
                ));

                seekbar.setProgress((int) startTime);
                mioHandler.postDelayed(AggiornaMusica, 100);

                pause.setEnabled(true);
                play.setEnabled(false);

            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Hai messo in pausa", Toast.LENGTH_SHORT).show();
                mediaPlayer.pause();
                pause.setEnabled(false);
                play.setEnabled(true);
            }
        });

        fwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = (int) startTime;

                if ((temp+fwdTime)<=finalTime){

                    startTime = startTime + fwdTime;
                    mediaPlayer.seekTo((int) startTime);



                } else

                    Toast.makeText(getApplicationContext(), "NUN SE PO FFA", Toast.LENGTH_SHORT).show();

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = (int) startTime;

                if ((temp+fwdTime)<=finalTime){

                    startTime = startTime - bwdTime;
                    mediaPlayer.seekTo((int) startTime);



                } else

                    Toast.makeText(getApplicationContext(), "NUN SE PO FFA", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private Runnable AggiornaMusica = new Runnable() {
        @Override
        public void run() {
            startTime = mediaPlayer.getCurrentPosition();
            esecuzioneBrano.setText(String.format("esecuzione: %d min, %d sec",
                    TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                    TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) startTime))
            ));
            seekbar.setProgress((int) startTime);
            mioHandler.postDelayed(this, 100);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_suona, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
