package projekt.java.rosliny;
import projekt.java.*;

import java.awt.*;

public class Mlecz extends Roslina {
    private static final int LICZBA_PROB_MLECZA = 3;
    public Mlecz(){}

    public Mlecz(Swiat swiat, Polozenie polozenie){
        super(swiat, polozenie);
        this.setSila(0);
        this.setNazwa("Mlecz");
        this.setKolor(new Color(255,255,0));
    }

    public Mlecz(Swiat swiat, int x, int y, int inicjatywa, int wiek, int sila){
        super(swiat, x, y, inicjatywa, wiek, sila);
        this.setNazwa("Mlecz");
        this.setKolor(new Color(255,255,0));
    }

    @Override
    public Organizm rozmnazanie(Polozenie polozenie){
        return new Mlecz(swiat, polozenie);
    }

    @Override
    public void akcja(){
        for(int i = 0; i< LICZBA_PROB_MLECZA; i++){
            if(probaRozmnozenia()){
                GUI.komentator.nowyKomentarz("Nowa roślina: " + this.getNazwa());
                GUI.komentator.wyswietlKomentarz();
            }
        }
    }
}
