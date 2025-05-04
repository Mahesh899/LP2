
import java.util.Arrays;

class Job {

    int id, profit, deadline;

    public Job(int id, int profit, int deadline) {
        this.id = id;
        this.profit = profit;
        this.deadline = deadline;
    }
}

class JobScheduling {

    public static int[] scheduleJobs(Job[] jobs, int n) {
        Arrays.sort(jobs, (a, b) -> b.profit - a.profit);

        int maxDeadline = 0;
        for (Job job : jobs) {
            if (job.deadline > maxDeadline) {
                maxDeadline = job.deadline;
            }
        }

        int[] result = new int[maxDeadline];
        Arrays.fill(result, -1);
        int totalProfit = 0;

        for (Job job : jobs) {
            for (int j = Math.min(maxDeadline - 1, job.deadline - 1); j >= 0; j--) {
                if (result[j] == -1) {
                    result[j] = job.id;
                    totalProfit += job.profit;
                    break;
                }
            }
        }

        System.out.println("Scheduled jobs:");
        for (int i = 0; i < maxDeadline; i++) {
            if (result[i] != -1) {
                System.out.print(result[i] + " ");
            }
        }
        System.out.println("\nTotal Profit: " + totalProfit);
        return result;
    }

    public static void main(String[] args) {
        Job[] jobs = {
            new Job(1, 35, 3),
            new Job(2, 30, 4),
            new Job(3, 25, 4),
            new Job(4, 20, 2),
            new Job(5, 15, 3),
            new Job(6, 12, 1),
            new Job(7, 5, 2)
        };
        int n = jobs.length;
        scheduleJobs(jobs, n);
    }
}
