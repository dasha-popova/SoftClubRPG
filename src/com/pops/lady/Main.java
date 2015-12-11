package com.pops.lady;

import com.pops.lady.Hero.Hero;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        System.out.println("Введите свое имя, сударь:");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String heroName = br.readLine();
            Hero hero = new Hero(heroName);
            Game game = new Game(hero);
            game.play();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
