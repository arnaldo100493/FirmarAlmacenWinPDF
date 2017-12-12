// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JceKeyTransAuthenticatedRecipient.java

package co.org.bouncy.cms.jcajce;

import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.jcajce.io.MacOutputStream;
import co.org.bouncy.operator.GenericKey;
import co.org.bouncy.operator.MacCalculator;
import java.io.OutputStream;
import java.security.Key;
import javax.crypto.Mac;

// Referenced classes of package co.org.bouncy.cms.jcajce:
//            JceKeyTransAuthenticatedRecipient

class JceKeyTransAuthenticatedRecipient$1
    implements MacCalculator
{

    public AlgorithmIdentifier getAlgorithmIdentifier()
    {
        return val$contentMacAlgorithm;
    }

    public GenericKey getKey()
    {
        return new GenericKey(val$secretKey);
    }

    public OutputStream getOutputStream()
    {
        return new MacOutputStream(val$dataMac);
    }

    public byte[] getMac()
    {
        return val$dataMac.doFinal();
    }

    final AlgorithmIdentifier val$contentMacAlgorithm;
    final Key val$secretKey;
    final Mac val$dataMac;
    final JceKeyTransAuthenticatedRecipient this$0;

    JceKeyTransAuthenticatedRecipient$1()
    {
        this$0 = final_jcekeytransauthenticatedrecipient;
        val$contentMacAlgorithm = algorithmidentifier;
        val$secretKey = key;
        val$dataMac = Mac.this;
        super();
    }
}
