// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SecretSubkeyPacket.java

package co.org.bouncy.bcpg;

import java.io.IOException;

// Referenced classes of package co.org.bouncy.bcpg:
//            SecretKeyPacket, BCPGOutputStream, BCPGInputStream, PublicKeyPacket, 
//            S2K

public class SecretSubkeyPacket extends SecretKeyPacket
{

    SecretSubkeyPacket(BCPGInputStream in)
        throws IOException
    {
        super(in);
    }

    public SecretSubkeyPacket(PublicKeyPacket pubKeyPacket, int encAlgorithm, S2K s2k, byte iv[], byte secKeyData[])
    {
        super(pubKeyPacket, encAlgorithm, s2k, iv, secKeyData);
    }

    public SecretSubkeyPacket(PublicKeyPacket pubKeyPacket, int encAlgorithm, int s2kUsage, S2K s2k, byte iv[], byte secKeyData[])
    {
        super(pubKeyPacket, encAlgorithm, s2kUsage, s2k, iv, secKeyData);
    }

    public void encode(BCPGOutputStream out)
        throws IOException
    {
        out.writePacket(7, getEncodedContents(), true);
    }
}
