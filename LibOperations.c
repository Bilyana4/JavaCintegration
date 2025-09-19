#include <jni.h>
#include <stdio.h>
#include <stdlib.h>
#include "LibOperations.h"

JNIEXPORT jfloatArray JNICALL Java_LibOperations_MatrixCalculation
  (JNIEnv *env, jobject obj, jobjectArray jmatrix, jint size) {

    jsize rows = (*env)->GetArrayLength(env, jmatrix);
   
    int i, j, k;
    float sum = 0.0, c = 0.0;

    printf("\nMatrix received from Java (rows=%d):\n", rows);
    for (i = 0; i < rows; i++) {
        jfloatArray oneDArray = (jfloatArray)(*env)->GetObjectArrayElement(env, jmatrix, i);
        if (oneDArray == NULL) {
            printf("\nRow %d is NULL!\n", i);
            continue;
        }

        jsize cols_in_row = (*env)->GetArrayLength(env, oneDArray);
        jfloat *floatElements = (*env)->GetFloatArrayElements(env, oneDArray, NULL);
        if (floatElements == NULL) {
            printf("\nRow %d elements could not be accessed!\n", i);
            continue;
        }

        printf("    %d: ", i);
        for (j = 0; j < cols_in_row; j++) {
            printf("%f\t", floatElements[j]);
        }
        printf("\n");

        (*env)->ReleaseFloatArrayElements(env, oneDArray, floatElements, 0);
    }

    int n = rows;
    int cols = n + 1;   
    // Allocate memory for 2D array A
    float **A = (float**)malloc(rows * sizeof(float*));
    if (A == NULL) {
        printf("\nDEBUG: Failed to allocate memory for A!\n");
        return NULL;
    }
    for (i = 0; i < rows; i++) {
        A[i] = (float*)malloc(cols * sizeof(float));
        if (A[i] == NULL) {
            printf("\nDEBUG: Failed to allocate memory for A[%d]!\n", i);
            return NULL;
        }
    }

    // Allocate memory for solution vector x
    float *x = (float*)malloc(rows * sizeof(float));
    if (x == NULL) {
        printf("\nDEBUG: Failed to allocate memory for x!\n");
        return NULL;
    }

    // Copy from Java 2D array (jmatrix) into C array A
    for (i = 0; i < rows; i++) {
        jfloatArray oneDArray = (jfloatArray)(*env)->GetObjectArrayElement(env, jmatrix, i);
        if (oneDArray == NULL) {
            printf("\nDEBUG: Null row encountered at %d!\n", i);
            return NULL;
        }

        jfloat *floatElements = (*env)->GetFloatArrayElements(env, oneDArray, NULL);
        if (floatElements == NULL) {
            printf("\nDEBUG: Failed to get float array elements for row %d!\n", i);
            return NULL;
        }

        for (j = 0; j < cols; j++) {
            A[i][j] = floatElements[j];
        }

        (*env)->ReleaseFloatArrayElements(env, oneDArray, floatElements, 0);
    }

    // Gaussian elimination (forward elimination)
    for (j = 0; j < rows - 1; j++) {
        for (i = j + 1; i < rows; i++) {
            if (A[j][j] == 0) {
                printf("\nSingular matrix (division by zero) at pivot %d!\n", j);
                return NULL;
            }
            c = A[i][j] / A[j][j];
            for (k = j; k < cols; k++) {
                A[i][k] -= c * A[j][k];
            }
        }
    }

    // Back substitution
    if (A[n-1][n-1] == 0) {
        printf("\nDivision by zero at last pivot!\n");
        return NULL;
    }
    x[n-1] = A[n-1][n] / A[n-1][n-1];

    for (i = n - 2; i >= 0; i--) {
        sum = 0;
        for (j = i + 1; j < n; j++) {
            sum += A[i][j] * x[j];
        }
        if (A[i][i] == 0) {
            printf("\nDivision by zero at back substitution (row %d)!\n", i);
            return NULL;
        }
        x[i] = (A[i][n] - sum) / A[i][i];   // fixed index: A[i][n] not A[i][n+1]
    }

    // Prepare result for Java
    jfloatArray result = (*env)->NewFloatArray(env, n);
    if (result == NULL) {
        printf("\nDEBUG: Failed to allocate jfloatArray result!\n");
        return NULL;
    }
    (*env)->SetFloatArrayRegion(env, result, 0, n, x);

    // Free allocated memory
    for (i = 0; i < rows; i++) {
        free(A[i]);
    }
    free(A);
    free(x);

    return result;
}
