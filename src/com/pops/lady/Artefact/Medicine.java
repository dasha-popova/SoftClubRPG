package com.pops.lady.Artefact;

import com.pops.lady.Hero.Hero;

/**
 * Created by lady.pops on 08.12.2015.
 */
public class Medicine implements Artefact{
    private Integer addHP;
    private Integer addMagic;

    public Medicine(Integer addHP, Integer addMagic) {
        this.addHP = addHP;
        this.addMagic = addMagic;
    }

    @Override
    public Artefact getArtefact() {
        return this;
    }

    @Override
    public boolean work(Hero person) {
        int hp = person.getHp();
        int maxhp = person.getLevel() * 100 + 30;
        if(maxhp - addHP > hp){
            person.setHp(hp+addHP);
        }
        else{
            person.setHp(maxhp);
        }
        int magic = person.getMagic();
        int maxmagic = person.getLevel() * 10;
        if(maxmagic - addMagic > magic){
            person.setMagic(magic+addMagic);
        }
        else{
            person.setMagic(maxmagic);
        }
        return true;
    }

    @Override
    public Integer getDamage() {
        return 0;
    }

    @Override
    public String getName(){
        return String.valueOf("Живая водица");
    }

    @Override
    public String toString() {
        return "Живая водица:\t " +
                "Прибавление здоровья = " + addHP +
                ",\tПрибавление магии = " + addMagic + ".\n";
    }

}
