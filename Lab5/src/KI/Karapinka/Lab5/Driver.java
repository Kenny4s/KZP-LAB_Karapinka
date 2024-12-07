package KI.Karapinka.Lab5;

import java.util.Scanner;
import java.io.*;

/**
 * Class <code>EquationsApp</code> Implements driver for Equations class
 * @version 1.0
 */
public class Driver {
    /**
     * @param args
     */
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        FunctionIO func = new FunctionIO();

        try (Scanner s = new Scanner(System.in)) {
            System.out.print("Введіть X: ");
            double data = s.nextDouble();
            func.calculate(data);
        }

        System.out.println("Результат обчислення: " + func.getResult());
        func.writeResTxt("textRes.txt");
        func.writeResBin("BinRes.bin");

        func.readResBin("BinRes.bin");
        System.out.println("Результат в бінарному файлі: " + func.getResult());

        func.readResTxt("textRes.txt");
        System.out.println("Результат в текстовому файлі: " + func.getResult());
    }
}
