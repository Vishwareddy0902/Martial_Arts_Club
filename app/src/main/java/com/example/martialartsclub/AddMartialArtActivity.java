package com.example.martialartsclub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.martialartsclub.Model.DataBaseHandler;
import com.example.martialartsclub.Model.MartialArt;

public class AddMartialArtActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtName, edtPrice, edtColor;
    Button btnAdd , btnAddGoBack;
    private DataBaseHandler mDataBaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_martial_art);

        edtName = (EditText) findViewById(R.id.edtName);
        edtPrice = (EditText) findViewById(R.id.edtPrice);
        edtColor = (EditText) findViewById(R.id.edtColor);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAddGoBack = (Button) findViewById(R.id.btnAddGoBack);

        mDataBaseHandler = new DataBaseHandler(AddMartialArtActivity.this);

        btnAdd.setOnClickListener(AddMartialArtActivity.this);
        btnAddGoBack.setOnClickListener(AddMartialArtActivity.this);


    }

    private void addMartialArtObjectToDatabase(){

        String nameValue = edtName.getText().toString();
        String priceValue = edtPrice.getText().toString();
        String colorValue = edtColor.getText().toString();

        try {

            double priceDoubleValue = Double.parseDouble(priceValue);
            MartialArt martialArt = new MartialArt(0,nameValue,priceDoubleValue,colorValue);
            mDataBaseHandler.addMartialArt(martialArt);
            Toast.makeText(AddMartialArtActivity.this,
                    martialArt + "\nMartial Art Object is added to DataBase",
                    Toast.LENGTH_LONG).show();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAdd:
                addMartialArtObjectToDatabase();
                break;
            case R.id.btnAddGoBack:
                finish();
                break;
        }
    }

}