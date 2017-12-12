// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Tables8kGCMMultiplier.java

package co.org.bouncy.crypto.modes.gcm;

import co.org.bouncy.crypto.util.Pack;
import co.org.bouncy.util.Arrays;

// Referenced classes of package co.org.bouncy.crypto.modes.gcm:
//            GCMMultiplier, GCMUtil

public class Tables8kGCMMultiplier
    implements GCMMultiplier
{

    public Tables8kGCMMultiplier()
    {
    }

    public void init(byte H[])
    {
        if(M == null)
            M = new int[32][16][4];
        else
        if(Arrays.areEqual(this.H, H))
            return;
        this.H = Arrays.clone(H);
        GCMUtil.asInts(H, M[1][8]);
        for(int j = 4; j >= 1; j >>= 1)
            GCMUtil.multiplyP(M[1][j + j], M[1][j]);

        GCMUtil.multiplyP(M[1][1], M[0][8]);
        for(int j = 4; j >= 1; j >>= 1)
            GCMUtil.multiplyP(M[0][j + j], M[0][j]);

        int i = 0;
        do
        {
            int j;
            do
            {
                for(j = 2; j < 16; j += j)
                {
                    for(int k = 1; k < j; k++)
                        GCMUtil.xor(M[i][j], M[i][k], M[i][j + k]);

                }

                if(++i == 32)
                    return;
            } while(i <= 1);
            j = 8;
            while(j > 0) 
            {
                GCMUtil.multiplyP8(M[i - 2][j], M[i][j]);
                j >>= 1;
            }
        } while(true);
    }

    public void multiplyH(byte x[])
    {
        int z[] = new int[4];
        for(int i = 15; i >= 0; i--)
        {
            int m[] = M[i + i][x[i] & 0xf];
            z[0] ^= m[0];
            z[1] ^= m[1];
            z[2] ^= m[2];
            z[3] ^= m[3];
            m = M[i + i + 1][(x[i] & 0xf0) >>> 4];
            z[0] ^= m[0];
            z[1] ^= m[1];
            z[2] ^= m[2];
            z[3] ^= m[3];
        }

        Pack.intToBigEndian(z, x, 0);
    }

    private byte H[];
    private int M[][][];
}
