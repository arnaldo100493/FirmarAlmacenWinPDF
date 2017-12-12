// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RainbowParameterSpec.java

package co.org.bouncy.pqc.jcajce.spec;

import co.org.bouncy.util.Arrays;
import java.security.spec.AlgorithmParameterSpec;

public class RainbowParameterSpec
    implements AlgorithmParameterSpec
{

    public RainbowParameterSpec()
    {
        vi = DEFAULT_VI;
    }

    public RainbowParameterSpec(int vi[])
    {
        this.vi = vi;
        try
        {
            checkParams();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void checkParams()
        throws Exception
    {
        if(vi == null)
            throw new IllegalArgumentException("no layers defined.");
        if(vi.length > 1)
        {
            for(int i = 0; i < vi.length - 1; i++)
                if(vi[i] >= vi[i + 1])
                    throw new IllegalArgumentException("v[i] has to be smaller than v[i+1]");

        } else
        {
            throw new IllegalArgumentException("Rainbow needs at least 1 layer, such that v1 < v2.");
        }
    }

    public int getNumOfLayers()
    {
        return vi.length - 1;
    }

    public int getDocumentLength()
    {
        return vi[vi.length - 1] - vi[0];
    }

    public int[] getVi()
    {
        return Arrays.clone(vi);
    }

    private static final int DEFAULT_VI[] = {
        6, 12, 17, 22, 33
    };
    private int vi[];

}
