// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IESEngine.java

package co.org.bouncy.crypto.engines;

import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.generators.EphemeralKeyPairGenerator;
import co.org.bouncy.crypto.params.*;
import co.org.bouncy.crypto.util.Pack;
import co.org.bouncy.util.Arrays;
import co.org.bouncy.util.BigIntegers;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;

public class IESEngine
{

    public IESEngine(BasicAgreement agree, DerivationFunction kdf, Mac mac)
    {
        this.agree = agree;
        this.kdf = kdf;
        this.mac = mac;
        macBuf = new byte[mac.getMacSize()];
        cipher = null;
    }

    public IESEngine(BasicAgreement agree, DerivationFunction kdf, Mac mac, BufferedBlockCipher cipher)
    {
        this.agree = agree;
        this.kdf = kdf;
        this.mac = mac;
        macBuf = new byte[mac.getMacSize()];
        this.cipher = cipher;
    }

    public void init(boolean forEncryption, CipherParameters privParam, CipherParameters pubParam, CipherParameters param)
    {
        this.forEncryption = forEncryption;
        this.privParam = privParam;
        this.pubParam = pubParam;
        this.param = (IESParameters)param;
        V = new byte[0];
    }

    public void init(AsymmetricKeyParameter publicKey, CipherParameters params, EphemeralKeyPairGenerator ephemeralKeyPairGenerator)
    {
        forEncryption = true;
        pubParam = publicKey;
        param = (IESParameters)params;
        keyPairGenerator = ephemeralKeyPairGenerator;
    }

    public void init(AsymmetricKeyParameter privateKey, CipherParameters params, KeyParser publicKeyParser)
    {
        forEncryption = false;
        privParam = privateKey;
        param = (IESParameters)params;
        keyParser = publicKeyParser;
    }

    public BufferedBlockCipher getCipher()
    {
        return cipher;
    }

    public Mac getMac()
    {
        return mac;
    }

    private byte[] encryptBlock(byte in[], int inOff, int inLen)
        throws InvalidCipherTextException
    {
        byte C[] = null;
        byte K[] = null;
        byte K1[] = null;
        byte K2[] = null;
        int len;
        if(cipher == null)
        {
            K1 = new byte[inLen];
            K2 = new byte[param.getMacKeySize() / 8];
            K = new byte[K1.length + K2.length];
            kdf.generateBytes(K, 0, K.length);
            if(V.length != 0)
            {
                System.arraycopy(K, 0, K2, 0, K2.length);
                System.arraycopy(K, K2.length, K1, 0, K1.length);
            } else
            {
                System.arraycopy(K, 0, K1, 0, K1.length);
                System.arraycopy(K, inLen, K2, 0, K2.length);
            }
            C = new byte[inLen];
            for(int i = 0; i != inLen; i++)
                C[i] = (byte)(in[inOff + i] ^ K1[i]);

            len = inLen;
        } else
        {
            K1 = new byte[((IESWithCipherParameters)param).getCipherKeySize() / 8];
            K2 = new byte[param.getMacKeySize() / 8];
            K = new byte[K1.length + K2.length];
            kdf.generateBytes(K, 0, K.length);
            System.arraycopy(K, 0, K1, 0, K1.length);
            System.arraycopy(K, K1.length, K2, 0, K2.length);
            cipher.init(true, new KeyParameter(K1));
            C = new byte[cipher.getOutputSize(inLen)];
            len = cipher.processBytes(in, inOff, inLen, C, 0);
            len += cipher.doFinal(C, len);
        }
        byte P2[] = param.getEncodingV();
        byte L2[] = new byte[4];
        if(V.length != 0 && P2 != null)
            Pack.intToBigEndian(P2.length * 8, L2, 0);
        byte T[] = new byte[mac.getMacSize()];
        mac.init(new KeyParameter(K2));
        mac.update(C, 0, C.length);
        if(P2 != null)
            mac.update(P2, 0, P2.length);
        if(V.length != 0)
            mac.update(L2, 0, L2.length);
        mac.doFinal(T, 0);
        byte Output[] = new byte[V.length + len + T.length];
        System.arraycopy(V, 0, Output, 0, V.length);
        System.arraycopy(C, 0, Output, V.length, len);
        System.arraycopy(T, 0, Output, V.length + len, T.length);
        return Output;
    }

    private byte[] decryptBlock(byte in_enc[], int inOff, int inLen)
        throws InvalidCipherTextException
    {
        byte M[] = null;
        byte K[] = null;
        byte K1[] = null;
        byte K2[] = null;
        int len;
        if(cipher == null)
        {
            K1 = new byte[inLen - V.length - mac.getMacSize()];
            K2 = new byte[param.getMacKeySize() / 8];
            K = new byte[K1.length + K2.length];
            kdf.generateBytes(K, 0, K.length);
            if(V.length != 0)
            {
                System.arraycopy(K, 0, K2, 0, K2.length);
                System.arraycopy(K, K2.length, K1, 0, K1.length);
            } else
            {
                System.arraycopy(K, 0, K1, 0, K1.length);
                System.arraycopy(K, K1.length, K2, 0, K2.length);
            }
            M = new byte[K1.length];
            for(int i = 0; i != K1.length; i++)
                M[i] = (byte)(in_enc[inOff + V.length + i] ^ K1[i]);

            len = K1.length;
        } else
        {
            K1 = new byte[((IESWithCipherParameters)param).getCipherKeySize() / 8];
            K2 = new byte[param.getMacKeySize() / 8];
            K = new byte[K1.length + K2.length];
            kdf.generateBytes(K, 0, K.length);
            System.arraycopy(K, 0, K1, 0, K1.length);
            System.arraycopy(K, K1.length, K2, 0, K2.length);
            cipher.init(false, new KeyParameter(K1));
            M = new byte[cipher.getOutputSize(inLen - V.length - mac.getMacSize())];
            len = cipher.processBytes(in_enc, inOff + V.length, inLen - V.length - mac.getMacSize(), M, 0);
            len += cipher.doFinal(M, len);
        }
        byte P2[] = param.getEncodingV();
        byte L2[] = new byte[4];
        if(V.length != 0 && P2 != null)
            Pack.intToBigEndian(P2.length * 8, L2, 0);
        int end = inOff + inLen;
        byte T1[] = Arrays.copyOfRange(in_enc, end - mac.getMacSize(), end);
        byte T2[] = new byte[T1.length];
        mac.init(new KeyParameter(K2));
        mac.update(in_enc, inOff + V.length, inLen - V.length - T2.length);
        if(P2 != null)
            mac.update(P2, 0, P2.length);
        if(V.length != 0)
            mac.update(L2, 0, L2.length);
        mac.doFinal(T2, 0);
        if(!Arrays.constantTimeAreEqual(T1, T2))
            throw new InvalidCipherTextException("Invalid MAC.");
        else
            return Arrays.copyOfRange(M, 0, len);
    }

    public byte[] processBlock(byte in[], int inOff, int inLen)
        throws InvalidCipherTextException
    {
        if(forEncryption)
        {
            if(keyPairGenerator != null)
            {
                EphemeralKeyPair ephKeyPair = keyPairGenerator.generate();
                privParam = ephKeyPair.getKeyPair().getPrivate();
                V = ephKeyPair.getEncodedPublicKey();
            }
        } else
        if(keyParser != null)
        {
            ByteArrayInputStream bIn = new ByteArrayInputStream(in, inOff, inLen);
            try
            {
                pubParam = keyParser.readKey(bIn);
            }
            catch(IOException e)
            {
                throw new InvalidCipherTextException((new StringBuilder()).append("unable to recover ephemeral public key: ").append(e.getMessage()).toString(), e);
            }
            int encLength = inLen - bIn.available();
            V = Arrays.copyOfRange(in, inOff, inOff + encLength);
        }
        agree.init(privParam);
        BigInteger z = agree.calculateAgreement(pubParam);
        byte Z[] = BigIntegers.asUnsignedByteArray(agree.getFieldSize(), z);
        byte VZ[];
        if(V.length != 0)
        {
            VZ = new byte[V.length + Z.length];
            System.arraycopy(V, 0, VZ, 0, V.length);
            System.arraycopy(Z, 0, VZ, V.length, Z.length);
        } else
        {
            VZ = Z;
        }
        KDFParameters kdfParam = new KDFParameters(VZ, param.getDerivationV());
        kdf.init(kdfParam);
        return forEncryption ? encryptBlock(in, inOff, inLen) : decryptBlock(in, inOff, inLen);
    }

    BasicAgreement agree;
    DerivationFunction kdf;
    Mac mac;
    BufferedBlockCipher cipher;
    byte macBuf[];
    boolean forEncryption;
    CipherParameters privParam;
    CipherParameters pubParam;
    IESParameters param;
    byte V[];
    private EphemeralKeyPairGenerator keyPairGenerator;
    private KeyParser keyParser;
}
