package KI.Karapinka.Lab4;

import java.util.Scanner;
import java.io.PrintWriter;
import java.io.IOException;

/**
 * Class <code>Driver</code> Implements driver for Function class
 * @version 1.0
 */
public class Driver {
    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            PrintWriter fout = new PrintWriter("MyFile.txt");

            try {
                Scanner in = new Scanner(System.in);
                System.out.print("Enter X: ");
                double x = in.nextDouble();

                double result = Function.calculate(x);

                fout.println("Result of y = sin(x) / sin(2x-4) when х = " + x + ": " + result);
                System.out.println("Result of y = sin(x) / sin(2x-4) when х = " + x + ": " + result);

            }
            // Блок перехоплює помилки обчислень виразу
            catch (CalcException e) {
                System.out.print(e.getMessage());
            }
            // Цей блок виконається за будь-яких обставин
            finally {
                fout.flush();
                fout.close();
            }
        }
        catch (IOException e) {
            // Блок перехоплює помилки роботи з файлом навіть якщо вони
            // виникли у блоці finally
            System.out.print("File opening error");
        }
    }
}