// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IntegerFunctions.java

package co.org.bouncy.pqc.math.linearalgebra;

import java.io.PrintStream;
import java.math.BigInteger;
import java.security.SecureRandom;

public final class IntegerFunctions
{

    private IntegerFunctions()
    {
    }

    public static int jacobi(BigInteger A, BigInteger B)
    {
        long k = 1L;
        k = 1L;
        BigInteger a;
        if(B.equals(ZERO))
        {
            a = A.abs();
            return a.equals(ONE) ? 1 : 0;
        }
        if(!A.testBit(0) && !B.testBit(0))
            return 0;
        a = A;
        BigInteger b = B;
        if(b.signum() == -1)
        {
            b = b.negate();
            if(a.signum() == -1)
                k = -1L;
        }
        BigInteger v = ZERO;
        for(; !b.testBit(0); b = b.divide(TWO))
            v = v.add(ONE);

        if(v.testBit(0))
            k *= jacobiTable[a.intValue() & 7];
        if(a.signum() < 0)
        {
            if(b.testBit(1))
                k = -k;
            a = a.negate();
        }
        for(; a.signum() != 0; a = a.subtract(b))
        {
            v = ZERO;
            for(; !a.testBit(0); a = a.divide(TWO))
                v = v.add(ONE);

            if(v.testBit(0))
                k *= jacobiTable[b.intValue() & 7];
            if(a.compareTo(b) >= 0)
                continue;
            BigInteger x = a;
            a = b;
            b = x;
            if(a.testBit(1) && b.testBit(1))
                k = -k;
        }

        return b.equals(ONE) ? (int)k : 0;
    }

    public static BigInteger ressol(BigInteger a, BigInteger p)
        throws IllegalArgumentException
    {
        BigInteger v = null;
        if(a.compareTo(ZERO) < 0)
            a = a.add(p);
        if(a.equals(ZERO))
            return ZERO;
        if(p.equals(TWO))
            return a;
        if(p.testBit(0) && p.testBit(1))
            if(jacobi(a, p) == 1)
            {
                v = p.add(ONE);
                v = v.shiftRight(2);
                return a.modPow(v, p);
            } else
            {
                throw new IllegalArgumentException((new StringBuilder()).append("No quadratic residue: ").append(a).append(", ").append(p).toString());
            }
        long t = 0L;
        BigInteger k = p.subtract(ONE);
        long s = 0L;
        for(; !k.testBit(0); k = k.shiftRight(1))
            s++;

        k = k.subtract(ONE);
        k = k.shiftRight(1);
        BigInteger r = a.modPow(k, p);
        BigInteger n = r.multiply(r).remainder(p);
        n = n.multiply(a).remainder(p);
        r = r.multiply(a).remainder(p);
        if(n.equals(ONE))
            return r;
        BigInteger z;
        for(z = TWO; jacobi(z, p) == 1; z = z.add(ONE));
        v = k;
        v = v.multiply(TWO);
        v = v.add(ONE);
        BigInteger c = z.modPow(v, p);
        for(; n.compareTo(ONE) == 1; n = n.multiply(c).mod(p))
        {
            k = n;
            t = s;
            for(s = 0L; !k.equals(ONE); s++)
                k = k.multiply(k).mod(p);

            t -= s;
            if(t == 0L)
                throw new IllegalArgumentException((new StringBuilder()).append("No quadratic residue: ").append(a).append(", ").append(p).toString());
            v = ONE;
            for(long i = 0L; i < t - 1L; i++)
                v = v.shiftLeft(1);

            c = c.modPow(v, p);
            r = r.multiply(c).remainder(p);
            c = c.multiply(c).remainder(p);
        }

        return r;
    }

    public static int gcd(int u, int v)
    {
        return BigInteger.valueOf(u).gcd(BigInteger.valueOf(v)).intValue();
    }

    public static int[] extGCD(int a, int b)
    {
        BigInteger ba = BigInteger.valueOf(a);
        BigInteger bb = BigInteger.valueOf(b);
        BigInteger bresult[] = extgcd(ba, bb);
        int result[] = new int[3];
        result[0] = bresult[0].intValue();
        result[1] = bresult[1].intValue();
        result[2] = bresult[2].intValue();
        return result;
    }

    public static BigInteger divideAndRound(BigInteger a, BigInteger b)
    {
        if(a.signum() < 0)
            return divideAndRound(a.negate(), b).negate();
        if(b.signum() < 0)
            return divideAndRound(a, b.negate()).negate();
        else
            return a.shiftLeft(1).add(b).divide(b.shiftLeft(1));
    }

    public static BigInteger[] divideAndRound(BigInteger a[], BigInteger b)
    {
        BigInteger out[] = new BigInteger[a.length];
        for(int i = 0; i < a.length; i++)
            out[i] = divideAndRound(a[i], b);

        return out;
    }

    public static int ceilLog(BigInteger a)
    {
        int result = 0;
        for(BigInteger p = ONE; p.compareTo(a) < 0; p = p.shiftLeft(1))
            result++;

        return result;
    }

    public static int ceilLog(int a)
    {
        int log = 0;
        for(int i = 1; i < a;)
        {
            i <<= 1;
            log++;
        }

        return log;
    }

    public static int ceilLog256(int n)
    {
        if(n == 0)
            return 1;
        int m;
        if(n < 0)
            m = -n;
        else
            m = n;
        int d = 0;
        for(; m > 0; m >>>= 8)
            d++;

        return d;
    }

    public static int ceilLog256(long n)
    {
        if(n == 0L)
            return 1;
        long m;
        if(n < 0L)
            m = -n;
        else
            m = n;
        int d = 0;
        for(; m > 0L; m >>>= 8)
            d++;

        return d;
    }

    public static int floorLog(BigInteger a)
    {
        int result = -1;
        for(BigInteger p = ONE; p.compareTo(a) <= 0; p = p.shiftLeft(1))
            result++;

        return result;
    }

    public static int floorLog(int a)
    {
        int h = 0;
        if(a <= 0)
            return -1;
        for(int p = a >>> 1; p > 0; p >>>= 1)
            h++;

        return h;
    }

    public static int maxPower(int a)
    {
        int h = 0;
        if(a != 0)
        {
            for(int p = 1; (a & p) == 0; p <<= 1)
                h++;

        }
        return h;
    }

    public static int bitCount(int a)
    {
        int h = 0;
        for(; a != 0; a >>>= 1)
            h += a & 1;

        return h;
    }

    public static int order(int g, int p)
    {
        int b = g % p;
        int j = 1;
        if(b == 0)
            throw new IllegalArgumentException((new StringBuilder()).append(g).append(" is not an element of Z/(").append(p).append("Z)^*; it is not meaningful to compute its order.").toString());
        while(b != 1) 
        {
            b *= g;
            b %= p;
            if(b < 0)
                b += p;
            j++;
        }
        return j;
    }

    public static BigInteger reduceInto(BigInteger n, BigInteger begin, BigInteger end)
    {
        return n.subtract(begin).mod(end.subtract(begin)).add(begin);
    }

    public static int pow(int a, int e)
    {
        int result = 1;
        for(; e > 0; e >>>= 1)
        {
            if((e & 1) == 1)
                result *= a;
            a *= a;
        }

        return result;
    }

    public static long pow(long a, int e)
    {
        long result = 1L;
        for(; e > 0; e >>>= 1)
        {
            if((e & 1) == 1)
                result *= a;
            a *= a;
        }

        return result;
    }

    public static int modPow(int a, int e, int n)
    {
        if(n <= 0 || n * n > 0x7fffffff || e < 0)
            return 0;
        int result = 1;
        a = (a % n + n) % n;
        for(; e > 0; e >>>= 1)
        {
            if((e & 1) == 1)
                result = (result * a) % n;
            a = (a * a) % n;
        }

        return result;
    }

    public static BigInteger[] extgcd(BigInteger a, BigInteger b)
    {
        BigInteger u = ONE;
        BigInteger v = ZERO;
        BigInteger d = a;
        if(b.signum() != 0)
        {
            BigInteger v1 = ZERO;
            BigInteger t3;
            for(BigInteger v3 = b; v3.signum() != 0; v3 = t3)
            {
                BigInteger tmp[] = d.divideAndRemainder(v3);
                BigInteger q = tmp[0];
                t3 = tmp[1];
                BigInteger t1 = u.subtract(q.multiply(v1));
                u = v1;
                d = v3;
                v1 = t1;
            }

            v = d.subtract(a.multiply(u)).divide(b);
        }
        return (new BigInteger[] {
            d, u, v
        });
    }

    public static BigInteger leastCommonMultiple(BigInteger numbers[])
    {
        int n = numbers.length;
        BigInteger result = numbers[0];
        for(int i = 1; i < n; i++)
        {
            BigInteger gcd = result.gcd(numbers[i]);
            result = result.multiply(numbers[i]).divide(gcd);
        }

        return result;
    }

    public static long mod(long a, long m)
    {
        long result = a % m;
        if(result < 0L)
            result += m;
        return result;
    }

    public static int modInverse(int a, int mod)
    {
        return BigInteger.valueOf(a).modInverse(BigInteger.valueOf(mod)).intValue();
    }

    public static long modInverse(long a, long mod)
    {
        return BigInteger.valueOf(a).modInverse(BigInteger.valueOf(mod)).longValue();
    }

    public static int isPower(int a, int p)
    {
        if(a <= 0)
            return -1;
        int n = 0;
        for(int d = a; d > 1;)
        {
            if(d % p != 0)
                return -1;
            d /= p;
            n++;
        }

        return n;
    }

    public static int leastDiv(int a)
    {
        if(a < 0)
            a = -a;
        if(a == 0)
            return 1;
        if((a & 1) == 0)
            return 2;
        for(int p = 3; p <= a / p; p += 2)
            if(a % p == 0)
                return p;

        return a;
    }

    public static boolean isPrime(int n)
    {
        if(n < 2)
            return false;
        if(n == 2)
            return true;
        if((n & 1) == 0)
            return false;
        if(n < 42)
        {
            for(int i = 0; i < SMALL_PRIMES.length; i++)
                if(n == SMALL_PRIMES[i])
                    return true;

        }
        if(n % 3 == 0 || n % 5 == 0 || n % 7 == 0 || n % 11 == 0 || n % 13 == 0 || n % 17 == 0 || n % 19 == 0 || n % 23 == 0 || n % 29 == 0 || n % 31 == 0 || n % 37 == 0 || n % 41 == 0)
            return false;
        else
            return BigInteger.valueOf(n).isProbablePrime(20);
    }

    public static boolean passesSmallPrimeTest(BigInteger candidate)
    {
        int smallPrime[] = {
            2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 
            31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 
            73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 
            127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 
            179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 
            233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 
            283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 
            353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 
            419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 
            467, 479, 487, 491, 499, 503, 509, 521, 523, 541, 
            547, 557, 563, 569, 571, 577, 587, 593, 599, 601, 
            607, 613, 617, 619, 631, 641, 643, 647, 653, 659, 
            661, 673, 677, 683, 691, 701, 709, 719, 727, 733, 
            739, 743, 751, 757, 761, 769, 773, 787, 797, 809, 
            811, 821, 823, 827, 829, 839, 853, 857, 859, 863, 
            877, 881, 883, 887, 907, 911, 919, 929, 937, 941, 
            947, 953, 967, 971, 977, 983, 991, 997, 1009, 1013, 
            1019, 1021, 1031, 1033, 1039, 1049, 1051, 1061, 1063, 1069, 
            1087, 1091, 1093, 1097, 1103, 1109, 1117, 1123, 1129, 1151, 
            1153, 1163, 1171, 1181, 1187, 1193, 1201, 1213, 1217, 1223, 
            1229, 1231, 1237, 1249, 1259, 1277, 1279, 1283, 1289, 1291, 
            1297, 1301, 1303, 1307, 1319, 1321, 1327, 1361, 1367, 1373, 
            1381, 1399, 1409, 1423, 1427, 1429, 1433, 1439, 1447, 1451, 
            1453, 1459, 1471, 1481, 1483, 1487, 1489, 1493, 1499
        };
        for(int i = 0; i < smallPrime.length; i++)
            if(candidate.mod(BigInteger.valueOf(smallPrime[i])).equals(ZERO))
                return false;

        return true;
    }

    public static int nextSmallerPrime(int n)
    {
        if(n <= 2)
            return 1;
        if(n == 3)
            return 2;
        if((n & 1) == 0)
            n--;
        else
            n -= 2;
        for(; (n > 3) & (!isPrime(n)); n -= 2);
        return n;
    }

    public static BigInteger nextProbablePrime(BigInteger n, int certainty)
    {
        if(n.signum() < 0 || n.signum() == 0 || n.equals(ONE))
            return TWO;
        BigInteger result = n.add(ONE);
        if(!result.testBit(0))
            result = result.add(ONE);
        do
        {
            if(result.bitLength() > 6)
            {
                long r = result.remainder(BigInteger.valueOf(0x8a5b6470af95L)).longValue();
                if(r % 3L == 0L || r % 5L == 0L || r % 7L == 0L || r % 11L == 0L || r % 13L == 0L || r % 17L == 0L || r % 19L == 0L || r % 23L == 0L || r % 29L == 0L || r % 31L == 0L || r % 37L == 0L || r % 41L == 0L)
                {
                    result = result.add(TWO);
                    continue;
                }
            }
            if(result.bitLength() < 4)
                return result;
            if(result.isProbablePrime(certainty))
                return result;
            result = result.add(TWO);
        } while(true);
    }

    public static BigInteger nextProbablePrime(BigInteger n)
    {
        return nextProbablePrime(n, 20);
    }

    public static BigInteger nextPrime(long n)
    {
        boolean found = false;
        long result = 0L;
        if(n <= 1L)
            return BigInteger.valueOf(2L);
        if(n == 2L)
            return BigInteger.valueOf(3L);
        for(long i = n + 1L + (n & 1L); i <= n << 1 && !found; i += 2L)
        {
            for(long j = 3L; j <= i >> 1 && !found; j += 2L)
                if(i % j == 0L)
                    found = true;

            if(found)
            {
                found = false;
            } else
            {
                result = i;
                found = true;
            }
        }

        return BigInteger.valueOf(result);
    }

    public static BigInteger binomial(int n, int t)
    {
        BigInteger result = ONE;
        if(n == 0)
            if(t == 0)
                return result;
            else
                return ZERO;
        if(t > n >>> 1)
            t = n - t;
        for(int i = 1; i <= t; i++)
            result = result.multiply(BigInteger.valueOf(n - (i - 1))).divide(BigInteger.valueOf(i));

        return result;
    }

    public static BigInteger randomize(BigInteger upperBound)
    {
        if(sr == null)
            sr = new SecureRandom();
        return randomize(upperBound, sr);
    }

    public static BigInteger randomize(BigInteger upperBound, SecureRandom prng)
    {
        int blen = upperBound.bitLength();
        BigInteger randomNum = BigInteger.valueOf(0L);
        if(prng == null)
            prng = sr == null ? new SecureRandom() : sr;
        for(int i = 0; i < 20; i++)
        {
            randomNum = new BigInteger(blen, prng);
            if(randomNum.compareTo(upperBound) < 0)
                return randomNum;
        }

        return randomNum.mod(upperBound);
    }

    public static BigInteger squareRoot(BigInteger a)
    {
        if(a.compareTo(ZERO) < 0)
            throw new ArithmeticException((new StringBuilder()).append("cannot extract root of negative number").append(a).append(".").toString());
        int bl = a.bitLength();
        BigInteger result = ZERO;
        BigInteger remainder = ZERO;
        if((bl & 1) != 0)
        {
            result = result.add(ONE);
            bl--;
        }
        do
        {
            if(bl <= 0)
                break;
            remainder = remainder.multiply(FOUR);
            remainder = remainder.add(BigInteger.valueOf((a.testBit(--bl) ? 2 : 0) + (a.testBit(--bl) ? 1 : 0)));
            BigInteger b = result.multiply(FOUR).add(ONE);
            result = result.multiply(TWO);
            if(remainder.compareTo(b) != -1)
            {
                result = result.add(ONE);
                remainder = remainder.subtract(b);
            }
        } while(true);
        return result;
    }

    public static float intRoot(int base, int root)
    {
        float gNew = base / root;
        float gOld = 0.0F;
        int counter = 0;
        float gPow;
        for(; (double)Math.abs(gOld - gNew) > 0.0001D; gNew = gOld - (gPow - (float)base) / ((float)root * floatPow(gOld, root - 1)))
        {
            for(gPow = floatPow(gNew, root); Float.isInfinite(gPow); gPow = floatPow(gNew, root))
                gNew = (gNew + gOld) / 2.0F;

            counter++;
            gOld = gNew;
        }

        return gNew;
    }

    public static float floatLog(float param)
    {
        double arg = (param - 1.0F) / (param + 1.0F);
        double arg2 = arg;
        int counter = 1;
        float result;
        for(result = (float)arg; arg2 > 0.001D; result = (float)((double)result + (1.0D / (double)counter) * arg2))
        {
            counter += 2;
            arg2 *= arg * arg;
        }

        return 2.0F * result;
    }

    public static float floatPow(float f, int i)
    {
        float g = 1.0F;
        for(; i > 0; i--)
            g *= f;

        return g;
    }

    /**
     * @deprecated Method log is deprecated
     */

    public static double log(double x)
    {
        if(x > 0.0D && x < 1.0D)
        {
            double d = 1.0D / x;
            double result = -log(d);
            return result;
        }
        int tmp = 0;
        double tmp2 = 1.0D;
        for(double d = x; d > 2D;)
        {
            d /= 2D;
            tmp++;
            tmp2 *= 2D;
        }

        double rem = x / tmp2;
        rem = logBKM(rem);
        return (double)tmp + rem;
    }

    /**
     * @deprecated Method log is deprecated
     */

    public static double log(long x)
    {
        int tmp = floorLog(BigInteger.valueOf(x));
        long tmp2 = 1 << tmp;
        double rem = (double)x / (double)tmp2;
        rem = logBKM(rem);
        return (double)tmp + rem;
    }

    /**
     * @deprecated Method logBKM is deprecated
     */

    private static double logBKM(double arg)
    {
        double ae[] = {
            1.0D, 0.58496250072115619D, 0.32192809488736235D, 0.16992500144231237D, 0.087462841250339401D, 0.044394119358453436D, 0.02236781302845451D, 0.011227255423254119D, 0.0056245491938781067D, 0.0028150156070540383D, 
            0.0014081943928083889D, 0.00070426901124664325D, 0.00035217748030102726D, 0.00017609948644250602D, 8.8052430122176906E-005D, 4.4026886827316716E-005D, 2.2013611360340496E-005D, 1.1006847667481442E-005D, 5.503434330648604E-006D, 2.7517197895612829E-006D, 
            1.3758605508411381E-006D, 6.8793043943584969E-007D, 3.4396526072176454E-007D, 1.7198264061184464E-007D, 8.5991322868663213E-008D, 4.2995662075016872E-008D, 2.1497831197679756E-008D, 1.0748915638882709E-008D, 5.374457829452062E-009D, 2.6872289172287081E-009D, 
            1.3436144592400231E-009D, 6.7180722977642895E-010D, 3.3590361492731876E-010D, 1.6795180747343547E-010D, 8.3975903739161756E-011D, 4.1987951870191886E-011D, 2.0993975935248694E-011D, 1.0496987967662534E-011D, 5.2484939838408146E-012D, 2.6242469919227939E-012D, 
            1.3121234959619935E-012D, 6.5606174798114599E-013D, 3.2803087399061026E-013D, 1.6401543699531447E-013D, 8.2007718497659557E-014D, 4.1003859248830365E-014D, 2.0501929624415328E-014D, 1.02509648122077E-014D, 5.1254824061038595E-015D, 2.5627412030519317E-015D, 
            1.2813706015259665E-015D, 6.4068530076298343E-016D, 3.2034265038149171E-016D, 1.6017132519074588E-016D, 8.0085662595372941E-017D, 4.0042831297686471E-017D, 2.0021415648843235E-017D, 1.0010707824421618E-017D, 5.0053539122108088E-018D, 2.5026769561054044E-018D, 
            1.2513384780527022E-018D, 6.256692390263511E-019D, 3.1283461951317555E-019D, 1.5641730975658778E-019D, 7.8208654878293888E-020D, 3.9104327439146944E-020D, 1.9552163719573472E-020D, 9.776081859786736E-021D, 4.888040929893368E-021D, 2.444020464946684E-021D, 
            1.222010232473342E-021D, 6.11005116236671E-022D, 3.055025581183355E-022D, 1.5275127905916775E-022D, 7.6375639529583875E-023D, 3.8187819764791937E-023D, 1.9093909882395969E-023D, 9.5469549411979843E-024D, 4.7734774705989922E-024D, 2.3867387352994961E-024D, 
            1.193369367649748E-024D, 5.9668468382487402E-025D, 2.9834234191243701E-025D, 1.4917117095621851E-025D, 7.4585585478109253E-026D, 3.7292792739054626E-026D, 1.8646396369527313E-026D, 9.3231981847636566E-027D, 4.6615990923818283E-027D, 2.3307995461909141E-027D, 
            1.1653997730954571E-027D, 5.8269988654772854E-028D, 2.9134994327386427E-028D, 1.4567497163693213E-028D, 7.2837485818466067E-029D, 3.6418742909233034E-029D, 1.8209371454616517E-029D, 9.1046857273082584E-030D, 4.5523428636541292E-030D, 2.2761714318270646E-030D
        };
        int n = 53;
        double x = 1.0D;
        double y = 0.0D;
        double s = 1.0D;
        for(int k = 0; k < n; k++)
        {
            double z = x + x * s;
            if(z <= arg)
            {
                x = z;
                y += ae[k];
            }
            s *= 0.5D;
        }

        return y;
    }

    public static boolean isIncreasing(int a[])
    {
        for(int i = 1; i < a.length; i++)
            if(a[i - 1] >= a[i])
            {
                System.out.println((new StringBuilder()).append("a[").append(i - 1).append("] = ").append(a[i - 1]).append(" >= ").append(a[i]).append(" = a[").append(i).append("]").toString());
                return false;
            }

        return true;
    }

    public static byte[] integerToOctets(BigInteger val)
    {
        byte valBytes[] = val.abs().toByteArray();
        if((val.bitLength() & 7) != 0)
        {
            return valBytes;
        } else
        {
            byte tmp[] = new byte[val.bitLength() >> 3];
            System.arraycopy(valBytes, 1, tmp, 0, tmp.length);
            return tmp;
        }
    }

    public static BigInteger octetsToInteger(byte data[], int offset, int length)
    {
        byte val[] = new byte[length + 1];
        val[0] = 0;
        System.arraycopy(data, offset, val, 1, length);
        return new BigInteger(val);
    }

    public static BigInteger octetsToInteger(byte data[])
    {
        return octetsToInteger(data, 0, data.length);
    }

    public static void main(String args[])
    {
        System.out.println("test");
        System.out.println(floatLog(10F));
        System.out.println("test2");
    }

    private static final BigInteger ZERO = BigInteger.valueOf(0L);
    private static final BigInteger ONE = BigInteger.valueOf(1L);
    private static final BigInteger TWO = BigInteger.valueOf(2L);
    private static final BigInteger FOUR = BigInteger.valueOf(4L);
    private static final int SMALL_PRIMES[] = {
        3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 
        37, 41
    };
    private static final long SMALL_PRIME_PRODUCT = 0x8a5b6470af95L;
    private static SecureRandom sr = null;
    private static final int jacobiTable[] = {
        0, 1, 0, -1, 0, -1, 0, 1
    };

}
