// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfCopyFieldsImp.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.*;
import co.com.pdf.text.error_messages.MessageLocalization;
import co.com.pdf.text.exceptions.BadPasswordException;
import co.com.pdf.text.log.Counter;
import co.com.pdf.text.log.CounterFactory;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfWriter, PdfDocument, PdfDictionary, PdfCopyFields, 
//            PdfReader, IntHashtable, PRIndirectReference, PdfIndirectReference, 
//            PdfName, PdfArray, PdfObject, PdfString, 
//            PdfNumber, AcroFields, PdfIndirectObject, PdfBoolean, 
//            PdfPages, RandomAccessFileOrArray, PdfFormField

/**
 * @deprecated Class PdfCopyFieldsImp is deprecated
 */

class PdfCopyFieldsImp extends PdfWriter
{

    protected Counter getCounter()
    {
        return COUNTER;
    }

    PdfCopyFieldsImp(OutputStream os)
        throws DocumentException
    {
        this(os, '\0');
    }

    PdfCopyFieldsImp(OutputStream os, char pdfVersion)
        throws DocumentException
    {
        super(new PdfDocument(), os);
        readers = new ArrayList();
        readers2intrefs = new HashMap();
        pages2intrefs = new HashMap();
        visited = new HashMap();
        fields = new ArrayList();
        fieldTree = new HashMap();
        pageRefs = new ArrayList();
        pageDics = new ArrayList();
        resources = new PdfDictionary();
        closing = false;
        calculationOrder = new ArrayList();
        needAppearances = false;
        COUNTER = CounterFactory.getCounter(co/com/pdf/text/pdf/PdfCopyFields);
        pdf.addWriter(this);
        if(pdfVersion != 0)
            super.setPdfVersion(pdfVersion);
        nd = new Document();
        nd.addDocListener(pdf);
    }

    void addDocument(PdfReader reader, List pagesToKeep)
        throws DocumentException, IOException
    {
        if(!readers2intrefs.containsKey(reader) && reader.isTampered())
            throw new DocumentException(MessageLocalization.getComposedMessage("the.document.was.reused", new Object[0]));
        reader = new PdfReader(reader);
        reader.selectPages(pagesToKeep);
        if(reader.getNumberOfPages() == 0)
        {
            return;
        } else
        {
            reader.setTampered(false);
            addDocument(reader);
            return;
        }
    }

    void addDocument(PdfReader reader)
        throws DocumentException, IOException
    {
        if(!reader.isOpenedWithFullPermissions())
            throw new BadPasswordException(MessageLocalization.getComposedMessage("pdfreader.not.opened.with.owner.password", new Object[0]));
        openDoc();
        if(readers2intrefs.containsKey(reader))
        {
            reader = new PdfReader(reader);
        } else
        {
            if(reader.isTampered())
                throw new DocumentException(MessageLocalization.getComposedMessage("the.document.was.reused", new Object[0]));
            reader.consolidateNamedDestinations();
            reader.setTampered(true);
        }
        reader.shuffleSubsetNames();
        readers2intrefs.put(reader, new IntHashtable());
        readers.add(reader);
        int len = reader.getNumberOfPages();
        IntHashtable refs = new IntHashtable();
        for(int p = 1; p <= len; p++)
        {
            refs.put(reader.getPageOrigRef(p).getNumber(), 1);
            reader.releasePage(p);
        }

        pages2intrefs.put(reader, refs);
        visited.put(reader, new IntHashtable());
        AcroFields acro = reader.getAcroFields();
        boolean needapp = !acro.isGenerateAppearances();
        if(needapp)
            needAppearances = true;
        fields.add(acro);
        updateCalculationOrder(reader);
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
            name = name.substring(0, name.length() - 1);
        return name;
    }

    protected void updateCalculationOrder(PdfReader reader)
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

    void propagate(PdfObject obj, PdfIndirectReference refo, boolean restricted)
        throws IOException
    {
        if(obj == null)
            return;
        if(obj instanceof PdfIndirectReference)
            return;
label0:
        switch(obj.type())
        {
        case 8: // '\b'
        case 9: // '\t'
        default:
            break;

        case 6: // '\006'
        case 7: // '\007'
            PdfDictionary dic = (PdfDictionary)obj;
            Iterator i$ = dic.getKeys().iterator();
            do
            {
                if(!i$.hasNext())
                    break;
                PdfName key = (PdfName)i$.next();
                if(!restricted || !key.equals(PdfName.PARENT) && !key.equals(PdfName.KIDS))
                {
                    PdfObject ob = dic.get(key);
                    if(ob != null && ob.isIndirect())
                    {
                        PRIndirectReference ind = (PRIndirectReference)ob;
                        if(!setVisited(ind) && !isPage(ind))
                        {
                            PdfIndirectReference ref = getNewReference(ind);
                            propagate(PdfReader.getPdfObjectRelease(ind), ref, restricted);
                        }
                    } else
                    {
                        propagate(ob, null, restricted);
                    }
                }
            } while(true);
            break;

        case 5: // '\005'
            Iterator it = ((PdfArray)obj).listIterator();
            do
            {
                if(!it.hasNext())
                    break label0;
                PdfObject ob = (PdfObject)it.next();
                if(ob != null && ob.isIndirect())
                {
                    PRIndirectReference ind = (PRIndirectReference)ob;
                    if(!isVisited(ind) && !isPage(ind))
                    {
                        PdfIndirectReference ref = getNewReference(ind);
                        propagate(PdfReader.getPdfObjectRelease(ind), ref, restricted);
                    }
                } else
                {
                    propagate(ob, null, restricted);
                }
            } while(true);

        case 10: // '\n'
            throw new RuntimeException(MessageLocalization.getComposedMessage("reference.pointing.to.reference", new Object[0]));
        }
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

    protected PdfArray branchForm(HashMap level, PdfIndirectReference parent, String fname)
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
                addToBody(dic, ind);
            } else
            {
                ArrayList list = (ArrayList)obj;
                dic.mergeDifferent((PdfDictionary)list.get(0));
                if(list.size() == 3)
                {
                    dic.mergeDifferent((PdfDictionary)list.get(2));
                    int page = ((Integer)list.get(1)).intValue();
                    PdfDictionary pageDic = (PdfDictionary)pageDics.get(page - 1);
                    PdfArray annots = pageDic.getAsArray(PdfName.ANNOTS);
                    if(annots == null)
                    {
                        annots = new PdfArray();
                        pageDic.put(PdfName.ANNOTS, annots);
                    }
                    PdfNumber nn = (PdfNumber)dic.get(iTextTag);
                    dic.remove(iTextTag);
                    adjustTabOrder(annots, ind, nn);
                } else
                {
                    PdfArray kids = new PdfArray();
                    for(int k = 1; k < list.size(); k += 2)
                    {
                        int page = ((Integer)list.get(k)).intValue();
                        PdfDictionary pageDic = (PdfDictionary)pageDics.get(page - 1);
                        PdfArray annots = pageDic.getAsArray(PdfName.ANNOTS);
                        if(annots == null)
                        {
                            annots = new PdfArray();
                            pageDic.put(PdfName.ANNOTS, annots);
                        }
                        PdfDictionary widget = new PdfDictionary();
                        widget.merge((PdfDictionary)list.get(k + 1));
                        widget.put(PdfName.PARENT, ind);
                        PdfNumber nn = (PdfNumber)widget.get(iTextTag);
                        widget.remove(iTextTag);
                        PdfIndirectReference wref = addToBody(widget).getIndirectReference();
                        adjustTabOrder(annots, wref, nn);
                        kids.add(wref);
                        propagate(widget, null, false);
                    }

                    dic.put(PdfName.KIDS, kids);
                }
                arr.add(ind);
                addToBody(dic, ind);
                propagate(dic, null, false);
            }
        }

        return arr;
    }

    protected void createAcroForms()
        throws IOException
    {
        if(fieldTree.isEmpty())
            return;
        form = new PdfDictionary();
        form.put(PdfName.DR, resources);
        propagate(resources, null, false);
        if(needAppearances)
            form.put(PdfName.NEEDAPPEARANCES, PdfBoolean.PDFTRUE);
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
    }

    public void close()
    {
        if(closing)
        {
            super.close();
            return;
        }
        closing = true;
        try
        {
            closeIt();
        }
        catch(Exception e)
        {
            throw new ExceptionConverter(e);
        }
    }

    protected void closeIt()
        throws IOException
    {
        Iterator i$;
        for(int k = 0; k < readers.size(); k++)
            ((PdfReader)readers.get(k)).removeFields();

        for(int r = 0; r < readers.size(); r++)
        {
            PdfReader reader = (PdfReader)readers.get(r);
            for(int page = 1; page <= reader.getNumberOfPages(); page++)
            {
                pageRefs.add(getNewReference(reader.getPageOrigRef(page)));
                pageDics.add(reader.getPageN(page));
            }

        }

        mergeFields();
        createAcroForms();
        for(int r = 0; r < readers.size(); r++)
        {
            PdfReader reader = (PdfReader)readers.get(r);
            for(int page = 1; page <= reader.getNumberOfPages(); page++)
            {
                PdfDictionary dic = reader.getPageN(page);
                PdfIndirectReference pageRef = getNewReference(reader.getPageOrigRef(page));
                PdfIndirectReference parent = root.addPageRef(pageRef);
                dic.put(PdfName.PARENT, parent);
                propagate(dic, pageRef, false);
            }

        }

        i$ = readers2intrefs.entrySet().iterator();
_L2:
        java.util.Map.Entry entry;
        PdfReader reader;
        if(!i$.hasNext())
            break; /* Loop/switch isn't completed */
        entry = (java.util.Map.Entry)i$.next();
        reader = (PdfReader)entry.getKey();
        file = reader.getSafeFile();
        file.reOpen();
        IntHashtable t = (IntHashtable)entry.getValue();
        int keys[] = t.toOrderedKeys();
        for(int k = 0; k < keys.length; k++)
        {
            PRIndirectReference ref = new PRIndirectReference(reader, keys[k]);
            addToBody(PdfReader.getPdfObjectRelease(ref), t.get(keys[k]));
        }

        try
        {
            file.close();
        }
        catch(Exception e) { }
        if(true) goto _L2; else goto _L1
        Exception exception;
        exception;
        try
        {
            file.close();
        }
        catch(Exception e) { }
        throw exception;
_L1:
        pdf.close();
        return;
    }

    void addPageOffsetToField(Map fd, int pageOffset)
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

    void createWidgets(ArrayList list, AcroFields.Item item)
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
                if(widgetKeys.containsKey(key))
                    widget.put(key, merged.get(key));
            } while(true);
            widget.put(iTextTag, new PdfNumber(item.getTabOrder(k).intValue() + 1));
            list.add(widget);
        }

    }

    void mergeField(String name, AcroFields.Item item)
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
                if(fieldKeys.containsKey(key))
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

    void mergeWithMaster(Map fd)
    {
        java.util.Map.Entry entry;
        String name;
        for(Iterator i$ = fd.entrySet().iterator(); i$.hasNext(); mergeField(name, (AcroFields.Item)entry.getValue()))
        {
            entry = (java.util.Map.Entry)i$.next();
            name = (String)entry.getKey();
        }

    }

    void mergeFields()
    {
        int pageOffset = 0;
        for(int k = 0; k < fields.size(); k++)
        {
            Map fd = ((AcroFields)fields.get(k)).getFields();
            addPageOffsetToField(fd, pageOffset);
            mergeWithMaster(fd);
            pageOffset += ((PdfReader)readers.get(k)).getNumberOfPages();
        }

    }

    public PdfIndirectReference getPageReference(int page)
    {
        return (PdfIndirectReference)pageRefs.get(page - 1);
    }

    protected PdfDictionary getCatalog(PdfIndirectReference rootObj)
    {
        try
        {
            PdfDictionary cat = pdf.getCatalog(rootObj);
            if(form != null)
            {
                PdfIndirectReference ref = addToBody(form).getIndirectReference();
                cat.put(PdfName.ACROFORM, ref);
            }
            return cat;
        }
        catch(IOException e)
        {
            throw new ExceptionConverter(e);
        }
    }

    protected PdfIndirectReference getNewReference(PRIndirectReference ref)
    {
        return new PdfIndirectReference(0, getNewObjectNumber(ref.getReader(), ref.getNumber(), 0));
    }

    protected int getNewObjectNumber(PdfReader reader, int number, int generation)
    {
        IntHashtable refs = (IntHashtable)readers2intrefs.get(reader);
        int n = refs.get(number);
        if(n == 0)
        {
            n = getIndirectReferenceNumber();
            refs.put(number, n);
        }
        return n;
    }

    protected boolean setVisited(PRIndirectReference ref)
    {
        IntHashtable refs = (IntHashtable)visited.get(ref.getReader());
        if(refs != null)
            return refs.put(ref.getNumber(), 1) != 0;
        else
            return false;
    }

    protected boolean isVisited(PRIndirectReference ref)
    {
        IntHashtable refs = (IntHashtable)visited.get(ref.getReader());
        if(refs != null)
            return refs.containsKey(ref.getNumber());
        else
            return false;
    }

    protected boolean isVisited(PdfReader reader, int number, int generation)
    {
        IntHashtable refs = (IntHashtable)readers2intrefs.get(reader);
        return refs.containsKey(number);
    }

    protected boolean isPage(PRIndirectReference ref)
    {
        IntHashtable refs = (IntHashtable)pages2intrefs.get(ref.getReader());
        if(refs != null)
            return refs.containsKey(ref.getNumber());
        else
            return false;
    }

    RandomAccessFileOrArray getReaderFile(PdfReader reader)
    {
        return file;
    }

    public void openDoc()
    {
        if(!nd.isOpen())
            nd.open();
    }

    private static final PdfName iTextTag = new PdfName("_iTextTag_");
    private static final Integer zero = Integer.valueOf(0);
    ArrayList readers;
    HashMap readers2intrefs;
    HashMap pages2intrefs;
    HashMap visited;
    ArrayList fields;
    RandomAccessFileOrArray file;
    HashMap fieldTree;
    ArrayList pageRefs;
    ArrayList pageDics;
    PdfDictionary resources;
    PdfDictionary form;
    boolean closing;
    Document nd;
    private HashMap tabOrder;
    private ArrayList calculationOrder;
    private ArrayList calculationOrderRefs;
    private boolean hasSignature;
    private boolean needAppearances;
    protected Counter COUNTER;
    protected static final HashMap widgetKeys;
    protected static final HashMap fieldKeys;

    static 
    {
        widgetKeys = new HashMap();
        fieldKeys = new HashMap();
        Integer one = Integer.valueOf(1);
        widgetKeys.put(PdfName.SUBTYPE, one);
        widgetKeys.put(PdfName.CONTENTS, one);
        widgetKeys.put(PdfName.RECT, one);
        widgetKeys.put(PdfName.NM, one);
        widgetKeys.put(PdfName.M, one);
        widgetKeys.put(PdfName.F, one);
        widgetKeys.put(PdfName.BS, one);
        widgetKeys.put(PdfName.BORDER, one);
        widgetKeys.put(PdfName.AP, one);
        widgetKeys.put(PdfName.AS, one);
        widgetKeys.put(PdfName.C, one);
        widgetKeys.put(PdfName.A, one);
        widgetKeys.put(PdfName.STRUCTPARENT, one);
        widgetKeys.put(PdfName.OC, one);
        widgetKeys.put(PdfName.H, one);
        widgetKeys.put(PdfName.MK, one);
        widgetKeys.put(PdfName.DA, one);
        widgetKeys.put(PdfName.Q, one);
        widgetKeys.put(PdfName.P, one);
        fieldKeys.put(PdfName.AA, one);
        fieldKeys.put(PdfName.FT, one);
        fieldKeys.put(PdfName.TU, one);
        fieldKeys.put(PdfName.TM, one);
        fieldKeys.put(PdfName.FF, one);
        fieldKeys.put(PdfName.V, one);
        fieldKeys.put(PdfName.DV, one);
        fieldKeys.put(PdfName.DS, one);
        fieldKeys.put(PdfName.RV, one);
        fieldKeys.put(PdfName.OPT, one);
        fieldKeys.put(PdfName.MAXLEN, one);
        fieldKeys.put(PdfName.TI, one);
        fieldKeys.put(PdfName.I, one);
        fieldKeys.put(PdfName.LOCK, one);
        fieldKeys.put(PdfName.SV, one);
    }
}
