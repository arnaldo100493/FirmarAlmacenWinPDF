// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BcUtil.java

package co.org.bouncy.openpgp.operator.bc;

import co.org.bouncy.crypto.BlockCipher;
import co.org.bouncy.crypto.BufferedBlockCipher;
import co.org.bouncy.crypto.io.CipherInputStream;
import co.org.bouncy.crypto.modes.CFBBlockCipher;
import co.org.bouncy.crypto.modes.OpenPGPCFBBlockCipher;
import co.org.bouncy.crypto.params.KeyParameter;
import co.org.bouncy.crypto.params.ParametersWithIV;
import co.org.bouncy.openpgp.operator.PGPDataDecryptor;
import co.org.bouncy.openpgp.operator.PGPDigestCalculator;
import java.io.InputStream;

// Referenced classes of package co.org.bouncy.openpgp.operator.bc:
//            SHA1PGPDigestCalculator

class BcUtil
{

    BcUtil()
    {
    }

    static BufferedBlockCipher createStreamCipher(boolean forEncryption, BlockCipher engine, boolean withIntegrityPacket, byte key[])
    {
        BufferedBlockCipher c;
        if(withIntegrityPacket)
            c = new BufferedBlockCipher(new CFBBlockCipher(engine, engine.getBlockSize() * 8));
        else
            c = new BufferedBlockCipher(new OpenPGPCFBBlockCipher(engine));
        KeyParameter keyParameter = new KeyParameter(key);
        if(withIntegrityPacket)
            c.init(forEncryption, new ParametersWithIV(keyParameter, new byte[engine.getBlockSize()]));
        else
            c.init(forEncryption, keyParameter);
        return c;
    }

    public static PGPDataDecryptor createDataDecryptor(boolean withIntegrityPacket, BlockCipher engine, byte key[])
    {
        BufferedBlockCipher c = createStreamCipher(false, engine, withIntegrityPacket, key);
        return new PGPDataDecryptor(c) {

            public InputStream getInputStream(InputStream in)
            {
                return new CipherInputStream(in, c);
            }

            public int getBlockSize()
            {
                return c.getBlockSize();
            }

            public PGPDigestCalculator getIntegrityCalculator()
            {
                return new SHA1PGPDigestCalculator();
            }

            final BufferedBlockCipher val$c;

            
            {
                c = bufferedblockcipher;
                super();
            }
        }
;
    }

    public static BufferedBlockCipher createSymmetricKeyWrapper(boolean forEncryption, BlockCipher engine, byte key[], byte iv[])
    {
        BufferedBlockCipher c = new BufferedBlockCipher(new CFBBlockCipher(engine, engine.getBlockSize() * 8));
        c.init(forEncryption, new ParametersWithIV(new KeyParameter(key), iv));
        return c;
    }
}
