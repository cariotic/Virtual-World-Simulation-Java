package projekt.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static projekt.java.Swiat.SZEROKOSC;
import static projekt.java.Swiat.WYSOKOSC;

public class PanelGry extends JPanel implements ActionListener{
    private Swiat swiat;
    static final int ROZMIAR_POLA = 25;
    static final int ROZMIAR = 500;
    static final int LICZBA_POL = (ROZMIAR*ROZMIAR)/ROZMIAR_POLA;
    boolean czyGra;
    private Pole[][] polaGry;

    PanelGry(Swiat swiat){
        this.swiat = swiat;
        this.setPreferredSize(new Dimension(ROZMIAR, ROZMIAR));
        this.setBackground(Color.white);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        polaGry = new Pole[WYSOKOSC][SZEROKOSC];
        for(int i=0; i<WYSOKOSC; i++){
            for(int j=0; j<SZEROKOSC; j++){
                polaGry[i][j] = new Pole(j, i);
            }
        }
        for(int i=0; i<WYSOKOSC; i++){
            for(int j=0; j<SZEROKOSC; j++){
                this.add(polaGry[i][j]);
            }
        }
        this.setLayout(new GridLayout(WYSOKOSC, SZEROKOSC));
        rozpocznijGre();
    }

    public void rozpocznijGre(){
        czyGra=true;
        rysuj();
    }

    public void rysuj(){
        for(int i=0; i<WYSOKOSC; i++){
            for(int j=0; j<SZEROKOSC; j++){
                if(swiat.tablicaOrganizmow[i][j] != null){
                    this.polaGry[i][j].setKolor(swiat.tablicaOrganizmow[i][j].getKolor());
                }
                else{
                    this.polaGry[i][j].setKolor(Color.white);
                }
            }
        }
    }

    public int getRozmiarPola(){
        return ROZMIAR_POLA;
    }

    public int getRozmiar(){
        return ROZMIAR;
    }

    @Override
    public void actionPerformed(ActionEvent e){

    }

    public class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            GUI.komentator.resetujKomentarz();
            GUI.komentator.wyswietlKomentarz();
            if(swiat.getStatusCzlowieka()) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_UP){
                    swiat.getCzlowiek().setKierunek('g');
                }
                else if(key == KeyEvent.VK_DOWN){
                    swiat.getCzlowiek().setKierunek('d');
                }
                else if(key == KeyEvent.VK_RIGHT){
                    swiat.getCzlowiek().setKierunek('p');
                }
                else if(key == KeyEvent.VK_LEFT){
                    swiat.getCzlowiek().setKierunek('l');
                }
                else if(key == KeyEvent.VK_X){
                    swiat.getCzlowiek().setKierunek('x');
                    if(swiat.getCzlowiek().czyMoznaAktywowacUmiejetnosc()){
                        swiat.getCzlowiek().umiejetnosc();
                    }
                }
                swiat.symulacjaTury();
                rysuj();
            }
            else{
                GUI.komentator.nowyKomentarz("Człowiek umarł, koniec gry!");
                GUI.komentator.wyswietlKomentarz();
            }
        }

        @Override
        public void keyTyped(KeyEvent e){

        }

        @Override
        public void keyReleased(KeyEvent e){

        }
    }

}
