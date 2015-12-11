package com.pops.lady;

import com.pops.lady.Hero.Hero;
import com.pops.lady.Room.Room;

/**
 * Created by lady.pops on 11.12.2015.
 */
public class Game {
    private String name;
    private Hero hero;
    private Room currentRoom;
    private boolean endGame = false;

    public Game(Hero hero) {
        this.hero = hero;
        this.name = hero.getName();
    }

    public void play(){
        System.out.println("Предстоит тебе, " + name + ", длинная и трудная путь-дорога в тридесятое царство " +
                "\nдабы освобить от плена у Змея-Горыныча свою Василису Прекрасную");
        for(int i = 1; i <= 11; ++i){
            currentRoom = new Room(hero);
            if(!currentRoom.battle()){
                endGame = true;
                System.out.println("Так и останется чахнуть твоя Василиса у Змея-то-Горыныча..." +
                        "Подвел ты деву свою, богатырь. \nПобедил тебя " + currentRoom.getBigBoss().getName() +
                        "\n\nИгра окончена. Вы проиграли.");
                break;
            }
            hero.addArtefact(currentRoom.getArtefact());
            hero.upLevel();
        }
        if(!endGame){
            System.out.println("И добрался ты," + " , да победил Змея-Горыныча свирепого." +
                    "\nИ освободил ты свою Василису Прекрасную. И стали вы жить-поживать да детишек рожать=)" +
                    "\nКонец!" +
                    "\n\nИгра окончена. Вы победили.");
        }
    }
}
