package fc.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.constant.FCSConstants;

import fcs.bean.ResourceAllocationBean;

/**
 * @author PRASHANT
 *
 */
public class FCSUtil {
	/*Output: status and ResourcesAllocated
	 * Info : This method analyse the requested quantity and allocate the resource to it
	 * 
	 * */
	public ResourceAllocationBean resourceAllocation(String requestedQuentity, Map<String, Integer> resWithinGeo, Map<String, Integer> resOutsideGeo){
		String status = FCSConstants.ALLOCATION_STATUS_NA;
		List<String> allocatedResources = new ArrayList<String>();
		
		
		/*Sorted Within Geo resources*/
		ValueComparator bvcWithinGeo = new ValueComparator(resWithinGeo);
        TreeMap<String, Integer> sorted_mapWithinGeo = new TreeMap(bvcWithinGeo); 
        sorted_mapWithinGeo.putAll(resWithinGeo);
        
        /*Sorted outside Geo resources*/
        ValueComparator bvcOutsideGeo = new ValueComparator(resOutsideGeo);
        TreeMap<String, Integer> sorted_mapOutsideGeo = new TreeMap(bvcOutsideGeo);
        sorted_mapOutsideGeo.putAll(resOutsideGeo);
        
        for(Map.Entry<String,Integer> entry : sorted_mapWithinGeo.entrySet()) {
        	  String key = entry.getKey();
        	  Integer value = entry.getValue();
        	  if(Integer.parseInt(requestedQuentity) <value){
        		  allocatedResources.add(key);
        		  System.out.println("inside GEO AllocatedResource is :"+key + " => " + value);
        		  status = FCSConstants.ALLOCATION_STATUS_ALLOCATED_WG;
        	  }        	  
        }
        
        
        if (status.equals(FCSConstants.ALLOCATION_STATUS_NA)) {
			for (Map.Entry<String, Integer> entry : sorted_mapOutsideGeo.entrySet()) {
				String key = entry.getKey();
				Integer value = entry.getValue();
				if (Integer.parseInt(requestedQuentity) < value) {
					allocatedResources.add(key);
					System.out.println("outside GEO AllocatedResource is :" + key + " => "
							+ value);
					status = FCSConstants.ALLOCATION_STATUS_ALLOCATED_OG;
				}
			}
		}
        
        if(status.equals(FCSConstants.ALLOCATION_STATUS_NA)){
        	return new ResourceAllocationBean(status, allocatedResources);
        }
		return null;
	}
	
}
