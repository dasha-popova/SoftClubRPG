package com.pops.lady.Artefact;

import com.pops.lady.Hero.Hero;

/**
 * Created by lady.pops on 08.12.2015.
 */
public class InvisibleHat implements Artefact{
    private final Integer addCost = 15;
    private final Integer needLevel = 4;

    public InvisibleHat() {
    }

    @Override
    public Artefact getArtefact() {
        return this;
    }

    @Override
    public boolean work(Hero person) {
        if(person.getLevel() >= needLevel){
            person.setDamage(person.getDamage() + addCost);
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public Integer getDamage() {
        return 0;
    }

    @Override
    public String getName() {
        return "Шапка-невидимка";
    }

    @Override
    public String toString() {
        return "Шапка-невидимка:\t" +
                "Прибавление силушки богатырской = " + addCost +
                ",\tНеобходимый уровень героя = " + needLevel + ".\n";
    }
}
