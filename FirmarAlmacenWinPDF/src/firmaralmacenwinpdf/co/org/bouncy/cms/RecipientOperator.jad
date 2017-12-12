// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RecipientOperator.java

package co.org.bouncy.cms;

import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.operator.InputDecryptor;
import co.org.bouncy.operator.MacCalculator;
import co.org.bouncy.util.io.TeeInputStream;
import java.io.InputStream;

public class RecipientOperator
{

    public RecipientOperator(InputDecryptor decryptor)
    {
        algorithmIdentifier = decryptor.getAlgorithmIdentifier();
        operator = decryptor;
    }

    public RecipientOperator(MacCalculator macCalculator)
    {
        algorithmIdentifier = macCalculator.getAlgorithmIdentifier();
        operator = macCalculator;
    }

    public InputStream getInputStream(InputStream dataIn)
    {
        if(operator instanceof InputDecryptor)
            return ((InputDecryptor)operator).getInputStream(dataIn);
        else
            return new TeeInputStream(dataIn, ((MacCalculator)operator).getOutputStream());
    }

    public boolean isMacBased()
    {
        return operator instanceof MacCalculator;
    }

    public byte[] getMac()
    {
        return ((MacCalculator)operator).getMac();
    }

    private final AlgorithmIdentifier algorithmIdentifier;
    private final Object operator;
}
