// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SMIMESignedGenerator.java

package co.org.bouncy.mail.smime;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.cms.AttributeTable;
import co.org.bouncy.asn1.cryptopro.CryptoProObjectIdentifiers;
import co.org.bouncy.asn1.nist.NISTObjectIdentifiers;
import co.org.bouncy.asn1.oiw.OIWObjectIdentifiers;
import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.asn1.teletrust.TeleTrusTObjectIdentifiers;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x9.X9ObjectIdentifiers;
import co.org.bouncy.cms.*;
import co.org.bouncy.mail.smime.util.CRLFOutputStream;
import co.org.bouncy.util.Store;
import co.org.bouncy.x509_.X509Store;
import java.io.IOException;
import java.io.OutputStream;
import java.security.*;
import java.security.cert.*;
import java.util.*;
import javax.activation.*;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.*;

// Referenced classes of package co.org.bouncy.mail.smime:
//            SMIMEGenerator, SMIMEException, SMIMEUtil, SMIMEStreamingProcessor

public class SMIMESignedGenerator extends SMIMEGenerator
{
    private class ContentSigner
        implements SMIMEStreamingProcessor
    {

        protected CMSSignedDataStreamGenerator getGenerator()
            throws CMSException, CertStoreException, InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException
        {
            CMSSignedDataStreamGenerator gen = new CMSSignedDataStreamGenerator();
            for(Iterator it = _certStores.iterator(); it.hasNext(); gen.addCertificatesAndCRLs((CertStore)it.next()));
            for(Iterator it = certStores.iterator(); it.hasNext(); gen.addCertificates((Store)it.next()));
            for(Iterator it = crlStores.iterator(); it.hasNext(); gen.addCRLs((Store)it.next()));
            for(Iterator it = attrCertStores.iterator(); it.hasNext(); gen.addAttributeCertificates((Store)it.next()));
            for(Iterator it = _attributeCerts.iterator(); it.hasNext(); gen.addAttributeCertificates((X509Store)it.next()));
            for(Iterator it = _signers.iterator(); it.hasNext();)
            {
                Signer signer = (Signer)it.next();
                if(signer.getEncryptionOID() != null)
                    gen.addSigner(signer.getKey(), signer.getCert(), signer.getEncryptionOID().getId(), signer.getDigestOID().getId(), signer.getSignedAttr(), signer.getUnsignedAttr(), provider);
                else
                    gen.addSigner(signer.getKey(), signer.getCert(), signer.getDigestOID().getId(), signer.getSignedAttr(), signer.getUnsignedAttr(), provider);
            }

            for(Iterator it = signerInfoGens.iterator(); it.hasNext(); gen.addSignerInfoGenerator((SignerInfoGenerator)it.next()));
            gen.addSigners(new SignerInformationStore(_oldSigners));
            return gen;
        }

        private void writeBodyPart(OutputStream out, MimeBodyPart bodyPart)
            throws IOException, MessagingException
        {
            if(bodyPart.getContent() instanceof Multipart)
            {
                Multipart mp = (Multipart)bodyPart.getContent();
                ContentType contentType = new ContentType(mp.getContentType());
                String boundary = (new StringBuilder()).append("--").append(contentType.getParameter("boundary")).toString();
                SMIMEUtil.LineOutputStream lOut = new SMIMEUtil.LineOutputStream(out);
                for(Enumeration headers = bodyPart.getAllHeaderLines(); headers.hasMoreElements(); lOut.writeln((String)headers.nextElement()));
                lOut.writeln();
                SMIMEUtil.outputPreamble(lOut, bodyPart, boundary);
                for(int i = 0; i < mp.getCount(); i++)
                {
                    lOut.writeln(boundary);
                    writeBodyPart(out, (MimeBodyPart)mp.getBodyPart(i));
                    lOut.writeln();
                }

                lOut.writeln((new StringBuilder()).append(boundary).append("--").toString());
            } else
            {
                if(SMIMEUtil.isCanonicalisationRequired(bodyPart, defaultContentTransferEncoding))
                    out = new CRLFOutputStream(out);
                bodyPart.writeTo(out);
            }
        }

        public void write(OutputStream out)
            throws IOException
        {
            try
            {
                CMSSignedDataStreamGenerator gen = getGenerator();
                OutputStream signingStream = gen.open(out, encapsulate);
                if(content != null)
                    if(!encapsulate)
                    {
                        writeBodyPart(signingStream, content);
                    } else
                    {
                        content.getDataHandler().setCommandMap(SMIMESignedGenerator.addCommands(CommandMap.getDefaultCommandMap()));
                        content.writeTo(signingStream);
                    }
                signingStream.close();
                _digests = gen.getGeneratedDigests();
            }
            catch(MessagingException e)
            {
                throw new IOException(e.toString());
            }
            catch(NoSuchAlgorithmException e)
            {
                throw new IOException(e.toString());
            }
            catch(NoSuchProviderException e)
            {
                throw new IOException(e.toString());
            }
            catch(CMSException e)
            {
                throw new IOException(e.toString());
            }
            catch(InvalidKeyException e)
            {
                throw new IOException(e.toString());
            }
            catch(CertStoreException e)
            {
                throw new IOException(e.toString());
            }
        }

        private final MimeBodyPart content;
        private final boolean encapsulate;
        private final Provider provider;
        private final boolean noProvider;
        final SMIMESignedGenerator this$0;

        ContentSigner(MimeBodyPart content, boolean encapsulate, Provider provider)
        {
            this$0 = SMIMESignedGenerator.this;
            super();
            this.content = content;
            this.encapsulate = encapsulate;
            this.provider = provider;
            noProvider = false;
        }

        ContentSigner(MimeBodyPart content, boolean encapsulate)
        {
            this$0 = SMIMESignedGenerator.this;
            super();
            this.content = content;
            this.encapsulate = encapsulate;
            provider = null;
            noProvider = true;
        }
    }

    private class Signer
    {

        public X509Certificate getCert()
        {
            return cert;
        }

        public ASN1ObjectIdentifier getEncryptionOID()
        {
            return encryptionOID;
        }

        public ASN1ObjectIdentifier getDigestOID()
        {
            return digestOID;
        }

        public PrivateKey getKey()
        {
            return key;
        }

        public AttributeTable getSignedAttr()
        {
            return signedAttr;
        }

        public AttributeTable getUnsignedAttr()
        {
            return unsignedAttr;
        }

        final PrivateKey key;
        final X509Certificate cert;
        final ASN1ObjectIdentifier encryptionOID;
        final ASN1ObjectIdentifier digestOID;
        final AttributeTable signedAttr;
        final AttributeTable unsignedAttr;
        final SMIMESignedGenerator this$0;

        Signer(PrivateKey key, X509Certificate cert, ASN1ObjectIdentifier digestOID, AttributeTable signedAttr, AttributeTable unsignedAttr)
        {
            this(key, cert, null, digestOID, signedAttr, unsignedAttr);
        }

        Signer(PrivateKey key, X509Certificate cert, ASN1ObjectIdentifier encryptionOID, ASN1ObjectIdentifier digestOID, AttributeTable signedAttr, AttributeTable unsignedAttr)
        {
            this$0 = SMIMESignedGenerator.this;
            super();
            this.key = key;
            this.cert = cert;
            this.encryptionOID = encryptionOID;
            this.digestOID = digestOID;
            this.signedAttr = signedAttr;
            this.unsignedAttr = unsignedAttr;
        }
    }


    private static MailcapCommandMap addCommands(CommandMap cm)
    {
        MailcapCommandMap mc = (MailcapCommandMap)cm;
        mc.addMailcap("application/pkcs7-signature;; x-java-content-handler=org.bouncy.mail.smime.handlers.pkcs7_signature");
        mc.addMailcap("application/pkcs7-mime;; x-java-content-handler=org.bouncy.mail.smime.handlers.pkcs7_mime");
        mc.addMailcap("application/x-pkcs7-signature;; x-java-content-handler=org.bouncy.mail.smime.handlers.x_pkcs7_signature");
        mc.addMailcap("application/x-pkcs7-mime;; x-java-content-handler=org.bouncy.mail.smime.handlers.x_pkcs7_mime");
        mc.addMailcap("multipart/signed;; x-java-content-handler=org.bouncy.mail.smime.handlers.multipart_signed");
        return mc;
    }

    public SMIMESignedGenerator()
    {
        this("7bit", STANDARD_MICALGS);
    }

    public SMIMESignedGenerator(String defaultContentTransferEncoding)
    {
        this(defaultContentTransferEncoding, STANDARD_MICALGS);
    }

    public SMIMESignedGenerator(Map micAlgs)
    {
        this("7bit", micAlgs);
    }

    public SMIMESignedGenerator(String defaultContentTransferEncoding, Map micAlgs)
    {
        _certStores = new ArrayList();
        certStores = new ArrayList();
        crlStores = new ArrayList();
        attrCertStores = new ArrayList();
        signerInfoGens = new ArrayList();
        _signers = new ArrayList();
        _oldSigners = new ArrayList();
        _attributeCerts = new ArrayList();
        _digests = new HashMap();
        this.defaultContentTransferEncoding = defaultContentTransferEncoding;
        this.micAlgs = micAlgs;
    }

    /**
     * @deprecated Method addSigner is deprecated
     */

    public void addSigner(PrivateKey key, X509Certificate cert, String digestOID)
        throws IllegalArgumentException
    {
        _signers.add(new Signer(key, cert, new ASN1ObjectIdentifier(digestOID), null, null));
    }

    /**
     * @deprecated Method addSigner is deprecated
     */

    public void addSigner(PrivateKey key, X509Certificate cert, String encryptionOID, String digestOID)
        throws IllegalArgumentException
    {
        _signers.add(new Signer(key, cert, new ASN1ObjectIdentifier(encryptionOID), new ASN1ObjectIdentifier(digestOID), null, null));
    }

    /**
     * @deprecated Method addSigner is deprecated
     */

    public void addSigner(PrivateKey key, X509Certificate cert, String digestOID, AttributeTable signedAttr, AttributeTable unsignedAttr)
        throws IllegalArgumentException
    {
        _signers.add(new Signer(key, cert, new ASN1ObjectIdentifier(digestOID), signedAttr, unsignedAttr));
    }

    /**
     * @deprecated Method addSigner is deprecated
     */

    public void addSigner(PrivateKey key, X509Certificate cert, String encryptionOID, String digestOID, AttributeTable signedAttr, AttributeTable unsignedAttr)
        throws IllegalArgumentException
    {
        _signers.add(new Signer(key, cert, new ASN1ObjectIdentifier(encryptionOID), new ASN1ObjectIdentifier(digestOID), signedAttr, unsignedAttr));
    }

    public void addSigners(SignerInformationStore signerStore)
    {
        for(Iterator it = signerStore.getSigners().iterator(); it.hasNext(); _oldSigners.add(it.next()));
    }

    public void addSignerInfoGenerator(SignerInfoGenerator sigInfoGen)
    {
        signerInfoGens.add(sigInfoGen);
    }

    /**
     * @deprecated Method addCertificatesAndCRLs is deprecated
     */

    public void addCertificatesAndCRLs(CertStore certStore)
        throws CertStoreException, SMIMEException
    {
        _certStores.add(certStore);
    }

    public void addCertificates(Store certStore)
    {
        certStores.add(certStore);
    }

    public void addCRLs(Store crlStore)
    {
        crlStores.add(crlStore);
    }

    public void addAttributeCertificates(Store certStore)
    {
        attrCertStores.add(certStore);
    }

    /**
     * @deprecated Method addAttributeCertificates is deprecated
     */

    public void addAttributeCertificates(X509Store store)
        throws CMSException
    {
        _attributeCerts.add(store);
    }

    private void addHashHeader(StringBuffer header, List signers)
    {
        int count = 0;
        Iterator it = signers.iterator();
        Set micAlgSet = new TreeSet();
        while(it.hasNext()) 
        {
            Object signer = it.next();
            ASN1ObjectIdentifier digestOID;
            if(signer instanceof Signer)
                digestOID = ((Signer)signer).getDigestOID();
            else
            if(signer instanceof SignerInformation)
                digestOID = ((SignerInformation)signer).getDigestAlgorithmID().getAlgorithm();
            else
                digestOID = ((SignerInfoGenerator)signer).getDigestAlgorithm().getAlgorithm();
            String micAlg = (String)micAlgs.get(digestOID);
            if(micAlg == null)
                micAlgSet.add("unknown");
            else
                micAlgSet.add(micAlg);
        }
        for(it = micAlgSet.iterator(); it.hasNext();)
        {
            String alg = (String)it.next();
            if(count == 0)
            {
                if(micAlgSet.size() != 1)
                    header.append("; micalg=\"");
                else
                    header.append("; micalg=");
            } else
            {
                header.append(',');
            }
            header.append(alg);
            count++;
        }

        if(count != 0 && micAlgSet.size() != 1)
            header.append('"');
    }

    private MimeMultipart make(MimeBodyPart content, Provider sigProvider)
        throws NoSuchAlgorithmException, SMIMEException
    {
        try
        {
            MimeBodyPart sig = new MimeBodyPart();
            sig.setContent(new ContentSigner(content, false, sigProvider), "application/pkcs7-signature; name=smime.p7s; smime-type=signed-data");
            sig.addHeader("Content-Type", "application/pkcs7-signature; name=smime.p7s; smime-type=signed-data");
            sig.addHeader("Content-Disposition", "attachment; filename=\"smime.p7s\"");
            sig.addHeader("Content-Description", "S/MIME Cryptographic Signature");
            sig.addHeader("Content-Transfer-Encoding", encoding);
            StringBuffer header = new StringBuffer("signed; protocol=\"application/pkcs7-signature\"");
            List allSigners = new ArrayList(_signers);
            allSigners.addAll(_oldSigners);
            allSigners.addAll(signerInfoGens);
            addHashHeader(header, allSigners);
            MimeMultipart mm = new MimeMultipart(header.toString());
            mm.addBodyPart(content);
            mm.addBodyPart(sig);
            return mm;
        }
        catch(MessagingException e)
        {
            throw new SMIMEException("exception putting multi-part together.", e);
        }
    }

    private MimeMultipart make(MimeBodyPart content)
        throws SMIMEException
    {
        try
        {
            MimeBodyPart sig = new MimeBodyPart();
            sig.setContent(new ContentSigner(content, false), "application/pkcs7-signature; name=smime.p7s; smime-type=signed-data");
            sig.addHeader("Content-Type", "application/pkcs7-signature; name=smime.p7s; smime-type=signed-data");
            sig.addHeader("Content-Disposition", "attachment; filename=\"smime.p7s\"");
            sig.addHeader("Content-Description", "S/MIME Cryptographic Signature");
            sig.addHeader("Content-Transfer-Encoding", encoding);
            StringBuffer header = new StringBuffer("signed; protocol=\"application/pkcs7-signature\"");
            List allSigners = new ArrayList(_signers);
            allSigners.addAll(_oldSigners);
            allSigners.addAll(signerInfoGens);
            addHashHeader(header, allSigners);
            MimeMultipart mm = new MimeMultipart(header.toString());
            mm.addBodyPart(content);
            mm.addBodyPart(sig);
            return mm;
        }
        catch(MessagingException e)
        {
            throw new SMIMEException("exception putting multi-part together.", e);
        }
    }

    private MimeBodyPart makeEncapsulated(MimeBodyPart content, Provider sigProvider)
        throws NoSuchAlgorithmException, SMIMEException
    {
        try
        {
            MimeBodyPart sig = new MimeBodyPart();
            sig.setContent(new ContentSigner(content, true, sigProvider), "application/pkcs7-mime; name=smime.p7m; smime-type=signed-data");
            sig.addHeader("Content-Type", "application/pkcs7-mime; name=smime.p7m; smime-type=signed-data");
            sig.addHeader("Content-Disposition", "attachment; filename=\"smime.p7m\"");
            sig.addHeader("Content-Description", "S/MIME Cryptographic Signed Data");
            sig.addHeader("Content-Transfer-Encoding", encoding);
            return sig;
        }
        catch(MessagingException e)
        {
            throw new SMIMEException("exception putting body part together.", e);
        }
    }

    private MimeBodyPart makeEncapsulated(MimeBodyPart content)
        throws SMIMEException
    {
        try
        {
            MimeBodyPart sig = new MimeBodyPart();
            sig.setContent(new ContentSigner(content, true), "application/pkcs7-mime; name=smime.p7m; smime-type=signed-data");
            sig.addHeader("Content-Type", "application/pkcs7-mime; name=smime.p7m; smime-type=signed-data");
            sig.addHeader("Content-Disposition", "attachment; filename=\"smime.p7m\"");
            sig.addHeader("Content-Description", "S/MIME Cryptographic Signed Data");
            sig.addHeader("Content-Transfer-Encoding", encoding);
            return sig;
        }
        catch(MessagingException e)
        {
            throw new SMIMEException("exception putting body part together.", e);
        }
    }

    public Map getGeneratedDigests()
    {
        return new HashMap(_digests);
    }

    /**
     * @deprecated Method generate is deprecated
     */

    public MimeMultipart generate(MimeBodyPart content, String sigProvider)
        throws NoSuchAlgorithmException, NoSuchProviderException, SMIMEException
    {
        return make(makeContentBodyPart(content), SMIMEUtil.getProvider(sigProvider));
    }

    public MimeMultipart generate(MimeBodyPart content, Provider sigProvider)
        throws NoSuchAlgorithmException, SMIMEException
    {
        return make(makeContentBodyPart(content), sigProvider);
    }

    public MimeMultipart generate(MimeMessage message, String sigProvider)
        throws NoSuchAlgorithmException, NoSuchProviderException, SMIMEException
    {
        return generate(message, SMIMEUtil.getProvider(sigProvider));
    }

    public MimeMultipart generate(MimeMessage message, Provider sigProvider)
        throws NoSuchAlgorithmException, SMIMEException
    {
        try
        {
            message.saveChanges();
        }
        catch(MessagingException e)
        {
            throw new SMIMEException("unable to save message", e);
        }
        return make(makeContentBodyPart(message), sigProvider);
    }

    public MimeMultipart generate(MimeBodyPart content)
        throws SMIMEException
    {
        return make(makeContentBodyPart(content));
    }

    public MimeBodyPart generateEncapsulated(MimeBodyPart content)
        throws SMIMEException
    {
        return makeEncapsulated(makeContentBodyPart(content));
    }

    /**
     * @deprecated Method generateEncapsulated is deprecated
     */

    public MimeBodyPart generateEncapsulated(MimeBodyPart content, String sigProvider)
        throws NoSuchAlgorithmException, NoSuchProviderException, SMIMEException
    {
        return makeEncapsulated(makeContentBodyPart(content), SMIMEUtil.getProvider(sigProvider));
    }

    /**
     * @deprecated Method generateEncapsulated is deprecated
     */

    public MimeBodyPart generateEncapsulated(MimeBodyPart content, Provider sigProvider)
        throws NoSuchAlgorithmException, NoSuchProviderException, SMIMEException
    {
        return makeEncapsulated(makeContentBodyPart(content), sigProvider);
    }

    /**
     * @deprecated Method generateEncapsulated is deprecated
     */

    public MimeBodyPart generateEncapsulated(MimeMessage message, String sigProvider)
        throws NoSuchAlgorithmException, NoSuchProviderException, SMIMEException
    {
        return generateEncapsulated(message, SMIMEUtil.getProvider(sigProvider));
    }

    /**
     * @deprecated Method generateEncapsulated is deprecated
     */

    public MimeBodyPart generateEncapsulated(MimeMessage message, Provider sigProvider)
        throws NoSuchAlgorithmException, SMIMEException
    {
        try
        {
            message.saveChanges();
        }
        catch(MessagingException e)
        {
            throw new SMIMEException("unable to save message", e);
        }
        return makeEncapsulated(makeContentBodyPart(message), sigProvider);
    }

    /**
     * @deprecated Method generateCertificateManagement is deprecated
     */

    public MimeBodyPart generateCertificateManagement(String provider)
        throws SMIMEException, NoSuchProviderException
    {
        return generateCertificateManagement(SMIMEUtil.getProvider(provider));
    }

    /**
     * @deprecated Method generateCertificateManagement is deprecated
     */

    public MimeBodyPart generateCertificateManagement(Provider provider)
        throws SMIMEException
    {
        try
        {
            MimeBodyPart sig = new MimeBodyPart();
            sig.setContent(new ContentSigner(null, true, provider), "application/pkcs7-mime; name=smime.p7c; smime-type=certs-only");
            sig.addHeader("Content-Type", "application/pkcs7-mime; name=smime.p7c; smime-type=certs-only");
            sig.addHeader("Content-Disposition", "attachment; filename=\"smime.p7c\"");
            sig.addHeader("Content-Description", "S/MIME Certificate Management Message");
            sig.addHeader("Content-Transfer-Encoding", encoding);
            return sig;
        }
        catch(MessagingException e)
        {
            throw new SMIMEException("exception putting body part together.", e);
        }
    }

    public MimeBodyPart generateCertificateManagement()
        throws SMIMEException
    {
        try
        {
            MimeBodyPart sig = new MimeBodyPart();
            sig.setContent(new ContentSigner(null, true), "application/pkcs7-mime; name=smime.p7c; smime-type=certs-only");
            sig.addHeader("Content-Type", "application/pkcs7-mime; name=smime.p7c; smime-type=certs-only");
            sig.addHeader("Content-Disposition", "attachment; filename=\"smime.p7c\"");
            sig.addHeader("Content-Description", "S/MIME Certificate Management Message");
            sig.addHeader("Content-Transfer-Encoding", encoding);
            return sig;
        }
        catch(MessagingException e)
        {
            throw new SMIMEException("exception putting body part together.", e);
        }
    }

    public static final String DIGEST_SHA1;
    public static final String DIGEST_MD5;
    public static final String DIGEST_SHA224;
    public static final String DIGEST_SHA256;
    public static final String DIGEST_SHA384;
    public static final String DIGEST_SHA512;
    public static final String DIGEST_GOST3411;
    public static final String DIGEST_RIPEMD128;
    public static final String DIGEST_RIPEMD160;
    public static final String DIGEST_RIPEMD256;
    public static final String ENCRYPTION_RSA;
    public static final String ENCRYPTION_DSA;
    public static final String ENCRYPTION_ECDSA;
    public static final String ENCRYPTION_RSA_PSS;
    public static final String ENCRYPTION_GOST3410;
    public static final String ENCRYPTION_ECGOST3410;
    private static final String CERTIFICATE_MANAGEMENT_CONTENT = "application/pkcs7-mime; name=smime.p7c; smime-type=certs-only";
    private static final String DETACHED_SIGNATURE_TYPE = "application/pkcs7-signature; name=smime.p7s; smime-type=signed-data";
    private static final String ENCAPSULATED_SIGNED_CONTENT_TYPE = "application/pkcs7-mime; name=smime.p7m; smime-type=signed-data";
    public static final Map RFC3851_MICALGS;
    public static final Map RFC5751_MICALGS;
    public static final Map STANDARD_MICALGS;
    private final String defaultContentTransferEncoding;
    private final Map micAlgs;
    private List _certStores;
    private List certStores;
    private List crlStores;
    private List attrCertStores;
    private List signerInfoGens;
    private List _signers;
    private List _oldSigners;
    private List _attributeCerts;
    private Map _digests;

    static 
    {
        DIGEST_SHA1 = OIWObjectIdentifiers.idSHA1.getId();
        DIGEST_MD5 = PKCSObjectIdentifiers.md5.getId();
        DIGEST_SHA224 = NISTObjectIdentifiers.id_sha224.getId();
        DIGEST_SHA256 = NISTObjectIdentifiers.id_sha256.getId();
        DIGEST_SHA384 = NISTObjectIdentifiers.id_sha384.getId();
        DIGEST_SHA512 = NISTObjectIdentifiers.id_sha512.getId();
        DIGEST_GOST3411 = CryptoProObjectIdentifiers.gostR3411.getId();
        DIGEST_RIPEMD128 = TeleTrusTObjectIdentifiers.ripemd128.getId();
        DIGEST_RIPEMD160 = TeleTrusTObjectIdentifiers.ripemd160.getId();
        DIGEST_RIPEMD256 = TeleTrusTObjectIdentifiers.ripemd256.getId();
        ENCRYPTION_RSA = PKCSObjectIdentifiers.rsaEncryption.getId();
        ENCRYPTION_DSA = X9ObjectIdentifiers.id_dsa_with_sha1.getId();
        ENCRYPTION_ECDSA = X9ObjectIdentifiers.ecdsa_with_SHA1.getId();
        ENCRYPTION_RSA_PSS = PKCSObjectIdentifiers.id_RSASSA_PSS.getId();
        ENCRYPTION_GOST3410 = CryptoProObjectIdentifiers.gostR3410_94.getId();
        ENCRYPTION_ECGOST3410 = CryptoProObjectIdentifiers.gostR3410_2001.getId();
        CommandMap.setDefaultCommandMap(addCommands(CommandMap.getDefaultCommandMap()));
        Map stdMicAlgs = new HashMap();
        stdMicAlgs.put(CMSAlgorithm.MD5, "md5");
        stdMicAlgs.put(CMSAlgorithm.SHA1, "sha-1");
        stdMicAlgs.put(CMSAlgorithm.SHA224, "sha-224");
        stdMicAlgs.put(CMSAlgorithm.SHA256, "sha-256");
        stdMicAlgs.put(CMSAlgorithm.SHA384, "sha-384");
        stdMicAlgs.put(CMSAlgorithm.SHA512, "sha-512");
        stdMicAlgs.put(CMSAlgorithm.GOST3411, "gostr3411-94");
        RFC5751_MICALGS = Collections.unmodifiableMap(stdMicAlgs);
        Map oldMicAlgs = new HashMap();
        oldMicAlgs.put(CMSAlgorithm.MD5, "md5");
        oldMicAlgs.put(CMSAlgorithm.SHA1, "sha1");
        oldMicAlgs.put(CMSAlgorithm.SHA224, "sha224");
        oldMicAlgs.put(CMSAlgorithm.SHA256, "sha256");
        oldMicAlgs.put(CMSAlgorithm.SHA384, "sha384");
        oldMicAlgs.put(CMSAlgorithm.SHA512, "sha512");
        oldMicAlgs.put(CMSAlgorithm.GOST3411, "gostr3411-94");
        RFC3851_MICALGS = Collections.unmodifiableMap(oldMicAlgs);
        STANDARD_MICALGS = RFC5751_MICALGS;
    }











}
