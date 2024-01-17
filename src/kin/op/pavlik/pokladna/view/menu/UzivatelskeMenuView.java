package kin.op.pavlik.pokladna.view.menu;

/**
 * Třída UzivatelskeMenuView poskytuje statické metody pro výpis uživatelského
 * menu pro zaměstnance a admina. Obsahuje volby pro odhlášení, evidenci tržeb,
 * manipulaci s pokladnou a další operace.
 */
public class UzivatelskeMenuView {

    /**
     * Statická metoda pro výpis uživatelského menu pro zaměstnance. Zahrnuje
     * možnosti spojené s evidencí tržeb a manipulací s pokladnou.
     */
    public static void vypisUzivatelskeMenuZamestanec() {

        System.out.println();
        vypisDeliciCaru();
        System.out.println("Menu pro Zaměstnance");
        vypisDeliciCaru();
        System.out.println("0. Odhlásit se a zpět na hlavní menu");
        System.out.println("1. Nová tržba");
        System.out.println("2. Zobrazit aktuální stav pokladny");
        System.out.println("3. Zobrazit seznam provedených tržeb");
        System.out.println("4. Zobrazit informace o určité tržbě");

    }

    /**
     * Statická metoda pro výpis uživatelského menu pro admina. Obsahuje volby
     * pro odhlášení, evidenci tržeb, manipulaci s pokladnou, správu dluhů a
     * další operace.
     */
    public static void vypisUzivatelskeMenuAdmin() {

        System.out.println();
        vypisDeliciCaru();
        System.out.println("Menu pro Admina");
        vypisDeliciCaru();
        System.out.println("0. Odhlásit se a zpět na hlavní menu");
        System.out.println("1. Nová tržba");
        System.out.println("2. Zobrazit aktuální stav pokladny");
        System.out.println("3. Zobrazit seznam provedených tržeb");
        System.out.println("4. Zobrazit informace o určité tržbě");
        System.out.println("5. Vložit peníze do pokladny");
        System.out.println("6. Vybrat peníze z pokladny");
        System.out.println("7. Odstranit určitou tržbu");
        System.out.println("8. Odstranit všechny tržby");

    }

    /**
     * Statická metoda pro výpis oddělovací čáry.
     */
    public static void vypisDeliciCaru() {
        System.out.println("====================");
    }
}
