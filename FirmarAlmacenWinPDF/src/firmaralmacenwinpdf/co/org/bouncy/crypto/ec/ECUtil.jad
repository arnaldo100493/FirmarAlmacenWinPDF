// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ECUtil.java

package co.org.bouncy.crypto.ec;

import co.org.bouncy.math.ec.ECConstants;
import java.math.BigInteger;
import java.security.SecureRandom;

class ECUtil
{

    ECUtil()
    {
    }

    static BigInteger generateK(BigInteger n, SecureRandom random)
    {
        int nBitLength = n.bitLength();
        BigInteger k;
        for(k = new BigInteger(nBitLength, random); k.equals(ECConstants.ZERO) || k.compareTo(n) >= 0; k = new BigInteger(nBitLength, random));
        return k;
    }
}
