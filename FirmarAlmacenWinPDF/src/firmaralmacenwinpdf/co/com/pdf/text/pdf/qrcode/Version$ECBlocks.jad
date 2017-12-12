// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Version.java

package co.com.pdf.text.pdf.qrcode;


// Referenced classes of package co.com.pdf.text.pdf.qrcode:
//            Version

public static final class Version$ECBlocks
{

    public int getECCodewordsPerBlock()
    {
        return ecCodewordsPerBlock;
    }

    public int getNumBlocks()
    {
        int total = 0;
        for(int i = 0; i < ecBlocks.length; i++)
            total += ecBlocks[i].getCount();

        return total;
    }

    public int getTotalECCodewords()
    {
        return ecCodewordsPerBlock * getNumBlocks();
    }

    public Version.ECB[] getECBlocks()
    {
        return ecBlocks;
    }

    private final int ecCodewordsPerBlock;
    private final Version.ECB ecBlocks[];

    Version$ECBlocks(int ecCodewordsPerBlock, Version.ECB ecBlocks)
    {
        this.ecCodewordsPerBlock = ecCodewordsPerBlock;
        this.ecBlocks = (new Version.ECB[] {
            ecBlocks
        });
    }

    Version$ECBlocks(int ecCodewordsPerBlock, Version.ECB ecBlocks1, Version.ECB ecBlocks2)
    {
        this.ecCodewordsPerBlock = ecCodewordsPerBlock;
        ecBlocks = (new Version.ECB[] {
            ecBlocks1, ecBlocks2
        });
    }
}
