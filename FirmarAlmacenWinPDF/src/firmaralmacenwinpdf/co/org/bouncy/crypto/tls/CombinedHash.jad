// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CombinedHash.java

package co.org.bouncy.crypto.tls;

import co.org.bouncy.crypto.Digest;

// Referenced classes of package co.org.bouncy.crypto.tls:
//            TlsHandshakeHash, TlsUtils, TlsContext, ProtocolVersion, 
//            SSL3Mac, SecurityParameters

class CombinedHash
    implements TlsHandshakeHash
{

    CombinedHash()
    {
        md5 = TlsUtils.createHash(1);
        sha1 = TlsUtils.createHash(2);
    }

    CombinedHash(CombinedHash t)
    {
        context = t.context;
        md5 = TlsUtils.cloneHash(1, t.md5);
        sha1 = TlsUtils.cloneHash(2, t.sha1);
    }

    public void init(TlsContext context)
    {
        this.context = context;
    }

    public TlsHandshakeHash commit()
    {
        return this;
    }

    public TlsHandshakeHash fork()
    {
        return new CombinedHash(this);
    }

    public String getAlgorithmName()
    {
        return (new StringBuilder()).append(md5.getAlgorithmName()).append(" and ").append(sha1.getAlgorithmName()).toString();
    }

    public int getDigestSize()
    {
        return md5.getDigestSize() + sha1.getDigestSize();
    }

    public void update(byte in)
    {
        md5.update(in);
        sha1.update(in);
    }

    public void update(byte in[], int inOff, int len)
    {
        md5.update(in, inOff, len);
        sha1.update(in, inOff, len);
    }

    public int doFinal(byte out[], int outOff)
    {
        if(context != null && context.getServerVersion().isSSL())
        {
            ssl3Complete(md5, SSL3Mac.IPAD, SSL3Mac.OPAD, 48);
            ssl3Complete(sha1, SSL3Mac.IPAD, SSL3Mac.OPAD, 40);
        }
        int i1 = md5.doFinal(out, outOff);
        int i2 = sha1.doFinal(out, outOff + i1);
        return i1 + i2;
    }

    public void reset()
    {
        md5.reset();
        sha1.reset();
    }

    protected void ssl3Complete(Digest d, byte ipad[], byte opad[], int padLength)
    {
        byte secret[] = context.getSecurityParameters().masterSecret;
        d.update(secret, 0, secret.length);
        d.update(ipad, 0, padLength);
        byte tmp[] = new byte[d.getDigestSize()];
        d.doFinal(tmp, 0);
        d.update(secret, 0, secret.length);
        d.update(opad, 0, padLength);
        d.update(tmp, 0, tmp.length);
    }

    protected TlsContext context;
    protected Digest md5;
    protected Digest sha1;
}
