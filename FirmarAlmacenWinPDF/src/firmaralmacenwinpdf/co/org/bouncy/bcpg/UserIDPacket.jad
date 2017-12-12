// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UserIDPacket.java

package co.org.bouncy.bcpg;

import co.org.bouncy.util.Strings;
import java.io.IOException;

// Referenced classes of package co.org.bouncy.bcpg:
//            ContainedPacket, BCPGInputStream, BCPGOutputStream

public class UserIDPacket extends ContainedPacket
{

    public UserIDPacket(BCPGInputStream in)
        throws IOException
    {
        idData = in.readAll();
    }

    public UserIDPacket(String id)
    {
        idData = Strings.toUTF8ByteArray(id);
    }

    public String getID()
    {
        return Strings.fromUTF8ByteArray(idData);
    }

    public void encode(BCPGOutputStream out)
        throws IOException
    {
        out.writePacket(13, idData, true);
    }

    private byte idData[];
}
