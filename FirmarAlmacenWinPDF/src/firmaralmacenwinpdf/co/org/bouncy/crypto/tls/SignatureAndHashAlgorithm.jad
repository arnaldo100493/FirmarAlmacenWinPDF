// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SignatureAndHashAlgorithm.java

package co.org.bouncy.crypto.tls;

import java.io.*;

// Referenced classes of package co.org.bouncy.crypto.tls:
//            TlsUtils

public class SignatureAndHashAlgorithm
{

    public SignatureAndHashAlgorithm(short hash, short signature)
    {
        if(!TlsUtils.isValidUint8(hash))
            throw new IllegalArgumentException("'hash' should be a uint8");
        if(!TlsUtils.isValidUint8(signature))
            throw new IllegalArgumentException("'signature' should be a uint8");
        if(signature == 0)
        {
            throw new IllegalArgumentException("'signature' MUST NOT be \"anonymous\"");
        } else
        {
            this.hash = hash;
            this.signature = signature;
            return;
        }
    }

    public short getHash()
    {
        return hash;
    }

    public short getSignature()
    {
        return signature;
    }

    public boolean equals(Object obj)
    {
        if(!(obj instanceof SignatureAndHashAlgorithm))
        {
            return false;
        } else
        {
            SignatureAndHashAlgorithm other = (SignatureAndHashAlgorithm)obj;
            return other.getHash() == getHash() && other.getSignature() == getSignature();
        }
    }

    public int hashCode()
    {
        return getHash() << 8 | getSignature();
    }

    public void encode(OutputStream output)
        throws IOException
    {
        TlsUtils.writeUint8(hash, output);
        TlsUtils.writeUint8(signature, output);
    }

    public static SignatureAndHashAlgorithm parse(InputStream input)
        throws IOException
    {
        short hash = TlsUtils.readUint8(input);
        short signature = TlsUtils.readUint8(input);
        return new SignatureAndHashAlgorithm(hash, signature);
    }

    private short hash;
    private short signature;
}
