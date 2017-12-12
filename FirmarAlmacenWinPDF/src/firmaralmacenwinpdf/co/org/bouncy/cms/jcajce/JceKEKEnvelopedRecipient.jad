// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JceKEKEnvelopedRecipient.java

package co.org.bouncy.cms.jcajce;

import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.cms.CMSException;
import co.org.bouncy.cms.RecipientOperator;
import co.org.bouncy.operator.InputDecryptor;
import java.io.InputStream;
import javax.crypto.*;

// Referenced classes of package co.org.bouncy.cms.jcajce:
//            JceKEKRecipient, EnvelopedDataHelper

public class JceKEKEnvelopedRecipient extends JceKEKRecipient
{

    public JceKEKEnvelopedRecipient(SecretKey recipientKey)
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

            public InputStream getInputStream(InputStream dataOut)
            {
                return new CipherInputStream(dataOut, dataCipher);
            }

            final AlgorithmIdentifier val$contentEncryptionAlgorithm;
            final Cipher val$dataCipher;
            final JceKEKEnvelopedRecipient this$0;

            
            {
                this$0 = JceKEKEnvelopedRecipient.this;
                contentEncryptionAlgorithm = algorithmidentifier;
                dataCipher = cipher;
                super();
            }
        }
);
    }
}
