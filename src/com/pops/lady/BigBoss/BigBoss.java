package com.pops.lady.BigBoss;

import java.util.Random;

/**
 * Created by lady.pops on 10.12.2015.
 */
public class BigBoss {
    private String name;
    private Integer damage;
    private Integer hp;
    private Integer level;

    private static String[] allNames =
            {"Водяной", "Леший", "Баба Яга", "Кощей Бессмертный", "Змей-Горыныч", "Бука-Злюка"};
    private static String[] objects =
            {" с медным костылем", " и посох подчинения", " на крыльях ночи", " и 40 кошек", " с мечом ярости", " и бешенный пес"};

    public BigBoss(Integer level) {
        this.level = level;
        Integer rndName = (new Random()).nextInt() % allNames.length;
        Integer rndObject = (new Random()).nextInt() % objects.length;
        this.name = allNames[rndName] + objects[rndObject];
        this.hp = (level - 1) * 100 + 10 * rndName + rndObject;
        this.damage = (level - 1) * 30 + 5 * rndName + rndObject;
    }

    public String getName() {
        return name;
    }

    public Integer getDamage() {
        return damage;
    }

    public Integer getLevel() {
        return level;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public boolean isAlive(){
        return hp > 0;
    }

    @Override
    public String toString() {
        return "BigBoss: " + name +
                ",\n\t Сила = " + damage +
                ",\n\t Здоровье = " + hp + '.';
    }
}
