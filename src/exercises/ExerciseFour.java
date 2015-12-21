/*
 --- Day 3: Perfectly Spherical Houses in a Vacuum ---

 Santa is delivering presents to an infinite two-dimensional grid of houses.

 He begins by delivering a present to the house at his starting location, and then an elf at the North Pole calls him via radio and tells him where to move next. Moves are always exactly one house to the north (^), south (v), east (>), or west (<). After each move, he delivers another present to the house at his new location.

 However, the elf back at the north pole has had a little too much eggnog, and so his directions are a little off, and Santa ends up visiting some houses more than once. How many houses receive at least one present?

 For example:

 > delivers presents to 2 houses: one at the starting location, and one to the east.
 ^>v< delivers presents to 4 houses in a square, including twice to the house at his starting/ending location.
 ^v^v^v^v^v delivers a bunch of presents to some very lucky children at only 2 houses.
 */
package exercises;

import Util.Hash;
import java.awt.Point;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

/**
 *
 * --- Day 4: The Ideal Stocking Stuffer ---
 *
 * Santa needs help mining some AdventCoins (very similar to bitcoins) to use as
 * gifts for all the economically forward-thinking little girls and boys.
 *
 * To do this, he needs to find MD5 hashes which, in hexadecimal, start with at
 * least five zeroes. The input to the MD5 hash is some secret key (your puzzle
 * input, given below) followed by a number in decimal. To mine AdventCoins, you
 * must find Santa the lowest positive number (no leading zeroes: 1, 2, 3, ...)
 * that produces such a hash.
 *
 * For example:
 *
 * If your secret key is abcdef, the answer is 609043, because the MD5 hash of
 * abcdef609043 starts with five zeroes (000001dbbfa...), and it is the lowest
 * such number to do so. If your secret key is pqrstuv, the lowest number it
 * combines with to make an MD5 hash starting with five zeroes is 1048970; that
 * is, the MD5 hash of pqrstuv1048970 looks like 000006136ef.... Your puzzle
 * input is bgvyzdsv.
 *
 * @author aalvarado
 */
public class ExerciseFour {

    public static String toHex(String arg) {
        return String.format("%040x", new BigInteger(1, arg.getBytes(StandardCharsets.UTF_8)));
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String data;
        int number;
        Scanner scanner = new Scanner(System.in);
        String hashedString;
        do {
            int posX = 0, posY = 0;
            Set<Point> places = new HashSet<Point>();
            places.add(new Point(posX, posY));
            data = scanner.next();
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            if (data.equals("eof")) {
                break;
            }
            for (number = 0; number <= 9999999; number++) {
                hashedString = Hash.getHash(data + number, "MD5");
                if (hashedString.startsWith("00000")) {
                    break;
                }
            }
            System.out.println("Min number: : " + number);
        } while (true);

    }
}
