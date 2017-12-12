// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Salsa20Engine.java

package co.org.bouncy.crypto.engines;

import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.params.KeyParameter;
import co.org.bouncy.crypto.params.ParametersWithIV;
import co.org.bouncy.crypto.util.Pack;
import co.org.bouncy.util.Strings;

public class Salsa20Engine
    implements StreamCipher
{

    public Salsa20Engine()
    {
        index = 0;
        engineState = new int[16];
        x = new int[16];
        keyStream = new byte[64];
        workingKey = null;
        workingIV = null;
        initialised = false;
    }

    public void init(boolean forEncryption, CipherParameters params)
    {
        if(!(params instanceof ParametersWithIV))
            throw new IllegalArgumentException("Salsa20 Init parameters must include an IV");
        ParametersWithIV ivParams = (ParametersWithIV)params;
        byte iv[] = ivParams.getIV();
        if(iv == null || iv.length != 8)
            throw new IllegalArgumentException("Salsa20 requires exactly 8 bytes of IV");
        if(!(ivParams.getParameters() instanceof KeyParameter))
        {
            throw new IllegalArgumentException("Salsa20 Init parameters must include a key");
        } else
        {
            KeyParameter key = (KeyParameter)ivParams.getParameters();
            workingKey = key.getKey();
            workingIV = iv;
            setKey(workingKey, workingIV);
            return;
        }
    }

    public String getAlgorithmName()
    {
        return "Salsa20";
    }

    public byte returnByte(byte in)
    {
        if(limitExceeded())
            throw new MaxBytesExceededException("2^70 byte limit per IV; Change IV");
        if(index == 0)
        {
            generateKeyStream(keyStream);
            if(++engineState[8] == 0)
                engineState[9]++;
        }
        byte out = (byte)(keyStream[index] ^ in);
        index = index + 1 & 0x3f;
        return out;
    }

    public void processBytes(byte in[], int inOff, int len, byte out[], int outOff)
    {
        if(!initialised)
            throw new IllegalStateException((new StringBuilder()).append(getAlgorithmName()).append(" not initialised").toString());
        if(inOff + len > in.length)
            throw new DataLengthException("input buffer too short");
        if(outOff + len > out.length)
            throw new OutputLengthException("output buffer too short");
        if(limitExceeded(len))
            throw new MaxBytesExceededException("2^70 byte limit per IV would be exceeded; Change IV");
        for(int i = 0; i < len; i++)
        {
            if(index == 0)
            {
                generateKeyStream(keyStream);
                if(++engineState[8] == 0)
                    engineState[9]++;
            }
            out[i + outOff] = (byte)(keyStream[index] ^ in[i + inOff]);
            index = index + 1 & 0x3f;
        }

    }

    public void reset()
    {
        setKey(workingKey, workingIV);
    }

    private void setKey(byte keyBytes[], byte ivBytes[])
    {
        workingKey = keyBytes;
        workingIV = ivBytes;
        index = 0;
        resetCounter();
        int offset = 0;
        engineState[1] = Pack.littleEndianToInt(workingKey, 0);
        engineState[2] = Pack.littleEndianToInt(workingKey, 4);
        engineState[3] = Pack.littleEndianToInt(workingKey, 8);
        engineState[4] = Pack.littleEndianToInt(workingKey, 12);
        byte constants[];
        if(workingKey.length == 32)
        {
            constants = sigma;
            offset = 16;
        } else
        {
            constants = tau;
        }
        engineState[11] = Pack.littleEndianToInt(workingKey, offset);
        engineState[12] = Pack.littleEndianToInt(workingKey, offset + 4);
        engineState[13] = Pack.littleEndianToInt(workingKey, offset + 8);
        engineState[14] = Pack.littleEndianToInt(workingKey, offset + 12);
        engineState[0] = Pack.littleEndianToInt(constants, 0);
        engineState[5] = Pack.littleEndianToInt(constants, 4);
        engineState[10] = Pack.littleEndianToInt(constants, 8);
        engineState[15] = Pack.littleEndianToInt(constants, 12);
        engineState[6] = Pack.littleEndianToInt(workingIV, 0);
        engineState[7] = Pack.littleEndianToInt(workingIV, 4);
        engineState[8] = engineState[9] = 0;
        initialised = true;
    }

    private void generateKeyStream(byte output[])
    {
        salsaCore(20, engineState, x);
        Pack.intToLittleEndian(x, output, 0);
    }

    public static void salsaCore(int rounds, int input[], int x[])
    {
        System.arraycopy(input, 0, x, 0, input.length);
        for(int i = rounds; i > 0; i -= 2)
        {
            x[4] ^= rotl(x[0] + x[12], 7);
            x[8] ^= rotl(x[4] + x[0], 9);
            x[12] ^= rotl(x[8] + x[4], 13);
            x[0] ^= rotl(x[12] + x[8], 18);
            x[9] ^= rotl(x[5] + x[1], 7);
            x[13] ^= rotl(x[9] + x[5], 9);
            x[1] ^= rotl(x[13] + x[9], 13);
            x[5] ^= rotl(x[1] + x[13], 18);
            x[14] ^= rotl(x[10] + x[6], 7);
            x[2] ^= rotl(x[14] + x[10], 9);
            x[6] ^= rotl(x[2] + x[14], 13);
            x[10] ^= rotl(x[6] + x[2], 18);
            x[3] ^= rotl(x[15] + x[11], 7);
            x[7] ^= rotl(x[3] + x[15], 9);
            x[11] ^= rotl(x[7] + x[3], 13);
            x[15] ^= rotl(x[11] + x[7], 18);
            x[1] ^= rotl(x[0] + x[3], 7);
            x[2] ^= rotl(x[1] + x[0], 9);
            x[3] ^= rotl(x[2] + x[1], 13);
            x[0] ^= rotl(x[3] + x[2], 18);
            x[6] ^= rotl(x[5] + x[4], 7);
            x[7] ^= rotl(x[6] + x[5], 9);
            x[4] ^= rotl(x[7] + x[6], 13);
            x[5] ^= rotl(x[4] + x[7], 18);
            x[11] ^= rotl(x[10] + x[9], 7);
            x[8] ^= rotl(x[11] + x[10], 9);
            x[9] ^= rotl(x[8] + x[11], 13);
            x[10] ^= rotl(x[9] + x[8], 18);
            x[12] ^= rotl(x[15] + x[14], 7);
            x[13] ^= rotl(x[12] + x[15], 9);
            x[14] ^= rotl(x[13] + x[12], 13);
            x[15] ^= rotl(x[14] + x[13], 18);
        }

        for(int i = 0; i < 16; i++)
            x[i] += input[i];

    }

    private static int rotl(int x, int y)
    {
        return x << y | x >>> -y;
    }

    private void resetCounter()
    {
        cW0 = 0;
        cW1 = 0;
        cW2 = 0;
    }

    private boolean limitExceeded()
    {
        if(++cW0 == 0 && ++cW1 == 0)
            return (++cW2 & 0x20) != 0;
        else
            return false;
    }

    private boolean limitExceeded(int len)
    {
        cW0 += len;
        if(cW0 < len && cW0 >= 0 && ++cW1 == 0)
            return (++cW2 & 0x20) != 0;
        else
            return false;
    }

    private static final int STATE_SIZE = 16;
    private static final byte sigma[] = Strings.toByteArray("expand 32-byte k");
    private static final byte tau[] = Strings.toByteArray("expand 16-byte k");
    private int index;
    private int engineState[];
    private int x[];
    private byte keyStream[];
    private byte workingKey[];
    private byte workingIV[];
    private boolean initialised;
    private int cW0;
    private int cW1;
    private int cW2;

}
