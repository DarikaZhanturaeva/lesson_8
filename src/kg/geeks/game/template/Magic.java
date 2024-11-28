package kg.geeks.game.template;

import kg.geeks.game.logic.RPG_Game;

public class Magic extends Hero {

    private int boostAmount;

    public Magic(String name, int health, int damage, int boostAmount) {
        super(name, health, damage, SuperAbility.BOOST);
        this.boostAmount = boostAmount;
    }

    @Override
    public void applySuperPower(Boss boss, Hero[] heroes) {
        if (RPG_Game.getRoundNumber() <= 4) {
            System.out.println(this.getName() + " увеличивает атаку героев на " + boostAmount + " в течение 4 раундов.");
            for (Hero hero : heroes) {
                if (hero != this && hero.getHealth() > 0) {
                    hero.setDamage(hero.getDamage() + boostAmount);
                }
            }
        }
    }
}