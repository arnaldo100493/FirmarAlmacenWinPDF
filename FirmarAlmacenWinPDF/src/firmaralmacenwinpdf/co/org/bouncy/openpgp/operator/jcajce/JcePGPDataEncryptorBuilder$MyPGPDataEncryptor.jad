// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcePGPDataEncryptorBuilder.java

package co.org.bouncy.openpgp.operator.jcajce;

import co.org.bouncy.openpgp.PGPException;
import co.org.bouncy.openpgp.operator.PGPDataEncryptor;
import co.org.bouncy.openpgp.operator.PGPDigestCalculator;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.IvParameterSpec;

// Referenced classes of package co.org.bouncy.openpgp.operator.jcajce:
//            SHA1PGPDigestCalculator, JcePGPDataEncryptorBuilder, OperatorHelper, PGPUtil

private class JcePGPDataEncryptorBuilder$MyPGPDataEncryptor
    implements PGPDataEncryptor
{

    public OutputStream getOutputStream(OutputStream out)
    {
        return new CipherOutputStream(out, c);
    }

    public PGPDigestCalculator getIntegrityCalculator()
    {
        if(JcePGPDataEncryptorBuilder.access$100(JcePGPDataEncryptorBuilder.this))
            return new SHA1PGPDigestCalculator();
        else
            return null;
    }

    public int getBlockSize()
    {
        return c.getBlockSize();
    }

    private final Cipher c;
    final JcePGPDataEncryptorBuilder this$0;

    JcePGPDataEncryptorBuilder$MyPGPDataEncryptor(byte keyBytes[])
        throws PGPException
    {
        this$0 = JcePGPDataEncryptorBuilder.this;
        super();
        c = JcePGPDataEncryptorBuilder.access$200(JcePGPDataEncryptorBuilder.this).createStreamCipher(JcePGPDataEncryptorBuilder.access$000(JcePGPDataEncryptorBuilder.this), JcePGPDataEncryptorBuilder.access$100(JcePGPDataEncryptorBuilder.this));
        byte iv[] = new byte[c.getBlockSize()];
        try
        {
            c.init(1, PGPUtil.makeSymmetricKey(JcePGPDataEncryptorBuilder.access$000(JcePGPDataEncryptorBuilder.this), keyBytes), new IvParameterSpec(iv));
        }
        catch(InvalidKeyException e)
        {
            throw new PGPException((new StringBuilder()).append("invalid key: ").append(e.getMessage()).toString(), e);
        }
        catch(InvalidAlgorithmParameterException e)
        {
            throw new PGPException((new StringBuilder()).append("imvalid algorithm parameter: ").append(e.getMessage()).toString(), e);
        }
    }
}
