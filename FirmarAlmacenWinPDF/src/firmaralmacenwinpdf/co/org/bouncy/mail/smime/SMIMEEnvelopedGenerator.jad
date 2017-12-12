// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SMIMEEnvelopedGenerator.java

package co.org.bouncy.mail.smime;

import co.org.bouncy.asn1.ASN1EncodableVector;
import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.cms.*;
import co.org.bouncy.cms.jcajce.*;
import co.org.bouncy.operator.OutputEncryptor;
import java.io.IOException;
import java.io.OutputStream;
import java.security.*;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.*;
import javax.activation.*;
import javax.crypto.SecretKey;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;

// Referenced classes of package co.org.bouncy.mail.smime:
//            SMIMEGenerator, SMIMEException, SMIMEUtil, SMIMEStreamingProcessor

public class SMIMEEnvelopedGenerator extends SMIMEGenerator
{
    private static class WrappingIOException extends IOException
    {

        public Throwable getCause()
        {
            return cause;
        }

        private Throwable cause;

        WrappingIOException(String msg, Throwable cause)
        {
            super(msg);
            this.cause = cause;
        }
    }

    private class EnvelopedGenerator extends CMSEnvelopedDataStreamGenerator
    {

        protected OutputStream open(ASN1ObjectIdentifier dataType, OutputStream out, ASN1EncodableVector recipientInfos, OutputEncryptor encryptor)
            throws IOException
        {
            this.dataType = dataType;
            this.recipientInfos = recipientInfos;
            return super.open(dataType, out, recipientInfos, encryptor);
        }

        OutputStream regenerate(OutputStream out, OutputEncryptor encryptor)
            throws IOException
        {
            return super.open(dataType, out, recipientInfos, encryptor);
        }

        private ASN1ObjectIdentifier dataType;
        private ASN1EncodableVector recipientInfos;
        final SMIMEEnvelopedGenerator this$0;

        private EnvelopedGenerator()
        {
            this$0 = SMIMEEnvelopedGenerator.this;
            super();
        }

    }

    private class ContentEncryptor
        implements SMIMEStreamingProcessor
    {

        public void write(OutputStream out)
            throws IOException
        {
            try
            {
                OutputStream encrypted;
                if(_firstTime)
                {
                    encrypted = fact.open(out, _encryptor);
                    _firstTime = false;
                } else
                {
                    encrypted = fact.regenerate(out, _encryptor);
                }
                _content.getDataHandler().setCommandMap(SMIMEEnvelopedGenerator.addCommands(CommandMap.getDefaultCommandMap()));
                _content.writeTo(encrypted);
                encrypted.close();
            }
            catch(MessagingException e)
            {
                throw new WrappingIOException(e.toString(), e);
            }
            catch(CMSException e)
            {
                throw new WrappingIOException(e.toString(), e);
            }
        }

        private final MimeBodyPart _content;
        private OutputEncryptor _encryptor;
        private boolean _firstTime;
        final SMIMEEnvelopedGenerator this$0;

        ContentEncryptor(MimeBodyPart content, ASN1ObjectIdentifier encryptionOid, int keySize, Provider provider)
            throws CMSException
        {
            this$0 = SMIMEEnvelopedGenerator.this;
            super();
            _firstTime = true;
            _content = content;
            if(keySize == 0)
                _encryptor = (new JceCMSContentEncryptorBuilder(encryptionOid)).setProvider(provider).build();
            else
                _encryptor = (new JceCMSContentEncryptorBuilder(encryptionOid, keySize)).setProvider(provider).build();
            if(provider != null)
            {
                Iterator it = recipients.iterator();
                do
                {
                    if(!it.hasNext())
                        break;
                    RecipientInfoGenerator rd = (RecipientInfoGenerator)it.next();
                    if(rd instanceof JceKeyTransRecipientInfoGenerator)
                        ((JceKeyTransRecipientInfoGenerator)rd).setProvider(provider);
                    else
                    if(rd instanceof JceKEKRecipientInfoGenerator)
                        ((JceKEKRecipientInfoGenerator)rd).setProvider(provider);
                } while(true);
            }
        }

        ContentEncryptor(MimeBodyPart content, OutputEncryptor encryptor)
        {
            this$0 = SMIMEEnvelopedGenerator.this;
            super();
            _firstTime = true;
            _content = content;
            _encryptor = encryptor;
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

    public SMIMEEnvelopedGenerator()
    {
        recipients = new ArrayList();
        fact = new EnvelopedGenerator();
    }

    public void addRecipientInfoGenerator(RecipientInfoGenerator recipientInfoGen)
        throws IllegalArgumentException
    {
        fact.addRecipientInfoGenerator(recipientInfoGen);
    }

    /**
     * @deprecated Method addKeyTransRecipient is deprecated
     */

    public void addKeyTransRecipient(X509Certificate cert)
        throws IllegalArgumentException
    {
        try
        {
            JceKeyTransRecipientInfoGenerator infoGenerator = new JceKeyTransRecipientInfoGenerator(cert);
            recipients.add(infoGenerator);
            fact.addRecipientInfoGenerator(infoGenerator);
        }
        catch(CertificateEncodingException e)
        {
            throw new IllegalArgumentException(e.toString());
        }
    }

    /**
     * @deprecated Method addKeyTransRecipient is deprecated
     */

    public void addKeyTransRecipient(PublicKey key, byte subKeyId[])
        throws IllegalArgumentException
    {
        JceKeyTransRecipientInfoGenerator infoGenerator = new JceKeyTransRecipientInfoGenerator(subKeyId, key);
        recipients.add(infoGenerator);
        fact.addRecipientInfoGenerator(infoGenerator);
    }

    /**
     * @deprecated Method addKEKRecipient is deprecated
     */

    public void addKEKRecipient(SecretKey key, byte keyIdentifier[])
        throws IllegalArgumentException
    {
        JceKEKRecipientInfoGenerator infoGenerator = new JceKEKRecipientInfoGenerator(keyIdentifier, key);
        recipients.add(infoGenerator);
        fact.addRecipientInfoGenerator(infoGenerator);
    }

    /**
     * @deprecated Method addKeyAgreementRecipient is deprecated
     */

    public void addKeyAgreementRecipient(String agreementAlgorithm, PrivateKey senderPrivateKey, PublicKey senderPublicKey, X509Certificate recipientCert, String cekWrapAlgorithm, String provider)
        throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeyException
    {
        addKeyAgreementRecipient(agreementAlgorithm, senderPrivateKey, senderPublicKey, recipientCert, cekWrapAlgorithm, provider);
    }

    /**
     * @deprecated Method addKeyAgreementRecipient is deprecated
     */

    public void addKeyAgreementRecipient(String agreementAlgorithm, PrivateKey senderPrivateKey, PublicKey senderPublicKey, X509Certificate recipientCert, String cekWrapAlgorithm, Provider provider)
        throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeyException
    {
        try
        {
            JceKeyAgreeRecipientInfoGenerator infoGenerator = new JceKeyAgreeRecipientInfoGenerator(new ASN1ObjectIdentifier(agreementAlgorithm), senderPrivateKey, senderPublicKey, new ASN1ObjectIdentifier(cekWrapAlgorithm));
            infoGenerator.addRecipient(recipientCert);
            if(provider != null)
                infoGenerator.setProvider(provider);
            fact.addRecipientInfoGenerator(infoGenerator);
        }
        catch(CertificateEncodingException e)
        {
            throw new NoSuchAlgorithmException((new StringBuilder()).append("cannot set up generator: ").append(e).toString());
        }
    }

    public void setBerEncodeRecipients(boolean berEncodeRecipientSet)
    {
        fact.setBEREncodeRecipients(berEncodeRecipientSet);
    }

    private MimeBodyPart make(MimeBodyPart content, ASN1ObjectIdentifier encryptionOID, int keySize, Provider provider)
        throws NoSuchAlgorithmException, SMIMEException
    {
        createSymmetricKeyGenerator(encryptionOID.getId(), provider);
        try
        {
            MimeBodyPart data = new MimeBodyPart();
            data.setContent(new ContentEncryptor(content, encryptionOID, keySize, provider), "application/pkcs7-mime; name=\"smime.p7m\"; smime-type=enveloped-data");
            data.addHeader("Content-Type", "application/pkcs7-mime; name=\"smime.p7m\"; smime-type=enveloped-data");
            data.addHeader("Content-Disposition", "attachment; filename=\"smime.p7m\"");
            data.addHeader("Content-Description", "S/MIME Encrypted Message");
            data.addHeader("Content-Transfer-Encoding", encoding);
            return data;
        }
        catch(MessagingException e)
        {
            throw new SMIMEException("exception putting S/MIME message together.", e);
        }
        catch(CMSException e)
        {
            throw new SMIMEException("exception putting envelope together.", e);
        }
    }

    private MimeBodyPart make(MimeBodyPart content, OutputEncryptor encryptor)
        throws SMIMEException
    {
        try
        {
            MimeBodyPart data = new MimeBodyPart();
            data.setContent(new ContentEncryptor(content, encryptor), "application/pkcs7-mime; name=\"smime.p7m\"; smime-type=enveloped-data");
            data.addHeader("Content-Type", "application/pkcs7-mime; name=\"smime.p7m\"; smime-type=enveloped-data");
            data.addHeader("Content-Disposition", "attachment; filename=\"smime.p7m\"");
            data.addHeader("Content-Description", "S/MIME Encrypted Message");
            data.addHeader("Content-Transfer-Encoding", encoding);
            return data;
        }
        catch(MessagingException e)
        {
            throw new SMIMEException("exception putting multi-part together.", e);
        }
    }

    public MimeBodyPart generate(MimeBodyPart content, OutputEncryptor encryptor)
        throws SMIMEException
    {
        return make(makeContentBodyPart(content), encryptor);
    }

    public MimeBodyPart generate(MimeMessage message, OutputEncryptor encryptor)
        throws SMIMEException
    {
        try
        {
            message.saveChanges();
        }
        catch(MessagingException e)
        {
            throw new SMIMEException("unable to save message", e);
        }
        return make(makeContentBodyPart(message), encryptor);
    }

    /**
     * @deprecated Method generate is deprecated
     */

    public MimeBodyPart generate(MimeBodyPart content, String encryptionOID, String provider)
        throws NoSuchAlgorithmException, NoSuchProviderException, SMIMEException
    {
        return make(makeContentBodyPart(content), new ASN1ObjectIdentifier(encryptionOID), 0, SMIMEUtil.getProvider(provider));
    }

    /**
     * @deprecated Method generate is deprecated
     */

    public MimeBodyPart generate(MimeBodyPart content, String encryptionOID, Provider provider)
        throws NoSuchAlgorithmException, SMIMEException
    {
        return make(makeContentBodyPart(content), new ASN1ObjectIdentifier(encryptionOID), 0, provider);
    }

    /**
     * @deprecated Method generate is deprecated
     */

    public MimeBodyPart generate(MimeMessage message, String encryptionOID, String provider)
        throws NoSuchAlgorithmException, NoSuchProviderException, SMIMEException
    {
        return generate(message, encryptionOID, SMIMEUtil.getProvider(provider));
    }

    /**
     * @deprecated Method generate is deprecated
     */

    public MimeBodyPart generate(MimeMessage message, String encryptionOID, Provider provider)
        throws NoSuchAlgorithmException, NoSuchProviderException, SMIMEException
    {
        try
        {
            message.saveChanges();
        }
        catch(MessagingException e)
        {
            throw new SMIMEException("unable to save message", e);
        }
        return make(makeContentBodyPart(message), new ASN1ObjectIdentifier(encryptionOID), 0, provider);
    }

    /**
     * @deprecated Method generate is deprecated
     */

    public MimeBodyPart generate(MimeBodyPart content, String encryptionOID, int keySize, String provider)
        throws NoSuchAlgorithmException, NoSuchProviderException, SMIMEException
    {
        return generate(content, encryptionOID, keySize, SMIMEUtil.getProvider(provider));
    }

    /**
     * @deprecated Method generate is deprecated
     */

    public MimeBodyPart generate(MimeBodyPart content, String encryptionOID, int keySize, Provider provider)
        throws NoSuchAlgorithmException, NoSuchProviderException, SMIMEException
    {
        return make(makeContentBodyPart(content), new ASN1ObjectIdentifier(encryptionOID), keySize, provider);
    }

    /**
     * @deprecated Method generate is deprecated
     */

    public MimeBodyPart generate(MimeMessage message, String encryptionOID, int keySize, String provider)
        throws NoSuchAlgorithmException, NoSuchProviderException, SMIMEException
    {
        return generate(message, encryptionOID, keySize, SMIMEUtil.getProvider(provider));
    }

    /**
     * @deprecated Method generate is deprecated
     */

    public MimeBodyPart generate(MimeMessage message, String encryptionOID, int keySize, Provider provider)
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
        return make(makeContentBodyPart(message), new ASN1ObjectIdentifier(encryptionOID), keySize, provider);
    }

    public static final String DES_EDE3_CBC;
    public static final String RC2_CBC;
    public static final String IDEA_CBC = "1.3.6.1.4.1.188.7.1.1.2";
    public static final String CAST5_CBC = "1.2.840.113533.7.66.10";
    public static final String AES128_CBC;
    public static final String AES192_CBC;
    public static final String AES256_CBC;
    public static final String CAMELLIA128_CBC;
    public static final String CAMELLIA192_CBC;
    public static final String CAMELLIA256_CBC;
    public static final String SEED_CBC;
    public static final String DES_EDE3_WRAP;
    public static final String AES128_WRAP;
    public static final String AES256_WRAP;
    public static final String CAMELLIA128_WRAP;
    public static final String CAMELLIA192_WRAP;
    public static final String CAMELLIA256_WRAP;
    public static final String SEED_WRAP;
    public static final String ECDH_SHA1KDF;
    private static final String ENCRYPTED_CONTENT_TYPE = "application/pkcs7-mime; name=\"smime.p7m\"; smime-type=enveloped-data";
    private EnvelopedGenerator fact;
    private List recipients;

    static 
    {
        DES_EDE3_CBC = CMSEnvelopedDataGenerator.DES_EDE3_CBC;
        RC2_CBC = CMSEnvelopedDataGenerator.RC2_CBC;
        AES128_CBC = CMSEnvelopedDataGenerator.AES128_CBC;
        AES192_CBC = CMSEnvelopedDataGenerator.AES192_CBC;
        AES256_CBC = CMSEnvelopedDataGenerator.AES256_CBC;
        CAMELLIA128_CBC = CMSEnvelopedDataGenerator.CAMELLIA128_CBC;
        CAMELLIA192_CBC = CMSEnvelopedDataGenerator.CAMELLIA192_CBC;
        CAMELLIA256_CBC = CMSEnvelopedDataGenerator.CAMELLIA256_CBC;
        SEED_CBC = CMSEnvelopedDataGenerator.SEED_CBC;
        DES_EDE3_WRAP = CMSEnvelopedDataGenerator.DES_EDE3_WRAP;
        AES128_WRAP = CMSEnvelopedDataGenerator.AES128_WRAP;
        AES256_WRAP = CMSEnvelopedDataGenerator.AES256_WRAP;
        CAMELLIA128_WRAP = CMSEnvelopedDataGenerator.CAMELLIA128_WRAP;
        CAMELLIA192_WRAP = CMSEnvelopedDataGenerator.CAMELLIA192_WRAP;
        CAMELLIA256_WRAP = CMSEnvelopedDataGenerator.CAMELLIA256_WRAP;
        SEED_WRAP = CMSEnvelopedDataGenerator.SEED_WRAP;
        ECDH_SHA1KDF = CMSEnvelopedDataGenerator.ECDH_SHA1KDF;
        CommandMap.setDefaultCommandMap(addCommands(CommandMap.getDefaultCommandMap()));
    }



}
