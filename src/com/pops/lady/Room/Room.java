package com.pops.lady.Room;

import com.pops.lady.Artefact.Artefact;
import com.pops.lady.Artefact.Fireball;
import com.pops.lady.Artefact.InvisibleHat;
import com.pops.lady.Artefact.Medicine;
import com.pops.lady.BigBoss.BigBoss;
import com.pops.lady.Hero.Hero;

import java.io.*;
import java.util.Random;
import java.util.Set;

/**
 * Created by lady.pops on 10.12.2015.
 */
public class Room implements Serializable{
    private Integer level;
    private BigBoss bigBoss;
    private Artefact artefact;
    private Hero hero;
    private boolean endBattle = true;

    public Room(Hero hero) {
        this.hero = hero;
        this.level = hero.getLevel();
        if(this.level == 11)
            this.bigBoss = new BigBoss("Змей-Горыныч");
        else
            this.bigBoss = new BigBoss(level);

        switch ((new Random()).nextInt(9) % 3) {
            case 0:
                this.artefact = new Fireball(level * 3);
                break;
            case 1:
                this.artefact = new Medicine( 30 + level * 10, level * 3);
                break;
            case 2:
                this.artefact = new InvisibleHat();
                break;
        }
    }

    private void fight(){
        System.out.println("\tУдар мечом со всею силушкой богатырскою.");
        System.out.println("________________________________________________________________");
        Integer bossHit = bigBoss.getDamage();
        Integer heroHit = hero.getDamage();
        bigBoss.setHp(bigBoss.getHp() - heroHit);
        if(!bigBoss.isAlive()){
            System.out.println("\tДа победил ты злыдня здешнего, и ждет тебя следующее царство...");
            System.out.println("================================================================");
            return;
        }
        hero.setHp(hero.getHp() - bossHit);
        System.out.println(bigBoss);
        System.out.println("Hero: " + hero.getName() +
                ",\n\t Здоровье = " + hero.getHp() +
                ",\n\t Магия = " + hero.getMagic() +
                ",\n\t Силушка богатырская = " + hero.getDamage() +
                ",\n\t Волшебный мешок = " + hero.getMagicBag() + '.');
    }

    private void fightWithArtefact(Artefact artefact){
        if(!artefact.work(hero)){
            System.out.println("Сударь пока не может использовать сей Артефакт!\n");
            fight();
        }
        else{
            System.out.println("\tУдар мечом со всею силушкой богатырскою и использование\n" + artefact.toString());
            hero.useArtefact(artefact);
            System.out.println("________________________________________________________________");
            Integer bossHit = bigBoss.getDamage();
            Integer heroHit = hero.getDamage() + artefact.getDamage();
            bigBoss.setHp(bigBoss.getHp() - heroHit);
            if(!bigBoss.isAlive()){
                System.out.println("\tДа победил ты злыдня здешнего, и ждет тебя следующее царство...");
                System.out.println("================================================================");
                return;
            }
            hero.setHp(hero.getHp() - bossHit);
            System.out.println(bigBoss);
            System.out.println("Hero: " + hero.getName() +
                    ",\n\t Здоровье = " + hero.getHp() +
                    ",\n\t Магия = " + hero.getMagic() +
                    ",\n\t Силушка богатырская = " + hero.getDamage() +
                    ",\n\t Волшебный мешок = " + hero.getMagicBag() + '.');
        }

    }

    public boolean battle(){
        //System.out.println(hero.toString());
        System.out.println("================================================================\n");
        System.out.println("Ты попал в царство №" + level +
                ".\n Здесь правит " + bigBoss.toString() +
                "\nВ БОЙ! Да прибудет с тобой сила!)\n");
        System.out.println("Сударь, ваши возможные действия:\n" +
                "а - Удар мечом по злыденю\n" +
                "б - Удар мечом с Артефактом-помощником\n" +
                "в - Позорное бегство с поле боя и возможное сохранение игры" );

        while(hero.isAlive() && bigBoss.isAlive()){
            if(hero.getMagicBag().isEmpty()){
                System.out.println("\n\t\tИдет битва на мечах...\n");
                while(hero.isAlive() && bigBoss.isAlive()) {
                    fight();
                }
                break;
            }
            System.out.println("Какие действия предпримете, Сударь: ");
            try {
                if (!choose()) {
                    endBattle = false;
                    return true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(!hero.isAlive())
            return false;
        return true;
    }

    private boolean choose() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        String abc = br.readLine();
        switch(abc){
            case "а":
                fight();
                break;
            case "б":
                System.out.println("Какой Артефакт применить желаете: ");
                Artefact[] keys = hero.getMagicBag().keySet().toArray(new Artefact[0]);
                for(int i = 0; i < keys.length; ++i){
                    System.out.println(i + " - " + keys[i].getName());
                }
                try {
                    Integer index = Integer.parseInt(br.readLine());
                    if(index < keys.length)
                        fightWithArtefact(keys[index]);
                    else{
                        System.out.println("Ай-ай-ай, шкодник.\n " +
                                "А вот тебе на, нет у тебя такого Артефакта, бьешься ты лишь с божьей помощью.\n");
                        fight();
                    }
                    break;
                } catch (NumberFormatException e){
                    System.out.println("Ай-ай-ай, шкодник.\n " +
                            "А вот тебе на, нет у тебя такого Артефакта, бьешься ты лишь с божьей помощью.\n");
                    fight();
                    break;
                }
            case "в":
                System.out.println("Позорное бегство с поле боя\n" +
                        "Ну и какой с тебя богатырь?\n");
                return false;
            default:
                System.out.println("Ай-ай-ай, шкодник.\n " +
                        "Сударь, тебя русской грамоте обучали вообще? Стыдно!\n" +
                        "Попробуй снова. Но на этот раз не подведи!\n");
                choose();
                break;
        }

        return true;
    }
    public BigBoss getBigBoss() {
        return bigBoss;
    }

    public Artefact getArtefact()
    {
        return artefact;
    }

    public boolean isEndBattle() {
        return endBattle;
    }
}
