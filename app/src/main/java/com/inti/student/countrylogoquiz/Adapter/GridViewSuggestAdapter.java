package com.inti.student.countrylogoquiz.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

import com.inti.student.countrylogoquiz.Common.Common;
import com.inti.student.countrylogoquiz.Game1;

import java.util.List;

/**
 * Created by Asus on 11/30/2018.
 */

public class GridViewSuggestAdapter extends BaseAdapter {

    private List<String> suggestSource;
    private Context context;
    private Game1 Game1;

    public GridViewSuggestAdapter(List<String> suggestSource, Context context, Game1 game1) {
        this.suggestSource = suggestSource;
        this.context = context;
        Game1 = game1;
    }

    @Override
    public int getCount() {
        return suggestSource.size();
    }

    @Override
    public Object getItem(int position) {
        return suggestSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Button button;
        if(convertView == null){
            if (suggestSource.get(position).equals("null")){

                button = new Button(context);
                button.setLayoutParams(new GridView.LayoutParams(85,85));
                button.setPadding(8,8,8,8);
                button.setBackgroundColor(Color.DKGRAY);


            }
            else {
                button = new Button(context);
                button.setLayoutParams(new GridView.LayoutParams(85,85));
                button.setPadding(8,8,8,8);
                button.setBackgroundColor(Color.DKGRAY);
                button.setTextColor(Color.YELLOW);
                button.setText(suggestSource.get(position));
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //if correct answer contain character  user selected
                        if(String.valueOf(Game1.answer).contains(suggestSource.get(position))){
                            char compare = suggestSource.get(position).charAt(0); //Get char

                            for(int i = 0; i< Game1.answer.length; i++){
                                if(compare == Game1.answer[i]){
                                    Common.user_submit_answer[i] = compare;
                                }
                            }

                            //update UI
                            GridViewAnswerAdapter answerAdapter = new GridViewAnswerAdapter(Common.user_submit_answer, context);
                            Game1.gridViewAnswer.setAdapter(answerAdapter);
                            answerAdapter.notifyDataSetChanged();

                            //remove from suggest source
                            Game1.suggestSource.set(position,"null");
                            Game1.suggestAdapter = new GridViewSuggestAdapter(Game1.suggestSource,context, Game1);
                            Game1.gridViewSuggest.setAdapter(Game1.suggestAdapter);
                            Game1.suggestAdapter.notifyDataSetChanged();
                        }
                        else //else
                        {
                            //remove from suggest source
                            Game1.suggestSource.set(position,"null");
                            Game1.suggestAdapter = new GridViewSuggestAdapter(Game1.suggestSource,context, Game1);
                            Game1.gridViewSuggest.setAdapter(Game1.suggestAdapter);
                            Game1.suggestAdapter.notifyDataSetChanged();
                        }
                    }
                });
            }
        }
        else
            button = (Button) convertView;
        return button;

    }
}
