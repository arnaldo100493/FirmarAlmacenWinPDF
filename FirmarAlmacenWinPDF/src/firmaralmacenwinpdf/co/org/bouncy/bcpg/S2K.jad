// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   S2K.java

package co.org.bouncy.bcpg;

import java.io.*;

// Referenced classes of package co.org.bouncy.bcpg:
//            BCPGObject, BCPGOutputStream

public class S2K extends BCPGObject
{

    S2K(InputStream in)
        throws IOException
    {
        itCount = -1;
        protectionMode = -1;
        DataInputStream dIn = new DataInputStream(in);
        type = dIn.read();
        algorithm = dIn.read();
        if(type != 101)
        {
            if(type != 0)
            {
                iv = new byte[8];
                dIn.readFully(iv, 0, iv.length);
                if(type == 3)
                    itCount = dIn.read();
            }
        } else
        {
            dIn.read();
            dIn.read();
            dIn.read();
            protectionMode = dIn.read();
        }
    }

    public S2K(int algorithm)
    {
        itCount = -1;
        protectionMode = -1;
        type = 0;
        this.algorithm = algorithm;
    }

    public S2K(int algorithm, byte iv[])
    {
        itCount = -1;
        protectionMode = -1;
        type = 1;
        this.algorithm = algorithm;
        this.iv = iv;
    }

    public S2K(int algorithm, byte iv[], int itCount)
    {
        this.itCount = -1;
        protectionMode = -1;
        type = 3;
        this.algorithm = algorithm;
        this.iv = iv;
        this.itCount = itCount;
    }

    public int getType()
    {
        return type;
    }

    public int getHashAlgorithm()
    {
        return algorithm;
    }

    public byte[] getIV()
    {
        return iv;
    }

    public long getIterationCount()
    {
        return (long)(16 + (itCount & 0xf) << (itCount >> 4) + 6);
    }

    public int getProtectionMode()
    {
        return protectionMode;
    }

    public void encode(BCPGOutputStream out)
        throws IOException
    {
        out.write(type);
        out.write(algorithm);
        if(type != 101)
        {
            if(type != 0)
                out.write(iv);
            if(type == 3)
                out.write(itCount);
        } else
        {
            out.write(71);
            out.write(78);
            out.write(85);
            out.write(protectionMode);
        }
    }

    private static final int EXPBIAS = 6;
    public static final int SIMPLE = 0;
    public static final int SALTED = 1;
    public static final int SALTED_AND_ITERATED = 3;
    public static final int GNU_DUMMY_S2K = 101;
    int type;
    int algorithm;
    byte iv[];
    int itCount;
    int protectionMode;
}
