// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfSigLockDictionary.java

package co.com.pdf.text.pdf;


// Referenced classes of package co.com.pdf.text.pdf:
//            PdfDictionary, PdfArray, PdfString, PdfName, 
//            PdfNumber

public class PdfSigLockDictionary extends PdfDictionary
{
    public static final class LockPermissions extends Enum
    {

        public static LockPermissions[] values()
        {
            return (LockPermissions[])$VALUES.clone();
        }

        public static LockPermissions valueOf(String name)
        {
            return (LockPermissions)Enum.valueOf(co/com/pdf/text/pdf/PdfSigLockDictionary$LockPermissions, name);
        }

        public PdfNumber getValue()
        {
            return number;
        }

        public static final LockPermissions NO_CHANGES_ALLOWED;
        public static final LockPermissions FORM_FILLING;
        public static final LockPermissions FORM_FILLING_AND_ANNOTATION;
        private PdfNumber number;
        private static final LockPermissions $VALUES[];

        static 
        {
            NO_CHANGES_ALLOWED = new LockPermissions("NO_CHANGES_ALLOWED", 0, 1);
            FORM_FILLING = new LockPermissions("FORM_FILLING", 1, 2);
            FORM_FILLING_AND_ANNOTATION = new LockPermissions("FORM_FILLING_AND_ANNOTATION", 2, 3);
            $VALUES = (new LockPermissions[] {
                NO_CHANGES_ALLOWED, FORM_FILLING, FORM_FILLING_AND_ANNOTATION
            });
        }

        private LockPermissions(String s, int i, int p)
        {
            super(s, i);
            number = new PdfNumber(p);
        }
    }

    public static final class LockAction extends Enum
    {

        public static LockAction[] values()
        {
            return (LockAction[])$VALUES.clone();
        }

        public static LockAction valueOf(String name)
        {
            return (LockAction)Enum.valueOf(co/com/pdf/text/pdf/PdfSigLockDictionary$LockAction, name);
        }

        public PdfName getValue()
        {
            return name;
        }

        public static final LockAction ALL;
        public static final LockAction INCLUDE;
        public static final LockAction EXCLUDE;
        private PdfName name;
        private static final LockAction $VALUES[];

        static 
        {
            ALL = new LockAction("ALL", 0, PdfName.ALL);
            INCLUDE = new LockAction("INCLUDE", 1, PdfName.INCLUDE);
            EXCLUDE = new LockAction("EXCLUDE", 2, PdfName.EXCLUDE);
            $VALUES = (new LockAction[] {
                ALL, INCLUDE, EXCLUDE
            });
        }

        private LockAction(String s, int i, PdfName name)
        {
            super(s, i);
            this.name = name;
        }
    }


    public PdfSigLockDictionary()
    {
        super(PdfName.SIGFIELDLOCK);
        put(PdfName.ACTION, LockAction.ALL.getValue());
    }

    public PdfSigLockDictionary(LockPermissions p)
    {
        this();
        put(PdfName.P, p.getValue());
    }

    public transient PdfSigLockDictionary(LockAction action, String fields[])
    {
        this(action, null, fields);
    }

    public transient PdfSigLockDictionary(LockAction action, LockPermissions p, String fields[])
    {
        super(PdfName.SIGFIELDLOCK);
        put(PdfName.ACTION, action.getValue());
        if(p != null)
            put(PdfName.P, p.getValue());
        PdfArray fieldsArray = new PdfArray();
        String arr$[] = fields;
        int len$ = arr$.length;
        for(int i$ = 0; i$ < len$; i$++)
        {
            String field = arr$[i$];
            fieldsArray.add(new PdfString(field));
        }

        put(PdfName.FIELDS, fieldsArray);
    }
}
