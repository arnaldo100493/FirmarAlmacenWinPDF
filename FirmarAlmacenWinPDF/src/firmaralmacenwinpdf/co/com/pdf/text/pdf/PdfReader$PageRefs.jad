// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfReader.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.*;
import java.io.IOException;
import java.util.*;

// Referenced classes of package co.com.pdf.text.pdf:
//            IntHashtable, PdfNumber, PdfObject, PRIndirectReference, 
//            PdfDictionary, PdfName, PdfArray, PdfReader, 
//            AcroFields

static class PdfReader$PageRefs
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
            if(PdfReader.access$300(reader) == -1)
                lastPageRead = -1;
            else
                lastPageRead = pageNum;
            PdfReader.access$302(reader, -1);
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
            PdfReader.access$302(reader, refsp.get(pageNum));
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
                IntHashtable$Entry entry;
                int p;
                for(Iterator it = refsp.getEntryIterator(); it.hasNext(); refs2.put(p < pageNum ? p : p + 1, entry.getValue()))
                {
                    entry = (IntHashtable$Entry)it.next();
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
                    int last = PdfReader.access$300(reader);
                    PdfObject count = PdfReader.getPdfObjectRelease(dic.get(PdfName.COUNT));
                    PdfReader.access$302(reader, last);
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
        if(PdfReader.access$200(reader))
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
            if(PdfReader.access$200(reader))
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


    private PdfReader$PageRefs(PdfReader reader)
        throws IOException
    {
        lastPageRead = -1;
        this.reader = reader;
        if(PdfReader.access$200(reader))
        {
            refsp = new IntHashtable();
            PdfNumber npages = (PdfNumber)PdfReader.getPdfObjectRelease(reader.rootPages.get(PdfName.COUNT));
            sizep = npages.intValue();
        } else
        {
            readPages();
        }
    }

    PdfReader$PageRefs(PdfReader$PageRefs other, PdfReader reader)
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

    PdfReader$PageRefs(PdfReader x0, PdfReader._cls1 x1)
        throws IOException
    {
        this(x0);
    }
}
