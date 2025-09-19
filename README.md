# Java-Cintegration

Авторски права
© Биляна Билянова 2025.

Всички права запазени. Този код е публикуван само с демонстрационна/образователна цел.

Забранява се копирането, разпространението, модифицирането или използването на съдържанието без изрично писмено разрешение.

*Бележка: Всички .java и .c файлове са написани и съхранени в Notepad версия 11.2507.26.0 . За изпълнението на всички действия, свързани с компилиране, построяване (build), свързване (link) и изпълнение на програмата, се използва Command Prompt.*

<img width="920" height="900" alt="Main class (1)" src="https://github.com/user-attachments/assets/d629f958-af3f-421d-898e-c4067fddda35" />

1. Чрез командата „javac“ се компилират изходните файлове, които съдържат декларации на Java модулитеим, в .class файлове, които се изпълняват от Java VM.
    javac Main.java FileOperations.java NumberOperations.java LibOperations.java

2. Трябва да бъде генериран JNI заглавен файл - LibOperations.h, който се използва в LibOperations.c. Той предоставя декларация на функциите, които ще бъдат използвани от Java, и осъществява Java свързването.
Използва се командата „javac -h /dir/ /fileName/.java“
    javac -h /dir/ LibOperations.java

3. Построяването на динамична библиотека
Ако приложението осъществява достъп до native библиотеки чрез Java Native Interface (JNI) (ако за създаването първоначално е използвано стандартния Command Prompt), трябва те да се надстроят, защото 32-битова JVM може да използва само 32-битови библиотеки. По подобен начин, 64-битова JVM може да използва само 64-битови оригинални библиотеки (в случая е изпобзвана 64-битова JVM, затова и е необходима 64-битова библиотека).
32-битовата виртуална машина на Java не е широко използвана, затова друг подход е компилиране на 64-битова библиотека. Това може да се осъществи като се използвва x64 Native Tools Command Prompt , конфигуриран от Visual Studio специално за подобни цели. При изпълнението на командата за генериране на DLL трябва да се добавят пътища към директории, в които се намират заглавните файлове jni.h и jni_md.h, използва се -I.
   cl -LD -I"C:\Program Files\Java\jdk-23\include" -I"C:\Program Files\Java\jdk-23\include\win32" -I. LibOperations.c /Fe:Gauss.dll
В Java класа LibOperations изрично се търси библиотеката Gauss.dll, затова резултат от командата за създаването е опоменат - /Fe:Gauss.dll

4. Link and Run
Към този момент проектът се състои от две готови компоненти – Java .class файлове и DLL. За да може JVM да открие native библиотеката (в случай, че не се намира в същата директория) се използва командата:
    java -Djava.library.path=/dir/ /JavafileName/
JavafileName се явява главният метод (входната точка на Java програмата) - в случая - Main (Main.java)
Тя задава пътят към директория bin, което позволява на виртуалната машина на Java да намери динамичната библиотека. В края се допълва името на Java метода, който се явява входна точка на програмата. При изпълнение на тази команда програмата стартира и трябва да бъдат подадени първо път към матрица с коефициентите пред неизвестните, после път към стълб от свободните членове на СЛУ, накрая - път към директория, в която да се създаде изходен файл, в който да се запишат изчислените корени на СЛУ.


--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

*Note: All .java and .c files are written and stored in Notepad version 11.2507.26.0 . Command Prompt is used to perform all actions related to compiling, building, linking and running the program.*

<img width="1920" height="1080" alt="Main class (1)" src="https://github.com/user-attachments/assets/d629f958-af3f-421d-898e-c4067fddda35" />

1. The "javac" command compiles the source files, which contain declarations of Java modules, into .class files, which are executed by the Java VM.
javac Main.java FileOperations.java NumberOperations.java LibOperations.java

2. A JNI header file - LibOperations.h, which is used in LibOperations.c, must be generated. It provides a declaration of the functions that will be used by Java, and performs Java linking.
The command "javac -h /dir/ /fileName/.java" is used
javac -h /dir/ LibOperations.java

3. Building a dynamic library
If the application accesses native libraries through the Java Native Interface (JNI) (if the standard Command Prompt was used initially for creation), they must be upgraded, because a 32-bit JVM can only use 32-bit libraries. Similarly, a 64-bit JVM can only use 64-bit native libraries (in this case, a 64-bit JVM is used, so a 64-bit library is needed).
The 32-bit Java virtual machine is not widely used, so another approach is to compile a 64-bit library. This can be done using the x64 Native Tools Command Prompt , configured by Visual Studio specifically for such purposes. When executing the command to generate DLL, paths to directories containing the header files jni.h and jni_md.h must be added, using -I.
cl -LD -I"C:\Program Files\Java\jdk-23\include" -I"C:\Program Files\Java\jdk-23\include\win32" -I. LibOperations.c /Fe:Gauss.dll
The Java class LibOperations explicitly searches for the Gauss.dll library, so the result of the creation command is mentioned - /Fe:Gauss.dll

4. Link and Run
At this point, the project consists of two ready-made components – Java .class files and a DLL. In order for the JVM to find the native library (in case it is not in the same directory), the command is used:
java -Djava.library.path=/dir/ /JavafileName/
JavafileName is the main method (the entry point of the Java program) - in this case - Main (Main.java)
It sets the path to the bin directory, which allows the Java virtual machine to find the dynamic library. At the end, the name of the Java method is added, which is the entry point of the program. When this command is executed, the program starts and must be passed first a path to a matrix with the coefficients in front of the unknowns, then a path to a column of the free members of the LSA, finally - a path to a directory in which to create an output file in which to write the calculated roots of the LSA.




