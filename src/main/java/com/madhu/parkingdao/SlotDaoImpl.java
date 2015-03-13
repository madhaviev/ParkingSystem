package com.madhu.parkingdao;

import java.sql.Types;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.internal.TypeLocatorImpl;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.type.EnumType;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import org.hibernate.type.TypeResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.madhu.parkingBeans.SlotInfo;
import com.madhu.parkingBeans.SlotInfoDTO;
import com.madhu.parkingBeans.VehicleType;

@SuppressWarnings("unchecked")
@Repository
public class SlotDaoImpl extends BaseHibernateDao<SlotInfo, Integer> implements SlotDao {

	public static final Logger logger = Logger.getLogger(SlotDaoImpl.class);
	
	public SlotDaoImpl() {
		super(SlotInfo.class);
	}

	BookingDao bookingDao;
	
	@Autowired
	public void setBookingDao(BookingDao bookingDao) {
		this.bookingDao = bookingDao;
	}
	
	@Override
	public List<SlotInfoDTO> getSlotsList(int levelNumber) {

				
		String slotListQuery = "select s.id slotId, s.slotName, s.levelNumber, s.vehicleType,  (CASE WHEN b.id is null THEN 1 ELSE 0 END) availability "
				+ "from slotsinfo s left outer join bookinginfo b on s.id = b.slotid  and b.RELEASETIME is  null where s.LEVELNUMBER = :levelNumber";
		
		Properties params = new Properties();
		params.put("enumClass", "com.madhu.parkingBeans.VehicleType");
		params.put("type", Types.VARCHAR);
		
		Type vehicleEnumType = new TypeLocatorImpl(new TypeResolver()).custom(EnumType.class, params);
		
		List<SlotInfoDTO> slotInfoDtoObjList  = getSession().createSQLQuery(slotListQuery)
											      .addScalar("slotId", StandardBasicTypes.INTEGER)
											      .addScalar("slotName", StandardBasicTypes.STRING)
											      .addScalar("levelNumber", StandardBasicTypes.INTEGER)
											      .addScalar("vehicleType",vehicleEnumType)
											      .addScalar("availability", StandardBasicTypes.BOOLEAN)
											      .setResultTransformer(new AliasToBeanResultTransformer(SlotInfoDTO.class))
											      .setParameter("levelNumber", levelNumber)
											      .list();
		
		return slotInfoDtoObjList;
		
	}

	
	@Override
	public List<SlotInfo> getFreeSlotsInAllLevels(VehicleType vehicleType) {
		
		String freeSlotsQuery = "select s.* from slotsinfo s left outer join bookinginfo b on s.id = b.slotid  and b.RELEASETIME is  null"
								+ " where s.VEHICLETYPE=:vehicleType and  b.id is null ";
		
		SQLQuery createSQLQuery = getSession().createSQLQuery(freeSlotsQuery);
		createSQLQuery.addEntity(SlotInfo.class);
		createSQLQuery.setParameter("vehicleType", vehicleType.name());
		return createSQLQuery.list();
	}

	
	@Override
	public void deleteSlot(int slotId) {
		
		 logger.info("Inside deleteSlot");
		 SlotInfo slotInfo = findById(slotId);
		 bookingDao.deleteBookingSlot(slotInfo);
		 
		 //SlotInfo slotInfo = findById(slotId);
		 logger.info("deleteSlot " + slotInfo);
		 makeTransient(slotInfo);
		
	}
	
	
}
