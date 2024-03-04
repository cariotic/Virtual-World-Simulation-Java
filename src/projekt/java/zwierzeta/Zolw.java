package projekt.java.zwierzeta;

import projekt.java.*;

import java.awt.*;
import java.util.Random;

public class Zolw extends Zwierze {
    public Zolw(){}

    public Zolw(Swiat swiat, Polozenie polozenie){
        super(swiat, polozenie);
        this.setInicjatywa(1);
        this.setSila(2);
        this.setNazwa("Żółw");
        this.setKolor(new Color(0,102,0));
    }

    public Zolw(Swiat swiat, int x, int y, int inicjatywa, int wiek, int sila){
        super(swiat, x, y, inicjatywa, wiek, sila);
        this.setNazwa("Żółw");
        this.setKolor(new Color(0,102,0));
    }

    public boolean czyOdbilAtak(Organizm atakujacy){
        if(atakujacy.getSila() < 5)
            return true;
        else
            return false;
    }

    @Override
    public Organizm rozmnazanie(Polozenie polozenie){
        return new Zolw(swiat, polozenie);
    }

    @Override
    public void akcja(){
        Random rand = new Random();
        int szansa = rand.nextInt(4);
        if(szansa == 0){
            this.ruch();
        }
    }
    @Override
    public void kolizja(Organizm atakujacy){
        if(atakujacy.getNazwa().equals(this.getNazwa())){
            if(probaRozmnozenia()){
                GUI.komentator.nowyKomentarz("Nowe zwierzę: " + this.getNazwa());
                GUI.komentator.wyswietlKomentarz();
            }
        }
        else if(czyOdbilAtak(atakujacy)){
            GUI.komentator.nowyKomentarz(this.getNazwa() + " odparl atak " + atakujacy.getNazwa());
            GUI.komentator.wyswietlKomentarz();
        }
        else{
            walka(atakujacy);
        }
    }
}
