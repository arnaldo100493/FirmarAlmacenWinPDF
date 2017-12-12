// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GMSSPrivateKeySpec.java

package co.org.bouncy.pqc.jcajce.spec;

import co.org.bouncy.pqc.crypto.gmss.*;
import co.org.bouncy.util.Arrays;
import java.security.spec.KeySpec;
import java.util.Vector;

public class GMSSPrivateKeySpec
    implements KeySpec
{

    public GMSSPrivateKeySpec(int index[], byte currentSeed[][], byte nextNextSeed[][], byte currentAuthPath[][][], byte nextAuthPath[][][], Treehash currentTreehash[][], Treehash nextTreehash[][], 
            Vector currentStack[], Vector nextStack[], Vector currentRetain[][], Vector nextRetain[][], byte keep[][][], GMSSLeaf nextNextLeaf[], GMSSLeaf upperLeaf[], 
            GMSSLeaf upperTreehashLeaf[], int minTreehash[], byte nextRoot[][], GMSSRootCalc nextNextRoot[], byte currentRootSig[][], GMSSRootSig nextRootSig[], GMSSParameters gmssParameterset)
    {
        this.index = index;
        this.currentSeed = currentSeed;
        this.nextNextSeed = nextNextSeed;
        this.currentAuthPath = currentAuthPath;
        this.nextAuthPath = nextAuthPath;
        this.currentTreehash = currentTreehash;
        this.nextTreehash = nextTreehash;
        this.currentStack = currentStack;
        this.nextStack = nextStack;
        this.currentRetain = currentRetain;
        this.nextRetain = nextRetain;
        this.keep = keep;
        this.nextNextLeaf = nextNextLeaf;
        this.upperLeaf = upperLeaf;
        this.upperTreehashLeaf = upperTreehashLeaf;
        this.minTreehash = minTreehash;
        this.nextRoot = nextRoot;
        this.nextNextRoot = nextNextRoot;
        this.currentRootSig = currentRootSig;
        this.nextRootSig = nextRootSig;
        gmssPS = gmssParameterset;
    }

    public int[] getIndex()
    {
        return Arrays.clone(index);
    }

    public byte[][] getCurrentSeed()
    {
        return clone(currentSeed);
    }

    public byte[][] getNextNextSeed()
    {
        return clone(nextNextSeed);
    }

    public byte[][][] getCurrentAuthPath()
    {
        return clone(currentAuthPath);
    }

    public byte[][][] getNextAuthPath()
    {
        return clone(nextAuthPath);
    }

    public Treehash[][] getCurrentTreehash()
    {
        return clone(currentTreehash);
    }

    public Treehash[][] getNextTreehash()
    {
        return clone(nextTreehash);
    }

    public byte[][][] getKeep()
    {
        return clone(keep);
    }

    public Vector[] getCurrentStack()
    {
        return clone(currentStack);
    }

    public Vector[] getNextStack()
    {
        return clone(nextStack);
    }

    public Vector[][] getCurrentRetain()
    {
        return clone(currentRetain);
    }

    public Vector[][] getNextRetain()
    {
        return clone(nextRetain);
    }

    public GMSSLeaf[] getNextNextLeaf()
    {
        return clone(nextNextLeaf);
    }

    public GMSSLeaf[] getUpperLeaf()
    {
        return clone(upperLeaf);
    }

    public GMSSLeaf[] getUpperTreehashLeaf()
    {
        return clone(upperTreehashLeaf);
    }

    public int[] getMinTreehash()
    {
        return Arrays.clone(minTreehash);
    }

    public GMSSRootSig[] getNextRootSig()
    {
        return clone(nextRootSig);
    }

    public GMSSParameters getGmssPS()
    {
        return gmssPS;
    }

    public byte[][] getNextRoot()
    {
        return clone(nextRoot);
    }

    public GMSSRootCalc[] getNextNextRoot()
    {
        return clone(nextNextRoot);
    }

    public byte[][] getCurrentRootSig()
    {
        return clone(currentRootSig);
    }

    private static GMSSLeaf[] clone(GMSSLeaf data[])
    {
        if(data == null)
        {
            return null;
        } else
        {
            GMSSLeaf copy[] = new GMSSLeaf[data.length];
            System.arraycopy(data, 0, copy, 0, data.length);
            return copy;
        }
    }

    private static GMSSRootCalc[] clone(GMSSRootCalc data[])
    {
        if(data == null)
        {
            return null;
        } else
        {
            GMSSRootCalc copy[] = new GMSSRootCalc[data.length];
            System.arraycopy(data, 0, copy, 0, data.length);
            return copy;
        }
    }

    private static GMSSRootSig[] clone(GMSSRootSig data[])
    {
        if(data == null)
        {
            return null;
        } else
        {
            GMSSRootSig copy[] = new GMSSRootSig[data.length];
            System.arraycopy(data, 0, copy, 0, data.length);
            return copy;
        }
    }

    private static byte[][] clone(byte data[][])
    {
        if(data == null)
            return (byte[][])null;
        byte copy[][] = new byte[data.length][];
        for(int i = 0; i != data.length; i++)
            copy[i] = Arrays.clone(data[i]);

        return copy;
    }

    private static byte[][][] clone(byte data[][][])
    {
        if(data == null)
            return (byte[][][])null;
        byte copy[][][] = new byte[data.length][][];
        for(int i = 0; i != data.length; i++)
            copy[i] = clone(data[i]);

        return copy;
    }

    private static Treehash[] clone(Treehash data[])
    {
        if(data == null)
        {
            return null;
        } else
        {
            Treehash copy[] = new Treehash[data.length];
            System.arraycopy(data, 0, copy, 0, data.length);
            return copy;
        }
    }

    private static Treehash[][] clone(Treehash data[][])
    {
        if(data == null)
            return (Treehash[][])null;
        Treehash copy[][] = new Treehash[data.length][];
        for(int i = 0; i != data.length; i++)
            copy[i] = clone(data[i]);

        return copy;
    }

    private static Vector[] clone(Vector data[])
    {
        if(data == null)
            return null;
        Vector copy[] = new Vector[data.length];
        for(int i = 0; i != data.length; i++)
            copy[i] = new Vector(data[i]);

        return copy;
    }

    private static Vector[][] clone(Vector data[][])
    {
        if(data == null)
            return (Vector[][])null;
        Vector copy[][] = new Vector[data.length][];
        for(int i = 0; i != data.length; i++)
            copy[i] = clone(data[i]);

        return copy;
    }

    private int index[];
    private byte currentSeed[][];
    private byte nextNextSeed[][];
    private byte currentAuthPath[][][];
    private byte nextAuthPath[][][];
    private Treehash currentTreehash[][];
    private Treehash nextTreehash[][];
    private Vector currentStack[];
    private Vector nextStack[];
    private Vector currentRetain[][];
    private Vector nextRetain[][];
    private byte keep[][][];
    private GMSSLeaf nextNextLeaf[];
    private GMSSLeaf upperLeaf[];
    private GMSSLeaf upperTreehashLeaf[];
    private int minTreehash[];
    private GMSSParameters gmssPS;
    private byte nextRoot[][];
    private GMSSRootCalc nextNextRoot[];
    private byte currentRootSig[][];
    private GMSSRootSig nextRootSig[];
}
