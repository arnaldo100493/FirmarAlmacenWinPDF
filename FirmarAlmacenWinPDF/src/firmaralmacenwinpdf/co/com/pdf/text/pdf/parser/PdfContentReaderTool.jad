// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfContentReaderTool.java

package co.com.pdf.text.pdf.parser;

import co.com.pdf.text.pdf.*;
import java.io.*;
import java.util.*;

// Referenced classes of package co.com.pdf.text.pdf.parser:
//            LocationTextExtractionStrategy, ContentByteUtils, PdfTextExtractor

public class PdfContentReaderTool
{

    public PdfContentReaderTool()
    {
    }

    public static String getDictionaryDetail(PdfDictionary dic)
    {
        return getDictionaryDetail(dic, 0);
    }

    public static String getDictionaryDetail(PdfDictionary dic, int depth)
    {
        StringBuffer builder = new StringBuffer();
        builder.append('(');
        List subDictionaries = new ArrayList();
        for(Iterator i$ = dic.getKeys().iterator(); i$.hasNext(); builder.append(", "))
        {
            PdfName key = (PdfName)i$.next();
            PdfObject val = dic.getDirectObject(key);
            if(val.isDictionary())
                subDictionaries.add(key);
            builder.append(key);
            builder.append('=');
            builder.append(val);
        }

        builder.setLength(builder.length() - 2);
        builder.append(')');
        PdfName pdfSubDictionaryName;
        for(Iterator i$ = subDictionaries.iterator(); i$.hasNext(); builder.append(getDictionaryDetail(dic.getAsDict(pdfSubDictionaryName), depth + 1)))
        {
            pdfSubDictionaryName = (PdfName)i$.next();
            builder.append('\n');
            for(int i = 0; i < depth + 1; i++)
                builder.append('\t');

            builder.append("Subdictionary ");
            builder.append(pdfSubDictionaryName);
            builder.append(" = ");
        }

        return builder.toString();
    }

    public static String getXObjectDetail(PdfDictionary resourceDic)
        throws IOException
    {
        StringBuilder sb = new StringBuilder();
        PdfDictionary xobjects = resourceDic.getAsDict(PdfName.XOBJECT);
        if(xobjects == null)
            return "No XObjects";
        Iterator i$ = xobjects.getKeys().iterator();
        do
        {
            if(!i$.hasNext())
                break;
            PdfName entryName = (PdfName)i$.next();
            PdfStream xobjectStream = xobjects.getAsStream(entryName);
            sb.append((new StringBuilder()).append("------ ").append(entryName).append(" - subtype = ").append(xobjectStream.get(PdfName.SUBTYPE)).append(" = ").append(xobjectStream.getAsNumber(PdfName.LENGTH)).append(" bytes ------\n").toString());
            if(!xobjectStream.get(PdfName.SUBTYPE).equals(PdfName.IMAGE))
            {
                byte contentBytes[] = ContentByteUtils.getContentBytesFromContentObject(xobjectStream);
                InputStream is = new ByteArrayInputStream(contentBytes);
                int ch;
                while((ch = is.read()) != -1) 
                    sb.append((char)ch);
                sb.append((new StringBuilder()).append("------ ").append(entryName).append(" - subtype = ").append(xobjectStream.get(PdfName.SUBTYPE)).append("End of Content").append("------\n").toString());
            }
        } while(true);
        return sb.toString();
    }

    public static void listContentStreamForPage(PdfReader reader, int pageNum, PrintWriter out)
        throws IOException
    {
        out.println((new StringBuilder()).append("==============Page ").append(pageNum).append("====================").toString());
        out.println("- - - - - Dictionary - - - - - -");
        PdfDictionary pageDictionary = reader.getPageN(pageNum);
        out.println(getDictionaryDetail(pageDictionary));
        out.println("- - - - - XObject Summary - - - - - -");
        out.println(getXObjectDetail(pageDictionary.getAsDict(PdfName.RESOURCES)));
        out.println("- - - - - Content Stream - - - - - -");
        RandomAccessFileOrArray f = reader.getSafeFile();
        byte contentBytes[] = reader.getPageContent(pageNum, f);
        f.close();
        out.flush();
        InputStream is = new ByteArrayInputStream(contentBytes);
        int ch;
        while((ch = is.read()) != -1) 
            out.print((char)ch);
        out.flush();
        out.println("- - - - - Text Extraction - - - - - -");
        String extractedText = PdfTextExtractor.getTextFromPage(reader, pageNum, new LocationTextExtractionStrategy());
        if(extractedText.length() != 0)
            out.println(extractedText);
        else
            out.println((new StringBuilder()).append("No text found on page ").append(pageNum).toString());
        out.println();
    }

    public static void listContentStream(File pdfFile, PrintWriter out)
        throws IOException
    {
        PdfReader reader = new PdfReader(pdfFile.getCanonicalPath());
        int maxPageNum = reader.getNumberOfPages();
        for(int pageNum = 1; pageNum <= maxPageNum; pageNum++)
            listContentStreamForPage(reader, pageNum, out);

    }

    public static void listContentStream(File pdfFile, int pageNum, PrintWriter out)
        throws IOException
    {
        PdfReader reader = new PdfReader(pdfFile.getCanonicalPath());
        listContentStreamForPage(reader, pageNum, out);
    }

    public static void main(String args[])
    {
        if(args.length < 1 || args.length > 3)
        {
            System.out.println("Usage:  PdfContentReaderTool <pdf file> [<output file>|stdout] [<page num>]");
            return;
        }
        try
        {
            PrintWriter writer = new PrintWriter(System.out);
            if(args.length >= 2 && args[1].compareToIgnoreCase("stdout") != 0)
            {
                System.out.println((new StringBuilder()).append("Writing PDF content to ").append(args[1]).toString());
                writer = new PrintWriter(new FileOutputStream(new File(args[1])));
            }
            int pageNum = -1;
            if(args.length >= 3)
                pageNum = Integer.parseInt(args[2]);
            if(pageNum == -1)
                listContentStream(new File(args[0]), writer);
            else
                listContentStream(new File(args[0]), pageNum, writer);
            writer.flush();
            if(args.length >= 2)
            {
                writer.close();
                System.out.println((new StringBuilder()).append("Finished writing content to ").append(args[1]).toString());
            }
        }
        catch(Exception e)
        {
            e.printStackTrace(System.err);
        }
        return;
    }
}
