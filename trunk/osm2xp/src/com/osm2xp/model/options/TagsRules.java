package com.osm2xp.model.options;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import com.osm2xp.model.osm.Tag;

/**
 * TagsRules.
 * 
 * @author Benjamin Blanchet
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TagsRules", propOrder = { "tag", "objectsFiles" })
@XmlSeeAlso({ ForestTagRule.class, FacadeTagRule.class, ObjectTagRule.class })
public class TagsRules {

	@XmlElement(required = true)
	protected Tag tag;
	@XmlElement(required = true)
	protected List<ObjectFile> objectsFiles;

	/**
	 * Default no-arg constructor
	 * 
	 */
	public TagsRules() {
		super();
	}

	/**
	 * Fully-initialising value constructor
	 * 
	 */
	public TagsRules(final Tag tag, final List<ObjectFile> objectsFiles) {
		this.tag = tag;
		this.objectsFiles = objectsFiles;
	}

	/**
	 * Gets the value of the tag property.
	 * 
	 * @return possible object is {@link Tag }
	 * 
	 */
	public Tag getTag() {
		return tag;
	}

	/**
	 * Sets the value of the tag property.
	 * 
	 * @param value
	 *            allowed object is {@link Tag }
	 * 
	 */
	public void setTag(Tag value) {
		this.tag = value;
	}

	/**
	 * Gets the value of the objectsFiles property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the objectsFiles property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getObjectsFiles().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link ObjectFile }
	 * 
	 * 
	 */
	public List<ObjectFile> getObjectsFiles() {
		if (objectsFiles == null) {
			objectsFiles = new ArrayList<ObjectFile>();
		}
		return this.objectsFiles;
	}

}
