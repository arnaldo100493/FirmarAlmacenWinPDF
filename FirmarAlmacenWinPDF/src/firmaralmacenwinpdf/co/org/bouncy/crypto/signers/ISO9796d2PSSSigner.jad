// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ISO9796d2PSSSigner.java

package co.org.bouncy.crypto.signers;

import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.params.*;
import co.org.bouncy.util.Arrays;
import co.org.bouncy.util.Integers;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Hashtable;

public class ISO9796d2PSSSigner
    implements SignerWithRecovery
{

    public ISO9796d2PSSSigner(AsymmetricBlockCipher cipher, Digest digest, int saltLength, boolean implicit)
    {
        this.cipher = cipher;
        this.digest = digest;
        hLen = digest.getDigestSize();
        this.saltLength = saltLength;
        if(implicit)
        {
            trailer = 188;
        } else
        {
            Integer trailerObj = (Integer)trailerMap.get(digest.getAlgorithmName());
            if(trailerObj != null)
                trailer = trailerObj.intValue();
            else
                throw new IllegalArgumentException("no valid trailer for digest");
        }
    }

    public ISO9796d2PSSSigner(AsymmetricBlockCipher cipher, Digest digest, int saltLength)
    {
        this(cipher, digest, saltLength, false);
    }

    public void init(boolean forSigning, CipherParameters param)
    {
        int lengthOfSalt = saltLength;
        RSAKeyParameters kParam;
        if(param instanceof ParametersWithRandom)
        {
            ParametersWithRandom p = (ParametersWithRandom)param;
            kParam = (RSAKeyParameters)p.getParameters();
            if(forSigning)
                random = p.getRandom();
        } else
        if(param instanceof ParametersWithSalt)
        {
            ParametersWithSalt p = (ParametersWithSalt)param;
            kParam = (RSAKeyParameters)p.getParameters();
            standardSalt = p.getSalt();
            lengthOfSalt = standardSalt.length;
            if(standardSalt.length != saltLength)
                throw new IllegalArgumentException("Fixed salt is of wrong length");
        } else
        {
            kParam = (RSAKeyParameters)param;
            if(forSigning)
                random = new SecureRandom();
        }
        cipher.init(forSigning, kParam);
        keyBits = kParam.getModulus().bitLength();
        block = new byte[(keyBits + 7) / 8];
        if(trailer == 188)
            mBuf = new byte[block.length - digest.getDigestSize() - lengthOfSalt - 1 - 1];
        else
            mBuf = new byte[block.length - digest.getDigestSize() - lengthOfSalt - 1 - 2];
        reset();
    }

    private boolean isSameAs(byte a[], byte b[])
    {
        boolean isOkay = true;
        if(messageLength != b.length)
            isOkay = false;
        for(int i = 0; i != b.length; i++)
            if(a[i] != b[i])
                isOkay = false;

        return isOkay;
    }

    private void clearBlock(byte block[])
    {
        for(int i = 0; i != block.length; i++)
            block[i] = 0;

    }

    public void updateWithRecoveredMessage(byte signature[])
        throws InvalidCipherTextException
    {
        byte block[] = cipher.processBlock(signature, 0, signature.length);
        if(block.length < (keyBits + 7) / 8)
        {
            byte tmp[] = new byte[(keyBits + 7) / 8];
            System.arraycopy(block, 0, tmp, tmp.length - block.length, block.length);
            clearBlock(block);
            block = tmp;
        }
        int tLength;
        if((block[block.length - 1] & 0xff ^ 0xbc) == 0)
        {
            tLength = 1;
        } else
        {
            int sigTrail = (block[block.length - 2] & 0xff) << 8 | block[block.length - 1] & 0xff;
            Integer trailerObj = (Integer)trailerMap.get(digest.getAlgorithmName());
            if(trailerObj != null)
            {
                if(sigTrail != trailerObj.intValue())
                    throw new IllegalStateException((new StringBuilder()).append("signer initialised with wrong digest for trailer ").append(sigTrail).toString());
            } else
            {
                throw new IllegalArgumentException("unrecognised hash in signature");
            }
            tLength = 2;
        }
        byte m2Hash[] = new byte[hLen];
        digest.doFinal(m2Hash, 0);
        byte dbMask[] = maskGeneratorFunction1(block, block.length - hLen - tLength, hLen, block.length - hLen - tLength);
        for(int i = 0; i != dbMask.length; i++)
            block[i] ^= dbMask[i];

        block[0] &= 0x7f;
        int mStart;
        for(mStart = 0; mStart != block.length && block[mStart] != 1; mStart++);
        if(++mStart >= block.length)
            clearBlock(block);
        fullMessage = mStart > 1;
        recoveredMessage = new byte[dbMask.length - mStart - saltLength];
        System.arraycopy(block, mStart, recoveredMessage, 0, recoveredMessage.length);
        System.arraycopy(recoveredMessage, 0, mBuf, 0, recoveredMessage.length);
        preSig = signature;
        preBlock = block;
        preMStart = mStart;
        preTLength = tLength;
    }

    public void update(byte b)
    {
        if(preSig == null && messageLength < mBuf.length)
            mBuf[messageLength++] = b;
        else
            digest.update(b);
    }

    public void update(byte in[], int off, int len)
    {
        if(preSig == null)
            for(; len > 0 && messageLength < mBuf.length; len--)
            {
                update(in[off]);
                off++;
            }

        if(len > 0)
            digest.update(in, off, len);
    }

    public void reset()
    {
        digest.reset();
        messageLength = 0;
        if(mBuf != null)
            clearBlock(mBuf);
        if(recoveredMessage != null)
        {
            clearBlock(recoveredMessage);
            recoveredMessage = null;
        }
        fullMessage = false;
        if(preSig != null)
        {
            preSig = null;
            clearBlock(preBlock);
            preBlock = null;
        }
    }

    public byte[] generateSignature()
        throws CryptoException
    {
        int digSize = digest.getDigestSize();
        byte m2Hash[] = new byte[digSize];
        digest.doFinal(m2Hash, 0);
        byte C[] = new byte[8];
        LtoOSP(messageLength * 8, C);
        digest.update(C, 0, C.length);
        digest.update(mBuf, 0, messageLength);
        digest.update(m2Hash, 0, m2Hash.length);
        byte salt[];
        if(standardSalt != null)
        {
            salt = standardSalt;
        } else
        {
            salt = new byte[saltLength];
            random.nextBytes(salt);
        }
        digest.update(salt, 0, salt.length);
        byte hash[] = new byte[digest.getDigestSize()];
        digest.doFinal(hash, 0);
        int tLength = 2;
        if(trailer == 188)
            tLength = 1;
        int off = block.length - messageLength - salt.length - hLen - tLength - 1;
        block[off] = 1;
        System.arraycopy(mBuf, 0, block, off + 1, messageLength);
        System.arraycopy(salt, 0, block, off + 1 + messageLength, salt.length);
        byte dbMask[] = maskGeneratorFunction1(hash, 0, hash.length, block.length - hLen - tLength);
        for(int i = 0; i != dbMask.length; i++)
            block[i] ^= dbMask[i];

        System.arraycopy(hash, 0, block, block.length - hLen - tLength, hLen);
        if(trailer == 188)
        {
            block[block.length - 1] = -68;
        } else
        {
            block[block.length - 2] = (byte)(trailer >>> 8);
            block[block.length - 1] = (byte)trailer;
        }
        block[0] &= 0x7f;
        byte b[] = cipher.processBlock(block, 0, block.length);
        clearBlock(mBuf);
        clearBlock(block);
        messageLength = 0;
        return b;
    }

    public boolean verifySignature(byte signature[])
    {
        byte m2Hash[] = new byte[hLen];
        digest.doFinal(m2Hash, 0);
        int mStart = 0;
        if(preSig == null)
            try
            {
                updateWithRecoveredMessage(signature);
            }
            catch(Exception e)
            {
                return false;
            }
        else
        if(!Arrays.areEqual(preSig, signature))
            throw new IllegalStateException("updateWithRecoveredMessage called on different signature");
        byte block[] = preBlock;
        mStart = preMStart;
        int tLength = preTLength;
        preSig = null;
        preBlock = null;
        byte C[] = new byte[8];
        LtoOSP(recoveredMessage.length * 8, C);
        digest.update(C, 0, C.length);
        if(recoveredMessage.length != 0)
            digest.update(recoveredMessage, 0, recoveredMessage.length);
        digest.update(m2Hash, 0, m2Hash.length);
        digest.update(block, mStart + recoveredMessage.length, saltLength);
        byte hash[] = new byte[digest.getDigestSize()];
        digest.doFinal(hash, 0);
        int off = block.length - tLength - hash.length;
        boolean isOkay = true;
        for(int i = 0; i != hash.length; i++)
            if(hash[i] != block[off + i])
                isOkay = false;

        clearBlock(block);
        clearBlock(hash);
        if(!isOkay)
        {
            fullMessage = false;
            clearBlock(recoveredMessage);
            return false;
        }
        if(messageLength != 0)
        {
            if(!isSameAs(mBuf, recoveredMessage))
            {
                clearBlock(mBuf);
                return false;
            }
            messageLength = 0;
        }
        clearBlock(mBuf);
        return true;
    }

    public boolean hasFullMessage()
    {
        return fullMessage;
    }

    public byte[] getRecoveredMessage()
    {
        return recoveredMessage;
    }

    private void ItoOSP(int i, byte sp[])
    {
        sp[0] = (byte)(i >>> 24);
        sp[1] = (byte)(i >>> 16);
        sp[2] = (byte)(i >>> 8);
        sp[3] = (byte)(i >>> 0);
    }

    private void LtoOSP(long l, byte sp[])
    {
        sp[0] = (byte)(int)(l >>> 56);
        sp[1] = (byte)(int)(l >>> 48);
        sp[2] = (byte)(int)(l >>> 40);
        sp[3] = (byte)(int)(l >>> 32);
        sp[4] = (byte)(int)(l >>> 24);
        sp[5] = (byte)(int)(l >>> 16);
        sp[6] = (byte)(int)(l >>> 8);
        sp[7] = (byte)(int)(l >>> 0);
    }

    private byte[] maskGeneratorFunction1(byte Z[], int zOff, int zLen, int length)
    {
        byte mask[] = new byte[length];
        byte hashBuf[] = new byte[hLen];
        byte C[] = new byte[4];
        int counter = 0;
        digest.reset();
        for(; counter < length / hLen; counter++)
        {
            ItoOSP(counter, C);
            digest.update(Z, zOff, zLen);
            digest.update(C, 0, C.length);
            digest.doFinal(hashBuf, 0);
            System.arraycopy(hashBuf, 0, mask, counter * hLen, hLen);
        }

        if(counter * hLen < length)
        {
            ItoOSP(counter, C);
            digest.update(Z, zOff, zLen);
            digest.update(C, 0, C.length);
            digest.doFinal(hashBuf, 0);
            System.arraycopy(hashBuf, 0, mask, counter * hLen, mask.length - counter * hLen);
        }
        return mask;
    }

    public static final int TRAILER_IMPLICIT = 188;
    public static final int TRAILER_RIPEMD160 = 12748;
    public static final int TRAILER_RIPEMD128 = 13004;
    public static final int TRAILER_SHA1 = 13260;
    public static final int TRAILER_SHA256 = 13516;
    public static final int TRAILER_SHA512 = 13772;
    public static final int TRAILER_SHA384 = 14028;
    public static final int TRAILER_WHIRLPOOL = 14284;
    private static Hashtable trailerMap;
    private Digest digest;
    private AsymmetricBlockCipher cipher;
    private SecureRandom random;
    private byte standardSalt[];
    private int hLen;
    private int trailer;
    private int keyBits;
    private byte block[];
    private byte mBuf[];
    private int messageLength;
    private int saltLength;
    private boolean fullMessage;
    private byte recoveredMessage[];
    private byte preSig[];
    private byte preBlock[];
    private int preMStart;
    private int preTLength;

    static 
    {
        trailerMap = new Hashtable();
        trailerMap.put("RIPEMD128", Integers.valueOf(13004));
        trailerMap.put("RIPEMD160", Integers.valueOf(12748));
        trailerMap.put("SHA-1", Integers.valueOf(13260));
        trailerMap.put("SHA-256", Integers.valueOf(13516));
        trailerMap.put("SHA-384", Integers.valueOf(14028));
        trailerMap.put("SHA-512", Integers.valueOf(13772));
        trailerMap.put("Whirlpool", Integers.valueOf(14284));
    }
}
