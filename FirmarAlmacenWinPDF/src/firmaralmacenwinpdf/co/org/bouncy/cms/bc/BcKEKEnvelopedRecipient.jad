// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BcKEKEnvelopedRecipient.java

package co.org.bouncy.cms.bc;

import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.cms.CMSException;
import co.org.bouncy.cms.RecipientOperator;
import co.org.bouncy.crypto.BufferedBlockCipher;
import co.org.bouncy.crypto.StreamCipher;
import co.org.bouncy.crypto.io.CipherInputStream;
import co.org.bouncy.crypto.params.KeyParameter;
import co.org.bouncy.operator.InputDecryptor;
import co.org.bouncy.operator.bc.BcSymmetricKeyUnwrapper;
import java.io.InputStream;

// Referenced classes of package co.org.bouncy.cms.bc:
//            BcKEKRecipient, EnvelopedDataHelper

public class BcKEKEnvelopedRecipient extends BcKEKRecipient
{

    public BcKEKEnvelopedRecipient(BcSymmetricKeyUnwrapper unwrapper)
    {
        super(unwrapper);
    }

    public RecipientOperator getRecipientOperator(AlgorithmIdentifier keyEncryptionAlgorithm, final AlgorithmIdentifier contentEncryptionAlgorithm, byte encryptedContentEncryptionKey[])
        throws CMSException
    {
        KeyParameter secretKey = (KeyParameter)extractSecretKey(keyEncryptionAlgorithm, contentEncryptionAlgorithm, encryptedContentEncryptionKey);
        final Object dataCipher = EnvelopedDataHelper.createContentCipher(false, secretKey, contentEncryptionAlgorithm);
        return new RecipientOperator(new InputDecryptor() {

            public AlgorithmIdentifier getAlgorithmIdentifier()
            {
                return contentEncryptionAlgorithm;
            }

            public InputStream getInputStream(InputStream dataOut)
            {
                if(dataCipher instanceof BufferedBlockCipher)
                    return new CipherInputStream(dataOut, (BufferedBlockCipher)dataCipher);
                else
                    return new CipherInputStream(dataOut, (StreamCipher)dataCipher);
            }

            final AlgorithmIdentifier val$contentEncryptionAlgorithm;
            final Object val$dataCipher;
            final BcKEKEnvelopedRecipient this$0;

            
            {
                this$0 = BcKEKEnvelopedRecipient.this;
                contentEncryptionAlgorithm = algorithmidentifier;
                dataCipher = obj;
                super();
            }
        }
);
    }
}
