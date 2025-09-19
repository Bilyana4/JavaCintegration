import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class FileOperations {
    static int size = 0;

    public static int Opener(String pathToFile) {
        int linesRead = 0;

        try{
            File file = new File(pathToFile);
            Scanner inputFileRead = new Scanner(file);
            Scanner inputFileDec = new Scanner(file);

            linesRead = Reader(inputFileRead);
            DeconstructFile(inputFileDec, linesRead);

            return linesRead;
        } catch (Exception e){
            return -1;
        }

    }

    private static int Reader(Scanner File){
        int lines = 0;

        while (File.hasNextLine()) {
            String line = File.nextLine();

            System.out.println(line);
            lines++;
        }

        File.close();
        return lines;
    }

    private static void DeconstructFile(Scanner File, int lines){
        int i = 0;
        float[] constants = new float[lines];

        size = lines;

        while (File.hasNextLine()){

            String line = File.nextLine();

            if(line.contains(" ")){
                // За матрица на неизвестните
                float[] array = NumbersOperations.CreateArrayUnknowns(line, lines);
                NumbersOperations.CreateMatrix(array, 0);
            }else{
                // За корените на уравненията
                constants[i] = NumbersOperations.CreateArrayConstants(line);
                //System.out.println(constants[i]);
                i++;
            }

        }

        NumbersOperations.CreateMatrix(constants, 1);
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

    public static int WriteRootsInFile(String directoryPath) throws FileNotFoundException {
        try {
            PrintWriter outputFile = new PrintWriter(directoryPath +"roots.txt");

            int len = (Main.Roots).length;

            for(int i = 0; i < len; i++){
                outputFile.println(Main.Roots[i]);
            }

            outputFile.close();

            return 0;
        } catch (IOException e) {
            System.out.println("An error occurred!");
            e.printStackTrace();

            return 1;
        }
    }
}
