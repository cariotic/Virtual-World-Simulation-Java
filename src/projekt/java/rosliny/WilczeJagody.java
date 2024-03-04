package projekt.java.rosliny;

import projekt.java.Organizm;
import projekt.java.Polozenie;
import projekt.java.Roslina;
import projekt.java.Swiat;

import java.awt.*;

public class WilczeJagody extends Roslina {
    public WilczeJagody(){}

    public WilczeJagody(Swiat swiat, Polozenie polozenie){
        super(swiat, polozenie);
        this.setSila(99);
        this.setNazwa("Wilcze_Jagody");
        this.setKolor(new Color(102,0,153));
    }

    public WilczeJagody(Swiat swiat, int x, int y, int inicjatywa, int wiek, int sila){
        super(swiat, x, y, inicjatywa, wiek, sila);
        this.setNazwa("Wilcze_Jagody");
        this.setKolor(new Color(102,0,153));
    }

    public void zabij(Organizm atakujacy){
        swiat.tablicaOrganizmow[atakujacy.getY()][atakujacy.getX()] = null;
        atakujacy.setCzyZywy(false);
    }

    @Override
    public Organizm rozmnazanie(Polozenie polozenie){
        return new WilczeJagody(swiat, polozenie);
    }

    @Override
    public void kolizja(Organizm atakujacy){
        zabij(atakujacy);
    }
}
