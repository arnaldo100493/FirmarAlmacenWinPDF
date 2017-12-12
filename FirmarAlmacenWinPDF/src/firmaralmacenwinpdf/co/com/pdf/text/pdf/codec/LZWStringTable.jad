// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LZWStringTable.java

package co.com.pdf.text.pdf.codec;

import java.io.PrintStream;

public class LZWStringTable
{

    public LZWStringTable()
    {
        strChr_ = new byte[4096];
        strNxt_ = new short[4096];
        strLen_ = new int[4096];
        strHsh_ = new short[9973];
    }

    public int AddCharString(short index, byte b)
    {
        if(numStrings_ >= 4096)
            return 65535;
        int hshidx;
        for(hshidx = Hash(index, b); strHsh_[hshidx] != -1; hshidx = (hshidx + 2039) % 9973);
        strHsh_[hshidx] = numStrings_;
        strChr_[numStrings_] = b;
        if(index == -1)
        {
            strNxt_[numStrings_] = -1;
            strLen_[numStrings_] = 1;
        } else
        {
            strNxt_[numStrings_] = index;
            strLen_[numStrings_] = strLen_[index] + 1;
        }
        return numStrings_++;
    }

    public short FindCharString(short index, byte b)
    {
        if(index == -1)
            return (short)(b & 0xff);
        int nxtidx;
        for(int hshidx = Hash(index, b); (nxtidx = strHsh_[hshidx]) != -1; hshidx = (hshidx + 2039) % 9973)
            if(strNxt_[nxtidx] == index && strChr_[nxtidx] == b)
                return (short)nxtidx;

        return -1;
    }

    public void ClearTable(int codesize)
    {
        numStrings_ = 0;
        for(int q = 0; q < 9973; q++)
            strHsh_[q] = -1;

        int w = (1 << codesize) + 2;
        for(int q = 0; q < w; q++)
            AddCharString((short)-1, (byte)q);

    }

    public static int Hash(short index, byte lastbyte)
    {
        return (((short)(lastbyte << 8) ^ index) & 0xffff) % 9973;
    }

    public int expandCode(byte buf[], int offset, short code, int skipHead)
    {
        if(offset == -2 && skipHead == 1)
            skipHead = 0;
        if(code == -1 || skipHead == strLen_[code])
            return 0;
        int codeLen = strLen_[code] - skipHead;
        int bufSpace = buf.length - offset;
        int expandLen;
        if(bufSpace > codeLen)
            expandLen = codeLen;
        else
            expandLen = bufSpace;
        int skipTail = codeLen - expandLen;
        for(int idx = offset + expandLen; idx > offset && code != -1; code = strNxt_[code])
            if(--skipTail < 0)
                buf[--idx] = strChr_[code];

        if(codeLen > expandLen)
            return -expandLen;
        else
            return expandLen;
    }

    public void dump(PrintStream out)
    {
        for(int i = 258; i < numStrings_; i++)
            out.println((new StringBuilder()).append(" strNxt_[").append(i).append("] = ").append(strNxt_[i]).append(" strChr_ ").append(Integer.toHexString(strChr_[i] & 0xff)).append(" strLen_ ").append(Integer.toHexString(strLen_[i])).toString());

    }

    private static final int RES_CODES = 2;
    private static final short HASH_FREE = -1;
    private static final short NEXT_FIRST = -1;
    private static final int MAXBITS = 12;
    private static final int MAXSTR = 4096;
    private static final short HASHSIZE = 9973;
    private static final short HASHSTEP = 2039;
    byte strChr_[];
    short strNxt_[];
    short strHsh_[];
    short numStrings_;
    int strLen_[];
}
