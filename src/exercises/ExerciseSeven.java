package exercises;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * --- Day 7: Some Assembly Required ---
 *
 * This year, Santa brought little Bobby Tables a set of wires and bitwise logic
 * gates! Unfortunately, little Bobby is a little under the recommended age
 * range, and he needs help assembling the circuit.
 *
 * Each wire has an identifier (some lowercase letters) and can carry a 16-bit
 * signal (a number from 0 to 65535). A signal is provided to each wire by a
 * gate, another wire, or some specific value. Each wire can only get a signal
 * from one source, but can provide its signal to multiple destinations. A gate
 * provides no signal until all of its inputs have a signal.
 *
 * The included instructions booklet describes how to connect the parts
 * together: x AND y -> z means to connect wires x and y to an AND gate, and
 * then connect its output to wire z.
 *
 * For example:
 *
 * 123 -> x means that the signal 123 is provided to wire x. x AND y -> z means
 * that the bitwise AND of wire x and wire y is provided to wire z. p LSHIFT 2
 * -> q means that the value from wire p is left-shifted by 2 and then provided
 * to wire q. NOT e -> f means that the bitwise complement of the value from
 * wire e is provided to wire f.
 *
 * Other possible gates include OR (bitwise OR) and RSHIFT (right-shift). If,
 * for some reason, you'd like to emulate the circuit instead, almost all
 * programming languages (for example, C, JavaScript, or Python) provide
 * operators for these gates.
 *
 * For example, here is a simple circuit:
 *
 * 123 -> x 456 -> y x AND y -> d x OR y -> e x LSHIFT 2 -> f y RSHIFT 2 -> g
 * NOT x -> h NOT y -> i
 *
 * After it is run, these are the signals on the wires:
 *
 * d: 72 e: 507 f: 492 g: 114 h: 65412 i: 65079 x: 123 y: 456
 *
 * In little Bobby's kit's instructions booklet (provided as your puzzle input),
 * what signal is ultimately provided to wire a?
 *
 *
 * @author aalvarado
 */
public class ExerciseSeven {

    public static void main(String[] args) {
        Operation operation = null;
        String data;
        Scanner scanner = new Scanner(System.in);
        Map<String, Integer> values = new HashMap<String, Integer>();
        int value = 0;
        do {
            data = scanner.nextLine();
            if (data.equals("eof")) {
                break;
            }
            operation = new Operation(data);
            if ("NOT".equals(operation.getOperation())) {
                if (isInteger(operation.getFirstOperator())) {
                    // to make the complement operation first we nned to mask the value
                    // example: if we need masking 4 bits, mask should be 00001111
                    // the four 1 will mask the result of the complement
                    // complement of 3 (00000111): 11111000
                    // aplying the mask with and: 00001111 & 11111000 = 00001000
                    int mask = (1 << 16) - 1;
                    value = ~Integer.parseInt(operation.getFirstOperator()) & mask;
                } else {
                    int mask = (1 << 16) - 1;
                    value = ~values.get(operation.getVariableName()) & mask;
                }

            } else if (isInteger(operation.getFirstOperator())) {
                value = Integer.parseInt(operation.getFirstOperator());
            } else {
                value = values.get(operation.getVariableName());
            }
            values.put(operation.getVariableName(), value);
            System.out.println(operation);
            System.out.println("data: " + values);
        } while (true);

    }

    public static boolean isInteger(String s) {
        return isInteger(s, 10);
    }

    public static boolean isInteger(String s, int radix) {
        if (s.isEmpty()) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            if (i == 0 && s.charAt(i) == '-') {
                if (s.length() == 1) {
                    return false;
                } else {
                    continue;
                }
            }
            if (Character.digit(s.charAt(i), radix) < 0) {
                return false;
            }
        }
        return true;
    }
}

class Operation {

    private String variableName, operation = null, firstOperator = null, secondOperator = null;

    public Operation(String op) {
        StringTokenizer strToken = new StringTokenizer(op, "->");
        String firstPart = strToken.nextToken();
        variableName = strToken.nextToken().trim();
        strToken = new StringTokenizer(firstPart, " ");
        if (firstPart.contains("NOT")) {
            operation = strToken.nextToken();
            firstOperator = strToken.nextToken();
        } else if (firstPart.contains("AND")
                || firstPart.contains("OR")
                || firstPart.contains("LSHIFT")
                || firstPart.contains("RSHIFT")) {
            firstOperator = strToken.nextToken();
            operation = strToken.nextToken();
            secondOperator = strToken.nextToken();
        } else {
            firstOperator = strToken.nextToken();
        }
    }

    @Override
    public String toString() {
        return "Operation{" + "variableName=" + variableName + ", operation=" + operation + ", operatorOne=" + firstOperator + ", operatorTwo=" + secondOperator + '}';
    }

    public String getVariableName() {
        return variableName;
    }

    public String getOperation() {
        return operation;
    }

    public String getFirstOperator() {
        return firstOperator;
    }

    public String getSecondOperator() {
        return secondOperator;
    }
}
