// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMSSignedDataGenerator.java

package co.org.bouncy.cms;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.cms.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.cms.jcajce.JcaSignerInfoGeneratorBuilder;
import co.org.bouncy.operator.ContentSigner;
import co.org.bouncy.operator.OperatorCreationException;
import co.org.bouncy.operator.bc.BcDigestCalculatorProvider;
import co.org.bouncy.operator.jcajce.JcaContentSignerBuilder;
import java.io.*;
import java.security.*;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.*;

// Referenced classes of package co.org.bouncy.cms:
//            CMSSignedGenerator, DefaultSignedAttributeTableGenerator, SimpleAttributeTableGenerator, CMSException, 
//            CMSAbsentContent, CMSTypedData, SignerInformation, SignerInfoGenerator, 
//            CMSSignedData, CMSProcessableByteArray, CMSProcessable, CMSUtils, 
//            CMSSignedHelper, CMSAttributeTableGenerator, SignerInformationStore

public class CMSSignedDataGenerator extends CMSSignedGenerator
{
    private class SignerInf
    {

        SignerInfoGenerator toSignerInfoGenerator(SecureRandom random, Provider sigProvider, boolean addDefaultAttributes)
            throws IOException, CertificateEncodingException, CMSException, OperatorCreationException, NoSuchAlgorithmException
        {
            String digestName = CMSSignedHelper.INSTANCE.getDigestAlgName(digestOID);
            String signatureName = (new StringBuilder()).append(digestName).append("with").append(CMSSignedHelper.INSTANCE.getEncryptionAlgName(encOID)).toString();
            JcaSignerInfoGeneratorBuilder builder = new JcaSignerInfoGeneratorBuilder(new BcDigestCalculatorProvider());
            if(addDefaultAttributes)
                builder.setSignedAttributeGenerator(sAttr);
            builder.setDirectSignature(!addDefaultAttributes);
            builder.setUnsignedAttributeGenerator(unsAttr);
            JcaContentSignerBuilder signerBuilder;
            try
            {
                signerBuilder = (new JcaContentSignerBuilder(signatureName)).setSecureRandom(random);
            }
            catch(IllegalArgumentException e)
            {
                throw new NoSuchAlgorithmException(e.getMessage());
            }
            if(sigProvider != null)
                signerBuilder.setProvider(sigProvider);
            ContentSigner contentSigner = signerBuilder.build(key);
            if(signerIdentifier instanceof X509Certificate)
                return builder.build(contentSigner, (X509Certificate)signerIdentifier);
            else
                return builder.build(contentSigner, (byte[])(byte[])signerIdentifier);
        }

        final PrivateKey key;
        final Object signerIdentifier;
        final String digestOID;
        final String encOID;
        final CMSAttributeTableGenerator sAttr;
        final CMSAttributeTableGenerator unsAttr;
        final AttributeTable baseSignedTable;
        final CMSSignedDataGenerator this$0;

        SignerInf(PrivateKey key, Object signerIdentifier, String digestOID, String encOID, CMSAttributeTableGenerator sAttr, CMSAttributeTableGenerator unsAttr, 
                AttributeTable baseSignedTable)
        {
            this$0 = CMSSignedDataGenerator.this;
            super();
            this.key = key;
            this.signerIdentifier = signerIdentifier;
            this.digestOID = digestOID;
            this.encOID = encOID;
            this.sAttr = sAttr;
            this.unsAttr = unsAttr;
            this.baseSignedTable = baseSignedTable;
        }
    }


    public CMSSignedDataGenerator()
    {
        signerInfs = new ArrayList();
    }

    /**
     * @deprecated Method CMSSignedDataGenerator is deprecated
     */

    public CMSSignedDataGenerator(SecureRandom rand)
    {
        super(rand);
        signerInfs = new ArrayList();
    }

    /**
     * @deprecated Method addSigner is deprecated
     */

    public void addSigner(PrivateKey key, X509Certificate cert, String digestOID)
        throws IllegalArgumentException
    {
        addSigner(key, cert, getEncOID(key, digestOID), digestOID);
    }

    /**
     * @deprecated Method addSigner is deprecated
     */

    public void addSigner(PrivateKey key, X509Certificate cert, String encryptionOID, String digestOID)
        throws IllegalArgumentException
    {
        doAddSigner(key, cert, encryptionOID, digestOID, new DefaultSignedAttributeTableGenerator(), null, null);
    }

    /**
     * @deprecated Method addSigner is deprecated
     */

    public void addSigner(PrivateKey key, byte subjectKeyID[], String digestOID)
        throws IllegalArgumentException
    {
        addSigner(key, subjectKeyID, getEncOID(key, digestOID), digestOID);
    }

    /**
     * @deprecated Method addSigner is deprecated
     */

    public void addSigner(PrivateKey key, byte subjectKeyID[], String encryptionOID, String digestOID)
        throws IllegalArgumentException
    {
        doAddSigner(key, subjectKeyID, encryptionOID, digestOID, new DefaultSignedAttributeTableGenerator(), null, null);
    }

    /**
     * @deprecated Method addSigner is deprecated
     */

    public void addSigner(PrivateKey key, X509Certificate cert, String digestOID, AttributeTable signedAttr, AttributeTable unsignedAttr)
        throws IllegalArgumentException
    {
        addSigner(key, cert, getEncOID(key, digestOID), digestOID, signedAttr, unsignedAttr);
    }

    /**
     * @deprecated Method addSigner is deprecated
     */

    public void addSigner(PrivateKey key, X509Certificate cert, String encryptionOID, String digestOID, AttributeTable signedAttr, AttributeTable unsignedAttr)
        throws IllegalArgumentException
    {
        doAddSigner(key, cert, encryptionOID, digestOID, new DefaultSignedAttributeTableGenerator(signedAttr), new SimpleAttributeTableGenerator(unsignedAttr), signedAttr);
    }

    /**
     * @deprecated Method addSigner is deprecated
     */

    public void addSigner(PrivateKey key, byte subjectKeyID[], String digestOID, AttributeTable signedAttr, AttributeTable unsignedAttr)
        throws IllegalArgumentException
    {
        addSigner(key, subjectKeyID, getEncOID(key, digestOID), digestOID, signedAttr, unsignedAttr);
    }

    /**
     * @deprecated Method addSigner is deprecated
     */

    public void addSigner(PrivateKey key, byte subjectKeyID[], String encryptionOID, String digestOID, AttributeTable signedAttr, AttributeTable unsignedAttr)
        throws IllegalArgumentException
    {
        doAddSigner(key, subjectKeyID, encryptionOID, digestOID, new DefaultSignedAttributeTableGenerator(signedAttr), new SimpleAttributeTableGenerator(unsignedAttr), signedAttr);
    }

    /**
     * @deprecated Method addSigner is deprecated
     */

    public void addSigner(PrivateKey key, X509Certificate cert, String digestOID, CMSAttributeTableGenerator signedAttrGen, CMSAttributeTableGenerator unsignedAttrGen)
        throws IllegalArgumentException
    {
        addSigner(key, cert, getEncOID(key, digestOID), digestOID, signedAttrGen, unsignedAttrGen);
    }

    /**
     * @deprecated Method addSigner is deprecated
     */

    public void addSigner(PrivateKey key, X509Certificate cert, String encryptionOID, String digestOID, CMSAttributeTableGenerator signedAttrGen, CMSAttributeTableGenerator unsignedAttrGen)
        throws IllegalArgumentException
    {
        doAddSigner(key, cert, encryptionOID, digestOID, signedAttrGen, unsignedAttrGen, null);
    }

    /**
     * @deprecated Method addSigner is deprecated
     */

    public void addSigner(PrivateKey key, byte subjectKeyID[], String digestOID, CMSAttributeTableGenerator signedAttrGen, CMSAttributeTableGenerator unsignedAttrGen)
        throws IllegalArgumentException
    {
        addSigner(key, subjectKeyID, getEncOID(key, digestOID), digestOID, signedAttrGen, unsignedAttrGen);
    }

    /**
     * @deprecated Method addSigner is deprecated
     */

    public void addSigner(PrivateKey key, byte subjectKeyID[], String encryptionOID, String digestOID, CMSAttributeTableGenerator signedAttrGen, CMSAttributeTableGenerator unsignedAttrGen)
        throws IllegalArgumentException
    {
        doAddSigner(key, subjectKeyID, encryptionOID, digestOID, signedAttrGen, unsignedAttrGen, null);
    }

    private void doAddSigner(PrivateKey key, Object signerIdentifier, String encryptionOID, String digestOID, CMSAttributeTableGenerator signedAttrGen, CMSAttributeTableGenerator unsignedAttrGen, AttributeTable baseSignedTable)
        throws IllegalArgumentException
    {
        signerInfs.add(new SignerInf(key, signerIdentifier, digestOID, encryptionOID, signedAttrGen, unsignedAttrGen, baseSignedTable));
    }

    /**
     * @deprecated Method generate is deprecated
     */

    public CMSSignedData generate(CMSProcessable content, String sigProvider)
        throws NoSuchAlgorithmException, NoSuchProviderException, CMSException
    {
        return generate(content, CMSUtils.getProvider(sigProvider));
    }

    /**
     * @deprecated Method generate is deprecated
     */

    public CMSSignedData generate(CMSProcessable content, Provider sigProvider)
        throws NoSuchAlgorithmException, CMSException
    {
        return generate(content, false, sigProvider);
    }

    /**
     * @deprecated Method generate is deprecated
     */

    public CMSSignedData generate(String eContentType, CMSProcessable content, boolean encapsulate, String sigProvider)
        throws NoSuchAlgorithmException, NoSuchProviderException, CMSException
    {
        return generate(eContentType, content, encapsulate, CMSUtils.getProvider(sigProvider), true);
    }

    /**
     * @deprecated Method generate is deprecated
     */

    public CMSSignedData generate(String eContentType, CMSProcessable content, boolean encapsulate, Provider sigProvider)
        throws NoSuchAlgorithmException, CMSException
    {
        return generate(eContentType, content, encapsulate, sigProvider, true);
    }

    /**
     * @deprecated Method generate is deprecated
     */

    public CMSSignedData generate(String eContentType, CMSProcessable content, boolean encapsulate, String sigProvider, boolean addDefaultAttributes)
        throws NoSuchAlgorithmException, NoSuchProviderException, CMSException
    {
        return generate(eContentType, content, encapsulate, CMSUtils.getProvider(sigProvider), addDefaultAttributes);
    }

    /**
     * @deprecated Method generate is deprecated
     */

    public CMSSignedData generate(String eContentType, final CMSProcessable content, boolean encapsulate, Provider sigProvider, boolean addDefaultAttributes)
        throws NoSuchAlgorithmException, CMSException
    {
        boolean isCounterSignature = eContentType == null;
        final ASN1ObjectIdentifier contentTypeOID = isCounterSignature ? null : new ASN1ObjectIdentifier(eContentType);
        for(Iterator it = signerInfs.iterator(); it.hasNext();)
        {
            SignerInf signer = (SignerInf)it.next();
            try
            {
                signerGens.add(signer.toSignerInfoGenerator(rand, sigProvider, addDefaultAttributes));
            }
            catch(OperatorCreationException e)
            {
                throw new CMSException("exception creating signerInf", e);
            }
            catch(IOException e)
            {
                throw new CMSException("exception encoding attributes", e);
            }
            catch(CertificateEncodingException e)
            {
                throw new CMSException("error creating sid.", e);
            }
        }

        signerInfs.clear();
        if(content != null)
            return generate(new CMSTypedData() {

                public ASN1ObjectIdentifier getContentType()
                {
                    return contentTypeOID;
                }

                public void write(OutputStream out)
                    throws IOException, CMSException
                {
                    content.write(out);
                }

                public Object getContent()
                {
                    return content.getContent();
                }

                final ASN1ObjectIdentifier val$contentTypeOID;
                final CMSProcessable val$content;
                final CMSSignedDataGenerator this$0;

            
            {
                this$0 = CMSSignedDataGenerator.this;
                contentTypeOID = asn1objectidentifier;
                content = cmsprocessable;
                super();
            }
            }
, encapsulate);
        else
            return generate(((CMSTypedData) (new CMSAbsentContent(contentTypeOID))), encapsulate);
    }

    /**
     * @deprecated Method generate is deprecated
     */

    public CMSSignedData generate(CMSProcessable content, boolean encapsulate, String sigProvider)
        throws NoSuchAlgorithmException, NoSuchProviderException, CMSException
    {
        if(content instanceof CMSTypedData)
            return generate(((CMSTypedData)content).getContentType().getId(), content, encapsulate, sigProvider);
        else
            return generate(DATA, content, encapsulate, sigProvider);
    }

    /**
     * @deprecated Method generate is deprecated
     */

    public CMSSignedData generate(CMSProcessable content, boolean encapsulate, Provider sigProvider)
        throws NoSuchAlgorithmException, CMSException
    {
        if(content instanceof CMSTypedData)
            return generate(((CMSTypedData)content).getContentType().getId(), content, encapsulate, sigProvider);
        else
            return generate(DATA, content, encapsulate, sigProvider);
    }

    public CMSSignedData generate(CMSTypedData content)
        throws CMSException
    {
        return generate(content, false);
    }

    public CMSSignedData generate(CMSTypedData content, boolean encapsulate)
        throws CMSException
    {
        if(!signerInfs.isEmpty())
            throw new IllegalStateException("this method can only be used with SignerInfoGenerator");
        ASN1EncodableVector digestAlgs = new ASN1EncodableVector();
        ASN1EncodableVector signerInfos = new ASN1EncodableVector();
        digests.clear();
        SignerInformation signer;
        for(Iterator it = _signers.iterator(); it.hasNext(); signerInfos.add(signer.toASN1Structure()))
        {
            signer = (SignerInformation)it.next();
            digestAlgs.add(CMSSignedHelper.INSTANCE.fixAlgID(signer.getDigestAlgorithmID()));
        }

        ASN1ObjectIdentifier contentTypeOID = content.getContentType();
        ASN1OctetString octs = null;
        if(content != null)
        {
            ByteArrayOutputStream bOut = null;
            if(encapsulate)
                bOut = new ByteArrayOutputStream();
            OutputStream cOut = CMSUtils.attachSignersToOutputStream(signerGens, bOut);
            cOut = CMSUtils.getSafeOutputStream(cOut);
            try
            {
                content.write(cOut);
                cOut.close();
            }
            catch(IOException e)
            {
                throw new CMSException((new StringBuilder()).append("data processing exception: ").append(e.getMessage()).toString(), e);
            }
            if(encapsulate)
                octs = new BEROctetString(bOut.toByteArray());
        }
        Iterator it = signerGens.iterator();
        do
        {
            if(!it.hasNext())
                break;
            SignerInfoGenerator sGen = (SignerInfoGenerator)it.next();
            SignerInfo inf = sGen.generate(contentTypeOID);
            digestAlgs.add(inf.getDigestAlgorithm());
            signerInfos.add(inf);
            byte calcDigest[] = sGen.getCalculatedDigest();
            if(calcDigest != null)
                digests.put(inf.getDigestAlgorithm().getAlgorithm().getId(), calcDigest);
        } while(true);
        ASN1Set certificates = null;
        if(certs.size() != 0)
            certificates = CMSUtils.createBerSetFromList(certs);
        ASN1Set certrevlist = null;
        if(crls.size() != 0)
            certrevlist = CMSUtils.createBerSetFromList(crls);
        ContentInfo encInfo = new ContentInfo(contentTypeOID, octs);
        SignedData sd = new SignedData(new DERSet(digestAlgs), encInfo, certificates, certrevlist, new DERSet(signerInfos));
        ContentInfo contentInfo = new ContentInfo(CMSObjectIdentifiers.signedData, sd);
        return new CMSSignedData(content, contentInfo);
    }

    /**
     * @deprecated Method generateCounterSigners is deprecated
     */

    public SignerInformationStore generateCounterSigners(SignerInformation signer, Provider sigProvider)
        throws NoSuchAlgorithmException, CMSException
    {
        return generate(null, new CMSProcessableByteArray(signer.getSignature()), false, sigProvider).getSignerInfos();
    }

    /**
     * @deprecated Method generateCounterSigners is deprecated
     */

    public SignerInformationStore generateCounterSigners(SignerInformation signer, String sigProvider)
        throws NoSuchAlgorithmException, NoSuchProviderException, CMSException
    {
        return generate(null, new CMSProcessableByteArray(signer.getSignature()), false, CMSUtils.getProvider(sigProvider)).getSignerInfos();
    }

    public SignerInformationStore generateCounterSigners(SignerInformation signer)
        throws CMSException
    {
        return generate(new CMSProcessableByteArray(null, signer.getSignature()), false).getSignerInfos();
    }

    private List signerInfs;
}
