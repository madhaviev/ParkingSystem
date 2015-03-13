package com.madhu.parking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/")
public class ParkingController {
	
	private static final Logger logger = LoggerFactory.getLogger(ParkingController.class);
	
	
	/**
	 * Mapper to show the main html page
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
	public String doNothing() {
		return "main";
	}

}
