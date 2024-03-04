package projekt.java.zwierzeta;

import projekt.java.*;

import java.awt.*;
import java.util.Random;

public class Antylopa extends Zwierze {
    static final int ZASIEG_ANTYLOPY = 2;

    public Antylopa(){}

    public Antylopa(Swiat swiat, Polozenie polozenie){
        super(swiat, polozenie);
        this.setInicjatywa(4);
        this.setSila(4);
        this.setNazwa("Antylopa");
        this.setKolor(new Color(153,102,0));
    }

    public Antylopa(Swiat swiat, int x, int y, int inicjatywa, int wiek, int sila){
        super(swiat, x, y, inicjatywa, wiek, sila);
        this.setNazwa("Antylopa");
        this.setKolor(new Color(153,102,0));
    }

    public boolean czyUcieknie(){
        Random rand = new Random();
        int szansa = rand.nextInt(2);
        if(szansa == 0)
            return true;
        else
            return false;
    }

    public boolean ucieczka(){
        int pomX = this.polozenie.x;
        int pomY = this.polozenie.y;
        if(pomY > 0 && swiat.tablicaOrganizmow[pomY - 1][pomX] == null){
            this.polozenie.y -= 1;
            swiat.tablicaOrganizmow[pomY - 1][pomX] = this;
            swiat.tablicaOrganizmow[pomY][pomX] = null;
            return true;
        }
        else if(pomY < swiat.getWYSOKOSC() - 1 && swiat.tablicaOrganizmow[pomY + 1][pomX] == null){
            this.polozenie.y += 1;
            swiat.tablicaOrganizmow[pomY + 1][pomX] = this;
            swiat.tablicaOrganizmow[pomY][pomX] = null;
            return true;
        }
        else if(pomX < swiat.getSZEROKOSC() - 1 && swiat.tablicaOrganizmow[pomY][pomX + 1] == null){
            this.polozenie.x += 1;
            swiat.tablicaOrganizmow[pomY][pomX + 1] = this;
            swiat.tablicaOrganizmow[pomY][pomX] = null;
            return true;
        }
        else if(pomX > 0 && swiat.tablicaOrganizmow[pomY][pomX - 1] == null){
            this.polozenie.x -= 1;
            swiat.tablicaOrganizmow[pomY][pomX - 1] = this;
            swiat.tablicaOrganizmow[pomY][pomX] = null;
            return true;
        }
        return false;
    }

    @Override
    public void ruch(){
        Random rand = new Random();
        int kierunek = rand.nextInt(swiat.getLiczbaSasiednichPol());
        int pomX = this.polozenie.x;
        int pomY = this.polozenie.y;
        switch(kierunek){
            case 0: // gora
                if(pomY - ZASIEG_ANTYLOPY >= 0){
                    if(swiat.tablicaOrganizmow[pomY - ZASIEG_ANTYLOPY][pomX] == null){
                        this.polozenie.y -= ZASIEG_ANTYLOPY;
                        swiat.tablicaOrganizmow[pomY - ZASIEG_ANTYLOPY][pomX] = this;
                        swiat.tablicaOrganizmow[pomY][pomX] = null;
                    }
                    else{
                        swiat.tablicaOrganizmow[pomY - ZASIEG_ANTYLOPY][pomX].kolizja(this);
                    }
                }
                break;
            case 1: // dol
                if(pomY < swiat.getWYSOKOSC() - ZASIEG_ANTYLOPY){
                    if(swiat.tablicaOrganizmow[pomY + ZASIEG_ANTYLOPY][pomX] == null){
                        this.polozenie.y += ZASIEG_ANTYLOPY;
                        swiat.tablicaOrganizmow[pomY + ZASIEG_ANTYLOPY][pomX] = this;
                        swiat.tablicaOrganizmow[pomY][pomX] = null;
                    }
                    else{
                        swiat.tablicaOrganizmow[pomY + ZASIEG_ANTYLOPY][pomX].kolizja(this);
                    }
                }
                break;
            case 2: // prawo
                if(pomX < swiat.getSZEROKOSC() - ZASIEG_ANTYLOPY){
                    if(swiat.tablicaOrganizmow[pomY][pomX + ZASIEG_ANTYLOPY] == null){
                        this.polozenie.x += ZASIEG_ANTYLOPY;
                        swiat.tablicaOrganizmow[pomY][pomX + ZASIEG_ANTYLOPY] = this;
                        swiat.tablicaOrganizmow[pomY][pomX] = null;
                    }
                    else{
                        swiat.tablicaOrganizmow[pomY][pomX + ZASIEG_ANTYLOPY].kolizja(this);
                    }
                }
                break;
            case 3: // lewo
                if(pomX - ZASIEG_ANTYLOPY >= 0){
                    if(swiat.tablicaOrganizmow[pomY][pomX - ZASIEG_ANTYLOPY] == null){
                        this.polozenie.x -= ZASIEG_ANTYLOPY;
                        swiat.tablicaOrganizmow[pomY][pomX - ZASIEG_ANTYLOPY] = this;
                        swiat.tablicaOrganizmow[pomY][pomX] = null;
                    }
                    else{
                        swiat.tablicaOrganizmow[pomY][pomX - ZASIEG_ANTYLOPY].kolizja(this);
                    }
                }
                break;
        }
    }

    @Override
    public Organizm rozmnazanie(Polozenie polozenie){
        return new Antylopa(swiat, polozenie);
    }

    @Override
    public void akcja(){
        ruch();
    }

    @Override
    public void kolizja(Organizm atakujacy){
        if(czyUcieknie()){
            if(ucieczka()) {
                GUI.komentator.nowyKomentarz(this.getNazwa() + " uciekła.");
                GUI.komentator.wyswietlKomentarz();
            }
        }
        else{
            this.walka(atakujacy);
        }
    }
}
