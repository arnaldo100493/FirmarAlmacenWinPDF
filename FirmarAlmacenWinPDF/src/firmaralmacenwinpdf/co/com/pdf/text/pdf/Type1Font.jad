// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Type1Font.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.Document;
import co.com.pdf.text.DocumentException;
import co.com.pdf.text.error_messages.MessageLocalization;
import co.com.pdf.text.io.StreamUtil;
import co.com.pdf.text.pdf.fonts.FontsResourceAnchor;
import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

// Referenced classes of package co.com.pdf.text.pdf:
//            BaseFont, RandomAccessFileOrArray, PdfDictionary, PdfNumber, 
//            PdfRectangle, PdfName, PdfArray, PdfStream, 
//            PdfIndirectReference, PdfObject, PdfIndirectObject, Pfm2afm, 
//            PdfEncodings, GlyphList, PdfWriter

class Type1Font extends BaseFont
{

    Type1Font(String afmFile, String enc, boolean emb, byte ttfAfm[], byte pfb[], boolean forceRead)
        throws DocumentException, IOException
    {
        RandomAccessFileOrArray rf;
        InputStream is;
        byte buf[];
        Weight = "";
        ItalicAngle = 0.0F;
        IsFixedPitch = false;
        llx = -50;
        lly = -200;
        urx = 1000;
        ury = 900;
        UnderlinePosition = -100;
        UnderlineThickness = 50;
        EncodingScheme = "FontSpecific";
        CapHeight = 700;
        XHeight = 480;
        Ascender = 800;
        Descender = -200;
        StdVW = 80;
        CharMetrics = new HashMap();
        KernPairs = new HashMap();
        builtinFont = false;
        if(emb && ttfAfm != null && pfb == null)
            throw new DocumentException(MessageLocalization.getComposedMessage("two.byte.arrays.are.needed.if.the.type1.font.is.embedded", new Object[0]));
        if(emb && ttfAfm != null)
            this.pfb = pfb;
        encoding = enc;
        embedded = emb;
        fileName = afmFile;
        fontType = 0;
        rf = null;
        is = null;
        if(!BuiltinFonts14.containsKey(afmFile))
            break MISSING_BLOCK_LABEL_459;
        embedded = false;
        builtinFont = true;
        buf = new byte[1024];
        if(resourceAnchor == null)
            resourceAnchor = new FontsResourceAnchor();
        is = StreamUtil.getResourceStream((new StringBuilder()).append("co/com/pdf/text/pdf/fonts/").append(afmFile).append(".afm").toString(), resourceAnchor.getClass().getClassLoader());
        if(is == null)
        {
            String msg = MessageLocalization.getComposedMessage("1.not.found.as.resource", new Object[] {
                afmFile
            });
            System.err.println(msg);
            throw new DocumentException(msg);
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        do
        {
            int size = is.read(buf);
            if(size < 0)
                break;
            out.write(buf, 0, size);
        } while(true);
        buf = out.toByteArray();
        if(is != null)
            try
            {
                is.close();
            }
            catch(Exception e) { }
        break MISSING_BLOCK_LABEL_401;
        Exception exception;
        exception;
        if(is != null)
            try
            {
                is.close();
            }
            catch(Exception e) { }
        throw exception;
        rf = new RandomAccessFileOrArray(buf);
        process(rf);
        if(rf != null)
            try
            {
                rf.close();
            }
            catch(Exception e) { }
        break MISSING_BLOCK_LABEL_701;
        Exception exception1;
        exception1;
        if(rf != null)
            try
            {
                rf.close();
            }
            catch(Exception e) { }
        throw exception1;
        if(!afmFile.toLowerCase().endsWith(".afm"))
            break MISSING_BLOCK_LABEL_552;
        if(ttfAfm == null)
            rf = new RandomAccessFileOrArray(afmFile, forceRead, Document.plainRandomAccess);
        else
            rf = new RandomAccessFileOrArray(ttfAfm);
        process(rf);
        if(rf != null)
            try
            {
                rf.close();
            }
            catch(Exception e) { }
        break MISSING_BLOCK_LABEL_701;
        Exception exception2;
        exception2;
        if(rf != null)
            try
            {
                rf.close();
            }
            catch(Exception e) { }
        throw exception2;
        if(!afmFile.toLowerCase().endsWith(".pfm"))
            break MISSING_BLOCK_LABEL_680;
        ByteArrayOutputStream ba = new ByteArrayOutputStream();
        if(ttfAfm == null)
            rf = new RandomAccessFileOrArray(afmFile, forceRead, Document.plainRandomAccess);
        else
            rf = new RandomAccessFileOrArray(ttfAfm);
        Pfm2afm.convert(rf, ba);
        rf.close();
        rf = new RandomAccessFileOrArray(ba.toByteArray());
        process(rf);
        if(rf != null)
            try
            {
                rf.close();
            }
            catch(Exception e) { }
        break MISSING_BLOCK_LABEL_701;
        Exception exception3;
        exception3;
        if(rf != null)
            try
            {
                rf.close();
            }
            catch(Exception e) { }
        throw exception3;
        throw new DocumentException(MessageLocalization.getComposedMessage("1.is.not.an.afm.or.pfm.font.file", new Object[] {
            afmFile
        }));
        EncodingScheme = EncodingScheme.trim();
        if(EncodingScheme.equals("AdobeStandardEncoding") || EncodingScheme.equals("StandardEncoding"))
            fontSpecific = false;
        if(!encoding.startsWith("#"))
            PdfEncodings.convertToBytes(" ", enc);
        createEncoding();
        return;
    }

    int getRawWidth(int c, String name)
    {
        Object metrics[];
        if(name == null)
        {
            metrics = (Object[])CharMetrics.get(Integer.valueOf(c));
        } else
        {
            if(name.equals(".notdef"))
                return 0;
            metrics = (Object[])CharMetrics.get(name);
        }
        if(metrics != null)
            return ((Integer)metrics[1]).intValue();
        else
            return 0;
    }

    public int getKerning(int char1, int char2)
    {
        String first = GlyphList.unicodeToName(char1);
        if(first == null)
            return 0;
        String second = GlyphList.unicodeToName(char2);
        if(second == null)
            return 0;
        Object obj[] = (Object[])KernPairs.get(first);
        if(obj == null)
            return 0;
        for(int k = 0; k < obj.length; k += 2)
            if(second.equals(obj[k]))
                return ((Integer)obj[k + 1]).intValue();

        return 0;
    }

    public void process(RandomAccessFileOrArray rf)
        throws DocumentException, IOException
    {
        boolean isMetrics;
label0:
        {
            isMetrics = false;
            String ident;
label1:
            do
            {
                String line;
                while((line = rf.readLine()) != null) 
                {
                    StringTokenizer tok = new StringTokenizer(line, " ,\n\r\t\f");
                    if(tok.hasMoreTokens())
                    {
                        ident = tok.nextToken();
                        if(ident.equals("FontName"))
                            FontName = tok.nextToken("\377").substring(1);
                        else
                        if(ident.equals("FullName"))
                            FullName = tok.nextToken("\377").substring(1);
                        else
                        if(ident.equals("FamilyName"))
                            FamilyName = tok.nextToken("\377").substring(1);
                        else
                        if(ident.equals("Weight"))
                            Weight = tok.nextToken("\377").substring(1);
                        else
                        if(ident.equals("ItalicAngle"))
                            ItalicAngle = Float.parseFloat(tok.nextToken());
                        else
                        if(ident.equals("IsFixedPitch"))
                            IsFixedPitch = tok.nextToken().equals("true");
                        else
                        if(ident.equals("CharacterSet"))
                            CharacterSet = tok.nextToken("\377").substring(1);
                        else
                        if(ident.equals("FontBBox"))
                        {
                            llx = (int)Float.parseFloat(tok.nextToken());
                            lly = (int)Float.parseFloat(tok.nextToken());
                            urx = (int)Float.parseFloat(tok.nextToken());
                            ury = (int)Float.parseFloat(tok.nextToken());
                        } else
                        if(ident.equals("UnderlinePosition"))
                            UnderlinePosition = (int)Float.parseFloat(tok.nextToken());
                        else
                        if(ident.equals("UnderlineThickness"))
                            UnderlineThickness = (int)Float.parseFloat(tok.nextToken());
                        else
                        if(ident.equals("EncodingScheme"))
                            EncodingScheme = tok.nextToken("\377").substring(1);
                        else
                        if(ident.equals("CapHeight"))
                            CapHeight = (int)Float.parseFloat(tok.nextToken());
                        else
                        if(ident.equals("XHeight"))
                            XHeight = (int)Float.parseFloat(tok.nextToken());
                        else
                        if(ident.equals("Ascender"))
                            Ascender = (int)Float.parseFloat(tok.nextToken());
                        else
                        if(ident.equals("Descender"))
                            Descender = (int)Float.parseFloat(tok.nextToken());
                        else
                        if(ident.equals("StdHW"))
                        {
                            StdHW = (int)Float.parseFloat(tok.nextToken());
                        } else
                        {
                            if(!ident.equals("StdVW"))
                                continue label1;
                            StdVW = (int)Float.parseFloat(tok.nextToken());
                        }
                    }
                }
                break label0;
            } while(!ident.equals("StartCharMetrics"));
            isMetrics = true;
        }
label2:
        {
            if(!isMetrics)
                throw new DocumentException(MessageLocalization.getComposedMessage("missing.startcharmetrics.in.1", new Object[] {
                    fileName
                }));
            String ident;
            do
            {
                String line;
                if((line = rf.readLine()) == null)
                    break;
                StringTokenizer tok = new StringTokenizer(line);
                if(!tok.hasMoreTokens())
                    continue;
                ident = tok.nextToken();
                if(ident.equals("EndCharMetrics"))
                {
                    isMetrics = false;
                    break;
                }
                Integer C = Integer.valueOf(-1);
                Integer WX = Integer.valueOf(250);
                String N = "";
                int B[] = null;
                tok = new StringTokenizer(line, ";");
                do
                {
                    if(!tok.hasMoreTokens())
                        break;
                    StringTokenizer tokc = new StringTokenizer(tok.nextToken());
                    if(tokc.hasMoreTokens())
                    {
                        ident = tokc.nextToken();
                        if(ident.equals("C"))
                            C = Integer.valueOf(tokc.nextToken());
                        else
                        if(ident.equals("WX"))
                            WX = Integer.valueOf((int)Float.parseFloat(tokc.nextToken()));
                        else
                        if(ident.equals("N"))
                            N = tokc.nextToken();
                        else
                        if(ident.equals("B"))
                            B = (new int[] {
                                Integer.parseInt(tokc.nextToken()), Integer.parseInt(tokc.nextToken()), Integer.parseInt(tokc.nextToken()), Integer.parseInt(tokc.nextToken())
                            });
                    }
                } while(true);
                Object metrics[] = {
                    C, WX, N, B
                };
                if(C.intValue() >= 0)
                    CharMetrics.put(C, ((Object) (metrics)));
                CharMetrics.put(N, ((Object) (metrics)));
            } while(true);
            if(isMetrics)
                throw new DocumentException(MessageLocalization.getComposedMessage("missing.endcharmetrics.in.1", new Object[] {
                    fileName
                }));
            if(!CharMetrics.containsKey("nonbreakingspace"))
            {
                Object space[] = (Object[])CharMetrics.get("space");
                if(space != null)
                    CharMetrics.put("nonbreakingspace", ((Object) (space)));
            }
            do
            {
                String line;
                if((line = rf.readLine()) == null)
                    break;
                StringTokenizer tok = new StringTokenizer(line);
                if(!tok.hasMoreTokens())
                    continue;
                ident = tok.nextToken();
                if(ident.equals("EndFontMetrics"))
                    return;
                if(!ident.equals("StartKernPairs"))
                    continue;
                isMetrics = true;
                break;
            } while(true);
            if(!isMetrics)
                throw new DocumentException(MessageLocalization.getComposedMessage("missing.endfontmetrics.in.1", new Object[] {
                    fileName
                }));
label3:
            do
            {
                String line;
                while((line = rf.readLine()) != null) 
                {
                    StringTokenizer tok = new StringTokenizer(line);
                    if(tok.hasMoreTokens())
                    {
                        ident = tok.nextToken();
                        if(!ident.equals("KPX"))
                            continue label3;
                        String first = tok.nextToken();
                        String second = tok.nextToken();
                        Integer width = Integer.valueOf((int)Float.parseFloat(tok.nextToken()));
                        Object relates[] = (Object[])KernPairs.get(first);
                        if(relates == null)
                        {
                            KernPairs.put(first, ((Object) (new Object[] {
                                second, width
                            })));
                        } else
                        {
                            int n = relates.length;
                            Object relates2[] = new Object[n + 2];
                            System.arraycopy(((Object) (relates)), 0, ((Object) (relates2)), 0, n);
                            relates2[n] = second;
                            relates2[n + 1] = width;
                            KernPairs.put(first, ((Object) (relates2)));
                        }
                    }
                }
                break label2;
            } while(!ident.equals("EndKernPairs"));
            isMetrics = false;
        }
        if(isMetrics)
        {
            throw new DocumentException(MessageLocalization.getComposedMessage("missing.endkernpairs.in.1", new Object[] {
                fileName
            }));
        } else
        {
            rf.close();
            return;
        }
    }

    public PdfStream getFullFontStream()
        throws DocumentException
    {
        RandomAccessFileOrArray rf;
        if(builtinFont || !embedded)
            return null;
        rf = null;
        BaseFont.StreamFont streamfont;
        try
        {
            String filePfb = (new StringBuilder()).append(fileName.substring(0, fileName.length() - 3)).append("pfb").toString();
            if(pfb == null)
                rf = new RandomAccessFileOrArray(filePfb, true, Document.plainRandomAccess);
            else
                rf = new RandomAccessFileOrArray(pfb);
            int fileLength = (int)rf.length();
            byte st[] = new byte[fileLength - 18];
            int lengths[] = new int[3];
            int bytePtr = 0;
            for(int k = 0; k < 3; k++)
            {
                if(rf.read() != 128)
                    throw new DocumentException(MessageLocalization.getComposedMessage("start.marker.missing.in.1", new Object[] {
                        filePfb
                    }));
                if(rf.read() != PFB_TYPES[k])
                    throw new DocumentException(MessageLocalization.getComposedMessage("incorrect.segment.type.in.1", new Object[] {
                        filePfb
                    }));
                int size = rf.read();
                size += rf.read() << 8;
                size += rf.read() << 16;
                size += rf.read() << 24;
                lengths[k] = size;
                int got;
                for(; size != 0; size -= got)
                {
                    got = rf.read(st, bytePtr, size);
                    if(got < 0)
                        throw new DocumentException(MessageLocalization.getComposedMessage("premature.end.in.1", new Object[] {
                            filePfb
                        }));
                    bytePtr += got;
                }

            }

            streamfont = new BaseFont.StreamFont(st, lengths, compressionLevel);
        }
        catch(Exception e)
        {
            throw new DocumentException(e);
        }
        if(rf != null)
            try
            {
                rf.close();
            }
            catch(Exception e) { }
        return streamfont;
        Exception exception;
        exception;
        if(rf != null)
            try
            {
                rf.close();
            }
            catch(Exception e) { }
        throw exception;
    }

    private PdfDictionary getFontDescriptor(PdfIndirectReference fontStream)
    {
        if(builtinFont)
            return null;
        PdfDictionary dic = new PdfDictionary(PdfName.FONTDESCRIPTOR);
        dic.put(PdfName.ASCENT, new PdfNumber(Ascender));
        dic.put(PdfName.CAPHEIGHT, new PdfNumber(CapHeight));
        dic.put(PdfName.DESCENT, new PdfNumber(Descender));
        dic.put(PdfName.FONTBBOX, new PdfRectangle(llx, lly, urx, ury));
        dic.put(PdfName.FONTNAME, new PdfName(FontName));
        dic.put(PdfName.ITALICANGLE, new PdfNumber(ItalicAngle));
        dic.put(PdfName.STEMV, new PdfNumber(StdVW));
        if(fontStream != null)
            dic.put(PdfName.FONTFILE, fontStream);
        int flags = 0;
        if(IsFixedPitch)
            flags |= 1;
        flags |= fontSpecific ? 4 : 32;
        if(ItalicAngle < 0.0F)
            flags |= 0x40;
        if(FontName.indexOf("Caps") >= 0 || FontName.endsWith("SC"))
            flags |= 0x20000;
        if(Weight.equals("Bold"))
            flags |= 0x40000;
        dic.put(PdfName.FLAGS, new PdfNumber(flags));
        return dic;
    }

    private PdfDictionary getFontBaseType(PdfIndirectReference fontDescriptor, int firstChar, int lastChar, byte shortTag[])
    {
        PdfDictionary dic = new PdfDictionary(PdfName.FONT);
        dic.put(PdfName.SUBTYPE, PdfName.TYPE1);
        dic.put(PdfName.BASEFONT, new PdfName(FontName));
        boolean stdEncoding = encoding.equals("Cp1252") || encoding.equals("MacRoman");
        if(!fontSpecific || specialMap != null)
        {
            int k = firstChar;
            do
            {
                if(k > lastChar)
                    break;
                if(!differences[k].equals(".notdef"))
                {
                    firstChar = k;
                    break;
                }
                k++;
            } while(true);
            if(stdEncoding)
            {
                dic.put(PdfName.ENCODING, encoding.equals("Cp1252") ? ((PdfObject) (PdfName.WIN_ANSI_ENCODING)) : ((PdfObject) (PdfName.MAC_ROMAN_ENCODING)));
            } else
            {
                PdfDictionary enc = new PdfDictionary(PdfName.ENCODING);
                PdfArray dif = new PdfArray();
                boolean gap = true;
                for(int k = firstChar; k <= lastChar; k++)
                    if(shortTag[k] != 0)
                    {
                        if(gap)
                        {
                            dif.add(new PdfNumber(k));
                            gap = false;
                        }
                        dif.add(new PdfName(differences[k]));
                    } else
                    {
                        gap = true;
                    }

                enc.put(PdfName.DIFFERENCES, dif);
                dic.put(PdfName.ENCODING, enc);
            }
        }
        if(specialMap != null || forceWidthsOutput || !builtinFont || !fontSpecific && !stdEncoding)
        {
            dic.put(PdfName.FIRSTCHAR, new PdfNumber(firstChar));
            dic.put(PdfName.LASTCHAR, new PdfNumber(lastChar));
            PdfArray wd = new PdfArray();
            for(int k = firstChar; k <= lastChar; k++)
                if(shortTag[k] == 0)
                    wd.add(new PdfNumber(0));
                else
                    wd.add(new PdfNumber(widths[k]));

            dic.put(PdfName.WIDTHS, wd);
        }
        if(!builtinFont && fontDescriptor != null)
            dic.put(PdfName.FONTDESCRIPTOR, fontDescriptor);
        return dic;
    }

    void writeFont(PdfWriter writer, PdfIndirectReference ref, Object params[])
        throws DocumentException, IOException
    {
        int firstChar = ((Integer)params[0]).intValue();
        int lastChar = ((Integer)params[1]).intValue();
        byte shortTag[] = (byte[])(byte[])params[2];
        boolean subsetp = ((Boolean)params[3]).booleanValue() && subset;
        if(!subsetp)
        {
            firstChar = 0;
            lastChar = shortTag.length - 1;
            for(int k = 0; k < shortTag.length; k++)
                shortTag[k] = 1;

        }
        PdfIndirectReference ind_font = null;
        PdfObject pobj = null;
        PdfIndirectObject obj = null;
        pobj = getFullFontStream();
        if(pobj != null)
        {
            obj = writer.addToBody(pobj);
            ind_font = obj.getIndirectReference();
        }
        pobj = getFontDescriptor(ind_font);
        if(pobj != null)
        {
            obj = writer.addToBody(pobj);
            ind_font = obj.getIndirectReference();
        }
        pobj = getFontBaseType(ind_font, firstChar, lastChar, shortTag);
        writer.addToBody(pobj, ref);
    }

    public float getFontDescriptor(int key, float fontSize)
    {
        switch(key)
        {
        case 1: // '\001'
        case 9: // '\t'
            return ((float)Ascender * fontSize) / 1000F;

        case 2: // '\002'
            return ((float)CapHeight * fontSize) / 1000F;

        case 3: // '\003'
        case 10: // '\n'
            return ((float)Descender * fontSize) / 1000F;

        case 4: // '\004'
            return ItalicAngle;

        case 5: // '\005'
            return ((float)llx * fontSize) / 1000F;

        case 6: // '\006'
            return ((float)lly * fontSize) / 1000F;

        case 7: // '\007'
            return ((float)urx * fontSize) / 1000F;

        case 8: // '\b'
            return ((float)ury * fontSize) / 1000F;

        case 11: // '\013'
            return 0.0F;

        case 12: // '\f'
            return ((float)(urx - llx) * fontSize) / 1000F;

        case 13: // '\r'
            return ((float)UnderlinePosition * fontSize) / 1000F;

        case 14: // '\016'
            return ((float)UnderlineThickness * fontSize) / 1000F;
        }
        return 0.0F;
    }

    public void setFontDescriptor(int key, float value)
    {
        switch(key)
        {
        case 1: // '\001'
        case 9: // '\t'
            Ascender = (int)value;
            break;

        case 3: // '\003'
        case 10: // '\n'
            Descender = (int)value;
            break;
        }
    }

    public String getPostscriptFontName()
    {
        return FontName;
    }

    public String[][] getFullFontName()
    {
        return (new String[][] {
            new String[] {
                "", "", "", FullName
            }
        });
    }

    public String[][] getAllNameEntries()
    {
        return (new String[][] {
            new String[] {
                "4", "", "", "", FullName
            }
        });
    }

    public String[][] getFamilyFontName()
    {
        return (new String[][] {
            new String[] {
                "", "", "", FamilyName
            }
        });
    }

    public boolean hasKernPairs()
    {
        return !KernPairs.isEmpty();
    }

    public void setPostscriptFontName(String name)
    {
        FontName = name;
    }

    public boolean setKerning(int char1, int char2, int kern)
    {
        String first = GlyphList.unicodeToName(char1);
        if(first == null)
            return false;
        String second = GlyphList.unicodeToName(char2);
        if(second == null)
            return false;
        Object obj[] = (Object[])KernPairs.get(first);
        if(obj == null)
        {
            obj = (new Object[] {
                second, Integer.valueOf(kern)
            });
            KernPairs.put(first, ((Object) (obj)));
            return true;
        }
        for(int k = 0; k < obj.length; k += 2)
            if(second.equals(obj[k]))
            {
                obj[k + 1] = Integer.valueOf(kern);
                return true;
            }

        int size = obj.length;
        Object obj2[] = new Object[size + 2];
        System.arraycopy(((Object) (obj)), 0, ((Object) (obj2)), 0, size);
        obj2[size] = second;
        obj2[size + 1] = Integer.valueOf(kern);
        KernPairs.put(first, ((Object) (obj2)));
        return true;
    }

    protected int[] getRawCharBBox(int c, String name)
    {
        Object metrics[];
        if(name == null)
        {
            metrics = (Object[])CharMetrics.get(Integer.valueOf(c));
        } else
        {
            if(name.equals(".notdef"))
                return null;
            metrics = (Object[])CharMetrics.get(name);
        }
        if(metrics != null)
            return (int[])(int[])metrics[3];
        else
            return null;
    }

    private static FontsResourceAnchor resourceAnchor;
    protected byte pfb[];
    private String FontName;
    private String FullName;
    private String FamilyName;
    private String Weight;
    private float ItalicAngle;
    private boolean IsFixedPitch;
    private String CharacterSet;
    private int llx;
    private int lly;
    private int urx;
    private int ury;
    private int UnderlinePosition;
    private int UnderlineThickness;
    private String EncodingScheme;
    private int CapHeight;
    private int XHeight;
    private int Ascender;
    private int Descender;
    private int StdHW;
    private int StdVW;
    private HashMap CharMetrics;
    private HashMap KernPairs;
    private String fileName;
    private boolean builtinFont;
    private static final int PFB_TYPES[] = {
        1, 2, 1
    };

}
