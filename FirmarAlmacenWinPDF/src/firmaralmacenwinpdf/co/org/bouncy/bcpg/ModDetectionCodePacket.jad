// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ModDetectionCodePacket.java

package co.org.bouncy.bcpg;

import java.io.IOException;

// Referenced classes of package co.org.bouncy.bcpg:
//            ContainedPacket, BCPGInputStream, BCPGOutputStream

public class ModDetectionCodePacket extends ContainedPacket
{

    ModDetectionCodePacket(BCPGInputStream in)
        throws IOException
    {
        digest = new byte[20];
        in.readFully(digest);
    }

    public ModDetectionCodePacket(byte digest[])
        throws IOException
    {
        this.digest = new byte[digest.length];
        System.arraycopy(digest, 0, this.digest, 0, this.digest.length);
    }

    public byte[] getDigest()
    {
        byte tmp[] = new byte[digest.length];
        System.arraycopy(digest, 0, tmp, 0, tmp.length);
        return tmp;
    }

    public void encode(BCPGOutputStream out)
        throws IOException
    {
        out.writePacket(19, digest, false);
    }

    private byte digest[];
}
