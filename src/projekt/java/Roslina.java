package projekt.java;
import java.util.Random;

public abstract class Roslina extends Organizm{
    public Roslina(){}

    public Roslina(Swiat swiat, Polozenie polozenie){
        super(swiat, polozenie);
    }

    public Roslina(Swiat swiat, int x, int y, int inicjatywa, int wiek, int sila){
        super(swiat, x, y, inicjatywa, wiek, sila);
    }

    public void zjedzenie(){
        swiat.tablicaOrganizmow[this.polozenie.y][this.polozenie.x] = null;
        this.setCzyZywy(false);
    }

    public boolean czyAkcja(){
        Random rand = new Random();
        int szansa = rand.nextInt(15);
        if(szansa == 0){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean probaRozmnozenia(){
        if(czyAkcja()){
            int pomY = this.polozenie.y;
            int pomX = this.polozenie.x;
            Random rand = new Random();
            int kierunek = rand.nextInt(swiat.getLiczbaSasiednichPol());
            switch(kierunek){
                case 0: //gora
                    if(pomY > 0 && swiat.tablicaOrganizmow[pomY - 1][pomX] == null){
                        Polozenie polozenieDziecka = new Polozenie(pomX, pomY - 1);
                        Organizm dziecko = rozmnazanie(polozenieDziecka);
                        swiat.organizmy.add(dziecko);
                        swiat.tablicaOrganizmow[pomY - 1][pomX] = dziecko;
                        return true;
                    }
                    break;
                case 1: //dol
                    if(pomY < swiat.getWYSOKOSC() - 1 && swiat.tablicaOrganizmow[pomY + 1][pomX] == null){
                        Polozenie polozenieDziecka = new Polozenie(pomX, pomY + 1);
                        Organizm dziecko = rozmnazanie(polozenieDziecka);
                        swiat.organizmy.add(dziecko);
                        swiat.tablicaOrganizmow[pomY + 1][pomX] = dziecko;
                        return true;
                    }
                    break;
                case 2: //prawo
                    if(pomX < swiat.getSZEROKOSC() - 1 && swiat.tablicaOrganizmow[pomY][pomX + 1] == null){
                        Polozenie polozenieDziecka = new Polozenie(pomX + 1, pomY);
                        Organizm dziecko = rozmnazanie(polozenieDziecka);
                        swiat.organizmy.add(dziecko);
                        swiat.tablicaOrganizmow[pomY][pomX + 1] = dziecko;
                        return true;
                    }
                    break;
                case 3:
                    if(pomX > 0 && swiat.tablicaOrganizmow[pomY][pomX - 1] == null){
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
        return false;
    }

    @Override
    public void akcja(){
        if(probaRozmnozenia()){
            GUI.komentator.nowyKomentarz("Nowa roślina: " + this.getNazwa());
            GUI.komentator.wyswietlKomentarz();
        }
    }
    @Override
    public void kolizja(Organizm atakujacy){
        swiat.tablicaOrganizmow[atakujacy.polozenie.y][atakujacy.polozenie.x] = null;
        atakujacy.polozenie = this.polozenie;
        this.zjedzenie();
        swiat.tablicaOrganizmow[atakujacy.polozenie.y][atakujacy.polozenie.x] = atakujacy;
        GUI.komentator.nowyKomentarz(atakujacy.getNazwa() + " zjada " + this.getNazwa());
        GUI.komentator.wyswietlKomentarz();
    }
}
