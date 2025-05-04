
import java.util.Scanner;

class A_star_WaterJugProblem {

    public static int getGCD(int x, int y) {
        if (y == 0) {
            return x;
        }
        return getGCD(y, x % y);
    }

    public static int pourWater(int fromCapacity, int toCapacity, int d) {
        int fromCap = fromCapacity;
        int toCap = 0;
        int reqStep = 1;

        while (fromCap != d && toCap != d) {
            int maxPour = Math.min(fromCap, toCapacity - toCap);
            toCap += maxPour;
            fromCap -= maxPour;
            reqStep++;

            if (fromCap == d || toCap == d) {
                break;
            }

            if (fromCap == 0) {
                fromCap = fromCapacity;
                reqStep++;
            }

            if (toCap == toCapacity) {
                toCap = 0;
                reqStep++;
            }
        }
        return reqStep;
    }

    public static int findMinSteps(int i, int j, int d) {
        // Fix: Proper swapping of i and j when i > j
        if (i > j) {
            int temp = i;
            i = j;
            j = temp;  // Fixed: was j = i; which is incorrect
        }

        if (d > j) {
            return -1;
        }

        if ((d % getGCD(j, i)) != 0) {
            return -1;
        }

        return Math.min(pourWater(j, i, d), pourWater(i, j, d));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the size of Jug1 in liters");
        int i = sc.nextInt();

        System.out.println("Enter the size of Jug2 in liters");
        int j = sc.nextInt();

        System.out.println("Enter the amount of water you want to measure:");
        int d = sc.nextInt();

        sc.close();

        int steps = findMinSteps(i, j, d);
        if (steps == -1) {
            System.out.println("Solution not possible with the given jug sizes");
        } else {
            System.out.println("Minimum number of steps required to measure water is " + steps);
        }
    }
}
