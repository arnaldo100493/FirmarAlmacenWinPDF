// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PKCS10CertificationRequestBuilder.java

package co.org.bouncy.pkcs;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.pkcs.*;
import co.org.bouncy.asn1.x500.X500Name;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import co.org.bouncy.operator.ContentSigner;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

// Referenced classes of package co.org.bouncy.pkcs:
//            PKCS10CertificationRequest

public class PKCS10CertificationRequestBuilder
{

    public PKCS10CertificationRequestBuilder(X500Name subject, SubjectPublicKeyInfo publicKeyInfo)
    {
        attributes = new ArrayList();
        leaveOffEmpty = false;
        this.subject = subject;
        this.publicKeyInfo = publicKeyInfo;
    }

    public PKCS10CertificationRequestBuilder addAttribute(ASN1ObjectIdentifier attrType, ASN1Encodable attrValue)
    {
        attributes.add(new Attribute(attrType, new DERSet(attrValue)));
        return this;
    }

    public PKCS10CertificationRequestBuilder addAttribute(ASN1ObjectIdentifier attrType, ASN1Encodable attrValues[])
    {
        attributes.add(new Attribute(attrType, new DERSet(attrValues)));
        return this;
    }

    public PKCS10CertificationRequestBuilder setLeaveOffEmptyAttributes(boolean leaveOffEmpty)
    {
        this.leaveOffEmpty = leaveOffEmpty;
        return this;
    }

    public PKCS10CertificationRequest build(ContentSigner signer)
    {
        CertificationRequestInfo info;
        if(attributes.isEmpty())
        {
            if(leaveOffEmpty)
                info = new CertificationRequestInfo(subject, publicKeyInfo, null);
            else
                info = new CertificationRequestInfo(subject, publicKeyInfo, new DERSet());
        } else
        {
            ASN1EncodableVector v = new ASN1EncodableVector();
            for(Iterator it = attributes.iterator(); it.hasNext(); v.add(Attribute.getInstance(it.next())));
            info = new CertificationRequestInfo(subject, publicKeyInfo, new DERSet(v));
        }
        try
        {
            OutputStream sOut = signer.getOutputStream();
            sOut.write(info.getEncoded("DER"));
            sOut.close();
            return new PKCS10CertificationRequest(new CertificationRequest(info, signer.getAlgorithmIdentifier(), new DERBitString(signer.getSignature())));
        }
        catch(IOException e)
        {
            throw new IllegalStateException("cannot produce certification request signature");
        }
    }

    private SubjectPublicKeyInfo publicKeyInfo;
    private X500Name subject;
    private List attributes;
    private boolean leaveOffEmpty;
}
