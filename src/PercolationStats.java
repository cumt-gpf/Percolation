import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * Created by GPF on 2017/4/23.
 */
public class PercolationStats {
    private int n;
    private int trials;
    private double[] result;
    public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid
    {
        if(n <=0 || trials <=0)
            throw new IllegalArgumentException(" ");
        this.n = n;
        this.trials = trials;
        this.result = new double[trials];
        for(int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            while(!percolation.percolates()) {
                int random = StdRandom.uniform(n * n);
                int row = random / n + 1;
                int col = random % n + 1;
                if(percolation.isOpen(row, col)) continue;
                percolation.open(row, col);
            }
            result[i] = (double)percolation.numberOfOpenSites() / (n * n);
        }
    }
    public double mean()                          // sample mean of percolation threshold
    {
        return StdStats.mean(result);
    }
    public double stddev()                        // sample standard deviation of percolation threshold
    {
        return StdStats.stddev(result);
    }
    public double confidenceLo()                  // low  endpoint of 95% confidence interval
    {
        return mean() - (1.96 * stddev()) / Math.sqrt((double)trials);
    }
    public double confidenceHi()                  // high endpoint of 95% confidence interval
    {
        return mean() + (1.96 * stddev()) / Math.sqrt((double)trials);
    }
    public static void main(String[] args)        // test client (described below)
    {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats percolationStats = new PercolationStats(n, trials);
        StdOut.println("mean                    = " + percolationStats.mean());
        StdOut.println("stddev                  = " + percolationStats.stddev());
        StdOut.println("95% confidence interval = [" + percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi() + "]");
    }

}
