// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SRP6VerifierGenerator.java

package co.org.bouncy.crypto.agreement.srp;

import co.org.bouncy.crypto.Digest;
import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.crypto.agreement.srp:
//            SRP6Util

public class SRP6VerifierGenerator
{

    public SRP6VerifierGenerator()
    {
    }

    public void init(BigInteger N, BigInteger g, Digest digest)
    {
        this.N = N;
        this.g = g;
        this.digest = digest;
    }

    public BigInteger generateVerifier(byte salt[], byte identity[], byte password[])
    {
        BigInteger x = SRP6Util.calculateX(digest, N, salt, identity, password);
        return g.modPow(x, N);
    }

    protected BigInteger N;
    protected BigInteger g;
    protected Digest digest;
}
