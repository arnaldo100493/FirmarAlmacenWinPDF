// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PGPSignatureList.java

package co.org.bouncy.openpgp;


// Referenced classes of package co.org.bouncy.openpgp:
//            PGPSignature

public class PGPSignatureList
{

    public PGPSignatureList(PGPSignature sigs[])
    {
        this.sigs = new PGPSignature[sigs.length];
        System.arraycopy(sigs, 0, this.sigs, 0, sigs.length);
    }

    public PGPSignatureList(PGPSignature sig)
    {
        sigs = new PGPSignature[1];
        sigs[0] = sig;
    }

    public PGPSignature get(int index)
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

    PGPSignature sigs[];
}
