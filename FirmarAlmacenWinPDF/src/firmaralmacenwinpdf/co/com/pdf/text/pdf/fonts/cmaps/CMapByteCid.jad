// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMapByteCid.java

package co.com.pdf.text.pdf.fonts.cmaps;

import co.com.pdf.text.error_messages.MessageLocalization;
import co.com.pdf.text.pdf.*;
import java.util.ArrayList;

// Referenced classes of package co.com.pdf.text.pdf.fonts.cmaps:
//            AbstractCMap, CMapSequence

public class CMapByteCid extends AbstractCMap
{

    public CMapByteCid()
    {
        planes = new ArrayList();
        planes.add(new char[256]);
    }

    void addChar(PdfString mark, PdfObject code)
    {
        if(!(code instanceof PdfNumber))
        {
            return;
        } else
        {
            encodeSequence(decodeStringToByte(mark), (char)((PdfNumber)code).intValue());
            return;
        }
    }

    private void encodeSequence(byte seqs[], char cid)
    {
        int size = seqs.length - 1;
        int nextPlane = 0;
        for(int idx = 0; idx < size; idx++)
        {
            char plane[] = (char[])planes.get(nextPlane);
            int one = seqs[idx] & 0xff;
            char c = plane[one];
            if(c != 0 && (c & 0x8000) == 0)
                throw new RuntimeException(MessageLocalization.getComposedMessage("inconsistent.mapping", new Object[0]));
            if(c == 0)
            {
                planes.add(new char[256]);
                c = (char)(planes.size() - 1 | 0x8000);
                plane[one] = c;
            }
            nextPlane = c & 0x7fff;
        }

        char plane[] = (char[])planes.get(nextPlane);
        int one = seqs[size] & 0xff;
        char c = plane[one];
        if((c & 0x8000) != 0)
        {
            throw new RuntimeException(MessageLocalization.getComposedMessage("inconsistent.mapping", new Object[0]));
        } else
        {
            plane[one] = cid;
            return;
        }
    }

    public int decodeSingle(CMapSequence seq)
    {
        int end = seq.off + seq.len;
        int cid;
        for(int currentPlane = 0; seq.off < end; currentPlane = cid & 0x7fff)
        {
            int one = seq.seq[seq.off++] & 0xff;
            seq.len--;
            char plane[] = (char[])planes.get(currentPlane);
            cid = plane[one];
            if((cid & 0x8000) == 0)
                return cid;
        }

        return -1;
    }

    public String decodeSequence(CMapSequence seq)
    {
        StringBuilder sb = new StringBuilder();
        for(int cid = 0; (cid = decodeSingle(seq)) >= 0;)
            sb.append((char)cid);

        return sb.toString();
    }

    private ArrayList planes;
}
