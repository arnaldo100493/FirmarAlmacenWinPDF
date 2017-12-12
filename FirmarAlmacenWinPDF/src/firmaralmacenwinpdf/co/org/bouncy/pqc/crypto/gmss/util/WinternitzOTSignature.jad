// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   WinternitzOTSignature.java

package co.org.bouncy.pqc.crypto.gmss.util;

import co.org.bouncy.crypto.Digest;

// Referenced classes of package co.org.bouncy.pqc.crypto.gmss.util:
//            GMSSRandom

public class WinternitzOTSignature
{

    public WinternitzOTSignature(byte seed0[], Digest digest, int w)
    {
        this.w = w;
        messDigestOTS = digest;
        gmssRandom = new GMSSRandom(messDigestOTS);
        mdsize = messDigestOTS.getDigestSize();
        int mdsizeBit = mdsize << 3;
        messagesize = (int)Math.ceil((double)mdsizeBit / (double)w);
        checksumsize = getLog((messagesize << w) + 1);
        keysize = messagesize + (int)Math.ceil((double)checksumsize / (double)w);
        privateKeyOTS = new byte[keysize][mdsize];
        byte dummy[] = new byte[mdsize];
        System.arraycopy(seed0, 0, dummy, 0, dummy.length);
        for(int i = 0; i < keysize; i++)
            privateKeyOTS[i] = gmssRandom.nextSeed(dummy);

    }

    public byte[][] getPrivateKey()
    {
        return privateKeyOTS;
    }

    public byte[] getPublicKey()
    {
        byte helppubKey[] = new byte[keysize * mdsize];
        byte help[] = new byte[mdsize];
        int two_power_t = 1 << w;
        for(int i = 0; i < keysize; i++)
        {
            messDigestOTS.update(privateKeyOTS[i], 0, privateKeyOTS[i].length);
            help = new byte[messDigestOTS.getDigestSize()];
            messDigestOTS.doFinal(help, 0);
            for(int j = 2; j < two_power_t; j++)
            {
                messDigestOTS.update(help, 0, help.length);
                help = new byte[messDigestOTS.getDigestSize()];
                messDigestOTS.doFinal(help, 0);
            }

            System.arraycopy(help, 0, helppubKey, mdsize * i, mdsize);
        }

        messDigestOTS.update(helppubKey, 0, helppubKey.length);
        byte tmp[] = new byte[messDigestOTS.getDigestSize()];
        messDigestOTS.doFinal(tmp, 0);
        return tmp;
    }

    public byte[] getSignature(byte message[])
    {
        byte sign[] = new byte[keysize * mdsize];
        byte hash[] = new byte[mdsize];
        int counter = 0;
        int c = 0;
        int test = 0;
        messDigestOTS.update(message, 0, message.length);
        hash = new byte[messDigestOTS.getDigestSize()];
        messDigestOTS.doFinal(hash, 0);
        if(8 % w == 0)
        {
            int d = 8 / w;
            int k = (1 << w) - 1;
            byte hlp[] = new byte[mdsize];
            for(int i = 0; i < hash.length; i++)
            {
                for(int j = 0; j < d; j++)
                {
                    test = hash[i] & k;
                    c += test;
                    System.arraycopy(privateKeyOTS[counter], 0, hlp, 0, mdsize);
                    for(; test > 0; test--)
                    {
                        messDigestOTS.update(hlp, 0, hlp.length);
                        hlp = new byte[messDigestOTS.getDigestSize()];
                        messDigestOTS.doFinal(hlp, 0);
                    }

                    System.arraycopy(hlp, 0, sign, counter * mdsize, mdsize);
                    hash[i] = (byte)(hash[i] >>> w);
                    counter++;
                }

            }

            c = (messagesize << w) - c;
            for(int i = 0; i < checksumsize; i += w)
            {
                test = c & k;
                System.arraycopy(privateKeyOTS[counter], 0, hlp, 0, mdsize);
                for(; test > 0; test--)
                {
                    messDigestOTS.update(hlp, 0, hlp.length);
                    hlp = new byte[messDigestOTS.getDigestSize()];
                    messDigestOTS.doFinal(hlp, 0);
                }

                System.arraycopy(hlp, 0, sign, counter * mdsize, mdsize);
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
                    test = (int)(big8 & (long)k);
                    c += test;
                    System.arraycopy(privateKeyOTS[counter], 0, hlp, 0, mdsize);
                    for(; test > 0; test--)
                    {
                        messDigestOTS.update(hlp, 0, hlp.length);
                        hlp = new byte[messDigestOTS.getDigestSize()];
                        messDigestOTS.doFinal(hlp, 0);
                    }

                    System.arraycopy(hlp, 0, sign, counter * mdsize, mdsize);
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
                test = (int)(big8 & (long)k);
                c += test;
                System.arraycopy(privateKeyOTS[counter], 0, hlp, 0, mdsize);
                for(; test > 0; test--)
                {
                    messDigestOTS.update(hlp, 0, hlp.length);
                    hlp = new byte[messDigestOTS.getDigestSize()];
                    messDigestOTS.doFinal(hlp, 0);
                }

                System.arraycopy(hlp, 0, sign, counter * mdsize, mdsize);
                big8 >>>= w;
                counter++;
            }

            c = (messagesize << w) - c;
            for(int i = 0; i < checksumsize; i += w)
            {
                test = c & k;
                System.arraycopy(privateKeyOTS[counter], 0, hlp, 0, mdsize);
                for(; test > 0; test--)
                {
                    messDigestOTS.update(hlp, 0, hlp.length);
                    hlp = new byte[messDigestOTS.getDigestSize()];
                    messDigestOTS.doFinal(hlp, 0);
                }

                System.arraycopy(hlp, 0, sign, counter * mdsize, mdsize);
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
                System.arraycopy(privateKeyOTS[counter], 0, hlp, 0, mdsize);
                for(; test8 > 0L; test8--)
                {
                    messDigestOTS.update(hlp, 0, hlp.length);
                    hlp = new byte[messDigestOTS.getDigestSize()];
                    messDigestOTS.doFinal(hlp, 0);
                }

                System.arraycopy(hlp, 0, sign, counter * mdsize, mdsize);
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
                System.arraycopy(privateKeyOTS[counter], 0, hlp, 0, mdsize);
                for(; test8 > 0L; test8--)
                {
                    messDigestOTS.update(hlp, 0, hlp.length);
                    hlp = new byte[messDigestOTS.getDigestSize()];
                    messDigestOTS.doFinal(hlp, 0);
                }

                System.arraycopy(hlp, 0, sign, counter * mdsize, mdsize);
                counter++;
            }
            c = (messagesize << w) - c;
            for(int i = 0; i < checksumsize; i += w)
            {
                long test8 = c & k;
                System.arraycopy(privateKeyOTS[counter], 0, hlp, 0, mdsize);
                for(; test8 > 0L; test8--)
                {
                    messDigestOTS.update(hlp, 0, hlp.length);
                    hlp = new byte[messDigestOTS.getDigestSize()];
                    messDigestOTS.doFinal(hlp, 0);
                }

                System.arraycopy(hlp, 0, sign, counter * mdsize, mdsize);
                c >>>= w;
                counter++;
            }

        }
        return sign;
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
    private int mdsize;
    private int keysize;
    private byte privateKeyOTS[][];
    private int w;
    private GMSSRandom gmssRandom;
    private int messagesize;
    private int checksumsize;
}
