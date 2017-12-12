// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfStructureTreeRoot.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.pdf.interfaces.IPdfStructureElement;
import java.io.IOException;
import java.util.*;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfDictionary, PdfObject, PdfArray, PdfIndirectReference, 
//            PdfStructureElement, PdfName, PdfWriter, PdfIndirectObject, 
//            PdfNumberTree

public class PdfStructureTreeRoot extends PdfDictionary
    implements IPdfStructureElement
{

    PdfStructureTreeRoot(PdfWriter writer)
    {
        super(PdfName.STRUCTTREEROOT);
        parentTree = new HashMap();
        classMap = null;
        classes = null;
        numTree = null;
        this.writer = writer;
        reference = writer.getPdfIndirectReference();
    }

    private void createNumTree()
        throws IOException
    {
        if(numTree != null)
            return;
        numTree = new HashMap();
        Iterator i$ = parentTree.keySet().iterator();
        do
        {
            if(!i$.hasNext())
                break;
            Integer i = (Integer)i$.next();
            PdfObject obj = (PdfObject)parentTree.get(i);
            if(obj.isArray())
            {
                PdfArray ar = (PdfArray)obj;
                numTree.put(i, writer.addToBody(ar).getIndirectReference());
            } else
            if(obj instanceof PdfIndirectReference)
                numTree.put(i, (PdfIndirectReference)obj);
        } while(true);
    }

    public void mapRole(PdfName used, PdfName standard)
    {
        PdfDictionary rm = (PdfDictionary)get(PdfName.ROLEMAP);
        if(rm == null)
        {
            rm = new PdfDictionary();
            put(PdfName.ROLEMAP, rm);
        }
        rm.put(used, standard);
    }

    public void mapClass(PdfName name, PdfObject object)
    {
        if(classMap == null)
        {
            classMap = new PdfDictionary();
            classes = new HashMap();
        }
        classes.put(name, object);
    }

    public PdfObject getMappedClass(PdfName name)
    {
        if(classes == null)
            return null;
        else
            return (PdfObject)classes.get(name);
    }

    public PdfWriter getWriter()
    {
        return writer;
    }

    public HashMap getNumTree()
        throws IOException
    {
        if(numTree == null)
            createNumTree();
        return numTree;
    }

    public PdfIndirectReference getReference()
    {
        return reference;
    }

    void setPageMark(int page, PdfIndirectReference struc)
    {
        Integer i = Integer.valueOf(page);
        PdfArray ar = (PdfArray)parentTree.get(i);
        if(ar == null)
        {
            ar = new PdfArray();
            parentTree.put(i, ar);
        }
        ar.add(struc);
    }

    void setAnnotationMark(int structParentIndex, PdfIndirectReference struc)
    {
        parentTree.put(Integer.valueOf(structParentIndex), struc);
    }

    private void nodeProcess(PdfDictionary struc, PdfIndirectReference reference)
        throws IOException
    {
        PdfObject obj = struc.get(PdfName.K);
        if(obj != null && obj.isArray())
        {
            PdfArray ar = (PdfArray)obj;
            for(int k = 0; k < ar.size(); k++)
            {
                PdfDictionary dictionary = ar.getAsDict(k);
                if(dictionary != null && PdfName.STRUCTELEM.equals(dictionary.get(PdfName.TYPE)) && (ar.getPdfObject(k) instanceof PdfStructureElement))
                {
                    PdfStructureElement e = (PdfStructureElement)dictionary;
                    ar.set(k, e.getReference());
                    nodeProcess(((PdfDictionary) (e)), e.getReference());
                }
            }

        }
        if(reference != null)
            writer.addToBody(struc, reference);
    }

    void buildTree()
        throws IOException
    {
        createNumTree();
        PdfDictionary dicTree = PdfNumberTree.writeTree(numTree, writer);
        if(dicTree != null)
            put(PdfName.PARENTTREE, writer.addToBody(dicTree).getIndirectReference());
        if(classMap != null && !classes.isEmpty())
        {
            Iterator i$ = classes.entrySet().iterator();
            do
            {
                if(!i$.hasNext())
                    break;
                java.util.Map.Entry entry = (java.util.Map.Entry)i$.next();
                PdfObject value = (PdfObject)entry.getValue();
                if(value.isDictionary())
                    classMap.put((PdfName)entry.getKey(), writer.addToBody(value).getIndirectReference());
                else
                if(value.isArray())
                {
                    PdfArray newArray = new PdfArray();
                    PdfArray array = (PdfArray)value;
                    for(int i = 0; i < array.size(); i++)
                        if(array.getPdfObject(i).isDictionary())
                            newArray.add(writer.addToBody(array.getAsDict(i)).getIndirectReference());

                    classMap.put((PdfName)entry.getKey(), newArray);
                }
            } while(true);
            put(PdfName.CLASSMAP, writer.addToBody(classMap).getIndirectReference());
        }
        nodeProcess(this, reference);
    }

    public PdfObject getAttribute(PdfName name)
    {
        PdfDictionary attr = getAsDict(PdfName.A);
        if(attr != null && attr.contains(name))
            return attr.get(name);
        else
            return null;
    }

    public void setAttribute(PdfName name, PdfObject obj)
    {
        PdfDictionary attr = getAsDict(PdfName.A);
        if(attr == null)
        {
            attr = new PdfDictionary();
            put(PdfName.A, attr);
        }
        attr.put(name, obj);
    }

    private HashMap parentTree;
    private PdfIndirectReference reference;
    private PdfDictionary classMap;
    protected HashMap classes;
    private HashMap numTree;
    private PdfWriter writer;
}
