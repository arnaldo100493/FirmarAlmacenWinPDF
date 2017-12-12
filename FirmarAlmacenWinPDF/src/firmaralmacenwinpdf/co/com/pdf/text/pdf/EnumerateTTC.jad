// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EnumerateTTC.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.DocumentException;
import co.com.pdf.text.error_messages.MessageLocalization;
import java.io.IOException;
import java.util.HashMap;

// Referenced classes of package co.com.pdf.text.pdf:
//            TrueTypeFont, RandomAccessFileOrArray

class EnumerateTTC extends TrueTypeFont
{

    EnumerateTTC(String ttcFile)
        throws DocumentException, IOException
    {
        fileName = ttcFile;
        rf = new RandomAccessFileOrArray(ttcFile);
        findNames();
    }

    EnumerateTTC(byte ttcArray[])
        throws DocumentException, IOException
    {
        fileName = "Byte array TTC";
        rf = new RandomAccessFileOrArray(ttcArray);
        findNames();
    }

    void findNames()
        throws DocumentException, IOException
    {
        tables = new HashMap();
        String mainTag = readStandardString(4);
        if(!mainTag.equals("ttcf"))
            throw new DocumentException(MessageLocalization.getComposedMessage("1.is.not.a.valid.ttc.file", new Object[] {
                fileName
            }));
        rf.skipBytes(4);
        int dirCount = rf.readInt();
        names = new String[dirCount];
        int dirPos = (int)rf.getFilePointer();
        for(int dirIdx = 0; dirIdx < dirCount; dirIdx++)
        {
            tables.clear();
            rf.seek(dirPos);
            rf.skipBytes(dirIdx * 4);
            directoryOffset = rf.readInt();
            rf.seek(directoryOffset);
            if(rf.readInt() != 0x10000)
                throw new DocumentException(MessageLocalization.getComposedMessage("1.is.not.a.valid.ttf.file", new Object[] {
                    fileName
                }));
            int num_tables = rf.readUnsignedShort();
            rf.skipBytes(6);
            for(int k = 0; k < num_tables; k++)
            {
                String tag = readStandardString(4);
                rf.skipBytes(4);
                int table_location[] = new int[2];
                table_location[0] = rf.readInt();
                table_location[1] = rf.readInt();
                tables.put(tag, table_location);
            }

            names[dirIdx] = getBaseFont();
        }

        if(rf != null)
            rf.close();
        break MISSING_BLOCK_LABEL_323;
        Exception exception;
        exception;
        if(rf != null)
            rf.close();
        throw exception;
    }

    String[] getNames()
    {
        return names;
    }

    protected String names[];
}
