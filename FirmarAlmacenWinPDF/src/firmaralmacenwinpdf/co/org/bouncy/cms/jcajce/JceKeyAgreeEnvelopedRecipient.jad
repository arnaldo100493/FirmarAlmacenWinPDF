// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JceKeyAgreeEnvelopedRecipient.java

package co.org.bouncy.cms.jcajce;

import co.org.bouncy.asn1.ASN1OctetString;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import co.org.bouncy.cms.CMSException;
import co.org.bouncy.cms.RecipientOperator;
import co.org.bouncy.operator.InputDecryptor;
import java.io.InputStream;
import java.security.PrivateKey;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;

// Referenced classes of package co.org.bouncy.cms.jcajce:
//            JceKeyAgreeRecipient, EnvelopedDataHelper

public class JceKeyAgreeEnvelopedRecipient extends JceKeyAgreeRecipient
{

    public JceKeyAgreeEnvelopedRecipient(PrivateKey recipientKey)
    {
        super(recipientKey);
    }

    public RecipientOperator getRecipientOperator(AlgorithmIdentifier keyEncryptionAlgorithm, final AlgorithmIdentifier contentEncryptionAlgorithm, SubjectPublicKeyInfo senderPublicKey, ASN1OctetString userKeyingMaterial, byte encryptedContentKey[])
        throws CMSException
    {
        java.security.Key secretKey = extractSecretKey(keyEncryptionAlgorithm, contentEncryptionAlgorithm, senderPublicKey, userKeyingMaterial, encryptedContentKey);
        final Cipher dataCipher = contentHelper.createContentCipher(secretKey, contentEncryptionAlgorithm);
        return new RecipientOperator(new InputDecryptor() {

            public AlgorithmIdentifier getAlgorithmIdentifier()
            {
                return contentEncryptionAlgorithm;
            }

            public InputStream getInputStream(InputStream dataOut)
            {
                return new CipherInputStream(dataOut, dataCipher);
            }

            final AlgorithmIdentifier val$contentEncryptionAlgorithm;
            final Cipher val$dataCipher;
            final JceKeyAgreeEnvelopedRecipient this$0;

            
            {
                this$0 = JceKeyAgreeEnvelopedRecipient.this;
                contentEncryptionAlgorithm = algorithmidentifier;
                dataCipher = cipher;
                super();
            }
        }
);
    }
}
