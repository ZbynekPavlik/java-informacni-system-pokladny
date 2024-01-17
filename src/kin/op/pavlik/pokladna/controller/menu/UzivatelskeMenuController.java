package kin.op.pavlik.pokladna.controller.menu;

import kin.op.pavlik.pokladna.controller.Autentizace;
import kin.op.pavlik.pokladna.controller.PokladnaController;
import kin.op.pavlik.pokladna.controller.VstupyConsole;
import kin.op.pavlik.pokladna.model.PokladnaModel;
import kin.op.pavlik.pokladna.model.uzivatele.Admin;
import kin.op.pavlik.pokladna.model.uzivatele.DatabazeUzivatele;
import kin.op.pavlik.pokladna.model.uzivatele.Uzivatel;
import kin.op.pavlik.pokladna.view.UpozorneniView;
import kin.op.pavlik.pokladna.view.menu.UzivatelskeMenuView;

/**
 * Třída UzivatelskeMenuController slouží k ovládání uživatelského menu pro
 * zaměstnance a admina. Implementuje operace spojené s prováděním tržeb,
 * správou pokladny a dalšími funkcemi.
 */
public class UzivatelskeMenuController {

    private Autentizace autentizace;

    /**
     * Konstruktor pro třídu UzivatelskeMenuController. Inicializuje potřebné
     * závislosti pro ovládání uživatelského menu.
     *
     * @param autentizace Instance třídy pro autentizaci uživatelů
     */
    public UzivatelskeMenuController(PokladnaModel pokladnaModel, PokladnaController pokladnaController,
            DatabazeUzivatele databazeUzivatele, Autentizace autentizace) {
        this.autentizace = autentizace;
    }

    /**
     * Metoda pro spuštění uživatelského menu pro zaměstnance nebo admina.
     * Rozhoduje se na základě přihlášeného uživatele a zobrazuje příslušné
     * menu.
     */
    public void spustUzivatelskeMenu() {

        // Pokud je přihlášen zaměstnanec, zobrazí se jeho menu
        if (autentizace.getAktualniUzivatelContainer().isPrihlasenyUzivatel() == true) {
            Uzivatel zamestnanec = autentizace.getAktualniUzivatelContainer().getAktualniUzivatel();

            spustMenuProZamestance(zamestnanec);
        } else if (autentizace.getAktualniAdminContainer().isPrihlasenyAdmin() == true) { // zobrazeni menu pro admina
            // na zaklade prihlaseni
            Admin admin = autentizace.getAktualniAdminContainer().getAktualniAdmin();

            spustMenuProAdmina(admin);
        } else {
            System.out.println("Nastala chyba. Nikdo není přihlášen.");
        }
    }

    /**
     * Metoda pro obsluhu menu pro zaměstnance.
     *
     * @param zamestnanec Instance třídy Uzivatel představující zaměstnance,
     * který menu ovládá.
     */
    private void spustMenuProZamestance(Uzivatel zamestnanec) {

        int volba;

        do {
            UzivatelskeMenuView.vypisUzivatelskeMenuZamestanec();
            volba = VstupyConsole.nactiIntSeZpravou("\nZadejte vaši volbu (0-4): ");

            switch (volba) {
                case 0:
                    System.out.println("\nOdhlásit se a zpět na hlavní menu.");
                    autentizace.odhlasUzivatele();
                    break;

                case 1:
                    zamestnanec.pridejNovouTrzbu();
                    break;

                case 2:
                    zamestnanec.zobrazStavPokladny();
                    break;

                case 3:
                    zamestnanec.vypisProvedeneTrzby();
                    break;

                case 4:
                    zamestnanec.vypisUrcitouTrzbu();
                    break;

                default:
                    UpozorneniView.vypisNeznamaVolbaSwitch();
                    break;
            }
        } while (volba != 0);
    }

    /**
     * Metoda pro obsluhu menu pro admina.
     *
     * @param admin Instance třídy Admin představující administrátora, který
     * menu ovládá.
     */
    private void spustMenuProAdmina(Admin admin) {
        int volba;

        do {
            UzivatelskeMenuView.vypisUzivatelskeMenuAdmin();
            volba = VstupyConsole.nactiIntSeZpravou("\nZadejte vaši volbu (0-8): ");

            switch (volba) {
                case 0:
                    System.out.println("\nOdhlásit se a zpět na hlavní menu.");
                    autentizace.odhlasUzivatele();
                    break;

                case 1:
                    admin.pridejNovouTrzbu();
                    break;

                case 2:
                    admin.zobrazStavPokladny();
                    break;

                case 3:
                    admin.vypisProvedeneTrzby();
                    break;

                case 4:
                    admin.vypisUrcitouTrzbu();
                    break;

                case 5:
                    admin.vlozPenizeDoPokladny();
                    break;

                case 6:
                    admin.vyberPenizeZPokladny();
                    break;

                case 7:
                    admin.odstranUrcitouExistujiciTrzbu();
                    break;

                case 8:
                    admin.odstranVsechnyTrzby();
                    break;

                default:
                    UpozorneniView.vypisNeznamaVolbaSwitch();
                    break;
            }
        } while (volba != 0);
    }

}
