// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RequestedCertificate.java

package co.org.bouncy.asn1.isismtt.ocsp;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.Certificate;
import java.io.IOException;

public class RequestedCertificate extends ASN1Object
    implements ASN1Choice
{

    public static RequestedCertificate getInstance(Object obj)
    {
        if(obj == null || (obj instanceof RequestedCertificate))
            return (RequestedCertificate)obj;
        if(obj instanceof ASN1Sequence)
            return new RequestedCertificate(Certificate.getInstance(obj));
        if(obj instanceof ASN1TaggedObject)
            return new RequestedCertificate((ASN1TaggedObject)obj);
        else
            throw new IllegalArgumentException((new StringBuilder()).append("illegal object in getInstance: ").append(obj.getClass().getName()).toString());
    }

    public static RequestedCertificate getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        if(!explicit)
            throw new IllegalArgumentException("choice item must be explicitly tagged");
        else
            return getInstance(obj.getObject());
    }

    private RequestedCertificate(ASN1TaggedObject tagged)
    {
        if(tagged.getTagNo() == 0)
            publicKeyCert = ASN1OctetString.getInstance(tagged, true).getOctets();
        else
        if(tagged.getTagNo() == 1)
            attributeCert = ASN1OctetString.getInstance(tagged, true).getOctets();
        else
            throw new IllegalArgumentException((new StringBuilder()).append("unknown tag number: ").append(tagged.getTagNo()).toString());
    }

    public RequestedCertificate(Certificate certificate)
    {
        cert = certificate;
    }

    public RequestedCertificate(int type, byte certificateOctets[])
    {
        this(((ASN1TaggedObject) (new DERTaggedObject(type, new DEROctetString(certificateOctets)))));
    }

    public int getType()
    {
        if(cert != null)
            return -1;
        return publicKeyCert == null ? 1 : 0;
    }

    public byte[] getCertificateBytes()
    {
        if(cert != null)
            try
            {
                return cert.getEncoded();
            }
            catch(IOException e)
            {
                throw new IllegalStateException((new StringBuilder()).append("can't decode certificate: ").append(e).toString());
            }
        if(publicKeyCert != null)
            return publicKeyCert;
        else
            return attributeCert;
    }

    public ASN1Primitive toASN1Primitive()
    {
        if(publicKeyCert != null)
            return new DERTaggedObject(0, new DEROctetString(publicKeyCert));
        if(attributeCert != null)
            return new DERTaggedObject(1, new DEROctetString(attributeCert));
        else
            return cert.toASN1Primitive();
    }

    public static final int certificate = -1;
    public static final int publicKeyCertificate = 0;
    public static final int attributeCertificate = 1;
    private Certificate cert;
    private byte publicKeyCert[];
    private byte attributeCert[];
}
