// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RevocationKey.java

package co.org.bouncy.bcpg.sig;

import co.org.bouncy.bcpg.SignatureSubpacket;

public class RevocationKey extends SignatureSubpacket
{

    public RevocationKey(boolean isCritical, byte data[])
    {
        super(12, isCritical, data);
    }

    public RevocationKey(boolean isCritical, byte signatureClass, int keyAlgorithm, byte fingerprint[])
    {
        super(12, isCritical, createData(signatureClass, (byte)(keyAlgorithm & 0xff), fingerprint));
    }

    private static byte[] createData(byte signatureClass, byte keyAlgorithm, byte fingerprint[])
    {
        byte data[] = new byte[2 + fingerprint.length];
        data[0] = signatureClass;
        data[1] = keyAlgorithm;
        System.arraycopy(fingerprint, 0, data, 2, fingerprint.length);
        return data;
    }

    public byte getSignatureClass()
    {
        return getData()[0];
    }

    public int getAlgorithm()
    {
        return getData()[1];
    }

    public byte[] getFingerprint()
    {
        byte data[] = getData();
        byte fingerprint[] = new byte[data.length - 2];
        System.arraycopy(data, 2, fingerprint, 0, fingerprint.length);
        return fingerprint;
    }
}
