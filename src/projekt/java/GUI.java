package projekt.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GUI extends JFrame implements ActionListener {
    private Swiat swiat;
    private JMenuBar menu;
    private JMenuItem zapisz;
    private JMenuItem wczytaj;
    public static PanelGry panelGry;
    public static Komentator komentator;

    GUI(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(520,700);
        this.setResizable(false);
        this.setLayout(new FlowLayout());

        menu = new JMenuBar();
        zapisz = new JMenuItem("Zapisz");
        wczytaj = new JMenuItem("Wczytaj");

        zapisz.addActionListener(this);
        wczytaj.addActionListener(this);

        menu.add(zapisz);
        menu.add(wczytaj);
        this.setJMenuBar(menu);

        this.swiat = new Swiat(this);
        panelGry = new PanelGry(swiat);
        this.add(panelGry);

        komentator = new Komentator(swiat);
        this.add(komentator);
        komentator.wyswietlKomentarz();

        this.setVisible(true);
    }

    // do wczytywania z pliku
    GUI(Swiat nowySwiat){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(520,700);
        this.setResizable(false);
        this.setLayout(new FlowLayout());

        menu = new JMenuBar();
        zapisz = new JMenuItem("Zapisz");
        wczytaj = new JMenuItem("Wczytaj");

        zapisz.addActionListener(this);
        wczytaj.addActionListener(this);

        menu.add(zapisz);
        menu.add(wczytaj);
        this.setJMenuBar(menu);

        this.swiat = nowySwiat;
        panelGry = new PanelGry(swiat);
        this.add(panelGry);

        komentator = new Komentator(swiat);
        this.add(komentator);
        komentator.wyswietlKomentarz();

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == zapisz){
            String nazwaPliku = JOptionPane.showInputDialog("Podaj nazwę pliku: ");
            swiat.zapiszSwiat(nazwaPliku);
        }
        if(e.getSource() == wczytaj){
            String nazwaPliku = JOptionPane.showInputDialog("Podaj nazwę pliku: ");
            this.dispose();
            Swiat nowySwiat = swiat.wczytajSwiat(nazwaPliku);
            GUI noweOkno = new GUI(nowySwiat);
            komentator.nowyKomentarz("Wczytano grę");
            komentator.wyswietlKomentarz();
        }
    }
}
