import java.util.Scanner; // Import the Scanner class to read text files

public class Main {
    public static void main(String[] args) {

        Scanner Scanner = new Scanner(System.in);

        System.out.println("GET FILE PATH: ");

        String unkownInputsFilePath = Scanner.nextLine();

        int unknownLinesCount = FileOperations.Opener(unkownInputsFilePath);

        if(unknownLinesCount != -1){
            System.out.println("Successfully opened file!");
        }else{
            System.out.println("Could not open the file!");
        }


        System.out.println("GET FILE PATH: ");

        String constantsInputsFilePath = Scanner.nextLine();

        int constLinesCount = FileOperations.Opener(constantsInputsFilePath);

        if(constLinesCount != -1){
            System.out.println("Successfully opened file!");
        }else{
            System.out.println("Could not open the file!");
        }

        if(unknownLinesCount != constLinesCount){
            System.out.println("Invalid input! Could not solve this problem!");
        }


        System.out.println("GET DIRECTORY FOR EXIT FILE PATH: ");

        String directoryFilePath = Scanner.nextLine();

        int isCreated = FileOperations.CreateFile(directoryFilePath);

        if(isCreated == 1){
            System.out.println("File successfully created!");
        }else if(isCreated == 0){
            System.out.println("File already exist!");
        }else{
            System.out.println("Could not create file!");
        }

        Scanner.close();
    }
}