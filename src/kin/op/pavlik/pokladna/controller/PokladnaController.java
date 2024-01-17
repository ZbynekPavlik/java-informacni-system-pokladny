package kin.op.pavlik.pokladna.controller;

import kin.op.pavlik.pokladna.model.PokladnaModel;
import kin.op.pavlik.pokladna.model.trzby.Trzba;

/**
 * Třída {@code PokladnaController} slouží k ovládání pokladny a poskytuje
 * metody pro zamestnance a admina. Implementuje funkcionalitu pro evidenci
 * tržeb, manipulaci s penězi v pokladně.
 *
 * Metody pro zaměstnance zahrnují přidání nové tržby, zobrazení aktuálního
 * stavu pokladny, výpis provedených tržeb a zobrazení určité tržby podle ID.
 *
 * Metody pro admina zahrnují vkládání a výběr peněz z pokladny, odstranění
 * existující nebo všech tržeb.
 */
public class PokladnaController {

    private PokladnaModel pokladnaModel;

    /**
     * Konstruktor třídy {@code PokladnaController}.
     *
     * @param pokladnaModel Instance třídy {@code PokladnaModel} pro správu dat
     * o pokladně.
     */
    public PokladnaController(PokladnaModel pokladnaModel) {
        this.pokladnaModel = pokladnaModel;
    }

    // Metody pro zamestnance
    /**
     * Metoda pro přidání nové tržby do pokladny.
     */
    public void pridejNovouTrzbu() {
        int castka;
        String prodaneZbozi;

        System.out.println("\nNová tržba");
        castka = VstupyConsole.nactiKladneIntSeZpravou("Zadejte částku tržby: ");
        prodaneZbozi = VstupyConsole.nactiStringSeZpravou("Zadejte položky prodaného zboží: ");

        if (pokladnaModel.pridejNovouTrzbu(castka, prodaneZbozi)) {
            System.out.println("\nNová tržba byla úspěšně přidána.");
        } else {
            System.out.println("\nNastala chyba. Nová tržba nebyla přidána.");
        }
    }

    /**
     * Metoda pro zobrazení aktuálního stavu pokladny.
     */
    public void zobrazStavPokladny() {
        System.out.println("\nAktuální stav pokladny: " + pokladnaModel.zobrazStavPokladny() + " Kč.");
    }

    /**
     * Metoda pro výpis provedených tržeb.
     */
    public void vypisProvedeneTrzby() {
        int maximalniPocetPolozekVypisu = 20; // Maximalni pocet trzeb, ktere se vypisi pri zavolani metody. Vypisuje se
        // od nejstarsich zaznamu.
        String vypis = pokladnaModel.vypisProvedeneTrzby(maximalniPocetPolozekVypisu);
        System.out.println("\nSeznam prvních " + maximalniPocetPolozekVypisu + ". tržeb");
        if (!vypis.equals("")) {
            System.out.print(vypis);
        } else {
            System.out.println("\nV seznamu není uložena žádná tržba.");
        }

    }

    /**
     * Metoda pro výpis určité tržby podle ID.
     */
    public void vypisUrcitouTrzbu() {
        int idTrzba;

        System.out.println("\nVypsání určité tržby");
        idTrzba = VstupyConsole.nactiIntSeZpravou("\nZadejte ID tržby, kterou chcete zobrazit: ");
        System.out.println();
        System.out.println(pokladnaModel.vypisUrcitouTrzbu(idTrzba));
    }

    // Metody pro admina
    /**
     * Metoda pro vložení peněz do pokladny.
     */
    public void vlozPenizeDoPokladny() {
        int castka;
        int stavPokladnyPred = pokladnaModel.zobrazStavPokladny();

        System.out.println("\nVložení peněz do pokladny");
        castka = VstupyConsole.nactiKladneIntSeZpravou("\nZadejte částku pro vklad do pokladny: ");

        if (pokladnaModel.vlozPenizeDoPokladny(castka)) {
            System.out.println("\nPeníze byly úspěšně vloženy do pokladny.");
            System.out.println("\nStav pokladny před vložením peněz: " + stavPokladnyPred + " Kč.");
            System.out.println("Změna stavu: + " + castka + " Kč.");
            System.out.println("Stav pokladny po vložení peněz: " + pokladnaModel.zobrazStavPokladny() + " Kč.");
        } else {
            System.out.println("\nNastala chyba. Peníze nebyly vloženy do pokladny.");
        }

    }

    /**
     * Metoda pro výběr peněz z pokladny.
     */
    public void vyberPenizeZPokladny() {
        int castka;
        int stavPokladnyPred = pokladnaModel.zobrazStavPokladny();

        System.out.println("\nVýběr peněz z pokladny");
        castka = VstupyConsole.nactiKladneIntSeZpravou("\nZadejte částku pro výběr z pokladny: ");

        if (pokladnaModel.vyberPenizeZPokladny(castka)) {
            System.out.println("\nPeníze byly úspěšně vybrány z pokladny.");
            System.out.println("\nStav pokladny před výběrem peněz: " + stavPokladnyPred + " Kč.");
            System.out.println("Změna stavu: - " + castka + " Kč.");
            System.out.println("Stav pokladny po výběru peněz: " + pokladnaModel.zobrazStavPokladny() + " Kč.");
        } else {
            System.out.println("\nNastala chyba. Peníze nebyly vybrány z pokladny.");
        }
    }

    // odstraneni urcite trzbu
    /**
     * Metoda pro odstranění existující tržby podle ID.
     */
    public void odstranExistujiciTrzbu() {
        int idTrzba;
        System.out.println("\nOdstranění určité tržby");
        idTrzba = VstupyConsole.nactiIntSeZpravou("Zadejte ID tržby, kterou chcete odstranit: ");

        if (zkontrolujDostatekPenezVPokladne(idTrzba)) {
            if (pokladnaModel.odstranExistujiciTrzbu(idTrzba)) {
                System.out.println("\nTržba s ID " + idTrzba + " byla úspěšně odstraněna.");
            } else {
                System.out.println("\nNastala chyba. Tržba nebyla odstraněna.");
            }
        }

    }

    /**
     * Metoda pro odstranění všech tržeb.
     */
    public void odstranVsechnyTrzby() {
        System.out.println("\nOdstranění všech tržeb");

        if (zkontrolujDostatekPenezVPokladne()) {
            pokladnaModel.odstranVsechnyTrzby();
            System.out.println("\nOdstranění všech tržeb bylo úspěšné.");
        }
    }

    /**
     * Metoda pro kontrolu dostatečného množství peněz v pokladně před
     * odstraněním určité tržby, aby bylo možné vrátit peníze zákazníkovi.
     *
     * @param idTrzba Identifikátor tržby, kterou chceme odstranit.
     * @return True, pokud je dostatek peněz v pokladně; jinak false.
     */
    private boolean zkontrolujDostatekPenezVPokladne(int idTrzba) {
        boolean isDostatekPenez = false;

        Trzba hledanaTrzba = pokladnaModel.vratUrcitouTrzbuObjekt(idTrzba);

        if (hledanaTrzba != null) {
            int aktualniStavPokladny = pokladnaModel.zobrazStavPokladny();
            int castkaKVybrani = hledanaTrzba.getCastka();

            if (aktualniStavPokladny >= castkaKVybrani) {
                isDostatekPenez = true;
            } else {
                System.out.println("V pokladně není dostatek peněz k provedení akce.");
                System.out.println("Doplňte prosím peníze do pokladny a poté proveďte akci.");
                System.out.println("Aktuální stav pokladny: " + aktualniStavPokladny + " Kč.");
                System.out.println("Požadovaná částka k vybrání: " + castkaKVybrani + " Kč.");
                System.out.println(
                        "Částka, kterou je potřeba doplnit: " + (castkaKVybrani - aktualniStavPokladny) + " Kč.");
            }
            return isDostatekPenez;
        } else {
            System.out.println("Tržba s ID " + idTrzba + " neexistuje.");
            return false;
        }

    }

    /**
     * Metoda pro kontrolu dostatečného množství peněz v pokladně před
     * odstraněním všech tržeb, aby bylo možné vrátit peníze zákazníkům.
     *
     * @return True, pokud je dostatek peněz v pokladně; jinak false.
     */
    private boolean zkontrolujDostatekPenezVPokladne() {
        boolean isDostatekPenez = false;
        int aktualniStavPokladny = pokladnaModel.zobrazStavPokladny();
        int castkaKVybrani = pokladnaModel.vratCastkuZaVsechnyTrzby();

        if (aktualniStavPokladny >= castkaKVybrani) {
            isDostatekPenez = true;
        } else {
            System.out.println("V pokladně není dostatek peněz k provedení akce.");
            System.out.println("Doplňte prosím peníze do pokladny a poté proveďte akci.");
            System.out.println("Aktuální stav pokladny: " + aktualniStavPokladny + " Kč.");
            System.out.println("Požadovaná částka k vybrání: " + castkaKVybrani + " Kč.");
            System.out
                    .println("Částka, kterou je potřeba doplnit: " + (castkaKVybrani - aktualniStavPokladny) + " Kč.");
        }

        return isDostatekPenez;
    }

}
