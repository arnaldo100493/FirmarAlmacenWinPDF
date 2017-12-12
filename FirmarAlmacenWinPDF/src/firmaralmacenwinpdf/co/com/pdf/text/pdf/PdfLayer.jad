// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfLayer.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.error_messages.MessageLocalization;
import java.io.IOException;
import java.util.ArrayList;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfDictionary, PdfStamperImp, PdfString, PdfName, 
//            PdfNumber, PdfArray, PdfOCG, PdfWriter, 
//            PdfIndirectObject, PdfIndirectReference, PdfObject

public class PdfLayer extends PdfDictionary
    implements PdfOCG
{

    PdfLayer(String title)
    {
        on = true;
        onPanel = true;
        this.title = title;
    }

    public static PdfLayer createTitle(String title, PdfWriter writer)
    {
        if(title == null)
        {
            throw new NullPointerException(MessageLocalization.getComposedMessage("title.cannot.be.null", new Object[0]));
        } else
        {
            PdfLayer layer = new PdfLayer(title);
            writer.registerLayer(layer);
            return layer;
        }
    }

    public PdfLayer(String name, PdfWriter writer)
        throws IOException
    {
        super(PdfName.OCG);
        on = true;
        onPanel = true;
        setName(name);
        if(writer instanceof PdfStamperImp)
            ref = writer.addToBody(this).getIndirectReference();
        else
            ref = writer.getPdfIndirectReference();
        writer.registerLayer(this);
    }

    String getTitle()
    {
        return title;
    }

    public void addChild(PdfLayer child)
    {
        if(child.parent != null)
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("the.layer.1.already.has.a.parent", new Object[] {
                child.getAsString(PdfName.NAME).toUnicodeString()
            }));
        child.parent = this;
        if(children == null)
            children = new ArrayList();
        children.add(child);
    }

    public PdfLayer getParent()
    {
        return parent;
    }

    public ArrayList getChildren()
    {
        return children;
    }

    public PdfIndirectReference getRef()
    {
        return ref;
    }

    void setRef(PdfIndirectReference ref)
    {
        this.ref = ref;
    }

    public void setName(String name)
    {
        put(PdfName.NAME, new PdfString(name, "UnicodeBig"));
    }

    public PdfObject getPdfObject()
    {
        return this;
    }

    public boolean isOn()
    {
        return on;
    }

    public void setOn(boolean on)
    {
        this.on = on;
    }

    private PdfDictionary getUsage()
    {
        PdfDictionary usage = getAsDict(PdfName.USAGE);
        if(usage == null)
        {
            usage = new PdfDictionary();
            put(PdfName.USAGE, usage);
        }
        return usage;
    }

    public void setCreatorInfo(String creator, String subtype)
    {
        PdfDictionary usage = getUsage();
        PdfDictionary dic = new PdfDictionary();
        dic.put(PdfName.CREATOR, new PdfString(creator, "UnicodeBig"));
        dic.put(PdfName.SUBTYPE, new PdfName(subtype));
        usage.put(PdfName.CREATORINFO, dic);
    }

    public void setLanguage(String lang, boolean preferred)
    {
        PdfDictionary usage = getUsage();
        PdfDictionary dic = new PdfDictionary();
        dic.put(PdfName.LANG, new PdfString(lang, "UnicodeBig"));
        if(preferred)
            dic.put(PdfName.PREFERRED, PdfName.ON);
        usage.put(PdfName.LANGUAGE, dic);
    }

    public void setExport(boolean export)
    {
        PdfDictionary usage = getUsage();
        PdfDictionary dic = new PdfDictionary();
        dic.put(PdfName.EXPORTSTATE, export ? ((PdfObject) (PdfName.ON)) : ((PdfObject) (PdfName.OFF)));
        usage.put(PdfName.EXPORT, dic);
    }

    public void setZoom(float min, float max)
    {
        if(min <= 0.0F && max < 0.0F)
            return;
        PdfDictionary usage = getUsage();
        PdfDictionary dic = new PdfDictionary();
        if(min > 0.0F)
            dic.put(PdfName.MIN_LOWER_CASE, new PdfNumber(min));
        if(max >= 0.0F)
            dic.put(PdfName.MAX_LOWER_CASE, new PdfNumber(max));
        usage.put(PdfName.ZOOM, dic);
    }

    public void setPrint(String subtype, boolean printstate)
    {
        PdfDictionary usage = getUsage();
        PdfDictionary dic = new PdfDictionary();
        dic.put(PdfName.SUBTYPE, new PdfName(subtype));
        dic.put(PdfName.PRINTSTATE, printstate ? ((PdfObject) (PdfName.ON)) : ((PdfObject) (PdfName.OFF)));
        usage.put(PdfName.PRINT, dic);
    }

    public void setView(boolean view)
    {
        PdfDictionary usage = getUsage();
        PdfDictionary dic = new PdfDictionary();
        dic.put(PdfName.VIEWSTATE, view ? ((PdfObject) (PdfName.ON)) : ((PdfObject) (PdfName.OFF)));
        usage.put(PdfName.VIEW, dic);
    }

    public void setPageElement(String pe)
    {
        PdfDictionary usage = getUsage();
        PdfDictionary dic = new PdfDictionary();
        dic.put(PdfName.SUBTYPE, new PdfName(pe));
        usage.put(PdfName.PAGEELEMENT, dic);
    }

    public transient void setUser(String type, String names[])
    {
        PdfDictionary usage = getUsage();
        PdfDictionary dic = new PdfDictionary();
        dic.put(PdfName.TYPE, new PdfName(type));
        PdfArray arr = new PdfArray();
        String arr$[] = names;
        int len$ = arr$.length;
        for(int i$ = 0; i$ < len$; i$++)
        {
            String s = arr$[i$];
            arr.add(new PdfString(s, "UnicodeBig"));
        }

        usage.put(PdfName.NAME, arr);
        usage.put(PdfName.USER, dic);
    }

    public boolean isOnPanel()
    {
        return onPanel;
    }

    public void setOnPanel(boolean onPanel)
    {
        this.onPanel = onPanel;
    }

    protected PdfIndirectReference ref;
    protected ArrayList children;
    protected PdfLayer parent;
    protected String title;
    private boolean on;
    private boolean onPanel;
}
