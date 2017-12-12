// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PGPEncryptedDataGenerator.java

package co.org.bouncy.openpgp;

import co.org.bouncy.bcpg.BCPGOutputStream;
import co.org.bouncy.bcpg.SymmetricKeyAlgorithmTags;
import co.org.bouncy.jce.provider.BouncyCastleProvider;
import co.org.bouncy.openpgp.operator.PBEKeyEncryptionMethodGenerator;
import co.org.bouncy.openpgp.operator.PGPDataEncryptor;
import co.org.bouncy.openpgp.operator.PGPDataEncryptorBuilder;
import co.org.bouncy.openpgp.operator.PGPDigestCalculator;
import co.org.bouncy.openpgp.operator.PGPDigestCalculatorProvider;
import co.org.bouncy.openpgp.operator.PGPKeyEncryptionMethodGenerator;
import co.org.bouncy.openpgp.operator.jcajce.JcaPGPDigestCalculatorProviderBuilder;
import co.org.bouncy.openpgp.operator.jcajce.JcePBEKeyEncryptionMethodGenerator;
import co.org.bouncy.openpgp.operator.jcajce.JcePGPDataEncryptorBuilder;
import co.org.bouncy.openpgp.operator.jcajce.JcePublicKeyKeyEncryptionMethodGenerator;
import co.org.bouncy.util.io.TeeOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.*;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package co.org.bouncy.openpgp:
//            WrappedGeneratorStream, PGPException, StreamGenerator, PGPPublicKey, 
//            PGPUtil

public class PGPEncryptedDataGenerator
    implements SymmetricKeyAlgorithmTags, StreamGenerator
{
    private class ClosableBCPGOutputStream extends BCPGOutputStream
    {

        public void close()
            throws IOException
        {
            finish();
        }

        final PGPEncryptedDataGenerator this$0;

        public ClosableBCPGOutputStream(OutputStream out, int symmetricKeyEnc, byte buffer[])
            throws IOException
        {
            this$0 = PGPEncryptedDataGenerator.this;
            super(out, symmetricKeyEnc, buffer);
        }

        public ClosableBCPGOutputStream(OutputStream out, int symmetricKeyEnc, long length, boolean oldFormat)
            throws IOException
        {
            this$0 = PGPEncryptedDataGenerator.this;
            super(out, symmetricKeyEnc, length, oldFormat);
        }

        public ClosableBCPGOutputStream(OutputStream out, int symEncIntegrityPro, long length)
            throws IOException
        {
            this$0 = PGPEncryptedDataGenerator.this;
            super(out, symEncIntegrityPro, length);
        }
    }


    /**
     * @deprecated Method PGPEncryptedDataGenerator is deprecated
     */

    public PGPEncryptedDataGenerator(int encAlgorithm, SecureRandom rand, String provider)
    {
        this(((PGPDataEncryptorBuilder) ((new JcePGPDataEncryptorBuilder(encAlgorithm)).setSecureRandom(rand).setProvider(provider))));
    }

    /**
     * @deprecated Method PGPEncryptedDataGenerator is deprecated
     */

    public PGPEncryptedDataGenerator(int encAlgorithm, SecureRandom rand, Provider provider)
    {
        this(((PGPDataEncryptorBuilder) ((new JcePGPDataEncryptorBuilder(encAlgorithm)).setSecureRandom(rand).setProvider(provider))));
    }

    /**
     * @deprecated Method PGPEncryptedDataGenerator is deprecated
     */

    public PGPEncryptedDataGenerator(int encAlgorithm, boolean withIntegrityPacket, SecureRandom rand, String provider)
    {
        this(((PGPDataEncryptorBuilder) ((new JcePGPDataEncryptorBuilder(encAlgorithm)).setWithIntegrityPacket(withIntegrityPacket).setSecureRandom(rand).setProvider(provider))));
    }

    /**
     * @deprecated Method PGPEncryptedDataGenerator is deprecated
     */

    public PGPEncryptedDataGenerator(int encAlgorithm, boolean withIntegrityPacket, SecureRandom rand, Provider provider)
    {
        this(((PGPDataEncryptorBuilder) ((new JcePGPDataEncryptorBuilder(encAlgorithm)).setWithIntegrityPacket(withIntegrityPacket).setSecureRandom(rand).setProvider(provider))));
    }

    /**
     * @deprecated Method PGPEncryptedDataGenerator is deprecated
     */

    public PGPEncryptedDataGenerator(int encAlgorithm, SecureRandom rand, boolean oldFormat, String provider)
    {
        this(((PGPDataEncryptorBuilder) ((new JcePGPDataEncryptorBuilder(encAlgorithm)).setSecureRandom(rand).setProvider(provider))), oldFormat);
    }

    /**
     * @deprecated Method PGPEncryptedDataGenerator is deprecated
     */

    public PGPEncryptedDataGenerator(int encAlgorithm, SecureRandom rand, boolean oldFormat, Provider provider)
    {
        this(((PGPDataEncryptorBuilder) ((new JcePGPDataEncryptorBuilder(encAlgorithm)).setSecureRandom(rand).setProvider(provider))), oldFormat);
    }

    public PGPEncryptedDataGenerator(PGPDataEncryptorBuilder encryptorBuilder)
    {
        this(encryptorBuilder, false);
    }

    public PGPEncryptedDataGenerator(PGPDataEncryptorBuilder encryptorBuilder, boolean oldFormat)
    {
        this.oldFormat = false;
        methods = new ArrayList();
        dataEncryptorBuilder = encryptorBuilder;
        this.oldFormat = oldFormat;
        defAlgorithm = dataEncryptorBuilder.getAlgorithm();
        rand = dataEncryptorBuilder.getSecureRandom();
    }

    /**
     * @deprecated Method addMethod is deprecated
     */

    public void addMethod(char passPhrase[])
        throws NoSuchProviderException, PGPException
    {
        addMethod(passPhrase, 2);
    }

    /**
     * @deprecated Method addMethod is deprecated
     */

    public void addMethod(char passPhrase[], int s2kDigest)
        throws NoSuchProviderException, PGPException
    {
        if(defProvider == null)
            defProvider = new BouncyCastleProvider();
        addMethod(((PGPKeyEncryptionMethodGenerator) ((new JcePBEKeyEncryptionMethodGenerator(passPhrase, (new JcaPGPDigestCalculatorProviderBuilder()).setProvider(defProvider).build().get(s2kDigest))).setProvider(defProvider).setSecureRandom(rand))));
    }

    /**
     * @deprecated Method addMethod is deprecated
     */

    public void addMethod(PGPPublicKey key)
        throws NoSuchProviderException, PGPException
    {
        if(!key.isEncryptionKey())
            throw new IllegalArgumentException("passed in key not an encryption key!");
        if(defProvider == null)
            defProvider = new BouncyCastleProvider();
        addMethod(((PGPKeyEncryptionMethodGenerator) ((new JcePublicKeyKeyEncryptionMethodGenerator(key)).setProvider(defProvider).setSecureRandom(rand))));
    }

    public void addMethod(PGPKeyEncryptionMethodGenerator method)
    {
        methods.add(method);
    }

    private void addCheckSum(byte sessionInfo[])
    {
        int check = 0;
        for(int i = 1; i != sessionInfo.length - 2; i++)
            check += sessionInfo[i] & 0xff;

        sessionInfo[sessionInfo.length - 2] = (byte)(check >> 8);
        sessionInfo[sessionInfo.length - 1] = (byte)check;
    }

    private byte[] createSessionInfo(int algorithm, byte keyBytes[])
    {
        byte sessionInfo[] = new byte[keyBytes.length + 3];
        sessionInfo[0] = (byte)algorithm;
        System.arraycopy(keyBytes, 0, sessionInfo, 1, keyBytes.length);
        addCheckSum(sessionInfo);
        return sessionInfo;
    }

    private OutputStream open(OutputStream out, long length, byte buffer[])
        throws IOException, PGPException, IllegalStateException
    {
        if(cOut != null)
            throw new IllegalStateException("generator already in open state");
        if(methods.size() == 0)
            throw new IllegalStateException("no encryption methods specified");
        byte key[] = null;
        pOut = new BCPGOutputStream(out);
        defAlgorithm = dataEncryptorBuilder.getAlgorithm();
        rand = dataEncryptorBuilder.getSecureRandom();
        if(methods.size() == 1)
        {
            if(methods.get(0) instanceof PBEKeyEncryptionMethodGenerator)
            {
                PBEKeyEncryptionMethodGenerator m = (PBEKeyEncryptionMethodGenerator)methods.get(0);
                key = m.getKey(dataEncryptorBuilder.getAlgorithm());
                pOut.writePacket(((PGPKeyEncryptionMethodGenerator)methods.get(0)).generate(defAlgorithm, null));
            } else
            {
                key = PGPUtil.makeRandomKey(defAlgorithm, rand);
                byte sessionInfo[] = createSessionInfo(defAlgorithm, key);
                PGPKeyEncryptionMethodGenerator m = (PGPKeyEncryptionMethodGenerator)methods.get(0);
                pOut.writePacket(m.generate(defAlgorithm, sessionInfo));
            }
        } else
        {
            key = PGPUtil.makeRandomKey(defAlgorithm, rand);
            byte sessionInfo[] = createSessionInfo(defAlgorithm, key);
            for(int i = 0; i != methods.size(); i++)
            {
                PGPKeyEncryptionMethodGenerator m = (PGPKeyEncryptionMethodGenerator)methods.get(i);
                pOut.writePacket(m.generate(defAlgorithm, sessionInfo));
            }

        }
        try
        {
            PGPDataEncryptor dataEncryptor = dataEncryptorBuilder.build(key);
            digestCalc = dataEncryptor.getIntegrityCalculator();
            if(buffer == null)
            {
                if(digestCalc != null)
                {
                    pOut = new ClosableBCPGOutputStream(out, 18, length + (long)dataEncryptor.getBlockSize() + 2L + 1L + 22L);
                    pOut.write(1);
                } else
                {
                    pOut = new ClosableBCPGOutputStream(out, 9, length + (long)dataEncryptor.getBlockSize() + 2L, oldFormat);
                }
            } else
            if(digestCalc != null)
            {
                pOut = new ClosableBCPGOutputStream(out, 18, buffer);
                pOut.write(1);
            } else
            {
                pOut = new ClosableBCPGOutputStream(out, 9, buffer);
            }
            genOut = cOut = dataEncryptor.getOutputStream(pOut);
            if(digestCalc != null)
                genOut = new TeeOutputStream(digestCalc.getOutputStream(), cOut);
            byte inLineIv[] = new byte[dataEncryptor.getBlockSize() + 2];
            rand.nextBytes(inLineIv);
            inLineIv[inLineIv.length - 1] = inLineIv[inLineIv.length - 3];
            inLineIv[inLineIv.length - 2] = inLineIv[inLineIv.length - 4];
            genOut.write(inLineIv);
            return new WrappedGeneratorStream(genOut, this);
        }
        catch(Exception e)
        {
            throw new PGPException("Exception creating cipher", e);
        }
    }

    public OutputStream open(OutputStream out, long length)
        throws IOException, PGPException
    {
        return open(out, length, null);
    }

    public OutputStream open(OutputStream out, byte buffer[])
        throws IOException, PGPException
    {
        return open(out, 0L, buffer);
    }

    public void close()
        throws IOException
    {
        if(cOut != null)
        {
            if(digestCalc != null)
            {
                BCPGOutputStream bOut = new BCPGOutputStream(genOut, 19, 20L);
                bOut.flush();
                byte dig[] = digestCalc.getDigest();
                cOut.write(dig);
            }
            cOut.close();
            cOut = null;
            pOut = null;
        }
    }

    public static final int S2K_SHA1 = 2;
    public static final int S2K_SHA224 = 11;
    public static final int S2K_SHA256 = 8;
    public static final int S2K_SHA384 = 9;
    public static final int S2K_SHA512 = 10;
    private BCPGOutputStream pOut;
    private OutputStream cOut;
    private boolean oldFormat;
    private PGPDigestCalculator digestCalc;
    private OutputStream genOut;
    private PGPDataEncryptorBuilder dataEncryptorBuilder;
    private List methods;
    private int defAlgorithm;
    private SecureRandom rand;
    private static Provider defProvider;
}
