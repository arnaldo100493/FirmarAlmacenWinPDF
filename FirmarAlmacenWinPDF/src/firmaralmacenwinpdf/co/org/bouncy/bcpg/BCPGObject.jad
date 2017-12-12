// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BCPGObject.java

package co.org.bouncy.bcpg;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

// Referenced classes of package co.org.bouncy.bcpg:
//            BCPGOutputStream

public abstract class BCPGObject
{

    public BCPGObject()
    {
    }

    public byte[] getEncoded()
        throws IOException
    {
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        BCPGOutputStream pOut = new BCPGOutputStream(bOut);
        pOut.writeObject(this);
        return bOut.toByteArray();
    }

    public abstract void encode(BCPGOutputStream bcpgoutputstream)
        throws IOException;
}
