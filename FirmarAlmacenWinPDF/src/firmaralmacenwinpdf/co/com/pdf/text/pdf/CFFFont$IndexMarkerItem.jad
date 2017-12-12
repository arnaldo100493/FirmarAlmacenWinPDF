// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CFFFont.java

package co.com.pdf.text.pdf;


// Referenced classes of package co.com.pdf.text.pdf:
//            CFFFont

protected static final class CFFFont$IndexMarkerItem extends CFFFont.Item
{

    public void xref()
    {
        offItem.set((myOffset - indexBase.myOffset) + 1);
    }

    private CFFFont.OffsetItem offItem;
    private CFFFont.IndexBaseItem indexBase;

    public CFFFont$IndexMarkerItem(CFFFont.OffsetItem offItem, CFFFont.IndexBaseItem indexBase)
    {
        this.offItem = offItem;
        this.indexBase = indexBase;
    }
}
