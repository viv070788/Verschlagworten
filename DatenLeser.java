
package verschlagworten;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

/**
 * Die Klasse DatenLeser liest die vorhandenen Daten ein, bereinigt sie mit
 * Hilfe der Kriterien der verschiedenen Methoden. Dann werden die Schlagwoerter
 * und Subschlagwoerter in eine Liste geschrieben und anschließend werden die
 * sechsstelligen Ziffern der gleichen Schlagwoerter aufaddiert. Daraufhin wird
 * bestimmt, welche Schlagwoerter wirkliche Schlagwoerter sind und die
 * Nicht-Schlagwoertern aus der Liste geloescht. Folglich werden alle wirklichen
 * Schlagwoerter angezeigt. Mit Hilfe einer Oberflaeche lassen sich die
 * Schlagwoerter suchen. Bitte geben Sie den jeweiligen Pfad an, indem Sie die
 * Datei "schlagsahneRaw.dat" abgespeichert haben ( ist im Quellcode markier).
 *
 * @author Viviane Bundle
 * @version 1.0
 */
public class DatenLeser {

    /**
     * Die main Methode macht mehrere Aufgaben und falls Probleme beim Einlesen
     * auftreten, wird eine Exception ausgeloest. Die Aufgaben umfassen folgende
     * Punkte, zum ersten Ausgeben der eingelesenen Daten. Zum zweiten werden die
     * bereinigten Daten mit Erstellung der Schlagwoerter und Subschlagwoerter
     * ausgegeben. Zum dritten werden die sechsstelligen Ziffern der gleichen
     * Schlagwoerter aufaddiert. Zum vierten werden die markierten
     * Nicht-Schlagwoerter und Schlagwoerter ausgegeben. Zum fuenften werden die
     * Nicht-Schlagwoerter geloescht und alle Schlagwoerter angegeben. Anschließend
     * wird die Oberflaeche, zum Eingeben der Schlagwoerter, erstellt. Bitte geben
     * Sie den jeweiligen Pfad an, indem Sie die Datei "schlagsahneRaw.dat"
     * abgespeichert haben (ist im Quellcode markiert).
     */
    // Methode wurde mit vorhandenen Daten getestet
    public static void main(String[] args) {
        DatenEinleserImpl einLeser = new DatenEinleserImpl();
        // BITTE GEBEN SIE HIER DEN PFAD AN, INDEM SIE DIE DATEI "schlagsahneRaw.dat"
        // ABGESPEICHERT HABEN
        String dateiPfad = "/home/viviane/kurs1618/workspace/ProjektVerschlagworten/src/verschlagworten/Verschlagwortung.dat";
        // String dateiPfad = "/home/viviane/Dokumente/keinTab.txt";
        try {

            /**
             * Die vorhandenen Daten werden in die Liste eingetragen, durch Kriterien der
             * verschiedenen Methoden bereinigt und ausgegeben.
             */
            List<DatenBankEintrag> einlesenUndBereinigenDerDaten = einLeser.dateiEinlesen(dateiPfad);
            for (DatenBankEintrag eingeleseneUndBereinigteDaten : einlesenUndBereinigenDerDaten) {
                // System.out.println(eingeleseneUndBereinigteDaten);
            }

            /**
             * Die eingelesenen Daten werden mit Kriterien der verschiedenen Methoden
             * ueberprueft und die Schlagwoerter und Subschlagwoerter werden mit
             * sechsstelltiger Ziffer und Pfad ausgegeben.
             */
            List<DatenBankEintrag> zurueckGegegebeneDaten = einLeser.getSchlagwoerterSubschlagwoerter();
            for (DatenBankEintrag zurueckDaten : zurueckGegegebeneDaten) {
                // System.out.println(zurueckDaten);
            }

            /**
             * Die Ziffern der gleichen Schlagwoerter werden aufaddiert und ausgegeben.
             */
            List<DatenBankEintrag> datenAuflisten = aufgelisteteListe(zurueckGegegebeneDaten);
            for (DatenBankEintrag datenAuflistung : datenAuflisten) {
                // System.out.println(datenAuflistung);
            }

            /**
             * Jetzt werden alle Nicht-Schlagwoerter markiert und ausgegeben, sowie alle
             * Schlagwoerter ausgegeben.
             */
            List<DatenBankEintrag> obSchlagWort = obSchlagWortListe(datenAuflisten);
            for (DatenBankEintrag datenObSchlagWort : obSchlagWort) {
                // System.out.println(datenObSchlagWort);
            }

            /**
             * Alle Nicht-Schlagwoerter werden aus der Liste entfernt und nur noch die
             * Schlagwoerter werden angezeigt.
             */
            List<DatenBankEintrag> nichtSchlagWortLoeschen = alleSchlagWoerterZeigen(obSchlagWort,
                    zurueckGegegebeneDaten);
            for (DatenBankEintrag istSchlagWort : nichtSchlagWortLoeschen) {
                System.out.println(istSchlagWort);
            }

            /**
             * Die Oberflaeche, um die Schlagwoerter einzugeben, wird erstellt.
             */
            JFrame suchMaschine = new SearchWindow();

            /**
             * 
             */
            SearchWindow.ueberGabeListe(nichtSchlagWortLoeschen);

            /**
             * Exception wird ausgeloest, falls Probleme beim Einlesen der Datei auftauchen.
             * 
             * @param IOException
             */
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Mit der Methode aufgelisteteArrayList wird als erstes eine zweite Liste
     * erstellt. Dann werden die sechsstellige Ziffer bei gleichen Schlagwoertern
     * aufaddiert. Falls das Schlagwort nur einmal vorkommt, wird nur diese
     * sechsstellige Ziffer genommen. Anschließend wird die aufaddierte Liste
     * zurueckgegeben.
     * 
     * @param datenAufListen
     * @return obSchlagWortInListeSchreiben
     */
    // Methode wurde mit vorhandenen Daten getestet
    public static List<DatenBankEintrag> aufgelisteteListe(List<DatenBankEintrag> datenAufListen) {
        ArrayList<DatenBankEintrag> backUpListe = new ArrayList<>();
        int indexZaehler[] = new int[500];
        ArrayList<DatenBankEintrag> obSchlagWortInListeSchreiben = new ArrayList<>(); // Schlagwoerter in Liste
                                                                                      // schreiben

        erstelleBackUpListeAusDenDaten(datenAufListen, backUpListe);
        addiereZiffernGleicherElementeHinzu(backUpListe, indexZaehler, obSchlagWortInListeSchreiben);

        return obSchlagWortInListeSchreiben;
    }

    /**
     * Mit der Methode erstelleBackUpListeAusDenDaten wird eine zweite Liste
     * erstellt aus der Liste mit Schlagwoertern (und Subschlagwoertern).
     *
     * @param datenAufListen
     * @param backUpListe
     */
    // Methode wurde mit vorhandenen Daten getestet
    private static void erstelleBackUpListeAusDenDaten(List<DatenBankEintrag> datenAufListen,
            ArrayList<DatenBankEintrag> backUpListe) {
        String schlagWort;
        String ziffer;
        String pfad;
        for (int i = 0; i < datenAufListen.size(); i++) {
            // backUpListe (zweite gleiche Liste) der ArrayListe "datenAufListen".
            schlagWort = datenAufListen.get(i).getWort();
            ziffer = datenAufListen.get(i).getSechsStelligeZiffer();
            pfad = datenAufListen.get(i).getPfadInhalt();
            DatenBankEintrag backUpDaten = new DatenBankEintrag(schlagWort, ziffer, pfad);
            backUpListe.add(backUpDaten);
        }
    }

    /**
     * Mit der Methode addiereZiffernGleicherElementeHinzu wird die sechsstellige
     * Ziffer eines jeden Schlagwortes aufaddiert und bei gleichen Schlagwoertern
     * wieder aufaddiert. Außerdem wird eine Umwandlung in einen String bei einem
     * Schlagwort vollzogen.
     *
     * @param backUpListe
     * @param indexZaehler
     * @param obSchlagWortInListeSchreiben
     */
    // Methode wurde mit vorhandenen Daten getestet
    private static void addiereZiffernGleicherElementeHinzu(ArrayList<DatenBankEintrag> backUpListe, int[] indexZaehler,
            ArrayList<DatenBankEintrag> obSchlagWortInListeSchreiben) {
        String aktuellesElement;
        String aktuelleZiffer;
        int zahlAktuelleZiffer;
        for (int i = 0; i < backUpListe.size(); i++) {
            int temp = 0;
            int indexCounter = 0;
            int counter = 0;
            // addiert die erste Ziffer des Elementes auf
            aktuellesElement = backUpListe.get(i).getWort();
            aktuelleZiffer = backUpListe.get(i).getSechsStelligeZiffer();
            zahlAktuelleZiffer = Integer.parseInt(aktuelleZiffer);
            temp = temp + zahlAktuelleZiffer;
            // falls das Element nur einmal in der backUpArrayListe vorkommt
            if (i == (backUpListe.size() - 1)) {
                bisherigVorkommendeElementeInIntegerUmwandeln(backUpListe, obSchlagWortInListeSchreiben,
                        aktuellesElement, temp, i);
            } else {
                schautObElementeNochmalInDerBackUpListeVorkommenUndAddiertZifferHinzu(backUpListe, indexZaehler,
                        obSchlagWortInListeSchreiben, aktuellesElement, i, temp, indexCounter, counter);
            }
        }
    }

    /**
     * Mit der Methode bisherigVorkommendeElementeInIntegerUmwandeln wird die
     * sechsstellige Ziffer der Elemente in einen String umgewandelt.
     *
     * @param backUpListe
     * @param obSchlagWortInListeSchreiben
     * @param aktuellesElement
     * @param temp
     * @param i
     */
    // Methode wurde mit vorhandenen Daten getestet
    private static void bisherigVorkommendeElementeInIntegerUmwandeln(ArrayList<DatenBankEintrag> backUpListe,
            ArrayList<DatenBankEintrag> obSchlagWortInListeSchreiben, String aktuellesElement, int temp, int i) {
        if (!aktuellesElement.equals(backUpListe.get(i).getSechsStelligeZiffer())) {
            String tempUmwandeln;
            tempUmwandeln = Integer.toString(temp);
            bisherVorkommendeElementInObSchlagWortInListeSchreibenSchreiben(obSchlagWortInListeSchreiben,
                    aktuellesElement, tempUmwandeln);
        }
    }

    /**
     * Mit der Methode
     * bisherVorkommendeElemementInObSchlagWortInListeSchreibenSchreiben werden
     * jeweils die Schlagwoerter und die sechsstelligen Ziffern in eine Liste
     * geschrieben.
     *
     * @param obSchlagWortInListeSchreiben
     * @param aktuellesElement
     * @param tempUmwandeln
     */
    // Methode wurde mit vorhandenen Daten getestet
    private static void bisherVorkommendeElementInObSchlagWortInListeSchreibenSchreiben(
            ArrayList<DatenBankEintrag> obSchlagWortInListeSchreiben, String aktuellesElement, String tempUmwandeln) {
        // Element in Liste "obSchlagWortInListeSchreiben" legen
        DatenBankEintrag neueDaten = new DatenBankEintrag(aktuellesElement, tempUmwandeln, "");
        obSchlagWortInListeSchreiben.add(neueDaten);
    }

    /**
     * Mit der Methode
     * schautObElementeNochmalInDerBackUpListeVorkommenUndAddiertZifferHinzu werden
     * die sechsstelligen Ziffern der gleichen Schlagwoerter aufaddiert. Alle
     * nochmaligen, sogenannten Schlagwoerter werden geloescht und alles auf Null
     * gesetzt. Die sechsstelligen Ziffern werden in Strings umgewandelt.
     *
     * @param backUpListe
     * @param indexZaehler
     * @param obSchlagWortInListeSchreiben
     * @param aktuellesElement
     * @param i
     * @param temp
     * @param indexCounter
     * @param counter
     */
    // Methode wurde mit vorhandenen Daten getestet
    private static void schautObElementeNochmalInDerBackUpListeVorkommenUndAddiertZifferHinzu(
            ArrayList<DatenBankEintrag> backUpListe, int[] indexZaehler,
            ArrayList<DatenBankEintrag> obSchlagWortInListeSchreiben, String aktuellesElement, int i, int temp,
            int indexCounter, int counter) {
        String weiteresElement;
        String weitereZiffer;
        int zahlWeitereZiffer;
        for (int j = i + 1; j < backUpListe.size(); j++) {
            // schaut ob Element j in der Mitte der Liste ist
            if (j != (backUpListe.size() - 1)) {
                // Addition temp = temp + zahlweiteresElement, wenn beide Elemente gleich sind
                if (aktuellesElement.equals(backUpListe.get(j).getWort())) {
                    weiteresElement = backUpListe.get(j).getWort();
                    weitereZiffer = backUpListe.get(j).getSechsStelligeZiffer();
                    zahlWeitereZiffer = Integer.parseInt(weitereZiffer);
                    temp = temp + zahlWeitereZiffer;
                    indexZaehler[indexCounter] = j;
                    indexCounter++;
                }
            } else {
                // Element j ist jetzt das letzte Element in der Liste
                if (j == (backUpListe.size() - 1)) {
                    if (aktuellesElement.equals(backUpListe.get(j).getWort())) {
                        temp = beiGleichemElementWeitereZifferDazuAddierenAmEndeDerListe(backUpListe, indexZaehler,
                                obSchlagWortInListeSchreiben, aktuellesElement, temp, indexCounter, j);
                        counter = nochmalVorkommendeElementeInBackUpListeLoeschenUndIndexCounterAufNullSetzen(
                                backUpListe, indexZaehler, counter);
                    } else {
                        if (!aktuellesElement.equals(backUpListe.get(j).getWort())) {
                            bisherigVorkommendeElementeInIntegerUmwandeln(backUpListe, obSchlagWortInListeSchreiben,
                                    aktuellesElement, temp, i);
                            counter = nochmalVorkommendeElementeInBackUpListeLoeschenUndIndexCounterAufNullSetzen(
                                    backUpListe, indexZaehler, counter);
                        }
                    }
                }
            }
        }
    }

    /**
     * Mit der Methode beiGleichemElementWeitereZifferDazuAddierenAmEndeDerListe
     * werden weitere sechsstellige Ziffern bei gleichen Schlagwoertern aufaddiert
     * und diese in die Liste geschrieben und alles auf Null gesetzt.
     *
     * @param backUpListe
     * @param indexCounter
     * @param obSchlagWortInListeSchreiben
     * @param aktuellesElement
     * @param temp
     * @param indexCounter
     * @param j
     * @return temp
     */
    // Methode wurde mit vorhandenen Daten getestet
    private static int beiGleichemElementWeitereZifferDazuAddierenAmEndeDerListe(
            ArrayList<DatenBankEintrag> backUpListe, int[] indexZaehler,
            ArrayList<DatenBankEintrag> obSchlagWortInListeSchreiben, String aktuellesElement, int temp,
            int indexCounter, int j) {
        String weiteresElement;
        String weitereZiffer;
        int zahlWeitereZiffer;
        weiteresElement = backUpListe.get(j).getWort();
        weitereZiffer = backUpListe.get(j).getSechsStelligeZiffer();
        zahlWeitereZiffer = Integer.parseInt(weitereZiffer);
        // Addition temp = temp + zahlweiteresElement, wenn beide Elemente gleich sind,
        // am Ende der Liste
        temp = temp + zahlWeitereZiffer;
        nochmalVorkommendesElementInObSchlagWortInListeSchreibenSchreibenUndPositionDerBackUpListeDemIndexZaehlerUebergeben(
                indexZaehler, obSchlagWortInListeSchreiben, aktuellesElement, temp, indexCounter, j);
        return temp;
    }

    /**
     * Mit der Methode
     * nochmalVorkommendesElementInObSchlagWortInListSchreibenSchreibenUndPositionDerBackUpListeDemIndexZaehlerUebergeben
     * wird die sechsstellige Ziffer in einen Integer umgewandelt und die
     * Schlagwoerter in die Liste eingetragen.
     *
     * @param indexZaehler
     * @param obSchlagWortInListeSchreiben
     * @param aktuellesElement
     * @param temp
     * @param indexCounter
     * @param j
     */
    // Methode wurde mit vorhandenen Daten getestet
    private static void nochmalVorkommendesElementInObSchlagWortInListeSchreibenSchreibenUndPositionDerBackUpListeDemIndexZaehlerUebergeben(
            int[] indexZaehler, ArrayList<DatenBankEintrag> obSchlagWortInListeSchreiben, String aktuellesElement,
            int temp, int indexCounter, int j) {
        String tempUmwandeln;
        tempUmwandeln = Integer.toString(temp);
        indexZaehler[indexCounter] = j;
        bisherVorkommendeElementInObSchlagWortInListeSchreibenSchreiben(obSchlagWortInListeSchreiben, aktuellesElement,
                tempUmwandeln);
    }

    /**
     * Mit der Methode
     * nochmalVorkommendeElementeInBackUpListeLoeschenUndIndexCounterAufNullSetzen
     * werden die sogenannten Schlagwoerter in der Liste geloescht und alles auf
     * Null gesetzt.
     *
     * @param backUpListe
     * @param indexZaehler
     * @param counter
     * @return counter
     */
    // Methode wurde mit vorhandenen Daten getestet
    private static int nochmalVorkommendeElementeInBackUpListeLoeschenUndIndexCounterAufNullSetzen(
            ArrayList<DatenBankEintrag> backUpListe, int[] indexZaehler, int counter) {
        outerLoop: for (int l = 0; l < indexZaehler.length; l++) {
            if (indexZaehler[l] != 0) {
                // Elemente aus der Liste "backUpListe" loeschen
                counter = nochmalVorkommendeElementeInBackUpListeLoeschen(backUpListe, indexZaehler, counter, l);
            } else {
                if (indexZaehler[l] == 0) {
                    // alle Indizis vom Int Array auf 0 setzten
                    for (int m = 0; m < indexZaehler.length; m++) {
                        if (indexZaehler[m] != 0) {
                            indexZaehler[m] = 0;
                        } else {
                            if (indexZaehler[m] == 0) {
                                break outerLoop;
                            }
                        }
                    }
                }
            }
        }
        return counter;
    }

    /**
     * Mit der Methode nochmalVorkommendeElementeInBackUpListeLoeschen werden die
     * sogenannten Schlagwoerter in der Liste geloescht.
     *
     * @param backUpListe
     * @param indexZaehler
     * @param counter
     * @param l
     * @return counter
     */
    // Methode wurde mit vorhandenen Daten getestet
    private static int nochmalVorkommendeElementeInBackUpListeLoeschen(ArrayList<DatenBankEintrag> backUpListe,
            int[] indexZaehler, int counter, int l) {
        int positionZaehler;
        int aktuellerZaehler;
        // jetzt loeschen in Liste "backUpListe"
        positionZaehler = indexZaehler[l];
        aktuellerZaehler = positionZaehler - counter;
        backUpListe.remove(aktuellerZaehler);
        counter++;
        return counter;
    }

    /**
     * Mit der Methode obSchlagWortList wird geschaut, welche Schlagwoerter wirklich
     * Schlagwoerter sind.
     *
     * @param datenObSchlagWort
     * @return datenObSchlagWort
     */
    // Methode wurde mit vorhandenen Daten getestet
    public static List<DatenBankEintrag> obSchlagWortListe(List<DatenBankEintrag> datenObSchlagWort) {
        String aktElement;
        for (int i = 0; i < datenObSchlagWort.size(); i++) {
            aktElement = datenObSchlagWort.get(i).getWort();
            keineSchlagWoerterSind(datenObSchlagWort, aktElement, i);
        }

        return datenObSchlagWort;
    }

    /**
     * Mit der Methode keineSchlagWoerterSind werden die Nicht-Schlagwoerter und die
     * wirklichen Schlagwoerter bestimmt.
     *
     * @param datenObSchlagWort
     * @param aktElement
     * @param i
     */
    // Methode wurde mit vorhandenen Daten getestet
    private static void keineSchlagWoerterSind(List<DatenBankEintrag> datenObSchlagWort, String aktElement, int i) {
        char characterElement;
        if (aktElement.length() == 1) {
            characterElement = aktElement.charAt(0);
            if (!Character.isDigit(characterElement)) {
                // ist kein Schlagwort -> *
                datenObSchlagWort.get(i).setPfadInhalt("*");
            }
        }
    }

    /**
     * Mit der Methode alleSchlagWoerterZeigen werden die Nicht-Schlagwoerter aus
     * der Liste entfernt.
     *
     * @param datenSchlagWort
     * @param datenIstSchlagWort
     * @return datenIstSchlagWort
     */
    // Methode wurde mit vorhandenen Daten getestet
    public static List<DatenBankEintrag> alleSchlagWoerterZeigen(List<DatenBankEintrag> datenSchlagWort,
            List<DatenBankEintrag> datenIstSchlagWort) {
        // die Nicht-Schlagwoerter werden geloescht
        // erste Arraylist -> Schlagwoerter mit Sternchen und zweite Arraylist ->
        // orginal Schlagwoerter

        String markierung;
        // schaut ob Sternchen enthalten ist in er Liste
        for (int i = 0; i < datenSchlagWort.size(); i++) {
            markierung = datenSchlagWort.get(i).getPfadInhalt();
            schautObEntsprechendeMarkierungVorhandenIst(datenSchlagWort, datenIstSchlagWort, markierung, i);
        }

        return datenIstSchlagWort;
    }

    /**
     * Mit der Methode schautObEntsprechendeMarkierungVorhandenIst wird die
     * Markierung ueberprueft und die Nicht-Schlagwoerter geloescht.
     *
     * @param datenSchlagWort
     * @param datenIstSchlagWort
     * @param markierung
     * @param i
     */
    // Methode wurde mit vorhandenen Daten getestet
    private static void schautObEntsprechendeMarkierungVorhandenIst(List<DatenBankEintrag> datenSchlagWort,
            List<DatenBankEintrag> datenIstSchlagWort, String markierung, int i) {
        String zuLoeschendesElement;
        if (markierung.equals("*")) {
            zuLoeschendesElement = datenSchlagWort.get(i).getWort();
            loeschtEntsprechendesElementMitMarkierungInOrginalListe(datenIstSchlagWort, zuLoeschendesElement);
        }
    }

    /**
     * Mit der Methode loeschtEntsprechendesElementMarkierungInOrginalArrayListe
     * werden die Nicht-Schlagwoerter geloescht.
     *
     * @param datenIstSchlagWort
     * @param zuLoeschendesElement
     */
    // Methode wurde mit vorhandenen Daten getestet
    private static void loeschtEntsprechendesElementMitMarkierungInOrginalListe(
            List<DatenBankEintrag> datenIstSchlagWort, String zuLoeschendesElement) {
        int zuLoeschendesElementIndex;
        for (int i = 0; i < datenIstSchlagWort.size(); i++) {
            if (zuLoeschendesElement.equals(datenIstSchlagWort.get(i).getWort())) {
                // geht in der Orginal ArrayListe durch und loescht entsprechende Elemente mit
                // Markierung
                zuLoeschendesElementIndex = i;
                datenIstSchlagWort.remove(zuLoeschendesElementIndex);
                i--;
            }
        }
    }
}
