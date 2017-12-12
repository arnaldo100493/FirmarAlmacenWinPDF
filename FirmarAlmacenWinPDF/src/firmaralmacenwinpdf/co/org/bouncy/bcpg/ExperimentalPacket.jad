// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ExperimentalPacket.java

package co.org.bouncy.bcpg;

import co.org.bouncy.util.Arrays;
import java.io.IOException;

// Referenced classes of package co.org.bouncy.bcpg:
//            ContainedPacket, PublicKeyAlgorithmTags, BCPGInputStream, BCPGOutputStream

public class ExperimentalPacket extends ContainedPacket
    implements PublicKeyAlgorithmTags
{

    ExperimentalPacket(int tag, BCPGInputStream in)
        throws IOException
    {
        this.tag = tag;
        contents = in.readAll();
    }

    public int getTag()
    {
        return tag;
    }

    public byte[] getContents()
    {
        return Arrays.clone(contents);
    }

    public void encode(BCPGOutputStream out)
        throws IOException
    {
        out.writePacket(tag, contents, true);
    }

    private int tag;
    private byte contents[];
}
