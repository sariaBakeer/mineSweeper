package com.company;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveandLoad {
    SaveandLoad(){

    }

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
private static void LoadGame(){
        try {
            FileInputStream fil = new FileInputStream("mine save");
            ObjectInputStream ois = new ObjectInputStream(fil);
            game = (Game) ois.readObject();
            ois.close();
            System.out.println("Game loaded");

        }
        catch (Exception e){
            System.out.println("cant load");
            System.out.println(e.getClass()+e.getMessage());
        }




}










}
