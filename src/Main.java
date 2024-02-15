import java.util.Scanner;
import java.util.Random;

public class Main {
    private static final Scanner in = new Scanner(System.in);
    private static int[][] matrix;
    private static int[][] matrix2;
    private static int[][] matrix3;
    private static int size;

    public static void main(String[] args) {

            while (true) {
            System.out.println("Меню:");
            System.out.println("1. Ввод данных вручную");
            System.out.println("2. Генерация случайных данных");
            System.out.println("3. Выполнение алгоритма");
            System.out.println("4. Вывод результата");
            System.out.println("5. Завершение работы программы");

                    int choice = 0;
                    if (in.hasNextInt()) {
                        choice = in.nextInt();
                    } else {
                        in.next();
                    }

                    switch (choice) {
                        case 1:
                            try {
                                inputMatrix();
                            } catch (Exception e) {
                                System.out.println("Введено недопустимое значение");
                                matrix = matrix2 = matrix3 = null;
                                in.nextLine(); // Очистка буфера ввода
                            }
                            break;
                        case 2:
                            generationMatrix();
                            break;
                        case 3:
                            if (matrix == null || matrix2 == null){
                                System.out.println("Данные не введены");
                            } else {
                                sumMatrix();
                                System.out.println("Алгоритм выполнен");
                            }
                            break;
                        case 4:
                            if (matrix3 == null) {
                                System.out.println("Результат не вычислен");
                            } else {
                                showResult();
                            }
                            break;
                        case 5:
                            System.out.println("Программа завершена.");
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Некорректный выбор");
                    }
            }
    }
    private static int calculateDeterminant(int[][] matrix) {
        if (matrix.length == 1) { // Базовый случай: матрица 1x1
            return matrix[0][0];
        }

        int determinant = 0;
        for (int i = 0; i < matrix.length; i++) {
            int[][] minor = new int[matrix.length - 1][matrix.length - 1];
            for (int j = 1; j < matrix.length; j++) {
                System.arraycopy(matrix[j], 0, minor[j - 1], 0, i);
                System.arraycopy(matrix[j], i + 1, minor[j - 1], i, matrix.length - i - 1);
            }
            determinant += (int) Math.pow(-1, i) * matrix[0][i] * calculateDeterminant(minor);
        }
        return determinant;
    }
    private static void inputMatrix() throws Exception{
        System.out.println("Введите размер матрицы");
        matrix = matrix2 = matrix3 = null;

        size = in.nextInt();
        if (size < 1 || size > 9) {
            throw new Exception();
        }

        matrix = new int[size][size];
        matrix2 = new int[size][size];

        System.out.println("Формирование первой матрицы:");
        makeMatrix(matrix);
        System.out.println("Формирование второй матрицы");
        makeMatrix(matrix2);
    }
    private static void makeMatrix(int[][] matrix) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print("Элемент [" + i + "][" + j + "]: ");
                matrix[i][j] = in.nextInt();
            }
        }
    }
    private static void generationMatrix() {
       Random random = new Random();

       matrix3 = null;
       int randomSize = random.nextInt(9) + 1;

       matrix = new int[randomSize][randomSize];
       matrix2 = new int[randomSize][randomSize];

       for (int i = 0; i < randomSize; i ++) {
           for (int j = 0; j < randomSize; j ++){
               matrix[i][j] = random.nextInt(300) - 150;
               matrix2[i][j] = random.nextInt(300) - 150;
           }
       }
       System.out.println("Первая матрица;");
        printMatrix(matrix);
        System.out.println("Вторая матрица");
        printMatrix(matrix2);
    }
    private static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    private static void sumMatrix() {
        matrix3 = new int[matrix.length][matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix3[i][j] = matrix[i][j] + matrix2[i][j];
            }
        }
    }
    private static void showResult() {
        int determinant;
        sumMatrix();
        determinant = calculateDeterminant(matrix3);
        System.out.println("Сумма матриц:");
        printMatrix(matrix3);
        System.out.println("Определитель матрицы:");
        System.out.println(determinant);
    }
}

