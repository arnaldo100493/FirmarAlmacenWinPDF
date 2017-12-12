// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PGPSignatureGenerator.java

package co.org.bouncy.openpgp;

import co.org.bouncy.bcpg.*;
import co.org.bouncy.bcpg.sig.IssuerKeyID;
import co.org.bouncy.bcpg.sig.SignatureCreationTime;
import co.org.bouncy.openpgp.operator.PGPContentSigner;
import co.org.bouncy.openpgp.operator.PGPContentSignerBuilder;
import co.org.bouncy.openpgp.operator.jcajce.JcaPGPContentSignerBuilder;
import co.org.bouncy.util.Strings;
import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.util.Date;

// Referenced classes of package co.org.bouncy.openpgp:
//            PGPException, PGPOnePassSignature, PGPSignature, PGPUserAttributeSubpacketVector, 
//            PGPPublicKey, PGPUtil, PGPSignatureSubpacketVector, PGPPrivateKey

public class PGPSignatureGenerator
{

    /**
     * @deprecated Method PGPSignatureGenerator is deprecated
     */

    public PGPSignatureGenerator(int keyAlgorithm, int hashAlgorithm, String provider)
        throws NoSuchAlgorithmException, NoSuchProviderException, PGPException
    {
        this(keyAlgorithm, provider, hashAlgorithm, provider);
    }

    /**
     * @deprecated Method PGPSignatureGenerator is deprecated
     */

    public PGPSignatureGenerator(int keyAlgorithm, int hashAlgorithm, Provider provider)
        throws NoSuchAlgorithmException, PGPException
    {
        this(keyAlgorithm, provider, hashAlgorithm, provider);
    }

    /**
     * @deprecated Method PGPSignatureGenerator is deprecated
     */

    public PGPSignatureGenerator(int keyAlgorithm, String sigProvider, int hashAlgorithm, String digProvider)
        throws NoSuchAlgorithmException, NoSuchProviderException, PGPException
    {
        this(keyAlgorithm, PGPUtil.getProvider(sigProvider), hashAlgorithm, PGPUtil.getProvider(digProvider));
    }

    /**
     * @deprecated Method PGPSignatureGenerator is deprecated
     */

    public PGPSignatureGenerator(int keyAlgorithm, Provider sigProvider, int hashAlgorithm, Provider digProvider)
        throws NoSuchAlgorithmException, PGPException
    {
        unhashed = new SignatureSubpacket[0];
        hashed = new SignatureSubpacket[0];
        providedKeyAlgorithm = -1;
        providedKeyAlgorithm = keyAlgorithm;
        contentSignerBuilder = (new JcaPGPContentSignerBuilder(keyAlgorithm, hashAlgorithm)).setProvider(sigProvider).setDigestProvider(digProvider);
    }

    public PGPSignatureGenerator(PGPContentSignerBuilder contentSignerBuilder)
    {
        unhashed = new SignatureSubpacket[0];
        hashed = new SignatureSubpacket[0];
        providedKeyAlgorithm = -1;
        this.contentSignerBuilder = contentSignerBuilder;
    }

    /**
     * @deprecated Method initSign is deprecated
     */

    public void initSign(int signatureType, PGPPrivateKey key)
        throws PGPException
    {
        contentSigner = contentSignerBuilder.build(signatureType, key);
        sigOut = contentSigner.getOutputStream();
        sigType = contentSigner.getType();
        lastb = 0;
        if(providedKeyAlgorithm >= 0 && providedKeyAlgorithm != contentSigner.getKeyAlgorithm())
            throw new PGPException("key algorithm mismatch");
        else
            return;
    }

    public void init(int signatureType, PGPPrivateKey key)
        throws PGPException
    {
        contentSigner = contentSignerBuilder.build(signatureType, key);
        sigOut = contentSigner.getOutputStream();
        sigType = contentSigner.getType();
        lastb = 0;
        if(providedKeyAlgorithm >= 0 && providedKeyAlgorithm != contentSigner.getKeyAlgorithm())
            throw new PGPException("key algorithm mismatch");
        else
            return;
    }

    /**
     * @deprecated Method initSign is deprecated
     */

    public void initSign(int signatureType, PGPPrivateKey key, SecureRandom random)
        throws PGPException
    {
        initSign(signatureType, key);
    }

    public void update(byte b)
        throws SignatureException
    {
        if(sigType == 1)
        {
            if(b == 13)
            {
                byteUpdate((byte)13);
                byteUpdate((byte)10);
            } else
            if(b == 10)
            {
                if(lastb != 13)
                {
                    byteUpdate((byte)13);
                    byteUpdate((byte)10);
                }
            } else
            {
                byteUpdate(b);
            }
            lastb = b;
        } else
        {
            byteUpdate(b);
        }
    }

    public void update(byte b[])
        throws SignatureException
    {
        update(b, 0, b.length);
    }

    public void update(byte b[], int off, int len)
        throws SignatureException
    {
        if(sigType == 1)
        {
            int finish = off + len;
            for(int i = off; i != finish; i++)
                update(b[i]);

        } else
        {
            blockUpdate(b, off, len);
        }
    }

    private void byteUpdate(byte b)
        throws SignatureException
    {
        try
        {
            sigOut.write(b);
        }
        catch(IOException e)
        {
            throw new SignatureException(e.getMessage());
        }
    }

    private void blockUpdate(byte block[], int off, int len)
        throws SignatureException
    {
        try
        {
            sigOut.write(block, off, len);
        }
        catch(IOException e)
        {
            throw new IllegalStateException(e.getMessage());
        }
    }

    public void setHashedSubpackets(PGPSignatureSubpacketVector hashedPcks)
    {
        if(hashedPcks == null)
        {
            hashed = new SignatureSubpacket[0];
            return;
        } else
        {
            hashed = hashedPcks.toSubpacketArray();
            return;
        }
    }

    public void setUnhashedSubpackets(PGPSignatureSubpacketVector unhashedPcks)
    {
        if(unhashedPcks == null)
        {
            unhashed = new SignatureSubpacket[0];
            return;
        } else
        {
            unhashed = unhashedPcks.toSubpacketArray();
            return;
        }
    }

    public PGPOnePassSignature generateOnePassVersion(boolean isNested)
        throws PGPException
    {
        return new PGPOnePassSignature(new OnePassSignaturePacket(sigType, contentSigner.getHashAlgorithm(), contentSigner.getKeyAlgorithm(), contentSigner.getKeyID(), isNested));
    }

    public PGPSignature generate()
        throws PGPException, SignatureException
    {
        int version = 4;
        ByteArrayOutputStream sOut = new ByteArrayOutputStream();
        SignatureSubpacket hPkts[];
        if(!packetPresent(hashed, 2))
            hPkts = insertSubpacket(hashed, new SignatureCreationTime(false, new Date()));
        else
            hPkts = hashed;
        SignatureSubpacket unhPkts[];
        if(!packetPresent(hashed, 16) && !packetPresent(unhashed, 16))
            unhPkts = insertSubpacket(unhashed, new IssuerKeyID(false, contentSigner.getKeyID()));
        else
            unhPkts = unhashed;
        try
        {
            sOut.write((byte)version);
            sOut.write((byte)sigType);
            sOut.write((byte)contentSigner.getKeyAlgorithm());
            sOut.write((byte)contentSigner.getHashAlgorithm());
            ByteArrayOutputStream hOut = new ByteArrayOutputStream();
            for(int i = 0; i != hPkts.length; i++)
                hPkts[i].encode(hOut);

            byte data[] = hOut.toByteArray();
            sOut.write((byte)(data.length >> 8));
            sOut.write((byte)data.length);
            sOut.write(data);
        }
        catch(IOException e)
        {
            throw new PGPException("exception encoding hashed data.", e);
        }
        byte hData[] = sOut.toByteArray();
        sOut.write((byte)version);
        sOut.write(-1);
        sOut.write((byte)(hData.length >> 24));
        sOut.write((byte)(hData.length >> 16));
        sOut.write((byte)(hData.length >> 8));
        sOut.write((byte)hData.length);
        byte trailer[] = sOut.toByteArray();
        blockUpdate(trailer, 0, trailer.length);
        MPInteger sigValues[];
        if(contentSigner.getKeyAlgorithm() == 3 || contentSigner.getKeyAlgorithm() == 1)
        {
            sigValues = new MPInteger[1];
            sigValues[0] = new MPInteger(new BigInteger(1, contentSigner.getSignature()));
        } else
        {
            sigValues = PGPUtil.dsaSigToMpi(contentSigner.getSignature());
        }
        byte digest[] = contentSigner.getDigest();
        byte fingerPrint[] = new byte[2];
        fingerPrint[0] = digest[0];
        fingerPrint[1] = digest[1];
        return new PGPSignature(new SignaturePacket(sigType, contentSigner.getKeyID(), contentSigner.getKeyAlgorithm(), contentSigner.getHashAlgorithm(), hPkts, unhPkts, fingerPrint, sigValues));
    }

    public PGPSignature generateCertification(String id, PGPPublicKey pubKey)
        throws SignatureException, PGPException
    {
        updateWithPublicKey(pubKey);
        updateWithIdData(180, Strings.toUTF8ByteArray(id));
        return generate();
    }

    public PGPSignature generateCertification(PGPUserAttributeSubpacketVector userAttributes, PGPPublicKey pubKey)
        throws SignatureException, PGPException
    {
        updateWithPublicKey(pubKey);
        try
        {
            ByteArrayOutputStream bOut = new ByteArrayOutputStream();
            UserAttributeSubpacket packets[] = userAttributes.toSubpacketArray();
            for(int i = 0; i != packets.length; i++)
                packets[i].encode(bOut);

            updateWithIdData(209, bOut.toByteArray());
        }
        catch(IOException e)
        {
            throw new PGPException("cannot encode subpacket array", e);
        }
        return generate();
    }

    public PGPSignature generateCertification(PGPPublicKey masterKey, PGPPublicKey pubKey)
        throws SignatureException, PGPException
    {
        updateWithPublicKey(masterKey);
        updateWithPublicKey(pubKey);
        return generate();
    }

    public PGPSignature generateCertification(PGPPublicKey pubKey)
        throws SignatureException, PGPException
    {
        updateWithPublicKey(pubKey);
        return generate();
    }

    private byte[] getEncodedPublicKey(PGPPublicKey pubKey)
        throws PGPException
    {
        byte keyBytes[];
        try
        {
            keyBytes = pubKey.publicPk.getEncodedContents();
        }
        catch(IOException e)
        {
            throw new PGPException("exception preparing key.", e);
        }
        return keyBytes;
    }

    private boolean packetPresent(SignatureSubpacket packets[], int type)
    {
        for(int i = 0; i != packets.length; i++)
            if(packets[i].getType() == type)
                return true;

        return false;
    }

    private SignatureSubpacket[] insertSubpacket(SignatureSubpacket packets[], SignatureSubpacket subpacket)
    {
        SignatureSubpacket tmp[] = new SignatureSubpacket[packets.length + 1];
        tmp[0] = subpacket;
        System.arraycopy(packets, 0, tmp, 1, packets.length);
        return tmp;
    }

    private void updateWithIdData(int header, byte idBytes[])
        throws SignatureException
    {
        update((byte)header);
        update((byte)(idBytes.length >> 24));
        update((byte)(idBytes.length >> 16));
        update((byte)(idBytes.length >> 8));
        update((byte)idBytes.length);
        update(idBytes);
    }

    private void updateWithPublicKey(PGPPublicKey key)
        throws PGPException, SignatureException
    {
        byte keyBytes[] = getEncodedPublicKey(key);
        update((byte)-103);
        update((byte)(keyBytes.length >> 8));
        update((byte)keyBytes.length);
        update(keyBytes);
    }

    private SignatureSubpacket unhashed[];
    private SignatureSubpacket hashed[];
    private OutputStream sigOut;
    private PGPContentSignerBuilder contentSignerBuilder;
    private PGPContentSigner contentSigner;
    private int sigType;
    private byte lastb;
    private int providedKeyAlgorithm;
}
