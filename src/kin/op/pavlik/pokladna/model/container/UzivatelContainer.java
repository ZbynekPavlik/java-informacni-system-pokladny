package kin.op.pavlik.pokladna.model.container;

import kin.op.pavlik.pokladna.model.uzivatele.Uzivatel;

/**
 * Třída reprezentuje kontejner pro aktuálně přihlášeného uživatele.
 *
 * UživatelContainer uchovává informace o aktuálně přihlášeném uživateli,
 * umožňuje nastavit nového přihlášeného uživatele, získat aktuálního
 * přihlášeného uživatele, ověřit, zda je nějaký uživatel přihlášen, a odhlásit
 * aktuálně přihlášeného uživatele.
 */
public class UzivatelContainer {

    private Uzivatel aktualniUzivatel = null;

    /**
     * Metoda pro nastavení aktuálního přihlášeného uživatele.
     *
     * @param aktualniUzivatel Nový aktuální přihlášený uživatel.
     */
    public void setAktualniUzivatel(Uzivatel aktualniUzivatel) {
        this.aktualniUzivatel = aktualniUzivatel;
    }

    /**
     * Metoda pro získání aktuálního přihlášeného uživatele.
     *
     * @return Aktuální přihlášený uživatel nebo null, pokud není žádný uživatel
     * přihlášen.
     */
    public Uzivatel getAktualniUzivatel() {
        return aktualniUzivatel;
    }

    /**
     * Metoda pro ověření, zda je nějaký uživatel přihlášen.
     *
     * @return True, pokud je nějaký uživatel přihlášen; jinak false.
     */
    public boolean isPrihlasenyUzivatel() {
        if (aktualniUzivatel != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metoda pro odhlášení aktuálně přihlášeného uživatele.
     */
    public void odhlasAktualnihoUzivatele() {
        aktualniUzivatel = null;
    }

}
