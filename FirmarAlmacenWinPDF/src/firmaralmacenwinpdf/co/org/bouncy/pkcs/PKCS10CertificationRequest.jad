// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PKCS10CertificationRequest.java

package co.org.bouncy.pkcs;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.pkcs.*;
import co.org.bouncy.asn1.x500.X500Name;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import co.org.bouncy.operator.ContentVerifier;
import co.org.bouncy.operator.ContentVerifierProvider;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package co.org.bouncy.pkcs:
//            PKCSIOException, PKCSException

public class PKCS10CertificationRequest
{

    private static CertificationRequest parseBytes(byte encoding[])
        throws IOException
    {
        try
        {
            return CertificationRequest.getInstance(ASN1Primitive.fromByteArray(encoding));
        }
        catch(ClassCastException e)
        {
            throw new PKCSIOException((new StringBuilder()).append("malformed data: ").append(e.getMessage()).toString(), e);
        }
        catch(IllegalArgumentException e)
        {
            throw new PKCSIOException((new StringBuilder()).append("malformed data: ").append(e.getMessage()).toString(), e);
        }
    }

    public PKCS10CertificationRequest(CertificationRequest certificationRequest)
    {
        this.certificationRequest = certificationRequest;
    }

    public PKCS10CertificationRequest(byte encoded[])
        throws IOException
    {
        this(parseBytes(encoded));
    }

    public CertificationRequest toASN1Structure()
    {
        return certificationRequest;
    }

    public X500Name getSubject()
    {
        return X500Name.getInstance(certificationRequest.getCertificationRequestInfo().getSubject());
    }

    public AlgorithmIdentifier getSignatureAlgorithm()
    {
        return certificationRequest.getSignatureAlgorithm();
    }

    public byte[] getSignature()
    {
        return certificationRequest.getSignature().getBytes();
    }

    public SubjectPublicKeyInfo getSubjectPublicKeyInfo()
    {
        return certificationRequest.getCertificationRequestInfo().getSubjectPublicKeyInfo();
    }

    public Attribute[] getAttributes()
    {
        ASN1Set attrSet = certificationRequest.getCertificationRequestInfo().getAttributes();
        if(attrSet == null)
            return EMPTY_ARRAY;
        Attribute attrs[] = new Attribute[attrSet.size()];
        for(int i = 0; i != attrSet.size(); i++)
            attrs[i] = Attribute.getInstance(attrSet.getObjectAt(i));

        return attrs;
    }

    public Attribute[] getAttributes(ASN1ObjectIdentifier type)
    {
        ASN1Set attrSet = certificationRequest.getCertificationRequestInfo().getAttributes();
        if(attrSet == null)
            return EMPTY_ARRAY;
        List list = new ArrayList();
        for(int i = 0; i != attrSet.size(); i++)
        {
            Attribute attr = Attribute.getInstance(attrSet.getObjectAt(i));
            if(attr.getAttrType().equals(type))
                list.add(attr);
        }

        if(list.size() == 0)
            return EMPTY_ARRAY;
        else
            return (Attribute[])(Attribute[])list.toArray(new Attribute[list.size()]);
    }

    public byte[] getEncoded()
        throws IOException
    {
        return certificationRequest.getEncoded();
    }

    public boolean isSignatureValid(ContentVerifierProvider verifierProvider)
        throws PKCSException
    {
        CertificationRequestInfo requestInfo = certificationRequest.getCertificationRequestInfo();
        ContentVerifier verifier;
        try
        {
            verifier = verifierProvider.get(certificationRequest.getSignatureAlgorithm());
            OutputStream sOut = verifier.getOutputStream();
            sOut.write(requestInfo.getEncoded("DER"));
            sOut.close();
        }
        catch(Exception e)
        {
            throw new PKCSException((new StringBuilder()).append("unable to process signature: ").append(e.getMessage()).toString(), e);
        }
        return verifier.verify(certificationRequest.getSignature().getBytes());
    }

    public boolean equals(Object o)
    {
        if(o == this)
            return true;
        if(!(o instanceof PKCS10CertificationRequest))
        {
            return false;
        } else
        {
            PKCS10CertificationRequest other = (PKCS10CertificationRequest)o;
            return toASN1Structure().equals(other.toASN1Structure());
        }
    }

    public int hashCode()
    {
        return toASN1Structure().hashCode();
    }

    private static Attribute EMPTY_ARRAY[] = new Attribute[0];
    private CertificationRequest certificationRequest;

}
