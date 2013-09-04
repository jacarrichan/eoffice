package com.palmelf.core.jbpm.jpdl;

import java.awt.Point;
import java.awt.Rectangle;

public class GeometryUtils {
	/**
	 * 获得直线(x1,y1)-(x2,y2)的斜率
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return
	 */
	public static double getSlope(int x1, int y1, int x2, int y2) {
		return ((double) y2 - y1) / (x2 - x1);
	}

	/**
	 * 获得直线(x1,y1)-(x2,y2)的y轴截距
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return
	 */
	public static double getYIntercep(int x1, int y1, int x2, int y2) {
		return y1 - x1 * getSlope(x1, y1, x2, y2);
	}

	/**
	 * 获得矩形的中点
	 * 
	 * @param rect
	 * @return
	 */
	public static Point getRectangleCenter(Rectangle rect) {
		return new Point((int) rect.getCenterX(), (int) rect.getCenterY());
	}

	/**
	 * 获得矩形中心p0与p1的线段和矩形的交点
	 * 
	 * @param rectangle
	 * @param p1
	 * @return
	 */
	public static Point getRectangleLineCrossPoint(Rectangle rectangle, Point p1, int grow) {
		final Rectangle rect = rectangle.getBounds();
		rect.grow(grow, grow);
		final Point p0 = GeometryUtils.getRectangleCenter(rect);

		if (p1.x == p0.x) {
			if (p1.y < p0.y) {
				return new Point(p0.x, rect.y);
			}
			return new Point(p0.x, rect.y + rect.height);
		}

		if (p1.y == p0.y) {
			if (p1.x < p0.x) {
				return new Point(rect.x, p0.y);
			}
			return new Point(rect.x + rect.width, p0.y);
		}

		final double slope = GeometryUtils.getSlope(p0.x, p0.y, rect.x, rect.y);
		final double slopeLine = GeometryUtils.getSlope(p0.x, p0.y, p1.x, p1.y);
		final double yIntercep = GeometryUtils.getYIntercep(p0.x, p0.y, p1.x, p1.y);

		if (Math.abs(slopeLine) > slope - 1e-2) {
			if (p1.y < rect.y) {
				return new Point((int) ((rect.y - yIntercep) / slopeLine), rect.y);
			} else {
				return new Point((int) ((rect.y + rect.height - yIntercep) / slopeLine), rect.y + rect.height);
			}
		}
		if (p1.x < rect.x) {
			return new Point(rect.x, (int) (slopeLine * rect.x + yIntercep));
		} else {
			return new Point(rect.x + rect.width, (int) (slopeLine * (rect.x + rect.width) + yIntercep));
		}
	}
}
