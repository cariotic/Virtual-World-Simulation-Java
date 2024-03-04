package projekt.java.rosliny;

import projekt.java.*;

import java.awt.*;

public class BarszczSosnowskiego extends Roslina {
    public BarszczSosnowskiego(){}

    public BarszczSosnowskiego(Swiat swiat, Polozenie polozenie){
        super(swiat, polozenie);
        this.setSila(10);
        this.setNazwa("Barszcz_Sosnowskiego");
        this.setKolor(new Color(102,255,102));
    }

    public BarszczSosnowskiego(Swiat swiat, int x, int y, int inicjatywa, int wiek, int sila){
        super(swiat, x, y, inicjatywa, wiek, sila);
        this.setNazwa("Barszcz_Sosnowskiego");
        this.setKolor(new Color(102,255,102));
    }

    public void zabij(Organizm atakujacy){
        swiat.tablicaOrganizmow[atakujacy.getY()][atakujacy.getX()] = null;
        atakujacy.setCzyZywy(false);
;    }

    @Override
    public Organizm rozmnazanie(Polozenie polozenie){
        return new BarszczSosnowskiego(swiat, polozenie);
    }

    @Override
    public void akcja(){
        if(probaRozmnozenia()){
            GUI.komentator.nowyKomentarz("Nowa roślina: " + this.getNazwa());
            GUI.komentator.wyswietlKomentarz();
        }
        int pomY = this.polozenie.y;
        int pomX = this.polozenie.x;
        if(pomY > 0 && swiat.tablicaOrganizmow[pomY - 1][pomX] != null && !swiat.tablicaOrganizmow[pomY - 1][pomX].getNazwa().equals(this.getNazwa())){
            String ofiara = swiat.tablicaOrganizmow[pomY - 1][pomX].getNazwa();
            zabij(swiat.tablicaOrganizmow[pomY - 1][pomX]);
            GUI.komentator.nowyKomentarz(this.getNazwa() + " zabija " + ofiara);
            GUI.komentator.wyswietlKomentarz();
        }
        if(pomY < swiat.getWYSOKOSC() - 1 && swiat.tablicaOrganizmow[pomY + 1][pomX] != null && !swiat.tablicaOrganizmow[pomY + 1][pomX].getNazwa().equals(this.getNazwa())){
            String ofiara = swiat.tablicaOrganizmow[pomY + 1][pomX].getNazwa();
            zabij(swiat.tablicaOrganizmow[pomY + 1][pomX]);
            GUI.komentator.nowyKomentarz(this.getNazwa() + " zabija " + ofiara);
            GUI.komentator.wyswietlKomentarz();
        }
        if(pomX > 0 && swiat.tablicaOrganizmow[pomY][pomX - 1] != null && !swiat.tablicaOrganizmow[pomY][pomX - 1].getNazwa().equals(this.getNazwa())){
            String ofiara = swiat.tablicaOrganizmow[pomY][pomX - 1].getNazwa();
            zabij(swiat.tablicaOrganizmow[pomY][pomX - 1]);
            GUI.komentator.nowyKomentarz(this.getNazwa() + " zabija " + ofiara);
            GUI.komentator.wyswietlKomentarz();
        }
        if(pomX < swiat.getSZEROKOSC() - 1 && swiat.tablicaOrganizmow[pomY][pomX + 1] != null && !swiat.tablicaOrganizmow[pomY][pomX + 1].getNazwa().equals(this.getNazwa())){
            String ofiara = swiat.tablicaOrganizmow[pomY][pomX + 1].getNazwa();
            zabij(swiat.tablicaOrganizmow[pomY][pomX + 1]);
            GUI.komentator.nowyKomentarz(this.getNazwa() + " zabija " + ofiara);
            GUI.komentator.wyswietlKomentarz();
        }
    }

    @Override
    public void kolizja(Organizm atakujacy){
        zabij(atakujacy);
        GUI.komentator.nowyKomentarz(this.getNazwa() + " zabija " + atakujacy.getNazwa());
        GUI.komentator.wyswietlKomentarz();
    }
}
