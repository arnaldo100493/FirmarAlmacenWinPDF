// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SRP6Client.java

package co.org.bouncy.crypto.agreement.srp;

import co.org.bouncy.crypto.CryptoException;
import co.org.bouncy.crypto.Digest;
import java.math.BigInteger;
import java.security.SecureRandom;

// Referenced classes of package co.org.bouncy.crypto.agreement.srp:
//            SRP6Util

public class SRP6Client
{

    public SRP6Client()
    {
    }

    public void init(BigInteger N, BigInteger g, Digest digest, SecureRandom random)
    {
        this.N = N;
        this.g = g;
        this.digest = digest;
        this.random = random;
    }

    public BigInteger generateClientCredentials(byte salt[], byte identity[], byte password[])
    {
        x = SRP6Util.calculateX(digest, N, salt, identity, password);
        a = selectPrivateValue();
        A = g.modPow(a, N);
        return A;
    }

    public BigInteger calculateSecret(BigInteger serverB)
        throws CryptoException
    {
        B = SRP6Util.validatePublicValue(N, serverB);
        u = SRP6Util.calculateU(digest, N, A, B);
        S = calculateS();
        return S;
    }

    protected BigInteger selectPrivateValue()
    {
        return SRP6Util.generatePrivateValue(digest, N, g, random);
    }

    private BigInteger calculateS()
    {
        BigInteger k = SRP6Util.calculateK(digest, N, g);
        BigInteger exp = u.multiply(x).add(a);
        BigInteger tmp = g.modPow(x, N).multiply(k).mod(N);
        return B.subtract(tmp).mod(N).modPow(exp, N);
    }

    protected BigInteger N;
    protected BigInteger g;
    protected BigInteger a;
    protected BigInteger A;
    protected BigInteger B;
    protected BigInteger x;
    protected BigInteger u;
    protected BigInteger S;
    protected Digest digest;
    protected SecureRandom random;
}
