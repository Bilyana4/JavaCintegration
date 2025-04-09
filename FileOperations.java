import java.io.File;  // Import the File class
import java.util.Scanner;

public class FileOperations {
    public static int Opener(String pathToFile) {
        int linesRead = 0;

        try{
            File file = new File(pathToFile);
            Scanner inputFile = new Scanner(file);

            linesRead = Reader(inputFile);
            return linesRead;
        } catch (Exception e){
            return -1;
        }

    }

    private static int Reader(Scanner Reader){
        int lines = 0;

        while (Reader.hasNextLine()) {
            String line = Reader.nextLine();
            System.out.println(line);
            lines++;
        }

        Reader.close();
        return lines;
    }

    public static int CreateFile(String directoryPath){
        try{
            String exitFilePath = directoryPath +'\\' + "roots.txt";

            File newExitFile = new File(exitFilePath);

            if(newExitFile.createNewFile()){
                return 1;
            }else if(newExitFile.exists()){
                return 0;
            }
        } catch (Exception e) {
            return -1;
        }
        return -1;
    }
}
