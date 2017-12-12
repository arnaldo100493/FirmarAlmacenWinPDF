// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SRP6Server.java

package co.org.bouncy.crypto.agreement.srp;

import co.org.bouncy.crypto.CryptoException;
import co.org.bouncy.crypto.Digest;
import java.math.BigInteger;
import java.security.SecureRandom;

// Referenced classes of package co.org.bouncy.crypto.agreement.srp:
//            SRP6Util

public class SRP6Server
{

    public SRP6Server()
    {
    }

    public void init(BigInteger N, BigInteger g, BigInteger v, Digest digest, SecureRandom random)
    {
        this.N = N;
        this.g = g;
        this.v = v;
        this.random = random;
        this.digest = digest;
    }

    public BigInteger generateServerCredentials()
    {
        BigInteger k = SRP6Util.calculateK(digest, N, g);
        b = selectPrivateValue();
        B = k.multiply(v).mod(N).add(g.modPow(b, N)).mod(N);
        return B;
    }

    public BigInteger calculateSecret(BigInteger clientA)
        throws CryptoException
    {
        A = SRP6Util.validatePublicValue(N, clientA);
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
        return v.modPow(u, N).multiply(A).mod(N).modPow(b, N);
    }

    protected BigInteger N;
    protected BigInteger g;
    protected BigInteger v;
    protected SecureRandom random;
    protected Digest digest;
    protected BigInteger A;
    protected BigInteger b;
    protected BigInteger B;
    protected BigInteger u;
    protected BigInteger S;
}
