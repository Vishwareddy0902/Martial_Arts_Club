package com.example.martialartsclub.Model;

public class MartialArt {

    private int martialArtID;
    private String martialArtName;
    private double martialArtPrice;
    private String martialArtColor;

    public MartialArt(int martialArtID, String martialArtName, double martialArtPrice, String martialArtColor) {
        setMartialArtID(martialArtID);
        setMartialArtName(martialArtName);
        setMartialArtPrice(martialArtPrice);
        setMartialArtColor(martialArtColor);
    }

    public int getMartialArtID() {
        return martialArtID;
    }

    public void setMartialArtID(int martialArtID) {
        this.martialArtID = martialArtID;
    }

    public String getMartialArtName() {
        return martialArtName;
    }

    public void setMartialArtName(String martialArtName) {
        this.martialArtName = martialArtName;
    }

    public double getMartialArtPrice() {
        return martialArtPrice;
    }

    public void setMartialArtPrice(double martialArtPrice) {
        this.martialArtPrice = martialArtPrice;
    }

    public String getMartialArtColor() {
        return martialArtColor;
    }

    public void setMartialArtColor(String martialArtColor) {
        this.martialArtColor = martialArtColor;
    }

    @Override
    public String toString() {
        return getMartialArtID() + " " +getMartialArtName() + " " +
                getMartialArtPrice() + " " + getMartialArtColor();
    }
}
