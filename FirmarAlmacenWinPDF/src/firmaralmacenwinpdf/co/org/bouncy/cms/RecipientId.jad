// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RecipientId.java

package co.org.bouncy.cms;

import co.org.bouncy.util.Selector;

public abstract class RecipientId
    implements Selector
{

    protected RecipientId(int type)
    {
        this.type = type;
    }

    public int getType()
    {
        return type;
    }

    public abstract Object clone();

    public static final int keyTrans = 0;
    public static final int kek = 1;
    public static final int keyAgree = 2;
    public static final int password = 3;
    private final int type;
}
