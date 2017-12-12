// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PGPSecretKey.java

package co.org.bouncy.openpgp;

import co.org.bouncy.bcpg.*;
import co.org.bouncy.openpgp.operator.PBESecretKeyDecryptor;
import co.org.bouncy.openpgp.operator.PBESecretKeyEncryptor;
import co.org.bouncy.openpgp.operator.PGPContentSignerBuilder;
import co.org.bouncy.openpgp.operator.PGPDigestCalculator;
import co.org.bouncy.openpgp.operator.PGPDigestCalculatorProvider;
import co.org.bouncy.openpgp.operator.jcajce.JcaPGPContentSignerBuilder;
import co.org.bouncy.openpgp.operator.jcajce.JcaPGPDigestCalculatorProviderBuilder;
import co.org.bouncy.openpgp.operator.jcajce.JcePBESecretKeyDecryptorBuilder;
import co.org.bouncy.openpgp.operator.jcajce.JcePBESecretKeyEncryptorBuilder;
import java.io.*;
import java.security.*;
import java.util.*;

// Referenced classes of package co.org.bouncy.openpgp:
//            PGPException, PGPSignatureGenerator, PGPKeyPair, PGPPrivateKey, 
//            PGPSignature, PGPUserAttributeSubpacketVector, PGPPublicKey, PGPUtil, 
//            PGPSignatureSubpacketVector

public class PGPSecretKey
{

    PGPSecretKey(SecretKeyPacket secret, PGPPublicKey pub)
    {
        this.secret = secret;
        this.pub = pub;
    }

    PGPSecretKey(PGPPrivateKey privKey, PGPPublicKey pubKey, PGPDigestCalculator checksumCalculator, PBESecretKeyEncryptor keyEncryptor)
        throws PGPException
    {
        this(privKey, pubKey, checksumCalculator, false, keyEncryptor);
    }

    PGPSecretKey(PGPPrivateKey privKey, PGPPublicKey pubKey, PGPDigestCalculator checksumCalculator, boolean isMasterKey, PBESecretKeyEncryptor keyEncryptor)
        throws PGPException
    {
        pub = pubKey;
        BCPGObject secKey = (BCPGObject)privKey.getPrivateKeyDataPacket();
        try
        {
            ByteArrayOutputStream bOut = new ByteArrayOutputStream();
            BCPGOutputStream pOut = new BCPGOutputStream(bOut);
            pOut.writeObject(secKey);
            byte keyData[] = bOut.toByteArray();
            pOut.write(checksum(checksumCalculator, keyData, keyData.length));
            int encAlgorithm = keyEncryptor.getAlgorithm();
            if(encAlgorithm != 0)
            {
                keyData = bOut.toByteArray();
                byte encData[] = keyEncryptor.encryptKeyData(keyData, 0, keyData.length);
                byte iv[] = keyEncryptor.getCipherIV();
                S2K s2k = keyEncryptor.getS2K();
                int s2kUsage;
                if(checksumCalculator != null)
                {
                    if(checksumCalculator.getAlgorithm() != 2)
                        throw new PGPException("only SHA1 supported for key checksum calculations.");
                    s2kUsage = 254;
                } else
                {
                    s2kUsage = 255;
                }
                if(isMasterKey)
                    secret = new SecretKeyPacket(pub.publicPk, encAlgorithm, s2kUsage, s2k, iv, encData);
                else
                    secret = new SecretSubkeyPacket(pub.publicPk, encAlgorithm, s2kUsage, s2k, iv, encData);
            } else
            if(isMasterKey)
                secret = new SecretKeyPacket(pub.publicPk, encAlgorithm, null, null, bOut.toByteArray());
            else
                secret = new SecretSubkeyPacket(pub.publicPk, encAlgorithm, null, null, bOut.toByteArray());
        }
        catch(PGPException e)
        {
            throw e;
        }
        catch(Exception e)
        {
            throw new PGPException("Exception encrypting key", e);
        }
    }

    /**
     * @deprecated Method PGPSecretKey is deprecated
     */

    public PGPSecretKey(int certificationLevel, PGPKeyPair keyPair, String id, int encAlgorithm, char passPhrase[], PGPSignatureSubpacketVector hashedPcks, PGPSignatureSubpacketVector unhashedPcks, 
            SecureRandom rand, String provider)
        throws PGPException, NoSuchProviderException
    {
        this(certificationLevel, keyPair, id, encAlgorithm, passPhrase, false, hashedPcks, unhashedPcks, rand, provider);
    }

    /**
     * @deprecated Method PGPSecretKey is deprecated
     */

    public PGPSecretKey(int certificationLevel, PGPKeyPair keyPair, String id, int encAlgorithm, char passPhrase[], boolean useSHA1, PGPSignatureSubpacketVector hashedPcks, 
            PGPSignatureSubpacketVector unhashedPcks, SecureRandom rand, String provider)
        throws PGPException, NoSuchProviderException
    {
        this(certificationLevel, keyPair, id, encAlgorithm, passPhrase, useSHA1, hashedPcks, unhashedPcks, rand, PGPUtil.getProvider(provider));
    }

    public PGPSecretKey(int certificationLevel, PGPKeyPair keyPair, String id, PGPSignatureSubpacketVector hashedPcks, PGPSignatureSubpacketVector unhashedPcks, PGPContentSignerBuilder certificationSignerBuilder, PBESecretKeyEncryptor keyEncryptor)
        throws PGPException
    {
        this(certificationLevel, keyPair, id, null, hashedPcks, unhashedPcks, certificationSignerBuilder, keyEncryptor);
    }

    /**
     * @deprecated Method PGPSecretKey is deprecated
     */

    public PGPSecretKey(int certificationLevel, PGPKeyPair keyPair, String id, int encAlgorithm, char passPhrase[], boolean useSHA1, PGPSignatureSubpacketVector hashedPcks, 
            PGPSignatureSubpacketVector unhashedPcks, SecureRandom rand, Provider provider)
        throws PGPException
    {
        this(keyPair.getPrivateKey(), certifiedPublicKey(certificationLevel, keyPair, id, hashedPcks, unhashedPcks, (new JcaPGPContentSignerBuilder(keyPair.getPublicKey().getAlgorithm(), 2)).setProvider(provider)), convertSHA1Flag(useSHA1), true, (new JcePBESecretKeyEncryptorBuilder(encAlgorithm, (new JcaPGPDigestCalculatorProviderBuilder()).build().get(2))).setProvider(provider).setSecureRandom(rand).build(passPhrase));
    }

    private static PGPDigestCalculator convertSHA1Flag(boolean useSHA1)
        throws PGPException
    {
        return useSHA1 ? (new JcaPGPDigestCalculatorProviderBuilder()).build().get(2) : null;
    }

    public PGPSecretKey(int certificationLevel, PGPKeyPair keyPair, String id, PGPDigestCalculator checksumCalculator, PGPSignatureSubpacketVector hashedPcks, PGPSignatureSubpacketVector unhashedPcks, PGPContentSignerBuilder certificationSignerBuilder, 
            PBESecretKeyEncryptor keyEncryptor)
        throws PGPException
    {
        this(keyPair.getPrivateKey(), certifiedPublicKey(certificationLevel, keyPair, id, hashedPcks, unhashedPcks, certificationSignerBuilder), checksumCalculator, true, keyEncryptor);
    }

    private static PGPPublicKey certifiedPublicKey(int certificationLevel, PGPKeyPair keyPair, String id, PGPSignatureSubpacketVector hashedPcks, PGPSignatureSubpacketVector unhashedPcks, PGPContentSignerBuilder certificationSignerBuilder)
        throws PGPException
    {
        PGPSignatureGenerator sGen;
        try
        {
            sGen = new PGPSignatureGenerator(certificationSignerBuilder);
        }
        catch(Exception e)
        {
            throw new PGPException((new StringBuilder()).append("creating signature generator: ").append(e).toString(), e);
        }
        sGen.init(certificationLevel, keyPair.getPrivateKey());
        sGen.setHashedSubpackets(hashedPcks);
        sGen.setUnhashedSubpackets(unhashedPcks);
        try
        {
            PGPSignature certification = sGen.generateCertification(id, keyPair.getPublicKey());
            return PGPPublicKey.addCertification(keyPair.getPublicKey(), id, certification);
        }
        catch(Exception e)
        {
            throw new PGPException((new StringBuilder()).append("exception doing certification: ").append(e).toString(), e);
        }
    }

    /**
     * @deprecated Method PGPSecretKey is deprecated
     */

    public PGPSecretKey(int certificationLevel, int algorithm, PublicKey pubKey, PrivateKey privKey, Date time, String id, int encAlgorithm, 
            char passPhrase[], PGPSignatureSubpacketVector hashedPcks, PGPSignatureSubpacketVector unhashedPcks, SecureRandom rand, String provider)
        throws PGPException, NoSuchProviderException
    {
        this(certificationLevel, new PGPKeyPair(algorithm, pubKey, privKey, time), id, encAlgorithm, passPhrase, hashedPcks, unhashedPcks, rand, provider);
    }

    /**
     * @deprecated Method PGPSecretKey is deprecated
     */

    public PGPSecretKey(int certificationLevel, int algorithm, PublicKey pubKey, PrivateKey privKey, Date time, String id, int encAlgorithm, 
            char passPhrase[], boolean useSHA1, PGPSignatureSubpacketVector hashedPcks, PGPSignatureSubpacketVector unhashedPcks, SecureRandom rand, String provider)
        throws PGPException, NoSuchProviderException
    {
        this(certificationLevel, new PGPKeyPair(algorithm, pubKey, privKey, time), id, encAlgorithm, passPhrase, useSHA1, hashedPcks, unhashedPcks, rand, provider);
    }

    /**
     * @deprecated Method PGPSecretKey is deprecated
     */

    public PGPSecretKey(int certificationLevel, int algorithm, PublicKey pubKey, PrivateKey privKey, Date time, String id, PGPDigestCalculator checksumCalculator, 
            PGPSignatureSubpacketVector hashedPcks, PGPSignatureSubpacketVector unhashedPcks, PGPContentSignerBuilder certificationSignerBuilder, PBESecretKeyEncryptor keyEncryptor)
        throws PGPException
    {
        this(certificationLevel, new PGPKeyPair(algorithm, pubKey, privKey, time), id, checksumCalculator, hashedPcks, unhashedPcks, certificationSignerBuilder, keyEncryptor);
    }

    /**
     * @deprecated Method PGPSecretKey is deprecated
     */

    public PGPSecretKey(int certificationLevel, int algorithm, PublicKey pubKey, PrivateKey privKey, Date time, String id, PGPSignatureSubpacketVector hashedPcks, 
            PGPSignatureSubpacketVector unhashedPcks, PGPContentSignerBuilder certificationSignerBuilder, PBESecretKeyEncryptor keyEncryptor)
        throws PGPException, NoSuchProviderException
    {
        this(certificationLevel, new PGPKeyPair(algorithm, pubKey, privKey, time), id, null, hashedPcks, unhashedPcks, certificationSignerBuilder, keyEncryptor);
    }

    public boolean isSigningKey()
    {
        int algorithm = pub.getAlgorithm();
        return algorithm == 1 || algorithm == 3 || algorithm == 17 || algorithm == 19 || algorithm == 20;
    }

    public boolean isMasterKey()
    {
        return pub.isMasterKey();
    }

    public boolean isPrivateKeyEmpty()
    {
        byte secKeyData[] = secret.getSecretKeyData();
        return secKeyData == null || secKeyData.length < 1;
    }

    public int getKeyEncryptionAlgorithm()
    {
        return secret.getEncAlgorithm();
    }

    public long getKeyID()
    {
        return pub.getKeyID();
    }

    public PGPPublicKey getPublicKey()
    {
        return pub;
    }

    public Iterator getUserIDs()
    {
        return pub.getUserIDs();
    }

    public Iterator getUserAttributes()
    {
        return pub.getUserAttributes();
    }

    private byte[] extractKeyData(PBESecretKeyDecryptor decryptorFactory)
        throws PGPException
    {
        byte encData[] = secret.getSecretKeyData();
        byte data[] = null;
        if(secret.getEncAlgorithm() != 0)
            try
            {
                if(secret.getPublicKeyPacket().getVersion() == 4)
                {
                    byte key[] = decryptorFactory.makeKeyFromPassPhrase(secret.getEncAlgorithm(), secret.getS2K());
                    data = decryptorFactory.recoverKeyData(secret.getEncAlgorithm(), key, secret.getIV(), encData, 0, encData.length);
                    boolean useSHA1 = secret.getS2KUsage() == 254;
                    byte check[] = checksum(useSHA1 ? decryptorFactory.getChecksumCalculator(2) : null, data, useSHA1 ? data.length - 20 : data.length - 2);
                    for(int i = 0; i != check.length; i++)
                        if(check[i] != data[(data.length - check.length) + i])
                            throw new PGPException((new StringBuilder()).append("checksum mismatch at ").append(i).append(" of ").append(check.length).toString());

                } else
                {
                    byte key[] = decryptorFactory.makeKeyFromPassPhrase(secret.getEncAlgorithm(), secret.getS2K());
                    data = new byte[encData.length];
                    byte iv[] = new byte[secret.getIV().length];
                    System.arraycopy(secret.getIV(), 0, iv, 0, iv.length);
                    int pos = 0;
                    for(int i = 0; i != 4; i++)
                    {
                        int encLen = ((encData[pos] << 8 | encData[pos + 1] & 0xff) + 7) / 8;
                        data[pos] = encData[pos];
                        data[pos + 1] = encData[pos + 1];
                        byte tmp[] = decryptorFactory.recoverKeyData(secret.getEncAlgorithm(), key, iv, encData, pos + 2, encLen);
                        System.arraycopy(tmp, 0, data, pos + 2, tmp.length);
                        pos += 2 + encLen;
                        if(i != 3)
                            System.arraycopy(encData, pos - iv.length, iv, 0, iv.length);
                    }

                    data[pos] = encData[pos];
                    data[pos + 1] = encData[pos + 1];
                    int cs = encData[pos] << 8 & 0xff00 | encData[pos + 1] & 0xff;
                    int calcCs = 0;
                    for(int j = 0; j < data.length - 2; j++)
                        calcCs += data[j] & 0xff;

                    calcCs &= 0xffff;
                    if(calcCs != cs)
                        throw new PGPException((new StringBuilder()).append("checksum mismatch: passphrase wrong, expected ").append(Integer.toHexString(cs)).append(" found ").append(Integer.toHexString(calcCs)).toString());
                }
            }
            catch(PGPException e)
            {
                throw e;
            }
            catch(Exception e)
            {
                throw new PGPException("Exception decrypting key", e);
            }
        else
            data = encData;
        return data;
    }

    /**
     * @deprecated Method extractPrivateKey is deprecated
     */

    public PGPPrivateKey extractPrivateKey(char passPhrase[], String provider)
        throws PGPException, NoSuchProviderException
    {
        return extractPrivateKey(passPhrase, PGPUtil.getProvider(provider));
    }

    /**
     * @deprecated Method extractPrivateKey is deprecated
     */

    public PGPPrivateKey extractPrivateKey(char passPhrase[], Provider provider)
        throws PGPException
    {
        return extractPrivateKey((new JcePBESecretKeyDecryptorBuilder((new JcaPGPDigestCalculatorProviderBuilder()).setProvider(provider).build())).setProvider(provider).build(passPhrase));
    }

    public PGPPrivateKey extractPrivateKey(PBESecretKeyDecryptor decryptorFactory)
        throws PGPException
    {
        PublicKeyPacket pubPk;
        if(isPrivateKeyEmpty())
            return null;
        pubPk = secret.getPublicKeyPacket();
        BCPGInputStream in;
        byte data[] = extractKeyData(decryptorFactory);
        in = new BCPGInputStream(new ByteArrayInputStream(data));
        pubPk.getAlgorithm();
        JVM INSTR tableswitch 1 20: default 218
    //                   1 140
    //                   2 140
    //                   3 140
    //                   4 218
    //                   5 218
    //                   6 218
    //                   7 218
    //                   8 218
    //                   9 218
    //                   10 218
    //                   11 218
    //                   12 218
    //                   13 218
    //                   14 218
    //                   15 218
    //                   16 192
    //                   17 166
    //                   18 218
    //                   19 218
    //                   20 192;
           goto _L1 _L2 _L2 _L2 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L3 _L4 _L1 _L1 _L3
_L2:
        RSASecretBCPGKey rsaPriv = new RSASecretBCPGKey(in);
        return new PGPPrivateKey(getKeyID(), pubPk, rsaPriv);
_L4:
        ElGamalSecretBCPGKey elPriv;
        try
        {
            DSASecretBCPGKey dsaPriv = new DSASecretBCPGKey(in);
            return new PGPPrivateKey(getKeyID(), pubPk, dsaPriv);
        }
        catch(PGPException e)
        {
            throw e;
        }
        catch(Exception e)
        {
            throw new PGPException("Exception constructing key", e);
        }
_L3:
        elPriv = new ElGamalSecretBCPGKey(in);
        return new PGPPrivateKey(getKeyID(), pubPk, elPriv);
_L1:
        throw new PGPException("unknown public key algorithm encountered");
    }

    private static byte[] checksum(PGPDigestCalculator digCalc, byte bytes[], int length)
        throws PGPException
    {
        if(digCalc != null)
        {
            OutputStream dOut = digCalc.getOutputStream();
            try
            {
                dOut.write(bytes, 0, length);
                dOut.close();
            }
            catch(Exception e)
            {
                throw new PGPException((new StringBuilder()).append("checksum digest calculation failed: ").append(e.getMessage()).toString(), e);
            }
            return digCalc.getDigest();
        }
        int checksum = 0;
        for(int i = 0; i != length; i++)
            checksum += bytes[i] & 0xff;

        byte check[] = new byte[2];
        check[0] = (byte)(checksum >> 8);
        check[1] = (byte)checksum;
        return check;
    }

    public byte[] getEncoded()
        throws IOException
    {
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        encode(bOut);
        return bOut.toByteArray();
    }

    public void encode(OutputStream outStream)
        throws IOException
    {
        BCPGOutputStream out;
        if(outStream instanceof BCPGOutputStream)
            out = (BCPGOutputStream)outStream;
        else
            out = new BCPGOutputStream(outStream);
        out.writePacket(secret);
        if(pub.trustPk != null)
            out.writePacket(pub.trustPk);
        if(pub.subSigs == null)
        {
            for(int i = 0; i != pub.keySigs.size(); i++)
                ((PGPSignature)pub.keySigs.get(i)).encode(out);

            for(int i = 0; i != pub.ids.size(); i++)
            {
                if(pub.ids.get(i) instanceof String)
                {
                    String id = (String)pub.ids.get(i);
                    out.writePacket(new UserIDPacket(id));
                } else
                {
                    PGPUserAttributeSubpacketVector v = (PGPUserAttributeSubpacketVector)pub.ids.get(i);
                    out.writePacket(new UserAttributePacket(v.toSubpacketArray()));
                }
                if(pub.idTrusts.get(i) != null)
                    out.writePacket((ContainedPacket)pub.idTrusts.get(i));
                List sigs = (ArrayList)pub.idSigs.get(i);
                for(int j = 0; j != sigs.size(); j++)
                    ((PGPSignature)sigs.get(j)).encode(out);

            }

        } else
        {
            for(int j = 0; j != pub.subSigs.size(); j++)
                ((PGPSignature)pub.subSigs.get(j)).encode(out);

        }
    }

    /**
     * @deprecated Method copyWithNewPassword is deprecated
     */

    public static PGPSecretKey copyWithNewPassword(PGPSecretKey key, char oldPassPhrase[], char newPassPhrase[], int newEncAlgorithm, SecureRandom rand, String provider)
        throws PGPException, NoSuchProviderException
    {
        return copyWithNewPassword(key, oldPassPhrase, newPassPhrase, newEncAlgorithm, rand, PGPUtil.getProvider(provider));
    }

    public static PGPSecretKey copyWithNewPassword(PGPSecretKey key, PBESecretKeyDecryptor oldKeyDecryptor, PBESecretKeyEncryptor newKeyEncryptor)
        throws PGPException
    {
        if(key.isPrivateKeyEmpty())
            throw new PGPException("no private key in this SecretKey - public key present only.");
        byte rawKeyData[] = key.extractKeyData(oldKeyDecryptor);
        int s2kUsage = key.secret.getS2KUsage();
        byte iv[] = null;
        S2K s2k = null;
        int newEncAlgorithm = 0;
        byte keyData[];
        if(newKeyEncryptor == null || newKeyEncryptor.getAlgorithm() == 0)
        {
            s2kUsage = 0;
            if(key.secret.getS2KUsage() == 254)
            {
                keyData = new byte[rawKeyData.length - 18];
                System.arraycopy(rawKeyData, 0, keyData, 0, keyData.length - 2);
                byte check[] = checksum(null, keyData, keyData.length - 2);
                keyData[keyData.length - 2] = check[0];
                keyData[keyData.length - 1] = check[1];
            } else
            {
                keyData = rawKeyData;
            }
        } else
        if(key.secret.getPublicKeyPacket().getVersion() < 4)
        {
            byte encKey[] = newKeyEncryptor.getKey();
            keyData = new byte[rawKeyData.length];
            if(newKeyEncryptor.getHashAlgorithm() != 1)
                throw new PGPException("MD5 Digest Calculator required for version 3 key encryptor.");
            int pos = 0;
            for(int i = 0; i != 4; i++)
            {
                int encLen = ((rawKeyData[pos] << 8 | rawKeyData[pos + 1] & 0xff) + 7) / 8;
                keyData[pos] = rawKeyData[pos];
                keyData[pos + 1] = rawKeyData[pos + 1];
                byte tmp[];
                if(i == 0)
                {
                    tmp = newKeyEncryptor.encryptKeyData(encKey, rawKeyData, pos + 2, encLen);
                    iv = newKeyEncryptor.getCipherIV();
                } else
                {
                    byte tmpIv[] = new byte[iv.length];
                    System.arraycopy(keyData, pos - iv.length, tmpIv, 0, tmpIv.length);
                    tmp = newKeyEncryptor.encryptKeyData(encKey, tmpIv, rawKeyData, pos + 2, encLen);
                }
                System.arraycopy(tmp, 0, keyData, pos + 2, tmp.length);
                pos += 2 + encLen;
            }

            keyData[pos] = rawKeyData[pos];
            keyData[pos + 1] = rawKeyData[pos + 1];
            s2k = newKeyEncryptor.getS2K();
            newEncAlgorithm = newKeyEncryptor.getAlgorithm();
        } else
        {
            keyData = newKeyEncryptor.encryptKeyData(rawKeyData, 0, rawKeyData.length);
            iv = newKeyEncryptor.getCipherIV();
            s2k = newKeyEncryptor.getS2K();
            newEncAlgorithm = newKeyEncryptor.getAlgorithm();
        }
        SecretKeyPacket secret;
        if(key.secret instanceof SecretSubkeyPacket)
            secret = new SecretSubkeyPacket(key.secret.getPublicKeyPacket(), newEncAlgorithm, s2kUsage, s2k, iv, keyData);
        else
            secret = new SecretKeyPacket(key.secret.getPublicKeyPacket(), newEncAlgorithm, s2kUsage, s2k, iv, keyData);
        return new PGPSecretKey(secret, key.pub);
    }

    /**
     * @deprecated Method copyWithNewPassword is deprecated
     */

    public static PGPSecretKey copyWithNewPassword(PGPSecretKey key, char oldPassPhrase[], char newPassPhrase[], int newEncAlgorithm, SecureRandom rand, Provider provider)
        throws PGPException
    {
        return copyWithNewPassword(key, (new JcePBESecretKeyDecryptorBuilder((new JcaPGPDigestCalculatorProviderBuilder()).setProvider(provider).build())).setProvider(provider).build(oldPassPhrase), (new JcePBESecretKeyEncryptorBuilder(newEncAlgorithm)).setProvider(provider).setSecureRandom(rand).build(newPassPhrase));
    }

    public static PGPSecretKey replacePublicKey(PGPSecretKey secretKey, PGPPublicKey publicKey)
    {
        if(publicKey.getKeyID() != secretKey.getKeyID())
            throw new IllegalArgumentException("keyIDs do not match");
        else
            return new PGPSecretKey(secretKey.secret, publicKey);
    }

    SecretKeyPacket secret;
    PGPPublicKey pub;
}
