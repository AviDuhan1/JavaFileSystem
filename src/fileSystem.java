/*The fileSystem class will be for reading, writing, creating, deleting, etc... files on a very basic level*/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class fileSystem{

    /*fileRead reads a given file*/
    /*1.0 reads a file to a variable. The input "fileName" must be the full path to the directory*/
    public static String fileRead(String fileName){
        /*Initialize the text string*/
        String text = "";
        try {
            /*get the name and directory of the file as input*/
            File toRead = new File(fileName);
            Scanner reader = new Scanner(toRead);
            while (reader.hasNextLine()) {
                text = reader.nextLine();
                System.out.println(text);
            }
            reader.close();
        }
        catch(FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return text;
    }

    /*Tries to create a file and then write to it. If the file already exists it will just write to it*/
    public static void fileWrite(String text, String fileName){
        /*Creating the new file*/
        try{
            File toCreate = new File(fileName);
            /*If the file does not exist then it creates a new file*/
            if(toCreate.createNewFile()){
                System.out.println("File Created: " + toCreate.getName());
            }
            /*If the file exists it will say so*/
            else{
                System.out.println("File failed to create: already exists");
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        try{
            FileWriter writer = new FileWriter(fileName);
            writer.write(text);
            writer.close();
            System.out.println("Successfully wrote to file");
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

}