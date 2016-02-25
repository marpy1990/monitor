package sjtu.cit.monitor.web.home.module.screen.rpc;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.citrus.turbine.dataresolver.Param;
import com.alibaba.fastjson.JSON;

public class Chart {

	List<Line> doGetPoints(@Param("seriesIds") String seriesIdsJson,
			@Param("from") Long from, @Param("to") Long to) {
		List<Integer> seriesIds = JSON.parseArray(seriesIdsJson, Integer.class);
		return null;
	}

}

class Series {

}

interface Line extends List<Point> {
}

class Point {

	private long x;

	private double y;

	public Point() {
		super();
	}

	public Point(long x, double y) {
		this.x = x;
		this.y = y;
	}

	public long getX() {
		return x;
	}

	public void setX(long x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

}