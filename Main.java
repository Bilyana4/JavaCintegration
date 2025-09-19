import java.io.FileNotFoundException;
import java.util.Scanner; // Import the Scanner class to read text files

public class Main {
    public static int TotalRows;
    protected static float[] Roots;

    public static void main(String[] args) throws FileNotFoundException {

        Scanner Scanner = new Scanner(System.in);

        System.out.println("GET FILE PATH OF UNKNOWNS: ");

        String unknownInputFilePath = Scanner.nextLine();

        int unknownLinesCount = FileOperations.Opener(unknownInputFilePath);

        if(unknownLinesCount != -1){
            System.out.println("\nSuccessfully opened file!");
        }else{
            System.out.println("\nCould not open the file!");
        }


        System.out.println("\nGET FILE PATH OF CONSTANTS: ");

        String constantsInputFilePath = Scanner.nextLine();

        int constLinesCount = FileOperations.Opener(constantsInputFilePath);

        if(constLinesCount != -1){
            System.out.println("\nSuccessfully opened file!");
        }else{
            System.out.println("\nCould not open the file!");
            System.out.println("Invalid input!");
            System.exit(0);
        }


        //TotalRows = NumbersOperations.Rows;
        //System.out.println("\n" + TotalRows);


        //извиква се метод от нов клас, който ще осигури свързаността между всички класове дотук и C кодът за решаване на матрици
        //на методът се подават аргументи- попълнената матрица и броят редове(+1 за колони-няма да е отделен аргумент)
        //класът ще бъде този, който ще претърпи JNI оформление и преработка, за да се използва в него C dll
        //това може да е преди създаването на файл за корените на матрицата
        //първо се създава новият клас с подходящия метод след това C кодът, извършват необходимите за JNI действия върху
        // тези две части, след това методът се обвързва с останалата част от проекта
        // C кодът трябва да върне едномерен масив-корените на матрицата, които ще бъдат записани във файла

        int result = GetResult();

        if(result == 0){
            System.out.println("\nNative method returned roots of the matrix: ");
            for (float el : Roots) {
                System.out.println(el);
            }
        }else {
            System.out.println("\nNative method did not return a result or encountered an error!");
System.exit(0);
        }


        System.out.println("\nGET FILE PATH FOR EXIT FILE TO WRITE DOWN ROOTS: ");

        String directoryFilePath = Scanner.nextLine();

        int isCreated = FileOperations.CreateFile(directoryFilePath);

        if(isCreated == 1){
            System.out.println("\nFile successfully created!");
        }else if(isCreated == 0){
            System.out.println("\nFile already exist!");
        }else{
            System.out.println("\nCould not create file!");
        }


        int savedFlag = FileOperations.WriteRootsInFile(directoryFilePath);

        if(savedFlag == 0){
            System.out.println("\nSuccessfully wrote to the file!");
        }else{
            System.out.println("\nCould not write to the file!");
        }
        Scanner.close();
    }

    private static int GetResult(){
        LibOperations executor = new LibOperations();

        int Size = TotalRows;


        Roots = executor.MatrixCalculation(NumbersOperations.matrix, Size);

        if (Roots == null) {
            return 1;
        }

        return 0;
    }
}
