// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UseSRTPData.java

package co.org.bouncy.crypto.tls;


// Referenced classes of package co.org.bouncy.crypto.tls:
//            TlsUtils

public class UseSRTPData
{

    public UseSRTPData(int protectionProfiles[], byte mki[])
    {
        if(protectionProfiles == null || protectionProfiles.length < 1 || protectionProfiles.length >= 32768)
            throw new IllegalArgumentException("'protectionProfiles' must have length from 1 to (2^15 - 1)");
        if(mki == null)
            mki = TlsUtils.EMPTY_BYTES;
        else
        if(mki.length > 255)
            throw new IllegalArgumentException("'mki' cannot be longer than 255 bytes");
        this.protectionProfiles = protectionProfiles;
        this.mki = mki;
    }

    public int[] getProtectionProfiles()
    {
        return protectionProfiles;
    }

    public byte[] getMki()
    {
        return mki;
    }

    private int protectionProfiles[];
    private byte mki[];
}
