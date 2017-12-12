// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PGPOnePassSignature.java

package co.org.bouncy.openpgp;

import co.org.bouncy.bcpg.*;
import co.org.bouncy.openpgp.operator.PGPContentVerifier;
import co.org.bouncy.openpgp.operator.PGPContentVerifierBuilder;
import co.org.bouncy.openpgp.operator.PGPContentVerifierBuilderProvider;
import co.org.bouncy.openpgp.operator.jcajce.JcaPGPContentVerifierBuilderProvider;
import java.io.*;
import java.security.*;

// Referenced classes of package co.org.bouncy.openpgp:
//            PGPException, PGPUtil, PGPSignature, PGPPublicKey

public class PGPOnePassSignature
{

    PGPOnePassSignature(BCPGInputStream pIn)
        throws IOException, PGPException
    {
        this((OnePassSignaturePacket)pIn.readPacket());
    }

    PGPOnePassSignature(OnePassSignaturePacket sigPack)
        throws PGPException
    {
        this.sigPack = sigPack;
        signatureType = sigPack.getSignatureType();
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
        PGPContentVerifierBuilder verifierBuilder = verifierBuilderProvider.get(sigPack.getKeyAlgorithm(), sigPack.getHashAlgorithm());
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
        if(signatureType == 1)
        {
            for(int i = 0; i != bytes.length; i++)
                update(bytes[i]);

        } else
        {
            blockUpdate(bytes, 0, bytes.length);
        }
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

    public boolean verify(PGPSignature pgpSig)
        throws PGPException, SignatureException
    {
        try
        {
            sigOut.write(pgpSig.getSignatureTrailer());
            sigOut.close();
        }
        catch(IOException e)
        {
            throw new PGPException((new StringBuilder()).append("unable to add trailer: ").append(e.getMessage()).toString(), e);
        }
        return verifier.verify(pgpSig.getSignature());
    }

    public long getKeyID()
    {
        return sigPack.getKeyID();
    }

    public int getSignatureType()
    {
        return sigPack.getSignatureType();
    }

    public int getHashAlgorithm()
    {
        return sigPack.getHashAlgorithm();
    }

    public int getKeyAlgorithm()
    {
        return sigPack.getKeyAlgorithm();
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
        out.writePacket(sigPack);
    }

    private OnePassSignaturePacket sigPack;
    private int signatureType;
    private PGPContentVerifier verifier;
    private byte lastb;
    private OutputStream sigOut;
}
