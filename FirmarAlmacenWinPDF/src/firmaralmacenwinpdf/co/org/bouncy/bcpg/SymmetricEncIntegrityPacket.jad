// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SymmetricEncIntegrityPacket.java

package co.org.bouncy.bcpg;

import java.io.IOException;

// Referenced classes of package co.org.bouncy.bcpg:
//            InputStreamPacket, BCPGInputStream

public class SymmetricEncIntegrityPacket extends InputStreamPacket
{

    SymmetricEncIntegrityPacket(BCPGInputStream in)
        throws IOException
    {
        super(in);
        version = in.read();
    }

    int version;
}
