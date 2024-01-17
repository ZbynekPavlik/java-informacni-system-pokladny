# Program - Informační systém pokladny

## Popis programu
Program představuje informační systém pokladny.

Jsou zde dva druhy uživatelů (zaměstnanec, administrátor).
Liší se v tom, které metody mohou používat.
Zaměstnanec může používat pouze metody pro zaměstnance.
Admin může používat metody pro zaměstnance a pro admina.

V programu je systém přihlašování, kde uživatel zadá jeho jméno a heslo.
Pro testování programu je vytvořen 1 uživatel a 1 admin.

Přihlašovací údaje testovacích účtů:
- **Uzivatel**
Jméno: Pokladni
Heslo: a

- **Admin**
Jméno: Admin
Heslo: b

Tabulky databáze v programu představují pouze ArrayListy ve třídách DatabazeUzivatele a DatabazeTrzby.

## Struktura programu
Soubory programu jsou rozděleny do balíčků podle architektury MVC (Model-View-Controller).

- **Model:** Soubory, které obstarávají logiku aplikace.

- **View:** Soubory, které obstarávají zobrazení výstupu uživatelů.

- **Controller:** Soubory, které obstarávají interakci s uživatelem (například načtou a předají vstup od uživatele do modelu ke zpracování). V rámci těchto souborů dochází k propojení modelu a view.

Hlavní třídy programu:
- **App:** Třída s metodou main, vytvoření potřebných instancí pro běh programu a spuštění programu.

- **PokladnaModel:** Třída představující jádro informačního systému. Uchovává informace o stavu pokladny, aktuálně přihlášeném uživateli nebo adminovi a provádí operace související s tržbami a financemi.

- **PokladnaController** Třída slouží k ovládání pokladny a poskytuje metody pro zaměstnance a admina. Implementuje funkcionalitu pro evidenci tržeb, manipulaci s penězi v pokladně.

- **Autentizace:** Třída Autentizace slouží k autentizaci uživatelů a správě aktuálně přihlášeného uživatele nebo admina.


- **Trzba:** Třída obsahuje informace o provedené transakci, včetně unikátního identifikátoru, částky, prodaného zboží a uživatele, který tržbu provedl.

- **DatabazeTrzby:** Třída představuje databázi tržeb.

- **Uzivatel:** Třída reprezentující zaměstnance s přístupem k metodám pro zaměstnance.

- **Admin:** Třída dědící od třídy Zaměstnanec, rozšiřující funkce o metody pro administrátora.

- **DatabazeUzivatelu:** Třída reprezentuje databázi uživatelů a administrátorů.

## Metody pro zaměstnance
- **Přidání nové tržby v pokladně**
- **Zobrazení aktuálního stavu pokladny**
- **Výpis provedených tržeb.**
- **Výpis informací o určité tržbě**

## Metody pro administrátora
- **Vložení peněz do pokladny**
- **Výběr peněz z pokladny**
- **Odstranění určité existující tržby**
- **Odstranění všech tržeb**