// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PGPPBEEncryptedData.java

package co.org.bouncy.openpgp;

import co.org.bouncy.bcpg.*;
import co.org.bouncy.openpgp.operator.PBEDataDecryptorFactory;
import co.org.bouncy.openpgp.operator.PGPDataDecryptor;
import co.org.bouncy.openpgp.operator.PGPDigestCalculator;
import co.org.bouncy.openpgp.operator.jcajce.JcaPGPDigestCalculatorProviderBuilder;
import co.org.bouncy.openpgp.operator.jcajce.JcePBEDataDecryptorFactoryBuilder;
import co.org.bouncy.util.io.TeeInputStream;
import java.io.EOFException;
import java.io.InputStream;
import java.security.NoSuchProviderException;
import java.security.Provider;

// Referenced classes of package co.org.bouncy.openpgp:
//            PGPEncryptedData, PGPDataValidationException, PGPException, PGPUtil

public class PGPPBEEncryptedData extends PGPEncryptedData
{

    PGPPBEEncryptedData(SymmetricKeyEncSessionPacket keyData, InputStreamPacket encData)
    {
        super(encData);
        this.keyData = keyData;
    }

    public InputStream getInputStream()
    {
        return encData.getInputStream();
    }

    /**
     * @deprecated Method getDataStream is deprecated
     */

    public InputStream getDataStream(char passPhrase[], String provider)
        throws PGPException, NoSuchProviderException
    {
        return getDataStream(passPhrase, PGPUtil.getProvider(provider));
    }

    /**
     * @deprecated Method getDataStream is deprecated
     */

    public InputStream getDataStream(char passPhrase[], Provider provider)
        throws PGPException
    {
        return getDataStream((new JcePBEDataDecryptorFactoryBuilder((new JcaPGPDigestCalculatorProviderBuilder()).setProvider(provider).build())).setProvider(provider).build(passPhrase));
    }

    public int getSymmetricAlgorithm(PBEDataDecryptorFactory dataDecryptorFactory)
        throws PGPException
    {
        byte key[] = dataDecryptorFactory.makeKeyFromPassPhrase(keyData.getEncAlgorithm(), keyData.getS2K());
        byte sessionData[] = dataDecryptorFactory.recoverSessionData(keyData.getEncAlgorithm(), key, keyData.getSecKeyData());
        return sessionData[0];
    }

    public InputStream getDataStream(PBEDataDecryptorFactory dataDecryptorFactory)
        throws PGPException
    {
        try
        {
            int keyAlgorithm = keyData.getEncAlgorithm();
            byte key[] = dataDecryptorFactory.makeKeyFromPassPhrase(keyAlgorithm, keyData.getS2K());
            boolean withIntegrityPacket = encData instanceof SymmetricEncIntegrityPacket;
            byte sessionData[] = dataDecryptorFactory.recoverSessionData(keyData.getEncAlgorithm(), key, keyData.getSecKeyData());
            byte sessionKey[] = new byte[sessionData.length - 1];
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
            boolean repeatCheckPassed = iv[iv.length - 2] == (byte)v1 && iv[iv.length - 1] == (byte)v2;
            boolean zeroesCheckPassed = v1 == 0 && v2 == 0;
            if(!repeatCheckPassed && !zeroesCheckPassed)
                throw new PGPDataValidationException("data check failed.");
            else
                return encStream;
        }
        catch(PGPException e)
        {
            throw e;
        }
        catch(Exception e)
        {
            throw new PGPException("Exception creating cipher", e);
        }
    }

    SymmetricKeyEncSessionPacket keyData;
}
