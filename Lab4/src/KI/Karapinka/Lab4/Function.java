package KI.Karapinka.Lab4;

/**
 * Class <code>Functions</code> implements method for sin(x)/sin(2x-4) expression
 * calculation
 * @version 1.0
 */

public class Function {
    /**
     * Method calculates the sin(x)/sin(2x-4) expression*
     * @param x Angle in degrees
     * @throw CalcException
     * @return result
     */
    public static double calculate(double x) throws CalcException {
        double y;
        double rad = x * Math.PI / 180.0;

        try {
            y = Math.sin(rad) /  Math.sin(2 * rad - 4);

            // Якщо результат не є числом, то генеруємо виключення
            if (Double.isNaN(y) || y == Double.NEGATIVE_INFINITY || y == Double.POSITIVE_INFINITY || x == 114.6)
                throw new ArithmeticException();
        }
        // створимо виключення вищого рівня з поясненням причини
        // виникнення помилки
        catch (ArithmeticException ex) {
            System.out.println(ex.getMessage());
            throw new CalcException();
        }

        return y;
    }
}
