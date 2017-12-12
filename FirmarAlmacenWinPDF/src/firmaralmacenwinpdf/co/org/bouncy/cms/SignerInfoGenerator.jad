// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SignerInfoGenerator.java

package co.org.bouncy.cms;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.cms.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.cert.X509CertificateHolder;
import co.org.bouncy.operator.*;
import co.org.bouncy.util.io.TeeOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

// Referenced classes of package co.org.bouncy.cms:
//            DefaultSignedAttributeTableGenerator, CMSException, CMSSignatureEncryptionAlgorithmFinder, CMSAttributeTableGenerator

public class SignerInfoGenerator
{

    SignerInfoGenerator(SignerIdentifier signerIdentifier, ContentSigner signer, DigestCalculatorProvider digesterProvider, CMSSignatureEncryptionAlgorithmFinder sigEncAlgFinder)
        throws OperatorCreationException
    {
        this(signerIdentifier, signer, digesterProvider, sigEncAlgFinder, false);
    }

    SignerInfoGenerator(SignerIdentifier signerIdentifier, ContentSigner signer, DigestCalculatorProvider digesterProvider, CMSSignatureEncryptionAlgorithmFinder sigEncAlgFinder, boolean isDirectSignature)
        throws OperatorCreationException
    {
        digAlgFinder = new DefaultDigestAlgorithmIdentifierFinder();
        calculatedDigest = null;
        this.signerIdentifier = signerIdentifier;
        this.signer = signer;
        if(digesterProvider != null)
            digester = digesterProvider.get(digAlgFinder.find(signer.getAlgorithmIdentifier()));
        else
            digester = null;
        if(isDirectSignature)
        {
            sAttrGen = null;
            unsAttrGen = null;
        } else
        {
            sAttrGen = new DefaultSignedAttributeTableGenerator();
            unsAttrGen = null;
        }
        this.sigEncAlgFinder = sigEncAlgFinder;
    }

    public SignerInfoGenerator(SignerInfoGenerator original, CMSAttributeTableGenerator sAttrGen, CMSAttributeTableGenerator unsAttrGen)
    {
        digAlgFinder = new DefaultDigestAlgorithmIdentifierFinder();
        calculatedDigest = null;
        signerIdentifier = original.signerIdentifier;
        signer = original.signer;
        digester = original.digester;
        sigEncAlgFinder = original.sigEncAlgFinder;
        this.sAttrGen = sAttrGen;
        this.unsAttrGen = unsAttrGen;
    }

    SignerInfoGenerator(SignerIdentifier signerIdentifier, ContentSigner signer, DigestCalculatorProvider digesterProvider, CMSSignatureEncryptionAlgorithmFinder sigEncAlgFinder, CMSAttributeTableGenerator sAttrGen, CMSAttributeTableGenerator unsAttrGen)
        throws OperatorCreationException
    {
        digAlgFinder = new DefaultDigestAlgorithmIdentifierFinder();
        calculatedDigest = null;
        this.signerIdentifier = signerIdentifier;
        this.signer = signer;
        if(digesterProvider != null)
            digester = digesterProvider.get(digAlgFinder.find(signer.getAlgorithmIdentifier()));
        else
            digester = null;
        this.sAttrGen = sAttrGen;
        this.unsAttrGen = unsAttrGen;
        this.sigEncAlgFinder = sigEncAlgFinder;
    }

    public SignerIdentifier getSID()
    {
        return signerIdentifier;
    }

    public ASN1Integer getGeneratedVersion()
    {
        return new ASN1Integer(signerIdentifier.isTagged() ? 3L : 1L);
    }

    public boolean hasAssociatedCertificate()
    {
        return certHolder != null;
    }

    public X509CertificateHolder getAssociatedCertificate()
    {
        return certHolder;
    }

    public AlgorithmIdentifier getDigestAlgorithm()
    {
        if(digester != null)
            return digester.getAlgorithmIdentifier();
        else
            return digAlgFinder.find(signer.getAlgorithmIdentifier());
    }

    public OutputStream getCalculatingOutputStream()
    {
        if(digester != null)
        {
            if(sAttrGen == null)
                return new TeeOutputStream(digester.getOutputStream(), signer.getOutputStream());
            else
                return digester.getOutputStream();
        } else
        {
            return signer.getOutputStream();
        }
    }

    public SignerInfo generate(ASN1ObjectIdentifier contentType)
        throws CMSException
    {
        try
        {
            ASN1Set signedAttr = null;
            AlgorithmIdentifier digestAlg = null;
            if(sAttrGen != null)
            {
                digestAlg = digester.getAlgorithmIdentifier();
                calculatedDigest = digester.getDigest();
                Map parameters = getBaseParameters(contentType, digester.getAlgorithmIdentifier(), calculatedDigest);
                AttributeTable signed = sAttrGen.getAttributes(Collections.unmodifiableMap(parameters));
                signedAttr = getAttributeSet(signed);
                OutputStream sOut = signer.getOutputStream();
                sOut.write(signedAttr.getEncoded("DER"));
                sOut.close();
            } else
            if(digester != null)
            {
                digestAlg = digester.getAlgorithmIdentifier();
                calculatedDigest = digester.getDigest();
            } else
            {
                digestAlg = digAlgFinder.find(signer.getAlgorithmIdentifier());
                calculatedDigest = null;
            }
            byte sigBytes[] = signer.getSignature();
            ASN1Set unsignedAttr = null;
            if(unsAttrGen != null)
            {
                Map parameters = getBaseParameters(contentType, digestAlg, calculatedDigest);
                parameters.put("encryptedDigest", sigBytes.clone());
                AttributeTable unsigned = unsAttrGen.getAttributes(Collections.unmodifiableMap(parameters));
                unsignedAttr = getAttributeSet(unsigned);
            }
            AlgorithmIdentifier digestEncryptionAlgorithm = sigEncAlgFinder.findEncryptionAlgorithm(signer.getAlgorithmIdentifier());
            return new SignerInfo(signerIdentifier, digestAlg, signedAttr, digestEncryptionAlgorithm, new DEROctetString(sigBytes), unsignedAttr);
        }
        catch(IOException e)
        {
            throw new CMSException("encoding error.", e);
        }
    }

    void setAssociatedCertificate(X509CertificateHolder certHolder)
    {
        this.certHolder = certHolder;
    }

    private ASN1Set getAttributeSet(AttributeTable attr)
    {
        if(attr != null)
            return new DERSet(attr.toASN1EncodableVector());
        else
            return null;
    }

    private Map getBaseParameters(ASN1ObjectIdentifier contentType, AlgorithmIdentifier digAlgId, byte hash[])
    {
        Map param = new HashMap();
        if(contentType != null)
            param.put("contentType", contentType);
        param.put("digestAlgID", digAlgId);
        param.put("digest", hash.clone());
        return param;
    }

    public byte[] getCalculatedDigest()
    {
        if(calculatedDigest != null)
            return (byte[])(byte[])calculatedDigest.clone();
        else
            return null;
    }

    public CMSAttributeTableGenerator getSignedAttributeTableGenerator()
    {
        return sAttrGen;
    }

    public CMSAttributeTableGenerator getUnsignedAttributeTableGenerator()
    {
        return unsAttrGen;
    }

    private final SignerIdentifier signerIdentifier;
    private final CMSAttributeTableGenerator sAttrGen;
    private final CMSAttributeTableGenerator unsAttrGen;
    private final ContentSigner signer;
    private final DigestCalculator digester;
    private final DigestAlgorithmIdentifierFinder digAlgFinder;
    private final CMSSignatureEncryptionAlgorithmFinder sigEncAlgFinder;
    private byte calculatedDigest[];
    private X509CertificateHolder certHolder;
}
