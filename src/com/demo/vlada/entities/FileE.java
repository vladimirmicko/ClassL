package com.demo.vlada.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="files")
public class FileE {
	@Id
	@SequenceGenerator(name="files_gen", sequenceName="files_seq", allocationSize=1, initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="files_gen")
	@Column(name="id")
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="file")
	private String fileUrl;
	
	public FileE() {};

	public FileE(String name, String fileUrl) {
		super();
		this.name = name;
		this.fileUrl = fileUrl;
	}

	public FileE(Integer id, String name, String fileUrl) {
		super();
		this.id = id;
		this.name = name;
		this.fileUrl = fileUrl;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	@Override
	public String toString() {
		return "FileE [id=" + id + ", name=" + name + ", fileUrl=" + fileUrl + "]";
	}
}
