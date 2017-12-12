// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PGPPublicKeyEncryptedData.java

package co.org.bouncy.openpgp;

import co.org.bouncy.bcpg.*;
import co.org.bouncy.openpgp.operator.PGPDataDecryptor;
import co.org.bouncy.openpgp.operator.PGPDigestCalculator;
import co.org.bouncy.openpgp.operator.PublicKeyDataDecryptorFactory;
import co.org.bouncy.openpgp.operator.jcajce.JcePublicKeyDataDecryptorFactoryBuilder;
import co.org.bouncy.util.io.TeeInputStream;
import java.io.EOFException;
import java.io.InputStream;
import java.security.NoSuchProviderException;
import java.security.Provider;

// Referenced classes of package co.org.bouncy.openpgp:
//            PGPEncryptedData, PGPKeyValidationException, PGPException, PGPUtil, 
//            PGPPrivateKey

public class PGPPublicKeyEncryptedData extends PGPEncryptedData
{

    PGPPublicKeyEncryptedData(PublicKeyEncSessionPacket keyData, InputStreamPacket encData)
    {
        super(encData);
        this.keyData = keyData;
    }

    private boolean confirmCheckSum(byte sessionInfo[])
    {
        int check = 0;
        for(int i = 1; i != sessionInfo.length - 2; i++)
            check += sessionInfo[i] & 0xff;

        return sessionInfo[sessionInfo.length - 2] == (byte)(check >> 8) && sessionInfo[sessionInfo.length - 1] == (byte)check;
    }

    public long getKeyID()
    {
        return keyData.getKeyID();
    }

    /**
     * @deprecated Method getSymmetricAlgorithm is deprecated
     */

    public int getSymmetricAlgorithm(PGPPrivateKey privKey, String provider)
        throws PGPException, NoSuchProviderException
    {
        return getSymmetricAlgorithm(privKey, PGPUtil.getProvider(provider));
    }

    /**
     * @deprecated Method getSymmetricAlgorithm is deprecated
     */

    public int getSymmetricAlgorithm(PGPPrivateKey privKey, Provider provider)
        throws PGPException, NoSuchProviderException
    {
        return getSymmetricAlgorithm((new JcePublicKeyDataDecryptorFactoryBuilder()).setProvider(provider).setContentProvider(provider).build(privKey));
    }

    public int getSymmetricAlgorithm(PublicKeyDataDecryptorFactory dataDecryptorFactory)
        throws PGPException
    {
        byte plain[] = dataDecryptorFactory.recoverSessionData(keyData.getAlgorithm(), keyData.getEncSessionKey());
        return plain[0];
    }

    /**
     * @deprecated Method getDataStream is deprecated
     */

    public InputStream getDataStream(PGPPrivateKey privKey, String provider)
        throws PGPException, NoSuchProviderException
    {
        return getDataStream(privKey, provider, provider);
    }

    /**
     * @deprecated Method getDataStream is deprecated
     */

    public InputStream getDataStream(PGPPrivateKey privKey, Provider provider)
        throws PGPException
    {
        return getDataStream(privKey, provider, provider);
    }

    /**
     * @deprecated Method getDataStream is deprecated
     */

    public InputStream getDataStream(PGPPrivateKey privKey, String asymProvider, String provider)
        throws PGPException, NoSuchProviderException
    {
        return getDataStream(privKey, PGPUtil.getProvider(asymProvider), PGPUtil.getProvider(provider));
    }

    /**
     * @deprecated Method getDataStream is deprecated
     */

    public InputStream getDataStream(PGPPrivateKey privKey, Provider asymProvider, Provider provider)
        throws PGPException
    {
        return getDataStream((new JcePublicKeyDataDecryptorFactoryBuilder()).setProvider(asymProvider).setContentProvider(provider).build(privKey));
    }

    public InputStream getDataStream(PublicKeyDataDecryptorFactory dataDecryptorFactory)
        throws PGPException
    {
        byte sessionData[] = dataDecryptorFactory.recoverSessionData(keyData.getAlgorithm(), keyData.getEncSessionKey());
        if(!confirmCheckSum(sessionData))
            throw new PGPKeyValidationException("key checksum failed");
        if(sessionData[0] != 0)
            try
            {
                boolean withIntegrityPacket = encData instanceof SymmetricEncIntegrityPacket;
                byte sessionKey[] = new byte[sessionData.length - 3];
                System.arraycopy(sessionData, 1, sessionKey, 0, sessionKey.length);
                PGPDataDecryptor dataDecryptor = dataDecryptorFactory.createDataDecryptor(withIntegrityPacket, sessionData[0] & 0xff, sessionKey);
                encStream = new BCPGInputStream(dataDecryptor.getInputStream(encData.getInputStream()));
                if(withIntegrityPacket)
                {
                    truncStream = new PGPEncryptedData.TruncatedStream(this, encStream);
                    integrityCalculator = dataDecryptor.getIntegrityCalculator();
                    encStream = new TeeInputStream(truncStream, integrityCalculator.getOutputStream());
                }
                byte iv[] = new byte[dataDecryptor.getBlockSize()];
                for(int i = 0; i != iv.length; i++)
                {
                    int ch = encStream.read();
                    if(ch < 0)
                        throw new EOFException("unexpected end of stream.");
                    iv[i] = (byte)ch;
                }

                int v1 = encStream.read();
                int v2 = encStream.read();
                if(v1 < 0 || v2 < 0)
                    throw new EOFException("unexpected end of stream.");
                else
                    return encStream;
            }
            catch(PGPException e)
            {
                throw e;
            }
            catch(Exception e)
            {
                throw new PGPException("Exception starting decryption", e);
            }
        else
            return encData.getInputStream();
    }

    PublicKeyEncSessionPacket keyData;
}
