// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XMPIteratorImpl.java

package co.com.pdf.xmp.impl;

import co.com.pdf.xmp.XMPMetaFactory;
import co.com.pdf.xmp.XMPSchemaRegistry;
import co.com.pdf.xmp.options.IteratorOptions;
import co.com.pdf.xmp.options.PropertyOptions;
import co.com.pdf.xmp.properties.XMPPropertyInfo;
import java.util.*;

// Referenced classes of package co.com.pdf.xmp.impl:
//            XMPNode, XMPIteratorImpl, QName

private class XMPIteratorImpl$NodeIterator
    implements Iterator
{

    public boolean hasNext()
    {
        if(returnProperty != null)
            return true;
        if(state == 0)
            return reportNode();
        if(state == 1)
        {
            if(childrenIterator == null)
                childrenIterator = visitedNode.iterateChildren();
            boolean hasNext = iterateChildren(childrenIterator);
            if(!hasNext && visitedNode.hasQualifier() && !getOptions().isOmitQualifiers())
            {
                state = 2;
                childrenIterator = null;
                hasNext = hasNext();
            }
            return hasNext;
        }
        if(childrenIterator == null)
            childrenIterator = visitedNode.iterateQualifier();
        return iterateChildren(childrenIterator);
    }

    protected boolean reportNode()
    {
        state = 1;
        if(visitedNode.getParent() != null && (!getOptions().isJustLeafnodes() || !visitedNode.hasChildren()))
        {
            returnProperty = createPropertyInfo(visitedNode, getBaseNS(), path);
            return true;
        } else
        {
            return hasNext();
        }
    }

    private boolean iterateChildren(Iterator iterator)
    {
        if(skipSiblings)
        {
            skipSiblings = false;
            subIterator = Collections.EMPTY_LIST.iterator();
        }
        if(!subIterator.hasNext() && iterator.hasNext())
        {
            XMPNode child = (XMPNode)iterator.next();
            index++;
            subIterator = new XMPIteratorImpl$NodeIterator(child, path, index);
        }
        if(subIterator.hasNext())
        {
            returnProperty = (XMPPropertyInfo)subIterator.next();
            return true;
        } else
        {
            return false;
        }
    }

    public Object next()
    {
        if(hasNext())
        {
            XMPPropertyInfo result = returnProperty;
            returnProperty = null;
            return result;
        } else
        {
            throw new NoSuchElementException("There are no more nodes to return");
        }
    }

    public void remove()
    {
        throw new UnsupportedOperationException();
    }

    protected String accumulatePath(XMPNode currNode, String parentPath, int currentIndex)
    {
        if(currNode.getParent() == null || currNode.getOptions().isSchemaNode())
            return null;
        String separator;
        String segmentName;
        if(currNode.getParent().getOptions().isArray())
        {
            separator = "";
            segmentName = (new StringBuilder()).append("[").append(String.valueOf(currentIndex)).append("]").toString();
        } else
        {
            separator = "/";
            segmentName = currNode.getName();
        }
        if(parentPath == null || parentPath.length() == 0)
            return segmentName;
        if(getOptions().isJustLeafname())
            return segmentName.startsWith("?") ? segmentName.substring(1) : segmentName;
        else
            return (new StringBuilder()).append(parentPath).append(separator).append(segmentName).toString();
    }

    protected XMPPropertyInfo createPropertyInfo(final XMPNode node, final String baseNS, final String path)
    {
        final String value = node.getOptions().isSchemaNode() ? null : node.getValue();
        return new XMPPropertyInfo() {

            public String getNamespace()
            {
                if(!node.getOptions().isSchemaNode())
                {
                    QName qname = new QName(node.getName());
                    return XMPMetaFactory.getSchemaRegistry().getNamespaceURI(qname.getPrefix());
                } else
                {
                    return baseNS;
                }
            }

            public String getPath()
            {
                return path;
            }

            public String getValue()
            {
                return value;
            }

            public PropertyOptions getOptions()
            {
                return node.getOptions();
            }

            public String getLanguage()
            {
                return null;
            }

            final XMPNode val$node;
            final String val$baseNS;
            final String val$path;
            final String val$value;
            final XMPIteratorImpl.NodeIterator this$1;

            
            {
                this$1 = XMPIteratorImpl.NodeIterator.this;
                node = xmpnode;
                baseNS = s;
                path = s1;
                value = s2;
                super();
            }
        }
;
    }

    protected Iterator getChildrenIterator()
    {
        return childrenIterator;
    }

    protected void setChildrenIterator(Iterator childrenIterator)
    {
        this.childrenIterator = childrenIterator;
    }

    protected XMPPropertyInfo getReturnProperty()
    {
        return returnProperty;
    }

    protected void setReturnProperty(XMPPropertyInfo returnProperty)
    {
        this.returnProperty = returnProperty;
    }

    protected static final int ITERATE_NODE = 0;
    protected static final int ITERATE_CHILDREN = 1;
    protected static final int ITERATE_QUALIFIER = 2;
    private int state;
    private XMPNode visitedNode;
    private String path;
    private Iterator childrenIterator;
    private int index;
    private Iterator subIterator;
    private XMPPropertyInfo returnProperty;
    final XMPIteratorImpl this$0;

    public XMPIteratorImpl$NodeIterator()
    {
        this$0 = XMPIteratorImpl.this;
        super();
        state = 0;
        childrenIterator = null;
        index = 0;
        subIterator = Collections.EMPTY_LIST.iterator();
        returnProperty = null;
    }

    public XMPIteratorImpl$NodeIterator(XMPNode visitedNode, String parentPath, int index)
    {
        this$0 = XMPIteratorImpl.this;
        super();
        state = 0;
        childrenIterator = null;
        this.index = 0;
        subIterator = Collections.EMPTY_LIST.iterator();
        returnProperty = null;
        this.visitedNode = visitedNode;
        state = 0;
        if(visitedNode.getOptions().isSchemaNode())
            setBaseNS(visitedNode.getName());
        path = accumulatePath(visitedNode, parentPath, index);
    }
}
