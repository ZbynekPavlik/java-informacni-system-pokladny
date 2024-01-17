package kin.op.pavlik.pokladna.model;

import kin.op.pavlik.pokladna.model.container.AdminContainer;
import kin.op.pavlik.pokladna.model.container.UzivatelContainer;
import kin.op.pavlik.pokladna.model.trzby.DatabazeTrzby;
import kin.op.pavlik.pokladna.model.trzby.Trzba;
import kin.op.pavlik.pokladna.model.uzivatele.Admin;
import kin.op.pavlik.pokladna.model.uzivatele.Uzivatel;

/**
 * Třída PokladnaModel představuje model pro správu pokladny v aplikaci.
 * Uchovává informace o stavu pokladny, aktuálně přihlášeném uživateli nebo
 * adminovi, a provádí operace související s tržbami a financemi.
 */
public class PokladnaModel {

    private DatabazeTrzby databazeTrzby = new DatabazeTrzby();
    private int stavPokladny = 0;

    private Uzivatel aktualnePrihlasen = null;
    private Admin aktualnePrihlasenAdmin = null;

    /**
     * Inicializuje aktuálně přihlášeného uživatele nebo admina podle předaných
     * kontejnerů.
     *
     * @param uzivatelContainer Kontejner pro uživatele.
     * @param adminContainer Kontejner pro admina.
     * @return True, pokud inicializace proběhla úspěšně; jinak false.
     */
    public boolean inicializujAktualnePrihlaseneho(UzivatelContainer uzivatelContainer, AdminContainer adminContainer) {
        boolean isInicializaceProvedono;

        if (uzivatelContainer.isPrihlasenyUzivatel()) {
            this.aktualnePrihlasen = uzivatelContainer.getAktualniUzivatel();
            isInicializaceProvedono = true;
        } else if (adminContainer.isPrihlasenyAdmin()) {
            this.aktualnePrihlasen = adminContainer.getAktualniAdmin();
            if (this.aktualnePrihlasen instanceof Admin) {
                aktualnePrihlasenAdmin = (Admin) aktualnePrihlasen;
            }
            isInicializaceProvedono = true;
        } else {
            this.aktualnePrihlasen = null;
            isInicializaceProvedono = false;
        }

        return isInicializaceProvedono;
    }

    // Metody pro zamestance
    /**
     * Vrátí aktuální stav pokladny.
     *
     * @return Aktuální stav pokladny.
     */
    public int zobrazStavPokladny() {
        return stavPokladny;
    }

    /**
     * Vrátí textový výpis provedených tržeb s maximálním počtem položek.
     *
     * @param maximalniPocetPolozekVypisu Maximální počet položek výpisu.
     * @return Textový výpis provedených tržeb.
     */
    public String vypisProvedeneTrzby(int maximalniPocetPolozekVypisu) {
        return databazeTrzby.vratVypisTrzeb(maximalniPocetPolozekVypisu);
    }

    /**
     * Vrátí textový výpis určité tržby podle indexu.
     *
     * @param index Index tržby.
     * @return Textový výpis určité tržby nebo informace, pokud tržba není
     * nalezena.
     */
    public String vypisUrcitouTrzbu(int index) {
        return databazeTrzby.vratUrcitouTrzbu(index);
    }

    /**
     * Přidá novou tržbu do databáze a aktualizuje stav pokladny.
     *
     * @param castka Castka tržby.
     * @param prodaneZbozi Prodane zbozi.
     * @return True, pokud přidání proběhlo úspěšně; jinak false.
     */
    public boolean pridejNovouTrzbu(int castka, String prodaneZbozi) {
        boolean isPridaniProvedeno;

        // kontrola zda je nejaky uzivatel prihlaseny, zamezeni ulozeni null hodnoty
        if (aktualnePrihlasen != null) {
            // Přidání nové tržby do databáze
            databazeTrzby.pridejNovouTrzbu(castka, prodaneZbozi, aktualnePrihlasen);

            // Aktualizace stavu pokladny
            stavPokladny += castka;
            isPridaniProvedeno = true;
        } else {
            isPridaniProvedeno = false;
        }

        return isPridaniProvedeno;
    }

    // Metody pro admina
    /**
     * Vloží zadanou částku peněz do pokladny a aktualizuje stav pokladny.
     *
     * @param castka Částka k vložení.
     * @return True, pokud vložení proběhlo úspěšně; jinak false.
     */
    public boolean vlozPenizeDoPokladny(int castka) {
        boolean isProvedenoVlozeni = false;
        if (castka > 0) {
            stavPokladny += castka;
            isProvedenoVlozeni = true;
        }
        return isProvedenoVlozeni;
    }

    /**
     * Vybere zadanou částku peněz z pokladny a aktualizuje stav pokladny.
     *
     * @param castka Částka k vybrání.
     * @return True, pokud vybrání proběhlo úspěšně; jinak false.
     */
    public boolean vyberPenizeZPokladny(int castka) {
        boolean isProvedenoVybrani = false;

        if (castka > 0) {
            if (castka <= stavPokladny) {
                stavPokladny -= castka;
                isProvedenoVybrani = true;
            }

        }

        return isProvedenoVybrani;
    }

    /**
     * Odstraní existující tržbu z databáze podle zadaného ID a aktualizuje stav
     * pokladny.
     *
     * @param idTrzba ID tržby k odstranění.
     * @return True, pokud odstranění proběhlo úspěšně; jinak false.
     */
    public boolean odstranExistujiciTrzbu(int idTrzba) {
        boolean isProvedenoOdstraneni;
        Trzba hledanaTrzba = databazeTrzby.vratUrcitouTrzbuObjekt(idTrzba);

        if (hledanaTrzba != null) {
            int castkaKVraceni = hledanaTrzba.getCastka();
            if (stavPokladny >= castkaKVraceni) {
                stavPokladny -= castkaKVraceni;
                databazeTrzby.odstranUrcitouTrzbu(idTrzba);
                isProvedenoOdstraneni = true;
            } else {
                isProvedenoOdstraneni = false;
            }
        } else {
            isProvedenoOdstraneni = false;
        }

        return isProvedenoOdstraneni;
    }

    /**
     * Odstraní všechny tržby z databáze a aktualizuje stav pokladny.
     *
     * @return True, pokud odstranění proběhlo úspěšně; jinak false (např.
     * nedostatek prostředků na úhradu tržeb).
     */
    public boolean odstranVsechnyTrzby() {
        boolean isProvedenoOdstraneni;

        int castkaKVraceni = databazeTrzby.vratCelkovaSumaCastkyZaVsechnyTrzby();

        if (stavPokladny >= castkaKVraceni) {
            stavPokladny -= castkaKVraceni;
            databazeTrzby.odstranVsechnyTrzby();
            isProvedenoOdstraneni = true;
        } else {
            isProvedenoOdstraneni = false;
        }

        return isProvedenoOdstraneni;
    }

    // Metoda pro odhlaseni uzivatele a admina z PokladnaModel
    /**
     * Resetuje aktuálně přihlášeného uživatele a admina v modelu.
     */
    public void resetujAktualnePrihlasen() {
        if (aktualnePrihlasen != null) {
            aktualnePrihlasen = null;
        }

        if (aktualnePrihlasenAdmin != null) {
            aktualnePrihlasenAdmin = null;
        }
    }

    /**
     * Vrací objekt tržby podle zadaného ID.
     *
     * @param idTrzba ID tržby.
     * @return Objekt tržby nebo null, pokud tržba není nalezena.
     */
    public Trzba vratUrcitouTrzbuObjekt(int idTrzba) {
        return databazeTrzby.vratUrcitouTrzbuObjekt(idTrzba);
    }

    /**
     * Vrací celkovou sumu peněz za všechny tržby v databázi.
     *
     * @return Celková suma peněz za všechny tržby.
     */
    public int vratCastkuZaVsechnyTrzby() {
        return databazeTrzby.vratCelkovaSumaCastkyZaVsechnyTrzby();
    }

}
