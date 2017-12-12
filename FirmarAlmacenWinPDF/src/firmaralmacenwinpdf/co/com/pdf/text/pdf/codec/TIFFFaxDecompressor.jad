// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TIFFFaxDecompressor.java

package co.com.pdf.text.pdf.codec;


public class TIFFFaxDecompressor
{

    public TIFFFaxDecompressor()
    {
        uncompressedMode = 0;
        fillBits = 0;
        changingElemSize = 0;
        lastChangingElement = 0;
    }

    public void SetOptions(int fillOrder, int compression, int t4Options, int t6Options)
    {
        this.fillOrder = fillOrder;
        this.compression = compression;
        this.t4Options = t4Options;
        this.t6Options = t6Options;
        oneD = t4Options & 1;
        uncompressedMode = (t4Options & 2) >> 1;
        fillBits = (t4Options & 4) >> 2;
    }

    public void decodeRaw(byte buffer[], byte compData[], int w, int h)
    {
        this.buffer = buffer;
        data = compData;
        this.w = w;
        this.h = h;
        bitsPerScanline = w;
        lineBitNum = 0;
        bitPointer = 0;
        bytePointer = 0;
        prevChangingElems = new int[w + 1];
        currChangingElems = new int[w + 1];
        fails = 0;
        try
        {
            if(compression == 2)
                decodeRLE();
            else
            if(compression == 3)
                decodeT4();
            else
            if(compression == 4)
            {
                uncompressedMode = (t6Options & 2) >> 1;
                decodeT6();
            } else
            {
                throw new RuntimeException((new StringBuilder()).append("Unknown compression type ").append(compression).toString());
            }
        }
        catch(ArrayIndexOutOfBoundsException e) { }
    }

    public void decodeRLE()
    {
        for(int i = 0; i < h; i++)
        {
            decodeNextScanline();
            if(bitPointer != 0)
            {
                bytePointer++;
                bitPointer = 0;
            }
            lineBitNum += bitsPerScanline;
        }

    }

    public void decodeNextScanline()
    {
        int bits = 0;
        int code = 0;
        int isT = 0;
        boolean isWhite = true;
        int bitOffset = 0;
        changingElemSize = 0;
        do
        {
            if(bitOffset >= w)
                break;
            int runOffset = bitOffset;
            do
            {
                if(!isWhite || bitOffset >= w)
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
                if(bits == 0)
                {
                    fails++;
                } else
                {
                    if(bits == 15)
                    {
                        fails++;
                        return;
                    }
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
            int runLength;
            if(bitOffset == w)
            {
                runLength = bitOffset - runOffset;
                if(isWhite && runLength != 0 && runLength % 64 == 0 && nextNBits(8) != 53)
                {
                    fails++;
                    updatePointer(8);
                }
                break;
            }
            runOffset = bitOffset;
            do
            {
                if(isWhite || bitOffset >= w)
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
                        setToBlack(bitOffset, code);
                        bitOffset += code;
                        updatePointer(4 - bits);
                    } else
                    {
                        if(bits == 15)
                        {
                            fails++;
                            return;
                        }
                        setToBlack(bitOffset, code);
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
                    setToBlack(bitOffset, code);
                    bitOffset += code;
                    updatePointer(2 - bits);
                    isWhite = true;
                    currChangingElems[changingElemSize++] = bitOffset;
                } else
                {
                    setToBlack(bitOffset, code);
                    bitOffset += code;
                    updatePointer(4 - bits);
                    isWhite = true;
                    currChangingElems[changingElemSize++] = bitOffset;
                }
            } while(true);
            if(bitOffset != w)
                continue;
            runLength = bitOffset - runOffset;
            if(!isWhite && runLength != 0 && runLength % 64 == 0 && nextNBits(10) != 55)
            {
                fails++;
                updatePointer(10);
            }
            break;
        } while(true);
        currChangingElems[changingElemSize++] = bitOffset;
    }

    public void decodeT4()
    {
        int height = h;
        int b[] = new int[2];
        int currIndex = 0;
        if(data.length < 2)
            throw new RuntimeException("Insufficient data to read initial EOL.");
        int next12 = nextNBits(12);
        if(next12 != 1)
            fails++;
        updatePointer(12);
        int modeFlag = 0;
        int lines = -1;
        while(modeFlag != 1) 
            try
            {
                modeFlag = findNextLine();
                lines++;
            }
            catch(Exception eofe)
            {
                throw new RuntimeException("No reference line present.");
            }
        decodeNextScanline();
        lines++;
        lineBitNum += bitsPerScanline;
label0:
        for(; lines < height; lines++)
        {
label1:
            {
label2:
                {
                    int bitOffset;
label3:
                    {
                        try
                        {
                            modeFlag = findNextLine();
                        }
                        catch(Exception eofe)
                        {
                            fails++;
                            break label0;
                        }
                        if(modeFlag != 0)
                            break label2;
                        int temp[] = prevChangingElems;
                        prevChangingElems = currChangingElems;
                        currChangingElems = temp;
                        currIndex = 0;
                        int a0 = -1;
                        boolean isWhite = true;
                        bitOffset = 0;
                        lastChangingElement = 0;
                        do
                        {
                            if(bitOffset >= w)
                                break label3;
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
                                    setToBlack(bitOffset, b2 - bitOffset);
                                bitOffset = a0 = b2;
                                updatePointer(7 - bits);
                                continue;
                            }
                            if(code == 1)
                            {
                                updatePointer(7 - bits);
                                if(isWhite)
                                {
                                    int number = decodeWhiteCodeWord();
                                    bitOffset += number;
                                    currChangingElems[currIndex++] = bitOffset;
                                    number = decodeBlackCodeWord();
                                    setToBlack(bitOffset, number);
                                    bitOffset += number;
                                    currChangingElems[currIndex++] = bitOffset;
                                } else
                                {
                                    int number = decodeBlackCodeWord();
                                    setToBlack(bitOffset, number);
                                    bitOffset += number;
                                    currChangingElems[currIndex++] = bitOffset;
                                    number = decodeWhiteCodeWord();
                                    bitOffset += number;
                                    currChangingElems[currIndex++] = bitOffset;
                                }
                                a0 = bitOffset;
                                continue;
                            }
                            if(code > 8)
                                break;
                            int a1 = b1 + (code - 5);
                            currChangingElems[currIndex++] = a1;
                            if(!isWhite)
                                setToBlack(bitOffset, a1 - bitOffset);
                            bitOffset = a0 = a1;
                            isWhite = !isWhite;
                            updatePointer(7 - bits);
                        } while(true);
                        fails++;
                        int numLinesTested = 0;
                        while(modeFlag != 1) 
                            try
                            {
                                modeFlag = findNextLine();
                                numLinesTested++;
                            }
                            catch(Exception eofe)
                            {
                                return;
                            }
                        lines += numLinesTested - 1;
                        updatePointer(13);
                    }
                    currChangingElems[currIndex++] = bitOffset;
                    changingElemSize = currIndex;
                    break label1;
                }
                decodeNextScanline();
            }
            lineBitNum += bitsPerScanline;
        }

    }

    public synchronized void decodeT6()
    {
        int height = h;
        int b[] = new int[2];
        int cce[] = currChangingElems;
        changingElemSize = 0;
        cce[changingElemSize++] = w;
        cce[changingElemSize++] = w;
        for(int lines = 0; lines < height; lines++)
        {
            int a0 = -1;
            boolean isWhite = true;
            int temp[] = prevChangingElems;
            prevChangingElems = currChangingElems;
            cce = currChangingElems = temp;
            int currIndex = 0;
            int bitOffset = 0;
            lastChangingElement = 0;
label0:
            do
            {
                int code;
label1:
                do
                {
                    while(bitOffset < w) 
                    {
                        getNextChangingElement(a0, isWhite, b);
                        int b1 = b[0];
                        int b2 = b[1];
                        int entry = nextLesserThan8Bits(7);
                        entry = twoDCodes[entry] & 0xff;
                        code = (entry & 0x78) >>> 3;
                        int bits = entry & 7;
                        if(code == 0)
                        {
                            if(!isWhite)
                            {
                                if(b2 > w)
                                    b2 = w;
                                setToBlack(bitOffset, b2 - bitOffset);
                            }
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
                                if(number > w - bitOffset)
                                    number = w - bitOffset;
                                setToBlack(bitOffset, number);
                                bitOffset += number;
                                cce[currIndex++] = bitOffset;
                            } else
                            {
                                int number = decodeBlackCodeWord();
                                if(number > w - bitOffset)
                                    number = w - bitOffset;
                                setToBlack(bitOffset, number);
                                bitOffset += number;
                                cce[currIndex++] = bitOffset;
                                number = decodeWhiteCodeWord();
                                bitOffset += number;
                                cce[currIndex++] = bitOffset;
                            }
                            a0 = bitOffset;
                        } else
                        {
                            if(code > 8)
                                continue label1;
                            int a1 = b1 + (code - 5);
                            cce[currIndex++] = a1;
                            if(!isWhite)
                            {
                                if(a1 > w)
                                    a1 = w;
                                setToBlack(bitOffset, a1 - bitOffset);
                            }
                            bitOffset = a0 = a1;
                            isWhite = !isWhite;
                            updatePointer(7 - bits);
                        }
                    }
                    break label0;
                } while(code != 11);
                int entranceCode = nextLesserThan8Bits(3);
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
                        setToBlack(bitOffset, 1);
                        bitOffset++;
                        isWhite = false;
                    }
                }
            } while(true);
            if(currIndex <= w)
                cce[currIndex++] = bitOffset;
            changingElemSize = currIndex;
            lineBitNum += bitsPerScanline;
        }

    }

    private void setToBlack(int bitNum, int numBits)
    {
        bitNum += lineBitNum;
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
                    throw new RuntimeException("Error 0");
                if(bits == 15)
                    throw new RuntimeException("Error 1");
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
                        throw new RuntimeException("Error 2");
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

    private int findNextLine()
    {
        int bitIndexMax = data.length * 8 - 1;
        int bitIndexMax12 = bitIndexMax - 12;
        for(int bitIndex = bytePointer * 8 + bitPointer; bitIndex <= bitIndexMax12;)
        {
            int next12Bits = nextNBits(12);
            for(bitIndex += 12; next12Bits != 1 && bitIndex < bitIndexMax; bitIndex++)
                next12Bits = (next12Bits & 0x7ff) << 1 | nextLesserThan8Bits(1) & 1;

            if(next12Bits == 1)
                if(oneD == 1)
                {
                    if(bitIndex < bitIndexMax)
                        return nextLesserThan8Bits(1);
                } else
                {
                    return 1;
                }
        }

        throw new RuntimeException();
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
            throw new RuntimeException("Invalid FillOrder");
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
        int l = data.length - 1;
        int bp = bytePointer;
        byte b;
        byte next;
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
            b = flipTable[data[bp] & 0xff];
            if(bp == l)
                next = 0;
            else
                next = flipTable[data[bp + 1] & 0xff];
        } else
        {
            throw new RuntimeException("Invalid FillOrder");
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
        if(bitsToMoveBack > 8)
        {
            bytePointer -= bitsToMoveBack / 8;
            bitsToMoveBack %= 8;
        }
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

    protected int fillOrder;
    protected int compression;
    private int t4Options;
    private int t6Options;
    public int fails;
    protected int uncompressedMode;
    protected int fillBits;
    protected int oneD;
    private byte data[];
    private int bitPointer;
    private int bytePointer;
    private byte buffer[];
    private int w;
    private int h;
    private int bitsPerScanline;
    private int lineBitNum;
    private int changingElemSize;
    private int prevChangingElems[];
    private int currChangingElems[];
    private int lastChangingElement;
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
