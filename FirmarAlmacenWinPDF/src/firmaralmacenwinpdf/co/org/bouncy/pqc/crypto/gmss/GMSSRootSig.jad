// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GMSSRootSig.java

package co.org.bouncy.pqc.crypto.gmss;

import co.org.bouncy.crypto.Digest;
import co.org.bouncy.pqc.crypto.gmss.util.GMSSRandom;
import co.org.bouncy.util.encoders.Hex;

public class GMSSRootSig
{

    public GMSSRootSig(Digest digest, byte statByte[][], int statInt[])
    {
        messDigestOTS = digest;
        gmssRandom = new GMSSRandom(messDigestOTS);
        counter = statInt[0];
        test = statInt[1];
        ii = statInt[2];
        r = statInt[3];
        steps = statInt[4];
        keysize = statInt[5];
        height = statInt[6];
        w = statInt[7];
        checksum = statInt[8];
        mdsize = messDigestOTS.getDigestSize();
        k = (1 << w) - 1;
        int mdsizeBit = mdsize << 3;
        messagesize = (int)Math.ceil((double)mdsizeBit / (double)w);
        privateKeyOTS = statByte[0];
        seed = statByte[1];
        hash = statByte[2];
        sign = statByte[3];
        test8 = (long)(statByte[4][0] & 0xff) | (long)(statByte[4][1] & 0xff) << 8 | (long)(statByte[4][2] & 0xff) << 16 | (long)(statByte[4][3] & 0xff) << 24 | (long)(statByte[4][4] & 0xff) << 32 | (long)(statByte[4][5] & 0xff) << 40 | (long)(statByte[4][6] & 0xff) << 48 | (long)(statByte[4][7] & 0xff) << 56;
        big8 = (long)(statByte[4][8] & 0xff) | (long)(statByte[4][9] & 0xff) << 8 | (long)(statByte[4][10] & 0xff) << 16 | (long)(statByte[4][11] & 0xff) << 24 | (long)(statByte[4][12] & 0xff) << 32 | (long)(statByte[4][13] & 0xff) << 40 | (long)(statByte[4][14] & 0xff) << 48 | (long)(statByte[4][15] & 0xff) << 56;
    }

    public GMSSRootSig(Digest digest, int w, int height)
    {
        messDigestOTS = digest;
        gmssRandom = new GMSSRandom(messDigestOTS);
        mdsize = messDigestOTS.getDigestSize();
        this.w = w;
        this.height = height;
        k = (1 << w) - 1;
        int mdsizeBit = mdsize << 3;
        messagesize = (int)Math.ceil((double)mdsizeBit / (double)w);
    }

    public void initSign(byte seed0[], byte message[])
    {
        hash = new byte[mdsize];
        messDigestOTS.update(message, 0, message.length);
        hash = new byte[messDigestOTS.getDigestSize()];
        messDigestOTS.doFinal(hash, 0);
        byte messPart[] = new byte[mdsize];
        System.arraycopy(hash, 0, messPart, 0, mdsize);
        int checkPart = 0;
        int sumH = 0;
        int checksumsize = getLog((messagesize << w) + 1);
        if(8 % w == 0)
        {
            int dt = 8 / w;
            for(int a = 0; a < mdsize; a++)
            {
                for(int b = 0; b < dt; b++)
                {
                    sumH += messPart[a] & k;
                    messPart[a] = (byte)(messPart[a] >>> w);
                }

            }

            checksum = (messagesize << w) - sumH;
            checkPart = checksum;
            for(int b = 0; b < checksumsize; b += w)
            {
                sumH += checkPart & k;
                checkPart >>>= w;
            }

        } else
        if(w < 8)
        {
            int ii = 0;
            int dt = mdsize / w;
            long big8;
            for(int i = 0; i < dt; i++)
            {
                big8 = 0L;
                for(int j = 0; j < w; j++)
                {
                    big8 ^= (messPart[ii] & 0xff) << (j << 3);
                    ii++;
                }

                for(int j = 0; j < 8; j++)
                {
                    sumH += (int)(big8 & (long)k);
                    big8 >>>= w;
                }

            }

            dt = mdsize % w;
            big8 = 0L;
            for(int j = 0; j < dt; j++)
            {
                big8 ^= (messPart[ii] & 0xff) << (j << 3);
                ii++;
            }

            dt <<= 3;
            for(int j = 0; j < dt; j += w)
            {
                sumH += (int)(big8 & (long)k);
                big8 >>>= w;
            }

            checksum = (messagesize << w) - sumH;
            checkPart = checksum;
            for(int i = 0; i < checksumsize; i += w)
            {
                sumH += checkPart & k;
                checkPart >>>= w;
            }

        } else
        if(w < 57)
        {
            int r;
            int s;
            for(r = 0; r <= (mdsize << 3) - w;)
            {
                s = r >>> 3;
                int rest = r % 8;
                r += w;
                int f = r + 7 >>> 3;
                long big8 = 0L;
                int ii = 0;
                for(int j = s; j < f; j++)
                {
                    big8 ^= (messPart[j] & 0xff) << (ii << 3);
                    ii++;
                }

                big8 >>>= rest;
                sumH = (int)((long)sumH + (big8 & (long)k));
            }

            s = r >>> 3;
            if(s < mdsize)
            {
                int rest = r % 8;
                long big8 = 0L;
                int ii = 0;
                for(int j = s; j < mdsize; j++)
                {
                    big8 ^= (messPart[j] & 0xff) << (ii << 3);
                    ii++;
                }

                big8 >>>= rest;
                sumH = (int)((long)sumH + (big8 & (long)k));
            }
            checksum = (messagesize << w) - sumH;
            checkPart = checksum;
            for(int i = 0; i < checksumsize; i += w)
            {
                sumH += checkPart & k;
                checkPart >>>= w;
            }

        }
        keysize = messagesize + (int)Math.ceil((double)checksumsize / (double)w);
        steps = (int)Math.ceil((double)(keysize + sumH) / (double)(1 << height));
        sign = new byte[keysize * mdsize];
        counter = 0;
        test = 0;
        this.ii = 0;
        test8 = 0L;
        this.r = 0;
        privateKeyOTS = new byte[mdsize];
        seed = new byte[mdsize];
        System.arraycopy(seed0, 0, seed, 0, mdsize);
    }

    public boolean updateSign()
    {
        for(int s = 0; s < steps; s++)
        {
            if(counter < keysize)
                oneStep();
            if(counter == keysize)
                return true;
        }

        return false;
    }

    public byte[] getSig()
    {
        return sign;
    }

    private void oneStep()
    {
        if(8 % w == 0)
        {
            if(test == 0)
            {
                privateKeyOTS = gmssRandom.nextSeed(seed);
                if(ii < mdsize)
                {
                    test = hash[ii] & k;
                    hash[ii] = (byte)(hash[ii] >>> w);
                } else
                {
                    test = checksum & k;
                    checksum >>>= w;
                }
            } else
            if(test > 0)
            {
                messDigestOTS.update(privateKeyOTS, 0, privateKeyOTS.length);
                privateKeyOTS = new byte[messDigestOTS.getDigestSize()];
                messDigestOTS.doFinal(privateKeyOTS, 0);
                test--;
            }
            if(test == 0)
            {
                System.arraycopy(privateKeyOTS, 0, sign, counter * mdsize, mdsize);
                counter++;
                if(counter % (8 / w) == 0)
                    ii++;
            }
        } else
        if(w < 8)
        {
            if(test == 0)
            {
                if(counter % 8 == 0 && ii < mdsize)
                {
                    big8 = 0L;
                    if(counter < mdsize / w << 3)
                    {
                        for(int j = 0; j < w; j++)
                        {
                            big8 ^= (hash[ii] & 0xff) << (j << 3);
                            ii++;
                        }

                    } else
                    {
                        for(int j = 0; j < mdsize % w; j++)
                        {
                            big8 ^= (hash[ii] & 0xff) << (j << 3);
                            ii++;
                        }

                    }
                }
                if(counter == messagesize)
                    big8 = checksum;
                test = (int)(big8 & (long)k);
                privateKeyOTS = gmssRandom.nextSeed(seed);
            } else
            if(test > 0)
            {
                messDigestOTS.update(privateKeyOTS, 0, privateKeyOTS.length);
                privateKeyOTS = new byte[messDigestOTS.getDigestSize()];
                messDigestOTS.doFinal(privateKeyOTS, 0);
                test--;
            }
            if(test == 0)
            {
                System.arraycopy(privateKeyOTS, 0, sign, counter * mdsize, mdsize);
                big8 >>>= w;
                counter++;
            }
        } else
        if(w < 57)
        {
            if(test8 == 0L)
            {
                big8 = 0L;
                ii = 0;
                int rest = r % 8;
                int s = r >>> 3;
                if(s < mdsize)
                {
                    int f;
                    if(r <= (mdsize << 3) - w)
                    {
                        r += w;
                        f = r + 7 >>> 3;
                    } else
                    {
                        f = mdsize;
                        r += w;
                    }
                    for(int i = s; i < f; i++)
                    {
                        big8 ^= (hash[i] & 0xff) << (ii << 3);
                        ii++;
                    }

                    big8 >>>= rest;
                    test8 = big8 & (long)k;
                } else
                {
                    test8 = checksum & k;
                    checksum >>>= w;
                }
                privateKeyOTS = gmssRandom.nextSeed(seed);
            } else
            if(test8 > 0L)
            {
                messDigestOTS.update(privateKeyOTS, 0, privateKeyOTS.length);
                privateKeyOTS = new byte[messDigestOTS.getDigestSize()];
                messDigestOTS.doFinal(privateKeyOTS, 0);
                test8--;
            }
            if(test8 == 0L)
            {
                System.arraycopy(privateKeyOTS, 0, sign, counter * mdsize, mdsize);
                counter++;
            }
        }
    }

    public int getLog(int intValue)
    {
        int log = 1;
        for(int i = 2; i < intValue;)
        {
            i <<= 1;
            log++;
        }

        return log;
    }

    public byte[][] getStatByte()
    {
        byte statByte[][] = new byte[5][mdsize];
        statByte[0] = privateKeyOTS;
        statByte[1] = seed;
        statByte[2] = hash;
        statByte[3] = sign;
        statByte[4] = getStatLong();
        return statByte;
    }

    public int[] getStatInt()
    {
        int statInt[] = new int[9];
        statInt[0] = counter;
        statInt[1] = test;
        statInt[2] = ii;
        statInt[3] = r;
        statInt[4] = steps;
        statInt[5] = keysize;
        statInt[6] = height;
        statInt[7] = w;
        statInt[8] = checksum;
        return statInt;
    }

    public byte[] getStatLong()
    {
        byte bytes[] = new byte[16];
        bytes[0] = (byte)(int)(test8 & 255L);
        bytes[1] = (byte)(int)(test8 >> 8 & 255L);
        bytes[2] = (byte)(int)(test8 >> 16 & 255L);
        bytes[3] = (byte)(int)(test8 >> 24 & 255L);
        bytes[4] = (byte)(int)(test8 >> 32 & 255L);
        bytes[5] = (byte)(int)(test8 >> 40 & 255L);
        bytes[6] = (byte)(int)(test8 >> 48 & 255L);
        bytes[7] = (byte)(int)(test8 >> 56 & 255L);
        bytes[8] = (byte)(int)(big8 & 255L);
        bytes[9] = (byte)(int)(big8 >> 8 & 255L);
        bytes[10] = (byte)(int)(big8 >> 16 & 255L);
        bytes[11] = (byte)(int)(big8 >> 24 & 255L);
        bytes[12] = (byte)(int)(big8 >> 32 & 255L);
        bytes[13] = (byte)(int)(big8 >> 40 & 255L);
        bytes[14] = (byte)(int)(big8 >> 48 & 255L);
        bytes[15] = (byte)(int)(big8 >> 56 & 255L);
        return bytes;
    }

    public String toString()
    {
        String out = (new StringBuilder()).append("").append(big8).append("  ").toString();
        int statInt[] = new int[9];
        statInt = getStatInt();
        byte statByte[][] = new byte[5][mdsize];
        statByte = getStatByte();
        for(int i = 0; i < 9; i++)
            out = (new StringBuilder()).append(out).append(statInt[i]).append(" ").toString();

        for(int i = 0; i < 5; i++)
            out = (new StringBuilder()).append(out).append(new String(Hex.encode(statByte[i]))).append(" ").toString();

        return out;
    }

    private Digest messDigestOTS;
    private int mdsize;
    private int keysize;
    private byte privateKeyOTS[];
    private byte hash[];
    private byte sign[];
    private int w;
    private GMSSRandom gmssRandom;
    private int messagesize;
    private int k;
    private int r;
    private int test;
    private int counter;
    private int ii;
    private long test8;
    private long big8;
    private int steps;
    private int checksum;
    private int height;
    private byte seed[];
}
