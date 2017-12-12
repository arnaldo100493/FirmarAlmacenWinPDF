// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SubjectKeyIdentifierStructure.java

package co.org.bouncy.x509.extension;

import co.org.bouncy.asn1.ASN1OctetString;
import co.org.bouncy.asn1.x509.SubjectKeyIdentifier;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.PublicKey;

// Referenced classes of package co.org.bouncy.x509.extension:
//            X509ExtensionUtil

/**
 * @deprecated Class SubjectKeyIdentifierStructure is deprecated
 */

public class SubjectKeyIdentifierStructure extends SubjectKeyIdentifier
{

    public SubjectKeyIdentifierStructure(byte encodedValue[])
        throws IOException
    {
        super((ASN1OctetString)X509ExtensionUtil.fromExtensionValue(encodedValue));
    }

    private static ASN1OctetString fromPublicKey(PublicKey pubKey)
        throws InvalidKeyException
    {
        try
        {
            SubjectPublicKeyInfo info = SubjectPublicKeyInfo.getInstance(pubKey.getEncoded());
            return (ASN1OctetString)(ASN1OctetString)(new SubjectKeyIdentifier(info)).toASN1Object();
        }
        catch(Exception e)
        {
            throw new InvalidKeyException((new StringBuilder()).append("Exception extracting key details: ").append(e.toString()).toString());
        }
    }

    public SubjectKeyIdentifierStructure(PublicKey pubKey)
        throws InvalidKeyException
    {
        super(fromPublicKey(pubKey));
    }
}
