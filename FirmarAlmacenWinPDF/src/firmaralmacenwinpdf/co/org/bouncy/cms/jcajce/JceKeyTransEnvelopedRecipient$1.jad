// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JceKeyTransEnvelopedRecipient.java

package co.org.bouncy.cms.jcajce;

import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.operator.InputDecryptor;
import java.io.InputStream;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;

// Referenced classes of package co.org.bouncy.cms.jcajce:
//            JceKeyTransEnvelopedRecipient

class JceKeyTransEnvelopedRecipient$1
    implements InputDecryptor
{

    public AlgorithmIdentifier getAlgorithmIdentifier()
    {
        return val$contentEncryptionAlgorithm;
    }

    public InputStream getInputStream(InputStream dataIn)
    {
        return new CipherInputStream(dataIn, val$dataCipher);
    }

    final AlgorithmIdentifier val$contentEncryptionAlgorithm;
    final Cipher val$dataCipher;
    final JceKeyTransEnvelopedRecipient this$0;

    JceKeyTransEnvelopedRecipient$1()
    {
        this$0 = final_jcekeytransenvelopedrecipient;
        val$contentEncryptionAlgorithm = algorithmidentifier;
        val$dataCipher = Cipher.this;
        super();
    }
}
