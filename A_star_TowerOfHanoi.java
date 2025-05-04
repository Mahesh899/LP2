
public class A_star_TowerOfHanoi {
// Recursive function to solve Tower of Hanoi puzzle

    public static void solve(int n, char source, char target, char auxiliary) {
        if (n == 1) {
            System.out.println("Move disk 1 from rod " + source + " to rod " + target);
            return;
        }
// Move n-1 disks from source to auxiliary, using target as auxiliary
        solve(n - 1, source, auxiliary, target);
// Move the nth disk from source to target
        System.out.println("Move disk " + n + " from rod " + source + " to rod " + target);
// Move n-1 disks from auxiliary to target, using source as auxiliary
        solve(n - 1, auxiliary, target, source);
    }
// Driver function

    public static void main(String[] args) {
        int n = 3; // Number of disks
        solve(n, 'A', 'C', 'B'); // A, B and C are names of rods
    }
}
