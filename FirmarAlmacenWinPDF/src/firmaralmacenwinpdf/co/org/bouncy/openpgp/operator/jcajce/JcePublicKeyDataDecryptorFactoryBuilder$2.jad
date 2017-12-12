// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcePublicKeyDataDecryptorFactoryBuilder.java

package co.org.bouncy.openpgp.operator.jcajce;

import co.org.bouncy.openpgp.PGPException;
import co.org.bouncy.openpgp.PGPPrivateKey;
import co.org.bouncy.openpgp.operator.PGPDataDecryptor;
import co.org.bouncy.openpgp.operator.PublicKeyDataDecryptorFactory;
import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.openpgp.operator.jcajce:
//            JcePublicKeyDataDecryptorFactoryBuilder, JcaPGPKeyConverter, OperatorHelper

class JcePublicKeyDataDecryptorFactoryBuilder$2
    implements PublicKeyDataDecryptorFactory
{

    public byte[] recoverSessionData(int keyAlgorithm, BigInteger secKeyData[])
        throws PGPException
    {
        return JcePublicKeyDataDecryptorFactoryBuilder.access$000(JcePublicKeyDataDecryptorFactoryBuilder.this, keyAlgorithm, JcePublicKeyDataDecryptorFactoryBuilder.access$200(JcePublicKeyDataDecryptorFactoryBuilder.this).getPrivateKey(val$privKey), secKeyData);
    }

    public PGPDataDecryptor createDataDecryptor(boolean withIntegrityPacket, int encAlgorithm, byte key[])
        throws PGPException
    {
        return JcePublicKeyDataDecryptorFactoryBuilder.access$100(JcePublicKeyDataDecryptorFactoryBuilder.this).createDataDecryptor(withIntegrityPacket, encAlgorithm, key);
    }

    final PGPPrivateKey val$privKey;
    final JcePublicKeyDataDecryptorFactoryBuilder this$0;

    JcePublicKeyDataDecryptorFactoryBuilder$2()
    {
        this$0 = final_jcepublickeydatadecryptorfactorybuilder;
        val$privKey = PGPPrivateKey.this;
        super();
    }
}
