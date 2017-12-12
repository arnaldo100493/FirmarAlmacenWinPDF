// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfReader.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.*;
import co.com.pdf.text.error_messages.MessageLocalization;
import co.com.pdf.text.exceptions.*;
import co.com.pdf.text.io.*;
import co.com.pdf.text.log.Counter;
import co.com.pdf.text.log.CounterFactory;
import co.com.pdf.text.pdf.interfaces.PdfViewerPreferences;
import co.com.pdf.text.pdf.internal.PdfViewerPreferencesImp;
import co.org.bouncy.cert.X509CertificateHolder;
import co.org.bouncy.cms.*;
import java.io.*;
import java.net.URL;
import java.security.*;
import java.security.cert.Certificate;
import java.util.*;
import java.util.zip.InflaterInputStream;

// Referenced classes of package co.com.pdf.text.pdf:
//            PRTokeniser, PdfEncryption, PdfObject, PdfDictionary, 
//            PRIndirectReference, RandomAccessFileOrArray, PdfReaderInstance, PRAcroForm, 
//            PdfArray, PdfName, PdfString, PdfNumber, 
//            PdfNull, PdfBoolean, PRStream, IntHashtable, 
//            LongHashtable, PdfLiteral, LZWDecoder, PdfIndirectReference, 
//            AcroFields, PdfEncryptor, PdfEncodings, FilterHandlers, 
//            BaseFont, PdfNameTree, PdfAnnotation, SequenceList, 
//            PdfWriter

public class PdfReader
    implements PdfViewerPreferences
{
    static class PageRefs
    {

        int size()
        {
            if(refsn != null)
                return refsn.size();
            else
                return sizep;
        }

        void readPages()
            throws IOException
        {
            if(refsn != null)
            {
                return;
            } else
            {
                refsp = null;
                refsn = new ArrayList();
                pageInh = new ArrayList();
                iteratePages((PRIndirectReference)reader.catalog.get(PdfName.PAGES));
                pageInh = null;
                reader.rootPages.put(PdfName.COUNT, new PdfNumber(refsn.size()));
                return;
            }
        }

        void reReadPages()
            throws IOException
        {
            refsn = null;
            readPages();
        }

        public PdfDictionary getPageN(int pageNum)
        {
            PRIndirectReference ref = getPageOrigRef(pageNum);
            return (PdfDictionary)PdfReader.getPdfObject(ref);
        }

        public PdfDictionary getPageNRelease(int pageNum)
        {
            PdfDictionary page = getPageN(pageNum);
            releasePage(pageNum);
            return page;
        }

        public PRIndirectReference getPageOrigRefRelease(int pageNum)
        {
            PRIndirectReference ref = getPageOrigRef(pageNum);
            releasePage(pageNum);
            return ref;
        }

        public PRIndirectReference getPageOrigRef(int pageNum)
        {
            int n;
            try
            {
                if(--pageNum < 0 || pageNum >= size())
                    return null;
            }
            catch(Exception e)
            {
                throw new ExceptionConverter(e);
            }
            if(refsn != null)
                return (PRIndirectReference)refsn.get(pageNum);
            n = refsp.get(pageNum);
            if(n == 0)
            {
                PRIndirectReference ref = getSinglePage(pageNum);
                if(reader.lastXrefPartial == -1)
                    lastPageRead = -1;
                else
                    lastPageRead = pageNum;
                reader.lastXrefPartial = -1;
                refsp.put(pageNum, ref.getNumber());
                if(keepPages)
                    lastPageRead = -1;
                return ref;
            }
            if(lastPageRead != pageNum)
                lastPageRead = -1;
            if(keepPages)
                lastPageRead = -1;
            return new PRIndirectReference(reader, n);
        }

        void keepPages()
        {
            if(refsp == null || keepPages)
            {
                return;
            } else
            {
                keepPages = true;
                refsp.clear();
                return;
            }
        }

        public void releasePage(int pageNum)
        {
            if(refsp == null)
                return;
            if(--pageNum < 0 || pageNum >= size())
                return;
            if(pageNum != lastPageRead)
            {
                return;
            } else
            {
                lastPageRead = -1;
                reader.lastXrefPartial = refsp.get(pageNum);
                reader.releaseLastXrefPartial();
                refsp.remove(pageNum);
                return;
            }
        }

        public void resetReleasePage()
        {
            if(refsp == null)
            {
                return;
            } else
            {
                lastPageRead = -1;
                return;
            }
        }

        void insertPage(int pageNum, PRIndirectReference ref)
        {
            pageNum--;
            if(refsn != null)
            {
                if(pageNum >= refsn.size())
                    refsn.add(ref);
                else
                    refsn.add(pageNum, ref);
            } else
            {
                sizep++;
                lastPageRead = -1;
                if(pageNum >= size())
                {
                    refsp.put(size(), ref.getNumber());
                } else
                {
                    IntHashtable refs2 = new IntHashtable((refsp.size() + 1) * 2);
                    IntHashtable.Entry entry;
                    int p;
                    for(Iterator it = refsp.getEntryIterator(); it.hasNext(); refs2.put(p < pageNum ? p : p + 1, entry.getValue()))
                    {
                        entry = (IntHashtable.Entry)it.next();
                        p = entry.getKey();
                    }

                    refs2.put(pageNum, ref.getNumber());
                    refsp = refs2;
                }
            }
        }

        private void pushPageAttributes(PdfDictionary nodePages)
        {
            PdfDictionary dic = new PdfDictionary();
            if(!pageInh.isEmpty())
                dic.putAll((PdfDictionary)pageInh.get(pageInh.size() - 1));
            for(int k = 0; k < PdfReader.pageInhCandidates.length; k++)
            {
                PdfObject obj = nodePages.get(PdfReader.pageInhCandidates[k]);
                if(obj != null)
                    dic.put(PdfReader.pageInhCandidates[k], obj);
            }

            pageInh.add(dic);
        }

        private void popPageAttributes()
        {
            pageInh.remove(pageInh.size() - 1);
        }

        private void iteratePages(PRIndirectReference rpage)
            throws IOException
        {
            PdfDictionary page = (PdfDictionary)PdfReader.getPdfObject(rpage);
            if(page == null)
                return;
            PdfArray kidsPR = page.getAsArray(PdfName.KIDS);
            if(kidsPR == null)
            {
                page.put(PdfName.TYPE, PdfName.PAGE);
                PdfDictionary dic = (PdfDictionary)pageInh.get(pageInh.size() - 1);
                Iterator i$ = dic.getKeys().iterator();
                do
                {
                    if(!i$.hasNext())
                        break;
                    Object element = i$.next();
                    PdfName key = (PdfName)element;
                    if(page.get(key) == null)
                        page.put(key, dic.get(key));
                } while(true);
                if(page.get(PdfName.MEDIABOX) == null)
                {
                    PdfArray arr = new PdfArray(new float[] {
                        0.0F, 0.0F, PageSize.LETTER.getRight(), PageSize.LETTER.getTop()
                    });
                    page.put(PdfName.MEDIABOX, arr);
                }
                refsn.add(rpage);
            } else
            {
                page.put(PdfName.TYPE, PdfName.PAGES);
                pushPageAttributes(page);
                int k = 0;
                do
                {
                    if(k >= kidsPR.size())
                        break;
                    PdfObject obj = kidsPR.getPdfObject(k);
                    if(!obj.isIndirect())
                    {
                        for(; k < kidsPR.size(); kidsPR.remove(k));
                        break;
                    }
                    iteratePages((PRIndirectReference)obj);
                    k++;
                } while(true);
                popPageAttributes();
            }
        }

        protected PRIndirectReference getSinglePage(int n)
        {
            PdfDictionary acc;
            PdfDictionary top;
            int base;
            acc = new PdfDictionary();
            top = reader.rootPages;
            base = 0;
_L2:
            for(int k = 0; k < PdfReader.pageInhCandidates.length; k++)
            {
                PdfObject obj = top.get(PdfReader.pageInhCandidates[k]);
                if(obj != null)
                    acc.put(PdfReader.pageInhCandidates[k], obj);
            }

            PdfArray kids = (PdfArray)PdfReader.getPdfObjectRelease(top.get(PdfName.KIDS));
            Iterator it = kids.listIterator();
            do
            {
                int acn;
                if(it.hasNext())
                {
label0:
                    {
                        PRIndirectReference ref = (PRIndirectReference)it.next();
                        PdfDictionary dic = (PdfDictionary)PdfReader.getPdfObject(ref);
                        int last = reader.lastXrefPartial;
                        PdfObject count = PdfReader.getPdfObjectRelease(dic.get(PdfName.COUNT));
                        reader.lastXrefPartial = last;
                        acn = 1;
                        if(count != null && count.type() == 2)
                            acn = ((PdfNumber)count).intValue();
                        if(n >= base + acn)
                            break label0;
                        if(count == null)
                        {
                            dic.mergeDifferent(acc);
                            return ref;
                        }
                        reader.releaseLastXrefPartial();
                        top = dic;
                    }
                }
                if(true)
                    continue;
                reader.releaseLastXrefPartial();
                base += acn;
            } while(true);
            if(true) goto _L2; else goto _L1
_L1:
        }

        private void selectPages(List pagesToKeep)
        {
            IntHashtable pg = new IntHashtable();
            ArrayList finalPages = new ArrayList();
            int psize = size();
            Iterator i$ = pagesToKeep.iterator();
            do
            {
                if(!i$.hasNext())
                    break;
                Integer pi = (Integer)i$.next();
                int p = pi.intValue();
                if(p >= 1 && p <= psize && pg.put(p, 1) == 0)
                    finalPages.add(pi);
            } while(true);
            if(reader.partial)
            {
                for(int k = 1; k <= psize; k++)
                {
                    getPageOrigRef(k);
                    resetReleasePage();
                }

            }
            PRIndirectReference parent = (PRIndirectReference)reader.catalog.get(PdfName.PAGES);
            PdfDictionary topPages = (PdfDictionary)PdfReader.getPdfObject(parent);
            ArrayList newPageRefs = new ArrayList(finalPages.size());
            PdfArray kids = new PdfArray();
            for(int k = 0; k < finalPages.size(); k++)
            {
                int p = ((Integer)finalPages.get(k)).intValue();
                PRIndirectReference pref = getPageOrigRef(p);
                resetReleasePage();
                kids.add(pref);
                newPageRefs.add(pref);
                getPageN(p).put(PdfName.PARENT, parent);
            }

            AcroFields af = reader.getAcroFields();
            boolean removeFields = af.getFields().size() > 0;
            for(int k = 1; k <= psize; k++)
            {
                if(pg.containsKey(k))
                    continue;
                if(removeFields)
                    af.removeFieldsFromPage(k);
                PRIndirectReference pref = getPageOrigRef(k);
                int nref = pref.getNumber();
                reader.xrefObj.set(nref, null);
                if(reader.partial)
                {
                    reader.xref[nref * 2] = -1L;
                    reader.xref[nref * 2 + 1] = 0L;
                }
            }

            topPages.put(PdfName.COUNT, new PdfNumber(finalPages.size()));
            topPages.put(PdfName.KIDS, kids);
            refsp = null;
            refsn = newPageRefs;
        }

        private final PdfReader reader;
        private ArrayList refsn;
        private int sizep;
        private IntHashtable refsp;
        private int lastPageRead;
        private ArrayList pageInh;
        private boolean keepPages;


        private PageRefs(PdfReader reader)
            throws IOException
        {
            lastPageRead = -1;
            this.reader = reader;
            if(reader.partial)
            {
                refsp = new IntHashtable();
                PdfNumber npages = (PdfNumber)PdfReader.getPdfObjectRelease(reader.rootPages.get(PdfName.COUNT));
                sizep = npages.intValue();
            } else
            {
                readPages();
            }
        }

        PageRefs(PageRefs other, PdfReader reader)
        {
            lastPageRead = -1;
            this.reader = reader;
            sizep = other.sizep;
            if(other.refsn != null)
            {
                refsn = new ArrayList(other.refsn);
                for(int k = 0; k < refsn.size(); k++)
                    refsn.set(k, (PRIndirectReference)PdfReader.duplicatePdfObject((PdfObject)refsn.get(k), reader));

            } else
            {
                refsp = (IntHashtable)other.refsp.clone();
            }
        }

    }


    protected Counter getCounter()
    {
        return COUNTER;
    }

    private PdfReader(RandomAccessSource byteSource, boolean partialRead, byte ownerPassword[], Certificate certificate, Key certificateKey, String certificateKeyProvider, boolean closeSourceOnConstructorError)
        throws IOException
    {
        acroForm = null;
        acroFormParsed = false;
        encrypted = false;
        rebuilt = false;
        tampered = false;
        password = null;
        this.certificateKey = null;
        this.certificate = null;
        this.certificateKeyProvider = null;
        strings = new ArrayList();
        sharedStreams = true;
        consolidateNamedDestinations = false;
        remoteToLocalNamedDestinations = false;
        lastXrefPartial = -1;
        viewerPreferences = new PdfViewerPreferencesImp();
        readDepth = 0;
        this.certificate = certificate;
        this.certificateKey = certificateKey;
        this.certificateKeyProvider = certificateKeyProvider;
        password = ownerPassword;
        partial = partialRead;
        try
        {
            tokens = getOffsetTokeniser(byteSource);
            if(partialRead)
                readPdfPartial();
            else
                readPdf();
        }
        catch(IOException e)
        {
            if(closeSourceOnConstructorError)
                byteSource.close();
            throw e;
        }
        getCounter().read(fileLength);
    }

    public PdfReader(String filename)
        throws IOException
    {
        this(filename, null);
    }

    public PdfReader(String filename, byte ownerPassword[])
        throws IOException
    {
        this(filename, ownerPassword, false);
    }

    public PdfReader(String filename, byte ownerPassword[], boolean partial)
        throws IOException
    {
        this((new RandomAccessSourceFactory()).setForceRead(false).setUsePlainRandomAccess(Document.plainRandomAccess).createBestSource(filename), partial, ownerPassword, null, null, null, true);
    }

    public PdfReader(byte pdfIn[])
        throws IOException
    {
        this(pdfIn, null);
    }

    public PdfReader(byte pdfIn[], byte ownerPassword[])
        throws IOException
    {
        this((new RandomAccessSourceFactory()).createSource(pdfIn), false, ownerPassword, null, null, null, true);
    }

    public PdfReader(String filename, Certificate certificate, Key certificateKey, String certificateKeyProvider)
        throws IOException
    {
        this((new RandomAccessSourceFactory()).setForceRead(false).setUsePlainRandomAccess(Document.plainRandomAccess).createBestSource(filename), false, null, certificate, certificateKey, certificateKeyProvider, true);
    }

    public PdfReader(URL url)
        throws IOException
    {
        this(url, null);
    }

    public PdfReader(URL url, byte ownerPassword[])
        throws IOException
    {
        this((new RandomAccessSourceFactory()).createSource(url), false, ownerPassword, null, null, null, true);
    }

    public PdfReader(InputStream is, byte ownerPassword[])
        throws IOException
    {
        this((new RandomAccessSourceFactory()).createSource(is), false, ownerPassword, null, null, null, false);
    }

    public PdfReader(InputStream is)
        throws IOException
    {
        this(is, null);
    }

    public PdfReader(RandomAccessFileOrArray raf, byte ownerPassword[])
        throws IOException
    {
        this(raf.getByteSource(), true, ownerPassword, null, null, null, false);
    }

    public PdfReader(PdfReader reader)
    {
        acroForm = null;
        acroFormParsed = false;
        encrypted = false;
        rebuilt = false;
        tampered = false;
        password = null;
        certificateKey = null;
        certificate = null;
        certificateKeyProvider = null;
        strings = new ArrayList();
        sharedStreams = true;
        consolidateNamedDestinations = false;
        remoteToLocalNamedDestinations = false;
        lastXrefPartial = -1;
        viewerPreferences = new PdfViewerPreferencesImp();
        readDepth = 0;
        appendable = reader.appendable;
        consolidateNamedDestinations = reader.consolidateNamedDestinations;
        encrypted = reader.encrypted;
        rebuilt = reader.rebuilt;
        sharedStreams = reader.sharedStreams;
        tampered = reader.tampered;
        password = reader.password;
        pdfVersion = reader.pdfVersion;
        eofPos = reader.eofPos;
        freeXref = reader.freeXref;
        lastXref = reader.lastXref;
        newXrefType = reader.newXrefType;
        tokens = new PRTokeniser(reader.tokens.getSafeFile());
        if(reader.decrypt != null)
            decrypt = new PdfEncryption(reader.decrypt);
        pValue = reader.pValue;
        rValue = reader.rValue;
        xrefObj = new ArrayList(reader.xrefObj);
        for(int k = 0; k < reader.xrefObj.size(); k++)
            xrefObj.set(k, duplicatePdfObject((PdfObject)reader.xrefObj.get(k), this));

        pageRefs = new PageRefs(reader.pageRefs, this);
        trailer = (PdfDictionary)duplicatePdfObject(reader.trailer, this);
        catalog = trailer.getAsDict(PdfName.ROOT);
        rootPages = catalog.getAsDict(PdfName.PAGES);
        fileLength = reader.fileLength;
        partial = reader.partial;
        hybridXref = reader.hybridXref;
        objStmToOffset = reader.objStmToOffset;
        xref = reader.xref;
        cryptoRef = (PRIndirectReference)duplicatePdfObject(reader.cryptoRef, this);
        ownerPasswordUsed = reader.ownerPasswordUsed;
    }

    private static PRTokeniser getOffsetTokeniser(RandomAccessSource byteSource)
        throws IOException
    {
        PRTokeniser tok = new PRTokeniser(new RandomAccessFileOrArray(byteSource));
        int offset = tok.getHeaderOffset();
        if(offset != 0)
        {
            RandomAccessSource offsetSource = new WindowRandomAccessSource(byteSource, offset);
            tok = new PRTokeniser(new RandomAccessFileOrArray(offsetSource));
        }
        return tok;
    }

    public RandomAccessFileOrArray getSafeFile()
    {
        return tokens.getSafeFile();
    }

    protected PdfReaderInstance getPdfReaderInstance(PdfWriter writer)
    {
        return new PdfReaderInstance(this, writer);
    }

    public int getNumberOfPages()
    {
        return pageRefs.size();
    }

    public PdfDictionary getCatalog()
    {
        return catalog;
    }

    public PRAcroForm getAcroForm()
    {
        if(!acroFormParsed)
        {
            acroFormParsed = true;
            PdfObject form = catalog.get(PdfName.ACROFORM);
            if(form != null)
                try
                {
                    acroForm = new PRAcroForm(this);
                    acroForm.readAcroForm((PdfDictionary)getPdfObject(form));
                }
                catch(Exception e)
                {
                    acroForm = null;
                }
        }
        return acroForm;
    }

    public int getPageRotation(int index)
    {
        return getPageRotation(pageRefs.getPageNRelease(index));
    }

    int getPageRotation(PdfDictionary page)
    {
        PdfNumber rotate = page.getAsNumber(PdfName.ROTATE);
        if(rotate == null)
        {
            return 0;
        } else
        {
            int n = rotate.intValue();
            n %= 360;
            return n >= 0 ? n : n + 360;
        }
    }

    public Rectangle getPageSizeWithRotation(int index)
    {
        return getPageSizeWithRotation(pageRefs.getPageNRelease(index));
    }

    public Rectangle getPageSizeWithRotation(PdfDictionary page)
    {
        Rectangle rect = getPageSize(page);
        for(int rotation = getPageRotation(page); rotation > 0; rotation -= 90)
            rect = rect.rotate();

        return rect;
    }

    public Rectangle getPageSize(int index)
    {
        return getPageSize(pageRefs.getPageNRelease(index));
    }

    public Rectangle getPageSize(PdfDictionary page)
    {
        PdfArray mediaBox = page.getAsArray(PdfName.MEDIABOX);
        return getNormalizedRectangle(mediaBox);
    }

    public Rectangle getCropBox(int index)
    {
        PdfDictionary page = pageRefs.getPageNRelease(index);
        PdfArray cropBox = (PdfArray)getPdfObjectRelease(page.get(PdfName.CROPBOX));
        if(cropBox == null)
            return getPageSize(page);
        else
            return getNormalizedRectangle(cropBox);
    }

    public Rectangle getBoxSize(int index, String boxName)
    {
        PdfDictionary page = pageRefs.getPageNRelease(index);
        PdfArray box = null;
        if(boxName.equals("trim"))
            box = (PdfArray)getPdfObjectRelease(page.get(PdfName.TRIMBOX));
        else
        if(boxName.equals("art"))
            box = (PdfArray)getPdfObjectRelease(page.get(PdfName.ARTBOX));
        else
        if(boxName.equals("bleed"))
            box = (PdfArray)getPdfObjectRelease(page.get(PdfName.BLEEDBOX));
        else
        if(boxName.equals("crop"))
            box = (PdfArray)getPdfObjectRelease(page.get(PdfName.CROPBOX));
        else
        if(boxName.equals("media"))
            box = (PdfArray)getPdfObjectRelease(page.get(PdfName.MEDIABOX));
        if(box == null)
            return null;
        else
            return getNormalizedRectangle(box);
    }

    public HashMap getInfo()
    {
        HashMap map = new HashMap();
        PdfDictionary info = trailer.getAsDict(PdfName.INFO);
        if(info == null)
            return map;
        Iterator i$ = info.getKeys().iterator();
        do
        {
            if(!i$.hasNext())
                break;
            Object element = i$.next();
            PdfName key = (PdfName)element;
            PdfObject obj = getPdfObject(info.get(key));
            if(obj != null)
            {
                String value = obj.toString();
                switch(obj.type())
                {
                case 3: // '\003'
                    value = ((PdfString)obj).toUnicodeString();
                    break;

                case 4: // '\004'
                    value = PdfName.decodeName(value);
                    break;
                }
                map.put(PdfName.decodeName(key.toString()), value);
            }
        } while(true);
        return map;
    }

    public static Rectangle getNormalizedRectangle(PdfArray box)
    {
        float llx = ((PdfNumber)getPdfObjectRelease(box.getPdfObject(0))).floatValue();
        float lly = ((PdfNumber)getPdfObjectRelease(box.getPdfObject(1))).floatValue();
        float urx = ((PdfNumber)getPdfObjectRelease(box.getPdfObject(2))).floatValue();
        float ury = ((PdfNumber)getPdfObjectRelease(box.getPdfObject(3))).floatValue();
        return new Rectangle(Math.min(llx, urx), Math.min(lly, ury), Math.max(llx, urx), Math.max(lly, ury));
    }

    public boolean isTagged()
    {
        PdfDictionary markInfo = catalog.getAsDict(PdfName.MARKINFO);
        if(markInfo == null)
            return false;
        else
            return PdfBoolean.PDFTRUE.equals(markInfo.getAsBoolean(PdfName.MARKED));
    }

    protected void readPdf()
        throws IOException
    {
        fileLength = tokens.getFile().length();
        pdfVersion = tokens.checkPdfHeader();
        try
        {
            readXref();
        }
        catch(Exception e)
        {
            try
            {
                rebuilt = true;
                rebuildXref();
                lastXref = -1L;
            }
            catch(Exception ne)
            {
                throw new InvalidPdfException(MessageLocalization.getComposedMessage("rebuild.failed.1.original.message.2", new Object[] {
                    ne.getMessage(), e.getMessage()
                }));
            }
        }
        try
        {
            readDocObj();
        }
        catch(Exception e)
        {
            if(e instanceof BadPasswordException)
                throw new BadPasswordException(e.getMessage());
            if(rebuilt || encryptionError)
                throw new InvalidPdfException(e.getMessage());
            rebuilt = true;
            encrypted = false;
            try
            {
                rebuildXref();
                lastXref = -1L;
                readDocObj();
            }
            catch(Exception ne)
            {
                throw new InvalidPdfException(MessageLocalization.getComposedMessage("rebuild.failed.1.original.message.2", new Object[] {
                    ne.getMessage(), e.getMessage()
                }));
            }
        }
        strings.clear();
        readPages();
        removeUnusedObjects();
    }

    protected void readPdfPartial()
        throws IOException
    {
        fileLength = tokens.getFile().length();
        pdfVersion = tokens.checkPdfHeader();
        try
        {
            readXref();
        }
        catch(Exception e)
        {
            try
            {
                rebuilt = true;
                rebuildXref();
                lastXref = -1L;
            }
            catch(Exception ne)
            {
                throw new InvalidPdfException(MessageLocalization.getComposedMessage("rebuild.failed.1.original.message.2", new Object[] {
                    ne.getMessage(), e.getMessage()
                }), ne);
            }
        }
        readDocObjPartial();
        readPages();
    }

    private boolean equalsArray(byte ar1[], byte ar2[], int size)
    {
        for(int k = 0; k < size; k++)
            if(ar1[k] != ar2[k])
                return false;

        return true;
    }

    private void readDecryptedDocObj()
        throws IOException
    {
        if(encrypted)
            return;
        PdfObject encDic = trailer.get(PdfName.ENCRYPT);
        if(encDic == null || encDic.toString().equals("null"))
            return;
        encryptionError = true;
        byte encryptionKey[] = null;
        encrypted = true;
        PdfDictionary enc = (PdfDictionary)getPdfObject(encDic);
        PdfArray documentIDs = trailer.getAsArray(PdfName.ID);
        byte documentID[] = null;
        if(documentIDs != null)
        {
            PdfObject o = documentIDs.getPdfObject(0);
            strings.remove(o);
            String s = o.toString();
            documentID = DocWriter.getISOBytes(s);
            if(documentIDs.size() > 1)
                strings.remove(documentIDs.getPdfObject(1));
        }
        if(documentID == null)
            documentID = new byte[0];
        byte uValue[] = null;
        byte oValue[] = null;
        int cryptoMode = 0;
        int lengthValue = 0;
        PdfObject filter = getPdfObjectRelease(enc.get(PdfName.FILTER));
        if(filter.equals(PdfName.STANDARD))
        {
            String s = enc.get(PdfName.U).toString();
            strings.remove(enc.get(PdfName.U));
            uValue = DocWriter.getISOBytes(s);
            s = enc.get(PdfName.O).toString();
            strings.remove(enc.get(PdfName.O));
            oValue = DocWriter.getISOBytes(s);
            if(enc.contains(PdfName.OE))
                strings.remove(enc.get(PdfName.OE));
            if(enc.contains(PdfName.UE))
                strings.remove(enc.get(PdfName.UE));
            if(enc.contains(PdfName.PERMS))
                strings.remove(enc.get(PdfName.PERMS));
            PdfObject o = enc.get(PdfName.P);
            if(!o.isNumber())
                throw new InvalidPdfException(MessageLocalization.getComposedMessage("illegal.p.value", new Object[0]));
            pValue = ((PdfNumber)o).intValue();
            o = enc.get(PdfName.R);
            if(!o.isNumber())
                throw new InvalidPdfException(MessageLocalization.getComposedMessage("illegal.r.value", new Object[0]));
            rValue = ((PdfNumber)o).intValue();
            switch(rValue)
            {
            case 2: // '\002'
                cryptoMode = 0;
                break;

            case 3: // '\003'
                o = enc.get(PdfName.LENGTH);
                if(!o.isNumber())
                    throw new InvalidPdfException(MessageLocalization.getComposedMessage("illegal.length.value", new Object[0]));
                lengthValue = ((PdfNumber)o).intValue();
                if(lengthValue > 128 || lengthValue < 40 || lengthValue % 8 != 0)
                    throw new InvalidPdfException(MessageLocalization.getComposedMessage("illegal.length.value", new Object[0]));
                cryptoMode = 1;
                break;

            case 4: // '\004'
                PdfDictionary dic = (PdfDictionary)enc.get(PdfName.CF);
                if(dic == null)
                    throw new InvalidPdfException(MessageLocalization.getComposedMessage("cf.not.found.encryption", new Object[0]));
                dic = (PdfDictionary)dic.get(PdfName.STDCF);
                if(dic == null)
                    throw new InvalidPdfException(MessageLocalization.getComposedMessage("stdcf.not.found.encryption", new Object[0]));
                if(PdfName.V2.equals(dic.get(PdfName.CFM)))
                    cryptoMode = 1;
                else
                if(PdfName.AESV2.equals(dic.get(PdfName.CFM)))
                    cryptoMode = 2;
                else
                    throw new UnsupportedPdfException(MessageLocalization.getComposedMessage("no.compatible.encryption.found", new Object[0]));
                PdfObject em = enc.get(PdfName.ENCRYPTMETADATA);
                if(em != null && em.toString().equals("false"))
                    cryptoMode |= 8;
                break;

            case 5: // '\005'
                cryptoMode = 3;
                PdfObject em5 = enc.get(PdfName.ENCRYPTMETADATA);
                if(em5 != null && em5.toString().equals("false"))
                    cryptoMode |= 8;
                break;

            default:
                throw new UnsupportedPdfException(MessageLocalization.getComposedMessage("unknown.encryption.type.r.eq.1", rValue));
            }
        } else
        if(filter.equals(PdfName.PUBSEC))
        {
            boolean foundRecipient = false;
            byte envelopedData[] = null;
            PdfArray recipients = null;
            PdfObject o = enc.get(PdfName.V);
            if(!o.isNumber())
                throw new InvalidPdfException(MessageLocalization.getComposedMessage("illegal.v.value", new Object[0]));
            int vValue = ((PdfNumber)o).intValue();
            switch(vValue)
            {
            case 1: // '\001'
                cryptoMode = 0;
                lengthValue = 40;
                recipients = (PdfArray)enc.get(PdfName.RECIPIENTS);
                break;

            case 2: // '\002'
                o = enc.get(PdfName.LENGTH);
                if(!o.isNumber())
                    throw new InvalidPdfException(MessageLocalization.getComposedMessage("illegal.length.value", new Object[0]));
                lengthValue = ((PdfNumber)o).intValue();
                if(lengthValue > 128 || lengthValue < 40 || lengthValue % 8 != 0)
                    throw new InvalidPdfException(MessageLocalization.getComposedMessage("illegal.length.value", new Object[0]));
                cryptoMode = 1;
                recipients = (PdfArray)enc.get(PdfName.RECIPIENTS);
                break;

            case 4: // '\004'
            case 5: // '\005'
                PdfDictionary dic = (PdfDictionary)enc.get(PdfName.CF);
                if(dic == null)
                    throw new InvalidPdfException(MessageLocalization.getComposedMessage("cf.not.found.encryption", new Object[0]));
                dic = (PdfDictionary)dic.get(PdfName.DEFAULTCRYPTFILTER);
                if(dic == null)
                    throw new InvalidPdfException(MessageLocalization.getComposedMessage("defaultcryptfilter.not.found.encryption", new Object[0]));
                if(PdfName.V2.equals(dic.get(PdfName.CFM)))
                {
                    cryptoMode = 1;
                    lengthValue = 128;
                } else
                if(PdfName.AESV2.equals(dic.get(PdfName.CFM)))
                {
                    cryptoMode = 2;
                    lengthValue = 128;
                } else
                if(PdfName.AESV3.equals(dic.get(PdfName.CFM)))
                {
                    cryptoMode = 3;
                    lengthValue = 256;
                } else
                {
                    throw new UnsupportedPdfException(MessageLocalization.getComposedMessage("no.compatible.encryption.found", new Object[0]));
                }
                PdfObject em = dic.get(PdfName.ENCRYPTMETADATA);
                if(em != null && em.toString().equals("false"))
                    cryptoMode |= 8;
                recipients = (PdfArray)dic.get(PdfName.RECIPIENTS);
                break;

            case 3: // '\003'
            default:
                throw new UnsupportedPdfException(MessageLocalization.getComposedMessage("unknown.encryption.type.v.eq.1", vValue));
            }
            X509CertificateHolder certHolder;
            try
            {
                certHolder = new X509CertificateHolder(certificate.getEncoded());
            }
            catch(Exception f)
            {
                throw new ExceptionConverter(f);
            }
label0:
            for(int i = 0; i < recipients.size(); i++)
            {
                PdfObject recipient = recipients.getPdfObject(i);
                strings.remove(recipient);
                CMSEnvelopedData data = null;
                try
                {
                    data = new CMSEnvelopedData(recipient.getBytes());
                    Iterator recipientCertificatesIt = data.getRecipientInfos().getRecipients().iterator();
                    do
                    {
                        if(!recipientCertificatesIt.hasNext())
                            continue label0;
                        RecipientInformation recipientInfo = (RecipientInformation)recipientCertificatesIt.next();
                        if(recipientInfo.getRID().match(certHolder) && !foundRecipient)
                        {
                            envelopedData = PdfEncryptor.getContent(recipientInfo, (PrivateKey)certificateKey, certificateKeyProvider);
                            foundRecipient = true;
                        }
                    } while(true);
                }
                catch(Exception f)
                {
                    throw new ExceptionConverter(f);
                }
            }

            if(!foundRecipient || envelopedData == null)
                throw new UnsupportedPdfException(MessageLocalization.getComposedMessage("bad.certificate.and.key", new Object[0]));
            MessageDigest md = null;
            try
            {
                if((cryptoMode & 7) == 3)
                    md = MessageDigest.getInstance("SHA-256");
                else
                    md = MessageDigest.getInstance("SHA-1");
                md.update(envelopedData, 0, 20);
                for(int i = 0; i < recipients.size(); i++)
                {
                    byte encodedRecipient[] = recipients.getPdfObject(i).getBytes();
                    md.update(encodedRecipient);
                }

                if((cryptoMode & 8) != 0)
                    md.update(new byte[] {
                        -1, -1, -1, -1
                    });
                encryptionKey = md.digest();
            }
            catch(Exception f)
            {
                throw new ExceptionConverter(f);
            }
        }
        decrypt = new PdfEncryption();
        decrypt.setCryptoMode(cryptoMode, lengthValue);
        if(filter.equals(PdfName.STANDARD))
        {
            if(rValue == 5)
            {
                ownerPasswordUsed = decrypt.readKey(enc, password);
                pValue = decrypt.getPermissions();
            } else
            {
                decrypt.setupByOwnerPassword(documentID, password, uValue, oValue, pValue);
                if(!equalsArray(uValue, decrypt.userKey, rValue != 3 && rValue != 4 ? 32 : 16))
                {
                    decrypt.setupByUserPassword(documentID, password, oValue, pValue);
                    if(!equalsArray(uValue, decrypt.userKey, rValue != 3 && rValue != 4 ? 32 : 16))
                        throw new BadPasswordException(MessageLocalization.getComposedMessage("bad.user.password", new Object[0]));
                } else
                {
                    ownerPasswordUsed = true;
                }
            }
        } else
        if(filter.equals(PdfName.PUBSEC))
        {
            if((cryptoMode & 7) == 3)
                decrypt.setKey(encryptionKey);
            else
                decrypt.setupByEncryptionKey(encryptionKey, lengthValue);
            ownerPasswordUsed = true;
        }
        for(int k = 0; k < strings.size(); k++)
        {
            PdfString str = (PdfString)strings.get(k);
            str.decrypt(this);
        }

        if(encDic.isIndirect())
        {
            cryptoRef = (PRIndirectReference)encDic;
            xrefObj.set(cryptoRef.getNumber(), null);
        }
        encryptionError = false;
    }

    public static PdfObject getPdfObjectRelease(PdfObject obj)
    {
        PdfObject obj2 = getPdfObject(obj);
        releaseLastXrefPartial(obj);
        return obj2;
    }

    public static PdfObject getPdfObject(PdfObject obj)
    {
        if(obj == null)
            return null;
        if(!obj.isIndirect())
            return obj;
        PRIndirectReference ref;
        boolean appendable;
        try
        {
            ref = (PRIndirectReference)obj;
            int idx = ref.getNumber();
            appendable = ref.getReader().appendable;
            obj = ref.getReader().getPdfObject(idx);
            if(obj == null)
                return null;
        }
        catch(Exception e)
        {
            throw new ExceptionConverter(e);
        }
        if(appendable)
        {
            switch(obj.type())
            {
            case 8: // '\b'
                obj = new PdfNull();
                break;

            case 1: // '\001'
                obj = new PdfBoolean(((PdfBoolean)obj).booleanValue());
                break;

            case 4: // '\004'
                obj = new PdfName(obj.getBytes());
                break;
            }
            obj.setIndRef(ref);
        }
        return obj;
    }

    public static PdfObject getPdfObjectRelease(PdfObject obj, PdfObject parent)
    {
        PdfObject obj2 = getPdfObject(obj, parent);
        releaseLastXrefPartial(obj);
        return obj2;
    }

    public static PdfObject getPdfObject(PdfObject obj, PdfObject parent)
    {
        if(obj == null)
            return null;
        if(!obj.isIndirect())
        {
            PRIndirectReference ref = null;
            if(parent != null && (ref = parent.getIndRef()) != null && ref.getReader().isAppendable())
            {
                switch(obj.type())
                {
                case 8: // '\b'
                    obj = new PdfNull();
                    break;

                case 1: // '\001'
                    obj = new PdfBoolean(((PdfBoolean)obj).booleanValue());
                    break;

                case 4: // '\004'
                    obj = new PdfName(obj.getBytes());
                    break;
                }
                obj.setIndRef(ref);
            }
            return obj;
        } else
        {
            return getPdfObject(obj);
        }
    }

    public PdfObject getPdfObjectRelease(int idx)
    {
        PdfObject obj = getPdfObject(idx);
        releaseLastXrefPartial();
        return obj;
    }

    public PdfObject getPdfObject(int idx)
    {
        PdfObject obj;
        try
        {
            lastXrefPartial = -1;
            if(idx < 0 || idx >= xrefObj.size())
                return null;
        }
        catch(Exception e)
        {
            throw new ExceptionConverter(e);
        }
        obj = (PdfObject)xrefObj.get(idx);
        if(!partial || obj != null)
            return obj;
        if(idx * 2 >= xref.length)
            return null;
        obj = readSingleObject(idx);
        lastXrefPartial = -1;
        if(obj != null)
            lastXrefPartial = idx;
        return obj;
    }

    public void resetLastXrefPartial()
    {
        lastXrefPartial = -1;
    }

    public void releaseLastXrefPartial()
    {
        if(partial && lastXrefPartial != -1)
        {
            xrefObj.set(lastXrefPartial, null);
            lastXrefPartial = -1;
        }
    }

    public static void releaseLastXrefPartial(PdfObject obj)
    {
        if(obj == null)
            return;
        if(!obj.isIndirect())
            return;
        if(!(obj instanceof PRIndirectReference))
            return;
        PRIndirectReference ref = (PRIndirectReference)obj;
        PdfReader reader = ref.getReader();
        if(reader.partial && reader.lastXrefPartial != -1 && reader.lastXrefPartial == ref.getNumber())
            reader.xrefObj.set(reader.lastXrefPartial, null);
        reader.lastXrefPartial = -1;
    }

    private void setXrefPartialObject(int idx, PdfObject obj)
    {
        if(!partial || idx < 0)
        {
            return;
        } else
        {
            xrefObj.set(idx, obj);
            return;
        }
    }

    public PRIndirectReference addPdfObject(PdfObject obj)
    {
        xrefObj.add(obj);
        return new PRIndirectReference(this, xrefObj.size() - 1);
    }

    protected void readPages()
        throws IOException
    {
        catalog = trailer.getAsDict(PdfName.ROOT);
        if(catalog == null)
            throw new InvalidPdfException(MessageLocalization.getComposedMessage("the.document.has.no.catalog.object", new Object[0]));
        rootPages = catalog.getAsDict(PdfName.PAGES);
        if(rootPages == null)
        {
            throw new InvalidPdfException(MessageLocalization.getComposedMessage("the.document.has.no.page.root", new Object[0]));
        } else
        {
            pageRefs = new PageRefs(this);
            return;
        }
    }

    protected void readDocObjPartial()
        throws IOException
    {
        xrefObj = new ArrayList(xref.length / 2);
        xrefObj.addAll(Collections.nCopies(xref.length / 2, null));
        readDecryptedDocObj();
        if(objStmToOffset != null)
        {
            long keys[] = objStmToOffset.getKeys();
            for(int k = 0; k < keys.length; k++)
            {
                long n = keys[k];
                objStmToOffset.put(n, xref[(int)(n * 2L)]);
                xref[(int)(n * 2L)] = -1L;
            }

        }
    }

    protected PdfObject readSingleObject(int k)
        throws IOException
    {
        strings.clear();
        int k2 = k * 2;
        long pos = xref[k2];
        if(pos < 0L)
            return null;
        if(xref[k2 + 1] > 0L)
            pos = objStmToOffset.get(xref[k2 + 1]);
        if(pos == 0L)
            return null;
        tokens.seek(pos);
        tokens.nextValidToken();
        if(tokens.getTokenType() != PRTokeniser.TokenType.NUMBER)
            tokens.throwError(MessageLocalization.getComposedMessage("invalid.object.number", new Object[0]));
        objNum = tokens.intValue();
        tokens.nextValidToken();
        if(tokens.getTokenType() != PRTokeniser.TokenType.NUMBER)
            tokens.throwError(MessageLocalization.getComposedMessage("invalid.generation.number", new Object[0]));
        objGen = tokens.intValue();
        tokens.nextValidToken();
        if(!tokens.getStringValue().equals("obj"))
            tokens.throwError(MessageLocalization.getComposedMessage("token.obj.expected", new Object[0]));
        PdfObject obj;
        try
        {
            obj = readPRObject();
            for(int j = 0; j < strings.size(); j++)
            {
                PdfString str = (PdfString)strings.get(j);
                str.decrypt(this);
            }

            if(obj.isStream())
                checkPRStreamLength((PRStream)obj);
        }
        catch(IOException e)
        {
            if(debugmode)
            {
                e.printStackTrace();
                obj = null;
            } else
            {
                throw e;
            }
        }
        if(xref[k2 + 1] > 0L)
            obj = readOneObjStm((PRStream)obj, (int)xref[k2]);
        xrefObj.set(k, obj);
        return obj;
    }

    protected PdfObject readOneObjStm(PRStream stream, int idx)
        throws IOException
    {
        int first;
        PRTokeniser saveTokens;
        first = stream.getAsNumber(PdfName.FIRST).intValue();
        byte b[] = getStreamBytes(stream, tokens.getFile());
        saveTokens = tokens;
        tokens = new PRTokeniser(new RandomAccessFileOrArray((new RandomAccessSourceFactory()).createSource(b)));
        PdfObject pdfobject;
        int address = 0;
        boolean ok = true;
        idx++;
        int k = 0;
        do
        {
            if(k >= idx)
                break;
            ok = tokens.nextToken();
            if(!ok)
                break;
            if(tokens.getTokenType() != PRTokeniser.TokenType.NUMBER)
            {
                ok = false;
                break;
            }
            ok = tokens.nextToken();
            if(!ok)
                break;
            if(tokens.getTokenType() != PRTokeniser.TokenType.NUMBER)
            {
                ok = false;
                break;
            }
            address = tokens.intValue() + first;
            k++;
        } while(true);
        if(!ok)
            throw new InvalidPdfException(MessageLocalization.getComposedMessage("error.reading.objstm", new Object[0]));
        tokens.seek(address);
        tokens.nextToken();
        PdfObject obj;
        if(tokens.getTokenType() == PRTokeniser.TokenType.NUMBER)
        {
            obj = new PdfNumber(tokens.getStringValue());
        } else
        {
            tokens.seek(address);
            obj = readPRObject();
        }
        pdfobject = obj;
        tokens = saveTokens;
        return pdfobject;
        Exception exception;
        exception;
        tokens = saveTokens;
        throw exception;
    }

    public double dumpPerc()
    {
        int total = 0;
        for(int k = 0; k < xrefObj.size(); k++)
            if(xrefObj.get(k) != null)
                total++;

        return ((double)total * 100D) / (double)xrefObj.size();
    }

    protected void readDocObj()
        throws IOException
    {
        ArrayList streams = new ArrayList();
        xrefObj = new ArrayList(xref.length / 2);
        xrefObj.addAll(Collections.nCopies(xref.length / 2, null));
        for(int k = 2; k < xref.length; k += 2)
        {
            long pos = xref[k];
            if(pos <= 0L || xref[k + 1] > 0L)
                continue;
            tokens.seek(pos);
            tokens.nextValidToken();
            if(tokens.getTokenType() != PRTokeniser.TokenType.NUMBER)
                tokens.throwError(MessageLocalization.getComposedMessage("invalid.object.number", new Object[0]));
            objNum = tokens.intValue();
            tokens.nextValidToken();
            if(tokens.getTokenType() != PRTokeniser.TokenType.NUMBER)
                tokens.throwError(MessageLocalization.getComposedMessage("invalid.generation.number", new Object[0]));
            objGen = tokens.intValue();
            tokens.nextValidToken();
            if(!tokens.getStringValue().equals("obj"))
                tokens.throwError(MessageLocalization.getComposedMessage("token.obj.expected", new Object[0]));
            PdfObject obj;
            try
            {
                obj = readPRObject();
                if(obj.isStream())
                    streams.add((PRStream)obj);
            }
            catch(IOException e)
            {
                if(debugmode)
                {
                    e.printStackTrace();
                    obj = null;
                } else
                {
                    throw e;
                }
            }
            xrefObj.set(k / 2, obj);
        }

        for(int k = 0; k < streams.size(); k++)
            checkPRStreamLength((PRStream)streams.get(k));

        readDecryptedDocObj();
        if(objStmMark != null)
        {
            int n;
            for(Iterator i$ = objStmMark.entrySet().iterator(); i$.hasNext(); xrefObj.set(n, null))
            {
                java.util.Map.Entry entry = (java.util.Map.Entry)i$.next();
                n = ((Integer)entry.getKey()).intValue();
                IntHashtable h = (IntHashtable)entry.getValue();
                readObjStm((PRStream)xrefObj.get(n), h);
            }

            objStmMark = null;
        }
        xref = null;
    }

    private void checkPRStreamLength(PRStream stream)
        throws IOException
    {
        long fileLength = tokens.length();
        long start = stream.getOffset();
        boolean calc = false;
        long streamLength = 0L;
        PdfObject obj = getPdfObjectRelease(stream.get(PdfName.LENGTH));
        if(obj != null && obj.type() == 2)
        {
            streamLength = ((PdfNumber)obj).intValue();
            if(streamLength + start > fileLength - 20L)
            {
                calc = true;
            } else
            {
                tokens.seek(start + streamLength);
                String line = tokens.readString(20);
                if(!line.startsWith("\nendstream") && !line.startsWith("\r\nendstream") && !line.startsWith("\rendstream") && !line.startsWith("endstream"))
                    calc = true;
            }
        } else
        {
            calc = true;
        }
        if(calc)
        {
            byte tline[] = new byte[16];
            tokens.seek(start);
            long pos;
            do
            {
                pos = tokens.getFilePointer();
                if(!tokens.readLineSegment(tline))
                    break;
                if(equalsn(tline, endstream))
                {
                    streamLength = pos - start;
                    break;
                }
                if(!equalsn(tline, endobj))
                    continue;
                tokens.seek(pos - 16L);
                String s = tokens.readString(16);
                int index = s.indexOf("endstream");
                if(index >= 0)
                    pos = (pos - 16L) + (long)index;
                streamLength = pos - start;
                break;
            } while(true);
            tokens.seek(pos - 2L);
            if(tokens.read() == 13)
                streamLength--;
            tokens.seek(pos - 1L);
            if(tokens.read() == 10)
                streamLength--;
        }
        stream.setLength((int)streamLength);
    }

    protected void readObjStm(PRStream stream, IntHashtable map)
        throws IOException
    {
        int first;
        int n;
        PRTokeniser saveTokens;
        first = stream.getAsNumber(PdfName.FIRST).intValue();
        n = stream.getAsNumber(PdfName.N).intValue();
        byte b[] = getStreamBytes(stream, tokens.getFile());
        saveTokens = tokens;
        tokens = new PRTokeniser(new RandomAccessFileOrArray((new RandomAccessSourceFactory()).createSource(b)));
        int address[] = new int[n];
        int objNumber[] = new int[n];
        boolean ok = true;
        int k = 0;
        do
        {
            if(k >= n)
                break;
            ok = tokens.nextToken();
            if(!ok)
                break;
            if(tokens.getTokenType() != PRTokeniser.TokenType.NUMBER)
            {
                ok = false;
                break;
            }
            objNumber[k] = tokens.intValue();
            ok = tokens.nextToken();
            if(!ok)
                break;
            if(tokens.getTokenType() != PRTokeniser.TokenType.NUMBER)
            {
                ok = false;
                break;
            }
            address[k] = tokens.intValue() + first;
            k++;
        } while(true);
        if(!ok)
            throw new InvalidPdfException(MessageLocalization.getComposedMessage("error.reading.objstm", new Object[0]));
        for(k = 0; k < n; k++)
        {
            if(!map.containsKey(k))
                continue;
            tokens.seek(address[k]);
            tokens.nextToken();
            PdfObject obj;
            if(tokens.getTokenType() == PRTokeniser.TokenType.NUMBER)
            {
                obj = new PdfNumber(tokens.getStringValue());
            } else
            {
                tokens.seek(address[k]);
                obj = readPRObject();
            }
            xrefObj.set(objNumber[k], obj);
        }

        tokens = saveTokens;
        break MISSING_BLOCK_LABEL_356;
        Exception exception;
        exception;
        tokens = saveTokens;
        throw exception;
    }

    public static PdfObject killIndirect(PdfObject obj)
    {
        if(obj == null || obj.isNull())
            return null;
        PdfObject ret = getPdfObjectRelease(obj);
        if(obj.isIndirect())
        {
            PRIndirectReference ref = (PRIndirectReference)obj;
            PdfReader reader = ref.getReader();
            int n = ref.getNumber();
            reader.xrefObj.set(n, null);
            if(reader.partial)
                reader.xref[n * 2] = -1L;
        }
        return ret;
    }

    private void ensureXrefSize(int size)
    {
        if(size == 0)
            return;
        if(xref == null)
            xref = new long[size];
        else
        if(xref.length < size)
        {
            long xref2[] = new long[size];
            System.arraycopy(xref, 0, xref2, 0, xref.length);
            xref = xref2;
        }
    }

    protected void readXref()
        throws IOException
    {
        hybridXref = false;
        newXrefType = false;
        tokens.seek(tokens.getStartxref());
        tokens.nextToken();
        if(!tokens.getStringValue().equals("startxref"))
            throw new InvalidPdfException(MessageLocalization.getComposedMessage("startxref.not.found", new Object[0]));
        tokens.nextToken();
        if(tokens.getTokenType() != PRTokeniser.TokenType.NUMBER)
            throw new InvalidPdfException(MessageLocalization.getComposedMessage("startxref.is.not.followed.by.a.number", new Object[0]));
        long startxref = tokens.longValue();
        lastXref = startxref;
        eofPos = tokens.getFilePointer();
        try
        {
            if(readXRefStream(startxref))
            {
                newXrefType = true;
                return;
            }
        }
        catch(Exception e) { }
        xref = null;
        tokens.seek(startxref);
        trailer = readXrefSection();
        PdfDictionary trailer2 = trailer;
        do
        {
            PdfNumber prev = (PdfNumber)trailer2.get(PdfName.PREV);
            if(prev != null)
            {
                tokens.seek(prev.longValue());
                trailer2 = readXrefSection();
            } else
            {
                return;
            }
        } while(true);
    }

    protected PdfDictionary readXrefSection()
        throws IOException
    {
        tokens.nextValidToken();
        if(!tokens.getStringValue().equals("xref"))
            tokens.throwError(MessageLocalization.getComposedMessage("xref.subsection.not.found", new Object[0]));
        int start = 0;
        int end = 0;
        long pos = 0L;
        int gen = 0;
        do
        {
            tokens.nextValidToken();
            if(tokens.getStringValue().equals("trailer"))
                break;
            if(tokens.getTokenType() != PRTokeniser.TokenType.NUMBER)
                tokens.throwError(MessageLocalization.getComposedMessage("object.number.of.the.first.object.in.this.xref.subsection.not.found", new Object[0]));
            start = tokens.intValue();
            tokens.nextValidToken();
            if(tokens.getTokenType() != PRTokeniser.TokenType.NUMBER)
                tokens.throwError(MessageLocalization.getComposedMessage("number.of.entries.in.this.xref.subsection.not.found", new Object[0]));
            end = tokens.intValue() + start;
            if(start == 1)
            {
                long back = tokens.getFilePointer();
                tokens.nextValidToken();
                pos = tokens.longValue();
                tokens.nextValidToken();
                gen = tokens.intValue();
                if(pos == 0L && gen == 65535)
                {
                    start--;
                    end--;
                }
                tokens.seek(back);
            }
            ensureXrefSize(end * 2);
            int k = start;
            while(k < end) 
            {
                tokens.nextValidToken();
                pos = tokens.longValue();
                tokens.nextValidToken();
                gen = tokens.intValue();
                tokens.nextValidToken();
                int p = k * 2;
                if(tokens.getStringValue().equals("n"))
                {
                    if(xref[p] == 0L && xref[p + 1] == 0L)
                        xref[p] = pos;
                } else
                if(tokens.getStringValue().equals("f"))
                {
                    if(xref[p] == 0L && xref[p + 1] == 0L)
                        xref[p] = -1L;
                } else
                {
                    tokens.throwError(MessageLocalization.getComposedMessage("invalid.cross.reference.entry.in.this.xref.subsection", new Object[0]));
                }
                k++;
            }
        } while(true);
        PdfDictionary trailer = (PdfDictionary)readPRObject();
        PdfNumber xrefSize = (PdfNumber)trailer.get(PdfName.SIZE);
        ensureXrefSize(xrefSize.intValue() * 2);
        PdfObject xrs = trailer.get(PdfName.XREFSTM);
        if(xrs != null && xrs.isNumber())
        {
            int loc = ((PdfNumber)xrs).intValue();
            try
            {
                readXRefStream(loc);
                newXrefType = true;
                hybridXref = true;
            }
            catch(IOException e)
            {
                xref = null;
                throw e;
            }
        }
        return trailer;
    }

    protected boolean readXRefStream(long ptr)
        throws IOException
    {
        tokens.seek(ptr);
        int thisStream = 0;
        if(!tokens.nextToken())
            return false;
        if(tokens.getTokenType() != PRTokeniser.TokenType.NUMBER)
            return false;
        thisStream = tokens.intValue();
        if(!tokens.nextToken() || tokens.getTokenType() != PRTokeniser.TokenType.NUMBER)
            return false;
        if(!tokens.nextToken() || !tokens.getStringValue().equals("obj"))
            return false;
        PdfObject object = readPRObject();
        PRStream stm = null;
        if(object.isStream())
        {
            stm = (PRStream)object;
            if(!PdfName.XREF.equals(stm.get(PdfName.TYPE)))
                return false;
        } else
        {
            return false;
        }
        if(trailer == null)
        {
            trailer = new PdfDictionary();
            trailer.putAll(stm);
        }
        stm.setLength(((PdfNumber)stm.get(PdfName.LENGTH)).intValue());
        int size = ((PdfNumber)stm.get(PdfName.SIZE)).intValue();
        PdfObject obj = stm.get(PdfName.INDEX);
        PdfArray index;
        if(obj == null)
        {
            index = new PdfArray();
            index.add(new int[] {
                0, size
            });
        } else
        {
            index = (PdfArray)obj;
        }
        PdfArray w = (PdfArray)stm.get(PdfName.W);
        long prev = -1L;
        obj = stm.get(PdfName.PREV);
        if(obj != null)
            prev = ((PdfNumber)obj).longValue();
        ensureXrefSize(size * 2);
        if(objStmMark == null && !partial)
            objStmMark = new HashMap();
        if(objStmToOffset == null && partial)
            objStmToOffset = new LongHashtable();
        byte b[] = getStreamBytes(stm, tokens.getFile());
        int bptr = 0;
        int wc[] = new int[3];
        for(int k = 0; k < 3; k++)
            wc[k] = w.getAsNumber(k).intValue();

        for(int idx = 0; idx < index.size(); idx += 2)
        {
            int start = index.getAsNumber(idx).intValue();
            int length = index.getAsNumber(idx + 1).intValue();
            ensureXrefSize((start + length) * 2);
            while(length-- > 0) 
            {
                int type = 1;
                if(wc[0] > 0)
                {
                    type = 0;
                    for(int k = 0; k < wc[0]; k++)
                        type = (type << 8) + (b[bptr++] & 0xff);

                }
                long field2 = 0L;
                for(int k = 0; k < wc[1]; k++)
                    field2 = (field2 << 8) + (long)(b[bptr++] & 0xff);

                int field3 = 0;
                for(int k = 0; k < wc[2]; k++)
                    field3 = (field3 << 8) + (b[bptr++] & 0xff);

                int base = start * 2;
                if(xref[base] == 0L && xref[base + 1] == 0L)
                    switch(type)
                    {
                    default:
                        break;

                    case 0: // '\0'
                        xref[base] = -1L;
                        break;

                    case 1: // '\001'
                        xref[base] = field2;
                        break;

                    case 2: // '\002'
                        xref[base] = field3;
                        xref[base + 1] = field2;
                        if(partial)
                        {
                            objStmToOffset.put(field2, 0L);
                            break;
                        }
                        Integer on = Integer.valueOf((int)field2);
                        IntHashtable seq = (IntHashtable)objStmMark.get(on);
                        if(seq == null)
                        {
                            seq = new IntHashtable();
                            seq.put(field3, 1);
                            objStmMark.put(on, seq);
                        } else
                        {
                            seq.put(field3, 1);
                        }
                        break;
                    }
                start++;
            }
        }

        thisStream *= 2;
        if(thisStream + 1 < xref.length && xref[thisStream] == 0L && xref[thisStream + 1] == 0L)
            xref[thisStream] = -1L;
        if(prev == -1L)
            return true;
        else
            return readXRefStream(prev);
    }

    protected void rebuildXref()
        throws IOException
    {
        hybridXref = false;
        newXrefType = false;
        tokens.seek(0L);
        long xr[][] = new long[1024][];
        long top = 0L;
        trailer = null;
        byte line[] = new byte[64];
        do
        {
            long pos = tokens.getFilePointer();
            if(!tokens.readLineSegment(line))
                break;
            if(line[0] == 116)
            {
                if(PdfEncodings.convertToString(line, null).startsWith("trailer"))
                {
                    tokens.seek(pos);
                    tokens.nextToken();
                    pos = tokens.getFilePointer();
                    try
                    {
                        PdfDictionary dic = (PdfDictionary)readPRObject();
                        if(dic.get(PdfName.ROOT) != null)
                            trailer = dic;
                        else
                            tokens.seek(pos);
                    }
                    catch(Exception e)
                    {
                        tokens.seek(pos);
                    }
                }
            } else
            if(line[0] >= 48 && line[0] <= 57)
            {
                long obj[] = PRTokeniser.checkObjectStart(line);
                if(obj != null)
                {
                    long num = obj[0];
                    long gen = obj[1];
                    if(num >= (long)xr.length)
                    {
                        long newLength = num * 2L;
                        long xr2[][] = new long[(int)newLength][];
                        System.arraycopy(xr, 0, xr2, 0, (int)top);
                        xr = xr2;
                    }
                    if(num >= top)
                        top = num + 1L;
                    if(xr[(int)num] == null || gen >= xr[(int)num][1])
                    {
                        obj[0] = pos;
                        xr[(int)num] = obj;
                    }
                }
            }
        } while(true);
        if(trailer == null)
            throw new InvalidPdfException(MessageLocalization.getComposedMessage("trailer.not.found", new Object[0]));
        xref = new long[(int)(top * 2L)];
        for(int k = 0; (long)k < top; k++)
        {
            long obj[] = xr[k];
            if(obj != null)
                xref[k * 2] = obj[0];
        }

    }

    protected PdfDictionary readDictionary()
        throws IOException
    {
        PdfDictionary dic = new PdfDictionary();
        do
        {
            tokens.nextValidToken();
            if(tokens.getTokenType() != PRTokeniser.TokenType.END_DIC)
            {
                if(tokens.getTokenType() != PRTokeniser.TokenType.NAME)
                    tokens.throwError(MessageLocalization.getComposedMessage("dictionary.key.1.is.not.a.name", new Object[] {
                        tokens.getStringValue()
                    }));
                PdfName name = new PdfName(tokens.getStringValue(), false);
                PdfObject obj = readPRObject();
                int type = obj.type();
                if(-type == PRTokeniser.TokenType.END_DIC.ordinal())
                    tokens.throwError(MessageLocalization.getComposedMessage("unexpected.gt.gt", new Object[0]));
                if(-type == PRTokeniser.TokenType.END_ARRAY.ordinal())
                    tokens.throwError(MessageLocalization.getComposedMessage("unexpected.close.bracket", new Object[0]));
                dic.put(name, obj);
            } else
            {
                return dic;
            }
        } while(true);
    }

    protected PdfArray readArray()
        throws IOException
    {
        PdfArray array = new PdfArray();
        do
        {
            PdfObject obj = readPRObject();
            int type = obj.type();
            if(-type != PRTokeniser.TokenType.END_ARRAY.ordinal())
            {
                if(-type == PRTokeniser.TokenType.END_DIC.ordinal())
                    tokens.throwError(MessageLocalization.getComposedMessage("unexpected.gt.gt", new Object[0]));
                array.add(obj);
            } else
            {
                return array;
            }
        } while(true);
    }

    protected PdfObject readPRObject()
        throws IOException
    {
        tokens.nextValidToken();
        PRTokeniser.TokenType type = tokens.getTokenType();
        static class _cls1
        {

            static final int $SwitchMap$co$com$pdf$text$pdf$PRTokeniser$TokenType[];

            static 
            {
                $SwitchMap$co$com$pdf$text$pdf$PRTokeniser$TokenType = new int[PRTokeniser.TokenType.values().length];
                try
                {
                    $SwitchMap$co$com$pdf$text$pdf$PRTokeniser$TokenType[PRTokeniser.TokenType.START_DIC.ordinal()] = 1;
                }
                catch(NoSuchFieldError ex) { }
                try
                {
                    $SwitchMap$co$com$pdf$text$pdf$PRTokeniser$TokenType[PRTokeniser.TokenType.START_ARRAY.ordinal()] = 2;
                }
                catch(NoSuchFieldError ex) { }
                try
                {
                    $SwitchMap$co$com$pdf$text$pdf$PRTokeniser$TokenType[PRTokeniser.TokenType.NUMBER.ordinal()] = 3;
                }
                catch(NoSuchFieldError ex) { }
                try
                {
                    $SwitchMap$co$com$pdf$text$pdf$PRTokeniser$TokenType[PRTokeniser.TokenType.STRING.ordinal()] = 4;
                }
                catch(NoSuchFieldError ex) { }
                try
                {
                    $SwitchMap$co$com$pdf$text$pdf$PRTokeniser$TokenType[PRTokeniser.TokenType.NAME.ordinal()] = 5;
                }
                catch(NoSuchFieldError ex) { }
                try
                {
                    $SwitchMap$co$com$pdf$text$pdf$PRTokeniser$TokenType[PRTokeniser.TokenType.REF.ordinal()] = 6;
                }
                catch(NoSuchFieldError ex) { }
                try
                {
                    $SwitchMap$co$com$pdf$text$pdf$PRTokeniser$TokenType[PRTokeniser.TokenType.ENDOFFILE.ordinal()] = 7;
                }
                catch(NoSuchFieldError ex) { }
            }
        }

        switch(_cls1..SwitchMap.co.com.pdf.text.pdf.PRTokeniser.TokenType[type.ordinal()])
        {
        case 1: // '\001'
            readDepth++;
            PdfDictionary dic = readDictionary();
            readDepth--;
            long pos = tokens.getFilePointer();
            boolean hasNext;
            do
                hasNext = tokens.nextToken();
            while(hasNext && tokens.getTokenType() == PRTokeniser.TokenType.COMMENT);
            if(hasNext && tokens.getStringValue().equals("stream"))
            {
                int ch;
                do
                    ch = tokens.read();
                while(ch == 32 || ch == 9 || ch == 0 || ch == 12);
                if(ch != 10)
                    ch = tokens.read();
                if(ch != 10)
                    tokens.backOnePosition(ch);
                PRStream stream = new PRStream(this, tokens.getFilePointer());
                stream.putAll(dic);
                stream.setObjNum(objNum, objGen);
                return stream;
            } else
            {
                tokens.seek(pos);
                return dic;
            }

        case 2: // '\002'
            readDepth++;
            PdfArray arr = readArray();
            readDepth--;
            return arr;

        case 3: // '\003'
            return new PdfNumber(tokens.getStringValue());

        case 4: // '\004'
            PdfString str = (new PdfString(tokens.getStringValue(), null)).setHexWriting(tokens.isHexString());
            str.setObjNum(objNum, objGen);
            if(strings != null)
                strings.add(str);
            return str;

        case 5: // '\005'
            PdfName cachedName = (PdfName)PdfName.staticNames.get(tokens.getStringValue());
            if(readDepth > 0 && cachedName != null)
                return cachedName;
            else
                return new PdfName(tokens.getStringValue(), false);

        case 6: // '\006'
            int num = tokens.getReference();
            PRIndirectReference ref = new PRIndirectReference(this, num, tokens.getGeneration());
            return ref;

        case 7: // '\007'
            throw new IOException(MessageLocalization.getComposedMessage("unexpected.end.of.file", new Object[0]));
        }
        String sv = tokens.getStringValue();
        if("null".equals(sv))
            if(readDepth == 0)
                return new PdfNull();
            else
                return PdfNull.PDFNULL;
        if("true".equals(sv))
            if(readDepth == 0)
                return new PdfBoolean(true);
            else
                return PdfBoolean.PDFTRUE;
        if("false".equals(sv))
        {
            if(readDepth == 0)
                return new PdfBoolean(false);
            else
                return PdfBoolean.PDFFALSE;
        } else
        {
            return new PdfLiteral(-type.ordinal(), tokens.getStringValue());
        }
    }

    public static byte[] FlateDecode(byte in[])
    {
        byte b[] = FlateDecode(in, true);
        if(b == null)
            return FlateDecode(in, false);
        else
            return b;
    }

    public static byte[] decodePredictor(byte in[], PdfObject dicPar)
    {
        DataInputStream dataStream;
        ByteArrayOutputStream fout;
        int bytesPerPixel;
        int bytesPerRow;
        byte curr[];
        byte prior[];
        if(dicPar == null || !dicPar.isDictionary())
            return in;
        PdfDictionary dic = (PdfDictionary)dicPar;
        PdfObject obj = getPdfObject(dic.get(PdfName.PREDICTOR));
        if(obj == null || !obj.isNumber())
            return in;
        int predictor = ((PdfNumber)obj).intValue();
        if(predictor < 10 && predictor != 2)
            return in;
        int width = 1;
        obj = getPdfObject(dic.get(PdfName.COLUMNS));
        if(obj != null && obj.isNumber())
            width = ((PdfNumber)obj).intValue();
        int colors = 1;
        obj = getPdfObject(dic.get(PdfName.COLORS));
        if(obj != null && obj.isNumber())
            colors = ((PdfNumber)obj).intValue();
        int bpc = 8;
        obj = getPdfObject(dic.get(PdfName.BITSPERCOMPONENT));
        if(obj != null && obj.isNumber())
            bpc = ((PdfNumber)obj).intValue();
        dataStream = new DataInputStream(new ByteArrayInputStream(in));
        fout = new ByteArrayOutputStream(in.length);
        bytesPerPixel = (colors * bpc) / 8;
        bytesPerRow = (colors * width * bpc + 7) / 8;
        curr = new byte[bytesPerRow];
        prior = new byte[bytesPerRow];
        if(predictor == 2)
        {
            if(bpc == 8)
            {
                int numRows = in.length / bytesPerRow;
                for(int row = 0; row < numRows; row++)
                {
                    int rowStart = row * bytesPerRow;
                    for(int col = 0 + bytesPerPixel; col < bytesPerRow; col++)
                        in[rowStart + col] = (byte)(in[rowStart + col] + in[(rowStart + col) - bytesPerPixel]);

                }

            }
            return in;
        }
_L2:
        int filter;
        filter = 0;
        try
        {
            filter = dataStream.read();
            if(filter < 0)
                return fout.toByteArray();
        }
        catch(Exception e)
        {
            return fout.toByteArray();
        }
        dataStream.readFully(curr, 0, bytesPerRow);
        switch(filter)
        {
        case 1: // '\001'
        {
            for(int i = bytesPerPixel; i < bytesPerRow; i++)
                curr[i] += curr[i - bytesPerPixel];

            break;
        }

        case 2: // '\002'
        {
            for(int i = 0; i < bytesPerRow; i++)
                curr[i] += prior[i];

            break;
        }

        case 3: // '\003'
        {
            for(int i = 0; i < bytesPerPixel; i++)
                curr[i] += prior[i] / 2;

            for(int i = bytesPerPixel; i < bytesPerRow; i++)
                curr[i] += ((curr[i - bytesPerPixel] & 0xff) + (prior[i] & 0xff)) / 2;

            break;
        }

        case 4: // '\004'
        {
            for(int i = 0; i < bytesPerPixel; i++)
                curr[i] += prior[i];

            for(int i = bytesPerPixel; i < bytesPerRow; i++)
            {
                int a = curr[i - bytesPerPixel] & 0xff;
                int b = prior[i] & 0xff;
                int c = prior[i - bytesPerPixel] & 0xff;
                int p = (a + b) - c;
                int pa = Math.abs(p - a);
                int pb = Math.abs(p - b);
                int pc = Math.abs(p - c);
                int ret;
                if(pa <= pb && pa <= pc)
                    ret = a;
                else
                if(pb <= pc)
                    ret = b;
                else
                    ret = c;
                curr[i] += (byte)ret;
            }

            break;
        }

        default:
        {
            throw new RuntimeException(MessageLocalization.getComposedMessage("png.filter.unknown", new Object[0]));
        }

        case 0: // '\0'
            break;
        }
        try
        {
            fout.write(curr);
        }
        catch(IOException ioe) { }
        byte tmp[] = prior;
        prior = curr;
        curr = tmp;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static byte[] FlateDecode(byte in[], boolean strict)
    {
        ByteArrayInputStream stream = new ByteArrayInputStream(in);
        InflaterInputStream zip = new InflaterInputStream(stream);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte b[] = new byte[strict ? 4092 : 1];
        try
        {
            int n;
            while((n = zip.read(b)) >= 0) 
                out.write(b, 0, n);
            zip.close();
            out.close();
            return out.toByteArray();
        }
        catch(Exception e) { }
        if(strict)
            return null;
        else
            return out.toByteArray();
    }

    public static byte[] ASCIIHexDecode(byte in[])
    {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        boolean first = true;
        int n1 = 0;
        for(int k = 0; k < in.length; k++)
        {
            int ch = in[k] & 0xff;
            if(ch == 62)
                break;
            if(PRTokeniser.isWhitespace(ch))
                continue;
            int n = PRTokeniser.getHex(ch);
            if(n == -1)
                throw new RuntimeException(MessageLocalization.getComposedMessage("illegal.character.in.asciihexdecode", new Object[0]));
            if(first)
                n1 = n;
            else
                out.write((byte)((n1 << 4) + n));
            first = !first;
        }

        if(!first)
            out.write((byte)(n1 << 4));
        return out.toByteArray();
    }

    public static byte[] ASCII85Decode(byte in[])
    {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int state = 0;
        int chn[] = new int[5];
        for(int k = 0; k < in.length; k++)
        {
            int ch = in[k] & 0xff;
            if(ch == 126)
                break;
            if(PRTokeniser.isWhitespace(ch))
                continue;
            if(ch == 122 && state == 0)
            {
                out.write(0);
                out.write(0);
                out.write(0);
                out.write(0);
                continue;
            }
            if(ch < 33 || ch > 117)
                throw new RuntimeException(MessageLocalization.getComposedMessage("illegal.character.in.ascii85decode", new Object[0]));
            chn[state] = ch - 33;
            if(++state != 5)
                continue;
            state = 0;
            int r = 0;
            for(int j = 0; j < 5; j++)
                r = r * 85 + chn[j];

            out.write((byte)(r >> 24));
            out.write((byte)(r >> 16));
            out.write((byte)(r >> 8));
            out.write((byte)r);
        }

        int r = 0;
        if(state == 2)
        {
            r = chn[0] * 85 * 85 * 85 * 85 + chn[1] * 85 * 85 * 85 + 0x95eed + 7225 + 85;
            out.write((byte)(r >> 24));
        } else
        if(state == 3)
        {
            r = chn[0] * 85 * 85 * 85 * 85 + chn[1] * 85 * 85 * 85 + chn[2] * 85 * 85 + 7225 + 85;
            out.write((byte)(r >> 24));
            out.write((byte)(r >> 16));
        } else
        if(state == 4)
        {
            r = chn[0] * 85 * 85 * 85 * 85 + chn[1] * 85 * 85 * 85 + chn[2] * 85 * 85 + chn[3] * 85 + 85;
            out.write((byte)(r >> 24));
            out.write((byte)(r >> 16));
            out.write((byte)(r >> 8));
        }
        return out.toByteArray();
    }

    public static byte[] LZWDecode(byte in[])
    {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        LZWDecoder lzw = new LZWDecoder();
        lzw.decode(in, out);
        return out.toByteArray();
    }

    public boolean isRebuilt()
    {
        return rebuilt;
    }

    public PdfDictionary getPageN(int pageNum)
    {
        PdfDictionary dic = pageRefs.getPageN(pageNum);
        if(dic == null)
            return null;
        if(appendable)
            dic.setIndRef(pageRefs.getPageOrigRef(pageNum));
        return dic;
    }

    public PdfDictionary getPageNRelease(int pageNum)
    {
        PdfDictionary dic = getPageN(pageNum);
        pageRefs.releasePage(pageNum);
        return dic;
    }

    public void releasePage(int pageNum)
    {
        pageRefs.releasePage(pageNum);
    }

    public void resetReleasePage()
    {
        pageRefs.resetReleasePage();
    }

    public PRIndirectReference getPageOrigRef(int pageNum)
    {
        return pageRefs.getPageOrigRef(pageNum);
    }

    public byte[] getPageContent(int pageNum, RandomAccessFileOrArray file)
        throws IOException
    {
        PdfDictionary page = getPageNRelease(pageNum);
        if(page == null)
            return null;
        PdfObject contents = getPdfObjectRelease(page.get(PdfName.CONTENTS));
        if(contents == null)
            return new byte[0];
        ByteArrayOutputStream bout = null;
        if(contents.isStream())
            return getStreamBytes((PRStream)contents, file);
        if(contents.isArray())
        {
            PdfArray array = (PdfArray)contents;
            bout = new ByteArrayOutputStream();
            for(int k = 0; k < array.size(); k++)
            {
                PdfObject item = getPdfObjectRelease(array.getPdfObject(k));
                if(item == null || !item.isStream())
                    continue;
                byte b[] = getStreamBytes((PRStream)item, file);
                bout.write(b);
                if(k != array.size() - 1)
                    bout.write(10);
            }

            return bout.toByteArray();
        } else
        {
            return new byte[0];
        }
    }

    public static byte[] getPageContent(PdfDictionary page)
        throws IOException
    {
        RandomAccessFileOrArray rf;
        if(page == null)
            return null;
        rf = null;
        PdfObject contents;
        byte abyte0[];
        contents = getPdfObjectRelease(page.get(PdfName.CONTENTS));
        if(contents != null)
            break MISSING_BLOCK_LABEL_42;
        abyte0 = new byte[0];
        try
        {
            if(rf != null)
                rf.close();
        }
        catch(Exception e) { }
        return abyte0;
        if(!contents.isStream())
            break MISSING_BLOCK_LABEL_92;
        if(rf == null)
        {
            rf = ((PRStream)contents).getReader().getSafeFile();
            rf.reOpen();
        }
        abyte0 = getStreamBytes((PRStream)contents, rf);
        try
        {
            if(rf != null)
                rf.close();
        }
        catch(Exception e) { }
        return abyte0;
        byte abyte1[];
        if(!contents.isArray())
            break MISSING_BLOCK_LABEL_237;
        PdfArray array = (PdfArray)contents;
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        for(int k = 0; k < array.size(); k++)
        {
            PdfObject item = getPdfObjectRelease(array.getPdfObject(k));
            if(item == null || !item.isStream())
                continue;
            if(rf == null)
            {
                rf = ((PRStream)item).getReader().getSafeFile();
                rf.reOpen();
            }
            byte b[] = getStreamBytes((PRStream)item, rf);
            bout.write(b);
            if(k != array.size() - 1)
                bout.write(10);
        }

        abyte1 = bout.toByteArray();
        try
        {
            if(rf != null)
                rf.close();
        }
        catch(Exception e) { }
        return abyte1;
        array = new byte[0];
        try
        {
            if(rf != null)
                rf.close();
        }
        catch(Exception e) { }
        return array;
        Exception exception;
        exception;
        try
        {
            if(rf != null)
                rf.close();
        }
        catch(Exception e) { }
        throw exception;
    }

    public PdfDictionary getPageResources(int pageNum)
    {
        return getPageResources(getPageN(pageNum));
    }

    public PdfDictionary getPageResources(PdfDictionary pageDict)
    {
        return pageDict.getAsDict(PdfName.RESOURCES);
    }

    public byte[] getPageContent(int pageNum)
        throws IOException
    {
        RandomAccessFileOrArray rf = getSafeFile();
        byte abyte0[];
        rf.reOpen();
        abyte0 = getPageContent(pageNum, rf);
        try
        {
            rf.close();
        }
        catch(Exception e) { }
        return abyte0;
        Exception exception;
        exception;
        try
        {
            rf.close();
        }
        catch(Exception e) { }
        throw exception;
    }

    protected void killXref(PdfObject obj)
    {
        if(obj == null)
            return;
        if((obj instanceof PdfIndirectReference) && !obj.isIndirect())
            return;
        switch(obj.type())
        {
        case 8: // '\b'
        case 9: // '\t'
        default:
            break;

        case 10: // '\n'
            int xr = ((PRIndirectReference)obj).getNumber();
            obj = (PdfObject)xrefObj.get(xr);
            xrefObj.set(xr, null);
            freeXref = xr;
            killXref(obj);
            break;

        case 5: // '\005'
            PdfArray t = (PdfArray)obj;
            for(int i = 0; i < t.size(); i++)
                killXref(t.getPdfObject(i));

            break;

        case 6: // '\006'
        case 7: // '\007'
            PdfDictionary dic = (PdfDictionary)obj;
            Object element;
            for(Iterator i$ = dic.getKeys().iterator(); i$.hasNext(); killXref(dic.get((PdfName)element)))
                element = i$.next();

            break;
        }
    }

    public void setPageContent(int pageNum, byte content[])
    {
        setPageContent(pageNum, content, -1);
    }

    public void setPageContent(int pageNum, byte content[], int compressionLevel)
    {
        PdfDictionary page = getPageN(pageNum);
        if(page == null)
            return;
        PdfObject contents = page.get(PdfName.CONTENTS);
        freeXref = -1;
        killXref(contents);
        if(freeXref == -1)
        {
            xrefObj.add(null);
            freeXref = xrefObj.size() - 1;
        }
        page.put(PdfName.CONTENTS, new PRIndirectReference(this, freeXref));
        xrefObj.set(freeXref, new PRStream(this, content, compressionLevel));
    }

    public static byte[] decodeBytes(byte b[], PdfDictionary streamDictionary)
        throws IOException
    {
        return decodeBytes(b, streamDictionary, FilterHandlers.getDefaultFilterHandlers());
    }

    public static byte[] decodeBytes(byte b[], PdfDictionary streamDictionary, Map filterHandlers)
        throws IOException
    {
        PdfObject filter = getPdfObjectRelease(streamDictionary.get(PdfName.FILTER));
        ArrayList filters = new ArrayList();
        if(filter != null)
            if(filter.isName())
                filters.add(filter);
            else
            if(filter.isArray())
                filters = ((PdfArray)filter).getArrayList();
        ArrayList dp = new ArrayList();
        PdfObject dpo = getPdfObjectRelease(streamDictionary.get(PdfName.DECODEPARMS));
        if(dpo == null || !dpo.isDictionary() && !dpo.isArray())
            dpo = getPdfObjectRelease(streamDictionary.get(PdfName.DP));
        if(dpo != null)
            if(dpo.isDictionary())
                dp.add(dpo);
            else
            if(dpo.isArray())
                dp = ((PdfArray)dpo).getArrayList();
        for(int j = 0; j < filters.size(); j++)
        {
            PdfName filterName = (PdfName)filters.get(j);
            FilterHandlers.FilterHandler filterHandler = (FilterHandlers.FilterHandler)filterHandlers.get(filterName);
            if(filterHandler == null)
                throw new UnsupportedPdfException(MessageLocalization.getComposedMessage("the.filter.1.is.not.supported", new Object[] {
                    filterName
                }));
            PdfDictionary decodeParams;
            if(j < dp.size())
            {
                PdfObject dpEntry = getPdfObject((PdfObject)dp.get(j));
                if(dpEntry instanceof PdfDictionary)
                    decodeParams = (PdfDictionary)dpEntry;
                else
                if(dpEntry == null || (dpEntry instanceof PdfNull))
                    decodeParams = null;
                else
                    throw new UnsupportedPdfException(MessageLocalization.getComposedMessage("the.decode.parameter.type.1.is.not.supported", new Object[] {
                        dpEntry.getClass().toString()
                    }));
            } else
            {
                decodeParams = null;
            }
            b = filterHandler.decode(b, filterName, decodeParams, streamDictionary);
        }

        return b;
    }

    public static byte[] getStreamBytes(PRStream stream, RandomAccessFileOrArray file)
        throws IOException
    {
        byte b[] = getStreamBytesRaw(stream, file);
        return decodeBytes(b, stream);
    }

    public static byte[] getStreamBytes(PRStream stream)
        throws IOException
    {
        RandomAccessFileOrArray rf = stream.getReader().getSafeFile();
        byte abyte0[];
        rf.reOpen();
        abyte0 = getStreamBytes(stream, rf);
        try
        {
            rf.close();
        }
        catch(Exception e) { }
        return abyte0;
        Exception exception;
        exception;
        try
        {
            rf.close();
        }
        catch(Exception e) { }
        throw exception;
    }

    public static byte[] getStreamBytesRaw(PRStream stream, RandomAccessFileOrArray file)
        throws IOException
    {
        PdfReader reader = stream.getReader();
        byte b[];
        if(stream.getOffset() < 0L)
        {
            b = stream.getBytes();
        } else
        {
            b = new byte[stream.getLength()];
            file.seek(stream.getOffset());
            file.readFully(b);
            PdfEncryption decrypt = reader.getDecrypt();
            if(decrypt != null)
            {
                PdfObject filter = getPdfObjectRelease(stream.get(PdfName.FILTER));
                ArrayList filters = new ArrayList();
                if(filter != null)
                    if(filter.isName())
                        filters.add(filter);
                    else
                    if(filter.isArray())
                        filters = ((PdfArray)filter).getArrayList();
                boolean skip = false;
                int k = 0;
                do
                {
                    if(k >= filters.size())
                        break;
                    PdfObject obj = getPdfObjectRelease((PdfObject)filters.get(k));
                    if(obj != null && obj.toString().equals("/Crypt"))
                    {
                        skip = true;
                        break;
                    }
                    k++;
                } while(true);
                if(!skip)
                {
                    decrypt.setHashKey(stream.getObjNum(), stream.getObjGen());
                    b = decrypt.decryptByteArray(b);
                }
            }
        }
        return b;
    }

    public static byte[] getStreamBytesRaw(PRStream stream)
        throws IOException
    {
        RandomAccessFileOrArray rf = stream.getReader().getSafeFile();
        byte abyte0[];
        rf.reOpen();
        abyte0 = getStreamBytesRaw(stream, rf);
        try
        {
            rf.close();
        }
        catch(Exception e) { }
        return abyte0;
        Exception exception;
        exception;
        try
        {
            rf.close();
        }
        catch(Exception e) { }
        throw exception;
    }

    public void eliminateSharedStreams()
    {
        if(!sharedStreams)
            return;
        sharedStreams = false;
        if(pageRefs.size() == 1)
            return;
        ArrayList newRefs = new ArrayList();
        ArrayList newStreams = new ArrayList();
        IntHashtable visited = new IntHashtable();
        for(int k = 1; k <= pageRefs.size(); k++)
        {
            PdfDictionary page = pageRefs.getPageN(k);
            if(page == null)
                continue;
            PdfObject contents = getPdfObject(page.get(PdfName.CONTENTS));
            if(contents == null)
                continue;
            if(contents.isStream())
            {
                PRIndirectReference ref = (PRIndirectReference)page.get(PdfName.CONTENTS);
                if(visited.containsKey(ref.getNumber()))
                {
                    newRefs.add(ref);
                    newStreams.add(new PRStream((PRStream)contents, null));
                } else
                {
                    visited.put(ref.getNumber(), 1);
                }
                continue;
            }
            if(!contents.isArray())
                continue;
            PdfArray array = (PdfArray)contents;
            for(int j = 0; j < array.size(); j++)
            {
                PRIndirectReference ref = (PRIndirectReference)array.getPdfObject(j);
                if(visited.containsKey(ref.getNumber()))
                {
                    newRefs.add(ref);
                    newStreams.add(new PRStream((PRStream)getPdfObject(ref), null));
                } else
                {
                    visited.put(ref.getNumber(), 1);
                }
            }

        }

        if(newStreams.isEmpty())
            return;
        for(int k = 0; k < newStreams.size(); k++)
        {
            xrefObj.add(newStreams.get(k));
            PRIndirectReference ref = (PRIndirectReference)newRefs.get(k);
            ref.setNumber(xrefObj.size() - 1, 0);
        }

    }

    public boolean isTampered()
    {
        return tampered;
    }

    public void setTampered(boolean tampered)
    {
        this.tampered = tampered;
        pageRefs.keepPages();
    }

    public byte[] getMetadata()
        throws IOException
    {
        PdfObject obj;
        RandomAccessFileOrArray rf;
        byte b[];
        obj = getPdfObject(catalog.get(PdfName.METADATA));
        if(!(obj instanceof PRStream))
            return null;
        rf = getSafeFile();
        b = null;
        rf.reOpen();
        b = getStreamBytes((PRStream)obj, rf);
        try
        {
            rf.close();
        }
        catch(Exception e) { }
        break MISSING_BLOCK_LABEL_69;
        Exception exception;
        exception;
        try
        {
            rf.close();
        }
        catch(Exception e) { }
        throw exception;
        return b;
    }

    public long getLastXref()
    {
        return lastXref;
    }

    public int getXrefSize()
    {
        return xrefObj.size();
    }

    public long getEofPos()
    {
        return eofPos;
    }

    public char getPdfVersion()
    {
        return pdfVersion;
    }

    public boolean isEncrypted()
    {
        return encrypted;
    }

    public int getPermissions()
    {
        return pValue;
    }

    public boolean is128Key()
    {
        return rValue == 3;
    }

    public PdfDictionary getTrailer()
    {
        return trailer;
    }

    PdfEncryption getDecrypt()
    {
        return decrypt;
    }

    static boolean equalsn(byte a1[], byte a2[])
    {
        int length = a2.length;
        for(int k = 0; k < length; k++)
            if(a1[k] != a2[k])
                return false;

        return true;
    }

    static boolean existsName(PdfDictionary dic, PdfName key, PdfName value)
    {
        PdfObject type = getPdfObjectRelease(dic.get(key));
        if(type == null || !type.isName())
        {
            return false;
        } else
        {
            PdfName name = (PdfName)type;
            return name.equals(value);
        }
    }

    static String getFontName(PdfDictionary dic)
    {
        if(dic == null)
            return null;
        PdfObject type = getPdfObjectRelease(dic.get(PdfName.BASEFONT));
        if(type == null || !type.isName())
            return null;
        else
            return PdfName.decodeName(type.toString());
    }

    static String getSubsetPrefix(PdfDictionary dic)
    {
        if(dic == null)
            return null;
        String s = getFontName(dic);
        if(s == null)
            return null;
        if(s.length() < 8 || s.charAt(6) != '+')
            return null;
        for(int k = 0; k < 6; k++)
        {
            char c = s.charAt(k);
            if(c < 'A' || c > 'Z')
                return null;
        }

        return s;
    }

    public int shuffleSubsetNames()
    {
        int total = 0;
        for(int k = 1; k < xrefObj.size(); k++)
        {
            PdfObject obj = getPdfObjectRelease(k);
            if(obj == null || !obj.isDictionary())
                continue;
            PdfDictionary dic = (PdfDictionary)obj;
            if(!existsName(dic, PdfName.TYPE, PdfName.FONT))
                continue;
            String s;
            if(existsName(dic, PdfName.SUBTYPE, PdfName.TYPE1) || existsName(dic, PdfName.SUBTYPE, PdfName.MMTYPE1) || existsName(dic, PdfName.SUBTYPE, PdfName.TRUETYPE))
            {
                s = getSubsetPrefix(dic);
                if(s == null)
                    continue;
                String ns = (new StringBuilder()).append(BaseFont.createSubsetPrefix()).append(s.substring(7)).toString();
                PdfName newName = new PdfName(ns);
                dic.put(PdfName.BASEFONT, newName);
                setXrefPartialObject(k, dic);
                total++;
                PdfDictionary fd = dic.getAsDict(PdfName.FONTDESCRIPTOR);
                if(fd != null)
                    fd.put(PdfName.FONTNAME, newName);
                continue;
            }
            if(!existsName(dic, PdfName.SUBTYPE, PdfName.TYPE0))
                continue;
            s = getSubsetPrefix(dic);
            PdfArray arr = dic.getAsArray(PdfName.DESCENDANTFONTS);
            if(arr == null || arr.isEmpty())
                continue;
            PdfDictionary desc = arr.getAsDict(0);
            String sde = getSubsetPrefix(desc);
            if(sde == null)
                continue;
            String ns = BaseFont.createSubsetPrefix();
            if(s != null)
                dic.put(PdfName.BASEFONT, new PdfName((new StringBuilder()).append(ns).append(s.substring(7)).toString()));
            setXrefPartialObject(k, dic);
            PdfName newName = new PdfName((new StringBuilder()).append(ns).append(sde.substring(7)).toString());
            desc.put(PdfName.BASEFONT, newName);
            total++;
            PdfDictionary fd = desc.getAsDict(PdfName.FONTDESCRIPTOR);
            if(fd != null)
                fd.put(PdfName.FONTNAME, newName);
        }

        return total;
    }

    public int createFakeFontSubsets()
    {
        int total = 0;
        for(int k = 1; k < xrefObj.size(); k++)
        {
            PdfObject obj = getPdfObjectRelease(k);
            if(obj == null || !obj.isDictionary())
                continue;
            PdfDictionary dic = (PdfDictionary)obj;
            if(!existsName(dic, PdfName.TYPE, PdfName.FONT) || !existsName(dic, PdfName.SUBTYPE, PdfName.TYPE1) && !existsName(dic, PdfName.SUBTYPE, PdfName.MMTYPE1) && !existsName(dic, PdfName.SUBTYPE, PdfName.TRUETYPE))
                continue;
            String s = getSubsetPrefix(dic);
            if(s != null)
                continue;
            s = getFontName(dic);
            if(s == null)
                continue;
            String ns = (new StringBuilder()).append(BaseFont.createSubsetPrefix()).append(s).toString();
            PdfDictionary fd = (PdfDictionary)getPdfObjectRelease(dic.get(PdfName.FONTDESCRIPTOR));
            if(fd != null && (fd.get(PdfName.FONTFILE) != null || fd.get(PdfName.FONTFILE2) != null || fd.get(PdfName.FONTFILE3) != null))
            {
                fd = dic.getAsDict(PdfName.FONTDESCRIPTOR);
                PdfName newName = new PdfName(ns);
                dic.put(PdfName.BASEFONT, newName);
                fd.put(PdfName.FONTNAME, newName);
                setXrefPartialObject(k, dic);
                total++;
            }
        }

        return total;
    }

    private static PdfArray getNameArray(PdfObject obj)
    {
        if(obj == null)
            return null;
        obj = getPdfObjectRelease(obj);
        if(obj == null)
            return null;
        if(obj.isArray())
            return (PdfArray)obj;
        if(obj.isDictionary())
        {
            PdfObject arr2 = getPdfObjectRelease(((PdfDictionary)obj).get(PdfName.D));
            if(arr2 != null && arr2.isArray())
                return (PdfArray)arr2;
        }
        return null;
    }

    public HashMap getNamedDestination()
    {
        return getNamedDestination(false);
    }

    public HashMap getNamedDestination(boolean keepNames)
    {
        HashMap names = getNamedDestinationFromNames(keepNames);
        names.putAll(getNamedDestinationFromStrings());
        return names;
    }

    public HashMap getNamedDestinationFromNames()
    {
        return new HashMap(getNamedDestinationFromNames(false));
    }

    public HashMap getNamedDestinationFromNames(boolean keepNames)
    {
        HashMap names = new HashMap();
        if(catalog.get(PdfName.DESTS) != null)
        {
            PdfDictionary dic = (PdfDictionary)getPdfObjectRelease(catalog.get(PdfName.DESTS));
            if(dic == null)
                return names;
            Set keys = dic.getKeys();
            Iterator i$ = keys.iterator();
            do
            {
                if(!i$.hasNext())
                    break;
                PdfName key = (PdfName)i$.next();
                PdfArray arr = getNameArray(dic.get(key));
                if(arr != null)
                    if(keepNames)
                    {
                        names.put(key, arr);
                    } else
                    {
                        String name = PdfName.decodeName(key.toString());
                        names.put(name, arr);
                    }
            } while(true);
        }
        return names;
    }

    public HashMap getNamedDestinationFromStrings()
    {
        if(catalog.get(PdfName.NAMES) != null)
        {
            PdfDictionary dic = (PdfDictionary)getPdfObjectRelease(catalog.get(PdfName.NAMES));
            if(dic != null)
            {
                dic = (PdfDictionary)getPdfObjectRelease(dic.get(PdfName.DESTS));
                if(dic != null)
                {
                    HashMap names = PdfNameTree.readTree(dic);
                    for(Iterator it = names.entrySet().iterator(); it.hasNext();)
                    {
                        java.util.Map.Entry entry = (java.util.Map.Entry)it.next();
                        PdfArray arr = getNameArray((PdfObject)entry.getValue());
                        if(arr != null)
                            entry.setValue(arr);
                        else
                            it.remove();
                    }

                    return names;
                }
            }
        }
        return new HashMap();
    }

    public void removeFields()
    {
        pageRefs.resetReleasePage();
        for(int k = 1; k <= pageRefs.size(); k++)
        {
            PdfDictionary page = pageRefs.getPageN(k);
            PdfArray annots = page.getAsArray(PdfName.ANNOTS);
            if(annots == null)
            {
                pageRefs.releasePage(k);
                continue;
            }
            for(int j = 0; j < annots.size(); j++)
            {
                PdfObject obj = getPdfObjectRelease(annots.getPdfObject(j));
                if(obj == null || !obj.isDictionary())
                    continue;
                PdfDictionary annot = (PdfDictionary)obj;
                if(PdfName.WIDGET.equals(annot.get(PdfName.SUBTYPE)))
                    annots.remove(j--);
            }

            if(annots.isEmpty())
                page.remove(PdfName.ANNOTS);
            else
                pageRefs.releasePage(k);
        }

        catalog.remove(PdfName.ACROFORM);
        pageRefs.resetReleasePage();
    }

    public void removeAnnotations()
    {
        pageRefs.resetReleasePage();
        for(int k = 1; k <= pageRefs.size(); k++)
        {
            PdfDictionary page = pageRefs.getPageN(k);
            if(page.get(PdfName.ANNOTS) == null)
                pageRefs.releasePage(k);
            else
                page.remove(PdfName.ANNOTS);
        }

        catalog.remove(PdfName.ACROFORM);
        pageRefs.resetReleasePage();
    }

    public ArrayList getLinks(int page)
    {
        pageRefs.resetReleasePage();
        ArrayList result = new ArrayList();
        PdfDictionary pageDic = pageRefs.getPageN(page);
        if(pageDic.get(PdfName.ANNOTS) != null)
        {
            PdfArray annots = pageDic.getAsArray(PdfName.ANNOTS);
            for(int j = 0; j < annots.size(); j++)
            {
                PdfDictionary annot = (PdfDictionary)getPdfObjectRelease(annots.getPdfObject(j));
                if(PdfName.LINK.equals(annot.get(PdfName.SUBTYPE)))
                    result.add(new PdfAnnotation.PdfImportedLink(annot));
            }

        }
        pageRefs.releasePage(page);
        pageRefs.resetReleasePage();
        return result;
    }

    private void iterateBookmarks(PdfObject outlineRef, HashMap names)
    {
        PdfDictionary outline;
        for(; outlineRef != null; outlineRef = outline.get(PdfName.NEXT))
        {
            replaceNamedDestination(outlineRef, names);
            outline = (PdfDictionary)getPdfObjectRelease(outlineRef);
            PdfObject first = outline.get(PdfName.FIRST);
            if(first != null)
                iterateBookmarks(first, names);
        }

    }

    public void makeRemoteNamedDestinationsLocal()
    {
        if(remoteToLocalNamedDestinations)
            return;
        remoteToLocalNamedDestinations = true;
        HashMap names = getNamedDestination(true);
        if(names.isEmpty())
            return;
        for(int k = 1; k <= pageRefs.size(); k++)
        {
            PdfDictionary page = pageRefs.getPageN(k);
            PdfObject annotsRef;
            PdfArray annots = (PdfArray)getPdfObject(annotsRef = page.get(PdfName.ANNOTS));
            int annotIdx = lastXrefPartial;
            releaseLastXrefPartial();
            if(annots == null)
            {
                pageRefs.releasePage(k);
                continue;
            }
            boolean commitAnnots = false;
            for(int an = 0; an < annots.size(); an++)
            {
                PdfObject objRef = annots.getPdfObject(an);
                if(convertNamedDestination(objRef, names) && !objRef.isIndirect())
                    commitAnnots = true;
            }

            if(commitAnnots)
                setXrefPartialObject(annotIdx, annots);
            if(!commitAnnots || annotsRef.isIndirect())
                pageRefs.releasePage(k);
        }

    }

    private boolean convertNamedDestination(PdfObject obj, HashMap names)
    {
        obj = getPdfObject(obj);
        int objIdx = lastXrefPartial;
        releaseLastXrefPartial();
        if(obj != null && obj.isDictionary())
        {
            PdfObject ob2 = getPdfObject(((PdfDictionary)obj).get(PdfName.A));
            if(ob2 != null)
            {
                int obj2Idx = lastXrefPartial;
                releaseLastXrefPartial();
                PdfDictionary dic = (PdfDictionary)ob2;
                PdfName type = (PdfName)getPdfObjectRelease(dic.get(PdfName.S));
                if(PdfName.GOTOR.equals(type))
                {
                    PdfObject ob3 = getPdfObjectRelease(dic.get(PdfName.D));
                    Object name = null;
                    if(ob3 != null)
                    {
                        if(ob3.isName())
                            name = ob3;
                        else
                        if(ob3.isString())
                            name = ob3.toString();
                        PdfArray dest = (PdfArray)names.get(name);
                        if(dest != null)
                        {
                            dic.remove(PdfName.F);
                            dic.remove(PdfName.NEWWINDOW);
                            dic.put(PdfName.S, PdfName.GOTO);
                            setXrefPartialObject(obj2Idx, ob2);
                            setXrefPartialObject(objIdx, obj);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public void consolidateNamedDestinations()
    {
        if(consolidateNamedDestinations)
            return;
        consolidateNamedDestinations = true;
        HashMap names = getNamedDestination(true);
        if(names.isEmpty())
            return;
        for(int k = 1; k <= pageRefs.size(); k++)
        {
            PdfDictionary page = pageRefs.getPageN(k);
            PdfObject annotsRef;
            PdfArray annots = (PdfArray)getPdfObject(annotsRef = page.get(PdfName.ANNOTS));
            int annotIdx = lastXrefPartial;
            releaseLastXrefPartial();
            if(annots == null)
            {
                pageRefs.releasePage(k);
                continue;
            }
            boolean commitAnnots = false;
            for(int an = 0; an < annots.size(); an++)
            {
                PdfObject objRef = annots.getPdfObject(an);
                if(replaceNamedDestination(objRef, names) && !objRef.isIndirect())
                    commitAnnots = true;
            }

            if(commitAnnots)
                setXrefPartialObject(annotIdx, annots);
            if(!commitAnnots || annotsRef.isIndirect())
                pageRefs.releasePage(k);
        }

        PdfDictionary outlines = (PdfDictionary)getPdfObjectRelease(catalog.get(PdfName.OUTLINES));
        if(outlines == null)
        {
            return;
        } else
        {
            iterateBookmarks(outlines.get(PdfName.FIRST), names);
            return;
        }
    }

    private boolean replaceNamedDestination(PdfObject obj, HashMap names)
    {
        obj = getPdfObject(obj);
        int objIdx = lastXrefPartial;
        releaseLastXrefPartial();
        if(obj != null && obj.isDictionary())
        {
            PdfObject ob2 = getPdfObjectRelease(((PdfDictionary)obj).get(PdfName.DEST));
            Object name = null;
            if(ob2 != null)
            {
                if(ob2.isName())
                    name = ob2;
                else
                if(ob2.isString())
                    name = ob2.toString();
                PdfArray dest = (PdfArray)names.get(name);
                if(dest != null)
                {
                    ((PdfDictionary)obj).put(PdfName.DEST, dest);
                    setXrefPartialObject(objIdx, obj);
                    return true;
                }
            } else
            if((ob2 = getPdfObject(((PdfDictionary)obj).get(PdfName.A))) != null)
            {
                int obj2Idx = lastXrefPartial;
                releaseLastXrefPartial();
                PdfDictionary dic = (PdfDictionary)ob2;
                PdfName type = (PdfName)getPdfObjectRelease(dic.get(PdfName.S));
                if(PdfName.GOTO.equals(type))
                {
                    PdfObject ob3 = getPdfObjectRelease(dic.get(PdfName.D));
                    if(ob3 != null)
                        if(ob3.isName())
                            name = ob3;
                        else
                        if(ob3.isString())
                            name = ob3.toString();
                    PdfArray dest = (PdfArray)names.get(name);
                    if(dest != null)
                    {
                        dic.put(PdfName.D, dest);
                        setXrefPartialObject(obj2Idx, ob2);
                        setXrefPartialObject(objIdx, obj);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    protected static PdfDictionary duplicatePdfDictionary(PdfDictionary original, PdfDictionary copy, PdfReader newReader)
    {
        if(copy == null)
            copy = new PdfDictionary();
        PdfName key;
        for(Iterator i$ = original.getKeys().iterator(); i$.hasNext(); copy.put(key, duplicatePdfObject(original.get(key), newReader)))
        {
            Object element = i$.next();
            key = (PdfName)element;
        }

        return copy;
    }

    protected static PdfObject duplicatePdfObject(PdfObject original, PdfReader newReader)
    {
        if(original == null)
            return null;
        switch(original.type())
        {
        case 6: // '\006'
        {
            return duplicatePdfDictionary((PdfDictionary)original, null, newReader);
        }

        case 7: // '\007'
        {
            PRStream org = (PRStream)original;
            PRStream stream = new PRStream(org, null, newReader);
            duplicatePdfDictionary(org, stream, newReader);
            return stream;
        }

        case 5: // '\005'
        {
            PdfArray arr = new PdfArray();
            for(Iterator it = ((PdfArray)original).listIterator(); it.hasNext(); arr.add(duplicatePdfObject((PdfObject)it.next(), newReader)));
            return arr;
        }

        case 10: // '\n'
        {
            PRIndirectReference org = (PRIndirectReference)original;
            return new PRIndirectReference(newReader, org.getNumber(), org.getGeneration());
        }

        case 8: // '\b'
        case 9: // '\t'
        default:
        {
            return original;
        }
        }
    }

    public void close()
    {
        try
        {
            tokens.close();
        }
        catch(IOException e)
        {
            throw new ExceptionConverter(e);
        }
    }

    protected void removeUnusedNode(PdfObject obj, boolean hits[])
    {
        Stack state = new Stack();
        state.push(obj);
label0:
        do
        {
            ArrayList ar;
            PdfDictionary dic;
            PdfName keys[];
            Object objs[];
            int idx;
label1:
            {
                if(state.empty())
                    break label0;
                Object current = state.pop();
                if(current == null)
                    continue;
                ar = null;
                dic = null;
                keys = null;
                objs = null;
                idx = 0;
                if(current instanceof PdfObject)
                {
                    obj = (PdfObject)current;
                    switch(obj.type())
                    {
                    case 6: // '\006'
                    case 7: // '\007'
                        dic = (PdfDictionary)obj;
                        keys = new PdfName[dic.size()];
                        dic.getKeys().toArray(keys);
                        break label1;

                    case 5: // '\005'
                        ar = ((PdfArray)obj).getArrayList();
                        break label1;

                    case 10: // '\n'
                        PRIndirectReference ref = (PRIndirectReference)obj;
                        int num = ref.getNumber();
                        if(!hits[num])
                        {
                            hits[num] = true;
                            state.push(getPdfObjectRelease(ref));
                        }
                        break;
                    }
                    continue;
                }
                objs = (Object[])(Object[])current;
                if(objs[0] instanceof ArrayList)
                {
                    ar = (ArrayList)objs[0];
                    idx = ((Integer)objs[1]).intValue();
                } else
                {
                    keys = (PdfName[])(PdfName[])objs[0];
                    dic = (PdfDictionary)objs[1];
                    idx = ((Integer)objs[2]).intValue();
                }
            }
            int k;
            if(ar != null)
            {
                k = idx;
                do
                {
label2:
                    {
                        if(k >= ar.size())
                            continue label0;
                        PdfObject v = (PdfObject)ar.get(k);
                        if(v.isIndirect())
                        {
                            int num = ((PRIndirectReference)v).getNumber();
                            if(num >= xrefObj.size() || !partial && xrefObj.get(num) == null)
                            {
                                ar.set(k, PdfNull.PDFNULL);
                                break label2;
                            }
                        }
                        if(objs == null)
                        {
                            state.push(((Object) (new Object[] {
                                ar, Integer.valueOf(k + 1)
                            })));
                        } else
                        {
                            objs[1] = Integer.valueOf(k + 1);
                            state.push(((Object) (objs)));
                        }
                        state.push(v);
                        continue label0;
                    }
                    k++;
                } while(true);
            }
            k = idx;
label3:
            do
            {
label4:
                {
                    if(k >= keys.length)
                        break label3;
                    PdfName key = keys[k];
                    PdfObject v = dic.get(key);
                    if(v.isIndirect())
                    {
                        int num = ((PRIndirectReference)v).getNumber();
                        if(num < 0 || num >= xrefObj.size() || !partial && xrefObj.get(num) == null)
                        {
                            dic.put(key, PdfNull.PDFNULL);
                            break label4;
                        }
                    }
                    if(objs == null)
                    {
                        state.push(((Object) (new Object[] {
                            keys, dic, Integer.valueOf(k + 1)
                        })));
                    } else
                    {
                        objs[2] = Integer.valueOf(k + 1);
                        state.push(((Object) (objs)));
                    }
                    state.push(v);
                    break label3;
                }
                k++;
            } while(true);
        } while(true);
    }

    public int removeUnusedObjects()
    {
        boolean hits[] = new boolean[xrefObj.size()];
        removeUnusedNode(trailer, hits);
        int total = 0;
        if(partial)
        {
            for(int k = 1; k < hits.length; k++)
                if(!hits[k])
                {
                    xref[k * 2] = -1L;
                    xref[k * 2 + 1] = 0L;
                    xrefObj.set(k, null);
                    total++;
                }

        } else
        {
            for(int k = 1; k < hits.length; k++)
                if(!hits[k])
                {
                    xrefObj.set(k, null);
                    total++;
                }

        }
        return total;
    }

    public AcroFields getAcroFields()
    {
        return new AcroFields(this, null);
    }

    public String getJavaScript(RandomAccessFileOrArray file)
        throws IOException
    {
        PdfDictionary names = (PdfDictionary)getPdfObjectRelease(catalog.get(PdfName.NAMES));
        if(names == null)
            return null;
        PdfDictionary js = (PdfDictionary)getPdfObjectRelease(names.get(PdfName.JAVASCRIPT));
        if(js == null)
            return null;
        HashMap jscript = PdfNameTree.readTree(js);
        String sortedNames[] = new String[jscript.size()];
        sortedNames = (String[])jscript.keySet().toArray(sortedNames);
        Arrays.sort(sortedNames);
        StringBuffer buf = new StringBuffer();
        for(int k = 0; k < sortedNames.length; k++)
        {
            PdfDictionary j = (PdfDictionary)getPdfObjectRelease((PdfObject)jscript.get(sortedNames[k]));
            if(j == null)
                continue;
            PdfObject obj = getPdfObjectRelease(j.get(PdfName.JS));
            if(obj == null)
                continue;
            if(obj.isString())
            {
                buf.append(((PdfString)obj).toUnicodeString()).append('\n');
                continue;
            }
            if(!obj.isStream())
                continue;
            byte bytes[] = getStreamBytes((PRStream)obj, file);
            if(bytes.length >= 2 && bytes[0] == -2 && bytes[1] == -1)
                buf.append(PdfEncodings.convertToString(bytes, "UnicodeBig"));
            else
                buf.append(PdfEncodings.convertToString(bytes, "PDF"));
            buf.append('\n');
        }

        return buf.toString();
    }

    public String getJavaScript()
        throws IOException
    {
        RandomAccessFileOrArray rf = getSafeFile();
        String s;
        rf.reOpen();
        s = getJavaScript(rf);
        try
        {
            rf.close();
        }
        catch(Exception e) { }
        return s;
        Exception exception;
        exception;
        try
        {
            rf.close();
        }
        catch(Exception e) { }
        throw exception;
    }

    public void selectPages(String ranges)
    {
        selectPages(SequenceList.expand(ranges, getNumberOfPages()));
    }

    public void selectPages(List pagesToKeep)
    {
        pageRefs.selectPages(pagesToKeep);
        removeUnusedObjects();
    }

    public void setViewerPreferences(int preferences)
    {
        viewerPreferences.setViewerPreferences(preferences);
        setViewerPreferences(viewerPreferences);
    }

    public void addViewerPreference(PdfName key, PdfObject value)
    {
        viewerPreferences.addViewerPreference(key, value);
        setViewerPreferences(viewerPreferences);
    }

    public void setViewerPreferences(PdfViewerPreferencesImp vp)
    {
        vp.addToCatalog(catalog);
    }

    public int getSimpleViewerPreferences()
    {
        return PdfViewerPreferencesImp.getViewerPreferences(catalog).getPageLayoutAndMode();
    }

    public boolean isAppendable()
    {
        return appendable;
    }

    public void setAppendable(boolean appendable)
    {
        this.appendable = appendable;
        if(appendable)
            getPdfObject(trailer.get(PdfName.ROOT));
    }

    public boolean isNewXrefType()
    {
        return newXrefType;
    }

    public long getFileLength()
    {
        return fileLength;
    }

    public boolean isHybridXref()
    {
        return hybridXref;
    }

    PdfIndirectReference getCryptoRef()
    {
        if(cryptoRef == null)
            return null;
        else
            return new PdfIndirectReference(0, cryptoRef.getNumber(), cryptoRef.getGeneration());
    }

    public boolean hasUsageRights()
    {
        PdfDictionary perms = catalog.getAsDict(PdfName.PERMS);
        if(perms == null)
            return false;
        else
            return perms.contains(PdfName.UR) || perms.contains(PdfName.UR3);
    }

    public void removeUsageRights()
    {
        PdfDictionary perms = catalog.getAsDict(PdfName.PERMS);
        if(perms == null)
            return;
        perms.remove(PdfName.UR);
        perms.remove(PdfName.UR3);
        if(perms.size() == 0)
            catalog.remove(PdfName.PERMS);
    }

    public int getCertificationLevel()
    {
        PdfDictionary dic = catalog.getAsDict(PdfName.PERMS);
        if(dic == null)
            return 0;
        dic = dic.getAsDict(PdfName.DOCMDP);
        if(dic == null)
            return 0;
        PdfArray arr = dic.getAsArray(PdfName.REFERENCE);
        if(arr == null || arr.size() == 0)
            return 0;
        dic = arr.getAsDict(0);
        if(dic == null)
            return 0;
        dic = dic.getAsDict(PdfName.TRANSFORMPARAMS);
        if(dic == null)
            return 0;
        PdfNumber p = dic.getAsNumber(PdfName.P);
        if(p == null)
            return 0;
        else
            return p.intValue();
    }

    public final boolean isOpenedWithFullPermissions()
    {
        return !encrypted || ownerPasswordUsed || unethicalreading;
    }

    public int getCryptoMode()
    {
        if(decrypt == null)
            return -1;
        else
            return decrypt.getCryptoMode();
    }

    public boolean isMetadataEncrypted()
    {
        if(decrypt == null)
            return false;
        else
            return decrypt.isMetadataEncrypted();
    }

    public byte[] computeUserPassword()
    {
        if(!encrypted || !ownerPasswordUsed)
            return null;
        else
            return decrypt.computeUserPassword(password);
    }

    public static boolean unethicalreading = false;
    public static boolean debugmode = false;
    static final PdfName pageInhCandidates[];
    static final byte endstream[] = PdfEncodings.convertToBytes("endstream", null);
    static final byte endobj[] = PdfEncodings.convertToBytes("endobj", null);
    protected PRTokeniser tokens;
    protected long xref[];
    protected HashMap objStmMark;
    protected LongHashtable objStmToOffset;
    protected boolean newXrefType;
    protected ArrayList xrefObj;
    PdfDictionary rootPages;
    protected PdfDictionary trailer;
    protected PdfDictionary catalog;
    protected PageRefs pageRefs;
    protected PRAcroForm acroForm;
    protected boolean acroFormParsed;
    protected boolean encrypted;
    protected boolean rebuilt;
    protected int freeXref;
    protected boolean tampered;
    protected long lastXref;
    protected long eofPos;
    protected char pdfVersion;
    protected PdfEncryption decrypt;
    protected byte password[];
    protected Key certificateKey;
    protected Certificate certificate;
    protected String certificateKeyProvider;
    private boolean ownerPasswordUsed;
    protected ArrayList strings;
    protected boolean sharedStreams;
    protected boolean consolidateNamedDestinations;
    protected boolean remoteToLocalNamedDestinations;
    protected int rValue;
    protected int pValue;
    private int objNum;
    private int objGen;
    private long fileLength;
    private boolean hybridXref;
    private int lastXrefPartial;
    private boolean partial;
    private PRIndirectReference cryptoRef;
    private final PdfViewerPreferencesImp viewerPreferences;
    private boolean encryptionError;
    private boolean appendable;
    protected static Counter COUNTER = CounterFactory.getCounter(co/com/pdf/text/pdf/PdfReader);
    private int readDepth;

    static 
    {
        pageInhCandidates = (new PdfName[] {
            PdfName.MEDIABOX, PdfName.ROTATE, PdfName.RESOURCES, PdfName.CROPBOX
        });
    }



}
