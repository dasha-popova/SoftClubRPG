package com.pops.lady;

import com.pops.lady.Hero.Hero;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        try {
            File file = new File("LastGame.out");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            Hero hero;
            Game game;

            if(!file.exists() || !file.isFile()){
                System.out.println("Новая игра...");
                System.out.println("Введите свое имя, сударь:");
                String heroName = br.readLine();
                hero = new Hero(heroName);
                game = new Game(hero);
                System.out.println("Предстоит тебе, " + heroName + ", длинная и трудная путь-дорога в тридесятое царство " +
                        "\nдабы освобить от плена у Змея-Горыныча свою Василису Прекрасную");
                game.play(1);
            }
            else {
                System.out.println("а - Продолжить игру\n" +
                        "б - Новая игра");
                String abc = br.readLine();
                switch (abc){
                    case "а":
                        FileInputStream fis = new FileInputStream(file);
                        ObjectInputStream ios = new ObjectInputStream(fis);
                        game = (Game) ios.readObject();
                        hero = game.getHero();
                        System.out.println(hero.getName() + " продолжает свою путь-дорогу...");
                        game.play(hero.getLevel());
                        break;
                    default:
                        System.out.println("Новая игра...");
                        file.delete();
                        System.out.println("Введите свое имя, сударь:");
                        String heroName = br.readLine();
                        hero = new Hero(heroName);
                        game = new Game(hero);
                        System.out.println("Предстоит тебе, " + heroName + ", длинная и трудная путь-дорога в тридесятое царство " +
                                "\nдабы освобить от плена у Змея-Горыныча свою Василису Прекрасную");
                        game.play(1);
                        break;
                }
            }
            if(!game.isEndGame()){
                System.out.println("Желаете сохранить игру?(да\\нет)");
                if(br.readLine().equals("да")){
                    FileOutputStream fos = new FileOutputStream("LastGame.out");
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(game);
                    System.out.println("Сохранение усешно. Восстанавливай силушку богатырскую и возвращайся =)");
                }
                else{
                    System.out.println("Потеряла твоя Василиса Прекрасная всю надежду на освобождение..." +
                            "Возвращайся и попробуй все заново =)");
                    file.delete();
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
