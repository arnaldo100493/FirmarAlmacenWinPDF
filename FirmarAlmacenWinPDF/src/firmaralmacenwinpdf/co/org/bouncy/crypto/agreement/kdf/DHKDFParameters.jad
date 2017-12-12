// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DHKDFParameters.java

package co.org.bouncy.crypto.agreement.kdf;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.DERObjectIdentifier;
import co.org.bouncy.crypto.DerivationParameters;

public class DHKDFParameters
    implements DerivationParameters
{

    public DHKDFParameters(DERObjectIdentifier algorithm, int keySize, byte z[])
    {
        this(algorithm, keySize, z, null);
    }

    public DHKDFParameters(DERObjectIdentifier algorithm, int keySize, byte z[], byte extraInfo[])
    {
        this.algorithm = new ASN1ObjectIdentifier(algorithm.getId());
        this.keySize = keySize;
        this.z = z;
        this.extraInfo = extraInfo;
    }

    public ASN1ObjectIdentifier getAlgorithm()
    {
        return algorithm;
    }

    public int getKeySize()
    {
        return keySize;
    }

    public byte[] getZ()
    {
        return z;
    }

    public byte[] getExtraInfo()
    {
        return extraInfo;
    }

    private ASN1ObjectIdentifier algorithm;
    private int keySize;
    private byte z[];
    private byte extraInfo[];
}
