package com.inti.student.countrylogoquiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.inti.student.countrylogoquiz.Adapter.GridViewAnswerAdapter;
import com.inti.student.countrylogoquiz.Adapter.GridViewSuggestAdapter;
import com.inti.student.countrylogoquiz.Common.Common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Game1 extends AppCompatActivity {

    public List<String> suggestSource = new ArrayList<>();

    public GridViewAnswerAdapter answerAdapter;
    public GridViewSuggestAdapter suggestAdapter;

    public Button btnSubmit;

    public GridView gridViewAnswer, gridViewSuggest;

    public ImageView imgViewQuestion;

    int[] image_list={
            R.drawable.america,
            R.drawable.canada,
            R.drawable.china,
            R.drawable.germany,
            R.drawable.japan,
            R.drawable.korea,
            R.drawable.malaysia,
            R.drawable.singapore,
            R.drawable.taiwan,
            R.drawable.thailand,
    };

    public char[] answer;

    String correct_answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1);

        //Init view
        initView();

        Button buttonBackMenu = (Button) findViewById(R.id.btnBack);
        buttonBackMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(Game1.this);
                builder.setMessage("Are you sure want to back to menu?");
                builder.setCancelable(true);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });


    }

    private void backToMain() {
        Intent intent = new Intent(Game1.this, MenuActivity.class);
        startActivity(intent);
    }

    private void initView(){

        gridViewAnswer = (GridView)findViewById(R.id.gridViewAnswer);
        gridViewSuggest = (GridView)findViewById(R.id.gridViewSuggest);

        imgViewQuestion = (ImageView)findViewById(R.id.imgLogo);

        //Add SetupList Here
        setupList();

        btnSubmit = (Button)findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result="";
                for(int i=0; i< Common.user_submit_answer.length; i++)
                    result+=String.valueOf(Common.user_submit_answer[i]);
                if(result.equals(correct_answer)){
                    Toast.makeText(getApplicationContext(), "You are correct! This is " + result, Toast.LENGTH_SHORT).show();

                    //Reset
                    Common.count = 0;
                    Common.user_submit_answer = new char[correct_answer.length()];

                    //set Adapter
                    GridViewAnswerAdapter answerAdapter = new GridViewAnswerAdapter(setupNullList(),getApplicationContext());
                    gridViewAnswer.setAdapter(answerAdapter);
                    answerAdapter.notifyDataSetChanged();

                    GridViewSuggestAdapter suggestAdapter = new GridViewSuggestAdapter(suggestSource, getApplicationContext(),Game1.this);
                    gridViewAnswer.setAdapter(suggestAdapter);
                    suggestAdapter.notifyDataSetChanged();

                    setupList();

                } else {
                    Toast.makeText(Game1.this, "Incorrect!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void setupList() {
        //Random logo
        Random random = new Random();
        int imageSelected = image_list[random.nextInt(image_list.length)];
        imgViewQuestion.setImageResource(imageSelected);

        correct_answer = getResources().getResourceName(imageSelected);
        correct_answer = correct_answer.substring(correct_answer.lastIndexOf("/")+1);

        answer = correct_answer.toCharArray();

        Common.user_submit_answer = new char[answer.length];

        //Add answer character to list
        suggestSource.clear();
        for(char item:answer){
            //add logo name to list
            suggestSource.add(String.valueOf(item));
        }

        //Random add some character to list
        for(int i = answer.length; i<answer.length*2; i++)
            suggestSource.add(Common.alphabet_character[random.nextInt(Common.alphabet_character.length)]);

        //sort random
        Collections.shuffle(suggestSource);

        //set for grid view
        answerAdapter = new GridViewAnswerAdapter(setupNullList(),this);
        suggestAdapter = new GridViewSuggestAdapter(suggestSource,this,this);

        answerAdapter.notifyDataSetChanged();
        suggestAdapter.notifyDataSetChanged();

        gridViewSuggest.setAdapter(suggestAdapter);
        gridViewAnswer.setAdapter(answerAdapter);
    }

    private char[] setupNullList() {
        char result[] = new char[answer.length];
        for(int i=0;i<answer.length;i++)
            result[i] = ' ';
        return result;
    }
}
