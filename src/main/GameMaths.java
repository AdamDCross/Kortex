package main;

import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;

/**
 * A class that contains mathematical functions that might be useful throughout
 * the game
 * Current list so far is:
 * clamp: Fixes a value into a specific range
 * map: converts a value from one range to another
 *
 * Created by Phillip on 19/01/2016.
 */
public class GameMaths {
    /**
     * maps a value from one range to another
     *
     * @param val value to change
     * @param oMin original range min
     * @param oMax original range max
     * @param nMin new range min
     * @param nMax new range max
     * @return nMin+((val-oMin)*dN/dO)
     */
    public static float map(float val,float oMin,float oMax,float nMin,float nMax){
        float dO=oMax-oMin;
        float dN=nMax-nMin;
        return nMin+((val-oMin)*dN/dO);
    }

    /**
     * Fixes a value into a specific range;
     *
     * @param val value to clamp
     * @param min min of range
     * @param max max of range
     * @return value
     */
    public static float clamp(float val,float min,float max){
        if(val>max){
            return max;
        }
        if (val < min) {
            return min;
        }
        return val;
    }
    /**
     * Fixes a value into a specific range;
     *
     * @param val value to clamp
     * @param min min of range
     * @param max max of range
     * @return value
     */
    public static int clamp(int val,int min,int max) {
        if (val > max) {
            return max;
        }
        if (val < min) {
            return min;
        }
        return val;
    }
    public static float mag(Vector2i vect){
        return (float) Math.sqrt(vect.x*vect.x+vect.y+vect.y);
    }
    public static float mag(Vector2f vect){
        return (float) Math.sqrt(vect.x*vect.x+vect.y+vect.y);
    }
}
