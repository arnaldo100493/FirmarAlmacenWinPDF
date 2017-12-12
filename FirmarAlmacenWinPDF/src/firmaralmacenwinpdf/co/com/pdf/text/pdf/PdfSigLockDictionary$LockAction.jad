// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfSigLockDictionary.java

package co.com.pdf.text.pdf;


// Referenced classes of package co.com.pdf.text.pdf:
//            PdfSigLockDictionary, PdfName

public static final class PdfSigLockDictionary$LockAction extends Enum
{

    public static PdfSigLockDictionary$LockAction[] values()
    {
        return (PdfSigLockDictionary$LockAction[])$VALUES.clone();
    }

    public static PdfSigLockDictionary$LockAction valueOf(String name)
    {
        return (PdfSigLockDictionary$LockAction)Enum.valueOf(co/com/pdf/text/pdf/PdfSigLockDictionary$LockAction, name);
    }

    public PdfName getValue()
    {
        return name;
    }

    public static final PdfSigLockDictionary$LockAction ALL;
    public static final PdfSigLockDictionary$LockAction INCLUDE;
    public static final PdfSigLockDictionary$LockAction EXCLUDE;
    private PdfName name;
    private static final PdfSigLockDictionary$LockAction $VALUES[];

    static 
    {
        ALL = new PdfSigLockDictionary$LockAction("ALL", 0, PdfName.ALL);
        INCLUDE = new PdfSigLockDictionary$LockAction("INCLUDE", 1, PdfName.INCLUDE);
        EXCLUDE = new PdfSigLockDictionary$LockAction("EXCLUDE", 2, PdfName.EXCLUDE);
        $VALUES = (new PdfSigLockDictionary$LockAction[] {
            ALL, INCLUDE, EXCLUDE
        });
    }

    private PdfSigLockDictionary$LockAction(String s, int i, PdfName name)
    {
        super(s, i);
        this.name = name;
    }
}
