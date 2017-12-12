// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JceKeyAgreeEnvelopedRecipient.java

package co.org.bouncy.cms.jcajce;

import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.operator.InputDecryptor;
import java.io.InputStream;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;

// Referenced classes of package co.org.bouncy.cms.jcajce:
//            JceKeyAgreeEnvelopedRecipient

class JceKeyAgreeEnvelopedRecipient$1
    implements InputDecryptor
{

    public AlgorithmIdentifier getAlgorithmIdentifier()
    {
        return val$contentEncryptionAlgorithm;
    }

    public InputStream getInputStream(InputStream dataOut)
    {
        return new CipherInputStream(dataOut, val$dataCipher);
    }

    final AlgorithmIdentifier val$contentEncryptionAlgorithm;
    final Cipher val$dataCipher;
    final JceKeyAgreeEnvelopedRecipient this$0;

    JceKeyAgreeEnvelopedRecipient$1()
    {
        this$0 = final_jcekeyagreeenvelopedrecipient;
        val$contentEncryptionAlgorithm = algorithmidentifier;
        val$dataCipher = Cipher.this;
        super();
    }
}
