
package xwgl.core.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xwgl.common.service.SimpleCurdService;
import xwgl.core.project.entity.FrontUser;
import xwgl.core.project.entity.HotelOrder;
import xwgl.core.project.repository.HotelOrderRepository;
/***
 * 
 * @author qq:263608237
 *
 */
@Service
public class HotelOrderService  extends SimpleCurdService< HotelOrder, Long> {
	
	@Autowired
	private HotelOrderRepository hotelOrderRepository;
	
	public List<HotelOrder> findByUser(FrontUser user){
		return this.hotelOrderRepository.findByUser(user);
	}
}