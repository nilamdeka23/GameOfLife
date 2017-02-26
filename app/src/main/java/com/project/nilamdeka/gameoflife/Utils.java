package com.project.nilamdeka.gameoflife;

/**
 * Created by nilamdeka on 2/25/17.
 */
public class Utils {

    public static void copyArray2D(int[][] dest, int[][] src) {

        for (int i = 0; i < src.length; i++) {
            System.arraycopy(src[i], 0, dest[i], 0, src[0].length);
        }
    }

}
