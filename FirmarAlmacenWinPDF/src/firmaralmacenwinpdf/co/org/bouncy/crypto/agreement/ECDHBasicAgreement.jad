// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ECDHBasicAgreement.java

package co.org.bouncy.crypto.agreement;

import co.org.bouncy.crypto.BasicAgreement;
import co.org.bouncy.crypto.CipherParameters;
import co.org.bouncy.crypto.params.*;
import co.org.bouncy.math.ec.*;
import java.math.BigInteger;

public class ECDHBasicAgreement
    implements BasicAgreement
{

    public ECDHBasicAgreement()
    {
    }

    public void init(CipherParameters key)
    {
        this.key = (ECPrivateKeyParameters)key;
    }

    public int getFieldSize()
    {
        return (key.getParameters().getCurve().getFieldSize() + 7) / 8;
    }

    public BigInteger calculateAgreement(CipherParameters pubKey)
    {
        ECPublicKeyParameters pub = (ECPublicKeyParameters)pubKey;
        ECPoint P = pub.getQ().multiply(key.getD());
        return P.getX().toBigInteger();
    }

    private ECPrivateKeyParameters key;
}
