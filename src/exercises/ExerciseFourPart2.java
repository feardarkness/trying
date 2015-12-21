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

/**
 *
 * Now find one that starts with six zeroes.
 *
 *
 * solution 1038736
 *
 * @author aalvarado
 */
public class ExerciseFourPart2 {

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
                if (hashedString.startsWith("000000")) {
                    break;
                }
            }
            System.out.println("Min number: : " + number);
        } while (true);

    }
}
