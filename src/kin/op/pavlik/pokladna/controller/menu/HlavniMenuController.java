package kin.op.pavlik.pokladna.controller.menu;

import kin.op.pavlik.pokladna.controller.Autentizace;
import kin.op.pavlik.pokladna.controller.PokladnaController;
import kin.op.pavlik.pokladna.controller.VstupyConsole;
import kin.op.pavlik.pokladna.model.PokladnaModel;
import kin.op.pavlik.pokladna.model.uzivatele.DatabazeUzivatele;
import kin.op.pavlik.pokladna.view.UpozorneniView;
import kin.op.pavlik.pokladna.view.menu.HlavniMenuView;

/**
 * Třída {@code HlavniMenuController} slouží k ovládání hlavního menu programu.
 * Zprostředkovává uživateli volbu mezi přihlášením do systému a ukončením
 * programu.
 */
public class HlavniMenuController {

    private PokladnaModel pokladnaModel;
    private PokladnaController pokladnaController;
    private DatabazeUzivatele databazeUzivatele;
    private Autentizace autentizace;

    /**
     * Konstruktor pro třídu {@code HlavniMenuController}. Inicializuje potřebné
     * závislosti pro ovládání hlavního menu.
     *
     * @param pokladnaModel Instance třídy pro manipulaci s daty pokladny
     * @param pokladnaController Instance třídy pro řízení operací s pokladnou
     * @param databazeUzivatele Instance třídy reprezentující databázi uživatelů
     * @param autentizace Instance třídy pro autentizaci uživatelů
     */
    public HlavniMenuController(PokladnaModel pokladnaModel, PokladnaController pokladnaController,
            DatabazeUzivatele databazeUzivatele, Autentizace autentizace) {
        this.pokladnaModel = pokladnaModel;
        this.pokladnaController = pokladnaController;
        this.databazeUzivatele = databazeUzivatele;
        this.autentizace = autentizace;
    }

    /**
     * Metoda pro spuštění hlavního menu programu. Uživateli umožňuje volbu mezi
     * přihlášením do systému a ukončením programu.
     */
    public void spustHlavniMenu() {

        int volba;

        do {
            HlavniMenuView.vypisHlavniMenu();
            volba = VstupyConsole.nactiIntSeZpravou("\nZadejte vaši volbu (0-1): ");

            switch (volba) {
                case 1:
                    System.out.println();
                    if (autentizace.prihlasUzivatele()) {
                        UzivatelskeMenuController uzivatelskeMenuController = new UzivatelskeMenuController(
                                pokladnaModel, pokladnaController, databazeUzivatele, autentizace);
                        uzivatelskeMenuController.spustUzivatelskeMenu();
                    }
                    break;

                case 0:
                    System.out.println("\nKonec programu.");
                    break;
                default:
                    UpozorneniView.vypisNeznamaVolbaSwitch();
                    break;
            }
        } while (volba != 0);

    }
}
