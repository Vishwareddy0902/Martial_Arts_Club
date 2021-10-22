package com.example.martialartsclub;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;

import com.example.martialartsclub.Model.MartialArt;

public class MartialArtButton extends AppCompatButton {

    private MartialArt martialArt;


    public MartialArtButton( Context context, MartialArt martialArt) {
        super(context);
        this.martialArt = martialArt;
    }

    public String getMartialArtColor(){
        return martialArt.getMartialArtColor();
    }

    public double getMartialArtPrice(){
        return  martialArt.getMartialArtPrice();
    }

}
