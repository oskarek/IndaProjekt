import org.newdawn.slick.GameContainer;

import java.io.*;
import java.util.Random;

/**
 * Created by RobertLorentz on 18/05/15.
 */
public class MapMaker {
    public void writeMap(GameContainer container){
        Random rand = new Random();
        try(
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                                new FileOutputStream("res/maps/map4.txt")))
        ){
            int xPos = 50; int yPos = 50; int distance = 65;
            writer.write("xPos, yPos, lives");
            writer.write(System.lineSeparator());
            for(int i=0;i<104;i++) {
                if(xPos+distance>container.getWidth()){
                    xPos = 50+distance; yPos+=30; distance+=65;
                }
                int lives = rand.nextInt(3)+1;
                writer.write(xPos + " " + yPos + " " + lives);
                xPos+=70;
                writer.write(System.lineSeparator());
            }
        } catch(IOException e){
            System.err.println("Failed to create the map file");
        }


        try(
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream("res/maps/map3.txt")))
        ){
            int xPos = 50; int yPos = 50;
            writer.write("xPos, yPos, lives");
            writer.write(System.lineSeparator());
            for(int i=0;i<52;i++) {
                if(xPos+70>container.getWidth()){
                    xPos = 50; yPos+=30;
                }
                int lives = rand.nextInt(3)+1;
                writer.write(xPos + " " + yPos + " " + lives);
                xPos+=70;
                writer.write(System.lineSeparator());
            }
        } catch(IOException e){
            System.err.println("Failed to create the map file");
        }

        try(
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream("res/maps/map2.txt")))
        ){
            int xPos = 50; int yPos = 50;
            writer.write("xPos, yPos, lives");
            writer.write(System.lineSeparator());
            for(int i=0;i<26;i++) {
                if(xPos+200>container.getWidth()){
                    xPos = 50; yPos+=30;
                }
                int lives = rand.nextInt(2)+1;
                writer.write(xPos + " " + yPos + " " + lives);
                xPos+=200;
                writer.write(System.lineSeparator());
            }
        } catch(IOException e){
            System.err.println("Failed to create the map file");
        }

        try(
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream("res/maps/map1.txt")))
        ){
            int xPos = 50; int yPos = 50;
            writer.write("xPos, yPos, lives");
            writer.write(System.lineSeparator());
            for(int i=0;i<52;i++) {
                if(xPos+70>container.getWidth()){
                    xPos = 50; yPos+=30;
                }
                int lives = 1;
                writer.write(xPos + " " + yPos + " " + lives);
                xPos+=70;
                writer.write(System.lineSeparator());
            }
        } catch(IOException e){
            System.err.println("Failed to create the map file");
        }

    }
}
