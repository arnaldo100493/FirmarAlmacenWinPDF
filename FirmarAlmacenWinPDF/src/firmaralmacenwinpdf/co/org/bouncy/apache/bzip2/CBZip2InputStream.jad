// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CBZip2InputStream.java

package co.org.bouncy.apache.bzip2;

import java.io.*;

// Referenced classes of package co.org.bouncy.apache.bzip2:
//            CRC, BZip2Constants

public class CBZip2InputStream extends InputStream
    implements BZip2Constants
{

    private static void cadvise()
    {
        System.out.println("CRC Error");
    }

    private static void compressedStreamEOF()
    {
        cadvise();
    }

    private void makeMaps()
    {
        nInUse = 0;
        for(int i = 0; i < 256; i++)
            if(inUse[i])
            {
                seqToUnseq[nInUse] = (char)i;
                unseqToSeq[i] = (char)nInUse;
                nInUse++;
            }

    }

    public CBZip2InputStream(InputStream zStream)
        throws IOException
    {
        mCrc = new CRC();
        inUse = new boolean[256];
        seqToUnseq = new char[256];
        unseqToSeq = new char[256];
        selector = new char[18002];
        selectorMtf = new char[18002];
        unzftab = new int[256];
        limit = new int[6][258];
        base = new int[6][258];
        perm = new int[6][258];
        minLens = new int[6];
        streamEnd = false;
        currentChar = -1;
        currentState = 1;
        rNToGo = 0;
        rTPos = 0;
        ll8 = null;
        tt = null;
        bsSetStream(zStream);
        initialize();
        initBlock();
        setupBlock();
    }

    public int read()
    {
        if(streamEnd)
            return -1;
        int retChar = currentChar;
        switch(currentState)
        {
        case 3: // '\003'
            setupRandPartB();
            break;

        case 4: // '\004'
            setupRandPartC();
            break;

        case 6: // '\006'
            setupNoRandPartB();
            break;

        case 7: // '\007'
            setupNoRandPartC();
            break;
        }
        return retChar;
    }

    private void initialize()
        throws IOException
    {
        char magic3 = bsGetUChar();
        char magic4 = bsGetUChar();
        if(magic3 != 'B' && magic4 != 'Z')
            throw new IOException("Not a BZIP2 marked stream");
        magic3 = bsGetUChar();
        magic4 = bsGetUChar();
        if(magic3 != 'h' || magic4 < '1' || magic4 > '9')
        {
            bsFinishedWithStream();
            streamEnd = true;
            return;
        } else
        {
            setDecompressStructureSizes(magic4 - 48);
            computedCombinedCRC = 0;
            return;
        }
    }

    private void initBlock()
    {
        char magic1 = bsGetUChar();
        char magic2 = bsGetUChar();
        char magic3 = bsGetUChar();
        char magic4 = bsGetUChar();
        char magic5 = bsGetUChar();
        char magic6 = bsGetUChar();
        if(magic1 == '\027' && magic2 == 'r' && magic3 == 'E' && magic4 == '8' && magic5 == 'P' && magic6 == '\220')
        {
            complete();
            return;
        }
        if(magic1 != '1' || magic2 != 'A' || magic3 != 'Y' || magic4 != '&' || magic5 != 'S' || magic6 != 'Y')
        {
            badBlockHeader();
            streamEnd = true;
            return;
        }
        storedBlockCRC = bsGetInt32();
        if(bsR(1) == 1)
            blockRandomised = true;
        else
            blockRandomised = false;
        getAndMoveToFrontDecode();
        mCrc.initialiseCRC();
        currentState = 1;
    }

    private void endBlock()
    {
        computedBlockCRC = mCrc.getFinalCRC();
        if(storedBlockCRC != computedBlockCRC)
            crcError();
        computedCombinedCRC = computedCombinedCRC << 1 | computedCombinedCRC >>> 31;
        computedCombinedCRC ^= computedBlockCRC;
    }

    private void complete()
    {
        storedCombinedCRC = bsGetInt32();
        if(storedCombinedCRC != computedCombinedCRC)
            crcError();
        bsFinishedWithStream();
        streamEnd = true;
    }

    private static void blockOverrun()
    {
        cadvise();
    }

    private static void badBlockHeader()
    {
        cadvise();
    }

    private static void crcError()
    {
        cadvise();
    }

    private void bsFinishedWithStream()
    {
        try
        {
            if(bsStream != null && bsStream != System.in)
            {
                bsStream.close();
                bsStream = null;
            }
        }
        catch(IOException ioe) { }
    }

    private void bsSetStream(InputStream f)
    {
        bsStream = f;
        bsLive = 0;
        bsBuff = 0;
    }

    private int bsR(int n)
    {
        for(; bsLive < n; bsLive += 8)
        {
            char thech = '\0';
            try
            {
                thech = (char)bsStream.read();
            }
            catch(IOException e)
            {
                compressedStreamEOF();
            }
            if(thech == '\uFFFF')
                compressedStreamEOF();
            int zzi = thech;
            bsBuff = bsBuff << 8 | zzi & 0xff;
        }

        int v = bsBuff >> bsLive - n & (1 << n) - 1;
        bsLive -= n;
        return v;
    }

    private char bsGetUChar()
    {
        return (char)bsR(8);
    }

    private int bsGetint()
    {
        int u = 0;
        u = u << 8 | bsR(8);
        u = u << 8 | bsR(8);
        u = u << 8 | bsR(8);
        u = u << 8 | bsR(8);
        return u;
    }

    private int bsGetIntVS(int numBits)
    {
        return bsR(numBits);
    }

    private int bsGetInt32()
    {
        return bsGetint();
    }

    private void hbCreateDecodeTables(int limit[], int base[], int perm[], char length[], int minLen, int maxLen, int alphaSize)
    {
        int pp = 0;
        for(int i = minLen; i <= maxLen; i++)
        {
            for(int j = 0; j < alphaSize; j++)
                if(length[j] == i)
                {
                    perm[pp] = j;
                    pp++;
                }

        }

        for(int i = 0; i < 23; i++)
            base[i] = 0;

        for(int i = 0; i < alphaSize; i++)
            base[length[i] + 1]++;

        for(int i = 1; i < 23; i++)
            base[i] += base[i - 1];

        for(int i = 0; i < 23; i++)
            limit[i] = 0;

        int vec = 0;
        for(int i = minLen; i <= maxLen; i++)
        {
            vec += base[i + 1] - base[i];
            limit[i] = vec - 1;
            vec <<= 1;
        }

        for(int i = minLen + 1; i <= maxLen; i++)
            base[i] = (limit[i - 1] + 1 << 1) - base[i];

    }

    private void recvDecodingTables()
    {
        char len[][] = new char[6][258];
        boolean inUse16[] = new boolean[16];
        for(int i = 0; i < 16; i++)
            if(bsR(1) == 1)
                inUse16[i] = true;
            else
                inUse16[i] = false;

        for(int i = 0; i < 256; i++)
            inUse[i] = false;

        for(int i = 0; i < 16; i++)
        {
            if(!inUse16[i])
                continue;
            for(int j = 0; j < 16; j++)
                if(bsR(1) == 1)
                    inUse[i * 16 + j] = true;

        }

        makeMaps();
        int alphaSize = nInUse + 2;
        int nGroups = bsR(3);
        int nSelectors = bsR(15);
        for(int i = 0; i < nSelectors; i++)
        {
            int j;
            for(j = 0; bsR(1) == 1; j++);
            selectorMtf[i] = (char)j;
        }

        char pos[] = new char[6];
        for(char v = '\0'; v < nGroups; v++)
            pos[v] = v;

        for(int i = 0; i < nSelectors; i++)
        {
            char v = selectorMtf[i];
            char tmp = pos[v];
            for(; v > 0; v--)
                pos[v] = pos[v - 1];

            pos[0] = tmp;
            selector[i] = tmp;
        }

        for(int t = 0; t < nGroups; t++)
        {
            int curr = bsR(5);
            for(int i = 0; i < alphaSize; i++)
            {
                while(bsR(1) == 1) 
                    if(bsR(1) == 0)
                        curr++;
                    else
                        curr--;
                len[t][i] = (char)curr;
            }

        }

        for(int t = 0; t < nGroups; t++)
        {
            int minLen = 32;
            int maxLen = 0;
            for(int i = 0; i < alphaSize; i++)
            {
                if(len[t][i] > maxLen)
                    maxLen = len[t][i];
                if(len[t][i] < minLen)
                    minLen = len[t][i];
            }

            hbCreateDecodeTables(limit[t], base[t], perm[t], len[t], minLen, maxLen, alphaSize);
            minLens[t] = minLen;
        }

    }

    private void getAndMoveToFrontDecode()
    {
        char yy[] = new char[256];
        int limitLast = 0x186a0 * blockSize100k;
        origPtr = bsGetIntVS(24);
        recvDecodingTables();
        int EOB = nInUse + 1;
        int groupNo = -1;
        int groupPos = 0;
        for(int i = 0; i <= 255; i++)
            unzftab[i] = 0;

        for(int i = 0; i <= 255; i++)
            yy[i] = (char)i;

        last = -1;
        if(groupPos == 0)
        {
            groupNo++;
            groupPos = 50;
        }
        groupPos--;
        int zt = selector[groupNo];
        int zn = minLens[zt];
        int zvec;
        int zj;
        for(zvec = bsR(zn); zvec > limit[zt][zn]; zvec = zvec << 1 | zj)
        {
            zn++;
            for(; bsLive < 1; bsLive += 8)
            {
                char thech = '\0';
                try
                {
                    thech = (char)bsStream.read();
                }
                catch(IOException e)
                {
                    compressedStreamEOF();
                }
                if(thech == '\uFFFF')
                    compressedStreamEOF();
                int zzi = thech;
                bsBuff = bsBuff << 8 | zzi & 0xff;
            }

            zj = bsBuff >> bsLive - 1 & 1;
            bsLive--;
        }

        int nextSym = perm[zt][zvec - base[zt][zn]];
        do
        {
            if(nextSym == EOB)
                break;
            if(nextSym == 0 || nextSym == 1)
            {
                int s = -1;
                int N = 1;
                do
                {
                    if(nextSym == 0)
                        s += 1 * N;
                    else
                    if(nextSym == 1)
                        s += 2 * N;
                    N *= 2;
                    if(groupPos == 0)
                    {
                        groupNo++;
                        groupPos = 50;
                    }
                    groupPos--;
                    int zt = selector[groupNo];
                    int zn = minLens[zt];
                    int zvec;
                    int zj;
                    for(zvec = bsR(zn); zvec > limit[zt][zn]; zvec = zvec << 1 | zj)
                    {
                        zn++;
                        for(; bsLive < 1; bsLive += 8)
                        {
                            char thech = '\0';
                            try
                            {
                                thech = (char)bsStream.read();
                            }
                            catch(IOException e)
                            {
                                compressedStreamEOF();
                            }
                            if(thech == '\uFFFF')
                                compressedStreamEOF();
                            int zzi = thech;
                            bsBuff = bsBuff << 8 | zzi & 0xff;
                        }

                        zj = bsBuff >> bsLive - 1 & 1;
                        bsLive--;
                    }

                    nextSym = perm[zt][zvec - base[zt][zn]];
                } while(nextSym == 0 || nextSym == 1);
                s++;
                char ch = seqToUnseq[yy[0]];
                unzftab[ch] += s;
                for(; s > 0; s--)
                {
                    last++;
                    ll8[last] = ch;
                }

                if(last >= limitLast)
                    blockOverrun();
            } else
            {
                last++;
                if(last >= limitLast)
                    blockOverrun();
                char tmp = yy[nextSym - 1];
                unzftab[seqToUnseq[tmp]]++;
                ll8[last] = seqToUnseq[tmp];
                int j;
                for(j = nextSym - 1; j > 3; j -= 4)
                {
                    yy[j] = yy[j - 1];
                    yy[j - 1] = yy[j - 2];
                    yy[j - 2] = yy[j - 3];
                    yy[j - 3] = yy[j - 4];
                }

                for(; j > 0; j--)
                    yy[j] = yy[j - 1];

                yy[0] = tmp;
                if(groupPos == 0)
                {
                    groupNo++;
                    groupPos = 50;
                }
                groupPos--;
                int zt = selector[groupNo];
                int zn = minLens[zt];
                int zvec;
                int zj;
                for(zvec = bsR(zn); zvec > limit[zt][zn]; zvec = zvec << 1 | zj)
                {
                    zn++;
                    for(; bsLive < 1; bsLive += 8)
                    {
                        char thech = '\0';
                        try
                        {
                            thech = (char)bsStream.read();
                        }
                        catch(IOException e)
                        {
                            compressedStreamEOF();
                        }
                        int zzi = thech;
                        bsBuff = bsBuff << 8 | zzi & 0xff;
                    }

                    zj = bsBuff >> bsLive - 1 & 1;
                    bsLive--;
                }

                nextSym = perm[zt][zvec - base[zt][zn]];
            }
        } while(true);
    }

    private void setupBlock()
    {
        int cftab[] = new int[257];
        cftab[0] = 0;
        for(i = 1; i <= 256; i++)
            cftab[i] = unzftab[i - 1];

        for(i = 1; i <= 256; i++)
            cftab[i] += cftab[i - 1];

        for(i = 0; i <= last; i++)
        {
            char ch = ll8[i];
            tt[cftab[ch]] = i;
            cftab[ch]++;
        }

        cftab = null;
        tPos = tt[origPtr];
        count = 0;
        i2 = 0;
        ch2 = 256;
        if(blockRandomised)
        {
            rNToGo = 0;
            rTPos = 0;
            setupRandPartA();
        } else
        {
            setupNoRandPartA();
        }
    }

    private void setupRandPartA()
    {
        if(i2 <= last)
        {
            chPrev = ch2;
            ch2 = ll8[tPos];
            tPos = tt[tPos];
            if(rNToGo == 0)
            {
                rNToGo = rNums[rTPos];
                rTPos++;
                if(rTPos == 512)
                    rTPos = 0;
            }
            rNToGo--;
            ch2 ^= rNToGo != 1 ? 0 : 1;
            i2++;
            currentChar = ch2;
            currentState = 3;
            mCrc.updateCRC(ch2);
        } else
        {
            endBlock();
            initBlock();
            setupBlock();
        }
    }

    private void setupNoRandPartA()
    {
        if(i2 <= last)
        {
            chPrev = ch2;
            ch2 = ll8[tPos];
            tPos = tt[tPos];
            i2++;
            currentChar = ch2;
            currentState = 6;
            mCrc.updateCRC(ch2);
        } else
        {
            endBlock();
            initBlock();
            setupBlock();
        }
    }

    private void setupRandPartB()
    {
        if(ch2 != chPrev)
        {
            currentState = 2;
            count = 1;
            setupRandPartA();
        } else
        {
            count++;
            if(count >= 4)
            {
                z = ll8[tPos];
                tPos = tt[tPos];
                if(rNToGo == 0)
                {
                    rNToGo = rNums[rTPos];
                    rTPos++;
                    if(rTPos == 512)
                        rTPos = 0;
                }
                rNToGo--;
                z ^= rNToGo != 1 ? '\0' : '\001';
                j2 = 0;
                currentState = 4;
                setupRandPartC();
            } else
            {
                currentState = 2;
                setupRandPartA();
            }
        }
    }

    private void setupRandPartC()
    {
        if(j2 < z)
        {
            currentChar = ch2;
            mCrc.updateCRC(ch2);
            j2++;
        } else
        {
            currentState = 2;
            i2++;
            count = 0;
            setupRandPartA();
        }
    }

    private void setupNoRandPartB()
    {
        if(ch2 != chPrev)
        {
            currentState = 5;
            count = 1;
            setupNoRandPartA();
        } else
        {
            count++;
            if(count >= 4)
            {
                z = ll8[tPos];
                tPos = tt[tPos];
                currentState = 7;
                j2 = 0;
                setupNoRandPartC();
            } else
            {
                currentState = 5;
                setupNoRandPartA();
            }
        }
    }

    private void setupNoRandPartC()
    {
        if(j2 < z)
        {
            currentChar = ch2;
            mCrc.updateCRC(ch2);
            j2++;
        } else
        {
            currentState = 5;
            i2++;
            count = 0;
            setupNoRandPartA();
        }
    }

    private void setDecompressStructureSizes(int newSize100k)
    {
        if(0 <= newSize100k && newSize100k <= 9 && 0 <= blockSize100k)
            if(blockSize100k <= 9);
        blockSize100k = newSize100k;
        if(newSize100k == 0)
        {
            return;
        } else
        {
            int n = 0x186a0 * newSize100k;
            ll8 = new char[n];
            tt = new int[n];
            return;
        }
    }

    private int last;
    private int origPtr;
    private int blockSize100k;
    private boolean blockRandomised;
    private int bsBuff;
    private int bsLive;
    private CRC mCrc;
    private boolean inUse[];
    private int nInUse;
    private char seqToUnseq[];
    private char unseqToSeq[];
    private char selector[];
    private char selectorMtf[];
    private int tt[];
    private char ll8[];
    private int unzftab[];
    private int limit[][];
    private int base[][];
    private int perm[][];
    private int minLens[];
    private InputStream bsStream;
    private boolean streamEnd;
    private int currentChar;
    private static final int START_BLOCK_STATE = 1;
    private static final int RAND_PART_A_STATE = 2;
    private static final int RAND_PART_B_STATE = 3;
    private static final int RAND_PART_C_STATE = 4;
    private static final int NO_RAND_PART_A_STATE = 5;
    private static final int NO_RAND_PART_B_STATE = 6;
    private static final int NO_RAND_PART_C_STATE = 7;
    private int currentState;
    private int storedBlockCRC;
    private int storedCombinedCRC;
    private int computedBlockCRC;
    private int computedCombinedCRC;
    int i2;
    int count;
    int chPrev;
    int ch2;
    int i;
    int tPos;
    int rNToGo;
    int rTPos;
    int j2;
    char z;
}
