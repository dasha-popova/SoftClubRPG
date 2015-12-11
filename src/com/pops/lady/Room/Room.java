package com.pops.lady.Room;

import com.pops.lady.Artefact.Artefact;
import com.pops.lady.Artefact.Fireball;
import com.pops.lady.Artefact.InvisibleHat;
import com.pops.lady.Artefact.Medicine;
import com.pops.lady.BigBoss.BigBoss;
import com.pops.lady.Hero.Hero;

import java.io.*;
import java.util.Set;

/**
 * Created by lady.pops on 10.12.2015.
 */
public class Room {
    private Integer level;
    private BigBoss bigBoss;
    private Artefact artefact;
    private Hero hero;

    public Room(Hero hero) {
        this.hero = hero;
        this.level = hero.getLevel();
        if(this.level == 11)
            this.bigBoss = new BigBoss("Змей-Горыныч");
        else
            this.bigBoss = new BigBoss(level);

        switch (level % 3) {
            case 0:
                this.artefact = new Fireball(level * 5);
                break;
            case 1:
                this.artefact = new Medicine( 50 + level * 10, level * 3);
                break;
            case 2:
                this.artefact = new InvisibleHat();
                break;
        }
    }

    private void fight(){
        System.out.println("Удар мечом со всею силушкой богатырскою.");
        Integer bossHit = bigBoss.getDamage();
        Integer heroHit = hero.getDamage();
        bigBoss.setHp(bigBoss.getHp() - heroHit);
        if(!bigBoss.isAlive()){
            System.out.println("Да победил ты злыдня здешнего, и ждет тебя следующее царство...");
            System.out.println("================================================================");
            return;
        }
        hero.setHp(hero.getHp() - bossHit);
        System.out.println(bigBoss);
        System.out.println("Hero: " + hero.getName() +
                ",\n\t Здоровье = " + hero.getHp() +
                ",\n\t Магия = " + hero.getMagic() +
                ",\n\t Волшебный мешок = " + hero.getMagicBag() + '.');
    }

    private void fightWithArtefact(Artefact artefact){
        if(!artefact.work(hero)){
            System.out.println("Сударь пока не может использовать сей Артефакт!\n");
            fight();
        }
        else{
            System.out.println("Удар мечом со всею силушкой богатырскою и использование\n" + artefact.toString());
            hero.useArtefact(artefact);
            Integer bossHit = bigBoss.getDamage();
            Integer heroHit = hero.getDamage() + artefact.getDamage();
            bigBoss.setHp(bigBoss.getHp() - heroHit);
            if(!bigBoss.isAlive()){
                System.out.println("Да победил ты злыдня здешнего, и ждет тебя следующее царство...");
                System.out.println("================================================================");
                return;
            }
            hero.setHp(hero.getHp() - bossHit);
            System.out.println(bigBoss);
            System.out.println("Hero: " + hero.getName() +
                    ",\n\t Здоровье = " + hero.getHp() +
                    ",\n\t Магия = " + hero.getMagic() +
                    ",\n\t Волшебный мешок = " + hero.getMagicBag() + '.');
        }

    }

    public boolean battle(){
        System.out.println(hero.toString());
        System.out.println("================================================================");
        System.out.println("Ты попал в царство №" + level +
                ".\n Здесь правит " + bigBoss.toString() +
                "\nВ БОЙ! Да прибудет с тобой сила!)");

        while(hero.isAlive() && bigBoss.isAlive()){
            System.out.println("Какие действия предпримете, Сударь:\n" +
                    "а - Удар мечом по злыденю\n" +
                    "б - Удар мечом с Артефактом-помощником\n" +
                    "с - Позорное бегство с поле боя" );
            BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
            try {
                String abc = br.readLine();
                switch(abc){
                    case "а":
                        fight();
                        break;
                    case "б":
                        System.out.println("Какой Артефакт применить желаете: ");
                        Artefact[] keys = {};
                        hero.getMagicBag().keySet().toArray(keys); // CHECK !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
                        for(int i = 0; i < keys.length; ++i){
                            System.out.println(i + " - " + keys[i].getName());
                        }
                        try {
                            Integer index = Integer.parseInt(br.readLine());
                            if(index < keys.length)
                                fightWithArtefact(keys[index]);
                            else{
                                System.out.println("Ай-ай-ай, шкодник.\n " +
                                        "А вот тебе на, нет у тебя такого Артефакта, бьешься ты лишь с божьей помощью.");
                                fight();
                            }
                            break;
                        } catch (NumberFormatException e){
                            System.out.println("Ай-ай-ай, шкодник.\n " +
                                    "А вот тебе на, нет у тебя такого Артефакта, бьешься ты лишь с божьей помощью.");
                            fight();
                            break;
                        }
                    case "с":
                        System.out.println("Позорное бегство с поле боя\n" +
                                "Ну и какой с тебя богатырь?");
                        return false;
                    default:
                        System.out.println("Ай-ай-ай, шкодник.\n " +
                                "Сударь, тебя русской грамоте обучали вообще? Стыдно!\n" +
                                "Попробуй снова. Но на этот раз не подведи!");
                        battle();
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(!hero.isAlive())
            return false;
        return true;
    }

    public BigBoss getBigBoss() {
        return bigBoss;
    }

    public Artefact getArtefact() {
        return artefact;
    }
}
