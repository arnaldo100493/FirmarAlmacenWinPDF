// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ProtectedPKIMessageBuilder.java

package co.org.bouncy.cert.cmp;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.cmp.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x509.GeneralName;
import co.org.bouncy.cert.X509CertificateHolder;
import co.org.bouncy.operator.ContentSigner;
import co.org.bouncy.operator.MacCalculator;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

// Referenced classes of package co.org.bouncy.cert.cmp:
//            CMPException, ProtectedPKIMessage

public class ProtectedPKIMessageBuilder
{

    public ProtectedPKIMessageBuilder(GeneralName sender, GeneralName recipient)
    {
        this(2, sender, recipient);
    }

    public ProtectedPKIMessageBuilder(int pvno, GeneralName sender, GeneralName recipient)
    {
        generalInfos = new ArrayList();
        extraCerts = new ArrayList();
        hdrBuilder = new PKIHeaderBuilder(pvno, sender, recipient);
    }

    public ProtectedPKIMessageBuilder setTransactionID(byte tid[])
    {
        hdrBuilder.setTransactionID(tid);
        return this;
    }

    public ProtectedPKIMessageBuilder setFreeText(PKIFreeText freeText)
    {
        hdrBuilder.setFreeText(freeText);
        return this;
    }

    public ProtectedPKIMessageBuilder addGeneralInfo(InfoTypeAndValue genInfo)
    {
        generalInfos.add(genInfo);
        return this;
    }

    public ProtectedPKIMessageBuilder setMessageTime(Date time)
    {
        hdrBuilder.setMessageTime(new ASN1GeneralizedTime(time));
        return this;
    }

    public ProtectedPKIMessageBuilder setRecipKID(byte kid[])
    {
        hdrBuilder.setRecipKID(kid);
        return this;
    }

    public ProtectedPKIMessageBuilder setRecipNonce(byte nonce[])
    {
        hdrBuilder.setRecipNonce(nonce);
        return this;
    }

    public ProtectedPKIMessageBuilder setSenderKID(byte kid[])
    {
        hdrBuilder.setSenderKID(kid);
        return this;
    }

    public ProtectedPKIMessageBuilder setSenderNonce(byte nonce[])
    {
        hdrBuilder.setSenderNonce(nonce);
        return this;
    }

    public ProtectedPKIMessageBuilder setBody(PKIBody body)
    {
        this.body = body;
        return this;
    }

    public ProtectedPKIMessageBuilder addCMPCertificate(X509CertificateHolder extraCert)
    {
        extraCerts.add(extraCert);
        return this;
    }

    public ProtectedPKIMessage build(MacCalculator macCalculator)
        throws CMPException
    {
        finaliseHeader(macCalculator.getAlgorithmIdentifier());
        PKIHeader header = hdrBuilder.build();
        try
        {
            DERBitString protection = new DERBitString(calculateMac(macCalculator, header, body));
            return finaliseMessage(header, protection);
        }
        catch(IOException e)
        {
            throw new CMPException((new StringBuilder()).append("unable to encode MAC input: ").append(e.getMessage()).toString(), e);
        }
    }

    public ProtectedPKIMessage build(ContentSigner signer)
        throws CMPException
    {
        finaliseHeader(signer.getAlgorithmIdentifier());
        PKIHeader header = hdrBuilder.build();
        try
        {
            DERBitString protection = new DERBitString(calculateSignature(signer, header, body));
            return finaliseMessage(header, protection);
        }
        catch(IOException e)
        {
            throw new CMPException((new StringBuilder()).append("unable to encode signature input: ").append(e.getMessage()).toString(), e);
        }
    }

    private void finaliseHeader(AlgorithmIdentifier algorithmIdentifier)
    {
        hdrBuilder.setProtectionAlg(algorithmIdentifier);
        if(!generalInfos.isEmpty())
        {
            InfoTypeAndValue genInfos[] = new InfoTypeAndValue[generalInfos.size()];
            hdrBuilder.setGeneralInfo((InfoTypeAndValue[])(InfoTypeAndValue[])generalInfos.toArray(genInfos));
        }
    }

    private ProtectedPKIMessage finaliseMessage(PKIHeader header, DERBitString protection)
    {
        if(!extraCerts.isEmpty())
        {
            CMPCertificate cmpCerts[] = new CMPCertificate[extraCerts.size()];
            for(int i = 0; i != cmpCerts.length; i++)
                cmpCerts[i] = new CMPCertificate(((X509CertificateHolder)extraCerts.get(i)).toASN1Structure());

            return new ProtectedPKIMessage(new PKIMessage(header, body, protection, cmpCerts));
        } else
        {
            return new ProtectedPKIMessage(new PKIMessage(header, body, protection));
        }
    }

    private byte[] calculateSignature(ContentSigner signer, PKIHeader header, PKIBody body)
        throws IOException
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(header);
        v.add(body);
        OutputStream sOut = signer.getOutputStream();
        sOut.write((new DERSequence(v)).getEncoded("DER"));
        sOut.close();
        return signer.getSignature();
    }

    private byte[] calculateMac(MacCalculator macCalculator, PKIHeader header, PKIBody body)
        throws IOException
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(header);
        v.add(body);
        OutputStream sOut = macCalculator.getOutputStream();
        sOut.write((new DERSequence(v)).getEncoded("DER"));
        sOut.close();
        return macCalculator.getMac();
    }

    private PKIHeaderBuilder hdrBuilder;
    private PKIBody body;
    private List generalInfos;
    private List extraCerts;
}
