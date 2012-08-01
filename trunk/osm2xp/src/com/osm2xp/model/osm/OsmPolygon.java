package com.osm2xp.model.osm;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import math.geom2d.Point2D;
import math.geom2d.polygon.LinearRing2D;

import com.osm2xp.utils.GeomUtils;
import com.osm2xp.utils.OsmUtils;

/**
 * OsmPolygon.
 * 
 * @author Benjamin Blanchet
 * 
 */
public class OsmPolygon {

	@Override
	public String toString() {
		return "OsmPolygon [id=" + id + ", tag=" + tags + "]";
	}

	protected Long id;
	protected List<Tag> tags;
	protected List<Node> nodes;
	private Double area;
	private Boolean simplePolygon;
	private LinearRing2D polygon;
	private Point2D center;
	private Integer height;

	public OsmPolygon(long id, List<Tag> tags, List<Node> nodes) {
		super();
		this.id = id;
		this.tags = tags;
		this.nodes = nodes;
		this.height = OsmUtils.getHeightFromTags(tags);

	}

	private void initPolygon() {
		polygon = GeomUtils.getPolygonFromOsmNodes(nodes);

	}

	public OsmPolygon() {

	}

	/**
	 * Split current polygon along tiles
	 * 
	 * @return
	 */
	public List<OsmPolygon> splitPolygonAroundTiles() {
		List<OsmPolygon> result = new ArrayList<OsmPolygon>();
		// first check on how many tiles is this polygon
		Set<Point2D> tiles = new HashSet<Point2D>();
		for (Node node : nodes) {
			Point2D point2d = new Point2D(node.lat, node.lon);
			point2d = GeomUtils.cleanCoordinatePoint(point2d);
			tiles.add(point2d);
		}

		// if the polygon is on only one tile, return the current polygon
		if (tiles.size() == 1) {
			result.add(this);
		} else {
			// the polygon is on more than one tile, split it.
			if (this.polygon == null) {
				initPolygon();
			}
			Map<Point2D, OsmPolygon> polygons = new HashMap<Point2D, OsmPolygon>();
			for (Point2D point : polygon.getVertices()) {
				Point2D tilePoint = GeomUtils.cleanCoordinatePoint(point);
				if (polygons.get(tilePoint) == null) {
					polygons.put(tilePoint, new OsmPolygon(id, tags,
							new ArrayList<Node>()));
				}
				polygons.get(tilePoint).getNodes()
						.add(new Node(null, point.x, point.y, 1));

			}

			for (Map.Entry<Point2D, OsmPolygon> entry : polygons.entrySet()) {
				result.add(entry.getValue());
			}
		}
		return result;

	}

	public void simplifyPolygon() {
		if (this.polygon != null) {
			LinearRing2D result = GeomUtils.simplifyPolygon(this.polygon);
			this.polygon = result;
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public List<Node> getNodes() {
		return nodes;
	}

	public Double getMinVectorSize() {
		Double result = null;
		if (this.getPolygon() != null) {
			Double[] vectors = GeomUtils.computeExtremeVectors(polygon);
			result = vectors[0];
		}
		return result;

	}

	public Double getMaxVectorSize() {
		Double result = null;
		if (this.getPolygon() != null) {
			Double[] vectors = GeomUtils.computeExtremeVectors(polygon);
			result = vectors[1];
		}
		return result;
	}

	public Double getArea() {
		if (nodes.size() > 2) {
			this.area = polygon.getArea();
		} else {
			this.area = 0D;
		}
		return area;
	}

	public Boolean isSimplePolygon() {
		Boolean result = false;
		if (this.getPolygon() != null) {
			result = (polygon.getEdges().size() == 4 && GeomUtils
					.areParallelsSegmentsIdentics(polygon));
		}

		return result;
	}

	public LinearRing2D getPolygon() {
		if (this.polygon == null) {
			initPolygon();
		}
		return polygon;
	}

	public void setPolygon(LinearRing2D polygon) {
		this.polygon = polygon;
	}

	public Point2D getCenter() {
		if (this.nodes.size() > 1) {
			this.center = GeomUtils.getPolygonCenter(polygon);
		} else {
			this.center = new Point2D(nodes.get(0).lat, nodes.get(0).lon);
		}
		return center;
	}

	public Integer getHeight() {
		return height;
	}

	public Color getRoofColor() {
		return OsmUtils.getRoofColorFromTags(this.tags);
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

}
