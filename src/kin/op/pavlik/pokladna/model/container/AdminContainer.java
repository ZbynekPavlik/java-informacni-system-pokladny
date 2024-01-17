package kin.op.pavlik.pokladna.model.container;

import kin.op.pavlik.pokladna.model.uzivatele.Admin;

/**
 * Třída reprezentuje kontejner pro aktuálně přihlášeného administrátora.
 *
 * AdminContainer uchovává informace o aktuálně přihlášeném administrátorovi,
 * umožňuje nastavit nového přihlášeného administrátora, získat aktuálního
 * přihlášeného administrátora, ověřit, zda je nějaký administrátor přihlášen, a
 * odhlásit aktuálně přihlášeného administrátora.
 */
public class AdminContainer {

    private Admin aktualniAdmin;

    /**
     * Metoda pro nastavení aktuálního přihlášeného administrátora.
     *
     * @param aktualniAdmin Nový aktuální přihlášený administrátor.
     */
    public void setAktualniAdmin(Admin aktualniAdmin) {
        this.aktualniAdmin = aktualniAdmin;
    }

    /**
     * Metoda pro získání aktuálního přihlášeného administrátora.
     *
     * @return Aktuální přihlášený administrátor nebo null, pokud není žádný
     * administrátor přihlášen.
     */
    public Admin getAktualniAdmin() {
        return aktualniAdmin;
    }

    /**
     * Metoda pro ověření, zda je nějaký administrátor přihlášen.
     *
     * @return True, pokud je nějaký administrátor přihlášen; jinak false.
     */
    public boolean isPrihlasenyAdmin() {
        return (aktualniAdmin != null);
    }

    /**
     * Metoda pro odhlášení aktuálně přihlášeného administrátora.
     */
    public void odhlasAktualnihoAdmina() {
        aktualniAdmin = null;
    }
}
