package xwgl.core.project.repository;
import java.util.List;

import xwgl.common.repository.SimpleCurdRepository;
import xwgl.core.project.entity.FrontUser;
import xwgl.core.project.entity.HotelOrder;

public interface HotelOrderRepository  extends SimpleCurdRepository< HotelOrder ,Long>{
	
	public List<HotelOrder> findByUser(FrontUser user);
}

