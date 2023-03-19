package com.sifan.livegoods.pojo;

import java.util.Map;

public class HouseType4Search {
        public static String getHouseType4Search(Map<String, String> info,String houseType) {
        // "楼层 | 几室几厅 - 面积"
        return info.get("level") + " | " + info.get("type") + " - " + houseType;
    }
}
