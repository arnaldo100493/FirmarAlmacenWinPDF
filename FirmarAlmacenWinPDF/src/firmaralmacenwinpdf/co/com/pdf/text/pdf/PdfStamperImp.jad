// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfStamperImp.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.*;
import co.com.pdf.text.error_messages.MessageLocalization;
import co.com.pdf.text.exceptions.BadPasswordException;
import co.com.pdf.text.log.Counter;
import co.com.pdf.text.log.CounterFactory;
import co.com.pdf.text.pdf.collection.PdfCollection;
import co.com.pdf.text.pdf.internal.PdfVersionImp;
import co.com.pdf.text.pdf.internal.PdfViewerPreferencesImp;
import co.com.pdf.text.xml.xmp.*;
import co.com.pdf.xmp.XMPException;
import co.com.pdf.xmp.XMPMetaFactory;
import co.com.pdf.xmp.options.SerializeOptions;
import java.io.*;
import java.util.*;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfWriter, PdfDocument, IntHashtable, PdfStamper, 
//            PdfEncryption, PdfDictionary, PdfNumber, PRIndirectReference, 
//            PdfName, PdfString, PdfDate, PRStream, 
//            PdfStream, PdfArray, PdfIndirectReference, ByteBuffer, 
//            RandomAccessFileOrArray, PdfObject, StampContent, PdfRectangle, 
//            AcroFields, PdfAppearance, PdfTemplate, PdfFormField, 
//            PdfAnnotation, PdfException, PdfLayer, PdfOCG, 
//            PdfReader, FdfReader, OutputStreamCounter, XfaForm, 
//            PdfPageLabels, PdfOCProperties, PdfIndirectObject, PdfContents, 
//            PageResources, PdfReaderInstance, PdfContentByte, PdfNameTree, 
//            PdfTransition, PdfAction

class PdfStamperImp extends PdfWriter
{
    static class PageStamp
    {

        PdfDictionary pageN;
        StampContent under;
        StampContent over;
        PageResources pageResources;
        int replacePoint;

        PageStamp(PdfStamperImp stamper, PdfReader reader, PdfDictionary pageN)
        {
            replacePoint = 0;
            this.pageN = pageN;
            pageResources = new PageResources();
            PdfDictionary resources = pageN.getAsDict(PdfName.RESOURCES);
            pageResources.setOriginalResources(resources, stamper.namePtr);
        }
    }


    protected Counter getCounter()
    {
        return COUNTER;
    }

    protected PdfStamperImp(PdfReader reader, OutputStream os, char pdfVersion, boolean append)
        throws DocumentException, IOException
    {
        super(new PdfDocument(), os);
        readers2intrefs = new HashMap();
        readers2file = new HashMap();
        myXref = new IntHashtable();
        pagesToContent = new HashMap();
        closed = false;
        rotateContents = true;
        flat = false;
        flatFreeText = false;
        partialFlattening = new HashSet();
        useVp = false;
        viewerPreferences = new PdfViewerPreferencesImp();
        fieldTemplates = new HashSet();
        fieldsAdded = false;
        sigFlags = 0;
        COUNTER = CounterFactory.getCounter(co/com/pdf/text/pdf/PdfStamper);
        if(!reader.isOpenedWithFullPermissions())
            throw new BadPasswordException(MessageLocalization.getComposedMessage("pdfreader.not.opened.with.owner.password", new Object[0]));
        if(reader.isTampered())
            throw new DocumentException(MessageLocalization.getComposedMessage("the.original.document.was.reused.read.it.again.from.file", new Object[0]));
        reader.setTampered(true);
        this.reader = reader;
        file = reader.getSafeFile();
        this.append = append;
        if(reader.isEncrypted() && (append || PdfReader.unethicalreading))
            crypto = new PdfEncryption(reader.getDecrypt());
        if(append)
        {
            if(reader.isRebuilt())
                throw new DocumentException(MessageLocalization.getComposedMessage("append.mode.requires.a.document.without.errors.even.if.recovery.was.possible", new Object[0]));
            pdf_version.setAppendmode(true);
            byte buf[] = new byte[8192];
            int n;
            while((n = file.read(buf)) > 0) 
                this.os.write(buf, 0, n);
            prevxref = reader.getLastXref();
            reader.setAppendable(true);
        } else
        if(pdfVersion == 0)
            super.setPdfVersion(reader.getPdfVersion());
        else
            super.setPdfVersion(pdfVersion);
        super.open();
        pdf.addWriter(this);
        if(append)
        {
            body.setRefnum(reader.getXrefSize());
            marked = new IntHashtable();
            if(reader.isNewXrefType())
                fullCompression = true;
            if(reader.isHybridXref())
                fullCompression = false;
        }
        initialXrefSize = reader.getXrefSize();
    }

    protected void setViewerPreferences()
    {
        reader.setViewerPreferences(viewerPreferences);
        markUsed(reader.getTrailer().get(PdfName.ROOT));
    }

    protected void close(Map moreInfo)
        throws IOException
    {
        if(closed)
            return;
        if(useVp)
            setViewerPreferences();
        if(flat)
            flatFields();
        if(flatFreeText)
            flatFreeTextFields();
        addFieldResources();
        PdfDictionary catalog = reader.getCatalog();
        getPdfVersion().addToCatalog(catalog);
        PdfDictionary acroForm = (PdfDictionary)PdfReader.getPdfObject(catalog.get(PdfName.ACROFORM), reader.getCatalog());
        if(acroFields != null && acroFields.getXfa().isChanged())
        {
            markUsed(acroForm);
            if(!flat)
                acroFields.getXfa().setXfa(this);
        }
        if(sigFlags != 0 && acroForm != null)
        {
            acroForm.put(PdfName.SIGFLAGS, new PdfNumber(sigFlags));
            markUsed(acroForm);
            markUsed(catalog);
        }
        closed = true;
        addSharedObjectsToBody();
        setOutlines();
        setJavaScript();
        addFileAttachments();
        if(openAction != null)
            catalog.put(PdfName.OPENACTION, openAction);
        if(pdf.pageLabels != null)
            catalog.put(PdfName.PAGELABELS, pdf.pageLabels.getDictionary(this));
        if(!documentOCG.isEmpty())
        {
            fillOCProperties(false);
            PdfDictionary ocdict = catalog.getAsDict(PdfName.OCPROPERTIES);
            if(ocdict == null)
            {
                reader.getCatalog().put(PdfName.OCPROPERTIES, OCProperties);
            } else
            {
                ocdict.put(PdfName.OCGS, OCProperties.get(PdfName.OCGS));
                PdfDictionary ddict = ocdict.getAsDict(PdfName.D);
                if(ddict == null)
                {
                    ddict = new PdfDictionary();
                    ocdict.put(PdfName.D, ddict);
                }
                ddict.put(PdfName.ORDER, OCProperties.getAsDict(PdfName.D).get(PdfName.ORDER));
                ddict.put(PdfName.RBGROUPS, OCProperties.getAsDict(PdfName.D).get(PdfName.RBGROUPS));
                ddict.put(PdfName.OFF, OCProperties.getAsDict(PdfName.D).get(PdfName.OFF));
                ddict.put(PdfName.AS, OCProperties.getAsDict(PdfName.D).get(PdfName.AS));
            }
            PdfWriter.checkPdfIsoConformance(this, 7, OCProperties);
        }
        int skipInfo = -1;
        PdfObject oInfo = reader.getTrailer().get(PdfName.INFO);
        PRIndirectReference iInfo = null;
        PdfDictionary oldInfo = null;
        if(oInfo instanceof PRIndirectReference)
            iInfo = (PRIndirectReference)oInfo;
        if(iInfo != null)
            oldInfo = (PdfDictionary)PdfReader.getPdfObject(iInfo);
        else
        if(oInfo instanceof PdfDictionary)
            oldInfo = (PdfDictionary)oInfo;
        String producer = null;
        if(iInfo != null)
            skipInfo = iInfo.getNumber();
        if(oldInfo != null && oldInfo.get(PdfName.PRODUCER) != null)
            producer = oldInfo.getAsString(PdfName.PRODUCER).toUnicodeString();
        Version version = Version.getInstance();
        if(producer == null)
            producer = version.getVersion();
        else
        if(producer.indexOf(version.getProduct()) == -1)
        {
            StringBuffer buf = new StringBuffer(producer);
            buf.append("; modified using ");
            buf.append(version.getVersion());
            producer = buf.toString();
        }
        PdfIndirectReference info = null;
        PdfDictionary newInfo = new PdfDictionary();
        if(oldInfo != null)
        {
            PdfName key;
            PdfObject value;
            for(Iterator i$ = oldInfo.getKeys().iterator(); i$.hasNext(); newInfo.put(key, value))
            {
                Object element = i$.next();
                key = (PdfName)element;
                value = PdfReader.getPdfObject(oldInfo.get(key));
            }

        }
        if(moreInfo != null)
        {
            for(Iterator i$ = moreInfo.entrySet().iterator(); i$.hasNext();)
            {
                java.util.Map.Entry entry = (java.util.Map.Entry)i$.next();
                String key = (String)entry.getKey();
                PdfName keyName = new PdfName(key);
                String value = (String)entry.getValue();
                if(value == null)
                    newInfo.remove(keyName);
                else
                    newInfo.put(keyName, new PdfString(value, "UnicodeBig"));
            }

        }
        PdfDate date = new PdfDate();
        newInfo.put(PdfName.MODDATE, date);
        newInfo.put(PdfName.PRODUCER, new PdfString(producer, "UnicodeBig"));
        if(append)
        {
            if(iInfo == null)
                info = addToBody(newInfo, false).getIndirectReference();
            else
                info = addToBody(newInfo, iInfo.getNumber(), false).getIndirectReference();
        } else
        {
            info = addToBody(newInfo, false).getIndirectReference();
        }
        byte altMetadata[] = null;
        PdfObject xmpo = PdfReader.getPdfObject(catalog.get(PdfName.METADATA));
        if(xmpo != null && xmpo.isStream())
        {
            altMetadata = PdfReader.getStreamBytesRaw((PRStream)xmpo);
            PdfReader.killIndirect(catalog.get(PdfName.METADATA));
        }
        PdfStream xmp = null;
        if(xmpMetadata != null)
            altMetadata = xmpMetadata;
        else
        if(xmpWriter != null)
            try
            {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                PdfProperties.setProducer(xmpWriter.getXmpMeta(), producer);
                XmpBasicProperties.setModDate(xmpWriter.getXmpMeta(), date.getW3CDate());
                XmpBasicProperties.setMetaDataDate(xmpWriter.getXmpMeta(), date.getW3CDate());
                xmpWriter.serialize(baos);
                xmpWriter.close();
                xmp = new PdfStream(baos.toByteArray());
            }
            catch(XMPException exc)
            {
                xmpWriter = null;
            }
        if(xmp == null && altMetadata != null)
            try
            {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                if(moreInfo == null || xmpMetadata != null)
                {
                    co.com.pdf.xmp.XMPMeta xmpMeta = XMPMetaFactory.parseFromBuffer(altMetadata);
                    PdfProperties.setProducer(xmpMeta, producer);
                    XmpBasicProperties.setModDate(xmpMeta, date.getW3CDate());
                    XmpBasicProperties.setMetaDataDate(xmpMeta, date.getW3CDate());
                    SerializeOptions serializeOptions = new SerializeOptions();
                    serializeOptions.setPadding(2000);
                    XMPMetaFactory.serialize(xmpMeta, baos, serializeOptions);
                } else
                {
                    XmpWriter xmpw = createXmpWriter(baos, newInfo);
                    xmpw.close();
                }
                xmp = new PdfStream(baos.toByteArray());
            }
            catch(XMPException e)
            {
                xmp = new PdfStream(altMetadata);
            }
            catch(IOException e)
            {
                xmp = new PdfStream(altMetadata);
            }
        if(xmp != null)
        {
            xmp.put(PdfName.TYPE, PdfName.METADATA);
            xmp.put(PdfName.SUBTYPE, PdfName.XML);
            if(crypto != null && !crypto.isMetadataEncrypted())
            {
                PdfArray ar = new PdfArray();
                ar.add(PdfName.CRYPT);
                xmp.put(PdfName.FILTER, ar);
            }
            if(append && xmpo != null)
            {
                body.add(xmp, xmpo.getIndRef());
            } else
            {
                catalog.put(PdfName.METADATA, body.add(xmp).getIndirectReference());
                markUsed(catalog);
            }
        }
        close(info, skipInfo);
    }

    protected void close(PdfIndirectReference info, int skipInfo)
        throws IOException
    {
        alterContents();
        int rootN = ((PRIndirectReference)reader.trailer.get(PdfName.ROOT)).getNumber();
        if(append)
        {
            int keys[] = marked.getKeys();
            for(int k = 0; k < keys.length; k++)
            {
                int j = keys[k];
                PdfObject obj = reader.getPdfObjectRelease(j);
                if(obj != null && skipInfo != j && j < initialXrefSize)
                    addToBody(obj, obj.getIndRef(), j != rootN);
            }

            for(int k = initialXrefSize; k < reader.getXrefSize(); k++)
            {
                PdfObject obj = reader.getPdfObject(k);
                if(obj != null)
                    addToBody(obj, getNewObjectNumber(reader, k, 0));
            }

        } else
        {
            for(int k = 1; k < reader.getXrefSize(); k++)
            {
                PdfObject obj = reader.getPdfObjectRelease(k);
                if(obj != null && skipInfo != k)
                    addToBody(obj, getNewObjectNumber(reader, k, 0), k != rootN);
            }

        }
        PdfIndirectReference encryption = null;
        PdfObject fileID = null;
        if(crypto != null)
        {
            if(append)
            {
                encryption = reader.getCryptoRef();
            } else
            {
                PdfIndirectObject encryptionObject = addToBody(crypto.getEncryptionDictionary(), false);
                encryption = encryptionObject.getIndirectReference();
            }
            fileID = crypto.getFileID();
        } else
        {
            PdfArray IDs = reader.trailer.getAsArray(PdfName.ID);
            if(IDs != null && IDs.getAsString(0) != null)
                fileID = PdfEncryption.createInfoId(IDs.getAsString(0).getBytes());
            else
                fileID = PdfEncryption.createInfoId(PdfEncryption.createDocumentId());
        }
        PRIndirectReference iRoot = (PRIndirectReference)reader.trailer.get(PdfName.ROOT);
        PdfIndirectReference root = new PdfIndirectReference(0, getNewObjectNumber(reader, iRoot.getNumber(), 0));
        body.writeCrossReferenceTable(os, root, info, encryption, fileID, prevxref);
        if(fullCompression)
        {
            writeKeyInfo(os);
            os.write(getISOBytes("startxref\n"));
            os.write(getISOBytes(String.valueOf(body.offset())));
            os.write(getISOBytes("\n%%EOF\n"));
        } else
        {
            PdfWriter.PdfTrailer trailer = new PdfWriter.PdfTrailer(body.size(), body.offset(), root, info, encryption, fileID, prevxref);
            trailer.toPdf(this, os);
        }
        os.flush();
        if(isCloseStream())
            os.close();
        getCounter().written(os.getCounter());
    }

    void applyRotation(PdfDictionary pageN, ByteBuffer out)
    {
        if(!rotateContents)
            return;
        Rectangle page = reader.getPageSizeWithRotation(pageN);
        int rotation = page.getRotation();
        switch(rotation)
        {
        case 90: // 'Z'
            out.append(PdfContents.ROTATE90);
            out.append(page.getTop());
            out.append(' ').append('0').append(PdfContents.ROTATEFINAL);
            break;

        case 180: 
            out.append(PdfContents.ROTATE180);
            out.append(page.getRight());
            out.append(' ');
            out.append(page.getTop());
            out.append(PdfContents.ROTATEFINAL);
            break;

        case 270: 
            out.append(PdfContents.ROTATE270);
            out.append('0').append(' ');
            out.append(page.getRight());
            out.append(PdfContents.ROTATEFINAL);
            break;
        }
    }

    protected void alterContents()
        throws IOException
    {
        PageStamp ps;
        for(Iterator i$ = pagesToContent.values().iterator(); i$.hasNext(); alterResources(ps))
        {
            Object element = i$.next();
            ps = (PageStamp)element;
            PdfDictionary pageN = ps.pageN;
            markUsed(pageN);
            PdfArray ar = null;
            PdfObject content = PdfReader.getPdfObject(pageN.get(PdfName.CONTENTS), pageN);
            if(content == null)
            {
                ar = new PdfArray();
                pageN.put(PdfName.CONTENTS, ar);
            } else
            if(content.isArray())
            {
                ar = (PdfArray)content;
                markUsed(ar);
            } else
            if(content.isStream())
            {
                ar = new PdfArray();
                ar.add(pageN.get(PdfName.CONTENTS));
                pageN.put(PdfName.CONTENTS, ar);
            } else
            {
                ar = new PdfArray();
                pageN.put(PdfName.CONTENTS, ar);
            }
            ByteBuffer out = new ByteBuffer();
            if(ps.under != null)
            {
                out.append(PdfContents.SAVESTATE);
                applyRotation(pageN, out);
                out.append(ps.under.getInternalBuffer());
                out.append(PdfContents.RESTORESTATE);
            }
            if(ps.over != null)
                out.append(PdfContents.SAVESTATE);
            PdfStream stream = new PdfStream(out.toByteArray());
            stream.flateCompress(compressionLevel);
            ar.addFirst(addToBody(stream).getIndirectReference());
            out.reset();
            if(ps.over != null)
            {
                out.append(' ');
                out.append(PdfContents.RESTORESTATE);
                ByteBuffer buf = ps.over.getInternalBuffer();
                out.append(buf.getBuffer(), 0, ps.replacePoint);
                out.append(PdfContents.SAVESTATE);
                applyRotation(pageN, out);
                out.append(buf.getBuffer(), ps.replacePoint, buf.size() - ps.replacePoint);
                out.append(PdfContents.RESTORESTATE);
                stream = new PdfStream(out.toByteArray());
                stream.flateCompress(compressionLevel);
                ar.add(addToBody(stream).getIndirectReference());
            }
        }

    }

    void alterResources(PageStamp ps)
    {
        ps.pageN.put(PdfName.RESOURCES, ps.pageResources.getResources());
    }

    protected int getNewObjectNumber(PdfReader reader, int number, int generation)
    {
        IntHashtable ref = (IntHashtable)readers2intrefs.get(reader);
        if(ref != null)
        {
            int n = ref.get(number);
            if(n == 0)
            {
                n = getIndirectReferenceNumber();
                ref.put(number, n);
            }
            return n;
        }
        if(currentPdfReaderInstance == null)
        {
            if(append && number < initialXrefSize)
                return number;
            int n = myXref.get(number);
            if(n == 0)
            {
                n = getIndirectReferenceNumber();
                myXref.put(number, n);
            }
            return n;
        } else
        {
            return currentPdfReaderInstance.getNewObjectNumber(number, generation);
        }
    }

    RandomAccessFileOrArray getReaderFile(PdfReader reader)
    {
        if(readers2intrefs.containsKey(reader))
        {
            RandomAccessFileOrArray raf = (RandomAccessFileOrArray)readers2file.get(reader);
            if(raf != null)
                return raf;
            else
                return reader.getSafeFile();
        }
        if(currentPdfReaderInstance == null)
            return file;
        else
            return currentPdfReaderInstance.getReaderFile();
    }

    public void registerReader(PdfReader reader, boolean openFile)
        throws IOException
    {
        if(readers2intrefs.containsKey(reader))
            return;
        readers2intrefs.put(reader, new IntHashtable());
        if(openFile)
        {
            RandomAccessFileOrArray raf = reader.getSafeFile();
            readers2file.put(reader, raf);
            raf.reOpen();
        }
    }

    public void unRegisterReader(PdfReader reader)
    {
        if(!readers2intrefs.containsKey(reader))
            return;
        readers2intrefs.remove(reader);
        RandomAccessFileOrArray raf = (RandomAccessFileOrArray)readers2file.get(reader);
        if(raf == null)
            return;
        readers2file.remove(reader);
        try
        {
            raf.close();
        }
        catch(Exception e) { }
    }

    static void findAllObjects(PdfReader reader, PdfObject obj, IntHashtable hits)
    {
        if(obj == null)
            return;
        switch(obj.type())
        {
        case 10: // '\n'
            PRIndirectReference iref = (PRIndirectReference)obj;
            if(reader != iref.getReader())
                return;
            if(hits.containsKey(iref.getNumber()))
            {
                return;
            } else
            {
                hits.put(iref.getNumber(), 1);
                findAllObjects(reader, PdfReader.getPdfObject(obj), hits);
                return;
            }

        case 5: // '\005'
            PdfArray a = (PdfArray)obj;
            for(int k = 0; k < a.size(); k++)
                findAllObjects(reader, a.getPdfObject(k), hits);

            return;

        case 6: // '\006'
        case 7: // '\007'
            PdfDictionary dic = (PdfDictionary)obj;
            PdfName name;
            for(Iterator i$ = dic.getKeys().iterator(); i$.hasNext(); findAllObjects(reader, dic.get(name), hits))
            {
                Object element = i$.next();
                name = (PdfName)element;
            }

            return;

        case 8: // '\b'
        case 9: // '\t'
        default:
            return;
        }
    }

    public void addComments(FdfReader fdf)
        throws IOException
    {
        if(readers2intrefs.containsKey(fdf))
            return;
        PdfDictionary catalog = fdf.getCatalog();
        catalog = catalog.getAsDict(PdfName.FDF);
        if(catalog == null)
            return;
        PdfArray annots = catalog.getAsArray(PdfName.ANNOTS);
        if(annots == null || annots.size() == 0)
            return;
        registerReader(fdf, false);
        IntHashtable hits = new IntHashtable();
        HashMap irt = new HashMap();
        ArrayList an = new ArrayList();
        for(int k = 0; k < annots.size(); k++)
        {
            PdfObject obj = annots.getPdfObject(k);
            PdfDictionary annot = (PdfDictionary)PdfReader.getPdfObject(obj);
            PdfNumber page = annot.getAsNumber(PdfName.PAGE);
            if(page == null || page.intValue() >= reader.getNumberOfPages())
                continue;
            findAllObjects(fdf, obj, hits);
            an.add(obj);
            if(obj.type() != 10)
                continue;
            PdfObject nm = PdfReader.getPdfObject(annot.get(PdfName.NM));
            if(nm != null && nm.type() == 3)
                irt.put(nm.toString(), obj);
        }

        int arhits[] = hits.getKeys();
        for(int k = 0; k < arhits.length; k++)
        {
            int n = arhits[k];
            PdfObject obj = fdf.getPdfObject(n);
            if(obj.type() == 6)
            {
                PdfObject str = PdfReader.getPdfObject(((PdfDictionary)obj).get(PdfName.IRT));
                if(str != null && str.type() == 3)
                {
                    PdfObject i = (PdfObject)irt.get(str.toString());
                    if(i != null)
                    {
                        PdfDictionary dic2 = new PdfDictionary();
                        dic2.merge((PdfDictionary)obj);
                        dic2.put(PdfName.IRT, i);
                        obj = dic2;
                    }
                }
            }
            addToBody(obj, getNewObjectNumber(fdf, n, 0));
        }

        for(int k = 0; k < an.size(); k++)
        {
            PdfObject obj = (PdfObject)an.get(k);
            PdfDictionary annot = (PdfDictionary)PdfReader.getPdfObject(obj);
            PdfNumber page = annot.getAsNumber(PdfName.PAGE);
            PdfDictionary dic = reader.getPageN(page.intValue() + 1);
            PdfArray annotsp = (PdfArray)PdfReader.getPdfObject(dic.get(PdfName.ANNOTS), dic);
            if(annotsp == null)
            {
                annotsp = new PdfArray();
                dic.put(PdfName.ANNOTS, annotsp);
                markUsed(dic);
            }
            markUsed(annotsp);
            annotsp.add(obj);
        }

    }

    PageStamp getPageStamp(int pageNum)
    {
        PdfDictionary pageN = reader.getPageN(pageNum);
        PageStamp ps = (PageStamp)pagesToContent.get(pageN);
        if(ps == null)
        {
            ps = new PageStamp(this, reader, pageN);
            pagesToContent.put(pageN, ps);
        }
        return ps;
    }

    PdfContentByte getUnderContent(int pageNum)
    {
        if(pageNum < 1 || pageNum > reader.getNumberOfPages())
            return null;
        PageStamp ps = getPageStamp(pageNum);
        if(ps.under == null)
            ps.under = new StampContent(this, ps);
        return ps.under;
    }

    PdfContentByte getOverContent(int pageNum)
    {
        if(pageNum < 1 || pageNum > reader.getNumberOfPages())
            return null;
        PageStamp ps = getPageStamp(pageNum);
        if(ps.over == null)
            ps.over = new StampContent(this, ps);
        return ps.over;
    }

    void correctAcroFieldPages(int page)
    {
        if(acroFields == null)
            return;
        if(page > reader.getNumberOfPages())
            return;
        Map fields = acroFields.getFields();
        for(Iterator i$ = fields.values().iterator(); i$.hasNext();)
        {
            AcroFields.Item item = (AcroFields.Item)i$.next();
            int k = 0;
            while(k < item.size()) 
            {
                int p = item.getPage(k).intValue();
                if(p >= page)
                    item.forcePage(k, p + 1);
                k++;
            }
        }

    }

    private static void moveRectangle(PdfDictionary dic2, PdfReader r, int pageImported, PdfName key, String name)
    {
        Rectangle m = r.getBoxSize(pageImported, name);
        if(m == null)
            dic2.remove(key);
        else
            dic2.put(key, new PdfRectangle(m));
    }

    void replacePage(PdfReader r, int pageImported, int pageReplaced)
    {
        PdfDictionary pageN = reader.getPageN(pageReplaced);
        if(pagesToContent.containsKey(pageN))
        {
            throw new IllegalStateException(MessageLocalization.getComposedMessage("this.page.cannot.be.replaced.new.content.was.already.added", new Object[0]));
        } else
        {
            PdfImportedPage p = getImportedPage(r, pageImported);
            PdfDictionary dic2 = reader.getPageNRelease(pageReplaced);
            dic2.remove(PdfName.RESOURCES);
            dic2.remove(PdfName.CONTENTS);
            moveRectangle(dic2, r, pageImported, PdfName.MEDIABOX, "media");
            moveRectangle(dic2, r, pageImported, PdfName.CROPBOX, "crop");
            moveRectangle(dic2, r, pageImported, PdfName.TRIMBOX, "trim");
            moveRectangle(dic2, r, pageImported, PdfName.ARTBOX, "art");
            moveRectangle(dic2, r, pageImported, PdfName.BLEEDBOX, "bleed");
            dic2.put(PdfName.ROTATE, new PdfNumber(r.getPageRotation(pageImported)));
            PdfContentByte cb = getOverContent(pageReplaced);
            cb.addTemplate(p, 0.0F, 0.0F);
            PageStamp ps = (PageStamp)pagesToContent.get(pageN);
            ps.replacePoint = ps.over.getInternalBuffer().size();
            return;
        }
    }

    void insertPage(int pageNumber, Rectangle mediabox)
    {
        Rectangle media = new Rectangle(mediabox);
        int rotation = media.getRotation() % 360;
        PdfDictionary page = new PdfDictionary(PdfName.PAGE);
        page.put(PdfName.RESOURCES, new PdfDictionary());
        page.put(PdfName.ROTATE, new PdfNumber(rotation));
        page.put(PdfName.MEDIABOX, new PdfRectangle(media, rotation));
        PRIndirectReference pref = reader.addPdfObject(page);
        PdfDictionary parent;
        PRIndirectReference parentRef;
        if(pageNumber > reader.getNumberOfPages())
        {
            PdfDictionary lastPage = reader.getPageNRelease(reader.getNumberOfPages());
            parentRef = (PRIndirectReference)lastPage.get(PdfName.PARENT);
            parentRef = new PRIndirectReference(reader, parentRef.getNumber());
            parent = (PdfDictionary)PdfReader.getPdfObject(parentRef);
            PdfArray kids = (PdfArray)PdfReader.getPdfObject(parent.get(PdfName.KIDS), parent);
            kids.add(pref);
            markUsed(kids);
            reader.pageRefs.insertPage(pageNumber, pref);
        } else
        {
            if(pageNumber < 1)
                pageNumber = 1;
            PdfDictionary firstPage = reader.getPageN(pageNumber);
            PRIndirectReference firstPageRef = reader.getPageOrigRef(pageNumber);
            reader.releasePage(pageNumber);
            parentRef = (PRIndirectReference)firstPage.get(PdfName.PARENT);
            parentRef = new PRIndirectReference(reader, parentRef.getNumber());
            parent = (PdfDictionary)PdfReader.getPdfObject(parentRef);
            PdfArray kids = (PdfArray)PdfReader.getPdfObject(parent.get(PdfName.KIDS), parent);
            int len = kids.size();
            int num = firstPageRef.getNumber();
            int k = 0;
            do
            {
                if(k >= len)
                    break;
                PRIndirectReference cur = (PRIndirectReference)kids.getPdfObject(k);
                if(num == cur.getNumber())
                {
                    kids.add(k, pref);
                    break;
                }
                k++;
            } while(true);
            if(len == kids.size())
                throw new RuntimeException(MessageLocalization.getComposedMessage("internal.inconsistence", new Object[0]));
            markUsed(kids);
            reader.pageRefs.insertPage(pageNumber, pref);
            correctAcroFieldPages(pageNumber);
        }
        page.put(PdfName.PARENT, parentRef);
        for(; parent != null; parent = parent.getAsDict(PdfName.PARENT))
        {
            markUsed(parent);
            PdfNumber count = (PdfNumber)PdfReader.getPdfObjectRelease(parent.get(PdfName.COUNT));
            parent.put(PdfName.COUNT, new PdfNumber(count.intValue() + 1));
        }

    }

    boolean isRotateContents()
    {
        return rotateContents;
    }

    void setRotateContents(boolean rotateContents)
    {
        this.rotateContents = rotateContents;
    }

    boolean isContentWritten()
    {
        return body.size() > 1;
    }

    AcroFields getAcroFields()
    {
        if(acroFields == null)
        {
            acroFields = new AcroFields(reader, this);
            try
            {
                Iterator i$ = acroFields.getFields().keySet().iterator();
                do
                {
                    if(!i$.hasNext())
                        break;
                    String key = (String)i$.next();
                    if(4 == acroFields.getFieldType(key))
                    {
                        String value = acroFields.getField(key).trim();
                        if(value.length() > 0)
                            acroFields.setField(key, value, value);
                    }
                } while(true);
            }
            catch(DocumentException de) { }
            catch(IOException ioe) { }
        }
        return acroFields;
    }

    void setFormFlattening(boolean flat)
    {
        this.flat = flat;
    }

    void setFreeTextFlattening(boolean flat)
    {
        flatFreeText = flat;
    }

    boolean partialFormFlattening(String name)
    {
        getAcroFields();
        if(acroFields.getXfa().isXfaPresent())
            throw new UnsupportedOperationException(MessageLocalization.getComposedMessage("partial.form.flattening.is.not.supported.with.xfa.forms", new Object[0]));
        if(!acroFields.getFields().containsKey(name))
        {
            return false;
        } else
        {
            partialFlattening.add(name);
            return true;
        }
    }

    protected void flatFields()
    {
        if(append)
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("field.flattening.is.not.supported.in.append.mode", new Object[0]));
        getAcroFields();
        Map fields = acroFields.getFields();
        if(fieldsAdded && partialFlattening.isEmpty())
        {
            String s;
            for(Iterator i$ = fields.keySet().iterator(); i$.hasNext(); partialFlattening.add(s))
                s = (String)i$.next();

        }
        PdfDictionary acroForm = reader.getCatalog().getAsDict(PdfName.ACROFORM);
        PdfArray acroFds = null;
        if(acroForm != null)
            acroFds = (PdfArray)PdfReader.getPdfObject(acroForm.get(PdfName.FIELDS), acroForm);
        Iterator i$ = fields.entrySet().iterator();
        do
        {
            if(!i$.hasNext())
                break;
            java.util.Map.Entry entry = (java.util.Map.Entry)i$.next();
            String name = (String)entry.getKey();
            if(partialFlattening.isEmpty() || partialFlattening.contains(name))
            {
                AcroFields.Item item = (AcroFields.Item)entry.getValue();
                int k = 0;
                while(k < item.size()) 
                {
                    PdfDictionary merged = item.getMerged(k);
                    PdfNumber ff = merged.getAsNumber(PdfName.F);
                    int flags = 0;
                    if(ff != null)
                        flags = ff.intValue();
                    int page = item.getPage(k).intValue();
                    if(page >= 1)
                    {
                        PdfDictionary appDic = merged.getAsDict(PdfName.AP);
                        if(appDic != null && (flags & 4) != 0 && (flags & 2) == 0)
                        {
                            PdfObject obj = appDic.get(PdfName.N);
                            PdfAppearance app = null;
                            if(obj != null)
                            {
                                PdfObject objReal = PdfReader.getPdfObject(obj);
                                if((obj instanceof PdfIndirectReference) && !obj.isIndirect())
                                    app = new PdfAppearance((PdfIndirectReference)obj);
                                else
                                if(objReal instanceof PdfStream)
                                {
                                    ((PdfDictionary)objReal).put(PdfName.SUBTYPE, PdfName.FORM);
                                    app = new PdfAppearance((PdfIndirectReference)obj);
                                } else
                                if(objReal != null && objReal.isDictionary())
                                {
                                    PdfName as = merged.getAsName(PdfName.AS);
                                    if(as != null)
                                    {
                                        PdfIndirectReference iref = (PdfIndirectReference)((PdfDictionary)objReal).get(as);
                                        if(iref != null)
                                        {
                                            app = new PdfAppearance(iref);
                                            if(iref.isIndirect())
                                            {
                                                objReal = PdfReader.getPdfObject(iref);
                                                ((PdfDictionary)objReal).put(PdfName.SUBTYPE, PdfName.FORM);
                                            }
                                        }
                                    }
                                }
                            }
                            if(app != null)
                            {
                                Rectangle box = PdfReader.getNormalizedRectangle(merged.getAsArray(PdfName.RECT));
                                PdfContentByte cb = getOverContent(page);
                                cb.setLiteral("Q ");
                                cb.addTemplate(app, box.getLeft(), box.getBottom());
                                cb.setLiteral("q ");
                            }
                        }
                        if(!partialFlattening.isEmpty())
                        {
                            PdfDictionary pageDic = reader.getPageN(page);
                            PdfArray annots = pageDic.getAsArray(PdfName.ANNOTS);
                            if(annots != null)
                            {
label0:
                                for(int idx = 0; idx < annots.size(); idx++)
                                {
                                    PdfObject ran = annots.getPdfObject(idx);
                                    if(!ran.isIndirect())
                                        continue;
                                    PdfObject ran2 = item.getWidgetRef(k);
                                    if(!ran2.isIndirect() || ((PRIndirectReference)ran).getNumber() != ((PRIndirectReference)ran2).getNumber())
                                        continue;
                                    annots.remove(idx--);
                                    PRIndirectReference wdref = (PRIndirectReference)ran2;
                                    do
                                    {
                                        PdfDictionary wd = (PdfDictionary)PdfReader.getPdfObject(wdref);
                                        PRIndirectReference parentRef = (PRIndirectReference)wd.get(PdfName.PARENT);
                                        PdfReader.killIndirect(wdref);
                                        if(parentRef == null)
                                        {
                                            int fr = 0;
                                            do
                                            {
                                                if(fr >= acroFds.size())
                                                    continue label0;
                                                PdfObject h = acroFds.getPdfObject(fr);
                                                if(h.isIndirect() && ((PRIndirectReference)h).getNumber() == wdref.getNumber())
                                                {
                                                    acroFds.remove(fr);
                                                    fr--;
                                                }
                                                fr++;
                                            } while(true);
                                        }
                                        PdfDictionary parent = (PdfDictionary)PdfReader.getPdfObject(parentRef);
                                        PdfArray kids = parent.getAsArray(PdfName.KIDS);
                                        for(int fr = 0; fr < kids.size(); fr++)
                                        {
                                            PdfObject h = kids.getPdfObject(fr);
                                            if(h.isIndirect() && ((PRIndirectReference)h).getNumber() == wdref.getNumber())
                                            {
                                                kids.remove(fr);
                                                fr--;
                                            }
                                        }

                                        if(!kids.isEmpty())
                                            continue label0;
                                        wdref = parentRef;
                                    } while(true);
                                }

                                if(annots.isEmpty())
                                {
                                    PdfReader.killIndirect(pageDic.get(PdfName.ANNOTS));
                                    pageDic.remove(PdfName.ANNOTS);
                                }
                            }
                        }
                    }
                    k++;
                }
            }
        } while(true);
        if(!fieldsAdded && partialFlattening.isEmpty())
        {
            for(int page = 1; page <= reader.getNumberOfPages(); page++)
            {
                PdfDictionary pageDic = reader.getPageN(page);
                PdfArray annots = pageDic.getAsArray(PdfName.ANNOTS);
                if(annots == null)
                    continue;
                for(int idx = 0; idx < annots.size(); idx++)
                {
                    PdfObject annoto = annots.getDirectObject(idx);
                    if((!(annoto instanceof PdfIndirectReference) || annoto.isIndirect()) && (!annoto.isDictionary() || PdfName.WIDGET.equals(((PdfDictionary)annoto).get(PdfName.SUBTYPE))))
                    {
                        annots.remove(idx);
                        idx--;
                    }
                }

                if(annots.isEmpty())
                {
                    PdfReader.killIndirect(pageDic.get(PdfName.ANNOTS));
                    pageDic.remove(PdfName.ANNOTS);
                }
            }

            eliminateAcroformObjects();
        }
    }

    void eliminateAcroformObjects()
    {
        PdfObject acro = reader.getCatalog().get(PdfName.ACROFORM);
        if(acro == null)
            return;
        PdfDictionary acrodic = (PdfDictionary)PdfReader.getPdfObject(acro);
        reader.killXref(acrodic.get(PdfName.XFA));
        acrodic.remove(PdfName.XFA);
        PdfObject iFields = acrodic.get(PdfName.FIELDS);
        if(iFields != null)
        {
            PdfDictionary kids = new PdfDictionary();
            kids.put(PdfName.KIDS, iFields);
            sweepKids(kids);
            PdfReader.killIndirect(iFields);
            acrodic.put(PdfName.FIELDS, new PdfArray());
        }
        acrodic.remove(PdfName.SIGFLAGS);
        acrodic.remove(PdfName.NEEDAPPEARANCES);
        acrodic.remove(PdfName.DR);
    }

    void sweepKids(PdfObject obj)
    {
        PdfObject oo = PdfReader.killIndirect(obj);
        if(oo == null || !oo.isDictionary())
            return;
        PdfDictionary dic = (PdfDictionary)oo;
        PdfArray kids = (PdfArray)PdfReader.killIndirect(dic.get(PdfName.KIDS));
        if(kids == null)
            return;
        for(int k = 0; k < kids.size(); k++)
            sweepKids(kids.getPdfObject(k));

    }

    protected void flatFreeTextFields()
    {
        if(append)
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("freetext.flattening.is.not.supported.in.append.mode", new Object[0]));
        for(int page = 1; page <= reader.getNumberOfPages(); page++)
        {
            PdfDictionary pageDic = reader.getPageN(page);
            PdfArray annots = pageDic.getAsArray(PdfName.ANNOTS);
            if(annots == null)
                continue;
            for(int idx = 0; idx < annots.size(); idx++)
            {
                PdfObject annoto = annots.getDirectObject(idx);
                if((annoto instanceof PdfIndirectReference) && !annoto.isIndirect())
                    continue;
                PdfDictionary annDic = (PdfDictionary)annoto;
                if(!((PdfName)annDic.get(PdfName.SUBTYPE)).equals(PdfName.FREETEXT))
                    continue;
                PdfNumber ff = annDic.getAsNumber(PdfName.F);
                int flags = ff == null ? 0 : ff.intValue();
                if((flags & 4) == 0 || (flags & 2) != 0)
                    continue;
                PdfObject obj1 = annDic.get(PdfName.AP);
                if(obj1 == null)
                    continue;
                PdfDictionary appDic = (obj1 instanceof PdfIndirectReference) ? (PdfDictionary)PdfReader.getPdfObject(obj1) : (PdfDictionary)obj1;
                PdfObject obj = appDic.get(PdfName.N);
                PdfAppearance app = null;
                PdfObject objReal = PdfReader.getPdfObject(obj);
                if((obj instanceof PdfIndirectReference) && !obj.isIndirect())
                    app = new PdfAppearance((PdfIndirectReference)obj);
                else
                if(objReal instanceof PdfStream)
                {
                    ((PdfDictionary)objReal).put(PdfName.SUBTYPE, PdfName.FORM);
                    app = new PdfAppearance((PdfIndirectReference)obj);
                } else
                if(objReal.isDictionary())
                {
                    PdfName as_p = appDic.getAsName(PdfName.AS);
                    if(as_p != null)
                    {
                        PdfIndirectReference iref = (PdfIndirectReference)((PdfDictionary)objReal).get(as_p);
                        if(iref != null)
                        {
                            app = new PdfAppearance(iref);
                            if(iref.isIndirect())
                            {
                                objReal = PdfReader.getPdfObject(iref);
                                ((PdfDictionary)objReal).put(PdfName.SUBTYPE, PdfName.FORM);
                            }
                        }
                    }
                }
                if(app != null)
                {
                    Rectangle box = PdfReader.getNormalizedRectangle(annDic.getAsArray(PdfName.RECT));
                    PdfContentByte cb = getOverContent(page);
                    cb.setLiteral("Q ");
                    cb.addTemplate(app, box.getLeft(), box.getBottom());
                    cb.setLiteral("q ");
                }
            }

            for(int idx = 0; idx < annots.size(); idx++)
            {
                PdfDictionary annot = annots.getAsDict(idx);
                if(annot != null && PdfName.FREETEXT.equals(annot.get(PdfName.SUBTYPE)))
                {
                    annots.remove(idx);
                    idx--;
                }
            }

            if(annots.isEmpty())
            {
                PdfReader.killIndirect(pageDic.get(PdfName.ANNOTS));
                pageDic.remove(PdfName.ANNOTS);
            }
        }

    }

    public PdfIndirectReference getPageReference(int page)
    {
        PdfIndirectReference ref = reader.getPageOrigRef(page);
        if(ref == null)
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("invalid.page.number.1", page));
        else
            return ref;
    }

    public void addAnnotation(PdfAnnotation annot)
    {
        throw new RuntimeException(MessageLocalization.getComposedMessage("unsupported.in.this.context.use.pdfstamper.addannotation", new Object[0]));
    }

    void addDocumentField(PdfIndirectReference ref)
    {
        PdfDictionary catalog = reader.getCatalog();
        PdfDictionary acroForm = (PdfDictionary)PdfReader.getPdfObject(catalog.get(PdfName.ACROFORM), catalog);
        if(acroForm == null)
        {
            acroForm = new PdfDictionary();
            catalog.put(PdfName.ACROFORM, acroForm);
            markUsed(catalog);
        }
        PdfArray fields = (PdfArray)PdfReader.getPdfObject(acroForm.get(PdfName.FIELDS), acroForm);
        if(fields == null)
        {
            fields = new PdfArray();
            acroForm.put(PdfName.FIELDS, fields);
            markUsed(acroForm);
        }
        if(!acroForm.contains(PdfName.DA))
        {
            acroForm.put(PdfName.DA, new PdfString("/Helv 0 Tf 0 g "));
            markUsed(acroForm);
        }
        fields.add(ref);
        markUsed(fields);
    }

    protected void addFieldResources()
        throws IOException
    {
        if(fieldTemplates.isEmpty())
            return;
        PdfDictionary catalog = reader.getCatalog();
        PdfDictionary acroForm = (PdfDictionary)PdfReader.getPdfObject(catalog.get(PdfName.ACROFORM), catalog);
        if(acroForm == null)
        {
            acroForm = new PdfDictionary();
            catalog.put(PdfName.ACROFORM, acroForm);
            markUsed(catalog);
        }
        PdfDictionary dr = (PdfDictionary)PdfReader.getPdfObject(acroForm.get(PdfName.DR), acroForm);
        if(dr == null)
        {
            dr = new PdfDictionary();
            acroForm.put(PdfName.DR, dr);
            markUsed(acroForm);
        }
        markUsed(dr);
        PdfTemplate template;
        for(Iterator i$ = fieldTemplates.iterator(); i$.hasNext(); PdfFormField.mergeResources(dr, (PdfDictionary)template.getResources(), this))
            template = (PdfTemplate)i$.next();

        PdfDictionary fonts = dr.getAsDict(PdfName.FONT);
        if(fonts == null)
        {
            fonts = new PdfDictionary();
            dr.put(PdfName.FONT, fonts);
        }
        if(!fonts.contains(PdfName.HELV))
        {
            PdfDictionary dic = new PdfDictionary(PdfName.FONT);
            dic.put(PdfName.BASEFONT, PdfName.HELVETICA);
            dic.put(PdfName.ENCODING, PdfName.WIN_ANSI_ENCODING);
            dic.put(PdfName.NAME, PdfName.HELV);
            dic.put(PdfName.SUBTYPE, PdfName.TYPE1);
            fonts.put(PdfName.HELV, addToBody(dic).getIndirectReference());
        }
        if(!fonts.contains(PdfName.ZADB))
        {
            PdfDictionary dic = new PdfDictionary(PdfName.FONT);
            dic.put(PdfName.BASEFONT, PdfName.ZAPFDINGBATS);
            dic.put(PdfName.NAME, PdfName.ZADB);
            dic.put(PdfName.SUBTYPE, PdfName.TYPE1);
            fonts.put(PdfName.ZADB, addToBody(dic).getIndirectReference());
        }
        if(acroForm.get(PdfName.DA) == null)
        {
            acroForm.put(PdfName.DA, new PdfString("/Helv 0 Tf 0 g "));
            markUsed(acroForm);
        }
    }

    void expandFields(PdfFormField field, ArrayList allAnnots)
    {
        allAnnots.add(field);
        ArrayList kids = field.getKids();
        if(kids != null)
        {
            for(int k = 0; k < kids.size(); k++)
                expandFields((PdfFormField)kids.get(k), allAnnots);

        }
    }

    void addAnnotation(PdfAnnotation annot, PdfDictionary pageN)
    {
        ArrayList allAnnots;
        PdfFormField field;
        try
        {
            allAnnots = new ArrayList();
            if(!annot.isForm())
                break MISSING_BLOCK_LABEL_50;
            fieldsAdded = true;
            getAcroFields();
            field = (PdfFormField)annot;
            if(field.getParent() != null)
                return;
        }
        catch(IOException e)
        {
            throw new ExceptionConverter(e);
        }
        expandFields(field, allAnnots);
        break MISSING_BLOCK_LABEL_56;
        allAnnots.add(annot);
        for(int k = 0; k < allAnnots.size(); k++)
        {
            annot = (PdfAnnotation)allAnnots.get(k);
            if(annot.getPlaceInPage() > 0)
                pageN = reader.getPageN(annot.getPlaceInPage());
            if(annot.isForm())
            {
                if(!annot.isUsed())
                {
                    HashSet templates = annot.getTemplates();
                    if(templates != null)
                        fieldTemplates.addAll(templates);
                }
                PdfFormField field = (PdfFormField)annot;
                if(field.getParent() == null)
                    addDocumentField(field.getIndirectReference());
            }
            if(annot.isAnnotation())
            {
                PdfObject pdfobj = PdfReader.getPdfObject(pageN.get(PdfName.ANNOTS), pageN);
                PdfArray annots = null;
                if(pdfobj == null || !pdfobj.isArray())
                {
                    annots = new PdfArray();
                    pageN.put(PdfName.ANNOTS, annots);
                    markUsed(pageN);
                } else
                {
                    annots = (PdfArray)pdfobj;
                }
                annots.add(annot.getIndirectReference());
                markUsed(annots);
                if(!annot.isUsed())
                {
                    PdfRectangle rect = (PdfRectangle)annot.get(PdfName.RECT);
                    if(rect != null && (rect.left() != 0.0F || rect.right() != 0.0F || rect.top() != 0.0F || rect.bottom() != 0.0F))
                    {
                        int rotation = reader.getPageRotation(pageN);
                        Rectangle pageSize = reader.getPageSizeWithRotation(pageN);
                        switch(rotation)
                        {
                        case 90: // 'Z'
                            annot.put(PdfName.RECT, new PdfRectangle(pageSize.getTop() - rect.top(), rect.right(), pageSize.getTop() - rect.bottom(), rect.left()));
                            break;

                        case 180: 
                            annot.put(PdfName.RECT, new PdfRectangle(pageSize.getRight() - rect.left(), pageSize.getTop() - rect.bottom(), pageSize.getRight() - rect.right(), pageSize.getTop() - rect.top()));
                            break;

                        case 270: 
                            annot.put(PdfName.RECT, new PdfRectangle(rect.bottom(), pageSize.getRight() - rect.left(), rect.top(), pageSize.getRight() - rect.right()));
                            break;
                        }
                    }
                }
            }
            if(!annot.isUsed())
            {
                annot.setUsed();
                addToBody(annot, annot.getIndirectReference());
            }
        }

        break MISSING_BLOCK_LABEL_556;
    }

    void addAnnotation(PdfAnnotation annot, int page)
    {
        annot.setPage(page);
        addAnnotation(annot, reader.getPageN(page));
    }

    private void outlineTravel(PRIndirectReference outline)
    {
        PdfDictionary outlineR;
        for(; outline != null; outline = (PRIndirectReference)outlineR.get(PdfName.NEXT))
        {
            outlineR = (PdfDictionary)PdfReader.getPdfObjectRelease(outline);
            PRIndirectReference first = (PRIndirectReference)outlineR.get(PdfName.FIRST);
            if(first != null)
                outlineTravel(first);
            PdfReader.killIndirect(outlineR.get(PdfName.DEST));
            PdfReader.killIndirect(outlineR.get(PdfName.A));
            PdfReader.killIndirect(outline);
        }

    }

    void deleteOutlines()
    {
        PdfDictionary catalog = reader.getCatalog();
        PdfObject obj = catalog.get(PdfName.OUTLINES);
        if(obj == null)
            return;
        if(obj instanceof PRIndirectReference)
        {
            PRIndirectReference outlines = (PRIndirectReference)obj;
            outlineTravel(outlines);
            PdfReader.killIndirect(outlines);
        }
        catalog.remove(PdfName.OUTLINES);
        markUsed(catalog);
    }

    protected void setJavaScript()
        throws IOException
    {
        HashMap djs = pdf.getDocumentLevelJS();
        if(djs.isEmpty())
            return;
        PdfDictionary catalog = reader.getCatalog();
        PdfDictionary names = (PdfDictionary)PdfReader.getPdfObject(catalog.get(PdfName.NAMES), catalog);
        if(names == null)
        {
            names = new PdfDictionary();
            catalog.put(PdfName.NAMES, names);
            markUsed(catalog);
        }
        markUsed(names);
        PdfDictionary tree = PdfNameTree.writeTree(djs, this);
        names.put(PdfName.JAVASCRIPT, addToBody(tree).getIndirectReference());
    }

    protected void addFileAttachments()
        throws IOException
    {
        HashMap fs = pdf.getDocumentFileAttachment();
        if(fs.isEmpty())
            return;
        PdfDictionary catalog = reader.getCatalog();
        PdfDictionary names = (PdfDictionary)PdfReader.getPdfObject(catalog.get(PdfName.NAMES), catalog);
        if(names == null)
        {
            names = new PdfDictionary();
            catalog.put(PdfName.NAMES, names);
            markUsed(catalog);
        }
        markUsed(names);
        HashMap old = PdfNameTree.readTree((PdfDictionary)PdfReader.getPdfObjectRelease(names.get(PdfName.EMBEDDEDFILES)));
        java.util.Map.Entry entry;
        StringBuilder nn;
        for(Iterator i$ = fs.entrySet().iterator(); i$.hasNext(); old.put(nn.toString(), entry.getValue()))
        {
            entry = (java.util.Map.Entry)i$.next();
            String name = (String)entry.getKey();
            int k = 0;
            for(nn = new StringBuilder(name); old.containsKey(nn.toString()); nn.append(" ").append(k))
                k++;

        }

        PdfDictionary tree = PdfNameTree.writeTree(old, this);
        PdfObject oldEmbeddedFiles = names.get(PdfName.EMBEDDEDFILES);
        if(oldEmbeddedFiles != null)
            PdfReader.killIndirect(oldEmbeddedFiles);
        names.put(PdfName.EMBEDDEDFILES, addToBody(tree).getIndirectReference());
    }

    void makePackage(PdfCollection collection)
    {
        PdfDictionary catalog = reader.getCatalog();
        catalog.put(PdfName.COLLECTION, collection);
    }

    protected void setOutlines()
        throws IOException
    {
        if(newBookmarks == null)
            return;
        deleteOutlines();
        if(newBookmarks.isEmpty())
        {
            return;
        } else
        {
            PdfDictionary catalog = reader.getCatalog();
            boolean namedAsNames = catalog.get(PdfName.DESTS) != null;
            writeOutlines(catalog, namedAsNames);
            markUsed(catalog);
            return;
        }
    }

    public void setViewerPreferences(int preferences)
    {
        useVp = true;
        viewerPreferences.setViewerPreferences(preferences);
    }

    public void addViewerPreference(PdfName key, PdfObject value)
    {
        useVp = true;
        viewerPreferences.addViewerPreference(key, value);
    }

    public void setSigFlags(int f)
    {
        sigFlags |= f;
    }

    public void setPageAction(PdfName actionType, PdfAction action)
        throws PdfException
    {
        throw new UnsupportedOperationException(MessageLocalization.getComposedMessage("use.setpageaction.pdfname.actiontype.pdfaction.action.int.page", new Object[0]));
    }

    void setPageAction(PdfName actionType, PdfAction action, int page)
        throws PdfException
    {
        if(!actionType.equals(PAGE_OPEN) && !actionType.equals(PAGE_CLOSE))
            throw new PdfException(MessageLocalization.getComposedMessage("invalid.page.additional.action.type.1", new Object[] {
                actionType.toString()
            }));
        PdfDictionary pg = reader.getPageN(page);
        PdfDictionary aa = (PdfDictionary)PdfReader.getPdfObject(pg.get(PdfName.AA), pg);
        if(aa == null)
        {
            aa = new PdfDictionary();
            pg.put(PdfName.AA, aa);
            markUsed(pg);
        }
        aa.put(actionType, action);
        markUsed(aa);
    }

    public void setDuration(int seconds)
    {
        throw new UnsupportedOperationException(MessageLocalization.getComposedMessage("use.setpageaction.pdfname.actiontype.pdfaction.action.int.page", new Object[0]));
    }

    public void setTransition(PdfTransition transition)
    {
        throw new UnsupportedOperationException(MessageLocalization.getComposedMessage("use.setpageaction.pdfname.actiontype.pdfaction.action.int.page", new Object[0]));
    }

    void setDuration(int seconds, int page)
    {
        PdfDictionary pg = reader.getPageN(page);
        if(seconds < 0)
            pg.remove(PdfName.DUR);
        else
            pg.put(PdfName.DUR, new PdfNumber(seconds));
        markUsed(pg);
    }

    void setTransition(PdfTransition transition, int page)
    {
        PdfDictionary pg = reader.getPageN(page);
        if(transition == null)
            pg.remove(PdfName.TRANS);
        else
            pg.put(PdfName.TRANS, transition.getTransitionDictionary());
        markUsed(pg);
    }

    protected void markUsed(PdfObject obj)
    {
        if(append && obj != null)
        {
            PRIndirectReference ref = null;
            if(obj.type() == 10)
                ref = (PRIndirectReference)obj;
            else
                ref = obj.getIndRef();
            if(ref != null)
                marked.put(ref.getNumber(), 1);
        }
    }

    protected void markUsed(int num)
    {
        if(append)
            marked.put(num, 1);
    }

    boolean isAppend()
    {
        return append;
    }

    public void setAdditionalAction(PdfName actionType, PdfAction action)
        throws PdfException
    {
        if(!actionType.equals(DOCUMENT_CLOSE) && !actionType.equals(WILL_SAVE) && !actionType.equals(DID_SAVE) && !actionType.equals(WILL_PRINT) && !actionType.equals(DID_PRINT))
            throw new PdfException(MessageLocalization.getComposedMessage("invalid.additional.action.type.1", new Object[] {
                actionType.toString()
            }));
        PdfDictionary aa = reader.getCatalog().getAsDict(PdfName.AA);
        if(aa == null)
        {
            if(action == null)
                return;
            aa = new PdfDictionary();
            reader.getCatalog().put(PdfName.AA, aa);
        }
        markUsed(aa);
        if(action == null)
            aa.remove(actionType);
        else
            aa.put(actionType, action);
    }

    public void setOpenAction(PdfAction action)
    {
        openAction = action;
    }

    public void setOpenAction(String name)
    {
        throw new UnsupportedOperationException(MessageLocalization.getComposedMessage("open.actions.by.name.are.not.supported", new Object[0]));
    }

    public void setThumbnail(Image image)
    {
        throw new UnsupportedOperationException(MessageLocalization.getComposedMessage("use.pdfstamper.setthumbnail", new Object[0]));
    }

    void setThumbnail(Image image, int page)
        throws PdfException, DocumentException
    {
        PdfIndirectReference thumb = getImageReference(addDirectImageSimple(image));
        reader.resetReleasePage();
        PdfDictionary dic = reader.getPageN(page);
        dic.put(PdfName.THUMB, thumb);
        reader.resetReleasePage();
    }

    public PdfContentByte getDirectContentUnder()
    {
        throw new UnsupportedOperationException(MessageLocalization.getComposedMessage("use.pdfstamper.getundercontent.or.pdfstamper.getovercontent", new Object[0]));
    }

    public PdfContentByte getDirectContent()
    {
        throw new UnsupportedOperationException(MessageLocalization.getComposedMessage("use.pdfstamper.getundercontent.or.pdfstamper.getovercontent", new Object[0]));
    }

    protected void readOCProperties()
    {
        if(!documentOCG.isEmpty())
            return;
        PdfDictionary dict = reader.getCatalog().getAsDict(PdfName.OCPROPERTIES);
        if(dict == null)
            return;
        PdfArray ocgs = dict.getAsArray(PdfName.OCGS);
        HashMap ocgmap = new HashMap();
        PdfIndirectReference ref;
        PdfLayer layer;
        for(Iterator i = ocgs.listIterator(); i.hasNext(); ocgmap.put(ref.toString(), layer))
        {
            ref = (PdfIndirectReference)i.next();
            layer = new PdfLayer(null);
            layer.setRef(ref);
            layer.setOnPanel(false);
            layer.merge((PdfDictionary)PdfReader.getPdfObject(ref));
        }

        PdfDictionary d = dict.getAsDict(PdfName.D);
        PdfArray off = d.getAsArray(PdfName.OFF);
        if(off != null)
        {
            PdfLayer layer;
            for(Iterator i = off.listIterator(); i.hasNext(); layer.setOn(false))
            {
                PdfIndirectReference ref = (PdfIndirectReference)i.next();
                layer = (PdfLayer)ocgmap.get(ref.toString());
            }

        }
        PdfArray order = d.getAsArray(PdfName.ORDER);
        if(order != null)
            addOrder(null, order, ocgmap);
        documentOCG.addAll(ocgmap.values());
        OCGRadioGroup = d.getAsArray(PdfName.RBGROUPS);
        if(OCGRadioGroup == null)
            OCGRadioGroup = new PdfArray();
        OCGLocked = d.getAsArray(PdfName.LOCKED);
        if(OCGLocked == null)
            OCGLocked = new PdfArray();
    }

    private void addOrder(PdfLayer parent, PdfArray arr, Map ocgmap)
    {
        for(int i = 0; i < arr.size(); i++)
        {
            PdfObject obj = arr.getPdfObject(i);
            if(obj.isIndirect())
            {
                PdfLayer layer = (PdfLayer)ocgmap.get(obj.toString());
                if(layer == null)
                    continue;
                layer.setOnPanel(true);
                registerLayer(layer);
                if(parent != null)
                    parent.addChild(layer);
                if(arr.size() > i + 1 && arr.getPdfObject(i + 1).isArray())
                {
                    i++;
                    addOrder(layer, (PdfArray)arr.getPdfObject(i), ocgmap);
                }
                continue;
            }
            if(!obj.isArray())
                continue;
            PdfArray sub = (PdfArray)obj;
            if(sub.isEmpty())
                return;
            obj = sub.getPdfObject(0);
            if(obj.isString())
            {
                PdfLayer layer = new PdfLayer(obj.toString());
                layer.setOnPanel(true);
                registerLayer(layer);
                if(parent != null)
                    parent.addChild(layer);
                PdfArray array = new PdfArray();
                for(Iterator j = sub.listIterator(); j.hasNext(); array.add((PdfObject)j.next()));
                addOrder(layer, array, ocgmap);
            } else
            {
                addOrder(parent, (PdfArray)obj, ocgmap);
            }
        }

    }

    public Map getPdfLayers()
    {
        if(documentOCG.isEmpty())
            readOCProperties();
        HashMap map = new HashMap();
        PdfLayer layer;
        String key;
        for(Iterator i$ = documentOCG.iterator(); i$.hasNext(); map.put(key, layer))
        {
            PdfOCG pdfOCG = (PdfOCG)i$.next();
            layer = (PdfLayer)pdfOCG;
            if(layer.getTitle() == null)
                key = layer.getAsString(PdfName.NAME).toString();
            else
                key = layer.getTitle();
            if(!map.containsKey(key))
                continue;
            int seq = 2;
            String tmp;
            for(tmp = (new StringBuilder()).append(key).append("(").append(seq).append(")").toString(); map.containsKey(tmp); tmp = (new StringBuilder()).append(key).append("(").append(seq).append(")").toString())
                seq++;

            key = tmp;
        }

        return map;
    }

    public void createXmpMetadata()
    {
        try
        {
            xmpWriter = createXmpWriter(null, reader.getInfo());
            xmpMetadata = null;
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
    }

    HashMap readers2intrefs;
    HashMap readers2file;
    protected RandomAccessFileOrArray file;
    PdfReader reader;
    IntHashtable myXref;
    HashMap pagesToContent;
    protected boolean closed;
    private boolean rotateContents;
    protected AcroFields acroFields;
    protected boolean flat;
    protected boolean flatFreeText;
    protected int namePtr[] = {
        0
    };
    protected HashSet partialFlattening;
    protected boolean useVp;
    protected PdfViewerPreferencesImp viewerPreferences;
    protected HashSet fieldTemplates;
    protected boolean fieldsAdded;
    protected int sigFlags;
    protected boolean append;
    protected IntHashtable marked;
    protected int initialXrefSize;
    protected PdfAction openAction;
    protected Counter COUNTER;
}
