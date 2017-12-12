// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GMSSLeaf.java

package co.org.bouncy.pqc.crypto.gmss;

import co.org.bouncy.crypto.Digest;
import co.org.bouncy.pqc.crypto.gmss.util.GMSSRandom;
import co.org.bouncy.util.Arrays;
import co.org.bouncy.util.encoders.Hex;

public class GMSSLeaf
{

    public GMSSLeaf(Digest digest, byte otsIndex[][], int numLeafs[])
    {
        i = numLeafs[0];
        j = numLeafs[1];
        steps = numLeafs[2];
        w = numLeafs[3];
        messDigestOTS = digest;
        gmssRandom = new GMSSRandom(messDigestOTS);
        mdsize = messDigestOTS.getDigestSize();
        int mdsizeBit = mdsize << 3;
        int messagesize = (int)Math.ceil((double)mdsizeBit / (double)w);
        int checksumsize = getLog((messagesize << w) + 1);
        keysize = messagesize + (int)Math.ceil((double)checksumsize / (double)w);
        two_power_w = 1 << w;
        privateKeyOTS = otsIndex[0];
        seed = otsIndex[1];
        concHashs = otsIndex[2];
        leaf = otsIndex[3];
    }

    GMSSLeaf(Digest digest, int w, int numLeafs)
    {
        this.w = w;
        messDigestOTS = digest;
        gmssRandom = new GMSSRandom(messDigestOTS);
        mdsize = messDigestOTS.getDigestSize();
        int mdsizeBit = mdsize << 3;
        int messagesize = (int)Math.ceil((double)mdsizeBit / (double)w);
        int checksumsize = getLog((messagesize << w) + 1);
        keysize = messagesize + (int)Math.ceil((double)checksumsize / (double)w);
        two_power_w = 1 << w;
        steps = (int)Math.ceil((double)(((1 << w) - 1) * keysize + 1 + keysize) / (double)numLeafs);
        seed = new byte[mdsize];
        leaf = new byte[mdsize];
        privateKeyOTS = new byte[mdsize];
        concHashs = new byte[mdsize * keysize];
    }

    public GMSSLeaf(Digest digest, int w, int numLeafs, byte seed0[])
    {
        this.w = w;
        messDigestOTS = digest;
        gmssRandom = new GMSSRandom(messDigestOTS);
        mdsize = messDigestOTS.getDigestSize();
        int mdsizeBit = mdsize << 3;
        int messagesize = (int)Math.ceil((double)mdsizeBit / (double)w);
        int checksumsize = getLog((messagesize << w) + 1);
        keysize = messagesize + (int)Math.ceil((double)checksumsize / (double)w);
        two_power_w = 1 << w;
        steps = (int)Math.ceil((double)(((1 << w) - 1) * keysize + 1 + keysize) / (double)numLeafs);
        seed = new byte[mdsize];
        leaf = new byte[mdsize];
        privateKeyOTS = new byte[mdsize];
        concHashs = new byte[mdsize * keysize];
        initLeafCalc(seed0);
    }

    private GMSSLeaf(GMSSLeaf original)
    {
        messDigestOTS = original.messDigestOTS;
        mdsize = original.mdsize;
        keysize = original.keysize;
        gmssRandom = original.gmssRandom;
        leaf = Arrays.clone(original.leaf);
        concHashs = Arrays.clone(original.concHashs);
        i = original.i;
        j = original.j;
        two_power_w = original.two_power_w;
        w = original.w;
        steps = original.steps;
        seed = Arrays.clone(original.seed);
        privateKeyOTS = Arrays.clone(original.privateKeyOTS);
    }

    void initLeafCalc(byte seed0[])
    {
        i = 0;
        j = 0;
        byte dummy[] = new byte[mdsize];
        System.arraycopy(seed0, 0, dummy, 0, seed.length);
        seed = gmssRandom.nextSeed(dummy);
    }

    GMSSLeaf nextLeaf()
    {
        GMSSLeaf nextLeaf = new GMSSLeaf(this);
        nextLeaf.updateLeafCalc();
        return nextLeaf;
    }

    private void updateLeafCalc()
    {
        byte buf[] = new byte[messDigestOTS.getDigestSize()];
        for(int s = 0; s < steps + 10000; s++)
        {
            if(i == keysize && j == two_power_w - 1)
            {
                messDigestOTS.update(concHashs, 0, concHashs.length);
                leaf = new byte[messDigestOTS.getDigestSize()];
                messDigestOTS.doFinal(leaf, 0);
                return;
            }
            if(i == 0 || j == two_power_w - 1)
            {
                i++;
                j = 0;
                privateKeyOTS = gmssRandom.nextSeed(seed);
                continue;
            }
            messDigestOTS.update(privateKeyOTS, 0, privateKeyOTS.length);
            privateKeyOTS = buf;
            messDigestOTS.doFinal(privateKeyOTS, 0);
            j++;
            if(j == two_power_w - 1)
                System.arraycopy(privateKeyOTS, 0, concHashs, mdsize * (i - 1), mdsize);
        }

        throw new IllegalStateException((new StringBuilder()).append("unable to updateLeaf in steps: ").append(steps).append(" ").append(i).append(" ").append(j).toString());
    }

    public byte[] getLeaf()
    {
        return Arrays.clone(leaf);
    }

    private int getLog(int intValue)
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
        byte statByte[][] = new byte[4][];
        statByte[0] = new byte[mdsize];
        statByte[1] = new byte[mdsize];
        statByte[2] = new byte[mdsize * keysize];
        statByte[3] = new byte[mdsize];
        statByte[0] = privateKeyOTS;
        statByte[1] = seed;
        statByte[2] = concHashs;
        statByte[3] = leaf;
        return statByte;
    }

    public int[] getStatInt()
    {
        int statInt[] = new int[4];
        statInt[0] = i;
        statInt[1] = j;
        statInt[2] = steps;
        statInt[3] = w;
        return statInt;
    }

    public String toString()
    {
        String out = "";
        for(int i = 0; i < 4; i++)
            out = (new StringBuilder()).append(out).append(getStatInt()[i]).append(" ").toString();

        out = (new StringBuilder()).append(out).append(" ").append(mdsize).append(" ").append(keysize).append(" ").append(two_power_w).append(" ").toString();
        byte temp[][] = getStatByte();
        for(int i = 0; i < 4; i++)
            if(temp[i] != null)
                out = (new StringBuilder()).append(out).append(new String(Hex.encode(temp[i]))).append(" ").toString();
            else
                out = (new StringBuilder()).append(out).append("null ").toString();

        return out;
    }

    private Digest messDigestOTS;
    private int mdsize;
    private int keysize;
    private GMSSRandom gmssRandom;
    private byte leaf[];
    private byte concHashs[];
    private int i;
    private int j;
    private int two_power_w;
    private int w;
    private int steps;
    private byte seed[];
    byte privateKeyOTS[];
}
