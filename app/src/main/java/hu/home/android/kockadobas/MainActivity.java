package hu.home.android.kockadobas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView kocka1Img;
    private ImageView kocka2Img;
    private Button egyKockaBtn;
    private Button ketKockaBtn;
    private Button dobasBtn;
    private Button resetBtn;
    private LinearLayout resultLinear;

    private int[] kepek;
    private Random rnd;
    private int elsoKockaErtek;
    private int masodikKockaErtek;
    private boolean isEgyKockasDobas;

    private AnimationDrawable kocka1Animacio;
    private AnimationDrawable kocka2Animacio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        addListeners();
    }

    private void addListeners() {
        egyKockaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kocka2Img.setVisibility(View.GONE);
                isEgyKockasDobas = true;
            }
        });
        ketKockaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kocka2Img.setVisibility(View.VISIBLE);
                isEgyKockasDobas = false;
            }
        });
        dobasBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dobas();
            }
        });
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this).setTitle("Reset")
                        .setMessage("Biztos, hogy törölni szeretné az eddigi dobásokat?")
                        .setPositiveButton("Igen", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                resultLinear.removeAllViews();
                            }
                        }).setNegativeButton("Nem", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).setCancelable(false).show();
            }
        });
    }

    private void dobas() {
        rnd = new Random();
        elsoKockaErtek = rnd.nextInt(6) + 1;
        masodikKockaErtek = rnd.nextInt(6) + 1;
        TextView tempTV = new TextView(MainActivity.this);
        if (isEgyKockasDobas){
            tempTV.setText(String.format("%d", elsoKockaErtek));
            Toast.makeText(this, String.format("Aktuális dobás értéke: %d", elsoKockaErtek), Toast.LENGTH_SHORT).show();
        }else {
            tempTV.setText(String.format("%d (%d+%d)", elsoKockaErtek + masodikKockaErtek, elsoKockaErtek, masodikKockaErtek));
            Toast.makeText(this, String.format("Aktuális dobás értéke: %d (%d+%d)", elsoKockaErtek + masodikKockaErtek, elsoKockaErtek, masodikKockaErtek), Toast.LENGTH_SHORT).show();
            kocka2Img.setImageResource(kepek[masodikKockaErtek - 1]);
        }
        kocka1Img.setBackgroundResource(R.drawable.kocka_dobas);
        kocka1Animacio = (AnimationDrawable)kocka1Img.getBackground();
        kocka1Animacio.start();
        kocka1Img.setImageResource(kepek[elsoKockaErtek - 1]);

        kocka2Img.setBackgroundResource(R.drawable.kocka_dobas);
        kocka1Animacio = (AnimationDrawable)kocka1Img.getBackground();
        kocka1Animacio.start();

        resultLinear.addView(tempTV);

    }

    private void init(){
        kocka1Img = findViewById(R.id.kocka1Img);
        kocka2Img = findViewById(R.id.kocka2Img);
        egyKockaBtn = findViewById(R.id.egyKockaBtn);
        ketKockaBtn = findViewById(R.id.ketKockaBtn);
        dobasBtn = findViewById(R.id.dobasBtn);
        resetBtn = findViewById(R.id.resetBtn);
        resultLinear = findViewById(R.id.resultLinear);

        kepek = new int[]{R.drawable.kocka1, R.drawable.kocka2, R.drawable.kocka3, R.drawable.kocka4, R.drawable.kocka5, R.drawable.kocka6};
    }
}