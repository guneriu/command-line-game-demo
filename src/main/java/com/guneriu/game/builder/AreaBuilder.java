package com.guneriu.game.builder;

import com.guneriu.game.model.Area;
import com.guneriu.game.model.Direction;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
 * Builder class for {@code Customer} 
 *
 */
public class AreaBuilder implements Builder<Area> {

	/**
	 * creates {@code Order} from string array
	 * 
	 * @param values string array containing values
	 * @return {@code Order}
	 */
	@Override
	public Area build(String[] values) {
		Map<String, Area> areaMap = new LinkedHashMap<>();

		Integer id = Integer.parseInt(values[0]);
		Area area = getOrCreate(areaMap, values[1]);

		//TODO collect stories

		if (values.length > 3) {
			for (int i = 3; i < values.length; i+=2) {
				Direction direction = Direction.fromName(values[i]);
				Area linkedArea = getOrCreate(areaMap, values[i + 1]);
				area.connect(linkedArea, direction);
			}
		}

		return new Area("hede");
	}

	private Area getOrCreate(Map<String, Area> areaMap, String name) {
		return areaMap.putIfAbsent(name, new Area(name));
	}
	
}
