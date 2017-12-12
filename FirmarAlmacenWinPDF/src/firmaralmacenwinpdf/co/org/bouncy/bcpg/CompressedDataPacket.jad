// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CompressedDataPacket.java

package co.org.bouncy.bcpg;

import java.io.IOException;

// Referenced classes of package co.org.bouncy.bcpg:
//            InputStreamPacket, BCPGInputStream

public class CompressedDataPacket extends InputStreamPacket
{

    CompressedDataPacket(BCPGInputStream in)
        throws IOException
    {
        super(in);
        algorithm = in.read();
    }

    public int getAlgorithm()
    {
        return algorithm;
    }

    int algorithm;
}
