// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfTransition.java

package co.com.pdf.text.pdf;


// Referenced classes of package co.com.pdf.text.pdf:
//            PdfDictionary, PdfNumber, PdfName

public class PdfTransition
{

    public PdfTransition()
    {
        this(6);
    }

    public PdfTransition(int type)
    {
        this(type, 1);
    }

    public PdfTransition(int type, int duration)
    {
        this.duration = duration;
        this.type = type;
    }

    public int getDuration()
    {
        return duration;
    }

    public int getType()
    {
        return type;
    }

    public PdfDictionary getTransitionDictionary()
    {
        PdfDictionary trans = new PdfDictionary(PdfName.TRANS);
        switch(type)
        {
        case 1: // '\001'
            trans.put(PdfName.S, PdfName.SPLIT);
            trans.put(PdfName.D, new PdfNumber(duration));
            trans.put(PdfName.DM, PdfName.V);
            trans.put(PdfName.M, PdfName.O);
            break;

        case 2: // '\002'
            trans.put(PdfName.S, PdfName.SPLIT);
            trans.put(PdfName.D, new PdfNumber(duration));
            trans.put(PdfName.DM, PdfName.H);
            trans.put(PdfName.M, PdfName.O);
            break;

        case 3: // '\003'
            trans.put(PdfName.S, PdfName.SPLIT);
            trans.put(PdfName.D, new PdfNumber(duration));
            trans.put(PdfName.DM, PdfName.V);
            trans.put(PdfName.M, PdfName.I);
            break;

        case 4: // '\004'
            trans.put(PdfName.S, PdfName.SPLIT);
            trans.put(PdfName.D, new PdfNumber(duration));
            trans.put(PdfName.DM, PdfName.H);
            trans.put(PdfName.M, PdfName.I);
            break;

        case 5: // '\005'
            trans.put(PdfName.S, PdfName.BLINDS);
            trans.put(PdfName.D, new PdfNumber(duration));
            trans.put(PdfName.DM, PdfName.V);
            break;

        case 6: // '\006'
            trans.put(PdfName.S, PdfName.BLINDS);
            trans.put(PdfName.D, new PdfNumber(duration));
            trans.put(PdfName.DM, PdfName.H);
            break;

        case 7: // '\007'
            trans.put(PdfName.S, PdfName.BOX);
            trans.put(PdfName.D, new PdfNumber(duration));
            trans.put(PdfName.M, PdfName.I);
            break;

        case 8: // '\b'
            trans.put(PdfName.S, PdfName.BOX);
            trans.put(PdfName.D, new PdfNumber(duration));
            trans.put(PdfName.M, PdfName.O);
            break;

        case 9: // '\t'
            trans.put(PdfName.S, PdfName.WIPE);
            trans.put(PdfName.D, new PdfNumber(duration));
            trans.put(PdfName.DI, new PdfNumber(0));
            break;

        case 10: // '\n'
            trans.put(PdfName.S, PdfName.WIPE);
            trans.put(PdfName.D, new PdfNumber(duration));
            trans.put(PdfName.DI, new PdfNumber(180));
            break;

        case 11: // '\013'
            trans.put(PdfName.S, PdfName.WIPE);
            trans.put(PdfName.D, new PdfNumber(duration));
            trans.put(PdfName.DI, new PdfNumber(90));
            break;

        case 12: // '\f'
            trans.put(PdfName.S, PdfName.WIPE);
            trans.put(PdfName.D, new PdfNumber(duration));
            trans.put(PdfName.DI, new PdfNumber(270));
            break;

        case 13: // '\r'
            trans.put(PdfName.S, PdfName.DISSOLVE);
            trans.put(PdfName.D, new PdfNumber(duration));
            break;

        case 14: // '\016'
            trans.put(PdfName.S, PdfName.GLITTER);
            trans.put(PdfName.D, new PdfNumber(duration));
            trans.put(PdfName.DI, new PdfNumber(0));
            break;

        case 15: // '\017'
            trans.put(PdfName.S, PdfName.GLITTER);
            trans.put(PdfName.D, new PdfNumber(duration));
            trans.put(PdfName.DI, new PdfNumber(270));
            break;

        case 16: // '\020'
            trans.put(PdfName.S, PdfName.GLITTER);
            trans.put(PdfName.D, new PdfNumber(duration));
            trans.put(PdfName.DI, new PdfNumber(315));
            break;
        }
        return trans;
    }

    public static final int SPLITVOUT = 1;
    public static final int SPLITHOUT = 2;
    public static final int SPLITVIN = 3;
    public static final int SPLITHIN = 4;
    public static final int BLINDV = 5;
    public static final int BLINDH = 6;
    public static final int INBOX = 7;
    public static final int OUTBOX = 8;
    public static final int LRWIPE = 9;
    public static final int RLWIPE = 10;
    public static final int BTWIPE = 11;
    public static final int TBWIPE = 12;
    public static final int DISSOLVE = 13;
    public static final int LRGLITTER = 14;
    public static final int TBGLITTER = 15;
    public static final int DGLITTER = 16;
    protected int duration;
    protected int type;
}
