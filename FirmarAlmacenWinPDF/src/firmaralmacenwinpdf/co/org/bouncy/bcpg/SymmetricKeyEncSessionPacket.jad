// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SymmetricKeyEncSessionPacket.java

package co.org.bouncy.bcpg;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

// Referenced classes of package co.org.bouncy.bcpg:
//            ContainedPacket, S2K, BCPGOutputStream, BCPGInputStream

public class SymmetricKeyEncSessionPacket extends ContainedPacket
{

    public SymmetricKeyEncSessionPacket(BCPGInputStream in)
        throws IOException
    {
        version = in.read();
        encAlgorithm = in.read();
        s2k = new S2K(in);
        secKeyData = in.readAll();
    }

    public SymmetricKeyEncSessionPacket(int encAlgorithm, S2K s2k, byte secKeyData[])
    {
        version = 4;
        this.encAlgorithm = encAlgorithm;
        this.s2k = s2k;
        this.secKeyData = secKeyData;
    }

    public int getEncAlgorithm()
    {
        return encAlgorithm;
    }

    public S2K getS2K()
    {
        return s2k;
    }

    public byte[] getSecKeyData()
    {
        return secKeyData;
    }

    public int getVersion()
    {
        return version;
    }

    public void encode(BCPGOutputStream out)
        throws IOException
    {
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        BCPGOutputStream pOut = new BCPGOutputStream(bOut);
        pOut.write(version);
        pOut.write(encAlgorithm);
        pOut.writeObject(s2k);
        if(secKeyData != null && secKeyData.length > 0)
            pOut.write(secKeyData);
        out.writePacket(3, bOut.toByteArray(), true);
    }

    private int version;
    private int encAlgorithm;
    private S2K s2k;
    private byte secKeyData[];
}
