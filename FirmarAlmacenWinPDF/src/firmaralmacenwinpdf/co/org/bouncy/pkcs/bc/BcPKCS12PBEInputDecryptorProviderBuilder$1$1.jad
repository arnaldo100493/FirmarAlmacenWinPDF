// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BcPKCS12PBEInputDecryptorProviderBuilder.java

package co.org.bouncy.pkcs.bc;

import co.org.bouncy.asn1.pkcs.PKCS12PBEParams;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.crypto.generators.PKCS12ParametersGenerator;
import co.org.bouncy.crypto.io.CipherInputStream;
import co.org.bouncy.crypto.paddings.PaddedBufferedBlockCipher;
import co.org.bouncy.operator.*;
import java.io.InputStream;

// Referenced classes of package co.org.bouncy.pkcs.bc:
//            BcPKCS12PBEInputDecryptorProviderBuilder, PKCS12PBEUtils

class BcPKCS12PBEInputDecryptorProviderBuilder$1$1
    implements InputDecryptor
{

    public AlgorithmIdentifier getAlgorithmIdentifier()
    {
        return val$algorithmIdentifier;
    }

    public InputStream getInputStream(InputStream input)
    {
        return new CipherInputStream(input, val$engine);
    }

    public GenericKey getKey()
    {
        return new GenericKey(PKCS12ParametersGenerator.PKCS12PasswordToBytes(val$password));
    }

    final AlgorithmIdentifier val$algorithmIdentifier;
    final PaddedBufferedBlockCipher val$engine;
    final BcPKCS12PBEInputDecryptorProviderBuilder._cls1 this$1;

    BcPKCS12PBEInputDecryptorProviderBuilder$1$1()
    {
        this$1 = final__pcls1;
        val$algorithmIdentifier = algorithmidentifier;
        val$engine = PaddedBufferedBlockCipher.this;
        super();
    }

    // Unreferenced inner class co/org/bouncy/pkcs/bc/BcPKCS12PBEInputDecryptorProviderBuilder$1

/* anonymous class */
    class BcPKCS12PBEInputDecryptorProviderBuilder._cls1
        implements InputDecryptorProvider
    {

        public InputDecryptor get(final AlgorithmIdentifier algorithmIdentifier)
        {
            PaddedBufferedBlockCipher engine = PKCS12PBEUtils.getEngine(algorithmIdentifier.getAlgorithm());
            PKCS12PBEParams pbeParams = PKCS12PBEParams.getInstance(algorithmIdentifier.getParameters());
            co.org.bouncy.crypto.CipherParameters params = PKCS12PBEUtils.createCipherParameters(algorithmIdentifier.getAlgorithm(), BcPKCS12PBEInputDecryptorProviderBuilder.access$000(BcPKCS12PBEInputDecryptorProviderBuilder.this), engine.getBlockSize(), pbeParams, password);
            engine.init(false, params);
            return engine. new BcPKCS12PBEInputDecryptorProviderBuilder._cls1._cls1();
        }

        final char val$password[];
        final BcPKCS12PBEInputDecryptorProviderBuilder this$0;

            
            {
                this$0 = final_bcpkcs12pbeinputdecryptorproviderbuilder;
                password = _5B_C.this;
                super();
            }
    }

}
