// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcePasswordAuthenticatedRecipient.java

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

// Referenced classes of package co.org.bouncy.cms.jcajce:
//            JcePasswordRecipient, EnvelopedDataHelper

public class JcePasswordAuthenticatedRecipient extends JcePasswordRecipient
{

    public JcePasswordAuthenticatedRecipient(char password[])
    {
        super(password);
    }

    public RecipientOperator getRecipientOperator(AlgorithmIdentifier keyEncryptionAlgorithm, final AlgorithmIdentifier contentMacAlgorithm, byte derivedKey[], byte encryptedContentEncryptionKey[])
        throws CMSException
    {
        final Key secretKey = extractSecretKey(keyEncryptionAlgorithm, contentMacAlgorithm, derivedKey, encryptedContentEncryptionKey);
        final Mac dataMac = helper.createContentMac(secretKey, contentMacAlgorithm);
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
            final JcePasswordAuthenticatedRecipient this$0;

            
            {
                this$0 = JcePasswordAuthenticatedRecipient.this;
                contentMacAlgorithm = algorithmidentifier;
                secretKey = key;
                dataMac = mac;
                super();
            }
        }
);
    }
}
