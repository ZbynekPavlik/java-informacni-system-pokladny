package kin.op.pavlik.pokladna.model.uzivatele;

import kin.op.pavlik.pokladna.controller.PokladnaController;

/**
 * Třída reprezentuje administrátora s rozšířenými právy.
 *
 * Administrátor je speciální typ uživatele, který má všechna práva a oprávnění
 * ve správě informačního systému.
 */
public class Admin extends Uzivatel {

    private static int pocetInstanciAdmin = 0; // Počet instancí třídy Admin
    private String role = "admin"; // Role admina - role pro admina je základně nastavena na "admin"
    private boolean isAdmin = true; // Indikuje, zda administrátor má všechna práva

    /**
     * Konstruktor pro vytvoření instance Administrátora.
     *
     * @param username Uživatelské jméno administrátora.
     * @param heslo Heslo administrátora.
     */
    public Admin(String username, String heslo, PokladnaController pokladnaController) {
        super(username, heslo, pokladnaController); // Volání konstruktoru rodičovské třídy Uzivatel
        pocetInstanciAdmin++;
    }

    // Gettery a settery
    /**
     * Getter pro získání počtu vytvořených instancí třídy Admin.
     *
     * @return Počet vytvořených instancí třídy Admin.
     */
    public static int getPocetInstanciAdmin() {
        return pocetInstanciAdmin;
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
     * Setter pro nastavení role uživatele.
     *
     * @param role Nová role uživatele.
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Getter pro zjištění, zda je uživatel adminem.
     *
     * @return True, pokud je uživatel adminem; jinak false.
     */
    public boolean isAdmin() {
        return isAdmin;
    }

    /**
     * Setter pro nastavení, zda je uživatel adminem.
     *
     * @param isAdmin True, pokud má být uživatel adminem; jinak false.
     */
    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    // Metody v pokladne pro admina
    /**
     * Přidá peníze do pokladny na základě uživatelova zadání.
     */
    public void vlozPenizeDoPokladny() {
        pokladnaController.vlozPenizeDoPokladny();
    }

    /**
     * Vybere peníze z pokladny na základě uživatelova zadání.
     */
    public void vyberPenizeZPokladny() {
        pokladnaController.vyberPenizeZPokladny();
    }

    /**
     * Odstraní určitou existující tržbu z evidence.
     */
    public void odstranUrcitouExistujiciTrzbu() {
        pokladnaController.odstranExistujiciTrzbu();
    }

    /**
     * Odstraní všechny tržby z evidence.
     */
    public void odstranVsechnyTrzby() {
        pokladnaController.odstranVsechnyTrzby();
    }
}
