package team.left.shoppingmall.global;

import java.util.ArrayList;
import java.util.List;

public class PaginationTool {
	
	public static <T>List<T> getPaginatedList(List<T> list, int pageSize, int index){
		
		int start = (index - 1) * pageSize;
		int end = index * pageSize;
		if(end > list.size()) end = list.size();
		
		List<T> result = new ArrayList<>();
		for(int i = start; i < end; i++) {
			result.add(list.get(i));
		}
		
		return result;
	}
}
