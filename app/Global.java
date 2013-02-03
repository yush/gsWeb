import play.*;
import java.math.BigDecimal;
import java.util.*;

import com.avaje.ebean.*;

public class Global extends GlobalSettings {

	public void onStart(Application app) {
				
	}

	public void onStop(Application app) {
		Logger.info("Application shutdown...");
		
	}
}