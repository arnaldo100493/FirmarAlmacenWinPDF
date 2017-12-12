// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JceKEKAuthenticatedRecipient.java

package co.org.bouncy.cms.jcajce;

import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.cms.CMSException;
import co.org.bouncy.cms.RecipientOperator;
import co.org.bouncy.jcajce.io.MacOutputStream;
import co.org.bouncy.operator.GenericKey;
import co.org.bouncy.operator.MacCalculator;
import co.org.bouncy.operator.jcajce.JceGenericKey;
import java.io.OutputStream;
import java.security.Key;
import javax.crypto.Mac;
import javax.crypto.SecretKey;

// Referenced classes of package co.org.bouncy.cms.jcajce:
//            JceKEKRecipient, EnvelopedDataHelper

public class JceKEKAuthenticatedRecipient extends JceKEKRecipient
{

    public JceKEKAuthenticatedRecipient(SecretKey recipientKey)
    {
        super(recipientKey);
    }

    public RecipientOperator getRecipientOperator(AlgorithmIdentifier keyEncryptionAlgorithm, final AlgorithmIdentifier contentMacAlgorithm, byte encryptedContentEncryptionKey[])
        throws CMSException
    {
        final Key secretKey = extractSecretKey(keyEncryptionAlgorithm, contentMacAlgorithm, encryptedContentEncryptionKey);
        final Mac dataMac = contentHelper.createContentMac(secretKey, contentMacAlgorithm);
        return new RecipientOperator(new MacCalculator() {

            public AlgorithmIdentifier getAlgorithmIdentifier()
            {
                return contentMacAlgorithm;
            }

            public GenericKey getKey()
            {
                return new JceGenericKey(contentMacAlgorithm, secretKey);
            }

            public OutputStream getOutputStream()
            {
                return new MacOutputStream(dataMac);
            }

            public byte[] getMac()
            {
                return dataMac.doFinal();
            }

            final AlgorithmIdentifier val$contentMacAlgorithm;
            final Key val$secretKey;
            final Mac val$dataMac;
            final JceKEKAuthenticatedRecipient this$0;

            
            {
                this$0 = JceKEKAuthenticatedRecipient.this;
                contentMacAlgorithm = algorithmidentifier;
                secretKey = key;
                dataMac = mac;
                super();
            }
        }
);
    }
}
