// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfCopy.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.*;
import co.com.pdf.text.error_messages.MessageLocalization;
import co.com.pdf.text.exceptions.BadPasswordException;
import co.com.pdf.text.log.Counter;
import co.com.pdf.text.log.CounterFactory;
import java.io.*;
import java.util.*;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfWriter, PdfDocument, PdfDictionary, PdfStructTreeController, 
//            PdfIndirectObject, PdfName, PdfObject, PdfIndirectReference, 
//            PdfNull, PdfNumber, PRIndirectReference, PRStream, 
//            PdfArray, PdfLiteral, PdfBoolean, PdfRectangle, 
//            PageResources, PdfPage, PdfReader, AcroFields, 
//            PdfString, PdfTemplate, BadPdfFormatException, PdfImportedPage, 
//            PdfException, PdfStructureTreeRoot, PdfReaderInstance, PdfPages, 
//            PdfFormField, PdfPageEvent, PdfStream, PdfOutline, 
//            PdfAnnotation, PdfContents, PdfContentByte, ByteBuffer

public class PdfCopy extends PdfWriter
{
    public static class StampContent extends PdfContentByte
    {

        public PdfContentByte getDuplicate()
        {
            return new StampContent(writer, pageResources);
        }

        PageResources getPageResources()
        {
            return pageResources;
        }

        PageResources pageResources;

        StampContent(PdfWriter writer, PageResources pageResources)
        {
            super(writer);
            this.pageResources = pageResources;
        }
    }

    public static class PageStamp
    {

        public PdfContentByte getUnderContent()
        {
            if(under == null)
            {
                if(pageResources == null)
                {
                    pageResources = new PageResources();
                    PdfDictionary resources = pageN.getAsDict(PdfName.RESOURCES);
                    pageResources.setOriginalResources(resources, cstp.namePtr);
                }
                under = new StampContent(cstp, pageResources);
            }
            return under;
        }

        public PdfContentByte getOverContent()
        {
            if(over == null)
            {
                if(pageResources == null)
                {
                    pageResources = new PageResources();
                    PdfDictionary resources = pageN.getAsDict(PdfName.RESOURCES);
                    pageResources.setOriginalResources(resources, cstp.namePtr);
                }
                over = new StampContent(cstp, pageResources);
            }
            return over;
        }

        public void alterContents()
            throws IOException
        {
            if(over == null && under == null)
                return;
            PdfArray ar = null;
            PdfObject content = PdfReader.getPdfObject(pageN.get(PdfName.CONTENTS), pageN);
            if(content == null)
            {
                ar = new PdfArray();
                pageN.put(PdfName.CONTENTS, ar);
            } else
            if(content.isArray())
                ar = (PdfArray)content;
            else
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
            if(under != null)
            {
                out.append(PdfContents.SAVESTATE);
                applyRotation(pageN, out);
                out.append(under.getInternalBuffer());
                out.append(PdfContents.RESTORESTATE);
            }
            if(over != null)
                out.append(PdfContents.SAVESTATE);
            PdfStream stream = new PdfStream(out.toByteArray());
            stream.flateCompress(cstp.getCompressionLevel());
            PdfIndirectReference ref1 = cstp.addToBody(stream).getIndirectReference();
            ar.addFirst(ref1);
            out.reset();
            if(over != null)
            {
                out.append(' ');
                out.append(PdfContents.RESTORESTATE);
                out.append(PdfContents.SAVESTATE);
                applyRotation(pageN, out);
                out.append(over.getInternalBuffer());
                out.append(PdfContents.RESTORESTATE);
                stream = new PdfStream(out.toByteArray());
                stream.flateCompress(cstp.getCompressionLevel());
                ar.add(cstp.addToBody(stream).getIndirectReference());
            }
            pageN.put(PdfName.RESOURCES, pageResources.getResources());
        }

        void applyRotation(PdfDictionary pageN, ByteBuffer out)
        {
            if(!cstp.rotateContents)
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

        private void addDocumentField(PdfIndirectReference ref)
        {
            if(cstp.fieldArray == null)
                cstp.fieldArray = new PdfArray();
            cstp.fieldArray.add(ref);
        }

        private void expandFields(PdfFormField field, ArrayList allAnnots)
        {
            allAnnots.add(field);
            ArrayList kids = field.getKids();
            if(kids != null)
            {
                PdfFormField f;
                for(Iterator i$ = kids.iterator(); i$.hasNext(); expandFields(f, allAnnots))
                    f = (PdfFormField)i$.next();

            }
        }

        public void addAnnotation(PdfAnnotation annot)
        {
            ArrayList allAnnots;
            PdfFormField field;
            try
            {
                allAnnots = new ArrayList();
                if(!annot.isForm())
                    break MISSING_BLOCK_LABEL_61;
                field = (PdfFormField)annot;
                if(field.getParent() != null)
                    return;
            }
            catch(IOException e)
            {
                throw new ExceptionConverter(e);
            }
            expandFields(field, allAnnots);
            if(cstp.fieldTemplates == null)
                cstp.fieldTemplates = new HashSet();
            break MISSING_BLOCK_LABEL_67;
            allAnnots.add(annot);
            for(int k = 0; k < allAnnots.size(); k++)
            {
                annot = (PdfAnnotation)allAnnots.get(k);
                if(annot.isForm())
                {
                    if(!annot.isUsed())
                    {
                        HashSet templates = annot.getTemplates();
                        if(templates != null)
                            cstp.fieldTemplates.addAll(templates);
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
                    } else
                    {
                        annots = (PdfArray)pdfobj;
                    }
                    annots.add(annot.getIndirectReference());
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
                                annot.put(PdfName.RECT, new PdfRectangle(pageSize.getTop() - rect.bottom(), rect.left(), pageSize.getTop() - rect.top(), rect.right()));
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
                    cstp.addToBody(annot, annot.getIndirectReference());
                }
            }

            break MISSING_BLOCK_LABEL_555;
        }

        PdfDictionary pageN;
        StampContent under;
        StampContent over;
        PageResources pageResources;
        PdfReader reader;
        PdfCopy cstp;

        PageStamp(PdfReader reader, PdfDictionary pageN, PdfCopy cstp)
        {
            this.pageN = pageN;
            this.reader = reader;
            this.cstp = cstp;
        }
    }

    protected static class ImportedPage
    {

        public boolean equals(Object o)
        {
            if(!(o instanceof ImportedPage))
            {
                return false;
            } else
            {
                ImportedPage other = (ImportedPage)o;
                return pageNumber == other.pageNumber && reader.equals(other.reader);
            }
        }

        public String toString()
        {
            return Integer.toString(pageNumber);
        }

        int pageNumber;
        PdfReader reader;
        PdfArray mergedFields;

        ImportedPage(PdfReader reader, int pageNumber, boolean keepFields)
        {
            this.pageNumber = pageNumber;
            this.reader = reader;
            if(keepFields)
                mergedFields = new PdfArray();
        }
    }

    protected static class RefKey
    {

        public int hashCode()
        {
            return (gen << 16) + num;
        }

        public boolean equals(Object o)
        {
            if(!(o instanceof RefKey))
            {
                return false;
            } else
            {
                RefKey other = (RefKey)o;
                return gen == other.gen && num == other.num;
            }
        }

        public String toString()
        {
            return (new StringBuilder()).append(Integer.toString(num)).append(' ').append(gen).toString();
        }

        int num;
        int gen;

        RefKey(int num, int gen)
        {
            this.num = num;
            this.gen = gen;
        }

        RefKey(PdfIndirectReference ref)
        {
            num = ref.getNumber();
            gen = ref.getGeneration();
        }

        RefKey(PRIndirectReference ref)
        {
            num = ref.getNumber();
            gen = ref.getGeneration();
        }
    }

    static class IndirectReferences
    {

        void setCopied()
        {
            hasCopied = true;
        }

        void setNotCopied()
        {
            hasCopied = false;
        }

        boolean getCopied()
        {
            return hasCopied;
        }

        PdfIndirectReference getRef()
        {
            return theRef;
        }

        public String toString()
        {
            String ext = "";
            if(hasCopied)
                ext = (new StringBuilder()).append(ext).append(" Copied").toString();
            return (new StringBuilder()).append(getRef()).append(ext).toString();
        }

        PdfIndirectReference theRef;
        boolean hasCopied;

        IndirectReferences(PdfIndirectReference ref)
        {
            theRef = ref;
            hasCopied = false;
        }
    }


    protected Counter getCounter()
    {
        return COUNTER;
    }

    public PdfCopy(Document document, OutputStream os)
        throws DocumentException
    {
        super(new PdfDocument(), os);
        rotateContents = true;
        structTreeController = null;
        currentStructArrayNumber = 0;
        updateRootKids = false;
        mergeFields = false;
        mergeFieldsInternalCall = false;
        document.addDocListener(pdf);
        pdf.addWriter(this);
        indirectMap = new HashMap();
        parentObjects = new HashMap();
        disableIndirects = new HashSet();
        indirectObjects = new HashMap();
        savedObjects = new ArrayList();
        importedPages = new ArrayList();
    }

    public void setPageEvent(PdfPageEvent event)
    {
        throw new UnsupportedOperationException();
    }

    public boolean isRotateContents()
    {
        return rotateContents;
    }

    public void setRotateContents(boolean rotateContents)
    {
        this.rotateContents = rotateContents;
    }

    public void setMergeFields()
    {
        mergeFields = true;
        resources = new PdfDictionary();
        fields = new ArrayList();
        calculationOrder = new ArrayList();
        fieldTree = new HashMap();
        unmergedMap = new HashMap();
        unmergedSet = new HashSet();
        mergedMap = new HashMap();
        mergedSet = new HashSet();
    }

    public PdfImportedPage getImportedPage(PdfReader reader, int pageNumber)
    {
        if(mergeFields && !mergeFieldsInternalCall)
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("1.method.cannot.be.used.in.mergeFields.mode.please.use.addDocument", new Object[] {
                "getImportedPage"
            }));
        if(mergeFields)
        {
            ImportedPage newPage = new ImportedPage(reader, pageNumber, mergeFields);
            importedPages.add(newPage);
        }
        if(structTreeController != null)
            structTreeController.reader = null;
        disableIndirects.clear();
        parentObjects.clear();
        return getImportedPageImpl(reader, pageNumber);
    }

    public PdfImportedPage getImportedPage(PdfReader reader, int pageNumber, boolean keepTaggedPdfStructure)
        throws BadPdfFormatException
    {
        if(mergeFields && !mergeFieldsInternalCall)
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("1.method.cannot.be.used.in.mergeFields.mode.please.use.addDocument", new Object[] {
                "getImportedPage"
            }));
        updateRootKids = false;
        ImportedPage newPage;
        if(!keepTaggedPdfStructure)
        {
            if(mergeFields)
            {
                newPage = new ImportedPage(reader, pageNumber, mergeFields);
                importedPages.add(newPage);
            }
            return getImportedPageImpl(reader, pageNumber);
        }
        if(structTreeController != null)
        {
            if(reader != structTreeController.reader)
                structTreeController.setReader(reader);
        } else
        {
            structTreeController = new PdfStructTreeController(reader, this);
        }
        newPage = new ImportedPage(reader, pageNumber, mergeFields);
        switch(checkStructureTreeRootKids(newPage))
        {
        case -1: 
            clearIndirects(reader);
            updateRootKids = true;
            break;

        case 0: // '\0'
            updateRootKids = false;
            break;

        case 1: // '\001'
            updateRootKids = true;
            break;
        }
        importedPages.add(newPage);
        disableIndirects.clear();
        parentObjects.clear();
        return getImportedPageImpl(reader, pageNumber);
    }

    private void clearIndirects(PdfReader reader)
    {
        HashMap currIndirects = (HashMap)indirectMap.get(reader);
        ArrayList forDelete = new ArrayList();
        Iterator i$ = currIndirects.entrySet().iterator();
        do
        {
            if(!i$.hasNext())
                break;
            java.util.Map.Entry entry = (java.util.Map.Entry)i$.next();
            PdfIndirectReference iRef = ((IndirectReferences)entry.getValue()).theRef;
            RefKey key = new RefKey(iRef);
            PdfIndirectObject iobj = (PdfIndirectObject)indirectObjects.get(key);
            if(iobj == null)
                forDelete.add(entry.getKey());
            else
            if(iobj.object.isArray() || iobj.object.isDictionary() || iobj.object.isStream())
                forDelete.add(entry.getKey());
        } while(true);
        RefKey key;
        for(i$ = forDelete.iterator(); i$.hasNext(); currIndirects.remove(key))
            key = (RefKey)i$.next();

    }

    private int checkStructureTreeRootKids(ImportedPage newPage)
    {
        if(importedPages.size() == 0)
            return 1;
        boolean readerExist = false;
        Iterator i$ = importedPages.iterator();
        do
        {
            if(!i$.hasNext())
                break;
            ImportedPage page = (ImportedPage)i$.next();
            if(!page.reader.equals(newPage.reader))
                continue;
            readerExist = true;
            break;
        } while(true);
        if(!readerExist)
            return 1;
        ImportedPage lastPage = (ImportedPage)importedPages.get(importedPages.size() - 1);
        boolean equalReader = lastPage.reader.equals(newPage.reader);
        return !equalReader || newPage.pageNumber <= lastPage.pageNumber ? -1 : 0;
    }

    protected void fixStructureTreeRoot(HashSet activeKeys, HashSet activeClassMaps)
    {
        HashMap newClassMap = new HashMap(activeClassMaps.size());
        Iterator i$ = activeClassMaps.iterator();
        do
        {
            if(!i$.hasNext())
                break;
            PdfName key = (PdfName)i$.next();
            PdfObject cm = (PdfObject)structureTreeRoot.classes.get(key);
            if(cm != null)
                newClassMap.put(key, cm);
        } while(true);
        structureTreeRoot.classes = newClassMap;
        PdfArray kids = structureTreeRoot.getAsArray(PdfName.K);
        if(kids != null)
        {
            for(int i = 0; i < kids.size(); i++)
            {
                PdfIndirectReference iref = (PdfIndirectReference)kids.getPdfObject(i);
                RefKey key = new RefKey(iref);
                if(!activeKeys.contains(key))
                    kids.remove(i--);
            }

        }
    }

    protected PdfImportedPage getImportedPageImpl(PdfReader reader, int pageNumber)
    {
        if(currentPdfReaderInstance != null)
        {
            if(currentPdfReaderInstance.getReader() != reader)
                currentPdfReaderInstance = super.getPdfReaderInstance(reader);
        } else
        {
            currentPdfReaderInstance = super.getPdfReaderInstance(reader);
        }
        return currentPdfReaderInstance.getImportedPage(pageNumber);
    }

    protected PdfIndirectReference copyIndirect(PRIndirectReference in, boolean keepStructure, boolean directRootKids)
        throws IOException, BadPdfFormatException
    {
        RefKey key = new RefKey(in);
        IndirectReferences iRef = (IndirectReferences)indirects.get(key);
        PdfObject obj = PdfReader.getPdfObjectRelease(in);
        if(keepStructure && directRootKids && (obj instanceof PdfDictionary))
        {
            PdfDictionary dict = (PdfDictionary)obj;
            if(dict.contains(PdfName.PG))
                return null;
        }
        PdfIndirectReference theRef;
        if(iRef != null)
        {
            theRef = iRef.getRef();
            if(iRef.getCopied())
                return theRef;
        } else
        {
            theRef = body.getPdfIndirectReference();
            iRef = new IndirectReferences(theRef);
            indirects.put(key, iRef);
        }
        if(obj != null && obj.isDictionary())
        {
            PdfObject type = PdfReader.getPdfObjectRelease(((PdfDictionary)obj).get(PdfName.TYPE));
            if(type != null && PdfName.PAGE.equals(type))
                return theRef;
        }
        iRef.setCopied();
        parentObjects.put(obj, in);
        PdfObject res = copyObject(obj, keepStructure, directRootKids);
        if(disableIndirects.contains(obj))
            iRef.setNotCopied();
        if(res != null && !(res instanceof PdfNull))
        {
            addToBody(res, theRef);
            return theRef;
        } else
        {
            indirects.remove(key);
            return null;
        }
    }

    protected PdfIndirectReference copyIndirect(PRIndirectReference in)
        throws IOException, BadPdfFormatException
    {
        return copyIndirect(in, false, false);
    }

    protected PdfDictionary copyDictionary(PdfDictionary in, boolean keepStruct, boolean directRootKids)
        throws IOException, BadPdfFormatException
    {
        PdfDictionary out = new PdfDictionary();
        PdfObject type = PdfReader.getPdfObjectRelease(in.get(PdfName.TYPE));
        if(keepStruct)
        {
            if(directRootKids && in.contains(PdfName.PG))
            {
                PdfObject curr = in;
                disableIndirects.add(curr);
                for(; parentObjects.containsKey(curr) && !disableIndirects.contains(curr); disableIndirects.add(curr))
                    curr = (PdfObject)parentObjects.get(curr);

                return null;
            }
            PdfName structType = in.getAsName(PdfName.S);
            structTreeController.addRole(structType);
            structTreeController.addClass(in);
        }
        if(structTreeController != null && structTreeController.reader != null && (in.contains(PdfName.STRUCTPARENTS) || in.contains(PdfName.STRUCTPARENT)))
        {
            PdfName key = PdfName.STRUCTPARENT;
            if(in.contains(PdfName.STRUCTPARENTS))
                key = PdfName.STRUCTPARENTS;
            PdfObject value = in.get(key);
            out.put(key, new PdfNumber(currentStructArrayNumber));
            structTreeController.copyStructTreeForPage((PdfNumber)value, currentStructArrayNumber++);
        }
        Iterator i$ = in.getKeys().iterator();
        do
        {
            if(!i$.hasNext())
                break;
            Object element = i$.next();
            PdfName key = (PdfName)element;
            PdfObject value = in.get(key);
            if(structTreeController == null || structTreeController.reader == null || !key.equals(PdfName.STRUCTPARENTS) && !key.equals(PdfName.STRUCTPARENT))
                if(PdfName.PAGE.equals(type))
                {
                    if(!key.equals(PdfName.B) && !key.equals(PdfName.PARENT))
                    {
                        parentObjects.put(value, in);
                        PdfObject res = copyObject(value, keepStruct, directRootKids);
                        if(res != null && !(res instanceof PdfNull))
                            out.put(key, res);
                    }
                } else
                {
                    PdfObject res;
                    if(tagged && value.isIndirect() && isStructTreeRootReference((PRIndirectReference)value))
                        res = structureTreeRoot.getReference();
                    else
                        res = copyObject(value, keepStruct, directRootKids);
                    if(res != null && !(res instanceof PdfNull))
                        out.put(key, res);
                }
        } while(true);
        return out;
    }

    protected PdfDictionary copyDictionary(PdfDictionary in)
        throws IOException, BadPdfFormatException
    {
        return copyDictionary(in, false, false);
    }

    protected PdfStream copyStream(PRStream in)
        throws IOException, BadPdfFormatException
    {
        PRStream out = new PRStream(in, null);
        Iterator i$ = in.getKeys().iterator();
        do
        {
            if(!i$.hasNext())
                break;
            Object element = i$.next();
            PdfName key = (PdfName)element;
            PdfObject value = in.get(key);
            parentObjects.put(value, in);
            PdfObject res = copyObject(value);
            if(res != null && !(res instanceof PdfNull))
                out.put(key, res);
        } while(true);
        return out;
    }

    protected PdfArray copyArray(PdfArray in, boolean keepStruct, boolean directRootKids)
        throws IOException, BadPdfFormatException
    {
        PdfArray out = new PdfArray();
        Iterator i = in.listIterator();
        do
        {
            if(!i.hasNext())
                break;
            PdfObject value = (PdfObject)i.next();
            parentObjects.put(value, in);
            PdfObject res = copyObject(value, keepStruct, directRootKids);
            if(res != null && !(res instanceof PdfNull))
                out.add(res);
        } while(true);
        return out;
    }

    protected PdfArray copyArray(PdfArray in)
        throws IOException, BadPdfFormatException
    {
        return copyArray(in, false, false);
    }

    protected PdfObject copyObject(PdfObject in, boolean keepStruct, boolean directRootKids)
        throws IOException, BadPdfFormatException
    {
        if(in == null)
            return PdfNull.PDFNULL;
        switch(in.type)
        {
        case 6: // '\006'
            return copyDictionary((PdfDictionary)in, keepStruct, directRootKids);

        case 10: // '\n'
            if(!keepStruct && !directRootKids)
                return copyIndirect((PRIndirectReference)in);
            else
                return copyIndirect((PRIndirectReference)in, keepStruct, directRootKids);

        case 5: // '\005'
            return copyArray((PdfArray)in, keepStruct, directRootKids);

        case 0: // '\0'
        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
        case 8: // '\b'
            return in;

        case 7: // '\007'
            return copyStream((PRStream)in);
        }
        if(in.type < 0)
        {
            String lit = ((PdfLiteral)in).toString();
            if(lit.equals("true") || lit.equals("false"))
                return new PdfBoolean(lit);
            else
                return new PdfLiteral(lit);
        } else
        {
            System.out.println((new StringBuilder()).append("CANNOT COPY type ").append(in.type).toString());
            return null;
        }
    }

    protected PdfObject copyObject(PdfObject in)
        throws IOException, BadPdfFormatException
    {
        return copyObject(in, false, false);
    }

    protected int setFromIPage(PdfImportedPage iPage)
    {
        int pageNum = iPage.getPageNumber();
        PdfReaderInstance inst = currentPdfReaderInstance = iPage.getPdfReaderInstance();
        reader = inst.getReader();
        setFromReader(reader);
        return pageNum;
    }

    protected void setFromReader(PdfReader reader)
    {
        this.reader = reader;
        indirects = (HashMap)indirectMap.get(reader);
        if(indirects == null)
        {
            indirects = new HashMap();
            indirectMap.put(reader, indirects);
        }
    }

    public void addPage(PdfImportedPage iPage)
        throws IOException, BadPdfFormatException
    {
        if(mergeFields && !mergeFieldsInternalCall)
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("1.method.cannot.be.used.in.mergeFields.mode.please.use.addDocument", new Object[] {
                "addPage"
            }));
        int pageNum = setFromIPage(iPage);
        PdfDictionary thePage = reader.getPageN(pageNum);
        PRIndirectReference origRef = reader.getPageOrigRef(pageNum);
        reader.releasePage(pageNum);
        RefKey key = new RefKey(origRef);
        IndirectReferences iRef = (IndirectReferences)indirects.get(key);
        if(iRef != null && !iRef.getCopied())
        {
            pageReferences.add(iRef.getRef());
            iRef.setCopied();
        }
        PdfIndirectReference pageRef = getCurrentPage();
        if(iRef == null)
        {
            iRef = new IndirectReferences(pageRef);
            indirects.put(key, iRef);
        }
        iRef.setCopied();
        if(tagged)
            structTreeRootReference = (PRIndirectReference)reader.getCatalog().get(PdfName.STRUCTTREEROOT);
        PdfDictionary newPage = copyDictionary(thePage);
        root.addPage(newPage);
        iPage.setCopied();
        currentPageNumber++;
        structTreeRootReference = null;
    }

    public void addPage(Rectangle rect, int rotation)
        throws DocumentException
    {
        if(mergeFields && !mergeFieldsInternalCall)
        {
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("1.method.cannot.be.used.in.mergeFields.mode.please.use.addDocument", new Object[] {
                "addPage"
            }));
        } else
        {
            PdfRectangle mediabox = new PdfRectangle(rect, rotation);
            PageResources resources = new PageResources();
            PdfPage page = new PdfPage(mediabox, new HashMap(), resources.getResources(), 0);
            page.put(PdfName.TABS, getTabs());
            root.addPage(page);
            currentPageNumber++;
            return;
        }
    }

    public void addDocument(PdfReader reader, List pagesToKeep)
        throws DocumentException, IOException
    {
        if(indirectMap.containsKey(reader))
        {
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("document.1.has.already.been.added", new Object[] {
                reader.toString()
            }));
        } else
        {
            reader.selectPages(pagesToKeep);
            addDocument(reader);
            return;
        }
    }

    public void addDocument(PdfReader reader)
        throws DocumentException, IOException
    {
        if(indirectMap.containsKey(reader))
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("document.1.has.already.been.added", new Object[] {
                reader.toString()
            }));
        if(!reader.isOpenedWithFullPermissions())
            throw new BadPasswordException(MessageLocalization.getComposedMessage("pdfreader.not.opened.with.owner.password", new Object[0]));
        if(mergeFields)
        {
            reader.consolidateNamedDestinations();
            reader.shuffleSubsetNames();
            for(int i = 1; i <= reader.getNumberOfPages(); i++)
            {
                PdfDictionary page = reader.getPageNRelease(i);
                if(page == null || !page.contains(PdfName.ANNOTS))
                    continue;
                PdfArray annots = page.getAsArray(PdfName.ANNOTS);
                if(annots == null)
                    continue;
                for(int j = 0; j < annots.size(); j++)
                {
                    PdfDictionary annot = annots.getAsDict(j);
                    if(annot != null)
                        annot.put(annotId, new PdfNumber(++annotIdCnt));
                }

            }

            fields.add(reader.getAcroFields());
            updateCalculationOrder(reader);
        }
        boolean tagged = PdfStructTreeController.checkTagged(reader);
        mergeFieldsInternalCall = true;
        for(int i = 1; i <= reader.getNumberOfPages(); i++)
            addPage(getImportedPage(reader, i, tagged && this.tagged));

        mergeFieldsInternalCall = false;
    }

    public PdfIndirectObject addToBody(PdfObject object, PdfIndirectReference ref)
        throws IOException
    {
        return addToBody(object, ref, false);
    }

    public PdfIndirectObject addToBody(PdfObject object, PdfIndirectReference ref, boolean formBranching)
        throws IOException
    {
        if(formBranching)
            updateReferences(object);
        PdfIndirectObject indObj;
        if((tagged || mergeFields) && indirectObjects != null && (object.isArray() || object.isDictionary() || object.isStream()))
        {
            RefKey key = new RefKey(ref);
            PdfIndirectObject obj = (PdfIndirectObject)indirectObjects.get(key);
            if(obj == null)
            {
                obj = new PdfIndirectObject(ref, object, this);
                indirectObjects.put(key, obj);
            }
            indObj = obj;
        } else
        {
            indObj = super.addToBody(object, ref);
        }
        if(mergeFields && object.isDictionary())
        {
            PdfNumber annotId = ((PdfDictionary)object).getAsNumber(annotId);
            if(annotId != null)
                if(formBranching)
                {
                    mergedMap.put(Integer.valueOf(annotId.intValue()), indObj);
                    mergedSet.add(indObj);
                } else
                {
                    unmergedMap.put(Integer.valueOf(annotId.intValue()), indObj);
                    unmergedSet.add(indObj);
                }
        }
        return indObj;
    }

    public PdfIndirectObject addToBody(PdfObject object)
        throws IOException
    {
        PdfIndirectObject iobj = super.addToBody(object);
        if((tagged || mergeFields) && indirectObjects != null)
        {
            savedObjects.add(iobj);
            RefKey key = new RefKey(iobj.number, iobj.generation);
            if(!indirectObjects.containsKey(key))
                indirectObjects.put(key, iobj);
        }
        return iobj;
    }

    protected void flushTaggedObjects()
        throws IOException
    {
        try
        {
            fixTaggedStructure();
        }
        catch(ClassCastException ex)
        {
            flushIndirectObjects();
            break MISSING_BLOCK_LABEL_26;
        }
        flushIndirectObjects();
        break MISSING_BLOCK_LABEL_26;
        Exception exception;
        exception;
        flushIndirectObjects();
        throw exception;
    }

    protected void flushAcroFields()
        throws IOException, BadPdfFormatException
    {
        if(!mergeFields)
            break MISSING_BLOCK_LABEL_97;
        try
        {
            PdfReader reader;
            for(Iterator i$ = indirectMap.keySet().iterator(); i$.hasNext(); reader.removeFields())
                reader = (PdfReader)i$.next();

            mergeFields();
            createAcroForms();
        }
        catch(ClassCastException ex)
        {
            if(!tagged)
                flushIndirectObjects();
            break MISSING_BLOCK_LABEL_97;
        }
        if(!tagged)
            flushIndirectObjects();
        break MISSING_BLOCK_LABEL_97;
        Exception exception;
        exception;
        if(!tagged)
            flushIndirectObjects();
        throw exception;
    }

    protected void fixTaggedStructure()
        throws IOException
    {
        HashMap numTree = structureTreeRoot.getNumTree();
        HashSet activeKeys = new HashSet();
        ArrayList actives = new ArrayList();
        int pageRefIndex = 0;
        if(mergeFields && acroForm != null)
        {
            actives.add(acroForm);
            activeKeys.add(new RefKey(acroForm));
        }
        PdfIndirectReference page;
        for(Iterator i$ = pageReferences.iterator(); i$.hasNext(); activeKeys.add(new RefKey(page)))
        {
            page = (PdfIndirectReference)i$.next();
            actives.add(page);
        }

        for(int i = numTree.size() - 1; i >= 0; i--)
        {
            PdfIndirectReference currNum = (PdfIndirectReference)numTree.get(Integer.valueOf(i));
            RefKey numKey = new RefKey(currNum);
            PdfObject obj = ((PdfIndirectObject)indirectObjects.get(numKey)).object;
            if(obj.isDictionary())
            {
                boolean addActiveKeys = false;
                if(pageReferences.contains(((PdfDictionary)obj).get(PdfName.PG)))
                {
                    addActiveKeys = true;
                } else
                {
                    PdfDictionary k = PdfStructTreeController.getKDict((PdfDictionary)obj);
                    if(k != null && pageReferences.contains(k.get(PdfName.PG)))
                        addActiveKeys = true;
                }
                if(addActiveKeys)
                {
                    activeKeys.add(numKey);
                    actives.add(currNum);
                } else
                {
                    numTree.remove(Integer.valueOf(i));
                }
                continue;
            }
            if(!obj.isArray())
                continue;
            activeKeys.add(numKey);
            actives.add(currNum);
            PdfArray currNums = (PdfArray)obj;
            PdfIndirectReference currPage = (PdfIndirectReference)pageReferences.get(pageRefIndex++);
            actives.add(currPage);
            activeKeys.add(new RefKey(currPage));
            PdfIndirectReference prevKid = null;
            for(int j = 0; j < currNums.size(); j++)
            {
                PdfIndirectReference currKid = (PdfIndirectReference)currNums.getDirectObject(j);
                if(currKid.equals(prevKid))
                    continue;
                RefKey kidKey = new RefKey(currKid);
                activeKeys.add(kidKey);
                actives.add(currKid);
                PdfIndirectObject iobj = (PdfIndirectObject)indirectObjects.get(kidKey);
                if(iobj.object.isDictionary())
                {
                    PdfDictionary dict = (PdfDictionary)iobj.object;
                    PdfIndirectReference pg = (PdfIndirectReference)dict.get(PdfName.PG);
                    if(!pageReferences.contains(pg) && !pg.equals(currPage))
                    {
                        dict.put(PdfName.PG, currPage);
                        PdfArray kids = dict.getAsArray(PdfName.K);
                        if(kids != null)
                        {
                            PdfObject firstKid = kids.getDirectObject(0);
                            if(firstKid.isNumber())
                                kids.remove(0);
                        }
                    }
                }
                prevKid = currKid;
            }

        }

        HashSet activeClassMaps = new HashSet();
        findActives(actives, activeKeys, activeClassMaps);
        ArrayList newRefs = findActiveParents(activeKeys);
        fixPgKey(newRefs, activeKeys);
        fixStructureTreeRoot(activeKeys, activeClassMaps);
        Iterator i$ = indirectObjects.entrySet().iterator();
        do
        {
            if(!i$.hasNext())
                break;
            java.util.Map.Entry entry = (java.util.Map.Entry)i$.next();
            if(!activeKeys.contains(entry.getKey()))
                entry.setValue(null);
            else
            if(((PdfIndirectObject)entry.getValue()).object.isArray())
                removeInactiveReferences((PdfArray)((PdfIndirectObject)entry.getValue()).object, activeKeys);
            else
            if(((PdfIndirectObject)entry.getValue()).object.isDictionary())
            {
                PdfObject kids = ((PdfDictionary)((PdfIndirectObject)entry.getValue()).object).get(PdfName.K);
                if(kids != null && kids.isArray())
                    removeInactiveReferences((PdfArray)kids, activeKeys);
            }
        } while(true);
    }

    private void removeInactiveReferences(PdfArray array, HashSet activeKeys)
    {
        for(int i = 0; i < array.size(); i++)
        {
            PdfObject obj = array.getPdfObject(i);
            if(obj.type() == 0 && !activeKeys.contains(new RefKey((PdfIndirectReference)obj)) || obj.isDictionary() && containsInactivePg((PdfDictionary)obj, activeKeys))
                array.remove(i--);
        }

    }

    private boolean containsInactivePg(PdfDictionary dict, HashSet activeKeys)
    {
        PdfObject pg = dict.get(PdfName.PG);
        return pg != null && !activeKeys.contains(new RefKey((PdfIndirectReference)pg));
    }

    private ArrayList findActiveParents(HashSet activeKeys)
    {
        ArrayList newRefs = new ArrayList();
        ArrayList tmpActiveKeys = new ArrayList(activeKeys);
        for(int i = 0; i < tmpActiveKeys.size(); i++)
        {
            PdfIndirectObject iobj = (PdfIndirectObject)indirectObjects.get(tmpActiveKeys.get(i));
            if(iobj == null || !iobj.object.isDictionary())
                continue;
            PdfObject parent = ((PdfDictionary)iobj.object).get(PdfName.P);
            if(parent == null || parent.type() != 0)
                continue;
            RefKey key = new RefKey((PdfIndirectReference)parent);
            if(!activeKeys.contains(key))
            {
                activeKeys.add(key);
                tmpActiveKeys.add(key);
                newRefs.add((PdfIndirectReference)parent);
            }
        }

        return newRefs;
    }

    private void fixPgKey(ArrayList newRefs, HashSet activeKeys)
    {
        Iterator i$ = newRefs.iterator();
label0:
        do
        {
            if(i$.hasNext())
            {
                PdfIndirectReference iref = (PdfIndirectReference)i$.next();
                PdfIndirectObject iobj = (PdfIndirectObject)indirectObjects.get(new RefKey(iref));
                if(iobj == null || !iobj.object.isDictionary())
                    continue;
                PdfDictionary dict = (PdfDictionary)iobj.object;
                PdfObject pg = dict.get(PdfName.PG);
                if(pg == null || activeKeys.contains(new RefKey((PdfIndirectReference)pg)))
                    continue;
                PdfArray kids = dict.getAsArray(PdfName.K);
                if(kids == null)
                    continue;
                int i = 0;
                do
                {
                    if(i >= kids.size())
                        continue label0;
                    PdfObject obj = kids.getPdfObject(i);
                    if(obj.type() != 0)
                    {
                        kids.remove(i--);
                    } else
                    {
                        PdfIndirectObject kid = (PdfIndirectObject)indirectObjects.get(new RefKey((PdfIndirectReference)obj));
                        if(kid != null && kid.object.isDictionary())
                        {
                            PdfObject kidPg = ((PdfDictionary)kid.object).get(PdfName.PG);
                            if(kidPg != null && activeKeys.contains(new RefKey((PdfIndirectReference)kidPg)))
                            {
                                dict.put(PdfName.PG, kidPg);
                                continue label0;
                            }
                        }
                    }
                    i++;
                } while(true);
            }
            return;
        } while(true);
    }

    private void findActives(ArrayList actives, HashSet activeKeys, HashSet activeClassMaps)
    {
        for(int i = 0; i < actives.size(); i++)
        {
            RefKey key = new RefKey((PdfIndirectReference)actives.get(i));
            PdfIndirectObject iobj = (PdfIndirectObject)indirectObjects.get(key);
            if(iobj != null && iobj.object != null)
                switch(iobj.object.type())
                {
                case 0: // '\0'
                    findActivesFromReference((PdfIndirectReference)iobj.object, actives, activeKeys);
                    break;

                case 5: // '\005'
                    findActivesFromArray((PdfArray)iobj.object, actives, activeKeys, activeClassMaps);
                    break;

                case 6: // '\006'
                case 7: // '\007'
                    findActivesFromDict((PdfDictionary)iobj.object, actives, activeKeys, activeClassMaps);
                    break;
                }
        }

    }

    private void findActivesFromReference(PdfIndirectReference iref, ArrayList actives, HashSet activeKeys)
    {
        RefKey key = new RefKey(iref);
        PdfIndirectObject iobj = (PdfIndirectObject)indirectObjects.get(key);
        if(iobj != null && iobj.object.isDictionary() && containsInactivePg((PdfDictionary)iobj.object, activeKeys))
            return;
        if(!activeKeys.contains(key))
        {
            activeKeys.add(key);
            actives.add(iref);
        }
    }

    private void findActivesFromArray(PdfArray array, ArrayList actives, HashSet activeKeys, HashSet activeClassMaps)
    {
        Iterator i$ = array.iterator();
        do
        {
            if(!i$.hasNext())
                break;
            PdfObject obj = (PdfObject)i$.next();
            switch(obj.type())
            {
            case 0: // '\0'
                findActivesFromReference((PdfIndirectReference)obj, actives, activeKeys);
                break;

            case 5: // '\005'
                findActivesFromArray((PdfArray)obj, actives, activeKeys, activeClassMaps);
                break;

            case 6: // '\006'
            case 7: // '\007'
                findActivesFromDict((PdfDictionary)obj, actives, activeKeys, activeClassMaps);
                break;
            }
        } while(true);
    }

    private void findActivesFromDict(PdfDictionary dict, ArrayList actives, HashSet activeKeys, HashSet activeClassMaps)
    {
        if(containsInactivePg(dict, activeKeys))
            return;
        Iterator i$ = dict.getKeys().iterator();
label0:
        do
        {
            if(!i$.hasNext())
                break;
            PdfName key = (PdfName)i$.next();
            PdfObject obj = dict.get(key);
            if(key.equals(PdfName.P))
                continue;
            if(key.equals(PdfName.C))
            {
                if(obj.isArray())
                {
                    Iterator i$ = ((PdfArray)obj).iterator();
                    do
                    {
                        PdfObject cm;
                        do
                        {
                            if(!i$.hasNext())
                                continue label0;
                            cm = (PdfObject)i$.next();
                        } while(!cm.isName());
                        activeClassMaps.add((PdfName)cm);
                    } while(true);
                }
                if(obj.isName())
                    activeClassMaps.add((PdfName)obj);
                continue;
            }
            switch(obj.type())
            {
            case 0: // '\0'
                findActivesFromReference((PdfIndirectReference)obj, actives, activeKeys);
                break;

            case 5: // '\005'
                findActivesFromArray((PdfArray)obj, actives, activeKeys, activeClassMaps);
                break;

            case 6: // '\006'
            case 7: // '\007'
                findActivesFromDict((PdfDictionary)obj, actives, activeKeys, activeClassMaps);
                break;
            }
        } while(true);
    }

    protected void flushIndirectObjects()
        throws IOException
    {
        PdfIndirectObject iobj;
        for(Iterator i$ = savedObjects.iterator(); i$.hasNext(); indirectObjects.remove(new RefKey(iobj.number, iobj.generation)))
            iobj = (PdfIndirectObject)i$.next();

        HashSet inactives = new HashSet();
        for(Iterator i$ = indirectObjects.entrySet().iterator(); i$.hasNext();)
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)i$.next();
            if(entry.getValue() != null)
                writeObjectToBody((PdfIndirectObject)entry.getValue());
            else
                inactives.add(entry.getKey());
        }

        ArrayList pdfCrossReferences = new ArrayList(body.xrefs);
        Iterator i$ = pdfCrossReferences.iterator();
        do
        {
            if(!i$.hasNext())
                break;
            PdfWriter.PdfBody.PdfCrossReference cr = (PdfWriter.PdfBody.PdfCrossReference)i$.next();
            RefKey key = new RefKey(cr.getRefnum(), 0);
            if(inactives.contains(key))
                body.xrefs.remove(cr);
        } while(true);
        indirectObjects = null;
    }

    private void writeObjectToBody(PdfIndirectObject object)
        throws IOException
    {
        boolean skipWriting = false;
        if(mergeFields)
        {
            updateAnnotationReferences(object.object);
            if(object.object.isDictionary() || object.object.isStream())
            {
                PdfDictionary dictionary = (PdfDictionary)object.object;
                if(unmergedSet.contains(object))
                {
                    PdfNumber annotId = dictionary.getAsNumber(annotId);
                    if(annotId != null && mergedMap.containsKey(Integer.valueOf(annotId.intValue())))
                        skipWriting = true;
                }
                if(mergedSet.contains(object))
                {
                    PdfNumber annotId = dictionary.getAsNumber(annotId);
                    if(annotId != null)
                    {
                        PdfIndirectObject unmerged = (PdfIndirectObject)unmergedMap.get(Integer.valueOf(annotId.intValue()));
                        if(unmerged != null && unmerged.object.isDictionary())
                        {
                            PdfNumber structParent = ((PdfDictionary)unmerged.object).getAsNumber(PdfName.STRUCTPARENT);
                            if(structParent != null)
                                dictionary.put(PdfName.STRUCTPARENT, structParent);
                        }
                    }
                }
            }
        }
        if(!skipWriting)
        {
            PdfDictionary dictionary = null;
            PdfNumber annotId = null;
            if(mergeFields && object.object.isDictionary())
            {
                dictionary = (PdfDictionary)object.object;
                annotId = dictionary.getAsNumber(annotId);
                if(annotId != null)
                    dictionary.remove(annotId);
            }
            body.write(object, object.number, object.generation);
            if(annotId != null)
                dictionary.put(annotId, annotId);
        }
    }

    private void updateAnnotationReferences(PdfObject obj)
    {
        if(obj.isArray())
        {
            PdfArray array = (PdfArray)obj;
            for(int i = 0; i < array.size(); i++)
            {
                PdfObject o = array.getPdfObject(i);
                if(o instanceof PdfIndirectReference)
                {
                    Iterator i$ = unmergedSet.iterator();
                    do
                    {
                        if(!i$.hasNext())
                            break;
                        PdfIndirectObject entry = (PdfIndirectObject)i$.next();
                        if(entry.getIndirectReference().toString().equals(o.toString()) && entry.object.isDictionary())
                        {
                            PdfNumber annotId = ((PdfDictionary)entry.object).getAsNumber(annotId);
                            if(annotId != null)
                            {
                                PdfIndirectObject merged = (PdfIndirectObject)mergedMap.get(Integer.valueOf(annotId.intValue()));
                                if(merged != null)
                                    array.set(i, merged.getIndirectReference());
                            }
                        }
                    } while(true);
                } else
                {
                    updateAnnotationReferences(o);
                }
            }

        } else
        if(obj.isDictionary() || obj.isStream())
        {
            PdfDictionary dictionary = (PdfDictionary)obj;
            Iterator i$ = dictionary.getKeys().iterator();
label0:
            do
            {
                if(!i$.hasNext())
                    break;
                PdfName key = (PdfName)i$.next();
                PdfObject o = dictionary.get(key);
                if(o instanceof PdfIndirectReference)
                {
                    Iterator i$ = unmergedSet.iterator();
                    do
                    {
                        PdfIndirectObject merged;
                        do
                        {
                            PdfNumber annotId;
                            do
                            {
                                PdfIndirectObject entry;
                                do
                                {
                                    if(!i$.hasNext())
                                        continue label0;
                                    entry = (PdfIndirectObject)i$.next();
                                } while(!entry.getIndirectReference().toString().equals(o.toString()) || !entry.object.isDictionary());
                                annotId = ((PdfDictionary)entry.object).getAsNumber(annotId);
                            } while(annotId == null);
                            merged = (PdfIndirectObject)mergedMap.get(Integer.valueOf(annotId.intValue()));
                        } while(merged == null);
                        dictionary.put(key, merged.getIndirectReference());
                    } while(true);
                }
                updateAnnotationReferences(o);
            } while(true);
        }
    }

    private void updateCalculationOrder(PdfReader reader)
    {
        PdfDictionary catalog = reader.getCatalog();
        PdfDictionary acro = catalog.getAsDict(PdfName.ACROFORM);
        if(acro == null)
            return;
        PdfArray co = acro.getAsArray(PdfName.CO);
        if(co == null || co.size() == 0)
            return;
        AcroFields af = reader.getAcroFields();
        for(int k = 0; k < co.size(); k++)
        {
            PdfObject obj = co.getPdfObject(k);
            if(obj == null || !obj.isIndirect())
                continue;
            String name = getCOName(reader, (PRIndirectReference)obj);
            if(af.getFieldItem(name) == null)
                continue;
            name = (new StringBuilder()).append(".").append(name).toString();
            if(!calculationOrder.contains(name))
                calculationOrder.add(name);
        }

    }

    private static String getCOName(PdfReader reader, PRIndirectReference ref)
    {
        String name = "";
        PdfDictionary dic;
        for(; ref != null; ref = (PRIndirectReference)dic.get(PdfName.PARENT))
        {
            PdfObject obj = PdfReader.getPdfObject(ref);
            if(obj == null || obj.type() != 6)
                break;
            dic = (PdfDictionary)obj;
            PdfString t = dic.getAsString(PdfName.T);
            if(t != null)
                name = (new StringBuilder()).append(t.toUnicodeString()).append(".").append(name).toString();
        }

        if(name.endsWith("."))
            name = name.substring(0, name.length() - 2);
        return name;
    }

    private void mergeFields()
    {
        int pageOffset = 0;
        for(int k = 0; k < fields.size(); k++)
        {
            AcroFields af = (AcroFields)fields.get(k);
            Map fd = af.getFields();
            addPageOffsetToField(fd, pageOffset);
            mergeWithMaster(fd);
            pageOffset += af.reader.getNumberOfPages();
        }

    }

    private void addPageOffsetToField(Map fd, int pageOffset)
    {
        if(pageOffset == 0)
            return;
        for(Iterator i$ = fd.values().iterator(); i$.hasNext();)
        {
            AcroFields.Item item = (AcroFields.Item)i$.next();
            int k = 0;
            while(k < item.size()) 
            {
                int p = item.getPage(k).intValue();
                item.forcePage(k, p + pageOffset);
                k++;
            }
        }

    }

    private void mergeWithMaster(Map fd)
    {
        java.util.Map.Entry entry;
        String name;
        for(Iterator i$ = fd.entrySet().iterator(); i$.hasNext(); mergeField(name, (AcroFields.Item)entry.getValue()))
        {
            entry = (java.util.Map.Entry)i$.next();
            name = (String)entry.getKey();
        }

    }

    private void mergeField(String name, AcroFields.Item item)
    {
        HashMap map = fieldTree;
        StringTokenizer tk = new StringTokenizer(name, ".");
        if(!tk.hasMoreTokens())
            return;
        String s;
        Object obj;
        do
        {
            s = tk.nextToken();
            obj = map.get(s);
            if(!tk.hasMoreTokens())
                break;
            if(obj == null)
            {
                obj = new HashMap();
                map.put(s, obj);
                map = (HashMap)obj;
            } else
            if(obj instanceof HashMap)
                map = (HashMap)obj;
            else
                return;
        } while(true);
        if(obj instanceof HashMap)
            return;
        PdfDictionary merged = item.getMerged(0);
        if(obj == null)
        {
            PdfDictionary field = new PdfDictionary();
            if(PdfName.SIG.equals(merged.get(PdfName.FT)))
                hasSignature = true;
            Iterator i$ = merged.getKeys().iterator();
            do
            {
                if(!i$.hasNext())
                    break;
                Object element = i$.next();
                PdfName key = (PdfName)element;
                if(fieldKeys.contains(key))
                    field.put(key, merged.get(key));
            } while(true);
            ArrayList list = new ArrayList();
            list.add(field);
            createWidgets(list, item);
            map.put(s, list);
        } else
        {
            ArrayList list = (ArrayList)obj;
            PdfDictionary field = (PdfDictionary)list.get(0);
            PdfName type1 = (PdfName)field.get(PdfName.FT);
            PdfName type2 = (PdfName)merged.get(PdfName.FT);
            if(type1 == null || !type1.equals(type2))
                return;
            int flag1 = 0;
            PdfObject f1 = field.get(PdfName.FF);
            if(f1 != null && f1.isNumber())
                flag1 = ((PdfNumber)f1).intValue();
            int flag2 = 0;
            PdfObject f2 = merged.get(PdfName.FF);
            if(f2 != null && f2.isNumber())
                flag2 = ((PdfNumber)f2).intValue();
            if(type1.equals(PdfName.BTN))
            {
                if(((flag1 ^ flag2) & 0x10000) != 0)
                    return;
                if((flag1 & 0x10000) == 0 && ((flag1 ^ flag2) & 0x8000) != 0)
                    return;
            } else
            if(type1.equals(PdfName.CH) && ((flag1 ^ flag2) & 0x20000) != 0)
                return;
            createWidgets(list, item);
        }
    }

    private void createWidgets(ArrayList list, AcroFields.Item item)
    {
        for(int k = 0; k < item.size(); k++)
        {
            list.add(item.getPage(k));
            PdfDictionary merged = item.getMerged(k);
            PdfObject dr = merged.get(PdfName.DR);
            if(dr != null)
                PdfFormField.mergeResources(resources, (PdfDictionary)PdfReader.getPdfObject(dr));
            PdfDictionary widget = new PdfDictionary();
            Iterator i$ = merged.getKeys().iterator();
            do
            {
                if(!i$.hasNext())
                    break;
                Object element = i$.next();
                PdfName key = (PdfName)element;
                if(widgetKeys.contains(key))
                    widget.put(key, merged.get(key));
            } while(true);
            widget.put(iTextTag, new PdfNumber(item.getTabOrder(k).intValue() + 1));
            list.add(widget);
        }

    }

    private PdfObject propagate(PdfObject obj)
        throws IOException
    {
        if(obj == null)
            return new PdfNull();
        if(obj.isArray())
        {
            PdfArray a = (PdfArray)obj;
            for(int i = 0; i < a.size(); i++)
                a.set(i, propagate(a.getPdfObject(i)));

            return a;
        }
        if(obj.isDictionary() || obj.isStream())
        {
            PdfDictionary d = (PdfDictionary)obj;
            PdfName key;
            for(Iterator i$ = d.getKeys().iterator(); i$.hasNext(); d.put(key, propagate(d.get(key))))
                key = (PdfName)i$.next();

            return d;
        }
        if(obj.isIndirect())
        {
            obj = PdfReader.getPdfObject(obj);
            return addToBody(propagate(obj)).getIndirectReference();
        } else
        {
            return obj;
        }
    }

    private void createAcroForms()
        throws IOException, BadPdfFormatException
    {
        if(fieldTree.isEmpty())
            return;
        PdfDictionary form = new PdfDictionary();
        form.put(PdfName.DR, propagate(resources));
        form.put(PdfName.DA, new PdfString("/Helv 0 Tf 0 g "));
        tabOrder = new HashMap();
        calculationOrderRefs = new ArrayList(calculationOrder);
        form.put(PdfName.FIELDS, branchForm(fieldTree, null, ""));
        if(hasSignature)
            form.put(PdfName.SIGFLAGS, new PdfNumber(3));
        PdfArray co = new PdfArray();
        for(int k = 0; k < calculationOrderRefs.size(); k++)
        {
            Object obj = calculationOrderRefs.get(k);
            if(obj instanceof PdfIndirectReference)
                co.add((PdfIndirectReference)obj);
        }

        if(co.size() > 0)
            form.put(PdfName.CO, co);
        acroForm = addToBody(form).getIndirectReference();
    }

    private void updateReferences(PdfObject obj)
    {
        if(obj.isDictionary() || obj.isStream())
        {
            PdfDictionary dictionary = (PdfDictionary)obj;
            Iterator i$ = dictionary.getKeys().iterator();
            do
            {
                if(!i$.hasNext())
                    break;
                PdfName key = (PdfName)i$.next();
                PdfObject o = dictionary.get(key);
                if(o.isIndirect())
                {
                    PdfReader reader = ((PRIndirectReference)o).getReader();
                    HashMap indirects = (HashMap)indirectMap.get(reader);
                    IndirectReferences indRef = (IndirectReferences)indirects.get(new RefKey((PRIndirectReference)o));
                    if(indRef != null)
                        dictionary.put(key, indRef.getRef());
                } else
                {
                    updateReferences(o);
                }
            } while(true);
        } else
        if(obj.isArray())
        {
            PdfArray array = (PdfArray)obj;
            for(int i = 0; i < array.size(); i++)
            {
                PdfObject o = array.getPdfObject(i);
                if(o.isIndirect())
                {
                    PdfReader reader = ((PRIndirectReference)o).getReader();
                    HashMap indirects = (HashMap)indirectMap.get(reader);
                    IndirectReferences indRef = (IndirectReferences)indirects.get(new RefKey((PRIndirectReference)o));
                    if(indRef != null)
                        array.set(i, indRef.getRef());
                } else
                {
                    updateReferences(o);
                }
            }

        }
    }

    private PdfArray branchForm(HashMap level, PdfIndirectReference parent, String fname)
        throws IOException
    {
        PdfArray arr = new PdfArray();
        for(Iterator i$ = level.entrySet().iterator(); i$.hasNext();)
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)i$.next();
            String name = (String)entry.getKey();
            Object obj = entry.getValue();
            PdfIndirectReference ind = getPdfIndirectReference();
            PdfDictionary dic = new PdfDictionary();
            if(parent != null)
                dic.put(PdfName.PARENT, parent);
            dic.put(PdfName.T, new PdfString(name, "UnicodeBig"));
            String fname2 = (new StringBuilder()).append(fname).append(".").append(name).toString();
            int coidx = calculationOrder.indexOf(fname2);
            if(coidx >= 0)
                calculationOrderRefs.set(coidx, ind);
            if(obj instanceof HashMap)
            {
                dic.put(PdfName.KIDS, branchForm((HashMap)obj, ind, fname2));
                arr.add(ind);
                addToBody(dic, ind, true);
            } else
            {
                ArrayList list = (ArrayList)obj;
                dic.mergeDifferent((PdfDictionary)list.get(0));
                if(list.size() == 3)
                {
                    dic.mergeDifferent((PdfDictionary)list.get(2));
                    int page = ((Integer)list.get(1)).intValue();
                    PdfArray annots = ((ImportedPage)importedPages.get(page - 1)).mergedFields;
                    PdfNumber nn = (PdfNumber)dic.get(iTextTag);
                    dic.remove(iTextTag);
                    dic.put(PdfName.TYPE, PdfName.ANNOT);
                    adjustTabOrder(annots, ind, nn);
                } else
                {
                    PdfArray kids = new PdfArray();
                    for(int k = 1; k < list.size(); k += 2)
                    {
                        int page = ((Integer)list.get(k)).intValue();
                        PdfArray annots = ((ImportedPage)importedPages.get(page - 1)).mergedFields;
                        PdfDictionary widget = new PdfDictionary();
                        widget.merge((PdfDictionary)list.get(k + 1));
                        widget.put(PdfName.PARENT, ind);
                        PdfNumber nn = (PdfNumber)widget.get(iTextTag);
                        widget.remove(iTextTag);
                        widget.put(PdfName.TYPE, PdfName.ANNOT);
                        PdfIndirectReference wref = addToBody(widget, getPdfIndirectReference(), true).getIndirectReference();
                        adjustTabOrder(annots, wref, nn);
                        kids.add(wref);
                    }

                    dic.put(PdfName.KIDS, kids);
                }
                arr.add(ind);
                addToBody(dic, ind, true);
            }
        }

        return arr;
    }

    private void adjustTabOrder(PdfArray annots, PdfIndirectReference ind, PdfNumber nn)
    {
        int v = nn.intValue();
        ArrayList t = (ArrayList)tabOrder.get(annots);
        if(t == null)
        {
            t = new ArrayList();
            int size = annots.size() - 1;
            for(int k = 0; k < size; k++)
                t.add(zero);

            t.add(Integer.valueOf(v));
            tabOrder.put(annots, t);
            annots.add(ind);
        } else
        {
            int size = t.size() - 1;
            int k = size;
            do
            {
                if(k < 0)
                    break;
                if(((Integer)t.get(k)).intValue() <= v)
                {
                    t.add(k + 1, Integer.valueOf(v));
                    annots.add(k + 1, ind);
                    size = -2;
                    break;
                }
                k--;
            } while(true);
            if(size != -2)
            {
                t.add(0, Integer.valueOf(v));
                annots.add(0, ind);
            }
        }
    }

    protected PdfDictionary getCatalog(PdfIndirectReference rootObj)
    {
        try
        {
            PdfDictionary theCat = pdf.getCatalog(rootObj);
            buildStructTreeRootForTagged(theCat);
            if(fieldArray != null)
                addFieldResources(theCat);
            else
            if(mergeFields && acroForm != null)
                theCat.put(PdfName.ACROFORM, acroForm);
            return theCat;
        }
        catch(IOException e)
        {
            throw new ExceptionConverter(e);
        }
    }

    protected boolean isStructTreeRootReference(PdfIndirectReference prRef)
    {
        if(prRef == null || structTreeRootReference == null)
            return false;
        else
            return prRef.number == structTreeRootReference.number && prRef.generation == structTreeRootReference.generation;
    }

    private void addFieldResources(PdfDictionary catalog)
        throws IOException
    {
        if(fieldArray == null)
            return;
        PdfDictionary acroForm = new PdfDictionary();
        catalog.put(PdfName.ACROFORM, acroForm);
        acroForm.put(PdfName.FIELDS, fieldArray);
        acroForm.put(PdfName.DA, new PdfString("/Helv 0 Tf 0 g "));
        if(fieldTemplates.isEmpty())
            return;
        PdfDictionary dr = new PdfDictionary();
        acroForm.put(PdfName.DR, dr);
        PdfTemplate template;
        for(Iterator i$ = fieldTemplates.iterator(); i$.hasNext(); PdfFormField.mergeResources(dr, (PdfDictionary)template.getResources()))
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
    }

    public void close()
    {
        if(open)
        {
            PdfReaderInstance ri = currentPdfReaderInstance;
            pdf.close();
            super.close();
        }
    }

    public PdfIndirectReference add(PdfOutline outline)
    {
        return null;
    }

    public void addAnnotation(PdfAnnotation pdfannotation)
    {
    }

    PdfIndirectReference add(PdfPage page, PdfContents contents)
        throws PdfException
    {
        return null;
    }

    public void freeReader(PdfReader reader)
        throws IOException
    {
        indirectMap.remove(reader);
        currentPdfReaderInstance = null;
        super.freeReader(reader);
    }

    public PageStamp createPageStamp(PdfImportedPage iPage)
    {
        int pageNum = iPage.getPageNumber();
        PdfReader reader = iPage.getPdfReaderInstance().getReader();
        PdfDictionary pageN = reader.getPageN(pageNum);
        return new PageStamp(reader, pageN, this);
    }

    protected static Counter COUNTER = CounterFactory.getCounter(co/com/pdf/text/pdf/PdfCopy);
    protected HashMap indirects;
    protected HashMap indirectMap;
    protected HashMap parentObjects;
    protected HashSet disableIndirects;
    protected PdfReader reader;
    protected int namePtr[] = {
        0
    };
    private boolean rotateContents;
    protected PdfArray fieldArray;
    protected HashSet fieldTemplates;
    private PdfStructTreeController structTreeController;
    private int currentStructArrayNumber;
    protected PRIndirectReference structTreeRootReference;
    protected HashMap indirectObjects;
    protected ArrayList savedObjects;
    protected ArrayList importedPages;
    protected boolean updateRootKids;
    private static PdfName annotId;
    private static int annotIdCnt = 0;
    protected boolean mergeFields;
    private boolean hasSignature;
    private PdfIndirectReference acroForm;
    private HashMap tabOrder;
    private ArrayList calculationOrderRefs;
    private PdfDictionary resources;
    protected ArrayList fields;
    private ArrayList calculationOrder;
    private HashMap fieldTree;
    private HashMap unmergedMap;
    private HashSet unmergedSet;
    private HashMap mergedMap;
    private HashSet mergedSet;
    private boolean mergeFieldsInternalCall;
    private static final PdfName iTextTag = new PdfName("_iTextTag_");
    private static final Integer zero = Integer.valueOf(0);
    protected static final HashSet widgetKeys;
    protected static final HashSet fieldKeys;

    static 
    {
        annotId = new PdfName("iTextAnnotId");
        widgetKeys = new HashSet();
        fieldKeys = new HashSet();
        widgetKeys.add(PdfName.SUBTYPE);
        widgetKeys.add(PdfName.CONTENTS);
        widgetKeys.add(PdfName.RECT);
        widgetKeys.add(PdfName.NM);
        widgetKeys.add(PdfName.M);
        widgetKeys.add(PdfName.F);
        widgetKeys.add(PdfName.BS);
        widgetKeys.add(PdfName.BORDER);
        widgetKeys.add(PdfName.AP);
        widgetKeys.add(PdfName.AS);
        widgetKeys.add(PdfName.C);
        widgetKeys.add(PdfName.A);
        widgetKeys.add(PdfName.STRUCTPARENT);
        widgetKeys.add(PdfName.OC);
        widgetKeys.add(PdfName.H);
        widgetKeys.add(PdfName.MK);
        widgetKeys.add(PdfName.DA);
        widgetKeys.add(PdfName.Q);
        widgetKeys.add(PdfName.P);
        widgetKeys.add(PdfName.TYPE);
        widgetKeys.add(annotId);
        fieldKeys.add(PdfName.AA);
        fieldKeys.add(PdfName.FT);
        fieldKeys.add(PdfName.TU);
        fieldKeys.add(PdfName.TM);
        fieldKeys.add(PdfName.FF);
        fieldKeys.add(PdfName.V);
        fieldKeys.add(PdfName.DV);
        fieldKeys.add(PdfName.DS);
        fieldKeys.add(PdfName.RV);
        fieldKeys.add(PdfName.OPT);
        fieldKeys.add(PdfName.MAXLEN);
        fieldKeys.add(PdfName.TI);
        fieldKeys.add(PdfName.I);
        fieldKeys.add(PdfName.LOCK);
        fieldKeys.add(PdfName.SV);
    }

}
