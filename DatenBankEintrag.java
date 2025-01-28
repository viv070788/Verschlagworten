
package verschlagworten;

/**
 * Die Klasse DatenBankEintrag wird benutzt um das Wort, die sechsstellige
 * Ziffer und den Pfad in eine Art Datenbank zu speichern, wieder auszugeben und
 * als String in einer kompletten Zeile auf der Konsole auszugeben.
 * 
 * @author Viviane Bundle
 * @version 1.0
 */
public class DatenBankEintrag {
    private String wort;
    private String sechsStelligeZiffer;
    private String pfad;

    /**
     * Mit dem Konstruktor werden die einzelnen Woerter, einzelnen Ziffern und die
     * einzelnen Pfade jeweils in die Attribute der DatenBankEintrag gelegt.
     * 
     * @param wort
     * @param sechsStelligeZiffer
     * @param pfad
     */
    // Methode wurde mit vorhandenen Daten getestet
    public DatenBankEintrag(String wort, String sechsStelligeZiffer, String pfad) {
        this.wort = wort;
        this.sechsStelligeZiffer = sechsStelligeZiffer;
        this.pfad = pfad;
    }

    /**
     * Mit der Methode getWort wird das Wort zurueckgegeben.
     * 
     * @return wort
     */
    // Methode wurde mit vorhandenen Daten getestet
    public String getWort() {
        return wort;
    }

    /**
     * Mit der Methode getSechsStelligeZiffer wird die sechsstellige Ziffer
     * zurueckgegeben.
     * 
     * @return sechsStelligeZiffer
     */
    // Methode wurde mit vorhandenen Daten getestet
    public String getSechsStelligeZiffer() {
        return sechsStelligeZiffer;
    }

    /**
     * Mit der Methode getPfadInhalt wird der Pfad zurueckgegeben.
     * 
     * @return pfad
     */
    // Methode wurde mit vorhandenen Daten getestet
    public String getPfadInhalt() {
        return pfad;
    }

    /**
     * Mit der Methode setWort wird das Wort in das Attribut gelegt.
     * 
     * @param schlagWort
     */
    // Methode wurde mit vorhandenen Daten getestet
    public void setWort(String schlagWort) {
        this.wort = schlagWort;
    }

    /**
     * Mit der Methode setSechsStelligeZiffer wird die sechsstellige Ziffer in das
     * Attribut gelegt.
     * 
     * @param sechsStelligeZiffer
     */
    // Methode wurde mit vorhandenen Daten getestet
    public void setSechsStelligeZiffer(String sechsStelligeZiffer) {
        this.sechsStelligeZiffer = sechsStelligeZiffer;
    }

    /**
     * Mit der Methode setPfadInhalt wird der Pfad in das Attribut gelegt.
     * 
     * @param pfad
     */
    // Methode wurde mit vorhandenen Daten getestet
    public void setPfadInhalt(String pfad) {
        this.pfad = pfad;
    }

    /**
     * Mit der Methode toString wird das Wort, die sechsstellige Ziffer und der Pfad
     * ausgegeben.
     * 
     * @return wort
     * @return sechsStelligeZiffer
     * @return pfad
     */
    // Methode wurde mit vorhandenen Daten getestet
    @Override
    public String toString() {
        return "Wort: " + wort + ", Ziffer: " + sechsStelligeZiffer + ", Pfad: " + pfad;
    }
}
