// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PGPPublicKey.java

package co.org.bouncy.openpgp;

import co.org.bouncy.bcpg.*;
import co.org.bouncy.openpgp.operator.KeyFingerPrintCalculator;
import co.org.bouncy.openpgp.operator.jcajce.JcaPGPKeyConverter;
import co.org.bouncy.util.Arrays;
import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.util.*;

// Referenced classes of package co.org.bouncy.openpgp:
//            PGPSignature, PGPUserAttributeSubpacketVector, PGPException, PGPSignatureSubpacketVector

public class PGPPublicKey
    implements PublicKeyAlgorithmTags
{

    private void init(KeyFingerPrintCalculator fingerPrintCalculator)
        throws PGPException
    {
        BCPGKey key = publicPk.getKey();
        fingerprint = fingerPrintCalculator.calculateFingerprint(publicPk);
        if(publicPk.getVersion() <= 3)
        {
            RSAPublicBCPGKey rK = (RSAPublicBCPGKey)key;
            keyID = rK.getModulus().longValue();
            keyStrength = rK.getModulus().bitLength();
        } else
        {
            keyID = (long)(fingerprint[fingerprint.length - 8] & 0xff) << 56 | (long)(fingerprint[fingerprint.length - 7] & 0xff) << 48 | (long)(fingerprint[fingerprint.length - 6] & 0xff) << 40 | (long)(fingerprint[fingerprint.length - 5] & 0xff) << 32 | (long)(fingerprint[fingerprint.length - 4] & 0xff) << 24 | (long)(fingerprint[fingerprint.length - 3] & 0xff) << 16 | (long)(fingerprint[fingerprint.length - 2] & 0xff) << 8 | (long)(fingerprint[fingerprint.length - 1] & 0xff);
            if(key instanceof RSAPublicBCPGKey)
                keyStrength = ((RSAPublicBCPGKey)key).getModulus().bitLength();
            else
            if(key instanceof DSAPublicBCPGKey)
                keyStrength = ((DSAPublicBCPGKey)key).getP().bitLength();
            else
            if(key instanceof ElGamalPublicBCPGKey)
                keyStrength = ((ElGamalPublicBCPGKey)key).getP().bitLength();
        }
    }

    /**
     * @deprecated Method PGPPublicKey is deprecated
     */

    public PGPPublicKey(int algorithm, PublicKey pubKey, Date time, String provider)
        throws PGPException, NoSuchProviderException
    {
        this((new JcaPGPKeyConverter()).setProvider(provider).getPGPPublicKey(algorithm, pubKey, time));
    }

    /**
     * @deprecated Method PGPPublicKey is deprecated
     */

    public PGPPublicKey(int algorithm, PublicKey pubKey, Date time)
        throws PGPException
    {
        this((new JcaPGPKeyConverter()).getPGPPublicKey(algorithm, pubKey, time));
    }

    public PGPPublicKey(PublicKeyPacket publicKeyPacket, KeyFingerPrintCalculator fingerPrintCalculator)
        throws PGPException
    {
        keySigs = new ArrayList();
        ids = new ArrayList();
        idTrusts = new ArrayList();
        idSigs = new ArrayList();
        subSigs = null;
        publicPk = publicKeyPacket;
        ids = new ArrayList();
        idSigs = new ArrayList();
        init(fingerPrintCalculator);
    }

    PGPPublicKey(PublicKeyPacket publicPk, TrustPacket trustPk, List sigs, KeyFingerPrintCalculator fingerPrintCalculator)
        throws PGPException
    {
        keySigs = new ArrayList();
        ids = new ArrayList();
        idTrusts = new ArrayList();
        idSigs = new ArrayList();
        subSigs = null;
        this.publicPk = publicPk;
        this.trustPk = trustPk;
        subSigs = sigs;
        init(fingerPrintCalculator);
    }

    PGPPublicKey(PGPPublicKey key, TrustPacket trust, List subSigs)
    {
        keySigs = new ArrayList();
        ids = new ArrayList();
        idTrusts = new ArrayList();
        idSigs = new ArrayList();
        this.subSigs = null;
        publicPk = key.publicPk;
        trustPk = trust;
        this.subSigs = subSigs;
        fingerprint = key.fingerprint;
        keyID = key.keyID;
        keyStrength = key.keyStrength;
    }

    PGPPublicKey(PGPPublicKey pubKey)
    {
        keySigs = new ArrayList();
        ids = new ArrayList();
        idTrusts = new ArrayList();
        idSigs = new ArrayList();
        subSigs = null;
        publicPk = pubKey.publicPk;
        keySigs = new ArrayList(pubKey.keySigs);
        ids = new ArrayList(pubKey.ids);
        idTrusts = new ArrayList(pubKey.idTrusts);
        idSigs = new ArrayList(pubKey.idSigs.size());
        for(int i = 0; i != pubKey.idSigs.size(); i++)
            idSigs.add(new ArrayList((ArrayList)pubKey.idSigs.get(i)));

        if(pubKey.subSigs != null)
        {
            subSigs = new ArrayList(pubKey.subSigs.size());
            for(int i = 0; i != pubKey.subSigs.size(); i++)
                subSigs.add(pubKey.subSigs.get(i));

        }
        fingerprint = pubKey.fingerprint;
        keyID = pubKey.keyID;
        keyStrength = pubKey.keyStrength;
    }

    PGPPublicKey(PublicKeyPacket publicPk, TrustPacket trustPk, List keySigs, List ids, List idTrusts, List idSigs, KeyFingerPrintCalculator fingerPrintCalculator)
        throws PGPException
    {
        this.keySigs = new ArrayList();
        this.ids = new ArrayList();
        this.idTrusts = new ArrayList();
        this.idSigs = new ArrayList();
        subSigs = null;
        this.publicPk = publicPk;
        this.trustPk = trustPk;
        this.keySigs = keySigs;
        this.ids = ids;
        this.idTrusts = idTrusts;
        this.idSigs = idSigs;
        init(fingerPrintCalculator);
    }

    public int getVersion()
    {
        return publicPk.getVersion();
    }

    public Date getCreationTime()
    {
        return publicPk.getTime();
    }

    public int getValidDays()
    {
        if(publicPk.getVersion() > 3)
            return (int)(getValidSeconds() / 0x15180L);
        else
            return publicPk.getValidDays();
    }

    public byte[] getTrustData()
    {
        if(trustPk == null)
            return null;
        else
            return Arrays.clone(trustPk.getLevelAndTrustAmount());
    }

    public long getValidSeconds()
    {
        if(publicPk.getVersion() > 3)
        {
            if(isMasterKey())
            {
                for(int i = 0; i != MASTER_KEY_CERTIFICATION_TYPES.length; i++)
                {
                    long seconds = getExpirationTimeFromSig(true, MASTER_KEY_CERTIFICATION_TYPES[i]);
                    if(seconds >= 0L)
                        return seconds;
                }

            } else
            {
                long seconds = getExpirationTimeFromSig(false, 24);
                if(seconds >= 0L)
                    return seconds;
            }
            return 0L;
        } else
        {
            return (long)publicPk.getValidDays() * 24L * 60L * 60L;
        }
    }

    private long getExpirationTimeFromSig(boolean selfSigned, int signatureType)
    {
        Iterator signatures = getSignaturesOfType(signatureType);
        long expiryTime = -1L;
        while(signatures.hasNext()) 
        {
            PGPSignature sig = (PGPSignature)signatures.next();
            if(!selfSigned || sig.getKeyID() == getKeyID())
            {
                PGPSignatureSubpacketVector hashed = sig.getHashedSubPackets();
                if(hashed != null)
                {
                    long current = hashed.getKeyExpirationTime();
                    if(current == 0L || current > expiryTime)
                        expiryTime = current;
                } else
                {
                    return 0L;
                }
            }
        }
        return expiryTime;
    }

    public long getKeyID()
    {
        return keyID;
    }

    public byte[] getFingerprint()
    {
        byte tmp[] = new byte[fingerprint.length];
        System.arraycopy(fingerprint, 0, tmp, 0, tmp.length);
        return tmp;
    }

    public boolean isEncryptionKey()
    {
        int algorithm = publicPk.getAlgorithm();
        return algorithm == 1 || algorithm == 2 || algorithm == 16 || algorithm == 20;
    }

    public boolean isMasterKey()
    {
        return subSigs == null;
    }

    public int getAlgorithm()
    {
        return publicPk.getAlgorithm();
    }

    public int getBitStrength()
    {
        return keyStrength;
    }

    /**
     * @deprecated Method getKey is deprecated
     */

    public PublicKey getKey(String provider)
        throws PGPException, NoSuchProviderException
    {
        return (new JcaPGPKeyConverter()).setProvider(provider).getPublicKey(this);
    }

    /**
     * @deprecated Method getKey is deprecated
     */

    public PublicKey getKey(Provider provider)
        throws PGPException
    {
        return (new JcaPGPKeyConverter()).setProvider(provider).getPublicKey(this);
    }

    public Iterator getUserIDs()
    {
        List temp = new ArrayList();
        for(int i = 0; i != ids.size(); i++)
            if(ids.get(i) instanceof String)
                temp.add(ids.get(i));

        return temp.iterator();
    }

    public Iterator getUserAttributes()
    {
        List temp = new ArrayList();
        for(int i = 0; i != ids.size(); i++)
            if(ids.get(i) instanceof PGPUserAttributeSubpacketVector)
                temp.add(ids.get(i));

        return temp.iterator();
    }

    public Iterator getSignaturesForID(String id)
    {
        for(int i = 0; i != ids.size(); i++)
            if(id.equals(ids.get(i)))
                return ((ArrayList)idSigs.get(i)).iterator();

        return null;
    }

    public Iterator getSignaturesForUserAttribute(PGPUserAttributeSubpacketVector userAttributes)
    {
        for(int i = 0; i != ids.size(); i++)
            if(userAttributes.equals(ids.get(i)))
                return ((ArrayList)idSigs.get(i)).iterator();

        return null;
    }

    public Iterator getSignaturesOfType(int signatureType)
    {
        List l = new ArrayList();
        Iterator it = getSignatures();
        do
        {
            if(!it.hasNext())
                break;
            PGPSignature sig = (PGPSignature)it.next();
            if(sig.getSignatureType() == signatureType)
                l.add(sig);
        } while(true);
        return l.iterator();
    }

    public Iterator getSignatures()
    {
        if(subSigs == null)
        {
            List sigs = new ArrayList();
            sigs.addAll(keySigs);
            for(int i = 0; i != idSigs.size(); i++)
                sigs.addAll((Collection)idSigs.get(i));

            return sigs.iterator();
        } else
        {
            return subSigs.iterator();
        }
    }

    public PublicKeyPacket getPublicKeyPacket()
    {
        return publicPk;
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
        out.writePacket(publicPk);
        if(trustPk != null)
            out.writePacket(trustPk);
        if(subSigs == null)
        {
            for(int i = 0; i != keySigs.size(); i++)
                ((PGPSignature)keySigs.get(i)).encode(out);

            for(int i = 0; i != ids.size(); i++)
            {
                if(ids.get(i) instanceof String)
                {
                    String id = (String)ids.get(i);
                    out.writePacket(new UserIDPacket(id));
                } else
                {
                    PGPUserAttributeSubpacketVector v = (PGPUserAttributeSubpacketVector)ids.get(i);
                    out.writePacket(new UserAttributePacket(v.toSubpacketArray()));
                }
                if(idTrusts.get(i) != null)
                    out.writePacket((ContainedPacket)idTrusts.get(i));
                List sigs = (List)idSigs.get(i);
                for(int j = 0; j != sigs.size(); j++)
                    ((PGPSignature)sigs.get(j)).encode(out);

            }

        } else
        {
            for(int j = 0; j != subSigs.size(); j++)
                ((PGPSignature)subSigs.get(j)).encode(out);

        }
    }

    public boolean isRevoked()
    {
        int ns = 0;
        boolean revoked = false;
        if(isMasterKey())
            do
            {
                if(revoked || ns >= keySigs.size())
                    break;
                if(((PGPSignature)keySigs.get(ns++)).getSignatureType() == 32)
                    revoked = true;
            } while(true);
        else
            do
            {
                if(revoked || ns >= subSigs.size())
                    break;
                if(((PGPSignature)subSigs.get(ns++)).getSignatureType() == 40)
                    revoked = true;
            } while(true);
        return revoked;
    }

    public static PGPPublicKey addCertification(PGPPublicKey key, String id, PGPSignature certification)
    {
        return addCert(key, id, certification);
    }

    public static PGPPublicKey addCertification(PGPPublicKey key, PGPUserAttributeSubpacketVector userAttributes, PGPSignature certification)
    {
        return addCert(key, userAttributes, certification);
    }

    private static PGPPublicKey addCert(PGPPublicKey key, Object id, PGPSignature certification)
    {
        PGPPublicKey returnKey = new PGPPublicKey(key);
        List sigList = null;
        for(int i = 0; i != returnKey.ids.size(); i++)
            if(id.equals(returnKey.ids.get(i)))
                sigList = (List)returnKey.idSigs.get(i);

        if(sigList != null)
        {
            sigList.add(certification);
        } else
        {
            sigList = new ArrayList();
            sigList.add(certification);
            returnKey.ids.add(id);
            returnKey.idTrusts.add(null);
            returnKey.idSigs.add(sigList);
        }
        return returnKey;
    }

    public static PGPPublicKey removeCertification(PGPPublicKey key, PGPUserAttributeSubpacketVector userAttributes)
    {
        return removeCert(key, userAttributes);
    }

    public static PGPPublicKey removeCertification(PGPPublicKey key, String id)
    {
        return removeCert(key, id);
    }

    private static PGPPublicKey removeCert(PGPPublicKey key, Object id)
    {
        PGPPublicKey returnKey = new PGPPublicKey(key);
        boolean found = false;
        for(int i = 0; i < returnKey.ids.size(); i++)
            if(id.equals(returnKey.ids.get(i)))
            {
                found = true;
                returnKey.ids.remove(i);
                returnKey.idTrusts.remove(i);
                returnKey.idSigs.remove(i);
            }

        if(!found)
            return null;
        else
            return returnKey;
    }

    public static PGPPublicKey removeCertification(PGPPublicKey key, String id, PGPSignature certification)
    {
        return removeCert(key, id, certification);
    }

    public static PGPPublicKey removeCertification(PGPPublicKey key, PGPUserAttributeSubpacketVector userAttributes, PGPSignature certification)
    {
        return removeCert(key, userAttributes, certification);
    }

    private static PGPPublicKey removeCert(PGPPublicKey key, Object id, PGPSignature certification)
    {
        PGPPublicKey returnKey = new PGPPublicKey(key);
        boolean found = false;
        for(int i = 0; i < returnKey.ids.size(); i++)
            if(id.equals(returnKey.ids.get(i)))
                found = ((List)returnKey.idSigs.get(i)).remove(certification);

        if(!found)
            return null;
        else
            return returnKey;
    }

    public static PGPPublicKey addCertification(PGPPublicKey key, PGPSignature certification)
    {
        if(key.isMasterKey())
        {
            if(certification.getSignatureType() == 40)
                throw new IllegalArgumentException("signature type incorrect for master key revocation.");
        } else
        if(certification.getSignatureType() == 32)
            throw new IllegalArgumentException("signature type incorrect for sub-key revocation.");
        PGPPublicKey returnKey = new PGPPublicKey(key);
        if(returnKey.subSigs != null)
            returnKey.subSigs.add(certification);
        else
            returnKey.keySigs.add(certification);
        return returnKey;
    }

    public static PGPPublicKey removeCertification(PGPPublicKey key, PGPSignature certification)
    {
        PGPPublicKey returnKey = new PGPPublicKey(key);
        boolean found;
        if(returnKey.subSigs != null)
            found = returnKey.subSigs.remove(certification);
        else
            found = returnKey.keySigs.remove(certification);
        if(!found)
        {
            for(Iterator it = key.getUserIDs(); it.hasNext();)
            {
                String id = (String)it.next();
                Iterator sIt = key.getSignaturesForID(id);
                while(sIt.hasNext()) 
                    if(certification == sIt.next())
                    {
                        found = true;
                        returnKey = removeCertification(returnKey, id, certification);
                    }
            }

            if(!found)
            {
                for(Iterator it = key.getUserAttributes(); it.hasNext();)
                {
                    PGPUserAttributeSubpacketVector id = (PGPUserAttributeSubpacketVector)it.next();
                    Iterator sIt = key.getSignaturesForUserAttribute(id);
                    while(sIt.hasNext()) 
                        if(certification == sIt.next())
                        {
                            found = true;
                            returnKey = removeCertification(returnKey, id, certification);
                        }
                }

            }
        }
        return returnKey;
    }

    private static final int MASTER_KEY_CERTIFICATION_TYPES[] = {
        19, 18, 17, 16
    };
    PublicKeyPacket publicPk;
    TrustPacket trustPk;
    List keySigs;
    List ids;
    List idTrusts;
    List idSigs;
    List subSigs;
    private long keyID;
    private byte fingerprint[];
    private int keyStrength;

}
