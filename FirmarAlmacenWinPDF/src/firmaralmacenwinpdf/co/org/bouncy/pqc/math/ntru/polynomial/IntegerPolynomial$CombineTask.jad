// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IntegerPolynomial.java

package co.org.bouncy.pqc.math.ntru.polynomial;

import java.util.concurrent.Callable;

// Referenced classes of package co.org.bouncy.pqc.math.ntru.polynomial:
//            ModularResultant, IntegerPolynomial

private class IntegerPolynomial$CombineTask
    implements Callable
{

    public ModularResultant call()
    {
        return ModularResultant.combineRho(modRes1, modRes2);
    }

    public volatile Object call()
        throws Exception
    {
        return call();
    }

    private ModularResultant modRes1;
    private ModularResultant modRes2;
    final IntegerPolynomial this$0;

    private IntegerPolynomial$CombineTask(ModularResultant modRes1, ModularResultant modRes2)
    {
        this$0 = IntegerPolynomial.this;
        super();
        this.modRes1 = modRes1;
        this.modRes2 = modRes2;
    }

    IntegerPolynomial$CombineTask(ModularResultant x1, ModularResultant x2, IntegerPolynomial._cls1 x3)
    {
        this(x1, x2);
    }
}
