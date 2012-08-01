package com.osm2xp.utils.helpers;

import java.util.ArrayList;

import com.osm2xp.model.options.ObjectFile;
import com.osm2xp.model.options.ObjectTagRule;
import com.osm2xp.model.osm.Tag;

/**
 * OptionsHelper.
 * 
 * @author Benjamin Blanchet
 * 
 */
public abstract class OptionsHelper {

	/**
	 * @return
	 */
	public static ObjectTagRule createObjectTagRule(final String objectMessage) {
		return new ObjectTagRule(new Tag("a tag key", "a tag value"),
				new ArrayList<ObjectFile>() {
					{
						add(new ObjectFile(objectMessage));
					}
				}, 0, true);
	}
}
