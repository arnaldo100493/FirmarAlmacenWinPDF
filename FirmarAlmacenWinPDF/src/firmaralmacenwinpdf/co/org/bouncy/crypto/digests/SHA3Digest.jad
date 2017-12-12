// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SHA3Digest.java

package co.org.bouncy.crypto.digests;

import co.org.bouncy.crypto.ExtendedDigest;
import co.org.bouncy.util.Arrays;

public class SHA3Digest
    implements ExtendedDigest
{

    private static long[] keccakInitializeRoundConstants()
    {
        long keccakRoundConstants[] = new long[24];
        byte LFSRstate[] = new byte[1];
        LFSRstate[0] = 1;
        for(int i = 0; i < 24; i++)
        {
            keccakRoundConstants[i] = 0L;
            for(int j = 0; j < 7; j++)
            {
                int bitPosition = (1 << j) - 1;
                if(LFSR86540(LFSRstate))
                    keccakRoundConstants[i] ^= 1L << bitPosition;
            }

        }

        return keccakRoundConstants;
    }

    private static boolean LFSR86540(byte LFSR[])
    {
        boolean result = (LFSR[0] & 1) != 0;
        if((LFSR[0] & 0x80) != 0)
            LFSR[0] = (byte)(LFSR[0] << 1 ^ 0x71);
        else
            LFSR[0] <<= 1;
        return result;
    }

    private static int[] keccakInitializeRhoOffsets()
    {
        int keccakRhoOffsets[] = new int[25];
        keccakRhoOffsets[0] = 0;
        int x = 1;
        int y = 0;
        for(int t = 0; t < 24; t++)
        {
            keccakRhoOffsets[x % 5 + 5 * (y % 5)] = (((t + 1) * (t + 2)) / 2) % 64;
            int newX = (0 * x + 1 * y) % 5;
            int newY = (2 * x + 3 * y) % 5;
            x = newX;
            y = newY;
        }

        return keccakRhoOffsets;
    }

    private void clearDataQueueSection(int off, int len)
    {
        for(int i = off; i != off + len; i++)
            dataQueue[i] = 0;

    }

    public SHA3Digest()
    {
        state = new byte[200];
        dataQueue = new byte[192];
        C = new long[5];
        tempA = new long[25];
        chiC = new long[5];
        init(0);
    }

    public SHA3Digest(int bitLength)
    {
        state = new byte[200];
        dataQueue = new byte[192];
        C = new long[5];
        tempA = new long[25];
        chiC = new long[5];
        init(bitLength);
    }

    public SHA3Digest(SHA3Digest source)
    {
        state = new byte[200];
        dataQueue = new byte[192];
        C = new long[5];
        tempA = new long[25];
        chiC = new long[5];
        System.arraycopy(source.state, 0, state, 0, source.state.length);
        System.arraycopy(source.dataQueue, 0, dataQueue, 0, source.dataQueue.length);
        rate = source.rate;
        bitsInQueue = source.bitsInQueue;
        fixedOutputLength = source.fixedOutputLength;
        squeezing = source.squeezing;
        bitsAvailableForSqueezing = source.bitsAvailableForSqueezing;
        chunk = Arrays.clone(source.chunk);
        oneByte = Arrays.clone(source.oneByte);
    }

    public String getAlgorithmName()
    {
        return (new StringBuilder()).append("SHA3-").append(fixedOutputLength).toString();
    }

    public int getDigestSize()
    {
        return fixedOutputLength / 8;
    }

    public void update(byte in)
    {
        oneByte[0] = in;
        doUpdate(oneByte, 0, 8L);
    }

    public void update(byte in[], int inOff, int len)
    {
        doUpdate(in, inOff, (long)len * 8L);
    }

    public int doFinal(byte out[], int outOff)
    {
        squeeze(out, outOff, fixedOutputLength);
        reset();
        return getDigestSize();
    }

    public void reset()
    {
        init(fixedOutputLength);
    }

    public int getByteLength()
    {
        return rate / 8;
    }

    private void init(int bitLength)
    {
        switch(bitLength)
        {
        case 0: // '\0'
        case 288: 
            initSponge(1024, 576);
            break;

        case 224: 
            initSponge(1152, 448);
            break;

        case 256: 
            initSponge(1088, 512);
            break;

        case 384: 
            initSponge(832, 768);
            break;

        case 512: 
            initSponge(576, 1024);
            break;

        default:
            throw new IllegalArgumentException("bitLength must be one of 224, 256, 384, or 512.");
        }
    }

    private void doUpdate(byte data[], int off, long databitlen)
    {
        if(databitlen % 8L == 0L)
        {
            absorb(data, off, databitlen);
        } else
        {
            absorb(data, off, databitlen - databitlen % 8L);
            byte lastByte[] = new byte[1];
            lastByte[0] = (byte)(data[off + (int)(databitlen / 8L)] >> (int)(8L - databitlen % 8L));
            absorb(lastByte, off, databitlen % 8L);
        }
    }

    private void initSponge(int rate, int capacity)
    {
        if(rate + capacity != 1600)
            throw new IllegalStateException("rate + capacity != 1600");
        if(rate <= 0 || rate >= 1600 || rate % 64 != 0)
        {
            throw new IllegalStateException("invalid rate value");
        } else
        {
            this.rate = rate;
            fixedOutputLength = 0;
            Arrays.fill(state, (byte)0);
            Arrays.fill(dataQueue, (byte)0);
            bitsInQueue = 0;
            squeezing = false;
            bitsAvailableForSqueezing = 0;
            fixedOutputLength = capacity / 2;
            chunk = new byte[rate / 8];
            oneByte = new byte[1];
            return;
        }
    }

    private void absorbQueue()
    {
        KeccakAbsorb(state, dataQueue, rate / 8);
        bitsInQueue = 0;
    }

    private void absorb(byte data[], int off, long databitlen)
    {
        if(bitsInQueue % 8 != 0)
            throw new IllegalStateException("attempt to absorb with odd length queue.");
        if(squeezing)
            throw new IllegalStateException("attempt to absorb while squeezing.");
        long i = 0L;
        do
        {
            if(i >= databitlen)
                break;
            if(bitsInQueue == 0 && databitlen >= (long)rate && i <= databitlen - (long)rate)
            {
                long wholeBlocks = (databitlen - i) / (long)rate;
                for(long j = 0L; j < wholeBlocks; j++)
                {
                    System.arraycopy(data, (int)((long)off + i / 8L + j * (long)chunk.length), chunk, 0, chunk.length);
                    KeccakAbsorb(state, chunk, chunk.length);
                }

                i += wholeBlocks * (long)rate;
            } else
            {
                int partialBlock = (int)(databitlen - i);
                if(partialBlock + bitsInQueue > rate)
                    partialBlock = rate - bitsInQueue;
                int partialByte = partialBlock % 8;
                partialBlock -= partialByte;
                System.arraycopy(data, off + (int)(i / 8L), dataQueue, bitsInQueue / 8, partialBlock / 8);
                bitsInQueue += partialBlock;
                i += partialBlock;
                if(bitsInQueue == rate)
                    absorbQueue();
                if(partialByte > 0)
                {
                    int mask = (1 << partialByte) - 1;
                    dataQueue[bitsInQueue / 8] = (byte)(data[off + (int)(i / 8L)] & mask);
                    bitsInQueue += partialByte;
                    i += partialByte;
                }
            }
        } while(true);
    }

    private void padAndSwitchToSqueezingPhase()
    {
        if(bitsInQueue + 1 == rate)
        {
            dataQueue[bitsInQueue / 8] |= 1 << bitsInQueue % 8;
            absorbQueue();
            clearDataQueueSection(0, rate / 8);
        } else
        {
            clearDataQueueSection((bitsInQueue + 7) / 8, rate / 8 - (bitsInQueue + 7) / 8);
            dataQueue[bitsInQueue / 8] |= 1 << bitsInQueue % 8;
        }
        dataQueue[(rate - 1) / 8] |= 1 << (rate - 1) % 8;
        absorbQueue();
        if(rate == 1024)
        {
            KeccakExtract1024bits(state, dataQueue);
            bitsAvailableForSqueezing = 1024;
        } else
        {
            KeccakExtract(state, dataQueue, rate / 64);
            bitsAvailableForSqueezing = rate;
        }
        squeezing = true;
    }

    private void squeeze(byte output[], int offset, long outputLength)
    {
        if(!squeezing)
            padAndSwitchToSqueezingPhase();
        if(outputLength % 8L != 0L)
            throw new IllegalStateException("outputLength not a multiple of 8");
        int partialBlock;
        for(long i = 0L; i < outputLength; i += partialBlock)
        {
            if(bitsAvailableForSqueezing == 0)
            {
                keccakPermutation(state);
                if(rate == 1024)
                {
                    KeccakExtract1024bits(state, dataQueue);
                    bitsAvailableForSqueezing = 1024;
                } else
                {
                    KeccakExtract(state, dataQueue, rate / 64);
                    bitsAvailableForSqueezing = rate;
                }
            }
            partialBlock = bitsAvailableForSqueezing;
            if((long)partialBlock > outputLength - i)
                partialBlock = (int)(outputLength - i);
            System.arraycopy(dataQueue, (rate - bitsAvailableForSqueezing) / 8, output, offset + (int)(i / 8L), partialBlock / 8);
            bitsAvailableForSqueezing -= partialBlock;
        }

    }

    private void fromBytesToWords(long stateAsWords[], byte state[])
    {
        for(int i = 0; i < 25; i++)
        {
            stateAsWords[i] = 0L;
            int index = i * 8;
            for(int j = 0; j < 8; j++)
                stateAsWords[i] |= ((long)state[index + j] & 255L) << 8 * j;

        }

    }

    private void fromWordsToBytes(byte state[], long stateAsWords[])
    {
        for(int i = 0; i < 25; i++)
        {
            int index = i * 8;
            for(int j = 0; j < 8; j++)
                state[index + j] = (byte)(int)(stateAsWords[i] >>> 8 * j & 255L);

        }

    }

    private void keccakPermutation(byte state[])
    {
        long longState[] = new long[state.length / 8];
        fromBytesToWords(longState, state);
        keccakPermutationOnWords(longState);
        fromWordsToBytes(state, longState);
    }

    private void keccakPermutationAfterXor(byte state[], byte data[], int dataLengthInBytes)
    {
        for(int i = 0; i < dataLengthInBytes; i++)
            state[i] ^= data[i];

        keccakPermutation(state);
    }

    private void keccakPermutationOnWords(long state[])
    {
        for(int i = 0; i < 24; i++)
        {
            theta(state);
            rho(state);
            pi(state);
            chi(state);
            iota(state, i);
        }

    }

    private void theta(long A[])
    {
        for(int x = 0; x < 5; x++)
        {
            C[x] = 0L;
            for(int y = 0; y < 5; y++)
                C[x] ^= A[x + 5 * y];

        }

        for(int x = 0; x < 5; x++)
        {
            long dX = C[(x + 1) % 5] << 1 ^ C[(x + 1) % 5] >>> 63 ^ C[(x + 4) % 5];
            for(int y = 0; y < 5; y++)
                A[x + 5 * y] ^= dX;

        }

    }

    private void rho(long A[])
    {
        for(int x = 0; x < 5; x++)
        {
            for(int y = 0; y < 5; y++)
            {
                int index = x + 5 * y;
                A[index] = KeccakRhoOffsets[index] == 0 ? A[index] : A[index] << KeccakRhoOffsets[index] ^ A[index] >>> 64 - KeccakRhoOffsets[index];
            }

        }

    }

    private void pi(long A[])
    {
        System.arraycopy(A, 0, tempA, 0, tempA.length);
        for(int x = 0; x < 5; x++)
        {
            for(int y = 0; y < 5; y++)
                A[y + 5 * ((2 * x + 3 * y) % 5)] = tempA[x + 5 * y];

        }

    }

    private void chi(long A[])
    {
        for(int y = 0; y < 5; y++)
        {
            for(int x = 0; x < 5; x++)
                chiC[x] = A[x + 5 * y] ^ ~A[(x + 1) % 5 + 5 * y] & A[(x + 2) % 5 + 5 * y];

            for(int x = 0; x < 5; x++)
                A[x + 5 * y] = chiC[x];

        }

    }

    private void iota(long A[], int indexRound)
    {
        A[0] ^= KeccakRoundConstants[indexRound];
    }

    private void KeccakAbsorb(byte byteState[], byte data[], int dataInBytes)
    {
        keccakPermutationAfterXor(byteState, data, dataInBytes);
    }

    private void KeccakExtract1024bits(byte byteState[], byte data[])
    {
        System.arraycopy(byteState, 0, data, 0, 128);
    }

    private void KeccakExtract(byte byteState[], byte data[], int laneCount)
    {
        System.arraycopy(byteState, 0, data, 0, laneCount * 8);
    }

    private static long KeccakRoundConstants[] = keccakInitializeRoundConstants();
    private static int KeccakRhoOffsets[] = keccakInitializeRhoOffsets();
    private byte state[];
    private byte dataQueue[];
    private int rate;
    private int bitsInQueue;
    private int fixedOutputLength;
    private boolean squeezing;
    private int bitsAvailableForSqueezing;
    private byte chunk[];
    private byte oneByte[];
    long C[];
    long tempA[];
    long chiC[];

}
