package kin.op.pavlik.pokladna.model.trzby;

import kin.op.pavlik.pokladna.model.uzivatele.Uzivatel;

/**
 * Třída reprezentuje tržbu v informačním systému pokladny.
 *
 * Trzba obsahuje informace o provedené transakci, včetně unikátního
 * identifikátoru, částky, prodaného zboží a uzivatele, který tržbu provedl.
 */
public class Trzba {

    private static int pocetInstanciTrzba = 0;

    private int idTrzba; // Unikátní identifikátor tržby
    private int castka; // Částka provedené transakce
    private String prodaneZbozi; // Popis prodaného zboží
    private Uzivatel provedlTrzbu; // Uživatel, který provedl tržbu

    /**
     * Konstruktor pro vytvoření instance Trzba.
     *
     * @param castka Částka provedené transakce.
     * @param prodaneZbozi Polozky prodaného zboží.
     * @param provedlTrzbu Uživatel, který provedl tržbu.
     */
    public Trzba(int castka, String prodaneZbozi, Uzivatel provedlTrzbu) {
        this.idTrzba = ++pocetInstanciTrzba;
        this.castka = castka;
        this.prodaneZbozi = prodaneZbozi;
        this.provedlTrzbu = provedlTrzbu;

    }

    // Gettery
    /**
     * Getter pro získání identifikátoru tržby.
     *
     * @return Identifikátor tržby.
     */
    public int getIdTrzba() {
        return idTrzba;
    }

    /**
     * Getter pro získání částky tržby.
     *
     * @return Částka tržby.
     */
    public int getCastka() {
        return castka;
    }

    /**
     * Getter pro získání informací o prodaném zboží tržby.
     *
     * @return Informace o prodaném zboží.
     */
    public String getProdaneZbozi() {
        return prodaneZbozi;
    }

    /**
     * Getter pro získání uživatele, který provedl tržbu.
     *
     * @return Uživatel, který provedl tržbu.
     */
    public Uzivatel getProvedlTrzbu() {
        return provedlTrzbu;
    }
}
