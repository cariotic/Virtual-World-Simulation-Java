package projekt.java.zwierzeta;

import projekt.java.Organizm;
import projekt.java.Polozenie;
import projekt.java.Swiat;
import projekt.java.Zwierze;

import java.awt.*;

public class Wilk extends Zwierze {
    public Wilk(){}

    public Wilk(Swiat swiat, Polozenie polozenie){
        super(swiat, polozenie);
        this.setInicjatywa(5);
        this.setSila(9);
        this.setNazwa("Wilk");
        this.setKolor(new Color(0,0,0));
    }

    public Wilk(Swiat swiat, int x, int y, int inicjatywa, int wiek, int sila){
        super(swiat, x, y, inicjatywa, wiek, sila);
        this.setNazwa("Wilk");
        this.setKolor(new Color(0,0,0));
    }

    @Override
    public Organizm rozmnazanie(Polozenie polozenie){
        return new Wilk(swiat, polozenie);
    }
}
