// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMSConfig.java

package co.org.bouncy.cms;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;

// Referenced classes of package co.org.bouncy.cms:
//            CMSSignedHelper

public class CMSConfig
{

    public CMSConfig()
    {
    }

    public static void setSigningEncryptionAlgorithmMapping(String oid, String algorithmName)
    {
        ASN1ObjectIdentifier id = new ASN1ObjectIdentifier(oid);
        CMSSignedHelper.INSTANCE.setSigningEncryptionAlgorithmMapping(id, algorithmName);
    }

    public static void setSigningDigestAlgorithmMapping(String oid, String algorithmName)
    {
        ASN1ObjectIdentifier id = new ASN1ObjectIdentifier(oid);
        CMSSignedHelper.INSTANCE.setSigningDigestAlgorithmMapping(id, algorithmName);
    }
}
