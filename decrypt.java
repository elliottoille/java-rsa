import java.math.BigInteger;
import java.util.Scanner;

public class decrypt {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        /* Take these values as input */
        System.out.print("Enter a prime, p: ");
        BigInteger p = input.nextBigInteger();
        System.out.print("Enter a prime, q: ");
        BigInteger q = input.nextBigInteger();
        System.out.print("Enter an encoded message, c: ");
        BigInteger c = input.nextBigInteger();
        System.out.print("Enter a encoding exponent, e: ");
        BigInteger e = input.nextBigInteger();
        /* Calculate these values */
        BigInteger n = p.multiply(q);
        System.out.println("The base is: " + n);
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        System.out.println("The phi is: " + phi);
        BigInteger d = e.modInverse(phi);
        System.out.println("The decoding exponent is: " + d);
    
        //BigInteger m = c.modPow(d, n);
        BigInteger m = chineseRemainder(p, q, d, c);

        /*BigInteger pp = q.modPow((p.subtract(BigInteger.ONE)), p);
        BigInteger qq = p.modPow((q.subtract(BigInteger.ONE)), q);
        System.out.println(pp);
        System.out.println(qq);

        BigInteger dp = d.mod(p.subtract(BigInteger.ONE));
        BigInteger dq = d.mod(q.subtract(BigInteger.ONE));
        BigInteger mp = c.modPow(dp, p);
        BigInteger mq = c.modPow(dq, q);

        BigInteger mm = mp.multiply(pp).add(mq.multiply(qq)).mod(n);

        System.out.println("mm = " + mm + " mod " + n);
        */
        System.out.println("The decoded message is: " + m);
    }

    public static BigInteger chineseRemainder(BigInteger p, BigInteger q, BigInteger d, BigInteger c) {
        BigInteger dp = d.mod(p.subtract(BigInteger.ONE));
        System.out.println("dp = " + dp);
        BigInteger dq = d.mod(q.subtract(BigInteger.ONE));
        System.out.println("dq = " + dq);
        BigInteger mp = c.modPow(dp, p);
        System.out.println("mp = " + mp + " mod " + p);
        BigInteger mq = c.modPow(dq, q);
        System.out.println("mq = " + mq + " mod " + q);
        BigInteger qInv = q.modInverse(p);
        System.out.println("qinv " + qInv);
        BigInteger pInv = p.modInverse(q);
        System.out.println("pInv " + pInv);
        /*
        BigInteger h = qInv.multiply(mq.subtract(mp)).mod(p);
        BigInteger m = mp.add(h.multiply(q));
        */
        BigInteger m = mp.multiply(q).multiply(qInv).add(mq.multiply(p).multiply(pInv)).mod(p.multiply(q));
        return m;
    }
}
