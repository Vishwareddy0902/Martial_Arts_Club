package com.example.martialartsclub;

import androidx.appcompat.app.AppCompatActivity;

import android.accessibilityservice.GestureDescription;
import android.graphics.Point;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.martialartsclub.Model.DataBaseHandler;
import com.example.martialartsclub.Model.MartialArt;

import java.util.ArrayList;

public class UpdateMartialArtActivity extends AppCompatActivity implements View.OnClickListener {

    DataBaseHandler mDataBaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_martial_art);

        mDataBaseHandler = new DataBaseHandler(UpdateMartialArtActivity.this);

        modifyUserInterface();

    }

    private void modifyUserInterface() {

        ArrayList<MartialArt> allMartialArts = mDataBaseHandler.returnAllMartialArts();

        if (allMartialArts.size()>0){

            ScrollView scrollView = new ScrollView(UpdateMartialArtActivity.this);
            GridLayout gridLayout = new GridLayout(UpdateMartialArtActivity.this);
            gridLayout.setRowCount(allMartialArts.size());
            gridLayout.setColumnCount(5);

            TextView[] idTextViews = new TextView[allMartialArts.size()];
            EditText[][] edtNamePricesAndColor = new EditText[allMartialArts.size()][3];
            Button[] button =  new Button[allMartialArts.size()];

            Point screenSize = new Point();
            getWindowManager().getDefaultDisplay().getSize(screenSize);
            int screenWidth = screenSize.x;

            int index = 0 ;

            for(MartialArt martialArt : allMartialArts){

                idTextViews[index] = new TextView(UpdateMartialArtActivity.this);
                idTextViews[index].setGravity(Gravity.CENTER);
                idTextViews[index].setText(martialArt.getMartialArtID()+"");

                edtNamePricesAndColor[index][0] = new EditText(UpdateMartialArtActivity.this);
                edtNamePricesAndColor[index][1] = new EditText(UpdateMartialArtActivity.this);
                edtNamePricesAndColor[index][2] = new EditText(UpdateMartialArtActivity.this);

                edtNamePricesAndColor[index][0].setText(martialArt.getMartialArtName());
                edtNamePricesAndColor[index][1].setText(martialArt.getMartialArtPrice()+"");
                edtNamePricesAndColor[index][1].setInputType(InputType.TYPE_CLASS_NUMBER);
                edtNamePricesAndColor[index][2].setText(martialArt.getMartialArtColor());

                edtNamePricesAndColor[index][0].setId(martialArt.getMartialArtID()+10000);
                edtNamePricesAndColor[index][1].setId(martialArt.getMartialArtID()+20000);
                edtNamePricesAndColor[index][2].setId(martialArt.getMartialArtID()+30000);

                button[index] = new Button(UpdateMartialArtActivity.this);
                button[index].setText("Modify");
                button[index].setId(martialArt.getMartialArtID());
                button[index].setOnClickListener(UpdateMartialArtActivity.this);

                gridLayout.addView(idTextViews[index], (int) (screenWidth*0.1),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(edtNamePricesAndColor[index][0], (int) (screenWidth*0.2),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(edtNamePricesAndColor[index][1], (int) (screenWidth*0.2),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(edtNamePricesAndColor[index][2], (int) (screenWidth*0.2),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(button[index], (int) (screenWidth*0.3),
                        ViewGroup.LayoutParams.WRAP_CONTENT);

                index++;
            }

            scrollView.addView(gridLayout);
            setContentView(scrollView);

        }

    }

    @Override
    public void onClick(View view) {

        int martialArtId = view.getId();

        EditText edtMartialArtName = (EditText) findViewById(martialArtId+10000);
        EditText edtMartialArtPrice = (EditText) findViewById(martialArtId+20000);
        EditText edtMartialArtColor = (EditText) findViewById(martialArtId+30000);

        String martialArtNameValue = edtMartialArtName.getText().toString();
        String martialArtPriceValue = edtMartialArtPrice.getText().toString();
        String martialArtColorValue = edtMartialArtColor.getText().toString();

        try {
            double martialArtPriceDoubleValue = Double.parseDouble(martialArtPriceValue);

            mDataBaseHandler.updateMartialArt(martialArtId,
                    martialArtNameValue,
                    martialArtPriceDoubleValue,
                    martialArtColorValue);

            Toast.makeText(UpdateMartialArtActivity.this, "Martial Art Updated", Toast.LENGTH_SHORT).show();

        }catch (NumberFormatException e){

            Toast.makeText(UpdateMartialArtActivity.this, "Enter a number in Price", Toast.LENGTH_SHORT).show();

        }






    }
}