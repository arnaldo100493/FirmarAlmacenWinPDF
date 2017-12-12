// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcePublicKeyDataDecryptorFactoryBuilder.java

package co.org.bouncy.openpgp.operator.jcajce;

import co.org.bouncy.openpgp.PGPException;
import co.org.bouncy.openpgp.operator.PGPDataDecryptor;
import co.org.bouncy.openpgp.operator.PublicKeyDataDecryptorFactory;
import java.math.BigInteger;
import java.security.PrivateKey;

// Referenced classes of package co.org.bouncy.openpgp.operator.jcajce:
//            JcePublicKeyDataDecryptorFactoryBuilder, OperatorHelper

class JcePublicKeyDataDecryptorFactoryBuilder$1
    implements PublicKeyDataDecryptorFactory
{

    public byte[] recoverSessionData(int keyAlgorithm, BigInteger secKeyData[])
        throws PGPException
    {
        return JcePublicKeyDataDecryptorFactoryBuilder.access$000(JcePublicKeyDataDecryptorFactoryBuilder.this, keyAlgorithm, val$privKey, secKeyData);
    }

    public PGPDataDecryptor createDataDecryptor(boolean withIntegrityPacket, int encAlgorithm, byte key[])
        throws PGPException
    {
        return JcePublicKeyDataDecryptorFactoryBuilder.access$100(JcePublicKeyDataDecryptorFactoryBuilder.this).createDataDecryptor(withIntegrityPacket, encAlgorithm, key);
    }

    final PrivateKey val$privKey;
    final JcePublicKeyDataDecryptorFactoryBuilder this$0;

    JcePublicKeyDataDecryptorFactoryBuilder$1()
    {
        this$0 = final_jcepublickeydatadecryptorfactorybuilder;
        val$privKey = PrivateKey.this;
        super();
    }
}
