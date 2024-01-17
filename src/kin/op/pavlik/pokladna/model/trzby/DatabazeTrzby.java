package kin.op.pavlik.pokladna.model.trzby;

import java.util.ArrayList;

import kin.op.pavlik.pokladna.model.uzivatele.Uzivatel;

/**
 * Třída představuje databázi tržeb.
 *
 * DatabazeTrzby uchovává seznam tržeb a poskytuje metody pro přidání nové
 * tržby, odstranění určité tržby, odstranění všech tržeb, vrácení informací o
 * určité tržbě a vypsání více tržeb.
 */
public class DatabazeTrzby {

    private ArrayList<Trzba> trzby = new ArrayList<>(); // Tabulka tržeb

    /**
     * Metoda pro přidání nové tržby do databáze.
     *
     * @param castka Částka tržby.
     * @param prodaneZbozi Prodané zboží.
     * @param provedlTrzbu Uživatel, který provedl tržbu.
     * @return True, pokud bylo přidání úspěšné; false, pokud nebylo.
     */
    public boolean pridejNovouTrzbu(int castka, String prodaneZbozi, Uzivatel provedlTrzbu) {
        return trzby.add(new Trzba(castka, prodaneZbozi, provedlTrzbu)); // true pokud je pridani uspesne, false pokud
        // neprebohlo uspesne
    }

    /**
     * Metoda pro odstranění určité tržby z databáze.
     *
     * @param idTrzba ID tržby, kterou chceme odstranit.
     * @return True, pokud bylo odstranění úspěšné; false, pokud nebylo.
     */
    public boolean odstranUrcitouTrzbu(int idTrzba) {
        boolean isOdstraneno = false;

        for (Trzba trzba : trzby) {
            if (trzba.getIdTrzba() == idTrzba) {
                trzby.remove(trzba);
                isOdstraneno = true;
                break;
            }
        }

        return isOdstraneno;
    }

    /**
     * Metoda pro odstranění všech tržeb z databáze.
     */
    public void odstranVsechnyTrzby() {
        trzby.clear();
    }

    /**
     * Metoda pro vrácení informací o určité tržbě.
     *
     * @param idTrzba ID tržby, o které chceme informace.
     * @return Textový popis tržby nebo informace o neexistenci tržby.
     */
    public String vratUrcitouTrzbu(int idTrzba) {
        String text = "Žadná tržba s indexem " + idTrzba + " nebyla nalezena.";

        for (Trzba trzba : trzby) {
            if (trzba.getIdTrzba() == idTrzba) {
                text = "ID Tržby: " + trzba.getIdTrzba()
                        + "\nČástka tržby: " + trzba.getCastka() + " Kč"
                        + "\nProdané zboží: " + trzba.getProdaneZbozi()
                        + "\nProdal: " + trzba.getProvedlTrzbu().getUsername();
            }

        }
        return text;
    }

    /**
     * Metoda pro vrácení textového výpisu určitého počtu tržeb v databázi.
     *
     * @param maximalniPocetPolozekVypisu Maximální počet položek výpisu.
     * Zabranění, kdyby v seznamu trzeb bylo mnoho polozek, tak aby se
     * nevypisovali vsechny do konzole, ale napriklad jenom prvnich 20 trzeb.
     * @return Textový výpis určitého počtu tržeb nebo informace o prázdné
     * databázi.
     */
    public String vratVypisTrzeb(int maximalniPocetPolozekVypisu) {
        String vypis = "";

        int index = 1;

        do {
            for (Trzba trzba : trzby) {

                vypis += index + "."
                        + " ID Tržby: " + trzba.getIdTrzba()
                        + " Částka tržby: " + trzba.getCastka() + " Kč"
                        + " Prodané zboží: " + trzba.getProdaneZbozi()
                        + " Prodal: " + trzba.getProvedlTrzbu().getUsername() + "\n";

                index++;

                if (index > maximalniPocetPolozekVypisu) {
                    break; // Pokud dosáhneme maximálního počtu položek výpisu, ukončíme vnitřní for cyklus
                }

            }
        } while (index <= maximalniPocetPolozekVypisu && index <= trzby.size());
        /*
         * Podmínka index <= maximalniPocetPolozekVypisu && index <= trzby.size()
         * Umožňuje cyklu pokračovat, dokud je splněna alespoň jedna z následujících
         * podmínek:
         * - Index nepřesáhl maximální počet položek výpisu
         * (maximalniPocetPolozekVypisu)
         * - Index nepřesáhl celkový počet položek v seznamu trzby (trzby.size())
         */

        return vypis;
    }

    /**
     * Metoda pro vrácení objektu tržby na základě ID.
     *
     * @param idTrzba ID tržby, kterou chceme získat.
     * @return Objekt tržby nebo null, pokud tržba s daným ID není nalezena.
     */
    public Trzba vratUrcitouTrzbuObjekt(int idTrzba) {
        Trzba hledanaTrzba = null;

        for (Trzba trzba : trzby) {
            if (trzba.getIdTrzba() == idTrzba) {
                hledanaTrzba = trzba;
            }

        }
        return hledanaTrzba;
    }

    /**
     * Metoda pro vrácení celkové sumy částky za všechny tržby v databázi.
     *
     * @return Celková suma částky za všechny tržby.
     */
    public int vratCelkovaSumaCastkyZaVsechnyTrzby() {
        int sumaCastky = 0;

        for (Trzba trzba : trzby) {
            sumaCastky += trzba.getCastka();
        }

        return sumaCastky;
    }

}
