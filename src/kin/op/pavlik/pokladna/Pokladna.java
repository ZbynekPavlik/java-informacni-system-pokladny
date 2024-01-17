package kin.op.pavlik.pokladna;

import kin.op.pavlik.pokladna.controller.Autentizace;
import kin.op.pavlik.pokladna.controller.PokladnaController;
import kin.op.pavlik.pokladna.controller.menu.HlavniMenuController;
import kin.op.pavlik.pokladna.model.PokladnaModel;
import kin.op.pavlik.pokladna.model.uzivatele.DatabazeUzivatele;
import kin.op.pavlik.pokladna.view.UvodniHlavickaView;

//Popis projektu je v souboru README.md
/**
 * Třída {@code Pokladna} představuje hlavní třídu aplikace Pokladna. Aplikace
 * slouží k evidenci tržeb a správě uživatelů. Popis projektu je k dispozici v
 * souboru README.md.
 *
 * Projekt obsahuje následující balíčky:
 * <ul>
 * <li>{@link kin.op.pavlik.pokladna.controller} - obsahuje kontroléry
 * aplikace</li>
 * <li>{@link kin.op.pavlik.pokladna.model} - obsahuje modely dat</li>
 * <li>{@link kin.op.pavlik.pokladna.view} - obsahuje uživatelské pohledy</li>
 * </ul>
 *
 *
 * Vstupní bod do aplikace je metoda {@link #main(String[]) main}, která vytváří
 * potřebné instance a spouští hlavní menu.
 *
 * @author Zbyněk_Pavlík
 * @version 1.0
 */
public class Pokladna {

    /**
     * Vstupní bod do aplikace. Inicializuje potřebné instance, přidá uživatele
     * a admina do databáze a spouští hlavní menu aplikace.
     *
     * Přihlašovací údaje testovacích účtů:
     *
     * <ul>
     * <li> Uzivatel Jméno: Pokladni Heslo: a </li>
     * <li> Admin Jméno: Admin Heslo: b </li>
     * </ul>
     *
     * @param args Parametry z příkazové řádky.
     */
    public static void main(String[] args) {
        // Vytvoření potřebných instancí
        PokladnaModel pokladnaModel = new PokladnaModel();
        DatabazeUzivatele databazeUzivatele = new DatabazeUzivatele();
        PokladnaController pokladnaController = new PokladnaController(pokladnaModel);
        Autentizace autentizace = new Autentizace(databazeUzivatele, pokladnaModel);
        HlavniMenuController hlavniMenuController = new HlavniMenuController(pokladnaModel, pokladnaController,
                databazeUzivatele, autentizace);
        /*
         * Přidání 1 uživatele a 1 admina do databáze uživatelů.
         * Uzivatel - Jméno: Pokladni Heslo: a
         * Admin - Jméno: Admin Heslo: b
         */
        databazeUzivatele.pridejNovyhoUzivatele("Pokladni", "a", pokladnaController);
        databazeUzivatele.pridejNovyhoAdmina("Admin", "b", pokladnaController);

        // Začátek programu
        UvodniHlavickaView.vypisUvodniHlavicku();
        hlavniMenuController.spustHlavniMenu();
    }
}
