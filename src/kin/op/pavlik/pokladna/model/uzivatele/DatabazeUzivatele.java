package kin.op.pavlik.pokladna.model.uzivatele;

import java.util.ArrayList;

import kin.op.pavlik.pokladna.controller.PokladnaController;

/**
 * Třída reprezentuje databázi uživatelů a administrátorů.
 *
 * Databáze uchovává seznam uživatelů a administrátorů, které lze přidávat a
 * následně zobrazovat.
 */
public class DatabazeUzivatele {

    // Seznam uživatelů představující tabulku v databázi
    private ArrayList<Uzivatel> uzivatele = new ArrayList<>(); // Tabulka uživatelů

    /**
     * Metoda pro přidání nového uživatele do databáze.
     *
     * @param username Uživatelské jméno nového uživatele.
     * @param heslo Heslo nového uživatele.
     */
    public void pridejNovyhoUzivatele(String username, String heslo, PokladnaController pokladnaController) {
        uzivatele.add(new Uzivatel(username, heslo, pokladnaController));
    }

    /**
     * Metoda pro přidání nového administrátora do databáze.
     *
     * @param username Uživatelské jméno nového administrátora.
     * @param heslo Heslo nového administrátora.
     */
    public void pridejNovyhoAdmina(String username, String heslo, PokladnaController pokladnaController) {
        uzivatele.add(new Admin(username, heslo, pokladnaController));
    }

    /**
     * Metoda vrací seznam všech uživatelů a administrátorů v databázi.
     *
     * @return Textový řetězec obsahující seznam uživatelů a administrátorů v
     * databázi.
     */
    public String vratSeznamVsechUzivatelu() {
        String seznam = "";
        int index = 1;

        for (Uzivatel uzivatel : uzivatele) {
            seznam += index + ". "
                    + "username: "
                    + uzivatel.getUsername()
                    + " role: "
                    + uzivatel.getRole()
                    + "\n"; // Řádek ve tvaru: 1. username
            index++;
        }

        return seznam;
    }

    /**
     * Metoda pro přihlášení uživatele na základě zadaného uživatelského jména a
     * hesla.
     *
     * @param usernameInput Uživatelské jméno pro přihlášení.
     * @param hesloInput Heslo pro přihlášení.
     * @return Odkaz na instanci třídy Uzivatel, pokud je uživatel nalezen;
     * jinak null.
     */
    public Uzivatel prihlasUzivatele(String usernameInput, String hesloInput) {
        Uzivatel prihlasenyUzivatel = null;

        for (Uzivatel uzivatel : uzivatele) {
            if (uzivatel.getUsername().equals(usernameInput) && uzivatel.getHeslo().equals(hesloInput)) {
                prihlasenyUzivatel = uzivatel;
                break;
            }
        }

        return prihlasenyUzivatel;
    }

}
