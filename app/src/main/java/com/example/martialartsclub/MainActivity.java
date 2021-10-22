package com.example.martialartsclub;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.martialartsclub.Model.DataBaseHandler;
import com.example.martialartsclub.Model.MartialArt;
import com.example.martialartsclub.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DataBaseHandler mDataBaseHandler;
    private ScrollView mScrollView;
    private int martialArtButtonSize;
    private double totalMartialArtPrice;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDataBaseHandler = new DataBaseHandler(MainActivity.this);
        totalMartialArtPrice = 0.0;
        mScrollView = new ScrollView(MainActivity.this);

        Point screenSize = new Point();
        getWindowManager().getDefaultDisplay().getSize(screenSize);
        martialArtButtonSize = screenSize.x/2;

        modifyUserInterface();




    }

    private void modifyUserInterface() {

        ArrayList<MartialArt> allMartialArt = mDataBaseHandler.returnAllMartialArts();
        mScrollView.removeAllViewsInLayout();
        if (allMartialArt.size()>0){

            GridLayout gridLayout = new GridLayout(MainActivity.this);
            gridLayout.setRowCount((allMartialArt.size()+1)/2);
            gridLayout.setColumnCount(2);

            MartialArtButton[] martialArtButton = new MartialArtButton[allMartialArt.size()];
            int index=0;

            for (MartialArt martialArt : allMartialArt){

                martialArtButton[index] = new MartialArtButton(MainActivity.this, martialArt);
                martialArtButton[index].setText(martialArt.getMartialArtID()+ "\n"+
                        martialArt.getMartialArtName() + " \n" +
                        martialArt.getMartialArtPrice());

                switch (martialArt.getMartialArtColor()){
                    case "Red":
                        martialArtButton[index].setBackgroundColor(Color.RED);
                        break;
                    case "Blue":
                        martialArtButton[index].setBackgroundColor(Color.BLUE);
                        break;
                    case "Gray":
                        martialArtButton[index].setBackgroundColor(Color.GRAY);
                        break;
                    case "Green":
                        martialArtButton[index].setBackgroundColor(Color.GREEN);
                        break;
                    case "Yellow":
                        martialArtButton[index].setBackgroundColor(Color.YELLOW);
                        break;
                    default:
                        martialArtButton[index].setBackgroundColor(Color.GRAY);
                }
                martialArtButton[index].setOnClickListener(MainActivity.this);
                gridLayout.addView(martialArtButton[index],martialArtButtonSize, ViewGroup.LayoutParams.WRAP_CONTENT);
                index++;
            }

            mScrollView.addView(gridLayout);
            setContentView(mScrollView);


        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        modifyUserInterface();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.add_martial_art:
                Intent addMartialArtIntent = new Intent(MainActivity.this, AddMartialArtActivity.class);
                startActivity(addMartialArtIntent);
                return true;
            case R.id.delete_martial_art:
                Intent deleteMartialArtIntent = new Intent(MainActivity.this, DeleteMartialArtActivity.class);
                startActivity(deleteMartialArtIntent);
                return true;
            case R.id.update_martial_art:
                Intent updateMartialArtIntent = new Intent(MainActivity.this, UpdateMartialArtActivity.class);
                startActivity(updateMartialArtIntent);
                return true;
            case R.id.reset_martial_art_prices:
                totalMartialArtPrice = 0.0;
                Toast.makeText(MainActivity.this,"" +
                        NumberFormat.getCurrencyInstance().format(totalMartialArtPrice),Toast.LENGTH_SHORT).show();
                return true;
        }
            return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

        MartialArtButton martialArtButton = (MartialArtButton) view;
        totalMartialArtPrice = totalMartialArtPrice + martialArtButton.getMartialArtPrice();
        String martialArtPriceFormatted = NumberFormat.getCurrencyInstance().format(totalMartialArtPrice);
        Toast.makeText(MainActivity.this, martialArtPriceFormatted + "" , Toast.LENGTH_SHORT).show();

    }
}