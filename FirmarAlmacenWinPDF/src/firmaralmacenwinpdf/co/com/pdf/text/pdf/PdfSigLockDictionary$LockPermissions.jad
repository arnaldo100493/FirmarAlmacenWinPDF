// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfSigLockDictionary.java

package co.com.pdf.text.pdf;


// Referenced classes of package co.com.pdf.text.pdf:
//            PdfNumber, PdfSigLockDictionary

public static final class PdfSigLockDictionary$LockPermissions extends Enum
{

    public static PdfSigLockDictionary$LockPermissions[] values()
    {
        return (PdfSigLockDictionary$LockPermissions[])$VALUES.clone();
    }

    public static PdfSigLockDictionary$LockPermissions valueOf(String name)
    {
        return (PdfSigLockDictionary$LockPermissions)Enum.valueOf(co/com/pdf/text/pdf/PdfSigLockDictionary$LockPermissions, name);
    }

    public PdfNumber getValue()
    {
        return number;
    }

    public static final PdfSigLockDictionary$LockPermissions NO_CHANGES_ALLOWED;
    public static final PdfSigLockDictionary$LockPermissions FORM_FILLING;
    public static final PdfSigLockDictionary$LockPermissions FORM_FILLING_AND_ANNOTATION;
    private PdfNumber number;
    private static final PdfSigLockDictionary$LockPermissions $VALUES[];

    static 
    {
        NO_CHANGES_ALLOWED = new PdfSigLockDictionary$LockPermissions("NO_CHANGES_ALLOWED", 0, 1);
        FORM_FILLING = new PdfSigLockDictionary$LockPermissions("FORM_FILLING", 1, 2);
        FORM_FILLING_AND_ANNOTATION = new PdfSigLockDictionary$LockPermissions("FORM_FILLING_AND_ANNOTATION", 2, 3);
        $VALUES = (new PdfSigLockDictionary$LockPermissions[] {
            NO_CHANGES_ALLOWED, FORM_FILLING, FORM_FILLING_AND_ANNOTATION
        });
    }

    private PdfSigLockDictionary$LockPermissions(String s, int i, int p)
    {
        super(s, i);
        number = new PdfNumber(p);
    }
}
