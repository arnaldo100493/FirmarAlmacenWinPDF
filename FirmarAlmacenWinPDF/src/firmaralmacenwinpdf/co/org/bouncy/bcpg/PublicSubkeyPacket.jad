// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PublicSubkeyPacket.java

package co.org.bouncy.bcpg;

import java.io.IOException;
import java.util.Date;

// Referenced classes of package co.org.bouncy.bcpg:
//            PublicKeyPacket, BCPGOutputStream, BCPGInputStream, BCPGKey

public class PublicSubkeyPacket extends PublicKeyPacket
{

    PublicSubkeyPacket(BCPGInputStream in)
        throws IOException
    {
        super(in);
    }

    public PublicSubkeyPacket(int algorithm, Date time, BCPGKey key)
    {
        super(algorithm, time, key);
    }

    public void encode(BCPGOutputStream out)
        throws IOException
    {
        out.writePacket(14, getEncodedContents(), true);
    }
}
