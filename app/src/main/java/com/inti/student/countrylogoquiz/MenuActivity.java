package com.inti.student.countrylogoquiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button btnLogoQuiz = (Button) findViewById(R.id.btnLogoQuiz);
        btnLogoQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game1();
            }
        });

        Button btnMemoryGame = (Button) findViewById(R.id.btnMemoryGame);
        btnMemoryGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game2();
            }
        });

        Button buttonExit = (Button) findViewById(R.id.btnExit);
        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MenuActivity.this);
                builder.setMessage("Are you sure want to EXIT?");
                builder.setCancelable(true);
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    private void game1() {
        Intent intent = new Intent(MenuActivity.this, Game1.class);
        startActivity(intent);
    }

    private void game2() {
        Intent intent = new Intent(MenuActivity.this, Game2.class);
        startActivity(intent);
    }
}
