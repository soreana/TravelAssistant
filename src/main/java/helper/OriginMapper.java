package helper;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by sinakashipazha on 3/1/2017 AD.
 */
public class OriginMapper {
	private static Map<String,String> map;

    static {
    	map = new HashMap<>();
        map.put("ADU","اردبیل");
        map.put("OMH","ارومیه");
		map.put("IFN","اصفهان");
		map.put("AWZ","اهواز");
		map.put("THR","تهران");

		map.put("TBZ","تبریز");
		map.put("BND","بندرعباس");
		map.put("RAS","رشت");
		map.put("ZAH","زاهدان");
		map.put("SRY","ساری");

		map.put("SYZ","شیراز");
		map.put("KER","کرمان");
		map.put("GBT","گرگان");
		map.put("AZD","یزد");
		map.put("MHD","مشهد");

	}

	public static Set<String> getKeySet(){
    	return map.keySet();
	}

	public static String getNameForAbbreviate(String key){
    	return map.get(key);
	}

}
