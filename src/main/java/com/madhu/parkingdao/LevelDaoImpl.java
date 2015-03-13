package com.madhu.parkingdao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.madhu.parkingBeans.LevelInfo;
import com.madhu.parkingBeans.SlotInfo;
import com.madhu.parkingBeans.VehicleType;

@Repository
@SuppressWarnings("unchecked")
public class LevelDaoImpl extends BaseHibernateDao<SlotInfo, Integer> implements
		LevelDao {

	private static final Logger logger = LoggerFactory
			.getLogger(LevelDaoImpl.class);

	public LevelDaoImpl() {
		super(SlotInfo.class);
	}
	
	private void generateSlots(int levelNumber, int numberOfSlots, VehicleType vehicleType,String prefix) {
		
		for (int i = 1; i <= numberOfSlots; i++) {
			SlotInfo slotObj = new SlotInfo();
			slotObj.setLevelNumber(levelNumber);
			slotObj.setSlotName(prefix + "-L" + levelNumber+ "-S" +i);
			slotObj.setVehicleType(vehicleType);

			makePersistant(slotObj);
		}
	}

	
	@Override
	public void createLevel(LevelInfo levelInfo) {

		generateSlots(levelInfo.getLevelNumber(), levelInfo.getCarSlotsCount(),VehicleType.Car, "SC");
		generateSlots(levelInfo.getLevelNumber(), levelInfo.getBikeSlotsCount(),VehicleType.Bike, "SB");
	}

	
	@Override
	public List<LevelInfo> getLevelObjList() {
		

		String hqlLoadLevelObjs = "select slot_mid.ln as levelNumber, max(CASE WHEN slot_mid.vt='Car' THEN slot_mid.vCnt ELSE 0 END) as carSlotsCount, max(CASE WHEN slot_mid.vt='Bike' THEN slot_mid.vCnt ELSE 0 END) as bikeSlotsCount"
				+ " from (select levelnumber ln, vehicletype vt, count(vehicletype) vCnt from slotsinfo group by levelnumber, vehicletype) slot_mid group by slot_mid.ln";

		List<LevelInfo> levelObjList = getSession()
				.createSQLQuery(hqlLoadLevelObjs)
				.addScalar("levelNumber", StandardBasicTypes.INTEGER)
				.addScalar("carSlotsCount", StandardBasicTypes.INTEGER)
				.addScalar("bikeSlotsCount", StandardBasicTypes.INTEGER)
				.setResultTransformer(
						new AliasToBeanResultTransformer(LevelInfo.class))
				.list();

		
		return levelObjList;
	}

}
