// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcePKCSPBEOutputEncryptorBuilder.java

package co.org.bouncy.pkcs.jcajce;

import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.crypto.PBEParametersGenerator;
import co.org.bouncy.operator.GenericKey;
import co.org.bouncy.operator.OutputEncryptor;
import java.io.OutputStream;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;

// Referenced classes of package co.org.bouncy.pkcs.jcajce:
//            JcePKCSPBEOutputEncryptorBuilder

class JcePKCSPBEOutputEncryptorBuilder$1
    implements OutputEncryptor
{

    public AlgorithmIdentifier getAlgorithmIdentifier()
    {
        return val$encryptionAlg;
    }

    public OutputStream getOutputStream(OutputStream out)
    {
        return new CipherOutputStream(out, val$cipher);
    }

    public GenericKey getKey()
    {
        if(JcePKCSPBEOutputEncryptorBuilder.access$000(JcePKCSPBEOutputEncryptorBuilder.this, val$encryptionAlg.getAlgorithm()))
            return new GenericKey(val$encryptionAlg, PBEParametersGenerator.PKCS5PasswordToBytes(val$password));
        else
            return new GenericKey(val$encryptionAlg, PBEParametersGenerator.PKCS12PasswordToBytes(val$password));
    }

    final AlgorithmIdentifier val$encryptionAlg;
    final Cipher val$cipher;
    final char val$password[];
    final JcePKCSPBEOutputEncryptorBuilder this$0;

    JcePKCSPBEOutputEncryptorBuilder$1()
    {
        this$0 = final_jcepkcspbeoutputencryptorbuilder;
        val$encryptionAlg = algorithmidentifier;
        val$cipher = cipher1;
        val$password = _5B_C.this;
        super();
    }
}
