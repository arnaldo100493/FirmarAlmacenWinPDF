// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TrustPacket.java

package co.org.bouncy.bcpg;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

// Referenced classes of package co.org.bouncy.bcpg:
//            ContainedPacket, BCPGInputStream, BCPGOutputStream

public class TrustPacket extends ContainedPacket
{

    public TrustPacket(BCPGInputStream in)
        throws IOException
    {
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        int ch;
        while((ch = in.read()) >= 0) 
            bOut.write(ch);
        levelAndTrustAmount = bOut.toByteArray();
    }

    public TrustPacket(int trustCode)
    {
        levelAndTrustAmount = new byte[1];
        levelAndTrustAmount[0] = (byte)trustCode;
    }

    public byte[] getLevelAndTrustAmount()
    {
        return levelAndTrustAmount;
    }

    public void encode(BCPGOutputStream out)
        throws IOException
    {
        out.writePacket(12, levelAndTrustAmount, true);
    }

    byte levelAndTrustAmount[];
}
