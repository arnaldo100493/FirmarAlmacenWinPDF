// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CertificateUtil.java

package co.com.pdf.text.pdf.security;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.security.cert.*;

public class CertificateUtil
{

    public CertificateUtil()
    {
    }

    public static CRL getCRL(X509Certificate certificate)
        throws CertificateException, CRLException, IOException
    {
        return getCRL(getCRLURL(certificate));
    }

    public static String getCRLURL(X509Certificate certificate)
        throws CertificateParsingException
    {
        ASN1Primitive obj;
        try
        {
            obj = getExtensionValue(certificate, Extension.cRLDistributionPoints.getId());
        }
        catch(IOException e)
        {
            obj = null;
        }
        if(obj == null)
            return null;
        CRLDistPoint dist = CRLDistPoint.getInstance(obj);
        DistributionPoint dists[] = dist.getDistributionPoints();
        DistributionPoint arr$[] = dists;
        int len$ = arr$.length;
label0:
        for(int i$ = 0; i$ < len$; i$++)
        {
            DistributionPoint p = arr$[i$];
            DistributionPointName distributionPointName = p.getDistributionPoint();
            if(0 != distributionPointName.getType())
                continue;
            GeneralNames generalNames = (GeneralNames)distributionPointName.getName();
            GeneralName names[] = generalNames.getNames();
            GeneralName arr$[] = names;
            int len$ = arr$.length;
            int i$ = 0;
            do
            {
                if(i$ >= len$)
                    continue label0;
                GeneralName name = arr$[i$];
                if(name.getTagNo() == 6)
                {
                    DERIA5String derStr = DERIA5String.getInstance((ASN1TaggedObject)name.toASN1Primitive(), false);
                    return derStr.getString();
                }
                i$++;
            } while(true);
        }

        return null;
    }

    public static CRL getCRL(String url)
        throws IOException, CertificateException, CRLException
    {
        if(url == null)
        {
            return null;
        } else
        {
            java.io.InputStream is = (new URL(url)).openStream();
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            return cf.generateCRL(is);
        }
    }

    public static String getOCSPURL(X509Certificate certificate)
    {
        ASN1Primitive obj;
        ASN1Sequence AccessDescriptions;
        int i;
        String AccessLocation;
        try
        {
            obj = getExtensionValue(certificate, Extension.authorityInfoAccess.getId());
            if(obj == null)
                return null;
        }
        catch(IOException e)
        {
            return null;
        }
        AccessDescriptions = (ASN1Sequence)obj;
        i = 0;
_L1:
        if(i >= AccessDescriptions.size())
            break MISSING_BLOCK_LABEL_131;
        ASN1Sequence AccessDescription = (ASN1Sequence)AccessDescriptions.getObjectAt(i);
        if(AccessDescription.size() != 2 || !(AccessDescription.getObjectAt(0) instanceof ASN1ObjectIdentifier))
            break MISSING_BLOCK_LABEL_119;
        ASN1ObjectIdentifier id = (ASN1ObjectIdentifier)AccessDescription.getObjectAt(0);
        if(!"1.3.6.1.5.5.7.48.1".equals(id.getId()))
            break MISSING_BLOCK_LABEL_119;
        ASN1Primitive description = (ASN1Primitive)AccessDescription.getObjectAt(1);
        AccessLocation = getStringFromGeneralName(description);
        if(AccessLocation == null)
            return "";
        return AccessLocation;
        i++;
          goto _L1
        return null;
    }

    public static String getTSAURL(X509Certificate certificate)
    {
        byte der[] = certificate.getExtensionValue("1.2.840.113583.1.1.9.1");
        if(der == null)
            return null;
        try
        {
            ASN1Primitive asn1obj = ASN1Primitive.fromByteArray(der);
            DEROctetString octets = (DEROctetString)asn1obj;
            asn1obj = ASN1Primitive.fromByteArray(octets.getOctets());
            ASN1Sequence asn1seq = ASN1Sequence.getInstance(asn1obj);
            return getStringFromGeneralName(asn1seq.getObjectAt(1).toASN1Primitive());
        }
        catch(IOException e)
        {
            return null;
        }
    }

    private static ASN1Primitive getExtensionValue(X509Certificate certificate, String oid)
        throws IOException
    {
        byte bytes[] = certificate.getExtensionValue(oid);
        if(bytes == null)
        {
            return null;
        } else
        {
            ASN1InputStream aIn = new ASN1InputStream(new ByteArrayInputStream(bytes));
            ASN1OctetString octs = (ASN1OctetString)aIn.readObject();
            aIn = new ASN1InputStream(new ByteArrayInputStream(octs.getOctets()));
            return aIn.readObject();
        }
    }

    private static String getStringFromGeneralName(ASN1Primitive names)
        throws IOException
    {
        ASN1TaggedObject taggedObject = (ASN1TaggedObject)names;
        return new String(ASN1OctetString.getInstance(taggedObject, false).getOctets(), "ISO-8859-1");
    }
}
