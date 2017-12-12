// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JceKeyAgreeAuthenticatedRecipient.java

package co.org.bouncy.cms.jcajce;

import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.jcajce.io.MacOutputStream;
import co.org.bouncy.operator.GenericKey;
import co.org.bouncy.operator.MacCalculator;
import co.org.bouncy.operator.jcajce.JceGenericKey;
import java.io.OutputStream;
import java.security.Key;
import javax.crypto.Mac;

// Referenced classes of package co.org.bouncy.cms.jcajce:
//            JceKeyAgreeAuthenticatedRecipient

class JceKeyAgreeAuthenticatedRecipient$1
    implements MacCalculator
{

    public AlgorithmIdentifier getAlgorithmIdentifier()
    {
        return val$contentMacAlgorithm;
    }

    public GenericKey getKey()
    {
        return new JceGenericKey(val$contentMacAlgorithm, val$secretKey);
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
    final JceKeyAgreeAuthenticatedRecipient this$0;

    JceKeyAgreeAuthenticatedRecipient$1()
    {
        this$0 = final_jcekeyagreeauthenticatedrecipient;
        val$contentMacAlgorithm = algorithmidentifier;
        val$secretKey = key;
        val$dataMac = Mac.this;
        super();
    }
}
