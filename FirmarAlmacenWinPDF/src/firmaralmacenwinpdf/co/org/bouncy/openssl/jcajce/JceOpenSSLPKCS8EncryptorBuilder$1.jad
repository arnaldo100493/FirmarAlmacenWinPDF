// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JceOpenSSLPKCS8EncryptorBuilder.java

package co.org.bouncy.openssl.jcajce;

import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.operator.GenericKey;
import co.org.bouncy.operator.OutputEncryptor;
import co.org.bouncy.operator.jcajce.JceGenericKey;
import java.io.OutputStream;
import javax.crypto.CipherOutputStream;

// Referenced classes of package co.org.bouncy.openssl.jcajce:
//            JceOpenSSLPKCS8EncryptorBuilder

class JceOpenSSLPKCS8EncryptorBuilder$1
    implements OutputEncryptor
{

    public AlgorithmIdentifier getAlgorithmIdentifier()
    {
        return val$algID;
    }

    public OutputStream getOutputStream(OutputStream encOut)
    {
        return new CipherOutputStream(encOut, JceOpenSSLPKCS8EncryptorBuilder.access$000(JceOpenSSLPKCS8EncryptorBuilder.this));
    }

    public GenericKey getKey()
    {
        return new JceGenericKey(val$algID, JceOpenSSLPKCS8EncryptorBuilder.access$100(JceOpenSSLPKCS8EncryptorBuilder.this));
    }

    final AlgorithmIdentifier val$algID;
    final JceOpenSSLPKCS8EncryptorBuilder this$0;

    JceOpenSSLPKCS8EncryptorBuilder$1()
    {
        this$0 = final_jceopensslpkcs8encryptorbuilder;
        val$algID = AlgorithmIdentifier.this;
        super();
    }
}
