package com.kreativ.kerp.web;

import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.roo.addon.web.mvc.controller.RooConversionService;

import com.kreativ.kerp.domain.Employee;
import com.kreativ.kerp.domain.Project;
import com.kreativ.kerp.domain.hist.EmployeeHist;
import com.kreativ.kerp.reference.Mst_job_role;
import com.kreativ.kerp.reference.Mst_job_status;

/**
 * A central place to register application Converters and Formatters.
 */
@RooConversionService
public class ApplicationConversionServiceFactoryBean extends
		FormattingConversionServiceFactoryBean {

    public void installLabelConverters(FormatterRegistry registry) {
        registry.addConverter(new EmployeeConverter());
        registry.addConverter(new ProjectConverter());
        registry.addConverter(new EmployeeHistConverter());
        registry.addConverter(new Mst_job_roleConverter());
        registry.addConverter(new Mst_job_statusConverter());
    }
    
	
	@Override
	protected void installFormatters(FormatterRegistry registry) {
		super.installFormatters(registry);
		// Register application converters and formatters
	}

	static class EmployeeConverter implements Converter<Employee, String> {
		public String convert(Employee employee) {
			return new StringBuilder().append(employee.getFirst_name()).append(" ").append(employee.getLast_name()).toString();
		}

	}
	
	 static class ProjectConverter implements Converter<Project, String>  {
	        public String convert(Project project) {
	            return new StringBuilder().append(project.getProject_name()).toString();
	        }
	        
	    }
	    
	    static class EmployeeHistConverter implements Converter<EmployeeHist, String>  {
	        public String convert(EmployeeHist employeeHist) {
	            return new StringBuilder().append(employeeHist.getEmployee()).append(" ").append(employeeHist.getYear()).append(" ").append(employeeHist.getMonth()).toString();
	        }
	        
	    }
	    
	    static class Mst_job_roleConverter implements Converter<Mst_job_role, String>  {
	        public String convert(Mst_job_role mst_job_role) {
	            return new StringBuilder().append(mst_job_role.getJob_role()).toString();
	        }
	        
	    }
	    
	    static class Mst_job_statusConverter implements Converter<Mst_job_status, String>  {
	        public String convert(Mst_job_status mst_job_status) {
	            return new StringBuilder().append(mst_job_status.getJob_status()).toString();
	        }
	    }
	        

}
