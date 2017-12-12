// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CertificateRequestMessageBuilder.java

package co.org.bouncy.cert.crmf;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.crmf.*;
import co.org.bouncy.asn1.x500.X500Name;
import co.org.bouncy.asn1.x509.*;
import co.org.bouncy.cert.CertIOException;
import co.org.bouncy.operator.ContentSigner;
import java.math.BigInteger;
import java.util.*;

// Referenced classes of package co.org.bouncy.cert.crmf:
//            Control, ProofOfPossessionSigningKeyBuilder, PKMACValueGenerator, CertificateRequestMessage, 
//            CRMFException, CRMFUtil, PKMACBuilder

public class CertificateRequestMessageBuilder
{

    public CertificateRequestMessageBuilder(BigInteger certReqId)
    {
        this.certReqId = certReqId;
        extGenerator = new ExtensionsGenerator();
        templateBuilder = new CertTemplateBuilder();
        controls = new ArrayList();
    }

    public CertificateRequestMessageBuilder setPublicKey(SubjectPublicKeyInfo publicKey)
    {
        if(publicKey != null)
            templateBuilder.setPublicKey(publicKey);
        return this;
    }

    public CertificateRequestMessageBuilder setIssuer(X500Name issuer)
    {
        if(issuer != null)
            templateBuilder.setIssuer(issuer);
        return this;
    }

    public CertificateRequestMessageBuilder setSubject(X500Name subject)
    {
        if(subject != null)
            templateBuilder.setSubject(subject);
        return this;
    }

    public CertificateRequestMessageBuilder setSerialNumber(BigInteger serialNumber)
    {
        if(serialNumber != null)
            templateBuilder.setSerialNumber(new ASN1Integer(serialNumber));
        return this;
    }

    public CertificateRequestMessageBuilder addExtension(ASN1ObjectIdentifier oid, boolean critical, ASN1Encodable value)
        throws CertIOException
    {
        CRMFUtil.addExtension(extGenerator, oid, critical, value);
        return this;
    }

    public CertificateRequestMessageBuilder addExtension(ASN1ObjectIdentifier oid, boolean critical, byte value[])
    {
        extGenerator.addExtension(oid, critical, value);
        return this;
    }

    public CertificateRequestMessageBuilder addControl(Control control)
    {
        controls.add(control);
        return this;
    }

    public CertificateRequestMessageBuilder setProofOfPossessionSigningKeySigner(ContentSigner popSigner)
    {
        if(popoPrivKey != null || popRaVerified != null)
        {
            throw new IllegalStateException("only one proof of possession allowed");
        } else
        {
            this.popSigner = popSigner;
            return this;
        }
    }

    public CertificateRequestMessageBuilder setProofOfPossessionSubsequentMessage(SubsequentMessage msg)
    {
        if(popSigner != null || popRaVerified != null)
        {
            throw new IllegalStateException("only one proof of possession allowed");
        } else
        {
            popoPrivKey = new POPOPrivKey(msg);
            return this;
        }
    }

    public CertificateRequestMessageBuilder setProofOfPossessionRaVerified()
    {
        if(popSigner != null || popoPrivKey != null)
        {
            throw new IllegalStateException("only one proof of possession allowed");
        } else
        {
            popRaVerified = DERNull.INSTANCE;
            return this;
        }
    }

    public CertificateRequestMessageBuilder setAuthInfoPKMAC(PKMACBuilder pkmacBuilder, char password[])
    {
        this.pkmacBuilder = pkmacBuilder;
        this.password = password;
        return this;
    }

    public CertificateRequestMessageBuilder setAuthInfoSender(X500Name sender)
    {
        return setAuthInfoSender(new GeneralName(sender));
    }

    public CertificateRequestMessageBuilder setAuthInfoSender(GeneralName sender)
    {
        this.sender = sender;
        return this;
    }

    public CertificateRequestMessage build()
        throws CRMFException
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(new ASN1Integer(certReqId));
        if(!extGenerator.isEmpty())
            templateBuilder.setExtensions(extGenerator.generate());
        v.add(templateBuilder.build());
        if(!controls.isEmpty())
        {
            ASN1EncodableVector controlV = new ASN1EncodableVector();
            Control control;
            for(Iterator it = controls.iterator(); it.hasNext(); controlV.add(new AttributeTypeAndValue(control.getType(), control.getValue())))
                control = (Control)it.next();

            v.add(new DERSequence(controlV));
        }
        CertRequest request = CertRequest.getInstance(new DERSequence(v));
        v = new ASN1EncodableVector();
        v.add(request);
        if(popSigner != null)
        {
            CertTemplate template = request.getCertTemplate();
            if(template.getSubject() == null || template.getPublicKey() == null)
            {
                SubjectPublicKeyInfo pubKeyInfo = request.getCertTemplate().getPublicKey();
                ProofOfPossessionSigningKeyBuilder builder = new ProofOfPossessionSigningKeyBuilder(pubKeyInfo);
                if(sender != null)
                {
                    builder.setSender(sender);
                } else
                {
                    PKMACValueGenerator pkmacGenerator = new PKMACValueGenerator(pkmacBuilder);
                    builder.setPublicKeyMac(pkmacGenerator, password);
                }
                v.add(new ProofOfPossession(builder.build(popSigner)));
            } else
            {
                ProofOfPossessionSigningKeyBuilder builder = new ProofOfPossessionSigningKeyBuilder(request);
                v.add(new ProofOfPossession(builder.build(popSigner)));
            }
        } else
        if(popoPrivKey != null)
            v.add(new ProofOfPossession(2, popoPrivKey));
        else
        if(popRaVerified != null)
            v.add(new ProofOfPossession());
        return new CertificateRequestMessage(CertReqMsg.getInstance(new DERSequence(v)));
    }

    private final BigInteger certReqId;
    private ExtensionsGenerator extGenerator;
    private CertTemplateBuilder templateBuilder;
    private List controls;
    private ContentSigner popSigner;
    private PKMACBuilder pkmacBuilder;
    private char password[];
    private GeneralName sender;
    private POPOPrivKey popoPrivKey;
    private ASN1Null popRaVerified;
}
