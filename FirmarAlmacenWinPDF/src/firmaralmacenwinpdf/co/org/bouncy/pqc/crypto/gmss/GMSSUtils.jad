// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GMSSUtils.java

package co.org.bouncy.pqc.crypto.gmss;

import co.org.bouncy.util.Arrays;
import java.util.Enumeration;
import java.util.Vector;

// Referenced classes of package co.org.bouncy.pqc.crypto.gmss:
//            GMSSLeaf, GMSSRootCalc, GMSSRootSig, Treehash

class GMSSUtils
{

    GMSSUtils()
    {
    }

    static GMSSLeaf[] clone(GMSSLeaf data[])
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

    static GMSSRootCalc[] clone(GMSSRootCalc data[])
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

    static GMSSRootSig[] clone(GMSSRootSig data[])
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

    static byte[][] clone(byte data[][])
    {
        if(data == null)
            return (byte[][])null;
        byte copy[][] = new byte[data.length][];
        for(int i = 0; i != data.length; i++)
            copy[i] = Arrays.clone(data[i]);

        return copy;
    }

    static byte[][][] clone(byte data[][][])
    {
        if(data == null)
            return (byte[][][])null;
        byte copy[][][] = new byte[data.length][][];
        for(int i = 0; i != data.length; i++)
            copy[i] = clone(data[i]);

        return copy;
    }

    static Treehash[] clone(Treehash data[])
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

    static Treehash[][] clone(Treehash data[][])
    {
        if(data == null)
            return (Treehash[][])null;
        Treehash copy[][] = new Treehash[data.length][];
        for(int i = 0; i != data.length; i++)
            copy[i] = clone(data[i]);

        return copy;
    }

    static Vector[] clone(Vector data[])
    {
        if(data == null)
            return null;
        Vector copy[] = new Vector[data.length];
        for(int i = 0; i != data.length; i++)
        {
            copy[i] = new Vector();
            for(Enumeration en = data[i].elements(); en.hasMoreElements(); copy[i].addElement(en.nextElement()));
        }

        return copy;
    }

    static Vector[][] clone(Vector data[][])
    {
        if(data == null)
            return (Vector[][])null;
        Vector copy[][] = new Vector[data.length][];
        for(int i = 0; i != data.length; i++)
            copy[i] = clone(data[i]);

        return copy;
    }
}
