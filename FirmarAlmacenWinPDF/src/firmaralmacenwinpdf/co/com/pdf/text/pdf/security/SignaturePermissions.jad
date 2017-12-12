// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SignaturePermissions.java

package co.com.pdf.text.pdf.security;

import co.com.pdf.text.pdf.*;
import java.util.ArrayList;
import java.util.List;

public class SignaturePermissions
{
    public class FieldLock
    {

        public PdfName getAction()
        {
            return action;
        }

        public PdfArray getFields()
        {
            return fields;
        }

        public String toString()
        {
            return (new StringBuilder()).append(action.toString()).append(fields != null ? fields.toString() : "").toString();
        }

        PdfName action;
        PdfArray fields;
        final SignaturePermissions this$0;

        public FieldLock(PdfName action, PdfArray fields)
        {
            this$0 = SignaturePermissions.this;
            super();
            this.action = action;
            this.fields = fields;
        }
    }


    public SignaturePermissions(PdfDictionary sigDict, SignaturePermissions previous)
    {
        certification = false;
        fillInAllowed = true;
        annotationsAllowed = true;
        fieldLocks = new ArrayList();
        if(previous != null)
        {
            annotationsAllowed &= previous.isAnnotationsAllowed();
            fillInAllowed &= previous.isFillInAllowed();
            fieldLocks.addAll(previous.getFieldLocks());
        }
        PdfArray ref = sigDict.getAsArray(PdfName.REFERENCE);
        if(ref != null)
        {
            for(int i = 0; i < ref.size(); i++)
            {
                PdfDictionary dict = ref.getAsDict(i);
                PdfDictionary params = dict.getAsDict(PdfName.TRANSFORMPARAMS);
                if(PdfName.DOCMDP.equals(dict.getAsName(PdfName.TRANSFORMMETHOD)))
                    certification = true;
                PdfName action = params.getAsName(PdfName.ACTION);
                if(action != null)
                    fieldLocks.add(new FieldLock(action, params.getAsArray(PdfName.FIELDS)));
                PdfNumber p = params.getAsNumber(PdfName.P);
                if(p != null)
                    switch(p.intValue())
                    {
                    case 1: // '\001'
                        fillInAllowed &= false;
                        // fall through

                    case 2: // '\002'
                        annotationsAllowed &= false;
                        break;
                    }
            }

        }
    }

    public boolean isCertification()
    {
        return certification;
    }

    public boolean isFillInAllowed()
    {
        return fillInAllowed;
    }

    public boolean isAnnotationsAllowed()
    {
        return annotationsAllowed;
    }

    public List getFieldLocks()
    {
        return fieldLocks;
    }

    boolean certification;
    boolean fillInAllowed;
    boolean annotationsAllowed;
    List fieldLocks;
}
