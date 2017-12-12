// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BcKeyTransRecipient.java

package co.org.bouncy.cms.bc;

import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.cms.CMSException;
import co.org.bouncy.cms.KeyTransRecipient;
import co.org.bouncy.crypto.CipherParameters;
import co.org.bouncy.crypto.params.AsymmetricKeyParameter;
import co.org.bouncy.operator.AsymmetricKeyUnwrapper;
import co.org.bouncy.operator.OperatorException;
import co.org.bouncy.operator.bc.BcRSAAsymmetricKeyUnwrapper;

// Referenced classes of package co.org.bouncy.cms.bc:
//            CMSUtils

public abstract class BcKeyTransRecipient
    implements KeyTransRecipient
{

    public BcKeyTransRecipient(AsymmetricKeyParameter recipientKey)
    {
        this.recipientKey = recipientKey;
    }

    protected CipherParameters extractSecretKey(AlgorithmIdentifier keyEncryptionAlgorithm, AlgorithmIdentifier encryptedKeyAlgorithm, byte encryptedEncryptionKey[])
        throws CMSException
    {
        AsymmetricKeyUnwrapper unwrapper = new BcRSAAsymmetricKeyUnwrapper(keyEncryptionAlgorithm, recipientKey);
        try
        {
            return CMSUtils.getBcKey(unwrapper.generateUnwrappedKey(encryptedKeyAlgorithm, encryptedEncryptionKey));
        }
        catch(OperatorException e)
        {
            throw new CMSException((new StringBuilder()).append("exception unwrapping key: ").append(e.getMessage()).toString(), e);
        }
    }

    private AsymmetricKeyParameter recipientKey;
}
