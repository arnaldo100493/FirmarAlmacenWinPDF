// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfWriter.java

package co.com.pdf.text.pdf;

import co.com.pdf.awt.geom.Rectangle;
import co.com.pdf.text.BaseColor;
import co.com.pdf.text.DocListener;
import co.com.pdf.text.DocWriter;
import co.com.pdf.text.Document;
import co.com.pdf.text.DocumentException;
import co.com.pdf.text.ExceptionConverter;
import co.com.pdf.text.Image;
import co.com.pdf.text.ImgJBIG2;
import co.com.pdf.text.ImgWMF;
import co.com.pdf.text.Version;
import co.com.pdf.text.error_messages.MessageLocalization;
import co.com.pdf.text.log.Counter;
import co.com.pdf.text.log.CounterFactory;
import co.com.pdf.text.pdf.collection.PdfCollection;
import co.com.pdf.text.pdf.events.PdfPageEventForwarder;
import co.com.pdf.text.pdf.interfaces.PdfAnnotations;
import co.com.pdf.text.pdf.interfaces.PdfDocumentActions;
import co.com.pdf.text.pdf.interfaces.PdfEncryptionSettings;
import co.com.pdf.text.pdf.interfaces.PdfIsoConformance;
import co.com.pdf.text.pdf.interfaces.PdfPageActions;
import co.com.pdf.text.pdf.interfaces.PdfRunDirection;
import co.com.pdf.text.pdf.interfaces.PdfVersion;
import co.com.pdf.text.pdf.interfaces.PdfViewerPreferences;
import co.com.pdf.text.pdf.interfaces.PdfXConformance;
import co.com.pdf.text.pdf.internal.PdfVersionImp;
import co.com.pdf.text.pdf.internal.PdfXConformanceImp;
import co.com.pdf.text.xml.xmp.XmpWriter;
import co.com.pdf.xmp.XMPException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfPages, PdfDictionary, PdfArray, PdfContentByte, 
//            PdfDocument, PdfString, PdfIndirectReference, PdfException, 
//            PdfOCG, PdfStream, PdfTemplate, PRIndirectReference, 
//            FontDetails, PdfReaderInstance, ColorDetails, PdfPatternPainter, 
//            PdfShadingPattern, PdfShading, PdfLayerMembership, PdfLayer, 
//            PdfNumber, PdfDestination, PdfXConformanceException, PdfICCBased, 
//            PRStream, PdfEncryption, PdfName, DocumentFont, 
//            PdfImportedPage, PdfObject, PdfStructureTreeRoot, PdfOCProperties, 
//            SpotColor, PdfImage, TtfUnicodeWriter, PdfIndirectObject, 
//            ICC_Profile, PdfReader, BadPdfFormatException, PdfBoolean, 
//            PdfPage, OutputStreamCounter, SimpleBookmark, PdfAction, 
//            PdfFileSpecification, BaseFont, ExtendedColor, PdfPageEvent, 
//            PdfContents, PdfOutline, PdfDeveloperExtension, PdfPageLabels, 
//            PdfAcroForm, PdfAnnotation, PdfFormField, RandomAccessFileOrArray, 
//            PdfSpotColor, PdfTransition, ByteBuffer

public class PdfWriter extends DocWriter
    implements PdfViewerPreferences, PdfEncryptionSettings, PdfVersion, PdfDocumentActions, PdfPageActions, PdfRunDirection, PdfAnnotations
{
    public static class PdfTrailer extends PdfDictionary
    {

        public void toPdf(PdfWriter writer, OutputStream os)
            throws IOException
        {
            PdfWriter.checkPdfIsoConformance(writer, 8, this);
            os.write(DocWriter.getISOBytes("trailer\n"));
            super.toPdf(null, os);
            os.write(10);
            PdfWriter.writeKeyInfo(os);
            os.write(DocWriter.getISOBytes("startxref\n"));
            os.write(DocWriter.getISOBytes(String.valueOf(offset)));
            os.write(DocWriter.getISOBytes("\n%%EOF\n"));
        }

        long offset;

        public PdfTrailer(int size, long offset, PdfIndirectReference root, PdfIndirectReference info, PdfIndirectReference encryption, PdfObject fileID, 
                long prevxref)
        {
            this.offset = offset;
            put(PdfName.SIZE, new PdfNumber(size));
            put(PdfName.ROOT, root);
            if(info != null)
                put(PdfName.INFO, info);
            if(encryption != null)
                put(PdfName.ENCRYPT, encryption);
            if(fileID != null)
                put(PdfName.ID, fileID);
            if(prevxref > 0L)
                put(PdfName.PREV, new PdfNumber(prevxref));
        }
    }

    public static class PdfBody
    {
        public static class PdfCrossReference
            implements Comparable
        {

            public int getRefnum()
            {
                return refnum;
            }

            public void toPdf(OutputStream os)
                throws IOException
            {
                StringBuffer off = (new StringBuffer("0000000000")).append(offset);
                off.delete(0, off.length() - 10);
                StringBuffer gen = (new StringBuffer("00000")).append(generation);
                gen.delete(0, gen.length() - 5);
                off.append(' ').append(gen).append(generation != 65535 ? " n \n" : " f \n");
                os.write(DocWriter.getISOBytes(off.toString()));
            }

            public void toPdf(int midSize, OutputStream os)
                throws IOException
            {
                os.write((byte)type);
                while(--midSize >= 0) 
                    os.write((byte)(int)(offset >>> 8 * midSize & 255L));
                os.write((byte)(generation >>> 8 & 0xff));
                os.write((byte)(generation & 0xff));
            }

            public int compareTo(PdfCrossReference other)
            {
                return refnum >= other.refnum ? ((int) (refnum != other.refnum ? 1 : 0)) : -1;
            }

            public boolean equals(Object obj)
            {
                if(obj instanceof PdfCrossReference)
                {
                    PdfCrossReference other = (PdfCrossReference)obj;
                    return refnum == other.refnum;
                } else
                {
                    return false;
                }
            }

            public int hashCode()
            {
                return refnum;
            }

            public volatile int compareTo(Object x0)
            {
                return compareTo((PdfCrossReference)x0);
            }

            private final int type;
            private final long offset;
            private final int refnum;
            private final int generation;

            public PdfCrossReference(int refnum, long offset, int generation)
            {
                type = 0;
                this.offset = offset;
                this.refnum = refnum;
                this.generation = generation;
            }

            public PdfCrossReference(int refnum, long offset)
            {
                type = 1;
                this.offset = offset;
                this.refnum = refnum;
                generation = 0;
            }

            public PdfCrossReference(int type, int refnum, long offset, int generation)
            {
                this.type = type;
                this.offset = offset;
                this.refnum = refnum;
                this.generation = generation;
            }
        }


        void setRefnum(int refnum)
        {
            this.refnum = refnum;
        }

        protected PdfCrossReference addToObjStm(PdfObject obj, int nObj)
            throws IOException
        {
            if(numObj >= 200)
                flushObjStm();
            if(index == null)
            {
                index = new ByteBuffer();
                streamObjects = new ByteBuffer();
                currentObjNum = getIndirectReferenceNumber();
                numObj = 0;
            }
            int p = streamObjects.size();
            int idx = numObj++;
            PdfEncryption enc = writer.crypto;
            writer.crypto = null;
            obj.toPdf(writer, streamObjects);
            writer.crypto = enc;
            streamObjects.append(' ');
            index.append(nObj).append(' ').append(p).append(' ');
            return new PdfCrossReference(2, nObj, currentObjNum, idx);
        }

        public void flushObjStm()
            throws IOException
        {
            if(numObj == 0)
            {
                return;
            } else
            {
                int first = index.size();
                index.append(streamObjects);
                PdfStream stream = new PdfStream(index.toByteArray());
                stream.flateCompress(writer.getCompressionLevel());
                stream.put(PdfName.TYPE, PdfName.OBJSTM);
                stream.put(PdfName.N, new PdfNumber(numObj));
                stream.put(PdfName.FIRST, new PdfNumber(first));
                add(stream, currentObjNum);
                index = null;
                streamObjects = null;
                numObj = 0;
                return;
            }
        }

        PdfIndirectObject add(PdfObject object)
            throws IOException
        {
            return add(object, getIndirectReferenceNumber());
        }

        PdfIndirectObject add(PdfObject object, boolean inObjStm)
            throws IOException
        {
            return add(object, getIndirectReferenceNumber(), 0, inObjStm);
        }

        public PdfIndirectReference getPdfIndirectReference()
        {
            return new PdfIndirectReference(0, getIndirectReferenceNumber());
        }

        protected int getIndirectReferenceNumber()
        {
            int n = refnum++;
            xrefs.add(new PdfCrossReference(n, 0L, 65535));
            return n;
        }

        PdfIndirectObject add(PdfObject object, PdfIndirectReference ref)
            throws IOException
        {
            return add(object, ref, true);
        }

        PdfIndirectObject add(PdfObject object, PdfIndirectReference ref, boolean inObjStm)
            throws IOException
        {
            return add(object, ref.getNumber(), ref.getGeneration(), inObjStm);
        }

        PdfIndirectObject add(PdfObject object, int refNumber)
            throws IOException
        {
            return add(object, refNumber, 0, true);
        }

        protected PdfIndirectObject add(PdfObject object, int refNumber, int generation, boolean inObjStm)
            throws IOException
        {
            if(inObjStm && object.canBeInObjStm() && writer.isFullCompression())
            {
                PdfCrossReference pxref = addToObjStm(object, refNumber);
                PdfIndirectObject indirect = new PdfIndirectObject(refNumber, object, writer);
                if(!xrefs.add(pxref))
                {
                    xrefs.remove(pxref);
                    xrefs.add(pxref);
                }
                return indirect;
            }
            PdfIndirectObject indirect;
            if(writer.isFullCompression())
            {
                indirect = new PdfIndirectObject(refNumber, object, writer);
                write(indirect, refNumber);
            } else
            {
                indirect = new PdfIndirectObject(refNumber, generation, object, writer);
                write(indirect, refNumber, generation);
            }
            return indirect;
        }

        protected void write(PdfIndirectObject indirect, int refNumber)
            throws IOException
        {
            PdfCrossReference pxref = new PdfCrossReference(refNumber, position);
            if(!xrefs.add(pxref))
            {
                xrefs.remove(pxref);
                xrefs.add(pxref);
            }
            indirect.writeTo(writer.getOs());
            position = writer.getOs().getCounter();
        }

        protected void write(PdfIndirectObject indirect, int refNumber, int generation)
            throws IOException
        {
            PdfCrossReference pxref = new PdfCrossReference(refNumber, position, generation);
            if(!xrefs.add(pxref))
            {
                xrefs.remove(pxref);
                xrefs.add(pxref);
            }
            indirect.writeTo(writer.getOs());
            position = writer.getOs().getCounter();
        }

        public long offset()
        {
            return position;
        }

        public int size()
        {
            return Math.max(((PdfCrossReference)xrefs.last()).getRefnum() + 1, refnum);
        }

        public void writeCrossReferenceTable(OutputStream os, PdfIndirectReference root, PdfIndirectReference info, PdfIndirectReference encryption, PdfObject fileID, long prevxref)
            throws IOException
        {
            int refNumber = 0;
            if(writer.isFullCompression())
            {
                flushObjStm();
                refNumber = getIndirectReferenceNumber();
                xrefs.add(new PdfCrossReference(refNumber, position));
            }
            PdfCrossReference entry = (PdfCrossReference)xrefs.first();
            int first = entry.getRefnum();
            int len = 0;
            ArrayList sections = new ArrayList();
            for(Iterator i$ = xrefs.iterator(); i$.hasNext();)
            {
                PdfCrossReference pdfCrossReference = (PdfCrossReference)i$.next();
                entry = pdfCrossReference;
                if(first + len == entry.getRefnum())
                {
                    len++;
                } else
                {
                    sections.add(Integer.valueOf(first));
                    sections.add(Integer.valueOf(len));
                    first = entry.getRefnum();
                    len = 1;
                }
            }

            sections.add(Integer.valueOf(first));
            sections.add(Integer.valueOf(len));
            if(writer.isFullCompression())
            {
                int mid = 5;
                for(long mask = 0xff00000000L; mid > 1 && (mask & position) == 0L; mid--)
                    mask >>>= 8;

                ByteBuffer buf = new ByteBuffer();
                for(Iterator i$ = xrefs.iterator(); i$.hasNext(); entry.toPdf(mid, buf))
                {
                    Object element = i$.next();
                    entry = (PdfCrossReference)element;
                }

                PdfStream xr = new PdfStream(buf.toByteArray());
                buf = null;
                xr.flateCompress(writer.getCompressionLevel());
                xr.put(PdfName.SIZE, new PdfNumber(size()));
                xr.put(PdfName.ROOT, root);
                if(info != null)
                    xr.put(PdfName.INFO, info);
                if(encryption != null)
                    xr.put(PdfName.ENCRYPT, encryption);
                if(fileID != null)
                    xr.put(PdfName.ID, fileID);
                xr.put(PdfName.W, new PdfArray(new int[] {
                    1, mid, 2
                }));
                xr.put(PdfName.TYPE, PdfName.XREF);
                PdfArray idx = new PdfArray();
                for(int k = 0; k < sections.size(); k++)
                    idx.add(new PdfNumber(((Integer)sections.get(k)).intValue()));

                xr.put(PdfName.INDEX, idx);
                if(prevxref > 0L)
                    xr.put(PdfName.PREV, new PdfNumber(prevxref));
                PdfEncryption enc = writer.crypto;
                writer.crypto = null;
                PdfIndirectObject indirect = new PdfIndirectObject(refNumber, xr, writer);
                indirect.writeTo(writer.getOs());
                writer.crypto = enc;
            } else
            {
                os.write(DocWriter.getISOBytes("xref\n"));
                Iterator i = xrefs.iterator();
                for(int k = 0; k < sections.size(); k += 2)
                {
                    first = ((Integer)sections.get(k)).intValue();
                    len = ((Integer)sections.get(k + 1)).intValue();
                    os.write(DocWriter.getISOBytes(String.valueOf(first)));
                    os.write(DocWriter.getISOBytes(" "));
                    os.write(DocWriter.getISOBytes(String.valueOf(len)));
                    os.write(10);
                    while(len-- > 0) 
                    {
                        entry = (PdfCrossReference)i.next();
                        entry.toPdf(os);
                    }
                }

            }
        }

        private static final int OBJSINSTREAM = 200;
        protected final TreeSet xrefs = new TreeSet();
        protected int refnum;
        protected long position;
        protected final PdfWriter writer;
        protected ByteBuffer index;
        protected ByteBuffer streamObjects;
        protected int currentObjNum;
        protected int numObj;

        protected PdfBody(PdfWriter writer)
        {
            numObj = 0;
            xrefs.add(new PdfCrossReference(0, 0L, 65535));
            position = writer.getOs().getCounter();
            refnum = 1;
            this.writer = writer;
        }
    }


    protected Counter getCounter()
    {
        return COUNTER;
    }

    protected PdfWriter()
    {
        root = new PdfPages(this);
        pageReferences = new ArrayList();
        currentPageNumber = 1;
        tabs = null;
        pageDictEntries = new PdfDictionary();
        prevxref = 0L;
        pdf_version = new PdfVersionImp();
        xmpMetadata = null;
        xmpWriter = null;
        pdfIsoConformance = getPdfIsoConformance();
        fullCompression = false;
        compressionLevel = -1;
        documentFonts = new LinkedHashMap();
        fontNumber = 1;
        formXObjects = new HashMap();
        formXObjectsCounter = 1;
        readerInstances = new HashMap();
        documentColors = new HashMap();
        colorNumber = 1;
        documentPatterns = new HashMap();
        patternNumber = 1;
        documentShadingPatterns = new HashSet();
        documentShadings = new HashSet();
        documentExtGState = new HashMap();
        documentProperties = new HashMap();
        tagged = false;
        documentOCG = new HashSet();
        documentOCGorder = new ArrayList();
        OCGRadioGroup = new PdfArray();
        OCGLocked = new PdfArray();
        spaceCharRatio = 2.5F;
        runDirection = 1;
        defaultColorspace = new PdfDictionary();
        documentSpotPatterns = new HashMap();
        imageDictionary = new PdfDictionary();
        images = new HashMap();
        JBIG2Globals = new HashMap();
        ttfUnicodeWriter = null;
    }

    protected PdfWriter(PdfDocument document, OutputStream os)
    {
        super(document, os);
        root = new PdfPages(this);
        pageReferences = new ArrayList();
        currentPageNumber = 1;
        tabs = null;
        pageDictEntries = new PdfDictionary();
        prevxref = 0L;
        pdf_version = new PdfVersionImp();
        xmpMetadata = null;
        xmpWriter = null;
        pdfIsoConformance = getPdfIsoConformance();
        fullCompression = false;
        compressionLevel = -1;
        documentFonts = new LinkedHashMap();
        fontNumber = 1;
        formXObjects = new HashMap();
        formXObjectsCounter = 1;
        readerInstances = new HashMap();
        documentColors = new HashMap();
        colorNumber = 1;
        documentPatterns = new HashMap();
        patternNumber = 1;
        documentShadingPatterns = new HashSet();
        documentShadings = new HashSet();
        documentExtGState = new HashMap();
        documentProperties = new HashMap();
        tagged = false;
        documentOCG = new HashSet();
        documentOCGorder = new ArrayList();
        OCGRadioGroup = new PdfArray();
        OCGLocked = new PdfArray();
        spaceCharRatio = 2.5F;
        runDirection = 1;
        defaultColorspace = new PdfDictionary();
        documentSpotPatterns = new HashMap();
        imageDictionary = new PdfDictionary();
        images = new HashMap();
        JBIG2Globals = new HashMap();
        ttfUnicodeWriter = null;
        pdf = document;
        directContentUnder = new PdfContentByte(this);
        directContent = directContentUnder.getDuplicate();
    }

    public static PdfWriter getInstance(Document document, OutputStream os)
        throws DocumentException
    {
        PdfDocument pdf = new PdfDocument();
        document.addDocListener(pdf);
        PdfWriter writer = new PdfWriter(pdf, os);
        pdf.addWriter(writer);
        return writer;
    }

    public static PdfWriter getInstance(Document document, OutputStream os, DocListener listener)
        throws DocumentException
    {
        PdfDocument pdf = new PdfDocument();
        pdf.addDocListener(listener);
        document.addDocListener(pdf);
        PdfWriter writer = new PdfWriter(pdf, os);
        pdf.addWriter(writer);
        return writer;
    }

    PdfDocument getPdfDocument()
    {
        return pdf;
    }

    public PdfDictionary getInfo()
    {
        return pdf.getInfo();
    }

    public float getVerticalPosition(boolean ensureNewLine)
    {
        return pdf.getVerticalPosition(ensureNewLine);
    }

    public void setInitialLeading(float leading)
        throws DocumentException
    {
        if(open)
        {
            throw new DocumentException(MessageLocalization.getComposedMessage("you.can.t.set.the.initial.leading.if.the.document.is.already.open", new Object[0]));
        } else
        {
            pdf.setLeading(leading);
            return;
        }
    }

    public PdfContentByte getDirectContent()
    {
        if(!open)
            throw new RuntimeException(MessageLocalization.getComposedMessage("the.document.is.not.open", new Object[0]));
        else
            return directContent;
    }

    public PdfContentByte getDirectContentUnder()
    {
        if(!open)
            throw new RuntimeException(MessageLocalization.getComposedMessage("the.document.is.not.open", new Object[0]));
        else
            return directContentUnder;
    }

    void resetContent()
    {
        directContent.reset();
        directContentUnder.reset();
    }

    void addLocalDestinations(TreeMap desto)
        throws IOException
    {
        for(Iterator i$ = desto.entrySet().iterator(); i$.hasNext();)
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)i$.next();
            String name = (String)entry.getKey();
            PdfDocument.Destination dest = (PdfDocument.Destination)entry.getValue();
            PdfDestination destination = dest.destination;
            if(dest.reference == null)
                dest.reference = getPdfIndirectReference();
            if(destination == null)
                addToBody(new PdfString((new StringBuilder()).append("invalid_").append(name).toString()), dest.reference);
            else
                addToBody(destination, dest.reference);
        }

    }

    public PdfIndirectObject addToBody(PdfObject object)
        throws IOException
    {
        PdfIndirectObject iobj = body.add(object);
        return iobj;
    }

    public PdfIndirectObject addToBody(PdfObject object, boolean inObjStm)
        throws IOException
    {
        PdfIndirectObject iobj = body.add(object, inObjStm);
        return iobj;
    }

    public PdfIndirectObject addToBody(PdfObject object, PdfIndirectReference ref)
        throws IOException
    {
        PdfIndirectObject iobj = body.add(object, ref);
        return iobj;
    }

    public PdfIndirectObject addToBody(PdfObject object, PdfIndirectReference ref, boolean inObjStm)
        throws IOException
    {
        PdfIndirectObject iobj = body.add(object, ref, inObjStm);
        return iobj;
    }

    public PdfIndirectObject addToBody(PdfObject object, int refNumber)
        throws IOException
    {
        PdfIndirectObject iobj = body.add(object, refNumber);
        return iobj;
    }

    public PdfIndirectObject addToBody(PdfObject object, int refNumber, boolean inObjStm)
        throws IOException
    {
        PdfIndirectObject iobj = body.add(object, refNumber, 0, inObjStm);
        return iobj;
    }

    public PdfIndirectReference getPdfIndirectReference()
    {
        return body.getPdfIndirectReference();
    }

    protected int getIndirectReferenceNumber()
    {
        return body.getIndirectReferenceNumber();
    }

    public OutputStreamCounter getOs()
    {
        return os;
    }

    protected PdfDictionary getCatalog(PdfIndirectReference rootObj)
    {
        PdfDictionary catalog = pdf.getCatalog(rootObj);
        buildStructTreeRootForTagged(catalog);
        if(!documentOCG.isEmpty())
        {
            fillOCProperties(false);
            catalog.put(PdfName.OCPROPERTIES, OCProperties);
        }
        return catalog;
    }

    protected void buildStructTreeRootForTagged(PdfDictionary catalog)
    {
        if(tagged)
        {
            try
            {
                getStructureTreeRoot().buildTree();
            }
            catch(Exception e)
            {
                throw new ExceptionConverter(e);
            }
            catalog.put(PdfName.STRUCTTREEROOT, structureTreeRoot.getReference());
            PdfDictionary mi = new PdfDictionary();
            mi.put(PdfName.MARKED, PdfBoolean.PDFTRUE);
            if(userProperties)
                mi.put(PdfName.USERPROPERTIES, PdfBoolean.PDFTRUE);
            catalog.put(PdfName.MARKINFO, mi);
        }
    }

    public PdfDictionary getExtraCatalog()
    {
        if(extraCatalog == null)
            extraCatalog = new PdfDictionary();
        return extraCatalog;
    }

    public void addPageDictEntry(PdfName key, PdfObject object)
    {
        pageDictEntries.put(key, object);
    }

    public PdfDictionary getPageDictEntries()
    {
        return pageDictEntries;
    }

    public void resetPageDictEntries()
    {
        pageDictEntries = new PdfDictionary();
    }

    public void setLinearPageMode()
    {
        root.setLinearMode(null);
    }

    public int reorderPages(int order[])
        throws DocumentException
    {
        return root.reorderPages(order);
    }

    public PdfIndirectReference getPageReference(int page)
    {
        if(--page < 0)
            throw new IndexOutOfBoundsException(MessageLocalization.getComposedMessage("the.page.number.must.be.gt.eq.1", new Object[0]));
        PdfIndirectReference ref;
        if(page < pageReferences.size())
        {
            ref = (PdfIndirectReference)pageReferences.get(page);
            if(ref == null)
            {
                ref = body.getPdfIndirectReference();
                pageReferences.set(page, ref);
            }
        } else
        {
            int empty = page - pageReferences.size();
            for(int k = 0; k < empty; k++)
                pageReferences.add(null);

            ref = body.getPdfIndirectReference();
            pageReferences.add(ref);
        }
        return ref;
    }

    public int getPageNumber()
    {
        return pdf.getPageNumber();
    }

    PdfIndirectReference getCurrentPage()
    {
        return getPageReference(currentPageNumber);
    }

    public int getCurrentPageNumber()
    {
        return currentPageNumber;
    }

    public void setPageViewport(PdfArray vp)
    {
        addPageDictEntry(PdfName.VP, vp);
    }

    public void setTabs(PdfName tabs)
    {
        this.tabs = tabs;
    }

    public PdfName getTabs()
    {
        return tabs;
    }

    PdfIndirectReference add(PdfPage page, PdfContents contents)
        throws PdfException
    {
        if(!open)
            throw new PdfException(MessageLocalization.getComposedMessage("the.document.is.not.open", new Object[0]));
        PdfIndirectObject object;
        try
        {
            object = addToBody(contents);
        }
        catch(IOException ioe)
        {
            throw new ExceptionConverter(ioe);
        }
        page.add(object.getIndirectReference());
        if(group != null)
        {
            page.put(PdfName.GROUP, group);
            group = null;
        } else
        if(rgbTransparencyBlending)
        {
            PdfDictionary pp = new PdfDictionary();
            pp.put(PdfName.TYPE, PdfName.GROUP);
            pp.put(PdfName.S, PdfName.TRANSPARENCY);
            pp.put(PdfName.CS, PdfName.DEVICERGB);
            page.put(PdfName.GROUP, pp);
        }
        root.addPage(page);
        currentPageNumber++;
        return null;
    }

    public void setPageEvent(PdfPageEvent event)
    {
        if(event == null)
            pageEvent = null;
        else
        if(pageEvent == null)
            pageEvent = event;
        else
        if(pageEvent instanceof PdfPageEventForwarder)
        {
            ((PdfPageEventForwarder)pageEvent).addPageEvent(event);
        } else
        {
            PdfPageEventForwarder forward = new PdfPageEventForwarder();
            forward.addPageEvent(pageEvent);
            forward.addPageEvent(event);
            pageEvent = forward;
        }
    }

    public PdfPageEvent getPageEvent()
    {
        return pageEvent;
    }

    public void open()
    {
        super.open();
        try
        {
            pdf_version.writeHeader(os);
            body = new PdfBody(this);
            if(isPdfX() && ((PdfXConformanceImp)pdfIsoConformance).isPdfX32002())
            {
                PdfDictionary sec = new PdfDictionary();
                sec.put(PdfName.GAMMA, new PdfArray(new float[] {
                    2.2F, 2.2F, 2.2F
                }));
                sec.put(PdfName.MATRIX, new PdfArray(new float[] {
                    0.4124F, 0.2126F, 0.0193F, 0.3576F, 0.7152F, 0.1192F, 0.1805F, 0.0722F, 0.9505F
                }));
                sec.put(PdfName.WHITEPOINT, new PdfArray(new float[] {
                    0.9505F, 1.0F, 1.089F
                }));
                PdfArray arr = new PdfArray(PdfName.CALRGB);
                arr.add(sec);
                setDefaultColorspace(PdfName.DEFAULTRGB, addToBody(arr).getIndirectReference());
            }
        }
        catch(IOException ioe)
        {
            throw new ExceptionConverter(ioe);
        }
    }

    public void close()
    {
        if(open)
        {
            if(currentPageNumber - 1 != pageReferences.size())
                throw new RuntimeException((new StringBuilder()).append("The page ").append(pageReferences.size()).append(" was requested but the document has only ").append(currentPageNumber - 1).append(" pages.").toString());
            pdf.close();
            try
            {
                addSharedObjectsToBody();
                PdfOCG layer;
                for(Iterator i$ = documentOCG.iterator(); i$.hasNext(); addToBody(layer.getPdfObject(), layer.getRef()))
                    layer = (PdfOCG)i$.next();

                PdfIndirectReference rootRef = root.writePageTree();
                PdfDictionary catalog = getCatalog(rootRef);
                if(!documentOCG.isEmpty())
                    checkPdfIsoConformance(this, 7, OCProperties);
                if(xmpMetadata == null && xmpWriter != null)
                    try
                    {
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        xmpWriter.serialize(baos);
                        xmpWriter.close();
                        xmpMetadata = baos.toByteArray();
                    }
                    catch(IOException exc)
                    {
                        xmpWriter = null;
                    }
                    catch(XMPException exc)
                    {
                        xmpWriter = null;
                    }
                if(xmpMetadata != null)
                {
                    PdfStream xmp = new PdfStream(xmpMetadata);
                    xmp.put(PdfName.TYPE, PdfName.METADATA);
                    xmp.put(PdfName.SUBTYPE, PdfName.XML);
                    if(crypto != null && !crypto.isMetadataEncrypted())
                    {
                        PdfArray ar = new PdfArray();
                        ar.add(PdfName.CRYPT);
                        xmp.put(PdfName.FILTER, ar);
                    }
                    catalog.put(PdfName.METADATA, body.add(xmp).getIndirectReference());
                }
                if(isPdfX())
                {
                    completeInfoDictionary(getInfo());
                    completeExtraCatalog(getExtraCatalog());
                }
                if(extraCatalog != null)
                    catalog.mergeDifferent(extraCatalog);
                writeOutlines(catalog, false);
                PdfIndirectObject indirectCatalog = addToBody(catalog, false);
                PdfIndirectObject infoObj = addToBody(getInfo(), false);
                PdfIndirectReference encryption = null;
                PdfObject fileID = null;
                body.flushObjStm();
                if(crypto != null)
                {
                    PdfIndirectObject encryptionObject = addToBody(crypto.getEncryptionDictionary(), false);
                    encryption = encryptionObject.getIndirectReference();
                    fileID = crypto.getFileID();
                } else
                {
                    fileID = PdfEncryption.createInfoId(PdfEncryption.createDocumentId());
                }
                body.writeCrossReferenceTable(os, indirectCatalog.getIndirectReference(), infoObj.getIndirectReference(), encryption, fileID, prevxref);
                if(fullCompression)
                {
                    writeKeyInfo(os);
                    os.write(getISOBytes("startxref\n"));
                    os.write(getISOBytes(String.valueOf(body.offset())));
                    os.write(getISOBytes("\n%%EOF\n"));
                } else
                {
                    PdfTrailer trailer = new PdfTrailer(body.size(), body.offset(), indirectCatalog.getIndirectReference(), infoObj.getIndirectReference(), encryption, fileID, prevxref);
                    trailer.toPdf(this, os);
                }
                super.close();
            }
            catch(IOException ioe)
            {
                throw new ExceptionConverter(ioe);
            }
        }
        getCounter().written(os.getCounter());
    }

    protected void addXFormsToBody()
        throws IOException
    {
        Iterator i$ = formXObjects.values().iterator();
        do
        {
            if(!i$.hasNext())
                break;
            Object objs[] = (Object[])i$.next();
            PdfTemplate template = (PdfTemplate)objs[1];
            if((template == null || !(template.getIndirectReference() instanceof PRIndirectReference)) && (template != null && template.getType() == 1))
                addToBody(template.getFormXObject(compressionLevel), template.getIndirectReference());
        } while(true);
    }

    protected void addSharedObjectsToBody()
        throws IOException
    {
        Iterator i$;
        FontDetails details;
        for(i$ = documentFonts.values().iterator(); i$.hasNext(); details.writeFont(this))
            details = (FontDetails)i$.next();

        addXFormsToBody();
        for(i$ = readerInstances.values().iterator(); i$.hasNext(); currentPdfReaderInstance.writeAllPages())
        {
            PdfReaderInstance element = (PdfReaderInstance)i$.next();
            currentPdfReaderInstance = element;
        }

        currentPdfReaderInstance = null;
        ColorDetails color;
        for(i$ = documentColors.values().iterator(); i$.hasNext(); addToBody(color.getSpotColor(this), color.getIndirectReference()))
            color = (ColorDetails)i$.next();

        PdfPatternPainter pat;
        for(i$ = documentPatterns.keySet().iterator(); i$.hasNext(); addToBody(pat.getPattern(compressionLevel), pat.getIndirectReference()))
            pat = (PdfPatternPainter)i$.next();

        PdfShadingPattern shadingPattern;
        for(i$ = documentShadingPatterns.iterator(); i$.hasNext(); shadingPattern.addToBody())
            shadingPattern = (PdfShadingPattern)i$.next();

        PdfShading shading;
        for(i$ = documentShadings.iterator(); i$.hasNext(); shading.addToBody())
            shading = (PdfShading)i$.next();

        PdfDictionary gstate;
        PdfObject obj[];
        for(i$ = documentExtGState.entrySet().iterator(); i$.hasNext(); addToBody(gstate, (PdfIndirectReference)obj[1]))
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)i$.next();
            gstate = (PdfDictionary)entry.getKey();
            obj = (PdfObject[])entry.getValue();
        }

        i$ = documentProperties.entrySet().iterator();
        do
        {
            if(!i$.hasNext())
                break;
            java.util.Map.Entry entry = (java.util.Map.Entry)i$.next();
            Object prop = entry.getKey();
            PdfObject obj[] = (PdfObject[])entry.getValue();
            if(prop instanceof PdfLayerMembership)
            {
                PdfLayerMembership layer = (PdfLayerMembership)prop;
                addToBody(layer.getPdfObject(), layer.getRef());
            } else
            if((prop instanceof PdfDictionary) && !(prop instanceof PdfLayer))
                addToBody((PdfDictionary)prop, (PdfIndirectReference)obj[1]);
        } while(true);
    }

    public PdfOutline getRootOutline()
    {
        return directContent.getRootOutline();
    }

    public void setOutlines(List outlines)
    {
        newBookmarks = outlines;
    }

    protected void writeOutlines(PdfDictionary catalog, boolean namedAsNames)
        throws IOException
    {
        if(newBookmarks == null || newBookmarks.isEmpty())
        {
            return;
        } else
        {
            PdfDictionary top = new PdfDictionary();
            PdfIndirectReference topRef = getPdfIndirectReference();
            Object kids[] = SimpleBookmark.iterateOutlines(this, topRef, newBookmarks, namedAsNames);
            top.put(PdfName.FIRST, (PdfIndirectReference)kids[0]);
            top.put(PdfName.LAST, (PdfIndirectReference)kids[1]);
            top.put(PdfName.COUNT, new PdfNumber(((Integer)kids[2]).intValue()));
            addToBody(top, topRef);
            catalog.put(PdfName.OUTLINES, topRef);
            return;
        }
    }

    public void setPdfVersion(char version)
    {
        pdf_version.setPdfVersion(version);
    }

    public void setAtLeastPdfVersion(char version)
    {
        pdf_version.setAtLeastPdfVersion(version);
    }

    public void setPdfVersion(PdfName version)
    {
        pdf_version.setPdfVersion(version);
    }

    public void addDeveloperExtension(PdfDeveloperExtension de)
    {
        pdf_version.addDeveloperExtension(de);
    }

    PdfVersionImp getPdfVersion()
    {
        return pdf_version;
    }

    public void setViewerPreferences(int preferences)
    {
        pdf.setViewerPreferences(preferences);
    }

    public void addViewerPreference(PdfName key, PdfObject value)
    {
        pdf.addViewerPreference(key, value);
    }

    public void setPageLabels(PdfPageLabels pageLabels)
    {
        pdf.setPageLabels(pageLabels);
    }

    public void addNamedDestinations(Map map, int page_offset)
    {
        int page;
        PdfDestination destination;
        java.util.Map.Entry entry;
        for(Iterator i$ = map.entrySet().iterator(); i$.hasNext(); addNamedDestination((String)entry.getKey(), page + page_offset, destination))
        {
            entry = (java.util.Map.Entry)i$.next();
            String dest = (String)entry.getValue();
            page = Integer.parseInt(dest.substring(0, dest.indexOf(" ")));
            destination = new PdfDestination(dest.substring(dest.indexOf(" ") + 1));
        }

    }

    public void addNamedDestination(String name, int page, PdfDestination dest)
    {
        dest.addPage(getPageReference(page));
        pdf.localDestination(name, dest);
    }

    public void addJavaScript(PdfAction js)
    {
        pdf.addJavaScript(js);
    }

    public void addJavaScript(String code, boolean unicode)
    {
        addJavaScript(PdfAction.javaScript(code, this, unicode));
    }

    public void addJavaScript(String code)
    {
        addJavaScript(code, false);
    }

    public void addJavaScript(String name, PdfAction js)
    {
        pdf.addJavaScript(name, js);
    }

    public void addJavaScript(String name, String code, boolean unicode)
    {
        addJavaScript(name, PdfAction.javaScript(code, this, unicode));
    }

    public void addJavaScript(String name, String code)
    {
        addJavaScript(name, code, false);
    }

    public void addFileAttachment(String description, byte fileStore[], String file, String fileDisplay)
        throws IOException
    {
        addFileAttachment(description, PdfFileSpecification.fileEmbedded(this, file, fileDisplay, fileStore));
    }

    public void addFileAttachment(String description, PdfFileSpecification fs)
        throws IOException
    {
        pdf.addFileAttachment(description, fs);
    }

    public void addFileAttachment(PdfFileSpecification fs)
        throws IOException
    {
        addFileAttachment(null, fs);
    }

    public void setOpenAction(String name)
    {
        pdf.setOpenAction(name);
    }

    public void setOpenAction(PdfAction action)
    {
        pdf.setOpenAction(action);
    }

    public void setAdditionalAction(PdfName actionType, PdfAction action)
        throws DocumentException
    {
        if(!actionType.equals(DOCUMENT_CLOSE) && !actionType.equals(WILL_SAVE) && !actionType.equals(DID_SAVE) && !actionType.equals(WILL_PRINT) && !actionType.equals(DID_PRINT))
        {
            throw new DocumentException(MessageLocalization.getComposedMessage("invalid.additional.action.type.1", new Object[] {
                actionType.toString()
            }));
        } else
        {
            pdf.addAdditionalAction(actionType, action);
            return;
        }
    }

    public void setCollection(PdfCollection collection)
    {
        setAtLeastPdfVersion('7');
        pdf.setCollection(collection);
    }

    public PdfAcroForm getAcroForm()
    {
        return pdf.getAcroForm();
    }

    public void addAnnotation(PdfAnnotation annot)
    {
        pdf.addAnnotation(annot);
    }

    void addAnnotation(PdfAnnotation annot, int page)
    {
        addAnnotation(annot);
    }

    public void addCalculationOrder(PdfFormField annot)
    {
        pdf.addCalculationOrder(annot);
    }

    public void setSigFlags(int f)
    {
        pdf.setSigFlags(f);
    }

    public void setLanguage(String language)
    {
        pdf.setLanguage(language);
    }

    public void setXmpMetadata(byte xmpMetadata[])
    {
        this.xmpMetadata = xmpMetadata;
    }

    public void setPageXmpMetadata(byte xmpMetadata[])
        throws IOException
    {
        pdf.setXmpMetadata(xmpMetadata);
    }

    public XmpWriter getXmpWriter()
    {
        return xmpWriter;
    }

    public void createXmpMetadata()
    {
        try
        {
            xmpWriter = createXmpWriter(null, pdf.getInfo());
            xmpMetadata = null;
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
    }

    protected PdfIsoConformance getPdfIsoConformance()
    {
        return new PdfXConformanceImp(this);
    }

    public void setPDFXConformance(int pdfx)
    {
        if(!(pdfIsoConformance instanceof PdfXConformanceImp))
            return;
        if(((PdfXConformance)pdfIsoConformance).getPDFXConformance() == pdfx)
            return;
        if(pdf.isOpen())
            throw new PdfXConformanceException(MessageLocalization.getComposedMessage("pdfx.conformance.can.only.be.set.before.opening.the.document", new Object[0]));
        if(crypto != null)
            throw new PdfXConformanceException(MessageLocalization.getComposedMessage("a.pdfx.conforming.document.cannot.be.encrypted", new Object[0]));
        if(pdfx != 0)
            setPdfVersion('3');
        ((PdfXConformance)pdfIsoConformance).setPDFXConformance(pdfx);
    }

    public int getPDFXConformance()
    {
        if(pdfIsoConformance instanceof PdfXConformanceImp)
            return ((PdfXConformance)pdfIsoConformance).getPDFXConformance();
        else
            return 0;
    }

    public boolean isPdfX()
    {
        if(pdfIsoConformance instanceof PdfXConformanceImp)
            return ((PdfXConformance)pdfIsoConformance).isPdfX();
        else
            return false;
    }

    public boolean isPdfIso()
    {
        return pdfIsoConformance.isPdfIso();
    }

    public void setOutputIntents(String outputConditionIdentifier, String outputCondition, String registryName, String info, ICC_Profile colorProfile)
        throws IOException
    {
        getExtraCatalog();
        PdfDictionary out = new PdfDictionary(PdfName.OUTPUTINTENT);
        if(outputCondition != null)
            out.put(PdfName.OUTPUTCONDITION, new PdfString(outputCondition, "UnicodeBig"));
        if(outputConditionIdentifier != null)
            out.put(PdfName.OUTPUTCONDITIONIDENTIFIER, new PdfString(outputConditionIdentifier, "UnicodeBig"));
        if(registryName != null)
            out.put(PdfName.REGISTRYNAME, new PdfString(registryName, "UnicodeBig"));
        if(info != null)
            out.put(PdfName.INFO, new PdfString(info, "UnicodeBig"));
        if(colorProfile != null)
        {
            PdfStream stream = new PdfICCBased(colorProfile, compressionLevel);
            out.put(PdfName.DESTOUTPUTPROFILE, addToBody(stream).getIndirectReference());
        }
        out.put(PdfName.S, PdfName.GTS_PDFX);
        extraCatalog.put(PdfName.OUTPUTINTENTS, new PdfArray(out));
    }

    public void setOutputIntents(String outputConditionIdentifier, String outputCondition, String registryName, String info, byte destOutputProfile[])
        throws IOException
    {
        ICC_Profile colorProfile = destOutputProfile != null ? ICC_Profile.getInstance(destOutputProfile) : null;
        setOutputIntents(outputConditionIdentifier, outputCondition, registryName, info, colorProfile);
    }

    public boolean setOutputIntents(PdfReader reader, boolean checkExistence)
        throws IOException
    {
        PdfDictionary catalog = reader.getCatalog();
        PdfArray outs = catalog.getAsArray(PdfName.OUTPUTINTENTS);
        if(outs == null)
            return false;
        if(outs.isEmpty())
            return false;
        PdfDictionary out = outs.getAsDict(0);
        PdfObject obj = PdfReader.getPdfObject(out.get(PdfName.S));
        if(obj == null || !PdfName.GTS_PDFX.equals(obj))
            return false;
        if(checkExistence)
            return true;
        PRStream stream = (PRStream)PdfReader.getPdfObject(out.get(PdfName.DESTOUTPUTPROFILE));
        byte destProfile[] = null;
        if(stream != null)
            destProfile = PdfReader.getStreamBytes(stream);
        setOutputIntents(getNameString(out, PdfName.OUTPUTCONDITIONIDENTIFIER), getNameString(out, PdfName.OUTPUTCONDITION), getNameString(out, PdfName.REGISTRYNAME), getNameString(out, PdfName.INFO), destProfile);
        return true;
    }

    private static String getNameString(PdfDictionary dic, PdfName key)
    {
        PdfObject obj = PdfReader.getPdfObject(dic.get(key));
        if(obj == null || !obj.isString())
            return null;
        else
            return ((PdfString)obj).toUnicodeString();
    }

    PdfEncryption getEncryption()
    {
        return crypto;
    }

    public void setEncryption(byte userPassword[], byte ownerPassword[], int permissions, int encryptionType)
        throws DocumentException
    {
        if(pdf.isOpen())
        {
            throw new DocumentException(MessageLocalization.getComposedMessage("encryption.can.only.be.added.before.opening.the.document", new Object[0]));
        } else
        {
            crypto = new PdfEncryption();
            crypto.setCryptoMode(encryptionType, 0);
            crypto.setupAllKeys(userPassword, ownerPassword, permissions);
            return;
        }
    }

    public void setEncryption(Certificate certs[], int permissions[], int encryptionType)
        throws DocumentException
    {
        if(pdf.isOpen())
            throw new DocumentException(MessageLocalization.getComposedMessage("encryption.can.only.be.added.before.opening.the.document", new Object[0]));
        crypto = new PdfEncryption();
        if(certs != null)
        {
            for(int i = 0; i < certs.length; i++)
                crypto.addRecipient(certs[i], permissions[i]);

        }
        crypto.setCryptoMode(encryptionType, 0);
        crypto.getEncryptionDictionary();
    }

    /**
     * @deprecated Method setEncryption is deprecated
     */

    public void setEncryption(byte userPassword[], byte ownerPassword[], int permissions, boolean strength128Bits)
        throws DocumentException
    {
        setEncryption(userPassword, ownerPassword, permissions, strength128Bits ? 1 : 0);
    }

    /**
     * @deprecated Method setEncryption is deprecated
     */

    public void setEncryption(boolean strength, String userPassword, String ownerPassword, int permissions)
        throws DocumentException
    {
        setEncryption(getISOBytes(userPassword), getISOBytes(ownerPassword), permissions, strength ? 1 : 0);
    }

    /**
     * @deprecated Method setEncryption is deprecated
     */

    public void setEncryption(int encryptionType, String userPassword, String ownerPassword, int permissions)
        throws DocumentException
    {
        setEncryption(getISOBytes(userPassword), getISOBytes(ownerPassword), permissions, encryptionType);
    }

    public boolean isFullCompression()
    {
        return fullCompression;
    }

    public void setFullCompression()
    {
        fullCompression = true;
        setAtLeastPdfVersion('5');
    }

    public int getCompressionLevel()
    {
        return compressionLevel;
    }

    public void setCompressionLevel(int compressionLevel)
    {
        if(compressionLevel < 0 || compressionLevel > 9)
            this.compressionLevel = -1;
        else
            this.compressionLevel = compressionLevel;
    }

    FontDetails addSimple(BaseFont bf)
    {
        FontDetails ret = (FontDetails)documentFonts.get(bf);
        if(ret == null)
        {
            checkPdfIsoConformance(this, 4, bf);
            if(bf.getFontType() == 4)
                ret = new FontDetails(new PdfName((new StringBuilder()).append("F").append(fontNumber++).toString()), ((DocumentFont)bf).getIndirectReference(), bf);
            else
                ret = new FontDetails(new PdfName((new StringBuilder()).append("F").append(fontNumber++).toString()), body.getPdfIndirectReference(), bf);
            documentFonts.put(bf, ret);
        }
        return ret;
    }

    void eliminateFontSubset(PdfDictionary fonts)
    {
        Iterator i$ = documentFonts.values().iterator();
        do
        {
            if(!i$.hasNext())
                break;
            Object element = i$.next();
            FontDetails ft = (FontDetails)element;
            if(fonts.get(ft.getFontName()) != null)
                ft.setSubset(false);
        } while(true);
    }

    PdfName addDirectTemplateSimple(PdfTemplate template, PdfName forcedName)
    {
        PdfIndirectReference ref = template.getIndirectReference();
        Object obj[] = (Object[])formXObjects.get(ref);
        PdfName name = null;
        try
        {
            if(obj == null)
            {
                if(forcedName == null)
                {
                    name = new PdfName((new StringBuilder()).append("Xf").append(formXObjectsCounter).toString());
                    formXObjectsCounter++;
                } else
                {
                    name = forcedName;
                }
                if(template.getType() == 2)
                {
                    PdfImportedPage ip = (PdfImportedPage)template;
                    PdfReader r = ip.getPdfReaderInstance().getReader();
                    if(!readerInstances.containsKey(r))
                        readerInstances.put(r, ip.getPdfReaderInstance());
                    template = null;
                }
                formXObjects.put(ref, ((Object) (new Object[] {
                    name, template
                })));
            } else
            {
                name = (PdfName)obj[0];
            }
        }
        catch(Exception e)
        {
            throw new ExceptionConverter(e);
        }
        return name;
    }

    public void releaseTemplate(PdfTemplate tp)
        throws IOException
    {
        PdfIndirectReference ref = tp.getIndirectReference();
        Object objs[] = (Object[])formXObjects.get(ref);
        if(objs == null || objs[1] == null)
            return;
        PdfTemplate template = (PdfTemplate)objs[1];
        if(template.getIndirectReference() instanceof PRIndirectReference)
            return;
        if(template.getType() == 1)
        {
            addToBody(template.getFormXObject(compressionLevel), template.getIndirectReference());
            objs[1] = null;
        }
    }

    public PdfImportedPage getImportedPage(PdfReader reader, int pageNumber)
    {
        return getPdfReaderInstance(reader).getImportedPage(pageNumber);
    }

    protected PdfReaderInstance getPdfReaderInstance(PdfReader reader)
    {
        PdfReaderInstance inst = (PdfReaderInstance)readerInstances.get(reader);
        if(inst == null)
        {
            inst = reader.getPdfReaderInstance(this);
            readerInstances.put(reader, inst);
        }
        return inst;
    }

    public void freeReader(PdfReader reader)
        throws IOException
    {
        currentPdfReaderInstance = (PdfReaderInstance)readerInstances.get(reader);
        if(currentPdfReaderInstance == null)
        {
            return;
        } else
        {
            currentPdfReaderInstance.writeAllPages();
            currentPdfReaderInstance = null;
            readerInstances.remove(reader);
            return;
        }
    }

    public long getCurrentDocumentSize()
    {
        return body.offset() + (long)(body.size() * 20) + 72L;
    }

    protected int getNewObjectNumber(PdfReader reader, int number, int generation)
    {
        if(currentPdfReaderInstance == null)
            currentPdfReaderInstance = getPdfReaderInstance(reader);
        return currentPdfReaderInstance.getNewObjectNumber(number, generation);
    }

    RandomAccessFileOrArray getReaderFile(PdfReader reader)
    {
        return currentPdfReaderInstance.getReaderFile();
    }

    PdfName getColorspaceName()
    {
        return new PdfName((new StringBuilder()).append("CS").append(colorNumber++).toString());
    }

    ColorDetails addSimple(PdfSpotColor spc)
    {
        ColorDetails ret = (ColorDetails)documentColors.get(spc);
        if(ret == null)
        {
            ret = new ColorDetails(getColorspaceName(), body.getPdfIndirectReference(), spc);
            documentColors.put(spc, ret);
        }
        return ret;
    }

    PdfName addSimplePattern(PdfPatternPainter painter)
    {
        PdfName name = (PdfName)documentPatterns.get(painter);
        try
        {
            if(name == null)
            {
                name = new PdfName((new StringBuilder()).append("P").append(patternNumber).toString());
                patternNumber++;
                documentPatterns.put(painter, name);
            }
        }
        catch(Exception e)
        {
            throw new ExceptionConverter(e);
        }
        return name;
    }

    void addSimpleShadingPattern(PdfShadingPattern shading)
    {
        if(!documentShadingPatterns.contains(shading))
        {
            shading.setName(patternNumber);
            patternNumber++;
            documentShadingPatterns.add(shading);
            addSimpleShading(shading.getShading());
        }
    }

    void addSimpleShading(PdfShading shading)
    {
        if(!documentShadings.contains(shading))
        {
            documentShadings.add(shading);
            shading.setName(documentShadings.size());
        }
    }

    PdfObject[] addSimpleExtGState(PdfDictionary gstate)
    {
        if(!documentExtGState.containsKey(gstate))
            documentExtGState.put(gstate, new PdfObject[] {
                new PdfName((new StringBuilder()).append("GS").append(documentExtGState.size() + 1).toString()), getPdfIndirectReference()
            });
        return (PdfObject[])documentExtGState.get(gstate);
    }

    PdfObject[] addSimpleProperty(Object prop, PdfIndirectReference refi)
    {
        if(!documentProperties.containsKey(prop))
        {
            if(prop instanceof PdfOCG)
                checkPdfIsoConformance(this, 7, prop);
            documentProperties.put(prop, new PdfObject[] {
                new PdfName((new StringBuilder()).append("Pr").append(documentProperties.size() + 1).toString()), refi
            });
        }
        return (PdfObject[])documentProperties.get(prop);
    }

    boolean propertyExists(Object prop)
    {
        return documentProperties.containsKey(prop);
    }

    public void setTagged()
    {
        if(open)
        {
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("tagging.must.be.set.before.opening.the.document", new Object[0]));
        } else
        {
            tagged = true;
            return;
        }
    }

    public boolean isTagged()
    {
        return tagged;
    }

    protected void flushTaggedObjects()
        throws IOException
    {
    }

    protected void flushAcroFields()
        throws IOException, BadPdfFormatException
    {
    }

    public PdfStructureTreeRoot getStructureTreeRoot()
    {
        if(tagged && structureTreeRoot == null)
            structureTreeRoot = new PdfStructureTreeRoot(this);
        return structureTreeRoot;
    }

    public PdfOCProperties getOCProperties()
    {
        fillOCProperties(true);
        return OCProperties;
    }

    public void addOCGRadioGroup(ArrayList group)
    {
        PdfArray ar = new PdfArray();
        for(int k = 0; k < group.size(); k++)
        {
            PdfLayer layer = (PdfLayer)group.get(k);
            if(layer.getTitle() == null)
                ar.add(layer.getRef());
        }

        if(ar.size() == 0)
        {
            return;
        } else
        {
            OCGRadioGroup.add(ar);
            return;
        }
    }

    public void lockLayer(PdfLayer layer)
    {
        OCGLocked.add(layer.getRef());
    }

    private static void getOCGOrder(PdfArray order, PdfLayer layer)
    {
        if(!layer.isOnPanel())
            return;
        if(layer.getTitle() == null)
            order.add(layer.getRef());
        ArrayList children = layer.getChildren();
        if(children == null)
            return;
        PdfArray kids = new PdfArray();
        if(layer.getTitle() != null)
            kids.add(new PdfString(layer.getTitle(), "UnicodeBig"));
        for(int k = 0; k < children.size(); k++)
            getOCGOrder(kids, (PdfLayer)children.get(k));

        if(kids.size() > 0)
            order.add(kids);
    }

    private void addASEvent(PdfName event, PdfName category)
    {
        PdfArray arr = new PdfArray();
        Iterator i$ = documentOCG.iterator();
        do
        {
            if(!i$.hasNext())
                break;
            Object element = i$.next();
            PdfLayer layer = (PdfLayer)element;
            PdfDictionary usage = layer.getAsDict(PdfName.USAGE);
            if(usage != null && usage.get(category) != null)
                arr.add(layer.getRef());
        } while(true);
        if(arr.size() == 0)
            return;
        PdfDictionary d = OCProperties.getAsDict(PdfName.D);
        PdfArray arras = d.getAsArray(PdfName.AS);
        if(arras == null)
        {
            arras = new PdfArray();
            d.put(PdfName.AS, arras);
        }
        PdfDictionary as = new PdfDictionary();
        as.put(PdfName.EVENT, event);
        as.put(PdfName.CATEGORY, new PdfArray(category));
        as.put(PdfName.OCGS, arr);
        arras.add(as);
    }

    protected void fillOCProperties(boolean erase)
    {
        if(OCProperties == null)
            OCProperties = new PdfOCProperties();
        if(erase)
        {
            OCProperties.remove(PdfName.OCGS);
            OCProperties.remove(PdfName.D);
        }
        if(OCProperties.get(PdfName.OCGS) == null)
        {
            PdfArray gr = new PdfArray();
            PdfLayer layer;
            for(Iterator i$ = documentOCG.iterator(); i$.hasNext(); gr.add(layer.getRef()))
            {
                Object element = i$.next();
                layer = (PdfLayer)element;
            }

            OCProperties.put(PdfName.OCGS, gr);
        }
        if(OCProperties.get(PdfName.D) != null)
            return;
        ArrayList docOrder = new ArrayList(documentOCGorder);
        Iterator it = docOrder.iterator();
        do
        {
            if(!it.hasNext())
                break;
            PdfLayer layer = (PdfLayer)it.next();
            if(layer.getParent() != null)
                it.remove();
        } while(true);
        PdfArray order = new PdfArray();
        PdfLayer layer;
        for(Iterator i$ = docOrder.iterator(); i$.hasNext(); getOCGOrder(order, layer))
        {
            Object element = i$.next();
            layer = (PdfLayer)element;
        }

        PdfDictionary d = new PdfDictionary();
        OCProperties.put(PdfName.D, d);
        d.put(PdfName.ORDER, order);
        if(docOrder.size() > 0 && (docOrder.get(0) instanceof PdfLayer))
        {
            PdfLayer l = (PdfLayer)docOrder.get(0);
            PdfString name = l.getAsString(PdfName.NAME);
            if(name != null)
                d.put(PdfName.NAME, name);
        }
        PdfArray gr = new PdfArray();
        Iterator i$ = documentOCG.iterator();
        do
        {
            if(!i$.hasNext())
                break;
            Object element = i$.next();
            PdfLayer layer = (PdfLayer)element;
            if(!layer.isOn())
                gr.add(layer.getRef());
        } while(true);
        if(gr.size() > 0)
            d.put(PdfName.OFF, gr);
        if(OCGRadioGroup.size() > 0)
            d.put(PdfName.RBGROUPS, OCGRadioGroup);
        if(OCGLocked.size() > 0)
            d.put(PdfName.LOCKED, OCGLocked);
        addASEvent(PdfName.VIEW, PdfName.ZOOM);
        addASEvent(PdfName.VIEW, PdfName.VIEW);
        addASEvent(PdfName.PRINT, PdfName.PRINT);
        addASEvent(PdfName.EXPORT, PdfName.EXPORT);
        d.put(PdfName.LISTMODE, PdfName.VISIBLEPAGES);
    }

    void registerLayer(PdfOCG layer)
    {
        checkPdfIsoConformance(this, 7, layer);
        if(layer instanceof PdfLayer)
        {
            PdfLayer la = (PdfLayer)layer;
            if(la.getTitle() == null)
            {
                if(!documentOCG.contains(layer))
                {
                    documentOCG.add(layer);
                    documentOCGorder.add(layer);
                }
            } else
            {
                documentOCGorder.add(layer);
            }
        } else
        {
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("only.pdflayer.is.accepted", new Object[0]));
        }
    }

    public co.com.pdf.text.Rectangle getPageSize()
    {
        return pdf.getPageSize();
    }

    public void setCropBoxSize(co.com.pdf.text.Rectangle crop)
    {
        pdf.setCropBoxSize(crop);
    }

    public void setBoxSize(String boxName, co.com.pdf.text.Rectangle size)
    {
        pdf.setBoxSize(boxName, size);
    }

    public co.com.pdf.text.Rectangle getBoxSize(String boxName)
    {
        return pdf.getBoxSize(boxName);
    }

    public co.com.pdf.text.Rectangle getBoxSize(String boxName, co.com.pdf.text.Rectangle intersectingRectangle)
    {
        co.com.pdf.text.Rectangle pdfRectangle = pdf.getBoxSize(boxName);
        if(pdfRectangle == null || intersectingRectangle == null)
            return null;
        Rectangle boxRect = new Rectangle(pdfRectangle);
        Rectangle intRect = new Rectangle(intersectingRectangle);
        Rectangle outRect = boxRect.intersection(intRect);
        if(outRect.isEmpty())
        {
            return null;
        } else
        {
            co.com.pdf.text.Rectangle output = new co.com.pdf.text.Rectangle((float)outRect.getX(), (float)outRect.getY(), (float)(outRect.getX() + outRect.getWidth()), (float)(outRect.getY() + outRect.getHeight()));
            output.normalize();
            return output;
        }
    }

    public void setPageEmpty(boolean pageEmpty)
    {
        if(pageEmpty)
        {
            return;
        } else
        {
            pdf.setPageEmpty(pageEmpty);
            return;
        }
    }

    public boolean isPageEmpty()
    {
        return pdf.isPageEmpty();
    }

    public void setPageAction(PdfName actionType, PdfAction action)
        throws DocumentException
    {
        if(!actionType.equals(PAGE_OPEN) && !actionType.equals(PAGE_CLOSE))
        {
            throw new DocumentException(MessageLocalization.getComposedMessage("invalid.page.additional.action.type.1", new Object[] {
                actionType.toString()
            }));
        } else
        {
            pdf.setPageAction(actionType, action);
            return;
        }
    }

    public void setDuration(int seconds)
    {
        pdf.setDuration(seconds);
    }

    public void setTransition(PdfTransition transition)
    {
        pdf.setTransition(transition);
    }

    public void setThumbnail(Image image)
        throws PdfException, DocumentException
    {
        pdf.setThumbnail(image);
    }

    public PdfDictionary getGroup()
    {
        return group;
    }

    public void setGroup(PdfDictionary group)
    {
        this.group = group;
    }

    public float getSpaceCharRatio()
    {
        return spaceCharRatio;
    }

    public void setSpaceCharRatio(float spaceCharRatio)
    {
        if(spaceCharRatio < 0.001F)
            this.spaceCharRatio = 0.001F;
        else
            this.spaceCharRatio = spaceCharRatio;
    }

    public void setRunDirection(int runDirection)
    {
        if(runDirection < 1 || runDirection > 3)
        {
            throw new RuntimeException(MessageLocalization.getComposedMessage("invalid.run.direction.1", runDirection));
        } else
        {
            this.runDirection = runDirection;
            return;
        }
    }

    public int getRunDirection()
    {
        return runDirection;
    }

    public void setUserunit(float userunit)
        throws DocumentException
    {
        if(userunit < 1.0F || userunit > 75000F)
        {
            throw new DocumentException(MessageLocalization.getComposedMessage("userunit.should.be.a.value.between.1.and.75000", new Object[0]));
        } else
        {
            addPageDictEntry(PdfName.USERUNIT, new PdfNumber(userunit));
            setAtLeastPdfVersion('6');
            return;
        }
    }

    public PdfDictionary getDefaultColorspace()
    {
        return defaultColorspace;
    }

    public void setDefaultColorspace(PdfName key, PdfObject cs)
    {
        if(cs == null || cs.isNull())
            defaultColorspace.remove(key);
        defaultColorspace.put(key, cs);
    }

    ColorDetails addSimplePatternColorspace(BaseColor color)
    {
        int type;
        type = ExtendedColor.getType(color);
        if(type == 4 || type == 5)
            throw new RuntimeException(MessageLocalization.getComposedMessage("an.uncolored.tile.pattern.can.not.have.another.pattern.or.shading.as.color", new Object[0]));
        type;
        JVM INSTR tableswitch 0 3: default 364
    //                   0 64
    //                   1 198
    //                   2 131
    //                   3 265;
           goto _L1 _L2 _L3 _L4 _L5
_L2:
        if(patternColorspaceRGB == null)
        {
            patternColorspaceRGB = new ColorDetails(getColorspaceName(), body.getPdfIndirectReference(), null);
            PdfArray array = new PdfArray(PdfName.PATTERN);
            array.add(PdfName.DEVICERGB);
            addToBody(array, patternColorspaceRGB.getIndirectReference());
        }
        return patternColorspaceRGB;
_L4:
        try
        {
            if(patternColorspaceCMYK == null)
            {
                patternColorspaceCMYK = new ColorDetails(getColorspaceName(), body.getPdfIndirectReference(), null);
                PdfArray array = new PdfArray(PdfName.PATTERN);
                array.add(PdfName.DEVICECMYK);
                addToBody(array, patternColorspaceCMYK.getIndirectReference());
            }
            return patternColorspaceCMYK;
        }
        catch(Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
_L3:
        if(patternColorspaceGRAY == null)
        {
            patternColorspaceGRAY = new ColorDetails(getColorspaceName(), body.getPdfIndirectReference(), null);
            PdfArray array = new PdfArray(PdfName.PATTERN);
            array.add(PdfName.DEVICEGRAY);
            addToBody(array, patternColorspaceGRAY.getIndirectReference());
        }
        return patternColorspaceGRAY;
_L5:
        ColorDetails details = addSimple(((SpotColor)color).getPdfSpotColor());
        ColorDetails patternDetails = (ColorDetails)documentSpotPatterns.get(details);
        if(patternDetails == null)
        {
            patternDetails = new ColorDetails(getColorspaceName(), body.getPdfIndirectReference(), null);
            PdfArray array = new PdfArray(PdfName.PATTERN);
            array.add(details.getIndirectReference());
            addToBody(array, patternDetails.getIndirectReference());
            documentSpotPatterns.put(details, patternDetails);
        }
        return patternDetails;
_L1:
        throw new RuntimeException(MessageLocalization.getComposedMessage("invalid.color.type", new Object[0]));
    }

    public boolean isStrictImageSequence()
    {
        return pdf.isStrictImageSequence();
    }

    public void setStrictImageSequence(boolean strictImageSequence)
    {
        pdf.setStrictImageSequence(strictImageSequence);
    }

    public void clearTextWrap()
        throws DocumentException
    {
        pdf.clearTextWrap();
    }

    public PdfName addDirectImageSimple(Image image)
        throws PdfException, DocumentException
    {
        return addDirectImageSimple(image, null);
    }

    public PdfName addDirectImageSimple(Image image, PdfIndirectReference fixedRef)
        throws PdfException, DocumentException
    {
        PdfName name;
        if(images.containsKey(image.getMySerialId()))
        {
            name = (PdfName)images.get(image.getMySerialId());
        } else
        {
            if(image.isImgTemplate())
            {
                name = new PdfName((new StringBuilder()).append("img").append(images.size()).toString());
                if(image instanceof ImgWMF)
                    try
                    {
                        ImgWMF wmf = (ImgWMF)image;
                        wmf.readWMF(PdfTemplate.createTemplate(this, 0.0F, 0.0F));
                    }
                    catch(Exception e)
                    {
                        throw new DocumentException(e);
                    }
            } else
            {
                PdfIndirectReference dref = image.getDirectReference();
                if(dref != null)
                {
                    PdfName rname = new PdfName((new StringBuilder()).append("img").append(images.size()).toString());
                    images.put(image.getMySerialId(), rname);
                    imageDictionary.put(rname, dref);
                    return rname;
                }
                Image maskImage = image.getImageMask();
                PdfIndirectReference maskRef = null;
                if(maskImage != null)
                {
                    PdfName mname = (PdfName)images.get(maskImage.getMySerialId());
                    maskRef = getImageReference(mname);
                }
                PdfImage i = new PdfImage(image, (new StringBuilder()).append("img").append(images.size()).toString(), maskRef);
                if(image instanceof ImgJBIG2)
                {
                    byte globals[] = ((ImgJBIG2)image).getGlobalBytes();
                    if(globals != null)
                    {
                        PdfDictionary decodeparms = new PdfDictionary();
                        decodeparms.put(PdfName.JBIG2GLOBALS, getReferenceJBIG2Globals(globals));
                        i.put(PdfName.DECODEPARMS, decodeparms);
                    }
                }
                if(image.hasICCProfile())
                {
                    PdfICCBased icc = new PdfICCBased(image.getICCProfile(), image.getCompressionLevel());
                    PdfIndirectReference iccRef = add(icc);
                    PdfArray iccArray = new PdfArray();
                    iccArray.add(PdfName.ICCBASED);
                    iccArray.add(iccRef);
                    PdfArray colorspace = i.getAsArray(PdfName.COLORSPACE);
                    if(colorspace != null)
                    {
                        if(colorspace.size() > 1 && PdfName.INDEXED.equals(colorspace.getPdfObject(0)))
                            colorspace.set(1, iccArray);
                        else
                            i.put(PdfName.COLORSPACE, iccArray);
                    } else
                    {
                        i.put(PdfName.COLORSPACE, iccArray);
                    }
                }
                add(i, fixedRef);
                name = i.name();
            }
            images.put(image.getMySerialId(), name);
        }
        return name;
    }

    PdfIndirectReference add(PdfImage pdfImage, PdfIndirectReference fixedRef)
        throws PdfException
    {
        if(!imageDictionary.contains(pdfImage.name()))
        {
            checkPdfIsoConformance(this, 5, pdfImage);
            if(fixedRef instanceof PRIndirectReference)
            {
                PRIndirectReference r2 = (PRIndirectReference)fixedRef;
                fixedRef = new PdfIndirectReference(0, getNewObjectNumber(r2.getReader(), r2.getNumber(), r2.getGeneration()));
            }
            try
            {
                if(fixedRef == null)
                    fixedRef = addToBody(pdfImage).getIndirectReference();
                else
                    addToBody(pdfImage, fixedRef);
            }
            catch(IOException ioe)
            {
                throw new ExceptionConverter(ioe);
            }
            imageDictionary.put(pdfImage.name(), fixedRef);
            return fixedRef;
        } else
        {
            return (PdfIndirectReference)imageDictionary.get(pdfImage.name());
        }
    }

    PdfIndirectReference getImageReference(PdfName name)
    {
        return (PdfIndirectReference)imageDictionary.get(name);
    }

    protected PdfIndirectReference add(PdfICCBased icc)
    {
        PdfIndirectObject object;
        try
        {
            object = addToBody(icc);
        }
        catch(IOException ioe)
        {
            throw new ExceptionConverter(ioe);
        }
        return object.getIndirectReference();
    }

    protected PdfIndirectReference getReferenceJBIG2Globals(byte content[])
    {
        if(content == null)
            return null;
        for(Iterator i$ = JBIG2Globals.keySet().iterator(); i$.hasNext();)
        {
            PdfStream stream = (PdfStream)i$.next();
            if(Arrays.equals(content, stream.getBytes()))
                return (PdfIndirectReference)JBIG2Globals.get(stream);
        }

        PdfStream stream = new PdfStream(content);
        PdfIndirectObject ref;
        try
        {
            ref = addToBody(stream);
        }
        catch(IOException e)
        {
            return null;
        }
        JBIG2Globals.put(stream, ref.getIndirectReference());
        return ref.getIndirectReference();
    }

    public boolean isUserProperties()
    {
        return userProperties;
    }

    public void setUserProperties(boolean userProperties)
    {
        this.userProperties = userProperties;
    }

    public boolean isRgbTransparencyBlending()
    {
        return rgbTransparencyBlending;
    }

    public void setRgbTransparencyBlending(boolean rgbTransparencyBlending)
    {
        this.rgbTransparencyBlending = rgbTransparencyBlending;
    }

    protected static void writeKeyInfo(OutputStream os)
        throws IOException
    {
        Version version = Version.getInstance();
        String k = version.getKey();
        if(k == null)
            k = "iText";
        os.write(getISOBytes(String.format("%%%s-%s\n", new Object[] {
            k, version.getRelease()
        })));
    }

    protected TtfUnicodeWriter getTtfUnicodeWriter()
    {
        if(ttfUnicodeWriter == null)
            ttfUnicodeWriter = new TtfUnicodeWriter(this);
        return ttfUnicodeWriter;
    }

    protected XmpWriter createXmpWriter(ByteArrayOutputStream baos, PdfDictionary info)
        throws IOException
    {
        return new XmpWriter(baos, info);
    }

    protected XmpWriter createXmpWriter(ByteArrayOutputStream baos, HashMap info)
        throws IOException
    {
        return new XmpWriter(baos, info);
    }

    public static void checkPdfIsoConformance(PdfWriter writer, int key, Object obj1)
    {
        if(writer != null)
            writer.checkPdfIsoConformance(key, obj1);
    }

    public void checkPdfIsoConformance(int key, Object obj1)
    {
        pdfIsoConformance.checkPdfIsoConformance(key, obj1);
    }

    private void completeInfoDictionary(PdfDictionary info)
    {
        if(isPdfX())
        {
            if(info.get(PdfName.GTS_PDFXVERSION) == null)
                if(((PdfXConformanceImp)pdfIsoConformance).isPdfX1A2001())
                {
                    info.put(PdfName.GTS_PDFXVERSION, new PdfString("PDF/X-1:2001"));
                    info.put(new PdfName("GTS_PDFXConformance"), new PdfString("PDF/X-1a:2001"));
                } else
                if(((PdfXConformanceImp)pdfIsoConformance).isPdfX32002())
                    info.put(PdfName.GTS_PDFXVERSION, new PdfString("PDF/X-3:2002"));
            if(info.get(PdfName.TITLE) == null)
                info.put(PdfName.TITLE, new PdfString("Pdf document"));
            if(info.get(PdfName.CREATOR) == null)
                info.put(PdfName.CREATOR, new PdfString("Unknown"));
            if(info.get(PdfName.TRAPPED) == null)
                info.put(PdfName.TRAPPED, new PdfName("False"));
        }
    }

    private void completeExtraCatalog(PdfDictionary extraCatalog)
    {
        if(isPdfX() && extraCatalog.get(PdfName.OUTPUTINTENTS) == null)
        {
            PdfDictionary out = new PdfDictionary(PdfName.OUTPUTINTENT);
            out.put(PdfName.OUTPUTCONDITION, new PdfString("SWOP CGATS TR 001-1995"));
            out.put(PdfName.OUTPUTCONDITIONIDENTIFIER, new PdfString("CGATS TR 001"));
            out.put(PdfName.REGISTRYNAME, new PdfString("http://www.color.org"));
            out.put(PdfName.INFO, new PdfString(""));
            out.put(PdfName.S, PdfName.GTS_PDFX);
            extraCatalog.put(PdfName.OUTPUTINTENTS, new PdfArray(out));
        }
    }

    public List getStandardStructElems()
    {
        if(pdf_version.getVersion() < '7')
            return standardStructElems_1_4;
        else
            return standardStructElems_1_7;
    }

    public static final int GENERATION_MAX = 65535;
    protected static Counter COUNTER = CounterFactory.getCounter(co/com/pdf/text/pdf/PdfWriter);
    protected PdfDocument pdf;
    protected PdfContentByte directContent;
    protected PdfContentByte directContentUnder;
    protected PdfBody body;
    protected PdfDictionary extraCatalog;
    protected PdfPages root;
    protected ArrayList pageReferences;
    protected int currentPageNumber;
    protected PdfName tabs;
    protected PdfDictionary pageDictEntries;
    private PdfPageEvent pageEvent;
    protected long prevxref;
    protected List newBookmarks;
    public static final char VERSION_1_2 = 50;
    public static final char VERSION_1_3 = 51;
    public static final char VERSION_1_4 = 52;
    public static final char VERSION_1_5 = 53;
    public static final char VERSION_1_6 = 54;
    public static final char VERSION_1_7 = 55;
    public static final PdfName PDF_VERSION_1_2 = new PdfName("1.2");
    public static final PdfName PDF_VERSION_1_3 = new PdfName("1.3");
    public static final PdfName PDF_VERSION_1_4 = new PdfName("1.4");
    public static final PdfName PDF_VERSION_1_5 = new PdfName("1.5");
    public static final PdfName PDF_VERSION_1_6 = new PdfName("1.6");
    public static final PdfName PDF_VERSION_1_7 = new PdfName("1.7");
    protected PdfVersionImp pdf_version;
    public static final int PageLayoutSinglePage = 1;
    public static final int PageLayoutOneColumn = 2;
    public static final int PageLayoutTwoColumnLeft = 4;
    public static final int PageLayoutTwoColumnRight = 8;
    public static final int PageLayoutTwoPageLeft = 16;
    public static final int PageLayoutTwoPageRight = 32;
    public static final int PageModeUseNone = 64;
    public static final int PageModeUseOutlines = 128;
    public static final int PageModeUseThumbs = 256;
    public static final int PageModeFullScreen = 512;
    public static final int PageModeUseOC = 1024;
    public static final int PageModeUseAttachments = 2048;
    public static final int HideToolbar = 4096;
    public static final int HideMenubar = 8192;
    public static final int HideWindowUI = 16384;
    public static final int FitWindow = 32768;
    public static final int CenterWindow = 0x10000;
    public static final int DisplayDocTitle = 0x20000;
    public static final int NonFullScreenPageModeUseNone = 0x40000;
    public static final int NonFullScreenPageModeUseOutlines = 0x80000;
    public static final int NonFullScreenPageModeUseThumbs = 0x100000;
    public static final int NonFullScreenPageModeUseOC = 0x200000;
    public static final int DirectionL2R = 0x400000;
    public static final int DirectionR2L = 0x800000;
    public static final int PrintScalingNone = 0x1000000;
    public static final PdfName DOCUMENT_CLOSE;
    public static final PdfName WILL_SAVE;
    public static final PdfName DID_SAVE;
    public static final PdfName WILL_PRINT;
    public static final PdfName DID_PRINT;
    public static final int SIGNATURE_EXISTS = 1;
    public static final int SIGNATURE_APPEND_ONLY = 2;
    protected byte xmpMetadata[];
    protected XmpWriter xmpWriter;
    public static final int PDFXNONE = 0;
    public static final int PDFX1A2001 = 1;
    public static final int PDFX32002 = 2;
    protected PdfIsoConformance pdfIsoConformance;
    public static final int STANDARD_ENCRYPTION_40 = 0;
    public static final int STANDARD_ENCRYPTION_128 = 1;
    public static final int ENCRYPTION_AES_128 = 2;
    public static final int ENCRYPTION_AES_256 = 3;
    static final int ENCRYPTION_MASK = 7;
    public static final int DO_NOT_ENCRYPT_METADATA = 8;
    public static final int EMBEDDED_FILES_ONLY = 24;
    public static final int ALLOW_PRINTING = 2052;
    public static final int ALLOW_MODIFY_CONTENTS = 8;
    public static final int ALLOW_COPY = 16;
    public static final int ALLOW_MODIFY_ANNOTATIONS = 32;
    public static final int ALLOW_FILL_IN = 256;
    public static final int ALLOW_SCREENREADERS = 512;
    public static final int ALLOW_ASSEMBLY = 1024;
    public static final int ALLOW_DEGRADED_PRINTING = 4;
    /**
     * @deprecated Field AllowPrinting is deprecated
     */
    public static final int AllowPrinting = 2052;
    /**
     * @deprecated Field AllowModifyContents is deprecated
     */
    public static final int AllowModifyContents = 8;
    /**
     * @deprecated Field AllowCopy is deprecated
     */
    public static final int AllowCopy = 16;
    /**
     * @deprecated Field AllowModifyAnnotations is deprecated
     */
    public static final int AllowModifyAnnotations = 32;
    /**
     * @deprecated Field AllowFillIn is deprecated
     */
    public static final int AllowFillIn = 256;
    /**
     * @deprecated Field AllowScreenReaders is deprecated
     */
    public static final int AllowScreenReaders = 512;
    /**
     * @deprecated Field AllowAssembly is deprecated
     */
    public static final int AllowAssembly = 1024;
    /**
     * @deprecated Field AllowDegradedPrinting is deprecated
     */
    public static final int AllowDegradedPrinting = 4;
    /**
     * @deprecated Field STRENGTH40BITS is deprecated
     */
    public static final boolean STRENGTH40BITS = false;
    /**
     * @deprecated Field STRENGTH128BITS is deprecated
     */
    public static final boolean STRENGTH128BITS = true;
    protected PdfEncryption crypto;
    protected boolean fullCompression;
    protected int compressionLevel;
    protected LinkedHashMap documentFonts;
    protected int fontNumber;
    protected HashMap formXObjects;
    protected int formXObjectsCounter;
    protected HashMap readerInstances;
    protected PdfReaderInstance currentPdfReaderInstance;
    protected HashMap documentColors;
    protected int colorNumber;
    protected HashMap documentPatterns;
    protected int patternNumber;
    protected HashSet documentShadingPatterns;
    protected HashSet documentShadings;
    protected HashMap documentExtGState;
    protected HashMap documentProperties;
    protected boolean tagged;
    protected PdfStructureTreeRoot structureTreeRoot;
    protected HashSet documentOCG;
    protected ArrayList documentOCGorder;
    protected PdfOCProperties OCProperties;
    protected PdfArray OCGRadioGroup;
    protected PdfArray OCGLocked;
    public static final PdfName PAGE_OPEN;
    public static final PdfName PAGE_CLOSE;
    protected PdfDictionary group;
    public static final float SPACE_CHAR_RATIO_DEFAULT = 2.5F;
    public static final float NO_SPACE_CHAR_RATIO = 1E+007F;
    private float spaceCharRatio;
    public static final int RUN_DIRECTION_DEFAULT = 0;
    public static final int RUN_DIRECTION_NO_BIDI = 1;
    public static final int RUN_DIRECTION_LTR = 2;
    public static final int RUN_DIRECTION_RTL = 3;
    protected int runDirection;
    protected PdfDictionary defaultColorspace;
    protected HashMap documentSpotPatterns;
    protected ColorDetails patternColorspaceRGB;
    protected ColorDetails patternColorspaceGRAY;
    protected ColorDetails patternColorspaceCMYK;
    protected PdfDictionary imageDictionary;
    private final HashMap images;
    protected HashMap JBIG2Globals;
    private boolean userProperties;
    private boolean rgbTransparencyBlending;
    protected TtfUnicodeWriter ttfUnicodeWriter;
    private static final List standardStructElems_1_4;
    private static final List standardStructElems_1_7;

    static 
    {
        DOCUMENT_CLOSE = PdfName.WC;
        WILL_SAVE = PdfName.WS;
        DID_SAVE = PdfName.DS;
        WILL_PRINT = PdfName.WP;
        DID_PRINT = PdfName.DP;
        PAGE_OPEN = PdfName.O;
        PAGE_CLOSE = PdfName.C;
        standardStructElems_1_4 = Arrays.asList(new PdfName[] {
            PdfName.DOCUMENT, PdfName.PART, PdfName.ART, PdfName.SECT, PdfName.DIV, PdfName.BLOCKQUOTE, PdfName.CAPTION, PdfName.TOC, PdfName.TOCI, PdfName.INDEX, 
            PdfName.NONSTRUCT, PdfName.PRIVATE, PdfName.P, PdfName.H, PdfName.H1, PdfName.H2, PdfName.H3, PdfName.H4, PdfName.H5, PdfName.H6, 
            PdfName.L, PdfName.LBL, PdfName.LI, PdfName.LBODY, PdfName.TABLE, PdfName.TR, PdfName.TH, PdfName.TD, PdfName.SPAN, PdfName.QUOTE, 
            PdfName.NOTE, PdfName.REFERENCE, PdfName.BIBENTRY, PdfName.CODE, PdfName.LINK, PdfName.FIGURE, PdfName.FORMULA, PdfName.FORM
        });
        standardStructElems_1_7 = Arrays.asList(new PdfName[] {
            PdfName.DOCUMENT, PdfName.PART, PdfName.ART, PdfName.SECT, PdfName.DIV, PdfName.BLOCKQUOTE, PdfName.CAPTION, PdfName.TOC, PdfName.TOCI, PdfName.INDEX, 
            PdfName.NONSTRUCT, PdfName.PRIVATE, PdfName.P, PdfName.H, PdfName.H1, PdfName.H2, PdfName.H3, PdfName.H4, PdfName.H5, PdfName.H6, 
            PdfName.L, PdfName.LBL, PdfName.LI, PdfName.LBODY, PdfName.TABLE, PdfName.TR, PdfName.TH, PdfName.TD, PdfName.THEAD, PdfName.TBODY, 
            PdfName.TFOOT, PdfName.SPAN, PdfName.QUOTE, PdfName.NOTE, PdfName.REFERENCE, PdfName.BIBENTRY, PdfName.CODE, PdfName.LINK, PdfName.ANNOT, PdfName.RUBY, 
            PdfName.RB, PdfName.RT, PdfName.RP, PdfName.WARICHU, PdfName.WT, PdfName.WP, PdfName.FIGURE, PdfName.FORMULA, PdfName.FORM
        });
    }
}
