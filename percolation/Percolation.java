import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Created by GPF on 2017/4/23.
 */
public class Percolation {
    private int[][] grids;
    private int n;
    private WeightedQuickUnionUF weightedQuickUnionUF;
    private int numbers;
    private boolean isPercolation;
    private int index(int row, int col) {
        return (row - 1) * n + (col - 1);
    }
    public Percolation(int n) { // create n-by-n grid, with all sites blocked
        this.n = n;
        if(n <= 0) throw new IllegalArgumentException("n <= 0");
        grids = new int[n][n];
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                grids[i][j] = 0;

        this.weightedQuickUnionUF = new WeightedQuickUnionUF(n * n);
        numbers = 0;
        isPercolation = false;
    }

    public void open(int row, int col)    // open site (row, col) if it is not open already
    {
        if(row < 1 || row > n || col < 1 || col > n) throw new IndexOutOfBoundsException(" ");
        grids[row - 1][col - 1] = 1;
        numbers++;
        int myindex = index(row, col);

        if(row != 1 && isOpen(row - 1, col)) {
            weightedQuickUnionUF.union(myindex, index(row - 1, col));
        }
        if(row != n && isOpen(row + 1, col)) {
            weightedQuickUnionUF.union(myindex, index(row + 1, col));
        }
        if(col != 1 && isOpen(row, col - 1)) {
            weightedQuickUnionUF.union(myindex, index(row, col - 1));
        }
        if(col != n && isOpen(row, col + 1)) {
            weightedQuickUnionUF.union(myindex, index(row, col + 1));
        }

        if(row == n && isFull(row, col)) {
            isPercolation = true;
        }
    }
    public boolean isOpen(int row, int col)  // is site (row, col) open?
    {
        if(row < 1 || row > n || col < 1 || col > n) throw new IndexOutOfBoundsException(" ");
        return grids[row - 1][col - 1] == 1;
    }
    public boolean isFull(int row, int col)  // is site (row, col) full?
    {
        //full指的是连接到最顶行就是full，若n是5的话，连接到0,1,2,3,4就行
        if(row < 1 || row > n || col < 1 || col > n) throw new IndexOutOfBoundsException(" ");
        int index = (row - 1) * n + (col - 1);
        if(grids[row - 1][col - 1] == 1) {
            for(int i = 0; i <= n - 1; i++) {
                if(weightedQuickUnionUF.connected(index, i))
                    return true;
            }
        }
        return false;
    }
    public int numberOfOpenSites()       // number of open sites
    {
        return numbers;
    }
    public boolean percolates()              // does the system percolate?
    {
        return isPercolation;
    }
    public static void main(String[] args)   // test client (optional)
    {

    }
}
