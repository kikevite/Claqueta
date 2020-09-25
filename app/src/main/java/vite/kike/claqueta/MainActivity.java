package vite.kike.claqueta;

import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final String sony = "Sony G8341";
        final String bq = "bq Edison 3";
        //final String altres = "other";
        String dispositiu = Build.MANUFACTURER + " " + Build.MODEL;

        if (dispositiu.equals(sony)) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main_sony_g8341);
        }
        else if (dispositiu.equals(bq)) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main_bq_edison_3);
        }
        else {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main_sony_g8341);
            Toast.makeText(this, "desconegut", Toast.LENGTH_SHORT).show();
        }

        final Button btnChack, btnReset, btnSuma;
        final TextView tvScene, tvTake;
        final EditText etScene, etTake;

        btnChack = findViewById(R.id.btnChack);
        btnReset = findViewById(R.id.btnReset);
        btnSuma = findViewById(R.id.btnSuma);

        tvScene = findViewById(R.id.tvScene);
        tvTake = findViewById(R.id.tvTake);
        etScene = findViewById(R.id.editScene);
        etTake = findViewById(R.id.editTake);

        final View root = btnChack.getRootView();

        final int negre = getResources().getColor(android.R.color.black);
        final int blanc = getResources().getColor(android.R.color.white);

        btnChack.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    MediaPlayer mPlayer = MediaPlayer.create(MainActivity.this, R.raw.chack);
                    mPlayer.start();

                    root.setBackgroundColor(negre);
                    tvScene.setTextColor(blanc);
                    tvTake.setTextColor(blanc);
                    etScene.setTextColor(blanc);
                    etTake.setTextColor(blanc);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    root.setBackgroundColor(blanc);
                    tvScene.setTextColor(negre);
                    tvTake.setTextColor(negre);
                    etScene.setTextColor(negre);
                    etTake.setTextColor(negre);
                }
                return true;
            }
        });

        btnReset.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                etTake.setText("1");


                return true;
            }
        });

        btnSuma.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int take = Integer.parseInt(etTake.getText().toString());
                take++;
                etTake.setText(String.format("%d",take));
                return true;
            }
        });

    }

}
