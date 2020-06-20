/*The fileSystem class will be for reading, writing, creating, deleting, etc... files on a very basic level*/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
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
    /*The String "filename" is (currently) the full path to the file you'd like to create with the filename at the end*/
    public static void fileWrite(String text, String fileName){
        fileCreate(fileName);
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

    /*Simple method to create a file. "fileName" is the full path to the file location desired ending with the name of the file itself*/
    public static void fileCreate(String fileName){
        /*Creating rhe new file*/
        try{
            File toCreate = new File(fileName);
            /*if the file does not exist it will create it and then confirm the creation*/
            if(toCreate.createNewFile()){
                System.out.println("File Created: " + toCreate.getName());
            }
            /*if the file already exists it will say so*/
            else {
                System.out.println(("File failed to create: already exists"));
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }

    /*Simple method to delete a file. "fileName" is the full path to the file location desired ending with the name of the file itself*/
    public static void fileDelete(String fileName){
        /*This try catch clause will be a little more complex as there are multiple parts of file deletion which can throw errors*/
        try{
            Files.deleteIfExists(Paths.get(fileName));
        }
        /*If the file or directory doesn't exist, throw an error*/
        catch(NoSuchFileException e){
            e.printStackTrace();
            System.out.println("Error, no such file nor directory exists.");
        }
        /*If the user is attempting to delete a directory without it being empty, throw an error*/
        catch(DirectoryNotEmptyException e){
            e.printStackTrace();
            System.out.println("Error, directory being deleted is not empty");
        }
        /*If the user encounters an IOException, throw an error*/
        catch(IOException e){
            e.printStackTrace();
        }
        /*Otherwise, print deletion successful*/
        System.out.println("Deletion Successful");
    }

}