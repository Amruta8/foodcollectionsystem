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
	/*
	 * Output: status and ResourcesAllocated Info : This method analyse the
	 * requested quantity and allocate the resource to it
	 */
	public ResourceAllocationBean resourceAllocation(
			String requestedQuentityParam, Map<String, Integer> resWithinGeo,
			Map<String, Integer> resOutsideGeo) {
		String status = FCSConstants.ALLOCATION_STATUS_NA;
		List<String> allocatedResources = new ArrayList<String>();
		Integer requestedQuentity = Integer.parseInt(requestedQuentityParam);

		/* Sorted Within Geo resources */
		ValueComparator bvcWithinGeo = new ValueComparator(resWithinGeo);
		TreeMap<String, Integer> sorted_mapWithinGeo = new TreeMap(bvcWithinGeo);
		sorted_mapWithinGeo.putAll(resWithinGeo);

		/* Sorted outside Geo resources */
		ValueComparator bvcOutsideGeo = new ValueComparator(resOutsideGeo);
		TreeMap<String, Integer> sorted_mapOutsideGeo = new TreeMap(
				bvcOutsideGeo);
		sorted_mapOutsideGeo.putAll(resOutsideGeo);
		int totalWGcapacity = 0;

		for (Map.Entry<String, Integer> entry : sorted_mapWithinGeo.entrySet()) {
			Integer value = entry.getValue();
			totalWGcapacity = totalWGcapacity + value;
		}
		if (totalWGcapacity >= requestedQuentity)
			while (totalWGcapacity <= 0) {
				boolean isFound = false;
				String lastKey = "";

				for (Map.Entry<String, Integer> entry : sorted_mapWithinGeo
						.entrySet()) {
					String key = entry.getKey();
					Integer value = entry.getValue();
					if (requestedQuentity < value) {
						isFound = true;
						allocatedResources.add(key);
						// sorted_mapWithinGeo.remove(key);
						totalWGcapacity = totalWGcapacity - value;
						System.out.println("inside GEO AllocatedResource is :"
								+ key + " => " + value);
						status = FCSConstants.ALLOCATION_STATUS_ALLOCATED_WG;
						lastKey = key;
						break;
					}
				}
				if (!isFound) {
					allocatedResources.add(lastKey);
					// sorted_mapWithinGeo.remove(key);
					totalWGcapacity = totalWGcapacity
							- sorted_mapWithinGeo.get(lastKey);
					System.out
							.println("inside GEO AllocatedResource last element is :"
									+ lastKey
									+ " => "
									+ sorted_mapOutsideGeo.get(lastKey));
					status = FCSConstants.ALLOCATION_STATUS_ALLOCATED_WG;
				}
			}
		
		/*Resources of Outside Geo Fances */
		if (status.equals(FCSConstants.ALLOCATION_STATUS_NA)) {
			int totalOGcapacity = 0;
			for (Map.Entry<String, Integer> entry : sorted_mapOutsideGeo.entrySet()) {
				Integer value = entry.getValue();
				totalOGcapacity = totalOGcapacity + value;
			}
			if (totalOGcapacity >= requestedQuentity)
				while (totalOGcapacity <= 0) {
					boolean isFound = false;
					String lastKey = "";
	
					for (Map.Entry<String, Integer> entry : sorted_mapOutsideGeo
							.entrySet()) {
						String key = entry.getKey();
						Integer value = entry.getValue();
						if (requestedQuentity < value) {
							isFound = true;
							allocatedResources.add(key);
							// sorted_mapOutsideGeo.remove(key);
							totalOGcapacity = totalOGcapacity - value;
							System.out.println("inside GEO AllocatedResource is :"
									+ key + " => " + value);
							status = FCSConstants.ALLOCATION_STATUS_ALLOCATED_OG;
							lastKey = key;
							break;
						}
					}
					if (!isFound) {
						allocatedResources.add(lastKey);
						// sorted_mapOutsideGeo.remove(key);
						totalOGcapacity = totalOGcapacity
								- sorted_mapOutsideGeo.get(lastKey);
						System.out
								.println("inside GEO AllocatedResource last element is :"
										+ lastKey
										+ " => "
										+ sorted_mapOutsideGeo.get(lastKey));
						status = FCSConstants.ALLOCATION_STATUS_ALLOCATED_OG;
						sorted_mapOutsideGeo.remove(lastKey);
					}
				}
		}
		if (!status.equals(FCSConstants.ALLOCATION_STATUS_NA)) {
			return new ResourceAllocationBean(status, allocatedResources);
		}
		return null;
	}

}
