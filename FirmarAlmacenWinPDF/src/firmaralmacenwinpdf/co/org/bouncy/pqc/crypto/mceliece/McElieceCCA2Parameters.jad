// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   McElieceCCA2Parameters.java

package co.org.bouncy.pqc.crypto.mceliece;

import co.org.bouncy.crypto.Digest;
import co.org.bouncy.crypto.digests.SHA256Digest;

// Referenced classes of package co.org.bouncy.pqc.crypto.mceliece:
//            McElieceParameters

public class McElieceCCA2Parameters extends McElieceParameters
{

    public McElieceCCA2Parameters()
    {
        digest = new SHA256Digest();
    }

    public McElieceCCA2Parameters(int m, int t)
    {
        super(m, t);
        digest = new SHA256Digest();
    }

    public McElieceCCA2Parameters(Digest digest)
    {
        this.digest = digest;
    }

    public Digest getDigest()
    {
        return digest;
    }

    public Digest digest;
}
