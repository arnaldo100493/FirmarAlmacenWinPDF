// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfLister.java

package co.com.pdf.text.pdf;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.Set;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfArray, PdfDictionary, PdfName, PdfObject, 
//            PRStream, PdfReaderInstance, PdfImportedPage, PdfReader

public class PdfLister
{

    public PdfLister(PrintStream out)
    {
        this.out = out;
    }

    public void listAnyObject(PdfObject object)
    {
        switch(object.type())
        {
        case 5: // '\005'
            listArray((PdfArray)object);
            break;

        case 6: // '\006'
            listDict((PdfDictionary)object);
            break;

        case 3: // '\003'
            out.println((new StringBuilder()).append("(").append(object.toString()).append(")").toString());
            break;

        case 4: // '\004'
        default:
            out.println(object.toString());
            break;
        }
    }

    public void listDict(PdfDictionary dictionary)
    {
        out.println("<<");
        PdfObject value;
        for(Iterator i$ = dictionary.getKeys().iterator(); i$.hasNext(); listAnyObject(value))
        {
            PdfName key = (PdfName)i$.next();
            value = dictionary.get(key);
            out.print(key.toString());
            out.print(' ');
        }

        out.println(">>");
    }

    public void listArray(PdfArray array)
    {
        out.println('[');
        PdfObject item;
        for(Iterator i = array.listIterator(); i.hasNext(); listAnyObject(item))
            item = (PdfObject)i.next();

        out.println(']');
    }

    public void listStream(PRStream stream, PdfReaderInstance reader)
    {
        try
        {
            listDict(stream);
            out.println("startstream");
            byte b[] = PdfReader.getStreamBytes(stream);
            int len = b.length - 1;
            for(int k = 0; k < len; k++)
                if(b[k] == 13 && b[k + 1] != 10)
                    b[k] = 10;

            out.println(new String(b));
            out.println("endstream");
        }
        catch(IOException e)
        {
            System.err.println((new StringBuilder()).append("I/O exception: ").append(e).toString());
        }
    }

    public void listPage(PdfImportedPage iPage)
    {
        int pageNum = iPage.getPageNumber();
        PdfReaderInstance readerInst = iPage.getPdfReaderInstance();
        PdfReader reader = readerInst.getReader();
        PdfDictionary page = reader.getPageN(pageNum);
        listDict(page);
        PdfObject obj = PdfReader.getPdfObject(page.get(PdfName.CONTENTS));
        if(obj == null)
            return;
        switch(obj.type)
        {
        default:
            break;

        case 7: // '\007'
            listStream((PRStream)obj, readerInst);
            break;

        case 5: // '\005'
            for(Iterator i = ((PdfArray)obj).listIterator(); i.hasNext(); out.println("-----------"))
            {
                PdfObject o = PdfReader.getPdfObject((PdfObject)i.next());
                listStream((PRStream)o, readerInst);
            }

            break;
        }
    }

    PrintStream out;
}
