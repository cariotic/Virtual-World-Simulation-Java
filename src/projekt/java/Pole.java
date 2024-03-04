package projekt.java;

import javax.swing.*;
import java.awt.*;

public class Pole extends JLabel {
    private Color kolor;
    private int x;
    private int y;

    public Pole(){
        setBackground(Color.white);
        setOpaque(true);
    }

    public Pole(int x, int y){
        super();
        setSize(25,25);
        setBackground(Color.white);
        setOpaque(true);
        this.x = x;
        this.y = y;
    }

    public void setKolor(Color kolor){
        this.kolor = kolor;
        this.setBackground(kolor);
    }
}
