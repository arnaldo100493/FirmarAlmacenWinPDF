// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OnePassSignaturePacket.java

package co.org.bouncy.bcpg;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

// Referenced classes of package co.org.bouncy.bcpg:
//            ContainedPacket, BCPGOutputStream, BCPGInputStream

public class OnePassSignaturePacket extends ContainedPacket
{

    OnePassSignaturePacket(BCPGInputStream in)
        throws IOException
    {
        version = in.read();
        sigType = in.read();
        hashAlgorithm = in.read();
        keyAlgorithm = in.read();
        keyID |= (long)in.read() << 56;
        keyID |= (long)in.read() << 48;
        keyID |= (long)in.read() << 40;
        keyID |= (long)in.read() << 32;
        keyID |= (long)in.read() << 24;
        keyID |= (long)in.read() << 16;
        keyID |= (long)in.read() << 8;
        keyID |= in.read();
        nested = in.read();
    }

    public OnePassSignaturePacket(int sigType, int hashAlgorithm, int keyAlgorithm, long keyID, boolean isNested)
    {
        version = 3;
        this.sigType = sigType;
        this.hashAlgorithm = hashAlgorithm;
        this.keyAlgorithm = keyAlgorithm;
        this.keyID = keyID;
        nested = isNested ? 0 : 1;
    }

    public int getSignatureType()
    {
        return sigType;
    }

    public int getKeyAlgorithm()
    {
        return keyAlgorithm;
    }

    public int getHashAlgorithm()
    {
        return hashAlgorithm;
    }

    public long getKeyID()
    {
        return keyID;
    }

    public void encode(BCPGOutputStream out)
        throws IOException
    {
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        BCPGOutputStream pOut = new BCPGOutputStream(bOut);
        pOut.write(version);
        pOut.write(sigType);
        pOut.write(hashAlgorithm);
        pOut.write(keyAlgorithm);
        pOut.write((byte)(int)(keyID >> 56));
        pOut.write((byte)(int)(keyID >> 48));
        pOut.write((byte)(int)(keyID >> 40));
        pOut.write((byte)(int)(keyID >> 32));
        pOut.write((byte)(int)(keyID >> 24));
        pOut.write((byte)(int)(keyID >> 16));
        pOut.write((byte)(int)(keyID >> 8));
        pOut.write((byte)(int)keyID);
        pOut.write(nested);
        out.writePacket(4, bOut.toByteArray(), true);
    }

    private int version;
    private int sigType;
    private int hashAlgorithm;
    private int keyAlgorithm;
    private long keyID;
    private int nested;
}
