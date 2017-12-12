// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BcPKCS12PBEOutputEncryptorBuilder.java

package co.org.bouncy.pkcs.bc;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.pkcs.PKCS12PBEParams;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.digests.SHA1Digest;
import co.org.bouncy.crypto.generators.PKCS12ParametersGenerator;
import co.org.bouncy.crypto.io.CipherOutputStream;
import co.org.bouncy.crypto.paddings.PKCS7Padding;
import co.org.bouncy.crypto.paddings.PaddedBufferedBlockCipher;
import co.org.bouncy.operator.GenericKey;
import co.org.bouncy.operator.OutputEncryptor;
import java.io.OutputStream;
import java.security.SecureRandom;

// Referenced classes of package co.org.bouncy.pkcs.bc:
//            PKCS12PBEUtils

public class BcPKCS12PBEOutputEncryptorBuilder
{

    public BcPKCS12PBEOutputEncryptorBuilder(ASN1ObjectIdentifier algorithm, BlockCipher engine)
    {
        this(algorithm, engine, ((ExtendedDigest) (new SHA1Digest())));
    }

    public BcPKCS12PBEOutputEncryptorBuilder(ASN1ObjectIdentifier algorithm, BlockCipher engine, ExtendedDigest pbeDigest)
    {
        this.algorithm = algorithm;
        this.engine = new PaddedBufferedBlockCipher(engine, new PKCS7Padding());
        digest = pbeDigest;
    }

    public OutputEncryptor build(final char password[])
    {
        if(random == null)
            random = new SecureRandom();
        byte salt[] = new byte[20];
        int iterationCount = 1024;
        random.nextBytes(salt);
        final PKCS12PBEParams pbeParams = new PKCS12PBEParams(salt, 1024);
        co.org.bouncy.crypto.CipherParameters params = PKCS12PBEUtils.createCipherParameters(algorithm, digest, engine.getBlockSize(), pbeParams, password);
        engine.init(true, params);
        return new OutputEncryptor() {

            public AlgorithmIdentifier getAlgorithmIdentifier()
            {
                return new AlgorithmIdentifier(algorithm, pbeParams);
            }

            public OutputStream getOutputStream(OutputStream out)
            {
                return new CipherOutputStream(out, engine);
            }

            public GenericKey getKey()
            {
                return new GenericKey(new AlgorithmIdentifier(algorithm, pbeParams), PKCS12ParametersGenerator.PKCS12PasswordToBytes(password));
            }

            final PKCS12PBEParams val$pbeParams;
            final char val$password[];
            final BcPKCS12PBEOutputEncryptorBuilder this$0;

            
            {
                this$0 = BcPKCS12PBEOutputEncryptorBuilder.this;
                pbeParams = pkcs12pbeparams;
                password = ac;
                super();
            }
        }
;
    }

    private ExtendedDigest digest;
    private BufferedBlockCipher engine;
    private ASN1ObjectIdentifier algorithm;
    private SecureRandom random;


}
