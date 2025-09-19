public class LibOperations { //Class wrapper за native lib

    // Декларация на native method
    public native float[] MatrixCalculation(float[][] matrix, int size);

    // Зареждане на dll lib
    static {
        System.loadLibrary("Gauss");
    }

}

