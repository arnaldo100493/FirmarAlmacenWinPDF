// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SignerInformation.java

package co.org.bouncy.cms;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.cms.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x509.DigestInfo;
import co.org.bouncy.cert.X509CertificateHolder;
import co.org.bouncy.cms.jcajce.JcaSignerInfoVerifierBuilder;
import co.org.bouncy.cms.jcajce.JcaSimpleSignerInfoVerifierBuilder;
import co.org.bouncy.operator.*;
import co.org.bouncy.operator.jcajce.JcaDigestCalculatorProviderBuilder;
import co.org.bouncy.util.Arrays;
import co.org.bouncy.util.io.TeeOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.*;
import java.util.*;

// Referenced classes of package co.org.bouncy.cms:
//            SignerId, SignerInformationStore, CMSProcessableByteArray, CMSException, 
//            CMSSignerDigestMismatchException, CMSVerifierCertificateNotValidException, CMSProcessable, SignerInformationVerifier, 
//            CMSSignedHelper, CMSUtils

public class SignerInformation
{

    SignerInformation(SignerInfo info, ASN1ObjectIdentifier contentType, CMSProcessable content, byte resultDigest[])
    {
        this.info = info;
        this.contentType = contentType;
        isCounterSignature = contentType == null;
        SignerIdentifier s = info.getSID();
        if(s.isTagged())
        {
            ASN1OctetString octs = ASN1OctetString.getInstance(s.getId());
            sid = new SignerId(octs.getOctets());
        } else
        {
            IssuerAndSerialNumber iAnds = IssuerAndSerialNumber.getInstance(s.getId());
            sid = new SignerId(iAnds.getName(), iAnds.getSerialNumber().getValue());
        }
        digestAlgorithm = info.getDigestAlgorithm();
        signedAttributeSet = info.getAuthenticatedAttributes();
        unsignedAttributeSet = info.getUnauthenticatedAttributes();
        encryptionAlgorithm = info.getDigestEncryptionAlgorithm();
        signature = info.getEncryptedDigest().getOctets();
        this.content = content;
        this.resultDigest = resultDigest;
    }

    public boolean isCounterSignature()
    {
        return isCounterSignature;
    }

    public ASN1ObjectIdentifier getContentType()
    {
        return contentType;
    }

    private byte[] encodeObj(ASN1Encodable obj)
        throws IOException
    {
        if(obj != null)
            return obj.toASN1Primitive().getEncoded();
        else
            return null;
    }

    public SignerId getSID()
    {
        return sid;
    }

    public int getVersion()
    {
        return info.getVersion().getValue().intValue();
    }

    public AlgorithmIdentifier getDigestAlgorithmID()
    {
        return digestAlgorithm;
    }

    public String getDigestAlgOID()
    {
        return digestAlgorithm.getAlgorithm().getId();
    }

    public byte[] getDigestAlgParams()
    {
        try
        {
            return encodeObj(digestAlgorithm.getParameters());
        }
        catch(Exception e)
        {
            throw new RuntimeException((new StringBuilder()).append("exception getting digest parameters ").append(e).toString());
        }
    }

    public byte[] getContentDigest()
    {
        if(resultDigest == null)
            throw new IllegalStateException("method can only be called after verify.");
        else
            return (byte[])(byte[])resultDigest.clone();
    }

    public String getEncryptionAlgOID()
    {
        return encryptionAlgorithm.getAlgorithm().getId();
    }

    public byte[] getEncryptionAlgParams()
    {
        try
        {
            return encodeObj(encryptionAlgorithm.getParameters());
        }
        catch(Exception e)
        {
            throw new RuntimeException((new StringBuilder()).append("exception getting encryption parameters ").append(e).toString());
        }
    }

    public AttributeTable getSignedAttributes()
    {
        if(signedAttributeSet != null && signedAttributeValues == null)
            signedAttributeValues = new AttributeTable(signedAttributeSet);
        return signedAttributeValues;
    }

    public AttributeTable getUnsignedAttributes()
    {
        if(unsignedAttributeSet != null && unsignedAttributeValues == null)
            unsignedAttributeValues = new AttributeTable(unsignedAttributeSet);
        return unsignedAttributeValues;
    }

    public byte[] getSignature()
    {
        return (byte[])(byte[])signature.clone();
    }

    public SignerInformationStore getCounterSignatures()
    {
        AttributeTable unsignedAttributeTable = getUnsignedAttributes();
        if(unsignedAttributeTable == null)
            return new SignerInformationStore(new ArrayList(0));
        List counterSignatures = new ArrayList();
        ASN1EncodableVector allCSAttrs = unsignedAttributeTable.getAll(CMSAttributes.counterSignature);
        for(int i = 0; i < allCSAttrs.size(); i++)
        {
            Attribute counterSignatureAttribute = (Attribute)allCSAttrs.get(i);
            ASN1Set values = counterSignatureAttribute.getAttrValues();
            if(values.size() >= 1);
            SignerInfo si;
            for(Enumeration en = values.getObjects(); en.hasMoreElements(); counterSignatures.add(new SignerInformation(si, null, new CMSProcessableByteArray(getSignature()), null)))
                si = SignerInfo.getInstance(en.nextElement());

        }

        return new SignerInformationStore(counterSignatures);
    }

    public byte[] getEncodedSignedAttributes()
        throws IOException
    {
        if(signedAttributeSet != null)
            return signedAttributeSet.getEncoded();
        else
            return null;
    }

    /**
     * @deprecated Method doVerify is deprecated
     */

    private boolean doVerify(PublicKey key, Provider sigProvider)
        throws CMSException, NoSuchAlgorithmException
    {
        try
        {
            SignerInformationVerifier verifier;
            if(sigProvider != null)
            {
                if(!sigProvider.getName().equalsIgnoreCase("BC"))
                    verifier = (new JcaSignerInfoVerifierBuilder((new JcaDigestCalculatorProviderBuilder()).build())).setProvider(sigProvider).build(key);
                else
                    verifier = (new JcaSimpleSignerInfoVerifierBuilder()).setProvider(sigProvider).build(key);
            } else
            {
                verifier = (new JcaSimpleSignerInfoVerifierBuilder()).build(key);
            }
            return doVerify(verifier);
        }
        catch(OperatorCreationException e)
        {
            throw new CMSException((new StringBuilder()).append("unable to create verifier: ").append(e.getMessage()).toString(), e);
        }
    }

    private boolean doVerify(SignerInformationVerifier verifier)
        throws CMSException
    {
        String encName;
        ContentVerifier contentVerifier;
        encName = CMSSignedHelper.INSTANCE.getEncryptionAlgName(getEncryptionAlgOID());
        try
        {
            contentVerifier = verifier.getContentVerifier(encryptionAlgorithm, info.getDigestAlgorithm());
        }
        catch(OperatorCreationException e)
        {
            throw new CMSException((new StringBuilder()).append("can't create content verifier: ").append(e.getMessage()).toString(), e);
        }
        try
        {
            OutputStream sigOut = contentVerifier.getOutputStream();
            if(resultDigest == null)
            {
                DigestCalculator calc = verifier.getDigestCalculator(getDigestAlgorithmID());
                if(content != null)
                {
                    OutputStream digOut = calc.getOutputStream();
                    if(signedAttributeSet == null)
                    {
                        if(contentVerifier instanceof RawContentVerifier)
                        {
                            content.write(digOut);
                        } else
                        {
                            OutputStream cOut = new TeeOutputStream(digOut, sigOut);
                            content.write(cOut);
                            cOut.close();
                        }
                    } else
                    {
                        content.write(digOut);
                        sigOut.write(getEncodedSignedAttributes());
                    }
                    digOut.close();
                } else
                if(signedAttributeSet != null)
                    sigOut.write(getEncodedSignedAttributes());
                else
                    throw new CMSException("data not encapsulated in signature - use detached constructor.");
                resultDigest = calc.getDigest();
            } else
            if(signedAttributeSet == null)
            {
                if(content != null)
                    content.write(sigOut);
            } else
            {
                sigOut.write(getEncodedSignedAttributes());
            }
            sigOut.close();
        }
        catch(IOException e)
        {
            throw new CMSException("can't process mime object to create signature.", e);
        }
        catch(OperatorCreationException e)
        {
            throw new CMSException((new StringBuilder()).append("can't create digest calculator: ").append(e.getMessage()).toString(), e);
        }
        ASN1Primitive validContentType = getSingleValuedSignedAttribute(CMSAttributes.contentType, "content-type");
        if(validContentType == null)
        {
            if(!isCounterSignature && signedAttributeSet != null)
                throw new CMSException("The content-type attribute type MUST be present whenever signed attributes are present in signed-data");
        } else
        {
            if(isCounterSignature)
                throw new CMSException("[For counter signatures,] the signedAttributes field MUST NOT contain a content-type attribute");
            if(!(validContentType instanceof ASN1ObjectIdentifier))
                throw new CMSException("content-type attribute value not of ASN.1 type 'OBJECT IDENTIFIER'");
            ASN1ObjectIdentifier signedContentType = (ASN1ObjectIdentifier)validContentType;
            if(!signedContentType.equals(contentType))
                throw new CMSException("content-type attribute value does not match eContentType");
        }
        ASN1Primitive validMessageDigest = getSingleValuedSignedAttribute(CMSAttributes.messageDigest, "message-digest");
        if(validMessageDigest == null)
        {
            if(signedAttributeSet != null)
                throw new CMSException("the message-digest signed attribute type MUST be present when there are any signed attributes present");
        } else
        {
            if(!(validMessageDigest instanceof ASN1OctetString))
                throw new CMSException("message-digest attribute value not of ASN.1 type 'OCTET STRING'");
            ASN1OctetString signedMessageDigest = (ASN1OctetString)validMessageDigest;
            if(!Arrays.constantTimeAreEqual(resultDigest, signedMessageDigest.getOctets()))
                throw new CMSSignerDigestMismatchException("message-digest attribute value does not match calculated value");
        }
        AttributeTable signedAttrTable = getSignedAttributes();
        if(signedAttrTable != null && signedAttrTable.getAll(CMSAttributes.counterSignature).size() > 0)
            throw new CMSException("A countersignature attribute MUST NOT be a signed attribute");
        AttributeTable unsignedAttrTable = getUnsignedAttributes();
        if(unsignedAttrTable != null)
        {
            ASN1EncodableVector csAttrs = unsignedAttrTable.getAll(CMSAttributes.counterSignature);
            for(int i = 0; i < csAttrs.size(); i++)
            {
                Attribute csAttr = (Attribute)csAttrs.get(i);
                if(csAttr.getAttrValues().size() < 1)
                    throw new CMSException("A countersignature attribute MUST contain at least one AttributeValue");
            }

        }
        RawContentVerifier rawVerifier;
        if(signedAttributeSet != null || resultDigest == null || !(contentVerifier instanceof RawContentVerifier))
            break MISSING_BLOCK_LABEL_728;
        rawVerifier = (RawContentVerifier)contentVerifier;
        if(encName.equals("RSA"))
        {
            DigestInfo digInfo = new DigestInfo(new AlgorithmIdentifier(digestAlgorithm.getAlgorithm(), DERNull.INSTANCE), resultDigest);
            return rawVerifier.verify(digInfo.getEncoded("DER"), getSignature());
        }
        try
        {
            return rawVerifier.verify(resultDigest, getSignature());
        }
        catch(IOException e)
        {
            throw new CMSException("can't process mime object to create signature.", e);
        }
        return contentVerifier.verify(getSignature());
    }

    /**
     * @deprecated Method verify is deprecated
     */

    public boolean verify(PublicKey key, String sigProvider)
        throws NoSuchAlgorithmException, NoSuchProviderException, CMSException
    {
        return verify(key, CMSUtils.getProvider(sigProvider));
    }

    /**
     * @deprecated Method verify is deprecated
     */

    public boolean verify(PublicKey key, Provider sigProvider)
        throws NoSuchAlgorithmException, NoSuchProviderException, CMSException
    {
        getSigningTime();
        return doVerify(key, sigProvider);
    }

    /**
     * @deprecated Method verify is deprecated
     */

    public boolean verify(X509Certificate cert, String sigProvider)
        throws NoSuchAlgorithmException, NoSuchProviderException, CertificateExpiredException, CertificateNotYetValidException, CMSException
    {
        return verify(cert, CMSUtils.getProvider(sigProvider));
    }

    /**
     * @deprecated Method verify is deprecated
     */

    public boolean verify(X509Certificate cert, Provider sigProvider)
        throws NoSuchAlgorithmException, CertificateExpiredException, CertificateNotYetValidException, CMSException
    {
        Time signingTime = getSigningTime();
        if(signingTime != null)
            cert.checkValidity(signingTime.getDate());
        return doVerify(cert.getPublicKey(), sigProvider);
    }

    public boolean verify(SignerInformationVerifier verifier)
        throws CMSException
    {
        Time signingTime = getSigningTime();
        if(verifier.hasAssociatedCertificate() && signingTime != null)
        {
            X509CertificateHolder dcv = verifier.getAssociatedCertificate();
            if(!dcv.isValidOn(signingTime.getDate()))
                throw new CMSVerifierCertificateNotValidException("verifier not valid at signingTime");
        }
        return doVerify(verifier);
    }

    /**
     * @deprecated Method toSignerInfo is deprecated
     */

    public SignerInfo toSignerInfo()
    {
        return info;
    }

    public SignerInfo toASN1Structure()
    {
        return info;
    }

    private ASN1Primitive getSingleValuedSignedAttribute(ASN1ObjectIdentifier attrOID, String printableName)
        throws CMSException
    {
        AttributeTable unsignedAttrTable = getUnsignedAttributes();
        if(unsignedAttrTable != null && unsignedAttrTable.getAll(attrOID).size() > 0)
            throw new CMSException((new StringBuilder()).append("The ").append(printableName).append(" attribute MUST NOT be an unsigned attribute").toString());
        AttributeTable signedAttrTable = getSignedAttributes();
        if(signedAttrTable == null)
            return null;
        ASN1EncodableVector v = signedAttrTable.getAll(attrOID);
        switch(v.size())
        {
        case 0: // '\0'
            return null;

        case 1: // '\001'
            Attribute t = (Attribute)v.get(0);
            ASN1Set attrValues = t.getAttrValues();
            if(attrValues.size() != 1)
                throw new CMSException((new StringBuilder()).append("A ").append(printableName).append(" attribute MUST have a single attribute value").toString());
            else
                return attrValues.getObjectAt(0).toASN1Primitive();
        }
        throw new CMSException((new StringBuilder()).append("The SignedAttributes in a signerInfo MUST NOT include multiple instances of the ").append(printableName).append(" attribute").toString());
    }

    private Time getSigningTime()
        throws CMSException
    {
        ASN1Primitive validSigningTime = getSingleValuedSignedAttribute(CMSAttributes.signingTime, "signing-time");
        if(validSigningTime == null)
            return null;
        try
        {
            return Time.getInstance(validSigningTime);
        }
        catch(IllegalArgumentException e)
        {
            throw new CMSException("signing-time attribute value not a valid 'Time' structure");
        }
    }

    public static SignerInformation replaceUnsignedAttributes(SignerInformation signerInformation, AttributeTable unsignedAttributes)
    {
        SignerInfo sInfo = signerInformation.info;
        ASN1Set unsignedAttr = null;
        if(unsignedAttributes != null)
            unsignedAttr = new DERSet(unsignedAttributes.toASN1EncodableVector());
        return new SignerInformation(new SignerInfo(sInfo.getSID(), sInfo.getDigestAlgorithm(), sInfo.getAuthenticatedAttributes(), sInfo.getDigestEncryptionAlgorithm(), sInfo.getEncryptedDigest(), unsignedAttr), signerInformation.contentType, signerInformation.content, null);
    }

    public static SignerInformation addCounterSigners(SignerInformation signerInformation, SignerInformationStore counterSigners)
    {
        SignerInfo sInfo = signerInformation.info;
        AttributeTable unsignedAttr = signerInformation.getUnsignedAttributes();
        ASN1EncodableVector v;
        if(unsignedAttr != null)
            v = unsignedAttr.toASN1EncodableVector();
        else
            v = new ASN1EncodableVector();
        ASN1EncodableVector sigs = new ASN1EncodableVector();
        for(Iterator it = counterSigners.getSigners().iterator(); it.hasNext(); sigs.add(((SignerInformation)it.next()).toASN1Structure()));
        v.add(new Attribute(CMSAttributes.counterSignature, new DERSet(sigs)));
        return new SignerInformation(new SignerInfo(sInfo.getSID(), sInfo.getDigestAlgorithm(), sInfo.getAuthenticatedAttributes(), sInfo.getDigestEncryptionAlgorithm(), sInfo.getEncryptedDigest(), new DERSet(v)), signerInformation.contentType, signerInformation.content, null);
    }

    private SignerId sid;
    private SignerInfo info;
    private AlgorithmIdentifier digestAlgorithm;
    private AlgorithmIdentifier encryptionAlgorithm;
    private final ASN1Set signedAttributeSet;
    private final ASN1Set unsignedAttributeSet;
    private CMSProcessable content;
    private byte signature[];
    private ASN1ObjectIdentifier contentType;
    private byte resultDigest[];
    private AttributeTable signedAttributeValues;
    private AttributeTable unsignedAttributeValues;
    private boolean isCounterSignature;
}
