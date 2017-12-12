// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AcroFields.java

package co.com.pdf.text.pdf;


// Referenced classes of package co.com.pdf.text.pdf:
//            IntHashtable, AcroFields

private static class AcroFields$InstHit
{

    public boolean isHit(int n)
    {
        if(hits == null)
            return true;
        else
            return hits.containsKey(n);
    }

    IntHashtable hits;

    public AcroFields$InstHit(int inst[])
    {
        if(inst == null)
            return;
        hits = new IntHashtable();
        for(int k = 0; k < inst.length; k++)
            hits.put(inst[k], 1);

    }
}
