package projekt.java.zwierzeta;

import projekt.java.*;

import java.awt.*;
import java.util.Random;

public class Lis extends Zwierze {
    public Lis(){}

    public Lis(Swiat swiat, Polozenie polozenie){
        super(swiat, polozenie);
        this.setInicjatywa(7);
        this.setSila(3);
        this.setNazwa("Lis");
        this.setKolor(new Color(255,102,0));
    }

    public Lis(Swiat swiat, int x, int y, int inicjatywa, int wiek, int sila){
        super(swiat, x, y, inicjatywa, wiek, sila);
        this.setNazwa("Lis");
        this.setKolor(new Color(255,102,0));
    }

    @Override
    public Organizm rozmnazanie(Polozenie polozenie){
        return new Lis(swiat, polozenie);
    }

    @Override
    public void ruch(){
        Random rand = new Random();
        int kierunek = rand.nextInt(swiat.getLiczbaSasiednichPol());
        int pomY = this.polozenie.y;
        int pomX = this.polozenie.x;
        switch(kierunek){
            case 0: // gora
                if(pomY > 0){
                    if(swiat.tablicaOrganizmow[pomY - 1][pomX] == null){
                        this.polozenie.y -= 1;
                        swiat.tablicaOrganizmow[pomY - 1][pomX] = this;
                        swiat.tablicaOrganizmow[pomY][pomX] = null;
                    }
                    else{
                        if(swiat.tablicaOrganizmow[pomY - 1][pomX].getSila() <= this.getSila()) {
                            swiat.tablicaOrganizmow[pomY - 1][pomX].kolizja(this);
                        }
                        else{
                            GUI.komentator.nowyKomentarz(this.getNazwa() + " wyczuł silniejszy organizm");
                            GUI.komentator.wyswietlKomentarz();
                        }
                    }
                }
                break;
            case 1: // dol
                if(pomY < swiat.getWYSOKOSC() - 1){
                    if(swiat.tablicaOrganizmow[pomY + 1][pomX] == null){
                        this.polozenie.y += 1;
                        swiat.tablicaOrganizmow[pomY + 1][pomX] = this;
                        swiat.tablicaOrganizmow[pomY][pomX] = null;
                    }
                    else{
                        if(swiat.tablicaOrganizmow[pomY + 1][pomX].getSila() <= this.getSila()) {
                            swiat.tablicaOrganizmow[pomY + 1][pomX].kolizja(this);
                        }
                        else{
                            GUI.komentator.nowyKomentarz(this.getNazwa() + " wyczuł silniejszy organizm");
                            GUI.komentator.wyswietlKomentarz();
                        }
                    }
                }
                break;
            case 2: // prawo
                if(pomX < swiat.getSZEROKOSC() - 1){
                    if(swiat.tablicaOrganizmow[pomY][pomX + 1] == null){
                        this.polozenie.x += 1;
                        swiat.tablicaOrganizmow[pomY][pomX + 1] = this;
                        swiat.tablicaOrganizmow[pomY][pomX] = null;
                    }
                    else{
                        if(swiat.tablicaOrganizmow[pomY][pomX + 1].getSila() <= this.getSila()) {
                            swiat.tablicaOrganizmow[pomY][pomX + 1].kolizja(this);
                        }
                        else{
                            GUI.komentator.nowyKomentarz(this.getNazwa() + " wyczuł silniejszy organizm");
                            GUI.komentator.wyswietlKomentarz();
                        }
                    }
                }
                break;
            case 3: // lewo
                if(pomX > 0){
                    if(swiat.tablicaOrganizmow[pomY][pomX - 1] == null){
                        this.polozenie.x -= 1;
                        swiat.tablicaOrganizmow[pomY][pomX - 1] = this;
                        swiat.tablicaOrganizmow[pomY][pomX] = null;
                    }
                    else{
                        if(swiat.tablicaOrganizmow[pomY][pomX - 1].getSila() <= this.getSila()) {
                            swiat.tablicaOrganizmow[pomY][pomX - 1].kolizja(this);
                        }
                        else{
                            GUI.komentator.nowyKomentarz(this.getNazwa() + " wyczuł silniejszy organizm");
                            GUI.komentator.wyswietlKomentarz();
                        }
                    }
                }
                break;
        }
    }
}
