package com.mujeeb.mosquemanager.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mujeeb.mosquemanager.beans.BaseException;
import com.mujeeb.mosquemanager.beans.DateBean;
import com.mujeeb.mosquemanager.beans.MasjidBean;
import com.mujeeb.mosquemanager.beans.NamazBean;
import com.mujeeb.mosquemanager.beans.OccasionBean;
import com.mujeeb.mosquemanager.beans.TempreatureBean;
import com.mujeeb.mosquemanager.beans.request.BaseRequestBean;
import com.mujeeb.mosquemanager.beans.request.NamazTimeUpdateRequestBean;
import com.mujeeb.mosquemanager.beans.request.OccasionRequestBean;
import com.mujeeb.mosquemanager.beans.response.BaseResponseBean;
import com.mujeeb.mosquemanager.mappers.MasjidBeanMapper;
import com.mujeeb.mosquemanager.mappers.NamazTimeMapper;
import com.mujeeb.mosquemanager.mappers.OccasionMapper;
import com.mujeeb.mosquemanager.mappers.QuranAyatMapper;
import com.mujeeb.mosquemanager.util.DateUtil;
import com.mujeeb.mosquemanager.util.IslamicUtil;
import com.mujeeb.mosquemanager.util.NoticeMapper;
import com.mujeeb.mosquemanager.util.WeatherUtil;

@Controller
public class MosqueManagerController{
 
   public static final String KEY_HIJRI_ADJUSTMENT = "HIJRI_ADJUSTMENT"; 
   public static final String KEY_MASJID_ID = "MASJID_ID";
   public static final String KEY_MASJID_NAME = "MASJID_NAME";
	
   @Autowired
   private JdbcTemplate jdbcTemplate;
   
   /**
    * Service to Authenticate
    * 
    * @param registerBean
    * @return BaseResponseBean
    */
   @RequestMapping(value = "apiAuthenticate", method = RequestMethod.POST, consumes ="application/json", produces="application/json")
   @ResponseBody
   public BaseResponseBean apiAuthenticate(@RequestBody BaseRequestBean bean) {
	    
	    try {
	    	
	    	authenticateUser(bean.getUserId(), bean.getPassword());
		   	
		   	return new BaseResponseBean(0, "Authentication Successful.");
	    	
	    } catch(BaseException b) {
	    	
	    	return new BaseResponseBean(b.getReasonCode()); 
	    }
   }
   
   /**
    * Service to Update Namaz time
    * 
    * @param registerBean
    * @return BaseResponseBean
    */
   @RequestMapping(value = "apiUpdateNamazTimes", method = RequestMethod.POST, consumes ="application/json", produces="application/json")
   @ResponseBody
   public BaseResponseBean apiUpdateNamazTimes(@RequestBody NamazTimeUpdateRequestBean bean) {
	    
	    try {
	    	
	    	authenticateUser(bean.getUserId(), bean.getPassword());
	    	
	    	boolean result = updateNamazTime(bean.getUserId(), bean.getName(), bean.getTime());
		   	
	    	if(result) {
	    		
	    		return new BaseResponseBean(0, "Namaz time updated successfully.");
	    	} else {
	    		
	    		return new BaseResponseBean(2);
	    	}
	    	
	    } catch(BaseException b) {
	    	
	    	return new BaseResponseBean(b.getReasonCode()); 
	    } catch(Throwable ex) {
	    	
	    	ex.printStackTrace();
	    	return new BaseResponseBean(-1); 
	    }
   }
   
   /**
    * Service to Update Refresh required flag
    * 
    * @param registerBean
    * @return BaseResponseBean
    */
   @RequestMapping(value = "apiUpdateRefreshRequired", method = RequestMethod.POST, consumes ="application/json", produces="application/json")
   @ResponseBody
   public BaseResponseBean apiUpdateRefreshRequired(@RequestBody BaseRequestBean bean) {
	    
	    try {
	    	
	    	authenticateUser(bean.getUserId(), bean.getPassword());
		   	
	    	boolean result = updateRefreshRequired(bean.getUserId(), true);
	    	
	    	if(result) {
	    		return new BaseResponseBean(0, "Request for Refresh page submitted Successfully.");
	    		
	    	} else {
	    		return new BaseResponseBean(3);
	    	}
	    	
	    } catch(BaseException b) {
	    	
	    	return new BaseResponseBean(b.getReasonCode()); 
	    }
   }
   
   /**
    * Service to Delete Occasion
    * 
    * @param registerBean
    * @return BaseResponseBean
    */
   @RequestMapping(value = "apiDeleteOccasion", method = RequestMethod.POST, consumes ="application/json", produces="application/json")
   @ResponseBody
   public BaseResponseBean apiDeleteOccasion(@RequestBody OccasionRequestBean bean) {
	   
	   try {
		   	authenticateUser(bean.getUserId(), bean.getPassword());
		   
		   	boolean result = deleteOccasion(bean.getUserId(), bean.getId());
		   
	    	if(result) {
	    		return new BaseResponseBean(0, "Occasion was Deleted Successfully.");
	    		
	    	} else {
	    		return new BaseResponseBean(4);
	    	}
	    	
	    } catch(BaseException b) {
	    	
	    	return new BaseResponseBean(b.getReasonCode()); 
	    }
   }
   
   /**
    * Service to Add Occasion
    * 
    * @param registerBean
    * @return BaseResponseBean
    */
   @RequestMapping(value = "apiAddOccasion", method = RequestMethod.POST, consumes ="application/json", produces="application/json")
   @ResponseBody
   public BaseResponseBean apiAddOccasion(@RequestBody OccasionRequestBean bean) {
	   
	   try {
		   	authenticateUser(bean.getUserId(), bean.getPassword());
		   	
		   	Date date = DateUtil.parseDate(bean.getDate());
		   
		   	boolean result = addOccasion(bean.getUserId(), date, bean.getDescription());
		   
	    	if(result) {
	    		return new BaseResponseBean(0, "Occasion was Added Successfully.");
	    		
	    	} else {
	    		return new BaseResponseBean(5);
	    	}
	    	
	    } catch(BaseException b) {
	    	
	    	return new BaseResponseBean(b.getReasonCode()); 
	    } catch(Throwable b) {
	    	
	    	return new BaseResponseBean(6); 
	    }
   }

   @RequestMapping(value = "loginPage", method = RequestMethod.GET)
   public ModelAndView loginPage() {
	   
		return new ModelAndView("login");
   }
   
   @RequestMapping(value = "login", method = RequestMethod.POST)
   public ModelAndView login(ModelMap model, HttpServletRequest request, @RequestParam String masjidId, @RequestParam String password) {
	   
	    
	    // Make sure ID contains a valid integer
	    try {
	    	Integer.parseInt(masjidId);
	    }catch(Exception ex) {
	    	return loginErrorPage(model, "Invalid Masjid ID or Password.");
	    }
	    
	    if(password == null || password.isEmpty()) {
	    	return loginErrorPage(model, "Password cannot be empty.");
	    }
	    
	   	List<MasjidBean> masjidBeans = null;
	   	try {
	   		masjidBeans = jdbcTemplate.query("select * from masjid_master where masjid_id = " + masjidId, new MasjidBeanMapper());
		} catch (Exception e) {
			e.printStackTrace();
			return loginErrorPage(model, "An Error occured while performing requested operation.");
		}
	   	
	   	if(masjidBeans == null || masjidBeans.size() < 1) {
	   		return loginErrorPage(model, "Invalid Masjid ID or Password.");
	   	} else {
	   		if(password.equals(masjidBeans.get(0).getPassword())) {
	   			request.getSession().setAttribute(KEY_MASJID_ID, masjidId);
	   			request.getSession().setAttribute(KEY_MASJID_NAME, masjidBeans.get(0).getName());
	   			return new ModelAndView("redirect:update.jsp");
	   		}
	   	}
	   	
	   	return loginErrorPage(model, "Please Login to continue.");
   }
   
   private String getMasjidId(HttpServletRequest request) {
	   return "" + request.getSession().getAttribute(KEY_MASJID_ID);
   }
   
   @RequestMapping(value = "logout", method = RequestMethod.GET)
   public ModelAndView login(HttpServletRequest request) {
	   
	    request.getSession().invalidate();
	   	
	   	return new ModelAndView("login");
   }
   	
   private ModelAndView loginErrorPage(ModelMap model, String msg) {

	   	model.put("error", msg);	
		
		return new ModelAndView("login","model",model);
   }
   
   @RequestMapping(value = "update", method = RequestMethod.GET)
   public ModelAndView updatePage(HttpServletRequest request) {
	   
	   	return new ModelAndView("../update");
   }

   @RequestMapping(value = "getNamazTimes", method = RequestMethod.GET, produces = "application/json")
   @ResponseBody
   public Map<String,String> getNamazTimes(@RequestParam String id) {
	   
	    // Make sure ID contains a valid integer
	    try {
	    	Integer.parseInt(id);
	    }catch(Exception ex) {
	    	id = "0";
	    }
	    
	   	Map<String,String> namazTimes = null;
	   	try {
	   		namazTimes = convertToMap(jdbcTemplate.query("select * from namaz_times where masjid_id = " + id, new NamazTimeMapper()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	   	
	   	Map<String,String> hijriNamazTimes = IslamicUtil.getPrayerTimes();
	   	namazTimes.put("SEHERI", hijriNamazTimes.get("Fajr"));
	   	namazTimes.put("ISHRAQ", hijriNamazTimes.get("Ishraq"));
	   	namazTimes.put("MAGRIB", hijriNamazTimes.get("Maghrib"));
	   	namazTimes.put("IFTAR", hijriNamazTimes.get("Iftar"));
	   	
	   	//RamzanTimesBean ramzanTimes = getRamzanTimes();
	   	//namazTimes.put("SEHERI", ramzanTimes.getSeheriTime());
	   	//namazTimes.put("IFTAR", ramzanTimes.getIftarTime());
	   
	   	return namazTimes;
   }

  @RequestMapping(value = "getCurrentTempreature", method = RequestMethod.GET, produces = "application/json")
   @ResponseBody
   public TempreatureBean getCurrentTempreature(@RequestParam String id) {
	   
	  	// Make sure ID contains a valid integer
	    try {
	    	Integer.parseInt(id);
	    }catch(Exception ex) {
	    	id = "0";
	    }
	    
	   	try {
	   		MasjidBean location = jdbcTemplate.query("select * from masjid_master where masjid_id = " + id, new MasjidBeanMapper()).get(0);
	   		System.out.println(location);
			return WeatherUtil.getCurrentTempreature(location.getLatitude(), location.getLongitude());
		} catch (Exception e) {
			e.printStackTrace();
			return new TempreatureBean("25", "cyan");
		}
   }
   
   @RequestMapping(value = "getQuranAyats", method = RequestMethod.GET, produces = "application/json")
   @ResponseBody
   public List<String> getQuranAyats() {
	   
	   	List<String> quratAyats = null;
	   	try {
	   		quratAyats = jdbcTemplate.query("select * from quran_ayats", new QuranAyatMapper());
		} catch (Exception e) {
			e.printStackTrace();
		}
	   
	   	return quratAyats;
   }
   
   @RequestMapping(value = "getHijriDate", method = RequestMethod.GET, produces = "application/json")
   @ResponseBody
   public DateBean getHijriDate(@RequestParam String id) {
	   
	   // Make sure ID contains a valid integer
       try {
    	   Integer.parseInt(id);
       }catch(Exception ex) {
    	   id = "0";
       }
	   
       int adjustment = getHijriAdjustment(id);
	   DateBean date = IslamicUtil.getHijriDate(adjustment);
	   /*if(adjustment < 0) {
	   		date.setDate("" + (Integer.parseInt(date.getDate()) - adjustment));
   	   } else if(adjustment > 0) {
	   		date.setDate("" + (Integer.parseInt(date.getDate()) + adjustment));
   	   }*/
	   String occasion = getCurrentOccasion(id);
	   if(occasion != null) {
		   
		   date.setYear(occasion);
		   date.setMonth("OCCASION");
	   }

	   return date;
   }
   
   @RequestMapping(value = "getNotices", method = RequestMethod.GET, produces = "application/json")
   @ResponseBody
   public List<String> getNotices(@RequestParam String id) {
	
		// Make sure ID contains a valid integer
    	try {
    		Integer.parseInt(id);
    	}catch(Exception ex) {
    		id = "0";
    	}
	   
	   	List<String> notices = null;
	   	try {
	   		notices = jdbcTemplate.query("select * from notices where masjid_id = " + id, new NoticeMapper());
		} catch (Exception e) {
			e.printStackTrace();
		}
	   
	   	return notices;
   }
   
   @RequestMapping(value = "updateNamazTimes", method = RequestMethod.POST)
   @ResponseBody
   public String updateNamazTimes(HttpServletRequest request, @RequestParam String name, @RequestParam String time) {
	   
	    String masjidId = getMasjidId(request);
	   	try {
	   		boolean result = updateNamazTime(masjidId, name, time);
	   		if(result) {
	   			return "Namaz time updated Successfully.";
	   			
	   		} else {
	   		
	   			return "Operation Failed";
	   		}
	   		
		} catch (Throwable e) {
			e.printStackTrace();
			return "Operation Failed";
		}	
   }
   
   @RequestMapping(value = "updateNotices", method = RequestMethod.POST)
   @ResponseBody
   public String updateNotices(HttpServletRequest request, @RequestParam String notices) {
	   
	    String masjidId = getMasjidId(request);
	   
	    String[] noticesArray = notices.split("\n");
	   	try {
	   		jdbcTemplate.update("truncate table notices");
	   		for(int i=0; i<noticesArray.length; i++) {
	   			jdbcTemplate.update("insert into notices values(?, ?, ?)", i, noticesArray[i], masjidId);
	   		}
		} catch (Exception e) {
			e.printStackTrace();
		}
	   
	   	return "Notices updated Successfully.";
   }
   
   @RequestMapping(value = "updateQuranAyats", method = RequestMethod.POST)
   @ResponseBody
   public String updateQuranAyats(HttpServletRequest request, @RequestParam String ayats) {
	   
	    String strMasjidId = getMasjidId(request);
	    
	    int masjidId = -1;
	    try {
	    	masjidId = Integer.parseInt(strMasjidId);
	    	
	    } catch(Exception ex) {
	    	/*Do Nothing*/
	    }
	    if(masjidId != 0) {
	    	return "You are not Authorized to make these changes.";
	    }
	   
	    String[] ayatsArray = ayats.split("\n");
	   	try {
	   		jdbcTemplate.update("truncate table quran_ayats");
	   		for(int i=0; i<ayatsArray.length; i++) {
	   			jdbcTemplate.update("insert into quran_ayats values(?, ?)", i, ayatsArray[i]);
	   		}
		} catch (Exception e) {
			e.printStackTrace();
		}
	   
	   	return "Quran Ayats updated Successfully.";
   }
   
   @RequestMapping(value = "getOccasions", method = RequestMethod.GET, produces = "application/json")
   @ResponseBody
   public List<OccasionBean> getOccasions(@RequestParam String id) {
	   
	    // Make sure ID contains a valid integer
	    try {
	    	Integer.parseInt(id);
	    }catch(Exception ex) {
	    	id = "0";
	    }
	    
	   	try {
	   		List<OccasionBean> occasions =  jdbcTemplate.query("select * from occasions where masjid_id = " + id, new OccasionMapper());
	   		return occasions; 
	   		
		} catch (Exception e) {
			e.printStackTrace();
		}
	   	
	   	return new ArrayList<OccasionBean>();
   }
   
   @RequestMapping(value = "addOccasion", method = RequestMethod.POST)
   @ResponseBody
   public String addOccasion(HttpServletRequest request, @RequestParam Integer date, @RequestParam Integer month, @RequestParam String description) {
	   
	    Calendar occasionDate = new GregorianCalendar();
	    occasionDate.set(Calendar.DATE, date);
	    occasionDate.set(Calendar.MONTH, month-1);
	    
	    String masjidId = getMasjidId(request);
	    
	   	boolean result = addOccasion(masjidId, occasionDate.getTime(), description);
	   	
	   	if(result) {
	   		return "Occasion added successfully.";
	   	} else {
	   		return "Failed";
	   	}
   }

   @RequestMapping(value = "updateHijriAdjustment", method = RequestMethod.POST)
   @ResponseBody
   public String updateHijriAdjustment(HttpServletRequest request, @RequestParam String adjustment) {
	   
	   String masjidId = getMasjidId(request);
	   
	   try {
	   		jdbcTemplate.update("update namaz_times set TIME = ? where MASJID_ID = ? and NAME = ?", adjustment, masjidId, KEY_HIJRI_ADJUSTMENT);
		} catch (Exception e) {
			e.printStackTrace();
		}
	   
	   	return "Hijri adjustment updated Successfully.";
   }
   
   @RequestMapping(value = "deleteOccasion", method = RequestMethod.GET)
   @ResponseBody
   public String deleteOccasion(HttpServletRequest request, @RequestParam Integer index) {
	   
	   String masjidId = getMasjidId(request);
	   
	   boolean result = deleteOccasion(masjidId, index);
	   
	   if(result) {
		   
		   return "Occasion Deleted Successfully.";
	   } else {
		   
		   return "FAILED";
	   }
   }

   @RequestMapping(value = "updateRefreshRequired", method = RequestMethod.POST)
   @ResponseBody
   public String updateRefreshRequired(@RequestParam String refreshRequired, @RequestParam String id) {
	   
	    boolean result = false;
	   
	    // Make sure ID contains a valid integer
	    try {
	    	Integer.parseInt(id);
	    	result = updateRefreshRequired(id, Boolean.parseBoolean(refreshRequired));
	    	
	    }catch(Exception ex) {
	    	return "Invalid Masjid ID.";
	    } 
	    
	    if(result) {
	    	return "Request for Refresh submitted successfully.";
	    } else {
	    	return "Request Failed.";
	    }
   }
   
   /*private RamzanTimesBean getRamzanTimes(String id) {
	   	try {
	   		int currentDate = new GregorianCalendar().get(Calendar.DATE);
	   		List<RamzanTimesBean> times = jdbcTemplate.query("select * from ramzan_timetable where ramzan_date = " + currentDate 
	   						+ " and masjid_id = " + id, new RamzanTimeMapper());
	   		if(times != null && times.size() > 0) {
	   			return times.get(0);
	   		}
		} catch (Exception e) {
			e.printStackTrace();
		}
	   	
	   	return null;
   }*/
   
   private boolean addOccasion(String masjidId, Date occasionDate, String description) {
	   
	   try {
	   		jdbcTemplate.update("INSERT INTO occasions (DESCRIPTION, OCCASION_DATE, MASJID_ID) VALUES (?, ?, ?)"
	   												, description, occasionDate, masjidId);
	   		return true;
	   		
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
   }
   
   private boolean deleteOccasion(String masjidId, int index) {
	   
	    try {
	   		jdbcTemplate.update("delete from occasions where MASJID_ID = ? and ID = ?", masjidId, index);
	   		return true;
	   		
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
   }
   
   private boolean updateRefreshRequired(String id, boolean refreshRequired) {
	   
	   try {
	   		jdbcTemplate.update("update namaz_times set TIME = ? where MASJID_ID = ? and NAME = ?"
	   								, refreshRequired ? "true" : "false"
	   								, id
	   								, "REFRESH_REQUIRED");
	   		return true;
	   		
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
   }
   
   private boolean updateNamazTime(String masjidId, String name, String time) throws BaseException {
	    try {
	   		jdbcTemplate.update("update namaz_times set TIME = ? where MASJID_ID = ? and NAME = ?", time, masjidId, name);
	   		return true;
	   		
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new BaseException(-1);
		}
   }

   private int getHijriAdjustment(String id) {
	
	   Map<String,String> times = getNamazTimes(id);
	   try {
		   return Integer.parseInt(times.get(KEY_HIJRI_ADJUSTMENT));
				   
	   }catch(Exception ex) {
		   
		   return 0;
	   }
  }
      
   private String getCurrentOccasion(String id) {
	    
	    Calendar currentDate = new GregorianCalendar();
	    try {
	   		List<OccasionBean> occasions = jdbcTemplate.query("select * from occasions where masjid_id = " + id, new OccasionMapper());
	   		for(OccasionBean occasion : occasions) {
	   			Calendar occasionDate = new GregorianCalendar();
	   			occasionDate.setTime(occasion.getDtDate());
	   			
	   			if(currentDate.get(Calendar.DATE) == occasionDate.get(Calendar.DATE) 
	   							&& currentDate.get(Calendar.MONTH) == occasionDate.get(Calendar.MONTH)) {
	   				return occasion.getDescription();
	   			}
	   		}
	   		
		} catch (Exception e) {
			e.printStackTrace();
		}
	   
	   return null;
   }
   
   private Map<String, String> convertToMap(List<NamazBean> beans) {
		
	    Map<String,String> map = new HashMap<String,String>();
		for(NamazBean bean : beans) {
			map.put(bean.getName(), bean.getTime());
		}
		return map;
  }
   
   private boolean authenticateUser(String userId, String password) throws BaseException {
	   
	   // Make sure ID contains a valid integer
	    try {
	    	Integer.parseInt(userId);
	    }catch(Throwable ex) {
	    	throw new BaseException(1);
	    }
	    
	    if(password == null || password.isEmpty()) {

	    	throw new BaseException(12);
	    }
	    
	   	List<MasjidBean> masjidBeans = null;
	   	try {
	   		masjidBeans = jdbcTemplate.query("select * from masjid_master where masjid_id = " + userId, new MasjidBeanMapper());
	   		
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new BaseException(14);
		}
	   	
	   	if(masjidBeans == null || masjidBeans.size() < 1) {
	   		
	   		throw new BaseException(14);
	   	} else {
	   		
	   		if(password.equals(masjidBeans.get(0).getPassword())) {
	   			return true;
	   		} else {
	   			throw new BaseException(14);
	   		}
	   	}
  }
   
  public static void main(String[] args) {
	 System.out.println(new MosqueManagerController().getHijriDate("0"));
  }
}