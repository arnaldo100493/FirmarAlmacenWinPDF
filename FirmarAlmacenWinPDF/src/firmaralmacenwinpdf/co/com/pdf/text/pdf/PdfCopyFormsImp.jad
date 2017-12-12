// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfCopyFormsImp.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.DocumentException;
import co.com.pdf.text.error_messages.MessageLocalization;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfCopyFieldsImp, PdfReader, IntHashtable, AcroFields

class PdfCopyFormsImp extends PdfCopyFieldsImp
{

    PdfCopyFormsImp(OutputStream os)
        throws DocumentException
    {
        super(os);
    }

    public void copyDocumentFields(PdfReader reader)
        throws DocumentException
    {
        if(!reader.isOpenedWithFullPermissions())
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("pdfreader.not.opened.with.owner.password", new Object[0]));
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
        fields.add(reader.getAcroFields());
        updateCalculationOrder(reader);
    }

    void mergeFields()
    {
        for(int k = 0; k < fields.size(); k++)
        {
            java.util.Map fd = ((AcroFields)fields.get(k)).getFields();
            mergeWithMaster(fd);
        }

    }
}
