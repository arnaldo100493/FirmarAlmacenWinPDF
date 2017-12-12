// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BitFile.java

package co.com.pdf.text.pdf.codec;

import java.io.IOException;
import java.io.OutputStream;

public class BitFile
{

    public BitFile(OutputStream output, boolean blocks)
    {
        blocks_ = false;
        output_ = output;
        blocks_ = blocks;
        buffer_ = new byte[256];
        index_ = 0;
        bitsLeft_ = 8;
    }

    public void flush()
        throws IOException
    {
        int numBytes = index_ + (bitsLeft_ != 8 ? 1 : 0);
        if(numBytes > 0)
        {
            if(blocks_)
                output_.write(numBytes);
            output_.write(buffer_, 0, numBytes);
            buffer_[0] = 0;
            index_ = 0;
            bitsLeft_ = 8;
        }
    }

    public void writeBits(int bits, int numbits)
        throws IOException
    {
        int bitsWritten = 0;
        int numBytes = 255;
        do
        {
            if(index_ == 254 && bitsLeft_ == 0 || index_ > 254)
            {
                if(blocks_)
                    output_.write(numBytes);
                output_.write(buffer_, 0, numBytes);
                buffer_[0] = 0;
                index_ = 0;
                bitsLeft_ = 8;
            }
            if(numbits <= bitsLeft_)
            {
                if(blocks_)
                {
                    buffer_[index_] |= (bits & (1 << numbits) - 1) << 8 - bitsLeft_;
                    bitsWritten += numbits;
                    bitsLeft_ -= numbits;
                    numbits = 0;
                } else
                {
                    buffer_[index_] |= (bits & (1 << numbits) - 1) << bitsLeft_ - numbits;
                    bitsWritten += numbits;
                    bitsLeft_ -= numbits;
                    numbits = 0;
                }
            } else
            if(blocks_)
            {
                buffer_[index_] |= (bits & (1 << bitsLeft_) - 1) << 8 - bitsLeft_;
                bitsWritten += bitsLeft_;
                bits >>= bitsLeft_;
                numbits -= bitsLeft_;
                buffer_[++index_] = 0;
                bitsLeft_ = 8;
            } else
            {
                int topbits = bits >>> numbits - bitsLeft_ & (1 << bitsLeft_) - 1;
                buffer_[index_] |= topbits;
                numbits -= bitsLeft_;
                bitsWritten += bitsLeft_;
                buffer_[++index_] = 0;
                bitsLeft_ = 8;
            }
        } while(numbits != 0);
    }

    OutputStream output_;
    byte buffer_[];
    int index_;
    int bitsLeft_;
    boolean blocks_;
}
