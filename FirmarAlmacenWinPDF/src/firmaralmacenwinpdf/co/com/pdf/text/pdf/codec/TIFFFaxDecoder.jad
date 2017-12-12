// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TIFFFaxDecoder.java

package co.com.pdf.text.pdf.codec;

import co.com.pdf.text.error_messages.MessageLocalization;
import co.com.pdf.text.exceptions.InvalidImageException;

public class TIFFFaxDecoder
{

    public TIFFFaxDecoder(int fillOrder, int w, int h)
    {
        changingElemSize = 0;
        lastChangingElement = 0;
        compression = 2;
        uncompressedMode = 0;
        fillBits = 0;
        this.fillOrder = fillOrder;
        this.w = w;
        this.h = h;
        bitPointer = 0;
        bytePointer = 0;
        prevChangingElems = new int[2 * w];
        currChangingElems = new int[2 * w];
    }

    public static void reverseBits(byte b[])
    {
        for(int k = 0; k < b.length; k++)
            b[k] = flipTable[b[k] & 0xff];

    }

    public void decode1D(byte buffer[], byte compData[], int startX, int height)
    {
        data = compData;
        int lineOffset = 0;
        int scanlineStride = (w + 7) / 8;
        bitPointer = 0;
        bytePointer = 0;
        for(int i = 0; i < height; i++)
        {
            decodeNextScanline(buffer, lineOffset, startX);
            lineOffset += scanlineStride;
        }

    }

    public void decodeNextScanline(byte buffer[], int lineOffset, int bitOffset)
    {
        int bits = 0;
        int code = 0;
        int isT = 0;
        boolean isWhite = true;
        changingElemSize = 0;
        do
        {
            if(bitOffset >= w)
                break;
            do
            {
                if(!isWhite)
                    break;
                int current = nextNBits(10);
                int entry = white[current];
                isT = entry & 1;
                bits = entry >>> 1 & 0xf;
                if(bits == 12)
                {
                    int twoBits = nextLesserThan8Bits(2);
                    current = current << 2 & 0xc | twoBits;
                    entry = additionalMakeup[current];
                    bits = entry >>> 1 & 7;
                    code = entry >>> 4 & 0xfff;
                    bitOffset += code;
                    updatePointer(4 - bits);
                } else
                {
                    if(bits == 0)
                        throw new RuntimeException(MessageLocalization.getComposedMessage("invalid.code.encountered", new Object[0]));
                    if(bits == 15)
                        throw new RuntimeException(MessageLocalization.getComposedMessage("eol.code.word.encountered.in.white.run", new Object[0]));
                    code = entry >>> 5 & 0x7ff;
                    bitOffset += code;
                    updatePointer(10 - bits);
                    if(isT == 0)
                    {
                        isWhite = false;
                        currChangingElems[changingElemSize++] = bitOffset;
                    }
                }
            } while(true);
            if(bitOffset == w)
            {
                if(compression == 2)
                    advancePointer();
                break;
            }
            do
            {
                if(isWhite)
                    break;
                int current = nextLesserThan8Bits(4);
                int entry = initBlack[current];
                isT = entry & 1;
                bits = entry >>> 1 & 0xf;
                code = entry >>> 5 & 0x7ff;
                if(code == 100)
                {
                    current = nextNBits(9);
                    entry = black[current];
                    isT = entry & 1;
                    bits = entry >>> 1 & 0xf;
                    code = entry >>> 5 & 0x7ff;
                    if(bits == 12)
                    {
                        updatePointer(5);
                        current = nextLesserThan8Bits(4);
                        entry = additionalMakeup[current];
                        bits = entry >>> 1 & 7;
                        code = entry >>> 4 & 0xfff;
                        setToBlack(buffer, lineOffset, bitOffset, code);
                        bitOffset += code;
                        updatePointer(4 - bits);
                    } else
                    {
                        if(bits == 15)
                            throw new RuntimeException(MessageLocalization.getComposedMessage("eol.code.word.encountered.in.black.run", new Object[0]));
                        setToBlack(buffer, lineOffset, bitOffset, code);
                        bitOffset += code;
                        updatePointer(9 - bits);
                        if(isT == 0)
                        {
                            isWhite = true;
                            currChangingElems[changingElemSize++] = bitOffset;
                        }
                    }
                } else
                if(code == 200)
                {
                    current = nextLesserThan8Bits(2);
                    entry = twoBitBlack[current];
                    code = entry >>> 5 & 0x7ff;
                    bits = entry >>> 1 & 0xf;
                    setToBlack(buffer, lineOffset, bitOffset, code);
                    bitOffset += code;
                    updatePointer(2 - bits);
                    isWhite = true;
                    currChangingElems[changingElemSize++] = bitOffset;
                } else
                {
                    setToBlack(buffer, lineOffset, bitOffset, code);
                    bitOffset += code;
                    updatePointer(4 - bits);
                    isWhite = true;
                    currChangingElems[changingElemSize++] = bitOffset;
                }
            } while(true);
            if(bitOffset != w)
                continue;
            if(compression == 2)
                advancePointer();
            break;
        } while(true);
        currChangingElems[changingElemSize++] = bitOffset;
    }

    public void decode2D(byte buffer[], byte compData[], int startX, int height, long tiffT4Options)
    {
        data = compData;
        compression = 3;
        bitPointer = 0;
        bytePointer = 0;
        int scanlineStride = (w + 7) / 8;
        int b[] = new int[2];
        int currIndex = 0;
        oneD = (int)(tiffT4Options & 1L);
        uncompressedMode = (int)((tiffT4Options & 2L) >> 1);
        fillBits = (int)((tiffT4Options & 4L) >> 2);
        if(readEOL(true) != 1)
            throw new RuntimeException(MessageLocalization.getComposedMessage("first.scanline.must.be.1d.encoded", new Object[0]));
        int lineOffset = 0;
        decodeNextScanline(buffer, lineOffset, startX);
        lineOffset += scanlineStride;
        for(int lines = 1; lines < height; lines++)
        {
            if(readEOL(false) == 0)
            {
                int temp[] = prevChangingElems;
                prevChangingElems = currChangingElems;
                currChangingElems = temp;
                currIndex = 0;
                int a0 = -1;
                boolean isWhite = true;
                int bitOffset = startX;
                lastChangingElement = 0;
                while(bitOffset < w) 
                {
                    getNextChangingElement(a0, isWhite, b);
                    int b1 = b[0];
                    int b2 = b[1];
                    int entry = nextLesserThan8Bits(7);
                    entry = twoDCodes[entry] & 0xff;
                    int code = (entry & 0x78) >>> 3;
                    int bits = entry & 7;
                    if(code == 0)
                    {
                        if(!isWhite)
                            setToBlack(buffer, lineOffset, bitOffset, b2 - bitOffset);
                        bitOffset = a0 = b2;
                        updatePointer(7 - bits);
                    } else
                    if(code == 1)
                    {
                        updatePointer(7 - bits);
                        if(isWhite)
                        {
                            int number = decodeWhiteCodeWord();
                            bitOffset += number;
                            currChangingElems[currIndex++] = bitOffset;
                            number = decodeBlackCodeWord();
                            setToBlack(buffer, lineOffset, bitOffset, number);
                            bitOffset += number;
                            currChangingElems[currIndex++] = bitOffset;
                        } else
                        {
                            int number = decodeBlackCodeWord();
                            setToBlack(buffer, lineOffset, bitOffset, number);
                            bitOffset += number;
                            currChangingElems[currIndex++] = bitOffset;
                            number = decodeWhiteCodeWord();
                            bitOffset += number;
                            currChangingElems[currIndex++] = bitOffset;
                        }
                        a0 = bitOffset;
                    } else
                    if(code <= 8)
                    {
                        int a1 = b1 + (code - 5);
                        currChangingElems[currIndex++] = a1;
                        if(!isWhite)
                            setToBlack(buffer, lineOffset, bitOffset, a1 - bitOffset);
                        bitOffset = a0 = a1;
                        isWhite = !isWhite;
                        updatePointer(7 - bits);
                    } else
                    {
                        throw new RuntimeException(MessageLocalization.getComposedMessage("invalid.code.encountered.while.decoding.2d.group.3.compressed.data", new Object[0]));
                    }
                }
                currChangingElems[currIndex++] = bitOffset;
                changingElemSize = currIndex;
            } else
            {
                decodeNextScanline(buffer, lineOffset, startX);
            }
            lineOffset += scanlineStride;
        }

    }

    public void decodeT6(byte buffer[], byte compData[], int startX, int height, long tiffT6Options)
    {
        data = compData;
        compression = 4;
        bitPointer = 0;
        bytePointer = 0;
        int scanlineStride = (w + 7) / 8;
        int b[] = new int[2];
        uncompressedMode = (int)((tiffT6Options & 2L) >> 1);
        int cce[] = currChangingElems;
        changingElemSize = 0;
        cce[changingElemSize++] = w;
        cce[changingElemSize++] = w;
        int lineOffset = 0;
        for(int lines = 0; lines < height; lines++)
        {
            int a0 = -1;
            boolean isWhite = true;
            int temp[] = prevChangingElems;
            prevChangingElems = currChangingElems;
            cce = currChangingElems = temp;
            int currIndex = 0;
            int bitOffset = startX;
            lastChangingElement = 0;
            while(bitOffset < w) 
            {
                getNextChangingElement(a0, isWhite, b);
                int b1 = b[0];
                int b2 = b[1];
                int entry = nextLesserThan8Bits(7);
                entry = twoDCodes[entry] & 0xff;
                int code = (entry & 0x78) >>> 3;
                int bits = entry & 7;
                if(code == 0)
                {
                    if(!isWhite)
                        setToBlack(buffer, lineOffset, bitOffset, b2 - bitOffset);
                    bitOffset = a0 = b2;
                    updatePointer(7 - bits);
                } else
                if(code == 1)
                {
                    updatePointer(7 - bits);
                    if(isWhite)
                    {
                        int number = decodeWhiteCodeWord();
                        bitOffset += number;
                        cce[currIndex++] = bitOffset;
                        number = decodeBlackCodeWord();
                        setToBlack(buffer, lineOffset, bitOffset, number);
                        bitOffset += number;
                        cce[currIndex++] = bitOffset;
                    } else
                    {
                        int number = decodeBlackCodeWord();
                        setToBlack(buffer, lineOffset, bitOffset, number);
                        bitOffset += number;
                        cce[currIndex++] = bitOffset;
                        number = decodeWhiteCodeWord();
                        bitOffset += number;
                        cce[currIndex++] = bitOffset;
                    }
                    a0 = bitOffset;
                } else
                if(code <= 8)
                {
                    int a1 = b1 + (code - 5);
                    cce[currIndex++] = a1;
                    if(!isWhite)
                        setToBlack(buffer, lineOffset, bitOffset, a1 - bitOffset);
                    bitOffset = a0 = a1;
                    isWhite = !isWhite;
                    updatePointer(7 - bits);
                } else
                if(code == 11)
                {
                    if(nextLesserThan8Bits(3) != 7)
                        throw new InvalidImageException(MessageLocalization.getComposedMessage("invalid.code.encountered.while.decoding.2d.group.4.compressed.data", new Object[0]));
                    int zeros = 0;
                    boolean exit = false;
                    while(!exit) 
                    {
                        while(nextLesserThan8Bits(1) != 1) 
                            zeros++;
                        if(zeros > 5)
                        {
                            zeros -= 6;
                            if(!isWhite && zeros > 0)
                                cce[currIndex++] = bitOffset;
                            bitOffset += zeros;
                            if(zeros > 0)
                                isWhite = true;
                            if(nextLesserThan8Bits(1) == 0)
                            {
                                if(!isWhite)
                                    cce[currIndex++] = bitOffset;
                                isWhite = true;
                            } else
                            {
                                if(isWhite)
                                    cce[currIndex++] = bitOffset;
                                isWhite = false;
                            }
                            exit = true;
                        }
                        if(zeros == 5)
                        {
                            if(!isWhite)
                                cce[currIndex++] = bitOffset;
                            bitOffset += zeros;
                            isWhite = true;
                        } else
                        {
                            bitOffset += zeros;
                            cce[currIndex++] = bitOffset;
                            setToBlack(buffer, lineOffset, bitOffset, 1);
                            bitOffset++;
                            isWhite = false;
                        }
                    }
                } else
                {
                    bitOffset = w;
                    updatePointer(7 - bits);
                }
            }
            if(currIndex < cce.length)
                cce[currIndex++] = bitOffset;
            changingElemSize = currIndex;
            lineOffset += scanlineStride;
        }

    }

    private void setToBlack(byte buffer[], int lineOffset, int bitOffset, int numBits)
    {
        int bitNum = 8 * lineOffset + bitOffset;
        int lastBit = bitNum + numBits;
        int byteNum = bitNum >> 3;
        int shift = bitNum & 7;
        if(shift > 0)
        {
            int maskVal = 1 << 7 - shift;
            byte val = buffer[byteNum];
            for(; maskVal > 0 && bitNum < lastBit; bitNum++)
            {
                val |= maskVal;
                maskVal >>= 1;
            }

            buffer[byteNum] = val;
        }
        byteNum = bitNum >> 3;
        for(; bitNum < lastBit - 7; bitNum += 8)
            buffer[byteNum++] = -1;

        for(; bitNum < lastBit; bitNum++)
        {
            byteNum = bitNum >> 3;
            if(!handleIncorrectImage || byteNum < buffer.length)
                buffer[byteNum] |= 1 << 7 - (bitNum & 7);
        }

    }

    private int decodeWhiteCodeWord()
    {
        int code = -1;
        int runLength = 0;
        boolean isWhite = true;
        do
        {
            if(!isWhite)
                break;
            int current = nextNBits(10);
            int entry = white[current];
            int isT = entry & 1;
            int bits = entry >>> 1 & 0xf;
            if(bits == 12)
            {
                int twoBits = nextLesserThan8Bits(2);
                current = current << 2 & 0xc | twoBits;
                entry = additionalMakeup[current];
                bits = entry >>> 1 & 7;
                code = entry >>> 4 & 0xfff;
                runLength += code;
                updatePointer(4 - bits);
            } else
            {
                if(bits == 0)
                    throw new InvalidImageException(MessageLocalization.getComposedMessage("invalid.code.encountered", new Object[0]));
                if(bits == 15)
                    throw new RuntimeException(MessageLocalization.getComposedMessage("eol.code.word.encountered.in.white.run", new Object[0]));
                code = entry >>> 5 & 0x7ff;
                runLength += code;
                updatePointer(10 - bits);
                if(isT == 0)
                    isWhite = false;
            }
        } while(true);
        return runLength;
    }

    private int decodeBlackCodeWord()
    {
        int code = -1;
        int runLength = 0;
        boolean isWhite = false;
        do
        {
            if(isWhite)
                break;
            int current = nextLesserThan8Bits(4);
            int entry = initBlack[current];
            int isT = entry & 1;
            int bits = entry >>> 1 & 0xf;
            code = entry >>> 5 & 0x7ff;
            if(code == 100)
            {
                current = nextNBits(9);
                entry = black[current];
                isT = entry & 1;
                bits = entry >>> 1 & 0xf;
                code = entry >>> 5 & 0x7ff;
                if(bits == 12)
                {
                    updatePointer(5);
                    current = nextLesserThan8Bits(4);
                    entry = additionalMakeup[current];
                    bits = entry >>> 1 & 7;
                    code = entry >>> 4 & 0xfff;
                    runLength += code;
                    updatePointer(4 - bits);
                } else
                {
                    if(bits == 15)
                        throw new RuntimeException(MessageLocalization.getComposedMessage("eol.code.word.encountered.in.black.run", new Object[0]));
                    runLength += code;
                    updatePointer(9 - bits);
                    if(isT == 0)
                        isWhite = true;
                }
            } else
            if(code == 200)
            {
                current = nextLesserThan8Bits(2);
                entry = twoBitBlack[current];
                code = entry >>> 5 & 0x7ff;
                runLength += code;
                bits = entry >>> 1 & 0xf;
                updatePointer(2 - bits);
                isWhite = true;
            } else
            {
                runLength += code;
                updatePointer(4 - bits);
                isWhite = true;
            }
        } while(true);
        return runLength;
    }

    private int readEOL(boolean isFirstEOL)
    {
label0:
        {
            if(fillBits == 0)
            {
                int next12Bits = nextNBits(12);
                if(isFirstEOL && next12Bits == 0 && nextNBits(4) == 1)
                {
                    fillBits = 1;
                    return 1;
                }
                if(next12Bits != 1)
                    throw new RuntimeException(MessageLocalization.getComposedMessage("scanline.must.begin.with.eol.code.word", new Object[0]));
                break label0;
            }
            if(fillBits != 1)
                break label0;
            int bitsLeft = 8 - bitPointer;
            if(nextNBits(bitsLeft) != 0)
                throw new RuntimeException(MessageLocalization.getComposedMessage("all.fill.bits.preceding.eol.code.must.be.0", new Object[0]));
            if(bitsLeft < 4 && nextNBits(8) != 0)
                throw new RuntimeException(MessageLocalization.getComposedMessage("all.fill.bits.preceding.eol.code.must.be.0", new Object[0]));
            int n;
            do
                if((n = nextNBits(8)) == 1)
                    break label0;
            while(n == 0);
            throw new RuntimeException(MessageLocalization.getComposedMessage("all.fill.bits.preceding.eol.code.must.be.0", new Object[0]));
        }
        if(oneD == 0)
            return 1;
        else
            return nextLesserThan8Bits(1);
    }

    private void getNextChangingElement(int a0, boolean isWhite, int ret[])
    {
        int pce[] = prevChangingElems;
        int ces = changingElemSize;
        int start = lastChangingElement <= 0 ? 0 : lastChangingElement - 1;
        if(isWhite)
            start &= -2;
        else
            start |= 1;
        int i = start;
        do
        {
            if(i >= ces)
                break;
            int temp = pce[i];
            if(temp > a0)
            {
                lastChangingElement = i;
                ret[0] = temp;
                break;
            }
            i += 2;
        } while(true);
        if(i + 1 < ces)
            ret[1] = pce[i + 1];
    }

    private int nextNBits(int bitsToGet)
    {
        int l = data.length - 1;
        int bp = bytePointer;
        byte b;
        byte next;
        byte next2next;
        if(fillOrder == 1)
        {
            b = data[bp];
            if(bp == l)
            {
                next = 0;
                next2next = 0;
            } else
            if(bp + 1 == l)
            {
                next = data[bp + 1];
                next2next = 0;
            } else
            {
                next = data[bp + 1];
                next2next = data[bp + 2];
            }
        } else
        if(fillOrder == 2)
        {
            b = flipTable[data[bp] & 0xff];
            if(bp == l)
            {
                next = 0;
                next2next = 0;
            } else
            if(bp + 1 == l)
            {
                next = flipTable[data[bp + 1] & 0xff];
                next2next = 0;
            } else
            {
                next = flipTable[data[bp + 1] & 0xff];
                next2next = flipTable[data[bp + 2] & 0xff];
            }
        } else
        {
            throw new RuntimeException(MessageLocalization.getComposedMessage("tiff.fill.order.tag.must.be.either.1.or.2", new Object[0]));
        }
        int bitsLeft = 8 - bitPointer;
        int bitsFromNextByte = bitsToGet - bitsLeft;
        int bitsFromNext2NextByte = 0;
        if(bitsFromNextByte > 8)
        {
            bitsFromNext2NextByte = bitsFromNextByte - 8;
            bitsFromNextByte = 8;
        }
        bytePointer++;
        int i1 = (b & table1[bitsLeft]) << bitsToGet - bitsLeft;
        int i2 = (next & table2[bitsFromNextByte]) >>> 8 - bitsFromNextByte;
        int i3 = 0;
        if(bitsFromNext2NextByte != 0)
        {
            i2 <<= bitsFromNext2NextByte;
            i3 = (next2next & table2[bitsFromNext2NextByte]) >>> 8 - bitsFromNext2NextByte;
            i2 |= i3;
            bytePointer++;
            bitPointer = bitsFromNext2NextByte;
        } else
        if(bitsFromNextByte == 8)
        {
            bitPointer = 0;
            bytePointer++;
        } else
        {
            bitPointer = bitsFromNextByte;
        }
        int i = i1 | i2;
        return i;
    }

    private int nextLesserThan8Bits(int bitsToGet)
    {
        byte b = 0;
        byte next = 0;
        int l = data.length - 1;
        int bp = bytePointer;
        if(fillOrder == 1)
        {
            b = data[bp];
            if(bp == l)
                next = 0;
            else
                next = data[bp + 1];
        } else
        if(fillOrder == 2)
        {
            if(!handleIncorrectImage || bp < data.length)
            {
                b = flipTable[data[bp] & 0xff];
                if(bp == l)
                    next = 0;
                else
                    next = flipTable[data[bp + 1] & 0xff];
            }
        } else
        {
            throw new RuntimeException(MessageLocalization.getComposedMessage("tiff.fill.order.tag.must.be.either.1.or.2", new Object[0]));
        }
        int bitsLeft = 8 - bitPointer;
        int bitsFromNextByte = bitsToGet - bitsLeft;
        int shift = bitsLeft - bitsToGet;
        int i1;
        if(shift >= 0)
        {
            i1 = (b & table1[bitsLeft]) >>> shift;
            bitPointer += bitsToGet;
            if(bitPointer == 8)
            {
                bitPointer = 0;
                bytePointer++;
            }
        } else
        {
            i1 = (b & table1[bitsLeft]) << -shift;
            int i2 = (next & table2[bitsFromNextByte]) >>> 8 - bitsFromNextByte;
            i1 |= i2;
            bytePointer++;
            bitPointer = bitsFromNextByte;
        }
        return i1;
    }

    private void updatePointer(int bitsToMoveBack)
    {
        int i = bitPointer - bitsToMoveBack;
        if(i < 0)
        {
            bytePointer--;
            bitPointer = 8 + i;
        } else
        {
            bitPointer = i;
        }
    }

    private boolean advancePointer()
    {
        if(bitPointer != 0)
        {
            bytePointer++;
            bitPointer = 0;
        }
        return true;
    }

    public void setHandleIncorrectImage(boolean handleIncorrectImage)
    {
        this.handleIncorrectImage = handleIncorrectImage;
    }

    private int bitPointer;
    private int bytePointer;
    private byte data[];
    private int w;
    private int h;
    private int fillOrder;
    private int changingElemSize;
    private int prevChangingElems[];
    private int currChangingElems[];
    private int lastChangingElement;
    private int compression;
    private int uncompressedMode;
    private int fillBits;
    private int oneD;
    private boolean handleIncorrectImage;
    static int table1[] = {
        0, 1, 3, 7, 15, 31, 63, 127, 255
    };
    static int table2[] = {
        0, 128, 192, 224, 240, 248, 252, 254, 255
    };
    static byte flipTable[] = {
        0, -128, 64, -64, 32, -96, 96, -32, 16, -112, 
        80, -48, 48, -80, 112, -16, 8, -120, 72, -56, 
        40, -88, 104, -24, 24, -104, 88, -40, 56, -72, 
        120, -8, 4, -124, 68, -60, 36, -92, 100, -28, 
        20, -108, 84, -44, 52, -76, 116, -12, 12, -116, 
        76, -52, 44, -84, 108, -20, 28, -100, 92, -36, 
        60, -68, 124, -4, 2, -126, 66, -62, 34, -94, 
        98, -30, 18, -110, 82, -46, 50, -78, 114, -14, 
        10, -118, 74, -54, 42, -86, 106, -22, 26, -102, 
        90, -38, 58, -70, 122, -6, 6, -122, 70, -58, 
        38, -90, 102, -26, 22, -106, 86, -42, 54, -74, 
        118, -10, 14, -114, 78, -50, 46, -82, 110, -18, 
        30, -98, 94, -34, 62, -66, 126, -2, 1, -127, 
        65, -63, 33, -95, 97, -31, 17, -111, 81, -47, 
        49, -79, 113, -15, 9, -119, 73, -55, 41, -87, 
        105, -23, 25, -103, 89, -39, 57, -71, 121, -7, 
        5, -123, 69, -59, 37, -91, 101, -27, 21, -107, 
        85, -43, 53, -75, 117, -11, 13, -115, 77, -51, 
        45, -83, 109, -19, 29, -99, 93, -35, 61, -67, 
        125, -3, 3, -125, 67, -61, 35, -93, 99, -29, 
        19, -109, 83, -45, 51, -77, 115, -13, 11, -117, 
        75, -53, 43, -85, 107, -21, 27, -101, 91, -37, 
        59, -69, 123, -5, 7, -121, 71, -57, 39, -89, 
        103, -25, 23, -105, 87, -41, 55, -73, 119, -9, 
        15, -113, 79, -49, 47, -81, 111, -17, 31, -97, 
        95, -33, 63, -65, 127, -1
    };
    static short white[] = {
        6430, 6400, 6400, 6400, 3225, 3225, 3225, 3225, 944, 944, 
        944, 944, 976, 976, 976, 976, 1456, 1456, 1456, 1456, 
        1488, 1488, 1488, 1488, 718, 718, 718, 718, 718, 718, 
        718, 718, 750, 750, 750, 750, 750, 750, 750, 750, 
        1520, 1520, 1520, 1520, 1552, 1552, 1552, 1552, 428, 428, 
        428, 428, 428, 428, 428, 428, 428, 428, 428, 428, 
        428, 428, 428, 428, 654, 654, 654, 654, 654, 654, 
        654, 654, 1072, 1072, 1072, 1072, 1104, 1104, 1104, 1104, 
        1136, 1136, 1136, 1136, 1168, 1168, 1168, 1168, 1200, 1200, 
        1200, 1200, 1232, 1232, 1232, 1232, 622, 622, 622, 622, 
        622, 622, 622, 622, 1008, 1008, 1008, 1008, 1040, 1040, 
        1040, 1040, 44, 44, 44, 44, 44, 44, 44, 44, 
        44, 44, 44, 44, 44, 44, 44, 44, 396, 396, 
        396, 396, 396, 396, 396, 396, 396, 396, 396, 396, 
        396, 396, 396, 396, 1712, 1712, 1712, 1712, 1744, 1744, 
        1744, 1744, 846, 846, 846, 846, 846, 846, 846, 846, 
        1264, 1264, 1264, 1264, 1296, 1296, 1296, 1296, 1328, 1328, 
        1328, 1328, 1360, 1360, 1360, 1360, 1392, 1392, 1392, 1392, 
        1424, 1424, 1424, 1424, 686, 686, 686, 686, 686, 686, 
        686, 686, 910, 910, 910, 910, 910, 910, 910, 910, 
        1968, 1968, 1968, 1968, 2000, 2000, 2000, 2000, 2032, 2032, 
        2032, 2032, 16, 16, 16, 16, 10257, 10257, 10257, 10257, 
        12305, 12305, 12305, 12305, 330, 330, 330, 330, 330, 330, 
        330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 
        330, 330, 330, 330, 330, 330, 330, 330, 330, 330, 
        330, 330, 330, 330, 330, 330, 362, 362, 362, 362, 
        362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 
        362, 362, 362, 362, 362, 362, 362, 362, 362, 362, 
        362, 362, 362, 362, 362, 362, 362, 362, 878, 878, 
        878, 878, 878, 878, 878, 878, 1904, 1904, 1904, 1904, 
        1936, 1936, 1936, 1936, -18413, -18413, -16365, -16365, -14317, -14317, 
        -10221, -10221, 590, 590, 590, 590, 590, 590, 590, 590, 
        782, 782, 782, 782, 782, 782, 782, 782, 1584, 1584, 
        1584, 1584, 1616, 1616, 1616, 1616, 1648, 1648, 1648, 1648, 
        1680, 1680, 1680, 1680, 814, 814, 814, 814, 814, 814, 
        814, 814, 1776, 1776, 1776, 1776, 1808, 1808, 1808, 1808, 
        1840, 1840, 1840, 1840, 1872, 1872, 1872, 1872, 6157, 6157, 
        6157, 6157, 6157, 6157, 6157, 6157, 6157, 6157, 6157, 6157, 
        6157, 6157, 6157, 6157, -12275, -12275, -12275, -12275, -12275, -12275, 
        -12275, -12275, -12275, -12275, -12275, -12275, -12275, -12275, -12275, -12275, 
        14353, 14353, 14353, 14353, 16401, 16401, 16401, 16401, 22547, 22547, 
        24595, 24595, 20497, 20497, 20497, 20497, 18449, 18449, 18449, 18449, 
        26643, 26643, 28691, 28691, 30739, 30739, -32749, -32749, -30701, -30701, 
        -28653, -28653, -26605, -26605, -24557, -24557, -22509, -22509, -20461, -20461, 
        8207, 8207, 8207, 8207, 8207, 8207, 8207, 8207, 72, 72, 
        72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 
        72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 
        72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 
        72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 
        72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 
        72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 
        72, 72, 104, 104, 104, 104, 104, 104, 104, 104, 
        104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 
        104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 
        104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 
        104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 
        104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 
        104, 104, 104, 104, 104, 104, 4107, 4107, 4107, 4107, 
        4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 
        4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 
        4107, 4107, 4107, 4107, 4107, 4107, 4107, 4107, 266, 266, 
        266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 
        266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 
        266, 266, 266, 266, 266, 266, 266, 266, 266, 266, 
        298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 
        298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 
        298, 298, 298, 298, 298, 298, 298, 298, 298, 298, 
        298, 298, 524, 524, 524, 524, 524, 524, 524, 524, 
        524, 524, 524, 524, 524, 524, 524, 524, 556, 556, 
        556, 556, 556, 556, 556, 556, 556, 556, 556, 556, 
        556, 556, 556, 556, 136, 136, 136, 136, 136, 136, 
        136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 
        136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 
        136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 
        136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 
        136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 
        136, 136, 136, 136, 136, 136, 136, 136, 168, 168, 
        168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 
        168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 
        168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 
        168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 
        168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 
        168, 168, 168, 168, 168, 168, 168, 168, 168, 168, 
        168, 168, 460, 460, 460, 460, 460, 460, 460, 460, 
        460, 460, 460, 460, 460, 460, 460, 460, 492, 492, 
        492, 492, 492, 492, 492, 492, 492, 492, 492, 492, 
        492, 492, 492, 492, 2059, 2059, 2059, 2059, 2059, 2059, 
        2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 
        2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 2059, 
        2059, 2059, 2059, 2059, 2059, 2059, 200, 200, 200, 200, 
        200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 
        200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 
        200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 
        200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 
        200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 
        200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 
        232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 
        232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 
        232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 
        232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 
        232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 
        232, 232, 232, 232, 232, 232, 232, 232, 232, 232, 
        232, 232, 232, 232
    };
    static short additionalMakeup[] = {
        28679, 28679, 31752, -32759, -31735, -30711, -29687, -28663, 29703, 29703, 
        30727, 30727, -27639, -26615, -25591, -24567
    };
    static short initBlack[] = {
        3226, 6412, 200, 168, 38, 38, 134, 134, 100, 100, 
        100, 100, 68, 68, 68, 68
    };
    static short twoBitBlack[] = {
        292, 260, 226, 226
    };
    static short black[] = {
        62, 62, 30, 30, 0, 0, 0, 0, 0, 0, 
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
        0, 0, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 
        3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 
        3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 3225, 
        3225, 3225, 3225, 3225, 588, 588, 588, 588, 588, 588, 
        588, 588, 1680, 1680, 20499, 22547, 24595, 26643, 1776, 1776, 
        1808, 1808, -24557, -22509, -20461, -18413, 1904, 1904, 1936, 1936, 
        -16365, -14317, 782, 782, 782, 782, 814, 814, 814, 814, 
        -12269, -10221, 10257, 10257, 12305, 12305, 14353, 14353, 16403, 18451, 
        1712, 1712, 1744, 1744, 28691, 30739, -32749, -30701, -28653, -26605, 
        2061, 2061, 2061, 2061, 2061, 2061, 2061, 2061, 424, 424, 
        424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 
        424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 
        424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 
        750, 750, 750, 750, 1616, 1616, 1648, 1648, 1424, 1424, 
        1456, 1456, 1488, 1488, 1520, 1520, 1840, 1840, 1872, 1872, 
        1968, 1968, 8209, 8209, 524, 524, 524, 524, 524, 524, 
        524, 524, 556, 556, 556, 556, 556, 556, 556, 556, 
        1552, 1552, 1584, 1584, 2000, 2000, 2032, 2032, 976, 976, 
        1008, 1008, 1040, 1040, 1072, 1072, 1296, 1296, 1328, 1328, 
        718, 718, 718, 718, 456, 456, 456, 456, 456, 456, 
        456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 
        456, 456, 456, 456, 456, 456, 456, 456, 456, 456, 
        456, 456, 456, 456, 456, 456, 326, 326, 326, 326, 
        326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 
        326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 
        326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 
        326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 
        326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 
        326, 326, 326, 326, 326, 326, 326, 326, 326, 326, 
        358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 
        358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 
        358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 
        358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 
        358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 
        358, 358, 358, 358, 358, 358, 358, 358, 358, 358, 
        358, 358, 358, 358, 490, 490, 490, 490, 490, 490, 
        490, 490, 490, 490, 490, 490, 490, 490, 490, 490, 
        4113, 4113, 6161, 6161, 848, 848, 880, 880, 912, 912, 
        944, 944, 622, 622, 622, 622, 654, 654, 654, 654, 
        1104, 1104, 1136, 1136, 1168, 1168, 1200, 1200, 1232, 1232, 
        1264, 1264, 686, 686, 686, 686, 1360, 1360, 1392, 1392, 
        12, 12, 12, 12, 12, 12, 12, 12, 390, 390, 
        390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 
        390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 
        390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 
        390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 
        390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 
        390, 390, 390, 390, 390, 390, 390, 390, 390, 390, 
        390, 390
    };
    static byte twoDCodes[] = {
        80, 88, 23, 71, 30, 30, 62, 62, 4, 4, 
        4, 4, 4, 4, 4, 4, 11, 11, 11, 11, 
        11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 
        11, 11, 35, 35, 35, 35, 35, 35, 35, 35, 
        35, 35, 35, 35, 35, 35, 35, 35, 51, 51, 
        51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 
        51, 51, 51, 51, 41, 41, 41, 41, 41, 41, 
        41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 
        41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 
        41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 
        41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 
        41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 
        41, 41, 41, 41, 41, 41, 41, 41
    };

}
