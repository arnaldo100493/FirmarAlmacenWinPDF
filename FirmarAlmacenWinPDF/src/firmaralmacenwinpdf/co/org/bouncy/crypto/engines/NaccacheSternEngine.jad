// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NaccacheSternEngine.java

package co.org.bouncy.crypto.engines;

import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.params.*;
import co.org.bouncy.util.Arrays;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.Vector;

public class NaccacheSternEngine
    implements AsymmetricBlockCipher
{

    public NaccacheSternEngine()
    {
        lookup = null;
        debug = false;
    }

    public void init(boolean forEncryption, CipherParameters param)
    {
        this.forEncryption = forEncryption;
        if(param instanceof ParametersWithRandom)
            param = ((ParametersWithRandom)param).getParameters();
        key = (NaccacheSternKeyParameters)param;
        if(!this.forEncryption)
        {
            if(debug)
                System.out.println("Constructing lookup Array");
            NaccacheSternPrivateKeyParameters priv = (NaccacheSternPrivateKeyParameters)key;
            Vector primes = priv.getSmallPrimes();
            lookup = new Vector[primes.size()];
            for(int i = 0; i < primes.size(); i++)
            {
                BigInteger actualPrime = (BigInteger)primes.elementAt(i);
                int actualPrimeValue = actualPrime.intValue();
                lookup[i] = new Vector();
                lookup[i].addElement(ONE);
                if(debug)
                    System.out.println((new StringBuilder()).append("Constructing lookup ArrayList for ").append(actualPrimeValue).toString());
                BigInteger accJ = ZERO;
                for(int j = 1; j < actualPrimeValue; j++)
                {
                    accJ = accJ.add(priv.getPhi_n());
                    BigInteger comp = accJ.divide(actualPrime);
                    lookup[i].addElement(priv.getG().modPow(comp, priv.getModulus()));
                }

            }

        }
    }

    public void setDebug(boolean debug)
    {
        this.debug = debug;
    }

    public int getInputBlockSize()
    {
        if(forEncryption)
            return (key.getLowerSigmaBound() + 7) / 8 - 1;
        else
            return key.getModulus().toByteArray().length;
    }

    public int getOutputBlockSize()
    {
        if(forEncryption)
            return key.getModulus().toByteArray().length;
        else
            return (key.getLowerSigmaBound() + 7) / 8 - 1;
    }

    public byte[] processBlock(byte in[], int inOff, int len)
        throws InvalidCipherTextException
    {
        if(key == null)
            throw new IllegalStateException("NaccacheStern engine not initialised");
        if(len > getInputBlockSize() + 1)
            throw new DataLengthException("input too large for Naccache-Stern cipher.\n");
        if(!forEncryption && len < getInputBlockSize())
            throw new InvalidCipherTextException("BlockLength does not match modulus for Naccache-Stern cipher.\n");
        byte block[];
        if(inOff != 0 || len != in.length)
        {
            block = new byte[len];
            System.arraycopy(in, inOff, block, 0, len);
        } else
        {
            block = in;
        }
        BigInteger input = new BigInteger(1, block);
        if(debug)
            System.out.println((new StringBuilder()).append("input as BigInteger: ").append(input).toString());
        byte output[];
        if(forEncryption)
        {
            output = encrypt(input);
        } else
        {
            Vector plain = new Vector();
            NaccacheSternPrivateKeyParameters priv = (NaccacheSternPrivateKeyParameters)key;
            Vector primes = priv.getSmallPrimes();
            for(int i = 0; i < primes.size(); i++)
            {
                BigInteger exp = input.modPow(priv.getPhi_n().divide((BigInteger)primes.elementAt(i)), priv.getModulus());
                Vector al = lookup[i];
                if(lookup[i].size() != ((BigInteger)primes.elementAt(i)).intValue())
                {
                    if(debug)
                        System.out.println((new StringBuilder()).append("Prime is ").append(primes.elementAt(i)).append(", lookup table has size ").append(al.size()).toString());
                    throw new InvalidCipherTextException((new StringBuilder()).append("Error in lookup Array for ").append(((BigInteger)primes.elementAt(i)).intValue()).append(": Size mismatch. Expected ArrayList with length ").append(((BigInteger)primes.elementAt(i)).intValue()).append(" but found ArrayList of length ").append(lookup[i].size()).toString());
                }
                int lookedup = al.indexOf(exp);
                if(lookedup == -1)
                {
                    if(debug)
                    {
                        System.out.println((new StringBuilder()).append("Actual prime is ").append(primes.elementAt(i)).toString());
                        System.out.println((new StringBuilder()).append("Decrypted value is ").append(exp).toString());
                        System.out.println((new StringBuilder()).append("LookupList for ").append(primes.elementAt(i)).append(" with size ").append(lookup[i].size()).append(" is: ").toString());
                        for(int j = 0; j < lookup[i].size(); j++)
                            System.out.println(lookup[i].elementAt(j));

                    }
                    throw new InvalidCipherTextException("Lookup failed");
                }
                plain.addElement(BigInteger.valueOf(lookedup));
            }

            BigInteger test = chineseRemainder(plain, primes);
            output = test.toByteArray();
        }
        return output;
    }

    public byte[] encrypt(BigInteger plain)
    {
        byte output[] = key.getModulus().toByteArray();
        Arrays.fill(output, (byte)0);
        byte tmp[] = key.getG().modPow(plain, key.getModulus()).toByteArray();
        System.arraycopy(tmp, 0, output, output.length - tmp.length, tmp.length);
        if(debug)
            System.out.println((new StringBuilder()).append("Encrypted value is:  ").append(new BigInteger(output)).toString());
        return output;
    }

    public byte[] addCryptedBlocks(byte block1[], byte block2[])
        throws InvalidCipherTextException
    {
        if(forEncryption)
        {
            if(block1.length > getOutputBlockSize() || block2.length > getOutputBlockSize())
                throw new InvalidCipherTextException("BlockLength too large for simple addition.\n");
        } else
        if(block1.length > getInputBlockSize() || block2.length > getInputBlockSize())
            throw new InvalidCipherTextException("BlockLength too large for simple addition.\n");
        BigInteger m1Crypt = new BigInteger(1, block1);
        BigInteger m2Crypt = new BigInteger(1, block2);
        BigInteger m1m2Crypt = m1Crypt.multiply(m2Crypt);
        m1m2Crypt = m1m2Crypt.mod(key.getModulus());
        if(debug)
        {
            System.out.println((new StringBuilder()).append("c(m1) as BigInteger:....... ").append(m1Crypt).toString());
            System.out.println((new StringBuilder()).append("c(m2) as BigInteger:....... ").append(m2Crypt).toString());
            System.out.println((new StringBuilder()).append("c(m1)*c(m2)%n = c(m1+m2)%n: ").append(m1m2Crypt).toString());
        }
        byte output[] = key.getModulus().toByteArray();
        Arrays.fill(output, (byte)0);
        System.arraycopy(m1m2Crypt.toByteArray(), 0, output, output.length - m1m2Crypt.toByteArray().length, m1m2Crypt.toByteArray().length);
        return output;
    }

    public byte[] processData(byte data[])
        throws InvalidCipherTextException
    {
        if(debug)
            System.out.println();
        if(data.length > getInputBlockSize())
        {
            int inBlocksize = getInputBlockSize();
            int outBlocksize = getOutputBlockSize();
            if(debug)
            {
                System.out.println((new StringBuilder()).append("Input blocksize is:  ").append(inBlocksize).append(" bytes").toString());
                System.out.println((new StringBuilder()).append("Output blocksize is: ").append(outBlocksize).append(" bytes").toString());
                System.out.println((new StringBuilder()).append("Data has length:.... ").append(data.length).append(" bytes").toString());
            }
            int datapos = 0;
            int retpos = 0;
            byte retval[] = new byte[(data.length / inBlocksize + 1) * outBlocksize];
            while(datapos < data.length) 
            {
                byte tmp[];
                if(datapos + inBlocksize < data.length)
                {
                    tmp = processBlock(data, datapos, inBlocksize);
                    datapos += inBlocksize;
                } else
                {
                    tmp = processBlock(data, datapos, data.length - datapos);
                    datapos += data.length - datapos;
                }
                if(debug)
                    System.out.println((new StringBuilder()).append("new datapos is ").append(datapos).toString());
                if(tmp != null)
                {
                    System.arraycopy(tmp, 0, retval, retpos, tmp.length);
                    retpos += tmp.length;
                } else
                {
                    if(debug)
                        System.out.println("cipher returned null");
                    throw new InvalidCipherTextException("cipher returned null");
                }
            }
            byte ret[] = new byte[retpos];
            System.arraycopy(retval, 0, ret, 0, retpos);
            if(debug)
                System.out.println((new StringBuilder()).append("returning ").append(ret.length).append(" bytes").toString());
            return ret;
        }
        if(debug)
            System.out.println("data size is less then input block size, processing directly");
        return processBlock(data, 0, data.length);
    }

    private static BigInteger chineseRemainder(Vector congruences, Vector primes)
    {
        BigInteger retval = ZERO;
        BigInteger all = ONE;
        for(int i = 0; i < primes.size(); i++)
            all = all.multiply((BigInteger)primes.elementAt(i));

        for(int i = 0; i < primes.size(); i++)
        {
            BigInteger a = (BigInteger)primes.elementAt(i);
            BigInteger b = all.divide(a);
            BigInteger b_ = b.modInverse(a);
            BigInteger tmp = b.multiply(b_);
            tmp = tmp.multiply((BigInteger)congruences.elementAt(i));
            retval = retval.add(tmp);
        }

        return retval.mod(all);
    }

    private boolean forEncryption;
    private NaccacheSternKeyParameters key;
    private Vector lookup[];
    private boolean debug;
    private static BigInteger ZERO = BigInteger.valueOf(0L);
    private static BigInteger ONE = BigInteger.valueOf(1L);

}
