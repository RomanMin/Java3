package HomeWork4;

import org.slf4j.LoggerFactory;

import java.util.Scanner;
import java.util.logging.Logger;

public class TriangleSquare {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите длину стороны 1: ");
        double a = in.nextInt();
        System.out.print("Введите длину стороны 2: ");
        double b = in.nextInt();
        System.out.print("Введите длину стороны 3: ");
        double c = in.nextInt();
        System.out.println("Площадь Вашего треугольника: " + area(a, b, c));
    }
    public static void squareIsValid(double a, double b, double c) throws NotTriangle {
        if( a + b > c &&
                b + c > a &&
                a + c > b) ;
        else throw new NotTriangle();
    }
    public static double area(double a, double b, double c) {
        double area = 0;
        double s = (a + b + c)/2;
        area =  Math.sqrt(s*(s - a)*(s - b)*(s - c));
        return area;
    }


}
