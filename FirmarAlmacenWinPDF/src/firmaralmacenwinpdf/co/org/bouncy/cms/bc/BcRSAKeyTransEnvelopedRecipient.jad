// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BcRSAKeyTransEnvelopedRecipient.java

package co.org.bouncy.cms.bc;

import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.cms.CMSException;
import co.org.bouncy.cms.RecipientOperator;
import co.org.bouncy.crypto.BufferedBlockCipher;
import co.org.bouncy.crypto.StreamCipher;
import co.org.bouncy.crypto.io.CipherInputStream;
import co.org.bouncy.crypto.params.AsymmetricKeyParameter;
import co.org.bouncy.operator.InputDecryptor;
import java.io.InputStream;

// Referenced classes of package co.org.bouncy.cms.bc:
//            BcKeyTransRecipient, EnvelopedDataHelper

public class BcRSAKeyTransEnvelopedRecipient extends BcKeyTransRecipient
{

    public BcRSAKeyTransEnvelopedRecipient(AsymmetricKeyParameter key)
    {
        super(key);
    }

    public RecipientOperator getRecipientOperator(AlgorithmIdentifier keyEncryptionAlgorithm, final AlgorithmIdentifier contentEncryptionAlgorithm, byte encryptedContentEncryptionKey[])
        throws CMSException
    {
        co.org.bouncy.crypto.CipherParameters secretKey = extractSecretKey(keyEncryptionAlgorithm, contentEncryptionAlgorithm, encryptedContentEncryptionKey);
        final Object dataCipher = EnvelopedDataHelper.createContentCipher(false, secretKey, contentEncryptionAlgorithm);
        return new RecipientOperator(new InputDecryptor() {

            public AlgorithmIdentifier getAlgorithmIdentifier()
            {
                return contentEncryptionAlgorithm;
            }

            public InputStream getInputStream(InputStream dataIn)
            {
                if(dataCipher instanceof BufferedBlockCipher)
                    return new CipherInputStream(dataIn, (BufferedBlockCipher)dataCipher);
                else
                    return new CipherInputStream(dataIn, (StreamCipher)dataCipher);
            }

            final AlgorithmIdentifier val$contentEncryptionAlgorithm;
            final Object val$dataCipher;
            final BcRSAKeyTransEnvelopedRecipient this$0;

            
            {
                this$0 = BcRSAKeyTransEnvelopedRecipient.this;
                contentEncryptionAlgorithm = algorithmidentifier;
                dataCipher = obj;
                super();
            }
        }
);
    }
}
