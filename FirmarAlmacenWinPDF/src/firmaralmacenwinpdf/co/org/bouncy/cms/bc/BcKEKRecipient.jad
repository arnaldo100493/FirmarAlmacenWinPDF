// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BcKEKRecipient.java

package co.org.bouncy.cms.bc;

import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.cms.CMSException;
import co.org.bouncy.cms.KEKRecipient;
import co.org.bouncy.crypto.CipherParameters;
import co.org.bouncy.operator.OperatorException;
import co.org.bouncy.operator.SymmetricKeyUnwrapper;
import co.org.bouncy.operator.bc.BcSymmetricKeyUnwrapper;

// Referenced classes of package co.org.bouncy.cms.bc:
//            CMSUtils

public abstract class BcKEKRecipient
    implements KEKRecipient
{

    public BcKEKRecipient(BcSymmetricKeyUnwrapper unwrapper)
    {
        this.unwrapper = unwrapper;
    }

    protected CipherParameters extractSecretKey(AlgorithmIdentifier keyEncryptionAlgorithm, AlgorithmIdentifier contentEncryptionAlgorithm, byte encryptedContentEncryptionKey[])
        throws CMSException
    {
        try
        {
            return CMSUtils.getBcKey(unwrapper.generateUnwrappedKey(contentEncryptionAlgorithm, encryptedContentEncryptionKey));
        }
        catch(OperatorException e)
        {
            throw new CMSException((new StringBuilder()).append("exception unwrapping key: ").append(e.getMessage()).toString(), e);
        }
    }

    private SymmetricKeyUnwrapper unwrapper;
}
