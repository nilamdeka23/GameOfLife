package com.project.nilamdeka.gameoflife.model;

import com.project.nilamdeka.gameoflife.Utils;

/**
 * Created by nilamdeka on 2/24/17.
 */
public class GameOfLife {

    private int m;
    private int n;

    private int[][] seed;
    private int[][] nxtGen;

    public GameOfLife(int m, int n) {
        this.m = m;
        this.n = n;
        seed = new int[m][n];
        nxtGen = new int[m][n];
    }

    public int[][] getSeed() {
        return seed;
    }

    public int[][] getNxtGen() {
        return nxtGen;
    }

    public void updateSeed(int[][] newSeed) {
        Utils.copyArray2D(seed, newSeed);
    }

    public void resetGame() {
        seed = new int[m][n];
        nxtGen = new int[m][n];
    }

    public void generateNextGen() {
        // outer loop
        for (int i = 0; i < m; i++) {

            // inner loop
            for (int j = 0; j < n; j++) {

                // keeps the count of live neighbors for the cell under
                // investigation
                int neighbors = 0;

                // scan sub array for it's neighbors
                for (int k = i - 1; k <= i + 1; k++) {
                    // border condition
                    if (k < 0 || k >= m)
                        continue;
                    for (int l = j - 1; l <= j + 1; l++) {
                        // border condition
                        if (l < 0 || l >= n)
                            continue;
                        // prevent comparison with itself
                        if (k == i && l == j)
                            continue;
                        // count live neighbors
                        neighbors += seed[k][l];
                    }

                }

                // cell under investigation is alive
                if (seed[i][j] == 1) {
                    // Any live cell with two or three live neighbors lives on
                    // to the next generation.
                    if (neighbors >= 2 && neighbors <= 3)
                        nxtGen[i][j] = 1;
                    else
                        nxtGen[i][j] = 0;

                    // cell is dead
                } else {
                    // Any dead cell with exactly three live neighbors becomes a
                    // live cell as if by reproduction.
                    if (neighbors == 3)
                        nxtGen[i][j] = 1;

                }

            }// end of inner loop

        }// end of outer loop

    }

}
