package kin.op.pavlik.pokladna.controller;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

/**
 * Třída VstupyConsole slouží k zpracování vstupů od uživatele pomocí konzole.
 */
public class VstupyConsole {

    // Nastavení české lokality
    private static Locale ceskaLokalita = new Locale("cs", "CZ");

    // Scanner pro čtení vstupů od uživatele
    private static Scanner scanner = new Scanner(System.in, "UTF-8").useLocale(ceskaLokalita);

    /**
     * Načte validní celé číslo od uživatele.
     *
     * @return Načtené celé číslo.
     * @throws InputMismatchException Pokud je zadaný vstup v nesprávném
     * formátu.
     */
    public static int nactiInt() throws InputMismatchException {

        boolean jeValidniVstup;
        int cislo = 0; // inicializace cisla, aby kompilator nehlasil chybu
        do {
            try {
                cislo = scanner.nextInt();
                scanner.nextLine(); // Odstranit znak nového řádku
                jeValidniVstup = true;
            } catch (InputMismatchException e) {
                System.out.println("Zadali jste špatný vstup. Zadejte celé číslo znovu.");
                scanner.nextLine(); // Odstranit chybný vstup
                jeValidniVstup = false;
            }
        } while (jeValidniVstup == false);

        return cislo;
    }

    /**
     * Načte řetězec od uživatele.
     *
     * @return Načtený řetězec.
     */
    public static String nactiString() {
        boolean jeValidniVstup = false;
        String vstupniText = "";

        do {
            try {
                vstupniText = scanner.nextLine();
                jeValidniVstup = true;
            } catch (Exception e) {
                System.out.println("Došlo k chybě při čtení vstupu: " + e.getMessage());
            }
        } while (jeValidniVstup == false);

        return vstupniText;
    }

    /**
     * Načte validní celé číslo od uživatele se zprávou pro uživatele.
     *
     * @param zprava Zpráva pro uživatele.
     * @return Načtené celé číslo.
     */
    public static int nactiIntSeZpravou(String zprava) {
        System.out.print(zprava);
        int cislo = nactiInt();
        return cislo;
    }

    /**
     * Metoda pro načtení kladného celého čísla s výpisem zprávy. Uživatele žádá
     * o zadání hodnoty, a pokud uživatel nezadá kladné celé číslo, vypíše chybu
     * a opakuje proces, dokud není zadáno kladné celé číslo.
     *
     * @param zprava Zpráva, která se zobrazí uživateli při zadávání hodnoty.
     * @return Kladné celé číslo zadané uživatelem.
     */
    public static int nactiKladneIntSeZpravou(String zprava) {
        boolean jeKladnyVstup = false;
        int cislo = 0;
        do {
            cislo = nactiIntSeZpravou(zprava);
            if (cislo > 0) {
                jeKladnyVstup = true;
            } else {
                System.out.println("\nMusíte zadat pouze kladné číslo.");
            }
        } while (jeKladnyVstup == false);
        return cislo;
    }

    /**
     * Načte řetězec od uživatele se zprávou pro uživatele.
     *
     * @param zprava Zpráva pro uživatele.
     * @return Načtený řetězec.
     */
    public static String nactiStringSeZpravou(String zprava) {
        System.out.print(zprava);
        String text = nactiString();
        return text;
    }

    /**
     * Uzavře scanner pro čtení vstupů od uživatele.
     */
    public static void closeScanner() {
        scanner.close();
    }
}
