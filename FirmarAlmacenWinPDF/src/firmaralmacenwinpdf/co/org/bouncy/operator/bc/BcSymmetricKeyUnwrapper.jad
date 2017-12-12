// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BcSymmetricKeyUnwrapper.java

package co.org.bouncy.operator.bc;

import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.crypto.InvalidCipherTextException;
import co.org.bouncy.crypto.Wrapper;
import co.org.bouncy.crypto.params.KeyParameter;
import co.org.bouncy.operator.*;
import java.security.SecureRandom;

public class BcSymmetricKeyUnwrapper extends SymmetricKeyUnwrapper
{

    public BcSymmetricKeyUnwrapper(AlgorithmIdentifier wrappingAlgorithm, Wrapper wrapper, KeyParameter wrappingKey)
    {
        super(wrappingAlgorithm);
        this.wrapper = wrapper;
        this.wrappingKey = wrappingKey;
    }

    public BcSymmetricKeyUnwrapper setSecureRandom(SecureRandom random)
    {
        this.random = random;
        return this;
    }

    public GenericKey generateUnwrappedKey(AlgorithmIdentifier encryptedKeyAlgorithm, byte encryptedKey[])
        throws OperatorException
    {
        wrapper.init(false, wrappingKey);
        try
        {
            return new GenericKey(encryptedKeyAlgorithm, wrapper.unwrap(encryptedKey, 0, encryptedKey.length));
        }
        catch(InvalidCipherTextException e)
        {
            throw new OperatorException((new StringBuilder()).append("unable to unwrap key: ").append(e.getMessage()).toString(), e);
        }
    }

    private SecureRandom random;
    private Wrapper wrapper;
    private KeyParameter wrappingKey;
}
