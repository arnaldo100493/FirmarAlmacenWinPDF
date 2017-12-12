// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PGPSecretKeyRing.java

package co.org.bouncy.openpgp;

import co.org.bouncy.bcpg.*;
import co.org.bouncy.openpgp.operator.KeyFingerPrintCalculator;
import co.org.bouncy.openpgp.operator.PBESecretKeyDecryptor;
import co.org.bouncy.openpgp.operator.PBESecretKeyEncryptor;
import co.org.bouncy.openpgp.operator.jcajce.JcaKeyFingerprintCalculator;
import java.io.*;
import java.security.*;
import java.util.*;

// Referenced classes of package co.org.bouncy.openpgp:
//            PGPKeyRing, PGPSecretKey, PGPPublicKey, PGPException, 
//            PGPPublicKeyRing, PGPUtil

public class PGPSecretKeyRing extends PGPKeyRing
{

    PGPSecretKeyRing(List keys)
    {
        this(keys, ((List) (new ArrayList())));
    }

    private PGPSecretKeyRing(List keys, List extraPubKeys)
    {
        this.keys = keys;
        this.extraPubKeys = extraPubKeys;
    }

    /**
     * @deprecated Method PGPSecretKeyRing is deprecated
     */

    public PGPSecretKeyRing(byte encoding[])
        throws IOException, PGPException
    {
        this(((InputStream) (new ByteArrayInputStream(encoding))));
    }

    public PGPSecretKeyRing(byte encoding[], KeyFingerPrintCalculator fingerPrintCalculator)
        throws IOException, PGPException
    {
        this(((InputStream) (new ByteArrayInputStream(encoding))), fingerPrintCalculator);
    }

    /**
     * @deprecated Method PGPSecretKeyRing is deprecated
     */

    public PGPSecretKeyRing(InputStream in)
        throws IOException, PGPException
    {
        this(in, ((KeyFingerPrintCalculator) (new JcaKeyFingerprintCalculator())));
    }

    public PGPSecretKeyRing(InputStream in, KeyFingerPrintCalculator fingerPrintCalculator)
        throws IOException, PGPException
    {
        keys = new ArrayList();
        extraPubKeys = new ArrayList();
        BCPGInputStream pIn = wrap(in);
        int initialTag = pIn.nextPacketTag();
        if(initialTag != 5 && initialTag != 7)
            throw new IOException((new StringBuilder()).append("secret key ring doesn't start with secret key tag: tag 0x").append(Integer.toHexString(initialTag)).toString());
        SecretKeyPacket secret = (SecretKeyPacket)pIn.readPacket();
        for(; pIn.nextPacketTag() == 61; pIn.readPacket());
        TrustPacket trust = readOptionalTrustPacket(pIn);
        List keySigs = readSignaturesAndTrust(pIn);
        List ids = new ArrayList();
        List idTrusts = new ArrayList();
        List idSigs = new ArrayList();
        readUserIDs(pIn, ids, idTrusts, idSigs);
        keys.add(new PGPSecretKey(secret, new PGPPublicKey(secret.getPublicKeyPacket(), trust, keySigs, ids, idTrusts, idSigs, fingerPrintCalculator)));
        while(pIn.nextPacketTag() == 7 || pIn.nextPacketTag() == 14) 
            if(pIn.nextPacketTag() == 7)
            {
                SecretSubkeyPacket sub = (SecretSubkeyPacket)pIn.readPacket();
                for(; pIn.nextPacketTag() == 61; pIn.readPacket());
                TrustPacket subTrust = readOptionalTrustPacket(pIn);
                List sigList = readSignaturesAndTrust(pIn);
                keys.add(new PGPSecretKey(sub, new PGPPublicKey(sub.getPublicKeyPacket(), subTrust, sigList, fingerPrintCalculator)));
            } else
            {
                PublicSubkeyPacket sub = (PublicSubkeyPacket)pIn.readPacket();
                TrustPacket subTrust = readOptionalTrustPacket(pIn);
                List sigList = readSignaturesAndTrust(pIn);
                extraPubKeys.add(new PGPPublicKey(sub, subTrust, sigList, fingerPrintCalculator));
            }
    }

    public PGPPublicKey getPublicKey()
    {
        return ((PGPSecretKey)keys.get(0)).getPublicKey();
    }

    public PGPPublicKey getPublicKey(long keyID)
    {
        PGPSecretKey key = getSecretKey(keyID);
        if(key != null)
            return key.getPublicKey();
        for(int i = 0; i != extraPubKeys.size(); i++)
        {
            PGPPublicKey k = (PGPPublicKey)keys.get(i);
            if(keyID == k.getKeyID())
                return k;
        }

        return null;
    }

    public Iterator getPublicKeys()
    {
        List pubKeys = new ArrayList();
        for(Iterator it = getSecretKeys(); it.hasNext(); pubKeys.add(((PGPSecretKey)it.next()).getPublicKey()));
        pubKeys.addAll(extraPubKeys);
        return Collections.unmodifiableList(pubKeys).iterator();
    }

    public PGPSecretKey getSecretKey()
    {
        return (PGPSecretKey)keys.get(0);
    }

    public Iterator getSecretKeys()
    {
        return Collections.unmodifiableList(keys).iterator();
    }

    public PGPSecretKey getSecretKey(long keyId)
    {
        for(int i = 0; i != keys.size(); i++)
        {
            PGPSecretKey k = (PGPSecretKey)keys.get(i);
            if(keyId == k.getKeyID())
                return k;
        }

        return null;
    }

    public Iterator getExtraPublicKeys()
    {
        return extraPubKeys.iterator();
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
        for(int i = 0; i != keys.size(); i++)
        {
            PGPSecretKey k = (PGPSecretKey)keys.get(i);
            k.encode(outStream);
        }

        for(int i = 0; i != extraPubKeys.size(); i++)
        {
            PGPPublicKey k = (PGPPublicKey)extraPubKeys.get(i);
            k.encode(outStream);
        }

    }

    public static PGPSecretKeyRing replacePublicKeys(PGPSecretKeyRing secretRing, PGPPublicKeyRing publicRing)
    {
        List newList = new ArrayList(secretRing.keys.size());
        PGPSecretKey sk;
        PGPPublicKey pk;
        for(Iterator it = secretRing.keys.iterator(); it.hasNext(); newList.add(PGPSecretKey.replacePublicKey(sk, pk)))
        {
            sk = (PGPSecretKey)it.next();
            pk = publicRing.getPublicKey(sk.getKeyID());
        }

        return new PGPSecretKeyRing(newList);
    }

    /**
     * @deprecated Method copyWithNewPassword is deprecated
     */

    public static PGPSecretKeyRing copyWithNewPassword(PGPSecretKeyRing ring, char oldPassPhrase[], char newPassPhrase[], int newEncAlgorithm, SecureRandom rand, String provider)
        throws PGPException, NoSuchProviderException
    {
        return copyWithNewPassword(ring, oldPassPhrase, newPassPhrase, newEncAlgorithm, rand, PGPUtil.getProvider(provider));
    }

    /**
     * @deprecated Method copyWithNewPassword is deprecated
     */

    public static PGPSecretKeyRing copyWithNewPassword(PGPSecretKeyRing ring, char oldPassPhrase[], char newPassPhrase[], int newEncAlgorithm, SecureRandom rand, Provider provider)
        throws PGPException
    {
        List newKeys = new ArrayList(ring.keys.size());
        for(Iterator keys = ring.getSecretKeys(); keys.hasNext(); newKeys.add(PGPSecretKey.copyWithNewPassword((PGPSecretKey)keys.next(), oldPassPhrase, newPassPhrase, newEncAlgorithm, rand, provider)));
        return new PGPSecretKeyRing(newKeys, ring.extraPubKeys);
    }

    public static PGPSecretKeyRing copyWithNewPassword(PGPSecretKeyRing ring, PBESecretKeyDecryptor oldKeyDecryptor, PBESecretKeyEncryptor newKeyEncryptor)
        throws PGPException
    {
        List newKeys = new ArrayList(ring.keys.size());
        for(Iterator keys = ring.getSecretKeys(); keys.hasNext();)
        {
            PGPSecretKey key = (PGPSecretKey)keys.next();
            if(key.isPrivateKeyEmpty())
                newKeys.add(key);
            else
                newKeys.add(PGPSecretKey.copyWithNewPassword(key, oldKeyDecryptor, newKeyEncryptor));
        }

        return new PGPSecretKeyRing(newKeys, ring.extraPubKeys);
    }

    public static PGPSecretKeyRing insertSecretKey(PGPSecretKeyRing secRing, PGPSecretKey secKey)
    {
        List keys = new ArrayList(secRing.keys);
        boolean found = false;
        boolean masterFound = false;
        for(int i = 0; i != keys.size(); i++)
        {
            PGPSecretKey key = (PGPSecretKey)keys.get(i);
            if(key.getKeyID() == secKey.getKeyID())
            {
                found = true;
                keys.set(i, secKey);
            }
            if(key.isMasterKey())
                masterFound = true;
        }

        if(!found)
            if(secKey.isMasterKey())
            {
                if(masterFound)
                    throw new IllegalArgumentException("cannot add a master key to a ring that already has one");
                keys.add(0, secKey);
            } else
            {
                keys.add(secKey);
            }
        return new PGPSecretKeyRing(keys, secRing.extraPubKeys);
    }

    public static PGPSecretKeyRing removeSecretKey(PGPSecretKeyRing secRing, PGPSecretKey secKey)
    {
        List keys = new ArrayList(secRing.keys);
        boolean found = false;
        for(int i = 0; i < keys.size(); i++)
        {
            PGPSecretKey key = (PGPSecretKey)keys.get(i);
            if(key.getKeyID() == secKey.getKeyID())
            {
                found = true;
                keys.remove(i);
            }
        }

        if(!found)
            return null;
        else
            return new PGPSecretKeyRing(keys, secRing.extraPubKeys);
    }

    List keys;
    List extraPubKeys;
}
