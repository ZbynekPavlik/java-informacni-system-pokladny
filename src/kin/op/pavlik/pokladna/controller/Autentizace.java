package kin.op.pavlik.pokladna.controller;



import kin.op.pavlik.pokladna.model.PokladnaModel;
import kin.op.pavlik.pokladna.model.container.AdminContainer;
import kin.op.pavlik.pokladna.model.container.UzivatelContainer;
import kin.op.pavlik.pokladna.model.uzivatele.Admin;
import kin.op.pavlik.pokladna.model.uzivatele.DatabazeUzivatele;
import kin.op.pavlik.pokladna.model.uzivatele.Uzivatel;

/**
 * Třída Autentizace slouží k autentizaci uživatelů a správě aktuálně
 * přihlášeného uživatele nebo admina.
 *
 * Autentizace umožňuje přihlašování a odhlašování uživatelů. Uživatelé jsou
 * načítáni z instance třídy DatabazeUzivatele.
 */
public class Autentizace {

    private DatabazeUzivatele databaze;
    private PokladnaModel pokladnaModel;
    private UzivatelContainer aktualniUzivatelContainer = new UzivatelContainer();
    private AdminContainer aktualniAdminContainer = new AdminContainer();

    

    /**
     * Konstruktor třídy Autentizace.
     *
     * @param databaze Instance třídy DatabazeUzivatele, která obsahuje
     * registrované uživatele.
     * @param pokladnaModel Instance třídy pokladnaModel
     */
    public Autentizace(DatabazeUzivatele databaze, PokladnaModel pokladnaModel) {
        this.databaze = databaze;
        this.pokladnaModel = pokladnaModel;
    }

    /**
     * Metoda pro přihlášení uživatele nebo admina.
     *
     * @return true pokud prihlaseni uzivatele probehlo v poradku; false pokud
     * neprobehlo v poradku
     */
    public boolean prihlasUzivatele() {
        boolean isProbehloPrihlaseny;

        System.out.println("Přihlášení:");

        String usernameInput = VstupyConsole.nactiStringSeZpravou("Zadejte uživatelské jméno: ");
        
        String hesloInput = VstupyConsole.nactiStringSeZpravou("Zadejte heslo: ");

        Uzivatel aktualniUzivatel = databaze.prihlasUzivatele(usernameInput, hesloInput);

        if (aktualniUzivatel != null) {

            if (aktualniUzivatel instanceof Admin) {
                Admin aktualniAdmin = (Admin) aktualniUzivatel;
                System.out.println("\nPřihlášení proběhlo v pořádku.");
                System.out.println("Role přihlášeného: " + aktualniUzivatel.getRole());
                aktualniAdminContainer.setAktualniAdmin(aktualniAdmin);
                isProbehloPrihlaseny = true;
            } else {
                System.out.println("\nPřihlášení proběhlo v pořádku.");
                System.out.println("Role přihlášeného: " + aktualniUzivatel.getRole());
                aktualniUzivatelContainer.setAktualniUzivatel(aktualniUzivatel);
                isProbehloPrihlaseny = true;
            }

            // inicializace nove prihlaseneho uzivatele do pokladny Model, aby s nim tato
            // trida mohla dale pracovat
            pokladnaModel.inicializujAktualnePrihlaseneho(aktualniUzivatelContainer, aktualniAdminContainer);

        } else {
            System.out.println("\nŽádný uživatel není přihlášen. Uživatel nebyl nalezen.");
            isProbehloPrihlaseny = false;
        }
        return isProbehloPrihlaseny;
    }

    /**
     * Metoda pro odhlášení aktuálně přihlášeného uživatele nebo admina.
     */
    public void odhlasUzivatele() {

        if (isPrihlasen()) {
            if (aktualniUzivatelContainer.isPrihlasenyUzivatel() == true) {
                aktualniUzivatelContainer.odhlasAktualnihoUzivatele();
                System.out.println("\nOdhlášení uživatele proběhlo v pořádku.");
                // odhlaseni prihlaseneho uzivatele v pokladneModel, aby tato trida byla
                // pripravena na nove prihlaseni.
                pokladnaModel.resetujAktualnePrihlasen();
            } else if (aktualniAdminContainer.isPrihlasenyAdmin() == true) {
                aktualniAdminContainer.odhlasAktualnihoAdmina();
                System.out.println("\nOdhlášení admina proběhlo v pořádku.");
                // odhlaseni prihlaseneho uzivatele v pokladneModel, aby tato trida byla
                // pripravena na nove prihlaseni.
                pokladnaModel.resetujAktualnePrihlasen();
            } else {
                System.out.println("\nPři odhlašování nastala chyba. Neproběhlo odhlášení.");
            }

        } else {
            System.out.println("\nOdhlášení neproběhlo. Žádný uživatel není přihlášen.");
        }

    }

    /**
     * Metoda pro zjištění, zda je nějaký uživatel přihlášen.
     *
     * @return True, pokud je nějaký uživatel nebo admin přihlášen; jinak false.
     */
    public boolean isPrihlasen() {
        return aktualniUzivatelContainer.isPrihlasenyUzivatel() || aktualniAdminContainer.isPrihlasenyAdmin(); // zjisti
        // zda
        // uzivatel
        // (aktualniUzivatelContainer.isPrihlasenyUzivatel())
        // nebo
        // (||)
        // admin
        // je
        // prihlasen
        // (aktualniAdminContainer.isPrihlasenyAdmin()).
        // vrati
        // true,
        // pokud
        // je
        // alespon
        // jedno
        // true.
    }

    // Gettery a settery
    /**
     * Getter pro aktuálně přihlášeného uživatele.
     *
     * @return Instance třídy UzivatelContainer s aktuálně přihlášeným
     * uživatelem.
     */
    public UzivatelContainer getAktualniUzivatelContainer() {
        return aktualniUzivatelContainer;
    }

    /**
     * Getter pro aktuálně přihlášeného admina.
     *
     * @return Instance třídy AdminContainer s aktuálně přihlášeným adminem.
     */
    public AdminContainer getAktualniAdminContainer() {
        return aktualniAdminContainer;
    }
}
