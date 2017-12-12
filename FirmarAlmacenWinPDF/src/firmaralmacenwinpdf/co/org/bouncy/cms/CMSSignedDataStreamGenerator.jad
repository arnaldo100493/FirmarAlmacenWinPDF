// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMSSignedDataStreamGenerator.java

package co.org.bouncy.cms;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.cms.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.cms.jcajce.JcaSignerInfoGeneratorBuilder;
import co.org.bouncy.operator.ContentSigner;
import co.org.bouncy.operator.OperatorCreationException;
import co.org.bouncy.operator.jcajce.JcaContentSignerBuilder;
import co.org.bouncy.operator.jcajce.JcaDigestCalculatorProviderBuilder;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.*;

// Referenced classes of package co.org.bouncy.cms:
//            CMSSignedGenerator, DefaultSignedAttributeTableGenerator, CMSAttributeTableGenerator, SimpleAttributeTableGenerator, 
//            SignerInformation, SignerInfoGenerator, CMSException, CMSUtils, 
//            CMSSignedHelper, CMSProcessable, CMSStreamException

public class CMSSignedDataStreamGenerator extends CMSSignedGenerator
{
    private class CmsSignedDataOutputStream extends OutputStream
    {

        public void write(int b)
            throws IOException
        {
            _out.write(b);
        }

        public void write(byte bytes[], int off, int len)
            throws IOException
        {
            _out.write(bytes, off, len);
        }

        public void write(byte bytes[])
            throws IOException
        {
            _out.write(bytes);
        }

        public void close()
            throws IOException
        {
            _out.close();
            _eiGen.close();
            digests.clear();
            if(certs.size() != 0)
            {
                co.org.bouncy.asn1.ASN1Set certSet = CMSUtils.createBerSetFromList(certs);
                _sigGen.getRawOutputStream().write((new BERTaggedObject(false, 0, certSet)).getEncoded());
            }
            if(crls.size() != 0)
            {
                co.org.bouncy.asn1.ASN1Set crlSet = CMSUtils.createBerSetFromList(crls);
                _sigGen.getRawOutputStream().write((new BERTaggedObject(false, 1, crlSet)).getEncoded());
            }
            ASN1EncodableVector signerInfos = new ASN1EncodableVector();
            for(Iterator it = signerGens.iterator(); it.hasNext();)
            {
                SignerInfoGenerator sigGen = (SignerInfoGenerator)it.next();
                try
                {
                    signerInfos.add(sigGen.generate(_contentOID));
                    byte calculatedDigest[] = sigGen.getCalculatedDigest();
                    digests.put(sigGen.getDigestAlgorithm().getAlgorithm().getId(), calculatedDigest);
                }
                catch(CMSException e)
                {
                    throw new CMSStreamException((new StringBuilder()).append("exception generating signers: ").append(e.getMessage()).toString(), e);
                }
            }

            SignerInformation signer;
            for(Iterator it = _signers.iterator(); it.hasNext(); signerInfos.add(signer.toASN1Structure()))
                signer = (SignerInformation)it.next();

            _sigGen.getRawOutputStream().write((new DERSet(signerInfos)).getEncoded());
            _sigGen.close();
            _sGen.close();
        }

        private OutputStream _out;
        private ASN1ObjectIdentifier _contentOID;
        private BERSequenceGenerator _sGen;
        private BERSequenceGenerator _sigGen;
        private BERSequenceGenerator _eiGen;
        final CMSSignedDataStreamGenerator this$0;

        public CmsSignedDataOutputStream(OutputStream out, ASN1ObjectIdentifier contentOID, BERSequenceGenerator sGen, BERSequenceGenerator sigGen, BERSequenceGenerator eiGen)
        {
            this$0 = CMSSignedDataStreamGenerator.this;
            super();
            _out = out;
            _contentOID = contentOID;
            _sGen = sGen;
            _sigGen = sigGen;
            _eiGen = eiGen;
        }
    }


    public CMSSignedDataStreamGenerator()
    {
    }

    /**
     * @deprecated Method CMSSignedDataStreamGenerator is deprecated
     */

    public CMSSignedDataStreamGenerator(SecureRandom rand)
    {
        super(rand);
    }

    public void setBufferSize(int bufferSize)
    {
        _bufferSize = bufferSize;
    }

    /**
     * @deprecated Method addSigner is deprecated
     */

    public void addSigner(PrivateKey key, X509Certificate cert, String digestOID, String sigProvider)
        throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException
    {
        addSigner(key, cert, digestOID, CMSUtils.getProvider(sigProvider));
    }

    /**
     * @deprecated Method addSigner is deprecated
     */

    public void addSigner(PrivateKey key, X509Certificate cert, String digestOID, Provider sigProvider)
        throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException
    {
        addSigner(key, cert, digestOID, ((CMSAttributeTableGenerator) (new DefaultSignedAttributeTableGenerator())), (CMSAttributeTableGenerator)null, sigProvider);
    }

    /**
     * @deprecated Method addSigner is deprecated
     */

    public void addSigner(PrivateKey key, X509Certificate cert, String encryptionOID, String digestOID, String sigProvider)
        throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException
    {
        addSigner(key, cert, encryptionOID, digestOID, CMSUtils.getProvider(sigProvider));
    }

    /**
     * @deprecated Method addSigner is deprecated
     */

    public void addSigner(PrivateKey key, X509Certificate cert, String encryptionOID, String digestOID, Provider sigProvider)
        throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException
    {
        addSigner(key, cert, encryptionOID, digestOID, ((CMSAttributeTableGenerator) (new DefaultSignedAttributeTableGenerator())), (CMSAttributeTableGenerator)null, sigProvider);
    }

    /**
     * @deprecated Method addSigner is deprecated
     */

    public void addSigner(PrivateKey key, X509Certificate cert, String digestOID, AttributeTable signedAttr, AttributeTable unsignedAttr, String sigProvider)
        throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException
    {
        addSigner(key, cert, digestOID, signedAttr, unsignedAttr, CMSUtils.getProvider(sigProvider));
    }

    /**
     * @deprecated Method addSigner is deprecated
     */

    public void addSigner(PrivateKey key, X509Certificate cert, String digestOID, AttributeTable signedAttr, AttributeTable unsignedAttr, Provider sigProvider)
        throws NoSuchAlgorithmException, InvalidKeyException
    {
        addSigner(key, cert, digestOID, ((CMSAttributeTableGenerator) (new DefaultSignedAttributeTableGenerator(signedAttr))), ((CMSAttributeTableGenerator) (new SimpleAttributeTableGenerator(unsignedAttr))), sigProvider);
    }

    /**
     * @deprecated Method addSigner is deprecated
     */

    public void addSigner(PrivateKey key, X509Certificate cert, String encryptionOID, String digestOID, AttributeTable signedAttr, AttributeTable unsignedAttr, String sigProvider)
        throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException
    {
        addSigner(key, cert, encryptionOID, digestOID, signedAttr, unsignedAttr, CMSUtils.getProvider(sigProvider));
    }

    /**
     * @deprecated Method addSigner is deprecated
     */

    public void addSigner(PrivateKey key, X509Certificate cert, String encryptionOID, String digestOID, AttributeTable signedAttr, AttributeTable unsignedAttr, Provider sigProvider)
        throws NoSuchAlgorithmException, InvalidKeyException
    {
        addSigner(key, cert, encryptionOID, digestOID, ((CMSAttributeTableGenerator) (new DefaultSignedAttributeTableGenerator(signedAttr))), ((CMSAttributeTableGenerator) (new SimpleAttributeTableGenerator(unsignedAttr))), sigProvider);
    }

    /**
     * @deprecated Method addSigner is deprecated
     */

    public void addSigner(PrivateKey key, X509Certificate cert, String digestOID, CMSAttributeTableGenerator signedAttrGenerator, CMSAttributeTableGenerator unsignedAttrGenerator, String sigProvider)
        throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException
    {
        addSigner(key, cert, digestOID, signedAttrGenerator, unsignedAttrGenerator, CMSUtils.getProvider(sigProvider));
    }

    /**
     * @deprecated Method addSigner is deprecated
     */

    public void addSigner(PrivateKey key, X509Certificate cert, String digestOID, CMSAttributeTableGenerator signedAttrGenerator, CMSAttributeTableGenerator unsignedAttrGenerator, Provider sigProvider)
        throws NoSuchAlgorithmException, InvalidKeyException
    {
        addSigner(key, cert, getEncOID(key, digestOID), digestOID, signedAttrGenerator, unsignedAttrGenerator, sigProvider);
    }

    /**
     * @deprecated Method addSigner is deprecated
     */

    public void addSigner(PrivateKey key, X509Certificate cert, String encryptionOID, String digestOID, CMSAttributeTableGenerator signedAttrGenerator, CMSAttributeTableGenerator unsignedAttrGenerator, String sigProvider)
        throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException
    {
        addSigner(key, cert, encryptionOID, digestOID, signedAttrGenerator, unsignedAttrGenerator, CMSUtils.getProvider(sigProvider));
    }

    /**
     * @deprecated Method addSigner is deprecated
     */

    public void addSigner(PrivateKey key, X509Certificate cert, String encryptionOID, String digestOID, CMSAttributeTableGenerator signedAttrGenerator, CMSAttributeTableGenerator unsignedAttrGenerator, Provider sigProvider)
        throws NoSuchAlgorithmException, InvalidKeyException
    {
        addSigner(key, cert, encryptionOID, digestOID, signedAttrGenerator, unsignedAttrGenerator, sigProvider, sigProvider);
    }

    /**
     * @deprecated Method addSigner is deprecated
     */

    public void addSigner(PrivateKey key, byte subjectKeyID[], String digestOID, String sigProvider)
        throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException
    {
        addSigner(key, subjectKeyID, digestOID, CMSUtils.getProvider(sigProvider));
    }

    /**
     * @deprecated Method addSigner is deprecated
     */

    public void addSigner(PrivateKey key, byte subjectKeyID[], String digestOID, Provider sigProvider)
        throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException
    {
        addSigner(key, subjectKeyID, digestOID, ((CMSAttributeTableGenerator) (new DefaultSignedAttributeTableGenerator())), (CMSAttributeTableGenerator)null, sigProvider);
    }

    /**
     * @deprecated Method addSigner is deprecated
     */

    public void addSigner(PrivateKey key, byte subjectKeyID[], String encryptionOID, String digestOID, String sigProvider)
        throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException
    {
        addSigner(key, subjectKeyID, encryptionOID, digestOID, CMSUtils.getProvider(sigProvider));
    }

    /**
     * @deprecated Method addSigner is deprecated
     */

    public void addSigner(PrivateKey key, byte subjectKeyID[], String encryptionOID, String digestOID, Provider sigProvider)
        throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException
    {
        addSigner(key, subjectKeyID, encryptionOID, digestOID, ((CMSAttributeTableGenerator) (new DefaultSignedAttributeTableGenerator())), (CMSAttributeTableGenerator)null, sigProvider);
    }

    /**
     * @deprecated Method addSigner is deprecated
     */

    public void addSigner(PrivateKey key, byte subjectKeyID[], String digestOID, AttributeTable signedAttr, AttributeTable unsignedAttr, String sigProvider)
        throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException
    {
        addSigner(key, subjectKeyID, digestOID, signedAttr, unsignedAttr, CMSUtils.getProvider(sigProvider));
    }

    /**
     * @deprecated Method addSigner is deprecated
     */

    public void addSigner(PrivateKey key, byte subjectKeyID[], String digestOID, AttributeTable signedAttr, AttributeTable unsignedAttr, Provider sigProvider)
        throws NoSuchAlgorithmException, InvalidKeyException
    {
        addSigner(key, subjectKeyID, digestOID, ((CMSAttributeTableGenerator) (new DefaultSignedAttributeTableGenerator(signedAttr))), ((CMSAttributeTableGenerator) (new SimpleAttributeTableGenerator(unsignedAttr))), sigProvider);
    }

    /**
     * @deprecated Method addSigner is deprecated
     */

    public void addSigner(PrivateKey key, byte subjectKeyID[], String digestOID, CMSAttributeTableGenerator signedAttrGenerator, CMSAttributeTableGenerator unsignedAttrGenerator, String sigProvider)
        throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException
    {
        addSigner(key, subjectKeyID, digestOID, signedAttrGenerator, unsignedAttrGenerator, CMSUtils.getProvider(sigProvider));
    }

    /**
     * @deprecated Method addSigner is deprecated
     */

    public void addSigner(PrivateKey key, byte subjectKeyID[], String digestOID, CMSAttributeTableGenerator signedAttrGenerator, CMSAttributeTableGenerator unsignedAttrGenerator, Provider sigProvider)
        throws NoSuchAlgorithmException, InvalidKeyException
    {
        addSigner(key, subjectKeyID, getEncOID(key, digestOID), digestOID, signedAttrGenerator, unsignedAttrGenerator, sigProvider);
    }

    /**
     * @deprecated Method addSigner is deprecated
     */

    public void addSigner(PrivateKey key, byte subjectKeyID[], String encryptionOID, String digestOID, CMSAttributeTableGenerator signedAttrGenerator, CMSAttributeTableGenerator unsignedAttrGenerator, String sigProvider)
        throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException
    {
        addSigner(key, subjectKeyID, encryptionOID, digestOID, signedAttrGenerator, unsignedAttrGenerator, CMSUtils.getProvider(sigProvider));
    }

    /**
     * @deprecated Method addSigner is deprecated
     */

    public void addSigner(PrivateKey key, byte subjectKeyID[], String encryptionOID, String digestOID, CMSAttributeTableGenerator signedAttrGenerator, CMSAttributeTableGenerator unsignedAttrGenerator, Provider sigProvider)
        throws NoSuchAlgorithmException, InvalidKeyException
    {
        addSigner(key, subjectKeyID, encryptionOID, digestOID, signedAttrGenerator, unsignedAttrGenerator, sigProvider, sigProvider);
    }

    /**
     * @deprecated Method addSigner is deprecated
     */

    public void addSigner(PrivateKey key, X509Certificate cert, String encryptionOID, String digestOID, CMSAttributeTableGenerator signedAttrGenerator, CMSAttributeTableGenerator unsignedAttrGenerator, Provider sigProvider, 
            Provider digProvider)
        throws NoSuchAlgorithmException, InvalidKeyException
    {
        doAddSigner(key, cert, encryptionOID, digestOID, signedAttrGenerator, unsignedAttrGenerator, sigProvider, digProvider);
    }

    private void doAddSigner(PrivateKey key, Object signerId, String encryptionOID, String digestOID, CMSAttributeTableGenerator signedAttrGenerator, CMSAttributeTableGenerator unsignedAttrGenerator, Provider sigProvider, 
            Provider digProvider)
        throws NoSuchAlgorithmException, InvalidKeyException
    {
        String digestName = CMSSignedHelper.INSTANCE.getDigestAlgName(digestOID);
        String signatureName = (new StringBuilder()).append(digestName).append("with").append(CMSSignedHelper.INSTANCE.getEncryptionAlgName(encryptionOID)).toString();
        JcaContentSignerBuilder signerBuilder;
        try
        {
            signerBuilder = (new JcaContentSignerBuilder(signatureName)).setSecureRandom(rand);
        }
        catch(IllegalArgumentException e)
        {
            throw new NoSuchAlgorithmException(e.getMessage());
        }
        if(sigProvider != null)
            signerBuilder.setProvider(sigProvider);
        try
        {
            JcaDigestCalculatorProviderBuilder calculatorProviderBuilder = new JcaDigestCalculatorProviderBuilder();
            if(digProvider != null && !digProvider.getName().equalsIgnoreCase("SunRsaSign"))
                calculatorProviderBuilder.setProvider(digProvider);
            JcaSignerInfoGeneratorBuilder builder = new JcaSignerInfoGeneratorBuilder(calculatorProviderBuilder.build());
            builder.setSignedAttributeGenerator(signedAttrGenerator);
            builder.setUnsignedAttributeGenerator(unsignedAttrGenerator);
            try
            {
                ContentSigner contentSigner = signerBuilder.build(key);
                if(signerId instanceof X509Certificate)
                    addSignerInfoGenerator(builder.build(contentSigner, (X509Certificate)signerId));
                else
                    addSignerInfoGenerator(builder.build(contentSigner, (byte[])(byte[])signerId));
            }
            catch(OperatorCreationException e)
            {
                if(e.getCause() instanceof NoSuchAlgorithmException)
                    throw (NoSuchAlgorithmException)e.getCause();
                if(e.getCause() instanceof InvalidKeyException)
                    throw (InvalidKeyException)e.getCause();
            }
        }
        catch(OperatorCreationException e)
        {
            throw new NoSuchAlgorithmException((new StringBuilder()).append("unable to create operators: ").append(e.getMessage()).toString());
        }
        catch(CertificateEncodingException e)
        {
            throw new IllegalStateException("unable to encode certificate");
        }
    }

    /**
     * @deprecated Method addSigner is deprecated
     */

    public void addSigner(PrivateKey key, byte subjectKeyID[], String encryptionOID, String digestOID, CMSAttributeTableGenerator signedAttrGenerator, CMSAttributeTableGenerator unsignedAttrGenerator, Provider sigProvider, 
            Provider digProvider)
        throws NoSuchAlgorithmException, InvalidKeyException
    {
        doAddSigner(key, subjectKeyID, encryptionOID, digestOID, signedAttrGenerator, unsignedAttrGenerator, sigProvider, digProvider);
    }

    public OutputStream open(OutputStream out)
        throws IOException
    {
        return open(out, false);
    }

    public OutputStream open(OutputStream out, boolean encapsulate)
        throws IOException
    {
        return open(CMSObjectIdentifiers.data, out, encapsulate);
    }

    public OutputStream open(OutputStream out, boolean encapsulate, OutputStream dataOutputStream)
        throws IOException
    {
        return open(CMSObjectIdentifiers.data, out, encapsulate, dataOutputStream);
    }

    /**
     * @deprecated Method open is deprecated
     */

    public OutputStream open(OutputStream out, String eContentType, boolean encapsulate)
        throws IOException
    {
        return open(out, eContentType, encapsulate, null);
    }

    public OutputStream open(ASN1ObjectIdentifier eContentType, OutputStream out, boolean encapsulate)
        throws IOException
    {
        return open(eContentType, out, encapsulate, null);
    }

    /**
     * @deprecated Method open is deprecated
     */

    public OutputStream open(OutputStream out, String eContentType, boolean encapsulate, OutputStream dataOutputStream)
        throws IOException
    {
        return open(new ASN1ObjectIdentifier(eContentType), out, encapsulate, dataOutputStream);
    }

    public OutputStream open(ASN1ObjectIdentifier eContentType, OutputStream out, boolean encapsulate, OutputStream dataOutputStream)
        throws IOException
    {
        BERSequenceGenerator sGen = new BERSequenceGenerator(out);
        sGen.addObject(CMSObjectIdentifiers.signedData);
        BERSequenceGenerator sigGen = new BERSequenceGenerator(sGen.getRawOutputStream(), 0, true);
        sigGen.addObject(calculateVersion(eContentType));
        ASN1EncodableVector digestAlgs = new ASN1EncodableVector();
        SignerInformation signer;
        for(Iterator it = _signers.iterator(); it.hasNext(); digestAlgs.add(CMSSignedHelper.INSTANCE.fixAlgID(signer.getDigestAlgorithmID())))
            signer = (SignerInformation)it.next();

        SignerInfoGenerator signerGen;
        for(Iterator it = signerGens.iterator(); it.hasNext(); digestAlgs.add(signerGen.getDigestAlgorithm()))
            signerGen = (SignerInfoGenerator)it.next();

        sigGen.getRawOutputStream().write((new DERSet(digestAlgs)).getEncoded());
        BERSequenceGenerator eiGen = new BERSequenceGenerator(sigGen.getRawOutputStream());
        eiGen.addObject(eContentType);
        OutputStream encapStream = encapsulate ? CMSUtils.createBEROctetOutputStream(eiGen.getRawOutputStream(), 0, true, _bufferSize) : null;
        OutputStream contentStream = CMSUtils.getSafeTeeOutputStream(dataOutputStream, encapStream);
        OutputStream sigStream = CMSUtils.attachSignersToOutputStream(signerGens, contentStream);
        return new CmsSignedDataOutputStream(sigStream, eContentType, sGen, sigGen, eiGen);
    }

    void generate(OutputStream out, String eContentType, boolean encapsulate, OutputStream dataOutputStream, CMSProcessable content)
        throws CMSException, IOException
    {
        OutputStream signedOut = open(out, eContentType, encapsulate, dataOutputStream);
        if(content != null)
            content.write(signedOut);
        signedOut.close();
    }

    private ASN1Integer calculateVersion(ASN1ObjectIdentifier contentOid)
    {
        boolean otherCert = false;
        boolean otherCrl = false;
        boolean attrCertV1Found = false;
        boolean attrCertV2Found = false;
        if(certs != null)
        {
            Iterator it = certs.iterator();
            do
            {
                if(!it.hasNext())
                    break;
                Object obj = it.next();
                if(obj instanceof ASN1TaggedObject)
                {
                    ASN1TaggedObject tagged = (ASN1TaggedObject)obj;
                    if(tagged.getTagNo() == 1)
                        attrCertV1Found = true;
                    else
                    if(tagged.getTagNo() == 2)
                        attrCertV2Found = true;
                    else
                    if(tagged.getTagNo() == 3)
                        otherCert = true;
                }
            } while(true);
        }
        if(otherCert)
            return new ASN1Integer(5L);
        if(crls != null)
        {
            Iterator it = crls.iterator();
            do
            {
                if(!it.hasNext())
                    break;
                Object obj = it.next();
                if(obj instanceof ASN1TaggedObject)
                    otherCrl = true;
            } while(true);
        }
        if(otherCrl)
            return new ASN1Integer(5L);
        if(attrCertV2Found)
            return new ASN1Integer(4L);
        if(attrCertV1Found)
            return new ASN1Integer(3L);
        if(checkForVersion3(_signers, signerGens))
            return new ASN1Integer(3L);
        if(!CMSObjectIdentifiers.data.equals(contentOid))
            return new ASN1Integer(3L);
        else
            return new ASN1Integer(1L);
    }

    private boolean checkForVersion3(List signerInfos, List signerInfoGens)
    {
        for(Iterator it = signerInfos.iterator(); it.hasNext();)
        {
            SignerInfo s = SignerInfo.getInstance(((SignerInformation)it.next()).toASN1Structure());
            if(s.getVersion().getValue().intValue() == 3)
                return true;
        }

        for(Iterator it = signerInfoGens.iterator(); it.hasNext();)
        {
            SignerInfoGenerator s = (SignerInfoGenerator)it.next();
            if(s.getGeneratedVersion().getValue().intValue() == 3)
                return true;
        }

        return false;
    }

    private int _bufferSize;
}
