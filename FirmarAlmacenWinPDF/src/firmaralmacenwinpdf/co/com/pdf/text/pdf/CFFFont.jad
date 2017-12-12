// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CFFFont.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.ExceptionConverter;
import java.util.Iterator;
import java.util.LinkedList;

// Referenced classes of package co.com.pdf.text.pdf:
//            RandomAccessFileOrArray

public class CFFFont
{
    protected final class Font
    {

        public String name;
        public String fullName;
        public boolean isCID;
        public int privateOffset;
        public int privateLength;
        public int privateSubrs;
        public int charstringsOffset;
        public int encodingOffset;
        public int charsetOffset;
        public int fdarrayOffset;
        public int fdselectOffset;
        public int fdprivateOffsets[];
        public int fdprivateLengths[];
        public int fdprivateSubrs[];
        public int nglyphs;
        public int nstrings;
        public int CharsetLength;
        public int charstringsOffsets[];
        public int charset[];
        public int FDSelect[];
        public int FDSelectLength;
        public int FDSelectFormat;
        public int CharstringType;
        public int FDArrayCount;
        public int FDArrayOffsize;
        public int FDArrayOffsets[];
        public int PrivateSubrsOffset[];
        public int PrivateSubrsOffsetsArray[][];
        public int SubrsOffsets[];
        final CFFFont this$0;

        protected Font()
        {
            this$0 = CFFFont.this;
            super();
            isCID = false;
            privateOffset = -1;
            privateLength = -1;
            privateSubrs = -1;
            charstringsOffset = -1;
            encodingOffset = -1;
            charsetOffset = -1;
            fdarrayOffset = -1;
            fdselectOffset = -1;
            CharstringType = 2;
        }
    }

    protected static final class MarkerItem extends Item
    {

        public void xref()
        {
            p.set(myOffset);
        }

        OffsetItem p;

        public MarkerItem(OffsetItem pointerToMarker)
        {
            p = pointerToMarker;
        }
    }

    protected static final class DictNumberItem extends Item
    {

        public void increment(int currentOffset[])
        {
            super.increment(currentOffset);
            currentOffset[0] += size;
        }

        public void emit(byte buffer[])
        {
            if(size == 5)
            {
                buffer[myOffset] = 29;
                buffer[myOffset + 1] = (byte)(value >>> 24 & 0xff);
                buffer[myOffset + 2] = (byte)(value >>> 16 & 0xff);
                buffer[myOffset + 3] = (byte)(value >>> 8 & 0xff);
                buffer[myOffset + 4] = (byte)(value >>> 0 & 0xff);
            }
        }

        public final int value;
        public int size;

        public DictNumberItem(int value)
        {
            size = 5;
            this.value = value;
        }
    }

    protected static final class StringItem extends Item
    {

        public void increment(int currentOffset[])
        {
            super.increment(currentOffset);
            currentOffset[0] += s.length();
        }

        public void emit(byte buffer[])
        {
            for(int i = 0; i < s.length(); i++)
                buffer[myOffset + i] = (byte)(s.charAt(i) & 0xff);

        }

        public String s;

        public StringItem(String s)
        {
            this.s = s;
        }
    }

    protected static final class UInt8Item extends Item
    {

        public void increment(int currentOffset[])
        {
            super.increment(currentOffset);
            currentOffset[0]++;
        }

        public void emit(byte buffer[])
        {
            buffer[myOffset + 0] = (byte)(value >>> 0 & 0xff);
        }

        public char value;

        public UInt8Item(char value)
        {
            this.value = value;
        }
    }

    protected static final class UInt16Item extends Item
    {

        public void increment(int currentOffset[])
        {
            super.increment(currentOffset);
            currentOffset[0] += 2;
        }

        public void emit(byte buffer[])
        {
            buffer[myOffset + 0] = (byte)(value >>> 8 & 0xff);
            buffer[myOffset + 1] = (byte)(value >>> 0 & 0xff);
        }

        public char value;

        public UInt16Item(char value)
        {
            this.value = value;
        }
    }

    protected static final class UInt32Item extends Item
    {

        public void increment(int currentOffset[])
        {
            super.increment(currentOffset);
            currentOffset[0] += 4;
        }

        public void emit(byte buffer[])
        {
            buffer[myOffset + 0] = (byte)(value >>> 24 & 0xff);
            buffer[myOffset + 1] = (byte)(value >>> 16 & 0xff);
            buffer[myOffset + 2] = (byte)(value >>> 8 & 0xff);
            buffer[myOffset + 3] = (byte)(value >>> 0 & 0xff);
        }

        public int value;

        public UInt32Item(int value)
        {
            this.value = value;
        }
    }

    protected static final class UInt24Item extends Item
    {

        public void increment(int currentOffset[])
        {
            super.increment(currentOffset);
            currentOffset[0] += 3;
        }

        public void emit(byte buffer[])
        {
            buffer[myOffset + 0] = (byte)(value >>> 16 & 0xff);
            buffer[myOffset + 1] = (byte)(value >>> 8 & 0xff);
            buffer[myOffset + 2] = (byte)(value >>> 0 & 0xff);
        }

        public int value;

        public UInt24Item(int value)
        {
            this.value = value;
        }
    }

    protected static final class DictOffsetItem extends OffsetItem
    {

        public void increment(int currentOffset[])
        {
            super.increment(currentOffset);
            currentOffset[0] += size;
        }

        public void emit(byte buffer[])
        {
            if(size == 5)
            {
                buffer[myOffset] = 29;
                buffer[myOffset + 1] = (byte)(value >>> 24 & 0xff);
                buffer[myOffset + 2] = (byte)(value >>> 16 & 0xff);
                buffer[myOffset + 3] = (byte)(value >>> 8 & 0xff);
                buffer[myOffset + 4] = (byte)(value >>> 0 & 0xff);
            }
        }

        public final int size = 5;

        public DictOffsetItem()
        {
        }
    }

    protected static final class SubrMarkerItem extends Item
    {

        public void xref()
        {
            offItem.set(myOffset - indexBase.myOffset);
        }

        private OffsetItem offItem;
        private IndexBaseItem indexBase;

        public SubrMarkerItem(OffsetItem offItem, IndexBaseItem indexBase)
        {
            this.offItem = offItem;
            this.indexBase = indexBase;
        }
    }

    protected static final class IndexMarkerItem extends Item
    {

        public void xref()
        {
            offItem.set((myOffset - indexBase.myOffset) + 1);
        }

        private OffsetItem offItem;
        private IndexBaseItem indexBase;

        public IndexMarkerItem(OffsetItem offItem, IndexBaseItem indexBase)
        {
            this.offItem = offItem;
            this.indexBase = indexBase;
        }
    }

    protected static final class IndexBaseItem extends Item
    {

        public IndexBaseItem()
        {
        }
    }

    protected static final class IndexOffsetItem extends OffsetItem
    {

        public void increment(int currentOffset[])
        {
            super.increment(currentOffset);
            currentOffset[0] += size;
        }

        public void emit(byte buffer[])
        {
            int i = 0;
            switch(size)
            {
            case 4: // '\004'
                buffer[myOffset + i] = (byte)(value >>> 24 & 0xff);
                i++;
                // fall through

            case 3: // '\003'
                buffer[myOffset + i] = (byte)(value >>> 16 & 0xff);
                i++;
                // fall through

            case 2: // '\002'
                buffer[myOffset + i] = (byte)(value >>> 8 & 0xff);
                i++;
                // fall through

            case 1: // '\001'
                buffer[myOffset + i] = (byte)(value >>> 0 & 0xff);
                i++;
                // fall through

            default:
                return;
            }
        }

        public final int size;

        public IndexOffsetItem(int size, int value)
        {
            this.size = size;
            this.value = value;
        }

        public IndexOffsetItem(int size)
        {
            this.size = size;
        }
    }

    protected static final class RangeItem extends Item
    {

        public void increment(int currentOffset[])
        {
            super.increment(currentOffset);
            currentOffset[0] += length;
        }

        public void emit(byte buffer[])
        {
            try
            {
                buf.seek(offset);
                for(int i = myOffset; i < myOffset + length; i++)
                    buffer[i] = buf.readByte();

            }
            catch(Exception e)
            {
                throw new ExceptionConverter(e);
            }
        }

        public int offset;
        public int length;
        private RandomAccessFileOrArray buf;

        public RangeItem(RandomAccessFileOrArray buf, int offset, int length)
        {
            this.offset = offset;
            this.length = length;
            this.buf = buf;
        }
    }

    protected static abstract class OffsetItem extends Item
    {

        public void set(int offset)
        {
            value = offset;
        }

        public int value;

        protected OffsetItem()
        {
        }
    }

    protected static abstract class Item
    {

        public void increment(int currentOffset[])
        {
            myOffset = currentOffset[0];
        }

        public void emit(byte abyte0[])
        {
        }

        public void xref()
        {
        }

        protected int myOffset;

        protected Item()
        {
            myOffset = -1;
        }
    }


    public String getString(char sid)
    {
        if(sid < standardStrings.length)
            return standardStrings[sid];
        if(sid >= (standardStrings.length + stringOffsets.length) - 1)
            return null;
        int j = sid - standardStrings.length;
        int p = getPosition();
        seek(stringOffsets[j]);
        StringBuffer s = new StringBuffer();
        for(int k = stringOffsets[j]; k < stringOffsets[j + 1]; k++)
            s.append(getCard8());

        seek(p);
        return s.toString();
    }

    char getCard8()
    {
        try
        {
            byte i = buf.readByte();
            return (char)(i & 0xff);
        }
        catch(Exception e)
        {
            throw new ExceptionConverter(e);
        }
    }

    char getCard16()
    {
        try
        {
            return buf.readChar();
        }
        catch(Exception e)
        {
            throw new ExceptionConverter(e);
        }
    }

    int getOffset(int offSize)
    {
        int offset = 0;
        for(int i = 0; i < offSize; i++)
        {
            offset *= 256;
            offset += getCard8();
        }

        return offset;
    }

    void seek(int offset)
    {
        try
        {
            buf.seek(offset);
        }
        catch(Exception e)
        {
            throw new ExceptionConverter(e);
        }
    }

    short getShort()
    {
        try
        {
            return buf.readShort();
        }
        catch(Exception e)
        {
            throw new ExceptionConverter(e);
        }
    }

    int getInt()
    {
        try
        {
            return buf.readInt();
        }
        catch(Exception e)
        {
            throw new ExceptionConverter(e);
        }
    }

    int getPosition()
    {
        try
        {
            return (int)buf.getFilePointer();
        }
        catch(Exception e)
        {
            throw new ExceptionConverter(e);
        }
    }

    int[] getIndex(int nextIndexOffset)
    {
        seek(nextIndexOffset);
        int count = getCard16();
        int offsets[] = new int[count + 1];
        if(count == 0)
        {
            offsets[0] = -1;
            nextIndexOffset += 2;
            return offsets;
        }
        int indexOffSize = getCard8();
        for(int j = 0; j <= count; j++)
            offsets[j] = ((nextIndexOffset + 2 + 1 + (count + 1) * indexOffSize) - 1) + getOffset(indexOffSize);

        return offsets;
    }

    protected void getDictItem()
    {
        for(int i = 0; i < arg_count; i++)
            args[i] = null;

        arg_count = 0;
        key = null;
        boolean gotKey = false;
        do
        {
            if(gotKey)
                break;
            char b0 = getCard8();
            if(b0 == '\035')
            {
                int item = getInt();
                args[arg_count] = Integer.valueOf(item);
                arg_count++;
            } else
            if(b0 == '\034')
            {
                short item = getShort();
                args[arg_count] = Integer.valueOf(item);
                arg_count++;
            } else
            if(b0 >= ' ' && b0 <= '\366')
            {
                byte item = (byte)(b0 - 139);
                args[arg_count] = Integer.valueOf(item);
                arg_count++;
            } else
            if(b0 >= '\367' && b0 <= '\372')
            {
                char b1 = getCard8();
                short item = (short)((b0 - 247) * 256 + b1 + 108);
                args[arg_count] = Integer.valueOf(item);
                arg_count++;
            } else
            if(b0 >= '\373' && b0 <= '\376')
            {
                char b1 = getCard8();
                short item = (short)(-(b0 - 251) * 256 - b1 - 108);
                args[arg_count] = Integer.valueOf(item);
                arg_count++;
            } else
            if(b0 == '\036')
            {
                StringBuilder item = new StringBuilder("");
                boolean done = false;
                char buffer = '\0';
                byte avail = 0;
                int nibble = 0;
                do
                {
                    if(done)
                        break;
                    if(avail == 0)
                    {
                        buffer = getCard8();
                        avail = 2;
                    }
                    if(avail == 1)
                    {
                        nibble = buffer / 16;
                        avail--;
                    }
                    if(avail == 2)
                    {
                        nibble = buffer % 16;
                        avail--;
                    }
                    switch(nibble)
                    {
                    case 10: // '\n'
                        item.append(".");
                        break;

                    case 11: // '\013'
                        item.append("E");
                        break;

                    case 12: // '\f'
                        item.append("E-");
                        break;

                    case 14: // '\016'
                        item.append("-");
                        break;

                    case 15: // '\017'
                        done = true;
                        break;

                    case 13: // '\r'
                    default:
                        if(nibble >= 0 && nibble <= 9)
                        {
                            item.append(String.valueOf(nibble));
                        } else
                        {
                            item.append("<NIBBLE ERROR: ").append(nibble).append('>');
                            done = true;
                        }
                        break;
                    }
                } while(true);
                args[arg_count] = item.toString();
                arg_count++;
            } else
            if(b0 <= '\025')
            {
                gotKey = true;
                if(b0 != '\f')
                    key = operatorNames[b0];
                else
                    key = operatorNames[32 + getCard8()];
            }
        } while(true);
    }

    protected RangeItem getEntireIndexRange(int indexOffset)
    {
        seek(indexOffset);
        int count = getCard16();
        if(count == 0)
        {
            return new RangeItem(buf, indexOffset, 2);
        } else
        {
            int indexOffSize = getCard8();
            seek(indexOffset + 2 + 1 + count * indexOffSize);
            int size = getOffset(indexOffSize) - 1;
            return new RangeItem(buf, indexOffset, 3 + (count + 1) * indexOffSize + size);
        }
    }

    public byte[] getCID(String fontName)
    {
        int j;
        for(j = 0; j < fonts.length && !fontName.equals(fonts[j].name); j++);
        if(j == fonts.length)
            return null;
        LinkedList l = new LinkedList();
        seek(0);
        int major = getCard8();
        int minor = getCard8();
        int hdrSize = getCard8();
        int offSize = getCard8();
        nextIndexOffset = hdrSize;
        l.addLast(new RangeItem(buf, 0, hdrSize));
        int nglyphs = -1;
        int nstrings = -1;
        if(!fonts[j].isCID)
        {
            seek(fonts[j].charstringsOffset);
            nglyphs = getCard16();
            seek(stringIndexOffset);
            nstrings = getCard16() + standardStrings.length;
        }
        l.addLast(new UInt16Item('\001'));
        l.addLast(new UInt8Item('\001'));
        l.addLast(new UInt8Item('\001'));
        l.addLast(new UInt8Item((char)(1 + fonts[j].name.length())));
        l.addLast(new StringItem(fonts[j].name));
        l.addLast(new UInt16Item('\001'));
        l.addLast(new UInt8Item('\002'));
        l.addLast(new UInt16Item('\001'));
        OffsetItem topdictIndex1Ref = new IndexOffsetItem(2);
        l.addLast(topdictIndex1Ref);
        IndexBaseItem topdictBase = new IndexBaseItem();
        l.addLast(topdictBase);
        OffsetItem charsetRef = new DictOffsetItem();
        OffsetItem charstringsRef = new DictOffsetItem();
        OffsetItem fdarrayRef = new DictOffsetItem();
        OffsetItem fdselectRef = new DictOffsetItem();
        if(!fonts[j].isCID)
        {
            l.addLast(new DictNumberItem(nstrings));
            l.addLast(new DictNumberItem(nstrings + 1));
            l.addLast(new DictNumberItem(0));
            l.addLast(new UInt8Item('\f'));
            l.addLast(new UInt8Item('\036'));
            l.addLast(new DictNumberItem(nglyphs));
            l.addLast(new UInt8Item('\f'));
            l.addLast(new UInt8Item('"'));
        }
        l.addLast(fdarrayRef);
        l.addLast(new UInt8Item('\f'));
        l.addLast(new UInt8Item('$'));
        l.addLast(fdselectRef);
        l.addLast(new UInt8Item('\f'));
        l.addLast(new UInt8Item('%'));
        l.addLast(charsetRef);
        l.addLast(new UInt8Item('\017'));
        l.addLast(charstringsRef);
        l.addLast(new UInt8Item('\021'));
        seek(topdictOffsets[j]);
        do
        {
            if(getPosition() >= topdictOffsets[j + 1])
                break;
            int p1 = getPosition();
            getDictItem();
            int p2 = getPosition();
            if(key != "Encoding" && key != "Private" && key != "FDSelect" && key != "FDArray" && key != "charset" && key != "CharStrings")
                l.add(new RangeItem(buf, p1, p2 - p1));
        } while(true);
        l.addLast(new IndexMarkerItem(topdictIndex1Ref, topdictBase));
        if(fonts[j].isCID)
        {
            l.addLast(getEntireIndexRange(stringIndexOffset));
        } else
        {
            String fdFontName = (new StringBuilder()).append(fonts[j].name).append("-OneRange").toString();
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
            l.addLast(new UInt16Item((char)((stringOffsets.length - 1) + 3)));
            l.addLast(new UInt8Item((char)stringsIndexOffSize));
            int arr$[] = stringOffsets;
            int len$ = arr$.length;
            for(int i$ = 0; i$ < len$; i$++)
            {
                int stringOffset = arr$[i$];
                l.addLast(new IndexOffsetItem(stringsIndexOffSize, stringOffset - stringsBaseOffset));
            }

            int currentStringsOffset = stringOffsets[stringOffsets.length - 1] - stringsBaseOffset;
            currentStringsOffset += "Adobe".length();
            l.addLast(new IndexOffsetItem(stringsIndexOffSize, currentStringsOffset));
            currentStringsOffset += "Identity".length();
            l.addLast(new IndexOffsetItem(stringsIndexOffSize, currentStringsOffset));
            currentStringsOffset += fdFontName.length();
            l.addLast(new IndexOffsetItem(stringsIndexOffSize, currentStringsOffset));
            l.addLast(new RangeItem(buf, stringOffsets[0], origStringsLen));
            l.addLast(new StringItem(extraStrings));
        }
        l.addLast(getEntireIndexRange(gsubrIndexOffset));
        if(!fonts[j].isCID)
        {
            l.addLast(new MarkerItem(fdselectRef));
            l.addLast(new UInt8Item('\003'));
            l.addLast(new UInt16Item('\001'));
            l.addLast(new UInt16Item('\0'));
            l.addLast(new UInt8Item('\0'));
            l.addLast(new UInt16Item((char)nglyphs));
            l.addLast(new MarkerItem(charsetRef));
            l.addLast(new UInt8Item('\002'));
            l.addLast(new UInt16Item('\001'));
            l.addLast(new UInt16Item((char)(nglyphs - 1)));
            l.addLast(new MarkerItem(fdarrayRef));
            l.addLast(new UInt16Item('\001'));
            l.addLast(new UInt8Item('\001'));
            l.addLast(new UInt8Item('\001'));
            OffsetItem privateIndex1Ref = new IndexOffsetItem(1);
            l.addLast(privateIndex1Ref);
            IndexBaseItem privateBase = new IndexBaseItem();
            l.addLast(privateBase);
            l.addLast(new DictNumberItem(fonts[j].privateLength));
            OffsetItem privateRef = new DictOffsetItem();
            l.addLast(privateRef);
            l.addLast(new UInt8Item('\022'));
            l.addLast(new IndexMarkerItem(privateIndex1Ref, privateBase));
            l.addLast(new MarkerItem(privateRef));
            l.addLast(new RangeItem(buf, fonts[j].privateOffset, fonts[j].privateLength));
            if(fonts[j].privateSubrs >= 0)
                l.addLast(getEntireIndexRange(fonts[j].privateSubrs));
        }
        l.addLast(new MarkerItem(charstringsRef));
        l.addLast(getEntireIndexRange(fonts[j].charstringsOffset));
        int currentOffset[] = new int[1];
        currentOffset[0] = 0;
        Item item;
        for(Iterator listIter = l.iterator(); listIter.hasNext(); item.increment(currentOffset))
            item = (Item)listIter.next();

        Item item;
        for(Iterator listIter = l.iterator(); listIter.hasNext(); item.xref())
            item = (Item)listIter.next();

        int size = currentOffset[0];
        byte b[] = new byte[size];
        Item item;
        for(Iterator listIter = l.iterator(); listIter.hasNext(); item.emit(b))
            item = (Item)listIter.next();

        return b;
    }

    public boolean isCID(String fontName)
    {
        for(int j = 0; j < fonts.length; j++)
            if(fontName.equals(fonts[j].name))
                return fonts[j].isCID;

        return false;
    }

    public boolean exists(String fontName)
    {
        for(int j = 0; j < fonts.length; j++)
            if(fontName.equals(fonts[j].name))
                return true;

        return false;
    }

    public String[] getNames()
    {
        String names[] = new String[fonts.length];
        for(int i = 0; i < fonts.length; i++)
            names[i] = fonts[i].name;

        return names;
    }

    public CFFFont(RandomAccessFileOrArray inputbuffer)
    {
        int j;
        args = new Object[48];
        arg_count = 0;
        buf = inputbuffer;
        seek(0);
        int major = getCard8();
        int minor = getCard8();
        int hdrSize = getCard8();
        offSize = getCard8();
        nameIndexOffset = hdrSize;
        nameOffsets = getIndex(nameIndexOffset);
        topdictIndexOffset = nameOffsets[nameOffsets.length - 1];
        topdictOffsets = getIndex(topdictIndexOffset);
        stringIndexOffset = topdictOffsets[topdictOffsets.length - 1];
        stringOffsets = getIndex(stringIndexOffset);
        gsubrIndexOffset = stringOffsets[stringOffsets.length - 1];
        gsubrOffsets = getIndex(gsubrIndexOffset);
        fonts = new Font[nameOffsets.length - 1];
        j = 0;
_L5:
        if(j >= nameOffsets.length - 1) goto _L2; else goto _L1
_L1:
        int k;
        fonts[j] = new Font();
        seek(nameOffsets[j]);
        fonts[j].name = "";
        k = nameOffsets[j];
_L4:
        if(k >= nameOffsets[j + 1])
            continue; /* Loop/switch isn't completed */
        new StringBuilder();
        fonts[j];
        JVM INSTR dup_x1 ;
        name;
        append();
        getCard8();
        append();
        toString();
        name;
        k++;
        if(true) goto _L4; else goto _L3
_L3:
        j++;
          goto _L5
_L2:
label0:
        for(j = 0; j < topdictOffsets.length - 1; j++)
        {
            seek(topdictOffsets[j]);
            do
            {
                if(getPosition() >= topdictOffsets[j + 1])
                    break;
                getDictItem();
                if(key == "FullName")
                    fonts[j].fullName = getString((char)((Integer)args[0]).intValue());
                else
                if(key == "ROS")
                    fonts[j].isCID = true;
                else
                if(key == "Private")
                {
                    fonts[j].privateLength = ((Integer)args[0]).intValue();
                    fonts[j].privateOffset = ((Integer)args[1]).intValue();
                } else
                if(key == "charset")
                    fonts[j].charsetOffset = ((Integer)args[0]).intValue();
                else
                if(key == "CharStrings")
                {
                    fonts[j].charstringsOffset = ((Integer)args[0]).intValue();
                    int p = getPosition();
                    fonts[j].charstringsOffsets = getIndex(fonts[j].charstringsOffset);
                    seek(p);
                } else
                if(key == "FDArray")
                    fonts[j].fdarrayOffset = ((Integer)args[0]).intValue();
                else
                if(key == "FDSelect")
                    fonts[j].fdselectOffset = ((Integer)args[0]).intValue();
                else
                if(key == "CharstringType")
                    fonts[j].CharstringType = ((Integer)args[0]).intValue();
            } while(true);
            if(fonts[j].privateOffset >= 0)
            {
                seek(fonts[j].privateOffset);
                do
                {
                    if(getPosition() >= fonts[j].privateOffset + fonts[j].privateLength)
                        break;
                    getDictItem();
                    if(key == "Subrs")
                        fonts[j].privateSubrs = ((Integer)args[0]).intValue() + fonts[j].privateOffset;
                } while(true);
            }
            if(fonts[j].fdarrayOffset < 0)
                continue;
            int fdarrayOffsets[] = getIndex(fonts[j].fdarrayOffset);
            fonts[j].fdprivateOffsets = new int[fdarrayOffsets.length - 1];
            fonts[j].fdprivateLengths = new int[fdarrayOffsets.length - 1];
            int k = 0;
            do
            {
                if(k >= fdarrayOffsets.length - 1)
                    continue label0;
                seek(fdarrayOffsets[k]);
                do
                {
                    if(getPosition() >= fdarrayOffsets[k + 1])
                        break;
                    getDictItem();
                    if(key == "Private")
                    {
                        fonts[j].fdprivateLengths[k] = ((Integer)args[0]).intValue();
                        fonts[j].fdprivateOffsets[k] = ((Integer)args[1]).intValue();
                    }
                } while(true);
                k++;
            } while(true);
        }

        return;
    }

    void ReadEncoding(int nextIndexOffset)
    {
        seek(nextIndexOffset);
        int format = getCard8();
    }

    static final String operatorNames[] = {
        "version", "Notice", "FullName", "FamilyName", "Weight", "FontBBox", "BlueValues", "OtherBlues", "FamilyBlues", "FamilyOtherBlues", 
        "StdHW", "StdVW", "UNKNOWN_12", "UniqueID", "XUID", "charset", "Encoding", "CharStrings", "Private", "Subrs", 
        "defaultWidthX", "nominalWidthX", "UNKNOWN_22", "UNKNOWN_23", "UNKNOWN_24", "UNKNOWN_25", "UNKNOWN_26", "UNKNOWN_27", "UNKNOWN_28", "UNKNOWN_29", 
        "UNKNOWN_30", "UNKNOWN_31", "Copyright", "isFixedPitch", "ItalicAngle", "UnderlinePosition", "UnderlineThickness", "PaintType", "CharstringType", "FontMatrix", 
        "StrokeWidth", "BlueScale", "BlueShift", "BlueFuzz", "StemSnapH", "StemSnapV", "ForceBold", "UNKNOWN_12_15", "UNKNOWN_12_16", "LanguageGroup", 
        "ExpansionFactor", "initialRandomSeed", "SyntheticBase", "PostScript", "BaseFontName", "BaseFontBlend", "UNKNOWN_12_24", "UNKNOWN_12_25", "UNKNOWN_12_26", "UNKNOWN_12_27", 
        "UNKNOWN_12_28", "UNKNOWN_12_29", "ROS", "CIDFontVersion", "CIDFontRevision", "CIDFontType", "CIDCount", "UIDBase", "FDArray", "FDSelect", 
        "FontName"
    };
    static final String standardStrings[] = {
        ".notdef", "space", "exclam", "quotedbl", "numbersign", "dollar", "percent", "ampersand", "quoteright", "parenleft", 
        "parenright", "asterisk", "plus", "comma", "hyphen", "period", "slash", "zero", "one", "two", 
        "three", "four", "five", "six", "seven", "eight", "nine", "colon", "semicolon", "less", 
        "equal", "greater", "question", "at", "A", "B", "C", "D", "E", "F", 
        "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", 
        "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", 
        "bracketleft", "backslash", "bracketright", "asciicircum", "underscore", "quoteleft", "a", "b", "c", "d", 
        "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", 
        "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", 
        "y", "z", "braceleft", "bar", "braceright", "asciitilde", "exclamdown", "cent", "sterling", "fraction", 
        "yen", "florin", "section", "currency", "quotesingle", "quotedblleft", "guillemotleft", "guilsinglleft", "guilsinglright", "fi", 
        "fl", "endash", "dagger", "daggerdbl", "periodcentered", "paragraph", "bullet", "quotesinglbase", "quotedblbase", "quotedblright", 
        "guillemotright", "ellipsis", "perthousand", "questiondown", "grave", "acute", "circumflex", "tilde", "macron", "breve", 
        "dotaccent", "dieresis", "ring", "cedilla", "hungarumlaut", "ogonek", "caron", "emdash", "AE", "ordfeminine", 
        "Lslash", "Oslash", "OE", "ordmasculine", "ae", "dotlessi", "lslash", "oslash", "oe", "germandbls", 
        "onesuperior", "logicalnot", "mu", "trademark", "Eth", "onehalf", "plusminus", "Thorn", "onequarter", "divide", 
        "brokenbar", "degree", "thorn", "threequarters", "twosuperior", "registered", "minus", "eth", "multiply", "threesuperior", 
        "copyright", "Aacute", "Acircumflex", "Adieresis", "Agrave", "Aring", "Atilde", "Ccedilla", "Eacute", "Ecircumflex", 
        "Edieresis", "Egrave", "Iacute", "Icircumflex", "Idieresis", "Igrave", "Ntilde", "Oacute", "Ocircumflex", "Odieresis", 
        "Ograve", "Otilde", "Scaron", "Uacute", "Ucircumflex", "Udieresis", "Ugrave", "Yacute", "Ydieresis", "Zcaron", 
        "aacute", "acircumflex", "adieresis", "agrave", "aring", "atilde", "ccedilla", "eacute", "ecircumflex", "edieresis", 
        "egrave", "iacute", "icircumflex", "idieresis", "igrave", "ntilde", "oacute", "ocircumflex", "odieresis", "ograve", 
        "otilde", "scaron", "uacute", "ucircumflex", "udieresis", "ugrave", "yacute", "ydieresis", "zcaron", "exclamsmall", 
        "Hungarumlautsmall", "dollaroldstyle", "dollarsuperior", "ampersandsmall", "Acutesmall", "parenleftsuperior", "parenrightsuperior", "twodotenleader", "onedotenleader", "zerooldstyle", 
        "oneoldstyle", "twooldstyle", "threeoldstyle", "fouroldstyle", "fiveoldstyle", "sixoldstyle", "sevenoldstyle", "eightoldstyle", "nineoldstyle", "commasuperior", 
        "threequartersemdash", "periodsuperior", "questionsmall", "asuperior", "bsuperior", "centsuperior", "dsuperior", "esuperior", "isuperior", "lsuperior", 
        "msuperior", "nsuperior", "osuperior", "rsuperior", "ssuperior", "tsuperior", "ff", "ffi", "ffl", "parenleftinferior", 
        "parenrightinferior", "Circumflexsmall", "hyphensuperior", "Gravesmall", "Asmall", "Bsmall", "Csmall", "Dsmall", "Esmall", "Fsmall", 
        "Gsmall", "Hsmall", "Ismall", "Jsmall", "Ksmall", "Lsmall", "Msmall", "Nsmall", "Osmall", "Psmall", 
        "Qsmall", "Rsmall", "Ssmall", "Tsmall", "Usmall", "Vsmall", "Wsmall", "Xsmall", "Ysmall", "Zsmall", 
        "colonmonetary", "onefitted", "rupiah", "Tildesmall", "exclamdownsmall", "centoldstyle", "Lslashsmall", "Scaronsmall", "Zcaronsmall", "Dieresissmall", 
        "Brevesmall", "Caronsmall", "Dotaccentsmall", "Macronsmall", "figuredash", "hypheninferior", "Ogoneksmall", "Ringsmall", "Cedillasmall", "questiondownsmall", 
        "oneeighth", "threeeighths", "fiveeighths", "seveneighths", "onethird", "twothirds", "zerosuperior", "foursuperior", "fivesuperior", "sixsuperior", 
        "sevensuperior", "eightsuperior", "ninesuperior", "zeroinferior", "oneinferior", "twoinferior", "threeinferior", "fourinferior", "fiveinferior", "sixinferior", 
        "seveninferior", "eightinferior", "nineinferior", "centinferior", "dollarinferior", "periodinferior", "commainferior", "Agravesmall", "Aacutesmall", "Acircumflexsmall", 
        "Atildesmall", "Adieresissmall", "Aringsmall", "AEsmall", "Ccedillasmall", "Egravesmall", "Eacutesmall", "Ecircumflexsmall", "Edieresissmall", "Igravesmall", 
        "Iacutesmall", "Icircumflexsmall", "Idieresissmall", "Ethsmall", "Ntildesmall", "Ogravesmall", "Oacutesmall", "Ocircumflexsmall", "Otildesmall", "Odieresissmall", 
        "OEsmall", "Oslashsmall", "Ugravesmall", "Uacutesmall", "Ucircumflexsmall", "Udieresissmall", "Yacutesmall", "Thornsmall", "Ydieresissmall", "001.000", 
        "001.001", "001.002", "001.003", "Black", "Bold", "Book", "Light", "Medium", "Regular", "Roman", 
        "Semibold"
    };
    int nextIndexOffset;
    protected String key;
    protected Object args[];
    protected int arg_count;
    protected RandomAccessFileOrArray buf;
    private int offSize;
    protected int nameIndexOffset;
    protected int topdictIndexOffset;
    protected int stringIndexOffset;
    protected int gsubrIndexOffset;
    protected int nameOffsets[];
    protected int topdictOffsets[];
    protected int stringOffsets[];
    protected int gsubrOffsets[];
    protected Font fonts[];

}
