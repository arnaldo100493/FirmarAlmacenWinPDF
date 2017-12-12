// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BcPasswordRecipient.java

package co.org.bouncy.cms.bc;

import co.org.bouncy.asn1.ASN1OctetString;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.cms.CMSException;
import co.org.bouncy.cms.PasswordRecipient;
import co.org.bouncy.crypto.InvalidCipherTextException;
import co.org.bouncy.crypto.Wrapper;
import co.org.bouncy.crypto.params.KeyParameter;
import co.org.bouncy.crypto.params.ParametersWithIV;

// Referenced classes of package co.org.bouncy.cms.bc:
//            EnvelopedDataHelper

public abstract class BcPasswordRecipient
    implements PasswordRecipient
{

    BcPasswordRecipient(char password[])
    {
        schemeID = 1;
        this.password = password;
    }

    public BcPasswordRecipient setPasswordConversionScheme(int schemeID)
    {
        this.schemeID = schemeID;
        return this;
    }

    protected KeyParameter extractSecretKey(AlgorithmIdentifier keyEncryptionAlgorithm, AlgorithmIdentifier contentEncryptionAlgorithm, byte derivedKey[], byte encryptedContentEncryptionKey[])
        throws CMSException
    {
        Wrapper keyEncryptionCipher = EnvelopedDataHelper.createRFC3211Wrapper(keyEncryptionAlgorithm.getAlgorithm());
        keyEncryptionCipher.init(false, new ParametersWithIV(new KeyParameter(derivedKey), ASN1OctetString.getInstance(keyEncryptionAlgorithm.getParameters()).getOctets()));
        try
        {
            return new KeyParameter(keyEncryptionCipher.unwrap(encryptedContentEncryptionKey, 0, encryptedContentEncryptionKey.length));
        }
        catch(InvalidCipherTextException e)
        {
            throw new CMSException((new StringBuilder()).append("unable to unwrap key: ").append(e.getMessage()).toString(), e);
        }
    }

    public int getPasswordConversionScheme()
    {
        return schemeID;
    }

    public char[] getPassword()
    {
        return password;
    }

    private int schemeID;
    private char password[];
}
