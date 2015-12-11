package com.pops.lady.BigBoss;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by lady.pops on 10.12.2015.
 */
public class BigBoss implements Serializable{
    private String name;
    private Integer damage;
    private Integer hp;
    private Integer level;

    private static String[] allNames =
            {"Водяной", "Леший", "Лихо одноглазое", "Баба Яга", "Кощей Бессмертный", "Бука-Злюка"};
    private static String[] objects =
            {" с медным костылем", " и посох подчинения", " на крыльях ночи", " и 40 кошек", " с мечом ярости", " и бешенный пес"};

    public BigBoss(Integer level) {
        this.level = level;
        Integer rndName = (new Random()).nextInt(allNames.length);
        Integer rndObject = (new Random()).nextInt(objects.length);
        this.name = allNames[rndName] + objects[rndObject];
        this.hp = level * 100 + 10 * (rndName + 1) + rndObject;
        this.damage = level * 17 + (rndName + 1) + rndObject;
    }

    public BigBoss(String name) {
        this.name = name;
        this.level = 11;
        this.hp = this.level * 110;
        this.damage = this.level * 20;
    }

    public String getName() {
        return name;
    }

    public Integer getDamage() {
        return damage;
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
