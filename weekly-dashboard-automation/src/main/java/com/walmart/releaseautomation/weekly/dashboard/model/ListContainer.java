package com.walmart.releaseautomation.weekly.dashboard.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso(value = { Rule.class })
public class ListContainer<T> implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private List<T> items;

	public ListContainer() {
		this.items = new ArrayList<T>();
	}

	public ListContainer(List<T> list) {
		this.items = list;
	}

	public void add(T t) {
		items.add(t);
	}

	/**
	 * @return the items
	 */
	@XmlAnyElement(lax = true)
	@XmlElementWrapper
	public List<T> getItems() {
		return items;
	}

}
