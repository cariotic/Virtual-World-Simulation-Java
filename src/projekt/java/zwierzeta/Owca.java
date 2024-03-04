package projekt.java.zwierzeta;

import projekt.java.Organizm;
import projekt.java.Polozenie;
import projekt.java.Swiat;
import projekt.java.Zwierze;

import java.awt.*;

public class Owca extends Zwierze {
    public Owca(){}

    public Owca(Swiat swiat, Polozenie polozenie){
        super(swiat, polozenie);
        this.setInicjatywa(4);
        this.setSila(4);
        this.setNazwa("Owca");
        this.setKolor(new Color(153,153,153));
    }

    public Owca(Swiat swiat, int x, int y, int inicjatywa, int wiek, int sila){
        super(swiat, x, y, inicjatywa, wiek, sila);
        this.setNazwa("Owca");
        this.setKolor(new Color(153,153,153));
    }

    @Override
    public Organizm rozmnazanie(Polozenie polozenie){
        return new Owca(swiat, polozenie);
    }
}
