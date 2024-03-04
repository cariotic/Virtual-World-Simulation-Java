package projekt.java;

import java.util.Random;

public abstract class Zwierze extends Organizm{
    public Zwierze(){}

    public Zwierze(Swiat swiat, Polozenie polozenie){
        super(swiat, polozenie);
    }

    public Zwierze(Swiat swiat, int x, int y, int inicjatywa, int wiek, int sila){
        super(swiat, x, y, inicjatywa, wiek, sila);
    }

    public void ruch(){
        Random rand = new Random();
        int kierunek = rand.nextInt(swiat.getLiczbaSasiednichPol());
        int pomX = this.polozenie.x;
        int pomY = this.polozenie.y;
        switch(kierunek){
            case 0: // gora
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
            case 1: // dol
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
            case 2: // prawo
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
            case 3: // lewo
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

    public void zabij(Organizm atakujacy){
        swiat.tablicaOrganizmow[atakujacy.getY()][atakujacy.getX()] = null;
        atakujacy.setCzyZywy(false);
    }

    @Override
    public boolean probaRozmnozenia(){
        Random rand = new Random();
        int kierunek = rand.nextInt(swiat.getLiczbaSasiednichPol());
        int pomY = this.polozenie.y;
        int pomX = this.polozenie.x;
        switch(kierunek){
            case 0: // gora
                if(pomY > 0 && swiat.tablicaOrganizmow[pomY - 1][pomX] == null){
                    Polozenie polozenieDziecka = new Polozenie(pomX, pomY - 1);
                    Organizm dziecko = rozmnazanie(polozenieDziecka);
                    swiat.organizmy.add(dziecko);
                    swiat.tablicaOrganizmow[pomY - 1][pomX] = dziecko;
                    return true;
                }
                break;
            case 1: // dol
                if(pomY < swiat.getWYSOKOSC() - 1 && swiat.tablicaOrganizmow[pomY + 1][pomX] == null){
                    Polozenie polozenieDziecka = new Polozenie(pomX, pomY + 1);
                    Organizm dziecko = rozmnazanie(polozenieDziecka);
                    swiat.organizmy.add(dziecko);
                    swiat.tablicaOrganizmow[pomY + 1][pomX] = dziecko;
                    return true;
                }
            case 2: // prawo
                if(pomX < swiat.getSZEROKOSC() - 1 && swiat.tablicaOrganizmow[pomY][pomX + 1] == null){
                    Polozenie polozenieDziecka = new Polozenie(pomX + 1, pomY);
                    Organizm dziecko = rozmnazanie(polozenieDziecka);
                    swiat.organizmy.add(dziecko);
                    swiat.tablicaOrganizmow[pomY][pomX + 1] = dziecko;
                    return true;
                }
                break;
            case 3: // lewo
                if(pomY > 0 && swiat.tablicaOrganizmow[pomY][pomX - 1] == null){
                    Polozenie polozenieDziecka = new Polozenie(pomX - 1, pomY);
                    Organizm dziecko = rozmnazanie(polozenieDziecka);
                    swiat.organizmy.add(dziecko);
                    swiat.tablicaOrganizmow[pomY][pomX - 1] = dziecko;
                    return true;
                }
                break;
        }
        return false;
    }

    public boolean czyOdbilAtak(Organizm atakujacy){
        if(this.getSila() > atakujacy.getSila())
            return true;
        else
            return false;
    }

    public void walka(Organizm atakujacy){
        if(this.czyOdbilAtak(atakujacy)){
            zabij(atakujacy);
            GUI.komentator.nowyKomentarz(this.getNazwa() + " zabija " + atakujacy.getNazwa());
            GUI.komentator.wyswietlKomentarz();
        }
        else{
            swiat.tablicaOrganizmow[atakujacy.polozenie.y][atakujacy.polozenie.x] = null;
            atakujacy.polozenie = this.polozenie;
            zabij(this);
            swiat.tablicaOrganizmow[atakujacy.polozenie.y][atakujacy.polozenie.x] = atakujacy;
            GUI.komentator.nowyKomentarz(atakujacy.getNazwa() + " zabija " + this.getNazwa());
            GUI.komentator.wyswietlKomentarz();
        }
    }

    @Override
    public void akcja(){
        this.ruch();
    }

    @Override
    public void kolizja(Organizm atakujacy){
        if(this.getNazwa().equals(atakujacy.getNazwa())){
            if(this.probaRozmnozenia()){
                GUI.komentator.nowyKomentarz("Nowe zwierzę: " + this.getNazwa());
                GUI.komentator.wyswietlKomentarz();
            }
        }
        else{
            walka(atakujacy);
        }
    }
}
