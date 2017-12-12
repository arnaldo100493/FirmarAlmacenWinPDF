// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XMPIteratorImpl.java

package co.com.pdf.xmp.impl;

import co.com.pdf.xmp.options.IteratorOptions;
import co.com.pdf.xmp.options.PropertyOptions;
import java.util.Iterator;

// Referenced classes of package co.com.pdf.xmp.impl:
//            XMPNode, XMPIteratorImpl

private class XMPIteratorImpl$NodeIteratorChildren extends XMPIteratorImpl.NodeIterator
{

    public boolean hasNext()
    {
        if(getReturnProperty() != null)
            return true;
        if(skipSiblings)
            return false;
        if(childrenIterator.hasNext())
        {
            XMPNode child = (XMPNode)childrenIterator.next();
            index++;
            String path = null;
            if(child.getOptions().isSchemaNode())
                setBaseNS(child.getName());
            else
            if(child.getParent() != null)
                path = accumulatePath(child, parentPath, index);
            if(!getOptions().isJustLeafnodes() || !child.hasChildren())
            {
                setReturnProperty(createPropertyInfo(child, getBaseNS(), path));
                return true;
            } else
            {
                return hasNext();
            }
        } else
        {
            return false;
        }
    }

    private String parentPath;
    private Iterator childrenIterator;
    private int index;
    final XMPIteratorImpl this$0;

    public XMPIteratorImpl$NodeIteratorChildren(XMPNode parentNode, String parentPath)
    {
        this$0 = XMPIteratorImpl.this;
        super(XMPIteratorImpl.this);
        index = 0;
        if(parentNode.getOptions().isSchemaNode())
            setBaseNS(parentNode.getName());
        this.parentPath = accumulatePath(parentNode, parentPath, 1);
        childrenIterator = parentNode.iterateChildren();
    }
}
