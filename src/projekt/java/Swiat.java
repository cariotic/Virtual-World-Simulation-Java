package projekt.java;

import projekt.java.rosliny.*;
import projekt.java.zwierzeta.*;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class Swiat {
    static final int WYSOKOSC = 20;
    static final int SZEROKOSC = 20;
    static final int LICZBA_SASIEDNICH_POL = 4;
    private boolean statusCzlowieka;
    public Organizm[][] tablicaOrganizmow;
    public List<Organizm> organizmy;
    public GUI okno;
    Czlowiek czlowiek;

    public Swiat(GUI okno){
        this.okno = okno;
        organizmy = new LinkedList<>();
        tablicaOrganizmow = new Organizm[WYSOKOSC][SZEROKOSC];
        for(int i=0; i<WYSOKOSC; i++){
            for(int j = 0; j< SZEROKOSC; j++){
                tablicaOrganizmow[i][j] = null;
            }
        }
        this.zaludnianie();
        this.statusCzlowieka = true;
    }

    // konstruktor do wczytywania z pliku
    public Swiat(boolean statusCzlowieka){
        this.statusCzlowieka = statusCzlowieka;
        organizmy = new LinkedList<>();
        tablicaOrganizmow = new Organizm[WYSOKOSC][SZEROKOSC];
    }

    public Polozenie losowePolozenie(){
        Random rand = new Random();
        while(true){
            int y = rand.nextInt(this.getWYSOKOSC());
            int x = rand.nextInt(this.getSZEROKOSC());
            if(this.tablicaOrganizmow[y][x] == null)
                return new Polozenie(x, y);
        }
    }

    public void dodawanieOrganizmu(Organizm organizm){
        organizmy.add(organizm);
        tablicaOrganizmow[organizm.polozenie.y][organizm.polozenie.x] = organizm;
    }

    public void zaludnianie(){
        Polozenie polozenieCzlowieka = new Polozenie(9, 19);
        czlowiek = new Czlowiek(this, polozenieCzlowieka);
        dodawanieOrganizmu(czlowiek);

        dodawanieOrganizmu(new Antylopa(this, losowePolozenie()));
        dodawanieOrganizmu(new Antylopa(this, losowePolozenie()));

        dodawanieOrganizmu(new BarszczSosnowskiego(this, losowePolozenie()));
        dodawanieOrganizmu(new BarszczSosnowskiego(this, losowePolozenie()));

        dodawanieOrganizmu(new Guarana(this, losowePolozenie()));
        dodawanieOrganizmu(new Guarana(this, losowePolozenie()));

        dodawanieOrganizmu(new Lis(this, losowePolozenie()));
        dodawanieOrganizmu(new Lis(this, losowePolozenie()));

        dodawanieOrganizmu(new Mlecz(this, losowePolozenie()));
        dodawanieOrganizmu(new Mlecz(this, losowePolozenie()));

        dodawanieOrganizmu(new Owca(this, losowePolozenie()));
        dodawanieOrganizmu(new Owca(this, losowePolozenie()));

        dodawanieOrganizmu(new Trawa(this, losowePolozenie()));
        dodawanieOrganizmu(new Trawa(this, losowePolozenie()));

        dodawanieOrganizmu(new WilczeJagody(this, losowePolozenie()));
        dodawanieOrganizmu(new WilczeJagody(this, losowePolozenie()));

        dodawanieOrganizmu(new Wilk(this, losowePolozenie()));
        dodawanieOrganizmu(new Wilk(this, losowePolozenie()));

        dodawanieOrganizmu(new Zolw(this, losowePolozenie()));
        dodawanieOrganizmu(new Zolw(this, losowePolozenie()));
    }

    public void sortujOrganizmy(){
        Collections.sort(organizmy, new Comparator<Organizm>() {
            @Override
            public int compare(Organizm o1, Organizm o2) {
                if(o1.getInicjatywa() != o2.getInicjatywa())
                    return Integer.valueOf(o2.getInicjatywa()).compareTo(o1.getInicjatywa());
                else{
                    return Integer.valueOf(o2.getWiek()).compareTo(o1.getWiek());
                }
            }
        });
    }

    public void symulacjaTury(){
        statusCzlowieka = czlowiek.getCzyZywy();
        if(!statusCzlowieka)
            return;
        sortujOrganizmy();
        for(int i=0; i<organizmy.size(); i++){
            if(organizmy.get(i).getCzyZywy()){
                organizmy.get(i).akcja();
                organizmy.get(i).setWiek(organizmy.get(i).getWiek() + 1);
            }
        }
    }

    public void zapiszSwiat(String nazwaPliku){
        try{
            File zapis = new File(nazwaPliku + ".txt");
            if(zapis.createNewFile()){
                FileWriter writer = new FileWriter(zapis);
                writer.write(String.valueOf(getStatusCzlowieka()));
                writer.write(System.lineSeparator());
                for(int i=0; i<organizmy.size(); i++){
                    if(organizmy.get(i).getCzyZywy()) {
                        writer.write(organizmy.get(i).getNazwa() + " ");
                        writer.write(organizmy.get(i).getX() + " ");
                        writer.write(organizmy.get(i).getY() + " ");
                        writer.write(organizmy.get(i).getInicjatywa() + " ");
                        writer.write(organizmy.get(i).getWiek() + " ");
                        writer.write(organizmy.get(i).getSila() + " ");
                        if(organizmy.get(i) instanceof Czlowiek){
                            writer.write(((Czlowiek) organizmy.get(i)).getPodstawowaSila() + " ");
                            writer.write(((Czlowiek) organizmy.get(i)).getBlokadaUmiejetnosci() + " ");
                        }
                        writer.write(System.lineSeparator());
                    }
                }
                writer.close();
                GUI.komentator.nowyKomentarz("Zapisano stan gry");
                GUI.komentator.wyswietlKomentarz();
            }
            else{
                GUI.komentator.nowyKomentarz("Plik już istnieje");
                GUI.komentator.wyswietlKomentarz();
            }
        } catch (IOException e) {
            GUI.komentator.nowyKomentarz("Błąd przy zapisywaniu");
            GUI.komentator.wyswietlKomentarz();
            e.printStackTrace();
        }
    }

    public Swiat wczytajSwiat(String nazwaPliku){
        try{
            File zapis = new File(nazwaPliku);
            Scanner scanner = new Scanner(zapis);
            String strStatusCzlowieka = scanner.nextLine();
            boolean statusCzlowieka = Boolean.parseBoolean(strStatusCzlowieka);
            Swiat nowySwiat = new Swiat(statusCzlowieka);
            String organizm;
            String[] daneOrganizmu;
            while(scanner.hasNextLine()){
                organizm = scanner.nextLine();
                daneOrganizmu = organizm.split(" ");
                String nazwaOrganizmu = daneOrganizmu[0];
                int x = Integer.parseInt(daneOrganizmu[1]);
                int y = Integer.parseInt(daneOrganizmu[2]);
                int inicjatywa = Integer.parseInt(daneOrganizmu[3]);
                int wiek = Integer.parseInt(daneOrganizmu[4]);
                int sila = Integer.parseInt(daneOrganizmu[5]);
                if(nazwaOrganizmu.equals("Człowiek")){
                    int podstawowaSila = Integer.parseInt(daneOrganizmu[6]);
                    int blokadaUmiejetnosci = Integer.parseInt(daneOrganizmu[7]);
                    Organizm organizmPom = nowySwiat.wczytajOrganizm(nazwaOrganizmu, nowySwiat, x, y, inicjatywa, wiek, sila, podstawowaSila, blokadaUmiejetnosci);
                    nowySwiat.dodawanieOrganizmu(organizmPom);
                    nowySwiat.setCzlowiek((Czlowiek) organizmPom);
                }
                else {
                    Organizm organizmPom = nowySwiat.wczytajOrganizm(nazwaOrganizmu, nowySwiat, x, y, inicjatywa, wiek, sila, 0, 0);
                    nowySwiat.dodawanieOrganizmu(organizmPom);
                }
            }
            return nowySwiat;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Organizm wczytajOrganizm(String nazwa, Swiat swiat, int x, int y, int inicjatywa, int wiek, int sila, int podstawowaSila, int blokadaUmiejetnosci){
        if(nazwa.equals("Barszcz_Sosnowskiego")){
            return new BarszczSosnowskiego(swiat, x, y, inicjatywa, wiek, sila);
        }
        else if(nazwa.equals("Guarana")){
            return new Guarana(swiat, x, y, inicjatywa, wiek, sila);
        }
        else if(nazwa.equals("Mlecz")){
            return new Mlecz(swiat, x, y, inicjatywa, wiek, sila);
        }
        else if(nazwa.equals("Trawa")){
            return new Trawa(swiat, x, y, inicjatywa, wiek, sila);
        }
        else if(nazwa.equals("Wilcze_Jagody")){
            return new WilczeJagody(swiat, x, y, inicjatywa, wiek, sila);
        }
        else if(nazwa.equals("Antylopa")){
            return new Antylopa(swiat, x, y, inicjatywa, wiek, sila);
        }
        else if(nazwa.equals("Człowiek")){
            return new Czlowiek(swiat, x, y, inicjatywa, wiek, sila, podstawowaSila, blokadaUmiejetnosci);
        }
        else if(nazwa.equals("Lis")){
            return new Lis(swiat, x, y , inicjatywa, wiek, sila);
        }
        else if(nazwa.equals("Owca")){
            return new Lis(swiat, x, y ,inicjatywa, wiek, sila);
        }
        else if(nazwa.equals("Wilk")){
            return new Wilk(swiat, x, y, inicjatywa, wiek, sila);
        }
        else if(nazwa.equals("Żółw")){
            return new Zolw(swiat, x, y, inicjatywa, wiek, sila);
        }
        else
            return null;
    }

    public int getWYSOKOSC(){
        return WYSOKOSC;
    }

    public int getSZEROKOSC(){
        return SZEROKOSC;
    }

    public int getLiczbaSasiednichPol(){
        return LICZBA_SASIEDNICH_POL;
    }

    public boolean getStatusCzlowieka(){
        return statusCzlowieka;
    }

    public Czlowiek getCzlowiek(){
        return czlowiek;
    }

    public void setCzlowiek(Czlowiek czlowiek){
        this.czlowiek = czlowiek;
    }
}
