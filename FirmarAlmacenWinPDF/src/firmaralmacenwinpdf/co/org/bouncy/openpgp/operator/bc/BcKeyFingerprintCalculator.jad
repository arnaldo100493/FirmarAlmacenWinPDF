// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BcKeyFingerprintCalculator.java

package co.org.bouncy.openpgp.operator.bc;

import co.org.bouncy.bcpg.*;
import co.org.bouncy.crypto.Digest;
import co.org.bouncy.crypto.digests.MD5Digest;
import co.org.bouncy.crypto.digests.SHA1Digest;
import co.org.bouncy.openpgp.PGPException;
import co.org.bouncy.openpgp.operator.KeyFingerPrintCalculator;
import java.io.IOException;

public class BcKeyFingerprintCalculator
    implements KeyFingerPrintCalculator
{

    public BcKeyFingerprintCalculator()
    {
    }

    public byte[] calculateFingerprint(PublicKeyPacket publicPk)
        throws PGPException
    {
        BCPGKey key = publicPk.getKey();
        Digest digest;
        if(publicPk.getVersion() <= 3)
        {
            RSAPublicBCPGKey rK = (RSAPublicBCPGKey)key;
            try
            {
                digest = new MD5Digest();
                byte bytes[] = (new MPInteger(rK.getModulus())).getEncoded();
                digest.update(bytes, 2, bytes.length - 2);
                bytes = (new MPInteger(rK.getPublicExponent())).getEncoded();
                digest.update(bytes, 2, bytes.length - 2);
            }
            catch(IOException e)
            {
                throw new PGPException((new StringBuilder()).append("can't encode key components: ").append(e.getMessage()).toString(), e);
            }
        } else
        {
            try
            {
                byte kBytes[] = publicPk.getEncodedContents();
                digest = new SHA1Digest();
                digest.update((byte)-103);
                digest.update((byte)(kBytes.length >> 8));
                digest.update((byte)kBytes.length);
                digest.update(kBytes, 0, kBytes.length);
            }
            catch(IOException e)
            {
                throw new PGPException((new StringBuilder()).append("can't encode key components: ").append(e.getMessage()).toString(), e);
            }
        }
        byte digBuf[] = new byte[digest.getDigestSize()];
        digest.doFinal(digBuf, 0);
        return digBuf;
    }
}
