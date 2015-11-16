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

interface Line extends List<Point> {
}

class Point {

	private Long x;

	private Number y;

	public Long getX() {
		return x;
	}

	public void setX(Long x) {
		this.x = x;
	}

	public Number getY() {
		return y;
	}

	public void setY(Number y) {
		this.y = y;
	}

}