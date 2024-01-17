package kin.op.pavlik.pokladna.model.uzivatele;

import kin.op.pavlik.pokladna.controller.PokladnaController;

/**
 * Třída reprezentuje uživatele se základními právy.
 *
 * Uživatel má atributy jako uživatelské jméno, heslo, role a vlastnost isAdmin
 * pro kontrolu přidělených práv.
 */
public class Uzivatel {

    private static int pocetInstanci = 0; // Statická proměnná pro udržení počtu vytvořených instancí
    private static int indexId = 0; // Staticka proměnná, která slouží jako počítadlo pro specificke indexy, id jsou
    // spolecne cislovane pro uzivatele i adminy.
    private int id; // Unikátní identifikátor instance uživatele
    private String username; // Uživatelské jméno
    private String heslo; // Heslo
    private String role = "zaměstnanec"; // Role uživatele - role pro uživatele je základně nastavena na "zaměstnanec"
    private boolean isAdmin = false; // Vlastnost indikující, zda uživatel má všechna práva, používá se v rámci
    // kontroly přidělených práv - isAdmin pro uživatele je základně nastavena na
    // false, protože nemá všechna práva
    protected PokladnaController pokladnaController; // nastaveno jako protected kvuli tomu aby mel k tomutu atributu
    // pristup i trida Admin

    /**
     * Konstruktor pro vytvoření instance Uzivatel.
     *
     * @param username Uživatelské jméno.
     * @param heslo Heslo.
     */
    public Uzivatel(String username, String heslo, PokladnaController pokladnaController) {
        this.id = ++indexId; // Přidělení nové hodnoty ID a zvýšení hodnoty pro další vytvoření
        this.username = username;
        this.heslo = heslo;
        this.pokladnaController = pokladnaController;
        pocetInstanci++;
    }

    // Gettery a settery
    /**
     * Getter pro získání počtu vytvořených instancí třídy Uzivatel.
     *
     * @return Počet vytvořených instancí třídy Uzivatel.
     */
    public static int getPocetInstanciUzivatel() {
        return pocetInstanci - Admin.getPocetInstanciAdmin();
    }

    /**
     * Getter pro získání uživatelského jména.
     *
     * @return Uživatelské jméno.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Getter pro získání hesla uživatele.
     *
     * @return Heslo uživatele.
     */
    public String getHeslo() {
        return heslo;
    }

    /**
     * Getter pro získání role uživatele.
     *
     * @return Role uživatele.
     */
    public String getRole() {
        return role;
    }

    /**
     * Getter pro získání identifikátoru uživatele.
     *
     * @return Identifikátor uživatele.
     */
    public int getId() {
        return id;
    }

    /**
     * Metoda pro ověření, zda je uživatel administrátor.
     *
     * @return True, pokud je uživatel administrátor, jinak false.
     */
    public boolean isAdmin() {
        return isAdmin;
    }

    // Metody v pokladne pro zamestnance
    /**
     * Metoda pro přidání nové tržby v pokladně.
     */
    public void pridejNovouTrzbu() {
        pokladnaController.pridejNovouTrzbu();
        ;
    }

    /**
     * Metoda pro zobrazení aktuálního stavu pokladny.
     */
    public void zobrazStavPokladny() {
        pokladnaController.zobrazStavPokladny();
    }

    /**
     * Metoda pro výpis provedených tržeb.
     */
    public void vypisProvedeneTrzby() {
        pokladnaController.vypisProvedeneTrzby();
    }

    /**
     * Metoda pro výpis informací o určité tržbě.
     */
    public void vypisUrcitouTrzbu() {
        pokladnaController.vypisUrcitouTrzbu();
    }
}
