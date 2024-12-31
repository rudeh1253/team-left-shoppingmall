package team.left.shoppingmall.global;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MapUtil {

    public static <K, V> Map<K, V> of(K[] ks, V[] vs) {
        if (ks.length != vs.length) {
            throw new IllegalArgumentException("key 배열과 value 배열의 길이가 일치하지 않음. ks.length=" + ks.length +", vs.length=" + vs.length);
        }
        
        Map<K, V> map = new HashMap<>();
        for (int i = 0; i < ks.length; i++) {
            map.put(ks[i], vs[i]);
        }
        return Collections.unmodifiableMap(map);
    }
    
    public static <K, V> Map<K, V> of(K k, V v) {
        Map<K, V> map = new HashMap<>();
        map.put(k, v);
        return Collections.unmodifiableMap(map);
    }
    
    public static Map<Integer, Object> getParamsOf(Object... vals) {
        Integer[] nums = new Integer[vals.length];
        for (int i = 0; i < vals.length; i++) {
            int num = i + 1;
            nums[i] = num;
        }
        return of(nums, vals);
    }
    
    public static <K, V> Map<K, V> empty() {
        return Collections.unmodifiableMap(new HashMap<>());
    }
}
