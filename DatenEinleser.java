
package verschlagworten;

import java.io.IOException;
import java.util.List;

/**
 * Das Interface DatenEinleser beinhaltet die Methoden dateiEinlesen, bereinigen
 * und getSchlagwoerterSubschlagwoerter und liest die vorhandenen Daten ein,
 * bereinigt sie mit Methoden unterschiedlicher Kriterien und schreibt die
 * Schlagwoerter und Subschlagwoerter in eine Liste, siehe Klasse
 * DatenEinleserImpl.
 * 
 * @author Viviane Bundle
 * @version 1.0
 */
// Methode wurde getestet, siehe Klasse DatenEinleserImpl
public interface DatenEinleser {

    /**
     * Mit der Methode dateiEinlesen wird der Pfad der Datei in einer Liste
     * eingelesen und zurueckgegeben, siehe Methode dateiEinlesen in der Klasse
     * DatenEinleserImpl.
     * 
     * @param dateiPfad
     * @throws IOException
     */
    // Methode wurde getestet, siehe Klasse DatenEinleserImpl
    public List<DatenBankEintrag> dateiEinlesen(String dateiPfad) throws IOException;

    /**
     * Mit der Methode bereinigen werden die jeweiligen Zeilen der eingelesenen
     * Datei mit unterschiedlichen Methoden ueberprueft und wahr zurueckgegeben,
     * wenn die jeweiligen Methoden die Bereinigungskriterien erfuellen.
     * 
     * @param zeile
     */
    // Methode wurde getestet, siehe Klasse DatenEinleserImpl
    public boolean bereinigen(String zeile);

    /**
     * Mit der Methode getSchlagwoerterSubschlagwoerter wird die Liste der
     * Schlagwoerter und Subschlagwoerter zurueckgegeben.
     */
    // Methode wurde getestet, siehe Klasse DatenEinleserImpl
    public List<DatenBankEintrag> getSchlagwoerterSubschlagwoerter();

}
