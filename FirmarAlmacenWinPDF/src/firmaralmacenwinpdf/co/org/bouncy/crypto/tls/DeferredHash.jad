// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DeferredHash.java

package co.org.bouncy.crypto.tls;

import co.org.bouncy.crypto.Digest;
import java.io.ByteArrayOutputStream;

// Referenced classes of package co.org.bouncy.crypto.tls:
//            TlsHandshakeHash, TlsContext, SecurityParameters, TlsUtils

class DeferredHash
    implements TlsHandshakeHash
{

    DeferredHash()
    {
        buf = new ByteArrayOutputStream();
        prfAlgorithm = -1;
        hash = null;
        buf = new ByteArrayOutputStream();
        hash = null;
    }

    private DeferredHash(Digest hash)
    {
        buf = new ByteArrayOutputStream();
        prfAlgorithm = -1;
        this.hash = null;
        buf = null;
        this.hash = hash;
    }

    public void init(TlsContext context)
    {
        this.context = context;
    }

    public TlsHandshakeHash commit()
    {
        int prfAlgorithm = context.getSecurityParameters().getPrfAlgorithm();
        Digest prfHash = TlsUtils.createPRFHash(prfAlgorithm);
        byte data[] = buf.toByteArray();
        prfHash.update(data, 0, data.length);
        if(prfHash instanceof TlsHandshakeHash)
        {
            TlsHandshakeHash tlsPRFHash = (TlsHandshakeHash)prfHash;
            tlsPRFHash.init(context);
            return tlsPRFHash.commit();
        } else
        {
            this.prfAlgorithm = prfAlgorithm;
            hash = prfHash;
            buf = null;
            return this;
        }
    }

    public TlsHandshakeHash fork()
    {
        checkHash();
        return new DeferredHash(TlsUtils.clonePRFHash(prfAlgorithm, hash));
    }

    public String getAlgorithmName()
    {
        checkHash();
        return hash.getAlgorithmName();
    }

    public int getDigestSize()
    {
        checkHash();
        return hash.getDigestSize();
    }

    public void update(byte input)
    {
        if(hash == null)
            buf.write(input);
        else
            hash.update(input);
    }

    public void update(byte input[], int inOff, int len)
    {
        if(hash == null)
            buf.write(input, inOff, len);
        else
            hash.update(input, inOff, len);
    }

    public int doFinal(byte output[], int outOff)
    {
        checkHash();
        return hash.doFinal(output, outOff);
    }

    public void reset()
    {
        if(hash == null)
            buf.reset();
        else
            hash.reset();
    }

    protected void checkHash()
    {
        if(hash == null)
            throw new IllegalStateException("No hash algorithm has been set");
        else
            return;
    }

    protected TlsContext context;
    private ByteArrayOutputStream buf;
    private int prfAlgorithm;
    private Digest hash;
}
