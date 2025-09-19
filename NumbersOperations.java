public class NumbersOperations {
    public static float[][] matrix = new float[FileOperations.size][FileOperations.size + 1];
    private static int currentRow = 0;

    public static float[] CreateArrayUnknowns(String line, int size){
        String[] numbers = line.split(" ");
        float[] array;
        array = new float[size];
        int count = 0;

        for (int i = 0; i < size && i < numbers.length; i++) {
            array[i] = Float.parseFloat(numbers[i]);
            count++;
        }

        if(count != size){
            System.out.println("Invalid input!");
            System.exit(0);
        }

//        for (int i = 0; i < size; i++) {
//            System.out.println(array[i]);
//        }

        return array;
    }

    public static float CreateArrayConstants(String line){
        float number = Float.parseFloat(line);

        return number;
    }

    public static void CreateMatrix(float[] array, int flag){
        if(array.length != FileOperations.size){
            System.out.println("Invalid input!");
            System.exit(0);
        }

        int lastColIndex = matrix[0].length - 1;

        if (flag == 1) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][lastColIndex] = array[i];
            }
            System.out.println("\nCurrent matrix state:\n");
            PrintMatrix();
        } else if (currentRow < matrix.length) {
            // Копират се стойности вместо да се присвояват директно (по-добра практика)
            for (int j = 0; j < array.length; j++) {
                matrix[currentRow][j] = array[j];
            }
            currentRow++;
        }
    }

//    private static void FillMatrix(int[] array){
//        for(int i = 0; i < matrix.length; i++){
//
//        }
//    }

    private static void PrintMatrix(){

        for (float[] row : matrix) {
            for (float val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }

    }

}
