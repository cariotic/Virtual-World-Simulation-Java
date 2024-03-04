package projekt.java;

import javax.swing.*;
import java.awt.*;

public class Komentator extends JPanel {
    private String autor = "autor = Joanna Symczyk\n";
    private Swiat swiat;
    private String komentarz = "";
    private JTextArea oknoTekstowe;

    public Komentator(Swiat swiat){
        super();
        this.swiat = swiat;
        //komentarz = autor;
        oknoTekstowe = new JTextArea(komentarz);
        this.add(oknoTekstowe);

        oknoTekstowe.setLineWrap(true);
        oknoTekstowe.setWrapStyleWord(true);
        oknoTekstowe.setSize(500, 200);
        oknoTekstowe.setMargin(new Insets(5,5,5,5));
        setVisible(true);
    }

    public void wyswietlKomentarz(){
        oknoTekstowe.setText(komentarz);
    }

    public void nowyKomentarz(String nowyKomentarz){
        komentarz += nowyKomentarz + '\n';
    }

    public String getKomentarz(){
        return komentarz;
    }

    public void resetujKomentarz(){
        komentarz = "";
    }
}
