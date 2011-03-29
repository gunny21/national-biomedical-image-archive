/**
 * DOMNodeAsMFNodeAdapter.java
 *
 * This file was generated by MapForce 2011r2sp1.
 *
 * YOU SHOULD NOT MODIFY THIS FILE, BECAUSE IT WILL BE
 * OVERWRITTEN WHEN YOU RE-RUN CODE GENERATION.
 *
 * Refer to the MapForce Documentation for further details.
 * http://www.altova.com/mapforce
 */

package com.altova.xml;

import javax.xml.namespace.QName;
import org.w3c.dom.Node;
import com.altova.mapforce.IEnumerable;
import com.altova.mapforce.IMFNode;
import com.altova.mapforce.MFNode;
import com.altova.mapforce.MFEmptySequence;
import com.altova.mapforce.MFNodeByKindAndQNameFilter;
import com.altova.mapforce.MFSingletonSequence;
import com.altova.mapforce.SequenceJoin;

public class DOMNodeAsMFNodeAdapter implements IMFNode 
{
	Node node;
	
	public DOMNodeAsMFNodeAdapter(Node node)
	{
		this.node = node;
	}
	
	public Node getNode() {return node;}
	
	public String getLocalName() 
	{
		if (node.getNodeType() == Node.PROCESSING_INSTRUCTION_NODE)
			return ((org.w3c.dom.ProcessingInstruction) node).getTarget();
		
		return node.getLocalName() == null ? "" : node.getLocalName();
	}

	public String getNamespaceURI() 
	{
		return node.getNamespaceURI() == null ? "" : node.getNamespaceURI(); 
	}
	
	public String getPrefix()
	{
		return node.getPrefix();
	}

	public int getNodeKind() 
	{
		switch (node.getNodeType())
		{
			case Node.ATTRIBUTE_NODE:
				return MFNodeKind_Attribute; // also field?
			case Node.CDATA_SECTION_NODE:
				return MFNodeKind_Text; // |MFNodeKind_CData;
			case Node.COMMENT_NODE:
				return MFNodeKind_Comment;
			case Node.DOCUMENT_NODE:
				return MFNodeKind_Document;
			case Node.ELEMENT_NODE:
				return MFNodeKind_Element;
			case Node.TEXT_NODE:
				return MFNodeKind_Text;
			case Node.PROCESSING_INSTRUCTION_NODE:
				return MFNodeKind_ProcessingInstruction;
			default:
				return 0;
		}
	}

	public QName getQName() 
	{
		return new QName(getNamespaceURI(), getLocalName());
	}

	public IEnumerable select(int mfQueryKind, Object query) 
	{
		switch(mfQueryKind)
		{
			case MFQueryKind_All:
				switch(node.getNodeType())
				{
					case Node.ELEMENT_NODE:
						return new SequenceJoin(new DOMAttributesAsMFNodeSequenceAdapter(node), new DOMChildrenAsMFNodeSequenceAdapter(node)); 
					case Node.DOCUMENT_NODE:
						return new DOMChildrenAsMFNodeSequenceAdapter(node);
						
					case Node.ATTRIBUTE_NODE:
						return new MFSingletonSequence(node.getNodeValue());
		
					case Node.TEXT_NODE:
					case Node.CDATA_SECTION_NODE:
						return new MFSingletonSequence(node.getNodeValue());
						
					case Node.COMMENT_NODE:
						return new MFSingletonSequence(node.getNodeValue());
						
					case Node.PROCESSING_INSTRUCTION_NODE:
						return new MFSingletonSequence(node.getNodeValue());
						
					default:
						return new MFEmptySequence();
				}
				
			case MFQueryKind_AllChildren:
				switch(node.getNodeType())
				{
					case Node.ELEMENT_NODE:
					case Node.DOCUMENT_NODE:
						return new DOMChildrenAsMFNodeSequenceAdapter(node);
						
					case Node.ATTRIBUTE_NODE:
						return new MFSingletonSequence(node.getNodeValue());
		
					case Node.TEXT_NODE:
					case Node.CDATA_SECTION_NODE:
						return new MFSingletonSequence(node.getNodeValue());
						
					case Node.COMMENT_NODE:
						return new MFSingletonSequence(node.getNodeValue());
						
					case Node.PROCESSING_INSTRUCTION_NODE:
						return new MFSingletonSequence(node.getNodeValue());
						
					default:
						return new MFEmptySequence();
				}
				
			case MFQueryKind_AllAttributes:
				switch (node.getNodeType())
				{
					case Node.ELEMENT_NODE:
					case Node.DOCUMENT_NODE:
						return new DOMAttributesAsMFNodeSequenceAdapter(node);

					default:
						return new MFEmptySequence();
				}
				
			case MFQueryKind_ChildrenByQName:
				switch (node.getNodeType())
				{
					case Node.ELEMENT_NODE:
					case Node.DOCUMENT_NODE:
						return new MFNodeByKindAndQNameFilter(new DOMChildrenAsMFNodeSequenceAdapter(node), MFNodeKind_Children, ((QName)query));

					default:
						return new MFEmptySequence();
				}
				
			case MFQueryKind_SelfByQName:
				switch (node.getNodeType())
				{
					case Node.ELEMENT_NODE:
					case Node.DOCUMENT_NODE:
						if (getQName().equals(query))
							return new MFSingletonSequence(this);
						else
							return new MFEmptySequence();

					default:
						return new MFEmptySequence();

				}
				
			case MFQueryKind_AttributeByQName:
				switch (node.getNodeType())
				{
					case Node.ELEMENT_NODE:
						{
							QName qq = (QName) query;
							Node att = node.getAttributes().getNamedItemNS(qq.getNamespaceURI().length() == 0 ? null : qq.getNamespaceURI(), qq.getLocalPart());
							
							if (att != null)
								return new MFSingletonSequence(new DOMNodeAsMFNodeAdapter(att));
							else
								return new MFEmptySequence();
						}

					default:
						return new MFEmptySequence();

				}
				
			default:
				throw new UnsupportedOperationException("Unsupported query type.");
		}
	}
	
	public javax.xml.namespace.QName qnameValue() {return XmlTreeOperations.castToQName(node, null);}
	public String value() throws Exception {return node.getTextContent();}
	public Object typedValue() throws Exception
	{
		return MFNode.collectTypedValue(select(IMFNode.MFQueryKind_AllChildren, null));
	}
}
