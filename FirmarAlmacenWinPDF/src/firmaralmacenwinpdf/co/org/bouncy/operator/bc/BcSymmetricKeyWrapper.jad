// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BcSymmetricKeyWrapper.java

package co.org.bouncy.operator.bc;

import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.crypto.Wrapper;
import co.org.bouncy.crypto.params.KeyParameter;
import co.org.bouncy.crypto.params.ParametersWithRandom;
import co.org.bouncy.operator.*;
import java.security.SecureRandom;

// Referenced classes of package co.org.bouncy.operator.bc:
//            OperatorUtils

public class BcSymmetricKeyWrapper extends SymmetricKeyWrapper
{

    public BcSymmetricKeyWrapper(AlgorithmIdentifier wrappingAlgorithm, Wrapper wrapper, KeyParameter wrappingKey)
    {
        super(wrappingAlgorithm);
        this.wrapper = wrapper;
        this.wrappingKey = wrappingKey;
    }

    public BcSymmetricKeyWrapper setSecureRandom(SecureRandom random)
    {
        this.random = random;
        return this;
    }

    public byte[] generateWrappedKey(GenericKey encryptionKey)
        throws OperatorException
    {
        byte contentEncryptionKeySpec[] = OperatorUtils.getKeyBytes(encryptionKey);
        if(random == null)
            wrapper.init(true, wrappingKey);
        else
            wrapper.init(true, new ParametersWithRandom(wrappingKey, random));
        return wrapper.wrap(contentEncryptionKeySpec, 0, contentEncryptionKeySpec.length);
    }

    private SecureRandom random;
    private Wrapper wrapper;
    private KeyParameter wrappingKey;
}
