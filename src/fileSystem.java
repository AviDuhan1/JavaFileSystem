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

    /*curDir (or current directory) is a global variable which can be set and then all file creation, deletion, reading, writing, etc... will use that file path*/
    private static String curDir = "";

    /*filePath is the path to the working directory you want to edit files in*/
    public static void setDirectory(String filePath){
        curDir = filePath;
    }

    /*Prints whatever curDir is*/
    public static String getDirectory(){
        return curDir;
    }

    /*fileRead reads a given file*/
    /*1.0 reads a file to a variable. The input "fileName" must be the full path to the directory*/
    public static String fileRead(String fileName){
        /*Initialize the text string*/
        String text = "";
        try {
            /*get the name and directory of the file as input*/
            File toRead = new File(curDir + fileName);
            Scanner reader = new Scanner(toRead);
            while (reader.hasNextLine()) {
                text = reader.nextLine();
                System.out.println(text);
            }
            reader.close();
        } catch (FileNotFoundException e) {
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
            FileWriter writer = new FileWriter(curDir + fileName);
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
        /*Creating the new file*/
        try{
            File toCreate = new File(curDir + fileName);
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
            Files.deleteIfExists(Paths.get(curDir + fileName));
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

    /*Moves a given file to a new specified directory and then gets the user back to the original directory*/
    public static void fileMove(String fileName, String newDir){
        /*textContain is the text in the file that is to be moved. Since it will be deleted it needs to be stored somewhere*/
        String textContain = fileRead(fileName);
        /*Delete the old file in the old location*/
        fileDelete(fileName);
        /*Store the current directory so it can be returned to later*/
        String dirContain = curDir;
        /*Set the current directory to be the new directory so it will work with the methods*/
        curDir = newDir;
        /*fileWrite will automatically create a new file if one doesn't exist. The text written is the text stored earlier*/
        fileWrite(textContain, fileName);
        /*Set the current directory back to the original*/
        curDir = dirContain;
    }

    /*createFolder makes a new folder of the title folderName in the current directory*/
    public static void createFolder(String folderName){
        /*Create the new folder*/
        new File(curDir + folderName).mkdir();
    }

}