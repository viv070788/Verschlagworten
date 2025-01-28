
package verschlagworten;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.ArrayList;

/**
 * In der Klasse SearchWindow wird die Oberflaeche, mit dem Fenster, mit dem
 * Label, mit dem Textfeld, mit dem Button erstellt und mit dem ActionListener
 * erstellt.
 *
 * @author Viviane Bundle
 * @version 1.0
 */
public class SearchWindow extends JFrame {

    /**
     * Hier befinden sich die dazugehoerigen Variablen und die letzte vorhandene
     * Liste aus Schlagwoertern und Subschlagwoertern.
     */
    JLabel findeLabel;
    JTextField textFeld;
    JButton sucheButton;
    private static List<DatenBankEintrag> letzteListe = new ArrayList<>();
    private List<DatenBankEintrag> eingabeUndSchlagwortListe = new ArrayList<>();

    /**
     * In der Klasse SearchWindow befindet sich die Oberflaeche, mit dem Fenster,
     * mit dem Label, mit dem Textfeld, mit dem Button und mit dem ActionListener.
     */
    public SearchWindow() {
        erstelleFenster();
        erstelleLabel();
        erstelleTextFeld();
        erstelleButton();
        ActionListener listener = erstelleActionListener();
        sucheButton.addActionListener(listener);
        this.setVisible(true);
    }

    /**
     * Mit der Methode erstelleFenster wird das Fenster der Oberflaeche erstellt.
     */
    // Methode wurde mit vorhandenen Daten getestet
    private void erstelleFenster() {
        this.setTitle("search window");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(1000, 1000);
        erstelleFarbeImFenster();
    }

    /**
     * Mit der Methode erstelleFarbeImFenster wird die Farbe des Fensters erstellt.
     */
    // Methode wurde mit vorhandenen Daten getestet
    private void erstelleFarbeImFenster() {
        Color farbe = new Color(152, 255, 152);
        this.getContentPane().setBackground(farbe);
    }

    /**
     * Mit der Methode erstelleLabel wird das Label des Fensters erstellt.
     */
    // Methode wurde mit vorhandenen Daten getestet
    private void erstelleLabel() {
        findeLabel = new JLabel(" Find ");
        findeLabel.setBounds(200, 100, 220, 50);
        findeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        this.add(findeLabel);
    }

    /**
     * Mit der Methode erstelleTextFeld wird das Textfeld des Fensters erstellt.
     */
    // Methode wurde mit vorhandenen Daten getestet
    private void erstelleTextFeld() {
        textFeld = new JTextField("  ", 50);
        textFeld.setBounds(300, 100, 400, 50);
        this.add(textFeld);
    }

    /**
     * Mit der Methode erstelleButton wird der Button des Fensters erstellt.
     */
    // Methode wurde mit vorhandenen Daten getestet
    private void erstelleButton() {
        sucheButton = new JButton(" Q ");
        sucheButton.setBounds(750, 100, 50, 50);
        this.add(sucheButton);
    }

    /**
     * Mit der Methode ueberGabeListe wird die letzte vorhandene Liste bestehend aus
     * Schlagwoertern und Subschlagwoertern in der Klasse DatenLeser.
     * 
     * @param uebergebeneListe
     */
    // Methode wurde mit vorhandenen Daten getestet
    public static void ueberGabeListe(List<DatenBankEintrag> uebergebeneListe) {
        // for (int l = 0; l < uebergebeneListe.size(); l++) {
        // String aktElement = uebergebeneListe.get(l).getWort();
        // String aktZiffer = uebergebeneListe.get(l).getSechsStelligeZiffer();
        // String aktPfad = uebergebeneListe.get(l).getPfadInhalt();
        // DatenBank neueDaten = new DatenBank(aktElement, aktZiffer, aktPfad);
        // vorhandeneLetzteArrayList.add(neueDaten);
        // }
        letzteListe = uebergebeneListe;
        for (DatenBankEintrag ausgeben : letzteListe) {
            // System.out.println(ausgeben);
        }
    }

    /**
     * Mit der Methode getLetzteVorhandeneListe
     * 
     * @return letzteListe
     */
    // Methode wurde mit vorhandenen Daten getestet
    public static List<DatenBankEintrag> getLetzteVorhandeneListe() {
        for (DatenBankEintrag ausgabe : letzteListe) {
            // Button druecken
            // System.out.println(ausgabe);
        }
        return letzteListe;
    }

    /**
     * Mit der Methode erstelleActionListener wird der ActionListener erstellt und
     * durchsucht die im Textfeld eingegebenen Woerter in der letzten vorhandenen
     * Liste bestehend aus Schlagwoertern und Subschlagwoertern nach Schlagwoertern
     * und schreibt alle in eine Liste.
     */
    // Methode wurde mit vorhandenen Daten getestet
    private ActionListener erstelleActionListener() {
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String eingabe = textFeld.getText();
                if (!eingabe.isEmpty()) {
                    if (!eingabe.equals("  ")) {
                        List<DatenBankEintrag> daten = getLetzteVorhandeneListe();
                        for (DatenBankEintrag erhalten : daten) {
                            // System.out.println(erhalten);
                        }
                        for (int i = 0; i < daten.size(); i++) {
                            String aktuellesElement = daten.get(i).getWort();
                            if (eingabe.equals(aktuellesElement)) {
                                String aktuelleSechstelligeZiffer = daten.get(i).getSechsStelligeZiffer();
                                String aktuellerPfad = daten.get(i).getPfadInhalt();
                                DatenBankEintrag einzutrageneDaten = new DatenBankEintrag(aktuellesElement,
                                        aktuelleSechstelligeZiffer, aktuellerPfad);
                                eingabeUndSchlagwortListe.add(einzutrageneDaten);
                            }
                        }
                        // Ausgabe der Liste des Eingabewoertes
                        for (DatenBankEintrag zeigen : eingabeUndSchlagwortListe) {
                            System.out.println(zeigen);
                        }
                    } else {
                        textFeld.setText("Bitte geben Sie ein Wort ohne Leerzeichen ein!");
                    }
                } else {
                    textFeld.setText("Bitte geben Sie ein Wort ohne Leerzeichen ein!");
                }
            }
        };
        return listener;
    }
}
