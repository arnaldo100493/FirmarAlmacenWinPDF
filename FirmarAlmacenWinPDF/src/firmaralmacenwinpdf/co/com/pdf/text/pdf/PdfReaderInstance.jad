// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfReaderInstance.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.error_messages.MessageLocalization;
import java.io.IOException;
import java.util.*;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfImportedPage, PdfDictionary, PRStream, PdfRectangle, 
//            PdfLiteral, PdfNumber, PdfObject, PdfArray, 
//            PdfReader, PdfWriter, PdfName, RandomAccessFileOrArray, 
//            PdfStream

class PdfReaderInstance
{

    PdfReaderInstance(PdfReader reader, PdfWriter writer)
    {
        importedPages = new HashMap();
        visited = new HashSet();
        nextRound = new ArrayList();
        this.reader = reader;
        this.writer = writer;
        file = reader.getSafeFile();
        myXref = new int[reader.getXrefSize()];
    }

    PdfReader getReader()
    {
        return reader;
    }

    PdfImportedPage getImportedPage(int pageNumber)
    {
        if(!reader.isOpenedWithFullPermissions())
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("pdfreader.not.opened.with.owner.password", new Object[0]));
        if(pageNumber < 1 || pageNumber > reader.getNumberOfPages())
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("invalid.page.number.1", pageNumber));
        Integer i = Integer.valueOf(pageNumber);
        PdfImportedPage pageT = (PdfImportedPage)importedPages.get(i);
        if(pageT == null)
        {
            pageT = new PdfImportedPage(this, writer, pageNumber);
            importedPages.put(i, pageT);
        }
        return pageT;
    }

    int getNewObjectNumber(int number, int generation)
    {
        if(myXref[number] == 0)
        {
            myXref[number] = writer.getIndirectReferenceNumber();
            nextRound.add(Integer.valueOf(number));
        }
        return myXref[number];
    }

    RandomAccessFileOrArray getReaderFile()
    {
        return file;
    }

    PdfObject getResources(int pageNumber)
    {
        PdfObject obj = PdfReader.getPdfObjectRelease(reader.getPageNRelease(pageNumber).get(PdfName.RESOURCES));
        return obj;
    }

    PdfStream getFormXObject(int pageNumber, int compressionLevel)
        throws IOException
    {
        PdfDictionary page = reader.getPageNRelease(pageNumber);
        PdfObject contents = PdfReader.getPdfObjectRelease(page.get(PdfName.CONTENTS));
        PdfDictionary dic = new PdfDictionary();
        byte bout[] = null;
        if(contents != null)
        {
            if(contents.isStream())
                dic.putAll((PRStream)contents);
            else
                bout = reader.getPageContent(pageNumber, file);
        } else
        {
            bout = new byte[0];
        }
        dic.put(PdfName.RESOURCES, PdfReader.getPdfObjectRelease(page.get(PdfName.RESOURCES)));
        dic.put(PdfName.TYPE, PdfName.XOBJECT);
        dic.put(PdfName.SUBTYPE, PdfName.FORM);
        PdfImportedPage impPage = (PdfImportedPage)importedPages.get(Integer.valueOf(pageNumber));
        dic.put(PdfName.BBOX, new PdfRectangle(impPage.getBoundingBox()));
        PdfArray matrix = impPage.getMatrix();
        if(matrix == null)
            dic.put(PdfName.MATRIX, IDENTITYMATRIX);
        else
            dic.put(PdfName.MATRIX, matrix);
        dic.put(PdfName.FORMTYPE, ONE);
        PRStream stream;
        if(bout == null)
        {
            stream = new PRStream((PRStream)contents, dic);
        } else
        {
            stream = new PRStream(reader, bout, compressionLevel);
            stream.putAll(dic);
        }
        return stream;
    }

    void writeAllVisited()
        throws IOException
    {
        while(!nextRound.isEmpty()) 
        {
            ArrayList vec = nextRound;
            nextRound = new ArrayList();
            int k = 0;
            while(k < vec.size()) 
            {
                Integer i = (Integer)vec.get(k);
                if(!visited.contains(i))
                {
                    visited.add(i);
                    int n = i.intValue();
                    writer.addToBody(reader.getPdfObjectRelease(n), myXref[n]);
                }
                k++;
            }
        }
    }

    public void writeAllPages()
        throws IOException
    {
        file.reOpen();
        Iterator i$ = importedPages.values().iterator();
        do
        {
            if(!i$.hasNext())
                break;
            Object element = i$.next();
            PdfImportedPage ip = (PdfImportedPage)element;
            if(ip.isToCopy())
            {
                writer.addToBody(ip.getFormXObject(writer.getCompressionLevel()), ip.getIndirectReference());
                ip.setCopied();
            }
        } while(true);
        writeAllVisited();
        try
        {
            file.close();
        }
        catch(Exception e) { }
        break MISSING_BLOCK_LABEL_113;
        Exception exception;
        exception;
        try
        {
            file.close();
        }
        catch(Exception e) { }
        throw exception;
    }

    static final PdfLiteral IDENTITYMATRIX = new PdfLiteral("[1 0 0 1 0 0]");
    static final PdfNumber ONE = new PdfNumber(1);
    int myXref[];
    PdfReader reader;
    RandomAccessFileOrArray file;
    HashMap importedPages;
    PdfWriter writer;
    HashSet visited;
    ArrayList nextRound;

}
