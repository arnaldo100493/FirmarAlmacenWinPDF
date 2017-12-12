// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BcRSAKeyTransEnvelopedRecipient.java

package co.org.bouncy.cms.bc;

import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.crypto.BufferedBlockCipher;
import co.org.bouncy.crypto.StreamCipher;
import co.org.bouncy.crypto.io.CipherInputStream;
import co.org.bouncy.operator.InputDecryptor;
import java.io.InputStream;

// Referenced classes of package co.org.bouncy.cms.bc:
//            BcRSAKeyTransEnvelopedRecipient

class BcRSAKeyTransEnvelopedRecipient$1
    implements InputDecryptor
{

    public AlgorithmIdentifier getAlgorithmIdentifier()
    {
        return val$contentEncryptionAlgorithm;
    }

    public InputStream getInputStream(InputStream dataIn)
    {
        if(val$dataCipher instanceof BufferedBlockCipher)
            return new CipherInputStream(dataIn, (BufferedBlockCipher)val$dataCipher);
        else
            return new CipherInputStream(dataIn, (StreamCipher)val$dataCipher);
    }

    final AlgorithmIdentifier val$contentEncryptionAlgorithm;
    final Object val$dataCipher;
    final BcRSAKeyTransEnvelopedRecipient this$0;

    BcRSAKeyTransEnvelopedRecipient$1()
    {
        this$0 = final_bcrsakeytransenvelopedrecipient;
        val$contentEncryptionAlgorithm = algorithmidentifier;
        val$dataCipher = Object.this;
        super();
    }
}
