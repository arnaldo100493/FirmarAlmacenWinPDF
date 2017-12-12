// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcePBEKeyEncryptionMethodGenerator.java

package co.org.bouncy.openpgp.operator.jcajce;

import co.org.bouncy.jcajce.*;
import co.org.bouncy.openpgp.PGPException;
import co.org.bouncy.openpgp.operator.PBEKeyEncryptionMethodGenerator;
import co.org.bouncy.openpgp.operator.PGPDigestCalculator;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

// Referenced classes of package co.org.bouncy.openpgp.operator.jcajce:
//            OperatorHelper, SHA1PGPDigestCalculator, PGPUtil

public class JcePBEKeyEncryptionMethodGenerator extends PBEKeyEncryptionMethodGenerator
{

    public JcePBEKeyEncryptionMethodGenerator(char passPhrase[], PGPDigestCalculator s2kDigestCalculator)
    {
        super(passPhrase, s2kDigestCalculator);
        helper = new OperatorHelper(new DefaultJcaJceHelper());
    }

    public JcePBEKeyEncryptionMethodGenerator(char passPhrase[])
    {
        this(passPhrase, ((PGPDigestCalculator) (new SHA1PGPDigestCalculator())));
    }

    public JcePBEKeyEncryptionMethodGenerator(char passPhrase[], PGPDigestCalculator s2kDigestCalculator, int s2kCount)
    {
        super(passPhrase, s2kDigestCalculator, s2kCount);
        helper = new OperatorHelper(new DefaultJcaJceHelper());
    }

    public JcePBEKeyEncryptionMethodGenerator(char passPhrase[], int s2kCount)
    {
        super(passPhrase, new SHA1PGPDigestCalculator(), s2kCount);
        helper = new OperatorHelper(new DefaultJcaJceHelper());
    }

    public JcePBEKeyEncryptionMethodGenerator setProvider(Provider provider)
    {
        helper = new OperatorHelper(new ProviderJcaJceHelper(provider));
        return this;
    }

    public JcePBEKeyEncryptionMethodGenerator setProvider(String providerName)
    {
        helper = new OperatorHelper(new NamedJcaJceHelper(providerName));
        return this;
    }

    public PBEKeyEncryptionMethodGenerator setSecureRandom(SecureRandom random)
    {
        super.setSecureRandom(random);
        return this;
    }

    protected byte[] encryptSessionInfo(int encAlgorithm, byte key[], byte sessionInfo[])
        throws PGPException
    {
        try
        {
            String cName = PGPUtil.getSymmetricCipherName(encAlgorithm);
            Cipher c = helper.createCipher((new StringBuilder()).append(cName).append("/CFB/NoPadding").toString());
            javax.crypto.SecretKey sKey = new SecretKeySpec(key, PGPUtil.getSymmetricCipherName(encAlgorithm));
            c.init(1, sKey, new IvParameterSpec(new byte[c.getBlockSize()]));
            return c.doFinal(sessionInfo, 0, sessionInfo.length);
        }
        catch(IllegalBlockSizeException e)
        {
            throw new PGPException((new StringBuilder()).append("illegal block size: ").append(e.getMessage()).toString(), e);
        }
        catch(BadPaddingException e)
        {
            throw new PGPException((new StringBuilder()).append("bad padding: ").append(e.getMessage()).toString(), e);
        }
        catch(InvalidAlgorithmParameterException e)
        {
            throw new PGPException((new StringBuilder()).append("IV invalid: ").append(e.getMessage()).toString(), e);
        }
        catch(InvalidKeyException e)
        {
            throw new PGPException((new StringBuilder()).append("key invalid: ").append(e.getMessage()).toString(), e);
        }
    }

    private OperatorHelper helper;
}
