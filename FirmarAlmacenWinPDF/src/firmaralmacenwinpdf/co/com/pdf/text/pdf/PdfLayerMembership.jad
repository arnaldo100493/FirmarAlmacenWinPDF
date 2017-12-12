// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfLayerMembership.java

package co.com.pdf.text.pdf;

import java.util.Collection;
import java.util.HashSet;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfDictionary, PdfArray, PdfName, PdfOCG, 
//            PdfWriter, PdfLayer, PdfIndirectReference, PdfVisibilityExpression, 
//            PdfObject

public class PdfLayerMembership extends PdfDictionary
    implements PdfOCG
{

    public PdfLayerMembership(PdfWriter writer)
    {
        super(PdfName.OCMD);
        members = new PdfArray();
        layers = new HashSet();
        put(PdfName.OCGS, members);
        ref = writer.getPdfIndirectReference();
    }

    public PdfIndirectReference getRef()
    {
        return ref;
    }

    public void addMember(PdfLayer layer)
    {
        if(!layers.contains(layer))
        {
            members.add(layer.getRef());
            layers.add(layer);
        }
    }

    public Collection getLayers()
    {
        return layers;
    }

    public void setVisibilityPolicy(PdfName type)
    {
        put(PdfName.P, type);
    }

    public void setVisibilityExpression(PdfVisibilityExpression ve)
    {
        put(PdfName.VE, ve);
    }

    public PdfObject getPdfObject()
    {
        return this;
    }

    public static final PdfName ALLON = new PdfName("AllOn");
    public static final PdfName ANYON = new PdfName("AnyOn");
    public static final PdfName ANYOFF = new PdfName("AnyOff");
    public static final PdfName ALLOFF = new PdfName("AllOff");
    PdfIndirectReference ref;
    PdfArray members;
    HashSet layers;

}
