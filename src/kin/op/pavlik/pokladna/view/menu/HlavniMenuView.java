package kin.op.pavlik.pokladna.view.menu;

/**
 * Třída HlavniMenuView poskytuje metody pro výpis hlavního menu aplikace.
 * Obsahuje statické metody pro zobrazení hlavního menu a oddělovací čáry. Menu
 * obsahuje volby pro ukončení programu a nové přihlášení.
 */
public class HlavniMenuView {

    /**
     * Statická metoda pro výpis hlavního menu. Obsahuje volby pro ukončení
     * programu a nové přihlášení.
     */
    public static void vypisHlavniMenu() {
        System.out.println();
        vypisDeliciCaru();
        System.out.println("Hlavní menu");
        vypisDeliciCaru();
        System.out.println("0. Ukončit program");
        System.out.println("1. Nové prihlášení");

    }

    /**
     * Statická metoda pro výpis oddělovací čáry.
     */
    public static void vypisDeliciCaru() {
        System.out.println("====================");
    }

}
