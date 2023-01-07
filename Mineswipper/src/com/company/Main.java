package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {

    static Game game;
    private static void SaveGame( ){
        try {
            FileOutputStream fos = new FileOutputStream("mine save");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(game);
            oos.flush();
            oos.close();
            System.out.println("game saved");
        }
        catch (Exception e) {

            System.out.println("cant save data" + e.getClass() + ":" + e.getMessage());
        }
    }
    private static void LoadGame() {
        try {
            FileInputStream fil = new FileInputStream("mine save");
            ObjectInputStream ois = new ObjectInputStream(fil);
            game = (Game) ois.readObject();
            ois.close();
            System.out.println("Game loaded");

        } catch (Exception e) {
            System.out.println("cant load");
            System.out.println(e.getClass() + e.getMessage());
        }
    }
    public static void main(String[] args) throws IOException{

        BufferedReader in;
        String input;
        String output ="" ;
       MenuFrame menuFrame= new MenuFrame();
        in = new BufferedReader(new InputStreamReader(System.in));
        do {
            System.out.print("> ");
            input = in.readLine();
            switch (input) {
                case "save":
                    SaveGame();
                    break;
                case "load":
                    LoadGame();
                    break;
                default:
                    break;
            }
            System.out.println(output);

        }  while (!"q".equals(output));

    }


}




