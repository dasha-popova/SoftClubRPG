package com.pops.lady;

import com.pops.lady.Hero.Hero;
import com.pops.lady.Room.Room;

import java.io.Serializable;

/**
 * Created by lady.pops on 11.12.2015.
 */
public class Game implements Serializable{
    private String name;
    private Hero hero;
    private Room currentRoom;
    private boolean endGame = false;

    public Game(Hero hero) {
        this.hero = hero;
        this.name = hero.getName();
        this.currentRoom = new Room(hero);
    }

    public void play(Integer level){

        for(int i = level; i <= 11; ++i){
            endGame = currentRoom.battle();
            if(!endGame){
                System.out.println("Так и останется чахнуть твоя Василиса у Змея-то-Горыныча..." +
                        "Подвел ты деву свою, богатырь. \nПобедил тебя " + currentRoom.getBigBoss().getName() +
                        "\n\nИгра окончена. Вы проиграли.");
                break;
            }
            hero.upLevel();
            if(hero.getLevel() != 11)
                hero.addArtefact(currentRoom.getArtefact());
            currentRoom = new Room(hero);
        }
        if(endGame){
            System.out.println("И добрался ты," + " , да победил Змея-Горыныча свирепого." +
                        "\nИ освободил ты свою Василису Прекрасную. И стали вы жить-поживать да детишек рожать=)" +
                        "\nКонец!" +
                        "\n\nИгра окончена. Вы победили.");

        }
    }

    public boolean isEndGame() {
        return endGame;
    }

    public void setEndGame(boolean endGame) {
        this.endGame = endGame;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public Hero getHero() {
        return hero;
    }
}
