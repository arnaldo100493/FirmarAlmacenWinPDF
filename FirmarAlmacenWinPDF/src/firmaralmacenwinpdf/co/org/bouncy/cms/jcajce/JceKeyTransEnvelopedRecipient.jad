// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JceKeyTransEnvelopedRecipient.java

package co.org.bouncy.cms.jcajce;

import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.cms.CMSException;
import co.org.bouncy.cms.RecipientOperator;
import co.org.bouncy.operator.InputDecryptor;
import java.io.InputStream;
import java.security.PrivateKey;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;

// Referenced classes of package co.org.bouncy.cms.jcajce:
//            JceKeyTransRecipient, EnvelopedDataHelper

public class JceKeyTransEnvelopedRecipient extends JceKeyTransRecipient
{

    public JceKeyTransEnvelopedRecipient(PrivateKey recipientKey)
    {
        super(recipientKey);
    }

    public RecipientOperator getRecipientOperator(AlgorithmIdentifier keyEncryptionAlgorithm, final AlgorithmIdentifier contentEncryptionAlgorithm, byte encryptedContentEncryptionKey[])
        throws CMSException
    {
        java.security.Key secretKey = extractSecretKey(keyEncryptionAlgorithm, contentEncryptionAlgorithm, encryptedContentEncryptionKey);
        final Cipher dataCipher = contentHelper.createContentCipher(secretKey, contentEncryptionAlgorithm);
        return new RecipientOperator(new InputDecryptor() {

            public AlgorithmIdentifier getAlgorithmIdentifier()
            {
                return contentEncryptionAlgorithm;
            }

            public InputStream getInputStream(InputStream dataIn)
            {
                return new CipherInputStream(dataIn, dataCipher);
            }

            final AlgorithmIdentifier val$contentEncryptionAlgorithm;
            final Cipher val$dataCipher;
            final JceKeyTransEnvelopedRecipient this$0;

            
            {
                this$0 = JceKeyTransEnvelopedRecipient.this;
                contentEncryptionAlgorithm = algorithmidentifier;
                dataCipher = cipher;
                super();
            }
        }
);
    }
}
