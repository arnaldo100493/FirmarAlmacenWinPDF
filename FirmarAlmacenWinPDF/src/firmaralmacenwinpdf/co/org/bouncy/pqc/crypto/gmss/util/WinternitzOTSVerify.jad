// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   WinternitzOTSVerify.java

package co.org.bouncy.pqc.crypto.gmss.util;

import co.org.bouncy.crypto.Digest;

public class WinternitzOTSVerify
{

    public WinternitzOTSVerify(Digest digest, int w)
    {
        this.w = w;
        messDigestOTS = digest;
    }

    public int getSignatureLength()
    {
        int mdsize = messDigestOTS.getDigestSize();
        int size = ((mdsize << 3) + (w - 1)) / w;
        int logs = getLog((size << w) + 1);
        size += ((logs + w) - 1) / w;
        return mdsize * size;
    }

    public byte[] Verify(byte message[], byte signature[])
    {
        int mdsize = messDigestOTS.getDigestSize();
        byte hash[] = new byte[mdsize];
        messDigestOTS.update(message, 0, message.length);
        hash = new byte[messDigestOTS.getDigestSize()];
        messDigestOTS.doFinal(hash, 0);
        int size = ((mdsize << 3) + (w - 1)) / w;
        int logs = getLog((size << w) + 1);
        int keysize = size + ((logs + w) - 1) / w;
        int testKeySize = mdsize * keysize;
        if(testKeySize != signature.length)
            return null;
        byte testKey[] = new byte[testKeySize];
        int c = 0;
        int counter = 0;
        if(8 % w == 0)
        {
            int d = 8 / w;
            int k = (1 << w) - 1;
            byte hlp[] = new byte[mdsize];
            for(int i = 0; i < hash.length; i++)
            {
                for(int j = 0; j < d; j++)
                {
                    int test = hash[i] & k;
                    c += test;
                    System.arraycopy(signature, counter * mdsize, hlp, 0, mdsize);
                    for(; test < k; test++)
                    {
                        messDigestOTS.update(hlp, 0, hlp.length);
                        hlp = new byte[messDigestOTS.getDigestSize()];
                        messDigestOTS.doFinal(hlp, 0);
                    }

                    System.arraycopy(hlp, 0, testKey, counter * mdsize, mdsize);
                    hash[i] = (byte)(hash[i] >>> w);
                    counter++;
                }

            }

            c = (size << w) - c;
            for(int i = 0; i < logs; i += w)
            {
                int test = c & k;
                System.arraycopy(signature, counter * mdsize, hlp, 0, mdsize);
                for(; test < k; test++)
                {
                    messDigestOTS.update(hlp, 0, hlp.length);
                    hlp = new byte[messDigestOTS.getDigestSize()];
                    messDigestOTS.doFinal(hlp, 0);
                }

                System.arraycopy(hlp, 0, testKey, counter * mdsize, mdsize);
                c >>>= w;
                counter++;
            }

        } else
        if(w < 8)
        {
            int d = mdsize / w;
            int k = (1 << w) - 1;
            byte hlp[] = new byte[mdsize];
            int ii = 0;
            long big8;
            for(int i = 0; i < d; i++)
            {
                big8 = 0L;
                for(int j = 0; j < w; j++)
                {
                    big8 ^= (hash[ii] & 0xff) << (j << 3);
                    ii++;
                }

                for(int j = 0; j < 8; j++)
                {
                    int test = (int)(big8 & (long)k);
                    c += test;
                    System.arraycopy(signature, counter * mdsize, hlp, 0, mdsize);
                    for(; test < k; test++)
                    {
                        messDigestOTS.update(hlp, 0, hlp.length);
                        hlp = new byte[messDigestOTS.getDigestSize()];
                        messDigestOTS.doFinal(hlp, 0);
                    }

                    System.arraycopy(hlp, 0, testKey, counter * mdsize, mdsize);
                    big8 >>>= w;
                    counter++;
                }

            }

            d = mdsize % w;
            big8 = 0L;
            for(int j = 0; j < d; j++)
            {
                big8 ^= (hash[ii] & 0xff) << (j << 3);
                ii++;
            }

            d <<= 3;
            for(int j = 0; j < d; j += w)
            {
                int test = (int)(big8 & (long)k);
                c += test;
                System.arraycopy(signature, counter * mdsize, hlp, 0, mdsize);
                for(; test < k; test++)
                {
                    messDigestOTS.update(hlp, 0, hlp.length);
                    hlp = new byte[messDigestOTS.getDigestSize()];
                    messDigestOTS.doFinal(hlp, 0);
                }

                System.arraycopy(hlp, 0, testKey, counter * mdsize, mdsize);
                big8 >>>= w;
                counter++;
            }

            c = (size << w) - c;
            for(int i = 0; i < logs; i += w)
            {
                int test = c & k;
                System.arraycopy(signature, counter * mdsize, hlp, 0, mdsize);
                for(; test < k; test++)
                {
                    messDigestOTS.update(hlp, 0, hlp.length);
                    hlp = new byte[messDigestOTS.getDigestSize()];
                    messDigestOTS.doFinal(hlp, 0);
                }

                System.arraycopy(hlp, 0, testKey, counter * mdsize, mdsize);
                c >>>= w;
                counter++;
            }

        } else
        if(w < 57)
        {
            int d = (mdsize << 3) - w;
            int k = (1 << w) - 1;
            byte hlp[] = new byte[mdsize];
            int r;
            int s;
            for(r = 0; r <= d;)
            {
                s = r >>> 3;
                int rest = r % 8;
                r += w;
                int f = r + 7 >>> 3;
                long big8 = 0L;
                int ii = 0;
                for(int j = s; j < f; j++)
                {
                    big8 ^= (hash[j] & 0xff) << (ii << 3);
                    ii++;
                }

                big8 >>>= rest;
                long test8 = big8 & (long)k;
                c = (int)((long)c + test8);
                System.arraycopy(signature, counter * mdsize, hlp, 0, mdsize);
                for(; test8 < (long)k; test8++)
                {
                    messDigestOTS.update(hlp, 0, hlp.length);
                    hlp = new byte[messDigestOTS.getDigestSize()];
                    messDigestOTS.doFinal(hlp, 0);
                }

                System.arraycopy(hlp, 0, testKey, counter * mdsize, mdsize);
                counter++;
            }

            s = r >>> 3;
            if(s < mdsize)
            {
                int rest = r % 8;
                long big8 = 0L;
                int ii = 0;
                for(int j = s; j < mdsize; j++)
                {
                    big8 ^= (hash[j] & 0xff) << (ii << 3);
                    ii++;
                }

                big8 >>>= rest;
                long test8 = big8 & (long)k;
                c = (int)((long)c + test8);
                System.arraycopy(signature, counter * mdsize, hlp, 0, mdsize);
                for(; test8 < (long)k; test8++)
                {
                    messDigestOTS.update(hlp, 0, hlp.length);
                    hlp = new byte[messDigestOTS.getDigestSize()];
                    messDigestOTS.doFinal(hlp, 0);
                }

                System.arraycopy(hlp, 0, testKey, counter * mdsize, mdsize);
                counter++;
            }
            c = (size << w) - c;
            for(int i = 0; i < logs; i += w)
            {
                long test8 = c & k;
                System.arraycopy(signature, counter * mdsize, hlp, 0, mdsize);
                for(; test8 < (long)k; test8++)
                {
                    messDigestOTS.update(hlp, 0, hlp.length);
                    hlp = new byte[messDigestOTS.getDigestSize()];
                    messDigestOTS.doFinal(hlp, 0);
                }

                System.arraycopy(hlp, 0, testKey, counter * mdsize, mdsize);
                c >>>= w;
                counter++;
            }

        }
        byte TKey[] = new byte[mdsize];
        messDigestOTS.update(testKey, 0, testKey.length);
        TKey = new byte[messDigestOTS.getDigestSize()];
        messDigestOTS.doFinal(TKey, 0);
        return TKey;
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

    private Digest messDigestOTS;
    private int w;
}
