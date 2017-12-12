// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PGPPublicKeyRing.java

package co.org.bouncy.openpgp;

import co.org.bouncy.bcpg.*;
import co.org.bouncy.openpgp.operator.KeyFingerPrintCalculator;
import co.org.bouncy.openpgp.operator.jcajce.JcaKeyFingerprintCalculator;
import java.io.*;
import java.util.*;

// Referenced classes of package co.org.bouncy.openpgp:
//            PGPKeyRing, PGPPublicKey, PGPException

public class PGPPublicKeyRing extends PGPKeyRing
{

    /**
     * @deprecated Method PGPPublicKeyRing is deprecated
     */

    public PGPPublicKeyRing(byte encoding[])
        throws IOException
    {
        this(((InputStream) (new ByteArrayInputStream(encoding))), ((KeyFingerPrintCalculator) (new JcaKeyFingerprintCalculator())));
    }

    public PGPPublicKeyRing(byte encoding[], KeyFingerPrintCalculator fingerPrintCalculator)
        throws IOException
    {
        this(((InputStream) (new ByteArrayInputStream(encoding))), fingerPrintCalculator);
    }

    PGPPublicKeyRing(List pubKeys)
    {
        keys = pubKeys;
    }

    /**
     * @deprecated Method PGPPublicKeyRing is deprecated
     */

    public PGPPublicKeyRing(InputStream in)
        throws IOException
    {
        this(in, ((KeyFingerPrintCalculator) (new JcaKeyFingerprintCalculator())));
    }

    public PGPPublicKeyRing(InputStream in, KeyFingerPrintCalculator fingerPrintCalculator)
        throws IOException
    {
        keys = new ArrayList();
        BCPGInputStream pIn = wrap(in);
        int initialTag = pIn.nextPacketTag();
        if(initialTag != 6 && initialTag != 14)
            throw new IOException((new StringBuilder()).append("public key ring doesn't start with public key tag: tag 0x").append(Integer.toHexString(initialTag)).toString());
        PublicKeyPacket pubPk = (PublicKeyPacket)pIn.readPacket();
        TrustPacket trustPk = readOptionalTrustPacket(pIn);
        List keySigs = readSignaturesAndTrust(pIn);
        List ids = new ArrayList();
        List idTrusts = new ArrayList();
        List idSigs = new ArrayList();
        readUserIDs(pIn, ids, idTrusts, idSigs);
        try
        {
            keys.add(new PGPPublicKey(pubPk, trustPk, keySigs, ids, idTrusts, idSigs, fingerPrintCalculator));
            for(; pIn.nextPacketTag() == 14; keys.add(readSubkey(pIn, fingerPrintCalculator)));
        }
        catch(PGPException e)
        {
            throw new IOException((new StringBuilder()).append("processing exception: ").append(e.toString()).toString());
        }
    }

    public PGPPublicKey getPublicKey()
    {
        return (PGPPublicKey)keys.get(0);
    }

    public PGPPublicKey getPublicKey(long keyID)
    {
        for(int i = 0; i != keys.size(); i++)
        {
            PGPPublicKey k = (PGPPublicKey)keys.get(i);
            if(keyID == k.getKeyID())
                return k;
        }

        return null;
    }

    public Iterator getPublicKeys()
    {
        return Collections.unmodifiableList(keys).iterator();
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
            PGPPublicKey k = (PGPPublicKey)keys.get(i);
            k.encode(outStream);
        }

    }

    public static PGPPublicKeyRing insertPublicKey(PGPPublicKeyRing pubRing, PGPPublicKey pubKey)
    {
        List keys = new ArrayList(pubRing.keys);
        boolean found = false;
        boolean masterFound = false;
        for(int i = 0; i != keys.size(); i++)
        {
            PGPPublicKey key = (PGPPublicKey)keys.get(i);
            if(key.getKeyID() == pubKey.getKeyID())
            {
                found = true;
                keys.set(i, pubKey);
            }
            if(key.isMasterKey())
                masterFound = true;
        }

        if(!found)
            if(pubKey.isMasterKey())
            {
                if(masterFound)
                    throw new IllegalArgumentException("cannot add a master key to a ring that already has one");
                keys.add(0, pubKey);
            } else
            {
                keys.add(pubKey);
            }
        return new PGPPublicKeyRing(keys);
    }

    public static PGPPublicKeyRing removePublicKey(PGPPublicKeyRing pubRing, PGPPublicKey pubKey)
    {
        List keys = new ArrayList(pubRing.keys);
        boolean found = false;
        for(int i = 0; i < keys.size(); i++)
        {
            PGPPublicKey key = (PGPPublicKey)keys.get(i);
            if(key.getKeyID() == pubKey.getKeyID())
            {
                found = true;
                keys.remove(i);
            }
        }

        if(!found)
            return null;
        else
            return new PGPPublicKeyRing(keys);
    }

    static PGPPublicKey readSubkey(BCPGInputStream in, KeyFingerPrintCalculator fingerPrintCalculator)
        throws IOException, PGPException
    {
        PublicKeyPacket pk = (PublicKeyPacket)in.readPacket();
        TrustPacket kTrust = readOptionalTrustPacket(in);
        List sigList = readSignaturesAndTrust(in);
        return new PGPPublicKey(pk, kTrust, sigList, fingerPrintCalculator);
    }

    List keys;
}
