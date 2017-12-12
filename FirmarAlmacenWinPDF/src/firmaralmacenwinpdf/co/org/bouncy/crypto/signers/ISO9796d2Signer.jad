// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ISO9796d2Signer.java

package co.org.bouncy.crypto.signers;

import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.params.RSAKeyParameters;
import co.org.bouncy.util.Arrays;
import co.org.bouncy.util.Integers;
import java.math.BigInteger;
import java.util.Hashtable;

public class ISO9796d2Signer
    implements SignerWithRecovery
{

    public ISO9796d2Signer(AsymmetricBlockCipher cipher, Digest digest, boolean implicit)
    {
        this.cipher = cipher;
        this.digest = digest;
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

    public ISO9796d2Signer(AsymmetricBlockCipher cipher, Digest digest)
    {
        this(cipher, digest, false);
    }

    public void init(boolean forSigning, CipherParameters param)
    {
        RSAKeyParameters kParam = (RSAKeyParameters)param;
        cipher.init(forSigning, kParam);
        keyBits = kParam.getModulus().bitLength();
        block = new byte[(keyBits + 7) / 8];
        if(trailer == 188)
            mBuf = new byte[block.length - digest.getDigestSize() - 2];
        else
            mBuf = new byte[block.length - digest.getDigestSize() - 3];
        reset();
    }

    private boolean isSameAs(byte a[], byte b[])
    {
        boolean isOkay = true;
        if(messageLength > mBuf.length)
        {
            if(mBuf.length > b.length)
                isOkay = false;
            for(int i = 0; i != mBuf.length; i++)
                if(a[i] != b[i])
                    isOkay = false;

        } else
        {
            if(messageLength != b.length)
                isOkay = false;
            for(int i = 0; i != b.length; i++)
                if(a[i] != b[i])
                    isOkay = false;

        }
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
        if((block[0] & 0xc0 ^ 0x40) != 0)
            throw new InvalidCipherTextException("malformed signature");
        if((block[block.length - 1] & 0xf ^ 0xc) != 0)
            throw new InvalidCipherTextException("malformed signature");
        int delta = 0;
        if((block[block.length - 1] & 0xff ^ 0xbc) == 0)
        {
            delta = 1;
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
            delta = 2;
        }
        int mStart = 0;
        for(mStart = 0; mStart != block.length && (block[mStart] & 0xf ^ 0xa) != 0; mStart++);
        mStart++;
        int off = block.length - delta - digest.getDigestSize();
        if(off - mStart <= 0)
            throw new InvalidCipherTextException("malformed block");
        if((block[0] & 0x20) == 0)
        {
            fullMessage = true;
            recoveredMessage = new byte[off - mStart];
            System.arraycopy(block, mStart, recoveredMessage, 0, recoveredMessage.length);
        } else
        {
            fullMessage = false;
            recoveredMessage = new byte[off - mStart];
            System.arraycopy(block, mStart, recoveredMessage, 0, recoveredMessage.length);
        }
        preSig = signature;
        preBlock = block;
        digest.update(recoveredMessage, 0, recoveredMessage.length);
        messageLength = recoveredMessage.length;
        System.arraycopy(recoveredMessage, 0, mBuf, 0, recoveredMessage.length);
    }

    public void update(byte b)
    {
        digest.update(b);
        if(messageLength < mBuf.length)
            mBuf[messageLength] = b;
        messageLength++;
    }

    public void update(byte in[], int off, int len)
    {
        for(; len > 0 && messageLength < mBuf.length; len--)
        {
            update(in[off]);
            off++;
        }

        digest.update(in, off, len);
        messageLength += len;
    }

    public void reset()
    {
        digest.reset();
        messageLength = 0;
        clearBlock(mBuf);
        if(recoveredMessage != null)
            clearBlock(recoveredMessage);
        recoveredMessage = null;
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
        int t = 0;
        int delta = 0;
        if(trailer == 188)
        {
            t = 8;
            delta = block.length - digSize - 1;
            digest.doFinal(block, delta);
            block[block.length - 1] = -68;
        } else
        {
            t = 16;
            delta = block.length - digSize - 2;
            digest.doFinal(block, delta);
            block[block.length - 2] = (byte)(trailer >>> 8);
            block[block.length - 1] = (byte)trailer;
        }
        byte header = 0;
        int x = ((digSize + messageLength) * 8 + t + 4) - keyBits;
        if(x > 0)
        {
            int mR = messageLength - (x + 7) / 8;
            header = 96;
            delta -= mR;
            System.arraycopy(mBuf, 0, block, delta, mR);
        } else
        {
            header = 64;
            delta -= messageLength;
            System.arraycopy(mBuf, 0, block, delta, messageLength);
        }
        if(delta - 1 > 0)
        {
            for(int i = delta - 1; i != 0; i--)
                block[i] = -69;

            block[delta - 1] ^= 1;
            block[0] = 11;
            block[0] |= header;
        } else
        {
            block[0] = 10;
            block[0] |= header;
        }
        byte b[] = cipher.processBlock(block, 0, block.length);
        clearBlock(mBuf);
        clearBlock(block);
        return b;
    }

    public boolean verifySignature(byte signature[])
    {
        byte block[] = null;
        if(preSig == null)
        {
            try
            {
                block = cipher.processBlock(signature, 0, signature.length);
            }
            catch(Exception e)
            {
                return false;
            }
        } else
        {
            if(!Arrays.areEqual(preSig, signature))
                throw new IllegalStateException("updateWithRecoveredMessage called on different signature");
            block = preBlock;
            preSig = null;
            preBlock = null;
        }
        if((block[0] & 0xc0 ^ 0x40) != 0)
            return returnFalse(block);
        if((block[block.length - 1] & 0xf ^ 0xc) != 0)
            return returnFalse(block);
        int delta = 0;
        if((block[block.length - 1] & 0xff ^ 0xbc) == 0)
        {
            delta = 1;
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
            delta = 2;
        }
        int mStart = 0;
        for(mStart = 0; mStart != block.length && (block[mStart] & 0xf ^ 0xa) != 0; mStart++);
        mStart++;
        byte hash[] = new byte[digest.getDigestSize()];
        int off = block.length - delta - hash.length;
        if(off - mStart <= 0)
            return returnFalse(block);
        if((block[0] & 0x20) == 0)
        {
            fullMessage = true;
            if(messageLength > off - mStart)
                return returnFalse(block);
            digest.reset();
            digest.update(block, mStart, off - mStart);
            digest.doFinal(hash, 0);
            boolean isOkay = true;
            for(int i = 0; i != hash.length; i++)
            {
                block[off + i] ^= hash[i];
                if(block[off + i] != 0)
                    isOkay = false;
            }

            if(!isOkay)
                return returnFalse(block);
            recoveredMessage = new byte[off - mStart];
            System.arraycopy(block, mStart, recoveredMessage, 0, recoveredMessage.length);
        } else
        {
            fullMessage = false;
            digest.doFinal(hash, 0);
            boolean isOkay = true;
            for(int i = 0; i != hash.length; i++)
            {
                block[off + i] ^= hash[i];
                if(block[off + i] != 0)
                    isOkay = false;
            }

            if(!isOkay)
                return returnFalse(block);
            recoveredMessage = new byte[off - mStart];
            System.arraycopy(block, mStart, recoveredMessage, 0, recoveredMessage.length);
        }
        if(messageLength != 0 && !isSameAs(mBuf, recoveredMessage))
        {
            return returnFalse(block);
        } else
        {
            clearBlock(mBuf);
            clearBlock(block);
            return true;
        }
    }

    private boolean returnFalse(byte block[])
    {
        clearBlock(mBuf);
        clearBlock(block);
        return false;
    }

    public boolean hasFullMessage()
    {
        return fullMessage;
    }

    public byte[] getRecoveredMessage()
    {
        return recoveredMessage;
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
    private int trailer;
    private int keyBits;
    private byte block[];
    private byte mBuf[];
    private int messageLength;
    private boolean fullMessage;
    private byte recoveredMessage[];
    private byte preSig[];
    private byte preBlock[];

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
