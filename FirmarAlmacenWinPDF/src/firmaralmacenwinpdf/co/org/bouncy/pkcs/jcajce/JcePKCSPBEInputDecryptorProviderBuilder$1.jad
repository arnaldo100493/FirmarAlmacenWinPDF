// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcePKCSPBEInputDecryptorProviderBuilder.java

package co.org.bouncy.pkcs.jcajce;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.ASN1OctetString;
import co.org.bouncy.asn1.pkcs.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.jcajce.JcaJceHelper;
import co.org.bouncy.jcajce.provider.symmetric.util.BCPBEKey;
import co.org.bouncy.operator.*;
import co.org.bouncy.operator.jcajce.JceGenericKey;
import java.io.InputStream;
import java.math.BigInteger;
import javax.crypto.*;
import javax.crypto.spec.*;

// Referenced classes of package co.org.bouncy.pkcs.jcajce:
//            JcePKCSPBEInputDecryptorProviderBuilder

class JcePKCSPBEInputDecryptorProviderBuilder$1
    implements InputDecryptorProvider
{

    public InputDecryptor get(AlgorithmIdentifier algorithmIdentifier)
        throws OperatorCreationException
    {
        ASN1ObjectIdentifier algorithm = algorithmIdentifier.getAlgorithm();
        try
        {
            if(algorithm.on(PKCSObjectIdentifiers.pkcs_12PbeIds))
            {
                PKCS12PBEParams pbeParams = PKCS12PBEParams.getInstance(algorithmIdentifier.getParameters());
                PBEKeySpec pbeSpec = new PBEKeySpec(val$password);
                SecretKeyFactory keyFact = JcePKCSPBEInputDecryptorProviderBuilder.access$000(JcePKCSPBEInputDecryptorProviderBuilder.this).createSecretKeyFactory(algorithm.getId());
                PBEParameterSpec defParams = new PBEParameterSpec(pbeParams.getIV(), pbeParams.getIterations().intValue());
                key = keyFact.generateSecret(pbeSpec);
                if(key instanceof BCPBEKey)
                    ((BCPBEKey)key).setTryWrongPKCS12Zero(JcePKCSPBEInputDecryptorProviderBuilder.access$100(JcePKCSPBEInputDecryptorProviderBuilder.this));
                cipher = JcePKCSPBEInputDecryptorProviderBuilder.access$000(JcePKCSPBEInputDecryptorProviderBuilder.this).createCipher(algorithm.getId());
                cipher.init(2, key, defParams);
                encryptionAlg = algorithmIdentifier;
            } else
            if(algorithm.equals(PKCSObjectIdentifiers.id_PBES2))
            {
                PBES2Parameters alg = PBES2Parameters.getInstance(algorithmIdentifier.getParameters());
                PBKDF2Params func = PBKDF2Params.getInstance(alg.getKeyDerivationFunc().getParameters());
                AlgorithmIdentifier encScheme = AlgorithmIdentifier.getInstance(alg.getEncryptionScheme());
                SecretKeyFactory keyFact = JcePKCSPBEInputDecryptorProviderBuilder.access$000(JcePKCSPBEInputDecryptorProviderBuilder.this).createSecretKeyFactory(alg.getKeyDerivationFunc().getAlgorithm().getId());
                key = keyFact.generateSecret(new PBEKeySpec(val$password, func.getSalt(), func.getIterationCount().intValue(), JcePKCSPBEInputDecryptorProviderBuilder.access$200(JcePKCSPBEInputDecryptorProviderBuilder.this).getKeySize(encScheme)));
                cipher = JcePKCSPBEInputDecryptorProviderBuilder.access$000(JcePKCSPBEInputDecryptorProviderBuilder.this).createCipher(alg.getEncryptionScheme().getAlgorithm().getId());
                encryptionAlg = AlgorithmIdentifier.getInstance(alg.getEncryptionScheme());
                cipher.init(2, key, new IvParameterSpec(ASN1OctetString.getInstance(alg.getEncryptionScheme().getParameters()).getOctets()));
            }
        }
        catch(Exception e)
        {
            throw new OperatorCreationException((new StringBuilder()).append("unable to create InputDecryptor: ").append(e.getMessage()).toString(), e);
        }
        return new InputDecryptor() {

            public AlgorithmIdentifier getAlgorithmIdentifier()
            {
                return encryptionAlg;
            }

            public InputStream getInputStream(InputStream input)
            {
                return new CipherInputStream(input, cipher);
            }

            public GenericKey getKey()
            {
                return new JceGenericKey(encryptionAlg, key);
            }

            final JcePKCSPBEInputDecryptorProviderBuilder._cls1 this$1;

            
            {
                this$1 = JcePKCSPBEInputDecryptorProviderBuilder._cls1.this;
                super();
            }
        }
;
    }

    private Cipher cipher;
    private SecretKey key;
    private AlgorithmIdentifier encryptionAlg;
    final char val$password[];
    final JcePKCSPBEInputDecryptorProviderBuilder this$0;




    JcePKCSPBEInputDecryptorProviderBuilder$1()
    {
        this$0 = final_jcepkcspbeinputdecryptorproviderbuilder;
        val$password = _5B_C.this;
        super();
    }
}
