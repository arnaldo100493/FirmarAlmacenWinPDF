// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BarcodeDatamatrix.java

package co.com.pdf.text.pdf;

import java.util.Arrays;
import java.util.Hashtable;

// Referenced classes of package co.com.pdf.text.pdf:
//            BarcodeDatamatrix

static class BarcodeDatamatrix$Placement
{

    static short[] doPlacement(int nrow, int ncol)
    {
        Integer key = Integer.valueOf(nrow * 1000 + ncol);
        short pc[] = (short[])cache.get(key);
        if(pc != null)
        {
            return pc;
        } else
        {
            BarcodeDatamatrix$Placement p = new BarcodeDatamatrix$Placement();
            p.nrow = nrow;
            p.ncol = ncol;
            p.array = new short[nrow * ncol];
            p.ecc200();
            cache.put(key, p.array);
            return p.array;
        }
    }

    private void module(int row, int col, int chr, int bit)
    {
        if(row < 0)
        {
            row += nrow;
            col += 4 - (nrow + 4) % 8;
        }
        if(col < 0)
        {
            col += ncol;
            row += 4 - (ncol + 4) % 8;
        }
        array[row * ncol + col] = (short)(8 * chr + bit);
    }

    private void utah(int row, int col, int chr)
    {
        module(row - 2, col - 2, chr, 0);
        module(row - 2, col - 1, chr, 1);
        module(row - 1, col - 2, chr, 2);
        module(row - 1, col - 1, chr, 3);
        module(row - 1, col, chr, 4);
        module(row, col - 2, chr, 5);
        module(row, col - 1, chr, 6);
        module(row, col, chr, 7);
    }

    private void corner1(int chr)
    {
        module(nrow - 1, 0, chr, 0);
        module(nrow - 1, 1, chr, 1);
        module(nrow - 1, 2, chr, 2);
        module(0, ncol - 2, chr, 3);
        module(0, ncol - 1, chr, 4);
        module(1, ncol - 1, chr, 5);
        module(2, ncol - 1, chr, 6);
        module(3, ncol - 1, chr, 7);
    }

    private void corner2(int chr)
    {
        module(nrow - 3, 0, chr, 0);
        module(nrow - 2, 0, chr, 1);
        module(nrow - 1, 0, chr, 2);
        module(0, ncol - 4, chr, 3);
        module(0, ncol - 3, chr, 4);
        module(0, ncol - 2, chr, 5);
        module(0, ncol - 1, chr, 6);
        module(1, ncol - 1, chr, 7);
    }

    private void corner3(int chr)
    {
        module(nrow - 3, 0, chr, 0);
        module(nrow - 2, 0, chr, 1);
        module(nrow - 1, 0, chr, 2);
        module(0, ncol - 2, chr, 3);
        module(0, ncol - 1, chr, 4);
        module(1, ncol - 1, chr, 5);
        module(2, ncol - 1, chr, 6);
        module(3, ncol - 1, chr, 7);
    }

    private void corner4(int chr)
    {
        module(nrow - 1, 0, chr, 0);
        module(nrow - 1, ncol - 1, chr, 1);
        module(0, ncol - 3, chr, 2);
        module(0, ncol - 2, chr, 3);
        module(0, ncol - 1, chr, 4);
        module(1, ncol - 3, chr, 5);
        module(1, ncol - 2, chr, 6);
        module(1, ncol - 1, chr, 7);
    }

    private void ecc200()
    {
        Arrays.fill(array, (short)0);
        int chr = 1;
        int row = 4;
        int col = 0;
        do
        {
            if(row == nrow && col == 0)
                corner1(chr++);
            if(row == nrow - 2 && col == 0 && ncol % 4 != 0)
                corner2(chr++);
            if(row == nrow - 2 && col == 0 && ncol % 8 == 4)
                corner3(chr++);
            if(row == nrow + 4 && col == 2 && ncol % 8 == 0)
                corner4(chr++);
            do
            {
                if(row < nrow && col >= 0 && array[row * ncol + col] == 0)
                    utah(row, col, chr++);
                row -= 2;
                col += 2;
            } while(row >= 0 && col < ncol);
            row++;
            col += 3;
            do
            {
                if(row >= 0 && col < ncol && array[row * ncol + col] == 0)
                    utah(row, col, chr++);
                row += 2;
                col -= 2;
            } while(row < nrow && col >= 0);
            row += 3;
            col++;
        } while(row < nrow || col < ncol);
        if(array[nrow * ncol - 1] == 0)
            array[nrow * ncol - 1] = array[nrow * ncol - ncol - 2] = 1;
    }

    private int nrow;
    private int ncol;
    private short array[];
    private static final Hashtable cache = new Hashtable();


    private BarcodeDatamatrix$Placement()
    {
    }
}
