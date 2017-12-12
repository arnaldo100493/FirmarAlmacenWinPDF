// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XMPIteratorImpl.java

package co.com.pdf.xmp.impl;

import co.com.pdf.xmp.*;
import co.com.pdf.xmp.impl.xpath.XMPPath;
import co.com.pdf.xmp.impl.xpath.XMPPathParser;
import co.com.pdf.xmp.options.IteratorOptions;
import co.com.pdf.xmp.options.PropertyOptions;
import co.com.pdf.xmp.properties.XMPPropertyInfo;
import java.util.*;

// Referenced classes of package co.com.pdf.xmp.impl:
//            XMPMetaImpl, XMPNode, XMPNodeUtils, QName

public class XMPIteratorImpl
    implements XMPIterator
{
    private class NodeIteratorChildren extends NodeIterator
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

        public NodeIteratorChildren(XMPNode parentNode, String parentPath)
        {
            this$0 = XMPIteratorImpl.this;
            super();
            index = 0;
            if(parentNode.getOptions().isSchemaNode())
                setBaseNS(parentNode.getName());
            this.parentPath = accumulatePath(parentNode, parentPath, 1);
            childrenIterator = parentNode.iterateChildren();
        }
    }

    private class NodeIterator
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
                subIterator = new NodeIterator(child, path, index);
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
                final NodeIterator this$1;

                
                {
                    this$1 = NodeIterator.this;
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

        public NodeIterator()
        {
            this$0 = XMPIteratorImpl.this;
            super();
            state = 0;
            childrenIterator = null;
            index = 0;
            subIterator = Collections.EMPTY_LIST.iterator();
            returnProperty = null;
        }

        public NodeIterator(XMPNode visitedNode, String parentPath, int index)
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


    public XMPIteratorImpl(XMPMetaImpl xmp, String schemaNS, String propPath, IteratorOptions options)
        throws XMPException
    {
        baseNS = null;
        skipSiblings = false;
        skipSubtree = false;
        nodeIterator = null;
        this.options = options == null ? new IteratorOptions() : options;
        XMPNode startNode = null;
        String initialPath = null;
        boolean baseSchema = schemaNS != null && schemaNS.length() > 0;
        boolean baseProperty = propPath != null && propPath.length() > 0;
        if(!baseSchema && !baseProperty)
            startNode = xmp.getRoot();
        else
        if(baseSchema && baseProperty)
        {
            XMPPath path = XMPPathParser.expandXPath(schemaNS, propPath);
            XMPPath basePath = new XMPPath();
            for(int i = 0; i < path.size() - 1; i++)
                basePath.add(path.getSegment(i));

            startNode = XMPNodeUtils.findNode(xmp.getRoot(), path, false, null);
            baseNS = schemaNS;
            initialPath = basePath.toString();
        } else
        if(baseSchema && !baseProperty)
            startNode = XMPNodeUtils.findSchemaNode(xmp.getRoot(), schemaNS, false);
        else
            throw new XMPException("Schema namespace URI is required", 101);
        if(startNode != null)
        {
            if(!this.options.isJustChildren())
                nodeIterator = new NodeIterator(startNode, initialPath, 1);
            else
                nodeIterator = new NodeIteratorChildren(startNode, initialPath);
        } else
        {
            nodeIterator = Collections.EMPTY_LIST.iterator();
        }
    }

    public void skipSubtree()
    {
        skipSubtree = true;
    }

    public void skipSiblings()
    {
        skipSubtree();
        skipSiblings = true;
    }

    public boolean hasNext()
    {
        return nodeIterator.hasNext();
    }

    public Object next()
    {
        return nodeIterator.next();
    }

    public void remove()
    {
        throw new UnsupportedOperationException("The XMPIterator does not support remove().");
    }

    protected IteratorOptions getOptions()
    {
        return options;
    }

    protected String getBaseNS()
    {
        return baseNS;
    }

    protected void setBaseNS(String baseNS)
    {
        this.baseNS = baseNS;
    }

    private IteratorOptions options;
    private String baseNS;
    protected boolean skipSiblings;
    protected boolean skipSubtree;
    private Iterator nodeIterator;
}
