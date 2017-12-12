// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SCrypt.java

package co.org.bouncy.crypto.generators;

import co.org.bouncy.crypto.PBEParametersGenerator;
import co.org.bouncy.crypto.digests.SHA256Digest;
import co.org.bouncy.crypto.engines.Salsa20Engine;
import co.org.bouncy.crypto.params.KeyParameter;
import co.org.bouncy.crypto.util.Pack;
import co.org.bouncy.util.Arrays;

// Referenced classes of package co.org.bouncy.crypto.generators:
//            PKCS5S2ParametersGenerator

public class SCrypt
{

    public SCrypt()
    {
    }

    public static byte[] generate(byte P[], byte S[], int N, int r, int p, int dkLen)
    {
        return MFcrypt(P, S, N, r, p, dkLen);
    }

    private static byte[] MFcrypt(byte P[], byte S[], int N, int r, int p, int dkLen)
    {
        int MFLenBytes;
        byte bytes[];
        int B[];
        MFLenBytes = r * 128;
        bytes = SingleIterationPBKDF2(P, S, p * MFLenBytes);
        B = null;
        byte abyte0[];
        int BLen = bytes.length >>> 2;
        B = new int[BLen];
        Pack.littleEndianToInt(bytes, 0, B);
        int MFLenWords = MFLenBytes >>> 2;
        for(int BOff = 0; BOff < BLen; BOff += MFLenWords)
            SMix(B, BOff, N, r);

        Pack.intToLittleEndian(B, bytes, 0);
        abyte0 = SingleIterationPBKDF2(P, bytes, dkLen);
        Clear(bytes);
        Clear(B);
        return abyte0;
        Exception exception;
        exception;
        Clear(bytes);
        Clear(B);
        throw exception;
    }

    private static byte[] SingleIterationPBKDF2(byte P[], byte S[], int dkLen)
    {
        PBEParametersGenerator pGen = new PKCS5S2ParametersGenerator(new SHA256Digest());
        pGen.init(P, S, 1);
        KeyParameter key = (KeyParameter)pGen.generateDerivedMacParameters(dkLen * 8);
        return key.getKey();
    }

    private static void SMix(int B[], int BOff, int N, int r)
    {
        int BCount;
        int blockX1[];
        int blockX2[];
        int blockY[];
        int X[];
        int V[][];
        BCount = r * 32;
        blockX1 = new int[16];
        blockX2 = new int[16];
        blockY = new int[BCount];
        X = new int[BCount];
        V = new int[N][];
        System.arraycopy(B, BOff, X, 0, BCount);
        for(int i = 0; i < N; i++)
        {
            V[i] = Arrays.clone(X);
            BlockMix(X, blockX1, blockX2, blockY, r);
        }

        int mask = N - 1;
        for(int i = 0; i < N; i++)
        {
            int j = X[BCount - 16] & mask;
            Xor(X, V[j], 0, X);
            BlockMix(X, blockX1, blockX2, blockY, r);
        }

        System.arraycopy(X, 0, B, BOff, BCount);
        ClearAll(V);
        ClearAll(new int[][] {
            X, blockX1, blockX2, blockY
        });
        break MISSING_BLOCK_LABEL_223;
        Exception exception;
        exception;
        ClearAll(V);
        ClearAll(new int[][] {
            X, blockX1, blockX2, blockY
        });
        throw exception;
    }

    private static void BlockMix(int B[], int X1[], int X2[], int Y[], int r)
    {
        System.arraycopy(B, B.length - 16, X1, 0, 16);
        int BOff = 0;
        int YOff = 0;
        int halfLen = B.length >>> 1;
        for(int i = 2 * r; i > 0; i--)
        {
            Xor(X1, B, BOff, X2);
            Salsa20Engine.salsaCore(8, X2, X1);
            System.arraycopy(X1, 0, Y, YOff, 16);
            YOff = (halfLen + BOff) - YOff;
            BOff += 16;
        }

        System.arraycopy(Y, 0, B, 0, Y.length);
    }

    private static void Xor(int a[], int b[], int bOff, int output[])
    {
        for(int i = output.length - 1; i >= 0; i--)
            output[i] = a[i] ^ b[bOff + i];

    }

    private static void Clear(byte array[])
    {
        if(array != null)
            Arrays.fill(array, (byte)0);
    }

    private static void Clear(int array[])
    {
        if(array != null)
            Arrays.fill(array, 0);
    }

    private static void ClearAll(int arrays[][])
    {
        for(int i = 0; i < arrays.length; i++)
            Clear(arrays[i]);

    }
}
