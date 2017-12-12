// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMapParserEx.java

package co.com.pdf.text.pdf.fonts.cmaps;

import co.com.pdf.text.error_messages.MessageLocalization;
import co.com.pdf.text.pdf.*;
import java.io.IOException;
import java.util.ArrayList;

// Referenced classes of package co.com.pdf.text.pdf.fonts.cmaps:
//            AbstractCMap, CidLocation

public class CMapParserEx
{

    public CMapParserEx()
    {
    }

    public static void parseCid(String cmapName, AbstractCMap cmap, CidLocation location)
        throws IOException
    {
        parseCid(cmapName, cmap, location, 0);
    }

    private static void parseCid(String cmapName, AbstractCMap cmap, CidLocation location, int level)
        throws IOException
    {
        PRTokeniser inp;
        if(level >= 10)
            return;
        inp = location.getLocation(cmapName);
        ArrayList list = new ArrayList();
        PdfContentParser cp = new PdfContentParser(inp);
        int maxExc = 50;
        do
        {
            try
            {
                cp.parse(list);
            }
            catch(Exception ex)
            {
                if(--maxExc < 0)
                    break;
                continue;
            }
            if(list.isEmpty())
                break;
            String last = ((PdfObject)list.get(list.size() - 1)).toString();
            if(level == 0 && list.size() == 3 && last.equals("def"))
            {
                PdfObject key = (PdfObject)list.get(0);
                if(PdfName.REGISTRY.equals(key))
                    cmap.setRegistry(((PdfObject)list.get(1)).toString());
                else
                if(PdfName.ORDERING.equals(key))
                    cmap.setOrdering(((PdfObject)list.get(1)).toString());
                else
                if(CMAPNAME.equals(key))
                    cmap.setName(((PdfObject)list.get(1)).toString());
                else
                if(PdfName.SUPPLEMENT.equals(key))
                    try
                    {
                        cmap.setSupplement(((PdfNumber)list.get(1)).intValue());
                    }
                    catch(Exception ex) { }
            } else
            if((last.equals("endcidchar") || last.equals("endbfchar")) && list.size() >= 3)
            {
                int lmax = list.size() - 2;
                int k = 0;
                while(k < lmax) 
                {
                    if(list.get(k) instanceof PdfString)
                        cmap.addChar((PdfString)list.get(k), (PdfObject)list.get(k + 1));
                    k += 2;
                }
            } else
            if((last.equals("endcidrange") || last.equals("endbfrange")) && list.size() >= 4)
            {
                int lmax = list.size() - 3;
                int k = 0;
                while(k < lmax) 
                {
                    if((list.get(k) instanceof PdfString) && (list.get(k + 1) instanceof PdfString))
                        cmap.addRange((PdfString)list.get(k), (PdfString)list.get(k + 1), (PdfObject)list.get(k + 2));
                    k += 3;
                }
            } else
            if(last.equals("usecmap") && list.size() == 2 && (list.get(0) instanceof PdfName))
                parseCid(PdfName.decodeName(((PdfObject)list.get(0)).toString()), cmap, location, level + 1);
        } while(true);
        inp.close();
        break MISSING_BLOCK_LABEL_551;
        Exception exception;
        exception;
        inp.close();
        throw exception;
    }

    private static void encodeSequence(int size, byte seqs[], char cid, ArrayList planes)
    {
        size--;
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

    private static final PdfName CMAPNAME = new PdfName("CMapName");
    private static final String DEF = "def";
    private static final String ENDCIDRANGE = "endcidrange";
    private static final String ENDCIDCHAR = "endcidchar";
    private static final String ENDBFRANGE = "endbfrange";
    private static final String ENDBFCHAR = "endbfchar";
    private static final String USECMAP = "usecmap";
    private static final int MAXLEVEL = 10;

}
