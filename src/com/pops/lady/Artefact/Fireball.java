package com.pops.lady.Artefact;

import com.pops.lady.Hero.Hero;

/**
 * Created by lady.pops on 08.12.2015.
 */

public class Fireball implements Artefact {
    private Integer damage;
    private final Integer needMana = 15;

    public Fireball(Integer damage) {
        this.damage = damage;
    }

    @Override
    public Artefact getArtefact() {
        return this;
    }

    @Override
    public Integer getDamage() {
        return damage;
    }

    @Override
    public String getName() {
        return "Шаровая молния";
    }

    @Override
    public boolean work(Hero person) {
        if(person.getMagic() - needMana > 0) {
            person.setMagic(person.getMagic() - needMana);
            return true;
        }
        else
            return false;
    }

    @Override
    public String toString() {
        return "Шаровая молния:\t" +
                " Урон противнику = " + damage +
                ",\tПотеря маны = " + needMana + ".\n";
    }
}
