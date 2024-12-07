import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Lab1KarapinkaKI306 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean validInput = false;

        while (!validInput) {
            try {
                // Get matrix size from user
                System.out.print("Enter the size of the square matrix: ");
                int size = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                if (size <= 0) {
                    System.out.println("Error: Size must be positive. Please try again.\n");
                    continue;
                }

                // Get fill symbol from user
                System.out.print("Enter the symbol to fill the pattern (single character): ");
                String input = scanner.nextLine();

                if (input.length() != 1) {
                    System.out.println("Error: Please enter exactly one character. Restarting...\n");
                    continue;
                }

                char fillSymbol = input.charAt(0);

                // Generate the matrix
                char[][] matrix = generateMatrix(size, fillSymbol);

                // Display the matrix on the screen
                System.out.println("\nGenerated Matrix:");
                displayMatrix(matrix, size);

                // Write the matrix to a text file
                writeMatrixToFile(matrix, size, "output.txt");

                validInput = true; // Exit the loop if we reach this point

            } catch (java.util.InputMismatchException e) {
                System.out.println("Error: Please enter a valid number for the size. Restarting...\n");
                scanner.nextLine(); // Clear the invalid input
            }
        }

        scanner.close();
    }

    /**
     * Generates a jagged array with the specific pattern of custom fill symbol.
     * Each row only contains the necessary elements.
     * @param size The size of the square matrix.
     * @param fillSymbol The character to use for filling the pattern.
     * @return A 2D jagged char array representing the matrix.
     */
    private static char[][] generateMatrix(int size, char fillSymbol) {
        char[][] matrix = new char[size][];
            
        for (int i = 0; i < size; i++) {
            // Calculate the number of fill symbols needed for this row
            int fillCount = Math.min(i, size - 1 - i) + 1;
            matrix[i] = new char[fillCount];

            // Fill the row with the symbol
            for (int j = 0; j < fillCount; j++) {
                matrix[i][j] = fillSymbol;
            }
        }
        return matrix;
    }

    /**
     * Displays the jagged array on the console with proper spacing.
     * @param matrix The 2D jagged char array to display.
     * @param size The original size of the matrix for proper spacing.
     */
    private static void displayMatrix(char[][] matrix, int size) {
        for (int i = 0; i < matrix.length; i++) {
            // Print leading spaces
            int leadingSpaces = size - matrix[i].length;
            for (int j = 0; j < leadingSpaces; j++) {
                System.out.print("  ");
            }
            // Print the actual symbols
            for (char val : matrix[i]) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    /**
     * Writes the jagged array to a text file with proper spacing.
     * @param matrix The 2D jagged char array to write.
     * @param size The original size of the matrix for proper spacing.
     * @param filename The name of the file to write to.
     */
    private static void writeMatrixToFile(char[][] matrix, int size, String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (int i = 0; i < matrix.length; i++) {
                // Write leading spaces
                int leadingSpaces = size - matrix[i].length;
                for (int j = 0; j < leadingSpaces; j++) {
                    writer.print("  ");
                }
                // Write the actual symbols
                for (char val : matrix[i]) {
                    writer.print(val + " ");
                }
                writer.println();
            }
            System.out.println("Matrix has been written to " + filename);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
}