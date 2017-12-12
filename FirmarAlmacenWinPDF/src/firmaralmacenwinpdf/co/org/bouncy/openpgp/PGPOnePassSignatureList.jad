// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PGPOnePassSignatureList.java

package co.org.bouncy.openpgp;


// Referenced classes of package co.org.bouncy.openpgp:
//            PGPOnePassSignature

public class PGPOnePassSignatureList
{

    public PGPOnePassSignatureList(PGPOnePassSignature sigs[])
    {
        this.sigs = new PGPOnePassSignature[sigs.length];
        System.arraycopy(sigs, 0, this.sigs, 0, sigs.length);
    }

    public PGPOnePassSignatureList(PGPOnePassSignature sig)
    {
        sigs = new PGPOnePassSignature[1];
        sigs[0] = sig;
    }

    public PGPOnePassSignature get(int index)
    {
        return sigs[index];
    }

    public int size()
    {
        return sigs.length;
    }

    public boolean isEmpty()
    {
        return sigs.length == 0;
    }

    PGPOnePassSignature sigs[];
}
