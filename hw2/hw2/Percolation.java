package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[][] matrix;
    private WeightedQuickUnionUF rootArr;
    private boolean hasPercolated;
    public Percolation(int N) {
        /// in my matrix, 0s represent blocked cells, 1s represent open, 2s represent open + full
        if (N <= 0 ) {
            throw new IllegalArgumentException();
        }
        matrix = new int[N][N];
        rootArr = new WeightedQuickUnionUF(N*N);
        hasPercolated = false;
    }
    private int matrixToUnionIndex(int row, int col) {
        return (row * matrix.length) + col;
    }

    public void open(int row, int col) {
        if ((row < 0) || (row >= matrix.length) || (col < 0) || (col >= matrix.length)) {
            throw new IndexOutOfBoundsException();
        }
        boolean openTop = false;
        boolean openBot = false;
        if (!isOpen(row, col)) {

            int rootArrIndex1 = matrixToUnionIndex(row, col);
            if (row == 0) {
                matrix[row][col] = 2;
                openTop = true;
            } else if (row == matrix.length - 1) {

            }
            else {
                matrix[row][col] = 1;
            }
            // top row is [row-1, col]
            if ((row - 1 >= 0) && (isOpen(row - 1, col))) {
                int rootArrIndex2 = matrixToUnionIndex(row - 1, col); //parent!!
                if (openTop) {
                    int parentIndex = rootArr.find(rootArrIndex2);
                    rootArr.parent[rootArrIndex1] = parentIndex;
                } else {
                    rootArr.union(rootArrIndex1, rootArrIndex2);
                }

            }
            // top row is [row, col]
            if ((row + 1 < matrix.length) && (isOpen(row + 1, col))) {
                int rootArrIndex2 = matrixToUnionIndex(row + 1, col);
                if (openTop) {
                    int parentIndex = rootArr.find(rootArrIndex2);
                    rootArr[parentIndex] = rootArrIndex1;
                } else {
                    rootArr.union(rootArrIndex1, rootArrIndex2);
                }
            }

            if ((col + 1 < matrix.length) && (isOpen(row, col + 1))) {
                int rootArrIndex2 = matrixToUnionIndex(row, col + 1);
                rootArr.union(rootArrIndex1, rootArrIndex2);
            }
            if ((col - 1 >= 0) && (isOpen(row, col - 1))) {
                int rootArrIndex2 = matrixToUnionIndex(row, col - 1);
                rootArr.union(rootArrIndex1, rootArrIndex2);
            }
        }
    }
    public boolean isOpen(int row, int col) {
        return (matrix[row][col] != 0);
    }
    public boolean percolates() {
        return hasPercolated;
    }







}