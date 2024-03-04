package projekt.java.rosliny;
import java.awt.*;
import java.util.Random;
import projekt.java.Organizm;
import projekt.java.Polozenie;
import projekt.java.Roslina;
import projekt.java.Swiat;

public class Trawa extends Roslina {
    public Trawa(){}

    public Trawa(Swiat swiat, Polozenie polozenie){
        super(swiat, polozenie);
        this.setSila(0);
        this.setNazwa("Trawa");
        this.setKolor(new Color(0,204,0));
    }

    public Trawa(Swiat swiat, int x, int y, int inicjatywa, int wiek, int sila){
        super(swiat, x, y, inicjatywa, wiek, sila);
        this.setNazwa("Trawa");
        this.setKolor(new Color(0,204,0));
    }

    @Override
    public Organizm rozmnazanie(Polozenie polozenie){
        return new Trawa(swiat, polozenie);
    }
}
