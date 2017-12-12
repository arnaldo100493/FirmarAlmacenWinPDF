// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OutputStreamPacket.java

package co.org.bouncy.bcpg;

import java.io.IOException;

// Referenced classes of package co.org.bouncy.bcpg:
//            BCPGOutputStream

public abstract class OutputStreamPacket
{

    public OutputStreamPacket(BCPGOutputStream out)
    {
        this.out = out;
    }

    public abstract BCPGOutputStream open()
        throws IOException;

    public abstract void close()
        throws IOException;

    protected BCPGOutputStream out;
}
