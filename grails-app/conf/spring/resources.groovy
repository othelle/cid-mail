
import com.othelle.cig.email.ApplicationContextHolder

beans = {
	applicationContextHolder(ApplicationContextHolder) { bean ->
		bean.factoryMethod = 'getInstance'
	}
}

