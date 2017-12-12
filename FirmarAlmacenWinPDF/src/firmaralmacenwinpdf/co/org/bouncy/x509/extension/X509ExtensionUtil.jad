// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   X509ExtensionUtil.java

package co.org.bouncy.x509.extension;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x500.X500Name;
import co.org.bouncy.asn1.x509.GeneralName;
import co.org.bouncy.asn1.x509.X509Extension;
import co.org.bouncy.util.Integers;
import java.io.IOException;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.*;

public class X509ExtensionUtil
{

    public X509ExtensionUtil()
    {
    }

    public static ASN1Primitive fromExtensionValue(byte encodedValue[])
        throws IOException
    {
        ASN1OctetString octs = (ASN1OctetString)ASN1Primitive.fromByteArray(encodedValue);
        return ASN1Primitive.fromByteArray(octs.getOctets());
    }

    public static Collection getIssuerAlternativeNames(X509Certificate cert)
        throws CertificateParsingException
    {
        byte extVal[] = cert.getExtensionValue(X509Extension.issuerAlternativeName.getId());
        return getAlternativeNames(extVal);
    }

    public static Collection getSubjectAlternativeNames(X509Certificate cert)
        throws CertificateParsingException
    {
        byte extVal[] = cert.getExtensionValue(X509Extension.subjectAlternativeName.getId());
        return getAlternativeNames(extVal);
    }

    private static Collection getAlternativeNames(byte extVal[])
        throws CertificateParsingException
    {
        if(extVal == null)
            return Collections.EMPTY_LIST;
        try
        {
            Collection temp = new ArrayList();
            List list;
            for(Enumeration it = DERSequence.getInstance(fromExtensionValue(extVal)).getObjects(); it.hasMoreElements(); temp.add(list))
            {
                GeneralName genName = GeneralName.getInstance(it.nextElement());
                list = new ArrayList();
                list.add(Integers.valueOf(genName.getTagNo()));
                switch(genName.getTagNo())
                {
                case 0: // '\0'
                case 3: // '\003'
                case 5: // '\005'
                    list.add(genName.getName().toASN1Primitive());
                    break;

                case 4: // '\004'
                    list.add(X500Name.getInstance(genName.getName()).toString());
                    break;

                case 1: // '\001'
                case 2: // '\002'
                case 6: // '\006'
                    list.add(((ASN1String)genName.getName()).getString());
                    break;

                case 8: // '\b'
                    list.add(ASN1ObjectIdentifier.getInstance(genName.getName()).getId());
                    break;

                case 7: // '\007'
                    list.add(DEROctetString.getInstance(genName.getName()).getOctets());
                    break;

                default:
                    throw new IOException((new StringBuilder()).append("Bad tag number: ").append(genName.getTagNo()).toString());
                }
            }

            return Collections.unmodifiableCollection(temp);
        }
        catch(Exception e)
        {
            throw new CertificateParsingException(e.getMessage());
        }
    }
}
