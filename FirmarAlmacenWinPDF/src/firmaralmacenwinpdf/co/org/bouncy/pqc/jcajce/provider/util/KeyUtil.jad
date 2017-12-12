// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KeyUtil.java

package co.org.bouncy.pqc.jcajce.provider.util;

import co.org.bouncy.asn1.ASN1Encodable;
import co.org.bouncy.asn1.pkcs.PrivateKeyInfo;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;

public class KeyUtil
{

    public KeyUtil()
    {
    }

    public static byte[] getEncodedSubjectPublicKeyInfo(AlgorithmIdentifier algId, ASN1Encodable keyData)
    {
        try
        {
            return getEncodedSubjectPublicKeyInfo(new SubjectPublicKeyInfo(algId, keyData));
        }
        catch(Exception e)
        {
            return null;
        }
    }

    public static byte[] getEncodedSubjectPublicKeyInfo(AlgorithmIdentifier algId, byte keyData[])
    {
        try
        {
            return getEncodedSubjectPublicKeyInfo(new SubjectPublicKeyInfo(algId, keyData));
        }
        catch(Exception e)
        {
            return null;
        }
    }

    public static byte[] getEncodedSubjectPublicKeyInfo(SubjectPublicKeyInfo info)
    {
        try
        {
            return info.getEncoded("DER");
        }
        catch(Exception e)
        {
            return null;
        }
    }

    public static byte[] getEncodedPrivateKeyInfo(AlgorithmIdentifier algId, ASN1Encodable privKey)
    {
        try
        {
            PrivateKeyInfo info = new PrivateKeyInfo(algId, privKey.toASN1Primitive());
            return getEncodedPrivateKeyInfo(info);
        }
        catch(Exception e)
        {
            return null;
        }
    }

    public static byte[] getEncodedPrivateKeyInfo(PrivateKeyInfo info)
    {
        try
        {
            return info.getEncoded("DER");
        }
        catch(Exception e)
        {
            return null;
        }
    }
}
