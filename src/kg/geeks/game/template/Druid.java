package kg.geeks.game.template;

import kg.geeks.game.logic.RPG_Game;

public class Druid extends Hero {
    private boolean hasUsedAbility = false;

    public Druid(String name, int health, int damage) {
        super(name, health, damage, SuperAbility.SUMMON);
    }

    private void summonAngel(Hero[] heroes) {
        System.out.println(this.getName() + " призывает Ангела!");
        for (Hero hero : heroes) {
            if (hero instanceof Medic) {
                int boost = RPG_Game.random.nextInt(10) + 5;
                ((Medic) hero).setHealPoints(((Medic) hero).getHealPoints() + boost);
                System.out.println("Ангел усиливает лечение " + hero.getName() + " на " + boost + "!");
            }
        }
    }

    private void summonCrow(Boss boss) {
        System.out.println(this.getName() + " призывает Ворона!");
        if (boss.getHealth() <= 5000) {
            int originalDamage = boss.getDamage();
            boss.setDamage((int) (originalDamage * 1.5));
            System.out.println("Ворон увеличивает урон босса на 50%!");
        } else {
            System.out.println("Но здоровье босса более 50%, Ворон ничего не делает.");
        }
    }

    @Override
    public void applySuperPower(Boss boss, Hero[] heroes) {
        if (!hasUsedAbility) {
            hasUsedAbility = true;
            int choice = RPG_Game.random.nextInt(2);

            if (choice == 0) {
                summonAngel(heroes);
            } else {
                summonCrow(boss);
            }
        }
    }
}
