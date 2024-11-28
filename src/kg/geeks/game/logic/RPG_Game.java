package kg.geeks.game.logic;

import kg.geeks.game.template.*;

import java.util.Random;

public class RPG_Game {
    public static Random random = new Random();
    private static int roundNumber = 0;

    public static int getRoundNumber() {
        return roundNumber;
    }

    public static void startGame() {
        Boss boss = new Boss("Skelet", 5000, 50);

        Warrior warrior1 = new Warrior("Ahiles", 290, 10);
        Warrior warrior2 = new Warrior("Hercules", 280, 15);
        Magic magic = new Magic("Маг", 280, 10, 10);
        Berserk berserk = new Berserk("Appolon", 240, 10);
        Medic doc = new Medic("Haus", 250, 5, 15);
        Medic assistant = new Medic("Connor", 300, 5, 5);
        Avrora avrora = new Avrora("Avrora", 250, 20);
        Druid druid = new Druid("Druid", 270, 15);
        Hacker hacker = new Hacker("Hacker", 300, 40, 1000);

        Hero[] heroes = {warrior1, doc, warrior2, magic, berserk, assistant, avrora, druid, hacker};
        printStatistics(boss, heroes);
        while (!isGameOver(boss, heroes)) {
            playRound(boss, heroes);
        }
    }

    private static void playRound(Boss boss, Hero[] heroes) {
        roundNumber++;
        boss.chooseDefence();
        boss.attack(heroes);
        for (Hero hero : heroes) {
            if (hero.getHealth() > 0 && boss.getHealth() > 0
                    && boss.getDefence() != hero.getAbility()) {
                hero.attack(boss);
                hero.applySuperPower(boss, heroes);

                if (hero instanceof Avrora) {
                    ((Avrora) hero).returnDamage(boss);
                }

            }
        }
        printStatistics(boss, heroes);
    }

    private static boolean isGameOver(Boss boss, Hero[] heroes) {
        if (boss.getHealth() <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        boolean allHeroesDead = true;
        for (Hero hero : heroes) {
            if (hero.getHealth() > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    private static void printStatistics(Boss boss, Hero[] heroes) {
        System.out.println("ROUND " + roundNumber + " -----------------");
        System.out.println(boss);
        for (Hero hero : heroes) {
            System.out.println(hero);
        }
    }
}
