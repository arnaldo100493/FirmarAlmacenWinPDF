// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BcPKCS12PBEOutputEncryptorBuilder.java

package co.org.bouncy.pkcs.bc;

import co.org.bouncy.asn1.pkcs.PKCS12PBEParams;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.crypto.generators.PKCS12ParametersGenerator;
import co.org.bouncy.crypto.io.CipherOutputStream;
import co.org.bouncy.operator.GenericKey;
import co.org.bouncy.operator.OutputEncryptor;
import java.io.OutputStream;

// Referenced classes of package co.org.bouncy.pkcs.bc:
//            BcPKCS12PBEOutputEncryptorBuilder

class BcPKCS12PBEOutputEncryptorBuilder$1
    implements OutputEncryptor
{

    public AlgorithmIdentifier getAlgorithmIdentifier()
    {
        return new AlgorithmIdentifier(BcPKCS12PBEOutputEncryptorBuilder.access$000(BcPKCS12PBEOutputEncryptorBuilder.this), val$pbeParams);
    }

    public OutputStream getOutputStream(OutputStream out)
    {
        return new CipherOutputStream(out, BcPKCS12PBEOutputEncryptorBuilder.access$100(BcPKCS12PBEOutputEncryptorBuilder.this));
    }

    public GenericKey getKey()
    {
        return new GenericKey(new AlgorithmIdentifier(BcPKCS12PBEOutputEncryptorBuilder.access$000(BcPKCS12PBEOutputEncryptorBuilder.this), val$pbeParams), PKCS12ParametersGenerator.PKCS12PasswordToBytes(val$password));
    }

    final PKCS12PBEParams val$pbeParams;
    final char val$password[];
    final BcPKCS12PBEOutputEncryptorBuilder this$0;

    BcPKCS12PBEOutputEncryptorBuilder$1()
    {
        this$0 = final_bcpkcs12pbeoutputencryptorbuilder;
        val$pbeParams = pkcs12pbeparams;
        val$password = _5B_C.this;
        super();
    }
}
