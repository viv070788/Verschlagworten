
package verschlagworten;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Die Klasse DatenEinleserImpl implementiert die Klasse DatenEinleser und liest
 * die vorhandenen Daten ein, bereinigt sie mit Kriterien der verschiedenen
 * Methoden und gibt wahr zurueck, sobald alle Kriterien der Methoden zutreffen.
 * Anschließend werden die Schlagwoerter und Subschlagwoerter in eine Liste
 * geschreiben.
 * 
 * @author Viviane Bundle
 * @version 1.0
 */
public class DatenEinleserImpl implements DatenEinleser {

    /**
     * Hier befinden sich statischen, unveraenderlichen Varialen und die Liste, in
     * der die Schlagwoerter und Subschlagwoerter hineingeschrieben werden sollen.
     */
    private static final String TABULATOR = "\t";
    private static final int DREI_EINTRAEGE = 3;
    private static final int ZIFFERN_LAENGE = 6;
    private static final int MAX_ZEILEN_LAENGE = 256;
    private static final int MAX_SCHLAGWORT_LAENGE = 70;
    private static final int SPALTEN_POSITION_ZWEI = 2;
    private static final int MAX_ZIFFERN_LAENGE = 6;
    private static final int SPALTEN_POSITION_DREI = 3;
    private static final int MAX_PFAD_LAENGE = 256;
    private List<DatenBankEintrag> schlagwoerterSubschlagwoerter = new ArrayList<>();

    /**
     * Mit der Methode dateiEinlesen wird der Pfad der Datei eingelesen und die
     * Datei zurueckgegeben.
     * 
     * @param dateiPfad
     * @throws IOException
     * @return daten
     */
    // Methode wurde mit der vorhandenen Datei getestet
    @Override
    public List<DatenBankEintrag> dateiEinlesen(String dateiPfad) throws IOException {
        List<DatenBankEintrag> daten = new ArrayList<>();
        try (BufferedReader dateienEinleser = new BufferedReader(new FileReader(dateiPfad))) {
            zeileEinlesen(daten, dateienEinleser);
        }
        return daten;
    }

    /**
     * Mit der Methode zeileEinlesen werden die Zeilen eingelesen, dann bereinigt
     * und dann in Spalten zerteilt.
     * 
     * @param daten
     * @param dateienEinleser
     * @throws IOException
     */
    // Methode wurde mit der vorhandenen Datei getestet
    private void zeileEinlesen(List<DatenBankEintrag> daten, BufferedReader dateienEinleser) throws IOException {
        String zeile;
        while ((zeile = dateienEinleser.readLine()) != null) {
            bereinigen(zeile);
            zeileInEinzelneEintraege(daten, zeile);
        }
    }

    /**
     * Mit der Methode zeileInEinzelneEintraege werden die einzelnen Zeilen in
     * Spalten zerteilt und in eine Liste eingetragen.
     * 
     * @param daten
     * @param zeile
     */
    // Methode wurde mit den vorhandenen Daten getestet
    private void zeileInEinzelneEintraege(List<DatenBankEintrag> daten, String zeile) {
        if (zeile != null) {
            String[] einzelneDaten = zeile.split(TABULATOR);
            eintragenEinzelneEintraegeInDatenBankEintrag(daten, einzelneDaten);
        }
    }

    /**
     * Mit der Methode eintragenEinzelneEintraegeInDatenBankEintrag werden die Daten
     * in die Liste getan.
     * 
     * @param daten
     * @param einzelneDaten
     */
    // Methode wurde mit vorhandenen Daten getestet
    private void eintragenEinzelneEintraegeInDatenBankEintrag(List<DatenBankEintrag> daten, String[] einzelneDaten) {
        if (einzelneDaten.length == DREI_EINTRAEGE) {
            DatenBankEintrag datenEintragen = new DatenBankEintrag(einzelneDaten[0], einzelneDaten[1],
                    einzelneDaten[2]);
            daten.add(datenEintragen);
        }
    }

    /**
     * Mit der Methode getSchlagwoerterSubschlagwoerter wird die Liste an
     * Schlagwoertern und Subschlagwoertern zurueckgegeben.
     * 
     * @return schlagwoerterSubschlagwoerter
     */
    // Methode wurde mit vorhandenen Daten getestet
    @Override
    public List<DatenBankEintrag> getSchlagwoerterSubschlagwoerter() {
        return schlagwoerterSubschlagwoerter;
    }

    /**
     * Mit der Methode ueberpruefeZeilenLaenge wird die Laenge der Zeile
     * ueberprueft.
     * 
     * @param zeile
     * @return boolean
     */
    // todo
    // Methode wurde getestet
    // test if validation run if the words are smaller equal 256 -> ok
    // test if validation fails if the words are larger 256 -> ok
    private boolean ueberpruefeZeilenLaenge(String zeile) {
        int zeilenLaenge = zeile.length();
        if (zeilenLaenge > MAX_ZEILEN_LAENGE) {
            return false;
        }
        return true;
    }

    /**
     * Mit der Methode ueberpruefeObAnzahlDerTabsGleichZwei wird ueberprueft, ob
     * genau zwei Tabs vorhanden sind.
     * 
     * @param zeile
     * @return boolean
     */
    // Methode wurde getestet
    // test if validation fails if non tab is there -> ok
    // test if validation fails if one tab is there -> ok
    // text if validation run if two tabs are there -> ok
    // test if validation fails if three tabs are there -> ok
    // Nur auf tabs validieren nicht auf leerzeichen
    private boolean ueberpruefeObAnzahlDerTabsGleichZwei(String zeile) {
        String[] stringTeiler = zeile.split(TABULATOR);
        if ((stringTeiler.length - 1) == 2) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Mit der Methode ueberpruefeSchlagWortLaenge wird ueberprueft, ob die
     * Schlagwortlaenge passt.
     * 
     * @param zeile
     * @return boolean
     */
    // Methode wurde getestet
    // test if validation fails if words of keyword are longer than 70 -> ok
    // test if validation runs if words of keyword are smaller equal 70 -> ok
    // Ueberprueft, ob Schlagwortlaenge maximal 70 Zeichen hat
    private boolean ueberpruefeSchlagWortLaenge(String zeile) {
        int schlagWortLaenge;
        String schlagWort;
        schlagWort = zeile.split(TABULATOR)[0];
        schlagWortLaenge = schlagWort.length();
        if (schlagWortLaenge <= MAX_SCHLAGWORT_LAENGE) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Mit der Methode erstelleZweiteSpalteUndUeberprueftZiffernLaenge wird die
     * zweite Spalte erstellt und die Ziffernlaenge ueberprueft.
     * 
     * @param zeile
     * @return ueberpruefeZiffernLaenge(zifferLaenge)
     * @return false
     */
    // Methode wurde getestet, siehe Methode ueberpruefeZiffernLanege
    private boolean erstelleZweiteSpalteUndUeberprueftZiffernLaenge(String zeile) {
        int spaltenLaenge;
        String[] spaltenSplitter;
        String ziffer;
        int zifferLaenge;
        spaltenSplitter = zeile.split(TABULATOR);
        spaltenLaenge = spaltenSplitter.length;
        if (spaltenLaenge >= SPALTEN_POSITION_ZWEI) {
            ziffer = spaltenSplitter[1];
            zifferLaenge = ziffer.length();
            return ueberpruefeZiffernLaenge(zifferLaenge);
        } else {
            return false;
        }
    }

    /**
     * Mit der Methode ueberpruefeZiffernLaenge wird die Ziffernlaenge ueberprueft.
     * 
     * @param zifferLaenge
     * @return boolean
     */
    // Methode wurde getestet
    // test if validation fails if the length of digits are 5 -> ok
    // test if validation runs if the length of digits are 6 -> ok
    // test if validation fails if the length of digits are 7 -> ok
    // Ueberprueft, ob die Zahl in der mittleren Spalte 6 Zeichen hat
    private boolean ueberpruefeZiffernLaenge(int zifferLaenge) {
        if (zifferLaenge == MAX_ZIFFERN_LAENGE) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Mit der Methode erstelleDritteSpalteUndUeberpruefePfadLaenge wird die dritte
     * Spalte erstellt und die PfadLaenge ueberprueft.
     * 
     * @param zeile
     * @return ueberpruefePfadLaenge(pfadLaenge)
     * @return false
     */
    // Methode wurde getestet, siehe Methode ueberpruefePfadLaenge
    private boolean erstelleDritteSpalteUndUeberprueftPfadLaenge(String zeile) {
        int spaltenLaenge;
        String[] spaltenSplitter;
        String pfad;
        int pfadLaenge;
        spaltenSplitter = zeile.split(TABULATOR);
        spaltenLaenge = spaltenSplitter.length;
        if (spaltenLaenge >= SPALTEN_POSITION_DREI) {
            pfad = spaltenSplitter[2];
            pfadLaenge = pfad.length();
            return ueberpruefePfadLaenge(pfadLaenge);
        } else {
            return false;
        }
    }

    /**
     * Mit der Methode ueberpruefePfadLaenge wird die Pfadlaenge ueberprueft.
     * 
     * @param pfadLaenge
     * @return boolean
     */
    // Methode wurde getestet
    // test if validation fails if words of path are longer than 256 -> ok
    // test if validation runs if words of path are smaller equal 256 -> ok
    // Ueberprueft, ob die Pfadlaenge in der dritten Spalte nicht mehr als 256
    // Zeichen hat
    private boolean ueberpruefePfadLaenge(int pfadLaenge) {
        if (pfadLaenge <= MAX_PFAD_LAENGE) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Mit der Methode erstelleZweiteSpalteUndUeberpruefeZiffernKorrektheit wird die
     * zweite Spalte erstellt und ueberprueft, ob Ziffern vorhanden sind.
     * 
     * @param zeile
     * @return ueberPruefeZiffernKorrektheit(ziffer, zifferLaenge)
     * @return false
     */
    // Methode wurde getestet, siehe Methode ueberPruefeZiffernKorrektheit
    private boolean erstelleZweiteSpalteUndRueckgabeZiffernKorrektheit(String zeile) {
        int spaltenLaenge;
        String[] spaltenSplitter;
        String ziffer;
        int zifferLaenge;
        spaltenSplitter = zeile.split(TABULATOR);
        spaltenLaenge = spaltenSplitter.length;
        if ((spaltenLaenge >= SPALTEN_POSITION_ZWEI)) {
            ziffer = spaltenSplitter[1];
            zifferLaenge = ziffer.length();
            return ueberPruefeZiffernKorrektheit(ziffer, zifferLaenge);
        } else {
            return false;
        }
    }

    /**
     * Mit der Methode ueberPruefeZiffernKorrektheit wird ueberprueft, ob die
     * Ziffern (sechsstellig) vorhanden sind.
     * 
     * @param ziffer
     * @param zifferLaenge
     * @return boolean
     */
    // Methode wurde getestet, siehe Methode ueberPruefeZiffernKorrektheit
    private boolean ueberPruefeZiffernKorrektheit(String ziffer, int zifferLaenge) {
        if (zifferLaenge == ZIFFERN_LAENGE) {
            char ersteZiffer = ziffer.charAt(0);
            char zweiteZiffer = ziffer.charAt(1);
            char dritteZiffer = ziffer.charAt(2);
            char vierteZiffer = ziffer.charAt(3);
            char fuenfteZiffer = ziffer.charAt(4);
            char sechsteZiffer = ziffer.charAt(5);
            if (testeObZifferVorhanden(ersteZiffer, zweiteZiffer, dritteZiffer, vierteZiffer, fuenfteZiffer,
                    sechsteZiffer)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Mit der Methode testeObZifferVorhanden wird ueberprueft, ob Ziffern vorhanden
     * sind.
     * 
     * @param ZifferEins
     * @param ZifferZwei
     * @param ZifferDrei
     * @param ZifferVier
     * @param ZifferFuenf
     * @param ZifferSechs
     */
    // The Character.isDigit() method in Java is used to determine if a given
    // character is a digit
    // This method is useful when you need to check whether characters in a string
    // or stream are numeric digits (0-9)

    // Methode wurde getestet. test if validation fails if the first digit of the
    // six digits is a char (NOT a digit) and a special character (Sonderzeichen) ->
    // ok
    // test if validation fails if the second digit of the six digits is a char (NOT
    // a digit) and a special character (Sonderzeichen) -> ok
    // test if validation fails if the third digit of the six digits is a char (NOT
    // a digit) and a special character (Sonderzeichen) -> ok
    // test if validation fails if the forth digit of the six digits is a char (NOT
    // a digit) and a special character (Sonderzeichen) -> ok
    // test if validation fails if the fifth digit of the six digits is a char (NOT
    // a digit) and a special character (Sonderzeichen) -> ok
    // test if validation fails if the sixth digit of the six digits is a char (NOT
    // a digit) and a special character (Sonderzeichen) -> ok

    // test if validation fails if the first digit of the five digits is a char (NOT
    // a digit) and a special character (Sonderzeichen) -> ok
    // test if validation fails if the second digit of the five digits is a char
    // (NOT a digit) and a special character (Sonderzeichen) -> ok
    // test if validation fails if the third digit of the five digits is a char (NOT
    // a digit) and a special character (Sonderzeichen) -> ok
    // test if validation fails if the forth digit of the five digits is a char (NOT
    // a digit) and a special character (Sonderzeichen) -> ok
    // test if validation fails if the fifth digit of the five digits is a char (NOT
    // a digit) and a special character (Sonderzeichen) -> ok

    // test if validation fails if the first digit of the seven digits is a char
    // (NOT a digit) and a special character (Sonderzeichen) -> ok
    // test if validation fails if the second digit of the seven digits is a char
    // (NOT a digit) and a special character (Sonderzeichen) -> ok
    // test if validation fails if the third digit of the seven digits is a char
    // (NOT a digit) and a special character (Sonderzeichen) -> ok
    // test if validation fails if the forth digit of the seven digits is a char
    // (NOT a digit) and a special character (Sonderzeichen) -> ok
    // test if validation fails if the fifth digit of the seven digits is a char
    // (NOT a digit) and a special character (Sonderzeichen) -> ok
    // test if validation fails if the sixth digit of the seven digits is a char
    // (NOT a digit) and a special character (Sonderzeichen) -> ok
    // test if validation fails if the seventh digit of the seven digits is a char
    // (NOT a digit) and a special character (Sonderzeichen) -> ok

    // test if validation runs if the first digit of the six digits is a digit ( 1
    // to 9 ) -> ok
    // test if validation runs if the second digit of the six digits is a digit ( 1
    // to 9 ) -> ok
    // test if validation runs if the third digit of the six digits is a digit ( 1
    // to 9 ) -> ok
    // test if validation runs if the forth digit of the six digits is a digit ( 1
    // to 9 ) -> ok
    // test if validation runs if the fifth digit of the six digits is a digit ( 1
    // to 9 ) -> ok
    // test if validation runs if the sixth digit of the six digits is a digit ( 0
    // to 9 without 2 ) -> ok

    // test if validation fails if the first digit of the five digits is a digit ( 1
    // to 9 ) -> ok
    // test if validation fails if the second digit of the five digits is a digit (
    // 1 to 9 ) -> ok
    // test if validation fails if the third digit of the five digits is a digit ( 1
    // to 9 ) -> ok
    // test if validation fails if the forth digit of the five digits is a digit ( 1
    // to 9 ) -> ok
    // test if validation fails if the fifth digit of the five digits is a digit ( 0
    // to 9 without 2 ) -> ok

    // test if validation fails if the first digit of the seven digits is a digit (
    // 1 to 9 ) -> ok
    // test if validation fails if the second digit of the seven digits is a digit (
    // 1 to 9 ) -> ok
    // test if validation fails if the third digit of the seven digits is a digit (
    // 1 to 9 ) -> ok
    // test if validation fails if the forth digit of the seven digits is a digit (
    // 1 to 9 ) -> ok
    // test if validation fails if the fifth digit of the seven digits is a digit (
    // 1 to 9 ) -> ok
    // test if validation fails if the sixth digit of the seven digits is a digit (
    // 1 to 9 ) -> ok
    // test if validation fails if the seventh digit of the seven digits is a digit
    // ( 0 to 9 without 2 ) -> ok
    // Ueberpruft, ob die Ziffern von 0 bis 9 korrekt sind
    private boolean testeObZifferVorhanden(char ZifferEins, char ZifferZwei, char ZifferDrei, char ZifferVier,
            char ZifferFuenf, char ZifferSechs) {
        return Character.isDigit(ZifferEins) && Character.isDigit(ZifferZwei) && Character.isDigit(ZifferDrei)
                && Character.isDigit(ZifferVier) && Character.isDigit(ZifferFuenf) && Character.isDigit(ZifferSechs);
    }

    /**
     * Mit der Methode testeObPfadKorrekt wird ueberprueft, ob der Pfad stimmt.
     * 
     * @param pfad
     * @return boolean
     */
    // Methode wurde getestet
    // test if validation fails if words of path have not "/", "." -> ok
    // test if validation fails if words of path have only one of this things: "/",
    // "." -> ok
    // test if validation fails if words of path have everything inside beside of
    // one: "." -> ok
    // test if validation fails if words of path have only one slash "/" -> ok
    // test if validation runs if words of path have "/", "." -> ok
    // Ueberpruft, ob nur Zeichen enthalten sind, die in diesem Pfad drinnen sein
    // duerfen
    private boolean testeObPfadKorrekt(String pfad) {
        return pfad.contains("/") && pfad.contains(".") && pfad.matches("^(?!.*//).*$");
    }

    /**
     * Mit der Methode erstelleDritteSpalteUndUeberpruefePfadKorrektheit wird die
     * dritte Spalte erstellt und uebrprueft, ob der Pfad stimmt.
     * 
     * @param zeile
     * @return boolean
     */
    // Methode wurde gestestet, siehe Methode testeObPfadKorrekt
    private boolean erstelleDritteSpalteUndTesteObPfadKorrekt(String zeile) {
        int spaltenLaenge;
        String[] spaltenSplitter;
        String pfad;
        spaltenSplitter = zeile.split(TABULATOR);
        spaltenLaenge = spaltenSplitter.length;
        if (spaltenLaenge >= SPALTEN_POSITION_DREI) {
            pfad = spaltenSplitter[2];
            if (testeObPfadKorrekt(pfad)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Mit der Methode testeObZahlOderBuchstabe wird ueberprueft, ob ein Buchstabe
     * oder eine Zahl vorhanden ist.
     * 
     * @param wort
     * @param position
     * @return boolean
     */
    // Methode wurde getestet, siehe Methode ueberpruefeSchlagWortInhalt
    private boolean testeObZahlOderBuchstabe(String wort, int position) {
        return wort.charAt(position) >= '0' && wort.charAt(position) <= '9'
                || wort.charAt(position) >= 'A' && wort.charAt(position) <= 'Z'
                || wort.charAt(position) >= 'a' && wort.charAt(position) <= 'z';
    }

    /**
     * Mit der Methode ueberpruefeSchlagWortInhalt werden Schlagwoerter und
     * Subschlagwoerter erzeugt, indem man die Sonderzeichen entfernt.
     * 
     * @param zeile
     * @return true
     */
    // Methode wurde mit vorhandenen Daten getestet.
    // Ueberprueft, ob erlaubte Zeichen in dem Schlagwort enthalten
    // sind in der ersten Spalte mit Schlagworten.
    private boolean ueberpruefeSchlagWortInhalt(String zeile) {
        String schlagWort;
        int zeilenSpalte;
        String zeileRueckGabe = "";
        String[] schlagWoerter = new String[12];
        int position; // Positionszeiger im Schlagwortstring kWi;
        int teilSchlagWort; // Teilschlagwort z.B. IGNORE im Schlagwortstring
        boolean inSubSchlagWort;
        int schlagWortLaenge; // Laenge des Schlagwortes
        inSubSchlagWort = false;
        position = 0; // kWi = 0;
        teilSchlagWort = 0;

        String[] zeileDurchTabsGeteilt = zeile.split(TABULATOR);
        schlagWort = zeileDurchTabsGeteilt[0];
        schlagWortLaenge = schlagWort.length(); // laenge des Schlagwortes
        zeilenSpalte = zeileDurchTabsGeteilt.length;

        schlagWoerterInitialisieren(schlagWoerter);

        // filtert die Subschlagwoerter aus den Schlagwoertern heraus
        while (position < schlagWortLaenge) {
            if (testeObZahlOderBuchstabe(schlagWort, position)) {
                inSubSchlagWort = true;
                schlagWoerter[teilSchlagWort] = schlagWoerter[teilSchlagWort] + schlagWort.charAt(position);
            } else {
                if (inSubSchlagWort) {
                    schreibtZifferHinterSchlagWort(zeilenSpalte, schlagWoerter, teilSchlagWort, zeileDurchTabsGeteilt);
                    schreibtPfadHinterSchlagWort(zeilenSpalte, schlagWoerter, teilSchlagWort, zeileDurchTabsGeteilt);
                    teilSchlagWort++; // Erhoehung der Position des Indexes 0 bis 9 im Array
                    schlagWoerter[teilSchlagWort] = "";
                    inSubSchlagWort = false;
                }
            }
            position++;
        }

        schreibtZifferUndPfadHinterSchlagWort(zeilenSpalte, schlagWoerter, position, teilSchlagWort, inSubSchlagWort,
                schlagWortLaenge, zeileDurchTabsGeteilt);

        schreibtSchlagWortUndSubSchlagWortInListe(schlagWoerter, teilSchlagWort);

        return true;

    }

    /**
     * Mit der Methode schlagWoerterInitialisieren wird die Liste initialisiert.
     * 
     * @param schlagWoerter
     */
    // Methode wurde mit vorhandenen Daten getestet
    private void schlagWoerterInitialisieren(String[] schlagWoerter) {
        for (int i = 0; i < schlagWoerter.length; i++) {
            schlagWoerter[i] = "";
        }
    }

    /**
     * Mit der Methode schreibtZifferHinterSchlagWort wird die sechsstellige Ziffer
     * hinter jedes Schlagwort und hinter jedes Subschlagwort geschrieben.
     * 
     * @param zeilenSpalte
     * @param schlagWoerter
     * @param teilSchlagWort
     * @param zeileDurchTabsGeteilt
     */
    // Methode wurde mit vorhandenen Daten getestet. Hier kommt 6-stellige Ziffer
    // hinein hinter jedes Subschlagwort
    private void schreibtZifferHinterSchlagWort(int zeilenSpalte, String[] schlagWoerter, int teilSchlagWort,
            String[] zeileDurchTabsGeteilt) {
        String ziffer;
        if (zeilenSpalte >= SPALTEN_POSITION_ZWEI) {
            ziffer = zeileDurchTabsGeteilt[1];
            schlagWoerter[teilSchlagWort] = schlagWoerter[teilSchlagWort] + " " + ziffer + " ";
        }
    }

    /**
     * Mit der Methode schreibtPfadHinterSchlagWort wird der Pfad hinter jedes
     * Schlagwort und hinter jedes Subschlagwort geschrieben.
     * 
     * @param zeilenSpalte
     * @param schlagWoerter
     * @param teilSchlagWort
     * @param zeileDurchTabsGeteilt
     */
    // Methode wurde mit vorhandenen Daten getestet
    // Hier kommt der Pfad hinein hinter jedes Subschlagwort
    private void schreibtPfadHinterSchlagWort(int zeilenSpalte, String[] schlagWoerter, int teilSchlagWort,
            String[] zeileDurchTabsGeteilt) {
        String pfad;
        if (zeilenSpalte >= SPALTEN_POSITION_DREI) {
            pfad = zeileDurchTabsGeteilt[2];
            schlagWoerter[teilSchlagWort] = schlagWoerter[teilSchlagWort] + pfad;
        }
    }

    /**
     * Mit der Methode schreibtZifferUndPfadHinterSchlagWort werden die
     * sechsstellige Ziffer und der Pfad hinter jedes Schlagwort und hinter jedes
     * Subschlagwort geschrieben, falls ein Schlagwort bzw. ein Subschlagword
     * vorhanden ist.
     * 
     * @param zeilenSpalte
     * @param schlagWoerter
     * @param position
     * @param teilSchlagWort
     * @param inSubSchlagWort
     * @param schlagWortLaenge
     * @param zeileDurchTabsGeteilt
     */
    // Methde wurde mit vorhandenen Daten getestet
    // Schreibt die 6-stellige Ziffer und den Pfad hinter jedes vorkommende
    // Schlagwort
    private void schreibtZifferUndPfadHinterSchlagWort(int zeilenSpalte, String[] schlagWoerter, int position,
            int teilSchlagWort, boolean inSubSchlagWort, int schlagWortLaenge, String[] zeileDurchTabsGeteilt) {
        if ((position == schlagWortLaenge) && (inSubSchlagWort)) {
            schreibtZifferHinterSchlagWort(zeilenSpalte, schlagWoerter, teilSchlagWort, zeileDurchTabsGeteilt);
            schreibtPfadHinterSchlagWort(zeilenSpalte, schlagWoerter, teilSchlagWort, zeileDurchTabsGeteilt);
        }
    }

    /**
     * Mit der Methode schreibtSchlagWortUndSubSchlagWortInListe werden die
     * Schlagwoerter und Subschlagwoerter in Liste geschrieben.
     * 
     * Methode wurde mit vorhandenen Daten getestet. Schreibt alle Schlagwoerter und
     * Subschlagwoerter in die ArrayList "schlagwoerterSubschlagwoerter".
     * 
     * @param schlagWoerter
     * @param teilSchlagWort
     */
    // Methode wurde mit vorhandenen Daten getestet.
    // Schreibt alle Schlagwoerter und Subschlagwoerter in die Liste
    // "schlagwoerterSubschlagwoerter"
    private void schreibtSchlagWortUndSubSchlagWortInListe(String[] schlagWoerter, int teilSchlagWort) {
        String zeilenRueckGabe = "";
        for (int i = 0; i <= teilSchlagWort; i++) {
            int len = schlagWoerter[i].length();
            if (len > 1) {
                zeilenRueckGabe = schlagWoerter[i];
                zerteilungInEinzelneEintraege(zeilenRueckGabe);
            }
        }
    }

    /**
     * Mit der Methode zerteilungInEinzelneEintraege werden die Zeilen in drei
     * Spalten zerteilt.
     * 
     * @param zeilenRueckGabe
     */
    // Methode wurde mit vorhandenen Daten getestet
    private void zerteilungInEinzelneEintraege(String zeilenRueckGabe) {
        String[] spaltenTeilung = zeilenRueckGabe.split(" ");
        eintragenVonDatenInDatenDankEintrag(spaltenTeilung);
    }

    /**
     * Mit der Methode eintragenVonDatenInDatenBankEintrag werden die drei Spalten
     * der Zeilen jeweils in die Liste eingetragen.
     * 
     * @param eintrag
     */
    // Methode wurde mit vorhandenen Daten getestet
    private void eintragenVonDatenInDatenDankEintrag(String[] eintrag) {
        if (eintrag.length == DREI_EINTRAEGE) {
            DatenBankEintrag rueckGabeInDatenBank = new DatenBankEintrag(eintrag[0], eintrag[1], eintrag[2]);
            schlagwoerterSubschlagwoerter.add(rueckGabeInDatenBank);
        }
    }

    /**
     * Mit der Methode bereinigen werden die einzelnen Zeilen einer Ueberpruefung
     * durch verschiedene Methoden unterzogen.
     * 
     * @param zeile
     * @return boolean
     */
    // Methode wurde mit vorhandenen Daten getestet
    @Override
    public boolean bereinigen(String zeile) {
        boolean rueck = false;
        if ((ueberpruefeZeilenLaenge(zeile)) && (ueberpruefeObAnzahlDerTabsGleichZwei(zeile))
                && (ueberpruefeSchlagWortLaenge(zeile)) && (erstelleZweiteSpalteUndUeberprueftZiffernLaenge(zeile))
                && (erstelleDritteSpalteUndUeberprueftPfadLaenge(zeile))
                && (erstelleZweiteSpalteUndRueckgabeZiffernKorrektheit(zeile))
                && (erstelleDritteSpalteUndTesteObPfadKorrekt(zeile)) && (ueberpruefeSchlagWortInhalt(zeile)))
            rueck = true;
        return rueck;
    }
}
