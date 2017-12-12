// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TIFFDirectory.java

package co.com.pdf.text.pdf.codec;

import co.com.pdf.text.error_messages.MessageLocalization;
import co.com.pdf.text.pdf.RandomAccessFileOrArray;
import java.io.*;
import java.util.*;

// Referenced classes of package co.com.pdf.text.pdf.codec:
//            TIFFField

public class TIFFDirectory
    implements Serializable
{

    TIFFDirectory()
    {
        fieldIndex = new Hashtable();
        IFDOffset = 8L;
        nextIFDOffset = 0L;
    }

    private static boolean isValidEndianTag(int endian)
    {
        return endian == 18761 || endian == 19789;
    }

    public TIFFDirectory(RandomAccessFileOrArray stream, int directory)
        throws IOException
    {
        fieldIndex = new Hashtable();
        IFDOffset = 8L;
        nextIFDOffset = 0L;
        long global_save_offset = stream.getFilePointer();
        stream.seek(0L);
        int endian = stream.readUnsignedShort();
        if(!isValidEndianTag(endian))
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("bad.endianness.tag.not.0x4949.or.0x4d4d", new Object[0]));
        isBigEndian = endian == 19789;
        int magic = readUnsignedShort(stream);
        if(magic != 42)
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("bad.magic.number.should.be.42", new Object[0]));
        long ifd_offset = readUnsignedInt(stream);
        for(int i = 0; i < directory; i++)
        {
            if(ifd_offset == 0L)
                throw new IllegalArgumentException(MessageLocalization.getComposedMessage("directory.number.too.large", new Object[0]));
            stream.seek(ifd_offset);
            int entries = readUnsignedShort(stream);
            stream.skip(12 * entries);
            ifd_offset = readUnsignedInt(stream);
        }

        stream.seek(ifd_offset);
        initialize(stream);
        stream.seek(global_save_offset);
    }

    public TIFFDirectory(RandomAccessFileOrArray stream, long ifd_offset, int directory)
        throws IOException
    {
        fieldIndex = new Hashtable();
        IFDOffset = 8L;
        nextIFDOffset = 0L;
        long global_save_offset = stream.getFilePointer();
        stream.seek(0L);
        int endian = stream.readUnsignedShort();
        if(!isValidEndianTag(endian))
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("bad.endianness.tag.not.0x4949.or.0x4d4d", new Object[0]));
        isBigEndian = endian == 19789;
        stream.seek(ifd_offset);
        for(int dirNum = 0; dirNum < directory; dirNum++)
        {
            int numEntries = readUnsignedShort(stream);
            stream.seek(ifd_offset + (long)(12 * numEntries));
            ifd_offset = readUnsignedInt(stream);
            stream.seek(ifd_offset);
        }

        initialize(stream);
        stream.seek(global_save_offset);
    }

    private void initialize(RandomAccessFileOrArray stream)
        throws IOException
    {
        long nextTagOffset = 0L;
        long maxOffset = stream.length();
        IFDOffset = stream.getFilePointer();
        numEntries = readUnsignedShort(stream);
        fields = new TIFFField[numEntries];
        for(int i = 0; i < numEntries && nextTagOffset < maxOffset; i++)
        {
            int tag = readUnsignedShort(stream);
            int type = readUnsignedShort(stream);
            int count = (int)readUnsignedInt(stream);
            boolean processTag = true;
            nextTagOffset = stream.getFilePointer() + 4L;
            try
            {
                if(count * sizeOfType[type] > 4)
                {
                    long valueOffset = readUnsignedInt(stream);
                    if(valueOffset < maxOffset)
                        stream.seek(valueOffset);
                    else
                        processTag = false;
                }
            }
            catch(ArrayIndexOutOfBoundsException ae)
            {
                processTag = false;
            }
            if(processTag)
            {
                fieldIndex.put(Integer.valueOf(tag), Integer.valueOf(i));
                Object obj = null;
                switch(type)
                {
                default:
                    break;

                case 1: // '\001'
                case 2: // '\002'
                case 6: // '\006'
                case 7: // '\007'
                    byte bvalues[] = new byte[count];
                    stream.readFully(bvalues, 0, count);
                    if(type == 2)
                    {
                        int index = 0;
                        int prevIndex = 0;
                        ArrayList v = new ArrayList();
                        while(index < count) 
                        {
                            while(index < count && bvalues[index++] != 0) ;
                            v.add(new String(bvalues, prevIndex, index - prevIndex));
                            prevIndex = index;
                        }
                        count = v.size();
                        String strings[] = new String[count];
                        for(int c = 0; c < count; c++)
                            strings[c] = (String)v.get(c);

                        obj = strings;
                    } else
                    {
                        obj = bvalues;
                    }
                    break;

                case 3: // '\003'
                    char cvalues[] = new char[count];
                    for(int j = 0; j < count; j++)
                        cvalues[j] = (char)readUnsignedShort(stream);

                    obj = cvalues;
                    break;

                case 4: // '\004'
                    long lvalues[] = new long[count];
                    for(int j = 0; j < count; j++)
                        lvalues[j] = readUnsignedInt(stream);

                    obj = lvalues;
                    break;

                case 5: // '\005'
                    long llvalues[][] = new long[count][2];
                    for(int j = 0; j < count; j++)
                    {
                        llvalues[j][0] = readUnsignedInt(stream);
                        llvalues[j][1] = readUnsignedInt(stream);
                    }

                    obj = llvalues;
                    break;

                case 8: // '\b'
                    short svalues[] = new short[count];
                    for(int j = 0; j < count; j++)
                        svalues[j] = readShort(stream);

                    obj = svalues;
                    break;

                case 9: // '\t'
                    int ivalues[] = new int[count];
                    for(int j = 0; j < count; j++)
                        ivalues[j] = readInt(stream);

                    obj = ivalues;
                    break;

                case 10: // '\n'
                    int iivalues[][] = new int[count][2];
                    for(int j = 0; j < count; j++)
                    {
                        iivalues[j][0] = readInt(stream);
                        iivalues[j][1] = readInt(stream);
                    }

                    obj = iivalues;
                    break;

                case 11: // '\013'
                    float fvalues[] = new float[count];
                    for(int j = 0; j < count; j++)
                        fvalues[j] = readFloat(stream);

                    obj = fvalues;
                    break;

                case 12: // '\f'
                    double dvalues[] = new double[count];
                    for(int j = 0; j < count; j++)
                        dvalues[j] = readDouble(stream);

                    obj = dvalues;
                    break;
                }
                fields[i] = new TIFFField(tag, type, count, obj);
            }
            stream.seek(nextTagOffset);
        }

        try
        {
            nextIFDOffset = readUnsignedInt(stream);
        }
        catch(Exception e)
        {
            nextIFDOffset = 0L;
        }
    }

    public int getNumEntries()
    {
        return numEntries;
    }

    public TIFFField getField(int tag)
    {
        Integer i = (Integer)fieldIndex.get(Integer.valueOf(tag));
        if(i == null)
            return null;
        else
            return fields[i.intValue()];
    }

    public boolean isTagPresent(int tag)
    {
        return fieldIndex.containsKey(Integer.valueOf(tag));
    }

    public int[] getTags()
    {
        int tags[] = new int[fieldIndex.size()];
        Enumeration e = fieldIndex.keys();
        int i = 0;
        while(e.hasMoreElements()) 
            tags[i++] = ((Integer)e.nextElement()).intValue();
        return tags;
    }

    public TIFFField[] getFields()
    {
        return fields;
    }

    public byte getFieldAsByte(int tag, int index)
    {
        Integer i = (Integer)fieldIndex.get(Integer.valueOf(tag));
        byte b[] = fields[i.intValue()].getAsBytes();
        return b[index];
    }

    public byte getFieldAsByte(int tag)
    {
        return getFieldAsByte(tag, 0);
    }

    public long getFieldAsLong(int tag, int index)
    {
        Integer i = (Integer)fieldIndex.get(Integer.valueOf(tag));
        return fields[i.intValue()].getAsLong(index);
    }

    public long getFieldAsLong(int tag)
    {
        return getFieldAsLong(tag, 0);
    }

    public float getFieldAsFloat(int tag, int index)
    {
        Integer i = (Integer)fieldIndex.get(Integer.valueOf(tag));
        return fields[i.intValue()].getAsFloat(index);
    }

    public float getFieldAsFloat(int tag)
    {
        return getFieldAsFloat(tag, 0);
    }

    public double getFieldAsDouble(int tag, int index)
    {
        Integer i = (Integer)fieldIndex.get(Integer.valueOf(tag));
        return fields[i.intValue()].getAsDouble(index);
    }

    public double getFieldAsDouble(int tag)
    {
        return getFieldAsDouble(tag, 0);
    }

    private short readShort(RandomAccessFileOrArray stream)
        throws IOException
    {
        if(isBigEndian)
            return stream.readShort();
        else
            return stream.readShortLE();
    }

    private int readUnsignedShort(RandomAccessFileOrArray stream)
        throws IOException
    {
        if(isBigEndian)
            return stream.readUnsignedShort();
        else
            return stream.readUnsignedShortLE();
    }

    private int readInt(RandomAccessFileOrArray stream)
        throws IOException
    {
        if(isBigEndian)
            return stream.readInt();
        else
            return stream.readIntLE();
    }

    private long readUnsignedInt(RandomAccessFileOrArray stream)
        throws IOException
    {
        if(isBigEndian)
            return stream.readUnsignedInt();
        else
            return stream.readUnsignedIntLE();
    }

    private long readLong(RandomAccessFileOrArray stream)
        throws IOException
    {
        if(isBigEndian)
            return stream.readLong();
        else
            return stream.readLongLE();
    }

    private float readFloat(RandomAccessFileOrArray stream)
        throws IOException
    {
        if(isBigEndian)
            return stream.readFloat();
        else
            return stream.readFloatLE();
    }

    private double readDouble(RandomAccessFileOrArray stream)
        throws IOException
    {
        if(isBigEndian)
            return stream.readDouble();
        else
            return stream.readDoubleLE();
    }

    private static int readUnsignedShort(RandomAccessFileOrArray stream, boolean isBigEndian)
        throws IOException
    {
        if(isBigEndian)
            return stream.readUnsignedShort();
        else
            return stream.readUnsignedShortLE();
    }

    private static long readUnsignedInt(RandomAccessFileOrArray stream, boolean isBigEndian)
        throws IOException
    {
        if(isBigEndian)
            return stream.readUnsignedInt();
        else
            return stream.readUnsignedIntLE();
    }

    public static int getNumDirectories(RandomAccessFileOrArray stream)
        throws IOException
    {
        long pointer;
        boolean isBigEndian;
        long offset;
        int numDirectories;
        pointer = stream.getFilePointer();
        stream.seek(0L);
        int endian = stream.readUnsignedShort();
        if(!isValidEndianTag(endian))
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("bad.endianness.tag.not.0x4949.or.0x4d4d", new Object[0]));
        isBigEndian = endian == 19789;
        int magic = readUnsignedShort(stream, isBigEndian);
        if(magic != 42)
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("bad.magic.number.should.be.42", new Object[0]));
        stream.seek(4L);
        offset = readUnsignedInt(stream, isBigEndian);
        numDirectories = 0;
_L2:
        if(offset == 0L)
            break; /* Loop/switch isn't completed */
        numDirectories++;
        stream.seek(offset);
        int entries = readUnsignedShort(stream, isBigEndian);
        stream.skip(12 * entries);
        offset = readUnsignedInt(stream, isBigEndian);
        if(true) goto _L2; else goto _L1
        EOFException eof;
        eof;
        numDirectories--;
_L1:
        stream.seek(pointer);
        return numDirectories;
    }

    public boolean isBigEndian()
    {
        return isBigEndian;
    }

    public long getIFDOffset()
    {
        return IFDOffset;
    }

    public long getNextIFDOffset()
    {
        return nextIFDOffset;
    }

    private static final long serialVersionUID = 0xfda8e1c12ef4938cL;
    boolean isBigEndian;
    int numEntries;
    TIFFField fields[];
    Hashtable fieldIndex;
    long IFDOffset;
    long nextIFDOffset;
    private static final int sizeOfType[] = {
        0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 
        8, 4, 8
    };

}
