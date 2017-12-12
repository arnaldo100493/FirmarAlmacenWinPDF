// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   X509AttributeCertificateHolder.java

package co.org.bouncy.cert;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.*;
import co.org.bouncy.operator.ContentVerifier;
import co.org.bouncy.operator.ContentVerifierProvider;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.*;

// Referenced classes of package co.org.bouncy.cert:
//            CertIOException, AttributeCertificateHolder, AttributeCertificateIssuer, CertException, 
//            CertUtils

public class X509AttributeCertificateHolder
{

    private static AttributeCertificate parseBytes(byte certEncoding[])
        throws IOException
    {
        try
        {
            return AttributeCertificate.getInstance(ASN1Primitive.fromByteArray(certEncoding));
        }
        catch(ClassCastException e)
        {
            throw new CertIOException((new StringBuilder()).append("malformed data: ").append(e.getMessage()).toString(), e);
        }
        catch(IllegalArgumentException e)
        {
            throw new CertIOException((new StringBuilder()).append("malformed data: ").append(e.getMessage()).toString(), e);
        }
    }

    public X509AttributeCertificateHolder(byte certEncoding[])
        throws IOException
    {
        this(parseBytes(certEncoding));
    }

    public X509AttributeCertificateHolder(AttributeCertificate attrCert)
    {
        this.attrCert = attrCert;
        extensions = attrCert.getAcinfo().getExtensions();
    }

    public byte[] getEncoded()
        throws IOException
    {
        return attrCert.getEncoded();
    }

    public int getVersion()
    {
        return attrCert.getAcinfo().getVersion().getValue().intValue() + 1;
    }

    public BigInteger getSerialNumber()
    {
        return attrCert.getAcinfo().getSerialNumber().getValue();
    }

    public AttributeCertificateHolder getHolder()
    {
        return new AttributeCertificateHolder((ASN1Sequence)attrCert.getAcinfo().getHolder().toASN1Primitive());
    }

    public AttributeCertificateIssuer getIssuer()
    {
        return new AttributeCertificateIssuer(attrCert.getAcinfo().getIssuer());
    }

    public Date getNotBefore()
    {
        return CertUtils.recoverDate(attrCert.getAcinfo().getAttrCertValidityPeriod().getNotBeforeTime());
    }

    public Date getNotAfter()
    {
        return CertUtils.recoverDate(attrCert.getAcinfo().getAttrCertValidityPeriod().getNotAfterTime());
    }

    public Attribute[] getAttributes()
    {
        ASN1Sequence seq = attrCert.getAcinfo().getAttributes();
        Attribute attrs[] = new Attribute[seq.size()];
        for(int i = 0; i != seq.size(); i++)
            attrs[i] = Attribute.getInstance(seq.getObjectAt(i));

        return attrs;
    }

    public Attribute[] getAttributes(ASN1ObjectIdentifier type)
    {
        ASN1Sequence seq = attrCert.getAcinfo().getAttributes();
        List list = new ArrayList();
        for(int i = 0; i != seq.size(); i++)
        {
            Attribute attr = Attribute.getInstance(seq.getObjectAt(i));
            if(attr.getAttrType().equals(type))
                list.add(attr);
        }

        if(list.size() == 0)
            return EMPTY_ARRAY;
        else
            return (Attribute[])(Attribute[])list.toArray(new Attribute[list.size()]);
    }

    public boolean hasExtensions()
    {
        return extensions != null;
    }

    public Extension getExtension(ASN1ObjectIdentifier oid)
    {
        if(extensions != null)
            return extensions.getExtension(oid);
        else
            return null;
    }

    public Extensions getExtensions()
    {
        return extensions;
    }

    public List getExtensionOIDs()
    {
        return CertUtils.getExtensionOIDs(extensions);
    }

    public Set getCriticalExtensionOIDs()
    {
        return CertUtils.getCriticalExtensionOIDs(extensions);
    }

    public Set getNonCriticalExtensionOIDs()
    {
        return CertUtils.getNonCriticalExtensionOIDs(extensions);
    }

    public boolean[] getIssuerUniqueID()
    {
        return CertUtils.bitStringToBoolean(attrCert.getAcinfo().getIssuerUniqueID());
    }

    public AlgorithmIdentifier getSignatureAlgorithm()
    {
        return attrCert.getSignatureAlgorithm();
    }

    public byte[] getSignature()
    {
        return attrCert.getSignatureValue().getBytes();
    }

    public AttributeCertificate toASN1Structure()
    {
        return attrCert;
    }

    public boolean isValidOn(Date date)
    {
        AttCertValidityPeriod certValidityPeriod = attrCert.getAcinfo().getAttrCertValidityPeriod();
        return !date.before(CertUtils.recoverDate(certValidityPeriod.getNotBeforeTime())) && !date.after(CertUtils.recoverDate(certValidityPeriod.getNotAfterTime()));
    }

    public boolean isSignatureValid(ContentVerifierProvider verifierProvider)
        throws CertException
    {
        AttributeCertificateInfo acinfo = attrCert.getAcinfo();
        if(!CertUtils.isAlgIdEqual(acinfo.getSignature(), attrCert.getSignatureAlgorithm()))
            throw new CertException("signature invalid - algorithm identifier mismatch");
        ContentVerifier verifier;
        try
        {
            verifier = verifierProvider.get(acinfo.getSignature());
            OutputStream sOut = verifier.getOutputStream();
            DEROutputStream dOut = new DEROutputStream(sOut);
            dOut.writeObject(acinfo);
            sOut.close();
        }
        catch(Exception e)
        {
            throw new CertException((new StringBuilder()).append("unable to process signature: ").append(e.getMessage()).toString(), e);
        }
        return verifier.verify(attrCert.getSignatureValue().getBytes());
    }

    public boolean equals(Object o)
    {
        if(o == this)
            return true;
        if(!(o instanceof X509AttributeCertificateHolder))
        {
            return false;
        } else
        {
            X509AttributeCertificateHolder other = (X509AttributeCertificateHolder)o;
            return attrCert.equals(other.attrCert);
        }
    }

    public int hashCode()
    {
        return attrCert.hashCode();
    }

    private static Attribute EMPTY_ARRAY[] = new Attribute[0];
    private AttributeCertificate attrCert;
    private Extensions extensions;

}
