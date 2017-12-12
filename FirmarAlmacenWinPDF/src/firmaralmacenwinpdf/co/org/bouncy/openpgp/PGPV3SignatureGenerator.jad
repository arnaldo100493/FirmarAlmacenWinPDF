// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PGPV3SignatureGenerator.java

package co.org.bouncy.openpgp;

import co.org.bouncy.bcpg.*;
import co.org.bouncy.openpgp.operator.PGPContentSigner;
import co.org.bouncy.openpgp.operator.PGPContentSignerBuilder;
import co.org.bouncy.openpgp.operator.jcajce.JcaPGPContentSignerBuilder;
import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.util.Date;

// Referenced classes of package co.org.bouncy.openpgp:
//            PGPException, PGPOnePassSignature, PGPSignature, PGPUtil, 
//            PGPPrivateKey

public class PGPV3SignatureGenerator
{

    /**
     * @deprecated Method PGPV3SignatureGenerator is deprecated
     */

    public PGPV3SignatureGenerator(int keyAlgorithm, int hashAlgorithm, String provider)
        throws NoSuchAlgorithmException, NoSuchProviderException, PGPException
    {
        this(keyAlgorithm, hashAlgorithm, PGPUtil.getProvider(provider));
    }

    /**
     * @deprecated Method PGPV3SignatureGenerator is deprecated
     */

    public PGPV3SignatureGenerator(int keyAlgorithm, int hashAlgorithm, Provider provider)
        throws NoSuchAlgorithmException, PGPException
    {
        providedKeyAlgorithm = -1;
        providedKeyAlgorithm = keyAlgorithm;
        contentSignerBuilder = (new JcaPGPContentSignerBuilder(keyAlgorithm, hashAlgorithm)).setProvider(provider);
    }

    public PGPV3SignatureGenerator(PGPContentSignerBuilder contentSignerBuilder)
    {
        providedKeyAlgorithm = -1;
        this.contentSignerBuilder = contentSignerBuilder;
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
        init(signatureType, key);
    }

    /**
     * @deprecated Method initSign is deprecated
     */

    public void initSign(int signatureType, PGPPrivateKey key)
        throws PGPException
    {
        init(signatureType, key);
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
            throw new IllegalStateException("unable to update signature");
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
            throw new IllegalStateException("unable to update signature");
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
        long creationTime = (new Date()).getTime() / 1000L;
        ByteArrayOutputStream sOut = new ByteArrayOutputStream();
        sOut.write(sigType);
        sOut.write((byte)(int)(creationTime >> 24));
        sOut.write((byte)(int)(creationTime >> 16));
        sOut.write((byte)(int)(creationTime >> 8));
        sOut.write((byte)(int)creationTime);
        byte hData[] = sOut.toByteArray();
        blockUpdate(hData, 0, hData.length);
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
        return new PGPSignature(new SignaturePacket(3, contentSigner.getType(), contentSigner.getKeyID(), contentSigner.getKeyAlgorithm(), contentSigner.getHashAlgorithm(), creationTime * 1000L, fingerPrint, sigValues));
    }

    private byte lastb;
    private OutputStream sigOut;
    private PGPContentSignerBuilder contentSignerBuilder;
    private PGPContentSigner contentSigner;
    private int sigType;
    private int providedKeyAlgorithm;
}
