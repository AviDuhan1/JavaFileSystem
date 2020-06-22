public class testMain extends fileSystem{
    public static void main(String args[]){
        setDirectory("C:\\Users\\Avi Duhan\\Desktop\\JavaFileSystem\\");
        fileRead("testText.txt");
        fileWrite("I hope that this will work", "testText.txt");
        fileWrite("This is a new file with some new text.", "newText.txt");
        fileCreate("toBeDeleted.txt");
        fileDelete("toBeDeleted.txt");
    }
}
