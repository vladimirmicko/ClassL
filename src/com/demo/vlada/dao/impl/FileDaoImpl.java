package com.demo.vlada.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.vlada.dao.FileDao;
import com.demo.vlada.entities.FileE;

@Repository
public class FileDaoImpl implements FileDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public void saveOrUpdate(FileE file) {
		sessionFactory.getCurrentSession().saveOrUpdate(file);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<FileE> getFiles() {
		return (List<FileE>)sessionFactory.getCurrentSession().createCriteria(FileE.class).list();
	}

	@Override
	@Transactional
	public FileE getFileById(Integer id) {
		return (FileE)sessionFactory.getCurrentSession().createCriteria(FileE.class).add(Restrictions.eq("id", id)).uniqueResult();
	}
	
	@Override
	@Transactional
	public Boolean isFile(FileE file) {
		return (Boolean)(sessionFactory.getCurrentSession().createCriteria(FileE.class).add(Restrictions.eq("name", file.getName())).uniqueResult()!=null)?Boolean.TRUE:Boolean.FALSE;
	}

	@Override
	@Transactional
	public void remove(FileE file) {
		sessionFactory.getCurrentSession().delete(file);
	}

}
