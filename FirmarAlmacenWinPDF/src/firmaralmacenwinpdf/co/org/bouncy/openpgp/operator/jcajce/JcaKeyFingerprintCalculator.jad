// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcaKeyFingerprintCalculator.java

package co.org.bouncy.openpgp.operator.jcajce;

import co.org.bouncy.bcpg.*;
import co.org.bouncy.openpgp.PGPException;
import co.org.bouncy.openpgp.operator.KeyFingerPrintCalculator;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class JcaKeyFingerprintCalculator
    implements KeyFingerPrintCalculator
{

    public JcaKeyFingerprintCalculator()
    {
    }

    public byte[] calculateFingerprint(PublicKeyPacket publicPk)
        throws PGPException
    {
        BCPGKey key = publicPk.getKey();
        if(publicPk.getVersion() <= 3)
        {
            RSAPublicBCPGKey rK = (RSAPublicBCPGKey)key;
            try
            {
                MessageDigest digest = MessageDigest.getInstance("MD5");
                byte bytes[] = (new MPInteger(rK.getModulus())).getEncoded();
                digest.update(bytes, 2, bytes.length - 2);
                bytes = (new MPInteger(rK.getPublicExponent())).getEncoded();
                digest.update(bytes, 2, bytes.length - 2);
                return digest.digest();
            }
            catch(NoSuchAlgorithmException e)
            {
                throw new PGPException("can't find MD5", e);
            }
            catch(IOException e)
            {
                throw new PGPException((new StringBuilder()).append("can't encode key components: ").append(e.getMessage()).toString(), e);
            }
        }
        try
        {
            byte kBytes[] = publicPk.getEncodedContents();
            MessageDigest digest = MessageDigest.getInstance("SHA1");
            digest.update((byte)-103);
            digest.update((byte)(kBytes.length >> 8));
            digest.update((byte)kBytes.length);
            digest.update(kBytes);
            return digest.digest();
        }
        catch(NoSuchAlgorithmException e)
        {
            throw new PGPException("can't find SHA1", e);
        }
        catch(IOException e)
        {
            throw new PGPException((new StringBuilder()).append("can't encode key components: ").append(e.getMessage()).toString(), e);
        }
    }
}
