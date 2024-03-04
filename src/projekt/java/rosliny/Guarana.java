package projekt.java.rosliny;

import projekt.java.*;
import projekt.java.zwierzeta.Czlowiek;

import java.awt.*;

public class Guarana extends Roslina {
    public Guarana(){}

    public Guarana(Swiat swiat, Polozenie polozenie){
        super(swiat, polozenie);
        this.setSila(0);
        this.setNazwa("Guarana");
        this.setKolor(new Color(255,51,51));
    }

    public Guarana(Swiat swiat, int x, int y, int inicjatywa, int wiek, int sila){
        super(swiat, x, y, inicjatywa, wiek, sila);
        this.setNazwa("Guarana");
        this.setKolor(new Color(255,51,51));
    }

    @Override
    public Organizm rozmnazanie(Polozenie polozenie){
        return new Guarana(swiat, polozenie);
    }

    @Override
    public void kolizja(Organizm atakujacy){
        if(atakujacy instanceof Czlowiek){
            ((Czlowiek) atakujacy).setPodstawowaSila(((Czlowiek) atakujacy).getPodstawowaSila() + 3);
        }
        atakujacy.setSila(atakujacy.getSila() + 3);
        swiat.tablicaOrganizmow[atakujacy.getY()][atakujacy.getX()] = null;
        atakujacy.setPolozenie(this.polozenie);
        this.zjedzenie();
        swiat.tablicaOrganizmow[atakujacy.getY()][atakujacy.getX()] = atakujacy;
        GUI.komentator.nowyKomentarz(atakujacy.getNazwa() + " zjada " + this.getNazwa() + " czym zwiększa swoją siłę o 3");
        GUI.komentator.wyswietlKomentarz();
    }
}
