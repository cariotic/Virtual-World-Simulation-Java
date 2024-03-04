package projekt.java.zwierzeta;

import projekt.java.*;

import java.awt.*;

public class Czlowiek extends Zwierze {
    char kierunek;
    int podstawowaSila = 5;
    int blokadaUmiejetnosci = 0;

    public Czlowiek(){}

    public Czlowiek(Swiat swiat, Polozenie polozenie){
        super(swiat, polozenie);
        this.setInicjatywa(4);
        this.setSila(5);
        this.setNazwa("Człowiek");
        this.setKolor(new Color(51,153,255));
    }

    public Czlowiek(Swiat swiat, int x, int y, int inicjatywa, int wiek, int sila, int podstawowaSila, int blokadaUmiejetnosci){
        super(swiat, x, y, inicjatywa, wiek, sila);
        this.setNazwa("Człowiek");
        this.setKolor(new Color(51,153,255));
        this.podstawowaSila = podstawowaSila;
        this.blokadaUmiejetnosci = blokadaUmiejetnosci;
    }

    public boolean czyMoznaAktywowacUmiejetnosc(){
        if(blokadaUmiejetnosci == 0)
            return true;
        else {
            GUI.komentator.nowyKomentarz("Nie można aktywować specjalnej umiejętności.");
            GUI.komentator.wyswietlKomentarz();
            return false;
        }
    }

    public void umiejetnosc(){
        if(this.getSila() == podstawowaSila && podstawowaSila <= 10) {
            this.setSila(11);
            this.blokadaUmiejetnosci = 11;
            GUI.komentator.nowyKomentarz("Aktywowano specjalną umiejętcość: magiczny eliksir.");
            GUI.komentator.wyswietlKomentarz();
        }
    }

    @Override
    public Organizm rozmnazanie(Polozenie polozenie){
        return new Czlowiek(swiat, polozenie);
    }

    @Override
    public void ruch(){
        int pomY = this.polozenie.y;
        int pomX = this.polozenie.x;
        switch(kierunek){
            case 'g': // gora
                if(pomY > 0){
                    if(swiat.tablicaOrganizmow[pomY - 1][pomX] == null){
                        this.polozenie.y -= 1;
                        swiat.tablicaOrganizmow[pomY - 1][pomX] = this;
                        swiat.tablicaOrganizmow[pomY][pomX] = null;
                    }
                    else{
                        swiat.tablicaOrganizmow[pomY - 1][pomX].kolizja(this);
                    }
                }
                break;
            case 'd': // dol
                if(pomY < swiat.getWYSOKOSC() - 1){
                    if(swiat.tablicaOrganizmow[pomY + 1][pomX] == null){
                        this.polozenie.y += 1;
                        swiat.tablicaOrganizmow[pomY + 1][pomX] = this;
                        swiat.tablicaOrganizmow[pomY][pomX] = null;
                    }
                    else{
                        swiat.tablicaOrganizmow[pomY + 1][pomX].kolizja(this);
                    }
                }
                break;
            case 'p': // prawo
                if(pomX < swiat.getSZEROKOSC() - 1){
                    if(swiat.tablicaOrganizmow[pomY][pomX + 1] == null){
                        this.polozenie.x += 1;
                        swiat.tablicaOrganizmow[pomY][pomX + 1] = this;
                        swiat.tablicaOrganizmow[pomY][pomX] = null;
                    }
                    else{
                        swiat.tablicaOrganizmow[pomY][pomX + 1].kolizja(this);
                    }
                }
                break;
            case 'l': // lewo
                if(pomX > 0){
                    if(swiat.tablicaOrganizmow[pomY][pomX - 1] == null){
                        this.polozenie.x -= 1;
                        swiat.tablicaOrganizmow[pomY][pomX - 1] = this;
                        swiat.tablicaOrganizmow[pomY][pomX] = null;
                    }
                    else{
                        swiat.tablicaOrganizmow[pomY][pomX - 1].kolizja(this);
                    }
                }
                break;
        }
    }

    @Override
    public void akcja(){
        if(blokadaUmiejetnosci != 0) {
            blokadaUmiejetnosci--;
            if (this.getSila() != podstawowaSila) {
                this.setSila(this.getSila() - 1);
            }
        }
        ruch();
    }

    public char getKierunek(){
        return kierunek;
    }

    public void setKierunek(char kierunek){
        this.kierunek = kierunek;
    }

    public int getPodstawowaSila(){
        return podstawowaSila;
    }

    public void setPodstawowaSila(int nowaSila){
        podstawowaSila = nowaSila;
    }

    public int getBlokadaUmiejetnosci(){
        return blokadaUmiejetnosci;
    }
}
