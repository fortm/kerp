package com.kreativ.kerp.timers;

import java.util.Calendar;
import java.util.List;

import com.kreativ.kerp.domain.Employee;
import com.kreativ.kerp.domain.Notifications;
import com.kreativ.kerp.reference.completed;

public class RunMeTask {

	public void act() {

		List<Employee> emp = Employee.findAllEmployees();

		for (int i = 0; i < emp.size(); i++) {

			Calendar cal = Calendar.getInstance();
			cal.setTime(emp.get(i).getJoining_date());
			int join_month = cal.get(Calendar.MONTH);
			int join_year = cal.get(Calendar.YEAR);
			int join_week = cal.get(Calendar.WEEK_OF_MONTH);

			Calendar cal1 = Calendar.getInstance();
			int cur_month = cal1.get(Calendar.MONTH);
			int cur_year = cal1.get(Calendar.YEAR);
			int cur_week = cal1.get(Calendar.WEEK_OF_MONTH);

			Long diffDays =  (cal1.getTimeInMillis() - cal.getTimeInMillis()) / (24 * 60 * 60 * 1000);

			
			//System.out.println(i+" week "+join_week + " month " + join_month + " year " + join_year);
			//System.out.println(i+" week "+cur_week + " month " + cur_month + " year " + cur_year);
			// System.out.println(i+" dif "+diffDays);

			// System.out.println();

			Notifications notifies = new Notifications();

			if (diffDays >= 84 && diffDays <= 90) {

				if (Notifications.findNotificationsesByEmployee(emp.get(i)).getResultList().size() == 0) {

					notifies.setEmployee(emp.get(i));
					notifies.setNotified(completed.COMPLETED_3_MONTH);
					notifies.persist();
				}
			} else if (diffDays >= 173 && diffDays <= 180) {
				if (Notifications.findNotificationsesByEmployee(emp.get(i)).getResultList().size() == 0) {

					notifies.setEmployee(emp.get(i));
					notifies.setNotified(completed.COMPLETED_6_MONTHS);
					notifies.persist();
				}
			}

		}

	}

}
