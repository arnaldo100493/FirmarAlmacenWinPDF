// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ECNewRandomnessTransform.java

package co.org.bouncy.crypto.ec;

import co.org.bouncy.crypto.CipherParameters;
import co.org.bouncy.crypto.params.*;
import co.org.bouncy.math.ec.ECPoint;
import java.security.SecureRandom;

// Referenced classes of package co.org.bouncy.crypto.ec:
//            ECPair, ECPairTransform, ECUtil

public class ECNewRandomnessTransform
    implements ECPairTransform
{

    public ECNewRandomnessTransform()
    {
    }

    public void init(CipherParameters param)
    {
        if(param instanceof ParametersWithRandom)
        {
            ParametersWithRandom p = (ParametersWithRandom)param;
            if(!(p.getParameters() instanceof ECPublicKeyParameters))
                throw new IllegalArgumentException("ECPublicKeyParameters are required for new randomness transform.");
            key = (ECPublicKeyParameters)p.getParameters();
            random = p.getRandom();
        } else
        {
            if(!(param instanceof ECPublicKeyParameters))
                throw new IllegalArgumentException("ECPublicKeyParameters are required for new randomness transform.");
            key = (ECPublicKeyParameters)param;
            random = new SecureRandom();
        }
    }

    public ECPair transform(ECPair cipherText)
    {
        if(key == null)
        {
            throw new IllegalStateException("ECNewRandomnessTransform not initialised");
        } else
        {
            java.math.BigInteger n = key.getParameters().getN();
            java.math.BigInteger k = ECUtil.generateK(n, random);
            ECPoint g = key.getParameters().getG();
            ECPoint gamma = g.multiply(k);
            ECPoint phi = key.getQ().multiply(k).add(cipherText.getY());
            return new ECPair(cipherText.getX().add(gamma), phi);
        }
    }

    private ECPublicKeyParameters key;
    private SecureRandom random;
}
