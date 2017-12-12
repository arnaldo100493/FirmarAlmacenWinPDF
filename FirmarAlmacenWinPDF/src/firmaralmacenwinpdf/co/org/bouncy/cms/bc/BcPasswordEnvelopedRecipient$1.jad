// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BcPasswordEnvelopedRecipient.java

package co.org.bouncy.cms.bc;

import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.crypto.BufferedBlockCipher;
import co.org.bouncy.crypto.StreamCipher;
import co.org.bouncy.crypto.io.CipherInputStream;
import co.org.bouncy.operator.InputDecryptor;
import java.io.InputStream;

// Referenced classes of package co.org.bouncy.cms.bc:
//            BcPasswordEnvelopedRecipient

class BcPasswordEnvelopedRecipient$1
    implements InputDecryptor
{

    public AlgorithmIdentifier getAlgorithmIdentifier()
    {
        return val$contentEncryptionAlgorithm;
    }

    public InputStream getInputStream(InputStream dataOut)
    {
        if(val$dataCipher instanceof BufferedBlockCipher)
            return new CipherInputStream(dataOut, (BufferedBlockCipher)val$dataCipher);
        else
            return new CipherInputStream(dataOut, (StreamCipher)val$dataCipher);
    }

    final AlgorithmIdentifier val$contentEncryptionAlgorithm;
    final Object val$dataCipher;
    final BcPasswordEnvelopedRecipient this$0;

    BcPasswordEnvelopedRecipient$1()
    {
        this$0 = final_bcpasswordenvelopedrecipient;
        val$contentEncryptionAlgorithm = algorithmidentifier;
        val$dataCipher = Object.this;
        super();
    }
}
