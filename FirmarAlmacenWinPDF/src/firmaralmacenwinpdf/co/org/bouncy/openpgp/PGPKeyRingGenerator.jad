// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PGPKeyRingGenerator.java

package co.org.bouncy.openpgp;

import co.org.bouncy.bcpg.PublicKeyPacket;
import co.org.bouncy.bcpg.PublicSubkeyPacket;
import co.org.bouncy.openpgp.operator.PBESecretKeyEncryptor;
import co.org.bouncy.openpgp.operator.PGPContentSignerBuilder;
import co.org.bouncy.openpgp.operator.PGPDigestCalculator;
import co.org.bouncy.openpgp.operator.PGPDigestCalculatorProvider;
import co.org.bouncy.openpgp.operator.jcajce.JcaPGPContentSignerBuilder;
import co.org.bouncy.openpgp.operator.jcajce.JcaPGPDigestCalculatorProviderBuilder;
import co.org.bouncy.openpgp.operator.jcajce.JcePBESecretKeyEncryptorBuilder;
import java.security.*;
import java.util.*;

// Referenced classes of package co.org.bouncy.openpgp:
//            PGPSecretKey, PGPSignatureGenerator, PGPPublicKey, PGPException, 
//            PGPSecretKeyRing, PGPPublicKeyRing, PGPUtil, PGPKeyPair, 
//            PGPSignatureSubpacketVector

public class PGPKeyRingGenerator
{

    /**
     * @deprecated Method PGPKeyRingGenerator is deprecated
     */

    public PGPKeyRingGenerator(int certificationLevel, PGPKeyPair masterKey, String id, int encAlgorithm, char passPhrase[], PGPSignatureSubpacketVector hashedPcks, PGPSignatureSubpacketVector unhashedPcks, 
            SecureRandom rand, String provider)
        throws PGPException, NoSuchProviderException
    {
        this(certificationLevel, masterKey, id, encAlgorithm, passPhrase, false, hashedPcks, unhashedPcks, rand, provider);
    }

    /**
     * @deprecated Method PGPKeyRingGenerator is deprecated
     */

    public PGPKeyRingGenerator(int certificationLevel, PGPKeyPair masterKey, String id, int encAlgorithm, char passPhrase[], boolean useSHA1, PGPSignatureSubpacketVector hashedPcks, 
            PGPSignatureSubpacketVector unhashedPcks, SecureRandom rand, String provider)
        throws PGPException, NoSuchProviderException
    {
        this(certificationLevel, masterKey, id, encAlgorithm, passPhrase, useSHA1, hashedPcks, unhashedPcks, rand, PGPUtil.getProvider(provider));
    }

    /**
     * @deprecated Method PGPKeyRingGenerator is deprecated
     */

    public PGPKeyRingGenerator(int certificationLevel, PGPKeyPair masterKey, String id, int encAlgorithm, char passPhrase[], boolean useSHA1, PGPSignatureSubpacketVector hashedPcks, 
            PGPSignatureSubpacketVector unhashedPcks, SecureRandom rand, Provider provider)
        throws PGPException, NoSuchProviderException
    {
        keys = new ArrayList();
        this.masterKey = masterKey;
        this.hashedPcks = hashedPcks;
        this.unhashedPcks = unhashedPcks;
        keyEncryptor = (new JcePBESecretKeyEncryptorBuilder(encAlgorithm)).setProvider(provider).setSecureRandom(rand).build(passPhrase);
        checksumCalculator = convertSHA1Flag(useSHA1);
        keySignerBuilder = new JcaPGPContentSignerBuilder(masterKey.getPublicKey().getAlgorithm(), 2);
        keys.add(new PGPSecretKey(certificationLevel, masterKey, id, checksumCalculator, hashedPcks, unhashedPcks, keySignerBuilder, keyEncryptor));
    }

    public PGPKeyRingGenerator(int certificationLevel, PGPKeyPair masterKey, String id, PGPDigestCalculator checksumCalculator, PGPSignatureSubpacketVector hashedPcks, PGPSignatureSubpacketVector unhashedPcks, PGPContentSignerBuilder keySignerBuilder, 
            PBESecretKeyEncryptor keyEncryptor)
        throws PGPException
    {
        keys = new ArrayList();
        this.masterKey = masterKey;
        this.keyEncryptor = keyEncryptor;
        this.checksumCalculator = checksumCalculator;
        this.keySignerBuilder = keySignerBuilder;
        this.hashedPcks = hashedPcks;
        this.unhashedPcks = unhashedPcks;
        keys.add(new PGPSecretKey(certificationLevel, masterKey, id, checksumCalculator, hashedPcks, unhashedPcks, keySignerBuilder, keyEncryptor));
    }

    public void addSubKey(PGPKeyPair keyPair)
        throws PGPException
    {
        addSubKey(keyPair, hashedPcks, unhashedPcks);
    }

    public void addSubKey(PGPKeyPair keyPair, PGPSignatureSubpacketVector hashedPcks, PGPSignatureSubpacketVector unhashedPcks)
        throws PGPException
    {
        try
        {
            PGPSignatureGenerator sGen = new PGPSignatureGenerator(keySignerBuilder);
            sGen.init(24, masterKey.getPrivateKey());
            sGen.setHashedSubpackets(hashedPcks);
            sGen.setUnhashedSubpackets(unhashedPcks);
            List subSigs = new ArrayList();
            subSigs.add(sGen.generateCertification(masterKey.getPublicKey(), keyPair.getPublicKey()));
            keys.add(new PGPSecretKey(keyPair.getPrivateKey(), new PGPPublicKey(keyPair.getPublicKey(), null, subSigs), checksumCalculator, keyEncryptor));
        }
        catch(PGPException e)
        {
            throw e;
        }
        catch(Exception e)
        {
            throw new PGPException("exception adding subkey: ", e);
        }
    }

    public PGPSecretKeyRing generateSecretKeyRing()
    {
        return new PGPSecretKeyRing(keys);
    }

    public PGPPublicKeyRing generatePublicKeyRing()
    {
        Iterator it = keys.iterator();
        List pubKeys = new ArrayList();
        pubKeys.add(((PGPSecretKey)it.next()).getPublicKey());
        PGPPublicKey k;
        for(; it.hasNext(); pubKeys.add(k))
        {
            k = new PGPPublicKey(((PGPSecretKey)it.next()).getPublicKey());
            k.publicPk = new PublicSubkeyPacket(k.getAlgorithm(), k.getCreationTime(), k.publicPk.getKey());
        }

        return new PGPPublicKeyRing(pubKeys);
    }

    private static PGPDigestCalculator convertSHA1Flag(boolean useSHA1)
        throws PGPException
    {
        return useSHA1 ? (new JcaPGPDigestCalculatorProviderBuilder()).build().get(2) : null;
    }

    List keys;
    private PBESecretKeyEncryptor keyEncryptor;
    private PGPDigestCalculator checksumCalculator;
    private PGPKeyPair masterKey;
    private PGPSignatureSubpacketVector hashedPcks;
    private PGPSignatureSubpacketVector unhashedPcks;
    private PGPContentSignerBuilder keySignerBuilder;
}
