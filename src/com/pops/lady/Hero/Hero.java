package com.pops.lady.Hero;

import com.pops.lady.Artefact.Artefact;

import java.util.HashMap;

/**
 * Created by lady.pops on 08.12.2015.
 */
public class Hero {
    String name;
    Integer level;
    Integer hp;
    Integer magic;
    Integer damage;
    HashMap<Artefact, Integer> magicBag;

    public Hero(String name) {
        this.name = name;
        this.level = 1;
        this.hp = 150 * this.level;
        this.magic = 10 * this.level;
        this.damage = 30 * this.level;
        this.magicBag = new HashMap<Artefact,Integer>();
    }

    public void upLevel(){
        this.level += 1;
        this.hp = this.level * 100 + 30;
        this.magic += 10;
        this.damage += 15;
    }
    public String getName() {
        return name;
    }
    public Integer getLevel() {
        return level;
    }
    public Integer getHp() {
        return hp;
    }

    public Integer getMagic() {
        return magic;
    }
    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public void setMagic(Integer magic) {
        this.magic = magic;
    }
    public void addArtefact(Artefact artefact){
        System.out.println("Да подобрал ты Артефакт чудный\n" + artefact.toString());
        if(!magicBag.containsKey(artefact))
            magicBag.put(artefact,1);
        else{
            magicBag.replace(artefact, magicBag.get(artefact) + 1);
        }
    }
    public void useArtefact(Artefact artefact){
        Integer countOfArtefact = magicBag.get(artefact) - 1;

        if( countOfArtefact <= 0 )
            magicBag.remove(artefact);
        else
            magicBag.replace(artefact, countOfArtefact);
    }
    public boolean isAlive(){
        return hp > 0;
    }

    public Integer getDamage() {
        return damage;
    }

    public void setDamage(Integer damage)
    {
        this.damage = damage;
    }

    public HashMap<Artefact, Integer> getMagicBag() {
        return magicBag;
    }

    @Override
    public String toString() {
        return "Hero: " + name +
                ",\n\t Уровень = " + level +
                ",\n\t Здоровье = " + hp +
                ",\n\t Магия = " + magic +
                ",\n\t Силушка богатырская = " + damage +
                ",\n\t Волшебный мешок = " + magicBag + '.';
    }
}
