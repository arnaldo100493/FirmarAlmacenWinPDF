// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SecretKeyPacket.java

package co.org.bouncy.bcpg;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

// Referenced classes of package co.org.bouncy.bcpg:
//            ContainedPacket, SecretSubkeyPacket, PublicSubkeyPacket, PublicKeyPacket, 
//            S2K, BCPGOutputStream, PublicKeyAlgorithmTags, BCPGInputStream

public class SecretKeyPacket extends ContainedPacket
    implements PublicKeyAlgorithmTags
{

    SecretKeyPacket(BCPGInputStream in)
        throws IOException
    {
        if(this instanceof SecretSubkeyPacket)
            pubKeyPacket = new PublicSubkeyPacket(in);
        else
            pubKeyPacket = new PublicKeyPacket(in);
        s2kUsage = in.read();
        if(s2kUsage == 255 || s2kUsage == 254)
        {
            encAlgorithm = in.read();
            s2k = new S2K(in);
        } else
        {
            encAlgorithm = s2kUsage;
        }
        if((s2k == null || s2k.getType() != 101 || s2k.getProtectionMode() != 1) && s2kUsage != 0)
        {
            if(encAlgorithm < 7)
                iv = new byte[8];
            else
                iv = new byte[16];
            in.readFully(iv, 0, iv.length);
        }
        secKeyData = in.readAll();
    }

    public SecretKeyPacket(PublicKeyPacket pubKeyPacket, int encAlgorithm, S2K s2k, byte iv[], byte secKeyData[])
    {
        this.pubKeyPacket = pubKeyPacket;
        this.encAlgorithm = encAlgorithm;
        if(encAlgorithm != 0)
            s2kUsage = 255;
        else
            s2kUsage = 0;
        this.s2k = s2k;
        this.iv = iv;
        this.secKeyData = secKeyData;
    }

    public SecretKeyPacket(PublicKeyPacket pubKeyPacket, int encAlgorithm, int s2kUsage, S2K s2k, byte iv[], byte secKeyData[])
    {
        this.pubKeyPacket = pubKeyPacket;
        this.encAlgorithm = encAlgorithm;
        this.s2kUsage = s2kUsage;
        this.s2k = s2k;
        this.iv = iv;
        this.secKeyData = secKeyData;
    }

    public int getEncAlgorithm()
    {
        return encAlgorithm;
    }

    public int getS2KUsage()
    {
        return s2kUsage;
    }

    public byte[] getIV()
    {
        return iv;
    }

    public S2K getS2K()
    {
        return s2k;
    }

    public PublicKeyPacket getPublicKeyPacket()
    {
        return pubKeyPacket;
    }

    public byte[] getSecretKeyData()
    {
        return secKeyData;
    }

    public byte[] getEncodedContents()
        throws IOException
    {
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        BCPGOutputStream pOut = new BCPGOutputStream(bOut);
        pOut.write(pubKeyPacket.getEncodedContents());
        pOut.write(s2kUsage);
        if(s2kUsage == 255 || s2kUsage == 254)
        {
            pOut.write(encAlgorithm);
            pOut.writeObject(s2k);
        }
        if(iv != null)
            pOut.write(iv);
        if(secKeyData != null && secKeyData.length > 0)
            pOut.write(secKeyData);
        return bOut.toByteArray();
    }

    public void encode(BCPGOutputStream out)
        throws IOException
    {
        out.writePacket(5, getEncodedContents(), true);
    }

    public static final int USAGE_NONE = 0;
    public static final int USAGE_CHECKSUM = 255;
    public static final int USAGE_SHA1 = 254;
    private PublicKeyPacket pubKeyPacket;
    private byte secKeyData[];
    private int s2kUsage;
    private int encAlgorithm;
    private S2K s2k;
    private byte iv[];
}
