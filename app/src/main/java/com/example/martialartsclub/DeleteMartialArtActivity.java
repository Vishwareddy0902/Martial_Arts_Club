package com.example.martialartsclub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.martialartsclub.Model.DataBaseHandler;
import com.example.martialartsclub.Model.MartialArt;

import java.util.ArrayList;

public class DeleteMartialArtActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    private DataBaseHandler mDataBaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_martial_art);

        mDataBaseHandler = new DataBaseHandler(DeleteMartialArtActivity.this);
        updateTheUserInterface();

    }

    private void updateTheUserInterface() {

        ArrayList<MartialArt> allMartialArt = mDataBaseHandler.returnAllMartialArts();

        RelativeLayout relativeLayout = new RelativeLayout(DeleteMartialArtActivity.this);
        ScrollView scrollView = new ScrollView(DeleteMartialArtActivity.this);
        RadioGroup radioGroup = new RadioGroup(DeleteMartialArtActivity.this);

        for (MartialArt martialArt : allMartialArt){

            RadioButton currentRadioButton = new RadioButton(DeleteMartialArtActivity.this);
            currentRadioButton.setId(martialArt.getMartialArtID());
            currentRadioButton.setText(martialArt.toString());
            radioGroup.addView(currentRadioButton);

        }

        radioGroup.setOnCheckedChangeListener(DeleteMartialArtActivity.this);
        scrollView.addView(radioGroup);

        RelativeLayout.LayoutParams scrollParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT , RelativeLayout.LayoutParams.MATCH_PARENT);
        relativeLayout.addView(scrollView , scrollParams);

        Button btnGoBack = new Button(DeleteMartialArtActivity.this);
        btnGoBack.setText("GO BACK");
        btnGoBack.setOnClickListener(DeleteMartialArtActivity.this);
        RelativeLayout.LayoutParams buttonParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT , RelativeLayout.LayoutParams.WRAP_CONTENT);
        buttonParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        buttonParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        buttonParams.setMargins(0, 0 , 0 , 70);

        relativeLayout.addView(btnGoBack,buttonParams);

        setContentView(relativeLayout);

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        mDataBaseHandler.deleteMartialArtByID(checkedId);
        Toast.makeText(DeleteMartialArtActivity.this , "The martial art is deleted",
                Toast.LENGTH_SHORT).show();
        updateTheUserInterface();
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}