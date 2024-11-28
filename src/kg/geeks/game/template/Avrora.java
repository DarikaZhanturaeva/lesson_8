package kg.geeks.game.template;

public class Avrora extends Hero {
    private boolean isInvisible = false;
    private int invisibleRounds = 0;
    private int damageAccumulated = 0;
    private boolean usedAbility = false;

    public Avrora(String name, int health, int damage) {
        super(name, health, damage, SuperAbility.INVISIBLE);
    }

    public void returnDamage(Boss boss) {
        if (damageAccumulated > 0 && !isInvisible) {
            boss.setHealth(boss.getHealth() - damageAccumulated);
            System.out.println(getName() + " возвращает боссу накопленный урон: " + damageAccumulated);
            damageAccumulated = 0;
        }
    }

    @Override
    public void takeDamage(int damage) {
        if (!isInvisible) {
            super.takeDamage(damage);
        } else {
            System.out.println(getName() + " уклонилась от атаки!");
        }
    }

    @Override
    public void applySuperPower(Boss boss, Hero[] heroes) {
        if (!usedAbility && getHealth() > 0) {
            isInvisible = true;
            invisibleRounds = 2;
            usedAbility = true;
            System.out.println(getName() + " становится невидимой на 2 раунда!");
        } else if (isInvisible) {
            damageAccumulated += boss.getDamage();
            invisibleRounds--;
            System.out.println(getName() + " невидима! Накоплено урона: " + damageAccumulated);

            if (invisibleRounds == 0) {
                isInvisible = false;
                System.out.println(getName() + " выходит из невидимости!");
            }
        }
    }
}