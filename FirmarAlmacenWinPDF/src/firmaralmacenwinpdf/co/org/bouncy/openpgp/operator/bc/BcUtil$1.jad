// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BcUtil.java

package co.org.bouncy.openpgp.operator.bc;

import co.org.bouncy.crypto.BufferedBlockCipher;
import co.org.bouncy.crypto.io.CipherInputStream;
import co.org.bouncy.openpgp.operator.PGPDataDecryptor;
import co.org.bouncy.openpgp.operator.PGPDigestCalculator;
import java.io.InputStream;

// Referenced classes of package co.org.bouncy.openpgp.operator.bc:
//            SHA1PGPDigestCalculator, BcUtil

static class BcUtil$1
    implements PGPDataDecryptor
{

    public InputStream getInputStream(InputStream in)
    {
        return new CipherInputStream(in, val$c);
    }

    public int getBlockSize()
    {
        return val$c.getBlockSize();
    }

    public PGPDigestCalculator getIntegrityCalculator()
    {
        return new SHA1PGPDigestCalculator();
    }

    final BufferedBlockCipher val$c;

    BcUtil$1(BufferedBlockCipher bufferedblockcipher)
    {
        val$c = bufferedblockcipher;
        super();
    }
}
