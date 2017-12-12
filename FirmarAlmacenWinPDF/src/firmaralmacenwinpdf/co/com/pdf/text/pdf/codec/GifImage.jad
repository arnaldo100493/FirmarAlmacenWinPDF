// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GifImage.java

package co.com.pdf.text.pdf.codec;

import co.com.pdf.text.*;
import co.com.pdf.text.error_messages.MessageLocalization;
import co.com.pdf.text.pdf.*;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;

public class GifImage
{
    static class GifFrame
    {

        Image image;
        int ix;
        int iy;

        GifFrame()
        {
        }
    }


    public GifImage(URL url)
        throws IOException
    {
        InputStream is;
        block = new byte[256];
        blockSize = 0;
        dispose = 0;
        transparency = false;
        delay = 0;
        frames = new ArrayList();
        fromUrl = url;
        is = null;
        is = url.openStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int read = 0;
        byte bytes[] = new byte[1024];
        while((read = is.read(bytes)) != -1) 
            baos.write(bytes, 0, read);
        is = new ByteArrayInputStream(baos.toByteArray());
        baos.flush();
        baos.close();
        process(is);
        if(is != null)
            is.close();
        break MISSING_BLOCK_LABEL_148;
        Exception exception;
        exception;
        if(is != null)
            is.close();
        throw exception;
    }

    public GifImage(String file)
        throws IOException
    {
        this(Utilities.toURL(file));
    }

    public GifImage(byte data[])
        throws IOException
    {
        InputStream is;
        block = new byte[256];
        blockSize = 0;
        dispose = 0;
        transparency = false;
        delay = 0;
        frames = new ArrayList();
        fromData = data;
        is = null;
        is = new ByteArrayInputStream(data);
        process(is);
        if(is != null)
            is.close();
        break MISSING_BLOCK_LABEL_87;
        Exception exception;
        exception;
        if(is != null)
            is.close();
        throw exception;
    }

    public GifImage(InputStream is)
        throws IOException
    {
        block = new byte[256];
        blockSize = 0;
        dispose = 0;
        transparency = false;
        delay = 0;
        frames = new ArrayList();
        process(is);
    }

    public int getFrameCount()
    {
        return frames.size();
    }

    public Image getImage(int frame)
    {
        GifFrame gf = (GifFrame)frames.get(frame - 1);
        return gf.image;
    }

    public int[] getFramePosition(int frame)
    {
        GifFrame gf = (GifFrame)frames.get(frame - 1);
        return (new int[] {
            gf.ix, gf.iy
        });
    }

    public int[] getLogicalScreen()
    {
        return (new int[] {
            width, height
        });
    }

    void process(InputStream is)
        throws IOException
    {
        in = new DataInputStream(new BufferedInputStream(is));
        readHeader();
        readContents();
        if(frames.isEmpty())
            throw new IOException(MessageLocalization.getComposedMessage("the.file.does.not.contain.any.valid.image", new Object[0]));
        else
            return;
    }

    protected void readHeader()
        throws IOException
    {
        StringBuilder id = new StringBuilder("");
        for(int i = 0; i < 6; i++)
            id.append((char)in.read());

        if(!id.toString().startsWith("GIF8"))
            throw new IOException(MessageLocalization.getComposedMessage("gif.signature.nor.found", new Object[0]));
        readLSD();
        if(gctFlag)
            m_global_table = readColorTable(m_gbpc);
    }

    protected void readLSD()
        throws IOException
    {
        width = readShort();
        height = readShort();
        int packed = in.read();
        gctFlag = (packed & 0x80) != 0;
        m_gbpc = (packed & 7) + 1;
        bgIndex = in.read();
        pixelAspect = in.read();
    }

    protected int readShort()
        throws IOException
    {
        return in.read() | in.read() << 8;
    }

    protected int readBlock()
        throws IOException
    {
        blockSize = in.read();
        if(blockSize <= 0)
        {
            return blockSize = 0;
        } else
        {
            blockSize = in.read(block, 0, blockSize);
            return blockSize;
        }
    }

    protected byte[] readColorTable(int bpc)
        throws IOException
    {
        int ncolors = 1 << bpc;
        int nbytes = 3 * ncolors;
        bpc = newBpc(bpc);
        byte table[] = new byte[(1 << bpc) * 3];
        in.readFully(table, 0, nbytes);
        return table;
    }

    protected static int newBpc(int bpc)
    {
        switch(bpc)
        {
        case 3: // '\003'
            return 4;

        default:
            return 8;

        case 1: // '\001'
        case 2: // '\002'
        case 4: // '\004'
            return bpc;
        }
    }

    protected void readContents()
        throws IOException
    {
        boolean done = false;
        do
        {
            if(done)
                break;
            int code = in.read();
            switch(code)
            {
            case 44: // ','
                readImage();
                continue;

            case 33: // '!'
                code = in.read();
                switch(code)
                {
                case 249: 
                    readGraphicControlExt();
                    break;

                case 255: 
                    readBlock();
                    skip();
                    break;

                default:
                    skip();
                    break;
                }
                break;

            default:
                done = true;
                break;
            }
        } while(true);
    }

    protected void readImage()
        throws IOException
    {
        ix = readShort();
        iy = readShort();
        iw = readShort();
        ih = readShort();
        int packed = in.read();
        lctFlag = (packed & 0x80) != 0;
        interlace = (packed & 0x40) != 0;
        lctSize = 2 << (packed & 7);
        m_bpc = newBpc(m_gbpc);
        if(lctFlag)
        {
            m_curr_table = readColorTable((packed & 7) + 1);
            m_bpc = newBpc((packed & 7) + 1);
        } else
        {
            m_curr_table = m_global_table;
        }
        if(transparency && transIndex >= m_curr_table.length / 3)
            transparency = false;
        if(transparency && m_bpc == 1)
        {
            byte tp[] = new byte[12];
            System.arraycopy(m_curr_table, 0, tp, 0, 6);
            m_curr_table = tp;
            m_bpc = 2;
        }
        boolean skipZero = decodeImageData();
        if(!skipZero)
            skip();
        Image img = null;
        try
        {
            img = new ImgRaw(iw, ih, 1, m_bpc, m_out);
            PdfArray colorspace = new PdfArray();
            colorspace.add(PdfName.INDEXED);
            colorspace.add(PdfName.DEVICERGB);
            int len = m_curr_table.length;
            colorspace.add(new PdfNumber(len / 3 - 1));
            colorspace.add(new PdfString(m_curr_table));
            PdfDictionary ad = new PdfDictionary();
            ad.put(PdfName.COLORSPACE, colorspace);
            img.setAdditional(ad);
            if(transparency)
                img.setTransparency(new int[] {
                    transIndex, transIndex
                });
        }
        catch(Exception e)
        {
            throw new ExceptionConverter(e);
        }
        img.setOriginalType(3);
        img.setOriginalData(fromData);
        img.setUrl(fromUrl);
        GifFrame gf = new GifFrame();
        gf.image = img;
        gf.ix = ix;
        gf.iy = iy;
        frames.add(gf);
    }

    protected boolean decodeImageData()
        throws IOException
    {
        int NullCode = -1;
        int npix = iw * ih;
        boolean skipZero = false;
        if(prefix == null)
            prefix = new short[4096];
        if(suffix == null)
            suffix = new byte[4096];
        if(pixelStack == null)
            pixelStack = new byte[4097];
        m_line_stride = (iw * m_bpc + 7) / 8;
        m_out = new byte[m_line_stride * ih];
        int pass = 1;
        int inc = interlace ? 8 : 1;
        int line = 0;
        int xpos = 0;
        int data_size = in.read();
        int clear = 1 << data_size;
        int end_of_information = clear + 1;
        int available = clear + 2;
        int old_code = NullCode;
        int code_size = data_size + 1;
        int code_mask = (1 << code_size) - 1;
        for(int code = 0; code < clear; code++)
        {
            prefix[code] = 0;
            suffix[code] = (byte)code;
        }

        int bits;
        int count;
        int first;
        int top;
        int bi;
        int datum = bits = count = first = top = bi = 0;
        int i = 0;
        do
        {
            if(i >= npix)
                break;
            if(top == 0)
            {
                if(bits < code_size)
                {
                    if(count == 0)
                    {
                        count = readBlock();
                        if(count <= 0)
                        {
                            skipZero = true;
                            break;
                        }
                        bi = 0;
                    }
                    datum += (block[bi] & 0xff) << bits;
                    bits += 8;
                    bi++;
                    count--;
                    continue;
                }
                int code = datum & code_mask;
                datum >>= code_size;
                bits -= code_size;
                if(code > available || code == end_of_information)
                    break;
                if(code == clear)
                {
                    code_size = data_size + 1;
                    code_mask = (1 << code_size) - 1;
                    available = clear + 2;
                    old_code = NullCode;
                    continue;
                }
                if(old_code == NullCode)
                {
                    pixelStack[top++] = suffix[code];
                    old_code = code;
                    first = code;
                    continue;
                }
                int in_code = code;
                if(code == available)
                {
                    pixelStack[top++] = (byte)first;
                    code = old_code;
                }
                for(; code > clear; code = prefix[code])
                    pixelStack[top++] = suffix[code];

                first = suffix[code] & 0xff;
                if(available >= 4096)
                    break;
                pixelStack[top++] = (byte)first;
                prefix[available] = (short)old_code;
                suffix[available] = (byte)first;
                if((++available & code_mask) == 0 && available < 4096)
                {
                    code_size++;
                    code_mask += available;
                }
                old_code = in_code;
            }
            top--;
            i++;
            setPixel(xpos, line, pixelStack[top]);
            if(++xpos >= iw)
            {
                xpos = 0;
                line += inc;
                if(line >= ih)
                    if(interlace)
                    {
                        do
                            switch(++pass)
                            {
                            case 2: // '\002'
                                line = 4;
                                break;

                            case 3: // '\003'
                                line = 2;
                                inc = 4;
                                break;

                            case 4: // '\004'
                                line = 1;
                                inc = 2;
                                break;

                            default:
                                line = ih - 1;
                                inc = 0;
                                break;
                            }
                        while(line >= ih);
                    } else
                    {
                        line = ih - 1;
                        inc = 0;
                    }
            }
        } while(true);
        return skipZero;
    }

    protected void setPixel(int x, int y, int v)
    {
        if(m_bpc == 8)
        {
            int pos = x + iw * y;
            m_out[pos] = (byte)v;
        } else
        {
            int pos = m_line_stride * y + x / (8 / m_bpc);
            int vout = v << 8 - m_bpc * (x % (8 / m_bpc)) - m_bpc;
            m_out[pos] |= vout;
        }
    }

    protected void resetFrame()
    {
    }

    protected void readGraphicControlExt()
        throws IOException
    {
        in.read();
        int packed = in.read();
        dispose = (packed & 0x1c) >> 2;
        if(dispose == 0)
            dispose = 1;
        transparency = (packed & 1) != 0;
        delay = readShort() * 10;
        transIndex = in.read();
        in.read();
    }

    protected void skip()
        throws IOException
    {
        do
            readBlock();
        while(blockSize > 0);
    }

    protected DataInputStream in;
    protected int width;
    protected int height;
    protected boolean gctFlag;
    protected int bgIndex;
    protected int bgColor;
    protected int pixelAspect;
    protected boolean lctFlag;
    protected boolean interlace;
    protected int lctSize;
    protected int ix;
    protected int iy;
    protected int iw;
    protected int ih;
    protected byte block[];
    protected int blockSize;
    protected int dispose;
    protected boolean transparency;
    protected int delay;
    protected int transIndex;
    protected static final int MaxStackSize = 4096;
    protected short prefix[];
    protected byte suffix[];
    protected byte pixelStack[];
    protected byte pixels[];
    protected byte m_out[];
    protected int m_bpc;
    protected int m_gbpc;
    protected byte m_global_table[];
    protected byte m_local_table[];
    protected byte m_curr_table[];
    protected int m_line_stride;
    protected byte fromData[];
    protected URL fromUrl;
    protected ArrayList frames;
}
