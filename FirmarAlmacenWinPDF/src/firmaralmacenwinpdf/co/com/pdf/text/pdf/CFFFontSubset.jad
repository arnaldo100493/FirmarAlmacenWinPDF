// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CFFFontSubset.java

package co.com.pdf.text.pdf;

import java.io.IOException;
import java.util.*;

// Referenced classes of package co.com.pdf.text.pdf:
//            CFFFont, RandomAccessFileOrArray

public class CFFFontSubset extends CFFFont
{

    public CFFFontSubset(RandomAccessFileOrArray rf, HashMap GlyphsUsed)
    {
        super(rf);
        FDArrayUsed = new HashSet();
        hGSubrsUsed = new HashMap();
        lGSubrsUsed = new ArrayList();
        hSubrsUsedNonCID = new HashMap();
        lSubrsUsedNonCID = new ArrayList();
        GBias = 0;
        NumOfHints = 0;
        this.GlyphsUsed = GlyphsUsed;
        glyphsInList = new ArrayList(GlyphsUsed.keySet());
        for(int i = 0; i < fonts.length; i++)
        {
            seek(fonts[i].charstringsOffset);
            fonts[i].nglyphs = getCard16();
            seek(stringIndexOffset);
            fonts[i].nstrings = getCard16() + standardStrings.length;
            fonts[i].charstringsOffsets = getIndex(fonts[i].charstringsOffset);
            if(fonts[i].fdselectOffset >= 0)
            {
                readFDSelect(i);
                BuildFDArrayUsed(i);
            }
            if(fonts[i].isCID)
                ReadFDArray(i);
            fonts[i].CharsetLength = CountCharset(fonts[i].charsetOffset, fonts[i].nglyphs);
        }

    }

    int CountCharset(int Offset, int NumofGlyphs)
    {
        int Length = 0;
        seek(Offset);
        int format = getCard8();
        switch(format)
        {
        case 0: // '\0'
            Length = 1 + 2 * NumofGlyphs;
            break;

        case 1: // '\001'
            Length = 1 + 3 * CountRange(NumofGlyphs, 1);
            break;

        case 2: // '\002'
            Length = 1 + 4 * CountRange(NumofGlyphs, 2);
            break;
        }
        return Length;
    }

    int CountRange(int NumofGlyphs, int Type)
    {
        int num = 0;
        int nLeft;
        for(int i = 1; i < NumofGlyphs; i += nLeft + 1)
        {
            num++;
            char Sid = getCard16();
            if(Type == 1)
                nLeft = getCard8();
            else
                nLeft = getCard16();
        }

        return num;
    }

    protected void readFDSelect(int Font)
    {
        int NumOfGlyphs = fonts[Font].nglyphs;
        int FDSelect[] = new int[NumOfGlyphs];
        seek(fonts[Font].fdselectOffset);
        fonts[Font].FDSelectFormat = getCard8();
        switch(fonts[Font].FDSelectFormat)
        {
        default:
            break;

        case 0: // '\0'
            for(int i = 0; i < NumOfGlyphs; i++)
                FDSelect[i] = getCard8();

            fonts[Font].FDSelectLength = fonts[Font].nglyphs + 1;
            break;

        case 3: // '\003'
            int nRanges = getCard16();
            int l = 0;
            int first = getCard16();
            for(int i = 0; i < nRanges; i++)
            {
                int fd = getCard8();
                int last = getCard16();
                int steps = last - first;
                for(int k = 0; k < steps; k++)
                {
                    FDSelect[l] = fd;
                    l++;
                }

                first = last;
            }

            fonts[Font].FDSelectLength = 3 + nRanges * 3 + 2;
            break;
        }
        fonts[Font].FDSelect = FDSelect;
    }

    protected void BuildFDArrayUsed(int Font)
    {
        int FDSelect[] = fonts[Font].FDSelect;
        for(int i = 0; i < glyphsInList.size(); i++)
        {
            int glyph = ((Integer)glyphsInList.get(i)).intValue();
            int FD = FDSelect[glyph];
            FDArrayUsed.add(Integer.valueOf(FD));
        }

    }

    protected void ReadFDArray(int Font)
    {
        seek(fonts[Font].fdarrayOffset);
        fonts[Font].FDArrayCount = getCard16();
        fonts[Font].FDArrayOffsize = getCard8();
        if(fonts[Font].FDArrayOffsize < 4)
            fonts[Font].FDArrayOffsize++;
        fonts[Font].FDArrayOffsets = getIndex(fonts[Font].fdarrayOffset);
    }

    public byte[] Process(String fontName)
        throws IOException
    {
        int j;
        byte abyte0[];
        buf.reOpen();
        for(j = 0; j < fonts.length && !fontName.equals(fonts[j].name); j++);
        if(j != fonts.length)
            break MISSING_BLOCK_LABEL_68;
        abyte0 = null;
        try
        {
            buf.close();
        }
        catch(Exception e) { }
        return abyte0;
        byte abyte1[];
        if(gsubrIndexOffset >= 0)
            GBias = CalcBias(gsubrIndexOffset, j);
        BuildNewCharString(j);
        BuildNewLGSubrs(j);
        byte Ret[] = BuildNewFile(j);
        abyte1 = Ret;
        try
        {
            buf.close();
        }
        catch(Exception e) { }
        return abyte1;
        Exception exception;
        exception;
        try
        {
            buf.close();
        }
        catch(Exception e) { }
        throw exception;
    }

    protected int CalcBias(int Offset, int Font)
    {
        seek(Offset);
        int nSubrs = getCard16();
        if(fonts[Font].CharstringType == 1)
            return 0;
        if(nSubrs < 1240)
            return 107;
        return nSubrs >= 33900 ? 32768 : 1131;
    }

    protected void BuildNewCharString(int FontIndex)
        throws IOException
    {
        NewCharStringsIndex = BuildNewIndex(fonts[FontIndex].charstringsOffsets, GlyphsUsed, (byte)14);
    }

    protected void BuildNewLGSubrs(int Font)
        throws IOException
    {
        if(fonts[Font].isCID)
        {
            hSubrsUsed = new HashMap[fonts[Font].fdprivateOffsets.length];
            lSubrsUsed = new ArrayList[fonts[Font].fdprivateOffsets.length];
            NewLSubrsIndex = new byte[fonts[Font].fdprivateOffsets.length][];
            fonts[Font].PrivateSubrsOffset = new int[fonts[Font].fdprivateOffsets.length];
            fonts[Font].PrivateSubrsOffsetsArray = new int[fonts[Font].fdprivateOffsets.length][];
            ArrayList FDInList = new ArrayList(FDArrayUsed);
            for(int j = 0; j < FDInList.size(); j++)
            {
                int FD = ((Integer)FDInList.get(j)).intValue();
                hSubrsUsed[FD] = new HashMap();
                lSubrsUsed[FD] = new ArrayList();
                BuildFDSubrsOffsets(Font, FD);
                if(fonts[Font].PrivateSubrsOffset[FD] >= 0)
                {
                    BuildSubrUsed(Font, FD, fonts[Font].PrivateSubrsOffset[FD], fonts[Font].PrivateSubrsOffsetsArray[FD], hSubrsUsed[FD], lSubrsUsed[FD]);
                    NewLSubrsIndex[FD] = BuildNewIndex(fonts[Font].PrivateSubrsOffsetsArray[FD], hSubrsUsed[FD], (byte)11);
                }
            }

        } else
        if(fonts[Font].privateSubrs >= 0)
        {
            fonts[Font].SubrsOffsets = getIndex(fonts[Font].privateSubrs);
            BuildSubrUsed(Font, -1, fonts[Font].privateSubrs, fonts[Font].SubrsOffsets, hSubrsUsedNonCID, lSubrsUsedNonCID);
        }
        BuildGSubrsUsed(Font);
        if(fonts[Font].privateSubrs >= 0)
            NewSubrsIndexNonCID = BuildNewIndex(fonts[Font].SubrsOffsets, hSubrsUsedNonCID, (byte)11);
        NewGSubrsIndex = BuildNewIndex(gsubrOffsets, hGSubrsUsed, (byte)11);
    }

    protected void BuildFDSubrsOffsets(int Font, int FD)
    {
        fonts[Font].PrivateSubrsOffset[FD] = -1;
        seek(fonts[Font].fdprivateOffsets[FD]);
        do
        {
            if(getPosition() >= fonts[Font].fdprivateOffsets[FD] + fonts[Font].fdprivateLengths[FD])
                break;
            getDictItem();
            if(key == "Subrs")
                fonts[Font].PrivateSubrsOffset[FD] = ((Integer)args[0]).intValue() + fonts[Font].fdprivateOffsets[FD];
        } while(true);
        if(fonts[Font].PrivateSubrsOffset[FD] >= 0)
            fonts[Font].PrivateSubrsOffsetsArray[FD] = getIndex(fonts[Font].PrivateSubrsOffset[FD]);
    }

    protected void BuildSubrUsed(int Font, int FD, int SubrOffset, int SubrsOffsets[], HashMap hSubr, ArrayList lSubr)
    {
        int LBias = CalcBias(SubrOffset, Font);
        for(int i = 0; i < glyphsInList.size(); i++)
        {
            int glyph = ((Integer)glyphsInList.get(i)).intValue();
            int Start = fonts[Font].charstringsOffsets[glyph];
            int End = fonts[Font].charstringsOffsets[glyph + 1];
            if(FD >= 0)
            {
                EmptyStack();
                NumOfHints = 0;
                int GlyphFD = fonts[Font].FDSelect[glyph];
                if(GlyphFD == FD)
                    ReadASubr(Start, End, GBias, LBias, hSubr, lSubr, SubrsOffsets);
            } else
            {
                ReadASubr(Start, End, GBias, LBias, hSubr, lSubr, SubrsOffsets);
            }
        }

        for(int i = 0; i < lSubr.size(); i++)
        {
            int Subr = ((Integer)lSubr.get(i)).intValue();
            if(Subr < SubrsOffsets.length - 1 && Subr >= 0)
            {
                int Start = SubrsOffsets[Subr];
                int End = SubrsOffsets[Subr + 1];
                ReadASubr(Start, End, GBias, LBias, hSubr, lSubr, SubrsOffsets);
            }
        }

    }

    protected void BuildGSubrsUsed(int Font)
    {
        int LBias = 0;
        int SizeOfNonCIDSubrsUsed = 0;
        if(fonts[Font].privateSubrs >= 0)
        {
            LBias = CalcBias(fonts[Font].privateSubrs, Font);
            SizeOfNonCIDSubrsUsed = lSubrsUsedNonCID.size();
        }
        for(int i = 0; i < lGSubrsUsed.size(); i++)
        {
            int Subr = ((Integer)lGSubrsUsed.get(i)).intValue();
            if(Subr >= gsubrOffsets.length - 1 || Subr < 0)
                continue;
            int Start = gsubrOffsets[Subr];
            int End = gsubrOffsets[Subr + 1];
            if(fonts[Font].isCID)
            {
                ReadASubr(Start, End, GBias, 0, hGSubrsUsed, lGSubrsUsed, null);
                continue;
            }
            ReadASubr(Start, End, GBias, LBias, hSubrsUsedNonCID, lSubrsUsedNonCID, fonts[Font].SubrsOffsets);
            if(SizeOfNonCIDSubrsUsed >= lSubrsUsedNonCID.size())
                continue;
            for(int j = SizeOfNonCIDSubrsUsed; j < lSubrsUsedNonCID.size(); j++)
            {
                int LSubr = ((Integer)lSubrsUsedNonCID.get(j)).intValue();
                if(LSubr < fonts[Font].SubrsOffsets.length - 1 && LSubr >= 0)
                {
                    int LStart = fonts[Font].SubrsOffsets[LSubr];
                    int LEnd = fonts[Font].SubrsOffsets[LSubr + 1];
                    ReadASubr(LStart, LEnd, GBias, LBias, hSubrsUsedNonCID, lSubrsUsedNonCID, fonts[Font].SubrsOffsets);
                }
            }

            SizeOfNonCIDSubrsUsed = lSubrsUsedNonCID.size();
        }

    }

    protected void ReadASubr(int begin, int end, int GBias, int LBias, HashMap hSubr, ArrayList lSubr, int LSubrsOffsets[])
    {
        EmptyStack();
        NumOfHints = 0;
        seek(begin);
label0:
        do
        {
            do
            {
                if(getPosition() >= end)
                    break label0;
                ReadCommand();
                int pos = getPosition();
                Object TopElement = null;
                if(arg_count > 0)
                    TopElement = args[arg_count - 1];
                int NumOfArgs = arg_count;
                HandelStack();
                if(key == "callsubr")
                {
                    if(NumOfArgs > 0)
                    {
                        int Subr = ((Integer)TopElement).intValue() + LBias;
                        if(!hSubr.containsKey(Integer.valueOf(Subr)))
                        {
                            hSubr.put(Integer.valueOf(Subr), null);
                            lSubr.add(Integer.valueOf(Subr));
                        }
                        CalcHints(LSubrsOffsets[Subr], LSubrsOffsets[Subr + 1], LBias, GBias, LSubrsOffsets);
                        seek(pos);
                    }
                } else
                if(key == "callgsubr")
                {
                    if(NumOfArgs > 0)
                    {
                        int Subr = ((Integer)TopElement).intValue() + GBias;
                        if(!hGSubrsUsed.containsKey(Integer.valueOf(Subr)))
                        {
                            hGSubrsUsed.put(Integer.valueOf(Subr), null);
                            lGSubrsUsed.add(Integer.valueOf(Subr));
                        }
                        CalcHints(gsubrOffsets[Subr], gsubrOffsets[Subr + 1], LBias, GBias, LSubrsOffsets);
                        seek(pos);
                    }
                } else
                {
                    if(key != "hstem" && key != "vstem" && key != "hstemhm" && key != "vstemhm")
                        continue;
                    NumOfHints += NumOfArgs / 2;
                }
                continue label0;
            } while(key != "hintmask" && key != "cntrmask");
            int SizeOfMask = NumOfHints / 8;
            if(NumOfHints % 8 != 0 || SizeOfMask == 0)
                SizeOfMask++;
            int i = 0;
            while(i < SizeOfMask) 
            {
                getCard8();
                i++;
            }
        } while(true);
    }

    protected void HandelStack()
    {
        int StackHandel = StackOpp();
        if(StackHandel < 2)
        {
            if(StackHandel == 1)
            {
                PushStack();
            } else
            {
                StackHandel *= -1;
                for(int i = 0; i < StackHandel; i++)
                    PopStack();

            }
        } else
        {
            EmptyStack();
        }
    }

    protected int StackOpp()
    {
        if(key == "ifelse")
            return -3;
        if(key == "roll" || key == "put")
            return -2;
        if(key == "callsubr" || key == "callgsubr" || key == "add" || key == "sub" || key == "div" || key == "mul" || key == "drop" || key == "and" || key == "or" || key == "eq")
            return -1;
        if(key == "abs" || key == "neg" || key == "sqrt" || key == "exch" || key == "index" || key == "get" || key == "not" || key == "return")
            return 0;
        return key != "random" && key != "dup" ? 2 : 1;
    }

    protected void EmptyStack()
    {
        for(int i = 0; i < arg_count; i++)
            args[i] = null;

        arg_count = 0;
    }

    protected void PopStack()
    {
        if(arg_count > 0)
        {
            args[arg_count - 1] = null;
            arg_count--;
        }
    }

    protected void PushStack()
    {
        arg_count++;
    }

    protected void ReadCommand()
    {
        key = null;
        boolean gotKey = false;
        do
        {
            if(gotKey)
                break;
            char b0 = getCard8();
            if(b0 == '\034')
            {
                int first = getCard8();
                int second = getCard8();
                args[arg_count] = Integer.valueOf(first << 8 | second);
                arg_count++;
            } else
            if(b0 >= ' ' && b0 <= '\366')
            {
                args[arg_count] = Integer.valueOf(b0 - 139);
                arg_count++;
            } else
            if(b0 >= '\367' && b0 <= '\372')
            {
                int w = getCard8();
                args[arg_count] = Integer.valueOf((b0 - 247) * 256 + w + 108);
                arg_count++;
            } else
            if(b0 >= '\373' && b0 <= '\376')
            {
                int w = getCard8();
                args[arg_count] = Integer.valueOf(-(b0 - 251) * 256 - w - 108);
                arg_count++;
            } else
            if(b0 == '\377')
            {
                int first = getCard8();
                int second = getCard8();
                int third = getCard8();
                int fourth = getCard8();
                args[arg_count] = Integer.valueOf(first << 24 | second << 16 | third << 8 | fourth);
                arg_count++;
            } else
            if(b0 <= '\037' && b0 != '\034')
            {
                gotKey = true;
                if(b0 == '\f')
                {
                    int b1 = getCard8();
                    if(b1 > SubrsEscapeFuncs.length - 1)
                        b1 = SubrsEscapeFuncs.length - 1;
                    key = SubrsEscapeFuncs[b1];
                } else
                {
                    key = SubrsFunctions[b0];
                }
            }
        } while(true);
    }

    protected int CalcHints(int begin, int end, int LBias, int GBias, int LSubrsOffsets[])
    {
        seek(begin);
label0:
        do
        {
            do
            {
                if(getPosition() >= end)
                    break label0;
                ReadCommand();
                int pos = getPosition();
                Object TopElement = null;
                if(arg_count > 0)
                    TopElement = args[arg_count - 1];
                int NumOfArgs = arg_count;
                HandelStack();
                if(key == "callsubr")
                {
                    if(NumOfArgs > 0)
                    {
                        int Subr = ((Integer)TopElement).intValue() + LBias;
                        CalcHints(LSubrsOffsets[Subr], LSubrsOffsets[Subr + 1], LBias, GBias, LSubrsOffsets);
                        seek(pos);
                    }
                } else
                if(key == "callgsubr")
                {
                    if(NumOfArgs > 0)
                    {
                        int Subr = ((Integer)TopElement).intValue() + GBias;
                        CalcHints(gsubrOffsets[Subr], gsubrOffsets[Subr + 1], LBias, GBias, LSubrsOffsets);
                        seek(pos);
                    }
                } else
                {
                    if(key != "hstem" && key != "vstem" && key != "hstemhm" && key != "vstemhm")
                        continue;
                    NumOfHints += NumOfArgs / 2;
                }
                continue label0;
            } while(key != "hintmask" && key != "cntrmask");
            int SizeOfMask = NumOfHints / 8;
            if(NumOfHints % 8 != 0 || SizeOfMask == 0)
                SizeOfMask++;
            int i = 0;
            while(i < SizeOfMask) 
            {
                getCard8();
                i++;
            }
        } while(true);
        return NumOfHints;
    }

    protected byte[] BuildNewIndex(int Offsets[], HashMap Used, byte OperatorForUnusedEntries)
        throws IOException
    {
        int unusedCount = 0;
        int Offset = 0;
        int NewOffsets[] = new int[Offsets.length];
        for(int i = 0; i < Offsets.length; i++)
        {
            NewOffsets[i] = Offset;
            if(Used.containsKey(Integer.valueOf(i)))
                Offset += Offsets[i + 1] - Offsets[i];
            else
                unusedCount++;
        }

        byte NewObjects[] = new byte[Offset + unusedCount];
        int unusedOffset = 0;
        for(int i = 0; i < Offsets.length - 1; i++)
        {
            int start = NewOffsets[i];
            int end = NewOffsets[i + 1];
            NewOffsets[i] = start + unusedOffset;
            if(start != end)
            {
                buf.seek(Offsets[i]);
                buf.readFully(NewObjects, start + unusedOffset, end - start);
            } else
            {
                NewObjects[start + unusedOffset] = OperatorForUnusedEntries;
                unusedOffset++;
            }
        }

        NewOffsets[Offsets.length - 1] += unusedOffset;
        return AssembleIndex(NewOffsets, NewObjects);
    }

    protected byte[] AssembleIndex(int NewOffsets[], byte NewObjects[])
    {
        char Count = (char)(NewOffsets.length - 1);
        int Size = NewOffsets[NewOffsets.length - 1];
        byte Offsize;
        if(Size <= 255)
            Offsize = 1;
        else
        if(Size <= 65535)
            Offsize = 2;
        else
        if(Size <= 0xffffff)
            Offsize = 3;
        else
            Offsize = 4;
        byte NewIndex[] = new byte[3 + Offsize * (Count + 1) + NewObjects.length];
        int Place = 0;
        NewIndex[Place++] = (byte)(Count >>> 8 & 0xff);
        NewIndex[Place++] = (byte)(Count >>> 0 & 0xff);
        NewIndex[Place++] = Offsize;
        int arr$[] = NewOffsets;
        int len$ = arr$.length;
        int i$ = 0;
        do
        {
            if(i$ >= len$)
                break;
            int newOffset = arr$[i$];
            int Num = (newOffset - NewOffsets[0]) + 1;
            switch(Offsize)
            {
            case 4: // '\004'
                NewIndex[Place++] = (byte)(Num >>> 24 & 0xff);
                // fall through

            case 3: // '\003'
                NewIndex[Place++] = (byte)(Num >>> 16 & 0xff);
                // fall through

            case 2: // '\002'
                NewIndex[Place++] = (byte)(Num >>> 8 & 0xff);
                // fall through

            case 1: // '\001'
                NewIndex[Place++] = (byte)(Num >>> 0 & 0xff);
                // fall through

            default:
                i$++;
                break;
            }
        } while(true);
        arr$ = NewObjects;
        len$ = arr$.length;
        for(i$ = 0; i$ < len$; i$++)
        {
            byte newObject = arr$[i$];
            NewIndex[Place++] = newObject;
        }

        return NewIndex;
    }

    protected byte[] BuildNewFile(int Font)
    {
        OutputList = new LinkedList();
        CopyHeader();
        BuildIndexHeader(1, 1, 1);
        OutputList.addLast(new CFFFont.UInt8Item((char)(1 + fonts[Font].name.length())));
        OutputList.addLast(new CFFFont.StringItem(fonts[Font].name));
        BuildIndexHeader(1, 2, 1);
        CFFFont.OffsetItem topdictIndex1Ref = new CFFFont.IndexOffsetItem(2);
        OutputList.addLast(topdictIndex1Ref);
        CFFFont.IndexBaseItem topdictBase = new CFFFont.IndexBaseItem();
        OutputList.addLast(topdictBase);
        CFFFont.OffsetItem charsetRef = new CFFFont.DictOffsetItem();
        CFFFont.OffsetItem charstringsRef = new CFFFont.DictOffsetItem();
        CFFFont.OffsetItem fdarrayRef = new CFFFont.DictOffsetItem();
        CFFFont.OffsetItem fdselectRef = new CFFFont.DictOffsetItem();
        CFFFont.OffsetItem privateRef = new CFFFont.DictOffsetItem();
        if(!fonts[Font].isCID)
        {
            OutputList.addLast(new CFFFont.DictNumberItem(fonts[Font].nstrings));
            OutputList.addLast(new CFFFont.DictNumberItem(fonts[Font].nstrings + 1));
            OutputList.addLast(new CFFFont.DictNumberItem(0));
            OutputList.addLast(new CFFFont.UInt8Item('\f'));
            OutputList.addLast(new CFFFont.UInt8Item('\036'));
            OutputList.addLast(new CFFFont.DictNumberItem(fonts[Font].nglyphs));
            OutputList.addLast(new CFFFont.UInt8Item('\f'));
            OutputList.addLast(new CFFFont.UInt8Item('"'));
        }
        seek(topdictOffsets[Font]);
        do
        {
            if(getPosition() >= topdictOffsets[Font + 1])
                break;
            int p1 = getPosition();
            getDictItem();
            int p2 = getPosition();
            if(key != "Encoding" && key != "Private" && key != "FDSelect" && key != "FDArray" && key != "charset" && key != "CharStrings")
                OutputList.add(new CFFFont.RangeItem(buf, p1, p2 - p1));
        } while(true);
        CreateKeys(fdarrayRef, fdselectRef, charsetRef, charstringsRef);
        OutputList.addLast(new CFFFont.IndexMarkerItem(topdictIndex1Ref, topdictBase));
        if(fonts[Font].isCID)
            OutputList.addLast(getEntireIndexRange(stringIndexOffset));
        else
            CreateNewStringIndex(Font);
        OutputList.addLast(new CFFFont.RangeItem(new RandomAccessFileOrArray(NewGSubrsIndex), 0, NewGSubrsIndex.length));
        if(fonts[Font].isCID)
        {
            OutputList.addLast(new CFFFont.MarkerItem(fdselectRef));
            if(fonts[Font].fdselectOffset >= 0)
                OutputList.addLast(new CFFFont.RangeItem(buf, fonts[Font].fdselectOffset, fonts[Font].FDSelectLength));
            else
                CreateFDSelect(fdselectRef, fonts[Font].nglyphs);
            OutputList.addLast(new CFFFont.MarkerItem(charsetRef));
            OutputList.addLast(new CFFFont.RangeItem(buf, fonts[Font].charsetOffset, fonts[Font].CharsetLength));
            if(fonts[Font].fdarrayOffset >= 0)
            {
                OutputList.addLast(new CFFFont.MarkerItem(fdarrayRef));
                Reconstruct(Font);
            } else
            {
                CreateFDArray(fdarrayRef, privateRef, Font);
            }
        } else
        {
            CreateFDSelect(fdselectRef, fonts[Font].nglyphs);
            CreateCharset(charsetRef, fonts[Font].nglyphs);
            CreateFDArray(fdarrayRef, privateRef, Font);
        }
        if(fonts[Font].privateOffset >= 0)
        {
            CFFFont.IndexBaseItem PrivateBase = new CFFFont.IndexBaseItem();
            OutputList.addLast(PrivateBase);
            OutputList.addLast(new CFFFont.MarkerItem(privateRef));
            CFFFont.OffsetItem Subr = new CFFFont.DictOffsetItem();
            CreateNonCIDPrivate(Font, Subr);
            CreateNonCIDSubrs(Font, PrivateBase, Subr);
        }
        OutputList.addLast(new CFFFont.MarkerItem(charstringsRef));
        OutputList.addLast(new CFFFont.RangeItem(new RandomAccessFileOrArray(NewCharStringsIndex), 0, NewCharStringsIndex.length));
        int currentOffset[] = new int[1];
        currentOffset[0] = 0;
        CFFFont.Item item;
        for(Iterator listIter = OutputList.iterator(); listIter.hasNext(); item.increment(currentOffset))
            item = (CFFFont.Item)listIter.next();

        CFFFont.Item item;
        for(Iterator listIter = OutputList.iterator(); listIter.hasNext(); item.xref())
            item = (CFFFont.Item)listIter.next();

        int size = currentOffset[0];
        byte b[] = new byte[size];
        CFFFont.Item item;
        for(Iterator listIter = OutputList.iterator(); listIter.hasNext(); item.emit(b))
            item = (CFFFont.Item)listIter.next();

        return b;
    }

    protected void CopyHeader()
    {
        seek(0);
        int major = getCard8();
        int minor = getCard8();
        int hdrSize = getCard8();
        int offSize = getCard8();
        nextIndexOffset = hdrSize;
        OutputList.addLast(new CFFFont.RangeItem(buf, 0, hdrSize));
    }

    protected void BuildIndexHeader(int Count, int Offsize, int First)
    {
        OutputList.addLast(new CFFFont.UInt16Item((char)Count));
        OutputList.addLast(new CFFFont.UInt8Item((char)Offsize));
        switch(Offsize)
        {
        case 1: // '\001'
            OutputList.addLast(new CFFFont.UInt8Item((char)First));
            break;

        case 2: // '\002'
            OutputList.addLast(new CFFFont.UInt16Item((char)First));
            break;

        case 3: // '\003'
            OutputList.addLast(new CFFFont.UInt24Item((char)First));
            break;

        case 4: // '\004'
            OutputList.addLast(new CFFFont.UInt32Item((char)First));
            break;
        }
    }

    protected void CreateKeys(CFFFont.OffsetItem fdarrayRef, CFFFont.OffsetItem fdselectRef, CFFFont.OffsetItem charsetRef, CFFFont.OffsetItem charstringsRef)
    {
        OutputList.addLast(fdarrayRef);
        OutputList.addLast(new CFFFont.UInt8Item('\f'));
        OutputList.addLast(new CFFFont.UInt8Item('$'));
        OutputList.addLast(fdselectRef);
        OutputList.addLast(new CFFFont.UInt8Item('\f'));
        OutputList.addLast(new CFFFont.UInt8Item('%'));
        OutputList.addLast(charsetRef);
        OutputList.addLast(new CFFFont.UInt8Item('\017'));
        OutputList.addLast(charstringsRef);
        OutputList.addLast(new CFFFont.UInt8Item('\021'));
    }

    protected void CreateNewStringIndex(int Font)
    {
        String fdFontName = (new StringBuilder()).append(fonts[Font].name).append("-OneRange").toString();
        if(fdFontName.length() > 127)
            fdFontName = fdFontName.substring(0, 127);
        String extraStrings = (new StringBuilder()).append("AdobeIdentity").append(fdFontName).toString();
        int origStringsLen = stringOffsets[stringOffsets.length - 1] - stringOffsets[0];
        int stringsBaseOffset = stringOffsets[0] - 1;
        byte stringsIndexOffSize;
        if(origStringsLen + extraStrings.length() <= 255)
            stringsIndexOffSize = 1;
        else
        if(origStringsLen + extraStrings.length() <= 65535)
            stringsIndexOffSize = 2;
        else
        if(origStringsLen + extraStrings.length() <= 0xffffff)
            stringsIndexOffSize = 3;
        else
            stringsIndexOffSize = 4;
        OutputList.addLast(new CFFFont.UInt16Item((char)((stringOffsets.length - 1) + 3)));
        OutputList.addLast(new CFFFont.UInt8Item((char)stringsIndexOffSize));
        int arr$[] = stringOffsets;
        int len$ = arr$.length;
        for(int i$ = 0; i$ < len$; i$++)
        {
            int stringOffset = arr$[i$];
            OutputList.addLast(new CFFFont.IndexOffsetItem(stringsIndexOffSize, stringOffset - stringsBaseOffset));
        }

        int currentStringsOffset = stringOffsets[stringOffsets.length - 1] - stringsBaseOffset;
        currentStringsOffset += "Adobe".length();
        OutputList.addLast(new CFFFont.IndexOffsetItem(stringsIndexOffSize, currentStringsOffset));
        currentStringsOffset += "Identity".length();
        OutputList.addLast(new CFFFont.IndexOffsetItem(stringsIndexOffSize, currentStringsOffset));
        currentStringsOffset += fdFontName.length();
        OutputList.addLast(new CFFFont.IndexOffsetItem(stringsIndexOffSize, currentStringsOffset));
        OutputList.addLast(new CFFFont.RangeItem(buf, stringOffsets[0], origStringsLen));
        OutputList.addLast(new CFFFont.StringItem(extraStrings));
    }

    protected void CreateFDSelect(CFFFont.OffsetItem fdselectRef, int nglyphs)
    {
        OutputList.addLast(new CFFFont.MarkerItem(fdselectRef));
        OutputList.addLast(new CFFFont.UInt8Item('\003'));
        OutputList.addLast(new CFFFont.UInt16Item('\001'));
        OutputList.addLast(new CFFFont.UInt16Item('\0'));
        OutputList.addLast(new CFFFont.UInt8Item('\0'));
        OutputList.addLast(new CFFFont.UInt16Item((char)nglyphs));
    }

    protected void CreateCharset(CFFFont.OffsetItem charsetRef, int nglyphs)
    {
        OutputList.addLast(new CFFFont.MarkerItem(charsetRef));
        OutputList.addLast(new CFFFont.UInt8Item('\002'));
        OutputList.addLast(new CFFFont.UInt16Item('\001'));
        OutputList.addLast(new CFFFont.UInt16Item((char)(nglyphs - 1)));
    }

    protected void CreateFDArray(CFFFont.OffsetItem fdarrayRef, CFFFont.OffsetItem privateRef, int Font)
    {
        OutputList.addLast(new CFFFont.MarkerItem(fdarrayRef));
        BuildIndexHeader(1, 1, 1);
        CFFFont.OffsetItem privateIndex1Ref = new CFFFont.IndexOffsetItem(1);
        OutputList.addLast(privateIndex1Ref);
        CFFFont.IndexBaseItem privateBase = new CFFFont.IndexBaseItem();
        OutputList.addLast(privateBase);
        int NewSize = fonts[Font].privateLength;
        int OrgSubrsOffsetSize = CalcSubrOffsetSize(fonts[Font].privateOffset, fonts[Font].privateLength);
        if(OrgSubrsOffsetSize != 0)
            NewSize += 5 - OrgSubrsOffsetSize;
        OutputList.addLast(new CFFFont.DictNumberItem(NewSize));
        OutputList.addLast(privateRef);
        OutputList.addLast(new CFFFont.UInt8Item('\022'));
        OutputList.addLast(new CFFFont.IndexMarkerItem(privateIndex1Ref, privateBase));
    }

    void Reconstruct(int Font)
    {
        CFFFont.OffsetItem fdPrivate[] = new CFFFont.DictOffsetItem[fonts[Font].FDArrayOffsets.length - 1];
        CFFFont.IndexBaseItem fdPrivateBase[] = new CFFFont.IndexBaseItem[fonts[Font].fdprivateOffsets.length];
        CFFFont.OffsetItem fdSubrs[] = new CFFFont.DictOffsetItem[fonts[Font].fdprivateOffsets.length];
        ReconstructFDArray(Font, fdPrivate);
        ReconstructPrivateDict(Font, fdPrivate, fdPrivateBase, fdSubrs);
        ReconstructPrivateSubrs(Font, fdPrivateBase, fdSubrs);
    }

    void ReconstructFDArray(int Font, CFFFont.OffsetItem fdPrivate[])
    {
        BuildIndexHeader(fonts[Font].FDArrayCount, fonts[Font].FDArrayOffsize, 1);
        CFFFont.OffsetItem fdOffsets[] = new CFFFont.IndexOffsetItem[fonts[Font].FDArrayOffsets.length - 1];
        for(int i = 0; i < fonts[Font].FDArrayOffsets.length - 1; i++)
        {
            fdOffsets[i] = new CFFFont.IndexOffsetItem(fonts[Font].FDArrayOffsize);
            OutputList.addLast(fdOffsets[i]);
        }

        CFFFont.IndexBaseItem fdArrayBase = new CFFFont.IndexBaseItem();
        OutputList.addLast(fdArrayBase);
        for(int k = 0; k < fonts[Font].FDArrayOffsets.length - 1; k++)
        {
            seek(fonts[Font].FDArrayOffsets[k]);
            while(getPosition() < fonts[Font].FDArrayOffsets[k + 1]) 
            {
                int p1 = getPosition();
                getDictItem();
                int p2 = getPosition();
                if(key == "Private")
                {
                    int NewSize = ((Integer)args[0]).intValue();
                    int OrgSubrsOffsetSize = CalcSubrOffsetSize(fonts[Font].fdprivateOffsets[k], fonts[Font].fdprivateLengths[k]);
                    if(OrgSubrsOffsetSize != 0)
                        NewSize += 5 - OrgSubrsOffsetSize;
                    OutputList.addLast(new CFFFont.DictNumberItem(NewSize));
                    fdPrivate[k] = new CFFFont.DictOffsetItem();
                    OutputList.addLast(fdPrivate[k]);
                    OutputList.addLast(new CFFFont.UInt8Item('\022'));
                    seek(p2);
                } else
                {
                    OutputList.addLast(new CFFFont.RangeItem(buf, p1, p2 - p1));
                }
            }
            OutputList.addLast(new CFFFont.IndexMarkerItem(fdOffsets[k], fdArrayBase));
        }

    }

    void ReconstructPrivateDict(int Font, CFFFont.OffsetItem fdPrivate[], CFFFont.IndexBaseItem fdPrivateBase[], CFFFont.OffsetItem fdSubrs[])
    {
        for(int i = 0; i < fonts[Font].fdprivateOffsets.length; i++)
        {
            OutputList.addLast(new CFFFont.MarkerItem(fdPrivate[i]));
            fdPrivateBase[i] = new CFFFont.IndexBaseItem();
            OutputList.addLast(fdPrivateBase[i]);
            seek(fonts[Font].fdprivateOffsets[i]);
            while(getPosition() < fonts[Font].fdprivateOffsets[i] + fonts[Font].fdprivateLengths[i]) 
            {
                int p1 = getPosition();
                getDictItem();
                int p2 = getPosition();
                if(key == "Subrs")
                {
                    fdSubrs[i] = new CFFFont.DictOffsetItem();
                    OutputList.addLast(fdSubrs[i]);
                    OutputList.addLast(new CFFFont.UInt8Item('\023'));
                } else
                {
                    OutputList.addLast(new CFFFont.RangeItem(buf, p1, p2 - p1));
                }
            }
        }

    }

    void ReconstructPrivateSubrs(int Font, CFFFont.IndexBaseItem fdPrivateBase[], CFFFont.OffsetItem fdSubrs[])
    {
        for(int i = 0; i < fonts[Font].fdprivateLengths.length; i++)
        {
            if(fdSubrs[i] == null || fonts[Font].PrivateSubrsOffset[i] < 0)
                continue;
            OutputList.addLast(new CFFFont.SubrMarkerItem(fdSubrs[i], fdPrivateBase[i]));
            if(NewLSubrsIndex[i] != null)
                OutputList.addLast(new CFFFont.RangeItem(new RandomAccessFileOrArray(NewLSubrsIndex[i]), 0, NewLSubrsIndex[i].length));
        }

    }

    int CalcSubrOffsetSize(int Offset, int Size)
    {
        int OffsetSize = 0;
        seek(Offset);
        do
        {
            if(getPosition() >= Offset + Size)
                break;
            int p1 = getPosition();
            getDictItem();
            int p2 = getPosition();
            if(key == "Subrs")
                OffsetSize = p2 - p1 - 1;
        } while(true);
        return OffsetSize;
    }

    protected int countEntireIndexRange(int indexOffset)
    {
        seek(indexOffset);
        int count = getCard16();
        if(count == 0)
        {
            return 2;
        } else
        {
            int indexOffSize = getCard8();
            seek(indexOffset + 2 + 1 + count * indexOffSize);
            int size = getOffset(indexOffSize) - 1;
            return 3 + (count + 1) * indexOffSize + size;
        }
    }

    void CreateNonCIDPrivate(int Font, CFFFont.OffsetItem Subr)
    {
        seek(fonts[Font].privateOffset);
        while(getPosition() < fonts[Font].privateOffset + fonts[Font].privateLength) 
        {
            int p1 = getPosition();
            getDictItem();
            int p2 = getPosition();
            if(key == "Subrs")
            {
                OutputList.addLast(Subr);
                OutputList.addLast(new CFFFont.UInt8Item('\023'));
            } else
            {
                OutputList.addLast(new CFFFont.RangeItem(buf, p1, p2 - p1));
            }
        }
    }

    void CreateNonCIDSubrs(int Font, CFFFont.IndexBaseItem PrivateBase, CFFFont.OffsetItem Subrs)
    {
        OutputList.addLast(new CFFFont.SubrMarkerItem(Subrs, PrivateBase));
        if(NewSubrsIndexNonCID != null)
            OutputList.addLast(new CFFFont.RangeItem(new RandomAccessFileOrArray(NewSubrsIndexNonCID), 0, NewSubrsIndexNonCID.length));
    }

    static final String SubrsFunctions[] = {
        "RESERVED_0", "hstem", "RESERVED_2", "vstem", "vmoveto", "rlineto", "hlineto", "vlineto", "rrcurveto", "RESERVED_9", 
        "callsubr", "return", "escape", "RESERVED_13", "endchar", "RESERVED_15", "RESERVED_16", "RESERVED_17", "hstemhm", "hintmask", 
        "cntrmask", "rmoveto", "hmoveto", "vstemhm", "rcurveline", "rlinecurve", "vvcurveto", "hhcurveto", "shortint", "callgsubr", 
        "vhcurveto", "hvcurveto"
    };
    static final String SubrsEscapeFuncs[] = {
        "RESERVED_0", "RESERVED_1", "RESERVED_2", "and", "or", "not", "RESERVED_6", "RESERVED_7", "RESERVED_8", "abs", 
        "add", "sub", "div", "RESERVED_13", "neg", "eq", "RESERVED_16", "RESERVED_17", "drop", "RESERVED_19", 
        "put", "get", "ifelse", "random", "mul", "RESERVED_25", "sqrt", "dup", "exch", "index", 
        "roll", "RESERVED_31", "RESERVED_32", "RESERVED_33", "hflex", "flex", "hflex1", "flex1", "RESERVED_REST"
    };
    static final byte ENDCHAR_OP = 14;
    static final byte RETURN_OP = 11;
    HashMap GlyphsUsed;
    ArrayList glyphsInList;
    HashSet FDArrayUsed;
    HashMap hSubrsUsed[];
    ArrayList lSubrsUsed[];
    HashMap hGSubrsUsed;
    ArrayList lGSubrsUsed;
    HashMap hSubrsUsedNonCID;
    ArrayList lSubrsUsedNonCID;
    byte NewLSubrsIndex[][];
    byte NewSubrsIndexNonCID[];
    byte NewGSubrsIndex[];
    byte NewCharStringsIndex[];
    int GBias;
    LinkedList OutputList;
    int NumOfHints;

}
