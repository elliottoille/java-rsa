import java.math.BigInteger;
import java.util.Scanner;

public class encrypt {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        /* Take these valyes as input */
        System.out.print("Enter a prime, p: ");
        BigInteger p = input.nextBigInteger();
        System.out.print("Enter a prime, q: ");
        BigInteger q = input.nextBigInteger();
        System.out.print("Enter a message, m: ");
        BigInteger m = input.nextBigInteger();
        System.out.print("Enter an encoding exponent, e: ");
        BigInteger e = input.nextBigInteger();
        //
        BigInteger n = p.multiply(q);
        System.out.println("The base is: " + n);
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        System.out.println("The phi is: " + phi);
        BigInteger d = e.modInverse(phi);
        System.out.println("The decoding exponent is: " + d);
        /* Checks */
        if (gcd(e, (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE))).compareTo(BigInteger.ONE) != 0) {
            System.out.println("The encoding exponent is not coprime to phi.");
            return;
        }
        if (e.compareTo(n) != -1) {
            System.out.println("The encoding exponent is not less than the base.");
            return;
        }
        if (e.compareTo(BigInteger.ONE) != 1) {
            System.out.println("The encoding exponent is not greater than 1.");
            return;
        }
        if (m.compareTo(n) != -1) {
            System.out.println("The message is not less than the base.");
            return;
        }
        if (m == p || m == q) {
            System.out.println("The message is not coprime to the base.");
            return;
        }
        if (gcd(p, q).compareTo(BigInteger.ONE) != 0) {
            System.out.println("The two primes are not coprime.");
            return;
        }
        /*  */
        //BigInteger c = m.modPow(e, n);
        BigInteger c = chineseRemainder(p, q, d, m);
        System.out.println("The encoded message is: " + c);
        input.close();
    }

    public static BigInteger gcd(BigInteger r, BigInteger s) { // euclidean algorithm to find the gcd
        while (s.compareTo(BigInteger.ZERO) != 0) {
           BigInteger t = s;
           s = r.mod(s);
           r = t;
        }
        return r;
    }

    public static BigInteger chineseRemainder(BigInteger p, BigInteger q, BigInteger d, BigInteger c) {
        BigInteger dp = d.mod(p.subtract(BigInteger.ONE));
        BigInteger dq = d.mod(q.subtract(BigInteger.ONE));
        BigInteger mp = c.modPow(dp, p);
        BigInteger mq = c.modPow(dq, q);
        BigInteger qInv = q.modInverse(p);
        BigInteger h = qInv.multiply(mq.subtract(mp)).mod(p);
        BigInteger m = mp.add(h.multiply(q));
        return m;
    }
}
