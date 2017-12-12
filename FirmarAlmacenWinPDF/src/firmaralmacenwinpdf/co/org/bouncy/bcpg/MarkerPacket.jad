// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MarkerPacket.java

package co.org.bouncy.bcpg;

import java.io.IOException;

// Referenced classes of package co.org.bouncy.bcpg:
//            ContainedPacket, BCPGInputStream, BCPGOutputStream

public class MarkerPacket extends ContainedPacket
{

    public MarkerPacket(BCPGInputStream in)
        throws IOException
    {
        in.readFully(marker);
    }

    public void encode(BCPGOutputStream out)
        throws IOException
    {
        out.writePacket(10, marker, true);
    }

    byte marker[] = {
        80, 71, 80
    };
}
