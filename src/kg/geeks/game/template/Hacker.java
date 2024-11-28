package kg.geeks.game.template;

public class Hacker extends Hero {
    private int stealAmount;
    private boolean canSteal = true;

    public Hacker(String name, int health, int damage,int stealAmount) {
        super(name, health, damage, SuperAbility.STEAL_HEALTH);
        this.stealAmount = stealAmount;
    }


    @Override
    public void applySuperPower(Boss boss, Hero[] heroes) {
        if (canSteal) {
            boss.setHealth(boss.getHealth() - stealAmount);
            Hero targetHero = heroes[(int) (Math.random() * heroes.length)];
            targetHero.setHealth(targetHero.getHealth() + stealAmount);
            System.out.println(this.getName() + " stole " + stealAmount + " health from Boss and gave it to " + targetHero.getName());
            canSteal = false;
        } else {
            canSteal = true;
        }
    }
}
