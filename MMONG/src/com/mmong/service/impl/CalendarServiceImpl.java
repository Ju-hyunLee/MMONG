package com.mmong.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmong.dao.CalendarDao;
import com.mmong.service.CalendarService;
import com.mmong.vo.Calendar;
import com.mmong.vo.Member;

@Service
public class CalendarServiceImpl implements CalendarService{
	
	@Autowired
	private CalendarDao dao;

	@Override
	public void insertSchedule(Calendar calendar) {
		dao.insertSchedule(calendar);
	}

	@Override
	public List<Calendar> selectSchedule(String memberId) {
		return dao.selectSchedule(memberId);
	}

	@Override
	public Calendar viewSchedule(int eventNo) {
		return dao.viewSchedule(eventNo);
	}

	@Override
	public void deleteSchedule(int no) {
		dao.deleteSchedule(no);
	}

	@Override
	public void updateSchedule(Calendar calendar) {
		dao.updateSchedule(calendar);
	}

	@Override
	public void deleteGroupDate(int groupDateNo, String memberId) {
		dao.deleteGroupDate(groupDateNo, memberId);
	}

	@Override
	public void deleteFromGroup(int groupDateNo) {
		dao.deleteFromGroup(groupDateNo);
	}

	@Override
	public void updateFromGroup(Calendar calendar) {
		dao.updateFromGroup(calendar);
	}

	@Override
	public void updateFromChart(Calendar calendar) {
		dao.updateFromChart(calendar);
	}

	@Override
	public void deleteFromChart(HashMap<String, Object> map) {
		dao.deleteFromChart(map);
	}

	
}
