// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CompareTool.java

package co.com.pdf.text.pdf;

import co.com.pdf.xmp.*;
import co.com.pdf.xmp.options.SerializeOptions;
import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import javax.xml.parsers.*;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfReader

public class CompareTool
{
    class ImageNameComparator
        implements Comparator
    {

        public int compare(File f1, File f2)
        {
            String f1Name = f1.getAbsolutePath();
            String f2Name = f2.getAbsolutePath();
            return f1Name.compareTo(f2Name);
        }

        public volatile int compare(Object x0, Object x1)
        {
            return compare((File)x0, (File)x1);
        }

        final CompareTool this$0;

        ImageNameComparator()
        {
            this$0 = CompareTool.this;
            super();
        }
    }

    class CmpPngFileFilter
        implements FileFilter
    {

        public boolean accept(File pathname)
        {
            String ap = pathname.getAbsolutePath();
            boolean b1 = ap.endsWith(".png");
            boolean b2 = ap.contains("cmp_");
            return b1 && b2 && ap.contains(cmpPdfName);
        }

        final CompareTool this$0;

        CmpPngFileFilter()
        {
            this$0 = CompareTool.this;
            super();
        }
    }

    class PngFileFilter
        implements FileFilter
    {

        public boolean accept(File pathname)
        {
            String ap = pathname.getAbsolutePath();
            boolean b1 = ap.endsWith(".png");
            boolean b2 = ap.contains("cmp_");
            return b1 && !b2 && ap.contains(outPdfName);
        }

        final CompareTool this$0;

        PngFileFilter()
        {
            this$0 = CompareTool.this;
            super();
        }
    }


    public CompareTool(String outPdf, String cmpPdf)
    {
        gsParams = " -dNOPAUSE -dBATCH -sDEVICE=png16m -r150 -sOutputFile=<outputfile> <inputfile>";
        compareParams = " <image1> <image2> <difference>";
        init(outPdf, cmpPdf);
        gsExec = System.getProperty("gsExec");
        compareExec = System.getProperty("compareExec");
    }

    public String compare(String outPath, String differenceImage)
        throws IOException, InterruptedException
    {
        if(gsExec == null || !(new File(gsExec)).exists())
            return undefinedGsPath;
        File targetDir = new File(outPath);
        if(!targetDir.exists())
        {
            targetDir.mkdir();
        } else
        {
            File imageFiles[] = targetDir.listFiles(new PngFileFilter());
            File arr$[] = imageFiles;
            int len$ = arr$.length;
            for(int i$ = 0; i$ < len$; i$++)
            {
                File file = arr$[i$];
                file.delete();
            }

            File cmpImageFiles[] = targetDir.listFiles(new CmpPngFileFilter());
            arr$ = cmpImageFiles;
            len$ = arr$.length;
            for(int i$ = 0; i$ < len$; i$++)
            {
                File file = arr$[i$];
                file.delete();
            }

        }
        File diffFile = new File(differenceImage);
        if(diffFile.exists())
            diffFile.delete();
        if(targetDir.exists())
        {
            String gsParams = this.gsParams.replace("<outputfile>", (new StringBuilder()).append(outPath).append(cmpImage).toString()).replace("<inputfile>", cmpPdf);
            Process p = Runtime.getRuntime().exec((new StringBuilder()).append(gsExec).append(gsParams).toString());
            BufferedReader bri = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader bre = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String line;
            while((line = bri.readLine()) != null) 
                System.out.println(line);
            bri.close();
            while((line = bre.readLine()) != null) 
                System.out.println(line);
            bre.close();
            if(p.waitFor() == 0)
            {
                gsParams = this.gsParams.replace("<outputfile>", (new StringBuilder()).append(outPath).append(outImage).toString()).replace("<inputfile>", outPdf);
                p = Runtime.getRuntime().exec((new StringBuilder()).append(gsExec).append(gsParams).toString());
                bri = new BufferedReader(new InputStreamReader(p.getInputStream()));
                bre = new BufferedReader(new InputStreamReader(p.getErrorStream()));
                while((line = bri.readLine()) != null) 
                    System.out.println(line);
                bri.close();
                while((line = bre.readLine()) != null) 
                    System.out.println(line);
                bre.close();
                int exitValue = p.waitFor();
                if(exitValue == 0)
                {
                    File imageFiles[] = targetDir.listFiles(new PngFileFilter());
                    File cmpImageFiles[] = targetDir.listFiles(new CmpPngFileFilter());
                    boolean bUnexpectedNumberOfPages = false;
                    if(imageFiles.length != cmpImageFiles.length)
                        bUnexpectedNumberOfPages = true;
                    int cnt = Math.min(imageFiles.length, cmpImageFiles.length);
                    if(cnt < 1)
                        return "No files for comparing!!!\nThe result or sample pdf file is not processed by GhostScript.";
                    Arrays.sort(imageFiles, new ImageNameComparator());
                    Arrays.sort(cmpImageFiles, new ImageNameComparator());
                    String differentPagesFail = null;
                    for(int i = 0; i < cnt; i++)
                    {
                        System.out.print((new StringBuilder()).append("Comparing page ").append(Integer.toString(i + 1)).append(" (").append(imageFiles[i].getAbsolutePath()).append(")...").toString());
                        FileInputStream is1 = new FileInputStream(imageFiles[i]);
                        FileInputStream is2 = new FileInputStream(cmpImageFiles[i]);
                        boolean cmpResult = compareStreams(is1, is2);
                        is1.close();
                        is2.close();
                        if(!cmpResult)
                        {
                            if((new File(compareExec)).exists())
                            {
                                String compareParams = this.compareParams.replace("<image1>", imageFiles[i].getAbsolutePath()).replace("<image2>", cmpImageFiles[i].getAbsolutePath()).replace("<difference>", (new StringBuilder()).append(differenceImage).append(Integer.toString(i + 1)).append(".png").toString());
                                p = Runtime.getRuntime().exec((new StringBuilder()).append(compareExec).append(compareParams).toString());
                                bre = new BufferedReader(new InputStreamReader(p.getErrorStream()));
                                while((line = bre.readLine()) != null) 
                                    System.out.println(line);
                                bre.close();
                                int cmpExitValue = p.waitFor();
                                if(cmpExitValue == 0)
                                {
                                    if(differentPagesFail == null)
                                    {
                                        differentPagesFail = differentPages.replace("<filename>", outPdf).replace("<pagenumber>", Integer.toString(i + 1));
                                        differentPagesFail = (new StringBuilder()).append(differentPagesFail).append("\nPlease, examine ").append(differenceImage).append(Integer.toString(i + 1)).append(".png for more details.").toString();
                                    } else
                                    {
                                        differentPagesFail = (new StringBuilder()).append("File ").append(outPdf).append(" differs.\nPlease, examine difference images for more details.").toString();
                                    }
                                } else
                                {
                                    differentPagesFail = differentPages.replace("<filename>", outPdf).replace("<pagenumber>", Integer.toString(i + 1));
                                }
                            } else
                            {
                                differentPagesFail = differentPages.replace("<filename>", outPdf).replace("<pagenumber>", Integer.toString(i + 1));
                                differentPagesFail = (new StringBuilder()).append(differentPagesFail).append("\nYou can optionally specify path to ImageMagick compare tool (e.g. -DcompareExec=\"C:/Program Files/ImageMagick-6.5.4-2/compare.exe\") to visualize differences.").toString();
                                break;
                            }
                            System.out.println(differentPagesFail);
                        } else
                        {
                            System.out.println("done.");
                        }
                    }

                    if(differentPagesFail != null)
                        return differentPagesFail;
                    if(bUnexpectedNumberOfPages)
                        return (new StringBuilder()).append(unexpectedNumberOfPages.replace("<filename>", outPdf)).append("\n").append(differentPagesFail).toString();
                    else
                        return null;
                } else
                {
                    return gsFailed.replace("<filename>", outPdf);
                }
            } else
            {
                return gsFailed.replace("<filename>", cmpPdf);
            }
        } else
        {
            return cannotOpenTargetDirectory.replace("<filename>", outPdf);
        }
    }

    public String compare(String outPdf, String cmpPdf, String outPath, String differenceImage)
        throws IOException, InterruptedException
    {
        init(outPdf, cmpPdf);
        return compare(outPath, differenceImage);
    }

    public String compareXmp()
    {
        return compareXmp(false);
    }

    public String compareXmp(boolean ignoreDateAndProducerProperties)
    {
        PdfReader cmpReader;
        PdfReader outReader;
        cmpReader = null;
        outReader = null;
        String s1;
        cmpReader = new PdfReader(cmpPdf);
        outReader = new PdfReader(outPdf);
        byte cmpBytes[] = cmpReader.getMetadata();
        byte outBytes[] = outReader.getMetadata();
        if(ignoreDateAndProducerProperties)
        {
            co.com.pdf.xmp.XMPMeta xmpMeta = XMPMetaFactory.parseFromBuffer(cmpBytes);
            XMPUtils.removeProperties(xmpMeta, "http://ns.adobe.com/xap/1.0/", "CreateDate", true, true);
            XMPUtils.removeProperties(xmpMeta, "http://ns.adobe.com/xap/1.0/", "ModifyDate", true, true);
            XMPUtils.removeProperties(xmpMeta, "http://ns.adobe.com/xap/1.0/", "MetadataDate", true, true);
            XMPUtils.removeProperties(xmpMeta, "http://ns.adobe.com/pdf/1.3/", "Producer", true, true);
            cmpBytes = XMPMetaFactory.serializeToBuffer(xmpMeta, new SerializeOptions(8192));
            xmpMeta = XMPMetaFactory.parseFromBuffer(outBytes);
            XMPUtils.removeProperties(xmpMeta, "http://ns.adobe.com/xap/1.0/", "CreateDate", true, true);
            XMPUtils.removeProperties(xmpMeta, "http://ns.adobe.com/xap/1.0/", "ModifyDate", true, true);
            XMPUtils.removeProperties(xmpMeta, "http://ns.adobe.com/xap/1.0/", "MetadataDate", true, true);
            XMPUtils.removeProperties(xmpMeta, "http://ns.adobe.com/pdf/1.3/", "Producer", true, true);
            outBytes = XMPMetaFactory.serializeToBuffer(xmpMeta, new SerializeOptions(8192));
        }
        if(compareXmls(cmpBytes, outBytes))
            break MISSING_BLOCK_LABEL_214;
        s1 = "The XMP packages different!!!";
        if(cmpReader != null)
            cmpReader.close();
        if(outReader != null)
            outReader.close();
        return s1;
        if(cmpReader != null)
            cmpReader.close();
        if(outReader != null)
            outReader.close();
        break MISSING_BLOCK_LABEL_354;
        XMPException xmpExc;
        xmpExc;
        String s = "XMP parsing failure!!!";
        if(cmpReader != null)
            cmpReader.close();
        if(outReader != null)
            outReader.close();
        return s;
        IOException ioExc;
        ioExc;
        s = "XMP parsing failure!!!";
        if(cmpReader != null)
            cmpReader.close();
        if(outReader != null)
            outReader.close();
        return s;
        ParserConfigurationException parseExc;
        parseExc;
        s = "XMP parsing failure!!!";
        if(cmpReader != null)
            cmpReader.close();
        if(outReader != null)
            outReader.close();
        return s;
        parseExc;
        s = "XMP parsing failure!!!";
        if(cmpReader != null)
            cmpReader.close();
        if(outReader != null)
            outReader.close();
        return s;
        Exception exception;
        exception;
        if(cmpReader != null)
            cmpReader.close();
        if(outReader != null)
            outReader.close();
        throw exception;
        return null;
    }

    public boolean compareXmls(byte xml1[], byte xml2[])
        throws ParserConfigurationException, SAXException, IOException
    {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        dbf.setCoalescing(true);
        dbf.setIgnoringElementContentWhitespace(true);
        dbf.setIgnoringComments(true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc1 = db.parse(new ByteArrayInputStream(xml1));
        doc1.normalizeDocument();
        Document doc2 = db.parse(new ByteArrayInputStream(xml2));
        doc2.normalizeDocument();
        return doc2.isEqualNode(doc1);
    }

    private void init(String outPdf, String cmpPdf)
    {
        this.outPdf = outPdf;
        this.cmpPdf = cmpPdf;
        outPdfName = (new File(outPdf)).getName();
        cmpPdfName = (new File(cmpPdf)).getName();
        outImage = (new StringBuilder()).append(outPdfName).append("-%03d.png").toString();
        cmpImage = (new StringBuilder()).append("cmp_").append(cmpPdfName).append("-%03d.png").toString();
    }

    private boolean compareStreams(InputStream is1, InputStream is2)
        throws IOException
    {
        byte buffer1[] = new byte[0x10000];
        byte buffer2[] = new byte[0x10000];
        int len1 = 0;
        int len2 = 0;
        do
        {
            len1 = is1.read(buffer1);
            len2 = is2.read(buffer2);
            if(len1 != len2)
                return false;
            if(!Arrays.equals(buffer1, buffer2))
                return false;
        } while(len1 != -1 && len2 != -1);
        return true;
    }

    private String gsExec;
    private String compareExec;
    private String gsParams;
    private String compareParams;
    private static String cannotOpenTargetDirectory = "Cannot open target directory for <filename>.";
    private static String gsFailed = "GhostScript failed for <filename>.";
    private static String unexpectedNumberOfPages = "Unexpected number of pages for <filename>.";
    private static String differentPages = "File <filename> differs on page <pagenumber>.";
    private static String undefinedGsPath = "Path to GhostScript is not specified. Please use -DgsExec=<path_to_ghostscript> (e.g. -DgsExec=\"C:/Program Files/gs/gs8.64/bin/gswin32c.exe\")";
    private String cmpPdf;
    private String cmpPdfName;
    private String cmpImage;
    private String outPdf;
    private String outPdfName;
    private String outImage;



}
