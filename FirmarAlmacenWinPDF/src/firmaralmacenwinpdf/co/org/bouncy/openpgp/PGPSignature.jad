// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PGPSignature.java

package co.org.bouncy.openpgp;

import co.org.bouncy.asn1.*;
import co.org.bouncy.bcpg.*;
import co.org.bouncy.openpgp.operator.PGPContentVerifier;
import co.org.bouncy.openpgp.operator.PGPContentVerifierBuilder;
import co.org.bouncy.openpgp.operator.PGPContentVerifierBuilderProvider;
import co.org.bouncy.openpgp.operator.jcajce.JcaPGPContentVerifierBuilderProvider;
import co.org.bouncy.util.BigIntegers;
import co.org.bouncy.util.Strings;
import java.io.*;
import java.security.*;
import java.util.Date;

// Referenced classes of package co.org.bouncy.openpgp:
//            PGPException, PGPSignatureSubpacketVector, PGPUserAttributeSubpacketVector, PGPPublicKey, 
//            PGPUtil

public class PGPSignature
{

    PGPSignature(BCPGInputStream pIn)
        throws IOException, PGPException
    {
        this((SignaturePacket)pIn.readPacket());
    }

    PGPSignature(SignaturePacket sigPacket)
        throws PGPException
    {
        sigPck = sigPacket;
        signatureType = sigPck.getSignatureType();
        trustPck = null;
    }

    PGPSignature(SignaturePacket sigPacket, TrustPacket trustPacket)
        throws PGPException
    {
        this(sigPacket);
        trustPck = trustPacket;
    }

    public int getVersion()
    {
        return sigPck.getVersion();
    }

    public int getKeyAlgorithm()
    {
        return sigPck.getKeyAlgorithm();
    }

    public int getHashAlgorithm()
    {
        return sigPck.getHashAlgorithm();
    }

    /**
     * @deprecated Method initVerify is deprecated
     */

    public void initVerify(PGPPublicKey pubKey, String provider)
        throws NoSuchProviderException, PGPException
    {
        initVerify(pubKey, PGPUtil.getProvider(provider));
    }

    /**
     * @deprecated Method initVerify is deprecated
     */

    public void initVerify(PGPPublicKey pubKey, Provider provider)
        throws PGPException
    {
        init((new JcaPGPContentVerifierBuilderProvider()).setProvider(provider), pubKey);
    }

    public void init(PGPContentVerifierBuilderProvider verifierBuilderProvider, PGPPublicKey pubKey)
        throws PGPException
    {
        PGPContentVerifierBuilder verifierBuilder = verifierBuilderProvider.get(sigPck.getKeyAlgorithm(), sigPck.getHashAlgorithm());
        verifier = verifierBuilder.build(pubKey);
        lastb = 0;
        sigOut = verifier.getOutputStream();
    }

    public void update(byte b)
        throws SignatureException
    {
        if(signatureType == 1)
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

    public void update(byte bytes[])
        throws SignatureException
    {
        update(bytes, 0, bytes.length);
    }

    public void update(byte bytes[], int off, int length)
        throws SignatureException
    {
        if(signatureType == 1)
        {
            int finish = off + length;
            for(int i = off; i != finish; i++)
                update(bytes[i]);

        } else
        {
            blockUpdate(bytes, off, length);
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

    public boolean verify()
        throws PGPException, SignatureException
    {
        try
        {
            sigOut.write(getSignatureTrailer());
            sigOut.close();
        }
        catch(IOException e)
        {
            throw new SignatureException(e.getMessage());
        }
        return verifier.verify(getSignature());
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

    public boolean verifyCertification(PGPUserAttributeSubpacketVector userAttributes, PGPPublicKey key)
        throws PGPException, SignatureException
    {
        if(verifier == null)
            throw new PGPException("PGPSignature not initialised - call init().");
        updateWithPublicKey(key);
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
        addTrailer();
        return verifier.verify(getSignature());
    }

    public boolean verifyCertification(String id, PGPPublicKey key)
        throws PGPException, SignatureException
    {
        if(verifier == null)
        {
            throw new PGPException("PGPSignature not initialised - call init().");
        } else
        {
            updateWithPublicKey(key);
            updateWithIdData(180, Strings.toUTF8ByteArray(id));
            addTrailer();
            return verifier.verify(getSignature());
        }
    }

    public boolean verifyCertification(PGPPublicKey masterKey, PGPPublicKey pubKey)
        throws SignatureException, PGPException
    {
        if(verifier == null)
        {
            throw new PGPException("PGPSignature not initialised - call init().");
        } else
        {
            updateWithPublicKey(masterKey);
            updateWithPublicKey(pubKey);
            addTrailer();
            return verifier.verify(getSignature());
        }
    }

    private void addTrailer()
        throws SignatureException
    {
        try
        {
            sigOut.write(sigPck.getSignatureTrailer());
            sigOut.close();
        }
        catch(IOException e)
        {
            throw new SignatureException(e.getMessage());
        }
    }

    public boolean verifyCertification(PGPPublicKey pubKey)
        throws SignatureException, PGPException
    {
        if(verifier == null)
            throw new PGPException("PGPSignature not initialised - call init().");
        if(getSignatureType() != 32 && getSignatureType() != 40)
        {
            throw new PGPException("signature is not a key signature");
        } else
        {
            updateWithPublicKey(pubKey);
            addTrailer();
            return verifier.verify(getSignature());
        }
    }

    public int getSignatureType()
    {
        return sigPck.getSignatureType();
    }

    public long getKeyID()
    {
        return sigPck.getKeyID();
    }

    public Date getCreationTime()
    {
        return new Date(sigPck.getCreationTime());
    }

    public byte[] getSignatureTrailer()
    {
        return sigPck.getSignatureTrailer();
    }

    public boolean hasSubpackets()
    {
        return sigPck.getHashedSubPackets() != null || sigPck.getUnhashedSubPackets() != null;
    }

    public PGPSignatureSubpacketVector getHashedSubPackets()
    {
        return createSubpacketVector(sigPck.getHashedSubPackets());
    }

    public PGPSignatureSubpacketVector getUnhashedSubPackets()
    {
        return createSubpacketVector(sigPck.getUnhashedSubPackets());
    }

    private PGPSignatureSubpacketVector createSubpacketVector(SignatureSubpacket pcks[])
    {
        if(pcks != null)
            return new PGPSignatureSubpacketVector(pcks);
        else
            return null;
    }

    public byte[] getSignature()
        throws PGPException
    {
        MPInteger sigValues[] = sigPck.getSignature();
        byte signature[];
        if(sigValues != null)
        {
            if(sigValues.length == 1)
                signature = BigIntegers.asUnsignedByteArray(sigValues[0].getValue());
            else
                try
                {
                    ASN1EncodableVector v = new ASN1EncodableVector();
                    v.add(new DERInteger(sigValues[0].getValue()));
                    v.add(new DERInteger(sigValues[1].getValue()));
                    signature = (new DERSequence(v)).getEncoded();
                }
                catch(IOException e)
                {
                    throw new PGPException("exception encoding DSA sig.", e);
                }
        } else
        {
            signature = sigPck.getSignatureBytes();
        }
        return signature;
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
        out.writePacket(sigPck);
        if(trustPck != null)
            out.writePacket(trustPck);
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

    public static final int BINARY_DOCUMENT = 0;
    public static final int CANONICAL_TEXT_DOCUMENT = 1;
    public static final int STAND_ALONE = 2;
    public static final int DEFAULT_CERTIFICATION = 16;
    public static final int NO_CERTIFICATION = 17;
    public static final int CASUAL_CERTIFICATION = 18;
    public static final int POSITIVE_CERTIFICATION = 19;
    public static final int SUBKEY_BINDING = 24;
    public static final int PRIMARYKEY_BINDING = 25;
    public static final int DIRECT_KEY = 31;
    public static final int KEY_REVOCATION = 32;
    public static final int SUBKEY_REVOCATION = 40;
    public static final int CERTIFICATION_REVOCATION = 48;
    public static final int TIMESTAMP = 64;
    private SignaturePacket sigPck;
    private int signatureType;
    private TrustPacket trustPck;
    private PGPContentVerifier verifier;
    private byte lastb;
    private OutputStream sigOut;
}
