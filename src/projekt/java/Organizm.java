package projekt.java;

import java.awt.*;
import java.util.Random;

public abstract class Organizm {
    private String nazwa;
    private boolean czyZywy;
    private int inicjatywa;
    private int wiek;
    private int sila;
    protected Polozenie polozenie;
    protected Swiat swiat;
    public Color kolor;

    public abstract void akcja();
    public abstract void kolizja(Organizm atakujacy);
    public abstract boolean probaRozmnozenia();
    public abstract Organizm rozmnazanie(Polozenie polozenie);

    public Organizm() {}

    public Organizm(Swiat swiat, Polozenie polozenie){
        this.swiat = swiat;
        this.polozenie = polozenie;
        this.czyZywy = true;
        this.inicjatywa = 0;
        this.wiek = 0;
    }

    public Organizm(Swiat swiat, int x, int y, int inicjatywa, int wiek, int sila){
        this.swiat = swiat;
        this.polozenie = new Polozenie(x, y);
        this.inicjatywa = inicjatywa;
        this.wiek = wiek;
        this.sila = sila;
        this.czyZywy = true;
    }

    public boolean getCzyZywy(){
        return czyZywy;
    }
    public void setCzyZywy(boolean czyZywy){
        this.czyZywy = czyZywy;
    }
    public int getInicjatywa() {
        return inicjatywa;
    }
    public void setInicjatywa(int inicjatywa){
        this.inicjatywa = inicjatywa;
    }
    public int getWiek(){
        return wiek;
    }
    public void setWiek(int wiek){
        this.wiek = wiek;
    }
    public int getSila(){
        return sila;
    }
    public void setSila(int sila){
        this.sila = sila;
    }
    public Polozenie getPolozenie(){
        return this.polozenie;
    }
    public void setPolozenie(Polozenie polozenie){
        this.polozenie = polozenie;
    }
    public int getX(){
        return this.polozenie.x;
    }
    public int getY(){
        return this.polozenie.y;
    }
    public String getNazwa(){
        return nazwa;
    }
    public void setNazwa(String nazwa){
        this.nazwa = nazwa;
    }
    public Color getKolor(){
        return kolor;
    }
    public void setKolor(Color kolor){
        this.kolor = kolor;
    }
}
